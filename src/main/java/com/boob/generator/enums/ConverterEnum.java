//package com.boob.generator.enums;
//
//import com.boob.generator.configuration.MainConfiguration;
//import com.boob.generator.converter.*;
//import com.boob.generator.entity.java.Model;
//import com.boob.generator.entity.java.PackageInstance;
//
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * @Author: yan
// * @Date: 2020/4/28 0028
// * @Version: 1.0
// */
//
//public enum ConverterEnum {
//
////    NULL_CONVERTER("null"),
////    DEFAULT_NAME_CONVERTER("defaultName"),
////    CAMEL_CASE_NAME_CONVERTER("camelCaseName"),
////
////    FIELD_TYPE_NAME_CONVERTER("fieldTypeName"),
////
////    FIELD_CONVERTER("field"),
////    INSTANCE_CONVERTER("instance"),
////    MODEL_CONVERTER("model"),
////    DAO_CONVERTER("dao"),
////    SERVICE_CONVERTER("service"),
//    ;
//
//    private String name;
//
//    private static Map<String, Converter> converterMap = new HashMap<>();
//
//    static {
////        converterMap.put("defaultName", new DefaultNameConverter());
////        converterMap.put("camelCaseName", new CamelCaseNameConverter());
////
////        converterMap.put("fieldTypeName", new FieldTypeNameConverter());
////
////        converterMap.put("field", new FieldConverter());
////        converterMap.put("instance", new InstanceConverter());
////        converterMap.put("model", new ModelConverter());
////        converterMap.put("dao", new DaoConverter());
////        converterMap.put("service", new ServiceConverter());
//    }
//
//    private ConverterEnum(String name) {
//        this.name = name;
//    }
//
//    public Converter getInstance() {
//        return getInstance(this.name);
//    }
//
//    public Converter getInstance(String name) {
//        return converterMap.get(name);
//    }
//
//
//}
