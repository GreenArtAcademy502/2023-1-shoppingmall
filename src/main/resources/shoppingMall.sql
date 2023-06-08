-- --------------------------------------------------------
-- 호스트:                          127.0.0.1
-- 서버 버전:                        10.11.2-MariaDB - mariadb.org binary distribution
-- 서버 OS:                        Win64
-- HeidiSQL 버전:                  11.3.0.6295
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- shoppingmall 데이터베이스 구조 내보내기
CREATE DATABASE IF NOT EXISTS `shoppingmall` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */;
USE `shoppingmall`;

-- 테이블 shoppingmall.t_buy 구조 내보내기
CREATE TABLE IF NOT EXISTS `t_buy` (
  `ibuy` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `iproduct` bigint(20) unsigned NOT NULL,
  `icustomer` bigint(20) unsigned NOT NULL,
  `quantity` int(10) unsigned NOT NULL,
  `buy_at` datetime DEFAULT current_timestamp(),
  PRIMARY KEY (`ibuy`),
  KEY `iproduct` (`iproduct`),
  KEY `icustomer` (`icustomer`),
  CONSTRAINT `t_buy_ibfk_1` FOREIGN KEY (`iproduct`) REFERENCES `t_product` (`iproduct`),
  CONSTRAINT `t_buy_ibfk_2` FOREIGN KEY (`icustomer`) REFERENCES `t_customer` (`icustomer`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 shoppingmall.t_customer 구조 내보내기
CREATE TABLE IF NOT EXISTS `t_customer` (
  `icustomer` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `nm` varchar(15) NOT NULL,
  `addr` varchar(50) NOT NULL,
  `ph` char(13) NOT NULL,
  PRIMARY KEY (`icustomer`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 shoppingmall.t_product 구조 내보내기
CREATE TABLE IF NOT EXISTS `t_product` (
  `iproduct` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `price` int(10) unsigned NOT NULL,
  `ctnt` text NOT NULL,
  `nm` varchar(30) NOT NULL,
  `main_pic` varchar(100) NOT NULL,
  `brand` varchar(20) NOT NULL,
  `created_at` datetime DEFAULT current_timestamp(),
  PRIMARY KEY (`iproduct`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 shoppingmall.t_product_pic 구조 내보내기
CREATE TABLE IF NOT EXISTS `t_product_pic` (
  `iproduct_pic` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `iproduct` bigint(20) unsigned NOT NULL,
  `pic` varchar(100) NOT NULL,
  PRIMARY KEY (`iproduct_pic`),
  KEY `iproduct` (`iproduct`),
  CONSTRAINT `t_product_pic_ibfk_1` FOREIGN KEY (`iproduct`) REFERENCES `t_product` (`iproduct`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 shoppingmall.t_review 구조 내보내기
CREATE TABLE IF NOT EXISTS `t_review` (
  `ireview` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `ibuy` bigint(20) unsigned NOT NULL,
  `ctnt` text NOT NULL,
  `star` tinyint(4) NOT NULL,
  `created_at` datetime DEFAULT current_timestamp(),
  PRIMARY KEY (`ireview`),
  KEY `ibuy` (`ibuy`),
  CONSTRAINT `t_review_ibfk_1` FOREIGN KEY (`ibuy`) REFERENCES `t_buy` (`ibuy`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 shoppingmall.t_review_pic 구조 내보내기
CREATE TABLE IF NOT EXISTS `t_review_pic` (
  `ireview_pic` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `ireview` bigint(20) unsigned NOT NULL,
  `pic` varchar(100) NOT NULL,
  PRIMARY KEY (`ireview_pic`),
  KEY `ireview` (`ireview`),
  CONSTRAINT `t_review_pic_ibfk_1` FOREIGN KEY (`ireview`) REFERENCES `t_review` (`ireview`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- 내보낼 데이터가 선택되어 있지 않습니다.

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
