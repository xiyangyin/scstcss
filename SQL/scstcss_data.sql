/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50721
Source Host           : localhost:3306
Source Database       : scstcss_data

Target Server Type    : MYSQL
Target Server Version : 50721
File Encoding         : 65001

Date: 2019-10-14 20:50:31
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for studio
-- ----------------------------
DROP TABLE IF EXISTS `studio`;
CREATE TABLE `studio` (
  `SID` int(20) NOT NULL AUTO_INCREMENT,
  `SNAME` varchar(255) DEFAULT NULL COMMENT '工作室名称',
  `SNUMBER` int(3) DEFAULT NULL COMMENT '工作室人数',
  `SADDRESS` varchar(100) DEFAULT NULL COMMENT '工作室所在地址',
  `SQQ` varchar(40) DEFAULT NULL COMMENT '联系人QQ号',
  `SSUMMARY` text COMMENT '工作室简介',
  `SLOGOPATH` varchar(255) DEFAULT NULL COMMENT '工作室logo网络地址',
  `SSTATUS` int(2) DEFAULT '0' COMMENT '提交工作室审核状态',
  `STIME` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '上传时间',
  PRIMARY KEY (`SID`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
