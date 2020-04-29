package com.boob.generator.entity.java;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @Author: yan
 * @Date: 2020/4/28 0028
 * @Version: 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class Dao implements PackageInstance {

    private String packageName;
    private List<Instance> instances;
}
