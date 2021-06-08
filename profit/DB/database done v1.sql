-- MySQL dump 10.13  Distrib 8.0.23, for Win64 (x86_64)
--
-- Host: localhost    Database: hotelmina
-- ------------------------------------------------------
-- Server version	8.0.23

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `checkingoutroomdamaged`
--

DROP TABLE IF EXISTS `checkingoutroomdamaged`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `checkingoutroomdamaged` (
  `idcheckingoutroomdamaded` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `idcheckoutroom` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `listproductdamaded` varchar(1000) COLLATE utf8_unicode_ci DEFAULT NULL,
  `sumaryindemnify` decimal(18,0) NOT NULL,
  `idstaffchecking` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `idticketbooking` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `status` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`idcheckingoutroomdamaded`),
  KEY `f_damaded_staff` (`idstaffchecking`),
  KEY `f_damaded_outroom` (`idcheckoutroom`),
  CONSTRAINT `f_damaded_outroom` FOREIGN KEY (`idcheckoutroom`) REFERENCES `ticketcheckoutroom` (`idticketcheckoutroom`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `f_damaded_staff` FOREIGN KEY (`idstaffchecking`) REFERENCES `staff` (`idstaff`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `checkingoutroomdamaged`
--

LOCK TABLES `checkingoutroomdamaged` WRITE;
/*!40000 ALTER TABLE `checkingoutroomdamaged` DISABLE KEYS */;
INSERT INTO `checkingoutroomdamaged` VALUES ('1614056889522','2021-02-24T22:31:56.176249600','tivi, quat',300000,'staff_07','2021-02-23T00:10:39.172618600','Done'),('1614056894197','2021-02-24T20:50:14.065531900','',0,'staff_07','2021-02-21T16:31:01.102493100','Done'),('1614056898819','2021-02-23T22:17:54.017793300','abc list',568909,'staff_04','2021-02-21T23:57:19.711801','Done'),('1614093574043','2021-02-24T00:36:34.841440500','',0,'staff_07','2021-02-23T22:19:25.351705700','Done'),('1614103818618','2021-02-24T01:10:53.682290','',0,'staff_07','2021-02-24T01:08:42.530350800','Done'),('1614181601954','2021-02-24T22:50:42.685980400','0',0,'staff_07','2021-02-24T22:33:05.023939500','Done'),('1614181862387','2021-02-24T22:57:02.604782200','0',0,'staff_07','2021-02-24T22:50:58.768480400','Done'),('1614182425493','2021-02-24T23:02:58.920792800','0',0,'staff_07','2021-02-24T22:51:57.123853200','Done'),('1614182809009','2021-02-24T23:08:54.414656500','tivi, quat',500000,'staff_07','2021-02-24T23:06:44.813961600','Done'),('1614184921155','2021-02-26T19:33:37.097728600','0',0,'staff_07','2021-02-24T23:09:22.135111800','Done'),('1614185356585','2021-02-26T14:57:44.225698300','tivi, quat',300000,'staff_07','2021-02-24T23:09:34.727253800','Done'),('1614185729389','2021-02-26T19:31:50.488968600','tivi, quat',500000,'staff_07','2021-02-24T23:36:45.886701300','Done'),('1614255215758','2021-02-27T10:09:04.695105200','tivi, quat',500000,'staff_07','2021-02-24T23:47:56.480227600','Done'),('1614309355772','2021-02-26T10:17:34.846010100','tivi',100000,'staff_07','2021-02-26T10:11:57.825156800','Done'),('1614342871795','2021-02-27T10:11:30.387460600','0',0,'staff_07','2021-02-26T19:33:54.339983600','Done');
/*!40000 ALTER TABLE `checkingoutroomdamaged` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dailyworking`
--

DROP TABLE IF EXISTS `dailyworking`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dailyworking` (
  `idtoday` date NOT NULL,
  `idstaffwork` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `timestart` time DEFAULT NULL,
  `timeend` time DEFAULT NULL,
  `note` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `idstaffmanagement` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `usernamestaff` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`idtoday`,`idstaffwork`),
  KEY `f_work_staff` (`idstaffwork`),
  KEY `f_work_today_staffmanagemenmt` (`idstaffmanagement`),
  CONSTRAINT `f_work_staff` FOREIGN KEY (`idstaffwork`) REFERENCES `staff` (`idstaff`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `f_work_today` FOREIGN KEY (`idtoday`) REFERENCES `datework` (`iddatework`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `f_work_today_staffmanagemenmt` FOREIGN KEY (`idstaffmanagement`) REFERENCES `staff` (`idstaff`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dailyworking`
--

LOCK TABLES `dailyworking` WRITE;
/*!40000 ALTER TABLE `dailyworking` DISABLE KEYS */;
INSERT INTO `dailyworking` VALUES ('2021-01-01','staff_01','07:08:16','19:08:16','1','staff_06','nguyen thi  vip'),('2021-01-01','staff_02','10:08:16','20:05:16','1','staff_06','nguyen thi  B'),('2021-01-01','staff_03','08:08:16','22:08:16','1','staff_06','nguyen thi C'),('2021-01-01','staff_04','05:08:16','19:08:16','1','staff_06','nguyen thi  D'),('2021-01-01','staff_05','10:08:16','16:08:16','1','staff_06','thinh manager'),('2021-01-01','staff_06','11:08:16','22:08:16','1','staff_06','admin'),('2021-01-01','staff_07','10:08:16','16:08:16','1','staff_06','user service A'),('2021-01-01','staff_11','01:08:16','22:08:16','1','staff_06','new chang 23455'),('2021-01-01','staff_13','10:08:16','15:08:16','1','staff_06','new change 11'),('2021-01-01','staff_14','10:08:16','12:08:16','1','staff_06','14 dfdasfds'),('2021-01-01','staff_15','13:08:16','10:08:16','1','staff_06','new 1111'),('2021-01-02','staff_01','04:54:20','17:54:20','1','staff_06','nguyen thi  vip'),('2021-01-02','staff_02','00:54:20','22:54:20','1','staff_06','nguyen thi  B'),('2021-01-02','staff_03','05:54:20','12:54:20','1','staff_06','nguyen thi C'),('2021-01-02','staff_04','00:54:20','15:54:20','1','staff_06','nguyen thi  D'),('2021-01-02','staff_05','00:54:20','19:54:20','1','staff_06','thinh manager'),('2021-01-02','staff_06','06:54:20','12:54:20','1','staff_06','admin'),('2021-01-02','staff_07','00:54:20','16:54:20','1','staff_06','user service A'),('2021-01-02','staff_11','00:54:20','16:54:20','1','staff_06','new chang 23455'),('2021-01-02','staff_13','00:54:20','12:54:20','1','staff_06','new change 11'),('2021-01-02','staff_14','07:54:20','12:54:20','1','staff_06','14 dfdasfds'),('2021-01-02','staff_15','00:54:20','17:54:20','1','staff_06','new 1111'),('2021-01-03','staff_01','01:52:30','18:52:30','1','staff_06','nguyen thi  vip'),('2021-01-03','staff_02','02:52:30','12:52:30','1','staff_06','nguyen thi  B'),('2021-01-03','staff_03','03:52:30','12:52:30','1','staff_06','nguyen thi C'),('2021-01-03','staff_04','00:52:30','17:52:30','1','staff_06','nguyen thi  D'),('2021-01-03','staff_05','07:52:30','12:52:30','1','staff_06','thinh manager'),('2021-01-03','staff_06','00:52:30','18:52:30','1','staff_06','admin'),('2021-01-03','staff_07','02:52:30','12:52:30','1','staff_06','user service A'),('2021-01-03','staff_11','09:52:30','12:52:30','1','staff_06','new chang 23455'),('2021-01-03','staff_13','00:52:30','21:52:30','1','staff_06','new change 11'),('2021-01-03','staff_14','00:52:30','17:52:30','1','staff_06','14 dfdasfds'),('2021-01-03','staff_15','00:52:30','13:52:30','1','staff_06','new 1111'),('2021-01-04','staff_01','04:31:58','13:31:58','1','staff_06','nguyen thi  vip'),('2021-01-04','staff_02','00:31:58','17:31:58','1','staff_06','nguyen thi  B'),('2021-01-04','staff_03','05:31:58','18:31:58','1','staff_06','nguyen thi C'),('2021-01-04','staff_04','03:31:58','22:31:58','1','staff_06','nguyen thi  D'),('2021-01-04','staff_05','10:31:58','17:31:58','1','staff_06','thinh manager'),('2021-01-04','staff_06','00:31:58','21:31:58','1','staff_06','admin'),('2021-01-04','staff_07','01:31:58','17:31:58','1','staff_06','user service A'),('2021-01-04','staff_11','01:31:58','17:31:58','1','staff_06','new chang 23455'),('2021-01-04','staff_13','05:31:58','15:31:58','1','staff_06','new change 11'),('2021-01-04','staff_14','05:31:58','15:31:58','1','staff_06','14 dfdasfds'),('2021-01-04','staff_15','10:31:58','14:31:58','1','staff_06','new 1111'),('2021-01-05','staff_01','01:07:43','17:07:43','1','staff_06','nguyen thi  vip'),('2021-01-05','staff_02','05:07:43','15:07:43','1','staff_06','nguyen thi  B'),('2021-01-05','staff_03','13:07:43','13:07:43','1','staff_03','nguyen thi C'),('2021-01-05','staff_04','01:07:43','13:07:43','1','staff_06','nguyen thi  D'),('2021-01-05','staff_05','03:07:43','13:07:43','1','staff_06','thinh manager'),('2021-01-05','staff_06','04:07:43','13:07:43','1','staff_06','admin'),('2021-01-05','staff_07','05:07:44','14:07:44','1','staff_06','user service A'),('2021-01-05','staff_11','04:07:44','13:07:44','1','staff_06','new chang 23455'),('2021-01-05','staff_13','01:07:44','20:07:44','1','staff_06','new change 11'),('2021-01-05','staff_14','01:07:44','16:07:44','1','staff_06','14 dfdasfds'),('2021-01-05','staff_15','00:07:44','16:07:44','1','staff_06','new 1111'),('2021-01-12','staff_01','10:08:48','10:08:48','1','staff_01','nguyen thi  vip'),('2021-01-12','staff_02','10:08:48','10:08:48','1','staff_02','nguyen thi  B'),('2021-01-12','staff_03','10:08:48','10:08:48','1','staff_03','nguyen thi C'),('2021-01-12','staff_04','10:08:48','10:08:48','1','staff_04','nguyen thi  D'),('2021-01-12','staff_05','10:08:48','10:08:48','1','staff_05','thinh manager'),('2021-01-12','staff_06','10:08:48','10:08:48','1','staff_06','admin'),('2021-01-12','staff_07','10:08:48','10:08:48','1','staff_07','user service A'),('2021-01-12','staff_11','10:08:48','10:08:48','1','staff_11','new chang 23455'),('2021-01-12','staff_13','10:08:48','10:08:48','1','staff_13','new change 11'),('2021-01-12','staff_14','10:08:48','10:08:48','1','staff_14','14 dfdasfds'),('2021-01-12','staff_15','10:08:48','10:08:48','1','staff_15','new 1111'),('2021-01-15','staff_01','10:32:04','10:32:04','1','staff_01','nguyen thi  vip'),('2021-01-15','staff_02','10:32:04','10:32:04','1','staff_02','nguyen thi  B'),('2021-01-15','staff_03','10:32:04','10:32:04','1','staff_03','nguyen thi C'),('2021-01-15','staff_04','10:32:04','10:32:04','1','staff_04','nguyen thi  D'),('2021-01-15','staff_05','10:32:04','10:32:04','1','staff_05','thinh manager'),('2021-01-15','staff_06','10:32:04','10:32:04','1','staff_06','admin'),('2021-01-15','staff_07','10:32:05','10:32:05','1','staff_07','user service A'),('2021-01-15','staff_11','10:32:05','10:32:05','1','staff_11','new chang 23455'),('2021-01-15','staff_13','10:32:05','10:32:05','1','staff_13','new change 11'),('2021-01-15','staff_14','10:32:05','10:32:05','1','staff_14','14 dfdasfds'),('2021-01-15','staff_15','10:32:05','10:32:05','1','staff_15','new 1111'),('2021-01-19','staff_01','10:09:18','10:09:18','1','staff_01','nguyen thi  vip'),('2021-01-19','staff_02','10:09:18','10:09:18','1','staff_02','nguyen thi  B'),('2021-01-19','staff_03','10:09:18','10:09:18','1','staff_03','nguyen thi C'),('2021-01-19','staff_04','10:09:18','10:09:18','1','staff_04','nguyen thi  D'),('2021-01-19','staff_05','10:09:18','10:09:18','1','staff_05','thinh manager'),('2021-01-19','staff_06','10:09:18','10:09:18','1','staff_06','admin'),('2021-01-19','staff_07','10:09:18','10:09:18','1','staff_07','user service A'),('2021-01-19','staff_11','10:09:18','10:09:18','1','staff_11','new chang 23455'),('2021-01-19','staff_13','10:09:18','10:09:18','1','staff_13','new change 11'),('2021-01-19','staff_14','10:09:18','10:09:18','1','staff_14','14 dfdasfds'),('2021-01-19','staff_15','10:09:18','10:09:18','1','staff_15','new 1111'),('2021-01-20','staff_01','10:07:34','10:07:34','1','staff_01','nguyen thi  vip'),('2021-01-20','staff_02','10:07:34','10:07:34','1','staff_02','nguyen thi  B'),('2021-01-20','staff_03','10:07:34','10:07:34','1','staff_03','nguyen thi C'),('2021-01-20','staff_04','10:07:34','10:07:34','1','staff_04','nguyen thi  D'),('2021-01-20','staff_05','10:07:34','10:07:34','1','staff_05','thinh manager'),('2021-01-20','staff_06','10:07:34','10:07:34','1','staff_06','admin'),('2021-01-20','staff_07','10:07:34','10:07:34','1','staff_07','user service A'),('2021-01-20','staff_11','10:07:34','10:07:34','1','staff_11','new chang 23455'),('2021-01-20','staff_13','10:07:34','10:07:34','1','staff_13','new change 11'),('2021-01-20','staff_14','10:07:34','10:07:34','1','staff_14','14 dfdasfds'),('2021-01-20','staff_15','10:07:34','10:07:34','1','staff_15','new 1111'),('2021-01-26','staff_01','10:07:28','10:07:28','1','staff_01','nguyen thi  vip'),('2021-01-26','staff_02','10:07:28','10:07:28','1','staff_02','nguyen thi  B'),('2021-01-26','staff_03','10:07:28','10:07:28','1','staff_03','nguyen thi C'),('2021-01-26','staff_04','10:07:28','10:07:28','1','staff_04','nguyen thi  D'),('2021-01-26','staff_05','10:07:28','10:07:28','1','staff_05','thinh manager'),('2021-01-26','staff_06','10:07:28','10:07:28','1','staff_06','admin'),('2021-01-26','staff_07','10:07:28','10:07:28','1','staff_07','user service A'),('2021-01-26','staff_11','10:07:28','10:07:28','1','staff_11','new chang 23455'),('2021-01-26','staff_13','10:07:28','10:07:28','1','staff_13','new change 11'),('2021-01-26','staff_14','10:07:28','10:07:28','1','staff_14','14 dfdasfds'),('2021-01-26','staff_15','10:07:28','10:07:28','1','staff_15','new 1111'),('2021-02-01','staff_01','05:08:03','17:08:03','1','staff_06','nguyen thi  vip'),('2021-02-01','staff_02','06:08:03','22:08:03','1','staff_06','nguyen thi  B'),('2021-02-01','staff_03','02:08:03','22:08:03','1','staff_06','nguyen thi C'),('2021-02-01','staff_04','16:08:03','10:08:03','1','staff_06','nguyen thi  D'),('2021-02-01','staff_05','10:08:03','21:08:03','1','staff_06','thinh manager'),('2021-02-01','staff_06','08:08:03','20:08:03','1','staff_06','admin'),('2021-02-01','staff_07','06:08:03','22:08:03','1','staff_06','user service A'),('2021-02-01','staff_11','10:08:03','13:08:03','1','staff_06','new chang 23455'),('2021-02-01','staff_13','10:08:03','18:08:03','1','staff_06','new change 11'),('2021-02-01','staff_14','10:08:03','20:08:03','1','staff_06','14 dfdasfds'),('2021-02-01','staff_15','23:08:03','10:08:03','1','staff_06','new 1111'),('2021-02-02','staff_01','12:48:52','12:48:52','1','staff_01','nguyen thi  vip'),('2021-02-02','staff_02','12:48:52','12:48:52','1','staff_02','nguyen thi  B'),('2021-02-02','staff_03','12:48:52','12:48:52','1','staff_03','nguyen thi C'),('2021-02-02','staff_04','12:48:52','12:48:52','1','staff_04','nguyen thi  D'),('2021-02-02','staff_05','12:48:52','12:48:52','1','staff_05','thinh manager'),('2021-02-02','staff_06','12:48:52','12:48:52','1','staff_06','admin'),('2021-02-02','staff_07','12:48:52','12:48:52','1','staff_07','user service A'),('2021-02-02','staff_11','12:48:52','12:48:52','1','staff_11','new chang 23455'),('2021-02-02','staff_13','12:48:52','12:48:52','1','staff_13','new change 11'),('2021-02-02','staff_14','12:48:52','12:48:52','1','staff_14','14 dfdasfds'),('2021-02-02','staff_15','12:48:52','12:48:52','1','staff_15','new 1111'),('2021-02-03','staff_01','12:49:03','12:49:03','1','staff_01','nguyen thi  vip'),('2021-02-03','staff_02','12:49:03','12:49:03','1','staff_02','nguyen thi  B'),('2021-02-03','staff_03','12:49:03','12:49:03','1','staff_03','nguyen thi C'),('2021-02-03','staff_04','12:49:03','12:49:03','1','staff_04','nguyen thi  D'),('2021-02-03','staff_05','12:49:03','12:49:03','1','staff_05','thinh manager'),('2021-02-03','staff_06','12:49:03','12:49:03','1','staff_06','admin'),('2021-02-03','staff_07','12:49:03','12:49:03','1','staff_07','user service A'),('2021-02-03','staff_11','12:49:03','12:49:03','1','staff_11','new chang 23455'),('2021-02-03','staff_13','12:49:03','12:49:03','1','staff_13','new change 11'),('2021-02-03','staff_14','12:49:03','12:49:03','1','staff_14','14 dfdasfds'),('2021-02-03','staff_15','12:49:03','12:49:03','1','staff_15','new 1111'),('2021-02-09','staff_01','14:42:56','14:42:56','1','staff_01','nguyen thi  vip'),('2021-02-09','staff_02','14:42:56','14:42:56','1','staff_02','nguyen thi  B'),('2021-02-09','staff_03','14:42:56','14:42:56','1','staff_03','nguyen thi C'),('2021-02-09','staff_04','14:42:56','14:42:56','1','staff_04','nguyen thi  D'),('2021-02-09','staff_05','14:42:56','14:42:56','1','staff_05','thinh manager'),('2021-02-09','staff_06','14:42:56','14:42:56','1','staff_06','admin'),('2021-02-09','staff_07','14:42:56','14:42:56','1','staff_07','user service A'),('2021-02-09','staff_11','14:42:56','14:42:56','1','staff_11','new chang 23455'),('2021-02-09','staff_13','14:42:56','14:42:56','1','staff_13','new change 11'),('2021-02-09','staff_14','14:42:56','14:42:56','1','staff_14','14 dfdasfds'),('2021-02-09','staff_15','14:42:56','14:42:56','1','staff_15','new 1111'),('2021-02-09','staff_9','11:42:56','13:42:56','1','staff_06','new user03'),('2021-02-11','staff_01','14:55:41','14:55:41','1','staff_01','nguyen thi  vip'),('2021-02-11','staff_02','14:55:41','14:55:41','1','staff_02','nguyen thi  B'),('2021-02-11','staff_03','14:55:41','14:55:41','1','staff_03','nguyen thi C'),('2021-02-11','staff_04','14:55:41','14:55:41','1','staff_04','nguyen thi  D'),('2021-02-11','staff_05','14:55:41','14:55:41','1','staff_05','thinh manager'),('2021-02-11','staff_06','14:55:41','14:55:41','1','staff_06','admin'),('2021-02-11','staff_07','14:55:41','14:55:41','1','staff_07','user service A'),('2021-02-11','staff_11','14:55:41','14:55:41','1','staff_11','new chang 23455'),('2021-02-11','staff_13','14:55:41','14:55:41','1','staff_13','new change 11'),('2021-02-11','staff_14','14:55:41','14:55:41','1','staff_14','14 dfdasfds'),('2021-02-11','staff_15','14:55:41','14:55:41','1','staff_15','new 1111'),('2021-02-11','staff_9','14:55:41','14:55:41','1','staff_9','new user03'),('2021-02-12','staff_01','10:08:42','10:08:42','1','staff_01','nguyen thi  vip'),('2021-02-12','staff_02','10:08:42','10:08:42','1','staff_02','nguyen thi  B'),('2021-02-12','staff_03','10:08:42','10:08:42','1','staff_03','nguyen thi C'),('2021-02-12','staff_04','10:08:42','10:08:42','1','staff_04','nguyen thi  D'),('2021-02-12','staff_05','10:08:42','10:08:42','1','staff_05','thinh manager'),('2021-02-12','staff_06','10:08:42','10:08:42','1','staff_06','admin'),('2021-02-12','staff_07','10:08:42','10:08:42','1','staff_07','user service A'),('2021-02-12','staff_11','10:08:42','10:08:42','1','staff_11','new chang 23455'),('2021-02-12','staff_13','10:08:42','10:08:42','1','staff_13','new change 11'),('2021-02-12','staff_14','10:08:42','10:08:42','1','staff_14','14 dfdasfds'),('2021-02-12','staff_15','10:08:42','10:08:42','1','staff_15','new 1111'),('2021-02-12','staff_9','10:08:42','10:08:42','1','staff_9','new user03'),('2021-02-17','staff_01','10:06:54','10:06:54','1','staff_01','nguyen thi  vip'),('2021-02-17','staff_02','10:06:54','10:06:54','1','staff_02','nguyen thi  B'),('2021-02-17','staff_03','10:06:54','10:06:54','1','staff_03','nguyen thi C'),('2021-02-17','staff_04','10:06:54','10:06:54','1','staff_04','nguyen thi  D'),('2021-02-17','staff_05','10:06:54','10:06:54','1','staff_05','thinh manager'),('2021-02-17','staff_06','10:06:54','10:06:54','1','staff_06','admin'),('2021-02-17','staff_07','10:06:54','10:06:54','1','staff_07','user service A'),('2021-02-17','staff_11','10:06:54','10:06:54','1','staff_11','new chang 23455'),('2021-02-17','staff_13','10:06:54','10:06:54','1','staff_13','new change 11'),('2021-02-17','staff_14','10:06:54','10:06:54','1','staff_14','14 dfdasfds'),('2021-02-17','staff_15','10:06:54','10:06:54','1','staff_15','new 1111'),('2021-02-17','staff_9','10:06:54','10:06:54','1','staff_9','new user03'),('2021-02-19','staff_01','10:09:11','10:09:11','1','staff_01','nguyen thi  vip'),('2021-02-19','staff_02','10:09:11','10:09:11','1','staff_02','nguyen thi  B'),('2021-02-19','staff_03','10:09:11','10:09:11','1','staff_03','nguyen thi C'),('2021-02-19','staff_04','10:09:11','10:09:11','1','staff_04','nguyen thi  D'),('2021-02-19','staff_05','10:09:11','10:09:11','1','staff_05','thinh manager'),('2021-02-19','staff_06','10:09:11','10:09:11','1','staff_06','admin'),('2021-02-19','staff_07','10:09:11','10:09:11','1','staff_07','user service A'),('2021-02-19','staff_11','10:09:11','10:09:11','1','staff_11','new chang 23455'),('2021-02-19','staff_13','10:09:12','10:09:12','1','staff_13','new change 11'),('2021-02-19','staff_14','10:09:12','10:09:12','1','staff_14','14 dfdasfds'),('2021-02-19','staff_15','10:09:12','10:09:12','1','staff_15','new 1111'),('2021-02-19','staff_9','10:09:12','10:09:12','1','staff_9','new user03'),('2021-02-20','staff_01','10:10:00','10:10:00','1','staff_01','nguyen thi  vip'),('2021-02-20','staff_02','10:10:00','10:10:00','1','staff_02','nguyen thi  B'),('2021-02-20','staff_03','10:10:00','10:10:00','1','staff_03','nguyen thi C'),('2021-02-20','staff_04','10:10:00','10:10:00','1','staff_04','nguyen thi  D'),('2021-02-20','staff_05','10:10:00','10:10:00','1','staff_05','thinh manager'),('2021-02-20','staff_06','10:10:00','10:10:00','1','staff_06','admin'),('2021-02-20','staff_07','10:10:00','10:10:00','1','staff_07','user service A'),('2021-02-20','staff_11','10:10:00','10:10:00','1','staff_11','new chang 23455'),('2021-02-20','staff_13','10:10:00','10:10:00','1','staff_13','new change 11'),('2021-02-20','staff_14','10:10:00','10:10:00','1','staff_14','14 dfdasfds'),('2021-02-20','staff_15','10:10:00','10:10:00','1','staff_15','new 1111'),('2021-02-20','staff_9','10:10:01','10:10:01','1','staff_9','new user03'),('2021-02-25','staff_01','10:07:04','10:07:04','1','staff_01','nguyen thi  vip'),('2021-02-25','staff_02','10:07:04','10:07:04','1','staff_02','nguyen thi  B'),('2021-02-25','staff_03','10:07:04','10:07:04','1','staff_03','nguyen thi C'),('2021-02-25','staff_04','10:07:04','10:07:04','1','staff_04','nguyen thi  D'),('2021-02-25','staff_05','10:07:04','10:07:04','1','staff_05','thinh manager'),('2021-02-25','staff_06','10:07:04','10:07:04','1','staff_06','admin'),('2021-02-25','staff_07','10:07:04','10:07:04','1','staff_07','user service A'),('2021-02-25','staff_11','10:07:04','10:07:04','1','staff_11','new chang 23455'),('2021-02-25','staff_13','10:07:04','10:07:04','1','staff_13','new change 11'),('2021-02-25','staff_14','10:07:04','10:07:04','1','staff_14','14 dfdasfds'),('2021-02-25','staff_15','10:07:04','10:07:04','1','staff_15','new 1111'),('2021-02-25','staff_7','10:07:04','10:07:04','1','staff_7','new user01'),('2021-02-25','staff_9','10:07:04','10:07:04','1','staff_9','new user03'),('2021-02-26','staff_01','08:06:21','10:06:21','1','staff_06','nguyen thi  vip'),('2021-02-26','staff_02','10:06:21','10:06:21','1','staff_02','nguyen thi  B'),('2021-02-26','staff_03','10:06:21','10:06:21','1','staff_03','nguyen thi C'),('2021-02-26','staff_04','10:06:21','10:06:21','1','staff_04','nguyen thi  D'),('2021-02-26','staff_05','10:06:21','10:06:21','1','staff_05','thinh manager'),('2021-02-26','staff_06','10:06:21','10:06:21','1','staff_06','admin'),('2021-02-26','staff_07','10:06:21','10:06:21','1','staff_07','user service A'),('2021-02-26','staff_11','10:06:21','10:06:21','1','staff_11','new chang 23455'),('2021-02-26','staff_13','10:06:21','10:06:21','1','staff_13','new change 11'),('2021-02-26','staff_14','10:06:21','10:06:21','1','staff_14','14 dfdasfds'),('2021-02-26','staff_15','10:06:21','10:06:21','1','staff_15','new 1111'),('2021-02-26','staff_7','10:06:21','10:06:21','1','staff_7','new user01'),('2021-02-26','staff_9','10:06:21','10:06:21','1','staff_9','new user03');
/*!40000 ALTER TABLE `dailyworking` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `datework`
--

DROP TABLE IF EXISTS `datework`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `datework` (
  `iddatework` date NOT NULL,
  `listuserworkfullday` varchar(5000) COLLATE utf8_unicode_ci DEFAULT NULL,
  `listuserhalfday` varchar(5000) COLLATE utf8_unicode_ci DEFAULT NULL,
  `regulation` int NOT NULL,
  PRIMARY KEY (`iddatework`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `datework`
--

LOCK TABLES `datework` WRITE;
/*!40000 ALTER TABLE `datework` DISABLE KEYS */;
INSERT INTO `datework` VALUES ('2020-01-01','','',1),('2021-01-01','staff_01,staff_02,staff_03,staff_04,staff_05,staff_06,staff_07,staff_11,staff_13','staff_14',1),('2021-01-02','staff_01,staff_02,staff_03,staff_04,staff_05,staff_06,staff_07,staff_11,staff_13,staff_14,staff_15','',1),('2021-01-03','staff_01,staff_02,staff_03,staff_04,staff_05,staff_06,staff_07,staff_13,staff_14,staff_15','staff_11',1),('2021-01-04','staff_01,staff_02,staff_03,staff_04,staff_05,staff_06,staff_07,staff_11,staff_13,staff_14','staff_15',1),('2021-01-05','staff_01,staff_02,staff_04,staff_05,staff_06,staff_07,staff_11,staff_13,staff_14,staff_15','',1),('2021-01-12','','',1),('2021-01-15','','',1),('2021-01-19','','',1),('2021-01-20','','',1),('2021-01-26','','',1),('2021-02-01','staff_01,staff_02,staff_03,staff_05,staff_06,staff_07,staff_13,staff_14','staff_11',1),('2021-02-02','','',1),('2021-02-03','','',1),('2021-02-09','','staff_9',1),('2021-02-11','','',1),('2021-02-12','','',1),('2021-02-17','','',1),('2021-02-19','','',1),('2021-02-20','','',1),('2021-02-25','','',1),('2021-02-26','','staff_01',1);
/*!40000 ALTER TABLE `datework` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `detailservices`
--

DROP TABLE IF EXISTS `detailservices`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `detailservices` (
  `idticketbooking` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `idproduct` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `idstaffservicesrepo` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `amount` int DEFAULT NULL,
  `startrent` timestamp NULL DEFAULT NULL,
  `endrent` timestamp NULL DEFAULT NULL,
  `status` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `sumaryservices` decimal(18,0) DEFAULT NULL,
  PRIMARY KEY (`idticketbooking`,`idproduct`),
  KEY `f_detail_product` (`idproduct`),
  KEY `f_detailservice_staffsupport` (`idstaffservicesrepo`),
  CONSTRAINT `f_detail_product` FOREIGN KEY (`idproduct`) REFERENCES `production` (`idproduction`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `f_detailservice_staffsupport` FOREIGN KEY (`idstaffservicesrepo`) REFERENCES `staff` (`idstaff`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `f_ticket_detail` FOREIGN KEY (`idticketbooking`) REFERENCES `ticketbooking` (`idticketbooking`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `detailservices`
--

LOCK TABLES `detailservices` WRITE;
/*!40000 ALTER TABLE `detailservices` DISABLE KEYS */;
INSERT INTO `detailservices` VALUES ('2021-02-21T15:46:02.867308900','6','staff_07',1,'2021-02-21 17:33:37','2021-02-21 08:33:44','Done',23400),('2021-02-21T15:46:02.867308900','7','staff_07',1,'2021-02-22 14:24:48','2021-02-21 08:33:44','Done',24000),('2021-02-21T15:46:02.867308900','9','staff_07',1,'2021-02-22 14:24:50','2021-02-21 08:33:44','Done',65000),('2021-02-21T16:31:01.102493100','1','staff_02',4,'2021-02-21 08:33:44','2021-02-22 17:12:32','Done',120000),('2021-02-21T16:31:01.102493100','10','staff_02',2,'2021-02-21 08:33:44','2021-02-23 18:06:29','Done',13000),('2021-02-21T16:31:01.102493100','7','staff_07',1,'2021-02-23 18:07:06','2021-02-21 08:33:44','Done',24000),('2021-02-21T23:57:19.711801','10','staff_02',3,'2021-02-23 15:15:29','2021-02-23 15:17:17','Done',19500),('2021-02-21T23:57:19.711801','13','staff_02',5,'2021-02-21 08:33:44','2021-02-23 07:42:46','Done',2250000),('2021-02-21T23:57:19.711801','2','staff_02',4,'2021-02-21 08:33:44','2021-02-23 15:17:19','Done',60000),('2021-02-23T00:10:39.172618600','11','staff_07',1,'2021-02-24 14:25:53','2021-02-21 08:33:44','Done',500000),('2021-02-23T00:10:39.172618600','5','staff_07',3,'2021-02-23 06:56:00','2021-02-21 08:33:44','Done',75000),('2021-02-23T00:10:39.172618600','6','staff_07',2,'2021-02-23 06:56:00','2021-02-21 08:33:44','Done',46800),('2021-02-24T22:33:05.023939500','10','staff_02',1,'2021-02-24 15:33:10','2021-02-24 15:46:33','Done',6500),('2021-02-24T22:33:05.023939500','11','staff_02',1,'2021-02-24 15:39:44','2021-02-24 15:46:36','Done',500000),('2021-02-24T22:33:05.023939500','12','staff_07',1,'2021-02-24 15:33:12','2021-02-21 08:33:44','Done',300000),('2021-02-24T22:33:05.023939500','5','staff_02',1,'2021-02-24 15:33:13','2021-02-24 15:46:38','Done',25000),('2021-02-24T22:50:58.768480400','10','staff_02',2,'2021-02-21 08:33:44','2021-02-24 15:51:16','Done',13000),('2021-02-24T22:50:58.768480400','11','staff_02',1,'2021-02-24 15:51:08','2021-02-24 15:51:17','Done',500000),('2021-02-24T23:09:22.135111800','11','staff_07',1,'2021-02-24 16:37:00','2021-02-24 16:47:32','Done',500000),('2021-02-24T23:09:22.135111800','2021-02-26T19:21:58.605059800','staff_02',1,'2021-02-26 12:24:57','2021-02-26 12:25:03','Done',50000),('2021-02-24T23:09:22.135111800','3','staff_07',1,'2021-02-24 16:45:42','2021-02-24 16:46:34','Done',6000),('2021-02-24T23:09:22.135111800','4','staff_02',3,'2021-02-21 08:33:44','2021-02-25 15:55:15','Done',67500),('2021-02-24T23:09:22.135111800','5','staff_07',1,'2021-02-24 16:37:33','2021-02-26 09:01:52','Done',25000),('2021-02-24T23:09:22.135111800','8','staff_07',1,'2021-02-26 09:01:59','2021-02-26 09:49:29','Done',24000),('2021-02-24T23:09:34.727253800','1','staff_07',1,'2021-02-25 20:06:40','2021-02-26 03:27:58','Done',30000),('2021-02-24T23:09:34.727253800','10','staff_02',1,'2021-02-25 20:06:39','2021-02-26 07:57:29','Done',6500),('2021-02-24T23:09:34.727253800','11','staff_02',2,'2021-02-24 16:09:41','2021-02-24 16:15:29','Done',1000000),('2021-02-24T23:09:34.727253800','12','staff_07',1,'2021-02-24 16:16:40','2021-02-26 07:57:30','Done',300000),('2021-02-24T23:09:34.727253800','7','staff_02',1,'2021-02-25 20:06:41','2021-02-26 07:57:32','Done',24000),('2021-02-24T23:36:45.886701300','9','staff_07',1,'2021-02-26 12:26:53','2021-02-26 12:28:49','Done',65000),('2021-02-26T10:11:57.825156800','2','staff_02',1,'2021-02-26 03:12:33','2021-02-26 03:13:10','Done',15000),('2021-02-26T10:11:57.825156800','6','staff_07',1,'2021-02-26 03:12:43','2021-02-26 03:14:18','Done',23400),('2021-02-26T10:11:57.825156800','7','staff_07',1,'2021-02-26 03:14:51','2021-02-26 03:15:42','Done',24000),('2021-02-26T19:33:54.339983600','1','staff_02',1,'2021-02-26 12:34:00','2021-02-21 08:33:44','Shipping',30000),('2021-02-26T19:33:54.339983600','10','staff_02',1,'2021-02-26 12:33:59','2021-02-21 08:33:44','Shipping',6500),('2021-02-26T19:33:54.339983600','2','staff_02',1,'2021-02-26 12:34:01','2021-02-26 12:34:16','Done',15000),('2021-02-26T19:33:54.339983600','2021-02-26T20:43:26.884615500','staff_02',1,'2021-02-26 13:45:47','2021-02-21 08:33:44','Prepare',230000);
/*!40000 ALTER TABLE `detailservices` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `production`
--

DROP TABLE IF EXISTS `production`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `production` (
  `idproduction` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `nameproduct` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `extention` varchar(455) COLLATE utf8_unicode_ci DEFAULT NULL,
  `productrates` decimal(18,0) NOT NULL,
  `img` varchar(2024) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `type` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `status` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`idproduction`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `production`
--

LOCK TABLES `production` WRITE;
/*!40000 ALTER TABLE `production` DISABLE KEYS */;
INSERT INTO `production` VALUES ('1','Mirinda  cammm 2','6 chai nước ngọt Mirinda hương cam 390ml',35000,'https://firebasestorage.googleapis.com/v0/b/readmepeter.appspot.com/o/images%2Fmirinda-cam-330ml-sleek-lon-2-org.jpg?alt=media&token=4f17724a-7afe-4ac7-b983-ed074130360a','Drinkandfood','On'),('10','socola Kinh Đô','Bánh mì tươi tròn nhân socola Kinh Đô 55g',6500,'https://cdn.tgdd.vn/Products/Images/7740/206519/bhx/banh-mi-tuoi-tron-nhan-socola-kinh-do-55g-202005271447095689.jpg','Drinkandfood','On'),('11','Satria 150 FI','Xe Satria 150 FI đời 2020 màu xanh , 59-f1 33337',500000,'https://cloud.muaban.net/images/thumb-detail/2021/02/20/087/be1e93a0efc2477ea33a31ccf9a512e2.jpg','MotoBike','On'),('12','wave , futrue ','xe máy 2 bánh giá rẻ, 59- g5 67890',300000,'https://cloud.muaban.net/images/thumb-detail/2020/03/19/165/3d63f6ad88a947d9beede3d2cb2752d5.jpg','MotoBike','On'),('13','AB 2020','Xe máy đi vòng vòng ra phố, 59-f3 12345',450000,'https://cloud.muaban.net/images/thumb-detail/2020/02/03/171/363193570531436982a07f48a8efe77c.jpg','MotoBike','On'),('2','Sprite vb','Nước ngọt Sprite hương chanh 1.5 lít',15000,'https://cdn.tgdd.vn/Products/Images/2443/84808/bhx/nuoc-ngot-sprite-vi-chanh-15-lit-201906251428180595.jpg','Drinkandfood','On'),('2021-02-26T19:21:58.605059800',' Bánh bơ trứng','Bột mì, đường, bơ 10%, trứng 2.8%, bột sữa whey',50000,'https://firebasestorage.googleapis.com/v0/b/readmepeter.appspot.com/o/images%2Fbanh-bo-trung-fiano-goi-270g-202102010850041887.jpg?alt=media&token=0d666556-8e2a-4047-9572-e4e3bbd90d97','Drinkandfood','Off'),('2021-02-26T19:25:52.659304300','Snack bắp','Snack bắp Green\'s A vị thịt nướng gói 36g',5000,'https://firebasestorage.googleapis.com/v0/b/readmepeter.appspot.com/o/images%2Fsnack-bap-greens-a-vi-thit-nuong-goi-36g-202011191524385552.jpg?alt=media&token=9752ff91-3f0e-4f6c-8157-29aab353dc6d','Drinkandfood','On'),('2021-02-26T19:38:49.538049400','Bánh gạo mật ong','Bánh gạo mật ong Fiano gói 108g',15000,'https://firebasestorage.googleapis.com/v0/b/readmepeter.appspot.com/o/images%2Fbanh-gao-mat-ong-fiano-goi-108g-202101230816087048.jpg?alt=media&token=fa5a7d32-865f-4137-a750-a7f15d815d95','Drinkandfood','On'),('2021-02-26T20:43:26.884615500','Hộp 6 hũ tổ yến update','Hộp 6 hũ tổ yến chưng đông trùng hạ thảo',230000,'https://firebasestorage.googleapis.com/v0/b/readmepeter.appspot.com/o/images%2Fhop-6-hu-to-yen-chung-san-sai-gon-anpha-dong-trung-ha-thao-70ml-202001031550320914.jpg?alt=media&token=ec7654c5-2b04-47f4-bf6a-bb880771e8a5','Drinkandfood','On'),('3','Mirinda đá me','Nước ngọt Mirinda đá me chai 390ml',6000,'https://cdn.tgdd.vn/Products/Images/2443/229521/bhx/nuoc-ngot-mirinda-da-me-chai-390ml-202010231339536666.jpg','Drinkandfood','On'),('4','Trà chanh','Trà chanh Wil hộp 272g Lama hộp',22500,'https://cdn.tgdd.vn/Products/Images/2385/230385/bhx/tra-atiso-lama-hop-50g-202011230855295859.jpg','Drinkandfood','On'),('5','Trà túi lọc Lama','Trà túi lọc Lama hương dâu hộp 50g',25000,'https://cdn.tgdd.vn/Products/Images/2385/230190/bhx/tra-tui-loc-lama-huong-dau-hop-50g-202010311335494943.jpg','Drinkandfood','On'),('6','Mì cay Nongshim','Mì cay Nongshim Shin Noodles tô 114g',23400,'https://cdn.tgdd.vn/Products/Images/2565/87670/bhx/mi-shin-to-116g-16-2-org.jpg','Drinkandfood','On'),('7','Mì trộn tương tàu','Mì trộn tương tàu dầu olive Samyang gói 140g',24000,'https://cdn.tgdd.vn/Products/Images/2565/113226/bhx/mi-tron-tuong-den-samyang-goi-140g-2-org.jpg','Drinkandfood','On'),('8','Cháo bổ dưỡng','Cháo bổ dưỡng SG Food tổ yến bát bảo gói 240g',24000,'https://cdn.tgdd.vn/Products/Images/2564/228365/bhx/chao-bo-duong-sg-food-to-yen-bat-bao-goi-240g-202010191556284954.jpg','Drinkandfood','On'),('9','Táo Gala','Táo Gala New Zealand (6 - 7 trái)',65000,'https://cdn.tgdd.vn/Products/Images/7578/202932/bhx/tao-gala-nhap-khau-new-zealand-tui-1kg-6-7-trai-202101271720526374.jpg','Drinkandfood','On'),('autogen','Mirinda  cammm','6 chai nước ngọt Mirinda hương cam 390ml',30000,'https://cdn.tgdd.vn/Products/Images/2443/84394/bhx/loc-6-chai-nuoc-ngot-mirinda-cam-390ml-201901291340138285.jpg','Drinkandfood','Off');
/*!40000 ALTER TABLE `production` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `room`
--

DROP TABLE IF EXISTS `room`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `room` (
  `idroom` int NOT NULL,
  `status` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `nametyperoom` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`idroom`),
  KEY `f_room_typeofroom` (`nametyperoom`),
  CONSTRAINT `f_room_typeofroom` FOREIGN KEY (`nametyperoom`) REFERENCES `typeofroom` (`nametypeofroom`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `room`
--

LOCK TABLES `room` WRITE;
/*!40000 ALTER TABLE `room` DISABLE KEYS */;
INSERT INTO `room` VALUES (1,NULL,'single'),(2,NULL,'single'),(3,NULL,'double'),(4,NULL,'double'),(5,NULL,'double'),(6,NULL,'single'),(7,NULL,'double'),(8,NULL,'double'),(9,NULL,'vip'),(10,NULL,'vip');
/*!40000 ALTER TABLE `room` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `staff`
--

DROP TABLE IF EXISTS `staff`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `staff` (
  `idstaff` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `username` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `pass` varchar(500) COLLATE utf8_unicode_ci NOT NULL,
  `role` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `datework` date NOT NULL,
  `salarymonth` decimal(18,0) NOT NULL,
  `bonussalary` decimal(18,0) DEFAULT NULL,
  `status` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`idstaff`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `staff`
--

LOCK TABLES `staff` WRITE;
/*!40000 ALTER TABLE `staff` DISABLE KEYS */;
INSERT INTO `staff` VALUES ('staff_01','nguyen thi  vip','$2a$2a$10$aK7ynWil8RK8Rlu9QXJIqeGjq2s2IJYpQZ78YNZvuxbVSxiw8.iYe','tieptan','2021-01-01',5000000,500000,'On'),('staff_02','nguyen thi  B','$2a$10$jNU.IW9V6a0ytauVZKMV0uPNeCsymX6kV3v/w9MzS89hssvKq3dBK','tieptan','2021-01-01',5000000,0,'On'),('staff_03','nguyen thi C','$2a$10$jNU.IW9V6a0ytauVZKMV0uPNeCsymX6kV3v/w9MzS89hssvKq3dBK','tieptan','2021-01-01',5000000,0,'On'),('staff_04','nguyen thi  D','$2a$10$jNU.IW9V6a0ytauVZKMV0uPNeCsymX6kV3v/w9MzS89hssvKq3dBK','dichvu','2021-01-01',4000000,0,'On'),('staff_05','thinh manager','$2a$10$jNU.IW9V6a0ytauVZKMV0uPNeCsymX6kV3v/w9MzS89hssvKq3dBK','quanli','2021-01-01',7000000,50000,'On'),('staff_06','admin','$2a$10$jNU.IW9V6a0ytauVZKMV0uPNeCsymX6kV3v/w9MzS89hssvKq3dBK','admin','2021-01-01',10000000,0,'On'),('staff_07','user service A','$2a$10$jNU.IW9V6a0ytauVZKMV0uPNeCsymX6kV3v/w9MzS89hssvKq3dBK','dichvu','2021-01-01',4000000,200000,'On'),('staff_11','new chang 23455','$2a$10$EnSp5jQ9.STGvw3On.iGm.v.YGkw.SANi5aPNBqVN7d31v9KJGYA6','quanli','2021-01-01',8000000,0,'On'),('staff_12','toi la 12','$2a$10$/iqm5zHg/HCZaUg6NbST/uwZ5MKbKNofrav93.eLHKGQ5hnppp/VK','quanli','2021-01-01',9000000,0,'Off'),('staff_13','new change 11','$2a$10$WnciJTVA0aNHQxKXGXIunOWbrd00cqzIoZPEE5IRs0Oh3gTtik1yi','quanli','2021-01-01',9000000,50000,'On'),('staff_14','14 dfdasfds','$2a$10$fJtXJHb.ie9QfJBGhQHrve7oJkNGc1uKIZnrazYj9fwSJsnli/v8m','dichvu','2021-01-01',4000000,200000,'On'),('staff_15','new 1111','$2a$10$7r8EnTS1EKqqscmzfcN7/.VfR8RZ9YSG/qXyuST4ig0rJiYtwayNS','quanli','2021-01-01',5000000,600000,'On'),('staff_16','new user0134','$2a$10$VCB.mdLaMKHeADlsaAOim.6yrcO6yKXTXFNQmYQHFB8Uyb1341gN2','dichvu','2021-02-01',12445,50000,'Off'),('staff_17','toi la 17_7777','$2a$10$IkD2M1OwSFfU288tqvOGre8vF91D5jKcTzPi9y4TzWfuuMcxsyl22','dichvu','2021-01-31',888888888,8888,'Off'),('staff_18','new User 18','$2a$10$xOLYL2H1ungl4gxrz03YE.FAXH3/Pz/.NYCnXluyNnmLd54IVra6O','dichvu','2021-02-01',333333333,333,'Off'),('staff_19','user1999','$2a$10$KumM.57XiFcV4RJMPAsScego1Qz6N7gst9IE4bb5vV0PLuPdZ0LeO','tieptan','2021-02-12',5000000,50000,'On'),('staff_7','new user01','$2a$10$jNSzXDWyaksCeQ/kTSPsKe1j/kRgKtayHbtFTaZ8n0TlNMKZB7tVm','tieptan','2021-02-25',7000000,400000,'Off'),('staff_9','new user03','$2a$10$i.j30jO.Af.VNj8GJ3uH4uGPUz3qzucT0TizG8Wj6foQoPtnfCeJm','dichvu','2021-02-08',5000000,0,'Off');
/*!40000 ALTER TABLE `staff` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ticketbooking`
--

DROP TABLE IF EXISTS `ticketbooking`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ticketbooking` (
  `idticketbooking` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `iduserrentroom` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `usernamerentroom` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `timestamprent` timestamp NOT NULL,
  `idstaffreception` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `numberroom` int NOT NULL,
  `numberinroom` int DEFAULT NULL,
  `status` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`idticketbooking`),
  KEY `f_room_staffreception` (`idstaffreception`),
  KEY `f_room_ticketbooking` (`numberroom`),
  CONSTRAINT `f_room_staffreception` FOREIGN KEY (`idstaffreception`) REFERENCES `staff` (`idstaff`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `f_room_ticketbooking` FOREIGN KEY (`numberroom`) REFERENCES `room` (`idroom`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ticketbooking`
--

LOCK TABLES `ticketbooking` WRITE;
/*!40000 ALTER TABLE `ticketbooking` DISABLE KEYS */;
INSERT INTO `ticketbooking` VALUES ('2021-02-21T15:33:52.285145400','23423','data.nameRent','2021-02-21 08:33:52','staff_02',2,2,'Off'),('2021-02-21T15:46:02.867308900','22323','sddsaas','2021-02-21 08:46:03','staff_02',1,3,'Off'),('2021-02-21T16:31:01.102493100','232323','thinh','2021-02-21 09:31:01','staff_02',10,5,'Off'),('2021-02-21T23:57:19.711801','2323','eeeeeeeeee','2021-02-21 16:57:20','staff_02',3,3,'Off'),('2021-02-23T00:10:39.172618600','2234123','vien ngov','2021-02-22 17:10:39','staff_02',8,2,'Off'),('2021-02-23T13:30:29.044613400','32322','toi test','2021-02-23 06:30:29','staff_02',2,4,'Off'),('2021-02-23T13:42:34.573627100','122232','test hnuya nek','2021-02-23 06:42:35','staff_02',2,3,'Off'),('2021-02-23T14:21:57.677331400','3233','yyyy','2021-02-23 07:21:58','staff_02',2,4,'Off'),('2021-02-23T22:19:25.351705700','223233','muon 3','2021-02-23 15:19:25','staff_02',3,7,'Off'),('2021-02-24T01:08:42.530350800','222332','toi ngu luon','2021-02-23 18:08:43','staff_02',7,9,'Off'),('2021-02-24T22:33:05.023939500','234224','khach vang lai','2021-02-24 15:33:05','staff_02',1,3,'Off'),('2021-02-24T22:50:58.768480400','324234','fgdafgd','2021-02-24 15:50:59','staff_02',2,4,'Off'),('2021-02-24T22:51:57.123853200','32324','thinh','2021-02-24 15:51:57','staff_02',10,7,'Off'),('2021-02-24T23:06:44.813961600','3434','55','2021-02-24 16:06:45','staff_02',4,4,'Off'),('2021-02-24T23:09:22.135111800','232323','khach a','2021-02-24 16:09:22','staff_02',5,4,'Off'),('2021-02-24T23:09:34.727253800','233223','ffff','2021-02-24 16:09:35','staff_02',9,8,'Off'),('2021-02-24T23:36:45.886701300','2123','khach 7','2021-02-24 16:36:46','staff_02',7,4,'Off'),('2021-02-24T23:47:56.480227600','2332','thinh22','2021-02-24 16:47:56','staff_02',10,3,'Off'),('2021-02-26T10:11:57.825156800','234232','ngoc diep','2021-02-26 03:11:58','staff_02',3,3,'Off'),('2021-02-26T19:33:54.339983600','4343444','A Nam','2021-02-26 12:33:54','staff_02',1,4,'Off'),('2021-02-27T10:11:39.949310300','23423423','test ','2021-02-27 03:11:40','staff_02',2,3,'On'),('2021-02-27T10:11:48.345373700','345333','sddsfdsf','2021-02-27 03:11:48','staff_02',6,4,'On');
/*!40000 ALTER TABLE `ticketbooking` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ticketcheckoutroom`
--

DROP TABLE IF EXISTS `ticketcheckoutroom`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ticketcheckoutroom` (
  `idticketcheckoutroom` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `idticketbooking` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `timeendrent` timestamp NULL DEFAULT NULL,
  `idstaffreceptionsupport` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `numberroomrent` int DEFAULT NULL,
  `sumaryratesandservices` decimal(18,0) DEFAULT NULL,
  `status` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `raterentroom` decimal(18,0) DEFAULT NULL,
  `rateservices` decimal(18,0) DEFAULT NULL,
  `roomsubcharge` decimal(18,0) DEFAULT NULL,
  `roomdamaged` decimal(18,0) DEFAULT NULL,
  `timerent` varchar(205) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`idticketcheckoutroom`),
  KEY `f_out_inroom` (`idticketbooking`),
  KEY `f_outroom_staffsupport` (`idstaffreceptionsupport`),
  CONSTRAINT `f_out_inroom` FOREIGN KEY (`idticketbooking`) REFERENCES `ticketbooking` (`idticketbooking`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `f_outroom_staffsupport` FOREIGN KEY (`idstaffreceptionsupport`) REFERENCES `staff` (`idstaff`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ticketcheckoutroom`
--

LOCK TABLES `ticketcheckoutroom` WRITE;
/*!40000 ALTER TABLE `ticketcheckoutroom` DISABLE KEYS */;
INSERT INTO `ticketcheckoutroom` VALUES ('2021-02-23T22:17:54.017793300','2021-02-21T23:57:19.711801','2021-02-23 15:17:42','staff_02',3,3748409,'Off',NULL,2329500,0,568909,'Time rent room: 1:22:20'),('2021-02-24T00:36:34.841440500','2021-02-23T22:19:25.351705700','2021-02-23 17:36:29','staff_07',3,498500,'Off',NULL,48400,100,0,'Time rent room: 0:2:17'),('2021-02-24T01:10:53.682290','2021-02-24T01:08:42.530350800','2021-02-23 18:10:45','staff_07',7,1130200,'Off',NULL,980000,200,0,'Time rent room: 0:0:2'),('2021-02-24T20:50:14.065531900','2021-02-21T16:31:01.102493100','2021-02-24 13:50:12','staff_07',10,4707000,'Off',NULL,157000,0,0,'Time rent room: 3:4:19'),('2021-02-24T22:31:56.176249600','2021-02-23T00:10:39.172618600','2021-02-24 15:31:47','staff_07',8,1771800,'Off',NULL,621800,0,300000,'Time rent room: 1:22:21'),('2021-02-24T22:50:42.685980400','2021-02-24T22:33:05.023939500','2021-02-24 15:47:10','staff_07',1,981500,'Off',NULL,831500,50000,0,'Time rent room: 0:0:14'),('2021-02-24T22:57:02.604782200','2021-02-24T22:50:58.768480400','2021-02-24 15:52:30','staff_07',2,713000,'Off',NULL,513000,100000,0,'Time rent room: 0:0:1'),('2021-02-24T23:02:58.920792800','2021-02-24T22:51:57.123853200','2021-02-24 16:00:43','staff_07',10,550000,'Off',NULL,0,0,0,'Time rent room: 0:0:8'),('2021-02-24T23:08:54.414656500','2021-02-24T23:06:44.813961600','2021-02-24 16:07:55','staff_07',4,650000,'Off',150000,0,0,500000,'Time rent room: 0:0:1'),('2021-02-26T10:17:34.846010100','2021-02-26T10:11:57.825156800','2021-02-26 03:17:23','staff_07',3,312400,'Off',150000,62400,0,100000,'Time rent room: 0:0:5'),('2021-02-26T14:57:44.225698300','2021-02-24T23:09:34.727253800','2021-02-26 07:57:34','staff_07',9,4160500,'Off',2500000,1360500,0,300000,'Time rent room: 1:15:47'),('2021-02-26T19:31:50.488968600','2021-02-24T23:36:45.886701300','2021-02-26 12:31:37','staff_02',7,1415000,'Clean',850000,65000,0,500000,'Time rent room: 1:19:54'),('2021-02-26T19:33:37.097728600','2021-02-24T23:09:22.135111800','2021-02-26 12:33:27','staff_02',5,1522500,'Clean',850000,672500,0,0,'Time rent room: 1:20:24'),('2021-02-27T10:09:04.695105200','2021-02-24T23:47:56.480227600','2021-02-27 03:09:01','staff_02',10,4000000,'Clean',3500000,0,0,500000,'Time rent room: 2:10:21'),('2021-02-27T10:11:30.387460600','2021-02-26T19:33:54.339983600','2021-02-27 03:11:29','staff_02',1,811500,'Clean',350000,281500,180000,0,'Time rent room: 0:14:37');
/*!40000 ALTER TABLE `ticketcheckoutroom` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `typeofroom`
--

DROP TABLE IF EXISTS `typeofroom`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `typeofroom` (
  `nametypeofroom` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `roomrateshours` decimal(18,0) NOT NULL,
  `roomratesdates` decimal(18,0) NOT NULL,
  `numberinroom` int NOT NULL,
  `roomratescharge` decimal(18,0) NOT NULL,
  PRIMARY KEY (`nametypeofroom`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `typeofroom`
--

LOCK TABLES `typeofroom` WRITE;
/*!40000 ALTER TABLE `typeofroom` DISABLE KEYS */;
INSERT INTO `typeofroom` VALUES ('double',150000,350000,5,70000),('single',100000,250000,2,90000),('vip',500000,1000000,100,0);
/*!40000 ALTER TABLE `typeofroom` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'hotelmina'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-02-27 10:56:01
