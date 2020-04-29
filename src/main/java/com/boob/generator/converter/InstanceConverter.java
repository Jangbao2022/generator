package com.boob.generator.converter;

import com.boob.generator.configuration.TableConfiguration;
import com.boob.generator.entity.java.Instance;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: yan
 * @Date: 2020/4/28 0028
 * @Version: 1.0
 */

public class InstanceConverter implements Converter<TableConfiguration, Instance> {

    private FieldConverter fieldConverter = FieldConverter.getInstance();

    private NameConvert nameConvert = null;

    public InstanceConverter setNameConvert(NameConvert nameConvert) {
        this.nameConvert = nameConvert;
        return this;
    }

    @Override
    public Instance convert(TableConfiguration tableConfiguration) {
        fieldConverter.setPrimaryKey(tableConfiguration.getPrimaryKey());
        return new Instance().setName(nameConvert.convert(tableConfiguration.getClassName()))
                .setAnnotations(Arrays.asList("@Entity", "@Table(name = \"" + tableConfiguration.getTableName() + "\")"))
                .setFields(fieldConverter.setNameConvert(nameConvert).convertList(tableConfiguration.getColumnConfigurations()));
    }

    @Override
    public List<Instance> convertList(List<TableConfiguration> list) {
        ArrayList<Instance> instances = new ArrayList<>();
        for (TableConfiguration tableConfiguration : list) {
            nameConvert.setFirstCharIsUpper(true);
            instances.add(convert(tableConfiguration));
        }
        return instances;
    }


    private static final InstanceConverter tableConverter = new InstanceConverter();

    private InstanceConverter() {

    }

    public static InstanceConverter getInstance() {
        return InstanceConverter.tableConverter;
    }
}
