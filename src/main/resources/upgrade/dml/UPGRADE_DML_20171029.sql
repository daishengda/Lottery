DROP PROCEDURE IF EXISTS insert_tbl_lottery_miss_group_pro;
DELIMITER $
CREATE PROCEDURE insert_tbl_lottery_miss_group_pro()
BEGIN
	IF NOT EXISTS(SELECT 1 FROM tbl_lottery_miss_group) THEN
			INSERT INTO `tbl_lottery_miss_group` VALUES ('1', '2', '01'),
			('2', '2', '02'),('3', '2', '03'),('4', '2', '04'),('5', '2', '05'), 
			('6', '2', '06'), ('7', '2', '07'), ('8', '2', '08'), ('9', '2', '09'), 
			('10', '2', '12'), ('11', '2', '13'), ('12', '2', '14'), ('13', '2', '15'), 
			('14', '2', '16'), ('15', '2', '17'), ('16', '2', '18'), ('17', '2', '19'), 
			('18', '2', '23'), ('19', '2', '24'), ('20', '2', '25'), ('21', '2', '26'), ('22', '2', '27'), 
			('23', '2', '28'), ('24', '2', '29'), ('25', '2', '34'), ('26', '2', '35'), ('27', '2', '36'), 
			('28', '2', '37'), ('29', '2', '38'), ('30', '2', '39'), ('31', '2', '45'), ('32', '2', '46'), 
			('33', '2', '47'), ('34', '2', '48'), ('35', '2', '49'), ('36', '2', '56'), ('37', '2', '57'), 
			('38', '2', '58'), ('39', '2', '59'), ('40', '2', '67'), ('41', '2', '68'), ('42', '2', '69'), 
			('43', '2', '78'), ('44', '2', '79'), ('45', '2', '89'), ('46', '3', '012'), ('47', '3', '013'), 
			('48', '3', '014'), ('49', '3', '015'), ('50', '3', '016'), ('51', '3', '017'), ('52', '3', '018'), 
			('53', '3', '019'), ('54', '3', '023'), ('55', '3', '024'), ('56', '3', '025'), ('57', '3', '026'), 
			('58', '3', '027'), ('59', '3', '028'), ('60', '3', '029'), ('61', '3', '034'), ('62', '3', '035'), 
			('63', '3', '036'), ('64', '3', '037'), ('65', '3', '038'), ('66', '3', '039'), ('67', '3', '045'), 
			('68', '3', '046'), ('69', '3', '047'), ('70', '3', '048'), ('71', '3', '049'), ('72', '3', '056'), 
			('73', '3', '057'), ('74', '3', '058'), ('75', '3', '059'), ('76', '3', '067'), ('77', '3', '068'), 
			('78', '3', '069'), ('79', '3', '078'), ('80', '3', '079'), ('81', '3', '089'), ('82', '3', '123'), 
			('83', '3', '124'), ('84', '3', '125'), ('85', '3', '126'), ('86', '3', '127'), ('87', '3', '128'), 
			('88', '3', '129'), ('89', '3', '134'), ('90', '3', '135'), ('91', '3', '136'), ('92', '3', '137'), 
			('93', '3', '138'), ('94', '3', '139'), ('95', '3', '145'), ('96', '3', '146'), ('97', '3', '147'), 
			('98', '3', '148'), ('99', '3', '149'), ('100', '3', '156'), ('101', '3', '157'), ('102', '3', '158'), 
			('103', '3', '159'), ('104', '3', '167'), ('105', '3', '168'), ('106', '3', '169'), ('107', '3', '178'), 
			('108', '3', '179'), ('109', '3', '189'), ('110', '3', '234'), ('111', '3', '235'), ('112', '3', '236'), 
			('113', '3', '237'), ('114', '3', '238'), ('115', '3', '239'), ('116', '3', '245'), ('117', '3', '246'), 
			('118', '3', '247'), ('119', '3', '248'), ('120', '3', '249'), ('121', '3', '256'), ('122', '3', '257'), 
			('123', '3', '258'), ('124', '3', '259'), ('125', '3', '267'), ('126', '3', '268'), ('127', '3', '269'), 
			('128', '3', '278'), ('129', '3', '279'), ('130', '3', '289'), ('131', '3', '345'), ('132', '3', '346'), 
			('133', '3', '347'), ('134', '3', '348'), ('135', '3', '349'), ('136', '3', '356'), ('137', '3', '357'), 
			('138', '3', '358'), ('139', '3', '359'), ('140', '3', '367'), ('141', '3', '368'), ('142', '3', '369'), 
			('143', '3', '378'), ('144', '3', '379'), ('145', '3', '389'), ('146', '3', '456'), ('147', '3', '457'), 
			('148', '3', '458'), ('149', '3', '459'), ('150', '3', '467'), ('151', '3', '468'), ('152', '3', '469'), 
			('153', '3', '478'), ('154', '3', '479'), ('155', '3', '489'), ('156', '3', '567'), ('157', '3', '568'), 
			('158', '3', '569'), ('159', '3', '578'), ('160', '3', '579'), ('161', '3', '589'), ('162', '3', '678'), 
			('163', '3', '679'), ('164', '3', '689'), ('165', '3', '789'), ('166', '4', '0123'), ('167', '4', '0124'), 
			('168', '4', '0125'), ('169', '4', '0126'), ('170', '4', '0127'), ('171', '4', '0128'), ('172', '4', '0129'), 
			('173', '4', '0134'), ('174', '4', '0135'), ('175', '4', '0136'), ('176', '4', '0137'), ('177', '4', '0138'), 
			('178', '4', '0139'), ('179', '4', '0145'), ('180', '4', '0146'), ('181', '4', '0147'), ('182', '4', '0148'), 
			('183', '4', '0149'), ('184', '4', '0156'), ('185', '4', '0157'), ('186', '4', '0158'), ('187', '4', '0159'), 
			('188', '4', '0167'), ('189', '4', '0168'), ('190', '4', '0169'), ('191', '4', '0178'), ('192', '4', '0179'), 
			('193', '4', '0189'), ('194', '4', '0234'), ('195', '4', '0235'), ('196', '4', '0236'), ('197', '4', '0237'), 
			('198', '4', '0238'), ('199', '4', '0239'), ('200', '4', '0245'), ('201', '4', '0246'), ('202', '4', '0247'), 
			('203', '4', '0248'), ('204', '4', '0249'), ('205', '4', '0256'), ('206', '4', '0257'), ('207', '4', '0258'), 
			('208', '4', '0259'), ('209', '4', '0267'), ('210', '4', '0268'), ('211', '4', '0269'), ('212', '4', '0278'), 
			('213', '4', '0279'), ('214', '4', '0289'), ('215', '4', '0345'), ('216', '4', '0346'), ('217', '4', '0347'), 
			('218', '4', '0348'), ('219', '4', '0349'), ('220', '4', '0356'), ('221', '4', '0357'), ('222', '4', '0358'), 
			('223', '4', '0359'), ('224', '4', '0367'), ('225', '4', '0368'), ('226', '4', '0369'), ('227', '4', '0378'), ('228', '4', '0379'), 
			('229', '4', '0389'), ('230', '4', '0456'), ('231', '4', '0457'), ('232', '4', '0458'), ('233', '4', '0459'), ('234', '4', '0467'), 
			('235', '4', '0468'), ('236', '4', '0469'), ('237', '4', '0478'), ('238', '4', '0479'), ('239', '4', '0489'), ('240', '4', '0567'), 
			('241', '4', '0568'), ('242', '4', '0569'), ('243', '4', '0578'), ('244', '4', '0579'), ('245', '4', '0589'), ('246', '4', '0678'), 
			('247', '4', '0679'), ('248', '4', '0689'), ('249', '4', '0789'), ('250', '4', '1234'), ('251', '4', '1235'), ('252', '4', '1236'), 
			('253', '4', '1237'), ('254', '4', '1238'), ('255', '4', '1239'), ('256', '4', '1245'), ('257', '4', '1246'), ('258', '4', '1247'), 
			('259', '4', '1248'), ('260', '4', '1249'), ('261', '4', '1256'), ('262', '4', '1257'), ('263', '4', '1258'), ('264', '4', '1259'), 
			('265', '4', '1267'), ('266', '4', '1268'), ('267', '4', '1269'), ('268', '4', '1278'), ('269', '4', '1279'), ('270', '4', '1289'), 
			('271', '4', '1345'), ('272', '4', '1346'), ('273', '4', '1347'), ('274', '4', '1348'), ('275', '4', '1349'), ('276', '4', '1356'), 
			('277', '4', '1357'), ('278', '4', '1358'), ('279', '4', '1359'), ('280', '4', '1367'), ('281', '4', '1368'), ('282', '4', '1369'), 
			('283', '4', '1378'), ('284', '4', '1379'), ('285', '4', '1389'), ('286', '4', '1456'), ('287', '4', '1457'), ('288', '4', '1458'), 
			('289', '4', '1459'), ('290', '4', '1467'), ('291', '4', '1468'), ('292', '4', '1469'), ('293', '4', '1478'), ('294', '4', '1479'), 
			('295', '4', '1489'), ('296', '4', '1567'), ('297', '4', '1568'), ('298', '4', '1569'), ('299', '4', '1578'), ('300', '4', '1579'), 
			('301', '4', '1589'), ('302', '4', '1678'), ('303', '4', '1679'), ('304', '4', '1689'), ('305', '4', '1789'), ('306', '4', '2345'), 
			('307', '4', '2346'), ('308', '4', '2347'), ('309', '4', '2348'), ('310', '4', '2349'), ('311', '4', '2356'), ('312', '4', '2357'), 
			('313', '4', '2358'), ('314', '4', '2359'), ('315', '4', '2367'), ('316', '4', '2368'), ('317', '4', '2369'), ('318', '4', '2378'), 
			('319', '4', '2379'), ('320', '4', '2389'), ('321', '4', '2456'), ('322', '4', '2457'), ('323', '4', '2458'), ('324', '4', '2459'), 
			('325', '4', '2467'), ('326', '4', '2468'), ('327', '4', '2469'), ('328', '4', '2478'), ('329', '4', '2479'), ('330', '4', '2489'), 
			('331', '4', '2567'), ('332', '4', '2568'), ('333', '4', '2569'), ('334', '4', '2578'), ('335', '4', '2579'), ('336', '4', '2589'), 
			('337', '4', '2678'), ('338', '4', '2679'), ('339', '4', '2689'), ('340', '4', '2789'), ('341', '4', '3456'), ('342', '4', '3457'), 
			('343', '4', '3458'), ('344', '4', '3459'), ('345', '4', '3467'), ('346', '4', '3468'), ('347', '4', '3469'), ('348', '4', '3478'), 
			('349', '4', '3479'), ('350', '4', '3489'), ('351', '4', '3567'), ('352', '4', '3568'), ('353', '4', '3569'), ('354', '4', '3578'), 
			('355', '4', '3579'), ('356', '4', '3589'), ('357', '4', '3678'), ('358', '4', '3679'), ('359', '4', '3689'), ('360', '4', '3789'), 
			('361', '4', '4567'), ('362', '4', '4568'), ('363', '4', '4569'), ('364', '4', '4578'), ('365', '4', '4579'), ('366', '4', '4589'), 
			('367', '4', '4678'), ('368', '4', '4679'), ('369', '4', '4689'), ('370', '4', '4789'), ('371', '4', '5678'), ('372', '4', '5679'), 
			('373', '4', '5689'), ('374', '4', '5789'), ('375', '4', '6789'), ('376', '5', '01234'), ('377', '5', '01235'), ('378', '5', '01236'), 
			('379', '5', '01237'), ('380', '5', '01238'), ('381', '5', '01239'), ('382', '5', '01245'), ('383', '5', '01246'), ('384', '5', '01247'), 
			('385', '5', '01248'), ('386', '5', '01249'), ('387', '5', '01256'), ('388', '5', '01257'), ('389', '5', '01258'), ('390', '5', '01259'), 
			('391', '5', '01267'), ('392', '5', '01268'), ('393', '5', '01269'), ('394', '5', '01278'), ('395', '5', '01279'), ('396', '5', '01289'), 
			('397', '5', '01345'), ('398', '5', '01346'), ('399', '5', '01347'), ('400', '5', '01348'), ('401', '5', '01349'), ('402', '5', '01356'), 
			('403', '5', '01357'), ('404', '5', '01358'), ('405', '5', '01359'), ('406', '5', '01367'), ('407', '5', '01368'), ('408', '5', '01369'), 
			('409', '5', '01378'), ('410', '5', '01379'), ('411', '5', '01389'), ('412', '5', '01456'), ('413', '5', '01457'), ('414', '5', '01458'), 
			('415', '5', '01459'), ('416', '5', '01467'), ('417', '5', '01468'), ('418', '5', '01469'), ('419', '5', '01478'), ('420', '5', '01479'), 
			('421', '5', '01489'), ('422', '5', '01567'), ('423', '5', '01568'), ('424', '5', '01569'), ('425', '5', '01578'), ('426', '5', '01579'), 
			('427', '5', '01589'), ('428', '5', '01678'), ('429', '5', '01679'), ('430', '5', '01689'), ('431', '5', '01789'), ('432', '5', '02345'), 
			('433', '5', '02346'), ('434', '5', '02347'), ('435', '5', '02348'), ('436', '5', '02349'), ('437', '5', '02356'), ('438', '5', '02357'), 
			('439', '5', '02358'), ('440', '5', '02359'), ('441', '5', '02367'), ('442', '5', '02368'), ('443', '5', '02369'), ('444', '5', '02378'), 
			('445', '5', '02379'), ('446', '5', '02389'), ('447', '5', '02456'), ('448', '5', '02457'), ('449', '5', '02458'), ('450', '5', '02459'), 
			('451', '5', '02467'), ('452', '5', '02468'), ('453', '5', '02469'), ('454', '5', '02478'), ('455', '5', '02479'), ('456', '5', '02489'), 
			('457', '5', '02567'), ('458', '5', '02568'), ('459', '5', '02569'), ('460', '5', '02578'), ('461', '5', '02579'), ('462', '5', '02589'), 
			('463', '5', '02678'), ('464', '5', '02679'), ('465', '5', '02689'), ('466', '5', '02789'), ('467', '5', '03456'), ('468', '5', '03457'), 
			('469', '5', '03458'), ('470', '5', '03459'), ('471', '5', '03467'), ('472', '5', '03468'), ('473', '5', '03469'), ('474', '5', '03478'), 
			('475', '5', '03479'), ('476', '5', '03489'), ('477', '5', '03567'), ('478', '5', '03568'), ('479', '5', '03569'), ('480', '5', '03578'), 
			('481', '5', '03579'), ('482', '5', '03589'), ('483', '5', '03678'), ('484', '5', '03679'), ('485', '5', '03689'), ('486', '5', '03789'), 
			('487', '5', '04567'), ('488', '5', '04568'), ('489', '5', '04569'), ('490', '5', '04578'), ('491', '5', '04579'), ('492', '5', '04589'), 
			('493', '5', '04678'), ('494', '5', '04679'), ('495', '5', '04689'), ('496', '5', '04789'), ('497', '5', '05678'), ('498', '5', '05679'), 
			('499', '5', '05689'), ('500', '5', '05789'), ('501', '5', '06789'), ('502', '5', '12345'), ('503', '5', '12346'), ('504', '5', '12347'), 
			('505', '5', '12348'), ('506', '5', '12349'), ('507', '5', '12356'), ('508', '5', '12357'), ('509', '5', '12358'), ('510', '5', '12359'), 
			('511', '5', '12367'), ('512', '5', '12368'), ('513', '5', '12369'), ('514', '5', '12378'), ('515', '5', '12379'), ('516', '5', '12389'), 
			('517', '5', '12456'), ('518', '5', '12457'), ('519', '5', '12458'), ('520', '5', '12459'), ('521', '5', '12467'), ('522', '5', '12468'), 
			('523', '5', '12469'), ('524', '5', '12478'), ('525', '5', '12479'), ('526', '5', '12489'), ('527', '5', '12567'), ('528', '5', '12568'), 
			('529', '5', '12569'), ('530', '5', '12578'), ('531', '5', '12579'), ('532', '5', '12589'), ('533', '5', '12678'), ('534', '5', '12679'), 
			('535', '5', '12689'), ('536', '5', '12789'), ('537', '5', '13456'), ('538', '5', '13457'), ('539', '5', '13458'), ('540', '5', '13459'), 
			('541', '5', '13467'), ('542', '5', '13468'), ('543', '5', '13469'), ('544', '5', '13478'), ('545', '5', '13479'), ('546', '5', '13489'), 
			('547', '5', '13567'), ('548', '5', '13568'), ('549', '5', '13569'), ('550', '5', '13578'), ('551', '5', '13579'), ('552', '5', '13589'), 
			('553', '5', '13678'), ('554', '5', '13679'), ('555', '5', '13689'), ('556', '5', '13789'), ('557', '5', '14567'), ('558', '5', '14568'), 
			('559', '5', '14569'), ('560', '5', '14578'), ('561', '5', '14579'), ('562', '5', '14589'), ('563', '5', '14678'), ('564', '5', '14679'), 
			('565', '5', '14689'), ('566', '5', '14789'), ('567', '5', '15678'), ('568', '5', '15679'), ('569', '5', '15689'), ('570', '5', '15789'), 
			('571', '5', '16789'), ('572', '5', '23456'), ('573', '5', '23457'), ('574', '5', '23458'), ('575', '5', '23459'), ('576', '5', '23467'), 
			('577', '5', '23468'), ('578', '5', '23469'), ('579', '5', '23478'), ('580', '5', '23479'), ('581', '5', '23489'), ('582', '5', '23567'), 
			('583', '5', '23568'), ('584', '5', '23569'), ('585', '5', '23578'), ('586', '5', '23579'), ('587', '5', '23589'), ('588', '5', '23678'), 
			('589', '5', '23679'), ('590', '5', '23689'), ('591', '5', '23789'), ('592', '5', '24567'), ('593', '5', '24568'), ('594', '5', '24569'), 
			('595', '5', '24578'), ('596', '5', '24579'), ('597', '5', '24589'), ('598', '5', '24678'), ('599', '5', '24679'), ('600', '5', '24689'), 
			('601', '5', '24789'), ('602', '5', '25678'), ('603', '5', '25679'), ('604', '5', '25689'), ('605', '5', '25789'), ('606', '5', '26789'), 
			('607', '5', '34567'), ('608', '5', '34568'), ('609', '5', '34569'), ('610', '5', '34578'), ('611', '5', '34579'), ('612', '5', '34589'), 
			('613', '5', '34678'), ('614', '5', '34679'), ('615', '5', '34689'), ('616', '5', '34789'), ('617', '5', '35678'), ('618', '5', '35679'), 
			('619', '5', '35689'), ('620', '5', '35789'), ('621', '5', '36789'), ('622', '5', '45678'), ('623', '5', '45679'), ('624', '5', '45689'), 
			('625', '5', '45789'), ('626', '5', '46789'), ('627', '5', '56789');
	END IF;
END
$
DELIMITER ;
CALL insert_tbl_lottery_miss_group_pro();
DROP PROCEDURE insert_tbl_lottery_miss_group_pro;