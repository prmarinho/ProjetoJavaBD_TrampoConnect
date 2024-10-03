-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Oct 01, 2024 at 07:20 AM
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
-- Database: `plataforma_vulnerabilidade`
--

-- --------------------------------------------------------

--
-- Table structure for table `usuarios`
--

CREATE TABLE `usuarios` (
  `id_usuarios` int(255) NOT NULL,
  `nomeCompleto` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `senha` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `usuarios`
--

INSERT INTO `usuarios` (`id_usuarios`, `nomeCompleto`, `email`, `senha`) VALUES
(1, 'João Silva', 'joao.silva@example.com', 'e7d80ffeefa212b7c5c55700e4f7193e'),
(2, 'Stephen Curry', 'StephenCurry@gmail.com', '732002cec7aeb7987bde842b9e00ee3b'),
(3, 'LeBron James', 'LeBron James@gmail.com', '1cd192ca7c4cb07962d762d6e4a79b49'),
(4, 'Ana Santos', 'ana.santos@example.com', 'd55581ff509eab06bf7d8650fd17588c'),
(5, 'Lucas Ferreira', 'lucas.ferreira@example.com', 'ee1f5ff37c6634104997f8545c1e41e8'),
(6, 'Fernanda Lima', 'fernanda.lima@example.com', '732002cec7aeb7987bde842b9e00ee3b'),
(7, 'Bruno Costa', 'bruno.costa@example.com', '613054737e2e829a305e6a57b076008c'),
(8, 'Larissa Almeida', 'larissa.almeida@example.com', '411d908398937e865dde126ad22c1199'),
(9, 'Ricardo Pinto', 'ricardo.pinto@example.com', '2a8e1117db0c974745be6566f877dddc'),
(10, 'Juliana Mello', 'juliana.mello@example.com', '712e46ceeeef720032b0f4f074f4478d'),
(11, 'Gustavo Azevedo', 'gustavo.azevedo@example.com', 'de2c4205a217b200d01e7df8d07aaca0'),
(12, 'Aline Martins', 'aline.martins@example.com', 'a78b1a81d74fec031c3f5e2f310d9855'),
(13, 'Marcos Nunes', 'marcos.nunes@example.com', 'b9413d9ce04d24ce2f832eea35f84108'),
(14, 'Clara Rocha', 'clara.rocha@example.com', 'f9aa19111b8e9d41db4c45afc942179b'),
(15, 'Daniel Mendes', 'daniel.mendes@example.com', 'fd55334bada4cb6131172e218e2e36c4'),
(16, 'Paula Cardoso', 'paula.cardoso@example.com', 'aabfdb97f4b2af22869f78d3149e1d9c'),
(17, 'Renato Borges', 'renato.borges@example.com', '2559568cf2bdc4b2a106a802b6837796'),
(18, 'Camila Teixeira', 'camila.teixeira@example.com', 'aeb1d5bab9a955f4746f75ba5b79124e'),
(19, 'Felipe Barbosa', 'felipe.barbosa@example.com', '674578ce24f1e4d32d125eb450577939'),
(20, 'Bianca Xavier', 'bianca.xavier@example.com', '2ebf36607408752caf99b35d24e1785c'),
(23, 'Nome', 'email@gmail.com', '81dc9bdb52d04dc20036dbd8313ed055');

--
-- Triggers `usuarios`
--
DELIMITER $$
CREATE TRIGGER `before_insert_usuario` BEFORE INSERT ON `usuarios` FOR EACH ROW SET NEW.senha = MD5(NEW.senha)
$$
DELIMITER ;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`id_usuarios`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `usuarios`
--
ALTER TABLE `usuarios`
  MODIFY `id_usuarios` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
