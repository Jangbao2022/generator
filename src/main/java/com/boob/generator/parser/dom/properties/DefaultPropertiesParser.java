package com.boob.generator.parser.dom.properties;

import com.boob.generator.configuration.ColumnConfiguration;
import com.boob.generator.configuration.DefaultMainConfiguration;
import com.boob.generator.configuration.MainConfiguration;
import com.boob.generator.configuration.TableConfiguration;
import com.boob.generator.entity.database.*;
import com.boob.generator.enums.GeneratorEnum;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.*;

/**
 * @Author: yan
 * @Date: 2020/4/27 0027
 * @Version: 1.0
 */

/**
 * 默认properties解析
 */
public class DefaultPropertiesParser implements PropertiesParser {

    private static final Log LOG = LogFactory.getLog(DefaultPropertiesParser.class);

    /**
     * 默认配置文件路径
     */
    private final String location = "generator.properties";

    /**
     * 配置文件
     */
    private Properties config = new Properties();

    private final String URL_PROPERTIES = "dataSource.url";
    private final String DRIVER_PROPERTIES = "dataSource.driver";
    private final String USERNAME_PROPERTIES = "dataSource.username";
    private final String PASSWORD_PROPERTIES = "dataSource.password";

    /**
     * 是否配置所有表全部
     */
    private final String ALL_TABLES = "table.all";

    /**
     * 配置java代码路径
     */
    private final String JAVA_PACKAGE = "java.package";

    /**
     * 配置resources路径
     */
    private final String RESOURCES_PACKAGE = "resources.package";

//    /**
//     * 配置model生成的位置
//     */
//    private final String MODEL_PACKAGE = "model.package";


    /**
     * 驼峰命名
     */
    private final String TO_CAMEL_CASE_NAME = "toCamelCaseName";

    /**
     * 包含的表
     */
    private final String INCLUDE_TABLE = "table.include";

    /**
     * 不包含的表
     */
    private final String EXCLUDE_TABLE = "table.exclude";

    /**
     * 解析生成的配置信息
     */
    private MainConfiguration mainConfiguration = null;

    /**
     * 数据库信息
     */
    private DatabaseInfo databaseInfo;

    /**
     * 加载配置文件
     */
    @Override
    public void load() {
        load(this.location);
    }

    /**
     * 根据文件路径加载配置文件
     *
     * @param location
     */
    @Override
    public void load(String location) {
        if (location == null) {
            load();
            return;
        }
        //先把location 转为stream
        InputStream is = this.getClass().getClassLoader().getResourceAsStream(location);

        load(is);

        close(is);
    }

    /**
     * 根据文件流加载
     *
     * @param is
     */
    @Override
    public void load(InputStream is) {
        doLoad(null, is);
    }

    /**
     * 根据reader加载
     *
     * @param reader
     */
    @Override
    public void load(Reader reader) {
        doLoad(reader, null);
    }

    /**
     * 加载
     *
     * @param reader
     * @param is
     */
    private void doLoad(Reader reader, InputStream is) {
        config.clear();
        try {
            if (reader != null) {
                config.load(reader);
            } else if (is != null) {
                config.load(is);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public MainConfiguration parse(Reader reader) {
        return parse(reader, null, null);
    }

    @Override
    public MainConfiguration parse(InputStream is) {
        return parse(null, is, null);
    }

    @Override
    public MainConfiguration parse() {
        return parse(null, null, null);
    }

    /**
     * 解析mainConfiguration
     *
     * @return
     */
    @Override
    public MainConfiguration parse(String location) {
        return parse(null, null, location);
    }

    /**
     * 解析
     *
     * @param reader
     * @param is
     * @return
     */
    private MainConfiguration parse(Reader reader, InputStream is, String location) {
        if (this.mainConfiguration != null) {
            return this.mainConfiguration;
        }
        if (is != null) {
            load(is);
            close(is);
        } else if (reader != null) {
            load(reader);
            close(reader);
        } else if (location != null) {
            load(location);
            close();
        } else {
            load();
            close();
        }
        return doParse();
    }


    private MainConfiguration doParse() {

        DefaultMainConfiguration mainConfiguration = new DefaultMainConfiguration();

        boolean allTables = parseProperty(ALL_TABLES, mainConfiguration.isAllTables());
        boolean toCamelCaseName = parseProperty(TO_CAMEL_CASE_NAME, mainConfiguration.isToCamelCaseName());

        String javaPackage = parseProperty(JAVA_PACKAGE, mainConfiguration.getJavaPackage());
        String resourcesPackage = parseProperty(RESOURCES_PACKAGE, mainConfiguration.getResourcesPackage());

        return mainConfiguration.setAllTables(allTables)//是否生成所有表
                .setToCamelCaseName(toCamelCaseName)//是否驼峰命名
                .setJavaPackage(javaPackage)
                .setGeneratorPackageMap(parsePackage()) //所有要生成的package
                .setResourcesPackage(resourcesPackage)
                .setDataSource(parseDatasource())//设置数据源
                .setTableConfigurations(parseTables(mainConfiguration))//设置table配置
                ;
    }

    /**
     * 解析要生成的package
     *
     * @return
     */
    private Map<String, String> parsePackage() {
        final String suffix = ".package";

        Map<String, String> generatorPackageMap = new HashMap<>();
        for (GeneratorEnum generatorEnum : GeneratorEnum.values()) {
            String generatorName = generatorEnum.getName();
            String packageName = parseProperty(generatorName + suffix);
            if (packageName == null) {
                continue;
            }
            generatorPackageMap.put(generatorName, packageName);
        }
        return generatorPackageMap;
    }

    /**
     * 解析表配置
     *
     * @return
     */
    private List<TableConfiguration> parseTables(MainConfiguration mainConfiguration) {

        List<TableConfiguration> tableConfigurations = new ArrayList<>();

        String[] includeTables = parseListProperty(INCLUDE_TABLE);
        String[] excludeTables = parseListProperty(EXCLUDE_TABLE);

        if (mainConfiguration.isAllTables()) {
            //加入所有表
            this.databaseInfo.getTableMap().forEach((tableName, table) -> {
                tableConfigurations.add(tableToTableConfiguration(table));
            });
        } else {
            //加入部分表
            if (includeTables != null) {
                for (String includeTable : includeTables) {
                    tableConfigurations.add(tableToTableConfiguration(this.databaseInfo.getTableMap().get(includeTable)));
                }
            }
        }

        if (excludeTables != null) {
            //去除不需要的表
            for (String excludeTable : excludeTables) {
                for (TableConfiguration tableConfiguration : tableConfigurations) {
                    if (tableConfiguration.getTableName().equals(excludeTable)) {
                        tableConfigurations.remove(tableConfiguration);
                        break;
                    }
                }
            }
        }

        //解析表对应的列配置
        for (TableConfiguration tableConfiguration : tableConfigurations) {
            String tableName = tableConfiguration.getTableName();
            List<ColumnConfiguration> columnConfigurations = parseColumns(mainConfiguration, tableName);
            tableConfiguration.setColumnConfigurations(columnConfigurations);
        }
        return tableConfigurations;
    }

    /**
     * 把表转为对应的表配置
     *
     * @param table
     * @return
     */
    private TableConfiguration tableToTableConfiguration(Table table) {
        return new TableConfiguration().setPrimaryKey(table.getPrimaryKey())
                .setTableName(table.getName())
                .setClassName(table.getName());
    }

    /**
     * 解析列配置
     *
     * @return
     */
    private List<ColumnConfiguration> parseColumns(MainConfiguration mainConfiguration, String tableName) {
        List<ColumnConfiguration> columnConfigurations = new ArrayList<>();
        this.databaseInfo.getTableMap().get(tableName).getColumns().forEach((column) -> {
            columnConfigurations.add(columnToColumnConfiguration(column));
        });
        //TODO 把不需要的列从表配置中去除

        return columnConfigurations;
    }

    /**
     * 把列转为对应的列配置
     *
     * @param column
     * @return
     */
    private ColumnConfiguration columnToColumnConfiguration(Column column) {
        return new ColumnConfiguration().setColumnName(column.getName())
                .setFieldName(column.getName())
                .setType(column.getType());
    }

    /**
     * 解析datasource配置
     *
     * @return
     */
    private DataSource parseDatasource() {
        String url = parseCantNullProperty(URL_PROPERTIES);
        String driver = parseCantNullProperty(DRIVER_PROPERTIES);
        String username = parseCantNullProperty(USERNAME_PROPERTIES);
        String password = parseCantNullProperty(PASSWORD_PROPERTIES);

        DataSource dataSource = new DataSource(url, driver, username, password);
        //根据数据源查出数据库信息
        this.databaseInfo = DatabaseInquirer.query(dataSource);
        return dataSource;
    }


    /**
     * 解析集合properties
     *
     * @param propertiesName
     * @return
     */
    private String[] parseListProperty(String propertiesName) {
        String excludeTables = parseProperty(EXCLUDE_TABLE);
        if (excludeTables != null) {
            //获取不自动生成的表, 用逗号分隔
            return excludeTables.split(",");
        }
        return null;
    }

    /**
     * 按名字解析配置文件
     *
     * @param propertiesName
     * @return
     */
    private String parseProperty(String propertiesName) {
        return config.getProperty(propertiesName);
    }

    /**
     * 按名字解析配置文件返回bool值,默认返回defaultValue
     *
     * @param propertiesName
     * @param defaultValue
     * @return
     */
    private boolean parseProperty(String propertiesName, boolean defaultValue) {
        String property = parseProperty(propertiesName);

        if (property == null) {
            return defaultValue;
        }
        property = property.toLowerCase();
        if ("true".equals(property)) {
            return true;
        } else if ("false".equals(property)) {
            return false;
        }
        throw new RuntimeException(propertiesName + " config fail");
    }

    /**
     * 带默认值的parseProperty
     *
     * @param propertiesName
     * @param defaultProperties
     * @return
     */
    private String parseProperty(String propertiesName, String defaultProperties) {
        String property = parseProperty(propertiesName);
        return property != null ? property : defaultProperties;
    }

    /**
     * 解析配置文件中不能为空的字段
     *
     * @param propertiesName
     * @return
     */
    private String parseCantNullProperty(String propertiesName) {
        String property = parseProperty(propertiesName);
        if (property == null) {
            throw new RuntimeException(propertiesName + " can't be null");
        }
        return property;
    }

    @Override
    public void close(InputStream is) {
        close(null, is);
    }

    @Override
    public void close(Reader reader) {
        close(reader, null);
    }

    /**
     * 关闭资源流
     *
     * @param reader
     * @param is
     */
    private void close(Reader reader, InputStream is) {
        try {
            if (is != null) {
                is.close();
            }
            if (reader != null) {
                reader.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() {

    }

    @Override
    public void clear() {
        config.clear();
        this.mainConfiguration = null;
    }
}
