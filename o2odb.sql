/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 5.7.12-log : Database - o2odb
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`o2odb` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `o2odb`;

/*Table structure for table `sys_log_login` */

DROP TABLE IF EXISTS `sys_log_login`;

CREATE TABLE `sys_log_login` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `loginname` varchar(255) DEFAULT NULL,
  `loginip` varchar(255) DEFAULT NULL,
  `logintime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=217 DEFAULT CHARSET=utf8;

/*Data for the table `sys_log_login` */

/*Table structure for table `sys_menu` */

DROP TABLE IF EXISTS `sys_menu`;

CREATE TABLE `sys_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pid` int(11) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `href` varchar(255) DEFAULT NULL,
  `spread` int(255) DEFAULT NULL COMMENT '0不展开1展开',
  `target` varchar(255) DEFAULT NULL,
  `icon` varchar(255) DEFAULT NULL,
  `available` int(255) DEFAULT NULL COMMENT '0不可用1可用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

/*Data for the table `sys_menu` */

/*Table structure for table `sys_news` */

DROP TABLE IF EXISTS `sys_news`;

CREATE TABLE `sys_news` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL,
  `content` varchar(4000) DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  `opername` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

/*Data for the table `sys_news` */

/*Table structure for table `sys_role` */

DROP TABLE IF EXISTS `sys_role`;

CREATE TABLE `sys_role` (
  `roleid` int(11) NOT NULL AUTO_INCREMENT,
  `rolename` varchar(255) DEFAULT NULL,
  `roledesc` varchar(255) DEFAULT NULL,
  `available` int(11) DEFAULT NULL,
  PRIMARY KEY (`roleid`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `sys_role` */

/*Table structure for table `sys_role_user` */

DROP TABLE IF EXISTS `sys_role_user`;

CREATE TABLE `sys_role_user` (
  `uid` int(11) NOT NULL,
  `rid` int(11) NOT NULL,
  PRIMARY KEY (`uid`,`rid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sys_role_user` */

/*Table structure for table `sys_user` */

DROP TABLE IF EXISTS `sys_user`;

CREATE TABLE `sys_user` (
  `userid` int(11) NOT NULL AUTO_INCREMENT,
  `loginname` varchar(255) DEFAULT NULL,
  `identity` varchar(255) DEFAULT NULL,
  `realname` varchar(255) DEFAULT NULL,
  `sex` int(255) DEFAULT NULL COMMENT '0女1男',
  `address` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `pwd` varchar(255) DEFAULT NULL,
  `position` varchar(255) DEFAULT NULL,
  `type` int(255) DEFAULT '2' COMMENT '1，超级管理员,2，系统用户',
  `available` int(255) DEFAULT NULL,
  PRIMARY KEY (`userid`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

/*Data for the table `sys_user` */

insert  into `sys_user`(`userid`,`loginname`,`identity`,`realname`,`sex`,`address`,`phone`,`pwd`,`position`,`type`,`available`) values (8,'admin','店主','管理员',1,'南京','22222','e10adc3949ba59abbe56e057f20f883e','aa',2,1);

/*Table structure for table `tb_area` */

DROP TABLE IF EXISTS `tb_area`;

CREATE TABLE `tb_area` (
  `area_id` int(5) NOT NULL AUTO_INCREMENT,
  `area_name` varchar(200) NOT NULL,
  `area_desc` varchar(1000) DEFAULT NULL,
  `priority` int(2) NOT NULL DEFAULT '0',
  `create_time` datetime DEFAULT NULL,
  `last_edit_time` datetime DEFAULT NULL,
  PRIMARY KEY (`area_id`),
  UNIQUE KEY `UK_AREA` (`area_name`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

/*Data for the table `tb_area` */

insert  into `tb_area`(`area_id`,`area_name`,`area_desc`,`priority`,`create_time`,`last_edit_time`) values (3,'东苑','东苑',12,'2017-06-04 19:12:58','2017-06-04 19:12:58'),(4,'南苑','南苑',10,'2017-06-04 19:13:09','2017-06-04 19:13:09'),(5,'西苑','西苑',9,'2017-06-04 19:13:18','2017-06-04 19:13:18'),(6,'北苑','北苑',7,'2017-06-04 19:13:29','2017-06-04 19:13:29');

/*Table structure for table `tb_award` */

DROP TABLE IF EXISTS `tb_award`;

CREATE TABLE `tb_award` (
  `award_id` int(10) NOT NULL AUTO_INCREMENT,
  `award_name` varchar(256) COLLATE utf8_unicode_ci NOT NULL,
  `award_desc` varchar(1024) COLLATE utf8_unicode_ci DEFAULT NULL,
  `award_img` varchar(1024) COLLATE utf8_unicode_ci DEFAULT NULL,
  `point` int(10) NOT NULL DEFAULT '0',
  `priority` int(2) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `expire_time` datetime DEFAULT NULL,
  `last_edit_time` datetime DEFAULT NULL,
  `enable_status` int(2) NOT NULL DEFAULT '0',
  `shop_id` int(10) DEFAULT NULL,
  PRIMARY KEY (`award_id`),
  KEY `fk_award_shop_idx` (`shop_id`),
  CONSTRAINT `fk_award_shop` FOREIGN KEY (`shop_id`) REFERENCES `tb_shop` (`shop_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `tb_award` */

/*Table structure for table `tb_head_line` */

DROP TABLE IF EXISTS `tb_head_line`;

CREATE TABLE `tb_head_line` (
  `line_id` int(100) NOT NULL AUTO_INCREMENT,
  `line_name` varchar(1000) DEFAULT NULL,
  `line_link` varchar(2000) NOT NULL,
  `line_img` varchar(2000) NOT NULL,
  `priority` int(2) DEFAULT NULL,
  `enable_status` int(2) NOT NULL DEFAULT '0',
  `create_time` datetime DEFAULT NULL,
  `last_edit_time` datetime DEFAULT NULL,
  PRIMARY KEY (`line_id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

/*Data for the table `tb_head_line` */

insert  into `tb_head_line`(`line_id`,`line_name`,`line_link`,`line_img`,`priority`,`enable_status`,`create_time`,`last_edit_time`) values (11,'1','1','/upload/images/item/headtitle/2017061320315746624.jpg',1,1,'2017-06-13 20:31:57','2017-06-13 20:31:57'),(12,'2','2','/upload/images/item/headtitle/2017061320371786788.jpg',2,1,'2017-06-13 20:37:17','2017-06-13 20:37:17'),(14,'3','3','/upload/images/item/headtitle/2017061320393452772.jpg',3,1,'2017-06-13 20:39:34','2017-06-13 20:39:34'),(15,'4','4','/upload/images/item/headtitle/2017061320400198256.jpg',4,1,'2017-06-13 20:40:01','2017-06-13 20:40:01');

/*Table structure for table `tb_local_auth` */

DROP TABLE IF EXISTS `tb_local_auth`;

CREATE TABLE `tb_local_auth` (
  `local_auth_id` int(10) NOT NULL AUTO_INCREMENT,
  `user_id` int(10) DEFAULT NULL,
  `user_name` varchar(128) COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(128) COLLATE utf8_unicode_ci NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `last_edit_time` datetime DEFAULT NULL,
  PRIMARY KEY (`local_auth_id`),
  UNIQUE KEY `uk_local_profile` (`user_name`),
  KEY `fk_local_profile` (`user_id`),
  CONSTRAINT `fk_local_profile` FOREIGN KEY (`user_id`) REFERENCES `tb_person_info` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `tb_local_auth` */

insert  into `tb_local_auth`(`local_auth_id`,`user_id`,`user_name`,`password`,`create_time`,`last_edit_time`) values (6,8,'admin','e10adc3949ba59abbe56e057f20f883e','2017-06-04 19:09:51','2017-06-04 19:09:51'),(7,9,'test','e10adc3949ba59abbe56e057f20f883e','2017-06-05 22:05:13','2017-06-05 22:05:13'),(9,13,'aaaaa','111111','2019-08-28 14:24:47',NULL),(12,16,'bbbbbb','e10adc3949ba59abbe56e057f20f883e','2019-08-28 14:41:19',NULL);

/*Table structure for table `tb_log_info` */

DROP TABLE IF EXISTS `tb_log_info`;

CREATE TABLE `tb_log_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `loginname` varchar(255) DEFAULT NULL COMMENT '登录用户名',
  `loginip` varchar(255) DEFAULT NULL COMMENT '用户ip',
  `logintime` datetime DEFAULT NULL COMMENT '登录时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='登录日志表';

/*Data for the table `tb_log_info` */

insert  into `tb_log_info`(`id`,`loginname`,`loginip`,`logintime`) values (1,'admin-孙悟空','0:0:0:0:0:0:0:1','2019-12-25 09:16:07'),(2,'admin-孙悟空','0:0:0:0:0:0:0:1','2019-12-25 09:20:45'),(3,'admin-孙悟空','0:0:0:0:0:0:0:1','2019-12-25 09:39:16'),(4,'admin-孙悟空','0:0:0:0:0:0:0:1','2019-12-25 09:43:14'),(5,'admin-孙悟空','0:0:0:0:0:0:0:1','2019-12-25 09:45:54');

/*Table structure for table `tb_menu` */

DROP TABLE IF EXISTS `tb_menu`;

CREATE TABLE `tb_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '菜单id',
  `pid` int(11) DEFAULT NULL COMMENT '菜单父id',
  `title` varchar(255) DEFAULT NULL COMMENT '菜单名称',
  `href` varchar(255) DEFAULT NULL COMMENT '菜单链接',
  `spread` int(255) DEFAULT NULL COMMENT '0不展开1展开',
  `target` varchar(255) DEFAULT NULL COMMENT '菜单打开方式',
  `icon` varchar(255) DEFAULT NULL COMMENT '菜单图标',
  `available` int(255) DEFAULT NULL COMMENT '0不可用1可用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8 COMMENT='菜单表';

/*Data for the table `tb_menu` */

insert  into `tb_menu`(`id`,`pid`,`title`,`href`,`spread`,`target`,`icon`,`available`) values (1,0,'店铺管理系统',NULL,1,NULL,'&#xe68e;',1),(2,1,'店铺信息管理',NULL,1,NULL,'&#xe653;',1),(3,1,'业务管理',NULL,0,NULL,'&#xe663;',1),(4,1,'系统管理',NULL,0,NULL,'&#xe716;',1),(5,1,'统计分析',NULL,0,NULL,'&#xe629;',1),(6,2,'注册店铺','../shop/op/register.html',0,NULL,'&#xe770;',1),(7,2,'门店列表','../shop/op/storeList.html',0,NULL,'&#xe657;',1),(8,3,'类别管理','../shop/category/list.html',0,NULL,'&#xe65b;',1),(9,3,'商品管理','../shop/product/list.html',0,NULL,'&#xe6b2;',1),(10,3,'订单管理','../shop/order/list.html',0,NULL,'&#xe65a;',1),(11,4,'菜单管理','../sys/toMenuManager',0,NULL,'&#xe60f;',1),(12,4,'角色管理','../sys/toRoleManager',0,NULL,'&#xe66f;',1),(13,4,'用户管理','../sys/toUserManager',0,NULL,'&#xe770;',1),(14,4,'日志管理','../sys/toLogInfoManager',0,NULL,'&#xe655;',1),(15,4,'公告管理','../sys/toNewsManager',0,NULL,'&#xe645;',1),(16,4,'数据源监控','../druid/index.html',0,NULL,'&#xe857;',1),(17,5,'购买力Top5','../shop/chart/top5.html',0,NULL,'&#xe63c;',1),(18,5,'热销商品','../shop/chart/top5.html',0,NULL,'&#xe62c;',1),(19,5,'订单分布','../shop/chart/order.html',0,NULL,'&#xe62d;',1),(20,5,'交易额统计','../shop/chart/money.html',0,NULL,'&#xe62d;',1);

/*Table structure for table `tb_news` */

DROP TABLE IF EXISTS `tb_news`;

CREATE TABLE `tb_news` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL COMMENT '新闻id',
  `content` varchar(4000) DEFAULT NULL COMMENT '新闻内容',
  `createtime` datetime DEFAULT NULL COMMENT '创建时间',
  `opername` varchar(255) DEFAULT NULL COMMENT '创建人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='新闻表';

/*Data for the table `tb_news` */

/*Table structure for table `tb_order` */

DROP TABLE IF EXISTS `tb_order`;

CREATE TABLE `tb_order` (
  `order_id` varchar(32) NOT NULL,
  `buyer_id` varchar(32) NOT NULL COMMENT '买家id,信息从tb_person_info表中查',
  `order_amount` decimal(8,2) NOT NULL COMMENT '订单总金额',
  `order_status` tinyint(3) NOT NULL DEFAULT '0' COMMENT '订单状态, 默认为新下单',
  `pay_status` tinyint(3) NOT NULL DEFAULT '0' COMMENT '支付状态, 默认未支付',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tb_order` */

/*Table structure for table `tb_order_detail` */

DROP TABLE IF EXISTS `tb_order_detail`;

CREATE TABLE `tb_order_detail` (
  `detail_id` varchar(32) NOT NULL,
  `order_id` varchar(32) NOT NULL,
  `product_id` varchar(32) NOT NULL,
  `shop_id` int(10) NOT NULL COMMENT '所属店铺',
  `product_name` varchar(64) NOT NULL COMMENT '商品名称',
  `product_price` decimal(8,2) NOT NULL COMMENT '当前价格,单位分',
  `product_quantity` int(11) NOT NULL COMMENT '数量',
  `product_icon` varchar(512) DEFAULT NULL COMMENT '小图',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`detail_id`),
  KEY `idx_order_id` (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tb_order_detail` */

/*Table structure for table `tb_person_info` */

DROP TABLE IF EXISTS `tb_person_info`;

CREATE TABLE `tb_person_info` (
  `user_id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  `birthday` datetime DEFAULT NULL,
  `gender` varchar(2) COLLATE utf8_unicode_ci DEFAULT NULL,
  `phone` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  `email` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `profile_img` varchar(1024) COLLATE utf8_unicode_ci DEFAULT NULL,
  `customer_flag` int(2) NOT NULL DEFAULT '0',
  `shop_owner_flag` int(2) NOT NULL DEFAULT '0',
  `admin_flag` int(2) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `last_edit_time` datetime DEFAULT NULL,
  `enable_status` int(2) NOT NULL DEFAULT '0',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `tb_person_info` */

insert  into `tb_person_info`(`user_id`,`name`,`birthday`,`gender`,`phone`,`email`,`profile_img`,`customer_flag`,`shop_owner_flag`,`admin_flag`,`create_time`,`last_edit_time`,`enable_status`) values (8,'孙悟空',NULL,'1','333333',NULL,'http://wx.qlogo.cn/mmopen/XZumId0qMA815ApfWI2zibDnRMahic6SU0wHib2HgGJj5narL2ymRaI4Kn2Tx2Q8UfkicibvjVicu3De6fDYRMfo0uGW0SGicibxVnJ9/0',1,1,1,'2017-06-04 19:01:09','2017-06-04 19:01:09',1),(9,'龙州一条街客服',NULL,'1',NULL,NULL,'http://wx.qlogo.cn/mmopen/icF4iau8Sj7b0FiakC6ibBoTPmkvLpIX9YhWkNyEIGYfzYyqBiag2M3q2rnxSlXAh95UDHdWgywvEW5bN5FBzFPFazxBzqHTRqNwn/0',1,1,0,'2017-06-04 21:20:43','2017-06-04 21:20:43',1),(10,'king',NULL,'2',NULL,NULL,'http://wx.qlogo.cn/mmopen/XZumId0qMA815ApfWI2zibDLckaAaV6QgcBJP0saJSDTuicZBd35HzPXUebLPSlexCIPJsLs3w6lG0xmwn3EZNicj04dJh4We7C/0',1,1,0,'2017-06-07 01:36:16','2017-06-07 01:36:16',1),(11,'音策',NULL,'2',NULL,NULL,'http://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTKCWfIBicEwS3U0legxxQd5XFpZibBXVPyz0wphvvtaXqiblzQF2GqE28c7j8FGpuYqBCg1QRJThEzuw/0',1,1,0,'2017-09-18 23:39:38','2017-09-18 23:39:38',1),(13,NULL,NULL,NULL,NULL,NULL,NULL,0,1,0,'2019-08-28 14:24:47',NULL,0),(16,NULL,NULL,NULL,NULL,NULL,NULL,0,1,0,'2019-08-28 14:41:18',NULL,0);

/*Table structure for table `tb_phone_auth` */

DROP TABLE IF EXISTS `tb_phone_auth`;

CREATE TABLE `tb_phone_auth` (
  `phone_auth_id` int(10) NOT NULL AUTO_INCREMENT,
  `user_id` int(10) NOT NULL,
  `phone` varchar(128) COLLATE utf8_unicode_ci NOT NULL,
  `auth_number` varchar(128) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`phone_auth_id`),
  KEY `fk_phoneauth_profile` (`user_id`),
  CONSTRAINT `fk_phoneauth_profile` FOREIGN KEY (`user_id`) REFERENCES `tb_person_info` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `tb_phone_auth` */

/*Table structure for table `tb_product` */

DROP TABLE IF EXISTS `tb_product`;

CREATE TABLE `tb_product` (
  `product_id` int(100) NOT NULL AUTO_INCREMENT,
  `product_name` varchar(100) NOT NULL,
  `product_desc` varchar(2000) DEFAULT NULL,
  `img_addr` varchar(2000) DEFAULT '',
  `normal_price` varchar(100) DEFAULT NULL,
  `promotion_price` varchar(100) DEFAULT NULL,
  `priority` int(2) NOT NULL DEFAULT '0',
  `create_time` datetime DEFAULT NULL,
  `last_edit_time` datetime DEFAULT NULL,
  `enable_status` int(2) NOT NULL DEFAULT '0',
  `point` int(10) DEFAULT NULL,
  `product_category_id` int(11) DEFAULT NULL,
  `shop_id` int(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`product_id`),
  KEY `fk_product_shop` (`shop_id`),
  KEY `fk_product_procate` (`product_category_id`),
  CONSTRAINT `fk_product_procate` FOREIGN KEY (`product_category_id`) REFERENCES `tb_product_category` (`product_category_id`),
  CONSTRAINT `fk_product_shop` FOREIGN KEY (`shop_id`) REFERENCES `tb_shop` (`shop_id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

/*Data for the table `tb_product` */

insert  into `tb_product`(`product_id`,`product_name`,`product_desc`,`img_addr`,`normal_price`,`promotion_price`,`priority`,`create_time`,`last_edit_time`,`enable_status`,`point`,`product_category_id`,`shop_id`) values (4,'美式咖啡','一丝醇香，流连忘返','https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1567329163918&di=8b1142db6b80c1eeb58a62a850da883e&imgtype=0&src=http%3A%2F%2Fimg2.ph.126.net%2F2zB3_wWPXlEW0RdwQa8d6A%3D%3D%2F2268688312388037455.jpg','12','11',12,'2017-06-05 23:30:21','2017-06-05 23:49:34',1,3,10,15),(5,'转让八成新XX牌小车','诚心转让八成新XX牌小车，有意者请连续8866666','https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1567329163918&di=393671940e0ab4e80132c3a36c050fc6&imgtype=0&src=http%3A%2F%2Fd-pic-image.yesky.com%2F1080x-%2FuploadImages%2F2019%2F044%2F59%2F1113V6L3Q6TY.jpg','100000','60000',100,'2017-06-05 23:48:52','2017-06-05 23:48:52',1,0,9,15),(6,'转让电瓶车一辆','转让电瓶车一辆，可当面看车，电话：1111222','https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1567329163917&di=4d224963399006bae1c4c55ca7ae35fa&imgtype=0&src=http%3A%2F%2Fpic27.nipic.com%2F20130314%2F11899688_192542628000_2.jpg','3000','1200',99,'2017-06-06 08:49:01','2017-06-06 08:50:57',1,0,9,15),(7,'转让半新旧男装摩托车一辆','转让半新旧男装摩托车一辆，当面验车，电话：3333666','/upload/images/item/shop/15/2017060608502085437.jpg','8000','3000',98,'2017-06-06 08:50:20','2017-06-06 08:51:19',1,0,9,15),(8,'大量二手书籍转让','大量二手书籍转让，电话详谈，或上门看书。联系电话：5556666   地址：东苑XX楼','/upload/images/item/shop/16/2017060608574074561.jpg','0','0',100,'2017-06-06 08:57:40','2017-06-06 08:57:40',1,0,10,16),(9,'<十万个为什么>','出手一本《十万个为什么》，8成新，想要的可以联系：9998886','/upload/images/item/shop/16/2017060609025850665.png','25','10',98,'2017-06-06 09:02:58','2017-06-06 09:02:58',1,0,10,16),(10,'珍珠奶茶','珍珠奶茶，弹性十足，香甜美味。','/upload/images/item/shop/20/2017060620114126875.jpg','10','8',100,'2017-06-06 20:11:41','2017-06-06 20:11:41',1,0,11,20),(11,'红豆奶茶','红豆和奶茶的完美结合，夏天不错的选择。','/upload/images/item/shop/20/2017060620363014331.jpg','10','8',99,'2017-06-06 20:36:30','2017-06-06 20:36:30',1,1,11,20),(12,'绿豆冰','清热解毒。','/upload/images/item/shop/20/2017060620384620536.jpg','8','7',98,'2017-06-06 20:38:46','2017-06-06 20:38:46',1,0,11,20),(13,'芒果冰沙','新鲜芒果制作。','/upload/images/item/shop/20/2017060620472125629.jpg','15','13',95,'2017-06-06 20:47:21','2017-06-06 20:47:21',1,2,11,20),(14,'鲜榨芒果汁','新鲜芒果新鲜榨，香甜可口，解暑降温。','/upload/images/item/shop/20/2017060620492297296.jpg','8','8',93,'2017-06-06 20:49:22','2017-06-06 20:49:22',0,0,11,20),(15,'鲜榨西瓜汁','每一杯都是鲜榨的，现榨现卖。','/upload/images/item/shop/20/2017060621052824735.jpg','8','8',90,'2018-06-06 21:05:28','2018-06-06 21:05:28',0,0,11,20);

/*Table structure for table `tb_product_category` */

DROP TABLE IF EXISTS `tb_product_category`;

CREATE TABLE `tb_product_category` (
  `product_category_id` int(11) NOT NULL AUTO_INCREMENT,
  `product_category_name` varchar(100) NOT NULL,
  `product_category_desc` varchar(500) DEFAULT NULL,
  `priority` int(2) DEFAULT '0',
  `create_time` datetime DEFAULT NULL,
  `last_edit_time` datetime DEFAULT NULL,
  `shop_id` int(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`product_category_id`),
  KEY `fk_procate_shop` (`shop_id`),
  CONSTRAINT `fk_procate_shop` FOREIGN KEY (`shop_id`) REFERENCES `tb_shop` (`shop_id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

/*Data for the table `tb_product_category` */

insert  into `tb_product_category`(`product_category_id`,`product_category_name`,`product_category_desc`,`priority`,`create_time`,`last_edit_time`,`shop_id`) values (9,'二手车',NULL,100,NULL,NULL,15),(10,'二手书籍',NULL,100,NULL,NULL,16),(11,'奶茶',NULL,100,NULL,NULL,20),(12,'咖啡',NULL,50,NULL,NULL,20),(13,'甜品',NULL,30,NULL,NULL,20),(14,'小吃',NULL,20,NULL,NULL,20),(15,'茗茶',NULL,10,NULL,NULL,20);

/*Table structure for table `tb_product_img` */

DROP TABLE IF EXISTS `tb_product_img`;

CREATE TABLE `tb_product_img` (
  `product_img_id` int(20) NOT NULL AUTO_INCREMENT,
  `img_addr` varchar(2000) NOT NULL,
  `img_desc` varchar(2000) DEFAULT NULL,
  `priority` int(2) DEFAULT '0',
  `create_time` datetime DEFAULT NULL,
  `product_id` int(20) DEFAULT NULL,
  PRIMARY KEY (`product_img_id`),
  KEY `fk_proimg_product` (`product_id`),
  CONSTRAINT `fk_proimg_product` FOREIGN KEY (`product_id`) REFERENCES `tb_product` (`product_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8;

/*Data for the table `tb_product_img` */

insert  into `tb_product_img`(`product_img_id`,`img_addr`,`img_desc`,`priority`,`create_time`,`product_id`) values (19,'/upload/images/item/shop/15/20170605233021865310.jpg',NULL,NULL,'2017-06-05 23:30:22',4),(20,'/upload/images/item/shop/15/20170605233022618071.jpg',NULL,NULL,'2017-06-05 23:30:22',4),(21,'/upload/images/item/shop/15/20170605233022246642.jpg',NULL,NULL,'2017-06-05 23:30:22',4),(22,'/upload/images/item/shop/15/20170605234852321010.jpg',NULL,NULL,'2017-06-05 23:48:52',5),(23,'/upload/images/item/shop/15/20170606084902162950.jpg',NULL,NULL,'2017-06-06 08:49:02',6),(24,'/upload/images/item/shop/15/20170606085020558290.jpg',NULL,NULL,'2017-06-06 08:50:20',7),(25,'/upload/images/item/shop/16/20170606085740956160.jpg',NULL,NULL,'2017-06-06 08:57:40',8),(26,'/upload/images/item/shop/16/20170606090259397060.png',NULL,NULL,'2017-06-06 09:02:59',9),(27,'/upload/images/item/shop/20/20170606201141425050.jpg',NULL,NULL,'2017-06-06 20:11:42',10),(28,'/upload/images/item/shop/20/20170606201141387851.jpg',NULL,NULL,'2017-06-06 20:11:42',10),(29,'/upload/images/item/shop/20/20170606201141503752.png',NULL,NULL,'2017-06-06 20:11:42',10),(30,'/upload/images/item/shop/20/20170606203630923430.jpg',NULL,NULL,'2017-06-06 20:36:31',11),(31,'/upload/images/item/shop/20/20170606203631552081.png',NULL,NULL,'2017-06-06 20:36:31',11),(32,'/upload/images/item/shop/20/20170606203631972862.jpg',NULL,NULL,'2017-06-06 20:36:31',11),(33,'/upload/images/item/shop/20/20170606203846623120.jpg',NULL,NULL,'2017-06-06 20:38:47',12),(34,'/upload/images/item/shop/20/20170606204721744860.jpg',NULL,NULL,'2017-06-06 20:47:21',13),(35,'/upload/images/item/shop/20/20170606204922968580.jpg',NULL,NULL,'2017-06-06 20:49:23',14),(36,'/upload/images/item/shop/20/20170606210528529220.jpg',NULL,NULL,'2017-06-06 21:05:28',15),(37,'/upload/images/item/shop/20/20170606210528132921.jpg',NULL,NULL,'2017-06-06 21:05:28',15);

/*Table structure for table `tb_role` */

DROP TABLE IF EXISTS `tb_role`;

CREATE TABLE `tb_role` (
  `roleid` int(11) NOT NULL AUTO_INCREMENT COMMENT '角色id',
  `rolename` varchar(255) DEFAULT NULL COMMENT '角色名称',
  `roledesc` varchar(255) DEFAULT NULL COMMENT '角色描述',
  `available` int(11) DEFAULT NULL COMMENT '是否可用 0不可用 1可用',
  PRIMARY KEY (`roleid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='角色表';

/*Data for the table `tb_role` */

insert  into `tb_role`(`roleid`,`rolename`,`roledesc`,`available`) values (1,'管理员','店主',1);

/*Table structure for table `tb_role_menu` */

DROP TABLE IF EXISTS `tb_role_menu`;

CREATE TABLE `tb_role_menu` (
  `rid` int(11) NOT NULL COMMENT '角色id',
  `mid` int(11) NOT NULL COMMENT '菜单id',
  PRIMARY KEY (`rid`,`mid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色菜单表';

/*Data for the table `tb_role_menu` */

insert  into `tb_role_menu`(`rid`,`mid`) values (1,1),(1,2),(1,3),(1,4),(1,5),(1,6),(1,7),(1,8),(1,9),(1,10),(1,11),(1,12),(1,13),(1,14),(1,15),(1,16),(1,17),(1,18),(1,19),(1,20);

/*Table structure for table `tb_role_user` */

DROP TABLE IF EXISTS `tb_role_user`;

CREATE TABLE `tb_role_user` (
  `uid` int(11) NOT NULL COMMENT '用户id',
  `rid` int(11) NOT NULL COMMENT '角色id',
  PRIMARY KEY (`uid`,`rid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色用户表';

/*Data for the table `tb_role_user` */

insert  into `tb_role_user`(`uid`,`rid`) values (1,1);

/*Table structure for table `tb_shop` */

DROP TABLE IF EXISTS `tb_shop`;

CREATE TABLE `tb_shop` (
  `shop_id` int(10) NOT NULL AUTO_INCREMENT,
  `owner_id` int(10) NOT NULL COMMENT '店铺创建人',
  `area_id` int(5) DEFAULT NULL,
  `shop_category_id` int(11) DEFAULT NULL,
  `parent_category_id` int(11) DEFAULT NULL,
  `shop_name` varchar(256) COLLATE utf8_unicode_ci NOT NULL,
  `shop_desc` varchar(1024) COLLATE utf8_unicode_ci DEFAULT NULL,
  `shop_addr` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  `phone` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `shop_img` varchar(1024) COLLATE utf8_unicode_ci DEFAULT NULL,
  `longitude` double(16,12) DEFAULT NULL,
  `latitude` double(16,12) DEFAULT NULL,
  `priority` int(3) DEFAULT '0',
  `create_time` datetime DEFAULT NULL,
  `last_edit_time` datetime DEFAULT NULL,
  `enable_status` int(2) NOT NULL DEFAULT '0',
  `advice` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`shop_id`),
  KEY `fk_shop_profile` (`owner_id`),
  KEY `fk_shop_area` (`area_id`),
  KEY `fk_shop_shopcate` (`shop_category_id`),
  KEY `fk_shop_parentcate` (`parent_category_id`),
  CONSTRAINT `fk_shop_area` FOREIGN KEY (`area_id`) REFERENCES `tb_area` (`area_id`),
  CONSTRAINT `fk_shop_parentcate` FOREIGN KEY (`parent_category_id`) REFERENCES `tb_shop_category` (`shop_category_id`),
  CONSTRAINT `fk_shop_profile` FOREIGN KEY (`owner_id`) REFERENCES `tb_person_info` (`user_id`),
  CONSTRAINT `fk_shop_shopcate` FOREIGN KEY (`shop_category_id`) REFERENCES `tb_shop_category` (`shop_category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `tb_shop` */

insert  into `tb_shop`(`shop_id`,`owner_id`,`area_id`,`shop_category_id`,`parent_category_id`,`shop_name`,`shop_desc`,`shop_addr`,`phone`,`shop_img`,`longitude`,`latitude`,`priority`,`create_time`,`last_edit_time`,`enable_status`,`advice`) values (15,8,3,14,10,'二手车辆','二手汽车、摩托车、电车等交通工具交易信息。','面向全市','0000000','/upload/images/item/shop/15/2017060522042982266.png',NULL,NULL,100,'2017-06-05 22:04:29','2017-08-25 10:50:16',1,NULL),(16,8,3,15,10,'旧书籍交易','旧书籍交易信息','旧书籍交易板块','0000000','/upload/images/item/shop/16/2017060608534289617.png',NULL,NULL,99,'2017-06-06 08:53:42','2017-06-06 08:54:40',1,NULL),(17,8,3,17,11,'靓仔靓妹美容护理中心','二十年手艺，专业护理秀发受损头发。美容美发首选。','东苑北面二号门','4445556','/upload/images/item/shop/17/2017060609084595067.jpg',NULL,NULL,0,'2017-06-06 09:08:45','2017-06-06 09:45:32',1,NULL),(18,8,3,18,11,'一剪没理发中心','专业洗剪吹，又好又便宜。','东苑北面3号门面','9998887','/upload/images/item/shop/18/2017060609110899956.jpg',NULL,NULL,0,'2017-06-06 09:11:08','2017-06-06 09:45:38',1,NULL),(19,8,4,20,12,'吃得饱大排档','吃得好又吃得饱，朋友聚会好地方。可预约。','南苑东面10号门面','1234567','/upload/images/item/shop/19/2017060609140699548.jpg',NULL,NULL,0,'2017-06-06 09:14:06','2017-06-06 09:45:43',1,NULL),(20,8,4,22,12,'香喷喷奶茶店','鲜榨果汁、奶茶等饮品。','南苑东面5号门面','77788444','/upload/images/item/shop/20/2017060609163395401.jpg',NULL,NULL,30,'2017-06-06 09:16:33','2017-06-07 16:24:07',1,'\"\"'),(21,8,5,25,13,'海陆空量贩KTV','订包厢电话：8889997。节假日请预约。','西苑1号门面','8889997','/upload/images/item/shop/21/2017060609194286080.jpg',NULL,NULL,0,'2017-06-06 09:19:42','2017-06-06 09:45:59',1,NULL),(22,8,5,24,13,'幽城室逃生娱乐城','考验你的智商，和小伙伴们一起来挑战吧。','西苑3号楼第二层','6666333','/upload/images/item/shop/22/2017060609223853062.jpg',NULL,NULL,0,'2017-06-06 09:22:38','2017-06-06 09:46:04',1,NULL),(23,8,6,29,27,'威水程序设计培训教育','保教抱会，前途无量。','北苑2栋5楼','66633111','/upload/images/item/shop/23/2017060609275777519.png',NULL,NULL,0,'2017-06-06 09:27:57','2017-06-06 09:46:09',1,NULL),(24,8,6,30,27,'武林风舞蹈培训','专业培训舞蹈，声乐。','北苑9懂10楼','5555555','/upload/images/item/shop/24/2017060609354459045.png',NULL,NULL,0,'2017-06-06 09:35:44','2017-06-06 09:46:13',1,NULL),(25,8,6,14,28,'易行交通工具租赁服务中心','本店租赁各种汽车，摩托车等。详情请拨打电话咨询。电话：2222222','1栋3号4号门面','2222222','/upload/images/item/shop/25/2017060609381150709.png',NULL,NULL,40,'2017-06-06 09:38:11','2017-06-06 19:58:32',1,NULL),(26,8,6,31,28,'有声有色','出租各种演出道具，乐器，服装等。','北苑15号门面','7777777','/upload/images/item/shop/26/2017060609431259039.png',NULL,NULL,41,'2017-06-06 09:43:12','2017-06-06 19:58:45',1,NULL),(27,8,3,22,12,'冰冻夏天奶茶店','本店出售各种冷饮，奶茶，冰花，鲜榨果汁。','东苑7懂2号门面','8889999','/upload/images/item/shop/27/2017060715512185473.jpg',NULL,NULL,10,'2017-06-07 15:51:21','2017-06-07 16:22:28',1,'\"\"'),(28,9,3,14,10,'test','dfafaf','sdafafafa','3424242','/upload/images/item/shop/28/2017082500103690946.png',NULL,NULL,0,'2017-08-25 00:10:36','2017-08-25 00:10:36',0,NULL);

/*Table structure for table `tb_shop_auth_map` */

DROP TABLE IF EXISTS `tb_shop_auth_map`;

CREATE TABLE `tb_shop_auth_map` (
  `shop_auth_id` int(10) NOT NULL AUTO_INCREMENT,
  `employee_id` int(10) NOT NULL,
  `shop_id` int(10) NOT NULL,
  `name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '冗余是为了让shop在查找员工的时候，不需要去连tb_shop表，直接连tb_shop_auth_map就okay',
  `title` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `title_flag` int(2) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `last_edit_time` datetime DEFAULT NULL,
  `enable_status` int(10) NOT NULL DEFAULT '0',
  PRIMARY KEY (`shop_auth_id`),
  KEY `fk_shop_auth_map_shop` (`shop_id`),
  KEY `uk_shop_auth_map` (`employee_id`,`shop_id`),
  CONSTRAINT `fk_shop_auth_map_employee` FOREIGN KEY (`employee_id`) REFERENCES `tb_person_info` (`user_id`),
  CONSTRAINT `fk_shop_auth_map_shop` FOREIGN KEY (`shop_id`) REFERENCES `tb_shop` (`shop_id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `tb_shop_auth_map` */

insert  into `tb_shop_auth_map`(`shop_auth_id`,`employee_id`,`shop_id`,`name`,`title`,`title_flag`,`create_time`,`last_edit_time`,`enable_status`) values (13,8,15,'','店家本人',1,'2017-06-05 22:04:30','2017-06-05 22:04:30',1),(14,8,16,'','店家本人',1,'2017-06-06 08:53:42','2017-06-06 08:53:42',1),(15,8,17,'','店家本人',1,'2017-06-06 09:08:45','2017-06-06 09:08:45',1),(16,8,18,'','店家本人',1,'2017-06-06 09:11:09','2017-06-06 09:11:09',1),(17,8,19,'','店家本人',1,'2017-06-06 09:14:06','2017-06-06 09:14:06',1),(18,8,21,'','店家本人',1,'2017-06-06 09:16:33','2017-06-06 09:16:33',1),(19,8,21,'','店家本人',1,'2017-06-06 09:19:42','2017-06-06 09:19:42',1),(20,8,22,'','店家本人',1,'2017-06-06 09:22:38','2017-06-06 09:22:38',1),(21,8,23,'','店家本人',1,'2017-06-06 09:27:57','2017-06-06 09:27:57',1),(22,8,24,'','店家本人',1,'2017-06-06 09:35:44','2017-06-06 09:35:44',1),(23,8,25,'','店家本人',1,'2017-06-06 09:38:11','2017-06-06 09:38:11',1),(24,8,26,'','店家本人',1,'2017-06-06 09:43:13','2017-06-06 09:43:13',1),(25,8,27,'','店家本人',1,'2017-06-07 15:51:21','2017-06-07 15:51:21',1),(26,9,28,'','店家本人',1,'2017-08-25 00:10:36','2017-08-25 00:10:36',1);

/*Table structure for table `tb_shop_category` */

DROP TABLE IF EXISTS `tb_shop_category`;

CREATE TABLE `tb_shop_category` (
  `shop_category_id` int(11) NOT NULL AUTO_INCREMENT,
  `shop_category_name` varchar(100) NOT NULL DEFAULT '',
  `shop_category_desc` varchar(1000) DEFAULT '',
  `shop_category_img` varchar(2000) DEFAULT NULL,
  `priority` int(2) NOT NULL DEFAULT '0',
  `create_time` datetime DEFAULT NULL,
  `last_edit_time` datetime DEFAULT NULL,
  `parent_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`shop_category_id`),
  KEY `fk_shop_category_self` (`parent_id`),
  CONSTRAINT `fk_shop_category_self` FOREIGN KEY (`parent_id`) REFERENCES `tb_shop_category` (`shop_category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8;

/*Data for the table `tb_shop_category` */

insert  into `tb_shop_category`(`shop_category_id`,`shop_category_name`,`shop_category_desc`,`shop_category_img`,`priority`,`create_time`,`last_edit_time`,`parent_id`) values (10,'二手市场','二手商品交易','/upload/images/item/shopcategory/2017061223272255687.png',100,'2017-06-04 20:10:58','2017-06-12 23:27:22',NULL),(11,'美容美发','美容美发','/upload/images/item/shopcategory/2017061223273314635.png',99,'2017-06-04 20:12:57','2017-06-12 23:27:33',NULL),(12,'美食饮品','美食饮品','/upload/images/item/shopcategory/2017061223274213433.png',98,'2017-06-04 20:15:21','2017-06-12 23:27:42',NULL),(13,'休闲娱乐','休闲娱乐','/upload/images/item/shopcategory/2017061223275121460.png',97,'2017-06-04 20:19:29','2017-06-12 23:27:51',NULL),(14,'旧车','旧车','/upload/images/item/shopcategory/2017060420315183203.png',80,'2017-06-04 20:31:51','2017-06-04 20:31:51',10),(15,'二手书籍','二手书籍','/upload/images/item/shopcategory/2017060420322333745.png',79,'2017-06-04 20:32:23','2017-06-04 20:32:23',10),(17,'护理','护理','/upload/images/item/shopcategory/2017060420372391702.png',76,'2017-06-04 20:37:23','2017-06-04 20:37:23',11),(18,'理发','理发','/upload/images/item/shopcategory/2017060420374775350.png',74,'2017-06-04 20:37:47','2017-06-04 20:37:47',11),(20,'大排档','大排档','/upload/images/item/shopcategory/2017060420460491494.png',59,'2017-06-04 20:46:04','2017-06-04 20:46:04',12),(22,'奶茶店','奶茶店','/upload/images/item/shopcategory/2017060420464594520.png',58,'2017-06-04 20:46:45','2017-06-04 20:46:45',12),(24,'密室逃生','密室逃生','/upload/images/item/shopcategory/2017060420500783376.png',56,'2017-06-04 20:50:07','2017-06-04 21:45:53',13),(25,'KTV','KTV','/upload/images/item/shopcategory/2017060420505834244.png',57,'2017-06-04 20:50:58','2017-06-04 20:51:14',13),(27,'培训教育','培训教育','/upload/images/item/shopcategory/2017061223280082147.png',96,'2017-06-04 21:51:36','2017-06-12 23:28:00',NULL),(28,'租赁市场','租赁市场','/upload/images/item/shopcategory/2017061223281361578.png',95,'2017-06-04 21:53:52','2017-06-12 23:28:13',NULL),(29,'程序设计','程序设计','/upload/images/item/shopcategory/2017060421593496807.png',50,'2017-06-04 21:59:34','2017-06-04 21:59:34',27),(30,'声乐舞蹈','声乐舞蹈','/upload/images/item/shopcategory/2017060421595843693.png',49,'2017-06-04 21:59:58','2017-06-04 21:59:58',27),(31,'演出道具','演出道具','/upload/images/item/shopcategory/2017060422114076152.png',45,'2017-06-04 22:11:40','2017-06-04 22:11:40',28),(32,'交通工具','交通工具','/upload/images/item/shopcategory/2017060422121144586.png',44,'2017-06-04 22:12:11','2017-06-04 22:12:11',28);

/*Table structure for table `tb_user_award_map` */

DROP TABLE IF EXISTS `tb_user_award_map`;

CREATE TABLE `tb_user_award_map` (
  `user_award_id` int(10) NOT NULL AUTO_INCREMENT,
  `user_id` int(10) NOT NULL,
  `award_id` int(10) NOT NULL,
  `shop_id` int(10) NOT NULL,
  `user_name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `award_name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `expire_time` datetime DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `used_status` int(2) NOT NULL DEFAULT '0',
  `point` int(10) DEFAULT NULL,
  PRIMARY KEY (`user_award_id`),
  KEY `fk_user_award_map_profile` (`user_id`),
  KEY `fk_user_award_map_award` (`award_id`),
  KEY `fk_user_award_map_shop` (`shop_id`),
  CONSTRAINT `fk_user_award_map_award` FOREIGN KEY (`award_id`) REFERENCES `tb_award` (`award_id`),
  CONSTRAINT `fk_user_award_map_profile` FOREIGN KEY (`user_id`) REFERENCES `tb_person_info` (`user_id`),
  CONSTRAINT `fk_user_award_map_shop` FOREIGN KEY (`shop_id`) REFERENCES `tb_shop` (`shop_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `tb_user_award_map` */

/*Table structure for table `tb_user_product_map` */

DROP TABLE IF EXISTS `tb_user_product_map`;

CREATE TABLE `tb_user_product_map` (
  `user_product_id` int(30) NOT NULL AUTO_INCREMENT,
  `user_id` int(10) DEFAULT NULL,
  `product_id` int(100) DEFAULT NULL,
  `shop_id` int(10) DEFAULT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  `product_name` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `point` int(10) DEFAULT '0',
  PRIMARY KEY (`user_product_id`),
  KEY `fk_user_product_map_profile` (`user_id`),
  KEY `fk_user_product_map_product` (`product_id`),
  KEY `fk_user_product_map_shop` (`shop_id`),
  CONSTRAINT `fk_user_product_map_product` FOREIGN KEY (`product_id`) REFERENCES `tb_product` (`product_id`),
  CONSTRAINT `fk_user_product_map_profile` FOREIGN KEY (`user_id`) REFERENCES `tb_person_info` (`user_id`),
  CONSTRAINT `fk_user_product_map_shop` FOREIGN KEY (`shop_id`) REFERENCES `tb_shop` (`shop_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tb_user_product_map` */

/*Table structure for table `tb_user_shop_map` */

DROP TABLE IF EXISTS `tb_user_shop_map`;

CREATE TABLE `tb_user_shop_map` (
  `user_shop_id` int(30) NOT NULL AUTO_INCREMENT,
  `user_id` int(10) NOT NULL,
  `shop_id` int(10) NOT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  `shop_name` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `point` int(10) DEFAULT NULL,
  PRIMARY KEY (`user_shop_id`),
  UNIQUE KEY `uq_user_shop` (`user_id`,`shop_id`),
  KEY `fk_user_shop_shop` (`shop_id`),
  CONSTRAINT `fk_user_shop_shop` FOREIGN KEY (`shop_id`) REFERENCES `tb_shop` (`shop_id`),
  CONSTRAINT `fk_user_shop_user` FOREIGN KEY (`user_id`) REFERENCES `tb_person_info` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tb_user_shop_map` */

/*Table structure for table `tb_wechat_auth` */

DROP TABLE IF EXISTS `tb_wechat_auth`;

CREATE TABLE `tb_wechat_auth` (
  `wechat_auth_id` int(10) NOT NULL AUTO_INCREMENT,
  `user_id` int(10) NOT NULL,
  `open_id` varchar(512) COLLATE utf8_unicode_ci NOT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`wechat_auth_id`),
  KEY `fk_oauth_profile` (`user_id`),
  KEY `uk_oauth` (`open_id`(255)),
  CONSTRAINT `fk_oauth_profile` FOREIGN KEY (`user_id`) REFERENCES `tb_person_info` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `tb_wechat_auth` */

insert  into `tb_wechat_auth`(`wechat_auth_id`,`user_id`,`open_id`,`create_time`) values (4,8,'ovLbns-gxJHqC-UTPQKvgEuENl-E','2017-06-04 19:01:09'),(5,9,'ovLbns9oD5K4g712TW63dgSHxC3o','2017-06-04 21:20:43'),(6,10,'ovLbnsz16NtYSt2bCoJktXOGlzyg','2017-06-07 01:36:16'),(7,11,'ovLbns4Z7ueIBJNmgVfpDTQQLCRA','2017-09-18 23:39:38');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
