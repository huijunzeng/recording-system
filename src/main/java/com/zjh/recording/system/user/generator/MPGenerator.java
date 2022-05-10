package com.zjh.recording.system.user.generator;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.IFill;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.fill.Column;
import com.zjh.recording.system.common.entity.BaseEntity;

import java.util.*;

/**
 * mybatis-plus代码生成器
 * https://mp.baomidou.com/config/generator-config.html#mapper-2 文档链接
 * @Author: ZJH
 * @Date: 2020/3/10 10:05
 */
public class MPGenerator {

    /**需要逆向代码生成的数据表*/
    private static String[] datasource_tables = new String[]{"shop", "user_shop_rel", "account", "transaction_record", "note_link"};
    /**生成文件的输出目录*/
    private static String projectPath = "/Users/zengjunhui/Documents";
    /**作者*/
    private static String author = "zjh";
    /**数据源url*/
    private static String url = "jdbc:mysql://101.33.205.68:3306/recording_system?characterEncoding=UTF-8&useUnicode=true&useSSL=false&serverTimezone=Asia/Shanghai";
    /**数据源username*/
    private static String username = "root";
    /**数据源password*/
    private static String password = "zeng@19940125...";
    /**父包路径*/
    private static String parentPackagePath = "com.zjh.recording.system.user";
    /**父模板文件*/
    private static String controllerTemplatePath = "/templates/controller.java.vm";
    private static String serviceTemplatePath = "/templates/service.java.vm";
    private static String serviceImplTemplatePath = "/templates/serviceImpl.java.vm";
    private static String mapperTemplatePath = "/templates/mapper.java.vm";
    private static String xmlTemplatePath = "/templates/mapper.xml.vm";
    private static String entityTemplatePath = "/templates/entity.java.vm";
    // 父类公共字段
    private static String[] superEntityColumns = {"id", "created_time", "updated_time", "created_by", "created_username", "updated_by", "updated_username", "deleted", "remark", "version"};

    public static void main(String[] args) {
        // 填充字段
        List<IFill> tableFillList = new ArrayList<>();
        tableFillList.add(new Column("created_time", FieldFill.INSERT));
        tableFillList.add(new Column("updated_time", FieldFill.INSERT_UPDATE));
        tableFillList.add(new Column("created_by", FieldFill.INSERT));
        tableFillList.add(new Column("created_username", FieldFill.INSERT));
        tableFillList.add(new Column("updated_by", FieldFill.INSERT_UPDATE));
        tableFillList.add(new Column("updated_username", FieldFill.INSERT_UPDATE));
        tableFillList.add(new Column("deleted", FieldFill.INSERT_UPDATE));
        tableFillList.add(new Column("remark", FieldFill.INSERT_UPDATE));
        tableFillList.add(new Column("version", FieldFill.INSERT_UPDATE));

        Map<String, Object> customMap = new HashMap<>();
        customMap.put("superColumns", superEntityColumns);
        FastAutoGenerator.create(url, username, password)
                .globalConfig(builder -> {
                    builder.author(author) // 设置作者
                            .enableSwagger() // 开启 swagger 模式
                            .fileOverride() // 覆盖已生成文件
                            .dateType(DateType.TIME_PACK) // 时间策略
                            .outputDir(projectPath); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent(parentPackagePath) // 设置父包名
                            //.moduleName("system") // 设置父包模块名，默认值:无
                            //.entity("po") // Entity 包名	默认值:entity
                            //.service("service") // Service 包名 默认值:service
                            //.serviceImpl("service.impl") // Service Impl 包名 默认值:service.impl
                            //.mapper("mapper") // Mapper 包名	默认值:mapper
                            //.xml("mapper.xml") // Mapper 包名 默认值:mapper
                            //.controller("controller") // Controller 包名	默认值:controller
                            .pathInfo(Collections.singletonMap(OutputFile.mapperXml, projectPath + "/src/main/resources/mapper/user/")); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude(datasource_tables) // 需要代码逆向生成的数据表
                            .entityBuilder() // Entity策略配置
                                .superClass(BaseEntity.class) // 设置父类
                                .disableSerialVersionUID() // 禁用生成 serialVersionUID 默认值:true
                                .enableLombok() // 开启 lombok 模型 默认值:false
                                .enableTableFieldAnnotation() // 开启生成实体时生成字段注解	默认值:false
                                .versionColumnName("version") // 乐观锁字段名(数据库)
                                .versionPropertyName("version") // 乐观锁属性名(实体)
                                .logicDeleteColumnName("deleted") // 逻辑删除字段名(数据库)
                                .logicDeletePropertyName("deleted") // 逻辑删除属性名(实体)
                                .naming(NamingStrategy.underline_to_camel) // 数据库表映射到实体的命名策略
                                .columnNaming(NamingStrategy.underline_to_camel) // 数据库表字段映射到实体的命名策略
                                .addSuperEntityColumns(superEntityColumns) // 添加父类公共字段
                                .addTableFills(tableFillList) // 添加表字段填充
                                .idType(IdType.ASSIGN_ID) // 全局主键类型
                                .formatFileName("%sEntity") // 格式化文件名称
                            .mapperBuilder() // Mapper策略配置
                                .superClass(BaseEntity.class) // 设置父类
                                .enableBaseResultMap() // 生成BaseResultMap
                                .enableBaseColumnList() // 生成BaseColumnList
                            .build();
                })
                .templateConfig(builder -> {
                    builder.entity(entityTemplatePath)
                            .service(serviceTemplatePath)
                            .serviceImpl(serviceImplTemplatePath)
                            .mapper(mapperTemplatePath)
                            .mapperXml(xmlTemplatePath)
                            .controller(controllerTemplatePath)
                            .build();
                })
                .injectionConfig(builder -> {
                    builder.customMap(customMap)
                    .build(); // 注入配置
                })
                //.templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }
}
