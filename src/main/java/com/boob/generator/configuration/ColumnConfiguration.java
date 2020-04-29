package com.boob.generator.configuration;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @Author: yan
 * @Date: 2020/4/27 0027
 * @Version: 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class ColumnConfiguration implements Configuration {

    private String columnName;
    private String fieldName;
    private String type;
}
