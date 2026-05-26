package com.poiji.parser;

public class BooleanParser implements Parser<Boolean> {

    @Override
    public Boolean parse(String value) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @SuppressWarnings("serial")
    public static class BooleanParseException extends RuntimeException {

        public BooleanParseException(String value) {
            super("Can't parse value to Boolean: " + value);
        }
    }
}
