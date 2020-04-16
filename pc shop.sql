-- phpMyAdmin SQL Dump
-- version 4.7.9
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 16, 2020 at 03:01 AM
-- Server version: 10.1.31-MariaDB
-- PHP Version: 7.2.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `pc shop`
--

-- --------------------------------------------------------

--
-- Table structure for table `components`
--

CREATE TABLE `components` (
  `Name` varchar(40) NOT NULL,
  `Model Number` varchar(10) NOT NULL,
  `Price` varchar(10) NOT NULL,
  `Quantity` int(5) NOT NULL,
  `Type` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `components`
--

INSERT INTO `components` (`Name`, `Model Number`, `Price`, `Quantity`, `Type`) VALUES
('GTX 2080', '2080114620', '1000.00', 6, 'GPU'),
('GTX 2070', '2070114620', '700.00', 9, 'GPU'),
('Intel i-7', '9700k', '600', 3, 'CPU');

-- --------------------------------------------------------

--
-- Table structure for table `employee login`
--

CREATE TABLE `employee login` (
  `ID` varchar(10) NOT NULL,
  `password` varchar(20) NOT NULL,
  `ppsn_FK` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `employee login`
--

INSERT INTO `employee login` (`ID`, `password`, `ppsn_FK`) VALUES
('C11421156', 'test', '9092229A'),
('C16461146', 'test', '9092227R');

-- --------------------------------------------------------

--
-- Table structure for table `employees`
--

CREATE TABLE `employees` (
  `ppsn` varchar(15) NOT NULL,
  `name` varchar(200) NOT NULL,
  `surname` varchar(200) NOT NULL,
  `dob` varchar(10) NOT NULL,
  `salary` varchar(20) NOT NULL,
  `manager status` varchar(3) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `employees`
--

INSERT INTO `employees` (`ppsn`, `name`, `surname`, `dob`, `salary`, `manager status`) VALUES
('9092227R', 'Neil', 'Young', '15-04-99', '40,000', 'Yes'),
('9092229A', 'John', 'Smith', '01-01-20', '30,000', 'No'),
('9097888B', 'Karl', 'Smith', '05-12-92', '40,000', 'No');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `employee login`
--
ALTER TABLE `employee login`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `ppsnFK` (`ppsn_FK`);

--
-- Indexes for table `employees`
--
ALTER TABLE `employees`
  ADD PRIMARY KEY (`ppsn`(8)),
  ADD KEY `Constaint for PPSN` (`ppsn`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `employee login`
--
ALTER TABLE `employee login`
  ADD CONSTRAINT `employee login_ibfk_1` FOREIGN KEY (`ppsn_FK`) REFERENCES `employees` (`ppsn`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
