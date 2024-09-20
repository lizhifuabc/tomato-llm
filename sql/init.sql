create database if not exists `tomato-llm`;
use `tomato-llm`;
# 自定义提示词
drop table if exists `t_custom_prompt`;
create table if not exists `t_custom_prompt` (
    `prompt_id` bigint unsigned not null auto_increment comment '主键',
    `prompt` text not null comment '提示词',
    `prompt_type` tinyint unsigned not null default 0 comment '提示词类型 0:自定义 1:系统',
    `create_time` datetime not null default current_timestamp comment '创建时间',
    `update_time` datetime not null default current_timestamp on update current_timestamp comment '更新时间',
    primary key (`prompt_id`)
) engine = innodb default CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci comment = '自定义提示词';
insert into t_custom_prompt (prompt_type,prompt) values (1,'中文回复所有的问题');

# 知识库
drop table if exists `t_knowledge`;
create table if not exists `t_knowledge` (
    `knowledge_id`      bigint unsigned not null auto_increment comment '主键',
    `name`              varchar(255) not null comment '名称',
    `desc`              varchar(255) not null comment '描述',
    `cover`             varchar(255) not null comment '封面',
    `create_time`       datetime not null default current_timestamp comment '创建时间',
    `update_time`       datetime not null default current_timestamp on update current_timestamp comment '更新时间',
    primary key (`knowledge_id`)
) engine = innodb default CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci comment = '知识库';

# 知识库文档
drop table if exists `t_knowledge_doc`;
create table if not exists `t_knowledge_doc` (
    `knowledge_doc_id`  bigint unsigned not null auto_increment comment '主键',
    `knowledge_id`      bigint unsigned not null comment '知识库id',
    `content`           text not null comment '文档内容',
    `create_time`       datetime not null default current_timestamp comment '创建时间',
    `update_time`       datetime not null default current_timestamp on update current_timestamp comment '更新时间',
    primary key (`knowledge_doc_id`)
) engine = innodb default CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci comment = '知识库文档';

# 聊天记录
drop table if exists `t_chat_history`;
CREATE TABLE IF NOT EXISTS `t_chat_history`
(
    `chat_history_id`   bigint unsigned not null auto_increment comment '主键',
    `conv_uid`          varchar(255)  NOT NULL COMMENT '会话记录唯一ID',
    `summary`           longtext  NOT NULL COMMENT '会话记录摘要',
    `create_time`       datetime not null default current_timestamp comment '创建时间',
    `update_time`       datetime not null default current_timestamp on update current_timestamp comment '更新时间',
    UNIQUE KEY `conv_uid` (`conv_uid`) COMMENT '唯一键：会话记录唯一ID',
    PRIMARY KEY (`chat_history_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT '聊天记录表';

# 聊天记录消息表
drop table if exists `t_chat_history_message`;
CREATE TABLE IF NOT EXISTS `t_chat_history_message`
(
    `chat_history_message_id`   bigint unsigned not null auto_increment comment '主键',
    `conv_uid`                  varchar(255)  NOT NULL COMMENT '会话记录唯一ID',
    `index`                     int                                     NOT NULL COMMENT '消息索引',
    `round_index`               int                                     NOT NULL COMMENT '对话轮次',
    `message_detail`            json  COMMENT '消息详细内容',
    `create_time`               datetime not null default current_timestamp comment '创建时间',
    `update_time`               datetime not null default current_timestamp on update current_timestamp comment '更新时间',
    UNIQUE KEY `message_uid_index` (`conv_uid`, `index`) COMMENT '唯一键：会话记录唯一ID和消息索引',
    PRIMARY KEY (`chat_history_message_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT '聊天记录消息表';

# 连接配置
drop table if exists `t_connect_config`;
CREATE TABLE IF NOT EXISTS `t_connect_config`
(
    `connect_config_id`      bigint unsigned not null auto_increment comment '主键',
    `type`                   varchar(255) NOT NULL COMMENT '数据库类型',
    `name`                   varchar(255) NOT NULL COMMENT '数据库名称',
    `url`                   varchar(255) DEFAULT NULL COMMENT 'url地址',
    `username`               varchar(255) DEFAULT NULL COMMENT '数据库用户名',
    `password`               varchar(255) DEFAULT NULL COMMENT '数据库密码',
    `driver_class_name`      varchar(255) DEFAULT NULL COMMENT '数据库驱动类名',
    `comment`                text COMMENT '数据库备注',
    PRIMARY KEY (`connect_config_id`),
    UNIQUE KEY `uk_db` (`name`) COMMENT '唯一键：数据库名称'
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='连接配置表';
INSERT INTO t_connect_config  VALUES (1, 'mysql', 'zjw', 'jdbc:mysql://192.168.18.88:3306/zjw', 'root', 'Hxhr1234@', 'com.mysql.cj.jdbc.Driver','');
