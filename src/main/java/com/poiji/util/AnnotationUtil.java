package com.poiji.util;

import com.poiji.annotation.ExcelCell;
import com.poiji.annotation.ExcelCellName;
import com.poiji.config.Formatting;
import com.poiji.exception.HeaderMissingException;
import com.poiji.option.PoijiOptions;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.function.BiPredicate;
import java.util.stream.Collectors;

/**
 * Created by hakan on 2.05.2020
 */
public final class AnnotationUtil {

    private AnnotationUtil() {
    }

    /**
     * Validate that all headers specified via @ExcelCellName annotations are
     * present in the list of header names.
     * <p>
     * Validation is only performed if it is set in the PoijiOptions
     *
     * @param options      poijoption
     * @param formatting   formatting
     * @param modelType    class model
     * @param titleToIndex tiletoindex
     * @param indexToTitle indextoTitle
     * @param <T>          model Type
     * @throws HeaderMissingException if one or more headers are missing
     */
    public static <T> void validateMandatoryNameColumns(PoijiOptions options, Formatting formatting, Class<T> modelType, Map<String, Integer> titleToIndex, Map<Integer, String> indexToTitle) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
