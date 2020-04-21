-- phpMyAdmin SQL Dump
-- version 5.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 21, 2020 at 07:34 PM
-- Server version: 10.4.11-MariaDB
-- PHP Version: 7.4.3

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
  `Supplier` varchar(30) NOT NULL,
  `Type` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `components`
--

INSERT INTO `components` (`Name`, `Model Number`, `Price`, `Quantity`, `Supplier`, `Type`) VALUES
('GTX 2080', '2080114620', '1000.00', 60, '', 'GPU'),
('GTX 2070', '2070114620', '700.00', 8, '', 'GPU'),
('Intel Core i5 4690K', '2080114690', '200.00', 25, '', 'CPU'),
('Intel Core i7 4770K', '2080114770', '300.00', 5, '', 'CPU'),
('test', '101010', '500.00', 3, '', 'CPU'),
('test', '101010', '500.00', 3, '', 'CPU'),
('test', '11111111', '12', 2, '', 'Storage'),
('test', '11111111', '12', 2, '', 'Storage'),
('test', '11111111', '12', 2, '', 'Storage'),
('test', '11111111', '12', 2, '', 'Storage'),
('Test', '10101010', '100', 1, '', 'CPU'),
('Test', '10101010', '100', 1, '', 'CPU'),
('Test', '10101010', '100', 1, '', 'GPU'),
('Test', '10101010', '100', 1, '', 'MotherBoard'),
('Test', '10101010', '100', 1, '', 'Storage'),
('Test', '10101010', '100', 1, '', 'PSU'),
('Test', '10101010', '100', 1, '', 'CPU'),
('newTest', '123456', '250', 2, '', 'CPU'),
('neil', '2020', '500', 1, '', 'CPU');

-- --------------------------------------------------------

--
-- Table structure for table `customers`
--

CREATE TABLE `customers` (
  `CustomerID` int(3) NOT NULL,
  `Name` varchar(30) NOT NULL,
  `Surname` varchar(30) NOT NULL,
  `Phone` varchar(12) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `customers`
--

INSERT INTO `customers` (`CustomerID`, `Name`, `Surname`, `Phone`) VALUES
(3, 'Neil', 'Test', '016102000'),
(8, 'Neil', 'Young', '0857821128'),
(9, 'John', 'Smith', '0858256133');

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
('C11421156', 'test1', '9092229A'),
('C16461146', 'test', '9092227R');

-- --------------------------------------------------------

--
-- Table structure for table `employees`
--

CREATE TABLE `employees` (
  `ppsn` varchar(15) NOT NULL,
  `name` varchar(30) NOT NULL,
  `surname` varchar(30) NOT NULL,
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

-- --------------------------------------------------------

--
-- Table structure for table `purchases`
--

CREATE TABLE `purchases` (
  `PurchaseID` int(11) NOT NULL,
  `FK_customerID` int(11) NOT NULL,
  `PurchaseAmount` varchar(10) NOT NULL,
  `RefundStatus` varchar(3) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `purchases`
--

INSERT INTO `purchases` (`PurchaseID`, `FK_customerID`, `PurchaseAmount`, `RefundStatus`) VALUES
(3, 3, '300', 'No'),
(8, 8, '500', 'No');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `customers`
--
ALTER TABLE `customers`
  ADD PRIMARY KEY (`CustomerID`),
  ADD KEY `FK for CustomerID` (`CustomerID`);

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
-- Indexes for table `purchases`
--
ALTER TABLE `purchases`
  ADD PRIMARY KEY (`PurchaseID`),
  ADD KEY `FK for CustomerID` (`FK_customerID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `customers`
--
ALTER TABLE `customers`
  MODIFY `CustomerID` int(3) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `purchases`
--
ALTER TABLE `purchases`
  MODIFY `PurchaseID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `employee login`
--
ALTER TABLE `employee login`
  ADD CONSTRAINT `employee login_ibfk_1` FOREIGN KEY (`ppsn_FK`) REFERENCES `employees` (`ppsn`);

--
-- Constraints for table `purchases`
--
ALTER TABLE `purchases`
  ADD CONSTRAINT `Constaint for CustomerID` FOREIGN KEY (`PurchaseID`) REFERENCES `customers` (`CustomerID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
