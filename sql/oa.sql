use tdt;

drop table if exists oa_process_template;
drop table if exists oa_process_type;
CREATE TABLE `oa_process_template` (
                                       `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '角色id',
                                       `name` varchar(20) NOT NULL DEFAULT '' COMMENT '模板名称',
                                       `icon_url` varchar(100) DEFAULT NULL COMMENT '图标路径',
                                       `process_type_id` varchar(255) DEFAULT NULL,
                                       `form_props` text COMMENT '表单属性',
                                       `form_options` text COMMENT '表单选项',
                                       `process_definition_key` varchar(20) DEFAULT NULL COMMENT '流程定义key',
                                       `process_definition_path` varchar(255) DEFAULT NULL COMMENT '流程定义上传路径',
                                       `process_model_id` varchar(255) DEFAULT NULL COMMENT '流程定义模型id',
                                       `description` varchar(255) DEFAULT NULL COMMENT '描述',
                                       `status` tinyint(3) NOT NULL DEFAULT '0',
                                       `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                       `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                                       `is_deleted` tinyint(3) NOT NULL DEFAULT '0' COMMENT '删除标记（0:不可用 1:可用）',
                                       PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='审批模板';


CREATE TABLE `oa_process_type` (
                                   `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
                                   `name` varchar(20) NOT NULL DEFAULT '' COMMENT '类型名称',
                                   `description` varchar(255) DEFAULT NULL COMMENT '描述',
                                   `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                   `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                                   `is_deleted` tinyint(3) NOT NULL DEFAULT '0' COMMENT '删除标记（0:不可用 1:可用）',
                                   PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='审批类型';
