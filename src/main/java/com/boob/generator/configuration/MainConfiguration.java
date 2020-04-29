package com.boob.generator.configuration;

import com.boob.generator.entity.database.DataSource;

import java.util.List;
import java.util.Map;

/**
 * @Author: yan
 * @Date: 2020/4/27 0027
 * @Version: 1.0
 */

public interface MainConfiguration extends Configuration {

    DataSource getDataSource();

    MainConfiguration setDataSource(DataSource dataSource);

    List<TableConfiguration> getTableConfigurations();

    MainConfiguration setTableConfigurations(List<TableConfiguration> tableConfigurations);

    boolean isAllTables();

    boolean isToCamelCaseName();

    Map<String, String> getGeneratorPackageMap();

    String getJavaPackage();

    String getResourcesPackage();
}
