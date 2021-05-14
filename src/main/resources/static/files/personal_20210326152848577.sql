/*
SQLyog Ultimate v10.00 Beta1
MySQL - 5.7.27-log : Database - personal
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`personal` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `personal`;

/*Table structure for table `business_inf` */

DROP TABLE IF EXISTS `business_inf`;

CREATE TABLE `business_inf` (
  `id` int(11) NOT NULL,
  `mailmarketing` int(11) DEFAULT NULL,
  `allianceadvertising` int(11) DEFAULT NULL,
  `videoadvertising` int(11) DEFAULT NULL,
  `directaccess` int(11) DEFAULT NULL,
  `searchengine` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `business_inf` */

insert  into `business_inf`(`id`,`mailmarketing`,`allianceadvertising`,`videoadvertising`,`directaccess`,`searchengine`) values (1,120,220,150,320,820),(2,132,182,232,332,932),(3,101,191,201,301,901),(4,134,234,154,334,934),(5,90,290,190,390,1290),(6,230,330,330,330,1330),(7,210,310,410,320,1340);

/*Table structure for table `checkwork_inf` */

DROP TABLE IF EXISTS `checkwork_inf`;

CREATE TABLE `checkwork_inf` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `DEPT_ID` int(11) NOT NULL,
  `JOB_ID` int(11) NOT NULL,
  `EMP_ID` int(11) NOT NULL,
  `WORKINGDAYS` int(18) NOT NULL,
  `DAYSLEAVE` int(50) NOT NULL,
  `CREATEDATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `DAYSOUT` int(20) DEFAULT NULL,
  PRIMARY KEY (`ID`,`EMP_ID`),
  KEY `FK_EMP_DEPT` (`DEPT_ID`),
  KEY `FK_EMP_JOB` (`JOB_ID`),
  KEY `ID` (`ID`),
  KEY `checkwork_inf_ibfk_3` (`EMP_ID`),
  CONSTRAINT `che_1` FOREIGN KEY (`DEPT_ID`) REFERENCES `employee_inf` (`dept_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `che_2` FOREIGN KEY (`JOB_ID`) REFERENCES `employee_inf` (`job_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `che_3` FOREIGN KEY (`EMP_ID`) REFERENCES `employee_inf` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

/*Data for the table `checkwork_inf` */

insert  into `checkwork_inf`(`ID`,`DEPT_ID`,`JOB_ID`,`EMP_ID`,`WORKINGDAYS`,`DAYSLEAVE`,`CREATEDATE`,`DAYSOUT`) values (5,3,9,5,25,2,'2019-05-08 10:38:11',2);

/*Table structure for table `code_inf` */

DROP TABLE IF EXISTS `code_inf`;

CREATE TABLE `code_inf` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) DEFAULT NULL,
  `createdate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=66 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

/*Data for the table `code_inf` */

insert  into `code_inf`(`id`,`code`,`createdate`) values (1,'KPHMYKTKT','2019-03-29 14:25:14'),(2,'PBB7KUYBT','2019-03-29 16:47:29'),(3,'9I2AN4D3W','2019-03-29 16:47:37'),(4,'WLMGU4RZH','2019-03-29 17:01:09'),(10,'QNTGMEIXV','2019-04-04 19:34:59');

/*Table structure for table `completion_inf` */

DROP TABLE IF EXISTS `completion_inf`;

CREATE TABLE `completion_inf` (
  `id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

/*Data for the table `completion_inf` */

insert  into `completion_inf`(`id`,`name`) values (1,'优秀'),(2,'良好'),(3,'不合格');

/*Table structure for table `confidentialitycontract_inf` */

DROP TABLE IF EXISTS `confidentialitycontract_inf`;

CREATE TABLE `confidentialitycontract_inf` (
  `id` int(255) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

/*Data for the table `confidentialitycontract_inf` */

insert  into `confidentialitycontract_inf`(`id`,`name`) values (1,'已签'),(2,'未签');

/*Table structure for table `contract_inf` */

DROP TABLE IF EXISTS `contract_inf`;

CREATE TABLE `contract_inf` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `DEPT_ID` int(11) NOT NULL,
  `JOB_ID` int(11) NOT NULL,
  `STATUS_ID` int(11) DEFAULT '0',
  `EMP_ID` int(11) NOT NULL,
  `TRAIN_CONTRACT` int(18) NOT NULL,
  `LABOR_CONTRACT` int(50) NOT NULL,
  `CREATE_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `CONFIDENTIALITY_CONTRACT` int(20) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_EMP_DEPT` (`DEPT_ID`) USING BTREE,
  KEY `FK_EMP_JOB` (`JOB_ID`) USING BTREE,
  KEY `ID` (`ID`) USING BTREE,
  KEY `checkwork_inf_ibfk_3` (`EMP_ID`) USING BTREE,
  CONSTRAINT `con_1` FOREIGN KEY (`DEPT_ID`) REFERENCES `employee_inf` (`dept_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `con_2` FOREIGN KEY (`JOB_ID`) REFERENCES `employee_inf` (`job_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `con_3` FOREIGN KEY (`EMP_ID`) REFERENCES `employee_inf` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

/*Data for the table `contract_inf` */

insert  into `contract_inf`(`ID`,`DEPT_ID`,`JOB_ID`,`STATUS_ID`,`EMP_ID`,`TRAIN_CONTRACT`,`LABOR_CONTRACT`,`CREATE_DATE`,`CONFIDENTIALITY_CONTRACT`) values (5,3,9,0,5,2,1,'2019-05-08 10:40:04',2),(6,3,1,0,11,1,1,'2020-07-29 09:31:20',1);

/*Table structure for table `dept_inf` */

DROP TABLE IF EXISTS `dept_inf`;

CREATE TABLE `dept_inf` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `remark` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

/*Data for the table `dept_inf` */

insert  into `dept_inf`(`id`,`name`,`remark`) values (1,'技术部','技术研发'),(2,'运营部','运营部'),(3,'财务部','财务分析与统计计划'),(5,'总公办','总公办'),(6,'市场部','市场部'),(7,'教学部','教学部'),(13,'网商公司二','网商部网商部二'),(14,'对对对','颠三倒四多');

/*Table structure for table `document_inf` */

DROP TABLE IF EXISTS `document_inf`;

CREATE TABLE `document_inf` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(50) DEFAULT NULL,
  `filename` varchar(300) DEFAULT NULL,
  `remark` varchar(300) DEFAULT NULL,
  `createdate` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;

/*Data for the table `document_inf` */

insert  into `document_inf`(`id`,`title`,`filename`,`remark`,`createdate`,`user_id`) values (1,'实习协议','实习协议.doc','实习协议文档信息','2019-05-19 10:37:39',1),(2,'员工模板','员工导入模板.xlsx','员工信息导入模板','2020-06-27 21:19:35',1),(23,'一个文档','计算机信息安全考核说明.docx','一个文档描述','2020-07-24 11:01:58',1);

/*Table structure for table `education_inf` */

DROP TABLE IF EXISTS `education_inf`;

CREATE TABLE `education_inf` (
  `id` int(255) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `education_inf` */

insert  into `education_inf`(`id`,`name`) values (1,'大专'),(2,'本科'),(3,'硕士'),(4,'博士');

/*Table structure for table `employee_inf` */

DROP TABLE IF EXISTS `employee_inf`;

CREATE TABLE `employee_inf` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `dept_id` int(11) NOT NULL,
  `job_id` int(11) NOT NULL,
  `name` varchar(20) DEFAULT NULL,
  `card_id` varchar(20) DEFAULT NULL,
  `address` varchar(50) DEFAULT NULL,
  `phone` varchar(11) DEFAULT NULL,
  `sex_id` int(11) DEFAULT NULL,
  `education_id` int(10) DEFAULT NULL,
  `createdate` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_EMP_DEPT` (`dept_id`),
  KEY `FK_EMP_JOB` (`job_id`),
  CONSTRAINT `FK_EMP_DEPT` FOREIGN KEY (`dept_id`) REFERENCES `dept_inf` (`id`),
  CONSTRAINT `FK_EMP_JOB` FOREIGN KEY (`job_id`) REFERENCES `job_inf` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

/*Data for the table `employee_inf` */

insert  into `employee_inf`(`id`,`dept_id`,`job_id`,`name`,`card_id`,`address`,`phone`,`sex_id`,`education_id`,`createdate`,`user_id`) values (1,1,7,'爱丽丝','432801197711251052','浙江省杭州市','13902001111',2,3,'2019-05-09 19:45:59',1),(3,1,3,'袭人','620523199410103810','江苏省南京市','13902001111',2,3,'2019-05-11 13:19:32',3),(5,3,9,'翎幺','620523199810103810','甘肃省兰州市','13902001111',1,3,'2019-04-15 15:44:57',5),(11,3,1,'小三','410303167899009878','河南省洛阳市','11022232232',1,3,'2020-08-02 10:33:19',1),(12,3,1,'都US','410303167899009878','北京市','12356567110',1,3,'2020-08-02 10:33:34',1),(13,3,1,'看看书我','41030316781201987X','安徽省亳州市','15677789110',2,3,'2021-03-25 14:23:08',1),(14,7,12,'是是是','410303167812019878','安徽省亳州市','13845677487',2,1,'2021-03-25 14:15:17',20);

/*Table structure for table `job_inf` */

DROP TABLE IF EXISTS `job_inf`;

CREATE TABLE `job_inf` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `remark` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

/*Data for the table `job_inf` */

insert  into `job_inf`(`id`,`name`,`remark`) values (1,'职员','职员'),(2,'Java开发工程师','Java开发工程师'),(3,'Java中级开发工程师','Java中级开发工作'),(6,'架构师','架构师'),(7,'主管','主管'),(9,'总经理','负责项目总体工作'),(12,'职员职员','职员职员职员'),(13,'是是是','坎坎坷坷的'),(18,'是是时','撒大声地多多');

/*Table structure for table `jobtype_inf` */

DROP TABLE IF EXISTS `jobtype_inf`;

CREATE TABLE `jobtype_inf` (
  `id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `jobtype_inf` */

insert  into `jobtype_inf`(`id`,`name`) values (1,'人才项目'),(2,'技术方向'),(3,'设计方向'),(4,'产品方向'),(5,'客户服务'),(6,'金融方向'),(7,'市场与职能方向');

/*Table structure for table `laborcontract_inf` */

DROP TABLE IF EXISTS `laborcontract_inf`;

CREATE TABLE `laborcontract_inf` (
  `id` int(255) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `laborcontract_inf` */

insert  into `laborcontract_inf`(`id`,`name`) values (1,'已签'),(2,'未签');

/*Table structure for table `leave_inf` */

DROP TABLE IF EXISTS `leave_inf`;

CREATE TABLE `leave_inf` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `emp_id` int(11) DEFAULT NULL,
  `dept_id` int(11) DEFAULT NULL,
  `job_id` int(11) DEFAULT NULL,
  `startdata` varchar(255) DEFAULT NULL,
  `enddata` varchar(255) DEFAULT NULL,
  `leavedays` varchar(255) DEFAULT NULL,
  `leavetype` int(11) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `leavestatus` int(11) DEFAULT '2',
  `leavetime` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `lea_1` (`emp_id`),
  KEY `lea_2` (`dept_id`),
  KEY `lea_3` (`job_id`),
  CONSTRAINT `lea_1` FOREIGN KEY (`emp_id`) REFERENCES `employee_inf` (`id`),
  CONSTRAINT `lea_2` FOREIGN KEY (`dept_id`) REFERENCES `employee_inf` (`dept_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `lea_3` FOREIGN KEY (`job_id`) REFERENCES `employee_inf` (`job_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

/*Data for the table `leave_inf` */

insert  into `leave_inf`(`id`,`emp_id`,`dept_id`,`job_id`,`startdata`,`enddata`,`leavedays`,`leavetype`,`content`,`leavestatus`,`leavetime`) values (4,5,3,9,'2019-05-07','2019-05-09','2',1,'头疼感冒',1,'2020-07-28 09:26:14');

/*Table structure for table `leavestatus_inf` */

DROP TABLE IF EXISTS `leavestatus_inf`;

CREATE TABLE `leavestatus_inf` (
  `id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `leavestatus_inf` */

insert  into `leavestatus_inf`(`id`,`name`) values (1,'批准'),(2,'未批准');

/*Table structure for table `leavetype_inf` */

DROP TABLE IF EXISTS `leavetype_inf`;

CREATE TABLE `leavetype_inf` (
  `id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `leavetype_inf` */

insert  into `leavetype_inf`(`id`,`name`) values (1,'病假'),(2,'事假');

/*Table structure for table `notice_inf` */

DROP TABLE IF EXISTS `notice_inf`;

CREATE TABLE `notice_inf` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(50) DEFAULT NULL,
  `content` text,
  `createdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `user_notice` (`user_id`),
  CONSTRAINT `user_notice` FOREIGN KEY (`user_id`) REFERENCES `user_inf` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

/*Data for the table `notice_inf` */

insert  into `notice_inf`(`id`,`title`,`content`,`createdate`,`user_id`) values (1,'你好','明天就是你自己的生日了','2019-01-29 15:06:36',1),(2,'下午开会','技术部下午3.00开会,请各部门准时参加会议','2019-01-29 15:13:24',2),(3,'五一放假','五一放假安排时间调整及时通知','2019-02-22 19:06:23',1),(4,'发布新消息','在普通用户页面实时提示！！！','2019-03-03 17:21:30',1),(5,'年终考核结果公','请各部门主管3.25号之前汇总上报部门考核情况！！！','2019-03-03 20:10:27',1),(6,'公告测试','公告测试内容一，发送邮件','2019-05-20 10:19:39',1),(8,'sdfsdfsd','	超级管理员sefsdfdsf','2021-03-25 15:57:31',1);

/*Table structure for table `recruitment_inf` */

DROP TABLE IF EXISTS `recruitment_inf`;

CREATE TABLE `recruitment_inf` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `jobtype` int(11) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `peoplenum` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT '1',
  `enddate` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

/*Data for the table `recruitment_inf` */

insert  into `recruitment_inf`(`id`,`jobtype`,`content`,`peoplenum`,`status`,`enddate`) values (1,1,'管培生',1,1,'2019-06-30'),(2,2,'java初级工程师',2,2,'2019-5-30'),(3,3,'UI设计工程师',5,1,'2019-6-30'),(5,5,'客服专员',2,1,'2019-6-30'),(6,6,'会计师',1,1,'2019-6-30'),(7,7,'行政专员',2,2,'2019-5-30'),(8,2,'算法工程师',2,1,'2019-6-30'),(9,2,'C++开发工程师',1,1,'2019-06-30'),(10,2,'Java专家',1,1,'2019-06-30'),(11,4,'产品经理',1,1,'2019-06-30'),(12,6,'高级会计师',1,1,'2019-06-30'),(13,5,'高级客服专员',2,1,'2019-06-30');

/*Table structure for table `recruitmentstatus_inf` */

DROP TABLE IF EXISTS `recruitmentstatus_inf`;

CREATE TABLE `recruitmentstatus_inf` (
  `id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `recruitmentstatus_inf` */

insert  into `recruitmentstatus_inf`(`id`,`name`) values (1,'正在招聘'),(2,'已结束');

/*Table structure for table `resume_inf` */

DROP TABLE IF EXISTS `resume_inf`;

CREATE TABLE `resume_inf` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `jobtype` varchar(255) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `sex_id` int(11) DEFAULT NULL,
  `education_id` int(11) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `status_id` int(11) DEFAULT '2',
  `filename` varchar(255) DEFAULT NULL,
  `createdate` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Data for the table `resume_inf` */

insert  into `resume_inf`(`id`,`jobtype`,`content`,`name`,`email`,`sex_id`,`education_id`,`phone`,`status_id`,`filename`,`createdate`) values (1,'设计方向','UI设计工程师','翎幺','13902001111@163.com',1,2,'13902001111',2,'翎幺-苏州简历(2019年5月版) .pdf','2019-06-03 20:27:12'),(2,'技术方向','C++开发工程师','翎幺','13902001111@163.com',1,1,'13902001111',1,'翎幺-苏州简历(2019年5月版) .pdf','2019-06-04 08:55:09'),(3,'市场与职能方向','行政专员','安安','13902001111@163.com',1,2,'13902001111',1,'翎幺-西安简历(2019年5月版) .pdf','2019-06-04 09:41:18'),(4,'产品方向','产品研发工程师','都受到','13902001111@163.com',2,4,'13902001111',2,'翎幺-简历(2019年6月版) .pdf','2019-06-04 10:30:18'),(5,'客户服务','客服专员','苟富贵','13902001111@qq.com',1,3,'13902001111',1,'翎幺-简历(2019年6月版) .pdf','2019-06-04 10:30:54');

/*Table structure for table `salary_inf` */

DROP TABLE IF EXISTS `salary_inf`;

CREATE TABLE `salary_inf` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `DEPT_ID` int(11) NOT NULL,
  `JOB_ID` int(11) NOT NULL,
  `EMP_ID` int(11) NOT NULL,
  `SALARY_STATION` int(18) NOT NULL,
  `SALARY_LEVEL` int(50) NOT NULL,
  `SENIORITY_PAY` int(50) DEFAULT NULL,
  `PERFORMANCE` int(16) DEFAULT NULL,
  `INDIVIDUAL_INCOME` float(11,0) NOT NULL,
  `CREATE_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`ID`),
  KEY `FK_EMP_DEPT` (`DEPT_ID`),
  KEY `FK_EMP_JOB` (`JOB_ID`),
  KEY `ID` (`ID`),
  KEY `salary_inf_ibfk_3` (`EMP_ID`),
  CONSTRAINT `sal_1` FOREIGN KEY (`DEPT_ID`) REFERENCES `employee_inf` (`dept_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `sal_2` FOREIGN KEY (`JOB_ID`) REFERENCES `employee_inf` (`job_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `sal_3` FOREIGN KEY (`EMP_ID`) REFERENCES `employee_inf` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

/*Data for the table `salary_inf` */

insert  into `salary_inf`(`ID`,`DEPT_ID`,`JOB_ID`,`EMP_ID`,`SALARY_STATION`,`SALARY_LEVEL`,`SENIORITY_PAY`,`PERFORMANCE`,`INDIVIDUAL_INCOME`,`CREATE_DATE`) values (3,1,3,3,5000,3,450,360,500,'2019-04-19 09:12:53'),(5,3,9,5,5500,2,450,360,540,'2019-04-19 09:13:21'),(6,3,1,11,5000,3,12,120,120,'2020-07-29 09:21:48');

/*Table structure for table `sex_inf` */

DROP TABLE IF EXISTS `sex_inf`;

CREATE TABLE `sex_inf` (
  `id` int(255) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sex_inf` */

insert  into `sex_inf`(`id`,`name`) values (1,'男'),(2,'女');

/*Table structure for table `staticid_inf` */

DROP TABLE IF EXISTS `staticid_inf`;

CREATE TABLE `staticid_inf` (
  `staticId` int(11) NOT NULL,
  PRIMARY KEY (`staticId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `staticid_inf` */

insert  into `staticid_inf`(`staticId`) values (13);

/*Table structure for table `status_inf` */

DROP TABLE IF EXISTS `status_inf`;

CREATE TABLE `status_inf` (
  `id` int(255) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `status_inf` */

insert  into `status_inf`(`id`,`name`) values (1,'已审核'),(2,'未审核');

/*Table structure for table `train_inf` */

DROP TABLE IF EXISTS `train_inf`;

CREATE TABLE `train_inf` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `emp_id` int(11) DEFAULT NULL,
  `dept_id` int(11) DEFAULT NULL,
  `job_id` int(11) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `startdata` varchar(255) DEFAULT NULL,
  `enddata` varchar(255) DEFAULT NULL,
  `totallength` int(11) DEFAULT NULL,
  `completion` int(11) DEFAULT NULL,
  `grade` int(11) DEFAULT NULL,
  `traintime` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `lea_1` (`emp_id`),
  KEY `lea_2` (`dept_id`),
  KEY `lea_3` (`job_id`),
  CONSTRAINT `train_inf_ibfk_1` FOREIGN KEY (`emp_id`) REFERENCES `employee_inf` (`id`),
  CONSTRAINT `train_inf_ibfk_2` FOREIGN KEY (`dept_id`) REFERENCES `employee_inf` (`dept_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `train_inf_ibfk_3` FOREIGN KEY (`job_id`) REFERENCES `employee_inf` (`job_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `train_inf` */

insert  into `train_inf`(`id`,`emp_id`,`dept_id`,`job_id`,`content`,`startdata`,`enddata`,`totallength`,`completion`,`grade`,`traintime`) values (1,5,3,9,'java进阶','2019-05-14','2019-05-22',8,1,86,'2019-05-24 15:37:48');

/*Table structure for table `traincontract_inf` */

DROP TABLE IF EXISTS `traincontract_inf`;

CREATE TABLE `traincontract_inf` (
  `id` int(255) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `traincontract_inf` */

insert  into `traincontract_inf`(`id`,`name`) values (1,'已签'),(2,'未签');

/*Table structure for table `traindata_inf` */

DROP TABLE IF EXISTS `traindata_inf`;

CREATE TABLE `traindata_inf` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(50) DEFAULT NULL,
  `filename` varchar(300) DEFAULT NULL,
  `remark` varchar(300) DEFAULT NULL,
  `createdate` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=37 DEFAULT CHARSET=utf8;

/*Data for the table `traindata_inf` */

insert  into `traindata_inf`(`id`,`title`,`filename`,`remark`,`createdate`,`user_id`) values (1,'大圣归来','0ec8cc80112a2d6.mp4','大圣归来一','2019-05-19 09:52:55',1),(2,'大圣归来','0ec8cc80112a2d7.mp4','大圣归来二','2019-05-19 10:04:18',1),(3,'北京北京','群星 - 北京欢迎你.mp4','北京欢迎你','2019-05-19 12:15:39',1);

/*Table structure for table `user_inf` */

DROP TABLE IF EXISTS `user_inf`;

CREATE TABLE `user_inf` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `loginname` varchar(20) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `status_id` int(11) DEFAULT '2',
  `createdate` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `username` varchar(20) DEFAULT NULL,
  `emp_id` int(11) DEFAULT NULL,
  `che_id` int(11) DEFAULT NULL,
  `con_id` int(11) DEFAULT NULL,
  `sal_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;

/*Data for the table `user_inf` */

insert  into `user_inf`(`id`,`loginname`,`password`,`email`,`status_id`,`createdate`,`username`,`emp_id`,`che_id`,`con_id`,`sal_id`) values (1,'admin','0192023a7bbd73250516f069df18b500','',1,'2021-03-24 10:51:44','超级管理员',NULL,NULL,NULL,NULL),(2,'manager','7304b1d41dbd735711f9e25db40b4f63','',1,'2019-04-15 15:35:43','总经理',NULL,NULL,NULL,NULL),(3,'addas','7ac6e9b93d3e3ecc13968f6cc53a9093','13902001111@163.com',1,'2020-06-26 10:13:07','阿迪达斯',1,1,1,1),(5,'ahualy','0246876c1c966a36923ceb77c1d82e3a','13902001111@163.com',1,'2019-05-18 09:04:08','测试',5,5,5,5),(6,'Leetcode','28621d88e9551ce4086aa466239ecb16','13902001111@163.com',1,'2019-04-17 08:40:33','铭星',8,8,8,8),(10,'xuzheng','e7b336879539d04ce882ff205d1903f1','546881916@qq.com',2,'2020-06-25 23:20:50','徐政',12,12,12,12),(11,'xujie','33f68e36f818d6165efd550d20fc8d48','1234567@qq.com',1,'2020-04-17 14:20:17','徐杰',13,13,13,13),(12,'zhaoyun','4ba21ea1c0e3b03de441d797e54547c1','zhaoyun@126.com',2,'2020-06-25 23:54:05','赵云',NULL,NULL,NULL,NULL),(13,'zhangsq','94860f25f3783d136327952dd47042fb','zhangsq@126.com',1,'2020-06-30 05:09:58','张胜强',NULL,NULL,NULL,NULL),(15,'zhangdanfeng','b8988ee1335fec445b5b6e9a05fe84c5','87704991@qq.com',2,'2020-06-26 13:01:21','张丹峰',NULL,NULL,NULL,NULL),(16,'sunwukong','bb5c87d0b744f99ccc02de2f42d7fd1a','swk@126.com',1,'2020-07-01 04:44:28','孙悟空',NULL,NULL,NULL,NULL),(17,'zhubajie','fde6adda55d5898a5b4bb1a8e106ac08','zbj@126.com',1,'2020-07-01 04:02:39','猪八戒',NULL,NULL,NULL,NULL),(18,'tangsanzang','tsz123','tsz@126.com',1,'2020-07-01 04:18:51','唐三藏',NULL,NULL,NULL,NULL),(19,'wwwww','w123456','1078897888@co.com',2,'2021-03-23 09:19:13','小王',NULL,NULL,NULL,NULL),(20,'ddd',NULL,NULL,2,'2021-03-23 11:34:09',NULL,NULL,NULL,NULL,NULL),(21,'sssddssss','fff','ff',1,'2021-03-23 15:07:39','户名',0,NULL,NULL,NULL),(23,'dddd','1234567','ddddd',2,'2021-03-23 19:14:26','dddd',NULL,NULL,NULL,NULL),(24,'sss','sssssss','sssss',2,'2021-03-23 19:22:06','ssss',NULL,NULL,NULL,NULL),(25,'eee','d2f2297d6e829cd3493aa7de4416a18f','eee',2,'2021-03-23 19:25:08','eee',NULL,NULL,NULL,NULL),(26,'wwww','0192023a7bbd73250516f069df18b500','13902001111@163.co',2,'2021-03-23 19:28:04','hjgjg',NULL,NULL,NULL,NULL);

/*Table structure for table `uservisit_inf` */

DROP TABLE IF EXISTS `uservisit_inf`;

CREATE TABLE `uservisit_inf` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `login_time` varchar(255) DEFAULT NULL,
  `exit_time` varchar(255) DEFAULT NULL,
  `visit_ip` varchar(255) DEFAULT NULL,
  `user_address` varchar(255) DEFAULT NULL,
  `user_from` varchar(255) NOT NULL,
  `browser` varchar(255) DEFAULT NULL,
  `system` varchar(255) DEFAULT NULL,
  `version` varchar(255) DEFAULT NULL,
  `loginname` varchar(255) DEFAULT NULL,
  `iphone` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8;

/*Data for the table `uservisit_inf` */

insert  into `uservisit_inf`(`id`,`login_time`,`exit_time`,`visit_ip`,`user_address`,`user_from`,`browser`,`system`,`version`,`loginname`,`iphone`) values (1,'2019-02-12 09:13:56','2019-02-12 20:10:21','117.157.184.39','北京 移动','移动端','Chrome','Android','57.0.2987.132','admin','HUAWEIFIG-AL10'),(2,'2019-02-12 09:16:46','2019-02-12 20:10:21','117.157.180.39','北京 移动','PC端','Firefox','Windows','65.0','admin','Windows NT 10.0; Win64; x64'),(3,'2019-02-12 09:17:19','2019-02-12 20:10:21','124.152.216.170','北京 联通','移动端','Mobile Safari','Android','4.0','admin','HUAWEICLT-AL00'),(4,'2020-04-16 15:36:00','2020-04-16 15:38:01','0:0:0:0:0:0:0:1',NULL,'PC端','Chrome','Windows','71.0.3578.98','admin','Windows NT 10.0; Win64'),(5,'2020-04-16 22:19:38','2020-04-16 22:22:08','0:0:0:0:0:0:0:1',NULL,'PC端','Chrome','Windows','71.0.3578.98','admin','Windows NT 10.0; Win64'),(6,'2020-04-17 14:14:25','2020-04-17 14:17:55','0:0:0:0:0:0:0:1',NULL,'PC端','Chrome','Windows','71.0.3578.98','admin','Windows NT 10.0; Win64'),(7,'2020-04-17 14:18:59','2020-04-17 14:19:30','0:0:0:0:0:0:0:1',NULL,'PC端','Chrome','Windows','71.0.3578.98','admin','Windows NT 10.0; Win64'),(8,'2020-04-17 14:19:54','2020-04-17 14:20:27','0:0:0:0:0:0:0:1',NULL,'PC端','Chrome','Windows','71.0.3578.98','admin','Windows NT 10.0; Win64'),(9,'2020-04-17 14:20:36','2020-04-17 14:21:15','0:0:0:0:0:0:0:1',NULL,'PC端','Chrome','Windows','71.0.3578.98','xujie','Windows NT 10.0; Win64'),(11,'2020-06-27 14:06:30','2020-06-27 14:06:40','127.0.0.1',NULL,'PC端','Chrome 8','Windows','83.0.4103.116','zhangsq','Windows NT 10.0; Win64'),(12,'2020-06-27 18:36:44','2020-06-27 18:36:49','127.0.0.1',NULL,'PC端','Chrome 8','Windows','83.0.4103.116','admin','Windows NT 10.0; Win64'),(13,'2020-06-27 18:40:12','2020-06-27 18:40:30','127.0.0.1',NULL,'PC端','Chrome 8','Windows','83.0.4103.116','admin','Windows NT 10.0; Win64'),(14,'2020-06-27 18:45:23','2020-06-27 18:45:39','127.0.0.1',NULL,'PC端','Chrome 8','Windows','83.0.4103.116','admin','Windows NT 10.0; Win64'),(15,'2020-06-27 18:46:21','2020-06-27 18:46:28','127.0.0.1',NULL,'PC端','Chrome 8','Windows','83.0.4103.116','zhangsq','Windows NT 10.0; Win64'),(16,'2020-06-27 18:47:11','2020-06-27 19:25:01','127.0.0.1',NULL,'PC端','Chrome 8','Windows','83.0.4103.116','admin','Windows NT 10.0; Win64'),(17,'2020-06-27 22:27:39','2020-06-27 22:27:43','127.0.0.1',NULL,'PC端','Chrome 8','Windows','83.0.4103.116','admin','Windows NT 10.0; Win64'),(18,'2020-06-27 22:37:05','2020-06-27 22:37:32','127.0.0.1',NULL,'PC端','Chrome 8','Windows','83.0.4103.116','admin','Windows NT 10.0; Win64'),(19,'2020-06-28 16:13:38','2020-06-28 16:14:03','192.168.1.102',NULL,'PC端','Chrome 8','Windows 7','80.0.3987.149','admin','Windows NT 6.1; Win64'),(20,'2020-06-29 14:57:47','2020-06-29 14:57:52','127.0.0.1',NULL,'PC端','Chrome 8','Windows','83.0.4103.116','admin','Windows NT 10.0; Win64'),(21,'2020-06-30 14:27:44','2020-06-30 15:23:09','127.0.0.1',NULL,'PC端','Chrome 8','Windows','83.0.4103.116','admin','Windows NT 10.0; Win64'),(22,'2020-06-30 22:31:12','2020-06-30 22:31:54','127.0.0.1',NULL,'PC端','Chrome 8','Windows','83.0.4103.116','admin','Windows NT 10.0; Win64'),(23,'2020-07-01 14:58:21','2020-07-01 14:58:33','127.0.0.1',NULL,'PC端','Chrome 8','Windows','83.0.4103.116','admin','Windows NT 10.0; Win64'),(24,'2020-07-01 15:45:41','2020-07-01 15:46:05','127.0.0.1',NULL,'PC端','Chrome 8','Windows','83.0.4103.116','admin','Windows NT 10.0; Win64'),(25,'2020-07-01 16:01:24','2020-07-01 16:02:52','127.0.0.1',NULL,'PC端','Chrome 8','Windows','83.0.4103.116','admin','Windows NT 10.0; Win64'),(28,'2020-07-01 16:44:20','2020-07-01 16:45:19','127.0.0.1',NULL,'PC端','Chrome 8','Windows','83.0.4103.116','sunwukong','Windows NT 10.0; Win64'),(29,'2020-07-01 16:45:28','2020-07-01 16:45:36','127.0.0.1',NULL,'PC端','Chrome 8','Windows','83.0.4103.116','admin','Windows NT 10.0; Win64'),(30,'2020-07-01 16:46:08','2020-07-01 16:46:57','127.0.0.1',NULL,'PC端','Chrome 8','Windows','83.0.4103.116','admin','Windows NT 10.0; Win64');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
