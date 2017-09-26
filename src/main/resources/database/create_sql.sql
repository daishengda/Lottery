/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50528
Source Host           : localhost:3306
Source Database       : lottery

Target Server Type    : MYSQL
Target Server Version : 50528
File Encoding         : 65001

Date: 2016-12-04 01:02:22
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `tbl_lottery_combination`
-- ----------------------------
DROP TABLE IF EXISTS `tbl_lottery_combination`;
CREATE TABLE `tbl_lottery_combination` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `left` varchar(20) DEFAULT '' COMMENT '左边号码(小)',
  `right` varchar(20) DEFAULT NULL COMMENT '右边号码(大)',
  PRIMARY KEY (`id`),
  KEY `combination_left_index` (`left`) USING BTREE,
  KEY `combination_right_index` (`right`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tbl_lottery_combination
-- ----------------------------

-- ----------------------------
-- Table structure for `tbl_lottery_record`
-- ----------------------------
DROP TABLE IF EXISTS `tbl_lottery_record`;
CREATE TABLE `tbl_lottery_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `number` varchar(30) NOT NULL DEFAULT '' COMMENT '期数(第几期)',
  `code` varchar(7) DEFAULT NULL COMMENT '开奖号码',
  `sortid` bigint(20) DEFAULT '0' COMMENT '针对number进行-处理成数字，方便排序',
  PRIMARY KEY (`id`),
  UNIQUE KEY `record_sortid_index` (`sortid`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tbl_lottery_record
-- ----------------------------
