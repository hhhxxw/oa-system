DROP TABLE IF EXISTS oa_process;
DROP TABLE IF EXISTS oa_process_record;
CREATE TABLE `oa_process` (
                              `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
                              `process_code` varchar(50) NOT NULL DEFAULT '' COMMENT '审批code',
                              `user_id` bigint(1) NOT NULL DEFAULT '0' COMMENT '用户id',
                              `process_template_id` bigint(20) DEFAULT NULL COMMENT '审批模板id',
                              `process_type_id` bigint(20) DEFAULT NULL COMMENT '审批类型id',
                              `title` varchar(255) DEFAULT NULL COMMENT '标题',
                              `description` varchar(255) DEFAULT NULL COMMENT '描述',
                              `form_values` text COMMENT '表单值',
                              `process_instance_id` varchar(255) DEFAULT NULL COMMENT '流程实例id',
                              `current_auditor` varchar(255) DEFAULT NULL COMMENT '当前审批人',
                              `status` tinyint(3) DEFAULT NULL COMMENT '状态（0：默认 1：审批中 2：审批通过 -1：驳回）',
                              `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                              `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                              `is_deleted` tinyint(3) NOT NULL DEFAULT '0' COMMENT '删除标记（0:不可用 1:可用）',
                              PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='审批类型';

#
# Structure for table "oa_process_record"
#

CREATE TABLE `oa_process_record` (
                                     `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
                                     `process_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '审批流程id',
                                     `description` varchar(255) DEFAULT NULL COMMENT '审批描述',
                                     `status` tinyint(3) DEFAULT '0' COMMENT '状态',
                                     `operate_user_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '操作用户id',
                                     `operate_user` varchar(20) DEFAULT NULL COMMENT '操作用户',
                                     `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                     `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                                     `is_deleted` tinyint(3) NOT NULL DEFAULT '0' COMMENT '删除标记（0:不可用 1:可用）',
                                     PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='审批记录';

#
# Structure for table "oa_process_template"
#

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

#
# Structure for table "oa_process_type"
#

CREATE TABLE `oa_process_type` (
                                   `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
                                   `name` varchar(20) NOT NULL DEFAULT '' COMMENT '类型名称',
                                   `description` varchar(255) DEFAULT NULL COMMENT '描述',
                                   `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                   `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                                   `is_deleted` tinyint(3) NOT NULL DEFAULT '0' COMMENT '删除标记（0:不可用 1:可用）',
                                   PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='审批类型';

#
# Structure for table "sys_dept"
#
