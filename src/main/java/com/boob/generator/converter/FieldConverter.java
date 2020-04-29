package com.boob.generator.converter;

import com.boob.generator.configuration.ColumnConfiguration;
import com.boob.generator.entity.java.Field;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: yan
 * @Date: 2020/4/28 0028
 * @Version: 1.0
 */

public class FieldConverter implements Converter<ColumnConfiguration, Field> {

    private NameConvert nameConvert = null;
    private String primaryKey = "";

    public void setPrimaryKey(String primaryKey) {
        this.primaryKey = primaryKey;
    }

    public FieldConverter setNameConvert(NameConvert nameConvert) {
        this.nameConvert = nameConvert;
        return this;
    }

    /**
     * 列属性转换
     *
     * @param configuration
     * @return
     */
    @Override
    public Field convert(ColumnConfiguration configuration) {
        NameConvert typeConverter = FieldTypeNameConverter.getInstance();
        List<String> annotations = checkIsId(configuration.getColumnName());
        annotations.add("@Column(name = \"" + configuration.getColumnName() + "\")");

        return new Field().setName(nameConvert.convert(configuration.getFieldName()))
                .setAnnotation(annotations)
                .setType(typeConverter.convert(configuration.getType().toLowerCase()));
//                .setPermissionType(PermissionTypeEnum.PRIVATE.getType())
    }


    @Override
    public List<Field> convertList(List<ColumnConfiguration> list) {
        List<Field> fields = new ArrayList<>();
        for (ColumnConfiguration columnConfiguration : list) {
            setNameConvert(nameConvert.setFirstCharIsUpper(false));
            Field field = convert(columnConfiguration);
            fields.add(field);
        }
        return fields;
    }

    private List<String> checkIsId(String name) {
        List<String> annotations = new ArrayList<>();
        if (primaryKey.equals(name)) {
            annotations.add("@Id");
        }
        return annotations;
    }

    private static final FieldConverter columnConverter = new FieldConverter();

    private FieldConverter() {

    }

    public static FieldConverter getInstance() {
        return FieldConverter.columnConverter;
    }
}
