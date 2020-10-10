CREATE TABLE `t_permission` (
`id` varchar(32) NOT NULL,
`code` varchar(32) NOT NULL COMMENT 'flag',
`description` varchar(64) DEFAULT NULL COMMENT 'comment',
`url` varchar(128) DEFAULT NULL COMMENT 'request url',
PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
insert into `t_permission`(`id`,`code`,`description`,`url`) values
('1','p1','resource 1','/r/r1'),('2','p3','resource 2','/r/r2');