package com.boob.generator.converter;

/**
 * @Author: yan
 * @Date: 2020/4/28 0028
 * @Version: 1.0
 */

public class DefaultNameConverter implements NameConvert {

    @Override
    public String convert(String source) {
        return source;
    }

    @Override
    public NameConvert setFirstCharIsUpper(boolean firstIsUpper) {
        return this;
    }

    private static final DefaultNameConverter defaultNameConverter = new DefaultNameConverter();

    private DefaultNameConverter() {

    }

    public static DefaultNameConverter getInstance() {
        return DefaultNameConverter.defaultNameConverter;
    }

}
