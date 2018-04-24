/*Table structure for table `user` */


DROP TABLE IF EXISTS `user`;


CREATE TABLE `user` (
  `id` tinyint(12) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `age` tinyint(3) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;


/*Data for the table `user` */