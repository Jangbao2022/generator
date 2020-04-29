package com.boob.generator.parser.dom.properties;

import com.boob.generator.configuration.MainConfiguration;
import com.boob.generator.parser.dom.DomParser;

import java.io.InputStream;
import java.io.Reader;

/**
 * @Author: yan
 * @Date: 2020/4/27 0027
 * @Version: 1.0
 */

public interface PropertiesParser extends DomParser {

    /**
     * 加载默认资源
     */
    void load();

    /**
     * 加载指定资源
     */
    void load(String location);

    /**
     * 加载inputStream
     *
     * @param is
     */
    void load(InputStream is);

    /**
     * 加载reader
     *
     * @param reader
     */
    void load(Reader reader);

    /**
     * 解析is
     *
     * @param is
     * @return
     */
    MainConfiguration parse(InputStream is);

    /**
     * 解析reader
     *
     * @param reader
     * @return
     */
    MainConfiguration parse(Reader reader);

    /**
     * 根据文件路径解析
     *
     * @param location
     * @return
     */
    MainConfiguration parse(String location);


}
