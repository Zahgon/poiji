package com.poiji.parser;

import java.text.NumberFormat;
import java.util.Locale;

/**
 * @see <a href="https://www.ibm.com/developerworks/library/j-numberformat/index.html">Resolving NumberFormat's parsing issues</a>
 */
public class Parsers {

    private Parsers() {
        // static factory
    }

    public static NumberParser longs() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static NumberParser integers() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static BigDecimalParser bigDecimals(Locale locale) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static NumberParser numbers(Locale locale) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static BooleanParser booleans() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
