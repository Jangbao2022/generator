package com.boob.generator.generator;

import com.boob.generator.entity.java.Field;
import com.boob.generator.entity.java.Instance;
import com.boob.generator.util.FileUtil;
import com.boob.generator.util.StringUtil;
import lombok.Data;

import java.util.List;

/**
 * @Author: yan
 * @Date: 2020/4/28 0028
 * @Version: 1.0
 */

@Data
public class InstanceGenerator implements Generator<Instance> {

    private FieldGenerator fieldGenerator = FieldGenerator.getInstance();
    //生成代码的地址
    private String packageName;
    private String baseFileName;

    @Override
    public void generate(Instance instance) {

        //生成代码
        fieldGenerator.generateList(instance.getFields());
        String generatedCode = generateHeadPackage(packageName)
                + generateAnnotations(instance.getAnnotations())
                + generateClassName(instance.getName())
                + "{"
                + GeneratorManager.LN
                + GeneratorManager.LN
                + fieldGenerator.getGeneratedCode()
                + "}";

        FileUtil.writeFile(baseFileName + "/" + instance.getName() + ".java", generatedCode);
    }

    @Override
    public void generateList(List<Instance> list) {
        for (Instance instance : list) {
            generate(instance);
        }
    }

    private String generateHeadPackage(String packageName) {
        return "package " + StringUtil.pathToPackage(packageName) + ";"
                + GeneratorManager.LN
                + GeneratorManager.LN;
    }

    private String generateAnnotations(List<String> annotations) {
        StringBuilder sb = new StringBuilder();
        for (String annotation : annotations) {
            sb.append(annotation)
                    .append(GeneratorManager.LN);
        }
        return sb.toString();
    }

    private String generateClassName(String className) {
//        return PermissionTypeEnum.PUBLIC.getType() +
        return "public"
                + " class "
                + className;

    }

    private String generateFields(List<Field> fields) {
        fieldGenerator.generateList(fields);
        return fieldGenerator.getGeneratedCode();
    }


    private String generateHeadImport() {
        return "";
    }

    private InstanceGenerator() {
    }

    private static final InstanceGenerator instanceGenerator = new InstanceGenerator();

    public static InstanceGenerator getInstance() {
        return InstanceGenerator.instanceGenerator;
    }
}
