package com.boob.generator.generator;

import com.boob.generator.entity.java.Dao;

/**
 * @Author: yan
 * @Date: 2020/4/28 0028
 * @Version: 1.0
 */

public class DaoGenerator implements Generator<Dao> {

    @Override
    public void generate(Dao dao) {

    }

    private DaoGenerator() {
    }

    private static final DaoGenerator daoGenerator = new DaoGenerator();

    public static DaoGenerator getInstance() {
        return DaoGenerator.daoGenerator;
    }

}
