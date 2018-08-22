/*
SQLyog Ultimate v8.71 
MySQL - 5.7.23-log : Database - demo
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`demo` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

USE `demo`;

/*Table structure for table `demo_user` */

DROP TABLE IF EXISTS `demo_user`;

CREATE TABLE `demo_user` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(100) NOT NULL,
  `password` char(60) DEFAULT NULL,
  `role` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UN_NAME` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8mb4;

/*Data for the table `demo_user` */

insert  into `demo_user`(`id`,`username`,`password`,`role`) values (1,'test','$2a$10$axpG6TabhXFbEZ7UVHHK..9Tka5LI0ZtRNz/E7XUN6x/TS3hffPCG','admin'),(3,'test1','$2a$10$twd4E4Y3Yqck.gKFuG2Wee9rFyrEunFL/z/EgwT6HpanvnKIO/gxG','user'),(4,'test2','$2a$10$7dV0BrRuPKDPYSwldfFSeeZlNPbsQZ79KEmSFdXW8IcW2et9GcdZ6','user'),(26,'test3','$2a$10$tkC1/EvibtrxaaDbpioOauFdn/j2fIxSW/ezwguPcpZa5p6mESXma','user'),(28,'test4','$2a$10$JQUZV5bz6CxoexRxlqJCsOC2Nt0tV7zaL/z2UC1dmgwX6kGM4Ytga','user'),(29,'test5','$2a$10$V.ECQTJPitErH90A2JLXO.cy1Y/jfdrGIEhsTAqUiLADAyXUm/212','user'),(30,'test6','$2a$10$9z0HNiKI2.4tUrUok9PtqueLu3Bjka2Zi1uDp80egPwlIaF0dSRQu','user'),(32,'测试','$2a$10$gX4LwbmePtv6MwiO8dCo7e18NJhR1E76emgRucLEhtmGsALllR/uO','user'),(33,'测试2','$2a$10$8o2H7BEUo2fkcG3tAeZ0Uu80rO.z2fkqnoZ9RmfDZkG1c3LGe.xfy','user'),(34,'测试3','$2a$10$Y1/6DlqYk6GQfDTXYpJNauvRTB26YXOhTO9WN51aNzW6pSCXHES/2','user'),(35,'测试4','$2a$10$8a0XVRfBobePPVaojRpRX.02P0sk0BIs48V7hmSgyoqT5xeXFOtCG','user'),(36,'测试5','$2a$10$wcJdjMIYSvtQrOjWRyw8EegwTAs6qnk4UZwXFrJ1GyLndH5VE8JpS','user'),(37,'test7','$2a$10$JULZLrW8Tk1.sGzKMVxuF.BgRsSp1Nst/OHLF7iGZSwJz3WPLhnRq','user'),(38,'test8','$2a$10$IFmgBQICaR8oMYSvazlpAexrYjSCGhHvUPTKPm4v3PcSgpkWXgjd.','user'),(39,'test9','$2a$10$jo83Nsodqo6xfJyz.SUujuJ8uDEBA7fy2kh71pfhXVGmynPOxutLC','user');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
