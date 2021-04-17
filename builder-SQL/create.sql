CREATE TABLE `t_user_info` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(128) NOT NULL DEFAULT '' COMMENT '名字',
  `password` varchar(128) NOT NULL DEFAULT '' COMMENT '密码',
  `email` varchar(128) NOT NULL DEFAULT '' COMMENT '邮箱',
  `avatar` varchar(128) DEFAULT '' COMMENT '头像URL',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '用户基本信息';

CREATE TABLE `t_device_info` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `code` varchar(128) NOT NULL DEFAULT '' COMMENT '设备编号',
  `name` varchar(128) NOT NULL DEFAULT '未命名' COMMENT '设备名称',
  `description` text COMMENT '设备描述',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_by` VARCHAR(128) NOT NULL DEFAULT '未分配' COMMENT '用户ID',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `type` tinyint(4) NOT NULL DEFAULT 0 COMMENT '设备类型',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '设备基本信息';

CREATE TABLE `t_device_message` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `client` varchar(128) NOT NULL DEFAULT '' COMMENT '设备编号',
  `info` varchar(128) NOT NULL DEFAULT '' COMMENT '数据信息',
  `value` int(11) NOT NULL DEFAULT 0 COMMENT '设备数据值',
  `alert` int(11) NOT NULL DEFAULT 0 COMMENT '是否报警',
  `longitude` DOUBLE(12,8) NOT NULL DEFAULT 0 COMMENT '经度',
  `latitude` DOUBLE(12,8) NOT NULL DEFAULT 0 COMMENT '纬度',
  `timestamp` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '时间戳',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '传输数据信息';