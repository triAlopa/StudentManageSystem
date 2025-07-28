/*
 Navicat Premium Data Transfer

 Source Server         : Mysql80
 Source Server Type    : MySQL
 Source Server Version : 80041
 Source Host           : localhost:3306
 Source Schema         : stumanage

 Target Server Type    : MySQL
 Target Server Version : 80041
 File Encoding         : 65001

 Date: 14/06/2025 22:02:18
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for academicinfo
-- ----------------------------
DROP TABLE IF EXISTS `academicinfo`;
CREATE TABLE `academicinfo`  (
  `student_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `counselor_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `class_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `major_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `grade` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '如: 2023级',
  `college_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`student_id`) USING BTREE,
  INDEX `idx_class_id`(`class_id` ASC) USING BTREE,
  INDEX `idx_major_id`(`major_id` ASC) USING BTREE,
  INDEX `idx_college_id`(`college_id` ASC) USING BTREE,
  INDEX `idx_counselor_id`(`counselor_id` ASC) USING BTREE,
  CONSTRAINT `academicinfo_ibfk_1` FOREIGN KEY (`student_id`) REFERENCES `student` (`student_id`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `academicinfo_ibfk_2` FOREIGN KEY (`counselor_id`) REFERENCES `counselor` (`counselor_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `academicinfo_ibfk_3` FOREIGN KEY (`class_id`) REFERENCES `class` (`class_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `academicinfo_ibfk_4` FOREIGN KEY (`major_id`) REFERENCES `major` (`major_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `academicinfo_ibfk_5` FOREIGN KEY (`college_id`) REFERENCES `college` (`college_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '学生学业信息表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of academicinfo
-- ----------------------------
INSERT INTO `academicinfo` VALUES ('52501011001', '7234', '525010110', '5250101', '大三', '52501', '2000-05-23 11:37:41', '2003-11-21 00:29:27');
INSERT INTO `academicinfo` VALUES ('52501011002', '7234', '525010110', '5250101', '大四', '52501', '2006-05-06 10:44:36', '2023-01-31 10:09:02');
INSERT INTO `academicinfo` VALUES ('52501011003', '7234', '525010110', '5250101', '大二', '52501', '2008-04-27 12:02:25', '2020-06-14 19:43:56');
INSERT INTO `academicinfo` VALUES ('52501011004', '7234', '525010110', '5250101', '大一', '52501', '2021-03-20 17:10:56', '2018-07-14 13:56:33');
INSERT INTO `academicinfo` VALUES ('52501011005', '7234', '525010110', '5250101', '大三', '52501', '2006-02-17 11:46:59', '2012-03-14 02:12:19');
INSERT INTO `academicinfo` VALUES ('52501011006', '7234', '525010110', '5250101', '大四', '52501', '2011-02-25 12:57:46', '2022-02-11 11:17:10');
INSERT INTO `academicinfo` VALUES ('52501011007', '7234', '525010110', '5250101', '大四', '52501', '2020-06-08 01:32:32', '2021-08-02 20:29:26');
INSERT INTO `academicinfo` VALUES ('52501011008', '7234', '525010110', '5250101', '大三', '52501', '2024-06-20 11:53:11', '2000-11-21 12:26:03');
INSERT INTO `academicinfo` VALUES ('52501011009', '7234', '525010110', '5250101', '大四', '52501', '2017-10-20 03:28:35', '2001-04-23 05:49:32');
INSERT INTO `academicinfo` VALUES ('52501011010', '7234', '525010110', '5250101', '大一', '52501', '2001-05-14 03:44:43', '2008-08-25 12:18:46');
INSERT INTO `academicinfo` VALUES ('52501011011', '7234', '525010110', '5250101', '大一', '52501', '2016-01-07 15:00:17', '2017-02-27 23:31:24');
INSERT INTO `academicinfo` VALUES ('52501011012', '7234', '525010110', '5250101', '大一', '52501', '2000-11-23 18:25:32', '2024-09-12 09:26:46');
INSERT INTO `academicinfo` VALUES ('52501011013', '7234', '525010110', '5250101', '大四', '52501', '2001-09-29 01:07:49', '2014-09-13 01:21:34');
INSERT INTO `academicinfo` VALUES ('52501011014', '7234', '525010110', '5250101', '大二', '52501', '2009-12-14 14:48:49', '2013-04-03 07:49:56');
INSERT INTO `academicinfo` VALUES ('52501011015', '7234', '525010110', '5250101', '大四', '52501', '2017-12-14 14:01:36', '2013-04-11 01:15:53');
INSERT INTO `academicinfo` VALUES ('52501011016', '7234', '525010110', '5250101', '大一', '52501', '2016-05-14 04:43:56', '2016-10-10 18:17:23');
INSERT INTO `academicinfo` VALUES ('52501011017', '7234', '525010110', '5250101', '大三', '52501', '2014-01-25 17:09:41', '2020-12-15 20:08:33');
INSERT INTO `academicinfo` VALUES ('52501011018', '7234', '525010110', '5250101', '大二', '52501', '2006-11-22 17:28:23', '2001-08-01 11:04:23');
INSERT INTO `academicinfo` VALUES ('52501011019', '7234', '525010110', '5250101', '大一', '52501', '2024-02-15 08:02:00', '2006-07-25 09:28:21');
INSERT INTO `academicinfo` VALUES ('52501011020', '7234', '525010110', '5250101', '大二', '52501', '2016-02-23 19:32:44', '2007-04-02 04:59:23');
INSERT INTO `academicinfo` VALUES ('52501011021', '7234', '525010110', '5250101', '大四', '52501', '2021-03-06 10:13:08', '2003-02-23 15:00:41');
INSERT INTO `academicinfo` VALUES ('52501011022', '7234', '525010110', '5250101', '大二', '52501', '2017-06-05 01:58:21', '2001-01-12 13:35:28');
INSERT INTO `academicinfo` VALUES ('52501011023', '7234', '525010110', '5250101', '大四', '52501', '2015-03-08 03:58:15', '2006-05-31 02:19:20');
INSERT INTO `academicinfo` VALUES ('52501011024', '7234', '525010110', '5250101', '大二', '52501', '2018-10-24 09:57:36', '2019-01-10 15:08:01');
INSERT INTO `academicinfo` VALUES ('52501011025', '7234', '525010110', '5250101', '大三', '52501', '2016-08-02 20:17:50', '2008-10-03 00:25:10');
INSERT INTO `academicinfo` VALUES ('52501011026', '7234', '525010110', '5250101', '大一', '52501', '2022-06-14 21:27:11', '2019-09-10 18:28:39');
INSERT INTO `academicinfo` VALUES ('52501011027', '7234', '525010110', '5250101', '大四', '52501', '2025-05-29 23:10:02', '2001-11-02 10:32:59');
INSERT INTO `academicinfo` VALUES ('52501011028', '7234', '525010110', '5250101', '大二', '52501', '2022-05-03 09:04:40', '2021-06-21 01:11:39');
INSERT INTO `academicinfo` VALUES ('52501011029', '7234', '525010110', '5250101', '大四', '52501', '2003-02-20 04:19:14', '2011-04-18 22:16:18');
INSERT INTO `academicinfo` VALUES ('52501011030', '7234', '525010110', '5250101', '大四', '52501', '2021-01-05 09:05:15', '2010-06-01 03:43:05');
INSERT INTO `academicinfo` VALUES ('52501011031', '7234', '525010110', '5250101', '大二', '52501', '2005-06-27 10:06:18', '2021-10-06 10:17:56');
INSERT INTO `academicinfo` VALUES ('52501011032', '7234', '525010110', '5250101', '大四', '52501', '2019-01-10 18:37:32', '2018-07-26 04:46:55');
INSERT INTO `academicinfo` VALUES ('52501011033', '7234', '525010110', '5250101', '大二', '52501', '2013-10-31 09:54:21', '2019-02-26 21:42:23');
INSERT INTO `academicinfo` VALUES ('52501011034', '7234', '525010110', '5250101', '大二', '52501', '2004-09-21 16:48:41', '2003-01-22 01:22:01');
INSERT INTO `academicinfo` VALUES ('52501011035', '7234', '525010110', '5250101', '大一', '52501', '2016-10-03 21:39:47', '2008-10-08 03:20:38');
INSERT INTO `academicinfo` VALUES ('52501011036', '7234', '525010110', '5250101', '大四', '52501', '2020-11-04 06:32:32', '2004-09-06 11:55:29');
INSERT INTO `academicinfo` VALUES ('52501011037', '7234', '525010110', '5250101', '大三', '52501', '2002-03-08 21:50:15', '2009-12-10 23:44:02');
INSERT INTO `academicinfo` VALUES ('52501011038', '7234', '525010110', '5250101', '大三', '52501', '2023-07-10 19:59:23', '2002-09-15 13:24:07');
INSERT INTO `academicinfo` VALUES ('52501011039', '7234', '525010110', '5250101', '大四', '52501', '2003-09-18 17:47:28', '2003-07-31 17:48:05');
INSERT INTO `academicinfo` VALUES ('52501011040', '7234', '525010110', '5250101', '大二', '52501', '2005-03-19 02:02:03', '2015-09-03 00:21:25');
INSERT INTO `academicinfo` VALUES ('52501011041', '7234', '525010110', '5250101', '大四', '52501', '2007-10-08 16:10:17', '2005-05-22 20:19:44');
INSERT INTO `academicinfo` VALUES ('52501011042', '7234', '525010110', '5250101', '大二', '52501', '2013-02-26 21:40:28', '2017-06-26 07:38:09');
INSERT INTO `academicinfo` VALUES ('52501011043', '7234', '525010110', '5250101', '大三', '52501', '2005-02-10 05:18:29', '2002-10-11 11:06:45');
INSERT INTO `academicinfo` VALUES ('52501011044', '7234', '525010110', '5250101', '大二', '52501', '2015-07-07 19:53:04', '2013-04-14 12:07:14');
INSERT INTO `academicinfo` VALUES ('52501011045', '7234', '525010110', '5250101', '大三', '52501', '2001-08-30 21:38:12', '2022-05-12 17:53:02');
INSERT INTO `academicinfo` VALUES ('52501011046', '7234', '525010110', '5250101', '大三', '52501', '2013-07-03 21:17:51', '2019-03-02 06:17:32');
INSERT INTO `academicinfo` VALUES ('52501011047', '7234', '525010110', '5250101', '大二', '52501', '2002-01-12 20:16:58', '2021-08-04 19:04:35');
INSERT INTO `academicinfo` VALUES ('52501011048', '7234', '525010110', '5250101', '大四', '52501', '2005-06-25 15:04:31', '2006-01-22 02:17:17');
INSERT INTO `academicinfo` VALUES ('52501011049', '7234', '525010110', '5250101', '大四', '52501', '2010-05-03 00:19:33', '2006-01-17 06:28:30');
INSERT INTO `academicinfo` VALUES ('52501011050', '7234', '525010110', '5250101', '大四', '52501', '2008-02-07 04:29:53', '2012-08-31 15:20:48');
INSERT INTO `academicinfo` VALUES ('52501011051', '7234', '525010110', '5250101', '大二', '52501', '2011-03-21 04:06:13', '2013-09-11 07:42:02');
INSERT INTO `academicinfo` VALUES ('52501011052', '7234', '525010110', '5250101', '大三', '52501', '2021-02-03 20:21:16', '2019-12-03 11:33:26');
INSERT INTO `academicinfo` VALUES ('52501011054', '7234', '525010110', '5250101', '大四', '52501', '2013-07-18 10:51:55', '2003-02-19 20:03:23');
INSERT INTO `academicinfo` VALUES ('52501011055', '7234', '525010110', '5250101', '大一', '52501', '2001-12-30 15:24:19', '2000-06-19 01:14:53');
INSERT INTO `academicinfo` VALUES ('52501011056', '7234', '525010110', '5250101', '大四', '52501', '2017-09-28 16:35:56', '2012-10-19 01:25:20');
INSERT INTO `academicinfo` VALUES ('52501012001', '7234', '525010120', '5250101', '大四', '52501', '2003-04-25 23:09:56', '2009-05-27 03:34:34');
INSERT INTO `academicinfo` VALUES ('52501012002', '7234', '525010120', '5250101', '大二', '52501', '2003-12-01 00:10:10', '2003-04-30 13:53:24');
INSERT INTO `academicinfo` VALUES ('52501012003', '7234', '525010120', '5250101', '大三', '52501', '2017-06-18 18:49:49', '2014-11-24 09:51:21');
INSERT INTO `academicinfo` VALUES ('52501012004', '7234', '525010120', '5250101', '大二', '52501', '2012-10-01 22:01:52', '2021-04-24 14:18:46');
INSERT INTO `academicinfo` VALUES ('52501012005', '7234', '525010120', '5250101', '大三', '52501', '2002-05-22 18:38:31', '2021-03-29 01:58:21');
INSERT INTO `academicinfo` VALUES ('52501012006', '7234', '525010120', '5250101', '大四', '52501', '2023-10-14 06:55:28', '2016-05-30 02:33:02');
INSERT INTO `academicinfo` VALUES ('52501012007', '7234', '525010120', '5250101', '大二', '52501', '2002-11-17 09:05:13', '2020-11-22 00:28:05');
INSERT INTO `academicinfo` VALUES ('52501012008', '7234', '525010120', '5250101', '大三', '52501', '2024-09-20 03:44:37', '2025-03-08 17:42:52');
INSERT INTO `academicinfo` VALUES ('52501012009', '7234', '525010120', '5250101', '大一', '52501', '2024-05-19 15:55:15', '2015-03-23 13:50:07');
INSERT INTO `academicinfo` VALUES ('52501012010', '7234', '525010120', '5250101', '大三', '52501', '2022-09-17 23:04:33', '2021-03-28 20:00:41');
INSERT INTO `academicinfo` VALUES ('52501012011', '7234', '525010120', '5250101', '大一', '52501', '2024-06-11 13:30:03', '2000-01-10 08:33:36');
INSERT INTO `academicinfo` VALUES ('52501012012', '7234', '525010120', '5250101', '大四', '52501', '2003-12-16 05:14:42', '2009-10-26 08:39:55');
INSERT INTO `academicinfo` VALUES ('52501012013', '7234', '525010120', '5250101', '大一', '52501', '2015-02-11 05:26:14', '2025-03-21 00:35:15');
INSERT INTO `academicinfo` VALUES ('52501012014', '7234', '525010120', '5250101', '大三', '52501', '2019-07-12 23:25:06', '2000-02-14 01:53:16');
INSERT INTO `academicinfo` VALUES ('52501012015', '7234', '525010120', '5250101', '大二', '52501', '2015-10-01 01:55:07', '2002-03-07 23:16:12');
INSERT INTO `academicinfo` VALUES ('52501012016', '7234', '525010120', '5250101', '大四', '52501', '2011-07-18 17:18:36', '2016-11-03 09:40:30');
INSERT INTO `academicinfo` VALUES ('52501012017', '7234', '525010120', '5250101', '大一', '52501', '2002-08-28 23:12:01', '2008-12-08 06:48:22');
INSERT INTO `academicinfo` VALUES ('52501012018', '7234', '525010120', '5250101', '大三', '52501', '2020-03-28 04:22:57', '2009-01-02 19:43:38');
INSERT INTO `academicinfo` VALUES ('52501012019', '7234', '525010120', '5250101', '大一', '52501', '2000-12-17 23:22:05', '2005-08-01 12:51:14');
INSERT INTO `academicinfo` VALUES ('52501012020', '7234', '525010120', '5250101', '大三', '52501', '2001-05-03 17:33:41', '2003-07-04 23:09:34');
INSERT INTO `academicinfo` VALUES ('52501012021', '7234', '525010120', '5250101', '大二', '52501', '2020-08-13 08:41:05', '2006-03-11 05:29:39');
INSERT INTO `academicinfo` VALUES ('52501012022', '7234', '525010120', '5250101', '大四', '52501', '2016-12-01 13:15:09', '2018-04-10 11:13:45');
INSERT INTO `academicinfo` VALUES ('52501012023', '7234', '525010120', '5250101', '大二', '52501', '2020-12-06 23:35:33', '2007-10-10 01:36:14');
INSERT INTO `academicinfo` VALUES ('52501012024', '7234', '525010120', '5250101', '大二', '52501', '2016-11-21 18:19:20', '2011-09-17 14:36:20');
INSERT INTO `academicinfo` VALUES ('52501012025', '7234', '525010120', '5250101', '大二', '52501', '2012-11-15 06:34:26', '2013-02-16 02:06:36');
INSERT INTO `academicinfo` VALUES ('52501012026', '7234', '525010120', '5250101', '大三', '52501', '2011-11-14 21:59:12', '2016-02-02 03:53:26');
INSERT INTO `academicinfo` VALUES ('52501012027', '7234', '525010120', '5250101', '大二', '52501', '2013-06-24 21:36:01', '2012-10-04 23:22:47');
INSERT INTO `academicinfo` VALUES ('52501012028', '7234', '525010120', '5250101', '大二', '52501', '2006-10-22 12:15:32', '2022-04-01 06:01:52');
INSERT INTO `academicinfo` VALUES ('52501012029', '7234', '525010120', '5250101', '大四', '52501', '2009-05-02 14:07:58', '2013-07-23 12:32:34');
INSERT INTO `academicinfo` VALUES ('52501012030', '7234', '525010120', '5250101', '大三', '52501', '2011-01-03 17:07:12', '2021-06-30 21:38:40');
INSERT INTO `academicinfo` VALUES ('52501012031', '7234', '525010120', '5250101', '大一', '52501', '2014-07-21 01:59:58', '2022-03-23 07:56:44');
INSERT INTO `academicinfo` VALUES ('52501012032', '7234', '525010120', '5250101', '大四', '52501', '2011-09-22 19:44:20', '2022-12-05 14:27:05');
INSERT INTO `academicinfo` VALUES ('52501012033', '7234', '525010120', '5250101', '大三', '52501', '2015-04-06 15:59:31', '2020-01-18 22:03:16');
INSERT INTO `academicinfo` VALUES ('52501012034', '7234', '525010120', '5250101', '大三', '52501', '2021-10-09 14:37:44', '2010-05-06 10:53:32');
INSERT INTO `academicinfo` VALUES ('52501012035', '7234', '525010120', '5250101', '大二', '52501', '2004-07-08 09:41:53', '2025-05-18 03:21:50');
INSERT INTO `academicinfo` VALUES ('52501012036', '7234', '525010120', '5250101', '大四', '52501', '2008-08-23 15:30:06', '2018-10-24 21:58:31');
INSERT INTO `academicinfo` VALUES ('52501012037', '7234', '525010120', '5250101', '大一', '52501', '2025-02-10 19:36:00', '2024-02-17 16:08:56');
INSERT INTO `academicinfo` VALUES ('52501012038', '7234', '525010120', '5250101', '大二', '52501', '2007-04-23 08:04:36', '2018-09-20 04:24:14');
INSERT INTO `academicinfo` VALUES ('52501012039', '7234', '525010120', '5250101', '大二', '52501', '2013-10-05 15:58:31', '2024-12-11 23:01:58');
INSERT INTO `academicinfo` VALUES ('52501012040', '7234', '525010120', '5250101', '大一', '52501', '2000-09-27 01:54:52', '2019-12-28 22:58:09');
INSERT INTO `academicinfo` VALUES ('52501012041', '7234', '525010120', '5250101', '大一', '52501', '2020-03-29 03:00:30', '2013-06-28 10:27:31');
INSERT INTO `academicinfo` VALUES ('52501012042', '7234', '525010120', '5250101', '大二', '52501', '2008-02-07 18:20:13', '2016-06-15 14:10:05');
INSERT INTO `academicinfo` VALUES ('52501012043', '7234', '525010120', '5250101', '大四', '52501', '2010-09-18 09:27:12', '2012-10-31 16:42:10');
INSERT INTO `academicinfo` VALUES ('52501012044', '7234', '525010120', '5250101', '大四', '52501', '2011-05-02 06:12:23', '2025-06-06 16:47:08');
INSERT INTO `academicinfo` VALUES ('52501012045', '7234', '525010120', '5250101', '大四', '52501', '2010-09-09 03:21:31', '2004-12-10 05:15:46');
INSERT INTO `academicinfo` VALUES ('52501012046', '7234', '525010120', '5250101', '大三', '52501', '2003-05-24 23:19:05', '2022-10-21 23:06:50');
INSERT INTO `academicinfo` VALUES ('52501012047', '7234', '525010120', '5250101', '大三', '52501', '2000-03-07 14:08:10', '2000-05-27 22:59:46');
INSERT INTO `academicinfo` VALUES ('52501012048', '7234', '525010120', '5250101', '大一', '52501', '2024-04-06 09:36:55', '2004-09-25 06:17:09');
INSERT INTO `academicinfo` VALUES ('52501012049', '7234', '525010120', '5250101', '大四', '52501', '2017-10-01 08:09:25', '2007-01-28 02:30:17');
INSERT INTO `academicinfo` VALUES ('52501012050', '7234', '525010120', '5250101', '大三', '52501', '2003-03-29 21:07:52', '2013-09-12 18:30:01');
INSERT INTO `academicinfo` VALUES ('52501012051', '7234', '525010120', '5250101', '大二', '52501', '2002-11-23 09:18:46', '2012-06-11 20:33:38');
INSERT INTO `academicinfo` VALUES ('52501012052', '7234', '525010120', '5250101', '大四', '52501', '2007-12-10 15:57:45', '2004-08-11 02:32:52');
INSERT INTO `academicinfo` VALUES ('52501012053', '7234', '525010120', '5250101', '大三', '52501', '2001-08-11 07:14:30', '2006-07-30 08:26:39');
INSERT INTO `academicinfo` VALUES ('52501012054', '7234', '525010120', '5250101', '大一', '52501', '2004-01-24 10:15:29', '2007-02-27 17:36:43');

-- ----------------------------
-- Table structure for class
-- ----------------------------
DROP TABLE IF EXISTS `class`;
CREATE TABLE `class`  (
  `class_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `class_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `major_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `counselor_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '辅导员ID',
  PRIMARY KEY (`class_id`) USING BTREE,
  INDEX `idx_class_name`(`class_name` ASC) USING BTREE,
  INDEX `idx_major_id`(`major_id` ASC) USING BTREE,
  INDEX `fk_class_counselor`(`counselor_id` ASC) USING BTREE,
  CONSTRAINT `class_ibfk_1` FOREIGN KEY (`major_id`) REFERENCES `major` (`major_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_class_counselor` FOREIGN KEY (`counselor_id`) REFERENCES `counselor` (`counselor_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '班级信息表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of class
-- ----------------------------
INSERT INTO `class` VALUES ('525010110', '测试翻页班级', '5250101', '2025-06-02 22:35:41', '2025-06-02 22:36:00', '7234');
INSERT INTO `class` VALUES ('525010120', '测试翻页班级1', '5250101', '2025-06-12 17:16:06', '2025-06-12 17:16:21', '7234');

-- ----------------------------
-- Table structure for college
-- ----------------------------
DROP TABLE IF EXISTS `college`;
CREATE TABLE `college`  (
  `college_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `college_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `established_date` date NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`college_id`) USING BTREE,
  INDEX `idx_college_name`(`college_name` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '学院信息表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of college
-- ----------------------------
INSERT INTO `college` VALUES ('52501', '测试de学院', '2042-08-09', '2025-06-02 22:33:52', '2025-06-02 22:33:52');
INSERT INTO `college` VALUES ('52511', '测试学院人数为空', '2025-06-12', '2025-06-12 21:16:58', '2025-06-12 21:16:58');

-- ----------------------------
-- Table structure for counselor
-- ----------------------------
DROP TABLE IF EXISTS `counselor`;
CREATE TABLE `counselor`  (
  `counselor_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `phone` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `college_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`counselor_id`) USING BTREE,
  INDEX `college_id`(`college_id` ASC) USING BTREE,
  INDEX `idx_name`(`name` ASC) USING BTREE,
  CONSTRAINT `counselor_ibfk_1` FOREIGN KEY (`college_id`) REFERENCES `college` (`college_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '辅导员信息表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of counselor
-- ----------------------------
INSERT INTO `counselor` VALUES ('7234', '测试辅导员', '17232456799', '52501', '2025-06-02 22:35:06', '2025-06-02 22:35:06');

-- ----------------------------
-- Table structure for course
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course`  (
  `course_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `course_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `credit` decimal(3, 1) NOT NULL COMMENT '学分，如：3.0',
  `classroom` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '教室编号',
  `start_date` date NOT NULL COMMENT '开课时间',
  `end_date` date NOT NULL,
  `teacher_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '主讲教师ID',
  `class_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `college_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '开课学院',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`course_id`) USING BTREE,
  INDEX `idx_course_name`(`course_name` ASC) USING BTREE,
  INDEX `idx_teacher_id`(`teacher_id` ASC) USING BTREE,
  INDEX `idx_college_id`(`college_id` ASC) USING BTREE,
  INDEX `fk_course_class`(`class_id` ASC) USING BTREE,
  CONSTRAINT `course_ibfk_1` FOREIGN KEY (`teacher_id`) REFERENCES `teacher` (`teacher_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `course_ibfk_2` FOREIGN KEY (`college_id`) REFERENCES `college` (`college_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_course_class` FOREIGN KEY (`class_id`) REFERENCES `class` (`class_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '课程信息表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of course
-- ----------------------------
INSERT INTO `course` VALUES ('C1089', '测试分数A', 3.0, 'B234', '2033-06-04', '2025-06-14', 'T7237', '525010110', '52501', '2025-06-02 22:55:03', '2025-06-02 22:55:39');
INSERT INTO `course` VALUES ('C1090', '测试分数B', 3.5, 'B223', '2025-02-05', '2025-05-14', 'T7237', '525010110', '52501', '2025-06-02 23:00:05', '2025-06-02 23:00:05');

-- ----------------------------
-- Table structure for grade
-- ----------------------------
DROP TABLE IF EXISTS `grade`;
CREATE TABLE `grade`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `student_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `course_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `score` decimal(5, 2) NULL DEFAULT NULL COMMENT '成绩分数(0-100)',
  `is_passed` tinyint(1) GENERATED ALWAYS AS ((`score` >= 60)) STORED COMMENT '是否及格' NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_student_id`(`student_id` ASC) USING BTREE,
  INDEX `idx_course_id`(`course_id` ASC) USING BTREE,
  INDEX `idx_score`(`score` ASC) USING BTREE,
  CONSTRAINT `fk_student` FOREIGN KEY (`student_id`) REFERENCES `student` (`student_id`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `grade_ibfk_1` FOREIGN KEY (`student_id`) REFERENCES `student` (`student_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `grade_ibfk_2` FOREIGN KEY (`course_id`) REFERENCES `course` (`course_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 224 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '学生成绩表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of grade
-- ----------------------------
INSERT INTO `grade` VALUES (103, '52501011001', 'C1090', 61.00, DEFAULT, '2025-06-02 23:00:05', '2025-06-12 22:43:04');
INSERT INTO `grade` VALUES (104, '52501011002', 'C1090', NULL, DEFAULT, '2025-06-02 23:00:05', '2025-06-02 23:00:05');
INSERT INTO `grade` VALUES (105, '52501011003', 'C1090', NULL, DEFAULT, '2025-06-02 23:00:05', '2025-06-02 23:00:05');
INSERT INTO `grade` VALUES (106, '52501011004', 'C1090', NULL, DEFAULT, '2025-06-02 23:00:05', '2025-06-02 23:00:05');
INSERT INTO `grade` VALUES (107, '52501011005', 'C1090', NULL, DEFAULT, '2025-06-02 23:00:05', '2025-06-02 23:00:05');
INSERT INTO `grade` VALUES (108, '52501011006', 'C1090', NULL, DEFAULT, '2025-06-02 23:00:05', '2025-06-02 23:00:05');
INSERT INTO `grade` VALUES (109, '52501011007', 'C1090', NULL, DEFAULT, '2025-06-02 23:00:05', '2025-06-02 23:00:05');
INSERT INTO `grade` VALUES (110, '52501011008', 'C1090', NULL, DEFAULT, '2025-06-02 23:00:05', '2025-06-02 23:00:05');
INSERT INTO `grade` VALUES (111, '52501011009', 'C1090', NULL, DEFAULT, '2025-06-02 23:00:05', '2025-06-02 23:00:05');
INSERT INTO `grade` VALUES (112, '52501011010', 'C1090', NULL, DEFAULT, '2025-06-02 23:00:05', '2025-06-02 23:00:05');
INSERT INTO `grade` VALUES (113, '52501011011', 'C1090', NULL, DEFAULT, '2025-06-02 23:00:05', '2025-06-02 23:00:05');
INSERT INTO `grade` VALUES (114, '52501011012', 'C1090', NULL, DEFAULT, '2025-06-02 23:00:05', '2025-06-02 23:00:05');
INSERT INTO `grade` VALUES (115, '52501011013', 'C1090', NULL, DEFAULT, '2025-06-02 23:00:05', '2025-06-02 23:00:05');
INSERT INTO `grade` VALUES (116, '52501011014', 'C1090', NULL, DEFAULT, '2025-06-02 23:00:05', '2025-06-02 23:00:05');
INSERT INTO `grade` VALUES (117, '52501011015', 'C1090', NULL, DEFAULT, '2025-06-02 23:00:05', '2025-06-02 23:00:05');
INSERT INTO `grade` VALUES (118, '52501011016', 'C1090', NULL, DEFAULT, '2025-06-02 23:00:05', '2025-06-02 23:00:05');
INSERT INTO `grade` VALUES (119, '52501011017', 'C1090', NULL, DEFAULT, '2025-06-02 23:00:05', '2025-06-02 23:00:05');
INSERT INTO `grade` VALUES (120, '52501011018', 'C1090', NULL, DEFAULT, '2025-06-02 23:00:05', '2025-06-02 23:00:05');
INSERT INTO `grade` VALUES (121, '52501011019', 'C1090', NULL, DEFAULT, '2025-06-02 23:00:05', '2025-06-02 23:00:05');
INSERT INTO `grade` VALUES (122, '52501011020', 'C1090', NULL, DEFAULT, '2025-06-02 23:00:05', '2025-06-02 23:00:05');
INSERT INTO `grade` VALUES (123, '52501011021', 'C1090', NULL, DEFAULT, '2025-06-02 23:00:05', '2025-06-02 23:00:05');
INSERT INTO `grade` VALUES (124, '52501011022', 'C1090', NULL, DEFAULT, '2025-06-02 23:00:05', '2025-06-02 23:00:05');
INSERT INTO `grade` VALUES (125, '52501011023', 'C1090', NULL, DEFAULT, '2025-06-02 23:00:05', '2025-06-02 23:00:05');
INSERT INTO `grade` VALUES (126, '52501011024', 'C1090', NULL, DEFAULT, '2025-06-02 23:00:05', '2025-06-02 23:00:05');
INSERT INTO `grade` VALUES (127, '52501011025', 'C1090', NULL, DEFAULT, '2025-06-02 23:00:05', '2025-06-02 23:00:05');
INSERT INTO `grade` VALUES (128, '52501011026', 'C1090', NULL, DEFAULT, '2025-06-02 23:00:05', '2025-06-02 23:00:05');
INSERT INTO `grade` VALUES (129, '52501011027', 'C1090', NULL, DEFAULT, '2025-06-02 23:00:05', '2025-06-02 23:00:05');
INSERT INTO `grade` VALUES (130, '52501011028', 'C1090', NULL, DEFAULT, '2025-06-02 23:00:05', '2025-06-02 23:00:05');
INSERT INTO `grade` VALUES (131, '52501011029', 'C1090', NULL, DEFAULT, '2025-06-02 23:00:05', '2025-06-02 23:00:05');
INSERT INTO `grade` VALUES (132, '52501011030', 'C1090', NULL, DEFAULT, '2025-06-02 23:00:05', '2025-06-02 23:00:05');
INSERT INTO `grade` VALUES (133, '52501011031', 'C1090', NULL, DEFAULT, '2025-06-02 23:00:05', '2025-06-02 23:00:05');
INSERT INTO `grade` VALUES (134, '52501011032', 'C1090', NULL, DEFAULT, '2025-06-02 23:00:05', '2025-06-02 23:00:05');
INSERT INTO `grade` VALUES (135, '52501011033', 'C1090', NULL, DEFAULT, '2025-06-02 23:00:05', '2025-06-02 23:00:05');
INSERT INTO `grade` VALUES (136, '52501011034', 'C1090', NULL, DEFAULT, '2025-06-02 23:00:05', '2025-06-02 23:00:05');
INSERT INTO `grade` VALUES (137, '52501011035', 'C1090', NULL, DEFAULT, '2025-06-02 23:00:05', '2025-06-02 23:00:05');
INSERT INTO `grade` VALUES (138, '52501011036', 'C1090', NULL, DEFAULT, '2025-06-02 23:00:05', '2025-06-02 23:00:05');
INSERT INTO `grade` VALUES (139, '52501011037', 'C1090', NULL, DEFAULT, '2025-06-02 23:00:05', '2025-06-02 23:00:05');
INSERT INTO `grade` VALUES (140, '52501011038', 'C1090', NULL, DEFAULT, '2025-06-02 23:00:05', '2025-06-02 23:00:05');
INSERT INTO `grade` VALUES (141, '52501011039', 'C1090', NULL, DEFAULT, '2025-06-02 23:00:05', '2025-06-02 23:00:05');
INSERT INTO `grade` VALUES (142, '52501011040', 'C1090', NULL, DEFAULT, '2025-06-02 23:00:05', '2025-06-02 23:00:05');
INSERT INTO `grade` VALUES (143, '52501011041', 'C1090', NULL, DEFAULT, '2025-06-02 23:00:05', '2025-06-02 23:00:05');
INSERT INTO `grade` VALUES (144, '52501011042', 'C1090', NULL, DEFAULT, '2025-06-02 23:00:05', '2025-06-02 23:00:05');
INSERT INTO `grade` VALUES (145, '52501011043', 'C1090', NULL, DEFAULT, '2025-06-02 23:00:05', '2025-06-02 23:00:05');
INSERT INTO `grade` VALUES (146, '52501011044', 'C1090', NULL, DEFAULT, '2025-06-02 23:00:05', '2025-06-02 23:00:05');
INSERT INTO `grade` VALUES (147, '52501011045', 'C1090', NULL, DEFAULT, '2025-06-02 23:00:05', '2025-06-02 23:00:05');
INSERT INTO `grade` VALUES (148, '52501011046', 'C1090', NULL, DEFAULT, '2025-06-02 23:00:05', '2025-06-02 23:00:05');
INSERT INTO `grade` VALUES (149, '52501011047', 'C1090', NULL, DEFAULT, '2025-06-02 23:00:05', '2025-06-02 23:00:05');
INSERT INTO `grade` VALUES (150, '52501011048', 'C1090', NULL, DEFAULT, '2025-06-02 23:00:05', '2025-06-02 23:00:05');
INSERT INTO `grade` VALUES (151, '52501011049', 'C1090', NULL, DEFAULT, '2025-06-02 23:00:05', '2025-06-02 23:00:05');
INSERT INTO `grade` VALUES (152, '52501011050', 'C1090', NULL, DEFAULT, '2025-06-02 23:00:05', '2025-06-02 23:00:05');
INSERT INTO `grade` VALUES (153, '52501011051', 'C1090', NULL, DEFAULT, '2025-06-02 23:00:05', '2025-06-02 23:00:05');
INSERT INTO `grade` VALUES (154, '52501011052', 'C1090', NULL, DEFAULT, '2025-06-02 23:00:05', '2025-06-02 23:00:05');
INSERT INTO `grade` VALUES (156, '52501011054', 'C1090', NULL, DEFAULT, '2025-06-02 23:00:05', '2025-06-02 23:00:05');
INSERT INTO `grade` VALUES (157, '52501011055', 'C1090', NULL, DEFAULT, '2025-06-02 23:00:05', '2025-06-02 23:00:05');
INSERT INTO `grade` VALUES (158, '52501011056', 'C1090', NULL, DEFAULT, '2025-06-02 23:00:05', '2025-06-02 23:00:05');

-- ----------------------------
-- Table structure for leave_application
-- ----------------------------
DROP TABLE IF EXISTS `leave_application`;
CREATE TABLE `leave_application`  (
  `leave_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT 'replace(uuid(),_utf8mb3\\\'-\\\',_utf8mb4\\\'\\\')',
  `student_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `teacher_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `leave_type` enum('P0','P1','P2','P3') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `reason` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `is_approved` tinyint(1) NULL DEFAULT 0,
  PRIMARY KEY (`leave_id`) USING BTREE,
  INDEX `teacher_id`(`teacher_id` ASC) USING BTREE,
  INDEX `fk_students`(`student_id` ASC) USING BTREE,
  CONSTRAINT `fk_students` FOREIGN KEY (`student_id`) REFERENCES `student` (`student_id`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `leave_application_ibfk_1` FOREIGN KEY (`student_id`) REFERENCES `student` (`student_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `leave_application_ibfk_2` FOREIGN KEY (`teacher_id`) REFERENCES `teacher` (`teacher_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of leave_application
-- ----------------------------
INSERT INTO `leave_application` VALUES ('3a02df37454311f0b951b025aa59d07c', '52501011001', 'T7235', 'P3', '今天太舒服了', -1);
INSERT INTO `leave_application` VALUES ('3a0309cb454311f0b951b025aa59d07c', '52501011038', 'T7235', 'P3', '今天太舒服了', -1);
INSERT INTO `leave_application` VALUES ('3a030c2f454311f0b951b025aa59d07c', '52501011045', 'T7235', 'P2', 'QQ异地登陆，需要调查', 1);
INSERT INTO `leave_application` VALUES ('3a030d30454311f0b951b025aa59d07c', '52501011026', 'T7235', 'P3', '实在听不懂', -1);
INSERT INTO `leave_application` VALUES ('3a030e32454311f0b951b025aa59d07c', '52501011039', 'T7235', 'P0', 'QQ异地登陆，需要调查', 1);
INSERT INTO `leave_application` VALUES ('3a031043454311f0b951b025aa59d07c', '52501011031', 'T7235', 'P0', '对，不起了', -1);
INSERT INTO `leave_application` VALUES ('3a031125454311f0b951b025aa59d07c', '52501011020', 'T7235', 'P0', '实在听不懂', 1);
INSERT INTO `leave_application` VALUES ('3a031207454311f0b951b025aa59d07c', '52501011044', 'T7235', 'P2', '对，不起了', -1);
INSERT INTO `leave_application` VALUES ('3a0312db454311f0b951b025aa59d07c', '52501011024', 'T7235', 'P2', '对，不起了', 1);
INSERT INTO `leave_application` VALUES ('3a0313ab454311f0b951b025aa59d07c', '52501011004', 'T7235', 'P2', '对，不起了', 1);
INSERT INTO `leave_application` VALUES ('3a031515454311f0b951b025aa59d07c', '52501011030', 'T7235', 'P1', '今天太舒服了', 1);
INSERT INTO `leave_application` VALUES ('3a0315f4454311f0b951b025aa59d07c', '52501011017', 'T7235', 'P3', '今天太舒服了', 1);
INSERT INTO `leave_application` VALUES ('bea29bd4480311f086ecb025aa59d07c', '52501011001', 'T7235', 'P0', '测试请假', 1);

-- ----------------------------
-- Table structure for major
-- ----------------------------
DROP TABLE IF EXISTS `major`;
CREATE TABLE `major`  (
  `major_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `major_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `college_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`major_id`) USING BTREE,
  INDEX `idx_major_name`(`major_name` ASC) USING BTREE,
  INDEX `idx_college_id`(`college_id` ASC) USING BTREE,
  CONSTRAINT `major_ibfk_1` FOREIGN KEY (`college_id`) REFERENCES `college` (`college_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '专业信息表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of major
-- ----------------------------
INSERT INTO `major` VALUES ('5250101', '测试人数专业', '52501', '2025-06-02 22:34:39', '2025-06-02 22:34:39');

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student`  (
  `student_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `gender` enum('男','女','其他') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `birth_date` date NULL DEFAULT NULL,
  `phone` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `email` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `enrollment_date` date NOT NULL,
  `graduation_status` tinyint NOT NULL DEFAULT 0 COMMENT '0-在读, 1-毕业, 2-肆业',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`student_id`) USING BTREE,
  INDEX `idx_name`(`name` ASC) USING BTREE,
  INDEX `idx_graduation_status`(`graduation_status` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '学生基本信息表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES ('52501011001', '蔡云熙', '女', '2000-02-07', '20-5011-6565', 'yunca@hotmail.com', '2020-01-11', 2, '2013-04-20 13:21:19', '2020-03-21 05:15:14');
INSERT INTO `student` VALUES ('52501011002', '邱璐', '女', '2003-06-13', '760-9277-7967', 'lqiu@outlook.com', '2021-01-05', 1, '2021-07-02 16:50:18', '2018-01-15 02:23:54');
INSERT INTO `student` VALUES ('52501011003', '罗璐', '男', '2000-10-05', '10-1656-5928', 'luolu1205@outlook.com', '2019-04-15', 0, '2008-09-24 08:46:47', '2012-10-24 20:31:09');
INSERT INTO `student` VALUES ('52501011004', '贾震南', '男', '2002-11-14', '755-9033-4651', 'jia10@gmail.com', '2020-05-17', 2, '2014-10-03 13:46:14', '2002-01-06 09:19:29');
INSERT INTO `student` VALUES ('52501011005', '朱致远', '男', '2003-04-25', '755-427-3456', 'zhiyuanz@gmail.com', '2020-11-13', 1, '2022-11-27 03:32:37', '2009-04-24 14:54:12');
INSERT INTO `student` VALUES ('52501011006', '廖璐', '男', '2001-07-30', '152-8447-1157', 'lulia@icloud.com', '2020-01-18', 1, '2012-05-12 22:58:32', '2018-07-04 19:37:36');
INSERT INTO `student` VALUES ('52501011007', '张安琪', '男', '2000-04-18', '21-485-7903', 'zhang05@icloud.com', '2020-12-08', 2, '2015-04-22 09:32:47', '2012-01-07 10:07:18');
INSERT INTO `student` VALUES ('52501011008', '袁云熙', '男', '2003-04-14', '157-8916-3777', 'yunxiyuan4@gmail.com', '2020-07-24', 0, '2006-07-23 18:42:32', '2003-05-25 15:25:45');
INSERT INTO `student` VALUES ('52501011009', '郑嘉伦', '女', '2003-06-27', '20-8888-0531', 'jialunzheng@gmail.com', '2021-02-20', 0, '2003-10-02 01:26:26', '2012-03-11 12:02:32');
INSERT INTO `student` VALUES ('52501011010', '何震南', '女', '2001-10-07', '21-723-7315', 'hz522@gmail.com', '2020-12-07', 2, '2006-03-17 12:43:45', '2015-08-11 06:12:34');
INSERT INTO `student` VALUES ('52501011011', '郑嘉伦', '男', '2001-10-12', '185-8029-8744', 'zhej@gmail.com', '2020-12-10', 1, '2001-02-18 07:48:08', '2008-09-05 03:59:05');
INSERT INTO `student` VALUES ('52501011012', '程云熙', '男', '2000-08-21', '760-501-7399', 'chengyu85@yahoo.com', '2020-06-17', 0, '2002-05-08 06:22:09', '2002-08-05 16:00:56');
INSERT INTO `student` VALUES ('52501011013', '梁璐', '女', '2001-05-02', '149-0336-2418', 'luli@outlook.com', '2019-11-15', 1, '2023-04-01 08:35:59', '2024-02-29 02:54:28');
INSERT INTO `student` VALUES ('52501011014', '韦云熙', '男', '2002-08-09', '769-848-2359', 'ywei@outlook.com', '2019-12-24', 1, '2004-06-27 21:56:00', '2004-02-09 03:28:40');
INSERT INTO `student` VALUES ('52501011015', '阎子韬', '男', '2003-12-02', '173-9640-3941', 'zitao1126@outlook.com', '2021-01-28', 1, '2018-03-30 05:35:47', '2020-05-01 22:39:22');
INSERT INTO `student` VALUES ('52501011016', '丁睿', '女', '2001-10-04', '143-0189-8225', 'rding@icloud.com', '2020-06-04', 2, '2002-03-26 00:21:00', '2023-03-02 05:53:26');
INSERT INTO `student` VALUES ('52501011017', '唐子异', '男', '2004-01-13', '172-6930-7343', 'ziyit319@gmail.com', '2019-10-02', 1, '2020-06-15 13:08:28', '2016-06-25 08:39:18');
INSERT INTO `student` VALUES ('52501011018', '孙宇宁', '女', '2001-08-17', '755-046-2984', 'yuningsun@mail.com', '2019-04-17', 1, '2025-05-23 09:20:28', '2009-11-23 11:01:47');
INSERT INTO `student` VALUES ('52501011019', '崔杰宏', '女', '2000-04-12', '164-3330-0463', 'cj8@gmail.com', '2019-05-16', 1, '2021-12-03 22:36:32', '2005-03-19 23:49:36');
INSERT INTO `student` VALUES ('52501011020', '卢杰宏', '男', '2000-09-30', '10-499-2611', 'jiehol90@outlook.com', '2019-10-08', 2, '2010-03-01 05:40:51', '2006-04-24 15:06:46');
INSERT INTO `student` VALUES ('52501011021', '姜杰宏', '女', '2000-11-22', '170-3935-2144', 'jiehong424@outlook.com', '2021-02-25', 1, '2007-05-11 21:17:10', '2006-11-05 20:48:21');
INSERT INTO `student` VALUES ('52501011022', '戴杰宏', '男', '2002-01-12', '769-843-7773', 'jda59@mail.com', '2019-05-03', 2, '2003-12-12 21:04:50', '2014-12-07 06:02:03');
INSERT INTO `student` VALUES ('52501011023', '龚睿', '女', '2002-08-27', '28-087-8391', 'gong63@gmail.com', '2019-11-30', 1, '2008-03-19 08:39:44', '2019-04-30 03:35:25');
INSERT INTO `student` VALUES ('52501011024', '廖睿', '女', '2002-08-01', '769-762-4071', 'liao46@gmail.com', '2019-03-19', 1, '2004-05-03 12:48:51', '2017-08-06 02:01:29');
INSERT INTO `student` VALUES ('52501011025', '汪致远', '男', '2002-07-17', '21-7665-3958', 'wangzhiyuan1002@gmail.com', '2019-12-31', 2, '2010-09-13 13:04:09', '2001-01-05 02:26:29');
INSERT INTO `student` VALUES ('52501011026', '贺秀英', '女', '2003-04-21', '179-5193-6121', 'xiuying08@gmail.com', '2020-08-16', 0, '2004-01-15 18:58:49', '2003-08-25 04:40:31');
INSERT INTO `student` VALUES ('52501011027', '尹震南', '女', '2000-10-04', '151-5204-4165', 'yizhennan3@gmail.com', '2020-09-02', 1, '2022-02-21 20:09:38', '2000-02-16 13:32:57');
INSERT INTO `student` VALUES ('52501011028', '邱震南', '男', '2000-06-01', '167-3362-9686', 'zhennqi@hotmail.com', '2019-08-06', 2, '2015-10-14 03:56:59', '2007-05-12 11:50:36');
INSERT INTO `student` VALUES ('52501011029', '田詩涵', '女', '2000-06-23', '21-4314-7680', 'shitian@icloud.com', '2021-01-19', 1, '2006-08-02 03:06:39', '2019-03-01 16:49:56');
INSERT INTO `student` VALUES ('52501011030', '冯詩涵', '女', '2002-01-03', '166-1020-1166', 'shihanfeng@outlook.com', '2019-12-21', 0, '2004-06-11 02:05:04', '2020-07-28 11:06:00');
INSERT INTO `student` VALUES ('52501011031', '戴秀英', '男', '2001-06-02', '760-8060-3640', 'daxiu@icloud.com', '2020-04-06', 2, '2009-09-26 11:48:17', '2007-10-04 16:35:26');
INSERT INTO `student` VALUES ('52501011032', '方云熙', '女', '2002-06-29', '196-8132-4202', 'yfang@outlook.com', '2020-05-13', 1, '2018-10-24 09:23:00', '2018-07-21 05:38:32');
INSERT INTO `student` VALUES ('52501011033', '尹致远', '女', '2002-03-10', '10-6165-0845', 'zhiyuany71@mail.com', '2019-06-11', 2, '2011-11-25 15:22:03', '2004-04-27 02:56:16');
INSERT INTO `student` VALUES ('52501011034', '梁云熙', '男', '2003-03-26', '182-7621-0786', 'yunxliang@outlook.com', '2020-02-18', 0, '2015-09-27 11:08:24', '2009-05-14 12:15:26');
INSERT INTO `student` VALUES ('52501011035', '傅晓明', '男', '2000-01-09', '151-9215-8911', 'fu828@yahoo.com', '2020-09-12', 1, '2019-04-06 12:11:56', '2015-12-05 20:08:47');
INSERT INTO `student` VALUES ('52501011036', '曾秀英', '男', '2004-01-03', '174-0198-1022', 'zengxiu@outlook.com', '2019-08-16', 1, '2019-10-15 15:55:16', '2014-05-18 00:52:07');
INSERT INTO `student` VALUES ('52501011037', '许睿', '男', '2003-05-19', '21-4527-2309', 'ruixu@yahoo.com', '2020-12-28', 2, '2001-11-13 21:28:00', '2010-08-08 15:55:05');
INSERT INTO `student` VALUES ('52501011038', '姜岚', '男', '2001-07-10', '10-312-7992', 'jiang1002@yahoo.com', '2021-02-27', 1, '2005-01-15 05:05:15', '2008-02-13 10:47:21');
INSERT INTO `student` VALUES ('52501011039', '杨子韬', '女', '2002-10-30', '175-7176-1595', 'ziyang@outlook.com', '2019-11-28', 0, '2006-07-05 05:18:55', '2003-08-01 02:52:25');
INSERT INTO `student` VALUES ('52501011040', '丁子韬', '男', '2001-06-27', '760-705-9270', 'dingzitao@icloud.com', '2019-07-09', 1, '2012-02-26 10:58:04', '2011-08-29 20:00:30');
INSERT INTO `student` VALUES ('52501011041', '江岚', '女', '2003-02-24', '161-0806-1291', 'lajiang@mail.com', '2020-06-14', 2, '2003-02-26 15:37:23', '2017-02-20 14:52:24');
INSERT INTO `student` VALUES ('52501011042', '邱子韬', '男', '2003-09-19', '760-5737-2568', 'qiuzitao@mail.com', '2020-03-25', 1, '2019-08-20 10:32:36', '2021-08-08 09:39:41');
INSERT INTO `student` VALUES ('52501011043', '段宇宁', '男', '2001-05-09', '160-9016-4930', 'duan1107@icloud.com', '2019-10-12', 2, '2025-04-08 13:33:25', '2019-01-22 03:14:06');
INSERT INTO `student` VALUES ('52501011044', '陆震南', '男', '2000-01-29', '145-4799-5023', 'luzhennan@hotmail.com', '2019-07-12', 1, '2014-02-18 22:34:41', '2012-04-14 00:04:51');
INSERT INTO `student` VALUES ('52501011045', '蒋秀英', '男', '2003-02-10', '760-0936-0398', 'xiuyingjiang115@mail.com', '2019-05-17', 2, '2024-04-21 13:12:30', '2023-06-23 09:51:12');
INSERT INTO `student` VALUES ('52501011046', '袁岚', '男', '2000-09-27', '156-3956-6780', 'ylan625@outlook.com', '2021-01-13', 1, '2002-03-29 02:47:54', '2009-12-19 06:57:49');
INSERT INTO `student` VALUES ('52501011047', '史震南', '男', '2002-10-28', '755-8292-3047', 'zhennansh@gmail.com', '2019-03-30', 1, '2024-07-12 10:06:56', '2017-07-19 00:18:58');
INSERT INTO `student` VALUES ('52501011048', '孙杰宏', '男', '2003-05-03', '168-2316-7220', 'jiehong3@outlook.com', '2019-12-08', 1, '2009-03-04 04:14:58', '2015-06-16 20:43:27');
INSERT INTO `student` VALUES ('52501011049', '邹詩涵', '女', '2001-04-21', '198-8528-3916', 'shihazo@hotmail.com', '2019-10-10', 1, '2018-04-21 05:22:43', '2006-02-04 15:26:20');
INSERT INTO `student` VALUES ('52501011050', '沈晓明', '女', '2002-10-14', '21-4323-1813', 'xiaomingshen@icloud.com', '2020-05-22', 0, '2013-04-01 16:14:20', '2016-08-07 02:19:50');
INSERT INTO `student` VALUES ('52501011051', '于璐', '男', '2001-12-07', '163-3589-6210', 'luyu1971@outlook.com', '2020-04-02', 1, '2008-12-16 04:39:55', '2022-12-01 18:41:03');
INSERT INTO `student` VALUES ('52501011052', '方安琪', '女', '2001-10-08', '171-7206-9636', 'anqfa@yahoo.com', '2020-03-17', 1, '2005-09-20 14:55:02', '2001-08-27 09:30:59');
INSERT INTO `student` VALUES ('52501011054', '杨子韬', '女', '2000-08-05', '172-5581-2977', 'zyang@outlook.com', '2019-09-01', 0, '2001-04-10 22:36:37', '2021-05-12 16:54:49');
INSERT INTO `student` VALUES ('52501011055', '邓安琪', '男', '2002-04-03', '21-1609-2820', 'anqide@mail.com', '2020-11-19', 1, '2024-04-11 11:50:03', '2011-05-10 12:59:09');
INSERT INTO `student` VALUES ('52501011056', '王子异', '男', '2001-04-12', '159-7101-9376', 'wanz@outlook.com', '2020-10-21', 1, '2011-05-26 17:09:53', '2013-03-13 20:25:18');
INSERT INTO `student` VALUES ('52501012001', '苏嘉伦', '女', '2004-10-23', '755-8556-0743', 'su10@gmail.com', '2017-04-25', 2, '2004-09-29 07:32:32', '2007-10-02 13:28:06');
INSERT INTO `student` VALUES ('52501012002', '夏岚', '男', '2011-07-22', '10-1350-0939', 'lanxi@hotmail.com', '2009-05-22', 1, '2005-02-06 23:51:53', '2018-04-15 05:24:08');
INSERT INTO `student` VALUES ('52501012003', '孙云熙', '女', '2001-08-30', '183-5961-5585', 'yunxis4@hotmail.com', '2022-06-07', 0, '2016-11-16 17:34:57', '2012-06-05 14:35:05');
INSERT INTO `student` VALUES ('52501012004', '蔡嘉伦', '女', '2011-09-08', '760-533-5827', 'cajialun818@outlook.com', '2001-09-26', 2, '2024-10-10 18:15:33', '2012-02-26 12:02:27');
INSERT INTO `student` VALUES ('52501012005', '龙璐', '女', '2017-11-28', '21-3754-9920', 'lonlu@outlook.com', '2016-11-05', 2, '2007-02-27 10:37:19', '2017-08-21 22:20:40');
INSERT INTO `student` VALUES ('52501012006', '高嘉伦', '女', '2018-05-11', '760-733-2362', 'gjialun7@gmail.com', '2007-07-27', 2, '2016-03-20 20:18:21', '2009-09-11 03:05:08');
INSERT INTO `student` VALUES ('52501012007', '石嘉伦', '其他', '2024-04-06', '21-996-1598', 'jialun1959@mail.com', '2000-10-28', 0, '2000-07-27 13:10:47', '2025-04-05 12:00:29');
INSERT INTO `student` VALUES ('52501012008', '向晓明', '女', '2014-08-07', '769-807-3323', 'xiaxia@hotmail.com', '2020-07-06', 0, '2024-08-12 03:19:55', '2018-03-10 09:22:06');
INSERT INTO `student` VALUES ('52501012009', '刘杰宏', '女', '2011-08-19', '161-2026-9099', 'liu1104@icloud.com', '2014-03-30', 2, '2014-11-12 08:02:35', '2022-11-30 13:08:59');
INSERT INTO `student` VALUES ('52501012010', '杨安琪', '其他', '2010-06-04', '152-2004-1780', 'yanqi@mail.com', '2005-03-27', 1, '2003-01-01 05:23:22', '2020-10-17 06:45:32');
INSERT INTO `student` VALUES ('52501012011', '石晓明', '女', '2015-06-15', '184-8562-5266', 'shix@gmail.com', '2012-07-23', 0, '2013-08-26 02:22:06', '2001-08-10 01:51:24');
INSERT INTO `student` VALUES ('52501012012', '宋宇宁', '女', '2003-09-30', '151-1014-5869', 'syuning@gmail.com', '2004-11-22', 1, '2009-08-16 21:06:12', '2007-08-24 09:31:27');
INSERT INTO `student` VALUES ('52501012013', '陶晓明', '男', '2009-04-20', '197-6048-1596', 'taoxiao421@icloud.com', '2006-03-21', 1, '2003-04-15 01:12:26', '2012-12-14 20:18:27');
INSERT INTO `student` VALUES ('52501012014', '曾子韬', '其他', '2005-11-16', '189-8085-8564', 'zenzitao10@outlook.com', '2002-02-17', 1, '2024-07-09 22:07:42', '2000-10-29 14:05:52');
INSERT INTO `student` VALUES ('52501012015', '朱杰宏', '女', '2024-10-26', '183-1806-3622', 'zhu106@yahoo.com', '2018-07-03', 1, '2011-07-16 10:25:48', '2022-03-10 15:19:04');
INSERT INTO `student` VALUES ('52501012016', '顾杰宏', '其他', '2005-03-27', '164-8704-7242', 'jiehonggu@yahoo.com', '2009-11-09', 1, '2020-03-19 12:52:10', '2023-10-13 10:50:02');
INSERT INTO `student` VALUES ('52501012017', '何秀英', '男', '2024-06-12', '135-4176-6164', 'hxiuyi96@outlook.com', '2006-01-07', 0, '2007-05-26 20:54:42', '2014-09-01 01:49:49');
INSERT INTO `student` VALUES ('52501012018', '沈睿', '男', '2002-01-01', '769-8476-9887', 'shenrui@outlook.com', '2022-04-16', 0, '2008-04-12 04:38:01', '2013-12-28 05:09:47');
INSERT INTO `student` VALUES ('52501012019', '崔致远', '其他', '2024-01-07', '135-9688-8807', 'cuizhiy@yahoo.com', '2006-04-14', 0, '2012-12-01 21:29:46', '2004-09-28 08:27:37');
INSERT INTO `student` VALUES ('52501012020', '汤子异', '其他', '2010-11-13', '166-8930-4037', 'tangz@gmail.com', '2007-02-01', 2, '2012-06-18 02:33:58', '2020-09-08 13:43:24');
INSERT INTO `student` VALUES ('52501012021', '唐杰宏', '女', '2020-10-08', '170-6567-4281', 'tjiehong@outlook.com', '2002-09-07', 1, '2024-01-12 16:57:27', '2021-01-10 09:05:34');
INSERT INTO `student` VALUES ('52501012022', '朱岚', '女', '2016-09-09', '158-6465-5401', 'lanzhu@icloud.com', '2001-09-11', 1, '2018-09-17 07:16:33', '2006-12-01 15:17:34');
INSERT INTO `student` VALUES ('52501012023', '谢子异', '女', '2009-12-19', '154-5458-1090', 'ziyi421@gmail.com', '2015-02-24', 0, '2016-03-16 17:58:05', '2011-02-04 06:36:31');
INSERT INTO `student` VALUES ('52501012024', '郑安琪', '女', '2000-08-27', '21-2131-5027', 'zhea@hotmail.com', '2014-08-18', 0, '2006-10-15 09:33:18', '2021-08-07 01:07:50');
INSERT INTO `student` VALUES ('52501012025', '蔡子异', '女', '2013-11-21', '171-0153-3335', 'zicai@hotmail.com', '2004-02-29', 1, '2025-06-09 09:44:29', '2023-09-22 02:15:59');
INSERT INTO `student` VALUES ('52501012026', '任子异', '男', '2021-07-16', '194-3738-7023', 'ziyiren@gmail.com', '2003-04-21', 0, '2015-09-04 01:35:06', '2025-02-15 03:53:54');
INSERT INTO `student` VALUES ('52501012027', '方震南', '女', '2009-10-22', '755-895-6501', 'zhennan10@outlook.com', '2014-04-08', 1, '2022-11-01 20:20:46', '2002-03-16 19:36:53');
INSERT INTO `student` VALUES ('52501012028', '龚秀英', '女', '2016-06-09', '21-017-5484', 'gxiuyi6@mail.com', '2013-06-28', 2, '2000-10-03 22:28:52', '2005-08-25 22:49:14');
INSERT INTO `student` VALUES ('52501012029', '向詩涵', '男', '2018-08-30', '28-861-5685', 'shihanxiang@gmail.com', '2004-05-26', 1, '2001-07-27 17:58:24', '2003-05-24 05:14:27');
INSERT INTO `student` VALUES ('52501012030', '龙晓明', '其他', '2020-08-04', '769-1833-0781', 'longxiaom@gmail.com', '2015-08-27', 0, '2008-05-28 11:25:24', '2005-11-18 07:48:54');
INSERT INTO `student` VALUES ('52501012031', '方子韬', '其他', '2013-05-14', '145-8629-0493', 'fangzitao@gmail.com', '2014-06-15', 1, '2002-07-24 17:11:33', '2021-09-15 05:27:59');
INSERT INTO `student` VALUES ('52501012032', '王宇宁', '男', '2019-01-07', '28-267-4244', 'wangyuni@icloud.com', '2000-03-23', 1, '2016-06-20 14:45:36', '2023-09-27 01:20:15');
INSERT INTO `student` VALUES ('52501012033', '苏詩涵', '其他', '2003-06-21', '134-3177-9431', 'shihan14@icloud.com', '2022-11-04', 1, '2000-07-20 05:22:06', '2011-04-22 02:39:21');
INSERT INTO `student` VALUES ('52501012034', '孔睿', '男', '2014-12-08', '769-004-0299', 'ruikong1222@icloud.com', '2022-04-13', 1, '2007-02-09 04:03:07', '2017-04-03 05:21:12');
INSERT INTO `student` VALUES ('52501012035', '孟震南', '男', '2012-07-14', '197-2966-4768', 'mengzhen@outlook.com', '2021-03-30', 0, '2008-07-15 12:59:27', '2014-07-29 05:58:59');
INSERT INTO `student` VALUES ('52501012036', '方子韬', '男', '2022-05-12', '166-2078-9034', 'fanzitao@mail.com', '2006-11-27', 1, '2007-08-14 23:31:38', '2023-08-14 23:12:52');
INSERT INTO `student` VALUES ('52501012037', '田子异', '其他', '2015-05-03', '20-100-5822', 'tian97@yahoo.com', '2018-04-04', 2, '2004-07-06 15:05:36', '2011-01-19 06:27:55');
INSERT INTO `student` VALUES ('52501012038', '刘安琪', '男', '2021-10-23', '188-1380-6286', 'liuanqi717@icloud.com', '2021-04-05', 0, '2008-01-12 12:34:05', '2016-01-09 09:26:59');
INSERT INTO `student` VALUES ('52501012039', '徐震南', '男', '2020-10-07', '176-4312-5847', 'xuzh@outlook.com', '2025-03-29', 0, '2014-03-04 21:31:51', '2013-02-06 20:20:19');
INSERT INTO `student` VALUES ('52501012040', '萧睿', '女', '2022-07-21', '181-5307-2511', 'xiaoru@gmail.com', '2003-05-25', 1, '2006-05-20 13:38:00', '2019-07-20 09:14:12');
INSERT INTO `student` VALUES ('52501012041', '吕安琪', '其他', '2016-05-23', '21-854-2071', 'alu@gmail.com', '2007-06-19', 2, '2009-04-27 17:05:03', '2007-10-12 10:38:37');
INSERT INTO `student` VALUES ('52501012042', '武嘉伦', '其他', '2007-12-01', '755-9463-0351', 'jialunwu@gmail.com', '2001-07-19', 2, '2004-11-02 06:04:40', '2016-02-16 06:45:34');
INSERT INTO `student` VALUES ('52501012043', '冯致远', '其他', '2018-08-17', '139-6114-2802', 'zhiyuanfe@gmail.com', '2018-01-05', 1, '2010-08-20 15:45:27', '2015-09-21 10:08:59');
INSERT INTO `student` VALUES ('52501012044', '杨云熙', '男', '2022-03-28', '10-6597-4783', 'yangyun@icloud.com', '2005-12-03', 0, '2008-06-01 15:05:43', '2021-11-21 20:27:37');
INSERT INTO `student` VALUES ('52501012045', '叶宇宁', '男', '2023-09-14', '162-0276-0921', 'ye1006@mail.com', '2022-05-17', 0, '2002-07-01 07:45:59', '2009-01-08 11:48:08');
INSERT INTO `student` VALUES ('52501012046', '林安琪', '男', '2010-02-27', '168-4093-6858', 'anlin2013@yahoo.com', '2024-10-24', 0, '2014-03-12 09:31:17', '2001-07-01 05:38:53');
INSERT INTO `student` VALUES ('52501012047', '汤晓明', '女', '2010-01-18', '170-6554-8109', 'xiaomingt@mail.com', '2020-10-04', 1, '2002-04-09 15:46:44', '2015-11-10 00:30:17');
INSERT INTO `student` VALUES ('52501012048', '曹杰宏', '其他', '2025-05-26', '20-7781-6504', 'jcao@outlook.com', '2020-06-28', 1, '2001-11-10 11:07:38', '2008-09-25 14:02:58');
INSERT INTO `student` VALUES ('52501012049', '姜宇宁', '男', '2011-05-09', '10-110-3772', 'yj4@yahoo.com', '2012-06-10', 2, '2001-08-20 10:55:04', '2008-03-13 14:46:59');
INSERT INTO `student` VALUES ('52501012050', '向秀英', '其他', '2017-04-25', '20-0874-3824', 'xianx@hotmail.com', '2003-10-17', 1, '2007-04-16 03:07:49', '2003-05-17 02:01:40');
INSERT INTO `student` VALUES ('52501012051', '丁杰宏', '其他', '2018-07-02', '10-5413-4119', 'ding526@hotmail.com', '2013-05-19', 1, '2016-04-20 21:26:17', '2012-08-27 08:02:33');
INSERT INTO `student` VALUES ('52501012052', '赵子韬', '女', '2011-12-24', '755-848-5473', 'zhaoz@outlook.com', '2009-01-16', 0, '2015-12-21 22:58:30', '2014-03-27 03:07:50');
INSERT INTO `student` VALUES ('52501012053', '叶安琪', '男', '2021-09-30', '755-887-6274', 'anqiye@outlook.com', '2010-09-18', 1, '2007-04-23 19:58:58', '2008-05-19 00:52:44');
INSERT INTO `student` VALUES ('52501012054', '段岚', '男', '2022-11-18', '159-5793-8817', 'dl43@gmail.com', '2007-08-21', 2, '2005-10-02 18:32:52', '2014-07-23 12:45:32');

-- ----------------------------
-- Table structure for teacher
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher`  (
  `teacher_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `gender` enum('男','女','其他') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `college_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '所属学院',
  `hire_date` date NOT NULL COMMENT '入职时间',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`teacher_id`) USING BTREE,
  INDEX `idx_name`(`name` ASC) USING BTREE,
  INDEX `idx_college_id`(`college_id` ASC) USING BTREE,
  CONSTRAINT `teacher_ibfk_1` FOREIGN KEY (`college_id`) REFERENCES `college` (`college_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '教师信息表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of teacher
-- ----------------------------
INSERT INTO `teacher` VALUES ('T7235', '测试请假', '男', '52501', '2014-02-20', '2014-12-18 22:47:02', '2011-06-01 14:47:28');
INSERT INTO `teacher` VALUES ('T7236', '测试没有请假', '女', '52501', '2016-07-08', '2020-07-17 01:44:36', '2024-02-09 03:08:27');
INSERT INTO `teacher` VALUES ('T7237', '课程及分数', '男', '52501', '2020-02-08', '2022-06-02 15:37:21', '2023-08-02 14:10:28');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `user_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户ID(学号/教师ID)',
  `password` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '密码',
  `role` tinyint NOT NULL COMMENT '0-学生, 1-老师/管理员, 2-超级管理员',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`user_id`) USING BTREE,
  INDEX `idx_role`(`role` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '系统用户表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('52501011001', 'bbc52501011001', 0, '2025-06-02 22:40:38', '2025-06-13 09:08:45');
INSERT INTO `user` VALUES ('52501011002', 'bbc52501011002', 0, '2025-06-02 22:40:38', '2025-06-02 22:40:38');
INSERT INTO `user` VALUES ('52501011003', 'bbc52501011003', 0, '2025-06-02 22:40:38', '2025-06-02 22:40:38');
INSERT INTO `user` VALUES ('52501011004', 'bbc52501011004', 0, '2025-06-02 22:40:38', '2025-06-02 22:40:38');
INSERT INTO `user` VALUES ('52501011005', 'bbc52501011005', 0, '2025-06-02 22:40:38', '2025-06-02 22:40:38');
INSERT INTO `user` VALUES ('52501011006', 'bbc52501011006', 0, '2025-06-02 22:40:38', '2025-06-02 22:40:38');
INSERT INTO `user` VALUES ('52501011007', 'bbc52501011007', 0, '2025-06-02 22:40:38', '2025-06-02 22:40:38');
INSERT INTO `user` VALUES ('52501011008', 'bbc52501011008', 0, '2025-06-02 22:40:38', '2025-06-02 22:40:38');
INSERT INTO `user` VALUES ('52501011009', 'bbc52501011009', 0, '2025-06-02 22:40:38', '2025-06-02 22:40:38');
INSERT INTO `user` VALUES ('52501011010', 'bbc52501011010', 0, '2025-06-02 22:40:38', '2025-06-02 22:40:38');
INSERT INTO `user` VALUES ('52501011011', 'bbc52501011011', 0, '2025-06-02 22:40:38', '2025-06-02 22:40:38');
INSERT INTO `user` VALUES ('52501011012', 'bbc52501011012', 0, '2025-06-02 22:40:38', '2025-06-02 22:40:38');
INSERT INTO `user` VALUES ('52501011013', 'bbc52501011013', 0, '2025-06-02 22:40:38', '2025-06-02 22:40:38');
INSERT INTO `user` VALUES ('52501011014', 'bbc52501011014', 0, '2025-06-02 22:40:38', '2025-06-02 22:40:38');
INSERT INTO `user` VALUES ('52501011015', 'bbc52501011015', 0, '2025-06-02 22:40:38', '2025-06-02 22:40:38');
INSERT INTO `user` VALUES ('52501011016', 'bbc52501011016', 0, '2025-06-02 22:40:38', '2025-06-02 22:40:38');
INSERT INTO `user` VALUES ('52501011017', 'bbc52501011017', 0, '2025-06-02 22:40:38', '2025-06-02 22:40:38');
INSERT INTO `user` VALUES ('52501011018', 'bbc52501011018', 0, '2025-06-02 22:40:38', '2025-06-02 22:40:38');
INSERT INTO `user` VALUES ('52501011019', 'bbc52501011019', 0, '2025-06-02 22:40:38', '2025-06-02 22:40:38');
INSERT INTO `user` VALUES ('52501011020', 'bbc52501011020', 0, '2025-06-02 22:40:38', '2025-06-02 22:40:38');
INSERT INTO `user` VALUES ('52501011021', 'bbc52501011021', 0, '2025-06-02 22:40:38', '2025-06-02 22:40:38');
INSERT INTO `user` VALUES ('52501011022', 'bbc52501011022', 0, '2025-06-02 22:40:38', '2025-06-02 22:40:38');
INSERT INTO `user` VALUES ('52501011023', 'bbc52501011023', 0, '2025-06-02 22:40:38', '2025-06-02 22:40:38');
INSERT INTO `user` VALUES ('52501011024', 'bbc52501011024', 0, '2025-06-02 22:40:38', '2025-06-02 22:40:38');
INSERT INTO `user` VALUES ('52501011025', 'bbc52501011025', 0, '2025-06-02 22:40:38', '2025-06-02 22:40:38');
INSERT INTO `user` VALUES ('52501011026', 'bbc52501011026', 0, '2025-06-02 22:40:38', '2025-06-02 22:40:38');
INSERT INTO `user` VALUES ('52501011027', 'bbc52501011027', 0, '2025-06-02 22:40:38', '2025-06-02 22:40:38');
INSERT INTO `user` VALUES ('52501011028', 'bbc52501011028', 0, '2025-06-02 22:40:38', '2025-06-02 22:40:38');
INSERT INTO `user` VALUES ('52501011029', 'bbc52501011029', 0, '2025-06-02 22:40:38', '2025-06-02 22:40:38');
INSERT INTO `user` VALUES ('52501011030', 'bbc52501011030', 0, '2025-06-02 22:40:38', '2025-06-02 22:40:38');
INSERT INTO `user` VALUES ('52501011031', 'bbc52501011031', 0, '2025-06-02 22:40:38', '2025-06-02 22:40:38');
INSERT INTO `user` VALUES ('52501011032', 'bbc52501011032', 0, '2025-06-02 22:40:38', '2025-06-02 22:40:38');
INSERT INTO `user` VALUES ('52501011033', 'bbc52501011033', 0, '2025-06-02 22:40:38', '2025-06-02 22:40:38');
INSERT INTO `user` VALUES ('52501011034', 'bbc52501011034', 0, '2025-06-02 22:40:38', '2025-06-02 22:40:38');
INSERT INTO `user` VALUES ('52501011035', 'bbc52501011035', 0, '2025-06-02 22:40:38', '2025-06-02 22:40:38');
INSERT INTO `user` VALUES ('52501011036', 'bbc52501011036', 0, '2025-06-02 22:40:38', '2025-06-02 22:40:38');
INSERT INTO `user` VALUES ('52501011037', 'bbc52501011037', 0, '2025-06-02 22:40:38', '2025-06-02 22:40:38');
INSERT INTO `user` VALUES ('52501011038', 'bbc52501011038', 0, '2025-06-02 22:40:38', '2025-06-02 22:40:38');
INSERT INTO `user` VALUES ('52501011039', 'bbc52501011039', 0, '2025-06-02 22:40:38', '2025-06-02 22:40:38');
INSERT INTO `user` VALUES ('52501011040', 'bbc52501011040', 0, '2025-06-02 22:40:38', '2025-06-02 22:40:38');
INSERT INTO `user` VALUES ('52501011041', 'bbc52501011041', 0, '2025-06-02 22:40:38', '2025-06-02 22:40:38');
INSERT INTO `user` VALUES ('52501011042', 'bbc52501011042', 0, '2025-06-02 22:40:38', '2025-06-02 22:40:38');
INSERT INTO `user` VALUES ('52501011043', 'bbc52501011043', 0, '2025-06-02 22:40:38', '2025-06-02 22:40:38');
INSERT INTO `user` VALUES ('52501011044', 'bbc52501011044', 0, '2025-06-02 22:40:38', '2025-06-02 22:40:38');
INSERT INTO `user` VALUES ('52501011045', 'bbc52501011045', 0, '2025-06-02 22:40:38', '2025-06-02 22:40:38');
INSERT INTO `user` VALUES ('52501011046', 'bbc52501011046', 0, '2025-06-02 22:40:38', '2025-06-02 22:40:38');
INSERT INTO `user` VALUES ('52501011047', 'bbc52501011047', 0, '2025-06-02 22:40:38', '2025-06-02 22:40:38');
INSERT INTO `user` VALUES ('52501011048', 'bbc52501011048', 0, '2025-06-02 22:40:38', '2025-06-02 22:40:38');
INSERT INTO `user` VALUES ('52501011049', 'bbc52501011049', 0, '2025-06-02 22:40:38', '2025-06-02 22:40:38');
INSERT INTO `user` VALUES ('52501011050', 'bbc52501011050', 0, '2025-06-02 22:40:38', '2025-06-02 22:40:38');
INSERT INTO `user` VALUES ('52501011051', 'bbc52501011051', 0, '2025-06-02 22:40:38', '2025-06-02 22:40:38');
INSERT INTO `user` VALUES ('52501011052', 'bbc52501011052', 0, '2025-06-02 22:40:38', '2025-06-02 22:40:38');
INSERT INTO `user` VALUES ('52501011053', 'bbc52501011053', 0, '2025-06-02 22:40:38', '2025-06-02 22:40:38');
INSERT INTO `user` VALUES ('52501011054', 'bbc52501011054', 0, '2025-06-02 22:40:38', '2025-06-02 22:40:38');
INSERT INTO `user` VALUES ('52501011055', 'bbc52501011055', 0, '2025-06-02 22:40:38', '2025-06-02 22:40:38');
INSERT INTO `user` VALUES ('52501011056', 'bbc52501011056', 0, '2025-06-02 22:40:38', '2025-06-02 22:40:38');
INSERT INTO `user` VALUES ('52501011057', 'bbc52501011057', 0, '2025-06-02 22:40:38', '2025-06-02 22:40:38');
INSERT INTO `user` VALUES ('52501012001', 'bbc52501012001', 0, '2025-06-12 17:22:33', '2025-06-12 17:22:33');
INSERT INTO `user` VALUES ('52501012002', 'bbc52501012002', 0, '2025-06-12 17:22:33', '2025-06-12 17:22:33');
INSERT INTO `user` VALUES ('52501012003', 'bbc52501012003', 0, '2025-06-12 17:22:33', '2025-06-12 17:22:33');
INSERT INTO `user` VALUES ('52501012004', 'bbc52501012004', 0, '2025-06-12 17:22:33', '2025-06-12 17:22:33');
INSERT INTO `user` VALUES ('52501012005', 'bbc52501012005', 0, '2025-06-12 17:22:33', '2025-06-12 17:22:33');
INSERT INTO `user` VALUES ('52501012006', 'bbc52501012006', 0, '2025-06-12 17:22:33', '2025-06-12 17:22:33');
INSERT INTO `user` VALUES ('52501012007', 'bbc52501012007', 0, '2025-06-12 17:22:33', '2025-06-12 17:22:33');
INSERT INTO `user` VALUES ('52501012008', 'bbc52501012008', 0, '2025-06-12 17:22:33', '2025-06-12 17:22:33');
INSERT INTO `user` VALUES ('52501012009', 'bbc52501012009', 0, '2025-06-12 17:22:33', '2025-06-12 17:22:33');
INSERT INTO `user` VALUES ('52501012010', 'bbc52501012010', 0, '2025-06-12 17:22:33', '2025-06-12 17:22:33');
INSERT INTO `user` VALUES ('52501012011', 'bbc52501012011', 0, '2025-06-12 17:22:33', '2025-06-12 17:22:33');
INSERT INTO `user` VALUES ('52501012012', 'bbc52501012012', 0, '2025-06-12 17:22:33', '2025-06-12 17:22:33');
INSERT INTO `user` VALUES ('52501012013', 'bbc52501012013', 0, '2025-06-12 17:22:33', '2025-06-12 17:22:33');
INSERT INTO `user` VALUES ('52501012014', 'bbc52501012014', 0, '2025-06-12 17:22:33', '2025-06-12 17:22:33');
INSERT INTO `user` VALUES ('52501012015', 'bbc52501012015', 0, '2025-06-12 17:22:33', '2025-06-12 17:22:33');
INSERT INTO `user` VALUES ('52501012016', 'bbc52501012016', 0, '2025-06-12 17:22:33', '2025-06-12 17:22:33');
INSERT INTO `user` VALUES ('52501012017', 'bbc52501012017', 0, '2025-06-12 17:22:33', '2025-06-12 17:22:33');
INSERT INTO `user` VALUES ('52501012018', 'bbc52501012018', 0, '2025-06-12 17:22:33', '2025-06-12 17:22:33');
INSERT INTO `user` VALUES ('52501012019', 'bbc52501012019', 0, '2025-06-12 17:22:33', '2025-06-12 17:22:33');
INSERT INTO `user` VALUES ('52501012020', 'bbc52501012020', 0, '2025-06-12 17:22:33', '2025-06-12 17:22:33');
INSERT INTO `user` VALUES ('52501012021', 'bbc52501012021', 0, '2025-06-12 17:22:33', '2025-06-12 17:22:33');
INSERT INTO `user` VALUES ('52501012022', 'bbc52501012022', 0, '2025-06-12 17:22:33', '2025-06-12 17:22:33');
INSERT INTO `user` VALUES ('52501012023', 'bbc52501012023', 0, '2025-06-12 17:22:33', '2025-06-12 17:22:33');
INSERT INTO `user` VALUES ('52501012024', 'bbc52501012024', 0, '2025-06-12 17:22:33', '2025-06-12 17:22:33');
INSERT INTO `user` VALUES ('52501012025', 'bbc52501012025', 0, '2025-06-12 17:22:33', '2025-06-12 17:22:33');
INSERT INTO `user` VALUES ('52501012026', 'bbc52501012026', 0, '2025-06-12 17:22:33', '2025-06-12 17:22:33');
INSERT INTO `user` VALUES ('52501012027', 'bbc52501012027', 0, '2025-06-12 17:22:33', '2025-06-12 17:22:33');
INSERT INTO `user` VALUES ('52501012028', 'bbc52501012028', 0, '2025-06-12 17:22:33', '2025-06-12 17:22:33');
INSERT INTO `user` VALUES ('52501012029', 'bbc52501012029', 0, '2025-06-12 17:22:33', '2025-06-12 17:22:33');
INSERT INTO `user` VALUES ('52501012030', 'bbc52501012030', 0, '2025-06-12 17:22:33', '2025-06-12 17:22:33');
INSERT INTO `user` VALUES ('52501012031', 'bbc52501012031', 0, '2025-06-12 17:22:33', '2025-06-12 17:22:33');
INSERT INTO `user` VALUES ('52501012032', 'bbc52501012032', 0, '2025-06-12 17:22:33', '2025-06-12 17:22:33');
INSERT INTO `user` VALUES ('52501012033', 'bbc52501012033', 0, '2025-06-12 17:22:33', '2025-06-12 17:22:33');
INSERT INTO `user` VALUES ('52501012034', 'bbc52501012034', 0, '2025-06-12 17:22:33', '2025-06-12 17:22:33');
INSERT INTO `user` VALUES ('52501012035', 'bbc52501012035', 0, '2025-06-12 17:22:33', '2025-06-12 17:22:33');
INSERT INTO `user` VALUES ('52501012036', 'bbc52501012036', 0, '2025-06-12 17:22:33', '2025-06-12 17:22:33');
INSERT INTO `user` VALUES ('52501012037', 'bbc52501012037', 0, '2025-06-12 17:22:33', '2025-06-12 17:22:33');
INSERT INTO `user` VALUES ('52501012038', 'bbc52501012038', 0, '2025-06-12 17:22:33', '2025-06-12 17:22:33');
INSERT INTO `user` VALUES ('52501012039', 'bbc52501012039', 0, '2025-06-12 17:22:33', '2025-06-12 17:22:33');
INSERT INTO `user` VALUES ('52501012040', 'bbc52501012040', 0, '2025-06-12 17:22:33', '2025-06-12 17:22:33');
INSERT INTO `user` VALUES ('52501012041', 'bbc52501012041', 0, '2025-06-12 17:22:33', '2025-06-12 17:22:33');
INSERT INTO `user` VALUES ('52501012042', 'bbc52501012042', 0, '2025-06-12 17:22:33', '2025-06-12 17:22:33');
INSERT INTO `user` VALUES ('52501012043', 'bbc52501012043', 0, '2025-06-12 17:22:33', '2025-06-12 17:22:33');
INSERT INTO `user` VALUES ('52501012044', 'bbc52501012044', 0, '2025-06-12 17:22:33', '2025-06-12 17:22:33');
INSERT INTO `user` VALUES ('52501012045', 'bbc52501012045', 0, '2025-06-12 17:22:33', '2025-06-12 17:22:33');
INSERT INTO `user` VALUES ('52501012046', 'bbc52501012046', 0, '2025-06-12 17:22:33', '2025-06-12 17:22:33');
INSERT INTO `user` VALUES ('52501012047', 'bbc52501012047', 0, '2025-06-12 17:22:33', '2025-06-12 17:22:33');
INSERT INTO `user` VALUES ('52501012048', 'bbc52501012048', 0, '2025-06-12 17:22:33', '2025-06-12 17:22:33');
INSERT INTO `user` VALUES ('52501012049', 'bbc52501012049', 0, '2025-06-12 17:22:33', '2025-06-12 17:22:33');
INSERT INTO `user` VALUES ('52501012050', 'bbc52501012050', 0, '2025-06-12 17:22:33', '2025-06-12 17:22:33');
INSERT INTO `user` VALUES ('52501012051', 'bbc52501012051', 0, '2025-06-12 17:22:33', '2025-06-12 17:22:33');
INSERT INTO `user` VALUES ('52501012052', 'bbc52501012052', 0, '2025-06-12 17:22:33', '2025-06-12 17:22:33');
INSERT INTO `user` VALUES ('52501012053', 'bbc52501012053', 0, '2025-06-12 17:22:33', '2025-06-12 17:22:33');
INSERT INTO `user` VALUES ('52501012054', 'bbc52501012054', 0, '2025-06-12 17:22:33', '2025-06-12 17:22:33');
INSERT INTO `user` VALUES ('52501012055', 'bbc52501012055', 0, '2025-06-12 17:22:33', '2025-06-12 17:22:33');
INSERT INTO `user` VALUES ('52501012056', 'bbc52501012056', 0, '2025-06-12 17:22:33', '2025-06-12 17:22:33');
INSERT INTO `user` VALUES ('52501012057', 'bbc52501012057', 0, '2025-06-12 17:22:33', '2025-06-12 17:22:33');
INSERT INTO `user` VALUES ('52501012058', 'bbc52501012058', 0, '2025-06-12 17:22:33', '2025-06-12 17:22:33');
INSERT INTO `user` VALUES ('52501012059', 'bbc52501012059', 0, '2025-06-12 17:22:33', '2025-06-12 17:22:33');
INSERT INTO `user` VALUES ('52501012060', 'bbc52501012060', 0, '2025-06-12 17:22:33', '2025-06-12 17:22:33');
INSERT INTO `user` VALUES ('superAdmin', '123456', 2, '2025-05-17 15:17:43', '2025-05-17 15:17:43');
INSERT INTO `user` VALUES ('T7235', 'T7235', 1, '2025-06-02 22:47:02', '2025-06-02 22:47:02');
INSERT INTO `user` VALUES ('T7236', 'T7236', 1, '2025-06-02 22:48:36', '2025-06-02 22:48:36');
INSERT INTO `user` VALUES ('T7237', 'T7237', 1, '2025-06-02 22:51:09', '2025-06-02 22:51:09');

-- ----------------------------
-- View structure for studentdetailview
-- ----------------------------
DROP VIEW IF EXISTS `studentdetailview`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `studentdetailview` AS select `s`.`student_id` AS `student_id`,`s`.`name` AS `name`,`co`.`name` AS `counselor_name`,`cl`.`class_id` AS `class_id`,`cl`.`class_name` AS `class_name`,`m`.`major_id` AS `major_id`,`m`.`major_name` AS `major_name`,`col`.`college_id` AS `college_id`,`col`.`college_name` AS `college_name`,`a`.`grade` AS `grade` from (((((`student` `s` join `academicinfo` `a` on((`s`.`student_id` = `a`.`student_id`))) left join `counselor` `co` on((`a`.`counselor_id` = `co`.`counselor_id`))) left join `class` `cl` on((`a`.`class_id` = `cl`.`class_id`))) left join `major` `m` on((`a`.`major_id` = `m`.`major_id`))) left join `college` `col` on((`a`.`college_id` = `col`.`college_id`)));

-- ----------------------------
-- Triggers structure for table course
-- ----------------------------
DROP TRIGGER IF EXISTS `after_course_insert`;
delimiter ;;
CREATE TRIGGER `after_course_insert` AFTER INSERT ON `course` FOR EACH ROW BEGIN
    DECLARE student_count INT;
    
    -- 先获取班级学生数量
    SELECT COUNT(*) INTO student_count
    FROM AcademicInfo
    WHERE class_id = NEW.class_id;
    
    -- 只有存在学生时才处理
    IF student_count > 0 THEN
        -- 使用批量插入替代游标，效率更高
        INSERT INTO grade (
            student_id, course_id
        )
        SELECT 
            a.student_id, 
            NEW.course_id
        FROM 
            AcademicInfo a
        WHERE 
            a.class_id = NEW.class_id;
    END IF;
END
;;
delimiter ;

-- ----------------------------
-- Triggers structure for table student
-- ----------------------------
DROP TRIGGER IF EXISTS `after_student_insert`;
delimiter ;;
CREATE TRIGGER `after_student_insert` AFTER INSERT ON `student` FOR EACH ROW BEGIN
    -- 将学号作为用户ID，密码设置为"bbc"+学号，角色为学生(0)
    INSERT INTO User (user_id, password, role)
    VALUES (NEW.student_id, CONCAT('bbc', NEW.student_id), 0);
END
;;
delimiter ;

-- ----------------------------
-- Triggers structure for table teacher
-- ----------------------------
DROP TRIGGER IF EXISTS `after_teacher_insert`;
delimiter ;;
CREATE TRIGGER `after_teacher_insert` AFTER INSERT ON `teacher` FOR EACH ROW BEGIN
    
    INSERT INTO User (user_id, password, role)
    VALUES (NEW.teacher_id, NEW.teacher_id, 1);
END
;;
delimiter ;

SET FOREIGN_KEY_CHECKS = 1;
