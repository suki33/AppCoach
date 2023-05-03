-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le : mar. 04 avr. 2023 à 16:33
-- Version du serveur :  5.7.31
-- Version de PHP : 7.4.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : coach
--

-- --------------------------------------------------------

--
-- Structure de la table profil
--

DROP TABLE IF EXISTS profil;
CREATE TABLE profil (
  datemesure datetime NOT NULL,
  poids int(3) NOT NULL,
  taille int(3) NOT NULL,
  age int(3) NOT NULL,
  sexe int(1) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table profil
--

INSERT INTO profil (datemesure, poids, taille, age, sexe) VALUES
('2023-01-29 19:06:58', 160, 70, 20, 1),
('2023-03-27 15:58:26', 150, 70, 20, 1),
('2023-03-27 15:57:17', 150, 70, 20, 1),
('2023-03-27 15:56:52', 160, 70, 20, 1),
('2023-03-27 02:07:14', 50, 168, 20, 0),
('2023-03-27 02:15:51', 150, 70, 20, 1);

--
-- Index pour les tables déchargées
--

--
-- Index pour la table profil
--
ALTER TABLE profil
  ADD PRIMARY KEY (datemesure);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
