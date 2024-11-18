CREATE TABLE `enba_user_flyway` (
                                    `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
                                    `user_name` varchar(18) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '名称',
                                    `age` int DEFAULT NULL COMMENT '年龄',
                                    `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                    `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                                    `deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除（0：未删除 1：删除）',
                                    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;