package com.tomato.llm.server.gen;

import cn.mybatis.mp.generator.FastGenerator;
import cn.mybatis.mp.generator.config.GeneratorConfig;

/**
 * 代码生成器
 *
 * @author lizhifu
 * @since 2024/9/19
 */
public class Generator {
    public static void main(String[] args) {
        String tableName = "t_connect_config";
        String author = "lizhifu";
        // 根据数据库链接生成
        new FastGenerator(new GeneratorConfig(
                "jdbc:mysql://127.0.0.1:3306/tomato-llm",
                "root",
                "12345678")
                .basePackage("com.tomato.llm.server")//根包路径
                .author(author)
                .tableConfig(tableConfig -> {
                    tableConfig.tablePrefixs("t_");
                    tableConfig.includeTable(tableName);
                })
                .entityConfig(entityConfig->{
                    entityConfig.swagger(true);
                    entityConfig.packageName("entity");
                })
                .actionConfig(actionConfig->{
                    actionConfig.suffix("Controller");
                })
        ).create();
    }
}
