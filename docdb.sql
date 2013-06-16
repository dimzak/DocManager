-- phpMyAdmin SQL Dump
-- version 3.4.10.1deb1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Jun 16, 2013 at 03:01 AM
-- Server version: 5.5.31
-- PHP Version: 5.3.10-1ubuntu3.6

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `docdb`
--

-- --------------------------------------------------------

--
-- Table structure for table `documents`
--

CREATE TABLE IF NOT EXISTS `documents` (
  `doc_id` int(11) NOT NULL AUTO_INCREMENT,
  `content` mediumblob,
  `content_type` varchar(255) DEFAULT NULL,
  `created` date DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `filename` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `priority` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`doc_id`),
  UNIQUE KEY `filename` (`filename`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

--
-- Dumping data for table `documents`
--

-- --------------------------------------------------------

--
-- Table structure for table `flows`
--

CREATE TABLE IF NOT EXISTS `flows` (
  `user_id` int(11) NOT NULL,
  `doc_id` int(11) NOT NULL,
  `flow_id` int(11) NOT NULL AUTO_INCREMENT,
  `line` int(11) NOT NULL,
  PRIMARY KEY (`flow_id`),
  UNIQUE KEY `doc_id` (`doc_id`),
  UNIQUE KEY `user_id` (`user_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=6 ;

--
-- Dumping data for table `flows`
--

INSERT INTO `flows` (`user_id`, `doc_id`, `flow_id`, `line`) VALUES
(1, 4, 5, 0);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE IF NOT EXISTS `users` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `cancreate` bit(1) DEFAULT NULL,
  `firstname` varchar(255) DEFAULT NULL,
  `lastname` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=6 ;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`user_id`, `cancreate`, `firstname`, `lastname`, `password`, `role`, `username`) VALUES
(1, b'0', 'Dimitrios', 'Zakas', 'dimzak', 'ROLE_ADMIN', 'dimzak'),
(2, b'1', 'number', '2', 'number2', 'ROLE_USER', 'number2'),
(3, b'1', 'John', 'Doe', 'JohnDoe', 'ROLE_USER', 'JohnDoe'),
(4, b'0', 'Human', 'Resources', 'hr', 'ROLE_HR', 'hr'),
(5, b'0', 'added by', 'hr', 'user1', 'ROLE_USER', 'user1');

--
-- Constraints for dumped tables
--

--
-- Constraints for table `flows`
--
ALTER TABLE `flows`
  ADD CONSTRAINT `flows_ibfk_3` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `flows_ibfk_4` FOREIGN KEY (`doc_id`) REFERENCES `documents` (`doc_id`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
