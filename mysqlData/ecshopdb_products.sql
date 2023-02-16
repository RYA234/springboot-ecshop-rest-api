-- MySQL dump 10.13  Distrib 8.0.29, for Win64 (x86_64)
--
-- Host: localhost    Database: ecshopdb
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
-- Table structure for table `products`
--

DROP TABLE IF EXISTS `products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `products` (
  `id` int NOT NULL,
  `category_id` int NOT NULL,
  `description` varchar(4096) NOT NULL,
  `discount_percent` float DEFAULT NULL,
  `image` varchar(255) NOT NULL,
  `in_stock` bit(1) DEFAULT NULL,
  `name` varchar(128) NOT NULL,
  `price` float NOT NULL,
  `tax_rate` float DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_o61fmio5yukmmiqgnxf8pnavn` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `products`
--

LOCK TABLES `products` WRITE;
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
INSERT INTO `products` VALUES (0,1,'赤身と脂身が良いバランスで、とても食べやすい部位。',0,'1',_binary '','国産 豚肩ロース　100g',250,0.08),(1,1,'',0,'1',_binary '','アメリカ産 豚切りおとし 230g',455,0.08),(2,1,'des',0,'2',_binary '','国産 豚肉かたロース 150g',387,0.08),(3,1,'des',0,'3',_binary '','国産 豚肉ロース　とんかつ・ソテー用 110g',305,0.08),(4,1,'des',0,'4',_binary '','国産 豚肉かたロース焼肉用 110g',327,0.08),(5,1,'des',0,'5',_binary '','アメリカ産 豚肉かたロースかたまり 300g',474,0.08),(6,1,'des',0,'6',_binary '','国産 豚肉スペアリブ 300g',534,0.08),(7,1,'des',0,'7',_binary '','国産　豚肉ばらうす切り 150g',387,0.08),(8,1,'des',0,'8',_binary '','スペイン産 イベリコ豚バラうす切り 380g',741,0.08),(9,1,'des',0,'9',_binary '','国産　豚肉ロース生姜焼き・豚丼用 135g',375,0.08),(10,1,'des',0,'10',_binary '','国産 豚小間切れ 290g',371,0.08),(11,1,'des',0,'11',_binary '','アメリカ産 豚肉小間切れ 200g',176,0.08),(12,1,'des',0,'12',_binary '','アメリカ産 豚肉ロースとんかつ・ソテー用 300g',414,0.08),(13,1,'des',0,'13',_binary '','国産 ブランド物　鶏もも肉 150g',297,0.08),(14,1,'des',0,'14',_binary '','国産 ブランド物　鶏もも肉 250g',495,0.08),(15,1,'des',0,'15',_binary '','国産　若鶏　もも肉 300g',294,0.08),(16,1,'des',0,'16',_binary '','国産　若鶏　もも肉 600g',588,0.08),(17,1,'des',0,'17',_binary '','国産　若鶏　もも肉 900g',855,0.08),(18,1,'des',0,'18',_binary '','国産　若鶏　もも肉 1200g',1140,0.08),(19,1,'des',0,'19',_binary '','国産　若鶏　もも肉 1500g',1425,0.08),(20,1,'des',0,'20',_binary '','国産　若鶏　むね肉 300g',234,0.08),(21,1,'des',0,'21',_binary '','国産　若鶏　むね肉 600g',468,0.08),(22,1,'des',0,'22',_binary '','国産　若鶏　むね肉 900g',702,0.08),(23,1,'des',0,'23',_binary '','国産　若鶏　むね肉 1200g',936,0.08),(24,1,'des',0,'24',_binary '','国産　若鶏　手羽元 1000g',580,0.08),(25,1,'des',0,'25',_binary '','サラダチキン（プレーン） 110g 1パック',248,0.08),(26,1,'des',0,'26',_binary '','サラダチキン（スモーク） 35g 1パック',158,0.08),(27,1,'des',0,'27',_binary '','サラダチキン（バジル） 110g 1パック',248,0.08),(28,1,'des',0,'28',_binary '','国産　わかどり　ささみ 280g',338,0.08),(29,1,'des',0,'29',_binary '','和牛サーロインステーキ 200g',1960,0.08),(30,1,'des',0,'30',_binary '','和牛きりおとし 150g',897,0.08),(31,1,'des',0,'31',_binary '','国産ばらカルビ焼用　100g',698,0.08),(32,1,'des',0,'32',_binary '','アメリカ産　牛タン 70g',558,0.08),(33,1,'des',0,'33',_binary '','オーストラリア産　牛肉すじ　シチュー用',496,0.08),(34,1,'des',0,'34',_binary '','和牛かたスライス 230g',2024,0.08),(35,1,'des',0,'35',_binary '','タスマニアビーフ ももローストビーフ用かたまり 250g',995,0.08),(36,1,'des',0,'36',_binary '','タスマニアビーフ サーロインステーキ 150g',897,0.08),(37,1,'des',0,'37',_binary '','タスマニアビーフ かたしゃぶしゃぶ用 240g',1195,0.08),(38,1,'des',0,'38',_binary '','タスマニアビーフ  ビーフカレー用 200g',596,0.08),(39,1,'des',0,'39',_binary '','タスマニアビーフ  切りおとし 200g',796,0.08),(40,1,'des',0,'40',_binary '','タスマニアビーフ  ハンバーグ デミグラス 200g',398,0.08),(41,1,'des',0,'41',_binary '','タスマニアビーフ  ハンバーグ 和風 200g',398,0.08),(42,1,'des',0,'42',_binary '','タスマニアビーフ  ハンバーグステーキ 140g',358,0.08),(43,1,'des',0,'43',_binary '','PB ロースハム 160g',258,0.08),(44,1,'des',0,'image',_binary '','I社 ロースハム 150g',398,0.08),(45,1,'des',0,'image',_binary '','I社 ももハム切り落とし 150g',398,0.08),(46,1,'des',0,'image',_binary '','I社 薄切りロースハム 82g',278,0.08),(47,1,'des',0,'image',_binary '','PB モモスライス 60g',198,0.08),(48,1,'des',0,'image',_binary '','N社 ロースハム 38g',98,0.08),(49,1,'des',0,'image',_binary '','I社　ハムステーキ 155g',358,0.08),(50,1,'des',0,'image',_binary '','PB ポークウィンナー 480g',298,0.08),(51,1,'des',0,'image',_binary '','PB 皮なしウインナー 275g',258,0.08),(52,1,'des',0,'image',_binary '','PB 特級あらびきポークウィンナー 260g',348,0.08),(53,1,'des',0,'image',_binary '','PB チキンウインナー 281g',251,0.08),(54,1,'des',0,'image',_binary '','PB 皮なしミニウインナー 80g',98,0.08),(55,2,'des',0,'image',_binary '','まぐろたたき 100g',398,0.08),(56,1,'des',0,'image',_binary '','びんちょうまぐろ 200g',516,0.08),(57,1,'des',0,'image',_binary '','サーモントラウト 刺身用 150g',597,0.08),(58,1,'des',0,'image',_binary '','ほたて 100g',598,0.08),(59,1,'des',0,'image',_binary '','かつおのたたき　お刺身 8切れ',398,0.08),(60,1,'des',0,'image',_binary '','天然ぶり 100g',238,0.08),(61,1,'des',0,'image',_binary '','塩真たら 100g',238,0.08),(62,1,'des',0,'image',_binary '','銀だら 100g',198,0.08),(63,1,'des',0,'image',_binary '','パナメイむきえび 200g',476,0.08),(64,1,'des',0,'image',_binary '','ずわいがに　１杯',1980,0.08),(65,1,'des',0,'image',_binary '','赤海老 1尾',130,0.08),(66,1,'des',0,'image',_binary '','生牡蠣 80g',278,0.08),(67,1,'des',0,'image',_binary '','辛子明太子 100g',598,0.08),(68,1,'des',0,'image',_binary '','いくら 24g',598,0.08),(69,1,'des',0,'image',_binary '','いか塩辛 200g',298,0.08),(70,1,'des',0,'image',_binary '','塩サバ 2切',438,0.08),(71,1,'des',0,'image',_binary '','紅鮭 100g',218,0.08),(72,1,'des',0,'image',_binary '','kyukyu51',100,0.08),(73,1,'des',0,'image',_binary '','kyukyu2131',100,0.08),(74,1,'des',0,'image',_binary '','kyukyu231',100,0.08),(75,1,'des',0,'image',_binary '','kyukyu321312',100,0.08),(76,1,'des',0,'image',_binary '','kyukyu321',100,0.08),(77,1,'des',0,'image',_binary '','kyukyu332121',100,0.08),(78,1,'des',0,'image',_binary '','kyukyu212331',100,0.08),(79,1,'des',0,'image',_binary '','kyukyu523321',100,0.08),(80,1,'des',0,'image',_binary '','oiuk52211',100,0.08),(81,1,'des',0,'image',_binary '','oiuk521',100,0.08),(82,1,'des',0,'image',_binary '','oiuk51',100,0.08),(83,1,'des',0,'image',_binary '','oiuk2131',100,0.08),(84,1,'des',0,'image',_binary '','oiuk231',100,0.08),(85,1,'des',0,'image',_binary '','oiuk321312',100,0.08),(86,1,'des',0,'image',_binary '','oiuk321',100,0.08),(87,1,'des',0,'image',_binary '','oiuk332121',100,0.08),(88,1,'des',0,'image',_binary '','oiuk212331',100,0.08),(89,1,'des',0,'image',_binary '','oiuk523321',100,0.08),(90,1,'des',0,'image',_binary '','loikj52211',100,0.08),(91,1,'des',0,'image',_binary '','loikj521',100,0.08),(92,1,'des',0,'image',_binary '','loikj51',100,0.08),(93,1,'des',0,'image',_binary '','loikj2131',100,0.08),(94,1,'des',0,'image',_binary '','loikj231',100,0.08),(95,1,'des',0,'image',_binary '','loikj321312',100,0.08),(96,1,'des',0,'image',_binary '','loikj321',100,0.08),(97,1,'des',0,'image',_binary '','loikj332121',100,0.08),(98,1,'des',0,'image',_binary '','loikj212331',100,0.08),(99,1,'des',0,'image',_binary '','loikj523321',100,0.08),(100,1,'des',0,'image',_binary '','dasfzcx52211',100,0.08),(101,1,'des',0,'image',_binary '','dasfzcx521',100,0.08),(102,1,'des',0,'image',_binary '','dasfzcx51',100,0.08),(103,1,'des',0,'image',_binary '','dasfzcx2131',100,0.08),(104,1,'des',0,'image',_binary '','dasfzcx231',100,0.08),(105,1,'des',0,'image',_binary '','dasfzcx321312',100,0.08),(106,1,'des',0,'image',_binary '','dasfzcx321',100,0.08),(107,1,'des',0,'image',_binary '','dasfzcx332121',100,0.08),(108,1,'des',0,'image',_binary '','dasfzcx212331',100,0.08),(109,1,'des',0,'image',_binary '','dasfzcx523321',100,0.08);
/*!40000 ALTER TABLE `products` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-12-16 23:14:14
