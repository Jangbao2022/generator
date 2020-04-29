package com.boob.generator.enums;

/**
 * @Author: yan
 * @Date: 2020/4/27 0027
 * @Version: 1.0
 */

public enum PermissionTypeEnum {

    PUBLIC("public"),
    PROTECT("protect"),
    DEFAULT("default"),
    PRIVATE("private"),
    ;

    private String type;

    PermissionTypeEnum(String type) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }
}
