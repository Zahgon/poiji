package com.poiji.config;

public final class DefaultCastingError {

    private String value;

    private Object defaultValue;

    private String sheetName;

    private int row;

    private int column;

    private Exception exception;

    public DefaultCastingError(String value, Object defaultValue, String sheetName, int row, int column, Exception exception) {
        this.value = value;
        this.defaultValue = defaultValue;
        this.sheetName = sheetName;
        this.row = row;
        this.column = column;
        this.exception = exception;
    }

    public String getValue() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public Object getDefaultValue() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public String getSheetName() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public int getRow() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public int getColumn() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public Exception getException() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
