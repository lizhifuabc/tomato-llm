package com.tomato.llm.server.entity;

import lombok.Data;
import java.time.LocalTime;
import cn.mybatis.mp.db.annotations.TableId;
import cn.mybatis.mp.db.annotations.TableField;
import cn.mybatis.mp.db.annotations.Table;
import cn.mybatis.mp.db.IdAutoType;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * <p>
 * 自定义提示词
 * </p>
 *
 * @author lizhifu
 * @since 2024-09-19
 */
@Data
@Schema(name = "自定义提示词")
@Table(value="t_custom_prompt")
public class CustomPrompt {

    /**
     * 主键
     */
    @TableId(IdAutoType.AUTO)
    private Long promptId;

    /**
     * 提示词
     */
    @Schema(description = "提示词")
    private String prompt;

    /**
     * 提示词类型 0:自定义 1:系统
     */
    @Schema(description = "提示词类型 0:自定义 1:系统")
    @TableField(defaultValue = "0")
    private Byte promptType;

    /**
     * 创建时间
     */
    @Schema(description = "创建时间")
    @TableField(defaultValue = "{NOW}")
    private LocalTime createTime;

    /**
     * 更新时间
     */
    @Schema(description = "更新时间")
    @TableField(defaultValue = "{NOW}")
    private LocalTime updateTime;

}
