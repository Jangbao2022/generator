package com.boob.generator.generator;

import com.boob.generator.configuration.MainConfiguration;
import com.boob.generator.converter.DaoConverter;
import com.boob.generator.converter.ModelConverter;
import com.boob.generator.converter.ServiceConverter;
import com.boob.generator.entity.java.Dao;
import com.boob.generator.entity.java.Model;
import com.boob.generator.entity.java.PackageInstance;
import com.boob.generator.entity.java.Service;
import com.boob.generator.enums.GeneratorEnum;
import com.boob.generator.parser.Parser;
import com.boob.generator.parser.dom.properties.DefaultPropertiesParser;
import lombok.Data;

import java.util.Set;

/**
 * @Author: yan
 * @Date: 2020/4/28 0028
 * @Version: 1.0
 */

@Data
public class GeneratorManager {
    public final static String LN = "\r\n";

    private Parser parser = new DefaultPropertiesParser();
    private String configLocation;

    public GeneratorManager() {

    }

    public GeneratorManager(String configLocation) {
        this.configLocation = configLocation;
    }

    public void generatorCode() {

        MainConfiguration configuration = (MainConfiguration) parser.parse(configLocation);
        Set<String> generatorKeys = configuration.getGeneratorPackageMap().keySet();

        for (String generatorKey : generatorKeys) {
            generateModel(generatorKey, configuration);
        }
    }

    private void generateModel(String name, MainConfiguration configuration) {
        if (GeneratorEnum.MODEL.getName().equals(name)) {
            ModelConverter modelConverter = ModelConverter.getInstance();
            Model model = modelConverter.convert(configuration);
            ModelGenerator.getInstance().generate(model);
            return;
        }
        generateDao(name, configuration);
    }

    private void generateDao(String name, MainConfiguration configuration) {
        if (GeneratorEnum.DAO.getName().equals(name)) {
            DaoConverter daoConverter = DaoConverter.getInstance();
            Dao dao = daoConverter.convert(configuration);
            DaoGenerator.getInstance().generate(dao);
            return;
        }
        generateService(name, configuration);
    }

    public void generateService(String name, MainConfiguration configuration) {
        if (GeneratorEnum.SERVICE.getName().equals(name)) {
            ServiceConverter serviceConverter = ServiceConverter.getInstance();
            Service service = serviceConverter.convert(configuration);
            ServiceGenerator.getInstance().generate(service);
            return;
        }
        generateModel(name, configuration);
    }

//        for (String key : generatorKeys) {
//            //获取转换器
//            Converter<MainConfiguration, PackageInstance> converter = ConverterEnum.NULL_CONVERTER.getInstance(key);
//            //转换出packageInstance对象
//            PackageInstance packageInstance = converter.convert(configuration);
//            //获取生成器
//            Generator<PackageInstance> generator = GeneratorEnum.NULL_GENERATOR.getInstance(key);
//            //生成代码
//            generator.generateList(packageInstance);
//        }

}
