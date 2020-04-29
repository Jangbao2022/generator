package com.boob.generator.converter;

import com.boob.generator.configuration.MainConfiguration;
import com.boob.generator.entity.java.Dao;

/**
 * @Author: yan
 * @Date: 2020/4/28 0028
 * @Version: 1.0
 */

public class DaoConverter implements Converter<MainConfiguration, Dao> {

    @Override
    public Dao convert(MainConfiguration mainConfiguration) {

        return null;
    }

    private static final DaoConverter daoConverter = new DaoConverter();

    private DaoConverter() {

    }

    public static DaoConverter getInstance() {
        return DaoConverter.daoConverter;
    }
}
