package com.boob.generator.configuration;

import com.boob.generator.entity.database.DataSource;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.Map;

/**
 * @Author: yan
 * @Date: 2020/4/27 0027
 * @Version: 1.0
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class DefaultMainConfiguration implements MainConfiguration {

    /**
     * 数据源信息
     */
    private DataSource dataSource;

    /**
     * 是否所有表都生成实体
     */
    private boolean allTables = true;

    /**
     * 所有表的配置信息
     */
    private List<TableConfiguration> tableConfigurations;

    /**
     * 转变为java驼峰命名
     */
    private boolean toCamelCaseName = true;

    private String javaPackage = "src.main.java";
    private String resourcesPackage = "src.main.resources";

    /**
     * 要自动生成代码的集合,key是名字,value是包名
     */
    private Map<String, String> generatorPackageMap;

    private String daoPackage;

    private String servicePackage;
}
