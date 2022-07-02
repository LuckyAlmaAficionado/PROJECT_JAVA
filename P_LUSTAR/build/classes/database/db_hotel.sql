-- phpMyAdmin SQL Dump
-- version 5.1.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 20, 2022 at 07:10 PM
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
-- Database: `db_hotel`
--

-- --------------------------------------------------------

--
-- Table structure for table `tb_checkin`
--

CREATE TABLE `tb_checkin` (
  `id_transaksi` int(11) NOT NULL,
  `no_transaksi` varchar(50) NOT NULL,
  `nik_tamu` varchar(50) NOT NULL,
  `nama_tamu` varchar(50) NOT NULL,
  `status_tamu` varchar(50) NOT NULL,
  `alamat_tamu` varchar(100) NOT NULL,
  `hp_tamu` varchar(50) NOT NULL,
  `wn_tamu` varchar(50) NOT NULL,
  `jenkel_tamu` varchar(20) NOT NULL,
  `tipe_room` varchar(100) NOT NULL,
  `hari` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tb_checkin`
--

INSERT INTO `tb_checkin` (`id_transaksi`, `no_transaksi`, `nik_tamu`, `nama_tamu`, `status_tamu`, `alamat_tamu`, `hp_tamu`, `wn_tamu`, `jenkel_tamu`, `tipe_room`, `hari`) VALUES
(1, 'T_1', 'awddwa', '', '', '', '', '', 'T_1', 'Reguler Room', 1);

-- --------------------------------------------------------

--
-- Table structure for table `tb_datakaryawan`
--

CREATE TABLE `tb_datakaryawan` (
  `id` int(11) NOT NULL,
  `first_name` varchar(20) NOT NULL,
  `last_name` varchar(20) NOT NULL,
  `title` varchar(20) NOT NULL,
  `email` varchar(50) NOT NULL,
  `number` varchar(15) NOT NULL,
  `username` varchar(30) NOT NULL,
  `password` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tb_datakaryawan`
--

INSERT INTO `tb_datakaryawan` (`id`, `first_name`, `last_name`, `title`, `email`, `number`, `username`, `password`) VALUES
(1, 'lucky', 'rigel', 'developer', 'luckyrigel9802@gmail.com', '087719857757', 'a', 'a'),
(2, 'Vincentius', 'Putra', 'Manager Hukum', 'vincentiussatrya@gmail.com', '081172837483', 'vc', '123'),
(3, 'Ghallen ', 'Rachmat', '', '', '', 'DenizGhallen', 'GhallenGanteng');

-- --------------------------------------------------------

--
-- Table structure for table `tb_kamar`
--

CREATE TABLE `tb_kamar` (
  `id_kmr` varchar(50) NOT NULL,
  `tipe_kmr` varchar(50) NOT NULL,
  `harga_kmr` int(11) NOT NULL,
  `kmr_kosong` int(11) NOT NULL,
  `kmr_isi` int(11) NOT NULL,
  `kmr_pesan` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tb_kamar`
--

INSERT INTO `tb_kamar` (`id_kmr`, `tipe_kmr`, `harga_kmr`, `kmr_kosong`, `kmr_isi`, `kmr_pesan`) VALUES
('K-001', 'Regular Room', 150000, 9, 1, 0),
('K-002', 'Superior Room', 200000, 10, 0, 0),
('K-003', 'Deluxe Room', 500000, 10, 0, 0),
('K-004', 'Family Room', 450000, 10, 0, 0),
('K-005', 'President Suit Room', 1000000, 10, 0, 0);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tb_checkin`
--
ALTER TABLE `tb_checkin`
  ADD PRIMARY KEY (`id_transaksi`,`nik_tamu`);

--
-- Indexes for table `tb_datakaryawan`
--
ALTER TABLE `tb_datakaryawan`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tb_kamar`
--
ALTER TABLE `tb_kamar`
  ADD PRIMARY KEY (`id_kmr`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `tb_checkin`
--
ALTER TABLE `tb_checkin`
  MODIFY `id_transaksi` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `tb_datakaryawan`
--
ALTER TABLE `tb_datakaryawan`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
