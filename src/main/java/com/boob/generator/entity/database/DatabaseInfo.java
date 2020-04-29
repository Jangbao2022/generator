package com.boob.generator.entity.database;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * 数据库信息
 *
 * @Author: yan
 * @Date: 2020/4/27 0027
 * @Version: 1.0
 */
@Data
public class DatabaseInfo {

    /**
     * 所有表的集合
     */
    private Map<String, Table> tableMap = new HashMap<>();

}
