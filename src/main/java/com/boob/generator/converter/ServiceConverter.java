package com.boob.generator.converter;

import com.boob.generator.configuration.MainConfiguration;
import com.boob.generator.entity.java.Dao;
import com.boob.generator.entity.java.Service;

/**
 * @Author: yan
 * @Date: 2020/4/28 0028
 * @Version: 1.0
 */

public class ServiceConverter implements Converter<MainConfiguration, Service> {

    @Override
    public Service convert(MainConfiguration mainConfiguration) {

        return null;
    }

    private static final ServiceConverter serviceConverter = new ServiceConverter();

    private ServiceConverter() {

    }

    public static ServiceConverter getInstance() {
        return ServiceConverter.serviceConverter;
    }
}
