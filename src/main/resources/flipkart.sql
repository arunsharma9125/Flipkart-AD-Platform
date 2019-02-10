CREATE TABLE `spring_session` (
   `PRIMARY_ID` char(36) NOT NULL,
   `SESSION_ID` char(36) NOT NULL,
   `CREATION_TIME` bigint(20) NOT NULL,
   `LAST_ACCESS_TIME` bigint(20) NOT NULL,
   `MAX_INACTIVE_INTERVAL` int(11) NOT NULL,
   `EXPIRY_TIME` bigint(20) NOT NULL,
   `PRINCIPAL_NAME` varchar(100) DEFAULT NULL,
   PRIMARY KEY (`PRIMARY_ID`),
   UNIQUE KEY `SPRING_SESSION_IX1` (`SESSION_ID`),
   KEY `SPRING_SESSION_IX2` (`EXPIRY_TIME`),
   KEY `SPRING_SESSION_IX3` (`PRINCIPAL_NAME`)
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC


DROP TABLE IF EXISTS `SPRING_SESSION_ATTRIBUTES`;
CREATE TABLE `spring_session_attributes` (
   `SESSION_PRIMARY_ID` char(36) NOT NULL,
   `ATTRIBUTE_NAME` varchar(200) NOT NULL,
   `ATTRIBUTE_BYTES` blob NOT NULL,
   PRIMARY KEY (`SESSION_PRIMARY_ID`,`ATTRIBUTE_NAME`),
   CONSTRAINT `SPRING_SESSION_ATTRIBUTES_FK` FOREIGN KEY (`SESSION_PRIMARY_ID`) REFERENCES `spring_session` (`primary_id`) ON DELETE CASCADE
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC

DROP TABLE IF EXISTS `persistent_logins`;
CREATE TABLE `persistent_logins` (
   `username` varchar(64) DEFAULT NULL,
   `series` varchar(64) NOT NULL,
   `token` varchar(64) DEFAULT NULL,
   `last_used` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
   PRIMARY KEY (`series`)
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci
	
DROP TABLE IF EXISTS `User`;
CREATE TABLE `User` (
	`id` varchar(255) NOT NULL,
	`email` varchar(255) DEFAULT NULL,
	`password` varchar(255) DEFAULT NULL,
	`name` varchar(255) DEFAULT NULL,
	`age` int(11) DEFAULT NULL,
	`gender` varchar(2) DEFAULT NULL,
	PRIMARY KEY (`id`)
	);
	
DROP TABLE IF EXISTS `User`;
CREATE TABLE `User` (
	`id` varchar(255) NOT NULL,
	`email` varchar(255) DEFAULT NULL,
	`password` varchar(255) DEFAULT NULL,
	`name` varchar(255) DEFAULT NULL,
	`age` int(11) DEFAULT NULL,
	`gender` varchar(2) DEFAULT NULL,
	PRIMARY KEY (`id`)
	);

DROP TABLE IF EXISTS `Advertiser`;
CREATE TABLE `Advertiser` (
	`id` varchar(255) NOT NULL,
	`name` varchar(255) DEFAULT NULL,
	PRIMARY KEY (`id`)
	);
	
DROP TABLE IF EXISTS `Advertisement`;
CREATE TABLE `Advertisement` (
	`id` varchar(255) NOT NULL,
	`message` varchar(255) DEFAULT NULL,
	`start_age` int(11),
	`end_age` int(11),
	`gender` varchar(5),
	`bid` int(11),
	`budget` int(11),
	`advertiser_id` varchar(255) DEFAULT NULL,
	PRIMARY KEY (`id`)
	);
	
	
DROP TABLE IF EXISTS `Bidding_Type`;
CREATE TABLE `Bidding_Type` (
	`id` varchar(255) NOT NULL,
	`bidding_type` varchar(255) DEFAULT NULL,
	PRIMARY KEY (`id`)
	);	