-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 15, 2023 at 08:47 PM
-- Server version: 10.4.28-MariaDB
-- PHP Version: 8.0.28

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `toko_2218138`
--

-- --------------------------------------------------------

--
-- Table structure for table `tb_penjualan`
--

CREATE TABLE `tb_penjualan` (
  `id` int(11) NOT NULL,
  `kode` varchar(11) NOT NULL,
  `nama` varchar(20) NOT NULL,
  `jenis` varchar(11) NOT NULL,
  `merk` varchar(20) NOT NULL,
  `harga` int(20) NOT NULL,
  `jumlah` int(20) NOT NULL,
  `potong` int(11) NOT NULL,
  `total` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tb_penjualan`
--

INSERT INTO `tb_penjualan` (`id`, `kode`, `nama`, `jenis`, `merk`, `harga`, `jumlah`, `potong`, `total`) VALUES
(11, 'B3920', 'Minyak Angin', 'Lain-Lain', 'Cap Lang', 17000, 2, 0, 34000),
(12, 'Go783', 'Mie', 'Grosir', 'Indomie', 3500, 20, 3000, 67000),
(13, 'Go783', 'Mie', 'Grosir', 'Indomie', 3500, 12, 3000, 39000),
(14, 'Mi872', 'Es Jeruk', 'Minuman', 'Sosro', 5000, 9, 3000, 42000);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tb_penjualan`
--
ALTER TABLE `tb_penjualan`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `tb_penjualan`
--
ALTER TABLE `tb_penjualan`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
