-- phpMyAdmin SQL Dump
-- version 4.2.5
-- http://www.phpmyadmin.net
--
-- Host: localhost:8889
-- Generation Time: Apr 08, 2015 at 09:01 PM
-- Server version: 5.5.38
-- PHP Version: 5.5.14

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";

--
-- Database: `is2731`
--

-- --------------------------------------------------------

--
-- Table structure for table `message`
--

CREATE TABLE `message` (
`mid` int(11) NOT NULL,
  `sender` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `receiver` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `time` datetime NOT NULL,
  `content` text COLLATE utf8_unicode_ci NOT NULL,
  `is_read` int(11) NOT NULL DEFAULT '0'
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=12 ;

--
-- Dumping data for table `message`
--

INSERT INTO `message` (`mid`, `sender`, `receiver`, `time`, `content`, `is_read`) VALUES
(1, 'dog', 'cat', '2015-04-07 19:59:59', 'dooogy', 1),
(5, 'dog', 'kitty', '2015-04-06 20:35:54', 'hello kitty\r\nhow are you!!!\r\nnice', 1),
(6, 'dog', 'cat', '2015-04-07 21:33:41', 'nice job !', 1),
(7, 'dog', 'cat', '2015-04-07 21:33:35', '1111qqqqqq1111qqqqqq1111qq111qqqqqq1111qqqqqq1111qqqqqq1111qqqqqq', 0),
(8, 'dog', 'kitty', '2015-04-07 21:33:24', 'hi  ', 1),
(9, 'dog', 'cat', '2015-04-07 20:47:07', 'test test test', 1),
(10, 'dog', 'cat', '2015-04-07 21:01:13', 'how do you feel?', 1),
(11, 'dog', 'cat', '2015-04-07 21:33:08', 'hhhhhhaaaaaa', 1);

-- --------------------------------------------------------

--
-- Table structure for table `roles`
--

CREATE TABLE `roles` (
  `role_name` varchar(50) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `roles`
--

INSERT INTO `roles` (`role_name`) VALUES
('admin'),
('client');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `user_name` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `hashed_password` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `email` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `hashed_answer` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `is_activated` int(11) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`user_name`, `hashed_password`, `email`, `hashed_answer`, `is_activated`) VALUES
('cat', '77af778b51abd4a3c51c5ddd97204a9c3ae614ebccb75a606c3b6865aed6744e', 'cat@cat.com', '77af778b51abd4a3c51c5ddd97204a9c3ae614ebccb75a606c3b6865aed6744e', 0),
('dog', 'cd6357efdd966de8c0cb2f876cc89ec74ce35f0968e11743987084bd42fb8944', 'dog@dog.com', 'cd6357efdd966de8c0cb2f876cc89ec74ce35f0968e11743987084bd42fb8944', 1),
('kitty', '67731ff58137eb39713ae30eba33c54c8c1d5418e081428ca815e4e733d64f6d', 'kitty@kitty.com', '67731ff58137eb39713ae30eba33c54c8c1d5418e081428ca815e4e733d64f6d', 0);

-- --------------------------------------------------------

--
-- Table structure for table `users_roles`
--

CREATE TABLE `users_roles` (
  `user_name` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `role_name` varchar(50) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `users_roles`
--

INSERT INTO `users_roles` (`user_name`, `role_name`) VALUES
('dog', 'admin'),
('cat', 'client'),
('kitty', 'client');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `message`
--
ALTER TABLE `message`
 ADD PRIMARY KEY (`mid`), ADD KEY `from_idx` (`sender`), ADD KEY `to_idx` (`receiver`);

--
-- Indexes for table `roles`
--
ALTER TABLE `roles`
 ADD PRIMARY KEY (`role_name`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
 ADD PRIMARY KEY (`user_name`);

--
-- Indexes for table `users_roles`
--
ALTER TABLE `users_roles`
 ADD PRIMARY KEY (`user_name`,`role_name`), ADD KEY `rolename_idx` (`role_name`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `message`
--
ALTER TABLE `message`
MODIFY `mid` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=12;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `message`
--
ALTER TABLE `message`
ADD CONSTRAINT `from` FOREIGN KEY (`sender`) REFERENCES `users` (`user_name`) ON DELETE CASCADE ON UPDATE CASCADE,
ADD CONSTRAINT `to` FOREIGN KEY (`receiver`) REFERENCES `users` (`user_name`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `users_roles`
--
ALTER TABLE `users_roles`
ADD CONSTRAINT `rolename` FOREIGN KEY (`role_name`) REFERENCES `roles` (`role_name`) ON DELETE CASCADE ON UPDATE CASCADE,
ADD CONSTRAINT `username` FOREIGN KEY (`user_name`) REFERENCES `users` (`user_name`) ON DELETE CASCADE ON UPDATE CASCADE;
