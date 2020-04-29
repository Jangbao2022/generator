package com.boob.generator.converter;

import java.util.List;

/**
 * 转换器
 *
 * @param <T>
 * @param <E>
 */
public interface Converter<T, E> {

    default List<E> convertList(List<T> t) {
        return null;
    }

    E convert(T t);
}