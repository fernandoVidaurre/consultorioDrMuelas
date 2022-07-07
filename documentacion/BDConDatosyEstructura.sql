CREATE DATABASE  IF NOT EXISTS `drmuelasbd` /*!40100 DEFAULT CHARACTER SET utf8mb3 COLLATE utf8_spanish_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `drmuelasbd`;
-- MySQL dump 10.13  Distrib 8.0.29, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: drmuelasbd
-- ------------------------------------------------------
-- Server version	8.0.29

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
-- Table structure for table `cuenta`
--

DROP TABLE IF EXISTS `cuenta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cuenta` (
  `id_cuenta` int unsigned NOT NULL AUTO_INCREMENT,
  `user` varchar(15) COLLATE utf8_spanish_ci DEFAULT NULL,
  `tipoUsuario` enum('P','A') COLLATE utf8_spanish_ci NOT NULL,
  `password` varchar(45) COLLATE utf8_spanish_ci DEFAULT NULL,
  PRIMARY KEY (`id_cuenta`),
  UNIQUE KEY `user` (`user`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cuenta`
--

LOCK TABLES `cuenta` WRITE;
/*!40000 ALTER TABLE `cuenta` DISABLE KEYS */;
INSERT INTO `cuenta` VALUES (1,'pepe','P','1234'),(2,'jose','P','1234'),(3,'maria','A','1234'),(4,'vale','A','1234'),(5,'luis','P','1234'),(6,'carlos','P','1234'),(7,'45233444','P','45233444');
/*!40000 ALTER TABLE `cuenta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fichamedica`
--

DROP TABLE IF EXISTS `fichamedica`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `fichamedica` (
  `idFichaMedica` int unsigned NOT NULL AUTO_INCREMENT,
  `fechaAlta` date NOT NULL,
  `obraSocial` varchar(30) COLLATE utf8_spanish_ci DEFAULT NULL,
  `codigoAfiliado` varchar(15) COLLATE utf8_spanish_ci DEFAULT NULL,
  PRIMARY KEY (`idFichaMedica`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fichamedica`
--

LOCK TABLES `fichamedica` WRITE;
/*!40000 ALTER TABLE `fichamedica` DISABLE KEYS */;
INSERT INTO `fichamedica` VALUES (1,'2022-06-25','ips','aa123'),(2,'2022-06-26','ospe','bb321'),(3,'2022-06-27','oseca','cc456'),(4,'2022-06-28','liderar','dd354'),(5,'2022-07-07',NULL,NULL);
/*!40000 ALTER TABLE `fichamedica` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `persona`
--

DROP TABLE IF EXISTS `persona`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `persona` (
  `idPersona` int unsigned NOT NULL AUTO_INCREMENT,
  `dni` varchar(8) COLLATE utf8_spanish_ci DEFAULT NULL,
  `apellido` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  `nombre` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  `fechaNac` date NOT NULL,
  `sexo` enum('M','F','X') COLLATE utf8_spanish_ci NOT NULL,
  `telefono` varchar(13) COLLATE utf8_spanish_ci DEFAULT NULL,
  `email` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  `domicilio` varchar(60) COLLATE utf8_spanish_ci NOT NULL,
  `cuenta_id_cuenta` int unsigned DEFAULT NULL,
  `fichamedica_idFichaMedica` int unsigned DEFAULT NULL,
  PRIMARY KEY (`idPersona`),
  UNIQUE KEY `dni` (`dni`),
  KEY `fk_persona_cuenta1_idx` (`cuenta_id_cuenta`),
  KEY `fk_persona_fichamedica1_idx` (`fichamedica_idFichaMedica`),
  CONSTRAINT `fk_persona_cuenta1` FOREIGN KEY (`cuenta_id_cuenta`) REFERENCES `cuenta` (`id_cuenta`),
  CONSTRAINT `fk_persona_fichamedica1` FOREIGN KEY (`fichamedica_idFichaMedica`) REFERENCES `fichamedica` (`idFichaMedica`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `persona`
--

LOCK TABLES `persona` WRITE;
/*!40000 ALTER TABLE `persona` DISABLE KEYS */;
INSERT INTO `persona` VALUES (1,'27631704','vidaurre','julio','1979-08-26','M','3875112233','julio','san luis',1,1),(2,'33970636','santos','valeria','1988-11-18','F','3874838719','vale@gmail.com','san rafael',2,2),(3,'53043774','kayssner','jose','2001-05-25','M','3876554422','pepe@gmail.com','cerrillos',3,3),(4,'45233444','julio','julio','2000-08-15','M','387455555','pipoip','lkjkljkl',7,5);
/*!40000 ALTER TABLE `persona` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tratamiento`
--

DROP TABLE IF EXISTS `tratamiento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tratamiento` (
  `idTratamiento` int unsigned NOT NULL AUTO_INCREMENT,
  `fecha` date NOT NULL,
  `nombreTratamiento` varchar(30) COLLATE utf8_spanish_ci NOT NULL,
  `detalle` varchar(50) COLLATE utf8_spanish_ci DEFAULT NULL,
  `fichamedica_idFichaMedica` int unsigned NOT NULL,
  PRIMARY KEY (`idTratamiento`),
  KEY `fk_tratamiento_fichamedica1_idx` (`fichamedica_idFichaMedica`),
  CONSTRAINT `fk_tratamiento_fichamedica1` FOREIGN KEY (`fichamedica_idFichaMedica`) REFERENCES `fichamedica` (`idFichaMedica`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tratamiento`
--

LOCK TABLES `tratamiento` WRITE;
/*!40000 ALTER TABLE `tratamiento` DISABLE KEYS */;
INSERT INTO `tratamiento` VALUES (1,'2022-07-07','curacion','curacion gral',1),(2,'2022-07-07','conducto','tratamiento de conduct',2),(3,'2022-07-07','extraccion','extraccion de muela 22',3);
/*!40000 ALTER TABLE `tratamiento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `turno`
--

DROP TABLE IF EXISTS `turno`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `turno` (
  `idTurno` int unsigned NOT NULL AUTO_INCREMENT,
  `fecha` date NOT NULL,
  `tipoTurno` enum('C','M','E') COLLATE utf8_spanish_ci DEFAULT NULL,
  `estado` tinyint DEFAULT NULL,
  `persona_idPersona` int unsigned DEFAULT NULL,
  `hora` time NOT NULL,
  PRIMARY KEY (`idTurno`),
  KEY `fk_turno_persona_idx` (`persona_idPersona`),
  CONSTRAINT `fk_turno_persona` FOREIGN KEY (`persona_idPersona`) REFERENCES `persona` (`idPersona`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `turno`
--

LOCK TABLES `turno` WRITE;
/*!40000 ALTER TABLE `turno` DISABLE KEYS */;
INSERT INTO `turno` VALUES (1,'2022-07-07',NULL,0,NULL,'08:00:00'),(2,'2022-07-07',NULL,0,NULL,'08:30:00'),(3,'2022-07-07',NULL,0,NULL,'09:00:00'),(4,'2022-07-07','E',1,4,'09:30:00'),(5,'2022-07-07',NULL,0,NULL,'10:00:00'),(6,'2022-07-07',NULL,0,NULL,'10:30:00'),(7,'2022-07-07',NULL,0,NULL,'11:00:00'),(8,'2022-07-07',NULL,0,NULL,'11:30:00'),(9,'2022-07-07',NULL,0,NULL,'12:00:00'),(10,'2022-07-07',NULL,0,NULL,'14:00:00'),(11,'2022-07-07',NULL,0,NULL,'14:30:00'),(12,'2022-07-07',NULL,0,NULL,'15:00:00'),(13,'2022-07-07',NULL,0,NULL,'15:30:00'),(14,'2022-07-07',NULL,0,NULL,'16:00:00'),(15,'2022-07-07',NULL,0,NULL,'16:30:00'),(16,'2022-07-07',NULL,0,NULL,'17:00:00'),(17,'2022-07-07',NULL,0,NULL,'18:00:00'),(18,'2022-07-08',NULL,0,NULL,'08:00:00'),(19,'2022-07-08',NULL,0,NULL,'08:30:00'),(20,'2022-07-08',NULL,0,NULL,'09:00:00'),(21,'2022-07-08',NULL,0,NULL,'09:30:00'),(22,'2022-07-08',NULL,0,NULL,'10:00:00'),(23,'2022-07-08',NULL,0,NULL,'10:30:00'),(24,'2022-07-08',NULL,0,NULL,'11:00:00'),(25,'2022-07-08',NULL,0,NULL,'11:30:00'),(26,'2022-07-08',NULL,0,NULL,'12:00:00'),(27,'2022-07-08',NULL,0,NULL,'14:00:00'),(28,'2022-07-08',NULL,0,NULL,'14:30:00'),(29,'2022-07-08',NULL,0,NULL,'15:00:00'),(30,'2022-07-08',NULL,0,NULL,'15:30:00'),(31,'2022-07-08',NULL,0,NULL,'16:00:00'),(32,'2022-07-08',NULL,0,NULL,'16:30:00'),(33,'2022-07-08',NULL,0,NULL,'17:00:00'),(34,'2022-07-08',NULL,0,NULL,'18:00:00');
/*!40000 ALTER TABLE `turno` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-07-07  9:37:18
