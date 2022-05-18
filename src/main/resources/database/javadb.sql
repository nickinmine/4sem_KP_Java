-- phpMyAdmin SQL Dump
-- version 5.1.3
-- https://www.phpmyadmin.net/
--
-- Хост: localhost
-- Время создания: Май 03 2022 г., 17:27
-- Версия сервера: 8.0.11
-- Версия PHP: 7.4.28

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- База данных: `javadb`
--

-- --------------------------------------------------------

--
-- Структура таблицы `car`
--

CREATE TABLE `car` (
  `id` bigint(20) NOT NULL,
  `count` int(11) NOT NULL,
  `client_id` int(11) DEFAULT NULL,
  `goods_id` int(11) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Дамп данных таблицы `car`
--

INSERT INTO `car` (`id`, `count`, `client_id`, `goods_id`) VALUES
(1, 2, 1, 4),
(2, 2, 2, 6),
(3, 1, 1, 1),
(4, 2, 1, 1);

-- --------------------------------------------------------

--
-- Структура таблицы `client`
--

CREATE TABLE `client` (
  `id` int(11) NOT NULL,
  `email` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `pass` varchar(255) NOT NULL,
  `role` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `address` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Дамп данных таблицы `client`
--

INSERT INTO `client` (`id`, `email`, `name`, `pass`, `role`, `address`) VALUES
(1, 'test.email-change.root@root', 'root', '$2a$10$3u3ML2IgyAvEzKOAvRe.QefMQ.4wUAU8quQxvWmUAf/FBsPr6ljUC', 'admin', 'улица Пушкина, дом Колотушкина, квартира Петрова'),
(2, 'usertest@user.user', 'user', '$2a$10$oZtFJfS49Vie8Wk9uDldcu1cEoYQGR3.3bT6/hM72lecwDTwRGRHK', 'user', NULL),
(3, 'root@root', 'root2', '$2a$10$yqT51smjumGrRACYWoP/DONqjvnp3jWlJExFDAkj1CAlKTBhbl5q.', 'admin', NULL),
(4, 'name@name', 'name', '$2a$10$wW/gcozSIOsJpLLSBaagL.2Qy5mpNeceM/mOY5n9zWcO/VEhjVSkq', 'user', NULL),
(5, 'name2@name', 'name2', '$2a$10$P.UAtTHhMRF5qLecwIFIB.B9AMUSh9xBp.2lvu/oAU3hHK/0WjDzW', 'user', NULL),
(6, 'mamer@ma', 'namer', '$2a$10$zTVXhzlO/JEXh9UNUUO5ZuF/GhobDA9FUThzPuqBHuBUkIxQ3OFNi', 'user', NULL),
(7, 'imya@imya', 'imya', '$2a$10$u5YlsjdQcPHTvaQGFHmiieTj2Fntm9jbFaOq04HcmYRwy2VuDc51u', 'user', 'imya imya imya'),
(8, 'new@new', 'new', '$2a$10$usFrKtL.ynvJYXkERqGmtenGI8uwOG.qX9YHbJBES0wVXhyEVIvSW', 'user', 'new new');

-- --------------------------------------------------------

--
-- Структура таблицы `goods`
--

CREATE TABLE `goods` (
  `id` int(11) NOT NULL,
  `count` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `price` float NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Дамп данных таблицы `goods`
--

INSERT INTO `goods` (`id`, `count`, `name`, `price`) VALUES
(1, 47, 'Краска алкидная красная', 200),
(2, 50, 'Краска алкидная синяя', 200),
(3, 50, 'Краска алкидная жёлтая', 200),
(4, 50, 'Краска алкидная зелёная', 200),
(5, 50, 'Краска алкидная белая', 200),
(6, 50, 'Краска алкидная чёрная', 200),
(7, 10, 'Кирпич строительный (паллет)', 6000),
(8, 10, 'Кирпич облицовочный (паллет)', 6000),
(9, 10, 'Газобетон (паллет)', 10500),
(10, 500, 'Мешок для мусора', 10),
(11, 500, 'Перчатки строительные', 20),
(12, 200, 'Малярная лента 20м', 50),
(13, 200, 'Скотч армированный 20м', 70),
(14, 50, 'Профнастил 1 лист', 350),
(15, 120, 'Гибкая черепица 1х1 м', 200),
(16, 20, 'Гидроизоляция кровельная 10х1 м', 685),
(17, 300, 'Цемент 50 кг', 310),
(18, 70, 'Клей плиточный 5 кг', 108),
(19, 40, 'Затирка плиточная 2 кг белая', 285),
(20, 40, 'Затирка плиточная 2 кг коричневая', 285),
(21, 30, 'Шпатлёвка гипсовая 5 кг', 112),
(22, 40, 'Шпатлёвка полимерная 3 кг', 87),
(23, 15, 'Штукатурка фасадная 25 кг', 275),
(24, 20, 'Штукатурка гипсовая 30 кг', 310),
(25, 5, 'Штукатурка декоративная 25 кг', 480),
(26, 75, 'Алебастр (гипс) 1 кг', 30),
(27, 10, 'Песок строительный 60 кг', 120),
(28, 130, 'Минеральная вата 2х1 м 5 шт', 2000),
(29, 34, 'Битум 5 кг', 100),
(30, 2, 'Геотекстиль садовый 2х22 м', 670),
(31, 32, 'Укрывочный материал 5х5 м', 200),
(32, 35, 'Гипсокартон 2х3 м', 105),
(33, 20, 'Грунтовка 18 кг', 1100),
(34, 186, 'Растворитель-646 1 л', 153),
(35, 420, 'Обезжириватель 0,5 л', 83),
(36, 256, 'Монтажная пена 1 л', 780),
(37, 34, 'Герметик водостойкий 0,2 л', 124),
(38, 45, 'Гофрированная труба для электропроводки 50 м', 227),
(39, 5000, 'Саморезы 0,1 кг', 20),
(40, 5000, 'Дюбели 0,1 кг', 20),
(41, 5000, 'Гвозди 0,1 кг', 20),
(42, 300, 'Шпингалет дверной', 20),
(43, 300, 'Проушина дверная', 40);

--
-- Индексы сохранённых таблиц
--

--
-- Индексы таблицы `car`
--
ALTER TABLE `car`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKqudpn3oci54oqa8wgn4wdo7ab` (`client_id`),
  ADD KEY `FK2c082yyi27in7dlwanuo1r4nt` (`goods_id`);

--
-- Индексы таблицы `client`
--
ALTER TABLE `client`
  ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `goods`
--
ALTER TABLE `goods`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT для сохранённых таблиц
--

--
-- AUTO_INCREMENT для таблицы `car`
--
ALTER TABLE `car`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT для таблицы `client`
--
ALTER TABLE `client`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT для таблицы `goods`
--
ALTER TABLE `goods`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=46;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
