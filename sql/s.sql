/*
SQLyog 企业版 - MySQL GUI v8.14 
MySQL - 5.0.20-nt-max : Database - watch-world
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`watch-world` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `watch-world`;

/*Table structure for table `activity` */

DROP TABLE IF EXISTS `activity`;

CREATE TABLE `activity` (
  `ActivityPicturePath` varchar(45) collate utf8_czech_ci default NULL,
  `StoreID` int(11) default NULL,
  KEY `FK_activity` (`StoreID`),
  CONSTRAINT `FK_activity` FOREIGN KEY (`StoreID`) REFERENCES `store_info` (`StoreID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_czech_ci CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC;

/*Table structure for table `admin_info` */

DROP TABLE IF EXISTS `admin_info`;

CREATE TABLE `admin_info` (
  `AdminID` varchar(15) collate utf8_czech_ci NOT NULL,
  `Pwd` varchar(15) collate utf8_czech_ci NOT NULL default '',
  `Session` int(11) default NULL,
  PRIMARY KEY  (`AdminID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_czech_ci;

/*Table structure for table `goods_display` */

DROP TABLE IF EXISTS `goods_display`;

CREATE TABLE `goods_display` (
  `GoodsID` int(11) default NULL,
  `Brand` varchar(15) collate utf8_czech_ci default NULL,
  `Time` time default NULL,
  KEY `FK_goods_display` (`GoodsID`),
  CONSTRAINT `FK_goods_display` FOREIGN KEY (`GoodsID`) REFERENCES `goods_info` (`GoodsID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_czech_ci CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC;

/*Table structure for table `goods_info` */

DROP TABLE IF EXISTS `goods_info`;

CREATE TABLE `goods_info` (
  `GoodsID` int(15) NOT NULL auto_increment,
  `GoodsName` varchar(45) collate utf8_czech_ci default NULL,
  `GoodsPicturePath` varchar(45) collate utf8_czech_ci default NULL,
  `Price` float default NULL,
  `Brand` varchar(15) collate utf8_czech_ci default NULL,
  `Color` varchar(15) collate utf8_czech_ci default NULL,
  `Model` varchar(15) collate utf8_czech_ci default NULL,
  `Number` int(11) default NULL,
  `StoreID` int(11) default NULL,
  `SalesVolumes` int(11) default NULL,
  PRIMARY KEY  (`GoodsID`),
  KEY `FK_goods_info` (`StoreID`),
  CONSTRAINT `FK_goods_info` FOREIGN KEY (`StoreID`) REFERENCES `store_info` (`StoreID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_czech_ci CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC;

/*Table structure for table `order_goods` */

DROP TABLE IF EXISTS `order_goods`;

CREATE TABLE `order_goods` (
  `OrderID` varchar(15) collate utf8_czech_ci default NULL,
  `GoodsID` int(15) default NULL,
  `GoodsName` varchar(255) collate utf8_czech_ci default NULL,
  `StoreID` varchar(15) collate utf8_czech_ci default NULL,
  `Price` int(11) default NULL,
  `Brand` varchar(15) collate utf8_czech_ci default NULL,
  `Color` varchar(15) collate utf8_czech_ci default NULL,
  `Model` varchar(15) collate utf8_czech_ci default NULL,
  `BuyAmount` int(11) default NULL,
  `GoodsPicturePath` varchar(45) collate utf8_czech_ci default NULL,
  KEY `FK_order_goods` (`OrderID`),
  CONSTRAINT `FK_order_goods` FOREIGN KEY (`OrderID`) REFERENCES `order_info` (`OrderID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_czech_ci CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC;

/*Table structure for table `order_info` */

DROP TABLE IF EXISTS `order_info`;

CREATE TABLE `order_info` (
  `OrderID` varchar(15) collate utf8_czech_ci NOT NULL default '',
  `OrderStatus` varchar(15) collate utf8_czech_ci default NULL,
  `PaymentTime` timestamp NULL default NULL,
  `SendGoodsTime` timestamp NULL default NULL,
  `StoreName` varchar(15) collate utf8_czech_ci default NULL,
  `UserName` varchar(15) collate utf8_czech_ci default NULL,
  `TotalPrice` int(11) default NULL,
  `Address` varchar(45) collate utf8_czech_ci default NULL,
  `Telephone` varchar(15) collate utf8_czech_ci default NULL,
  `UserID` varchar(15) collate utf8_czech_ci default NULL,
  PRIMARY KEY  (`OrderID`),
  KEY `FK_userID` (`UserID`),
  CONSTRAINT `FK_userID` FOREIGN KEY (`UserID`) REFERENCES `user_info` (`UserID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_czech_ci CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC;

/*Table structure for table `store_info` */

DROP TABLE IF EXISTS `store_info`;

CREATE TABLE `store_info` (
  `StoreID` int(11) NOT NULL auto_increment,
  `LoginName` varchar(15) collate utf8_czech_ci default NULL,
  `Pwd` varchar(15) collate utf8_czech_ci default NULL,
  `StoreName` varchar(15) collate utf8_czech_ci default NULL,
  `Owner` varchar(15) collate utf8_czech_ci default NULL,
  `RegTime` varchar(45) collate utf8_czech_ci default NULL,
  `Telephone` varchar(11) collate utf8_czech_ci default NULL,
  PRIMARY KEY  (`StoreID`),
  UNIQUE KEY `LoginName` (`LoginName`),
  UNIQUE KEY `StoreName` (`StoreName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_czech_ci MIN_ROWS=8888 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC;

/*Table structure for table `user_data` */

DROP TABLE IF EXISTS `user_data`;

CREATE TABLE `user_data` (
  `UserId` varchar(15) collate utf8_czech_ci NOT NULL,
  `UserName` varchar(15) collate utf8_czech_ci default NULL,
  `Age` int(11) default NULL,
  `Sex` varchar(10) collate utf8_czech_ci default NULL,
  `Portrait` varchar(100) collate utf8_czech_ci default NULL,
  `Address` varchar(50) collate utf8_czech_ci default NULL,
  `Telephone` varchar(15) collate utf8_czech_ci default NULL,
  PRIMARY KEY  (`UserId`),
  CONSTRAINT `FK_userdata` FOREIGN KEY (`UserId`) REFERENCES `user_info` (`UserID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_czech_ci;

/*Table structure for table `user_goods` */

DROP TABLE IF EXISTS `user_goods`;

CREATE TABLE `user_goods` (
  `GoodsID` int(15) default NULL,
  `UserID` varchar(15) collate utf8_czech_ci default NULL,
  `Number` int(11) default NULL,
  KEY `FK_user_goods` (`GoodsID`),
  KEY `FK_user_info` (`UserID`),
  CONSTRAINT `FK_user_goods` FOREIGN KEY (`GoodsID`) REFERENCES `goods_info` (`GoodsID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_user_info` FOREIGN KEY (`UserID`) REFERENCES `user_info` (`UserID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_czech_ci;

/*Table structure for table `user_info` */

DROP TABLE IF EXISTS `user_info`;

CREATE TABLE `user_info` (
  `UserID` varchar(15) collate utf8_czech_ci NOT NULL default '',
  `Pwd` varchar(15) collate utf8_czech_ci NOT NULL default '',
  PRIMARY KEY  (`UserID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_czech_ci CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
