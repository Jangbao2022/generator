package com.boob.generator.enums;

/**
 * @Author: yan
 * @Date: 2020/4/28 0028
 * @Version: 1.0
 */

public enum GeneratorEnum {
    //    NULL_GENERATOR("null"),
//    FIELD_GENERATOR("field"),
//    INSTANCE_GENERATOR("instance"),
//
//    MODEL_GENERATOR("model"),
//    DAO_GENERATOR("dao"),
//    SERVICE_GENERATOR("service"),
    MODEL("model"),
    DAO("dao"),
    SERVICE("service"),
    ;

    private String name;

    private GeneratorEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    //    private static Map<String, Generator> generatorMap = new HashMap<>();

//    private static List<String> generatorPackageKeys = new ArrayList<>();

//    static {
//        generatorMap.put("field", new FieldGenerator());
//        generatorMap.put("instance", new InstanceGenerator());
//
//        generatorMap.put("model", new ModelGenerator());
//        generatorMap.put("dao", new DaoGenerator());
//        generatorMap.put("service", new ServiceGenerator());

//        generatorPackageKeys.add("model");
//        generatorPackageKeys.add("dao");
//        generatorPackageKeys.add("service");
//    }

//    public List<String> getGeneratorPackageKeys() {
//        return generatorPackageKeys;
//    }

//    public Generator getInstance() {
//        return getInstance(this.name);
//    }
//
//    public Generator getInstance(String name) {
//        return generatorMap.get(name);
//    }
}
