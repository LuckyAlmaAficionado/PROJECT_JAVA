-- phpMyAdmin SQL Dump
-- version 5.1.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jul 02, 2022 at 09:47 AM
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
-- Table structure for table `tb_absen`
--

CREATE TABLE `tb_absen` (
  `id` int(11) NOT NULL,
  `id_karyawan` varchar(50) NOT NULL,
  `tanggal` varchar(50) NOT NULL,
  `jam_masuk` varchar(50) NOT NULL,
  `jam_keluar` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tb_absen`
--

INSERT INTO `tb_absen` (`id`, `id_karyawan`, `tanggal`, `jam_masuk`, `jam_keluar`) VALUES
(26, 'K-2', '22-06-2022', '16:23:54', '16:24:08'),
(27, 'K-1', '02-07-2022', '00:16:19', '14:23:34'),
(28, 'K-1', '02-07-2022', '00:16:19', '14:23:34'),
(29, 'K-1', '02-07-2022', '00:16:19', '14:23:34'),
(30, 'K-1', '02-07-2022', '00:16:19', '14:23:34'),
(31, 'K-1', '02-07-2022', '00:16:19', '14:23:34'),
(32, 'K-1', '02-07-2022', '00:16:19', '14:23:34'),
(33, 'K-1', '02-07-2022', '00:16:19', '14:23:34'),
(34, 'K-1', '02-07-2022', '00:24:03', '14:23:34'),
(35, 'K-5', '02-07-2022', '00:37:59', '00:38:07'),
(36, 'K-1', '02-07-2022', '14:23:32', '14:23:34');

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
  `tgl_masuk` varchar(50) NOT NULL,
  `tgl_keluar` varchar(50) NOT NULL,
  `hari` int(11) NOT NULL,
  `jumlah_orang` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tb_checkin`
--

INSERT INTO `tb_checkin` (`id_transaksi`, `no_transaksi`, `nik_tamu`, `nama_tamu`, `status_tamu`, `alamat_tamu`, `hp_tamu`, `wn_tamu`, `jenkel_tamu`, `tipe_room`, `tgl_masuk`, `tgl_keluar`, `hari`, `jumlah_orang`) VALUES
(5, 'T-4', '6371050908020014', 'LUCKY ALMA AFICIONADO RIGEL', 'Belum Menikah', 'Jl. MANDI ANGIN NO.11', '087719857757', 'WNI', 'Laki-laki', 'Superior Room', '16-06-2022', '24-06-2022', 8, '2 Adult, 0 Child'),
(20, 'T-19', 'asd', 'sad', 'Belum Menikah', 'asd', 'asd', 'WNA', 'Laki-laki', 'Regular Room', '05-07-2022', '08-07-2022', 3, '2 Adult, 2 Child'),
(21, 'T-20', 'Lucky', 'sad', 'Sudah Menikah', 'sda', 'sad', 'WNI', 'Laki-laki', 'Regular Room', '05-07-2022', '07-07-2022', 2, '2 Adult, 1 Child'),
(22, 'T-21', 'Lucky Alma', 'sad', 'Sudah Menikah', 'sad', 'sa', 'WNA', 'Laki-laki', 'Regular Room', '07-07-2022', '16-07-2022', 9, '1 Adult, 0 Child'),
(23, 'T-22', 'Lucky Alma', 'Lucky Alma', 'Sudah Menikah', '213s', 'adsd', 'WNA', 'Laki-laki', 'Regular Room', '11-07-2022', '08-07-2022', -3, '2 Adult, 1 Child');

-- --------------------------------------------------------

--
-- Table structure for table `tb_history`
--

CREATE TABLE `tb_history` (
  `no_transaksi` varchar(50) NOT NULL,
  `nik_tamu` varchar(50) NOT NULL,
  `nama_tamu` varchar(50) NOT NULL,
  `tgl_masuk` varchar(50) NOT NULL,
  `tgl_keluar` varchar(50) NOT NULL,
  `kerusakan` varchar(50) NOT NULL,
  `keterangan` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tb_history`
--

INSERT INTO `tb_history` (`no_transaksi`, `nik_tamu`, `nama_tamu`, `tgl_masuk`, `tgl_keluar`, `kerusakan`, `keterangan`) VALUES
('T-23', '08465345463153', 'Vincentuius', '04-07-2022', '16-07-2022', '3', 'Wastafel Pecah, Toilet Retak');

-- --------------------------------------------------------

--
-- Table structure for table `tb_kamar`
--

CREATE TABLE `tb_kamar` (
  `id_kmr` varchar(50) NOT NULL,
  `tipe_kmr` varchar(50) NOT NULL,
  `harga_kmr` int(11) NOT NULL,
  `kmr_kosong` int(11) NOT NULL,
  `kmr_isi` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tb_kamar`
--

INSERT INTO `tb_kamar` (`id_kmr`, `tipe_kmr`, `harga_kmr`, `kmr_kosong`, `kmr_isi`) VALUES
('K-001', 'Regular Room', 150000, 4, 6),
('K-002', 'Superior Room', 200000, 7, 3),
('K-003', 'Deluxe Room', 500000, 7, 3),
('K-004', 'Family Room', 450000, 7, 3),
('K-005', 'President Suit Room', 1000000, 7, 3);

-- --------------------------------------------------------

--
-- Table structure for table `tb_karyawan`
--

CREATE TABLE `tb_karyawan` (
  `id_karyawan` varchar(11) NOT NULL,
  `nama_depan` varchar(50) NOT NULL,
  `nama_belakang` varchar(50) NOT NULL,
  `nama_lengkap` varchar(100) DEFAULT NULL,
  `ttl_karyawan` varchar(50) DEFAULT NULL,
  `jenkel_karyawan` varchar(50) DEFAULT NULL,
  `agama_karyawan` varchar(50) DEFAULT NULL,
  `status_karyawan` varchar(50) DEFAULT NULL,
  `goldar_karyawan` varchar(50) DEFAULT NULL,
  `alamat_karyawan` varchar(50) DEFAULT NULL,
  `rtrw_karyawan` varchar(50) DEFAULT NULL,
  `keldesa_karyawan` varchar(50) DEFAULT NULL,
  `kecamatan_karyawan` varchar(50) DEFAULT NULL,
  `email_karyawan` varchar(50) DEFAULT NULL,
  `no_karyawan` varchar(50) DEFAULT NULL,
  `medsos_karyawan` varchar(50) DEFAULT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(64) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tb_karyawan`
--

INSERT INTO `tb_karyawan` (`id_karyawan`, `nama_depan`, `nama_belakang`, `nama_lengkap`, `ttl_karyawan`, `jenkel_karyawan`, `agama_karyawan`, `status_karyawan`, `goldar_karyawan`, `alamat_karyawan`, `rtrw_karyawan`, `keldesa_karyawan`, `kecamatan_karyawan`, `email_karyawan`, `no_karyawan`, `medsos_karyawan`, `username`, `password`) VALUES
('K-1', 'Lucky ', 'Rigel', 'Lucky Alma Aficionado Rigel', 'Semarang, 09-06-1999', 'Laki - laki', 'Islam', 'Belum Menikah', 'A', 'Pembangunan 1', '09/07', 'Pedurungan', 'Plamongan Sari', '11120102073107@gmail.com', '08765461161516', '_luckyalmaaficionado', 'admin', 'admin'),
('K-2', 'Carlota', 'Mauren', 'Carlota Massaccio Mauren', '', '', '', '', '', '', '', '', '', '', '', '', 'lota', 'lota'),
('K-3', 'Atha', 'Ardisa', '', '', '', '', '', '', '', '', '', '', '', '', '', 'artaardisa', '123'),
('K-4', 'deva', 'ganteng', '', 'Semarang, 980203', 'Perempuan', 'Hindu', 'Belum Menikah', 'A', '', '', '', '', '', '', '', 'deva123', '12345'),
('K-5', 'Lucky', 'Rigel', '', 'Semarang, 09-08-2002', 'Laki - laki', 'Islam', 'Belum Menikah', 'O', 'Plamongan Indah', '09/07', 'Pedurungan', 'Pedurungan', '111202012797@mhs.dinus.ac.id', '087719857757', '_luckyalmaaficionado', '123', '123');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tb_absen`
--
ALTER TABLE `tb_absen`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tb_checkin`
--
ALTER TABLE `tb_checkin`
  ADD PRIMARY KEY (`id_transaksi`,`nik_tamu`);

--
-- Indexes for table `tb_history`
--
ALTER TABLE `tb_history`
  ADD PRIMARY KEY (`no_transaksi`);

--
-- Indexes for table `tb_kamar`
--
ALTER TABLE `tb_kamar`
  ADD PRIMARY KEY (`id_kmr`);

--
-- Indexes for table `tb_karyawan`
--
ALTER TABLE `tb_karyawan`
  ADD PRIMARY KEY (`id_karyawan`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `tb_absen`
--
ALTER TABLE `tb_absen`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=37;

--
-- AUTO_INCREMENT for table `tb_checkin`
--
ALTER TABLE `tb_checkin`
  MODIFY `id_transaksi` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
