CREATE TABLE `bmember` (
	`memberID` VARCHAR(50) NOT NULL DEFAULT '' COLLATE 'utf8mb3_general_ci',
	`memberPW` VARCHAR(50) NULL DEFAULT NULL COLLATE 'utf8mb3_general_ci',
	`memberName` VARCHAR(50) NULL DEFAULT NULL COLLATE 'utf8mb3_general_ci',
	`phone` VARCHAR(20) NULL DEFAULT NULL COLLATE 'utf8mb3_general_ci',
	`address` VARCHAR(100) NULL DEFAULT NULL COLLATE 'utf8mb3_general_ci',
	`email` VARCHAR(50) NULL DEFAULT NULL COLLATE 'utf8mb3_general_ci',
	`createDate` TIMESTAMP NULL DEFAULT current_timestamp(),
	PRIMARY KEY (`memberID`) USING BTREE
)
COLLATE='utf8mb3_general_ci'
ENGINE=InnoDB
;

CREATE TABLE `book` (
	`id` VARCHAR(100) NOT NULL COLLATE 'touristdbtouristdbutf8mb3_general_ci',
	`name` VARCHAR(100) NULL DEFAULT NULL COLLATE 'utf8mb3_general_ci',
	`description` TEXT NULL DEFAULT NULL COLLATE 'utf8mb3_general_ci',
	`category` VARCHAR(100) NULL DEFAULT NULL COLLATE 'utf8mb3_general_ci',
	`author` VARCHAR(100) NULL DEFAULT NULL COLLATE 'utf8mb3_general_ci',
	`publisher` VARCHAR(100) NULL DEFAULT NULL COLLATE 'utf8mb3_general_ci',
	`releaseDate` VARCHAR(100) NULL DEFAULT NULL COLLATE 'utf8mb3_general_ci',
	`pages` INT(11) NULL DEFAULT NULL,
	`unitPrice` INT(11) NULL DEFAULT NULL,
	`unitInStock` INT(11) NULL DEFAULT NULL,
	`imgFileName` VARCHAR(50) NULL DEFAULT NULL COLLATE 'utf8mb3_general_ci',
	`createDate` TIMESTAMP NULL DEFAULT current_timestamp(),
	PRIMARY KEY (`id`) USING BTREE
)
COLLATE='utf8mb3_general_ci'
ENGINE=InnoDB
;
