package com.boob.generator.parser.dom;

import com.boob.generator.configuration.MainConfiguration;
import com.boob.generator.parser.Parser;

import java.io.File;
import java.io.InputStream;
import java.io.Reader;

/**
 * @Author: yan
 * @Date: 2020/4/27 0027
 * @Version: 1.0
 */

public interface DomParser<E> extends Parser<MainConfiguration, String> {

    /**
     * 加载默认资源
     */
    void load();

    /**
     * 加载指定资源
     */
    void load(String location);

    /**
     * 加载指定资源
     */
    default void load(File file) {

    }

    /**
     * 加载inputStream
     *
     * @param is
     */
    default void load(InputStream is) {

    }


    @Override
    default void clear() {

    }

    /**
     * 加载reader
     *
     * @param reader
     */
    default void load(Reader reader) {

    }

    @Override
    default MainConfiguration parse(InputStream is) {
        return null;
    }

    @Override
    default MainConfiguration parse(File file) {
        return null;
    }

    @Override
    default MainConfiguration parse(Reader reader) {
        return null;
    }

    @Override
    default MainConfiguration parse() {
        return null;
    }

    @Override
    default MainConfiguration parse(String location) {
        return null;
    }


    default void close(InputStream is) {

    }

    default void close(Reader reader) {

    }

    /**
     * 关闭资源
     */
    void close();
}
