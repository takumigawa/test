/*
MySQL Data Transfer
Source Host: 192.168.220.128
Source Database: sibschedule
Target Host: 192.168.220.128
Target Database: sibschedule
Date: 2012/06/22 14:41:31
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for m_company
-- ----------------------------
DROP TABLE IF EXISTS `m_company`;
CREATE TABLE `m_company` (
  `company_id` int(11) NOT NULL AUTO_INCREMENT,
  `company_name` varchar(32) NOT NULL,
  `president_name` varchar(32) DEFAULT NULL,
  `class_id` int(11) DEFAULT NULL,
  `post_code` varchar(8) DEFAULT NULL,
  `address1` varchar(64) DEFAULT NULL,
  `address2` varchar(32) DEFAULT NULL,
  `tel` varchar(32) DEFAULT NULL,
  `fax` varchar(32) DEFAULT NULL,
  `email` varchar(64) DEFAULT NULL,
  `adddate` varchar(19) NOT NULL,
  `addid` int(11) NOT NULL,
  `upddate` varchar(19) DEFAULT NULL,
  `updid` int(11) DEFAULT NULL,
  PRIMARY KEY (`company_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for m_customer
-- ----------------------------
DROP TABLE IF EXISTS `m_customer`;
CREATE TABLE `m_customer` (
  `customer_ID` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(60) NOT NULL,
  `name_kana` varchar(120) DEFAULT NULL,
  `postcode` varchar(8) DEFAULT NULL,
  `Address1` varchar(120) DEFAULT NULL,
  `Address2` varchar(60) DEFAULT NULL,
  `tel` varchar(20) DEFAULT NULL,
  `fax` varchar(20) DEFAULT NULL,
  `email` varchar(64) DEFAULT NULL,
  `postcode2` varchar(8) DEFAULT NULL,
  `Address12` varchar(64) DEFAULT NULL,
  `Address22` varchar(32) DEFAULT NULL,
  `tel2` varchar(20) DEFAULT NULL,
  `fax2` varchar(20) DEFAULT NULL,
  `email2` varchar(64) DEFAULT NULL,
  `orderSEQ` int(11) NOT NULL,
  `kaihai` int(11) NOT NULL,
  `AddDate` varchar(19) NOT NULL,
  `AddID` int(11) NOT NULL,
  `UpdDate` varchar(19) DEFAULT NULL,
  `UpdID` int(11) DEFAULT NULL,
  PRIMARY KEY (`customer_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for m_employee
-- ----------------------------
DROP TABLE IF EXISTS `m_employee`;
CREATE TABLE `m_employee` (
  `Employee_ID` int(11) NOT NULL AUTO_INCREMENT,
  `Employee_Name` varchar(20) NOT NULL,
  `Employee_Name_kana` varchar(40) NOT NULL,
  `loginID` varchar(20) NOT NULL,
  `loginPassword` varchar(20) NOT NULL,
  `Employee_position_ID` int(11) DEFAULT NULL,
  `Default_Group_ID` int(11) NOT NULL,
  `Employee_email` varchar(64) DEFAULT NULL,
  `Employee_email2` varchar(64) DEFAULT NULL,
  `Employee_email3` varchar(64) DEFAULT NULL,
  `Employee_postcode` varchar(8) DEFAULT NULL,
  `Employee_address1` varchar(120) DEFAULT NULL,
  `Employee_address2` varchar(60) DEFAULT NULL,
  `Employee_tel` varchar(20) DEFAULT NULL,
  `Employee_mphone` varchar(20) DEFAULT NULL,
  `Employee_fax` varchar(20) DEFAULT NULL,
  `orderSEQ` int(11) NOT NULL,
  `kaihai` int(11) NOT NULL DEFAULT '0',
  `AddDate` varchar(19) NOT NULL,
  `AddID` int(11) NOT NULL,
  `UpdDate` varchar(19) DEFAULT NULL,
  `UpdID` int(11) DEFAULT NULL,
  PRIMARY KEY (`Employee_ID`),
  KEY `Employee_ID_pk` (`Employee_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for m_group
-- ----------------------------
DROP TABLE IF EXISTS `m_group`;
CREATE TABLE `m_group` (
  `Group_ID` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(60) NOT NULL DEFAULT '',
  `name_kana` varchar(120) NOT NULL,
  `authority` int(11) NOT NULL DEFAULT '0',
  `orderSEQ` int(11) NOT NULL,
  `kaihai` int(11) NOT NULL DEFAULT '0',
  `AddDate` varchar(19) NOT NULL,
  `AddID` int(11) NOT NULL,
  `UpdDate` varchar(19) DEFAULT '',
  `UpdID` int(11) DEFAULT NULL,
  PRIMARY KEY (`Group_ID`)
) ENGINE=MyISAM AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_commerical
-- ----------------------------
DROP TABLE IF EXISTS `t_commerical`;
CREATE TABLE `t_commerical` (
  `cd_ID` int(11) NOT NULL,
  `userID` int(11) NOT NULL,
  `comSEQ` int(11) NOT NULL,
  PRIMARY KEY (`cd_ID`,`userID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_groupattach
-- ----------------------------
DROP TABLE IF EXISTS `t_groupattach`;
CREATE TABLE `t_groupattach` (
  `group_ID` int(11) NOT NULL,
  `Employee_ID` int(11) NOT NULL,
  `dummy` int(11) DEFAULT NULL,
  PRIMARY KEY (`group_ID`,`Employee_ID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_matter
-- ----------------------------
DROP TABLE IF EXISTS `t_matter`;
CREATE TABLE `t_matter` (
  `Matter_ID` int(11) NOT NULL AUTO_INCREMENT,
  `Employee_ID` int(11) NOT NULL,
  `customer_ID` int(11) NOT NULL,
  `Commerical_ID` int(11) DEFAULT NULL,
  `Matter_Address1` varchar(120) DEFAULT NULL,
  `Matter_Address2` varchar(60) DEFAULT NULL,
  `Matter_Titel` varchar(50) NOT NULL,
  `Matter_note` varchar(255) DEFAULT NULL,
  `Matter_Start` varchar(50) DEFAULT NULL,
  `Matter_end` varchar(50) DEFAULT NULL,
  `kaihai` int(11) NOT NULL,
  `AddDate` varchar(19) NOT NULL,
  `AddID` int(11) NOT NULL,
  `UpdDate` varchar(19) DEFAULT NULL,
  `UpdID` int(11) DEFAULT NULL,
  PRIMARY KEY (`Matter_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_schedule
-- ----------------------------
DROP TABLE IF EXISTS `t_schedule`;
CREATE TABLE `t_schedule` (
  `work_ID` int(11) NOT NULL AUTO_INCREMENT,
  `Employee_ID` int(11) NOT NULL,
  `start_Date` varchar(10) NOT NULL,
  `Start_Time` varchar(8) DEFAULT NULL,
  `end_Date` varchar(10) NOT NULL,
  `end_Time` varchar(8) DEFAULT NULL,
  `titel` varchar(50) NOT NULL,
  `note` varchar(255) DEFAULT NULL,
  `priority` int(11) DEFAULT NULL,
  `AddDate` varchar(19) NOT NULL,
  `AddID` int(11) NOT NULL,
  `UpdDate` varchar(19) DEFAULT NULL,
  `UpdID` int(11) DEFAULT NULL,
  PRIMARY KEY (`work_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_schedulegroup
-- ----------------------------
DROP TABLE IF EXISTS `t_schedulegroup`;
CREATE TABLE `t_schedulegroup` (
  `schedule_ID` int(11) NOT NULL,
  `Employee_ID` int(11) NOT NULL,
  PRIMARY KEY (`schedule_ID`,`Employee_ID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_setting
-- ----------------------------
DROP TABLE IF EXISTS `t_setting`;
CREATE TABLE `t_setting` (
  `key1` varchar(10) NOT NULL,
  `key2` varchar(10) NOT NULL,
  `key3` varchar(50) NOT NULL,
  `stringValue` varchar(255) DEFAULT NULL,
  `integerValue` int(11) DEFAULT NULL,
  PRIMARY KEY (`key1`,`key2`,`key3`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_tips
-- ----------------------------
DROP TABLE IF EXISTS `t_tips`;
CREATE TABLE `t_tips` (
  `tips_ID` int(11) NOT NULL AUTO_INCREMENT,
  `startDate` varchar(10) NOT NULL,
  `endDate` varchar(10) NOT NULL,
  `note` varchar(255) NOT NULL,
  `inputemployeeID` int(11) NOT NULL,
  `file1_ID` int(11) DEFAULT NULL,
  `file2_ID` int(11) DEFAULT NULL,
  `file3_ID` int(11) DEFAULT NULL,
  `file4_ID` int(11) DEFAULT NULL,
  `file5_ID` int(11) DEFAULT NULL,
  `priority` int(11) DEFAULT NULL,
  PRIMARY KEY (`tips_ID`)
) ENGINE=MyISAM AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_uploadfile
-- ----------------------------
DROP TABLE IF EXISTS `t_uploadfile`;
CREATE TABLE `t_uploadfile` (
  `file_ID` int(11) NOT NULL AUTO_INCREMENT,
  `file_Name` varchar(255) NOT NULL,
  `file_Value` mediumblob NOT NULL,
  `deleteFlg` tinyint(2) NOT NULL DEFAULT '1',
  `AddDate` varchar(19) NOT NULL,
  `AddID` int(11) NOT NULL,
  PRIMARY KEY (`file_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records 
-- ----------------------------
INSERT INTO `m_company` VALUES ('1', '株式会社SIB', '新井　修', '2', '169-0074', '東京都新宿区北新宿1-1-16', 'JSビル503', '03-5289-2332', null, 'info@sib.co.jp', '2009/12/06', '2', '2012/06/21 16:56:30', '2');
INSERT INTO `m_customer` VALUES ('1', '株式会社フューチャーネクスト', 'ﾌｭｰﾁｬｰﾈｸｽﾄd', '160-0022', '東京都新宿区1-26-1', '長田屋ビル7階', '03-5369-7763', '03-5369-7764', 'inf４４@fnext.co.jp', null, null, null, null, null, null, '10', '0', '2009/12/07 23:37:00', '2', '2009/12/15 11:17:47', '2');
INSERT INTO `m_customer` VALUES ('2', 'e-Zeus株式会社', 'ｲｰｾﾞｳｽ', '160-0022', '東京都新宿区1-26-1', '長田屋ビル7階', '03-5369-8024', null, 'inf@ezeus.co.jp', null, null, null, null, null, null, '20', '0', '2009/12/07 23:37:00', '2', '2009/12/15 11:18:05', '2');
INSERT INTO `m_customer` VALUES ('3', '株式会社エクシィ', 'ｴｸｼｨ', '160-0022', '東京都新宿区1-26-1', '長田屋ビル7階', '03-6380-6817', '03-6380-6818', 'inf@xsi.co.jp', null, null, null, null, null, null, '30', '0', '2009/12/07 23:37:00', '2', '', '0');
INSERT INTO `m_customer` VALUES ('4', '株式会社メディアフォース', 'ﾒﾃﾞｨｱﾌｫｰｽ', null, '東京都新宿区下落合2丁目3-18', 'SKビル5階', '03-5988-2010', null, null, null, null, null, null, null, null, '40', '0', '2009/12/07 23:37:00', '2', '', '0');
INSERT INTO `m_customer` VALUES ('5', '株式会社エム・ビー・エス', 'ｴﾑﾋﾞｰｴｽ', '277-0852', '千葉県柏市旭町1-4-18', '後藤ビル4F', '04-7146-9640', '04-7146-9684', null, null, null, null, null, null, null, '50', '1', '2009/12/07 23:37:00', '2', '2009/12/10 10:38:14', '0');
INSERT INTO `m_customer` VALUES ('6', 'd', 'ｶ', '300-1532', '茨城県取手市谷中31-29', '自宅', '0297-82-4910', null, 'bzm07302@nifty.com', null, null, null, null, null, null, '60', '0', '2009/12/10 10:54:35', '0', '2009/12/10 10:56:26', '0');
INSERT INTO `m_customer` VALUES ('7', 'サッポロビール', 'ｻｯﾎﾟﾛﾋﾞｰﾙ', null, null, null, null, null, null, null, null, null, null, null, null, '70', '0', '2009/12/11 09:41:00', '2', null, null);
INSERT INTO `m_customer` VALUES ('8', 'コムニック', 'ｺﾑﾆｯｸ', null, null, null, null, null, null, null, null, null, null, null, null, '80', '0', '2009/12/11 09:42:13', '2', null, null);
INSERT INTO `m_customer` VALUES ('9', 'sfdsafd', 'ｴ', '1111', 'afsdf', 'afds', 'afds', 'afsd', 'afsfad', null, null, null, null, null, null, '90', '0', '2012/04/14 08:56:50', '2', null, null);
INSERT INTO `m_employee` VALUES ('1', '新井　修', 'ｱﾗｲ ｵｻﾑ', 'arai', 'osamu', '2', '1', 'osamu_arai@sib.co.jp', null, null, null, null, null, null, null, null, '10', '0', '2009/12/07 23:29:00', '2', '2009/12/15 15:38:27', '2');
INSERT INTO `m_employee` VALUES ('2', '藤本　卓', 'ﾌｼﾞﾓﾄ ﾀｸ', 'takumigawa', 'ryuunosuke', '6', '6', 'taku_fujimoto@sib.co.jp', null, null, '302-0015', '茨城県取手市井野台3-3-1', 'ロータリーパレス取手611', null, '090-5409-4152', null, '20', '0', '2009/12/07 23:29:00', '2', '2012/06/21 17:34:27', '2');
INSERT INTO `m_employee` VALUES ('3', '平　純一', 'ﾋﾗ ｼﾞｭﾝｲﾁ', 'hira', 'abc', '7', '8', 'junichi_hira@sib.co.jp', null, null, null, null, null, null, null, null, '30', '0', '2009/12/07 23:29:00', '2', '2012/04/14 08:58:06', '2');
INSERT INTO `m_employee` VALUES ('4', 'あだたら高原', 'ｱﾀﾞﾀﾗｺｳｹﾞﾝ', 'ada', 'ada', '6', '8', null, null, null, null, null, null, null, null, null, '40', '1', '2009/12/08 11:11:11', '2', '2010/02/23 13:03:06', '2');
INSERT INTO `m_employee` VALUES ('5', 'q', 'ｱ', 'q', 'q', '1', '2', 'qwew', null, null, null, null, null, null, null, null, '100', '1', '2009/12/09 13:44:45', '0', '2009/12/14 15:20:56', '2');
INSERT INTO `m_employee` VALUES ('6', '御殿場忠利', 'ｺﾞﾃﾝﾊﾞ ﾀﾀﾞﾄｼ', 'gotada', 'e', null, '1', 'gotenba@sib.co.jp', null, null, null, null, null, null, null, null, '110', '0', '2009/12/09 15:27:49', '0', '2009/12/09 15:39:23', '0');
INSERT INTO `m_employee` VALUES ('7', 'テスト　太郎', 'ﾃｽﾄﾀﾛｳ', 'test', 'test', '6', '5', 'test@sib.co.jp', null, null, null, null, null, null, null, null, '120', '0', '2009/12/28 11:34:20', '2', '2009/12/28 12:00:17', '2');
INSERT INTO `m_group` VALUES ('1', '営業部', 'ｴｲｷﾞｮｳﾌﾞ', '6', '10', '0', '2009/12/18 00:00:00', '2', '2012/06/21 17:22:12', '2');
INSERT INTO `m_group` VALUES ('2', '', '', '0', '0', '1', '', '0', '2009/12/18 17:01:33', '2');
INSERT INTO `m_group` VALUES ('3', '1', '1', '1', '1', '1', '2009/12/18 17:24:17', '2', '2009/12/18 17:29:49', '2');
INSERT INTO `m_group` VALUES ('4', '3', 'dd', '0', '0', '1', '2009/12/18 17:26:30', '2', '2009/12/18 17:29:45', '2');
INSERT INTO `m_group` VALUES ('5', '総務部', 'ｿｳﾑﾌﾞ', '2', '20', '0', '2009/12/18 17:29:35', '2', '', null);
INSERT INTO `m_group` VALUES ('6', '管理者ぐぷーぷ', 'ｶﾝﾘｼｬｸﾞﾙｰﾌﾟ', '7', '5', '0', '2009/12/21 15:57:42', '2', '2012/06/21 18:04:37', '2');
INSERT INTO `m_group` VALUES ('7', 'デブリ回収事業部', 'ﾃﾞﾌﾞﾘｶｲｼｭｳｼﾞｷﾞｮｳﾌﾞ', '0', '30', '0', '2009/12/21 16:22:27', '2', '', null);
INSERT INTO `m_group` VALUES ('8', 'テスター', 'ﾃｽﾀｰ', '2', '40', '0', '2009/12/25 16:02:35', '2', '', null);
INSERT INTO `m_group` VALUES ('9', '武装派戦線無意味派', 'ﾌﾞｿｳﾊｾﾝｾﾝﾑｲﾐﾊ', '0', '50', '0', '2012/06/04 17:20:50', '2', '2012/06/04 17:21:20', '2');
INSERT INTO `t_commerical` VALUES ('1', '1', '3');
INSERT INTO `t_commerical` VALUES ('1', '2', '4');
INSERT INTO `t_commerical` VALUES ('1', '3', '5');
INSERT INTO `t_commerical` VALUES ('1', '4', '2');
INSERT INTO `t_commerical` VALUES ('1', '5', '6');
INSERT INTO `t_commerical` VALUES ('1', '6', '7');
INSERT INTO `t_commerical` VALUES ('1', '7', '0');
INSERT INTO `t_commerical` VALUES ('1', '8', '1');
INSERT INTO `t_groupattach` VALUES ('1', '6', null);
INSERT INTO `t_groupattach` VALUES ('1', '3', null);
INSERT INTO `t_groupattach` VALUES ('1', '1', null);
INSERT INTO `t_groupattach` VALUES ('5', '1', null);
INSERT INTO `t_groupattach` VALUES ('5', '6', null);
INSERT INTO `t_groupattach` VALUES ('6', '2', null);
INSERT INTO `t_groupattach` VALUES ('5', '3', null);
INSERT INTO `t_groupattach` VALUES ('8', '7', null);
INSERT INTO `t_groupattach` VALUES ('8', '6', null);
INSERT INTO `t_groupattach` VALUES ('8', '3', null);
INSERT INTO `t_groupattach` VALUES ('5', '7', null);
INSERT INTO `t_matter` VALUES ('1', '1', '1', '1', '館林', '富士通データセンター', '館林通勤地獄', '通勤がんばってください', '11月', '12月末', '1', '', '0', '2012/06/04 17:39:51', '2');
INSERT INTO `t_matter` VALUES ('2', '2', '2', '1', '新宿御苑', null, '自社待機', '仕事ください。', '無期限', null, '1', '', '0', null, null);
INSERT INTO `t_matter` VALUES ('3', '3', '3', '1', '新宿御苑', null, '自社待機', '仕事ください。', '無期限', null, '0', '', '0', null, null);
INSERT INTO `t_matter` VALUES ('4', '4', '4', '1', 'この世のどこか', null, 'あの世のどこか', '・・・・', '・・・・', '・・・・・', '0', '', '0', null, null);
INSERT INTO `t_matter` VALUES ('5', '2', '4', '1', '小伝馬町', '', 'L-net リバースエンジニアリング', 'いやだ・・・', '8月', '10月', '1', '', '0', '2012/06/04 17:39:55', '2');
INSERT INTO `t_matter` VALUES ('6', '5', '4', '1', '牛久', null, '終わった案件', null, '８', null, '1', '', '0', null, null);
INSERT INTO `t_matter` VALUES ('7', '1', '0', null, null, null, 'gfrds', null, null, null, '0', '2009/12/15 19:45:36', '2', null, null);
INSERT INTO `t_matter` VALUES ('8', '2', '0', null, 'あのよ', null, 'もぐろふくぞう', null, '11月末', '15月末', '0', '2009/12/15 19:46:08', '2', null, null);
INSERT INTO `t_matter` VALUES ('9', '2', '0', null, 'しごとくれぇぇぇｌ', null, 'もぐろふくぞう２', 'もろへいやもろへいや\r\nもろもろへいや\r\nｄ\r\n\r\nｄ」', '生まれてから', null, '0', '2009/12/15 19:47:26', '2', null, null);
INSERT INTO `t_schedule` VALUES ('1', '1', '2010/02/01', '09:00', '2010/02/01', '12:00', '帰社日', '御苑事務所にて9:00から12:00まで帰社日とします。', '5', '2010/01/29', '1', null, null);
INSERT INTO `t_schedule` VALUES ('3', '2', '2010/01/04', '09:00', '2010/03/31', '18:00', '竹芝', '眺めがいい現場で、お台場が見えます。', '3', '2009/12/28', '2', null, null);
INSERT INTO `t_schedule` VALUES ('4', '3', '2009/12/01', null, '2010/02/28', null, '御苑事務所', '家事代行業務管理システム・社内システムの開発を行っています。', '0', '2009/12/01', '3', null, null);
INSERT INTO `t_schedule` VALUES ('5', '3', '2010/02/01', '10:00', '2010/02/01', '10:30', '独演会', '平純一独演会', '0', '2010/01/27', '3', null, null);
INSERT INTO `t_schedule` VALUES ('6', '3', '2010/02/02', '09:30', '2010/02/02', '10:00', '独演会', '平純一独演会', '0', '2010/02/01', '3', null, null);
INSERT INTO `t_schedule` VALUES ('7', '3', '2010/02/06', null, '2010/02/06', null, '休日出勤', '社内システムの納期に間に合わない為。', '5', '2010/02/01', '3', null, null);
INSERT INTO `t_schedule` VALUES ('8', '3', '2010/02/06', null, '2010/02/07', '18:00', 'テスト', 'テストスケジュール', '5', '2010/02/01', '3', null, null);
INSERT INTO `t_schedule` VALUES ('9', '3', '2010/02/05', '09:00', '2010/02/06', null, 'テスト２', 'テストスケジュール２', '3', '2010/02/04', '3', null, null);
INSERT INTO `t_schedule` VALUES ('10', '3', '2010/02/04', '09:00', '2010/02/08', '18:00', 'テスト３', 'テストスケジュール３', '4', '2010/02/04', '3', null, null);
INSERT INTO `t_schedule` VALUES ('11', '3', '2010/01/01', '09:00', '2010/01/31', '18:00', 'テスト４', 'テストスケジュール４', '3', '2010/01/01', '3', null, null);
INSERT INTO `t_schedule` VALUES ('12', '3', '2010/01/01', '09:00', '2010/01/31', '18:00', 'テスト５', 'テストスケジュール５', '3', '2010/01/01', '3', null, null);
INSERT INTO `t_schedule` VALUES ('13', '3', '2010/01/01', '09:00', '2010/01/31', '18:00', 'テスト６', 'テストスケジュール６', '3', '2010/01/01', '3', null, null);
INSERT INTO `t_schedule` VALUES ('15', '3', '2010/01/01', '09:00', '2010/01/31', '18:00', 'テスト８', 'テストスケジュール８', '3', '2010/01/01', '3', null, null);
INSERT INTO `t_schedule` VALUES ('16', '3', '2010/02/15', '00:00', '2010/02/15', '01:00', 'A', 'A', '5', '2010/02/12', '3', '2010/02/25 15:13:28', '2');
INSERT INTO `t_schedule` VALUES ('17', '3', '2010/02/15', '01:00', '2010/02/15', '02:00', 'B', 'B', '5', '2010/02/12', '3', null, null);
INSERT INTO `t_schedule` VALUES ('18', '3', '2010/02/15', '01:30', '2010/02/15', '02:30', 'C', 'C', '4', '2010/02/12', '3', null, null);
INSERT INTO `t_schedule` VALUES ('19', '3', '2010/02/15', '02:30', '2010/02/15', '04:00', 'D', 'D', '5', '2010/02/12', '3', null, null);
INSERT INTO `t_schedule` VALUES ('20', '3', '2010/02/15', '03:00', '2010/02/15', '04:00', 'E', 'E', '4', '2010/02/12', '3', null, null);
INSERT INTO `t_schedule` VALUES ('21', '3', '2010/02/15', '02:30', '2010/02/15', '03:30', 'F', 'F', '0', '2010/02/12', '3', null, null);
INSERT INTO `t_schedule` VALUES ('22', '3', '2010/02/15', '01:00', '2010/02/15', '02:00', 'G', 'G', '2', '2010/02/12', '3', null, null);
INSERT INTO `t_schedule` VALUES ('23', '3', '2010/02/15', '04:00', '2010/02/15', '05:00', 'H', 'H', '5', '2010/02/12', '3', null, null);
INSERT INTO `t_schedule` VALUES ('24', '3', '2010/02/15', '04:00', '2010/02/15', '05:00', 'I', 'I', '4', '2010/02/12', '3', null, null);
INSERT INTO `t_schedule` VALUES ('25', '3', '2010/02/15', '05:00', '2010/02/15', '06:00', 'J', 'J', '5', '2010/02/12', '3', null, null);
INSERT INTO `t_schedule` VALUES ('26', '3', '2010/02/15', '05:00', '2010/02/15', '06:00', 'K', 'K', '4', '2010/02/12', '3', null, null);
INSERT INTO `t_schedule` VALUES ('27', '3', '2010/02/15', '05:30', '2010/02/15', '06:30', 'L', 'L', '3', '2010/02/12', '3', null, null);
INSERT INTO `t_schedule` VALUES ('28', '3', '2010/02/15', '06:00', '2010/02/15', '07:00', 'M', 'M', '2', '2010/02/12', '3', null, null);
INSERT INTO `t_schedule` VALUES ('29', '3', '2010/02/15', '06:00', '2010/02/15', '07:00', 'N', 'N', '1', '2010/02/12', '3', null, null);
INSERT INTO `t_schedule` VALUES ('31', '3', '2010/02/15', '12:45', '2010/02/15', '15:00', '2', '2', null, '2010/02/12', '3', null, null);
INSERT INTO `t_schedule` VALUES ('32', '3', '2010/02/15', '17:00', '2010/02/15', '18:00', '3', '3', '2', '2010/02/12', '3', null, null);
INSERT INTO `t_schedule` VALUES ('33', '3', '2010/02/15', '16:00', '2010/02/15', '18:00', '4', '4', '4', '2010/02/12', '3', null, null);
INSERT INTO `t_schedule` VALUES ('34', '2', '2010/02/26', '09:00', '2010/02/26', '08:00', 'test', 'テストテストテストテストテストテストテストテストテストテストテストテストテストテストテストテストテストテストテストテストテストテストテストテストテストテストテストテストテストテストテストテストテストテストテストテストテストテストテストテストテストテストテストテストテストテストテストテストテストテストテストテストテストテストテストテスト', '0', '2010/02/23 09:58:55', '2', '2010/02/23 10:01:17', '2');
INSERT INTO `t_schedule` VALUES ('35', '2', '2010/03/01', null, '2010/03/31', null, '御苑', '4月13日から秋葉原かも・・・', '1', '2010/02/25 15:14:58', '2', '2010/03/30 10:12:10', '2');
INSERT INTO `t_schedule` VALUES ('38', '2', '2010/01/31', '00:00', '2010/01/31', '18:00', '嗚呼', 'スタイルテスト用のスケジュールです。', '2', '2010/03/19 17:09:54', '2', '2010/03/19 17:10:21', '2');
INSERT INTO `t_schedule` VALUES ('39', '2', '2010/04/13', '09:00', '2010/04/28', '18:00', '携帯電話評価業務', '携帯電話評価', null, '2010/03/30 10:13:37', '2', null, null);
INSERT INTO `t_schedule` VALUES ('40', '2', '2012/04/02', '04:00', '2012/04/30', '21:00', 'test', 'test', '5', '2012/04/14 08:52:27', '2', null, null);
INSERT INTO `t_schedulegroup` VALUES ('1', '1');
INSERT INTO `t_schedulegroup` VALUES ('1', '2');
INSERT INTO `t_schedulegroup` VALUES ('1', '3');
INSERT INTO `t_schedulegroup` VALUES ('3', '2');
INSERT INTO `t_schedulegroup` VALUES ('4', '3');
INSERT INTO `t_schedulegroup` VALUES ('5', '1');
INSERT INTO `t_schedulegroup` VALUES ('5', '3');
INSERT INTO `t_schedulegroup` VALUES ('6', '3');
INSERT INTO `t_schedulegroup` VALUES ('7', '3');
INSERT INTO `t_schedulegroup` VALUES ('8', '3');
INSERT INTO `t_schedulegroup` VALUES ('9', '3');
INSERT INTO `t_schedulegroup` VALUES ('10', '3');
INSERT INTO `t_schedulegroup` VALUES ('11', '3');
INSERT INTO `t_schedulegroup` VALUES ('12', '3');
INSERT INTO `t_schedulegroup` VALUES ('13', '3');
INSERT INTO `t_schedulegroup` VALUES ('15', '3');
INSERT INTO `t_schedulegroup` VALUES ('16', '3');
INSERT INTO `t_schedulegroup` VALUES ('17', '3');
INSERT INTO `t_schedulegroup` VALUES ('18', '3');
INSERT INTO `t_schedulegroup` VALUES ('19', '3');
INSERT INTO `t_schedulegroup` VALUES ('20', '3');
INSERT INTO `t_schedulegroup` VALUES ('21', '3');
INSERT INTO `t_schedulegroup` VALUES ('22', '3');
INSERT INTO `t_schedulegroup` VALUES ('23', '3');
INSERT INTO `t_schedulegroup` VALUES ('24', '3');
INSERT INTO `t_schedulegroup` VALUES ('25', '3');
INSERT INTO `t_schedulegroup` VALUES ('26', '3');
INSERT INTO `t_schedulegroup` VALUES ('27', '3');
INSERT INTO `t_schedulegroup` VALUES ('28', '3');
INSERT INTO `t_schedulegroup` VALUES ('29', '3');
INSERT INTO `t_schedulegroup` VALUES ('31', '3');
INSERT INTO `t_schedulegroup` VALUES ('32', '3');
INSERT INTO `t_schedulegroup` VALUES ('33', '3');
INSERT INTO `t_schedulegroup` VALUES ('34', '3');
INSERT INTO `t_schedulegroup` VALUES ('35', '3');
INSERT INTO `t_schedulegroup` VALUES ('38', '1');
INSERT INTO `t_schedulegroup` VALUES ('39', '3');
INSERT INTO `t_schedulegroup` VALUES ('39', '7');
INSERT INTO `t_schedulegroup` VALUES ('40', '1');
INSERT INTO `t_schedulegroup` VALUES ('40', '3');
INSERT INTO `t_schedulegroup` VALUES ('40', '6');
INSERT INTO `t_schedulegroup` VALUES ('40', '7');
INSERT INTO `t_setting` VALUES ('comblist', 'class', '1', 'qqqqqqqqqq', '123');
INSERT INTO `t_setting` VALUES ('comblist', 'class', '10', '株主', '0');
INSERT INTO `t_setting` VALUES ('comblist', 'class', '11', '可能天明', '26');
INSERT INTO `t_setting` VALUES ('comblist', 'class', '12', 'qwe', '99');
INSERT INTO `t_setting` VALUES ('comblist', 'class', '2', '取締役社長', '2');
INSERT INTO `t_setting` VALUES ('comblist', 'class', '3', '部長', '3');
INSERT INTO `t_setting` VALUES ('comblist', 'class', '4', '課長', '3');
INSERT INTO `t_setting` VALUES ('comblist', 'class', '5', 'リーダー', '5');
INSERT INTO `t_setting` VALUES ('comblist', 'class', '6', 'メンバー', '6');
INSERT INTO `t_setting` VALUES ('comblist', 'class', '7', '給料泥棒（そんなこと', '16');
INSERT INTO `t_setting` VALUES ('comblist', 'kaihai', '0', '有効のみ', '1');
INSERT INTO `t_setting` VALUES ('comblist', 'kaihai', '1', '無効のみ', '2');
INSERT INTO `t_setting` VALUES ('comblist', 'kaihai', '2', '有効/無効', '3');
INSERT INTO `t_setting` VALUES ('comblist', 'kana', 'between \'ｦ\' and \'ﾟ\'', '条件無し', '1');
INSERT INTO `t_setting` VALUES ('comblist', 'kana', 'between \'ｱ\' and \'ｵ\'', 'あ行', '2');
INSERT INTO `t_setting` VALUES ('comblist', 'kana', 'between \'ｶ\' and \'ｺ\'', 'か行', '3');
INSERT INTO `t_setting` VALUES ('comblist', 'kana', 'between \'ｻ\' and \'ｿ\'', 'さ行', '4');
INSERT INTO `t_setting` VALUES ('comblist', 'kana', 'between \'ﾀ\' and \'ﾄ\'', 'た行', '5');
INSERT INTO `t_setting` VALUES ('comblist', 'kana', 'between \'ﾅ\' and \'ﾉ\'', 'な行', '6');
INSERT INTO `t_setting` VALUES ('comblist', 'kana', 'between \'ﾊ\' and \'ﾎ\'', 'は行', '7');
INSERT INTO `t_setting` VALUES ('comblist', 'kana', 'between \'ﾏ\' and \'ﾓ\'', 'ま行', '8');
INSERT INTO `t_setting` VALUES ('comblist', 'kana', 'between \'ﾔ\' and \'ﾖ\'', 'や行', '9');
INSERT INTO `t_setting` VALUES ('comblist', 'kana', 'between \'ﾗ\' and \'ﾛ\'', 'ら行', '10');
INSERT INTO `t_setting` VALUES ('comblist', 'kana', 'between \'ﾜ\' and \'ﾝ\'', 'わ行', '11');
INSERT INTO `t_setting` VALUES ('system', 'authority', 'groupexecute', null, '2');
INSERT INTO `t_setting` VALUES ('system', 'authority', 'groupmarge', null, '0');
INSERT INTO `t_setting` VALUES ('system', 'authority', 'kogutikanri', null, '4');
INSERT INTO `t_setting` VALUES ('system', 'authority', 'mastermainte', null, '1');
INSERT INTO `t_tips` VALUES ('3', '2009/12/01', '2010/01/31', '2009/12/28～2010/01/03　\r\n年末年始休暇', '2', null, null, null, null, null, '3');
INSERT INTO `t_tips` VALUES ('5', '2010/01/01', '2010/03/31', '頑張っていきましょう！！', '2', '9', null, null, null, null, '1');
INSERT INTO `t_tips` VALUES ('-1', '2010/04/01', '2010/04/30', '新規追加しました。', '2', '13', null, null, null, null, '0');
INSERT INTO `t_tips` VALUES ('10', '2010/04/01', '2010/04/30', '編集テスト', '2', null, null, null, null, null, '3');
INSERT INTO `t_tips` VALUES ('14', '2010/04/01', '2010/04/30', 'bbbb', '2', '14', null, null, null, null, '3');
INSERT INTO `t_tips` VALUES ('15', '2010/03/01', '2010/03/31', 'aaaaa', '2', null, null, null, null, null, '2');
INSERT INTO `t_tips` VALUES ('16', '2010/04/01', '2010/04/30', '稼動高めの仕事してきます。', '2', '15', null, null, null, null, '3');
INSERT INTO `t_tips` VALUES ('17', '2010/04/16', '2010/04/30', 'テストがんばってきてください。', '2', '16', null, null, null, null, '4');
INSERT INTO `t_uploadfile` VALUES ('9', 'uploadfileDelete_Events.txt', 0x2D2D202D2D2D2D2D2D2D2D2D2D2D2D2D2D2D2D2D2D2D2D2D2D2D2D2D2D2D2D0D0A2D2D20424547494E814581458145454E4493E082CC3B82AA8BE690D882E895B68E9A82C694468EAF82B382EA82C882A282E682A482C981418BE690D882E895B68E9A82F02F2F82C995CF8D580D0A2D2D202D2D2D2D2D2D2D2D2D2D2D2D2D2D2D2D2D2D2D2D2D2D2D2D2D2D2D2D0D0A44454C494D49544552202F2F0D0A0D0A2D2D202D2D2D2D2D2D2D2D2D2D2D2D2D2D2D2D2D2D2D2D2D2D2D2D2D2D2D2D0D0A2D2D204576656E74202275706C6F616466696C6544656C6574655F4576656E7473222044444C0D0A2D2D202D2D2D2D2D2D2D2D2D2D2D2D2D2D2D2D2D2D2D2D2D2D2D2D2D2D2D2D0D0A0D0A435245415445204556454E54206075706C6F616466696C6544656C6574655F4576656E747360204F4E205343484544554C452045564552592031204D4F4E5448205354415254532027323031302D30312D30312031303A30303A303027204F4E20434F4D504C4554494F4E20505245534552564520454E41424C4520434F4D4D454E54202796888C8E3193FA82C9836783738362834E835882CC8C668DDA8F4997B993FA82AA5C725C6E8CBB8DDD82CC93FA957482E682E8914F82CC836783738362834E835882C99359957482B382EA82C482A282E9837483408343838B82F08DED8F9C82B581415C725C6E836783738362834E83588FEE95F182F08D58905681422720444F20424547494E0D0D0A0944454C4554452046524F4D0D0D0A0909745F75706C6F616466696C650D0D0A0957484552450D0D0A090966696C655F494420494E0D0D0A090909280D0D0A0909090953454C4543540D0D0A090909090966696C65315F49440D0D0A0909090946524F4D0D0D0A0909090909745F746970730D0D0A0909090957484552450D0D0A090909090966696C65315F4944206973206E6F74206E756C6C0D0D0A09090909616E640D0D0A0909090909656E6444617465203C20646174655F666F726D6174286E6F7728292C202725592F256D2F256427290D0D0A090909290D0D0A096F720D0D0A090966696C655F494420494E0D0D0A090909280D0D0A0909090953454C4543540D0D0A090909090966696C65325F49440D0D0A0909090946524F4D0D0D0A0909090909745F746970730D0D0A0909090957484552450D0D0A090909090966696C65325F4944206973206E6F74206E756C6C0D0D0A09090909616E640D0D0A0909090909656E6444617465203C20646174655F666F726D6174286E6F7728292C202725592F256D2F256427290D0D0A090909290D0D0A096F720D0D0A090966696C655F494420494E0D0D0A090909280D0D0A0909090953454C4543540D0D0A090909090966696C65335F49440D0D0A0909090946524F4D0D0D0A0909090909745F746970730D0D0A0909090957484552450D0D0A090909090966696C65335F4944206973206E6F74206E756C6C0D0D0A09090909616E640D0D0A0909090909656E6444617465203C20646174655F666F726D6174286E6F7728292C202725592F256D2F256427290D0D0A090909290D0D0A096F720D0D0A090966696C655F494420494E0D0D0A090909280D0D0A0909090953454C4543540D0D0A090909090966696C65345F49440D0D0A0909090946524F4D0D0D0A0909090909745F746970730D0D0A0909090957484552450D0D0A090909090966696C65345F4944206973206E6F74206E756C6C0D0D0A09090909616E640D0D0A0909090909656E6444617465203C20646174655F666F726D6174286E6F7728292C202725592F256D2F256427290D0D0A090909290D0D0A096F720D0D0A090966696C655F494420494E0D0D0A090909280D0D0A0909090953454C4543540D0D0A090909090966696C65355F49440D0D0A0909090946524F4D0D0D0A0909090909745F746970730D0D0A0909090957484552450D0D0A090909090966696C65355F4944206973206E6F74206E756C6C0D0D0A09090909616E640D0D0A0909090909656E6444617465203C20646174655F666F726D6174286E6F7728292C202725592F256D2F256427290D0D0A090909293B0D0D0A095550444154450D0D0A0909745F746970730D0D0A09534554200D0D0A090966696C65315F4944203D206E756C6C2C0D0D0A090966696C65325F4944203D206E756C6C2C0D0D0A090966696C65335F4944203D206E756C6C2C0D0D0A090966696C65345F4944203D206E756C6C2C0D0D0A090966696C65355F4944203D206E756C6C0D0D0A0957484552450D0D0A0909280D0D0A09090966696C65315F4944206973206E6F74206E756C6C0D0D0A09096F720D0D0A09090966696C65325F4944206973206E6F74206E756C6C0D0D0A09096F720D0D0A09090966696C65335F4944206973206E6F74206E756C6C0D0D0A09096F720D0D0A09090966696C65345F4944206973206E6F74206E756C6C0D0D0A09096F720D0D0A09090966696C65355F4944206973206E6F74206E756C6C0D0D0A0909290D0D0A09616E640D0D0A0909745F746970732E656E6444617465203C20646174655F666F726D6174286E6F7728292C202725592F256D2F25642729203B0D0D0A454E442F2F0D0A0D0A2D2D202D2D2D2D2D2D2D2D2D2D2D2D2D2D2D2D2D2D2D2D2D2D2D2D2D2D2D2D0D0A2D2D208BE690D882E895B68E9A82F03B82C996DF82B70D0A2D2D202D2D2D2D2D2D2D2D2D2D2D2D2D2D2D2D2D2D2D2D2D2D2D2D2D2D2D2D0D0A44454C494D49544552203B0D0A, '2', '2010/03/12 11:06:47', '2');
INSERT INTO `t_uploadfile` VALUES ('13', 'test.txt', 0x74657374, '1', '2010/03/29 14:10:09', '2');
INSERT INTO `t_uploadfile` VALUES ('14', 'test.txt', 0x74657374, '1', '2010/03/30 09:59:00', '2');
