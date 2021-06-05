create database iot_app;

use iot_app;

create table t_device_info
(
    id          int unsigned auto_increment
        primary key,
    code        varchar(128) default ''                not null comment '设备编号',
    name        varchar(128) default '未命名'             not null comment '设备名称',
    description text                                   null comment '设备描述',
    create_time datetime     default CURRENT_TIMESTAMP not null comment '创建时间',
    create_by   varchar(128) default '未分配'             not null comment '用户名',
    update_time datetime     default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    type        tinyint      default 0                 not null comment '设备类型'
)
    comment '设备基本信息';

create table t_device_message
(
    id        int unsigned auto_increment
        primary key,
    client    varchar(128)  default ''                not null comment '设备编号',
    info      varchar(128)  default ''                not null comment '数据信息',
    value     int           default 0                 not null comment '设备数据值',
    alert     int           default 0                 not null comment '是否报警',
    longitude double(12, 8) default 0.00000000        not null comment '经度',
    latitude  double(12, 8) default 0.00000000        not null comment '纬度',
    timestamp datetime      default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '时间戳'
)
    comment '传输数据信息' charset = utf8;

create table t_user_info
(
    id       int unsigned auto_increment
        primary key,
    name     varchar(128) default '' not null comment '名字',
    password varchar(128) default '' not null comment '密码',
    email    varchar(128) default '' not null comment '邮箱',
    avatar   varchar(128) default '' null comment '头像URL'
)
    comment '用户基本信息';

