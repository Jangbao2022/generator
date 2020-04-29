package com.boob.generator.parser.dom.xml;

import com.boob.generator.configuration.MainConfiguration;
import com.boob.generator.parser.Parser;
import com.boob.generator.parser.dom.DomParser;

import java.io.File;
import java.io.InputStream;
import java.io.Reader;

/**
 * @Author: yan
 * @Date: 2020/4/27 0027
 * @Version: 1.0
 */

public class DefaultXmlParser implements XmlParser {

    private String location = "";

    public DefaultXmlParser() {

    }

    public DefaultXmlParser(String location) {
        this.location = location;
    }

    @Override
    public void load() {

    }

    @Override
    public void load(String s) {

    }

    @Override
    public void load(InputStream is) {

    }


    @Override
    public void load(File file) {

    }

    @Override
    public void load(Reader reader) {

    }

    @Override
    public MainConfiguration parse(String location) {
        return null;
    }

    @Override
    public MainConfiguration parse() {
        return null;
    }

    @Override
    public void close() {

    }

    @Override
    public void clear() {

    }

    @Override
    public void close(InputStream is) {

    }

    @Override
    public void close(Reader reader) {

    }
}
