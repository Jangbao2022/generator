package com.boob.generator.converter;

/**
 * @Author: yan
 * @Date: 2020/4/28 0028
 * @Version: 1.0
 */

public interface NameConvert extends Converter<String, String> {

    String convert(String source);

    NameConvert setFirstCharIsUpper(boolean firstIsUpper);

}
