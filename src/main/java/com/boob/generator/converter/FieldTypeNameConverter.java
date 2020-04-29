package com.boob.generator.converter;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: yan
 * @Date: 2020/4/28 0028
 * @Version: 1.0
 */

public class FieldTypeNameConverter implements NameConvert {

    private boolean firstIsUpper = true;

    @Override
    public NameConvert setFirstCharIsUpper(boolean firstIsUpper) {
        return null;
    }

    private static Map<String, String> mapping = new HashMap<>();

    static {
        mapping.put("varchar", "String");
        mapping.put("char", "String");
        mapping.put("text", "String");

        mapping.put("tinyint", "Short");
        mapping.put("smallint", "Short");
        mapping.put("int", "Integer");
        mapping.put("bigint", "Long");
        mapping.put("float", "Float");
        mapping.put("double", "Double");

        mapping.put("date", "Date");
        mapping.put("time", "Date");
        mapping.put("year", "Date");
        mapping.put("datetime", "Date");
        mapping.put("timestamp", "Date");
    }

    @Override
    public String convert(String source) {
        return mapping.get(source);
    }

    private FieldTypeNameConverter() {

    }

    private static final FieldTypeNameConverter fieldTypeConverter = new FieldTypeNameConverter();

    public static FieldTypeNameConverter getInstance() {
        return FieldTypeNameConverter.fieldTypeConverter;
    }

}
