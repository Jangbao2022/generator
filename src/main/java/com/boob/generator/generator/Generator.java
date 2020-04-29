package com.boob.generator.generator;

import java.util.List;

/**
 * 代码生成器
 *
 * @param <T>
 */
public interface Generator<T> {
    default void generateList(List<T> list) {
    }

    void generate(T t);
}
