package com.boob.generator.generator;

import com.boob.generator.entity.java.Model;
import com.boob.generator.util.FileUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.File;

/**
 * @Author: yan
 * @Date: 2020/4/28 0028
 * @Version: 1.0
 */

public class ModelGenerator implements Generator<Model> {
    private static final Log LOG = LogFactory.getLog(ModelGenerator.class);

    private InstanceGenerator instanceGenerator = InstanceGenerator.getInstance();

    @Override
    public void generate(Model model) {
        String packageName = model.getPackageName();
        String fileName = model.getFileName();

        File file = FileUtil.getFile(fileName);
        if (!file.exists()) {
            LOG.info(fileName + "not exists, we will create it");
            file.mkdirs();
        } else if (!file.isDirectory()) {
            throw new RuntimeException(fileName + "is not directory,please check it");
        }
        instanceGenerator.setPackageName(packageName);
        instanceGenerator.setBaseFileName(fileName);
        instanceGenerator.generateList(model.getInstances());
    }

    private ModelGenerator() {
    }

    private static final ModelGenerator modelGenerator = new ModelGenerator();

    public static ModelGenerator getInstance() {
        return ModelGenerator.modelGenerator;
    }
}
