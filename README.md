通过使用这个工具包,你只需要使用简单的操作就能自动生成jpa 实体类.

1. 导入依赖
```xml
<dependency>
    <groupId>com.boob.generator</groupId>
    <artifactId>generator-maven-plugin</artifactId>
    <version>1.0-SNAPSHOT</version>
</dependency>
```

2. 配置文件


下面是配置文件的例子(其中dataSource为必须配置)
```properties
dataSource.driver=com.mysql.jdbc.Driver
dataSource.url=jdbc:mysql://localhost:3306/database?useSSL=false
dataSource.username=username
dataSource.password=password

#java.package=src.main.java
#resources.package=src.main.resources
model.package=com.boob.generator.model
#table.all=false
#table.include=table1,table2
#table.exclude=table3,table3
#toCamelCaseName=false

```

3. 执行代码

```java

//location是配置文件的路径,默认从resources下面找
new GeneratorManager(location).generatorCode();

//或者执行这行代码,默认文件为generator.properties
new GeneratorManager().generatorCode();

```

4. 样例生成

```java
package com.boob.generator.model;

@Entity
@Table(name = "administrator")
public class Administrator{

@Id
@Column(name = "id")
private Long id;

@Column(name = "name")
private String name;

@Column(name = "account")
private String account;

@Column(name = "password")
private String password;

@Column(name = "gmt_created")
private Date gmtCreated;

@Column(name = "gmt_modified")
private Date gmtModified;

}
```

5. 导包

最后简单的把实体类中需要的包导入一下就可以用了
