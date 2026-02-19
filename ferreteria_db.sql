-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Feb 19, 2026 at 04:54 AM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `ferreteria_db`
--
CREATE DATABASE IF NOT EXISTS `ferreteria_db` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `ferreteria_db`;

-- --------------------------------------------------------

--
-- Table structure for table `productos`
--

CREATE TABLE `productos` (
  `Id` int(11) NOT NULL,
  `Nombre` varchar(100) NOT NULL,
  `Descripcion` text DEFAULT NULL,
  `Precio` decimal(10,2) NOT NULL,
  `Stock` int(11) NOT NULL,
  `IdProveedor` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `productos`
--

INSERT INTO `productos` (`Id`, `Nombre`, `Descripcion`, `Precio`, `Stock`, `IdProveedor`) VALUES
(1, 'Teclado Mecánico', 'Teclado RGB switches azules', 45.99, 18, 1),
(2, 'Mouse Gamer', 'Mouse óptico 7200 DPI', 25.50, 5, 2),
(3, 'Monitor 24 Pulgadas', 'Monitor Full HD 75Hz', 180.00, 5, 3),
(5, 'rer', '', 45.00, 5, 1),
(6, 'fdfff', '', 40.00, 45, 1),
(7, 'Adsd', '', 30.00, 10, 3),
(8, 'Audifonos', '', 150.00, 50, 2),
(9, 'Lampara LED', '', 30.00, 15, 5),
(10, 'Wejs', '', 30.00, 20, 7);

-- --------------------------------------------------------

--
-- Table structure for table `proveedores`
--

CREATE TABLE `proveedores` (
  `Id` int(11) NOT NULL,
  `Nombre` varchar(100) NOT NULL,
  `Telefono` varchar(20) DEFAULT NULL,
  `Email` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `proveedores`
--

INSERT INTO `proveedores` (`Id`, `Nombre`, `Telefono`, `Email`) VALUES
(1, 'Adriana', '72191061', 'adrmi.ar@gmail.com'),
(2, 'Maria', '69990990', 'maridb@gmail.com'),
(3, 'Joel', '00000000', 'joelito@gmail.com'),
(5, 'Iconic', '20202021', 'iconic@gmail.com'),
(7, 'AquaTech', '20212025', 'aquatch@gmail.com');

-- --------------------------------------------------------

--
-- Table structure for table `ventas`
--

CREATE TABLE `ventas` (
  `Id` int(11) NOT NULL,
  `Fecha` datetime DEFAULT current_timestamp(),
  `IdProducto` int(11) DEFAULT NULL,
  `CantidadVendida` int(11) NOT NULL,
  `Total` decimal(10,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `ventas`
--

INSERT INTO `ventas` (`Id`, `Fecha`, `IdProducto`, `CantidadVendida`, `Total`) VALUES
(1, '2026-02-17 10:30:00', 1, 2, 91.98),
(2, '2026-02-17 11:15:00', 2, 1, 25.50),
(3, '2026-02-17 12:00:00', 3, 3, 540.00),
(4, '2026-02-18 16:22:51', 1, 2, 91.98),
(5, '2026-02-18 16:30:47', 2, 10, 255.00),
(6, '2026-02-18 16:56:42', 7, 10, 300.00),
(7, '2026-02-18 17:09:40', 8, 50, 7500.00),
(8, '2026-02-18 17:17:07', 2, 20, 510.00),
(9, '2026-02-18 19:22:47', 3, 5, 900.00);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `productos`
--
ALTER TABLE `productos`
  ADD PRIMARY KEY (`Id`),
  ADD KEY `IdProveedor` (`IdProveedor`);

--
-- Indexes for table `proveedores`
--
ALTER TABLE `proveedores`
  ADD PRIMARY KEY (`Id`);

--
-- Indexes for table `ventas`
--
ALTER TABLE `ventas`
  ADD PRIMARY KEY (`Id`),
  ADD KEY `IdProducto` (`IdProducto`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `productos`
--
ALTER TABLE `productos`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `proveedores`
--
ALTER TABLE `proveedores`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `ventas`
--
ALTER TABLE `ventas`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `productos`
--
ALTER TABLE `productos`
  ADD CONSTRAINT `productos_ibfk_1` FOREIGN KEY (`IdProveedor`) REFERENCES `proveedores` (`Id`);

--
-- Constraints for table `ventas`
--
ALTER TABLE `ventas`
  ADD CONSTRAINT `ventas_ibfk_1` FOREIGN KEY (`IdProducto`) REFERENCES `productos` (`Id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
