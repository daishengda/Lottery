CREATE TABLE IF NOT EXISTS `tbl_lottery_miss_group`(
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `digit` int(4) DEFAULT NULL COMMENT '位数，如2、3、4、5位',
  `group` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `tbl_lottery_miss_relation`(
  `group_id` int(11) NOT NULL DEFAULT '0',
  `result_id` bigint(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`group_id`,`result_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `tbl_lottery_miss_result`(
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `begin` bigint(20) DEFAULT NULL COMMENT '开始期号',
  `end` bigint(20) DEFAULT NULL COMMENT '结束期号',
  `miss_periods` int(11) DEFAULT NULL COMMENT '遗漏期数',
  `type` int(11) DEFAULT NULL COMMENT '类型，如2、3、4、5',
  `digit` int(11) DEFAULT NULL COMMENT '位数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;