drop table if exists `GROUP_AUTHORITIES`;
drop table if exists `GROUP_MEMBERS`;
drop table if exists `GROUPS`;
drop table if exists `AUTHORITIES`;
drop table if exists `USERS`;

CREATE TABLE `USERS`   
(
	USERNAME VARCHAR(50) NOT NULL,
    PASSWORD VARCHAR(255) NOT NULL,
    ENABLED TINYINT(1) NOT NULL,
    PRIMARY KEY(USERNAME)
)ENGINE=InnoDB;
   
CREATE TABLE `AUTHORITIES`
(
	USERNAME VARCHAR(50) NOT NULL,
    AUTHORITY VARCHAR(68) NOT NULL,
    FOREIGN KEY (USERNAME) REFERENCES `USERS`(USERNAME)
)ENGINE=InnoDB;

CREATE TABLE `GROUPS`
(
	ID INT NOT NULL AUTO_INCREMENT,
	GROUP_NAME VARCHAR(50) NOT NULL,
	PRIMARY KEY(ID),
	UNIQUE KEY (GROUP_NAME)
)ENGINE=InnoDB;

CREATE TABLE `GROUP_MEMBERS`
(
	GROUP_ID INT NOT NULL,
	USERNAME VARCHAR(50) NOT NULL,
	PRIMARY KEY(GROUP_ID, USERNAME),
	FOREIGN KEY (GROUP_ID) REFERENCES `GROUPS`(ID),
	FOREIGN KEY (USERNAME) REFERENCES `USERS`(USERNAME)
)ENGINE=InnoDB;

CREATE TABLE `GROUP_AUTHORITIES`
(
	GROUP_ID INT NOT NULL,
	AUTHORITY VARCHAR(68) NOT NULL,
	PRIMARY KEY(GROUP_ID, AUTHORITY),
	FOREIGN KEY (GROUP_ID) REFERENCES `GROUPS`(ID)
)ENGINE=InnoDB;
