-- phpMyAdmin SQL Dump
-- version 5.1.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jul 01, 2022 at 03:17 PM
-- Server version: 10.4.24-MariaDB
-- PHP Version: 7.4.28

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_telebot`
--

-- --------------------------------------------------------

--
-- Table structure for table `db_akun`
--

CREATE TABLE `db_akun` (
  `id` int(11) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `db_akun`
--

INSERT INTO `db_akun` (`id`, `username`, `password`) VALUES
(1, 'a', 'a'),
(2, 'b', 'b'),
(3, 'pkkpokta2022', '123');

-- --------------------------------------------------------

--
-- Table structure for table `tb_data`
--

CREATE TABLE `tb_data` (
  `id_data` int(11) NOT NULL,
  `username` varchar(50) DEFAULT NULL,
  `noHp` varchar(50) DEFAULT NULL,
  `chatId` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tb_data`
--

INSERT INTO `tb_data` (`id_data`, `username`, `noHp`, `chatId`) VALUES
(3, 'LuckyRigel', '087719857757', '1049094300'),
(4, 'naufal_iksham', '082243090750', '871352347'),
(6, 'Oktaaa', '081226008762', '1162759904');

-- --------------------------------------------------------

--
-- Table structure for table `tb_keyword`
--

CREATE TABLE `tb_keyword` (
  `keyword` varchar(50) NOT NULL,
  `answer` varchar(225) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tb_keyword`
--

INSERT INTO `tb_keyword` (`keyword`, `answer`) VALUES
('/motor', 'Ninja 250cc'),
('/nama', 'Aliv Okta Aseda'),
('/sapa', 'Hai hallo /\" + name + \"/, apakabar semoga hari anda sangat menyenangkan'),
('/start', 'Hallo selamat datang ada yang bisa saya bantu ');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `db_akun`
--
ALTER TABLE `db_akun`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tb_data`
--
ALTER TABLE `tb_data`
  ADD PRIMARY KEY (`id_data`);

--
-- Indexes for table `tb_keyword`
--
ALTER TABLE `tb_keyword`
  ADD PRIMARY KEY (`keyword`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `db_akun`
--
ALTER TABLE `db_akun`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `tb_data`
--
ALTER TABLE `tb_data`
  MODIFY `id_data` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
