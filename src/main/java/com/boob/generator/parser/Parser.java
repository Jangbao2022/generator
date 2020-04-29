package com.boob.generator.parser;

import java.io.File;
import java.io.InputStream;
import java.io.Reader;

/**
 * 解析并返回一个实体
 *
 * @param <T>
 */
public interface Parser<T, E> {


    /**
     * 根据路径解析
     *
     * @param location
     * @return
     */
    T parse(String location);

    /**
     * 根据is解析
     *
     * @param is
     * @return
     */
    T parse(InputStream is);


    /**
     * 根据file解析
     *
     * @param file
     * @return
     */
    T parse(File file);

    /**
     * @param reader
     * @return
     */
    T parse(Reader reader);

    /**
     * 默认解析(取缓存中的东西)
     *
     * @return
     */
    T parse();

    default T parse(E e) {
        return null;
    }


    /**
     * 清空缓存
     */
    void clear();

}
