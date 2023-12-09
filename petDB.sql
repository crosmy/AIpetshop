/*
 Navicat Premium Data Transfer

 Source Server         : connect-local
 Source Server Type    : MySQL
 Source Server Version : 50728
 Source Host           : localhost:3306
 Source Schema         : petdb

 Target Server Type    : MySQL
 Target Server Version : 50728
 File Encoding         : 65001

 Date: 09/12/2023 15:35:59
*/
use petDB;

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for message
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message`  (
  `message_id` int(11) NOT NULL AUTO_INCREMENT,
  `sender_id` int(11) NOT NULL,
  `receiver_id` int(11) NOT NULL,
  `content` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `timestamp` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`message_id`) USING BTREE,
  INDEX `sender_id`(`sender_id`) USING BTREE,
  INDEX `receiver_id`(`receiver_id`) USING BTREE,
  CONSTRAINT `message_ibfk_1` FOREIGN KEY (`sender_id`) REFERENCES `user` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `message_ibfk_2` FOREIGN KEY (`receiver_id`) REFERENCES `user` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for pet
-- ----------------------------
DROP TABLE IF EXISTS `pet`;
CREATE TABLE `pet`  (
  `pet_id` int(11) NOT NULL AUTO_INCREMENT,
  `owner_id` int(11) NOT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `type` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `breed` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `age` int(11) NULL DEFAULT NULL,
  `description` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `health_information` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `vaccinations` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `photo_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `created_at` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `updated_at` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`pet_id`) USING BTREE,
  INDEX `owner_id`(`owner_id`) USING BTREE,
  CONSTRAINT `pet_ibfk_1` FOREIGN KEY (`owner_id`) REFERENCES `user` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for post
-- ----------------------------
DROP TABLE IF EXISTS `post`;
CREATE TABLE `post`  (
  `post_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `content` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `image_urls` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `created_at` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `updated_at` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  `pet_id` int(11) NULL DEFAULT NULL,
  `price` int(11) NOT NULL,
  `stars` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`post_id`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE,
  INDEX `pet_id`(`pet_id`) USING BTREE,
  CONSTRAINT `post_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `post_ibfk_2` FOREIGN KEY (`pet_id`) REFERENCES `pet` (`pet_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of post
-- ----------------------------
INSERT INTO `post` VALUES (4, 4, 'titleA', 'contentA', NULL, '2023-11-21 16:14:53', '2023-11-21 16:14:53', NULL, 0, NULL);
INSERT INTO `post` VALUES (5, 2, 'titleTest', 'contentTest', NULL, '2023-11-22 14:34:22', '2023-11-22 14:34:22', NULL, 0, NULL);
INSERT INTO `post` VALUES (6, 6, '11', '11', 'https://plus.unsplash.com/premium_photo-1661503280224-a86d7ad2a574?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MXx8cGV0c3xlbnwwfHwwfHx8MA%3D%3D', '2023-11-29 14:56:07', '2023-11-29 14:56:07', NULL, 100, NULL);
INSERT INTO `post` VALUES (7, 6, '22', '22', 'https://images.unsplash.com/photo-1450778869180-41d0601e046e?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Mnx8cGV0c3xlbnwwfHwwfHx8MA%3D%3D', '2023-11-29 14:56:26', '2023-11-29 14:56:26', NULL, 100, NULL);
INSERT INTO `post` VALUES (8, 6, '猫和狗', '猫和狗在睡觉', 'https://images.unsplash.com/photo-1623387641168-d9803ddd3f35?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8M3x8cGV0c3xlbnwwfHwwfHx8MA%3D%3D', '2023-11-29 14:57:03', '2023-11-29 14:57:03', NULL, 100, NULL);
INSERT INTO `post` VALUES (9, 6, '这是动物', '不知道是什么动物', 'https://images.unsplash.com/photo-1592754862816-1a21a4ea2281?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Nnx8cGV0c3xlbnwwfHwwfHx8MA%3D%3D', '2023-11-29 16:00:51', '2023-11-29 16:00:51', NULL, 100, NULL);
INSERT INTO `post` VALUES (10, 6, '猫', '这是一只可爱的猫', 'https://images.unsplash.com/photo-1583337130417-3346a1be7dee?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8NHx8cGV0c3xlbnwwfHwwfHx8MA%3D%3D', '2023-11-29 16:01:22', '2023-11-29 16:01:22', NULL, 100, NULL);
INSERT INTO `post` VALUES (11, 6, '狗', '这是一只凶猛的狗', 'https://plus.unsplash.com/premium_photo-1663091202352-a19c438ca22d?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8NXx8cGV0c3xlbnwwfHwwfHx8MA%3D%3D', '2023-11-29 16:01:41', '2023-11-29 16:01:41', NULL, 100, NULL);
INSERT INTO `post` VALUES (12, 11, '动物', '可爱，能动', 'https://plus.unsplash.com/premium_photo-1663036434780-998c35df5513?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8OXx8cGV0c3xlbnwwfHwwfHx8MA%3D%3D', '2023-12-06 16:36:08', '2023-12-06 16:36:08', NULL, 100, NULL);

-- ----------------------------
-- Table structure for ratings
-- ----------------------------
DROP TABLE IF EXISTS `ratings`;
CREATE TABLE `ratings`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `post_id` int(11) NULL DEFAULT NULL,
  `pet_id` int(11) NULL DEFAULT NULL,
  `star` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `post_id`(`post_id`) USING BTREE,
  INDEX `pet_id`(`pet_id`) USING BTREE,
  CONSTRAINT `ratings_ibfk_1` FOREIGN KEY (`post_id`) REFERENCES `post` (`post_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `ratings_ibfk_2` FOREIGN KEY (`pet_id`) REFERENCES `post` (`pet_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `role_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `authorities` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`role_id`) USING BTREE,
  UNIQUE INDEX `name`(`name`) USING BTREE,
  UNIQUE INDEX `role_pk`(`name`) USING BTREE,
  UNIQUE INDEX `role_pk2`(`name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for transaction
-- ----------------------------
DROP TABLE IF EXISTS `transaction`;
CREATE TABLE `transaction`  (
  `transaction_id` int(11) NOT NULL AUTO_INCREMENT,
  `seller_id` int(11) NOT NULL,
  `buyer_id` int(11) NOT NULL,
  `pet_id` int(11) NOT NULL,
  `price` decimal(10, 2) NULL DEFAULT NULL,
  `transaction_date` date NULL DEFAULT NULL,
  `status` enum('PENDING','COMPLETED','CANCELLED') CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT 'PENDING',
  PRIMARY KEY (`transaction_id`) USING BTREE,
  INDEX `seller_id`(`seller_id`) USING BTREE,
  INDEX `buyer_id`(`buyer_id`) USING BTREE,
  INDEX `pet_id`(`pet_id`) USING BTREE,
  CONSTRAINT `transaction_ibfk_1` FOREIGN KEY (`seller_id`) REFERENCES `user` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `transaction_ibfk_2` FOREIGN KEY (`buyer_id`) REFERENCES `user` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `transaction_ibfk_3` FOREIGN KEY (`pet_id`) REFERENCES `pet` (`pet_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `role` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT 'user/query',
  `profile_picture_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `contact_info` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `created_at` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`user_id`) USING BTREE,
  UNIQUE INDEX `username`(`username`) USING BTREE,
  UNIQUE INDEX `email`(`email`) USING BTREE,
  INDEX `role_id`(`role`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (2, 'user1', 'abc@qq.com', '$2a$10$Ut38B.K.I5cEWEXoG29Qxun1aF1a0VbFY0K5Uo/apN5SGSK3.8Mpq', 'user/query', NULL, NULL, '2023-11-21 01:22:32');
INSERT INTO `user` VALUES (4, 'user2', 'def@qq.com', '$2a$10$tF9svHUn13cl4sDS/8x2CuUWHhMU9hzN4kYpv9/EZUCijyjrRbeYO', 'user/query', NULL, NULL, '2023-11-21 16:14:09');
INSERT INTO `user` VALUES (5, 'usertest', 'test@qq.com', '$2a$10$sGnvdo/Ht6dwr7lK8MqiS.4/IRziqYEh3GR2d8nOUFTr/SsS0IeDK', 'user/query', NULL, NULL, '2023-11-22 14:29:12');
INSERT INTO `user` VALUES (6, 'user123', 'user123@qq.com', '$2a$10$G7KeUAkrOBERXRwHL4NnbOCFiHGMz6r3jBnthBnaI4.ndpKybZJNy', 'user/query', NULL, NULL, '2023-11-27 12:48:43');
INSERT INTO `user` VALUES (7, 'user456', 'user456@qq.com', '$2a$10$zHWQASfOupqkRBSyqzLhlel0sGYfiiHWPknKTCtmaLS.YH2JALGhy', 'user/query', NULL, NULL, '2023-11-27 12:53:22');
INSERT INTO `user` VALUES (8, 'useruser', '', '$2a$10$i0SsiehVjJadnPVdokRki.YV08.keKwzf6yYvsYRiXuzBqgr6mhdy', 'user/query', NULL, NULL, '2023-11-27 13:09:01');
INSERT INTO `user` VALUES (9, 'user5', 'user5@qq.com', '$2a$10$D3M.eyHisi4Bo.XW5m/WYO0fbHYx7oubf1jow7cgZy77yZZ4qB9Py', 'user/query', NULL, NULL, '2023-11-27 13:11:38');
INSERT INTO `user` VALUES (10, 'google', 'google@qq.com', '$2a$10$riSX7U257E76WybpTyZx8.LD4xxSgNKGogX5ET/KPXB.yvAZcxqfu', 'user/query', NULL, NULL, '2023-11-29 09:56:36');
INSERT INTO `user` VALUES (11, 'user', 'user@user.com', '$2a$10$XbDM251znvgQF1J.t8eAtezQ9mZX36JDXBQV8bGaU.0FhIrIiadj6', 'user/query', NULL, NULL, '2023-12-01 11:57:51');

SET FOREIGN_KEY_CHECKS = 1;
