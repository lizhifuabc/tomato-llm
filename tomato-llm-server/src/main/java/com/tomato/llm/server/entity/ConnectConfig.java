package com.tomato.llm.server.entity;

import lombok.Data;
import cn.mybatis.mp.db.annotations.TableId;
import cn.mybatis.mp.db.annotations.Table;
import cn.mybatis.mp.db.IdAutoType;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * <p>
 * 连接配置表
 * </p>
 *
 * @author lizhifu
 * @since 2024-09-20
 */
@Data
@Schema(name = "连接配置表")
@Table(value="t_connect_config")
public class ConnectConfig {

    /**
     * 主键
     */
    @TableId(IdAutoType.AUTO)
    private Long connectConfigId;

    /**
     * 数据库类型
     */
    @Schema(description = "数据库类型")
    private String type;

    /**
     * 数据库名称
     */
    @Schema(description = "数据库名称")
    private String name;

    /**
     * url地址
     */
    @Schema(description = "url地址")
    private String url;

    /**
     * 数据库用户名
     */
    @Schema(description = "数据库用户名")
    private String username;

    /**
     * 数据库密码
     */
    @Schema(description = "数据库密码")
    private String password;

    /**
     * 数据库驱动类名
     */
    @Schema(description = "数据库驱动类名")
    private String driverClassName;

    /**
     * 数据库备注
     */
    @Schema(description = "数据库备注")
    private String comment;

}
