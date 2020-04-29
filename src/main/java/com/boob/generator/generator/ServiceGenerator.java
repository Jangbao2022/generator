package com.boob.generator.generator;

import com.boob.generator.entity.java.Service;


/**
 * @Author: yan
 * @Date: 2020/4/28 0028
 * @Version: 1.0
 */

public class ServiceGenerator implements Generator<Service> {

    @Override
    public void generate(Service service) {

    }

    private ServiceGenerator() {
    }

    private static final ServiceGenerator serviceGenerator = new ServiceGenerator();

    public static ServiceGenerator getInstance() {
        return ServiceGenerator.serviceGenerator;
    }
}
