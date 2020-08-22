-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: 45.80.153.253    Database: db_uniprotec
-- ------------------------------------------------------
-- Server version	5.7.31-0ubuntu0.18.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `acciones`
--

DROP TABLE IF EXISTS `acciones`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `acciones` (
  `id_accion` bigint(20) NOT NULL AUTO_INCREMENT,
  `nombre_accion` varchar(255) DEFAULT NULL,
  `icono_accion` varchar(255) DEFAULT NULL,
  `referencia_accion` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_accion`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `acciones`
--

LOCK TABLES `acciones` WRITE;
/*!40000 ALTER TABLE `acciones` DISABLE KEYS */;
INSERT INTO `acciones` VALUES (1,'NUEVA ASIGNACION','metismenu-icon pe-7s-mouse','AAsignacion'),(2,'CURSOS AGENDADOS','metismenu-icon pe-7s-keypad','CAsignacion'),(3,'ENTREGABLES','metismenu-icon pe-7s-eyedropper','CEntregable'),(4,'AGENDA ASIGNACION','metismenu-icon pe-7s-id','CAsignacionI'),(5,'CURSOS REALIZADOS','metismenu-icon pe-7s-check','CEntregableI'),(15,'Auxiliares','metismenu-icon pe-7s-pendrive','AAuxiliares');
/*!40000 ALTER TABLE `acciones` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `asignaciones`
--

DROP TABLE IF EXISTS `asignaciones`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `asignaciones` (
  `id_asignacion` bigint(20) NOT NULL AUTO_INCREMENT,
  `fecha_asignacion` varchar(255) NOT NULL,
  `id_cliente_asignacion` bigint(20) NOT NULL,
  `cliente_asignacion` varchar(300) NOT NULL,
  `id_curso_asignacion` bigint(20) NOT NULL,
  `curso_asignacion` varchar(255) NOT NULL,
  `id_instructor_asignacion` bigint(20) NOT NULL,
  `instructor_asignacion` varchar(255) NOT NULL,
  `horario_asignacion` varchar(255) NOT NULL,
  `archivos_asignacion` varchar(255) DEFAULT NULL,
  `participantes_asignacion` varchar(255) NOT NULL,
  `nivel_asignacion` varchar(255) NOT NULL,
  `observaciones_asignacion` varchar(255) DEFAULT NULL,
  `create_at_asignacion` varchar(255) NOT NULL,
  `user_create_asignacion` varchar(255) NOT NULL,
  `status_asignacion` varchar(255) NOT NULL,
  `id_region_asignacion` bigint(20) NOT NULL,
  `nombre_region_asignacion` varchar(255) NOT NULL,
  `tipo_curso_asignacion` varchar(255) NOT NULL,
  `id_asignacion_logica` varchar(255) NOT NULL,
  `fecha_pago` varchar(255) DEFAULT NULL,
  `guia_entregable` varchar(255) DEFAULT NULL,
  `numero_factura` varchar(255) DEFAULT NULL,
  `verificar_entregable` bit(1) DEFAULT NULL,
  `user_create_asignacion_texto` varchar(255) NOT NULL,
  PRIMARY KEY (`id_asignacion`)
) ENGINE=InnoDB AUTO_INCREMENT=203 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `asignaciones`
--

LOCK TABLES `asignaciones` WRITE;
/*!40000 ALTER TABLE `asignaciones` DISABLE KEYS */;
INSERT INTO `asignaciones` VALUES (145,'08/11/2020',48,'SAYER BAJIO',18,'BLOQUEO DE ENERGIA LOTO',1,'Ing. Felipe Javier Castillo Damian','0800;1230;undefined;undefined;04:30','Evaluaciones y datos participantes Magna EXT QRO Gruas y Polipastos 30 de junio 2020.xlsx','10','Avanzado','Refresh 4 horas teorico practico , 30 min de comida a las 10 am','2020-08-17 16:58:17.126','36','Entregables Validado',2,'ESTADO DE QUERETARO + CELAYA Y SAN JOSE ITURBIDE GTO + TEPEJI DEL RIO HIDALGO','PRESENCIAL','12082020-48-1-18','',NULL,'',NULL,'Martha  Maria Aguilera Aranda '),(146,'08/13/2020',18,'TENNECO AMAZOC PUEBLA',18,'BLOQUEO DE ENERGIA LOTO',1,'Ing. Felipe Javier Castillo Damian','0800;1600;undefined;undefined;08:00','','15','Avanzado','revisar comida en sede','2020-08-17 12:36:29.392','37','Entregables Validado',7,'ESTADO DE PUEBLA + ESTADO DE TLAXCALA + ESTADO DE MORELOS','PRESENCIAL','13082020-18-1-18','','','',NULL,'Olivier Sanchez'),(147,'08/12/2020',29,'JAFRA',3,'OPERADOR DE GRUAS VIAJERAS Y POLIPASTOS',1,'Ing. Felipe Javier Castillo Damian','0800;1700;undefined;undefined;09:00','Arcelormittal Examen en linea incendio Titanium  (respuestas) (1).xlsx','18','Intermedio','comida de 11 a 12','2020-08-18 21:29:02.042','35','Entregable Enviado',2,'ESTADO DE QUERETARO + CELAYA Y SAN JOSE ITURBIDE GTO + TEPEJI DEL RIO HIDALGO','PRESENCIAL','12082020-29-1-3','Viernes 14 de Agosto de 2020','paquete express 121231554448897789','2465455445',NULL,'Angeles Andrea Vargas Cortes'),(148,'08/18/2020',27,'SHOWA',1,'OPERADOR DE MONTACARGAS',5,'Ing. Gerardo Federico Moreno Specia','0600;1400;undefined;undefined;08:00','','21','Básico','comida a definir en sede.E','2020-08-18 22:19:37.618','52','Curso Editado',3,'ESTADO DE GUANAJUATO MENOS CELAYA Y SAN JOSE ITURBIDE + LAGOS DE MORENO + EDO DE AGUASCALIENTES','PRESENCIAL','12082020-27-5-1',NULL,NULL,NULL,NULL,'Karla Ivonne Hernandez Mena'),(149,'08/18/2020',27,'SHOWA',1,'OPERADOR DE MONTACARGAS',5,'Ing. Gerardo Federico Moreno Specia','0800;1400;undefined;undefined;8:00','','21','Básico','comida a definir en sede.E','2020-08-18 22:20:53.911','52','Curso Editado',3,'ESTADO DE GUANAJUATO MENOS CELAYA Y SAN JOSE ITURBIDE + LAGOS DE MORENO + EDO DE AGUASCALIENTES','PRESENCIAL','12082020-27-5-1',NULL,NULL,NULL,NULL,'Karla Ivonne Hernandez Mena'),(150,'08/18/2020',27,'SHOWA',1,'OPERADOR DE MONTACARGAS',5,'Ing. Gerardo Federico Moreno Specia','0600;1400;undefined;undefined;08:00','','21','Básico','comida a definir en sede.','2020-08-18 22:39:30.699','52','Curso Editado',3,'ESTADO DE GUANAJUATO MENOS CELAYA Y SAN JOSE ITURBIDE + LAGOS DE MORENO + EDO DE AGUASCALIENTES','PRESENCIAL','12082020-27-5-1',NULL,NULL,NULL,NULL,'Karla Ivonne Hernandez Mena'),(151,'08/18/2020',27,'SHOWA',1,'OPERADOR DE MONTACARGAS',5,'Ing. Gerardo Federico Moreno Specia','0600;1400;undefined;undefined;08:00','','21','Básico','comida a definir en sede.','2020-08-18 22:44:13.536','52','Curso Editado',3,'ESTADO DE GUANAJUATO MENOS CELAYA Y SAN JOSE ITURBIDE + LAGOS DE MORENO + EDO DE AGUASCALIENTES','PRESENCIAL','12082020-27-5-1',NULL,NULL,NULL,NULL,'Karla Ivonne Hernandez Mena'),(152,'08/18/2020',27,'SHOWA',1,'OPERADOR DE MONTACARGAS',5,'Ing. Gerardo Federico Moreno Specia','0600;1400;undefined;undefined;08:00','','21','Básico','comida a definir en sede.','2020-08-18 22:49:45.832','4','Curso Editado',3,'ESTADO DE GUANAJUATO MENOS CELAYA Y SAN JOSE ITURBIDE + LAGOS DE MORENO + EDO DE AGUASCALIENTES','PRESENCIAL','12082020-27-5-1',NULL,NULL,NULL,NULL,'Karla Ivonne Hernandez Mena'),(153,'08/18/2020',27,'SHOWA',1,'OPERADOR DE MONTACARGAS',5,'Ing. Gerardo Federico Moreno Specia','06:00;14:00;undefined;undefined;08:00','','21','Básico','comida a definir en sede.','2020-08-18 22:58:28.074','4','Curso Editado',3,'ESTADO DE GUANAJUATO MENOS CELAYA Y SAN JOSE ITURBIDE + LAGOS DE MORENO + EDO DE AGUASCALIENTES','PRESENCIAL','12082020-27-5-1',NULL,NULL,NULL,NULL,'Karla Ivonne Hernandez Mena'),(154,'08/18/2020',27,'SHOWA',1,'OPERADOR DE MONTACARGAS',5,'Ing. Gerardo Federico Moreno Specia','0600;1400;undefined;undefined;8:00','','21','Básico','comida a definir en sede. A','2020-08-18 23:05:53.647','4','Curso Editado',3,'ESTADO DE GUANAJUATO MENOS CELAYA Y SAN JOSE ITURBIDE + LAGOS DE MORENO + EDO DE AGUASCALIENTES','PRESENCIAL','12082020-27-5-1',NULL,NULL,NULL,NULL,'Karla Ivonne Hernandez Mena'),(155,'08/18/2020',27,'SHOWA',1,'OPERADOR DE MONTACARGAS',5,'Ing. Gerardo Federico Moreno Specia','06:00;14:00;undefined;undefined;08:00','','21','Básico','comida a definir en sede.A','2020-08-18 23:51:52.22','4','Curso Editado',3,'ESTADO DE GUANAJUATO MENOS CELAYA Y SAN JOSE ITURBIDE + LAGOS DE MORENO + EDO DE AGUASCALIENTES','PRESENCIAL','12082020-27-5-1',NULL,NULL,NULL,NULL,'Karla Ivonne Hernandez Mena'),(156,'08/18/2020',27,'SHOWA',1,'OPERADOR DE MONTACARGAS',5,'Ing. Gerardo Federico Moreno Specia','0800;1400;undefined;undefined;06:00','','21','Básico','comida a definir en sede.','2020-08-19 00:02:00.823','4','Evento Cancelado',3,'ESTADO DE GUANAJUATO MENOS CELAYA Y SAN JOSE ITURBIDE + LAGOS DE MORENO + EDO DE AGUASCALIENTES','PRESENCIAL','12082020-27-5-1',NULL,NULL,NULL,NULL,'Karla Ivonne Hernandez Mena'),(157,'08/18/2020',27,'SHOWA',1,'OPERADOR DE MONTACARGAS',5,'Ing. Gerardo Federico Moreno Specia','06:00;14:00;undefined;undefined;08:00','','21','Intermedio','comida a definir en sede','2020-08-19 00:08:37.188','4','Evento Cancelado',3,'ESTADO DE GUANAJUATO MENOS CELAYA Y SAN JOSE ITURBIDE + LAGOS DE MORENO + EDO DE AGUASCALIENTES','PRESENCIAL','12082020-27-5-1',NULL,NULL,NULL,NULL,'Karla Ivonne Hernandez Mena'),(158,'08/22/2020',27,'SHOWA',1,'OPERADOR DE MONTACARGAS',5,'Ing. Gerardo Federico Moreno Specia','0600;1400;undefined;undefined;08:00','','1','Básico','comida a definir en sede','2020-08-22 04:49:16.644','52','Curso Editado',3,'ESTADO DE GUANAJUATO MENOS CELAYA Y SAN JOSE ITURBIDE + LAGOS DE MORENO + EDO DE AGUASCALIENTES','PRESENCIAL','12082020-27-5-1',NULL,NULL,NULL,NULL,'Mario Alberto Perez Martinez'),(159,'08/12/2020',27,'SHOWA',1,'OPERADOR DE MONTACARGAS',5,'Ing. Gerardo Federico Moreno Specia','0600;1400;undefined;undefined;08:00','','21','Básico','comida a definir en sede','2020-08-11 02:06:53.768','4','Curso Asignado',3,'ESTADO DE GUANAJUATO MENOS CELAYA Y SAN JOSE ITURBIDE + LAGOS DE MORENO + EDO DE AGUASCALIENTES','PRESENCIAL','12082020-27-5-1',NULL,NULL,NULL,NULL,'Karla Ivonne Hernandez Mena'),(160,'08/12/2020',27,'SHOWA',1,'OPERADOR DE MONTACARGAS',5,'Ing. Gerardo Federico Moreno Specia','0600;1400;undefined;undefined;08:00','','21','Básico','comida a definir en sede','2020-08-11 02:06:53.978','4','Curso Asignado',3,'ESTADO DE GUANAJUATO MENOS CELAYA Y SAN JOSE ITURBIDE + LAGOS DE MORENO + EDO DE AGUASCALIENTES','PRESENCIAL','12082020-27-5-1',NULL,NULL,NULL,NULL,'Karla Ivonne Hernandez Mena'),(161,'08/18/2020',42,'GENERAL MOTORS SAN LUIS POTOSI',19,'MANEJO DE SUSTANCIAS QUIMICAS ',13,'Ing. Maximino Garcia Camilo','0800;1600;undefined;undefined;08:00','','13','Avanzado','comida a definir en sede.E','2020-08-19 00:56:04.771','36','Curso Editado',1,'ESTADO DE SAN LUIS POTOSI','PRESENCIAL','11082020-42-13-19',NULL,NULL,NULL,NULL,'Martha  Maria Aguilera Aranda '),(163,'09/02/2020',18,'TENNECO AMAZOC PUEBLA',18,'BLOQUEO DE ENERGIA LOTO',1,'Ing. Felipe Javier Castillo Damian','0330;1130;undefined;undefined;11:00','','12','Avanzado','test B','2020-08-11 15:26:34.136','37','Curso Asignado',7,'ESTADO DE PUEBLA + ESTADO DE TLAXCALA + ESTADO DE MORELOS','PRESENCIAL','02092020-18-1-18',NULL,NULL,NULL,NULL,'Olivier Sanchez'),(165,'09/01/2020',42,'GENERAL MOTORS SAN LUIS POTOSI',1,'OPERADOR DE MONTACARGAS',9,'Ing. Ricardo Geciel Velazquez Medina','0630;1130;undefined;undefined;7:00','','15','Intermedio','','2020-08-11 15:57:49.242','37','Curso Asignado',1,'ESTADO DE SAN LUIS POTOSI','PRESENCIAL','01092020-42-9-1',NULL,NULL,NULL,NULL,'Olivier Sanchez'),(166,'09/01/2020',30,'FERRO MEXICANA',2,'OPERADOR DE MONTACARGAS (ORDER PICKER)',1,'Ing. Felipe Javier Castillo Damian','0430;1130;undefined;undefined;8:30','','14','Intermedio','Test Evento Editado','2020-08-19 00:17:52.017','37','Confirmado Instructor',2,'ESTADO DE QUERETARO + CELAYA Y SAN JOSE ITURBIDE GTO + TEPEJI DEL RIO HIDALGO','PRESENCIAL','01092020-30-1-2','Jueves 20 de Agosto de 2020','','123456',NULL,'Olivier Sanchez'),(167,'09/01/2020',43,'TOPRE',2,'OPERADOR DE MONTACARGAS (ORDER PICKER)',8,'Ing. Juan Alberto Zuñiga Vazquez','0730;1530;undefined;undefined;9:30','','15','Intermedio','','2020-08-11 17:29:29.541','3','Curso Asignado',2,'ESTADO DE QUERETARO + CELAYA Y SAN JOSE ITURBIDE GTO + TEPEJI DEL RIO HIDALGO','ON LINE','01092020-43-8-2',NULL,NULL,NULL,NULL,'Lidia Arellano Urbina'),(168,'09/01/2020',43,'TOPRE',11,'OPERADOR DE TRACTOR REMOLQUE CLASE VI (TUGGER)',4,'Ing. Juan Antonio Gomez Diaz','1000;1600;undefined;undefined;6:30','','14','Avanzado','','2020-08-11 18:21:31.623','3','Curso Editado',2,'ESTADO DE QUERETARO + CELAYA Y SAN JOSE ITURBIDE GTO + TEPEJI DEL RIO HIDALGO','ON LINE','01092020-43-4-11',NULL,NULL,NULL,NULL,'Lidia Arellano Urbina'),(169,'09/01/2020',43,'TOPRE',2,'OPERADOR DE MONTACARGAS (ORDER PICKER)',11,'Ing. Ramon Noriega Lopez','0600;930;undefined;undefined;6:00','','10','Avanzado','','2020-08-11 18:27:45.24','3','Curso Editado',2,'ESTADO DE QUERETARO + CELAYA Y SAN JOSE ITURBIDE GTO + TEPEJI DEL RIO HIDALGO','ON LINE','01092020-43-11-2',NULL,NULL,NULL,NULL,'Lidia Arellano Urbina'),(170,'09/01/2020',43,'TOPRE',2,'OPERADOR DE MONTACARGAS (ORDER PICKER)',11,'Ing. Ramon Noriega Lopez','0500;930;undefined;undefined;5:30','','10','Avanzado','','2020-08-11 17:34:50.622','3','Curso Asignado',2,'ESTADO DE QUERETARO + CELAYA Y SAN JOSE ITURBIDE GTO + TEPEJI DEL RIO HIDALGO','ON LINE','01092020-43-11-2',NULL,NULL,NULL,NULL,'Lidia Arellano Urbina'),(171,'09/01/2020',43,'TOPRE',2,'OPERADOR DE MONTACARGAS (ORDER PICKER)',11,'Ing. Ramon Noriega Lopez','0500;930;undefined;undefined;5:30','','10','Avanzado','','2020-08-11 17:34:45.635','3','Curso Asignado',2,'ESTADO DE QUERETARO + CELAYA Y SAN JOSE ITURBIDE GTO + TEPEJI DEL RIO HIDALGO','ON LINE','01092020-43-11-2',NULL,NULL,NULL,NULL,'Lidia Arellano Urbina'),(172,'09/02/2020',43,'TOPRE',14,'OPERADOR DE MINI CARGADOR FRONTAL (BOBCAT)',5,'Ing. Gerardo Federico Moreno Specia','0530;1130;undefined;undefined;8:30','','12','Avanzado','','2020-08-11 17:38:05.664','3','Curso Asignado',2,'ESTADO DE QUERETARO + CELAYA Y SAN JOSE ITURBIDE GTO + TEPEJI DEL RIO HIDALGO','ON LINE','02092020-43-5-14',NULL,NULL,NULL,NULL,'Lidia Arellano Urbina'),(173,'09/02/2020',43,'TOPRE',14,'OPERADOR DE MINI CARGADOR FRONTAL (BOBCAT)',5,'Ing. Gerardo Federico Moreno Specia','0530;1130;undefined;undefined;8:30','','12','Avanzado','','2020-08-11 17:38:07.546','3','Curso Asignado',2,'ESTADO DE QUERETARO + CELAYA Y SAN JOSE ITURBIDE GTO + TEPEJI DEL RIO HIDALGO','ON LINE','02092020-43-5-14',NULL,NULL,NULL,NULL,'Lidia Arellano Urbina'),(174,'09/02/2020',43,'TOPRE',14,'OPERADOR DE MINI CARGADOR FRONTAL (BOBCAT)',5,'Ing. Gerardo Federico Moreno Specia','0530;1130;undefined;undefined;8:30','','12','Avanzado','','2020-08-11 17:38:09.026','3','Curso Asignado',2,'ESTADO DE QUERETARO + CELAYA Y SAN JOSE ITURBIDE GTO + TEPEJI DEL RIO HIDALGO','ON LINE','02092020-43-5-14',NULL,NULL,NULL,NULL,'Lidia Arellano Urbina'),(175,'09/02/2020',43,'TOPRE',14,'OPERADOR DE MINI CARGADOR FRONTAL (BOBCAT)',5,'Ing. Gerardo Federico Moreno Specia','0530;1130;undefined;undefined;8:30','','12','Avanzado','','2020-08-11 17:38:10.421','3','Curso Asignado',2,'ESTADO DE QUERETARO + CELAYA Y SAN JOSE ITURBIDE GTO + TEPEJI DEL RIO HIDALGO','ON LINE','02092020-43-5-14',NULL,NULL,NULL,NULL,'Lidia Arellano Urbina'),(176,'09/02/2020',43,'TOPRE',14,'OPERADOR DE MINI CARGADOR FRONTAL (BOBCAT)',5,'Ing. Gerardo Federico Moreno Specia','0530;1130;undefined;undefined;8:30','','12','Avanzado','','2020-08-11 17:38:11.741','3','Curso Asignado',2,'ESTADO DE QUERETARO + CELAYA Y SAN JOSE ITURBIDE GTO + TEPEJI DEL RIO HIDALGO','ON LINE','02092020-43-5-14',NULL,NULL,NULL,NULL,'Lidia Arellano Urbina'),(177,'09/02/2020',43,'TOPRE',14,'OPERADOR DE MINI CARGADOR FRONTAL (BOBCAT)',5,'Ing. Gerardo Federico Moreno Specia','0530;1130;undefined;undefined;8:30','','12','Avanzado','','2020-08-11 17:38:13.16','3','Curso Asignado',2,'ESTADO DE QUERETARO + CELAYA Y SAN JOSE ITURBIDE GTO + TEPEJI DEL RIO HIDALGO','ON LINE','02092020-43-5-14',NULL,NULL,NULL,NULL,'Lidia Arellano Urbina'),(178,'09/02/2020',43,'TOPRE',14,'OPERADOR DE MINI CARGADOR FRONTAL (BOBCAT)',5,'Ing. Gerardo Federico Moreno Specia','0530;1130;undefined;undefined;8:30','','12','Avanzado','','2020-08-11 17:38:14.5','3','Curso Asignado',2,'ESTADO DE QUERETARO + CELAYA Y SAN JOSE ITURBIDE GTO + TEPEJI DEL RIO HIDALGO','ON LINE','02092020-43-5-14',NULL,NULL,NULL,NULL,'Lidia Arellano Urbina'),(179,'09/02/2020',43,'TOPRE',2,'OPERADOR DE MONTACARGAS (ORDER PICKER)',2,'Ing. Cesar Octavio Vazquez Galicia','0530;1130;undefined;undefined;9:00','','9','Intermedio','','2020-08-11 17:39:56.08','3','Curso Asignado',2,'ESTADO DE QUERETARO + CELAYA Y SAN JOSE ITURBIDE GTO + TEPEJI DEL RIO HIDALGO','ON LINE','02092020-43-2-2',NULL,NULL,NULL,NULL,'Lidia Arellano Urbina'),(180,'09/02/2020',43,'TOPRE',2,'OPERADOR DE MONTACARGAS (ORDER PICKER)',2,'Ing. Cesar Octavio Vazquez Galicia','0530;1130;undefined;undefined;9:00','','9','Intermedio','','2020-08-11 17:39:57.777','3','Curso Asignado',2,'ESTADO DE QUERETARO + CELAYA Y SAN JOSE ITURBIDE GTO + TEPEJI DEL RIO HIDALGO','ON LINE','02092020-43-2-2',NULL,NULL,NULL,NULL,'Lidia Arellano Urbina'),(181,'09/02/2020',43,'TOPRE',2,'OPERADOR DE MONTACARGAS (ORDER PICKER)',2,'Ing. Cesar Octavio Vazquez Galicia','0530;1130;undefined;undefined;9:00','','9','Intermedio','','2020-08-11 17:39:59.264','3','Curso Asignado',2,'ESTADO DE QUERETARO + CELAYA Y SAN JOSE ITURBIDE GTO + TEPEJI DEL RIO HIDALGO','ON LINE','02092020-43-2-2',NULL,NULL,NULL,NULL,'Lidia Arellano Urbina'),(182,'09/02/2020',43,'TOPRE',2,'OPERADOR DE MONTACARGAS (ORDER PICKER)',2,'Ing. Cesar Octavio Vazquez Galicia','0530;1130;undefined;undefined;9:00','','9','Intermedio','','2020-08-11 17:40:00.566','3','Curso Asignado',2,'ESTADO DE QUERETARO + CELAYA Y SAN JOSE ITURBIDE GTO + TEPEJI DEL RIO HIDALGO','ON LINE','02092020-43-2-2',NULL,NULL,NULL,NULL,'Lidia Arellano Urbina'),(183,'09/01/2020',45,'ARCELORMITTAL ',2,'OPERADOR DE MONTACARGAS (ORDER PICKER)',5,'Ing. Gerardo Federico Moreno Specia','0530;1230;undefined;undefined;11:00','','15','Avanzado','','2020-08-11 17:41:44.633','3','Curso Asignado',3,'ESTADO DE GUANAJUATO MENOS CELAYA Y SAN JOSE ITURBIDE + LAGOS DE MORENO + EDO DE AGUASCALIENTES','ON LINE','01092020-45-5-2',NULL,NULL,NULL,NULL,'Lidia Arellano Urbina'),(184,'09/01/2020',45,'ARCELORMITTAL ',2,'OPERADOR DE MONTACARGAS (ORDER PICKER)',5,'Ing. Gerardo Federico Moreno Specia','0530;1230;undefined;undefined;11:00','','15','Avanzado','','2020-08-11 17:41:46.019','3','Curso Asignado',3,'ESTADO DE GUANAJUATO MENOS CELAYA Y SAN JOSE ITURBIDE + LAGOS DE MORENO + EDO DE AGUASCALIENTES','ON LINE','01092020-45-5-2',NULL,NULL,NULL,NULL,'Lidia Arellano Urbina'),(185,'09/01/2020',45,'ARCELORMITTAL ',2,'OPERADOR DE MONTACARGAS (ORDER PICKER)',5,'Ing. Gerardo Federico Moreno Specia','0530;1230;undefined;undefined;11:00','','15','Avanzado','','2020-08-11 17:41:47.439','3','Curso Asignado',3,'ESTADO DE GUANAJUATO MENOS CELAYA Y SAN JOSE ITURBIDE + LAGOS DE MORENO + EDO DE AGUASCALIENTES','ON LINE','01092020-45-5-2',NULL,NULL,NULL,NULL,'Lidia Arellano Urbina'),(186,'09/01/2020',45,'ARCELORMITTAL ',2,'OPERADOR DE MONTACARGAS (ORDER PICKER)',5,'Ing. Gerardo Federico Moreno Specia','0530;1230;undefined;undefined;11:00','','15','Avanzado','','2020-08-11 17:41:48.958','3','Curso Asignado',3,'ESTADO DE GUANAJUATO MENOS CELAYA Y SAN JOSE ITURBIDE + LAGOS DE MORENO + EDO DE AGUASCALIENTES','ON LINE','01092020-45-5-2',NULL,NULL,NULL,NULL,'Lidia Arellano Urbina'),(187,'09/01/2020',45,'ARCELORMITTAL ',2,'OPERADOR DE MONTACARGAS (ORDER PICKER)',5,'Ing. Gerardo Federico Moreno Specia','0530;1230;undefined;undefined;11:00','','15','Avanzado','','2020-08-11 17:41:50.422','3','Curso Asignado',3,'ESTADO DE GUANAJUATO MENOS CELAYA Y SAN JOSE ITURBIDE + LAGOS DE MORENO + EDO DE AGUASCALIENTES','ON LINE','01092020-45-5-2',NULL,NULL,NULL,NULL,'Lidia Arellano Urbina'),(188,'09/01/2020',45,'ARCELORMITTAL ',2,'OPERADOR DE MONTACARGAS (ORDER PICKER)',5,'Ing. Gerardo Federico Moreno Specia','0530;1230;undefined;undefined;11:00','','15','Avanzado','','2020-08-11 17:41:51.889','3','Curso Asignado',3,'ESTADO DE GUANAJUATO MENOS CELAYA Y SAN JOSE ITURBIDE + LAGOS DE MORENO + EDO DE AGUASCALIENTES','ON LINE','01092020-45-5-2',NULL,NULL,NULL,NULL,'Lidia Arellano Urbina'),(189,'09/01/2020',45,'ARCELORMITTAL ',2,'OPERADOR DE MONTACARGAS (ORDER PICKER)',5,'Ing. Gerardo Federico Moreno Specia','0530;1230;undefined;undefined;11:00','','15','Avanzado','','2020-08-11 17:41:53.323','3','Curso Asignado',3,'ESTADO DE GUANAJUATO MENOS CELAYA Y SAN JOSE ITURBIDE + LAGOS DE MORENO + EDO DE AGUASCALIENTES','ON LINE','01092020-45-5-2',NULL,NULL,NULL,NULL,'Lidia Arellano Urbina'),(190,'09/01/2020',45,'ARCELORMITTAL ',2,'OPERADOR DE MONTACARGAS (ORDER PICKER)',2,'Ing. Cesar Octavio Vazquez Galicia','0500;1100;undefined;undefined;7:30','','10','Intermedio','','2020-08-19 00:18:32.826','3','Curso Asignado',3,'ESTADO DE GUANAJUATO MENOS CELAYA Y SAN JOSE ITURBIDE + LAGOS DE MORENO + EDO DE AGUASCALIENTES','ON LINE','01092020-45-2-2','Jueves 20 de Agosto de 2020','','123456',NULL,'Lidia Arellano Urbina'),(191,'09/01/2020',45,'ARCELORMITTAL ',2,'OPERADOR DE MONTACARGAS (ORDER PICKER)',12,'Ing. Fernando Mares Ortiz','1000;1830;undefined;undefined;08:30','','6','Básico','','2020-08-11 18:06:43.161','3','Curso Asignado',3,'ESTADO DE GUANAJUATO MENOS CELAYA Y SAN JOSE ITURBIDE + LAGOS DE MORENO + EDO DE AGUASCALIENTES','ON LINE','01092020-45-12-2',NULL,NULL,NULL,NULL,'Lidia Arellano Urbina'),(192,'09/01/2020',45,'ARCELORMITTAL ',2,'OPERADOR DE MONTACARGAS (ORDER PICKER)',13,'Ing. Maximino Garcia Camilo','1000;1500;undefined;undefined;9:00','','13','Intermedio','','2020-08-11 19:56:29.175','3','Confirmado Instructor',3,'ESTADO DE GUANAJUATO MENOS CELAYA Y SAN JOSE ITURBIDE + LAGOS DE MORENO + EDO DE AGUASCALIENTES','ON LINE','01092020-45-13-2',NULL,NULL,NULL,NULL,'Lidia Arellano Urbina'),(193,'09/01/2020',43,'TOPRE',2,'OPERADOR DE MONTACARGAS (ORDER PICKER)',14,'Ing. Rafael Humberto Cebada Beltran','1000;1900;undefined;undefined;5:30','','11','Avanzado','','2020-08-11 18:15:40.946','3','Curso Asignado',2,'ESTADO DE QUERETARO + CELAYA Y SAN JOSE ITURBIDE GTO + TEPEJI DEL RIO HIDALGO','ON LINE','01092020-43-14-2',NULL,NULL,NULL,NULL,'Lidia Arellano Urbina'),(194,'09/01/2020',43,'TOPRE',2,'OPERADOR DE MONTACARGAS (ORDER PICKER)',3,'Ing. Carlos Alberto Dominguez Mejia','1000;1700;undefined;undefined;10:30','','12','Avanzado','','2020-08-11 18:24:22.836','3','Curso Asignado',2,'ESTADO DE QUERETARO + CELAYA Y SAN JOSE ITURBIDE GTO + TEPEJI DEL RIO HIDALGO','ON LINE','01092020-43-3-2',NULL,NULL,NULL,NULL,'Lidia Arellano Urbina'),(196,'08/19/2020',43,'TOPRE',1,'OPERADOR DE MONTACARGAS',12,'Ing. Fernando Mares Ortiz','0600;1430;undefined;undefined;8:00','','17','Avanzado','comida de 11 a 11.30 am','2020-08-18 21:21:02.837','37','Curso Editado',2,'ESTADO DE QUERETARO + CELAYA Y SAN JOSE ITURBIDE GTO + TEPEJI DEL RIO HIDALGO','PRESENCIAL','19082020-43-14-1',NULL,NULL,NULL,NULL,'Olivier Sanchez'),(198,'08/19/2020',48,'SAYER BAJIO',15,'PLAN DE IZAJE',11,'Ing. Ramon Noriega Lopez','0700;1500;undefined;undefined;08:00','','9','Intermedio','','2020-08-18 21:25:50.041','37','Curso Editado',2,'ESTADO DE QUERETARO + CELAYA Y SAN JOSE ITURBIDE GTO + TEPEJI DEL RIO HIDALGO','PRESENCIAL','19082020-48-11-15',NULL,NULL,NULL,NULL,'Olivier Sanchez'),(199,'08/21/2020',27,'SHOWA',3,'OPERADOR DE GRUAS VIAJERAS Y POLIPASTOS',5,'Ing. Gerardo Federico Moreno Specia','0700;1500;undefined;undefined;8:00','','17','Avanzado','','2020-08-18 21:43:41.222','4','Curso Asignado',3,'ESTADO DE GUANAJUATO MENOS CELAYA Y SAN JOSE ITURBIDE + LAGOS DE MORENO + EDO DE AGUASCALIENTES','PRESENCIAL','21082020-27-5-3',NULL,NULL,NULL,NULL,'Karla Ivonne Hernandez Mena'),(200,'08/21/2020',28,'GRUPO ACERERO 122 DIVISION GALYP',12,'OPERADOR DE PLATAFORMA ELEVADORA',9,'Ing. Ricardo Geciel Velazquez Medina','0600;1400;undefined;undefined;08:00','','9','Intermedio','','2020-08-18 21:44:46.883','5','Curso Asignado',1,'ESTADO DE SAN LUIS POTOSI','PRESENCIAL','21082020-28-9-12',NULL,NULL,NULL,NULL,'Isabel Gabriela Perez Chavez'),(201,'08/21/2020',18,'TENNECO AMAZOC PUEBLA',2,'OPERADOR DE MONTACARGAS (ORDER PICKER)',11,'Ing. Ramon Noriega Lopez','0530;1430;undefined;undefined;09:00','','17','Intermedio','d+1','2020-08-19 08:55:18.255','9','Curso Asignado',7,'ESTADO DE PUEBLA + ESTADO DE TLAXCALA + ESTADO DE MORELOS','PRESENCIAL','21082020-18-11-2',NULL,NULL,NULL,NULL,'Veronica Tejeda Gutierrez');
/*!40000 ALTER TABLE `asignaciones` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `clientes`
--

DROP TABLE IF EXISTS `clientes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `clientes` (
  `id_cliente` bigint(20) NOT NULL AUTO_INCREMENT,
  `archivos_cliente` varchar(255) DEFAULT NULL,
  `documentos_acceso_cliente` varchar(255) NOT NULL,
  `domicilio_cliente` varchar(1000) NOT NULL,
  `email_cliente` varchar(255) NOT NULL,
  `google_maps_cliente` varchar(2550) NOT NULL,
  `imagen_logo_cliente` varchar(255) DEFAULT NULL,
  `informacion_paqueteria_cliente` varchar(255) NOT NULL,
  `material_didactico_cliente` varchar(255) NOT NULL,
  `nombre_completo_cliente` varchar(220) NOT NULL,
  `nombre_contacto_recibe_cliente` varchar(255) NOT NULL,
  `nombre_corto_cliente` varchar(220) NOT NULL,
  `nota_cliente` varchar(255) DEFAULT NULL,
  `pauta_entregable_cliente` varchar(2500) NOT NULL,
  `pauta_general_cliente` varchar(2550) NOT NULL,
  `pauta_operativa_cliente` varchar(2550) NOT NULL,
  `reglas_acceso_cliente` varchar(255) NOT NULL,
  `representante_empresa_cliente` varchar(255) NOT NULL,
  `representante_trabajador_cliente` varchar(255) NOT NULL,
  `rfc_cliente` varchar(14) NOT NULL,
  `status_cliente` varchar(255) NOT NULL,
  `telefono_cliente` varchar(50) NOT NULL,
  `user_create_cliente` bigint(20) NOT NULL,
  `region_cliente_id_region` bigint(20) DEFAULT NULL,
  `create_at_cliente` datetime(6) NOT NULL,
  `vendedor_cliente_id_vendedor` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id_cliente`),
  UNIQUE KEY `UK_5jfytvrvm0lgy55e9ouwh5vg2` (`domicilio_cliente`),
  UNIQUE KEY `UK_hw0p8k74odnmh0o7r2mhr5bay` (`email_cliente`),
  UNIQUE KEY `UK_k0o4wtemhxwxydbjwbyoi7j81` (`telefono_cliente`),
  KEY `FK7m6q426qas27e994e0vgf9k3k` (`region_cliente_id_region`),
  KEY `FKe8jykeh27iexnn0620qsjiims` (`vendedor_cliente_id_vendedor`),
  CONSTRAINT `FK7m6q426qas27e994e0vgf9k3k` FOREIGN KEY (`region_cliente_id_region`) REFERENCES `regiones` (`id_region`),
  CONSTRAINT `FKe8jykeh27iexnn0620qsjiims` FOREIGN KEY (`vendedor_cliente_id_vendedor`) REFERENCES `vendedores` (`id_vendedor`)
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clientes`
--

LOCK TABLES `clientes` WRITE;
/*!40000 ALTER TABLE `clientes` DISABLE KEYS */;
INSERT INTO `clientes` VALUES (18,'perfilCliente.txt','IFE, Credencial Uniprotec y cuestionario A del protocolo del cliente ','Carril del Norte San Felipe S/N , Parque Industrial Chachapa, Chachapa Amazoc, Puebla, CP 72990','amartinez5@tenneco.com','https://www.google.com/maps/place/Tenneco+Automotive+Puebla/@19.0678047,-98.1092037,15.56z/data=!4m5!3m4!1s0x85cfea5ac60a1cf3:0x4aa61ee41546d583!8m2!3d19.0684515!4d-98.1027948','UNIPROTEC.jpg','Con Fedex','Normal','AUTOPARTES WALKER S DE RL DE CV','ADRIANA MARTÍNEZ GUERRERO  ','TENNECO AMAZOC PUEBLA',NULL,'NORMAL','NA','NORMAL','30 Min antes, EPP completo y EPP Sanitario con cubreboca ATSM 1 que no sea lavable','MARIA GUADALUPE MARTINEZ HERNANDEZ ','BLANCA DE SANTIAGO CAMACHO','AWA911128HV8','Actualizado','2223627562',1,7,'2020-08-05 21:21:46.636000',8),(27,'perfilCliente.txt','INE + credencial Uniprotec con monitoreo de temperatura actualizado','Calle Mineral de Peñafiel 77, Col Parque Industrial Santa Fe Puerto Interior, Silao, GTO, CP 36275','BCruz@showa.com.mx','https://www.google.com/maps/place/Showa+Autoparts+Mexico,+Guanajuato/@20.9999921,-101.4745385,17z/data=!3m1!4b1!4m5!3m4!1s0x842b997a385f07af:0xc8fc9355e5531858!8m2!3d20.9999921!4d-101.4723498','UNIPROTEC.jpg','Paquete Express','Normal','SHOWA AUTOPARTS MEXICO  S.A. DE C.V.','Berenice de la Cruz y Ing Jose Luis Morales','SHOWA',NULL,'Vendedor envia datos completos, se hace el entregable hasta tener los datos, En el campo de ocupación especifica favor de poner directamente en las constancias la 4.6 procesos industriales ','NA','NORMAL','30 Min antes, EPP completo, EPP y medidas del protocolo de seguridad sanitaria de Uniprotec','Gabriela Margarita Cabeza de Vaca Cardenas ','José Jesús Vega Monroy ','SAM1211014G9','Actualizado','4727485800',1,3,'2020-08-05 21:21:25.929000',3),(28,'perfilCliente.txt','INE + credencial Uniprotec con monitoreo de temperatura actualizado','Av Eje 122, No 335 esq Av CFE, Col Zona Industrial, San Luis Potos, SLP , CP 78395','mesparza@fonderia.com','https://www.google.com/maps/place/Grupo+Acerero+Fonderia/@22.08885,-100.9082022,17.5z/data=!4m5!3m4!1s0x842aa36985a70917:0x71b10c6ec95297ab!8m2!3d22.0888929!4d-100.9066025?hl=es-419','UNIPROTEC.jpg','Paquete Express mencionando Div Galyp','Normal','FITCE SA DE CV','Lic. Miriam Esparza','GRUPO ACERERO 122 DIVISION GALYP',NULL,'NORMAL','NA','NORMAL','30 Min antes, EPP completo, EPP y medidas del protocolo de seguridad sanitaria de Uniprotec','en blanco','en blanco','FIT130802RZ4','Actualizado','4448707905',1,1,'2020-08-05 21:19:16.690000',4),(29,'INGRESO DE PERSONAL - GENERAL.pdf','INE + credencial Uniprotec con monitoreo de temperatura actualizado + Comprobante IMSS + SUA + Cerificado medico no mayor a 15 dias (sera valido durante 6 meses ante Jafra)','Av la Estacada 201, Parque Ind. Queretaro, Queretaro, QRO, CP 76215','Gabriela_Camacho@jafra.com.mx','https://www.google.com/maps/place/Jafra+Manufacturing+S.+A.+de+C.+V./@20.832586,-100.4258357,17z/data=!3m1!4b1!4m5!3m4!1s0x85d35b36002a381f:0xdfc2e31246167bd1!8m2!3d20.832586!4d-100.423647','logo jafra.jpg','Paquete Express','Normal','JAFRA MANUFRACTURING SA DE CV','Gabriela Camacho y Luis Madrigal','JAFRA',NULL,'NORMAL','NA','NORMAL','30 Min antes, EPP completo, EPP y medidas del protocolo de seguridad sanitaria de Uniprotec + traje Tyvek','en blanco','en blanco','JMA0904211G3','Actualizado','4421015000',1,2,'2020-08-11 02:16:10.644000',4),(30,'perfilCliente.txt','INE','Carretera Celaya Salamanca KM 12.5 Rancho el Pintor, Villagran, CP 38260 Guanajuato','luismanuel.esquivel@ferro.com','https://www.google.com/search?bih=969&biw=1920&hl=es-419&tbm=lcl&sxsrf=ALeKk00Rk0L88pZu0wRP21ZVn7SfFm0zbA%3A1588028521564&ei=aWSnXtjSIYS4tAbF8Z6wBw&q=ferro+mexicana+sa+de+cv+guanajuato&oq=ferro+mexicana+sa+&gs_l=psy-ab.3.1.0l3j0i22i30k1l4j0i22i10i30k1.739031.746065.0.752539.54.30.0.0.0.0.344.4232.0j14j7j1.23.0....0...1.1.64.psy-ab..35.19.3962.10..38j35i39k1j35i362i39k1j0i131k1j0i67k1j0i10k1j0i13k1.345.A-CzphU_0p0#rlfi=hd:;si:3498780906562540629;mv:[[20.612433245399124,-100.86491425159072],[20.470332966169757,-101.1224063170204],null,[20.541399612880245,-100.99366028430556],13]','FERRO.jpg','Se envia con Fedex','Generico','FERRO MEXICANA, S.A. DE C.V.','Ing. Luis Esquivel','FERRO MEXICANA',NULL,'Normal','NA','Normal','EPP COMPLETO 30 MINUTOS ANTES DEL CURSO','Garza Espinoza Fernando','Mendoza Lopez Martin','FME7303132T1','Actualizado','4611243090',1,2,'2020-08-05 21:18:44.715000',14),(41,'perfilCliente.txt','INE + credencial Uniprotec con monitoreo de temperatura actualizado','Av 20 de Noviembre #1200, Col Barrio de Tlaxcala, San Luis Potosi, SLP , CP 78038','brenda.ramirez@Wabtec.com','https://www.google.com/maps/place/Gets+Locomotive+Services+Noreste+S.A.+de+C.V./@22.1606824,-100.9740087,17z/data=!3m1!4b1!4m5!3m4!1s0x842aa1fa4c031c81:0x9974e7575af6237e!8m2!3d22.1606774!4d-100.97182','UNIPROTEC.jpg','Paquete Express','Presentacion y Manual especial en BOX carpeta clientes con material especial y luego carpeta General Electric WABTEC','Wabtec Locomotive Services Noreste, S.A. de C.V.','BRENDA RAMIREZ','WABTEC GENERAL ELECTRIC',NULL,'Vendedor envia datos completos antes del curso para que se generen DC3, Diplomas y Credenciales que deberan ser entregados al final del curso a los participantes aprobados. Las DC3 deberan ser firmadas de forma manuscrita','NA','Revisar con vendedor si se debe llevar factura impresa el dia del curso','30 Min antes, EPP completo, EPP y medidas del protocolo de seguridad sanitaria de Uniprotec','Araceli Agundis Aguilar','José Esteban Hernández Gutierrez','GLS980105GJ1','Actualizado','4448042914',1,1,'2020-08-05 21:22:27.702000',10),(42,'perfilCliente.txt','INE + credencial Uniprotec con monitoreo de temperatura actualizado + SUA actualizado impreso','Super Carretera 80 (San Luis Potosi - Villa de Arriaga km 3.3), Col. Parque Industrial Logistic I, Villa de los Reyes, San Luis Potosí, CP 79525','malu.gutierrez@gm.com','https://www.google.com.mx/maps/place/Complejo+General+Motors/@21.9906851,-100.8801575,16.13z/data=!4m5!3m4!1s0x842abbec30c655c1:0xd04d85aaa60a593a!8m2!3d21.9898222!4d-100.8755887?hl=es-419','1200px-Logo_of_General_Motors.svg.png','DHL','Normal','General Motors de Mexico S. de R.L. de C.V.','Lic. María de Lourdes Gtz Herrera / Andres Ramirez','GENERAL MOTORS SAN LUIS POTOSI',NULL,' Normal pero no hay credenciales','Los Refresh de 4 horas son de pura teoria, los cursos completos de 8 horas son teoria y practica. No esta permitido proyectar ni entregar ningun documentos con LOGO o Marcas de GM','Mandar previamente el SUA actualizado a malu.gutierrez@gm.com y a esteban.montoyacontreras@gm.com con asunto de correo envio de SUA proveedor Uniprotec SA de CV y en el correo mencionar el nombre completo del instructor, los cursos que impartirá y las fechas de imparticion','Ingreso Acceso 2, 30 Min antes, EPP completo, EPP y medidas del protocolo de seguridad sanitaria de Uniprotec, Completar cuestionario de evaluacion sanitaria a la entrada','SAMANTA AGUILAR HINOJOSA','CARLOS IVAN BERNABÉ GONZÁLEZ','GMM3612284D0','Actualizado','4441139441',1,1,'2020-08-05 21:18:00.320000',3),(43,'perfilCliente.txt','INE + credencial Uniprotec con monitoreo de temperatura actualizado','Yaquis 9 , Col Nuevo Parque Industrial, San Juan del Rio, QRO, CP 76806','jose.becerra@topre.com.mx','https://www.google.com.mx/maps/place/TOPRE+AUTOPARTS+MEXICO+SA+DE+CV/@20.3607328,-99.9713783,14.34z/data=!4m5!3m4!1s0x85d30bae0fca7159:0xb8828a4d96d2a30!8m2!3d20.3680854!4d-99.9539884?hl=es-419','UNIPROTEC.jpg','Paquete Express','Normal','TOPRE AUTOPARTS MEXICO SERVICES SA DE CV ','JOSÉ ANTONIO BECERRA TOLEDO','TOPRE',NULL,'Vendedor envia datos, no se hace entregable hasta tener datos, al recibir los datos se deben de pedir diplomas y credenciales (si es curso con credencial) en digital de inmediato a Oscar y se deben de mandar DC3 en PDF  a vendedor. Adicional se elabora el entregable normal completo fisico','Normal','NORMAL','30 Min antes, EPP completo, EPP y medidas del protocolo de seguridad sanitaria de Uniprotec','MARI UNNO ','HUGO SANTIAGO','TAM120425UC8','Actualizado','4272682200',1,2,'2020-08-05 21:22:07.720000',2),(44,'perfilCliente.txt','INE + credencial Uniprotec con monitoreo de temperatura actualizado','Cohuaila #5 , Col Obrera, Queretaro, QRO , CP 76130','laura.reyes@o-i.com','https://www.google.com.mx/maps/place/Vidriera+Quer%C3%A9taro+S.A./@20.6176434,-100.4278044,17.75z/data=!4m5!3m4!1s0x85d35a93d0437d03:0x56259bfd8f3dab5e!8m2!3d20.6178877!4d-100.4262614?hl=es-419','UNIPROTEC.jpg','Paquete Express','Materiales especificos presentaciones y manuales disponibles en Box segun el curso en Caperta cliente con material especial 2020 y luego carpeta OWENS 2020','OWENS AMERICA S DE RL DE CV','LAURA REYES HERNANDEZ','OWENS QRO',NULL,'EL instructor pide lista de participantes con su respectiva razon social  al cliente, hay 2 razones sociales, los datos de ambas estan cargados en archivos cliente. Aplica credencial para trabajos en altura poniendo en concepto Trabajo en Altura','Enfocado a procedimientos OWENS segun el curso','NORMAL','30 Min antes, EPP completo, EPP y medidas del protocolo de seguridad sanitaria de Uniprotec','JOSE ANTONIO LUCKIE PACHECO','JUAN VEGA MEDINA','VAM140630FL1','Actualizado','4422111040',1,2,'2020-08-05 21:20:02.779000',17),(45,'perfilCliente.txt','INE + credencial Uniprotec con monitoreo de temperatura actualizado + Certificado Medico original no mayor a 2 dias','PASEO DE LOS INDUSTRIALES PTE lote 15-19, Parque Industrial FIPASI, Silao, GTO, CP 36100','victoria.carrillo@arcelormittal.com','https://www.google.com.mx/maps/place/Arcelor+Mittal/@20.9048862,-101.3936941,17z/data=!3m1!4b1!4m5!3m4!1s0x842b9cd454ff2927:0xdc97630f87aea043!8m2!3d20.9048862!4d-101.3915054?hl=es-419','UNIPROTEC.jpg','Paquete Express','Normal','ARCELORMITTAL TAILORED BLANKS SILAO S DE RL DE CV','Victoria Carrillo ','ARCELORMITTAL ',NULL,'Vendedor envia datos completos, se hace el entregable hasta contar con ellos','Normal','Instructor Mandar Certificado Medico Digital No mayor a 2 dias a vendedor','30 Min antes, EPP completo, EPP y medidas del protocolo de seguridad sanitaria de Uniprotec','Ivan Omar Zaldivar Zaldivar','María Isabel Barrón Ramírez','ASU041126KA7','Actualizado','4727377122',1,3,'2020-08-05 21:16:40.545000',2),(46,'perfilCliente.txt','INE + credencial Uniprotec con monitoreo de temperatura actualizado','Av Michoacan #20, Nave industrial No 12, Parque Industrial FINSA, Col Renovacion, IZTAPALAPA , CDMX, CP 09209','patricio.delariva@se.com','https://www.google.com/maps/place/Schneider-Electric+Planta+CDMX/@19.3714687,-99.0584597,17z/data=!3m1!4b1!4m5!3m4!1s0x85d1fd0a3fde67ad:0x20bb6b7f6a20b648!8m2!3d19.3714687!4d-99.056271','UNIPROTEC.jpg','Paquete Express','Normal','SCHNEIDER ELECTRIC MEXICO SA DE CV ','Patricio de la Riva','SCHNEIDER CDMX AV MICHOACAN',NULL,'Normal pero las credenciales son especiales mencionando los equipos moviles autorizados en caso de curso de equipos moviles','En los cursos de equipos moviles se manejan varios equipos, revisar bien como se agendo el curso y cumplir con todos los equipos','NORMAL','30 Min antes, EPP completo, EPP y medidas del protocolo de seguridad sanitaria de Uniprotec','en blanco','en blanco','SEM940926N44','Actualizado','5547801400',1,6,'2020-08-05 21:20:47.189000',3),(47,'perfilCliente.txt','INE + credencial Uniprotec con monitoreo de temperatura actualizado','Av. Javier Rojo Gómez 1121-A, San Pedro, Iztapalapa, 09300 Ciudad de México, CDMX','adrian.reyeszuniga@se.com','https://www.google.com.mx/maps/place/Schneider+Electric/@19.3665867,-99.0860663,17z/data=!3m1!4b1!4m5!3m4!1s0x85d1fdeef7893c2d:0x659e940f18d58a5e!8m2!3d19.3665867!4d-99.0838776','UNIPROTEC.jpg','Paquete Express','Normal','SCHNEIDER ELECTRIC MEXICO SA DE CV ','Adrian Reyes Zuñiga','SCHNEIDER CDMX ROJO GOMEZ',NULL,'Normal pero las credenciales son especiales mencionando los equipos moviles autorizados en caso de curso de equipos moviles','En los cursos de equipos moviles se manejan varios equipos, revisar bien como se agendo el curso y cumplir con todos los equipos','NORMAL','30 Min antes, EPP completo, EPP y medidas del protocolo de seguridad sanitaria de Uniprotec','en blanco','en blanco','SEM940926N44','Actualizado','5558045000',1,6,'2020-08-05 21:21:05.949000',3),(48,'perfilCliente.txt','INE + credencial Uniprotec con monitoreo de temperatura actualizado +  credencial de induccion de sayer vigente , de no tener la credencial sayer se debe tomar un video de 10 minutos ','Carretera Libre QRO- Celaya KM 13.7 , Col San Isidro del LLanito, APASEO EL ALTO, GTO, CP 38513','jroque@gruposayer.com','https://www.google.com.mx/maps/place/Grupo+Sayer/@20.5313228,-100.5116772,17z/data=!3m1!4b1!4m5!3m4!1s0x85d34e8b0038feb1:0x6a5011345d44ff61!8m2!3d20.5313228!4d-100.5094885?hl=es-419','UNIPROTEC.jpg','Paquete Express pero el cp debe salir 76060','Normal','PRESTADORA DE SERVICIOS TECNICA SA DE CV','Ing. Joel Roque / Jenny Gutierrez','SAYER BAJIO',NULL,'Normal pero no hay logo cliente ni en dc3 ni en credenciales','Los refresh de Montacargas son de 4 horas, 1h30 de teoria sin manuales, examen teorico final y 2h30 de practica','NORMAL','30 Min antes, EPP completo, EPP y medidas del protocolo de seguridad sanitaria de Uniprotec ','Luis Alberto Gonzalez Juarez','Hugo Adrian Villanueva Delgado','PST980202NW4','Actualizado','4423171132',1,2,'2020-08-05 21:20:23.879000',4),(49,'','cliente','cliente','cliente@cliente.vom','cliente','','cliente','cliente','cliente','cliente','cliente',NULL,'cliente','cliente','cliente','cliente','cliente','cliente','clientecliente','Actualizado','654654654',1,1,'2020-08-22 08:02:48.462000',19);
/*!40000 ALTER TABLE `clientes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cursos`
--

DROP TABLE IF EXISTS `cursos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cursos` (
  `id_curso` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_at_curso` datetime(6) NOT NULL,
  `nombre_curso` varchar(220) NOT NULL,
  `nota_curso` varchar(255) DEFAULT NULL,
  `status_curso` varchar(255) NOT NULL,
  `user_create_curso` bigint(20) NOT NULL,
  PRIMARY KEY (`id_curso`)
) ENGINE=InnoDB AUTO_INCREMENT=94 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cursos`
--

LOCK TABLES `cursos` WRITE;
/*!40000 ALTER TABLE `cursos` DISABLE KEYS */;
INSERT INTO `cursos` VALUES (1,'2020-08-22 03:35:10.682000','OPERADOR DE MONTACARGAS',NULL,'Actualizado',1),(2,'2020-04-05 15:24:15.183000','OPERADOR DE MONTACARGAS (ORDER PICKER)','','Activo',1),(3,'2020-04-05 15:24:15.183000','OPERADOR DE GRUAS VIAJERAS Y POLIPASTOS','','Activo',1),(4,'2020-04-05 15:24:15.183000','OPERADOR DE GRUAS VIAJERAS','','Activo',1),(5,'2020-04-05 15:24:15.183000','OPERADOR DE POLIPASTOS','','Activo',1),(6,'2020-04-05 15:24:15.183000','OPERADOR DE GRUA MOVIL','','Activo',1),(7,'2020-04-05 15:24:15.183000','OPERADOR DE PATIN ELECTRICO','','Activo',1),(8,'2020-04-05 15:24:15.183000','OPERADOR DE PATIN HIDRAULICO','','Activo',1),(9,'2020-04-05 15:24:15.183000','OPERADOR DE PLATAFORMA (COLCHON DE AIRE)','','Activo',1),(10,'2020-04-05 15:24:15.183000','OPERADOR DE PATIN (MOVER TRUCK)','','Activo',1),(11,'2020-04-05 15:24:15.183000','OPERADOR DE TRACTOR REMOLQUE CLASE VI (TUGGER)','','Activo',1),(12,'2020-04-05 15:24:15.183000','OPERADOR DE PLATAFORMA ELEVADORA','','Activo',1),(13,'2020-04-05 15:24:15.183000','OPERADOR DE EQUIPO MOVIL TODO TERRENO (POLARIS)','','Activo',1),(14,'2020-04-05 15:24:15.183000','OPERADOR DE MINI CARGADOR FRONTAL (BOBCAT)','','Activo',1),(15,'2020-04-05 15:24:15.183000','PLAN DE IZAJE','','Activo',1),(16,'2020-04-05 15:24:15.183000','SEGURIDAD EN EL MANEJO DE MAQUINARIA, EQUIPO Y HERRAMIENTAS NOM-004-STPS','','Activo',1),(17,'2020-04-05 15:24:15.183000','MANEJO Y ALMACENAMIENTO DE MATERIALES NOM-006-STPS','','Activo',1),(18,'2020-04-05 15:24:15.183000','BLOQUEO DE ENERGIA LOTO','','Activo',1),(19,'2020-04-05 15:24:15.183000','MANEJO DE SUSTANCIAS QUIMICAS ','','Activo',1),(20,'2020-04-05 15:24:15.183000','MANEJO DE RESIDUOS PELIGROSOS','','Activo',1),(21,'2020-04-05 15:24:15.183000','SEGURIDAD EN TRABAJOS EN ALTURA NOM 009 STPS','','Activo',1),(22,'2020-04-05 15:24:15.183000','CONDICIONES DE RUIDO EN LOS CENTROS DE TRABAJO NOM 011 STPS','','Activo',1),(23,'2020-04-05 15:24:15.183000','EQUIPO DE PROTECCION PERSONAL EPP NOM 017 STPS','','Activo',1),(24,'2020-04-05 15:24:15.183000','SGA SISTEMA GLOBALMENTE ARMONIZADO NOM 018 STPS','','Activo',1),(25,'2020-04-05 15:24:15.183000','COMISION DE SEGURIDAD E HIGIENE NOM 019 STPS','','Activo',1),(26,'2020-04-05 15:24:15.183000','OPERADOR DE RECIPIENTES SUJETOS A PRESION NOM 020 STPS','','Activo',1),(27,'2020-04-05 15:24:15.183000','OPERACIÓN SEGURA Y FUNCIONAMIENTO DE CALDERAS NOM 020 STPS','','Activo',1),(28,'2020-04-05 15:24:15.183000','OPERADOR DE RECIPIENTES SUJETOS A PRESION Y CALDERAS NOM 020 STPS','','Activo',1),(29,'2020-04-05 15:24:15.183000','ELECTRICIDAD ESTATICA EN LOS CENTROS DE TRABAJO CONDICIONES DE SEGURIDAD NOM- 022-STPS-2015 ','','Activo',1),(30,'2020-04-05 15:24:15.183000','CONDICIONES DE ILUMINACION EN LOS CENTROS DE TRABAJO NOM 025 STPS','','Activo',1),(31,'2020-04-05 15:24:15.183000','INTERPRETACION DE LOS ELEMENTOS DE SEÑALIZACION NOM 026 STPS','','Activo',1),(32,'2020-04-05 15:24:15.183000','SEGURIDAD EN CORTE Y SOLDADURA NOM 027-STPS','','Activo',1),(33,'2020-04-05 15:24:15.183000','PROCESOS Y EQUIPOS CRITICOS QUE MANEJAN SUSTANCIAS QUIMICAS NOM 028 STPS','','Activo',1),(34,'2020-04-05 15:24:15.183000','SEGURIDAD EN MANTENIMIENTO A INSTALACIONES ELECTRICAS NOM 029 STPS','','Activo',1),(35,'2020-04-05 15:24:15.183000','SERVICIOS DE SEGURIDAD Y SALUD EN EL TRABAJO NOM-030-STPS ','','Activo',1),(36,'2020-04-05 15:24:15.183000','SEGURIDAD EN TRABAJOS EN ESPACIOS CONFINADOS NOM-033-STPS-2015','','Activo',1),(37,'2020-04-05 15:24:15.183000','FACTORES DE RIESGOS PSICOSOCIALES EN EL TRABAJO, IDENTIFICACION, ANALISIS Y PREVENCION NOM-035-STPS-2018','','Activo',1),(38,'2020-04-05 15:24:15.183000','MANEJO DEFENSIVO','','Activo',1),(39,'2020-04-05 15:24:15.183000','LEGISLACION AMBIENTAL','','Activo',1),(40,'2020-04-05 15:24:15.183000','LEGISLACION, SEGURIDAD HIGIENE Y OCUPACIONAL','','Activo',1),(41,'2020-04-05 15:24:15.183000','IDENTIDIFICACION DE PELIGROS Y EVALUACION DE RIESGO','','Activo',1),(42,'2020-04-05 15:24:15.183000','INVESTIGACION DE ACCIDENTES E INCIDENTES','','Activo',1),(43,'2020-04-05 15:24:15.183000','ANALISIS DE CAUSA RAIZ Y DETERMINACION DE PLANES DE ACCION','','Activo',1),(44,'2020-04-05 15:24:15.183000','FACTORES DE RIESGO ERGONOMICO NOM-036-STPS ','','Activo',1),(45,'2020-04-05 15:24:15.183000','SALUD OCUPACIONAL (ATMOSFERAS EXPLOSIVAS ATEX)','','Activo',1),(46,'2020-04-05 15:24:15.183000','MANEJO DE GAS NATURAL GAS LP','','Activo',1),(47,'2020-04-05 15:24:15.183000','FORMACION DE SUPERVISORES','','Activo',1),(48,'2020-04-05 15:24:15.183000','TRABAJO EN CALIENTE','','Activo',1),(49,'2020-04-05 15:24:15.183000','CUIDADO DE LAS MANOS','','Activo',1),(50,'2020-04-05 15:24:15.183000','SEGURIDAD EN EL MANEJO DE HORNOS','','Activo',1),(51,'2020-04-05 15:24:15.183000','MANTENIMIENTO A PLANTAS DE TRATAMIENTO DE AGUAS RESIDUALES','','Activo',1),(52,'2020-04-05 15:24:15.183000','8 DS','','Activo',1),(53,'2020-04-05 15:24:15.183000','SIX SIGMA ','','Activo',1),(54,'2020-04-05 15:24:15.183000','MANTENIMIENTO PRODUCTIVO TOTAL (TPM LEAN MANUFACTURING)','','Activo',1),(55,'2020-04-05 15:24:15.183000','LAS 5S','','Activo',1),(56,'2020-04-05 15:24:15.183000','PROCESOS DE SOLDADURA ','','Activo',1),(57,'2020-04-05 15:24:15.183000','PLC ','','Activo',1),(58,'2020-04-05 15:24:15.183000','INTSTRUMENTOS DE MEDICION','','Activo',1),(59,'2020-04-05 15:24:15.183000','MAQUINAS Y HERRAMIENTAS','','Activo',1),(60,'2020-04-05 15:24:15.183000','ELECTRICIDAD INDUSTRIAL','','Activo',1),(61,'2020-04-05 15:24:15.183000','MANTENIMIENTO A SUBESTACIONES ELECTRICAS','','Activo',1),(62,'2020-04-05 15:24:15.183000','ELECTRONICA INDUSTRIAL','','Activo',1),(63,'2020-04-05 15:24:15.183000','NEUMATICA','','Activo',1),(64,'2020-04-05 15:24:15.183000','HIDRAULICA','','Activo',1),(65,'2020-04-05 15:24:15.183000','AIRE ACONDICIONADO','','Activo',1),(66,'2020-04-05 15:24:15.183000','CONTROL DE MOTORES CONTROL ELECTRICO','','Activo',1),(67,'2020-04-05 15:24:15.183000','FORMACION DE BRIGADA DE COMBATE CONTRA INCENDIO','','Activo',1),(68,'2020-04-05 15:24:15.183000','MANEJO DE EXTINTORES','','Activo',1),(69,'2020-04-05 15:24:15.183000','FORMACION DE BRIGADA DE PRIMEROS AUXILIOS','','Activo',1),(70,'2020-04-05 15:24:15.183000','FORMACION DE BRIGADA DE COMUNICACION, EVACUACION Y RESCATE','','Activo',1),(71,'2020-04-05 15:24:15.183000','COMUNICACIÓN SISTEMA DE COMANDO DE INCIDENTES','','Activo',1),(72,'2020-04-05 15:24:15.183000','RESPUESTA A EMERGENCIA CONTRA DERRAMES','','Activo',1),(73,'2020-04-05 15:24:15.183000','BRIGADA MULTIFUNCIONAL','','Activo',1),(74,'2020-04-05 15:24:15.183000','RESCATE EN ALTURAS Y ESPACIOS CONFINADOS','','Activo',1),(75,'2020-04-05 15:24:15.183000','FORMACION DE INSTRUCTORES','','Activo',1),(76,'2020-04-05 15:24:15.183000','HABITOS DE LA GENTE EFECTIVA','','Activo',1),(77,'2020-04-05 15:24:15.183000','COMUNICACIÓN EFECTIVA','','Activo',1),(78,'2020-04-05 15:24:15.183000','MOTIVACION','','Activo',1),(79,'2020-04-05 15:24:15.183000','TRABAJO EN EQUIPO','','Activo',1),(80,'2020-04-05 15:24:15.183000','LIDERAZGO','','Activo',1),(81,'2020-04-05 15:24:15.183000','RECLUTAMIENTO EFECTIVO','','Activo',1),(82,'2020-04-05 15:24:15.183000','LIDERAZGO GERENCIAL','','Activo',1),(83,'2020-04-05 15:24:15.183000','ADMINISTRACION DEL TIEMPO','','Activo',1),(84,'2020-04-05 15:24:15.183000','ATENCIÓN A CLIENTES','','Activo',1),(85,'2020-04-05 15:24:15.183000','HABILIDADES GERENCIALES','','Activo',1),(86,'2020-04-05 15:24:15.183000','MANEJO DE CONFLICTOS','','Activo',1),(87,'2020-04-05 15:24:15.183000','MANEJO Y SOLUCIÓN DE CONFLICTOS','','Activo',1),(88,'2020-04-05 15:24:15.183000','NEGOCIACIONES EFECTIVAS','','Activo',1),(89,'2020-04-05 15:24:15.183000','MANEJO DE ESTRÉS LABORAL','','Activo',1),(90,'2020-04-05 15:24:15.183000','INTELIGENCIA EMOCIONAL PNL','','Activo',1),(91,'2020-04-05 15:24:15.183000','TOMA DE DECISIONES','','Activo',1),(92,'2020-04-05 15:24:15.183000','TRABAJO EN EQUIPO','','Activo',1);
/*!40000 ALTER TABLE `cursos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cursos_instructores`
--

DROP TABLE IF EXISTS `cursos_instructores`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cursos_instructores` (
  `curso_id` bigint(20) NOT NULL,
  `instructor_id` bigint(20) NOT NULL,
  UNIQUE KEY `UK50m0iicew3t3nyhvsh9ef9rer` (`curso_id`,`instructor_id`),
  KEY `FK9m2cfiog0c1mk3bhueifed6c2` (`instructor_id`),
  CONSTRAINT `FK837mv81v90k8e4ek77fxetm8e` FOREIGN KEY (`curso_id`) REFERENCES `cursos` (`id_curso`),
  CONSTRAINT `FK9m2cfiog0c1mk3bhueifed6c2` FOREIGN KEY (`instructor_id`) REFERENCES `instructores` (`id_instructor`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cursos_instructores`
--

LOCK TABLES `cursos_instructores` WRITE;
/*!40000 ALTER TABLE `cursos_instructores` DISABLE KEYS */;
INSERT INTO `cursos_instructores` VALUES (1,1),(2,1),(3,1),(4,1),(5,1),(6,1),(7,1),(8,1),(10,1),(11,1),(12,1),(13,1),(14,1),(15,1),(16,1),(17,1),(18,1),(21,1),(23,1),(29,1),(31,1),(32,1),(34,1),(36,1),(48,1),(49,1),(55,1),(1,2),(2,2),(3,2),(4,2),(5,2),(6,2),(7,2),(8,2),(11,2),(12,2),(13,2),(15,2),(16,2),(17,2),(18,2),(19,2),(20,2),(21,2),(22,2),(23,2),(24,2),(25,2),(26,2),(27,2),(28,2),(29,2),(30,2),(31,2),(32,2),(33,2),(34,2),(35,2),(36,2),(38,2),(39,2),(40,2),(41,2),(42,2),(43,2),(44,2),(45,2),(46,2),(48,2),(49,2),(50,2),(51,2),(52,2),(53,2),(54,2),(55,2),(68,2),(69,2),(70,2),(72,2),(73,2),(1,3),(2,3),(7,3),(8,3),(11,3),(13,3),(23,3),(49,3),(55,3),(1,4),(2,4),(3,4),(4,4),(5,4),(6,4),(7,4),(8,4),(11,4),(12,4),(13,4),(15,4),(16,4),(17,4),(18,4),(21,4),(23,4),(31,4),(32,4),(34,4),(36,4),(48,4),(49,4),(55,4),(1,5),(2,5),(3,5),(4,5),(5,5),(6,5),(7,5),(8,5),(9,5),(11,5),(12,5),(13,5),(14,5),(15,5),(16,5),(17,5),(18,5),(19,5),(21,5),(23,5),(24,5),(26,5),(27,5),(28,5),(29,5),(30,5),(31,5),(32,5),(33,5),(34,5),(36,5),(38,5),(44,5),(48,5),(49,5),(55,5),(37,6),(52,6),(55,6),(75,6),(76,6),(77,6),(78,6),(79,6),(80,6),(81,6),(82,6),(83,6),(84,6),(85,6),(86,6),(87,6),(88,6),(89,6),(90,6),(91,6),(92,6),(37,7),(55,7),(75,7),(76,7),(77,7),(78,7),(79,7),(80,7),(81,7),(82,7),(83,7),(84,7),(85,7),(86,7),(87,7),(88,7),(89,7),(90,7),(91,7),(92,7),(1,8),(2,8),(3,8),(4,8),(5,8),(7,8),(8,8),(9,8),(11,8),(12,8),(16,8),(18,8),(21,8),(23,8),(32,8),(36,8),(55,8),(1,9),(2,9),(3,9),(4,9),(5,9),(6,9),(7,9),(8,9),(11,9),(12,9),(15,9),(16,9),(17,9),(18,9),(19,9),(20,9),(21,9),(22,9),(23,9),(24,9),(31,9),(32,9),(34,9),(36,9),(38,9),(41,9),(42,9),(43,9),(48,9),(49,9),(55,9),(67,9),(68,9),(69,9),(70,9),(71,9),(72,9),(73,9),(19,10),(20,10),(21,10),(22,10),(23,10),(24,10),(25,10),(30,10),(31,10),(35,10),(39,10),(40,10),(41,10),(42,10),(43,10),(44,10),(45,10),(47,10),(49,10),(51,10),(52,10),(55,10),(72,10),(1,11),(2,11),(3,11),(4,11),(5,11),(6,11),(7,11),(8,11),(11,11),(12,11),(13,11),(15,11),(16,11),(17,11),(18,11),(19,11),(20,11),(21,11),(22,11),(23,11),(24,11),(25,11),(26,11),(27,11),(28,11),(30,11),(31,11),(32,11),(34,11),(35,11),(36,11),(39,11),(40,11),(45,11),(46,11),(48,11),(49,11),(55,11),(67,11),(68,11),(69,11),(70,11),(71,11),(72,11),(73,11),(1,12),(2,12),(3,12),(4,12),(5,12),(7,12),(8,12),(11,12),(12,12),(14,12),(18,12),(21,12),(32,12),(36,12),(1,13),(2,13),(3,13),(4,13),(5,13),(6,13),(7,13),(8,13),(11,13),(12,13),(13,13),(15,13),(16,13),(17,13),(18,13),(19,13),(20,13),(21,13),(22,13),(23,13),(24,13),(25,13),(26,13),(27,13),(28,13),(29,13),(30,13),(31,13),(32,13),(33,13),(34,13),(35,13),(36,13),(39,13),(40,13),(41,13),(42,13),(43,13),(46,13),(47,13),(48,13),(49,13),(51,13),(52,13),(55,13),(67,13),(68,13),(69,13),(70,13),(71,13),(72,13),(73,13),(1,14),(2,14),(3,14),(4,14),(5,14),(6,14),(7,14),(8,14),(11,14),(12,14),(13,14),(14,14),(15,14),(16,14),(17,14),(18,14),(19,14),(20,14),(21,14),(22,14),(23,14),(24,14),(25,14),(29,14),(30,14),(31,14),(32,14),(33,14),(34,14),(35,14),(36,14),(38,14),(41,14),(42,14),(43,14),(44,14),(47,14),(48,14),(49,14),(55,14),(67,14),(68,14),(69,14),(70,14),(71,14),(72,14),(73,14),(77,14),(79,14),(47,15),(56,15),(57,15),(58,15),(59,15),(60,15),(61,15),(62,15),(63,15),(64,15),(65,15),(66,15),(67,15),(68,15),(69,15),(70,15),(71,15),(72,15),(73,15),(74,15),(75,15),(76,15),(77,15),(78,15),(79,15),(80,15),(82,15),(85,15),(92,15);
/*!40000 ALTER TABLE `cursos_instructores` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `instructores`
--

DROP TABLE IF EXISTS `instructores`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `instructores` (
  `id_instructor` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_at_instructor` datetime(6) NOT NULL,
  `cursos_instructor` varchar(255) NOT NULL,
  `email_instructor` varchar(255) NOT NULL,
  `email_gmail_instructor` varchar(255) NOT NULL,
  `nombre_instructor` varchar(300) NOT NULL,
  `nota_instructor` varchar(255) DEFAULT NULL,
  `status_instructor` varchar(255) NOT NULL,
  `user_create_instructor` bigint(20) NOT NULL,
  `region_instructor_id_region` bigint(20) DEFAULT NULL,
  `list_fechas` varchar(2550) DEFAULT NULL,
  `usuario_instructor_id_usuario` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id_instructor`),
  UNIQUE KEY `email_instructor_UNIQUE` (`email_instructor`),
  UNIQUE KEY `email_gmail_instructor_UNIQUE` (`email_gmail_instructor`),
  UNIQUE KEY `nombre_instructor_UNIQUE` (`nombre_instructor`),
  KEY `FKlksq9bjejjderse63aclkkkpq` (`region_instructor_id_region`),
  KEY `FK84vcl8j3qwqmpwkje3qi64scx` (`usuario_instructor_id_usuario`),
  CONSTRAINT `FK84vcl8j3qwqmpwkje3qi64scx` FOREIGN KEY (`usuario_instructor_id_usuario`) REFERENCES `usuarios` (`id_usuario`),
  CONSTRAINT `FKlksq9bjejjderse63aclkkkpq` FOREIGN KEY (`region_instructor_id_region`) REFERENCES `regiones` (`id_region`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `instructores`
--

LOCK TABLES `instructores` WRITE;
/*!40000 ALTER TABLE `instructores` DISABLE KEYS */;
INSERT INTO `instructores` VALUES (1,'2020-07-31 23:49:11.904000','[1, 2, 3, 4, 5, 6, 7, 8, 10, 11, 12, 13, 14, 15, 16, 17, 18, 21, 23, 29, 31, 32, 34, 36, 48, 49, 55]','javier.castillo@uniprotec.net','javiercdamian1965@gmail.com','Ing. Felipe Javier Castillo Damian','dias de ausencia2','Actualizado',1,2,'Mon Aug 31 2020 00:00:00 GMT-0500 (Central Daylight Time);Tue Sep 01 2020 00:00:00 GMT-0500 (Central Daylight Time);Wed Sep 02 2020 00:00:00 GMT-0500 (Central Daylight Time);Thu Sep 03 2020 00:00:00 GMT-0500 (Central Daylight Time);Fri Sep 04 2020 00:00:00 GMT-0500 (Central Daylight Time);Sat Sep 05 2020 00:00:00 GMT-0500 (Central Daylight Time)',21),(2,'2020-04-05 15:24:15.183000','1,2,3,4,5,6,7,8,11,12,13,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,38,39,40,41,42,43,44,45,46,48,49,50,51,52,53,54,55,68,69,70,72,73','cesar.vazquez@uniprotec.net','vazquezcesarovg@gmail.com','Ing. Cesar Octavio Vazquez Galicia','nota','Activo',1,2,NULL,22),(3,'2020-04-05 15:24:15.183000','1,2,7,8,11,13,23,49,55','carlos.dominguez@uniprotec.net','car.dominguez90@gmail.com','Ing. Carlos Alberto Dominguez Mejia','nota','Activo',1,2,NULL,24),(4,'2020-04-05 15:24:15.183000','1,2,3,4,5,6,7,8,11,12,13,15,16,17,18,21,23,31,32,34,36,48,49,55','antonio.gomez@uniprotec.net','gozafest@gmail.com','Ing. Juan Antonio Gomez Diaz','nota','Activo',1,2,NULL,23),(5,'2020-08-18 21:17:14.312000','[1, 2, 3, 4, 5, 6, 7, 8, 9, 11, 12, 13, 14, 15, 16, 17, 18, 19, 21, 23, 24, 26, 27, 28, 29, 30, 31, 32, 33, 34, 36, 38, 44, 48, 49, 55]','gerardo.moreno@uniprotec.net','gspecia652008@gmail.com','Ing. Gerardo Federico Moreno Specia','nota','Actualizado',1,3,'Wed Jul 15 2020 00:00:00 GMT-0500 (Central Daylight Time);Thu Jul 16 2020 00:00:00 GMT-0500 (Central Daylight Time);Fri Jul 17 2020 00:00:00 GMT-0500 (Central Daylight Time);Sat Jul 18 2020 00:00:00 GMT-0500 (Central Daylight Time);Sun Jul 19 2020 00:00:00 GMT-0500 (Central Daylight Time);Mon Jul 20 2020 00:00:00 GMT-0500 (Central Daylight Time);Tue Jul 21 2020 00:00:00 GMT-0500 (Central Daylight Time);Wed Jul 22 2020 00:00:00 GMT-0500 (Central Daylight Time);Thu Jul 23 2020 00:00:00 GMT-0500 (Central Daylight Time);Fri Jul 24 2020 00:00:00 GMT-0500 (Central Daylight Time);Sat Jul 25 2020 00:00:00 GMT-0500 (Central Daylight Time);Sun Jul 26 2020 00:00:00 GMT-0500 (Central Daylight Time);Mon Jul 27 2020 00:00:00 GMT-0500 (Central Daylight Time);Tue Jul 28 2020 00:00:00 GMT-0500 (Central Daylight Time);Wed Jul 29 2020 00:00:00 GMT-0500 (Central Daylight Time);Thu Jul 30 2020 00:00:00 GMT-0500 (Central Daylight Time);Fri Jul 31 2020 00:00:00 GMT-0500 (Central Daylight Time)',31),(6,'2020-08-19 22:03:04.367000','[37, 52, 55, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92]','mayra.padron@uniprotec.net','mayrapadron66@gmail.com','Lic. Mayra Andrea Padron Aguilar','nota','Actualizado',1,1,'Wed Jun 10 2020 00:00:00 GMT-0500 (hora de verano central);Thu Jun 11 2020 00:00:00 GMT-0500 (hora de verano central);Fri Jun 12 2020 00:00:00 GMT-0500 (hora de verano central);Sat Jun 13 2020 00:00:00 GMT-0500 (hora de verano central);Sun Jun 14 2020 00:00:00 GMT-0500 (hora de verano central);Mon Jun 15 2020 00:00:00 GMT-0500 (hora de verano central);Tue Jun 16 2020 00:00:00 GMT-0500 (hora de verano central);Wed Jun 17 2020 00:00:00 GMT-0500 (hora de verano central);Thu Jun 18 2020 00:00:00 GMT-0500 (hora de verano central);Fri Jun 19 2020 00:00:00 GMT-0500 (hora de verano central);Sat Jun 20 2020 00:00:00 GMT-0500 (hora de verano central);Sun Jun 21 2020 00:00:00 GMT-0500 (hora de verano central);Mon Jun 22 2020 00:00:00 GMT-0500 (hora de verano central);Tue Jun 23 2020 00:00:00 GMT-0500 (hora de verano central);Wed Jun 24 2020 00:00:00 GMT-0500 (hora de verano central);Thu Jun 25 2020 00:00:00 GMT-0500 (hora de verano central)',27),(7,'2020-08-19 22:03:32.692000','[37, 55, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92]','alejandra.campos@uniprotec.net','alecmpss@gmail.com','Lic. Claudia Alejandra Campos Segura','nota','Actualizado',1,3,'Wed Jun 10 2020 00:00:00 GMT-0500 (hora de verano central);Thu Jun 11 2020 00:00:00 GMT-0500 (hora de verano central);Fri Jun 12 2020 00:00:00 GMT-0500 (hora de verano central);Sat Jun 13 2020 00:00:00 GMT-0500 (hora de verano central);Sun Jun 14 2020 00:00:00 GMT-0500 (hora de verano central);Mon Jun 15 2020 00:00:00 GMT-0500 (hora de verano central);Tue Jun 16 2020 00:00:00 GMT-0500 (hora de verano central);Wed Jun 17 2020 00:00:00 GMT-0500 (hora de verano central);Thu Jun 18 2020 00:00:00 GMT-0500 (hora de verano central);Fri Jun 19 2020 00:00:00 GMT-0500 (hora de verano central);Sat Jun 20 2020 00:00:00 GMT-0500 (hora de verano central);Sun Jun 21 2020 00:00:00 GMT-0500 (hora de verano central);Mon Jun 22 2020 00:00:00 GMT-0500 (hora de verano central);Tue Jun 23 2020 00:00:00 GMT-0500 (hora de verano central);Wed Jun 24 2020 00:00:00 GMT-0500 (hora de verano central);Thu Jun 25 2020 00:00:00 GMT-0500 (hora de verano central)',26),(8,'2020-04-05 15:24:15.183000','1,2,3,4,5,7,8,9,11,12,16,18,21,23,32,36,55','alberto.zuniga@uniprotec.net','yakk.74@gmail.com','Ing. Juan Alberto Zuñiga Vazquez','nota','Activo',1,3,NULL,25),(9,'2020-08-19 22:02:48.089000','[1, 2, 3, 4, 5, 6, 7, 8, 11, 12, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 31, 32, 34, 36, 38, 41, 42, 43, 48, 49, 55, 67, 68, 69, 70, 71, 72, 73]','ricardo.velazquez@uniprotec.net','ricardovm472@gmail.com','Ing. Ricardo Geciel Velazquez Medina','nota','Actualizado',1,1,'Wed Jul 15 2020 00:00:00 GMT-0500 (Central Daylight Time);Thu Jul 16 2020 00:00:00 GMT-0500 (Central Daylight Time);Fri Jul 17 2020 00:00:00 GMT-0500 (Central Daylight Time);Sat Jul 18 2020 00:00:00 GMT-0500 (Central Daylight Time);Sun Jul 19 2020 00:00:00 GMT-0500 (Central Daylight Time);Mon Jul 20 2020 00:00:00 GMT-0500 (Central Daylight Time);Tue Jul 21 2020 00:00:00 GMT-0500 (Central Daylight Time);Wed Jul 22 2020 00:00:00 GMT-0500 (Central Daylight Time);Thu Jul 23 2020 00:00:00 GMT-0500 (Central Daylight Time);Fri Jul 24 2020 00:00:00 GMT-0500 (Central Daylight Time);Sat Jul 25 2020 00:00:00 GMT-0500 (Central Daylight Time);Sun Jul 26 2020 00:00:00 GMT-0500 (Central Daylight Time);Mon Jul 27 2020 00:00:00 GMT-0500 (Central Daylight Time);Tue Jul 28 2020 00:00:00 GMT-0500 (Central Daylight Time);Wed Jul 29 2020 00:00:00 GMT-0500 (Central Daylight Time);Thu Jul 30 2020 00:00:00 GMT-0500 (Central Daylight Time);Fri Jul 31 2020 00:00:00 GMT-0500 (Central Daylight Time)',29),(10,'2020-04-05 15:24:15.183000','19,20,21,22,23,24,25,30,31,35,39,40,41,42,43,44,45,47,49,51,52,55,72','diana.vazquez@uniprotec.net','didivago2@gmail.com','Ing. Diana Laura Vazquez Gomez','nota','Activo',1,3,NULL,28),(11,'2020-04-05 15:24:15.183000','1,2,3,4,5,6,7,8,11,12,13,15,16,17,18,19,20,21,22,23,24,25,26,27,28,30,31,32,34,35,36,39,40,45,46,48,49,55,67,68,69,70,71,72,73','ramon.noriega@uniprotec.net','norlopmx@gmail.com','Ing. Ramon Noriega Lopez','nota','Activo',1,3,NULL,30),(12,'2020-04-05 15:24:15.183000','1,2,3,4,5,7,8,11,12,14,18,21,32,36','fernando.mares@uniprotec.net','ferblumares@gmail.com','Ing. Fernando Mares Ortiz','nota','Activo',1,1,NULL,32),(13,'2020-04-05 15:24:15.183000','1,2,3,4,5,6,7,8,11,12,13,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,39,40,41,42,43,46,47,48,49,51,52,55,67,68,69,70,71,72,73','maximino.garcia@uniprotec.net','maximino.garcia.camilo@gmail.com','Ing. Maximino Garcia Camilo','nota','Activo',1,1,NULL,33),(14,'2020-08-18 21:58:39.286000','[1, 2, 3, 4, 5, 6, 7, 8, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 29, 30, 31, 32, 33, 34, 35, 36, 38, 41, 42, 43, 44, 47, 48, 49, 55, 67, 68, 69, 70, 71, 72, 73, 77, 79]','rafadel.cebada@uniprotec.net','cebada1984@gmail.com','Ing. Rafael Humberto Cebada Beltran','nota','Actualizado',1,5,NULL,34),(15,'2020-05-25 14:25:45.483000','47,56,57,58,59,60,61,62,63,64,65,66,67,68,69,70,71,72,73,74,75,76,77,78,79,80,82,85,92','olivier.sanchez@uniprotec.net','olivier.sanchez201184@gmail.com','Externo','nota','Activo',1,8,NULL,35);
/*!40000 ALTER TABLE `instructores` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mensajes`
--

DROP TABLE IF EXISTS `mensajes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mensajes` (
  `id_mensaje` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_at_instructor` datetime(6) DEFAULT NULL,
  `mensaje` varchar(300) NOT NULL,
  `user_create_instructor` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id_mensaje`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mensajes`
--

LOCK TABLES `mensajes` WRITE;
/*!40000 ALTER TABLE `mensajes` DISABLE KEYS */;
INSERT INTO `mensajes` VALUES (16,'2020-08-15 04:44:23.203000','ESPACIO DE COMUNICACION PARA LOS USUARIOS DE APLICACION',37),(17,'2020-08-21 23:22:33.598000','ESPACIO DE COMUNICACION PARA LOS USUARIOS DE APLICACION.',37);
/*!40000 ALTER TABLE `mensajes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `modulos`
--

DROP TABLE IF EXISTS `modulos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `modulos` (
  `id_modulo` bigint(20) NOT NULL AUTO_INCREMENT,
  `nombre_modulo` varchar(555) DEFAULT NULL,
  PRIMARY KEY (`id_modulo`)
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `modulos`
--

LOCK TABLES `modulos` WRITE;
/*!40000 ALTER TABLE `modulos` DISABLE KEYS */;
INSERT INTO `modulos` VALUES (1,'CLIENTES'),(2,'CURSOS'),(3,'INSTRUCTORES'),(4,'VENDEDORES'),(5,'USUARIOS'),(6,'LOGISTICA'),(7,'ADMINISTRACION');
/*!40000 ALTER TABLE `modulos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `modulos_submodulos`
--

DROP TABLE IF EXISTS `modulos_submodulos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `modulos_submodulos` (
  `modulo_id` bigint(20) NOT NULL,
  `submodule_id` bigint(20) NOT NULL,
  UNIQUE KEY `UK7dutm6xcy8j5m76431bn6k95b` (`modulo_id`,`submodule_id`),
  KEY `FKgcla8f9q9yen0p4vq0qxbo6ai` (`submodule_id`),
  CONSTRAINT `FK6cmvpokhsncphnrowbev1m8oa` FOREIGN KEY (`modulo_id`) REFERENCES `modulos` (`id_modulo`),
  CONSTRAINT `FKgcla8f9q9yen0p4vq0qxbo6ai` FOREIGN KEY (`submodule_id`) REFERENCES `submodules` (`id_submodulo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `modulos_submodulos`
--

LOCK TABLES `modulos_submodulos` WRITE;
/*!40000 ALTER TABLE `modulos_submodulos` DISABLE KEYS */;
INSERT INTO `modulos_submodulos` VALUES (1,1),(1,2),(2,4),(2,5),(3,7),(3,8),(4,10),(4,11),(5,13),(5,14);
/*!40000 ALTER TABLE `modulos_submodulos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `perfiles`
--

DROP TABLE IF EXISTS `perfiles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `perfiles` (
  `id_perfil` bigint(20) NOT NULL AUTO_INCREMENT,
  `nombre_perfil` varchar(120) DEFAULT NULL,
  PRIMARY KEY (`id_perfil`),
  UNIQUE KEY `UK_49lth7wyhon46ap83mojts899` (`nombre_perfil`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `perfiles`
--

LOCK TABLES `perfiles` WRITE;
/*!40000 ALTER TABLE `perfiles` DISABLE KEYS */;
INSERT INTO `perfiles` VALUES (4,'Administracion'),(5,'Direccion'),(2,'Instructor'),(3,'Operacion'),(1,'Vendedor');
/*!40000 ALTER TABLE `perfiles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `perfiles_acciones`
--

DROP TABLE IF EXISTS `perfiles_acciones`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `perfiles_acciones` (
  `perfil_id` bigint(20) NOT NULL,
  `accion_id` bigint(20) NOT NULL,
  UNIQUE KEY `UKd167w3ldrbyfn6sqlmukwdj0m` (`perfil_id`,`accion_id`),
  KEY `FKf99her4y8kd6ayhw9gqs6cogs` (`accion_id`),
  CONSTRAINT `FKf99her4y8kd6ayhw9gqs6cogs` FOREIGN KEY (`accion_id`) REFERENCES `acciones` (`id_accion`),
  CONSTRAINT `FKl3e9nht70p8d943broh5s0l0k` FOREIGN KEY (`perfil_id`) REFERENCES `perfiles` (`id_perfil`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `perfiles_acciones`
--

LOCK TABLES `perfiles_acciones` WRITE;
/*!40000 ALTER TABLE `perfiles_acciones` DISABLE KEYS */;
INSERT INTO `perfiles_acciones` VALUES (1,1),(3,1),(5,1),(1,2),(3,2),(4,2),(5,2),(3,3),(5,3),(2,4),(2,5);
/*!40000 ALTER TABLE `perfiles_acciones` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `perfiles_modulos`
--

DROP TABLE IF EXISTS `perfiles_modulos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `perfiles_modulos` (
  `perfil_id` bigint(20) NOT NULL,
  `modulo_id` bigint(20) NOT NULL,
  UNIQUE KEY `UKbj02a0rsw2vmjkp9kjaj5qw76` (`perfil_id`,`modulo_id`),
  KEY `FKgteeh43paxt5049x2k16ylqy5` (`modulo_id`),
  CONSTRAINT `FKapxhbk8n0v98kwstln90x0ml4` FOREIGN KEY (`perfil_id`) REFERENCES `perfiles` (`id_perfil`),
  CONSTRAINT `FKgteeh43paxt5049x2k16ylqy5` FOREIGN KEY (`modulo_id`) REFERENCES `modulos` (`id_modulo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `perfiles_modulos`
--

LOCK TABLES `perfiles_modulos` WRITE;
/*!40000 ALTER TABLE `perfiles_modulos` DISABLE KEYS */;
INSERT INTO `perfiles_modulos` VALUES (3,1),(5,1),(5,2),(3,3),(5,3),(5,4),(5,5),(1,6),(2,6),(4,6),(5,6);
/*!40000 ALTER TABLE `perfiles_modulos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `regiones`
--

DROP TABLE IF EXISTS `regiones`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `regiones` (
  `id_region` bigint(20) NOT NULL AUTO_INCREMENT,
  `nombre_region` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_region`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `regiones`
--

LOCK TABLES `regiones` WRITE;
/*!40000 ALTER TABLE `regiones` DISABLE KEYS */;
INSERT INTO `regiones` VALUES (1,'ESTADO DE SAN LUIS POTOSI'),(2,'ESTADO DE QUERETARO + CELAYA Y SAN JOSE ITURBIDE GTO + TEPEJI DEL RIO HIDALGO'),(3,'ESTADO DE GUANAJUATO MENOS CELAYA Y SAN JOSE ITURBIDE + LAGOS DE MORENO + EDO DE AGUASCALIENTES'),(4,'ESTADO DE JALISCO MENOS LAGOS DE MORENO'),(5,'ESTADO DE NUEVO LEON + ESTADO DE COAHUILA'),(6,'CIUDAD DE MEXICO + EDO DE MEXICO'),(7,'ESTADO DE PUEBLA + ESTADO DE TLAXCALA + ESTADO DE MORELOS'),(8,'CUALQUIER OTRA UBICACIÓN');
/*!40000 ALTER TABLE `regiones` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `roles` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(120) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_ofx66keruapi6vyqpv6f2or37` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (6,'Administracion'),(7,'Direccion'),(4,'Instructor'),(5,'Operador'),(2,'ROLE_ADMIN'),(1,'ROLE_USER'),(3,'Vendedor');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `submodules`
--

DROP TABLE IF EXISTS `submodules`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `submodules` (
  `id_submodulo` bigint(20) NOT NULL AUTO_INCREMENT,
  `id_modulo` varchar(255) DEFAULT NULL,
  `nombre_submodulo` varchar(255) DEFAULT NULL,
  `referencia_submodulo` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_submodulo`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `submodules`
--

LOCK TABLES `submodules` WRITE;
/*!40000 ALTER TABLE `submodules` DISABLE KEYS */;
INSERT INTO `submodules` VALUES (1,'1','Alta Cliente','ACliente'),(2,'1','Edicion Cliente','BCliente'),(4,'2','Alta Curso','ACurso'),(5,'2','Edicion Curso','BCurso'),(7,'3','Alta Instructor','AInstructor'),(8,'3','Edicion Instructor','BInstructor'),(10,'4','Alta Vendedor','AVendedor'),(11,'4','Edicion Vendedor','BVendedor'),(13,'5','Alta Usuario','AUsuario'),(14,'5','Edicion Usuario','BUsuario');
/*!40000 ALTER TABLE `submodules` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuarios` (
  `id_usuario` bigint(20) NOT NULL AUTO_INCREMENT,
  `nombre_usuario` varchar(255) NOT NULL,
  `username_usuario` varchar(300) NOT NULL,
  `password_usuario` varchar(300) NOT NULL,
  `perfil_usuario` varchar(255) DEFAULT NULL,
  `email_usuario` varchar(255) NOT NULL,
  `nota_usuario` varchar(255) DEFAULT NULL,
  `status_usuario` varchar(255) NOT NULL,
  `user_create_usuario` bigint(20) NOT NULL,
  `create_at_usuario` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id_usuario`)
) ENGINE=InnoDB AUTO_INCREMENT=72 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios`
--

LOCK TABLES `usuarios` WRITE;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` VALUES (3,'Lidia Arellano Urbina','v.lidia.arellano','$2a$10$C3Uln5uqnzx/GswADURJGOIdBqYrly9731fnwKDaUdBkt/M3qvtLq','Vendedor','lidia.arellano@uniprotec.net','A','Activo',1,'2020-04-06 15:24:15.183000'),(4,'Karla Ivonne Hernandez Mena','v.karla.hernandez','$2a$10$C3Uln5uqnzx/GswADURJGOIdBqYrly9731fnwKDaUdBkt/M3qvtLq','Vendedor','atencionaclientes1@uniprotec.net','A','Activo',1,'2020-04-06 15:24:15.183000'),(5,'Isabel Gabriela Perez Chavez','v.isabel.perez','$2a$10$C3Uln5uqnzx/GswADURJGOIdBqYrly9731fnwKDaUdBkt/M3qvtLq','Vendedor','gabriela.perez@uniprotec.net','A','Activo',1,'2020-04-06 15:24:15.183000'),(6,'Carolina Viridiana Muñiz Montoya','v.carolina.muñiz','$2a$10$C3Uln5uqnzx/GswADURJGOIdBqYrly9731fnwKDaUdBkt/M3qvtLq','Vendedor','atencionaclientes4@uniprotec.net','A','Activo',1,'2020-04-06 15:24:15.183000'),(7,'Ma del Rocio Muñoz Vazquez','v.rocio.muñoz','$2a$10$C3Uln5uqnzx/GswADURJGOIdBqYrly9731fnwKDaUdBkt/M3qvtLq','Vendedor','rocio.munoz@uniprotec.net','A','Activo',1,'2020-04-06 15:24:15.183000'),(8,'Oscar Alejandro Vazquez','v.oscar.vazquez','$2a$10$C3Uln5uqnzx/GswADURJGOIdBqYrly9731fnwKDaUdBkt/M3qvtLq','Vendedor','oscar.vazquez@uniprotec.net','A','Activo',1,'2020-04-06 15:24:15.183000'),(9,'Veronica Tejeda Gutierrez','v.veronica.tejeda','$2a$10$C3Uln5uqnzx/GswADURJGOIdBqYrly9731fnwKDaUdBkt/M3qvtLq','Vendedor','atencionaclientes6@uniprotec.net','A','Activo',1,'2020-04-06 15:24:15.183000'),(10,'Sergio Fabian Zuñiga Vazquez','v.sergio.zuñiga','$2a$10$C3Uln5uqnzx/GswADURJGOIdBqYrly9731fnwKDaUdBkt/M3qvtLq','Vendedor','sergio.zuñiga@uniprotec.net','A','Activo',1,'2020-04-06 15:24:15.183000'),(11,'Maria Guadalupe Araujo Gonzalez','v.maria.araujo','$2a$10$C3Uln5uqnzx/GswADURJGOIdBqYrly9731fnwKDaUdBkt/M3qvtLq','Vendedor','guadalupe.araujo@uniprotec.net','A','Activo',1,'2020-04-06 15:24:15.183000'),(12,'Natalia Denisse Estrada Granados','v.natalia.estrada','$2a$10$C3Uln5uqnzx/GswADURJGOIdBqYrly9731fnwKDaUdBkt/M3qvtLq','Vendedor','natalia.estrada@uniprotec.net','A','Activo',1,'2020-04-06 15:24:15.183000'),(13,'Melisa Jazmin Anguiano Alvarez','v.melisa.anguiano','$2a$10$C3Uln5uqnzx/GswADURJGOIdBqYrly9731fnwKDaUdBkt/M3qvtLq','Vendedor','melisa.anguiano@uniprotec.net','A','Activo',1,'2020-04-06 15:24:15.183000'),(14,'Julisa Marcial Santos','v.julisa.marcial','$2a$10$C3Uln5uqnzx/GswADURJGOIdBqYrly9731fnwKDaUdBkt/M3qvtLq','Vendedor','julisa.marcial@uniprotec.net','A','Activo',1,'2020-04-06 15:24:15.183000'),(15,'Ricardo Alonso Perez Palacios','v.ricardo.perez','$2a$10$C3Uln5uqnzx/GswADURJGOIdBqYrly9731fnwKDaUdBkt/M3qvtLq','Vendedor','ricardo.perez@uniprotec.net','A','Activo',1,'2020-04-06 15:24:15.183000'),(16,'Rodolfo Valdez Sanchez','v.rodolfo.valdez','$2a$10$C3Uln5uqnzx/GswADURJGOIdBqYrly9731fnwKDaUdBkt/M3qvtLq','Vendedor','rodolfo.valdez@uniprotec.net','A','Activo',1,'2020-04-06 15:24:15.183000'),(17,'Janeth Verenice Valdez Sanchez','v.janeth.valdez','$2a$10$C3Uln5uqnzx/GswADURJGOIdBqYrly9731fnwKDaUdBkt/M3qvtLq','Vendedor','verenice.valdez@uniprotec.net','A','Activo',1,'2020-04-06 15:24:15.183000'),(18,'Gabriel Limon Rodriguez','v.gabriel.limon','$2a$10$C3Uln5uqnzx/GswADURJGOIdBqYrly9731fnwKDaUdBkt/M3qvtLq','Vendedor','gabriel.limon@uniprotec.net','A','Activo',1,'2020-04-06 15:24:15.183000'),(19,'Olivier Sanchez','v.olivier.sanchez','$2a$10$C3Uln5uqnzx/GswADURJGOIdBqYrly9731fnwKDaUdBkt/M3qvtLq','Vendedor','olivier.sanchez@uniprotec.net','A','Activo',1,'2020-04-06 15:24:15.183000'),(20,'Jesus Mares','v.jesus.mares','$2a$10$C3Uln5uqnzx/GswADURJGOIdBqYrly9731fnwKDaUdBkt/M3qvtLq','Vendedor','jesus.mares@uniprotec.net','A','Activo',1,'2020-04-06 15:24:15.183000'),(21,'Ing. Felipe Javier Castillo Damian','i.felipe.castillo','$2a$10$C3Uln5uqnzx/GswADURJGOIdBqYrly9731fnwKDaUdBkt/M3qvtLq','Instructor','javier.castillo@uniprotec.net','A','Activo',1,'2020-04-06 15:24:15.183000'),(22,'Ing. Cesar Octavio Vazquez Galicia','i.cesar.vazquez','$2a$10$C3Uln5uqnzx/GswADURJGOIdBqYrly9731fnwKDaUdBkt/M3qvtLq','Instructor','cesar.vazquez@uniprotec.net','A','Activo',1,'2020-04-06 15:24:15.183000'),(23,'Ing. Juan Antonio Gomez Diaz','i.juan.gomez','$2a$10$C3Uln5uqnzx/GswADURJGOIdBqYrly9731fnwKDaUdBkt/M3qvtLq','Instructor','antonio.gomez@uniprotec.net','A','Activo',1,'2020-04-06 15:24:15.183000'),(24,'Ing. Carlos Alberto Dominguez Mejia','i.carlos.dominguez','$2a$10$C3Uln5uqnzx/GswADURJGOIdBqYrly9731fnwKDaUdBkt/M3qvtLq','Instructor','carlos.dominguez@uniprotec.net','A','Activo',1,'2020-04-06 15:24:15.183000'),(25,'Ing. Juan Alberto Zuñiga Vazquez','i.juan.zuñiga','$2a$10$C3Uln5uqnzx/GswADURJGOIdBqYrly9731fnwKDaUdBkt/M3qvtLq','Instructor','alberto.zuniga@uniprotec.net','A','Activo',1,'2020-04-06 15:24:15.183000'),(26,'Lic. Claudia Alejandra Campos Segura','i.claudia.campos','$2a$10$C3Uln5uqnzx/GswADURJGOIdBqYrly9731fnwKDaUdBkt/M3qvtLq','Instructor','alejandra.campos@uniprotec.net','A','Activo',1,'2020-04-06 15:24:15.183000'),(27,'Lic. Mayra Andrea Padron Aguilar','i.mayra.padron','$2a$10$C3Uln5uqnzx/GswADURJGOIdBqYrly9731fnwKDaUdBkt/M3qvtLq','Instructor','mayra.padron@uniprotec.net','A','Activo',1,'2020-04-06 15:24:15.183000'),(28,'Ing. Diana Laura Vazquez Gomez','i.diana.vazquez','$2a$10$C3Uln5uqnzx/GswADURJGOIdBqYrly9731fnwKDaUdBkt/M3qvtLq','Instructor','diana.vazquez@uniprotec.net','A','Activo',1,'2020-04-06 15:24:15.183000'),(29,'Ing. Ricardo Geciel Velazquez Medina','i.ricardo.velazquez','$2a$10$C3Uln5uqnzx/GswADURJGOIdBqYrly9731fnwKDaUdBkt/M3qvtLq','Instructor','ricardo.velazquez@uniprotec.net','A','Activo',1,'2020-04-06 15:24:15.183000'),(30,'Ing. Ramon Noriega Lopez','i.ramon.noriega','$2a$10$C3Uln5uqnzx/GswADURJGOIdBqYrly9731fnwKDaUdBkt/M3qvtLq','Instructor','ramon.noriega@uniprotec.net','A','Activo',1,'2020-04-06 15:24:15.183000'),(31,'Ing. Gerardo Federico Moreno Specia','i.gerardo.moreno','$2a$10$C3Uln5uqnzx/GswADURJGOIdBqYrly9731fnwKDaUdBkt/M3qvtLq','Instructor','gerardo.moreno@uniprotec.net','A','Activo',1,'2020-04-06 15:24:15.183000'),(32,'Ing. Fernando Mares Ortiz','i.fernando.mares','$2a$10$C3Uln5uqnzx/GswADURJGOIdBqYrly9731fnwKDaUdBkt/M3qvtLq','Instructor','fernando.mares@uniprotec.net','A','Actualizado',1,'2020-08-19 01:45:54.941000'),(33,'Ing. Maximino Garcia Camilo','i.maximino.garcia','$2a$10$C3Uln5uqnzx/GswADURJGOIdBqYrly9731fnwKDaUdBkt/M3qvtLq','Instructor','maximino.garcia@uniprotec.net','A','Activo',1,'2020-04-06 15:24:15.183000'),(34,'Ing. Rafael Humberto Cebada Beltran','i.rafael.cebada','$2a$10$C3Uln5uqnzx/GswADURJGOIdBqYrly9731fnwKDaUdBkt/M3qvtLq','Instructor','rafadel.cebada@uniprotec.net','A','Activo',1,'2020-04-06 15:24:15.183000'),(35,'Angeles Andrea Vargas Cortes','o.angeles.vargas','$2a$10$C3Uln5uqnzx/GswADURJGOIdBqYrly9731fnwKDaUdBkt/M3qvtLq','Operacion','andrea.vargas@uniprotec.net','A','Activo',1,'2020-04-06 15:24:15.183000'),(36,'Martha  Maria Aguilera Aranda ','a.martha.aguilera','$2a$10$C3Uln5uqnzx/GswADURJGOIdBqYrly9731fnwKDaUdBkt/M3qvtLq','Administracion','martha.aguilera@uniprotec.net','A','Activo',1,'2020-04-06 15:24:15.183000'),(37,'Olivier Sanchez','d.olivier.sanchez','$2a$10$CEd2bSs5lhZXOK6l6GAb6OajSAnuFYEJWb4h7q5PmAmRQa1rUNNcO','Direccion','olivier.sanchez@uniprotec.net','A','Actualizado',1,'2020-07-30 10:34:18.176000'),(38,'Jesus Mares Vazquez','d.jesus.mares','$2a$10$C3Uln5uqnzx/GswADURJGOIdBqYrly9731fnwKDaUdBkt/M3qvtLq','Direccion','jesus.mares@uniprotec.net','A','Activo',1,'2020-04-06 15:24:15.183000'),(52,'Mario Alberto Perez Martinez','a.mario.perez','$2a$10$C3Uln5uqnzx/GswADURJGOIdBqYrly9731fnwKDaUdBkt/M3qvtLq','Administracion','mario.perez@uniprotec.net','','Activo',1,'2020-07-19 19:39:34.062000'),(54,'hugo rivas','d.hrivas','$2a$10$C3Uln5uqnzx/GswADURJGOIdBqYrly9731fnwKDaUdBkt/M3qvtLq','Direccion','hrivas@uniprotec.net','test','Activo',1,'2020-08-21 23:41:00.085000');
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuarios_modulos`
--

DROP TABLE IF EXISTS `usuarios_modulos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuarios_modulos` (
  `usuario_id` bigint(20) NOT NULL,
  `modulo_id` bigint(20) NOT NULL,
  UNIQUE KEY `UKqnyl9k6xhxltoqaywnwxc2iej` (`usuario_id`,`modulo_id`),
  KEY `FKclb3sboy3lqpbxfc41u4v9sm0` (`modulo_id`),
  CONSTRAINT `FKclb3sboy3lqpbxfc41u4v9sm0` FOREIGN KEY (`modulo_id`) REFERENCES `modulos` (`id_modulo`),
  CONSTRAINT `FKdqxfd4w3c8dlrug86grwrt6lr` FOREIGN KEY (`usuario_id`) REFERENCES `usuarios` (`id_usuario`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios_modulos`
--

LOCK TABLES `usuarios_modulos` WRITE;
/*!40000 ALTER TABLE `usuarios_modulos` DISABLE KEYS */;
/*!40000 ALTER TABLE `usuarios_modulos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuarios_roles`
--

DROP TABLE IF EXISTS `usuarios_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuarios_roles` (
  `usuario_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  UNIQUE KEY `UKqjaspm7473pnu9y4jxhrds8r2` (`usuario_id`,`role_id`),
  KEY `FKihom0uklpkfpffipxpoyf7b74` (`role_id`),
  CONSTRAINT `FKihom0uklpkfpffipxpoyf7b74` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`),
  CONSTRAINT `FKqcxu02bqipxpr7cjyj9dmhwec` FOREIGN KEY (`usuario_id`) REFERENCES `usuarios` (`id_usuario`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios_roles`
--

LOCK TABLES `usuarios_roles` WRITE;
/*!40000 ALTER TABLE `usuarios_roles` DISABLE KEYS */;
INSERT INTO `usuarios_roles` VALUES (3,3),(4,3),(5,3),(6,3),(7,3),(8,3),(9,3),(10,3),(11,3),(12,3),(13,3),(14,3),(15,3),(16,3),(17,3),(18,3),(19,3),(20,3),(21,4),(22,4),(23,4),(24,4),(25,4),(26,4),(27,4),(28,4),(29,4),(30,4),(31,4),(32,4),(33,4),(34,4),(54,4),(35,5),(36,6),(52,6),(37,7);
/*!40000 ALTER TABLE `usuarios_roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuariosa`
--

DROP TABLE IF EXISTS `usuariosa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuariosa` (
  `id_usuario` bigint(20) NOT NULL AUTO_INCREMENT,
  `nombre_usuario` varchar(255) NOT NULL,
  `email_usuario` varchar(255) NOT NULL,
  `perfil_usuario` varchar(255) NOT NULL,
  `username_usuario` varchar(300) NOT NULL,
  `password_usuario` varchar(300) NOT NULL,
  `nota_usuario` varchar(255) DEFAULT NULL,
  `create_at_usuario` datetime(6) NOT NULL,
  `status_usuario` varchar(255) NOT NULL,
  `user_create_usuario` bigint(20) NOT NULL,
  PRIMARY KEY (`id_usuario`),
  UNIQUE KEY `UK_o1ha10ovxfb8g7h3yo70n6i7m` (`username_usuario`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuariosa`
--

LOCK TABLES `usuariosa` WRITE;
/*!40000 ALTER TABLE `usuariosa` DISABLE KEYS */;
INSERT INTO `usuariosa` VALUES (1,'Lidia Arellano Urbina','lidia.arellano@uniprotec.net','Vendedor','v.lidia.arellano','$2a$10$5BzyZU1116kXoe96eXhQQepH2v5zD9o9snsF7TNX7BKxHy6ZwoEhW','A','2020-04-06 15:24:15.183000','Activo',1),(2,'Karla ivonne Hernandez Mena','atencionaclientes1@uniprotec.net','Vendedor','v.karla.hernandez','$2a$10$5BzyZU1116kXoe96eXhQQepH2v5zD9o9snsF7TNX7BKxHy6ZwoEhW','A','2020-04-06 15:24:15.183000','Activo',1),(3,'Isabel Gabriela Perez Chavez','gabriela.perez@uniprotec.net','Vendedor','v.isabel.perez','$2a$10$5BzyZU1116kXoe96eXhQQepH2v5zD9o9snsF7TNX7BKxHy6ZwoEhW','A','2020-04-06 15:24:15.183000','Activo',1),(4,'Carolina Viridiana Muñiz Montoya','atencionaclientes4@uniprotec.net','Vendedor','v.carolina.muñiz','$2a$10$5BzyZU1116kXoe96eXhQQepH2v5zD9o9snsF7TNX7BKxHy6ZwoEhW','A','2020-04-06 15:24:15.183000','Activo',1),(5,'Ma del Rocio Muñoz Vazquez','rocio.munoz@uniprotec.net','Vendedor','v.rocio.muñoz','$2a$10$5BzyZU1116kXoe96eXhQQepH2v5zD9o9snsF7TNX7BKxHy6ZwoEhW','A','2020-04-06 15:24:15.183000','Activo',1),(6,'Oscar Alejandro Vazquez','oscar.vazquez@uniprotec.net','Vendedor','v.oscar.vazquez','$2a$10$5BzyZU1116kXoe96eXhQQepH2v5zD9o9snsF7TNX7BKxHy6ZwoEhW','A','2020-04-06 15:24:15.183000','Activo',1),(7,'Veronica Tejeda Gutierrez','atencionaclientes6@uniprotec.net','Vendedor','v.veronica.tejeda','$2a$10$5BzyZU1116kXoe96eXhQQepH2v5zD9o9snsF7TNX7BKxHy6ZwoEhW','A','2020-04-06 15:24:15.183000','Activo',1),(8,'Sergio Fabian Zuñiga Vazquez','sergio.zuñiga@uniprotec.net','Vendedor','v.sergio.zuñiga','$2a$10$5BzyZU1116kXoe96eXhQQepH2v5zD9o9snsF7TNX7BKxHy6ZwoEhW','A','2020-04-06 15:24:15.183000','Activo',1),(9,'Maria Guadalupe Araujo Gonzalez','guadalupe.araujo@uniprotec.net','Vendedor','v.maria.araujo','$2a$10$5BzyZU1116kXoe96eXhQQepH2v5zD9o9snsF7TNX7BKxHy6ZwoEhW','A','2020-04-06 15:24:15.183000','Activo',1),(10,'Natalia Denisse Estrada Granados','natalia.estrada@uniprotec.net','Vendedor','v.natalia.estrada','$2a$10$5BzyZU1116kXoe96eXhQQepH2v5zD9o9snsF7TNX7BKxHy6ZwoEhW','A','2020-04-06 15:24:15.183000','Activo',1),(11,'Melisa Jazmin Anguiano Alvarez','melisa.anguiano@uniprotec.net','Vendedor','v.melisa.anguiano','$2a$10$5BzyZU1116kXoe96eXhQQepH2v5zD9o9snsF7TNX7BKxHy6ZwoEhW','A','2020-04-06 15:24:15.183000','Activo',1),(12,'Julisa Marcial Santos','julisa.marcial@uniprotec.net','Vendedor','v.julisa.marcial','$2a$10$5BzyZU1116kXoe96eXhQQepH2v5zD9o9snsF7TNX7BKxHy6ZwoEhW','A','2020-04-06 15:24:15.183000','Activo',1),(13,'Ricardo Alonso Perez Palacios','ricardo.perez@uniprotec.net','Vendedor','v.ricardo.perez','$2a$10$5BzyZU1116kXoe96eXhQQepH2v5zD9o9snsF7TNX7BKxHy6ZwoEhW','A','2020-04-06 15:24:15.183000','Activo',1),(14,'Rodolfo Valdez Sanchez','rodolfo.valdez@uniprotec.net','Vendedor','v.rodolfo.valdez','$2a$10$5BzyZU1116kXoe96eXhQQepH2v5zD9o9snsF7TNX7BKxHy6ZwoEhW','A','2020-04-06 15:24:15.183000','Activo',1),(15,'Janeth Verenice Valdez Sanchez','verenice.valdez@uniprotec.net','Vendedor','v.janeth.valdez','$2a$10$5BzyZU1116kXoe96eXhQQepH2v5zD9o9snsF7TNX7BKxHy6ZwoEhW','A','2020-04-06 15:24:15.183000','Activo',1),(16,'Gabriel Limon Rodriguez','gabriel.limon@uniprotec.net','Vendedor','v.gabriel.limon','$2a$10$5BzyZU1116kXoe96eXhQQepH2v5zD9o9snsF7TNX7BKxHy6ZwoEhW','A','2020-04-06 15:24:15.183000','Activo',1),(17,'Olivier Sanchez','olivier.sanchez@uniprotec.net','Vendedor','v.olivier.sanchez','$2a$10$5BzyZU1116kXoe96eXhQQepH2v5zD9o9snsF7TNX7BKxHy6ZwoEhW','A','2020-04-06 15:24:15.183000','Activo',1),(18,'Jesus Mares','jesus.mares@uniprotec.net','Vendedor','v.jesus.mares','$2a$10$5BzyZU1116kXoe96eXhQQepH2v5zD9o9snsF7TNX7BKxHy6ZwoEhW','A','2020-04-06 15:24:15.183000','Activo',1),(19,'Ing. Felipe Javier Castillo Damian','javier.castillo@uniprotec.net','Instructor','i.felipe.castillo','$2a$10$5BzyZU1116kXoe96eXhQQepH2v5zD9o9snsF7TNX7BKxHy6ZwoEhW','A','2020-04-06 15:24:15.183000','Activo',1),(20,'Ing. Cesar Octavio Vazquez Galicia','cesar.vazquez@uniprotec.net','Instructor','i.cesar.vazquez','$2a$10$5BzyZU1116kXoe96eXhQQepH2v5zD9o9snsF7TNX7BKxHy6ZwoEhW','A','2020-04-06 15:24:15.183000','Activo',1),(21,'Ing. Juan Antonio Gomez Diaz','antonio.gomez@uniprotec.net','Instructor','i.juan.gomez','$2a$10$5BzyZU1116kXoe96eXhQQepH2v5zD9o9snsF7TNX7BKxHy6ZwoEhW','A','2020-04-06 15:24:15.183000','Activo',1),(22,'Ing. Carlos Alberto Dominguez Mejia','carlos.dominguez@uniprotec.net','Instructor','i.carlos.dominguez','$2a$10$5BzyZU1116kXoe96eXhQQepH2v5zD9o9snsF7TNX7BKxHy6ZwoEhW','A','2020-04-06 15:24:15.183000','Activo',1),(23,'Ing. Juan Alberto Zuñiga Vazquez','alberto.zuniga@uniprotec.net','Instructor','i.juan.zuñiga','$2a$10$5BzyZU1116kXoe96eXhQQepH2v5zD9o9snsF7TNX7BKxHy6ZwoEhW','A','2020-04-06 15:24:15.183000','Activo',1),(24,'Lic. Claudia Alejandra Campos Segura','alejandra.campos@uniprotec.net','Instructor','i.claudia.campos','$2a$10$5BzyZU1116kXoe96eXhQQepH2v5zD9o9snsF7TNX7BKxHy6ZwoEhW','A','2020-04-06 15:24:15.183000','Activo',1),(25,'Lic. Mayra Andrea Padron Aguilar','mayra.padron@uniprotec.net','Instructor','i.mayra.padron','$2a$10$5BzyZU1116kXoe96eXhQQepH2v5zD9o9snsF7TNX7BKxHy6ZwoEhW','A','2020-04-06 15:24:15.183000','Activo',1),(26,'Ing. Diana Laura Vazquez Gomez','diana.vazquez@uniprotec.net','Instructor','i.diana.vazquez','$2a$10$5BzyZU1116kXoe96eXhQQepH2v5zD9o9snsF7TNX7BKxHy6ZwoEhW','A','2020-04-06 15:24:15.183000','Activo',1),(27,'Ing. Ricardo Geciel Velazquez Medina','ricardo.velazquez@uniprotec.net','Instructor','i.ricardo.velazquez','$2a$10$5BzyZU1116kXoe96eXhQQepH2v5zD9o9snsF7TNX7BKxHy6ZwoEhW','A','2020-04-06 15:24:15.183000','Activo',1),(28,'Ing. Ramon Noriega Lopez','ramon.noriega@uniprotec.net','Instructor','i.ramon.noriega','$2a$10$5BzyZU1116kXoe96eXhQQepH2v5zD9o9snsF7TNX7BKxHy6ZwoEhW','A','2020-04-06 15:24:15.183000','Activo',1),(29,'Ing. Gerardo Federico Moreno Specia','gerardo.moreno@uniprotec.net','Instructor','i.gerardo.moreno','$2a$10$5BzyZU1116kXoe96eXhQQepH2v5zD9o9snsF7TNX7BKxHy6ZwoEhW','A','2020-04-06 15:24:15.183000','Activo',1),(30,'Ing. Fernando Marez Ortiz','fernando.mares@uniprotec.net','Instructor','i.fernando.marez','$2a$10$5BzyZU1116kXoe96eXhQQepH2v5zD9o9snsF7TNX7BKxHy6ZwoEhW','A','2020-04-06 15:24:15.183000','Activo',1),(31,'Ing. Maximino Garcia Camilo','maximino.garcia@uniprotec.net','Instructor','i.maximino.garcia','$2a$10$5BzyZU1116kXoe96eXhQQepH2v5zD9o9snsF7TNX7BKxHy6ZwoEhW','A','2020-04-06 15:24:15.183000','Activo',1),(32,'Ing. Rafael Humberto Cebada Beltran','rafadel.cebada@uniprotec.net','Instructor','i.rafael.cebada','$2a$10$5BzyZU1116kXoe96eXhQQepH2v5zD9o9snsF7TNX7BKxHy6ZwoEhW','A','2020-04-06 15:24:15.183000','Activo',1),(33,'Angeles Andrea Vargas Cortes','andrea.vargas@uniprotec.net','Operacion','o.angeles.vargas','$2a$10$5BzyZU1116kXoe96eXhQQepH2v5zD9o9snsF7TNX7BKxHy6ZwoEhW','A','2020-04-06 15:24:15.183000','Activo',1),(34,'Martha  Maria Aguilera Aranda ','martha.aguilera@uniprotec.net','Administracion','a.martha.aguilera','$2a$10$5BzyZU1116kXoe96eXhQQepH2v5zD9o9snsF7TNX7BKxHy6ZwoEhW','A','2020-04-06 15:24:15.183000','Activo',1),(35,'Olivier Sanchez','olivier.sanchez@uniprotec.net','Direccion','d.olivier.sanchez','$2a$10$5BzyZU1116kXoe96eXhQQepH2v5zD9o9snsF7TNX7BKxHy6ZwoEhW','A','2020-04-06 15:24:15.183000','Activo',1),(36,'Jesus Mares Vazquez','jesus.mares@uniprotec.net','Direccion','d.jesus.mares','$2a$10$5BzyZU1116kXoe96eXhQQepH2v5zD9o9snsF7TNX7BKxHy6ZwoEhW','A','2020-04-06 15:24:15.183000','Activo',1);
/*!40000 ALTER TABLE `usuariosa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vendedores`
--

DROP TABLE IF EXISTS `vendedores`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `vendedores` (
  `id_vendedor` bigint(20) NOT NULL AUTO_INCREMENT,
  `nombre_vendedor` varchar(300) NOT NULL,
  `email_vendedor` varchar(255) NOT NULL,
  `email_gmail_vendedor` varchar(255) NOT NULL,
  `nota_vendedor` varchar(255) NOT NULL,
  `create_at_vendedor` datetime(6) NOT NULL,
  `status_vendedor` varchar(255) NOT NULL,
  `user_create_vendedor` bigint(20) NOT NULL,
  `usuario_vendedor_id_usuario` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id_vendedor`),
  UNIQUE KEY `nombre_vendedor_UNIQUE` (`nombre_vendedor`),
  UNIQUE KEY `email_vendedor_UNIQUE` (`email_vendedor`),
  UNIQUE KEY `email_gmail_vendedor_UNIQUE` (`email_gmail_vendedor`),
  KEY `FKm2wqj7f8r3kecl92xhw8v6sug` (`usuario_vendedor_id_usuario`),
  CONSTRAINT `FKm2wqj7f8r3kecl92xhw8v6sug` FOREIGN KEY (`usuario_vendedor_id_usuario`) REFERENCES `usuarios` (`id_usuario`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vendedores`
--

LOCK TABLES `vendedores` WRITE;
/*!40000 ALTER TABLE `vendedores` DISABLE KEYS */;
INSERT INTO `vendedores` VALUES (2,'Lidia Arellano Urbina','lidia.arellano@uniprotec.net','lidia.uniprotec@gmail.com','nota','2020-04-05 15:24:15.183000','Activo',1,3),(3,'Karla Ivonne Hernandez Mena','atencionaclientes1@uniprotec.net','karla12hernandezmena@gmail.com','nota','2020-06-20 06:29:22.834000','Activo',1,4),(4,'Isabel Gabriela Perez Chavez','gabriela.perez@uniprotec.net','gabriela.uniprotec@gmail.com','nota','2020-04-05 15:24:15.183000','Activo',1,5),(5,'Carolina Viridiana Muñiz Montoya','atencionaclientes4@uniprotec.net','viridiana.muniz@gmail.com','nota','2020-04-05 15:24:15.183000','Activo',1,6),(6,'Ma del Rocio Muñoz Vazquez','rocio.munoz@uniprotec.net','muñoz.rocio09oct@gmail.com','nota','2020-04-05 15:24:15.183000','Activo',1,7),(7,'Oscar Alejandro Vazquez','oscar.vazquez@uniprotec.net','oscaruniprotec@gmail.com','nota','2020-04-05 15:24:15.183000','Activo',1,8),(8,'Veronica Tejeda Gutierrez','atencionaclientes6@uniprotec.net','kasamber.1311@gmail.com','nota','2020-04-05 15:24:15.183000','Activo',1,9),(9,'Sergio Fabian Zuñiga Vazquez','sergio.zuñiga@uniprotec.net','sergiouniprotec@gmail.com','nota','2020-04-05 15:24:15.183000','Activo',1,10),(10,'Maria Guadalupe Araujo Gonzalez','guadalupe.araujo@uniprotec.net','lupita.argon02@gmail.com','nota','2020-04-05 15:24:15.183000','Activo',1,11),(11,'Natalia Denisse Estrada Granados','natalia.estrada@uniprotec.net','natalia.estrada@gmail.com','nota','2020-04-05 15:24:15.183000','Activo',1,12),(12,'Melisa Jazmin Anguiano Alvarez','melisa.anguiano@uniprotec.net','melisa.anguiano.a@gmail.com','nota','2020-04-05 15:24:15.183000','Activo',1,13),(13,'Julisa Marcial Santos','julisa.marcial@uniprotec.net','marcialjulisa78@gmail.com','nota','2020-04-05 15:24:15.183000','Activo',1,14),(14,'Ricardo Alonso Perez Palacios','ricardo.perez@uniprotec.net','ricardoalonsoperez49@gmail.com','nota','2020-04-05 15:24:15.183000','Activo',1,15),(15,'Rodolfo Valdez Sanchez','rodolfo.valdez@uniprotec.net','rodolfovaldezsanchez3@gmail.com','nota','2020-04-05 15:24:15.183000','Activo',1,16),(16,'Janeth Verenice Valdez Sanchez','verenice.valdez@uniprotec.net','verenice.valdez78@gmail.com','nota','2020-04-05 15:24:15.183000','Activo',1,17),(17,'Gabriel Limon Rodriguez','gabriel.limon@uniprotec.net','gabriel.uniprotec@gmail.com','nota','2020-04-05 15:24:15.183000','Activo',1,18),(19,'Olivier Sanchez','olivier.sanchez@uniprotec.net','olivier.sanchez201184@gmail.com','','2020-06-17 21:11:00.780000','Activo',1,19),(20,'Jesus Mares Vazquez','jesus.mares@gmail.com','jesusmaresv@gmail.com','','2020-06-17 21:11:54.578000','Activo',1,20);
/*!40000 ALTER TABLE `vendedores` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `zonabase`
--

DROP TABLE IF EXISTS `zonabase`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `zonabase` (
  `id_zonabase` int(11) NOT NULL,
  `data_zonabase` json DEFAULT NULL,
  PRIMARY KEY (`id_zonabase`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `zonabase`
--

LOCK TABLES `zonabase` WRITE;
/*!40000 ALTER TABLE `zonabase` DISABLE KEYS */;
INSERT INTO `zonabase` VALUES (1,'{\"11\": true, \"12\": true, \"13\": true, \"14\": true, \"15\": true, \"16\": true, \"17\": false, \"18\": false, \"21\": true, \"22\": true, \"23\": true, \"24\": true, \"25\": false, \"26\": true, \"27\": false, \"28\": false, \"31\": true, \"32\": true, \"33\": true, \"34\": true, \"35\": false, \"36\": true, \"37\": false, \"38\": false, \"41\": true, \"42\": true, \"43\": true, \"44\": true, \"45\": false, \"46\": false, \"47\": false, \"48\": false, \"51\": true, \"52\": false, \"53\": false, \"54\": false, \"55\": true, \"56\": false, \"57\": false, \"58\": false, \"61\": true, \"62\": true, \"63\": true, \"64\": false, \"65\": false, \"66\": true, \"67\": true, \"68\": false, \"71\": false, \"72\": true, \"73\": false, \"74\": false, \"75\": false, \"76\": true, \"77\": true, \"78\": false, \"81\": false, \"82\": false, \"83\": false, \"84\": false, \"85\": false, \"86\": false, \"87\": false, \"88\": false}');
/*!40000 ALTER TABLE `zonabase` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-08-22  5:10:40
