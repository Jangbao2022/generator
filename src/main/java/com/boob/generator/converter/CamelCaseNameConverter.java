package com.boob.generator.converter;

/**
 * @Author: yan
 * @Date: 2020/4/28 0028
 * @Version: 1.0
 */

public class CamelCaseNameConverter implements NameConvert {

    public CamelCaseNameConverter setFirstCharIsUpper(boolean firstCharIsUpper) {
        this.firstCharIsUpper = firstCharIsUpper;
        return this;
    }

    /**
     * 首字母是否大写
     */
    private boolean firstCharIsUpper = false;


    @Override
    public String convert(String source) {
        int length = source.length();
        if (length == 0) return source;

        char[] chars = source.toCharArray();
        for (int i = 1; i < length; ++i) {
            if (chars[i - 1] == '_') {
                chars[i] = charToUpperCase(chars[i]);
            }
        }
        String replace = String.valueOf(chars).replace("_", "");
        if (firstCharIsUpper) {
            return firstCharToUpper(replace);
        }
        return firstCharToLower(replace);
    }

    private char charToUpperCase(char c) {
        if ('a' <= c && c <= 'z') {
            return (char) (c - 32);
        }
        return c;
    }

    private char charToLowerCase(char c) {
        if ('A' <= c && c <= 'Z') {
            return (char) (c + 32);
        }
        return c;
    }

    private String firstCharToLower(String string) {
        char[] chars = string.toCharArray();
        chars[0] = charToLowerCase(chars[0]);
        return String.valueOf(chars);
    }

    private String firstCharToUpper(String string) {
        char[] chars = string.toCharArray();
        chars[0] = charToUpperCase(chars[0]);
        return String.valueOf(chars);
    }

    private CamelCaseNameConverter() {
    }

    private static final CamelCaseNameConverter camelCaseNameConverter = new CamelCaseNameConverter();

    public static CamelCaseNameConverter getInstance() {
        return CamelCaseNameConverter.camelCaseNameConverter;
    }

}
