-- auto-generated definition
create table office_supplies
(
    id            bigint auto_increment
        primary key,
    sort_no       int          null,
    supplies_id   varchar(50)  null comment '物资ID',
    sort_id       varchar(50)  null comment '分类Id',
    supplies_name varchar(50)  null comment '物资名称',
    supplies_code varchar(50)  null comment '物资编号',
    quantity      int          null comment '数量',
    brand         varchar(50)  null comment '品牌',
    model         varchar(100) null comment '型号',
    unit          varchar(50)  null comment '计量单位',
    status        varchar(2)   null comment '当前状态',
    remark        varchar(200) null comment '备注说明',
    own_dept      text         null,
    create_time   varchar(20)  null,
    create_user   varchar(50)  null,
    org_id        varchar(50)  null
)
    comment '物资记录' collate = utf8mb4_general_ci
                       row_format = DYNAMIC;

create index office_s_s_i_index
    on office_supplies (supplies_id);

create index office_s_so_i_index
    on office_supplies (sort_id);

-- auto-generated definition
create table office_supplies_apply
(
    id                bigint auto_increment
        primary key,
    apply_id          varchar(50)  null comment '申请记录唯一标识',
    title             varchar(100) null comment '申请标题',
    supplies_id       varchar(50)  null comment '物资Id',
    remark            text         null comment '备注说明',
    quantity          int          null comment '数量',
    used_user         varchar(50)  null comment '使用人',
    status            varchar(2)   null comment '0:审批中1:同意2:不同意3:发放完成',
    approval_user     varchar(50)  null comment '审批人',
    approval_time     varchar(20)  null comment '审批时间',
    approval_quantity int          null comment '审批同意数量',
    approval_idea     varchar(200) null comment '审批意见',
    create_time       varchar(20)  null,
    create_user       varchar(50)  null,
    org_id            varchar(50)  null
)
    comment '物资申请' collate = utf8mb4_general_ci
                       row_format = DYNAMIC;

create index office_s_a_a_i_index
    on office_supplies_apply (apply_id);

create index office_s_a_s_i_index
    on office_supplies_apply (supplies_id);

-- auto-generated definition
create table office_supplies_grant
(
    id          bigint auto_increment
        primary key,
    grant_id    varchar(50) null comment '发放记录唯一标识',
    apply_id    varchar(50) null comment '关联申请Id',
    supplies_id varchar(50) null comment '发放物质ID',
    get_user    varchar(50) null comment '接收人',
    quantity    int         null comment '数量',
    remark      text        null comment '备注说明',
    create_time varchar(20) null,
    create_user varchar(50) null,
    org_id      varchar(50) null
)
    comment '物质发放记录' collate = utf8mb4_general_ci
                           row_format = DYNAMIC;

create index office_s_g_a_i_index
    on office_supplies_grant (apply_id);

create index office_s_g_g_i_index
    on office_supplies_grant (grant_id);

create index office_s_g_s_i_index
    on office_supplies_grant (supplies_id);

-- auto-generated definition
create table office_supplies_sort
(
    id          bigint auto_increment
        primary key,
    sort_id     varchar(50) null,
    sort_no     int         null,
    sort_name   varchar(50) null,
    parent_id   varchar(50) null,
    create_user varchar(50) null,
    create_time varchar(20) null,
    org_id      varchar(50) null
)
    comment '物资分类' collate = utf8mb4_general_ci
                       row_format = DYNAMIC;

create index office_s_s_p_i_index
    on office_supplies_sort (parent_id);

create index office_s_s_s_i_index
    on office_supplies_sort (sort_id);



