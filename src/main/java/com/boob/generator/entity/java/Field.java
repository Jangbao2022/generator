package com.boob.generator.entity.java;

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
public class Field {

    private String name;

//    private String permissionType;

    private List<String> annotation;

    private String type;

}
