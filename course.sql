/*
 Navicat Premium Dump SQL

 Source Server         : homework
 Source Server Type    : MySQL
 Source Server Version : 80026 (8.0.26)
 Source Host           : localhost:3306
 Source Schema         : course

 Target Server Type    : MySQL
 Target Server Version : 80026 (8.0.26)
 File Encoding         : 65001

 Date: 24/08/2025 15:13:47
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for course
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `coursename` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `coursenum` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `courseteacher` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `coursedate` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '',
  `coursedesc` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `courseroom` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '',
  `status` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of course
-- ----------------------------
INSERT INTO `course` VALUES ('07197654', 'mysql', '187', '01', '星期二3-4', NULL, 'B教室', '1');
INSERT INTO `course` VALUES ('21238783', 'java', '188', '05', '星期二5-6', NULL, 'B教室', '1');
INSERT INTO `course` VALUES ('29847875', 'python', '45', '2205002', '星期三3-4', NULL, 'B教室', '1');

-- ----------------------------
-- Table structure for crcount
-- ----------------------------
DROP TABLE IF EXISTS `crcount`;
CREATE TABLE `crcount`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `teacher_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '教师名称',
  `tk_count` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '听课次数',
  `bt_count` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '被听课次数',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `updatetime` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8662422 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '课程统计信息' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of crcount
-- ----------------------------
INSERT INTO `crcount` VALUES (7403534, '李四', '2', '1', '2021-03-31 15:03:01', '2021-04-09 11:44:49');
INSERT INTO `crcount` VALUES (8662421, '张三', '1', '3', '2021-03-31 14:57:50', '2021-04-09 11:44:49');

-- ----------------------------
-- Table structure for curelation
-- ----------------------------
DROP TABLE IF EXISTS `curelation`;
CREATE TABLE `curelation`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `courseid` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `userid` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `type` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of curelation
-- ----------------------------
INSERT INTO `curelation` VALUES ('.1478368', '87179597', NULL, '计算机网络', '张三');
INSERT INTO `curelation` VALUES ('.4793548', '07197654', NULL, 'mysql', '蔡奕成2');
INSERT INTO `curelation` VALUES ('72050220', '73515590', '02', '数据结构', '张三,null');

-- ----------------------------
-- Table structure for notice
-- ----------------------------
DROP TABLE IF EXISTS `notice`;
CREATE TABLE `notice`  (
  `id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `creatdate` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `userid` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of notice
-- ----------------------------
INSERT INTO `notice` VALUES ('984287', '2023-01-20 22:19:04', '1', 'admin', '1');

-- ----------------------------
-- Table structure for power
-- ----------------------------
DROP TABLE IF EXISTS `power`;
CREATE TABLE `power`  (
  `id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `roleid` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `power` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of power
-- ----------------------------
INSERT INTO `power` VALUES ('1', '1111', '1001,2001,3001,3002,4002');
INSERT INTO `power` VALUES ('2', '2222', '1001,1002,2001,3001,3002,4001');
INSERT INTO `power` VALUES ('3', '3333', '1001,1002,2001,3001,3002,4001,4002');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `roleid` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `powerid` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `rolename` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`roleid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('0001', '1111', '教师');
INSERT INTO `role` VALUES ('0002', '2222', '主任');
INSERT INTO `role` VALUES ('0003', '3333', '管理员');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sex` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `mobile` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `roleid` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `type` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('01', '张三', '男', '13546895131', '0001', '123456', '1');
INSERT INTO `user` VALUES ('02', '李四', '男', '13586952257', '0001', '123456', '1');
INSERT INTO `user` VALUES ('03', '刘六', '男', '18963214584', '0001', '123456', '1');
INSERT INTO `user` VALUES ('05', '蔡奕成2', '男', '12', '0001', '123456', '1');
INSERT INTO `user` VALUES ('2205002', '蔡奕成', '男', '1111111', '0001', '123456', '1');
INSERT INTO `user` VALUES ('admin', '管理员', '男', '18813935480', '0003', '12345', '3');
INSERT INTO `user` VALUES ('zr01', '王五', '男', '13584529974', '0002', '123456', '2');
INSERT INTO `user` VALUES ('zr02', '刘六', '男', '4564987', '0002', '123456', '2');

SET FOREIGN_KEY_CHECKS = 1;
