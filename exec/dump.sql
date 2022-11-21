-- --------------------------------------------------------
-- 호스트:                          k7d106.p.ssafy.io
-- 서버 버전:                        10.9.3-MariaDB-1:10.9.3+maria~ubu2204 - mariadb.org binary distribution
-- 서버 OS:                        debian-linux-gnu
-- HeidiSQL 버전:                  11.3.0.6295
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- ijij 데이터베이스 구조 내보내기
CREATE DATABASE IF NOT EXISTS `ijij` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;
USE `ijij`;

-- 테이블 ijij.category 구조 내보내기
CREATE TABLE IF NOT EXISTS `category` (
  `category_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `category_name` varchar(255) NOT NULL,
  `color` varchar(255) NOT NULL,
  `time` varchar(255) NOT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`category_id`),
  KEY `FKpfk8djhv5natgshmxiav6xkpu` (`user_id`),
  CONSTRAINT `FKpfk8djhv5natgshmxiav6xkpu` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=263 DEFAULT CHARSET=utf8mb4;

-- 테이블 데이터 ijij.category:~11 rows (대략적) 내보내기
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` (`category_id`, `category_name`, `color`, `time`, `user_id`) VALUES
	(42, '기본', '#D5EAEF', '0100', 4),
	(213, '개인면담', '#c3dbe3', '0100', 9),
	(214, '개인상담', '#d7dfff', '0130', 9),
	(246, '테스트', '#c3dbe3', '0100', 24),
	(247, '기본카테고리', '#fdeec6', '0100', 28),
	(249, '발표 준비', '#d5eaef', '0500', 31),
	(258, '남성컷', '#d5eaef', '0100', 8),
	(259, '남성펌', '#ffc4c2', '0200', 8),
	(260, '여성펌', '#f0f4c4', '0200', 8),
	(261, '여성염색', '#d7cbf4', '0400', 8),
	(262, '남성염색', '#ffe3f4', '0200', 8);
/*!40000 ALTER TABLE `category` ENABLE KEYS */;

-- 테이블 ijij.schedule 구조 내보내기
CREATE TABLE IF NOT EXISTS `schedule` (
  `schedule_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `cancel_from` varchar(255) DEFAULT NULL,
  `category_name` varchar(255) NOT NULL,
  `color` varchar(255) DEFAULT NULL,
  `contents` varchar(255) DEFAULT NULL,
  `end_date` datetime(6) NOT NULL,
  `phonenum` varchar(255) DEFAULT NULL,
  `reason` varchar(255) DEFAULT NULL,
  `start_date` datetime(6) NOT NULL,
  `type` varchar(255) NOT NULL,
  `user_from_id` bigint(20) DEFAULT NULL,
  `user_to_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`schedule_id`),
  KEY `FKq8ahi9ls6ou18yhcdlhjv82wl` (`user_from_id`),
  KEY `FKoc7nk4jh5eglfajcp580i6bs2` (`user_to_id`),
  CONSTRAINT `FKoc7nk4jh5eglfajcp580i6bs2` FOREIGN KEY (`user_to_id`) REFERENCES `user` (`user_id`),
  CONSTRAINT `FKq8ahi9ls6ou18yhcdlhjv82wl` FOREIGN KEY (`user_from_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=671 DEFAULT CHARSET=utf8mb4;

-- 테이블 데이터 ijij.schedule:~49 rows (대략적) 내보내기
/*!40000 ALTER TABLE `schedule` DISABLE KEYS */;
INSERT INTO `schedule` (`schedule_id`, `cancel_from`, `category_name`, `color`, `contents`, `end_date`, `phonenum`, `reason`, `start_date`, `type`, `user_from_id`, `user_to_id`) VALUES
	(27, NULL, '아무거나2', '#d7cbf4', '오마이갓', '2022-11-19 12:00:00.000000', '01012312344', NULL, '2022-11-19 10:30:00.000000', 'DELETE', 8, 9),
	(40, '사용자', '아무거나2', '#d7cbf4', '멍청이', '2022-11-20 16:30:00.000000', '01012312344', '다 취소할거야', '2022-11-20 15:00:00.000000', 'CANCEL', 8, 9),
	(41, '제공자', '아무거나', '#ffc4c2', '바보', '2022-11-20 18:00:00.000000', '01012312344', '아무튼 안됩니다.', '2022-11-20 17:00:00.000000', 'CANCEL', 8, 9),
	(42, NULL, 'ㄷㄹㄷㄹ', '#ffc4c2', '야이자식아', '2022-11-18 11:00:00.000000', '01092720643', NULL, '2022-11-18 10:00:00.000000', 'DELETE', 9, 8),
	(43, NULL, '도돔ㄷㅎ', '#c3dbe3', '야이자식아2', '2022-11-19 11:30:00.000000', '01092720643', NULL, '2022-11-19 10:30:00.000000', 'DELETE', 9, 8),
	(53, NULL, 'ㄷㄹㄷㄹ', '#ffc4c2', 'ㅎㅎ', '2022-11-18 21:00:00.000000', '01092720643', NULL, '2022-11-18 20:00:00.000000', 'DELETE', 9, 8),
	(147, '제공자', '아무거나2', '#d7cbf4', '부탁합니다이', '2022-11-19 16:30:00.000000', '01012312344', '바쁘다고 했잔아요', '2022-11-19 15:00:00.000000', 'CANCEL', 8, 9),
	(148, '사용자', '아무거나', '#ffc4c2', '안녕하세요', '2022-11-20 12:30:00.000000', '01012312344', '내맘입니다.', '2022-11-20 11:30:00.000000', 'CANCEL', 8, 9),
	(150, '사용자', '아무거나2', '#d7cbf4', '잘 부탁드립니다 ^^', '2022-11-19 11:00:00.000000', '01073666880', '취소 테스트임', '2022-11-19 09:30:00.000000', 'CANCEL', 8, 9),
	(151, '제공자', '아무거나2', '#d7cbf4', '어차피 취소할 예약', '2022-11-18 22:00:00.000000', '01073666880', '지송', '2022-11-18 20:30:00.000000', 'CANCEL', 8, 9),
	(152, '사용자', '아무거나', '#ffc4c2', '조인후의 시간을 뺏겠습니다', '2022-11-21 11:00:00.000000', '01073666880', '마지막 취소 텐스트', '2022-11-21 10:00:00.000000', 'CANCEL', 8, 9),
	(153, '사용자', '아무거나', '#ffc4c2', '안녀아세요', '2022-11-21 13:00:00.000000', '01073666880', '취소취소', '2022-11-21 12:00:00.000000', 'CANCEL', 8, 9),
	(154, '사용자', '아무거나2', '#d7cbf4', '안녀아세요', '2022-11-24 12:30:00.000000', '01073666880', '웅', '2022-11-24 11:00:00.000000', 'CANCEL', 8, 9),
	(437, '제공자', '아무거나2', '#d7cbf4', '부탁합니다', '2022-11-27 16:00:00.000000', '01073666880', '지송', '2022-11-27 14:30:00.000000', 'CANCEL', 8, 9),
	(448, '제공자', '멋진 그림', '#ffc4c2', 'ㅎㅎ', '2022-11-19 19:00:00.000000', '01092720643', '하기 싫거덩', '2022-11-19 17:00:00.000000', 'CANCEL', 9, 8),
	(451, '제공자', '예쁜 그림', '#D5EAEF', '', '2022-11-20 21:30:00.000000', '01092720643', '절대 안해주지 ㅋ', '2022-11-20 20:30:00.000000', 'CANCEL', 9, 8),
	(602, NULL, '여성펌', '#f0f4c4', '히피펌 부탁드립니다', '2022-11-27 14:00:00.000000', '01092720643', NULL, '2022-11-27 12:00:00.000000', 'ACCEPT', 9, 8),
	(603, NULL, 'block', NULL, NULL, '2022-11-15 11:00:00.000000', NULL, NULL, '2022-11-15 10:30:00.000000', 'BLOCK', NULL, 9),
	(604, NULL, 'block', NULL, NULL, '2022-11-15 11:30:00.000000', NULL, NULL, '2022-11-15 11:00:00.000000', 'BLOCK', NULL, 9),
	(605, NULL, 'block', NULL, NULL, '2022-11-16 11:00:00.000000', NULL, NULL, '2022-11-16 10:30:00.000000', 'BLOCK', NULL, 9),
	(606, NULL, 'block', NULL, NULL, '2022-11-16 11:30:00.000000', NULL, NULL, '2022-11-16 11:00:00.000000', 'BLOCK', NULL, 9),
	(607, NULL, 'block', NULL, NULL, '2022-11-17 11:00:00.000000', NULL, NULL, '2022-11-17 10:30:00.000000', 'BLOCK', NULL, 9),
	(608, NULL, 'block', NULL, NULL, '2022-11-17 11:30:00.000000', NULL, NULL, '2022-11-17 11:00:00.000000', 'BLOCK', NULL, 9),
	(609, NULL, 'block', NULL, NULL, '2022-11-18 11:00:00.000000', NULL, NULL, '2022-11-18 10:30:00.000000', 'BLOCK', NULL, 9),
	(610, NULL, 'block', NULL, NULL, '2022-11-18 11:30:00.000000', NULL, NULL, '2022-11-18 11:00:00.000000', 'BLOCK', NULL, 9),
	(611, NULL, '개인면담', '#c3dbe3', '', '2022-11-26 11:30:00.000000', '01066032208', NULL, '2022-11-26 10:30:00.000000', 'REQUEST', 20, 9),
	(612, NULL, '개인면담', '#c3dbe3', '개인 면담 부탁드립니다.', '2022-11-21 11:00:00.000000', '01073666880', NULL, '2022-11-21 10:00:00.000000', 'ACCEPT', 8, 9),
	(614, NULL, '여성펌', '#f0f4c4', '', '2022-11-26 13:00:00.000000', '01066032208', NULL, '2022-11-26 11:00:00.000000', 'ACCEPT', 20, 8),
	(623, NULL, '개인면담', '#c3dbe3', '잘 부탁드립니다', '2022-11-25 13:00:00.000000', '01092720643', NULL, '2022-11-25 12:00:00.000000', 'REQUEST', 20, 9),
	(624, NULL, '개인상담', '#d7dfff', '잘 부탁드립니다', '2022-11-22 11:30:00.000000', '01092720643', NULL, '2022-11-22 10:00:00.000000', 'ACCEPT', 20, 9),
	(625, NULL, '개인상담', '#d7dfff', '잘 부탁드립니다', '2022-11-26 10:30:00.000000', '01092720643', NULL, '2022-11-26 09:00:00.000000', 'ACCEPT', 20, 9),
	(630, NULL, '여성염색', '#d7cbf4', '탈색할게요\n머리색은 상담 받고 싶어요', '2022-11-23 17:00:00.000000', '01092720643', NULL, '2022-11-23 13:00:00.000000', 'ACCEPT', 9, 8),
	(631, '제공자', '여성염색', '#d7cbf4', '밀크 브라운으로 부탁드립니다', '2022-11-21 13:00:00.000000', '01033876880', '죄송합니다.', '2022-11-21 09:00:00.000000', 'CANCEL', 26, 8),
	(640, '제공자', '여성펌', '#f0f4c4', '안녕하세요', '2022-11-24 11:00:00.000000', '01000000000', '2', '2022-11-24 09:00:00.000000', 'CANCEL', 26, 8),
	(647, NULL, '개인상담', '#d7dfff', '개인 상담 요청합니다.', '2022-11-21 13:30:00.000000', '01073666880', NULL, '2022-11-21 12:00:00.000000', 'REQUEST', 8, 9),
	(648, NULL, '개인면담', '#c3dbe3', '개인면담 신청합니다.', '2022-11-22 13:00:00.000000', '01073666880', NULL, '2022-11-22 12:00:00.000000', 'REQUEST', 8, 9),
	(649, '사용자', '개인상담', '#d7dfff', '개인상담 신청합니다.', '2022-11-25 14:30:00.000000', '01073666880', '갑자기 마음이 바뀌었습니다.', '2022-11-25 13:00:00.000000', 'CANCEL', 8, 9),
	(650, NULL, '개인면담', '#c3dbe3', '진지한 상담 부탁드립니다.', '2022-11-23 13:00:00.000000', '01073666880', NULL, '2022-11-23 12:00:00.000000', 'REQUEST', 8, 9),
	(655, NULL, '여성염색', '#d7cbf4', '밀크브라운으로 부탁드립니다.', '2022-11-21 13:00:00.000000', '01033876880', NULL, '2022-11-21 09:00:00.000000', 'ACCEPT', 26, 8),
	(657, '제공자', '테스트', '#c3dbe3', '예약신청', '2022-11-25 13:30:00.000000', '01071527518', '호ㅎ호', '2022-11-25 12:30:00.000000', 'CANCEL', 28, 24),
	(658, '사용자', '테스트', '#c3dbe3', '예약신청', '2022-11-23 11:00:00.000000', '01071527518', '취소하고싶네요', '2022-11-23 10:00:00.000000', 'CANCEL', 28, 24),
	(659, NULL, 'block', NULL, NULL, '2022-11-22 11:30:00.000000', NULL, NULL, '2022-11-22 11:00:00.000000', 'BLOCK', NULL, 31),
	(660, NULL, 'block', NULL, NULL, '2022-11-23 11:30:00.000000', NULL, NULL, '2022-11-23 11:00:00.000000', 'BLOCK', NULL, 31),
	(661, NULL, 'block', NULL, NULL, '2022-11-22 12:00:00.000000', NULL, NULL, '2022-11-22 11:30:00.000000', 'BLOCK', NULL, 31),
	(662, NULL, 'block', NULL, NULL, '2022-11-23 12:00:00.000000', NULL, NULL, '2022-11-23 11:30:00.000000', 'BLOCK', NULL, 31),
	(663, NULL, 'block', NULL, NULL, '2022-11-24 12:00:00.000000', NULL, NULL, '2022-11-24 11:30:00.000000', 'BLOCK', NULL, 31),
	(664, NULL, 'block', NULL, NULL, '2022-11-24 11:30:00.000000', NULL, NULL, '2022-11-24 11:00:00.000000', 'BLOCK', NULL, 31),
	(665, NULL, 'block', NULL, NULL, '2022-11-25 11:30:00.000000', NULL, NULL, '2022-11-25 11:00:00.000000', 'BLOCK', NULL, 31),
	(666, NULL, 'block', NULL, NULL, '2022-11-25 12:00:00.000000', NULL, NULL, '2022-11-25 11:30:00.000000', 'BLOCK', NULL, 31);
/*!40000 ALTER TABLE `schedule` ENABLE KEYS */;

-- 테이블 ijij.user 구조 내보내기
CREATE TABLE IF NOT EXISTS `user` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `close_time` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `image_path` varchar(255) DEFAULT NULL,
  `introduction` varchar(255) DEFAULT NULL,
  `nickname` varchar(255) DEFAULT NULL,
  `open_time` varchar(255) DEFAULT NULL,
  `phonenum` varchar(255) DEFAULT NULL,
  `friday` tinyint(1) DEFAULT NULL,
  `monday` tinyint(1) DEFAULT NULL,
  `saturday` tinyint(1) DEFAULT NULL,
  `sunday` tinyint(1) DEFAULT NULL,
  `thursday` tinyint(1) DEFAULT NULL,
  `tuesday` tinyint(1) DEFAULT NULL,
  `wednesday` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `UK_ob8kqyqqgmefl0aco34akdtpe` (`email`),
  UNIQUE KEY `UK_n4swgcf30j6bmtb4l4cjryuym` (`nickname`),
  UNIQUE KEY `UK_le7md2am20u8jy9jdus549sqn` (`phonenum`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8mb4;

-- 테이블 데이터 ijij.user:~10 rows (대략적) 내보내기
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`user_id`, `close_time`, `description`, `email`, `image_path`, `introduction`, `nickname`, `open_time`, `phonenum`, `friday`, `monday`, `saturday`, `sunday`, `thursday`, `tuesday`, `wednesday`) VALUES
	(4, NULL, '되세요', 'goodman1223@naver.com', 'http://k.kakaocdn.net/dn/qveAv/btrKAsR6m5v/GBEWkaIiLf0sLyqOZ01HE0/img_640x640.jpg', '행복한하루', '쌍미니', NULL, NULL, 0, 0, 0, 0, 0, 0, 0),
	(8, NULL, '☆ 컷 \n남성컷 ------- 17,000원\n여성컷 ------- 20,000원\n\n◎ 일반펌\n남성펌 ------- 65,000원 \n여성펌 ------- 80,000원\n\n★ 염색\n남성염색 ----- 60,000원\n여성염색 ----- 80,000원', 'wn00315@naver.com', 'http://k.kakaocdn.net/dn/TVp0G/btrOZJCsdV0/EYPLTcE0k5Ua1MZXqL4hK1/img_640x640.jpg', '고객님께 어울리는 맞춤 상담과 전문가의 손길로 친절하게 서비스 하겠습니다.', '주영디자이너', NULL, '01073666880', 0, 0, 0, 0, 0, 0, 0),
	(9, NULL, '개인면담 가능합니다.', 'lovelinus57@naver.com', 'http://k.kakaocdn.net/dn/9yXs4/btrLYlDpDiM/dqOHkmFNox6FVqEqFiIXr0/img_640x640.jpg', '상담 전문가 김싸피입니다.', '김싸피', NULL, NULL, 1, 0, 0, 0, 1, 0, 1),
	(15, NULL, NULL, 'rnrwls211@naver.com', 'http://k.kakaocdn.net/dn/dpk9l1/btqmGhA2lKL/Oz0wDuJn1YV2DIn92f6DVK/img_640x640.jpg', NULL, 'aa', NULL, NULL, 0, 0, 0, 0, 0, 0, 0),
	(20, NULL, NULL, 'dmg03170@naver.com', 'http://k.kakaocdn.net/dn/brlScc/btrLjjAox8J/onqAHofxfn2MX2VfuxIukK/img_640x640.jpg', '-', '잉구', NULL, '01066032208', 0, 0, 0, 0, 0, 0, 0),
	(24, NULL, NULL, 'scm1007@hanmail.net', 'http://k.kakaocdn.net/dn/bA2zrN/btrGPCi66eh/vONfgoC771zMFjYMkws3rk/img_640x640.jpg', '다은이 친구입니다', '다은친구', NULL, '01020360801', 0, 0, 0, 0, 1, 0, 0),
	(26, NULL, NULL, 'wjddls9813@naver.com', 'http://k.kakaocdn.net/dn/HvkwY/btrNw93jUTg/A2umZz1zruZfkKCfHjA0x1/img_640x640.jpg', '.', 'zzang', NULL, '01033876880', 0, 0, 0, 0, 0, 0, 0),
	(28, NULL, NULL, 'asd5687@naver.com', 'http://k.kakaocdn.net/dn/Q38SF/btrHPd5KnBJ/Azy0sBV147aCeZjggd6Sf0/img_640x640.jpg', 'aa', '다은', NULL, '01071527518', 0, 0, 0, 0, 0, 0, 0),
	(30, NULL, NULL, 'rl008222@daum.net', 'http://k.kakaocdn.net/dn/dpk9l1/btqmGhA2lKL/Oz0wDuJn1YV2DIn92f6DVK/img_640x640.jpg', 'df', '이기종', NULL, '01087624001', 0, 0, 0, 0, 0, 0, 0),
	(31, NULL, NULL, 'hmr2406@naver.com', 'http://k.kakaocdn.net/dn/dpk9l1/btqmGhA2lKL/Oz0wDuJn1YV2DIn92f6DVK/img_640x640.jpg', '우와아 일정 관리 해보겠습니다!', '김성령', NULL, '01037299155', 0, 0, 0, 0, 0, 0, 0);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
