package com.poiji.bind.mapping;

import org.apache.poi.xssf.model.StylesTable;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * Created by hakan on 26.04.2020
 */
public final class PoijiNumberFormat {

    private final SortedMap<Short, String> numberFormats = new TreeMap<>();

    public void putNumberFormat(short index, String fmt) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public String getNumberFormatAt(short fmtId) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    void overrideExcelNumberFormats(final StylesTable styles) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
