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
-- Table structure for table `checkingoutroomdamaded`
--

DROP TABLE IF EXISTS `checkingoutroomdamaded`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `checkingoutroomdamaded` (
  `idcheckingoutroomdamaded` int NOT NULL AUTO_INCREMENT,
  `idcheckoutroom` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `listproductdamaded` varchar(1000) COLLATE utf8_unicode_ci DEFAULT NULL,
  `sumaryindemnify` decimal(18,0) NOT NULL,
  `idstaffchecking` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`idcheckingoutroomdamaded`),
  KEY `f_damaded_outroom` (`idcheckoutroom`),
  KEY `f_damaded_staff` (`idstaffchecking`),
  CONSTRAINT `f_damaded_outroom` FOREIGN KEY (`idcheckoutroom`) REFERENCES `ticketcheckoutroom` (`idticketcheckoutroom`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `f_damaded_staff` FOREIGN KEY (`idstaffchecking`) REFERENCES `staff` (`idstaff`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `checkingoutroomdamaded`
--

LOCK TABLES `checkingoutroomdamaded` WRITE;
/*!40000 ALTER TABLE `checkingoutroomdamaded` DISABLE KEYS */;
/*!40000 ALTER TABLE `checkingoutroomdamaded` ENABLE KEYS */;
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
  `timestart` time NOT NULL,
  `timeend` time NOT NULL,
  `note` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `idstaffmanagement` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`idtoday`,`idstaffwork`),
  KEY `f_work_staff` (`idstaffwork`),
  KEY `f_work_today_staffmanagemenmt` (`idstaffmanagement`),
  CONSTRAINT `f_work_staff` FOREIGN KEY (`idstaffwork`) REFERENCES `staff` (`idstaff`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `f_work_today` FOREIGN KEY (`idtoday`) REFERENCES `datework` (`iddatework`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `f_work_today_staffmanagemenmt` FOREIGN KEY (`idstaffmanagement`) REFERENCES `staff` (`idstaff`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dailyworking`
--

LOCK TABLES `dailyworking` WRITE;
/*!40000 ALTER TABLE `dailyworking` DISABLE KEYS */;
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
  CONSTRAINT `f_detail_product` FOREIGN KEY (`idproduct`) REFERENCES `production` (`idproduction`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `f_detailservice_staffsupport` FOREIGN KEY (`idstaffservicesrepo`) REFERENCES `staff` (`idstaff`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `f_ticket_detail` FOREIGN KEY (`idticketbooking`) REFERENCES `ticketbooking` (`idticketbooking`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `detailservices`
--

LOCK TABLES `detailservices` WRITE;
/*!40000 ALTER TABLE `detailservices` DISABLE KEYS */;
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
  `nameproduct` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `extention` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `productrates` decimal(18,0) NOT NULL,
  PRIMARY KEY (`idproduction`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `production`
--

LOCK TABLES `production` WRITE;
/*!40000 ALTER TABLE `production` DISABLE KEYS */;
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
  `pass` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `role` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `datework` date NOT NULL,
  `salarymonth` decimal(18,0) NOT NULL,
  `bonussalary` decimal(18,0) DEFAULT NULL,
  PRIMARY KEY (`idstaff`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `staff`
--

LOCK TABLES `staff` WRITE;
/*!40000 ALTER TABLE `staff` DISABLE KEYS */;
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
  PRIMARY KEY (`idticketbooking`),
  KEY `f_room_ticketbooking` (`numberroom`),
  KEY `f_room_staffreception` (`idstaffreception`),
  CONSTRAINT `f_room_staffreception` FOREIGN KEY (`idstaffreception`) REFERENCES `staff` (`idstaff`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `f_room_ticketbooking` FOREIGN KEY (`numberroom`) REFERENCES `room` (`idroom`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ticketbooking`
--

LOCK TABLES `ticketbooking` WRITE;
/*!40000 ALTER TABLE `ticketbooking` DISABLE KEYS */;
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
  PRIMARY KEY (`idticketcheckoutroom`),
  KEY `f_out_inroom` (`idticketbooking`),
  KEY `f_outroom_staffsupport` (`idstaffreceptionsupport`),
  CONSTRAINT `f_out_inroom` FOREIGN KEY (`idticketbooking`) REFERENCES `ticketbooking` (`idticketbooking`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `f_outroom_staffsupport` FOREIGN KEY (`idstaffreceptionsupport`) REFERENCES `staff` (`idstaff`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ticketcheckoutroom`
--

LOCK TABLES `ticketcheckoutroom` WRITE;
/*!40000 ALTER TABLE `ticketcheckoutroom` DISABLE KEYS */;
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
/*!40000 ALTER TABLE `typeofroom` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-01-29 16:17:23
