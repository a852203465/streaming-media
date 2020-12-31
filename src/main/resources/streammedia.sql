/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50731
 Source Host           : localhost:3306
 Source Schema         : stream-media

 Target Server Type    : MySQL
 Target Server Version : 50731
 File Encoding         : 65001

 Date: 26/12/2020 16:12:59
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for cn_t_device
-- ----------------------------
# DROP TABLE IF EXISTS `device`;
CREATE TABLE IF NOT EXISTS `device`  (
  `id` bigint(20) NOT NULL COMMENT '信息主键',
  `created_user` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '添加人',
  `created_time` bigint(20) NULL DEFAULT NULL COMMENT '添加时间',
  `updated_time` bigint(20) NULL DEFAULT NULL COMMENT '修改时间',
  `updated_user` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `serial_number` varchar(150) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '序列号',
  `ip` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '抓拍机ip',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  `port` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '抓拍机端口',
  `username` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名称',
  `online` tinyint(4) NULL DEFAULT NULL COMMENT '是否在线（0：离线，1:在线）',
  `main_streams` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '主码流（多个时，以\",\"）',
  `subcode_flows` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '子码流（多个时，以\",\"）',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `index_ip`(`ip`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '抓拍机信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for dvr
-- ----------------------------
# DROP TABLE IF EXISTS `dvr`;
CREATE TABLE IF NOT EXISTS `dvr`  (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `device_id` bigint(20) NULL DEFAULT NULL COMMENT '所有设备',
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '访问路径',
  `file` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文件名',
  `created_user` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '添加人',
  `created_time` bigint(20) NULL DEFAULT NULL COMMENT '添加时间',
  `updated_time` bigint(20) NULL DEFAULT NULL COMMENT '修改时间',
  `updated_user` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  `raw_file` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '原文件地址',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;


SET FOREIGN_KEY_CHECKS = 1;
