package com.poiji.option;

import com.poiji.annotation.ExcelCellName;
import com.poiji.bind.mapping.PoijiLogCellFormat;
import com.poiji.bind.mapping.PoijiNumberFormat;
import com.poiji.config.Casting;
import com.poiji.config.DefaultCasting;
import com.poiji.config.DefaultFormatting;
import com.poiji.config.Formatting;
import com.poiji.exception.PoijiException;
import org.apache.poi.util.LocaleUtil;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Objects;
import static com.poiji.util.PoijiConstants.DEFAULT_DATE_FORMATTER;
import static com.poiji.util.PoijiConstants.DEFAULT_DATE_PATTERN;
import static com.poiji.util.PoijiConstants.DEFAULT_DATE_TIME_FORMATTER;
import static com.poiji.util.PoijiConstants.DEFAULT_TIME_FORMATTER;

/**
 * Created by hakan on 17/01/2017.
 */
public final class PoijiOptions {

    private int skip;

    private int limit;

    private int sheetIndex;

    private String password;

    private String dateRegex;

    private String timeRegex;

    private String dateTimeRegex;

    private String datePattern;

    private boolean dateLenient;

    private boolean trimCellValue;

    private boolean ignoreHiddenSheets;

    private boolean preferNullOverDefault;

    private DateTimeFormatter dateFormatter;

    private DateTimeFormatter timeFormatter;

    private DateTimeFormatter dateTimeFormatter;

    private Casting casting;

    private int headerStart;

    private int headerCount;

    private String sheetName;

    private boolean caseInsensitive;

    private boolean ignoreWhitespaces;

    private PoijiLogCellFormat poijiLogCellFormat;

    private PoijiNumberFormat numberFormat;

    private boolean disableXLSXNumberCellFormat;

    private String listDelimiter;

    private Formatting formatting;

    private Locale locale;

    private boolean rawData;

    private boolean ignoreFileExtension;

    private boolean processEmptyCell;

    public PoijiNumberFormat getPoijiNumberFormat() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private PoijiOptions setPoijiNumberFormat(PoijiNumberFormat numberFormat) {
        this.numberFormat = numberFormat;
        return this;
    }

    public PoijiLogCellFormat getPoijiCellFormat() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private PoijiOptions setPoijiLogCellFormat(PoijiLogCellFormat poijiLogCellFormat) {
        this.poijiLogCellFormat = poijiLogCellFormat;
        return this;
    }

    private PoijiOptions() {
        super();
    }

    private PoijiOptions setSkip(int skip) {
        this.skip = skip;
        return this;
    }

    public int getLimit() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public PoijiOptions setLimit(int limit) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private PoijiOptions setDatePattern(String datePattern) {
        this.datePattern = datePattern;
        return this;
    }

    private PoijiOptions setDateFormatter(DateTimeFormatter dateFormatter) {
        this.dateFormatter = dateFormatter;
        return this;
    }

    private PoijiOptions setTimeFormatter(DateTimeFormatter timeFormatter) {
        this.timeFormatter = timeFormatter;
        return this;
    }

    private PoijiOptions setDateTimeFormatter(DateTimeFormatter dateTimeFormatter) {
        this.dateTimeFormatter = dateTimeFormatter;
        return this;
    }

    private PoijiOptions setPreferNullOverDefault(boolean preferNullOverDefault) {
        this.preferNullOverDefault = preferNullOverDefault;
        return this;
    }

    public String getPassword() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private PoijiOptions setPassword(String password) {
        this.password = password;
        return this;
    }

    public String datePattern() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public DateTimeFormatter dateFormatter() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public DateTimeFormatter timeFormatter() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public DateTimeFormatter dateTimeFormatter() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public boolean preferNullOverDefault() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * the number of skipped rows
     *
     * @return n rows skipped
     */
    public int skip() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public boolean ignoreHiddenSheets() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private PoijiOptions setIgnoreHiddenSheets(boolean ignoreHiddenSheets) {
        this.ignoreHiddenSheets = ignoreHiddenSheets;
        return this;
    }

    public boolean trimCellValue() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public PoijiOptions setTrimCellValue(boolean trimCellValue) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public Casting getCasting() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public PoijiOptions setCasting(Casting casting) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private PoijiOptions setSheetIndex(int sheetIndex) {
        this.sheetIndex = sheetIndex;
        return this;
    }

    public int sheetIndex() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public String getDateRegex() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private PoijiOptions setDateRegex(String dateRegex) {
        this.dateRegex = dateRegex;
        return this;
    }

    public String getTimeRegex() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private PoijiOptions setTimeRegex(String timeRegex) {
        this.timeRegex = timeRegex;
        return this;
    }

    public String getDateTimeRegex() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private PoijiOptions setDateTimeRegex(String dateTimeRegex) {
        this.dateTimeRegex = dateTimeRegex;
        return this;
    }

    public boolean getDateLenient() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private PoijiOptions setDateLenient(boolean dateLenient) {
        this.dateLenient = dateLenient;
        return this;
    }

    public int getHeaderStart() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public int getHeaderCount() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private PoijiOptions setHeaderStart(int headerStart) {
        this.headerStart = headerStart;
        return this;
    }

    private PoijiOptions setHeaderCount(int headerCount) {
        this.headerCount = headerCount;
        return this;
    }

    private PoijiOptions setSheetName(String sheetName) {
        this.sheetName = sheetName;
        return this;
    }

    public String getSheetName() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public boolean getCaseInsensitive() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private PoijiOptions setCaseInsensitive(final boolean caseInsensitive) {
        this.caseInsensitive = caseInsensitive;
        return this;
    }

    public boolean getIgnoreWhitespaces() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private PoijiOptions setIgnoreWhitespaces(final boolean ignoreWhitespaces) {
        this.ignoreWhitespaces = ignoreWhitespaces;
        return this;
    }

    private PoijiOptions disableXLSXNumberCellFormat(boolean disableXLSXNumberCellFormat) {
        this.disableXLSXNumberCellFormat = disableXLSXNumberCellFormat;
        return this;
    }

    public boolean isDisableXLSXNumberCellFormat() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public String getListDelimiter() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private PoijiOptions setListDelimiter(String listDelimiter) {
        this.listDelimiter = listDelimiter;
        return this;
    }

    public Formatting getFormatting() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private PoijiOptions setFormatting(Formatting formatting) {
        this.formatting = formatting;
        return this;
    }

    public Locale getLocale() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private PoijiOptions setLocale(Locale locale) {
        if (!this.rawData) {
            this.locale = locale;
            LocaleUtil.setUserLocale(locale);
        }
        return this;
    }

    public boolean isRawData() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private PoijiOptions setRawData(boolean rawData) {
        if (rawData) {
            setLocale(Locale.US);
        }
        this.rawData = rawData;
        return this;
    }

    public boolean ignoreFileExtension() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private PoijiOptions setIgnoreFileExtension(boolean ignoreFileExtension) {
        this.ignoreFileExtension = ignoreFileExtension;
        return this;
    }

    public boolean isProcessEmptyCell() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private PoijiOptions setProcessEmptyCell(boolean processEmptyCell) {
        this.processEmptyCell = processEmptyCell;
        return this;
    }

    public static class PoijiOptionsBuilder {

        private int sheetIndex;

        private String password;

        private String dateRegex;

        private String timeRegex;

        private String dateTimeRegex;

        private boolean dateLenient;

        private boolean trimCellValue;

        private boolean ignoreHiddenSheets;

        private boolean preferNullOverDefault;

        private String datePattern = DEFAULT_DATE_PATTERN;

        private DateTimeFormatter dateFormatter = DEFAULT_DATE_FORMATTER;

        private DateTimeFormatter timeFormatter = DEFAULT_TIME_FORMATTER;

        private DateTimeFormatter dateTimeFormatter = DEFAULT_DATE_TIME_FORMATTER;

        private Casting casting = new DefaultCasting();

        private Formatting formatting = new DefaultFormatting();

        private PoijiLogCellFormat cellFormat;

        private PoijiNumberFormat numberFormat = StylesFormatHelper.loadDefaultStyles();

        private int headerStart = 0;

        private int headerCount = 1;

        private int skip = 1;

        private int limit = 0;

        private String sheetName;

        private boolean caseInsensitive;

        private boolean ignoreWhitespaces;

        private boolean disabledXLSXNumberCellFormat;

        private String listDelimiter = "\\s*,\\s*";

        private Locale locale = Locale.US;

        private boolean rawData;

        private boolean ignoreFileExtension;

        private boolean processEmptyCell;

        private PoijiOptionsBuilder() {
        }

        private PoijiOptionsBuilder(int skip) {
            this.skip = skip;
        }

        /**
         * Skip a number of rows after the header row. The header row is not counted.
         *
         * @param skip ignored row number after the header row
         * @return builder itself
         */
        public static PoijiOptionsBuilder settings(int skip) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public static PoijiOptionsBuilder settings() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * set a date formatter, default date time formatter is "dd/M/yyyy"
         * for java.time.LocalDate
         *
         * @param dateFormatter date time formatter
         * @return this
         */
        public PoijiOptionsBuilder dateFormatter(DateTimeFormatter dateFormatter) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * set a time formatter, default time formatter
         * is {@link com.poiji.util.PoijiConstants#DEFAULT_TIME_FORMATTER}
         * for {@link java.time.LocalTime}
         *
         * @param timeFormatter time formatter
         * @return this
         */
        public PoijiOptionsBuilder timeFormatter(DateTimeFormatter timeFormatter) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * set a date time formatter, default date time formatter is "dd/M/yyyy
         * HH:mm:ss"
         * for java.time.LocalDateTime
         *
         * @param dateTimeFormatter date time formatter
         * @return this
         */
        public PoijiOptionsBuilder dateTimeFormatter(DateTimeFormatter dateTimeFormatter) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * set date pattern, default date format is "dd/M/yyyy" for
         * java.util.Date
         *
         * @param datePattern date time formatter
         * @return this
         */
        public PoijiOptionsBuilder datePattern(String datePattern) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * set whether or not to use null instead of default values for Integer,
         * Double, Float, Long, String and java.util.Date types.
         *
         * @param preferNullOverDefault boolean
         * @return this
         */
        public PoijiOptionsBuilder preferNullOverDefault(boolean preferNullOverDefault) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Set the {@link Locale} used by Apache Poi and PoiJ. Default is
         * {@link Locale#ENGLISH}.
         * This setting is only used by Apache Poi thread and PoiJ. See
         * {@link org.apache.poi.util.LocaleUtil}
         * for more details.
         *
         * @param locale Locale
         * @return this
         */
        public PoijiOptionsBuilder setLocale(Locale locale) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public PoijiOptions build() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * set sheet index, default is 0
         *
         * @param sheetIndex number
         * @return this
         */
        public PoijiOptionsBuilder sheetIndex(int sheetIndex) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Set the sheet Name
         *
         * @param sheetName excel sheet name
         * @return this
         */
        public PoijiOptionsBuilder sheetName(String sheetName) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * skip a number of rows after the header row. The header row is not counted.
         *
         * @param skip number
         * @return this
         */
        public PoijiOptionsBuilder skip(int skip) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * limit a number of rows after the header &amp; skipped rows row. The header
         * &amp; skipped rows are not counted.
         *
         * @param limit number
         * @return this
         */
        public PoijiOptionsBuilder limit(int limit) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * set password for encrypted excel file, Default is null
         *
         * @param password excel password
         * @return this
         */
        public PoijiOptionsBuilder password(String password) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Ignore hidden sheets
         *
         * @param ignoreHiddenSheets whether or not to ignore any hidden sheets
         *                           in the work book.
         * @return this
         */
        public PoijiOptionsBuilder ignoreHiddenSheets(boolean ignoreHiddenSheets) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Trim cell value
         *
         * @param trimCellValue trim the cell value before processing work book.
         * @return this
         */
        public PoijiOptionsBuilder trimCellValue(boolean trimCellValue) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Date regex, if would like to specify a regex patter the date must be
         * in, e.g.\\d{2}/\\d{1}/\\d{4}.
         *
         * @param dateRegex date regex pattern
         * @return this
         */
        public PoijiOptionsBuilder dateRegex(String dateRegex) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Time regex, if you would like to specify a regex pattern the time must be
         * in, e.g. {@literal \\d{2}:\\d{2}:\\d{2} }.
         *
         * @param timeRegex time regex pattern
         * @return this
         */
        public PoijiOptionsBuilder timeRegex(String timeRegex) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * DateTime regex, if would like to specify a regex patter the date time must be
         * in, e.g.\\d{2}/\\d{1}/\\d{4} \\d{2}:\\d{2}:\\d{2}.
         *
         * @param dateTimeRegex date regex pattern
         * @return this
         */
        public PoijiOptionsBuilder dateTimeRegex(String dateTimeRegex) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * If the simple date format is lenient, use to
         * set how strict the date formatting must be, defaults to lenient false.
         * It works only for java.util.Date.
         *
         * @param dateLenient true or false
         * @return this
         */
        public PoijiOptionsBuilder dateLenient(boolean dateLenient) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Use a custom casting implementation
         *
         * @param casting custom casting implementation
         * @return this
         */
        public PoijiOptionsBuilder withCasting(Casting casting) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * This is to set the row which the unmarshall will
         * use to start reading header titles, incase the
         * header is not in row 0.
         *
         * @param headerStart an index number of the excel header to start reading
         *                    header
         * @return this
         */
        public PoijiOptionsBuilder headerStart(int headerStart) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * This is to set the number of row contains headers
         *
         * Set 0 to indicate that no header in the excel file.
         * Default - 1.
         *
         * @param headerCount an index number of the excel header to start reading
         *                    header
         * @return this
         */
        public PoijiOptionsBuilder headerCount(int headerCount) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Permits case insensitive column names mapping for annotation
         * {@link ExcelCellName}.
         * Default - false.
         *
         * @param caseInsensitive true or false
         * @return this
         */
        public PoijiOptionsBuilder caseInsensitive(final boolean caseInsensitive) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Ignore white space before and after column names for annotation
         * {@link ExcelCellName}.
         * Default - false.
         *
         * @param ignoreWhitespaces true or false
         * @return this
         */
        public PoijiOptionsBuilder ignoreWhitespaces(final boolean ignoreWhitespaces) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Add cell format option to see each internal cell's excel format for files
         * ending with xlsx format.
         * This option should be enabled for debugging purpose.
         *
         * @param cellFormat poiji cell format instance
         * @return this
         */
        public PoijiOptionsBuilder poijiLogCellFormat(final PoijiLogCellFormat cellFormat) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Change the default cell formats of a xlsx excel file by overriding
         *
         * @param numberFormat poiji number format instance
         * @return this
         */
        public PoijiOptionsBuilder poijiNumberFormat(final PoijiNumberFormat numberFormat) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Disable the cell format of all the number cells of an excel file ending with
         * xlsx
         *
         * @return this
         */
        public PoijiOptionsBuilder disableXLSXNumberCellFormat() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Use different delimiter to split the list of items of a cell
         *
         * @param delimiter by default delimiter is ','
         * @return this
         */
        public PoijiOptionsBuilder addListDelimiter(String delimiter) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Use a custom excel header format implementation
         *
         * @param formatting custom header format implementation
         * @return this
         */
        public PoijiOptionsBuilder withFormatting(Formatting formatting) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Use this option to get the underlying/original/non-visible/raw cell value.
         * The cell must be a numeric type.
         *
         * @param status set true to retrieve the underlying data in the excel file.
         * @return this
         */
        public PoijiOptionsBuilder rawData(boolean status) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Ignore file extension validation. When set to true, Poiji will not throw
         * InvalidExcelFileExtension if the file extension is not .xls or .xlsx.
         * This is useful when working with temporary files that don't have proper extensions.
         * <p>
         * <strong>Note:</strong> Files will be treated as XLSX format when the extension is ignored.
         * XLS files without proper extensions are not supported with this option.
         * This option only works with {@code fromExcel} methods, not {@code fromExcelProperties}.
         *
         * @param ignoreFileExtension set true to skip file extension validation
         * @return this
         */
        public PoijiOptionsBuilder ignoreFileExtension(boolean ignoreFileExtension) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Process empty cells/rows during deserialization.
         * When set to true, rows with no data will be processed and deserialized.
         * Default is false.
         *
         * @param processEmptyCell set true to process empty cells
         * @return this
         */
        public PoijiOptionsBuilder processEmptyCell(boolean processEmptyCell) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }
}
