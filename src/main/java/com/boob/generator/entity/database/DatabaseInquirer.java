package com.boob.generator.entity.database;

/**
 * @Author: yan
 * @Date: 2020/4/27 0027
 * @Version: 1.0
 */

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 数据库查询者
 */
public class DatabaseInquirer {

    private static final Log LOG = LogFactory.getLog(DatabaseInquirer.class);

    /**
     * 数据库连接
     */
    private static Connection conn = null;

    /**
     * datasource
     */
    private static DataSource dataSource;

    /**
     * 缓存区
     */
    private static List<Table> tableList = null;

    /**
     * 通过datasource查出数据库信息
     *
     * @param dataSource
     * @return
     */
    public static DatabaseInfo query(DataSource dataSource) {
        //置入数据库信息
        DatabaseInquirer.dataSource = dataSource;
        DatabaseInfo databaseInfo = new DatabaseInfo();

        //查询所有表
        databaseInfo.setTableMap(queryTableMap());

        return databaseInfo;
    }


    /**
     * @return
     * @throws Exception
     */
    public static Map<String, Table> queryTableMap() {
        List<Table> tableList = queryTableList();
        return tableList.stream()
                .collect(Collectors.toMap(Table::getName, table -> table));
    }

    /**
     * 查询数据库中所有表的list集合
     *
     * @return
     * @throws Exception
     */
    public static List<Table> queryTableList() {
        return queryTables();
    }

    private static List<Table> queryTables() {
        //如果缓存区中有
        if (DatabaseInquirer.tableList != null) {
            return DatabaseInquirer.tableList;
        }

        //初始化
        init();

        List<Table> tableList = new ArrayList<>();
        try {
            String sql = "select table_name from information_schema.tables where table_schema='" + conn.getCatalog() + "'";
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            LOG.info("query all tables");
            //获取数据库中所有表名
            while (rs.next()) {
                String tableName = rs.getString("TABLE_NAME");
                Table table = queryTable(tableName);
                tableList.add(table);
            }
            close(statement, rs);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //销毁
        destroy();
        DatabaseInquirer.tableList = tableList;
        return tableList;
    }

    /**
     * 根据表名查询表的信息
     *
     * @param tableName
     * @return
     * @throws Exception
     */
    private static Table queryTable(String tableName) throws Exception {

        Table table = new Table();
        table.setName(tableName);
        LOG.info("query table " + tableName);
        //查询主键名
        String primaryKey = getPrimaryKeyByTableName(tableName);
        table.setPrimaryKey(primaryKey);

        Statement statement = conn.createStatement();
        ResultSet rs = statement.executeQuery("select * from " + tableName + " limit 0,1");

        ResultSetMetaData metaData = rs.getMetaData();
        //根据metaData查询数据库中所有列
        List<Column> columns = queryColumns(metaData);
        table.setColumns(columns);
        close(statement, rs);
        return table;
    }

    /**
     * 根据表名查询主键名
     *
     * @param tableName
     * @return
     */
    private static String getPrimaryKeyByTableName(String tableName) {
        String primaryColumn = "";
        try {
            ResultSet rs = conn.getMetaData().getPrimaryKeys(null, null, tableName);
            while (rs.next()) {
                String pkName = rs.getString("PK_NAME"); //主键名称
                if ("PRIMARY".equals(pkName)) {
                    primaryColumn = rs.getString("COLUMN_NAME");//列名

                    LOG.info("find table " + tableName + " primary key " + primaryColumn);
                    break;
                }
            }
            close(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return primaryColumn;
    }

    /**
     * 查询所有列信息
     *
     * @param metaData
     * @return
     */
    private static List<Column> queryColumns(ResultSetMetaData metaData) {
        List<Column> columns = new ArrayList<>();

        try {
            int columnCount = metaData.getColumnCount();
            for (int i = 1; i <= columnCount; i++) {
                columns.add(queryColumn(metaData, i));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("get columnCount fail");
        }

        return columns;
    }

    /**
     * 查询单列信息
     *
     * @param metaData
     * @param columnIndex
     * @return
     */
    private static Column queryColumn(ResultSetMetaData metaData, int columnIndex) {
        Column column = new Column();
        try {
            String columnName = metaData.getColumnName(columnIndex);
            String columnType = metaData.getColumnTypeName(columnIndex);

            column.setName(columnName)
                    .setType(columnType);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("query column fail");
        }
        return column;
    }


    /**
     * 关闭statement,resultSet
     */
    private static void close(Statement statement, ResultSet rs) {
        close(rs);
        close(statement);
    }

    /**
     * 关闭 statement
     */
    private static void close(Statement statement) {
        try {
            if (statement != null) {
                statement.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("statement is null, can't close");
        }
    }

    /**
     * 关闭 resultSet
     */
    private static void close(ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("statement is null, can't close");
        }
    }

    /**
     * 初始化连接
     */
    private static void init() {
        try {
            Class.forName(dataSource.getDriver());
            conn = DriverManager.getConnection(dataSource.getUrl(), dataSource.getUsername(), dataSource.getPassword());
            LOG.info("connection successful");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("get database connection fail");
        }
    }

    /**
     * 销毁连接
     */
    private static void destroy() {
        try {
            if (conn != null) {
                conn.close();
                LOG.info("connection destroy");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("connection is null, can't close");
        }
    }
}
