DROP TABLE IF EXISTS hr_kpi_item;
DROP TABLE IF EXISTS hr_kpi_plan;
DROP TABLE IF EXISTS hr_kpi_plan_record;
-- auto-generated definition
create table hr_kpi_item
(
    id          bigint auto_increment
        primary key,
    item_id     varchar(50)  null comment '指标ID',
    sort_no     int          null comment '排序号',
    title       varchar(100) null comment '指标标题',
    kpi_type    varchar(50)  null comment '类型',
    item_json   text         null comment '指标内容',
    remark      varchar(400) null comment '备注说明',
    create_time varchar(20)  null,
    create_user varchar(50)  null,
    org_id      varchar(50)  null
)
    comment '人力考核指标集' collate = utf8mb4_general_ci
                             row_format = DYNAMIC;

create index hr_k_i_i_i_index
    on hr_kpi_item (item_id);

-- 插入5条考核指标数据，忽略自增id和部分字段
INSERT INTO hr_kpi_item (item_id, sort_no, title, kpi_type, item_json, remark, create_time, create_user, org_id)
VALUES
    ('KPI001', 1, '工作效率', '定量', '{"weight": 0.4, "target": 95}', '考核任务完成率', '2025-01-10 09:00', 'admin', 'ORG001'),
    ('KPI002', 2, '团队协作', '定性', '{"weight": 0.3, "metrics": ["沟通", "支持"]}', '同事互评得分', '2025-01-10 09:05', 'admin', 'ORG001'),
    ('KPI003', 3, '创新能力', '定性', '{"weight": 0.2, "metrics": ["提案数", "采纳率"]}', '参考季度提案记录', '2025-01-10 09:10', 'admin', 'ORG001'),
    ('KPI004', 4, '考勤纪律', '定量', '{"weight": 0.1, "target": 100}', '迟到/缺勤次数', '2025-01-10 09:15', 'admin', 'ORG002'),
    ('KPI005', 5, '客户满意度', '定量', '{"weight": 0.5, "target": 90}', '来源于调研问卷', '2025-01-10 09:20', 'admin', 'ORG002');

-- auto-generated definition
create table hr_kpi_plan
(
    id          bigint auto_increment
        primary key,
    sort_no     int          null,
    plan_id     varchar(50)  null,
    title       varchar(100) null comment '考核标题',
    begin_time  varchar(20)  null comment '考核开始时间',
    end_time    varchar(20)  null comment '考核结束时间',
    kpi_rule    varchar(2)   null comment '考核规则',
    item_id     varchar(50)  null comment '考核项Id',
    charge_user varchar(50)  null comment '考核人',
    user_role   text         null comment '被考核人',
    dept_role   text         null comment '被考核人',
    level_role  text         null comment '被考核人',
    attach_id   text         null comment '相关附件',
    msg_type    varchar(50)  null comment '消息提醒方式',
    remark      varchar(400) null comment '备注说明',
    status      varchar(2)   null comment '0:未生效，1：生效中，2：终止',
    create_user varchar(50)  null,
    create_time varchar(20)  null,
    org_id      varchar(50)  null
)
    comment '考核计划' collate = utf8mb4_general_ci
                       row_format = DYNAMIC;

create index hr_k_p_b_t_inex
    on hr_kpi_plan (begin_time);

create index hr_k_p_e_t_index
    on hr_kpi_plan (end_time);

create index hr_k_p_p_i_index
    on hr_kpi_plan (plan_id);


-- 插入3条考核计划，关联上述指标ID
INSERT INTO hr_kpi_plan (sort_no, plan_id, title, begin_time, end_time, kpi_rule, item_id, charge_user, user_role, dept_role, level_role, status, create_user, create_time, org_id)
VALUES
    (1, 'PLAN2024Q1', '2024Q1绩效考核', '2024-01-01', '2024-03-31', 'A', 'KPI001,KPI002', 'user_manager', 'role_employee', 'dept_tech,dept_hr', 'level_mid', '1', 'admin', '2024-01-01 10:00', 'ORG001'),
    (2, 'PLAN2024Q2', '2024Q2晋升考核', '2024-04-01', '2024-06-30', 'B', 'KPI003,KPI004', 'user_hr', 'role_manager', 'dept_all', 'level_high', '1', 'admin', '2024-04-01 10:00', 'ORG001'),
    (3, 'PLAN2024Q3', '2024Q3年度评估', '2024-07-01', '2024-09-30', 'A', 'KPI002,KPI005', 'user_director', 'role_all', 'dept_sales,dept_mkt', 'level_all', '0', 'admin', '2024-07-01 10:00', 'ORG002');

-- auto-generated definition
create table hr_kpi_plan_record
(
    id          bigint auto_increment
        primary key,
    record_id   varchar(50)    null,
    plan_id     varchar(50)    null comment '关联计划ID',
    item_id     varchar(50)    null comment '关联考核指标',
    score       decimal(10, 2) null comment '分数',
    status      varchar(2)     null comment '0:未考核，1:已考核',
    account_id  varchar(50)    null comment '被考核人',
    charge_user varchar(50)    null comment '考核人',
    remark      varchar(1200)  null comment '说明',
    create_time varchar(20)    null,
    create_user varchar(50)    null,
    org_id      varchar(50)    null
)
    comment '人力资源考核详情' collate = utf8mb4_general_ci
                               row_format = DYNAMIC;

create index hr_k_r_i_i_index
    on hr_kpi_plan_record (item_id);

create index hr_k_r_p_i_index
    on hr_kpi_plan_record (plan_id);

create index hr_k_r_r_i_index
    on hr_kpi_plan_record (record_id);

-- 插入8条考核记录，关联计划和指标
INSERT INTO hr_kpi_plan_record (record_id, plan_id, item_id, score, status, account_id, charge_user, remark, create_time, create_user, org_id)
VALUES
    ('REC001', 'PLAN2024Q1', 'KPI001', 92.50, '1', 'EMP001', 'user_manager', '效率达标，但协作待提升', '2024-04-01 14:00', 'eval_user', 'ORG001'),
    ('REC002', 'PLAN2024Q1', 'KPI002', 85.00, '1', 'EMP001', 'user_manager', '团队协作评分良好', '2024-04-01 14:05', 'eval_user', 'ORG001'),
    ('REC003', 'PLAN2024Q2', 'KPI003', 78.50, '1', 'EMP002', 'user_hr', '创新提案较少', '2024-07-01 11:30', 'eval_user', 'ORG001'),
    ('REC004', 'PLAN2024Q2', 'KPI004', 100.00, '1', 'EMP002', 'user_hr', '全勤无迟到', '2024-07-01 11:35', 'eval_user', 'ORG001'),
    ('REC005', 'PLAN2024Q1', 'KPI001', 88.00, '0', 'EMP003', 'user_manager', '未完成最终评审', '2024-04-02 09:00', 'eval_user', 'ORG002'),
    ('REC006', 'PLAN2024Q3', 'KPI005', 95.50, '1', 'EMP004', 'user_director', '客户满意度超目标', '2024-10-01 15:20', 'eval_user', 'ORG002'),
    ('REC007', 'PLAN2024Q3', 'KPI002', 90.00, '1', 'EMP004', 'user_director', '跨部门协作表现突出', '2024-10-01 15:25', 'eval_user', 'ORG002'),
    ('REC008', 'PLAN2024Q2', 'KPI004', 60.00, '1', 'EMP005', 'user_hr', '缺勤3次，需改进', '2024-07-02 10:10', 'eval_user', 'ORG002');