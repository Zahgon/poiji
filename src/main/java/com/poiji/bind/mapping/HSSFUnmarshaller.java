package com.poiji.bind.mapping;

import com.poiji.annotation.DisableCellFormatXLS;
import com.poiji.annotation.ExcelCell;
import com.poiji.annotation.ExcelCellName;
import com.poiji.annotation.ExcelCellRange;
import com.poiji.annotation.ExcelRow;
import com.poiji.annotation.ExcelUnknownCells;
import com.poiji.annotation.ExcelCellsJoinedByName;
import com.poiji.bind.Unmarshaller;
import com.poiji.config.Casting;
import com.poiji.config.Formatting;
import com.poiji.exception.IllegalCastException;
import com.poiji.exception.PoijiMultiRowException;
import com.poiji.exception.PoijiMultiRowException.PoijiRowSpecificException;
import com.poiji.option.PoijiOptions;
import com.poiji.util.AnnotationUtil;
import com.poiji.util.ReflectUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MultiValuedMap;
import org.apache.poi.hssf.usermodel.HSSFFormulaEvaluator;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.formula.BaseFormulaEvaluator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.util.StringUtil;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.Consumer;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import static java.lang.String.valueOf;

/**
 * responsible for xls files
 * <p>
 * Created by hakan on 16/01/2017.
 */
abstract class HSSFUnmarshaller extends PoijiWorkBook implements Unmarshaller {

    private final DataFormatter dataFormatter;

    protected final PoijiOptions options;

    private final Casting casting;

    private final Formatting formatting;

    private final Map<String, Integer> titleToIndex;

    private final Map<Integer, String> indexToTitle;

    private final int limit;

    private int internalCount;

    BaseFormulaEvaluator baseFormulaEvaluator;

    HSSFUnmarshaller(PoijiOptions options) {
        this.options = options;
        this.limit = options.getLimit();
        dataFormatter = new DataFormatter();
        titleToIndex = new HashMap<>();
        indexToTitle = new HashMap<>();
        this.casting = options.getCasting();
        this.formatting = options.getFormatting();
    }

    @Override
    public <T> void unmarshal(Class<T> type, Consumer<? super T> consumer) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    <T> void processRowsToObjects(Sheet sheet, Class<T> type, Consumer<? super T> consumer) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private Sheet getSheetToProcess(Workbook workbook, PoijiOptions options, String sheetName) {
        int nonHiddenSheetIndex = 0;
        int requestedIndex = options.sheetIndex();
        Sheet sheet = null;
        if (options.ignoreHiddenSheets()) {
            for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
                if (!workbook.isSheetHidden(i) && !workbook.isSheetVeryHidden(i)) {
                    if (sheetName == null) {
                        if (nonHiddenSheetIndex == requestedIndex) {
                            return workbook.getSheetAt(i);
                        }
                    } else {
                        if (workbook.getSheetName(i).equalsIgnoreCase(sheetName)) {
                            return workbook.getSheetAt(i);
                        }
                    }
                    nonHiddenSheetIndex++;
                }
            }
        } else {
            if (sheetName == null) {
                sheet = workbook.getSheetAt(requestedIndex);
            } else {
                sheet = workbook.getSheet(sheetName);
            }
        }
        return sheet;
    }

    private void loadColumnTitles(Sheet sheet, int maxPhysicalNumberOfRows) {
        if (maxPhysicalNumberOfRows > 0) {
            int row = options.getHeaderStart();
            int headerCount = options.getHeaderCount();
            if (headerCount == 0) {
                return;
            }
            for (short i = 0; i < headerCount; i++) {
                Row firstRow = sheet.getRow(row + i);
                for (Cell cell : firstRow) {
                    final int columnIndex = cell.getColumnIndex();
                    final String titleName = formatting.transform(options, cell.getStringCellValue());
                    indexToTitle.put(columnIndex, getTitleNameForMap(titleName, columnIndex));
                    titleToIndex.put(titleName, columnIndex);
                }
            }
        }
    }

    private String getTitleNameForMap(String cellContent, int columnIndex) {
        if (indexToTitle.containsValue(cellContent) || cellContent.isEmpty()) {
            return cellContent + "@" + columnIndex;
        } else {
            return cellContent;
        }
    }

    <T> T deserializeRowToInstance(Row currentRow, Class<T> type) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private <T> T deserializeRowToRecordInstance(Row currentRow, Class<T> type) {
        Map<String, Object> recordValues = new HashMap<>();
        setFieldValuesFromRowIntoRecordMap(currentRow, type, recordValues);
        return ReflectUtil.newRecordInstance(type, recordValues);
    }

    private <T> void setFieldValuesFromRowIntoRecordMap(Row currentRow, Class<? super T> subclass, Map<String, Object> recordValues) {
        if (subclass == null) {
            return;
        }
        setFieldValuesFromRowIntoRecordMap(currentRow, subclass.getSuperclass(), recordValues);
        tailSetFieldValueForRecord(currentRow, subclass, recordValues);
    }

    private <T> void tailSetFieldValueForRecord(Row currentRow, Class<? super T> type, Map<String, Object> recordValues) {
        List<Integer> mappedColumnIndices = new ArrayList<>();
        List<Field> unknownCells = new ArrayList<>();
        List<PoijiRowSpecificException> errors = new ArrayList<>();
        for (Field field : type.getDeclaredFields()) {
            if (field.getAnnotation(ExcelRow.class) != null) {
                final int rowNum = currentRow.getRowNum();
                final Object data = casting.castValue(field, valueOf(rowNum), rowNum, -1, options);
                recordValues.put(field.getName(), data);
            } else if (field.getAnnotation(ExcelCellRange.class) != null) {
                Class<?> fieldType = field.getType();
                Object fieldInstance = ReflectUtil.newInstanceOf(fieldType);
                for (Field fieldField : fieldType.getDeclaredFields()) {
                    mapColumns(currentRow, fieldInstance, mappedColumnIndices, errors, fieldField);
                }
                recordValues.put(field.getName(), fieldInstance);
            } else if (field.getAnnotation(ExcelUnknownCells.class) != null) {
                unknownCells.add(field);
            } else {
                mapColumnsForRecord(currentRow, recordValues, mappedColumnIndices, errors, field);
            }
        }
        if (!errors.isEmpty()) {
            throw new PoijiMultiRowException("Problem(s) occurred while reading data", errors);
        }
        if (unknownCells.isEmpty()) {
            return;
        }
        if (!indexToTitle.isEmpty()) {
            Map<String, String> excelUnknownCellsMap = StreamSupport.stream(Spliterators.spliteratorUnknownSize(currentRow.cellIterator(), Spliterator.ORDERED), false).filter(cell -> !mappedColumnIndices.contains(cell.getColumnIndex())).collect(Collectors.toMap(cell -> indexToTitle.get(cell.getColumnIndex()), Object::toString));
            unknownCells.forEach(field -> recordValues.put(field.getName(), excelUnknownCellsMap));
        }
    }

    private void mapColumnsForRecord(Row currentRow, Map<String, Object> recordValues, List<Integer> mappedColumnIndices, List<PoijiRowSpecificException> errors, Field field) {
        try {
            mappedColumnIndices.add(tailSetFieldValueForRecord(currentRow, recordValues, field));
        } catch (PoijiRowSpecificException poijiRowException) {
            errors.add(poijiRowException);
        }
    }

    private Integer tailSetFieldValueForRecord(Row currentRow, Map<String, Object> recordValues, Field field) {
        final FieldAnnotationDetail annotationDetail = getFieldColumn(field);
        if (annotationDetail.getColumn() != null) {
            constructTypeValueForRecord(currentRow, recordValues, field, annotationDetail);
        }
        if (CollectionUtils.isNotEmpty(annotationDetail.getColumns())) {
            for (Integer column : annotationDetail.getColumns()) {
                annotationDetail.setColumn(column);
                constructTypeValueForRecord(currentRow, recordValues, field, annotationDetail);
            }
        }
        return annotationDetail.getColumn();
    }

    private <T> void constructTypeValueForRecord(Row currentRow, Map<String, Object> recordValues, Field field, FieldAnnotationDetail annotationDetail) {
        Cell cell = currentRow.getCell(annotationDetail.getColumn());
        if (cell != null) {
            if (annotationDetail.isDisabledCellFormat()) {
                cell.setCellStyle(null);
            }
            String value;
            if (options.isRawData() && isCellNumeric(cell)) {
                value = NumberToTextConverter.toText(cell.getNumericCellValue());
            } else {
                value = dataFormatter.formatCellValue(cell, baseFormulaEvaluator);
            }
            Object data = casting.castValue(field, value, currentRow.getRowNum(), annotationDetail.getColumn(), options);
            constructTypeForRecord(recordValues, field, annotationDetail, data);
        } else if (options.isProcessEmptyCell()) {
            // Process empty cells by setting them to empty string
            Object data = casting.castValue(field, "", currentRow.getRowNum(), annotationDetail.getColumn(), options);
            constructTypeForRecord(recordValues, field, annotationDetail, data);
        } else if (annotationDetail.isMandatoryCell()) {
            throw new PoijiRowSpecificException(annotationDetail.getColumnName(), field.getName(), currentRow.getRowNum());
        }
    }

    private void constructTypeForRecord(Map<String, Object> recordValues, Field field, FieldAnnotationDetail annotationDetail, Object data) {
        if (!annotationDetail.isMultiValueMap()) {
            recordValues.put(field.getName(), data);
        } else {
            String titleColumn = indexToTitle.get(annotationDetail.getColumn());
            titleColumn = titleColumn.replaceAll("@[0-9]+", "");
            // For records with MultiValuedMap, we need to collect values into the map
            fillMultiValueMap(recordValues, field, data, titleColumn);
        }
    }

    static void fillMultiValueMap(Map<String, Object> recordValues, Field field, Object data, String titleColumn) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private <T> T tailSetFieldValue(Row currentRow, Class<? super T> type, T instance) {
        List<Integer> mappedColumnIndices = new ArrayList<>();
        List<Field> unknownCells = new ArrayList<>();
        List<PoijiRowSpecificException> errors = new ArrayList<>();
        for (Field field : type.getDeclaredFields()) {
            if (field.getModifiers() == 25) {
                continue;
            }
            if (field.getAnnotation(ExcelRow.class) != null) {
                final int rowNum = currentRow.getRowNum();
                final Object data = casting.castValue(field, valueOf(rowNum), rowNum, -1, options);
                setFieldData(instance, field, data);
            } else if (field.getAnnotation(ExcelCellRange.class) != null) {
                Class<?> fieldType = field.getType();
                Object fieldInstance = ReflectUtil.newInstanceOf(fieldType);
                for (Field fieldField : fieldType.getDeclaredFields()) {
                    mapColumns(currentRow, fieldInstance, mappedColumnIndices, errors, fieldField);
                }
                setFieldData(instance, field, fieldInstance);
            } else if (field.getAnnotation(ExcelUnknownCells.class) != null) {
                unknownCells.add(field);
            } else {
                mapColumns(currentRow, instance, mappedColumnIndices, errors, field);
            }
        }
        if (!errors.isEmpty()) {
            throw new PoijiMultiRowException("Problem(s) occurred while reading data", errors);
        }
        if (unknownCells.isEmpty()) {
            return instance;
        }
        if (!indexToTitle.isEmpty()) {
            Map<String, String> excelUnknownCellsMap = StreamSupport.stream(Spliterators.spliteratorUnknownSize(currentRow.cellIterator(), Spliterator.ORDERED), false).filter(cell -> !mappedColumnIndices.contains(cell.getColumnIndex())).collect(Collectors.toMap(cell -> indexToTitle.get(cell.getColumnIndex()), Object::toString));
            unknownCells.forEach(field -> setFieldData(instance, field, excelUnknownCellsMap));
        } else {
            Map<String, String> excelUnknownCellsMap = StreamSupport.stream(Spliterators.spliteratorUnknownSize(currentRow.cellIterator(), Spliterator.ORDERED), false).filter(cell -> !mappedColumnIndices.contains(cell.getColumnIndex())).collect(Collectors.toMap(cell -> valueOf(cell.getColumnIndex()), Object::toString));
            unknownCells.forEach(field -> setFieldData(instance, field, excelUnknownCellsMap));
        }
        return instance;
    }

    private <T> void mapColumns(Row currentRow, T instance, List<Integer> mappedColumnIndices, List<PoijiRowSpecificException> errors, Field field) {
        try {
            mappedColumnIndices.add(tailSetFieldValue(currentRow, instance, field));
        } catch (PoijiRowSpecificException poijiRowException) {
            errors.add(poijiRowException);
        }
    }

    private <T> Integer tailSetFieldValue(Row currentRow, T instance, Field field) {
        final FieldAnnotationDetail annotationDetail = getFieldColumn(field);
        if (annotationDetail.getColumn() != null) {
            constructTypeValue(currentRow, instance, field, annotationDetail);
        }
        if (CollectionUtils.isNotEmpty(annotationDetail.getColumns())) {
            for (Integer column : annotationDetail.getColumns()) {
                annotationDetail.setColumn(column);
                constructTypeValue(currentRow, instance, field, annotationDetail);
            }
        }
        return annotationDetail.getColumn();
    }

    private FieldAnnotationDetail getFieldColumn(final Field field) {
        DisableCellFormatXLS disableCellFormat = field.getAnnotation(DisableCellFormatXLS.class);
        final FieldAnnotationDetail annotationDetail = new FieldAnnotationDetail();
        if (disableCellFormat != null) {
            annotationDetail.setDisabledCellFormat(disableCellFormat.value());
        }
        ExcelCell index = field.getAnnotation(ExcelCell.class);
        if (index != null) {
            annotationDetail.setColumn(index.value());
            annotationDetail.setMandatoryCell(index.mandatoryCell());
        }
        ExcelCellName excelCellName = field.getAnnotation(ExcelCellName.class);
        if (excelCellName != null) {
            annotationDetail.setMandatoryCell(excelCellName.mandatoryCell());
            annotationDetail.setColumnName(excelCellName.value());
            Integer column = findTitleColumn(excelCellName);
            annotationDetail.setColumn(column);
        }
        ExcelCellsJoinedByName excelCellsJoinedByName = field.getAnnotation(ExcelCellsJoinedByName.class);
        if (excelCellsJoinedByName != null) {
            String expression = excelCellsJoinedByName.expression();
            Pattern pattern = Pattern.compile(expression);
            List<Integer> columns = indexToTitle.entrySet().stream().filter(entry -> pattern.matcher(entry.getValue().replaceAll("@[0-9]+", "")).matches()).map(Map.Entry::getKey).collect(Collectors.toList());
            annotationDetail.setColumns(columns);
            annotationDetail.setMultiValueMap(CollectionUtils.isNotEmpty(columns));
        }
        return annotationDetail;
    }

    public Integer findTitleColumn(ExcelCellName excelCellName) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private <T> void constructTypeValue(Row currentRow, T instance, Field field, FieldAnnotationDetail annotationDetail) {
        Cell cell = currentRow.getCell(annotationDetail.getColumn());
        if (cell != null) {
            if (annotationDetail.isDisabledCellFormat()) {
                cell.setCellStyle(null);
            }
            String value;
            if (options.isRawData() && isCellNumeric(cell)) {
                value = NumberToTextConverter.toText(cell.getNumericCellValue());
            } else {
                value = dataFormatter.formatCellValue(cell, baseFormulaEvaluator);
            }
            Object data = casting.castValue(field, value, currentRow.getRowNum(), annotationDetail.getColumn(), options);
            if (!annotationDetail.isMultiValueMap()) {
                setFieldData(instance, field, data);
            } else {
                String titleColumn = indexToTitle.get(annotationDetail.getColumn());
                titleColumn = titleColumn.replaceAll("@[0-9]+", "");
                putFieldMultiValueMapData(instance, field, titleColumn, data);
            }
        } else if (options.isProcessEmptyCell()) {
            // Process empty cells by setting them to empty string
            Object data = casting.castValue(field, "", currentRow.getRowNum(), annotationDetail.getColumn(), options);
            if (!annotationDetail.isMultiValueMap()) {
                setFieldData(instance, field, data);
            } else {
                String titleColumn = indexToTitle.get(annotationDetail.getColumn());
                titleColumn = titleColumn.replaceAll("@[0-9]+", "");
                putFieldMultiValueMapData(instance, field, titleColumn, data);
            }
        } else if (annotationDetail.isMandatoryCell()) {
            throw new PoijiRowSpecificException(annotationDetail.getColumnName(), field.getName(), currentRow.getRowNum());
        }
    }

    private boolean isCellNumeric(Cell cell) {
        return (cell.getCellType() == CellType.NUMERIC || (cell.getCellType() == CellType.FORMULA && cell.getCachedFormulaResultType() == CellType.NUMERIC));
    }

    private <T> void setFieldData(T instance, Field field, Object data) {
        try {
            field.setAccessible(true);
            field.set(instance, data);
        } catch (IllegalAccessException e) {
            throw new IllegalCastException("Unexpected cast type {" + data + "} of field" + field.getName());
        }
    }

    public void putFieldMultiValueMapData(Object instance, Field field, String columnName, Object o) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private <T> T setFieldValuesFromRowIntoInstance(Row currentRow, Class<? super T> subclass, T instance) {
        return subclass == null ? instance : tailSetFieldValue(currentRow, subclass, setFieldValuesFromRowIntoInstance(currentRow, subclass.getSuperclass(), instance));
    }

    boolean skip(final Row currentRow, int skip) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    boolean isRowEmpty(Row row) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private static class FieldAnnotationDetail {

        private Integer column;

        private String columnName;

        private boolean disabledCellFormat;

        private boolean mandatoryCell;

        private List<Integer> columns;

        private boolean multiValueMap;

        Integer getColumn() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        void setColumn(Integer column) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public String getColumnName() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public void setColumnName(String columnName) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        boolean isDisabledCellFormat() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        void setDisabledCellFormat(boolean disabledCellFormat) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public boolean isMandatoryCell() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public void setMandatoryCell(boolean mandatoryCell) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public List<Integer> getColumns() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public void setColumns(List<Integer> columns) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public boolean isMultiValueMap() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public void setMultiValueMap(boolean multiValueMap) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }
}
