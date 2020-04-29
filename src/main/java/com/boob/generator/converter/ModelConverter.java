package com.boob.generator.converter;

import com.boob.generator.configuration.MainConfiguration;
import com.boob.generator.entity.java.Model;
import com.boob.generator.util.StringUtil;

/**
 * @Author: yan
 * @Date: 2020/4/28 0028
 * @Version: 1.0
 */

public class ModelConverter implements Converter<MainConfiguration, Model> {

    private InstanceConverter instanceConverter = InstanceConverter.getInstance();
    private NameConvert nameConvert = DefaultNameConverter.getInstance();

    private final String packageKey = "model";

    @Override
    public Model convert(MainConfiguration mainConfiguration) {
        Model model = new Model();
        //包名转换
        String packageName = mainConfiguration.getGeneratorPackageMap().get(packageKey);
        model.setFileName(StringUtil.packageToPath(mainConfiguration.getJavaPackage(), packageName));
        model.setPackageName(StringUtil.packageToPath(packageName));

        //驼峰命名转化
        if (mainConfiguration.isToCamelCaseName()) {
            nameConvert = CamelCaseNameConverter.getInstance();
        }

        return model.setInstances(instanceConverter.setNameConvert(nameConvert).convertList(mainConfiguration.getTableConfigurations()));
    }

    private static final ModelConverter modelConverter = new ModelConverter();

    private ModelConverter() {

    }

    public static ModelConverter getInstance() {
        return ModelConverter.modelConverter;
    }

}
