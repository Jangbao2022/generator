package com.boob.generator.generator;

import com.boob.generator.entity.java.Field;

import java.util.List;

/**
 * @Author: yan
 * @Date: 2020/4/28 0028
 * @Version: 1.0
 */

public class FieldGenerator implements Generator<Field> {

    private String generatedCode = "";

    public String getGeneratedCode() {
        return generatedCode;
    }

    @Override
    public void generate(Field field) {
        generatedCode += generateAnnotations(field.getAnnotation());
        generatedCode += generateField(field.getType(), field.getName());
    }

    @Override
    public void generateList(List<Field> list) {
        generatedCode = "";
        for (Field field : list) {
            generate(field);
        }
    }

    private String generateAnnotations(List<String> annotations) {
        StringBuilder sb = new StringBuilder();
        for (String annotation : annotations) {
            sb.append(annotation)
                    .append(GeneratorManager.LN);
        }
        return sb.toString();
    }

    private String generateField(String type, String name) {
        return "private " + type + " " + name + ";"
                + GeneratorManager.LN
                + GeneratorManager.LN;
    }

    private FieldGenerator() {
    }

    private static final FieldGenerator fieldGenerator = new FieldGenerator();

    public static FieldGenerator getInstance() {
        return FieldGenerator.fieldGenerator;
    }

}
