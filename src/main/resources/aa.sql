/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50621
Source Host           : localhost:3306
Source Database       : kanjian

Target Server Type    : MYSQL
Target Server Version : 50699
File Encoding         : 65001

Date: 2015-03-13 00:49:34
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_auth_account
-- ----------------------------
DROP TABLE IF EXISTS `t_auth_account`;
CREATE TABLE `t_auth_account` (
`ID`  int(10) UNSIGNED NOT NULL ,
`MOBILEPHONE`  char(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`PASSWORD`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`NICK_NAME`  varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`CREATE_DATE`  datetime NULL DEFAULT NULL ,
PRIMARY KEY (`ID`),
UNIQUE INDEX `UQ_T_AUTH_ACCOUNT_MOBILEPHONE` (`MOBILEPHONE`) USING BTREE 
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci

;

-- ----------------------------
-- Records of t_auth_account
-- ----------------------------
BEGIN;
INSERT INTO `t_auth_account` VALUES ('5643', '123456655', null, null, '2015-02-21 13:01:19'), ('6900', '1234566533', null, null, '2015-02-21 13:02:15');
COMMIT;
