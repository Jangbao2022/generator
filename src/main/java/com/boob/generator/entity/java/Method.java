package com.boob.generator.entity.java;

import com.boob.generator.enums.PermissionTypeEnum;
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
public class Method {

    private PermissionTypeEnum permission;

    private String returnType;

    private String name;

    private List<String> args;

    private String body;

}
