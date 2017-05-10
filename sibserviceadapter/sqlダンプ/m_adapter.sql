/*
MySQL Data Transfer
Source Host: 192.168.220.128
Source Database: sibadapter
Target Host: 192.168.220.128
Target Database: sibadapter
Date: 2012/06/22 14:42:17
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for m_adapter
-- ----------------------------
DROP TABLE IF EXISTS `m_adapter`;
CREATE TABLE `m_adapter` (
  `serviceType` varchar(20) NOT NULL,
  `serviceURL` varchar(250) NOT NULL,
  `serviceTitle` varchar(30) NOT NULL,
  `orderSEQ` int(11) NOT NULL,
  `kaihai` int(11) NOT NULL,
  `AddDate` varchar(19) NOT NULL,
  `AddID` int(11) NOT NULL,
  `UpdDate` varchar(19) DEFAULT NULL,
  `UpdID` int(11) DEFAULT NULL,
  PRIMARY KEY (`serviceType`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records 
-- ----------------------------
INSERT INTO `m_adapter` VALUES ('userAuth', 'http://localhost:8080/sib/view/adapter/userinfo/userauth.html', 'ユーザ認証', '10', '0', '2012/06/21 00:00:00', '2', null, null);
INSERT INTO `m_adapter` VALUES ('companyInfo', 'http://localhost:8080/sib/view/adapter/companyinfo/getcompanyinfo.html', '会社情報', '30', '0', '2012/06/21 00:00:00', '2', null, null);
INSERT INTO `m_adapter` VALUES ('userInfo', 'http://localhost:8080/sib/view/adapter/userinfo/userinfo.html', 'ユーザ情報', '20', '0', '2012/06/21 00:00:00', '2', null, null);
INSERT INTO `m_adapter` VALUES ('combList', 'http://localhost:8080/sib/view/adapter/comblist/getcomblist.html', 'コンボリスト情報', '40', '0', '2012/06/22 00:00:00', '2', null, null);
