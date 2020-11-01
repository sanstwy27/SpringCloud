DROP TABLE IF EXISTS `oauth_client_details`;
CREATE TABLE `oauth_client_details` (
    `client_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
    `resource_ids` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `client_secret` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `scope` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `authorized_grant_types` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `web_server_redirect_uri` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `authorities` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `access_token_validity` int(11) NULL DEFAULT NULL,
    `refresh_token_validity` int(11) NULL DEFAULT NULL,
    `additional_information` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
    `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
    `archived` tinyint(4) NULL DEFAULT NULL,
    `trusted` tinyint(4) NULL DEFAULT NULL,
    `autoapprove` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    PRIMARY KEY (`client_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'oauth_client_details'
ROW_FORMAT = Dynamic;
INSERT INTO `oauth_client_details` VALUES
('c1', 'res1',
'$2a$10$NlBC84MVb7F95EXYTXwLneXgCca6/GipyWR5NHm8K0203bSQMLpvm', 'ROLE_ADMIN,ROLE_USER,ROLE_API',
'client_credentials,password,authorization_code,implicit,refresh_token', 'http://www.google.com',
NULL, 7200, 259200, NULL, CURRENT_TIMESTAMP(0), 0, 0, 'false');
INSERT INTO `oauth_client_details` VALUES
('c2', 'res2',
'$2a$10$NlBC84MVb7F95EXYTXwLneXgCca6/GipyWR5NHm8K0203bSQMLpvm', 'ROLE_API',
'client_credentials,password,authorization_code,implicit,refresh_token', 'http://www.google.com',
NULL, 31536000, 2592000, NULL, CURRENT_TIMESTAMP(0), 0, 0, 'false');

DROP TABLE IF EXISTS `oauth_code`;
CREATE TABLE `oauth_code` (
    `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `authentication` blob NULL,
    INDEX `code_index`(`code`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;