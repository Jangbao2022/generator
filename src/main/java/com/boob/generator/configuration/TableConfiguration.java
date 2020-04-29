package com.boob.generator.configuration;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @Author: yan
 * @Date: 2020/4/27 0027
 * @Version: 1.0
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class TableConfiguration implements Configuration {

    /**
     * 是否所有列都生成字段
     */
    private boolean allColumn = true;
    private String tableName;
    private String className;
    private String primaryKey = "id";
    private List<ColumnConfiguration> columnConfigurations;

}
