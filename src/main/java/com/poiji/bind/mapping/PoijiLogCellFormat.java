package com.poiji.bind.mapping;

import org.apache.poi.ss.util.CellAddress;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hakan on 26.04.2020
 */
public final class PoijiLogCellFormat {

    private final List<InternalCellFormat> formats = new ArrayList<>();

    public List<InternalCellFormat> formats() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    void addFormat(CellAddress cellAddress, short formatIndex, String formatString, String cellType, String cellStyleStr) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public final static class InternalCellFormat {

        private String cellType;

        private short formatIndex;

        private String formatString;

        private String cellStypeStr;

        private CellAddress cellAddress;

        public CellAddress getCellAddress() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        void setCellAddress(CellAddress cellAddress) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public String getCellStypeStr() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        void setCellStypeStr(String cellStypeStr) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public short getFormatIndex() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        void setFormatIndex(short formatIndex) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public String getFormatString() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        void setFormatString(String formatString) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public String getCellType() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public void setCellType(String cellType) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public String toString() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }
}
