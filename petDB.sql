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

 Date: 12/12/2023 00:50:50
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for chat_record
-- ----------------------------
DROP TABLE IF EXISTS `chat_record`;
CREATE TABLE `chat_record`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_message` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `model_response` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `timestamp` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  `user_id` int(11) NULL DEFAULT NULL,
  `seller_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of chat_record
-- ----------------------------
INSERT INTO `chat_record` VALUES (8, '我想买一只可爱的狗，有什么推荐吗？', '当然，我们这里有各种不同品种的狗可供选择。您更倾向于大型犬还是小型犬呢？或者有没有特定的品种感兴趣？我可以根据您的喜好给出一些建议。', '2023-12-11 14:41:06', 2, 1);
INSERT INTO `chat_record` VALUES (9, '大型犬', '对于大型犬，有很多不同的品种可供选择。您对哪种大型犬感兴趣？例如德国牧羊犬、拉布拉多寻回犬、金毛寻回犬等。', '2023-12-11 14:41:30', 2, 1);
INSERT INTO `chat_record` VALUES (10, '德牧', '德国牧羊犬是一种聪明、勇敢和忠诚的工作犬，它们非常适合作为家庭宠物和工作伙伴。它们需要大量的运动和训练，因为它们是工作犬，渴望有任务可做。您是否对德国牧羊犬有兴趣？', '2023-12-11 14:41:43', 2, 1);
INSERT INTO `chat_record` VALUES (11, '是的', '很高兴您做出了决定！如果您需要任何协助或有其他问题，随时联系我。祝您和新宠物伙伴幸福快乐！', '2023-12-11 14:41:53', 2, 1);
INSERT INTO `chat_record` VALUES (12, '德牧有什么具体的驯养需要注意的地方吗？', '德国牧羊犬是一种非常聪明、忠诚和勇敢的犬种，需要适当的训练和社交化来确保其行为良好。以下是一些德国牧羊犬的驯养注意事项：\n\n1. 训练与社交化：德国牧羊犬需要早期训练和社交化，以确保其成为友善、亲近人类的宠物。定期进行基本的听从性训练和社交化训练是非常重要的。\n\n2. 锻炼：德国牧羊犬是高度活跃的犬种，需要充分的锻炼来保持身体和心理健康。提供足够的户外活动和游戏时间对于德国牧羊犬非常重要。\n\n3. 梳理：德国牧羊犬的被毛需要定期梳理，特别是在换毛期。这有助于减少脱毛和保持被毛清洁。\n\n4. 精神刺激：考虑为德国牧羊犬提供足够的智力刺激，例如玩具、智力游戏和训练，以帮助它们保持活跃的思维。\n\n5. 爱与关怀：德国牧羊犬非常忠诚，喜欢与家人在一起。定期给予爱与关怀可以帮助它们建立亲密关系。\n\n请记住，每只宠物都有自己独特的需求和个性，因此最好根据您具体的德国牧羊犬的行为和需求进行个性化的驯养。', '2023-12-11 14:42:48', 2, 1);
INSERT INTO `chat_record` VALUES (13, '你叫什么名字', '我是宠物商城智能客服，您可以叫我宠物小助手。有什么可以帮到您的吗？', '2023-12-11 21:11:13', 2, 1);
INSERT INTO `chat_record` VALUES (14, '我想买一本书', '抱歉，我只能回答与宠物有关的问题。如果您有关于宠物的任何问题，欢迎向我咨询。', '2023-12-11 21:11:38', 2, 1);
INSERT INTO `chat_record` VALUES (15, '能否为我推荐一本书？', '非常抱歉，我只能回答和宠物有关的问题。如果您有任何关于宠物的问题，我会很乐意帮助您。', '2023-12-11 23:44:43', 2, 24);
INSERT INTO `chat_record` VALUES (16, '能否为我推荐一本书？', '非常抱歉，我只能提供与宠物相关的帮助。如果您有关于选择、护理或培训宠物的问题，我会很乐意为您提供帮助。', '2023-12-11 23:44:44', 2, 24);

-- ----------------------------
-- Table structure for favorites
-- ----------------------------
DROP TABLE IF EXISTS `favorites`;
CREATE TABLE `favorites`  (
  `user_id` int(11) NOT NULL,
  `post_id` int(11) NOT NULL,
  PRIMARY KEY (`user_id`, `post_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of favorites
-- ----------------------------
INSERT INTO `favorites` VALUES (2, 24);

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
  `price` decimal(10, 2) NULL DEFAULT NULL,
  `is_sold` tinyint(1) NULL DEFAULT NULL,
  PRIMARY KEY (`pet_id`) USING BTREE,
  INDEX `owner_id`(`owner_id`) USING BTREE,
  CONSTRAINT `pet_ibfk_1` FOREIGN KEY (`owner_id`) REFERENCES `user` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of pet
-- ----------------------------
INSERT INTO `pet` VALUES (4, 2, '柴犬', '狗', '柴犬', 3, '可爱', '健康', '接种', 'https://pic.52112.com/180108/DOG/ztuo2nUs8z.jpg', '2023-12-11 23:29:14', '2023-12-11 23:29:14', 5000.00, 1);
INSERT INTO `pet` VALUES (5, 2, '小程', '鱼', '热带鱼', 1, '橙色', '健康', '无', 'https://pic.quanjing.com/ue/jp/QJ8144942917.jpg@!350h', '2023-12-11 23:30:06', '2023-12-11 23:30:06', 30.00, NULL);

-- ----------------------------
-- Table structure for post
-- ----------------------------
DROP TABLE IF EXISTS `post`;
CREATE TABLE `post`  (
  `post_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `content` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `image_urls` json NULL,
  `created_at` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `updated_at` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  `pet_ids` json NULL,
  `stars` double NULL DEFAULT NULL,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `pet_names` json NULL,
  PRIMARY KEY (`post_id`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE,
  CONSTRAINT `post_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 25 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of post
-- ----------------------------
INSERT INTO `post` VALUES (24, 2, '一只柴犬', '柴犬很可爱', '[\"https://pic.quanjing.com/zh/6f/QJ6331436333.jpg@!350h\"]', '2023-12-11 23:35:57', '2023-12-11 23:44:04', '[\"4\"]', NULL, NULL, '[]');

-- ----------------------------
-- Table structure for ratings
-- ----------------------------
DROP TABLE IF EXISTS `ratings`;
CREATE TABLE `ratings`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `post_id` int(11) NULL DEFAULT NULL,
  `user_id` int(11) NULL DEFAULT NULL,
  `star` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `post_id`(`post_id`) USING BTREE,
  INDEX `pet_id`(`user_id`) USING BTREE,
  CONSTRAINT `ratings_ibfk_1` FOREIGN KEY (`post_id`) REFERENCES `post` (`post_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
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
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

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
INSERT INTO `user` VALUES (2, 'user1', 'abc@qq.com', '$2a$10$Ut38B.K.I5cEWEXoG29Qxun1aF1a0VbFY0K5Uo/apN5SGSK3.8Mpq', 'user/query', 'https://pic.quanjing.com/k9/f9/QJ6967399057.jpg@!350h\n', NULL, '2023-11-21 01:22:32');
INSERT INTO `user` VALUES (4, 'user2', 'def@qq.com', '$2a$10$tF9svHUn13cl4sDS/8x2CuUWHhMU9hzN4kYpv9/EZUCijyjrRbeYO', 'user/query', NULL, NULL, '2023-11-21 16:14:09');
INSERT INTO `user` VALUES (5, 'usertest', 'test@qq.com', '$2a$10$sGnvdo/Ht6dwr7lK8MqiS.4/IRziqYEh3GR2d8nOUFTr/SsS0IeDK', 'user/query', NULL, NULL, '2023-11-22 14:29:12');
INSERT INTO `user` VALUES (6, 'user123', 'user123@qq.com', '$2a$10$G7KeUAkrOBERXRwHL4NnbOCFiHGMz6r3jBnthBnaI4.ndpKybZJNy', 'user/query', NULL, NULL, '2023-11-27 12:48:43');
INSERT INTO `user` VALUES (7, 'user456', 'user456@qq.com', '$2a$10$zHWQASfOupqkRBSyqzLhlel0sGYfiiHWPknKTCtmaLS.YH2JALGhy', 'user/query', NULL, NULL, '2023-11-27 12:53:22');
INSERT INTO `user` VALUES (8, 'useruser', '', '$2a$10$i0SsiehVjJadnPVdokRki.YV08.keKwzf6yYvsYRiXuzBqgr6mhdy', 'user/query', NULL, NULL, '2023-11-27 13:09:01');
INSERT INTO `user` VALUES (9, 'user5', 'user5@qq.com', '$2a$10$D3M.eyHisi4Bo.XW5m/WYO0fbHYx7oubf1jow7cgZy77yZZ4qB9Py', 'user/query', NULL, NULL, '2023-11-27 13:11:38');
INSERT INTO `user` VALUES (10, 'google', 'google@qq.com', '$2a$10$riSX7U257E76WybpTyZx8.LD4xxSgNKGogX5ET/KPXB.yvAZcxqfu', 'user/query', NULL, NULL, '2023-11-29 09:56:36');
INSERT INTO `user` VALUES (11, 'user', 'user@user.com', '$2a$10$XbDM251znvgQF1J.t8eAtezQ9mZX36JDXBQV8bGaU.0FhIrIiadj6', 'user/query', '\"https://pic.quanjing.com/ex/3v/QJ6395044826.jpg@!350h\"', NULL, '2023-12-01 11:57:51');

SET FOREIGN_KEY_CHECKS = 1;
