-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 05, 2019 at 05:27 AM
-- Server version: 10.1.38-MariaDB
-- PHP Version: 7.3.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `hotel_database`
--

-- --------------------------------------------------------

--
-- Table structure for table `credentials`
--

CREATE TABLE `credentials` (
  `ID_User` int(11) NOT NULL,
  `Username` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `Password` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `Account_Type` varchar(6) COLLATE utf8_unicode_ci NOT NULL,
  `Name` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `Address` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `Phone` varchar(16) COLLATE utf8_unicode_ci DEFAULT NULL,
  `Description` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `credentials`
--

INSERT INTO `credentials` (`ID_User`, `Username`, `Password`, `Account_Type`, `Name`, `Address`, `Phone`, `Description`) VALUES
(8, 'testh', 'test', 'Hotel', 'Hotel Testowy', 'Testowa 12 Wroc&#322;aw', '666666696', ''),
(9, 'testc', 'test', 'Client', 'Marek Chmieelwski', 'Wyspia&#324;skiego 23', '123456123', ''),
(10, 'testh2', 'test', 'Hotel', 'testowy2', 'testowa 87', '888999111', 'nie');

-- --------------------------------------------------------

--
-- Table structure for table `reservations`
--

CREATE TABLE `reservations` (
  `Reservation_id` int(8) NOT NULL,
  `Hotel_Id` int(11) DEFAULT NULL,
  `Client_Id` int(11) DEFAULT NULL,
  `Date_Start` date DEFAULT NULL,
  `Date_End` date NOT NULL,
  `People` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `reservations`
--

INSERT INTO `reservations` (`Reservation_id`, `Hotel_Id`, `Client_Id`, `Date_Start`, `Date_End`, `People`) VALUES
(1, 8, 8, '1996-03-23', '1996-03-23', 123),
(2, 8, 8, '1996-03-23', '1996-03-23', 23),
(4, 8, 9, '1996-03-23', '1996-03-23', 123),
(10, 8, 9, '1000-10-10', '1010-10-10', 6),
(12, 10, 9, '2001-01-01', '2001-01-04', 4);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `credentials`
--
ALTER TABLE `credentials`
  ADD PRIMARY KEY (`ID_User`);

--
-- Indexes for table `reservations`
--
ALTER TABLE `reservations`
  ADD PRIMARY KEY (`Reservation_id`),
  ADD KEY `Hotel_Id` (`Hotel_Id`),
  ADD KEY `Client_Id` (`Client_Id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `credentials`
--
ALTER TABLE `credentials`
  MODIFY `ID_User` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `reservations`
--
ALTER TABLE `reservations`
  MODIFY `Reservation_id` int(8) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `reservations`
--
ALTER TABLE `reservations`
  ADD CONSTRAINT `reservations_ibfk_2` FOREIGN KEY (`Client_Id`) REFERENCES `credentials` (`ID_User`),
  ADD CONSTRAINT `reservations_ibfk_3` FOREIGN KEY (`Hotel_Id`) REFERENCES `credentials` (`ID_User`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
