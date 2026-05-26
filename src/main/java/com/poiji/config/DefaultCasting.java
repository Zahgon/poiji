package com.poiji.config;

import com.poiji.exception.PoijiException;
import com.poiji.option.PoijiOptions;
import com.poiji.parser.BooleanParser;
import com.poiji.parser.Parsers;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by hakan on 22/01/2017.
 */
public class DefaultCasting implements Casting {

    private final boolean errorLoggingEnabled;

    private final List<DefaultCastingError> errors = new ArrayList<>();

    public DefaultCasting() {
        this(false);
    }

    public DefaultCasting(boolean errorLoggingEnabled) {
        this.errorLoggingEnabled = errorLoggingEnabled;
    }

    protected <T> T onError(String value, String sheetName, int row, int col, Exception exception, T defaultValue) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private void logError(String value, Object defaultValue, String sheetName, int row, int col, Exception exception) {
        if (errorLoggingEnabled) {
            errors.add(new DefaultCastingError(value, defaultValue, sheetName, row, col, exception));
        }
    }

    protected Boolean primitiveBooleanValue(String value, String sheetName, int row, int col) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected Boolean booleanValue(String value, String sheetName, int row, int col, PoijiOptions options) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected int primitiveIntegerValue(String value, String sheetName, int row, int col) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected Integer integerValue(String value, String sheetName, int row, int col, PoijiOptions options) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected long primitiveLongValue(String value, String sheetName, int row, int col) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected Long longValue(String value, String sheetName, int row, int col, PoijiOptions options) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected double primitiveDoubleValue(String value, String sheetName, int row, int col, PoijiOptions options) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected Double doubleValue(String value, String sheetName, int row, int col, PoijiOptions options) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected float primitiveFloatValue(String value, String sheetName, int row, int col, PoijiOptions options) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected Float floatValue(String value, String sheetName, int row, int col, PoijiOptions options) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected BigDecimal bigDecimalValue(String value, String sheetName, int row, int col, PoijiOptions options) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /*
     * 
     * ISSUE #57
     * if a date regex has been specified then it wont be null
     * so then make sure the string matches the pattern
     * if it doesn't, fall back to default
     * else continue to turn string into java date
     * 
     * the reason for this is sometime Java will manage to parse a string to a
     * date object without any exceptions but since the string was not an exact
     * match you get a very strange date
     */
    protected Date dateValue(String value, String sheetName, int row, int col, PoijiOptions options) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /*
     * ISSUE #57
     * if a date regex has been specified then it wont be null
     * so then make sure the string matches the pattern
     * if it doesn't, fall back to default
     * else continue to turn string into java date
     * 
     * the reason for this is sometime java will manage to parse a string to a
     * date object without any exceptions but since the string was not an exact
     * match you get a very strange date
     * 
     */
    protected LocalDate localDateValue(String value, String sheetName, int row, int col, PoijiOptions options) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected LocalDateTime localDateTimeValue(String value, String sheetName, int row, int col, PoijiOptions options) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected LocalTime localTimeValue(String value, String sheetName, int row, int col, PoijiOptions options) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected Object enumValue(String value, String sheetName, int row, int col, Class<?> type) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected Object castListValue(String value, String sheetName, int row, int col, Field field, PoijiOptions options) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public Object castValue(Field field, String rawValue, int row, int col, PoijiOptions options) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected Object getValueObject(Field field, int row, int col, PoijiOptions options, String rawValue, Class<?> fieldType) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public boolean isErrorLoggingEnabled() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public List<DefaultCastingError> getErrors() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
