-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Tempo de geração: 29/09/2024 às 08:13
-- Versão do servidor: 10.4.32-MariaDB
-- Versão do PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Banco de dados: `plataforma_vulnerabilidade`
--

-- --------------------------------------------------------

--
-- Estrutura para tabela `candidaturas`
--

CREATE TABLE `candidaturas` (
  `id_candidaturas` int(255) NOT NULL,
  `id_vagas` int(255) NOT NULL,
  `id_perfis_candidatos` int(255) NOT NULL,
  `data` date NOT NULL,
  `status` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `candidaturas`
--

INSERT INTO `candidaturas` (`id_candidaturas`, `id_vagas`, `id_perfis_candidatos`, `data`, `status`) VALUES
(1, 1, 1, '2024-01-15', 'Pendente'),
(2, 2, 2, '2024-01-20', 'Aceito'),
(3, 3, 3, '2024-01-22', 'Rejeitado'),
(4, 4, 4, '2024-02-25', 'Pendente'),
(5, 5, 5, '2024-02-05', 'Aceito'),
(6, 6, 6, '2024-02-10', 'Pendente'),
(7, 7, 7, '2024-02-15', 'Rejeitado'),
(8, 8, 8, '2024-02-20', 'Aceito'),
(9, 9, 9, '2024-03-01', 'Pendente'),
(10, 10, 10, '2024-03-10', 'Aceito'),
(11, 1, 11, '2024-03-15', 'Pendente'),
(12, 2, 12, '2024-03-20', 'Aceito'),
(13, 3, 13, '2024-03-25', 'Rejeitado'),
(14, 4, 14, '2024-04-01', 'Aceito'),
(15, 5, 15, '2024-04-05', 'Pendente'),
(16, 6, 16, '2024-04-10', 'Aceito'),
(17, 7, 17, '2024-04-15', 'Rejeitado'),
(18, 8, 18, '2024-04-20', 'Pendente'),
(19, 9, 19, '2024-05-01', 'Aceito'),
(20, 10, 20, '2024-05-10', 'Pendente'),
(21, 1, 1, '2024-05-15', 'Rejeitado'),
(22, 2, 2, '2024-05-20', 'Aceito'),
(23, 3, 3, '2024-05-25', 'Pendente'),
(24, 4, 4, '2024-06-01', 'Aceito'),
(25, 5, 5, '2024-06-05', 'Rejeitado'),
(26, 6, 6, '2024-06-10', 'Aceito'),
(27, 7, 7, '2024-06-15', 'Pendente'),
(28, 8, 8, '2024-06-20', 'Aceito'),
(29, 9, 9, '2024-06-25', 'Pendente'),
(30, 10, 10, '2024-07-01', 'Aceito');

--
-- Acionadores `candidaturas`
--
DELIMITER $$
CREATE TRIGGER `after_update_candidatura` AFTER UPDATE ON `candidaturas` FOR EACH ROW INSERT INTO historico_atividades (id_usuarios, descricao, data) 
VALUES (NEW.id_perfis_candidatos, 'Candidatura atualizada', NOW())
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Estrutura para tabela `empregadores`
--

CREATE TABLE `empregadores` (
  `id_empregadores` int(255) NOT NULL,
  `empresa` varchar(100) NOT NULL,
  `cnpj` varchar(14) NOT NULL,
  `email` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `empregadores`
--

INSERT INTO `empregadores` (`id_empregadores`, `empresa`, `cnpj`, `email`) VALUES
(1, 'Tech Solutions Ltda', '12345678000101', 'contact@techsolutions.com'),
(2, 'MegaSoft Corp', '22345678000102', 'hr@megasoft.com'),
(3, 'AgroTech SA', '32345678000103', 'jobs@agrotech.com'),
(4, 'WebDesign Pro', '42345678000104', 'careers@webdesignpro.com'),
(5, 'DataSecurity Inc.', '52345678000105', 'info@datasecurity.com'),
(6, 'EcoBuild Ltda', '62345678000106', 'eco@ecobuild.com'),
(7, 'FinanceCo SA', '72345678000107', 'finance@financeco.com'),
(8, 'HealthCare Solutions', '82345678000108', 'hr@healthcaresolutions.com'),
(9, 'EduTech Ltda', '92345678000109', 'info@edutech.com'),
(10, 'RetailPro SA', '10345678000110', 'jobs@retailpro.com');

-- --------------------------------------------------------

--
-- Estrutura para tabela `eventos`
--

CREATE TABLE `eventos` (
  `id_eventos` int(255) NOT NULL,
  `nome` varchar(100) NOT NULL,
  `descricao` varchar(255) NOT NULL,
  `data` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `eventos`
--

INSERT INTO `eventos` (`id_eventos`, `nome`, `descricao`, `data`) VALUES
(1, 'Workshop de Front-End', 'Evento sobre tendências de desenvolvimento web', '2024-03-15'),
(2, 'Seminário de Inclusão Digital', 'Discussão sobre inclusão de minorias no setor de TI', '2024-04-01'),
(3, 'Encontro de Startups Sociais', 'Evento voltado para o empreendedorismo social', '2024-05-05'),
(4, 'Workshop de Soft Skills', 'Desenvolvimento de habilidades interpessoais', '2024-01-20'),
(5, 'Palestra Mulheres na Tecnologia', 'Evento para incentivar mulheres no setor de TI', '2024-02-10'),
(6, 'Seminário de Inovação e Tecnologia', 'Discussão sobre novas tecnologias no mercado', '2024-03-25'),
(7, 'Encontro de Empreendedores', 'Discussão sobre inovação e startups', '2024-04-20'),
(8, 'Workshop de Marketing Digital', 'Capacitação em estratégias de marketing', '2024-01-30'),
(9, 'Palestra de Liderança', 'Como desenvolver habilidades de liderança', '2024-02-15'),
(10, 'Seminário de Programação Web', 'Técnicas modernas de desenvolvimento web', '2024-03-05'),
(11, 'Conferência de Data Science', 'Discussão sobre o impacto da ciência de dados', '2024-04-15'),
(12, 'Workshop de Negócios Sociais', 'Capacitação para empreendedores sociais', '2024-05-01'),
(13, 'Palestra sobre Economia Digital', 'Discussão sobre o impacto da economia digital', '2024-02-25'),
(14, 'Seminário de Inteligência Artificial', 'Inovações em IA e automação', '2024-03-15'),
(15, 'Workshop de Gestão de Projetos', 'Capacitação para líderes de projetos', '2024-04-10');

-- --------------------------------------------------------

--
-- Estrutura para tabela `feedback`
--

CREATE TABLE `feedback` (
  `id_feedback` int(255) NOT NULL,
  `id_vagas` int(255) NOT NULL,
  `id_perfis_candidatos` int(255) NOT NULL,
  `feedback` varchar(255) NOT NULL,
  `data` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `feedback`
--

INSERT INTO `feedback` (`id_feedback`, `id_vagas`, `id_perfis_candidatos`, `feedback`, `data`) VALUES
(1, 1, 1, 'Ótimo processo seletivo, muito organizado.', '2024-05-01'),
(2, 2, 2, 'Gostei das etapas, mas faltou mais informações sobre a vaga.', '2024-05-15'),
(3, 3, 3, 'Processo foi rápido e eficiente.', '2024-06-01'),
(4, 4, 4, 'Houve muita demora para retorno.', '2024-06-20'),
(5, 5, 5, 'Tudo correu bem, recomendo.', '2024-07-10'),
(6, 6, 6, 'Faltou clareza nos critérios de seleção.', '2024-07-25'),
(7, 7, 7, 'Bom atendimento e feedback claro.', '2024-08-05'),
(8, 8, 8, 'Processo seletivo tranquilo, bem organizado.', '2024-09-01'),
(9, 9, 9, 'Demora no retorno, mas vaga bem descrita.', '2024-10-01'),
(10, 10, 10, 'Gostei do processo, bem direto e claro.', '2024-11-10');

-- --------------------------------------------------------

--
-- Estrutura para tabela `historico_atividades`
--

CREATE TABLE `historico_atividades` (
  `id_historico_atividades` int(255) NOT NULL,
  `id_usuarios` int(255) NOT NULL,
  `descricao` varchar(255) NOT NULL,
  `data` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `historico_atividades`
--

INSERT INTO `historico_atividades` (`id_historico_atividades`, `id_usuarios`, `descricao`, `data`) VALUES
(1, 1, 'Atualização de perfil', '2024-02-01'),
(2, 2, 'Candidatura à vaga de Desenvolvedor Back-End', '2024-02-10'),
(3, 3, 'Participação no Programa de Capacitação em TI', '2023-02-15'),
(4, 4, 'Candidatura à vaga de Desenvolvedor Mobile', '2024-03-05'),
(5, 5, 'Atualização de currículo', '2024-03-10'),
(6, 6, 'Participação no Workshop de Gestão Financeira', '2024-04-01'),
(7, 7, 'Candidatura à vaga de Engenheiro de Qualidade', '2024-04-20'),
(8, 8, 'Participação no Programa Jovem Aprendiz', '2024-05-05'),
(9, 9, 'Candidatura à vaga de Cientista de Dados', '2024-06-01'),
(10, 10, 'Participação no Curso de Desenvolvimento Mobile', '2024-06-15'),
(11, 11, 'Feedback sobre a vaga de Desenvolvedor Front-End', '2024-07-05'),
(12, 12, 'Candidatura à vaga de Gerente de Projetos', '2024-08-10'),
(13, 13, 'Participação no Programa Mulheres na Tecnologia', '2024-09-01'),
(14, 14, 'Candidatura à vaga de Engenheiro DevOps', '2024-10-05'),
(15, 15, 'Atualização de perfil e habilidades', '2024-11-01'),
(16, 16, 'Participação no Treinamento em Data Science', '2024-11-15'),
(17, 17, 'Feedback sobre a vaga de Desenvolvedor PHP', '2024-12-05'),
(18, 18, 'Candidatura à vaga de Administrador de Redes', '2024-12-10'),
(19, 19, 'Participação no Treinamento em Qualidade de Software', '2024-12-20'),
(20, 20, 'Candidatura à vaga de Engenheiro de Software', '2024-12-25');

-- --------------------------------------------------------

--
-- Estrutura para tabela `participacao_eventos`
--

CREATE TABLE `participacao_eventos` (
  `id_participacao_eventos` int(255) NOT NULL,
  `id_perfis_candidatos` int(255) NOT NULL,
  `id_eventos` int(255) NOT NULL,
  `data` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `participacao_eventos`
--

INSERT INTO `participacao_eventos` (`id_participacao_eventos`, `id_perfis_candidatos`, `id_eventos`, `data`) VALUES
(1, 1, 3, '2024-03-16'),
(2, 2, 5, '2024-02-10'),
(3, 3, 8, '2024-01-30'),
(4, 4, 11, '2024-04-15'),
(5, 5, 2, '2024-04-02'),
(6, 6, 10, '2024-03-06'),
(7, 7, 4, '2024-01-21'),
(8, 8, 12, '2024-05-02'),
(9, 9, 6, '2024-03-26'),
(10, 10, 1, '2024-03-16'),
(11, 11, 13, '2024-02-26'),
(12, 12, 7, '2024-04-21'),
(13, 13, 14, '2024-03-16'),
(14, 14, 9, '2024-02-16'),
(15, 15, 15, '2024-04-11'),
(16, 16, 8, '2024-01-31'),
(17, 17, 5, '2024-02-11'),
(18, 18, 6, '2024-03-27'),
(19, 19, 7, '2024-04-22'),
(20, 20, 11, '2024-04-16');

-- --------------------------------------------------------

--
-- Estrutura para tabela `participacoes`
--

CREATE TABLE `participacoes` (
  `id_participacoes` int(255) NOT NULL,
  `id_perfis_candidatos` int(255) NOT NULL,
  `id_programas` int(255) NOT NULL,
  `data` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `participacoes`
--

INSERT INTO `participacoes` (`id_participacoes`, `id_perfis_candidatos`, `id_programas`, `data`) VALUES
(1, 1, 1, '2024-01-15'),
(2, 2, 2, '2024-02-20'),
(3, 3, 3, '2024-03-05'),
(4, 4, 4, '2024-04-15'),
(5, 5, 5, '2024-05-10'),
(6, 6, 6, '2024-06-15'),
(7, 7, 7, '2024-07-20'),
(8, 8, 8, '2024-08-05'),
(9, 9, 9, '2024-09-10'),
(10, 10, 10, '2024-10-15'),
(11, 11, 11, '2024-10-25'),
(12, 12, 12, '2024-11-05'),
(13, 13, 13, '2024-12-15'),
(14, 14, 14, '2024-12-20'),
(15, 15, 15, '2024-12-25');

-- --------------------------------------------------------

--
-- Estrutura para tabela `perfis_candidatos`
--

CREATE TABLE `perfis_candidatos` (
  `id_perfis_candidatos` int(255) NOT NULL,
  `id_usuarios` int(255) NOT NULL,
  `habilidade` varchar(100) NOT NULL,
  `experiencia` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `perfis_candidatos`
--

INSERT INTO `perfis_candidatos` (`id_perfis_candidatos`, `id_usuarios`, `habilidade`, `experiencia`) VALUES
(1, 1, 'HTML, CSS, Javascript', '3 anos como Desenvolvedor Front-End'),
(2, 2, 'Python, Django', '2 anos como Desenvolvedor Back-End'),
(3, 3, 'Banco de Dados, MySQL', '5 anos como Administrador de Banco de Dados'),
(4, 4, 'Java, Spring Boot', '4 anos como Desenvolvedor Java'),
(5, 5, 'Flutter, Dart', '1 ano como Desenvolvedor Mobile'),
(6, 6, 'React, Node.js', '2 anos como Desenvolvedor Full Stack'),
(7, 7, 'PHP, Laravel', '3 anos como Desenvolvedor PHP'),
(8, 8, 'Angular, Typescript', '2 anos como Desenvolvedor Front-End'),
(9, 9, 'C#, .NET', '5 anos como Desenvolvedor Backend'),
(10, 10, 'AWS, DevOps', '3 anos como Engenheiro DevOps'),
(11, 11, 'UI/UX Design', '4 anos como Designer de Interfaces'),
(12, 12, 'SEO, Marketing Digital', '3 anos em Marketing'),
(13, 13, 'Project Management, Agile', '4 anos como Gerente de Projetos'),
(14, 14, 'Machine Learning, Python', '2 anos como Cientista de Dados'),
(15, 15, 'Game Development, Unity', '3 anos como Desenvolvedor de Jogos'),
(16, 16, 'QA Testing, Selenium', '4 anos como Engenheiro de Qualidade'),
(17, 17, 'Scrum Master, Agile Coach', '5 anos em Metodologias Ágeis'),
(18, 18, 'Vendas, CRM', '3 anos em Vendas e Atendimento ao Cliente'),
(19, 19, 'Gestão Financeira, ERP', '5 anos como Analista Financeiro'),
(20, 20, 'Social Media, Content Creation', '3 anos como Criador de Conteúdo');

-- --------------------------------------------------------

--
-- Estrutura para tabela `programas`
--

CREATE TABLE `programas` (
  `id_programas` int(255) NOT NULL,
  `programa` varchar(100) NOT NULL,
  `descricao` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `programas`
--

INSERT INTO `programas` (`id_programas`, `programa`, `descricao`) VALUES
(1, 'Programa de Capacitação em TI', 'Treinamento intensivo para desenvolvedores em situação de vulnerabilidade.'),
(2, 'Programa de Inclusão Digital', 'Curso de informática básica para jovens.'),
(3, 'Capacitação em Marketing Digital', 'Curso completo sobre marketing digital para pequenas empresas.'),
(4, 'Formação em DevOps', 'Treinamento especializado em práticas de DevOps.'),
(5, 'Programa Jovem Aprendiz', 'Capacitação e inserção de jovens no mercado de trabalho.'),
(6, 'Curso de UI/UX Design', 'Curso para aprender a criar interfaces intuitivas.'),
(7, 'Workshop de Gestão Financeira', 'Oficinas práticas sobre controle financeiro.'),
(8, 'Treinamento em Data Science', 'Capacitação em análise de dados e machine learning.'),
(9, 'Programa Mulheres na Tecnologia', 'Treinamento voltado para inclusão feminina na TI.'),
(10, 'Capacitação em Vendas', 'Curso sobre técnicas de vendas e CRM.'),
(11, 'Curso de Desenvolvimento Mobile', 'Formação para desenvolvimento de aplicativos.'),
(12, 'Programa de Consultoria para Pequenos Negócios', 'Suporte para microempresas em crescimento.'),
(13, 'Capacitação em Redes e Infraestrutura', 'Treinamento prático para administradores de redes.'),
(14, 'Treinamento em Qualidade de Software', 'Capacitação em testes e validação de software.'),
(15, 'Programa de Empreendedorismo Social', 'Suporte para empreendedores sociais em comunidades.');

-- --------------------------------------------------------

--
-- Estrutura para tabela `recursos_educacionais`
--

CREATE TABLE `recursos_educacionais` (
  `id_recursos_educacionais` int(255) NOT NULL,
  `recurso` varchar(100) NOT NULL,
  `link` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `recursos_educacionais`
--

INSERT INTO `recursos_educacionais` (`id_recursos_educacionais`, `recurso`, `link`) VALUES
(1, 'Artigo', 'https://www.exemplo.com/html-basico'),
(2, 'Videoaula', 'https://www.exemplo.com/css-videoaula'),
(3, 'PDF', 'https://www.exemplo.com/javascript-apostila'),
(4, 'Artigo', 'https://www.exemplo.com/sql-iniciantes'),
(5, 'Curso Online', 'https://www.exemplo.com/front-end-curso'),
(6, 'E-book', 'https://www.exemplo.com/marketing-digital-pequenas-empresas'),
(7, 'Videoaula', 'https://www.exemplo.com/ux-ui-design-videoaula'),
(8, 'Curso Online', 'https://www.exemplo.com/full-stack-dev'),
(9, 'PDF', 'https://www.exemplo.com/gestao-de-projetos-apostila'),
(10, 'Curso Online', 'https://www.exemplo.com/python-data-science'),
(11, 'Videoaula', 'https://www.exemplo.com/git-github-videoaula'),
(12, 'Artigo', 'https://www.exemplo.com/dicas-entrevista-emprego'),
(13, 'Curso Online', 'https://www.exemplo.com/java-programacao'),
(14, 'E-book', 'https://www.exemplo.com/redes-computadores-ebook'),
(15, 'PDF', 'https://www.exemplo.com/sql-avancado-apostila');

-- --------------------------------------------------------

--
-- Estrutura para tabela `solicitar_assistencia`
--

CREATE TABLE `solicitar_assistencia` (
  `id_soliciatar_assistencia` int(255) NOT NULL,
  `id_usuarios` int(255) NOT NULL,
  `solicitacao` varchar(255) NOT NULL,
  `data` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `solicitar_assistencia`
--

INSERT INTO `solicitar_assistencia` (`id_soliciatar_assistencia`, `id_usuarios`, `solicitacao`, `data`) VALUES
(1, 1, 'Ajuda com o currículo: Preciso de ajuda para atualizar meu currículo.', '2024-01-10'),
(2, 2, 'Orientação sobre entrevistas: Gostaria de dicas para entrevistas de emprego.', '2024-02-15'),
(3, 3, 'Apoio psicológico: Preciso de suporte psicológico para lidar com o estresse.', '2024-03-20'),
(4, 4, 'Capacitação técnica: Estou buscando cursos para melhorar minhas habilidades técnicas.', '2024-04-05'),
(5, 5, 'Consultoria financeira: Preciso de ajuda com gestão financeira pessoal.', '2024-05-01'),
(6, 6, 'Ajuda com perfil no sistema: Gostaria de orientações para melhorar meu perfil.', '2024-06-10'),
(7, 7, 'Suporte em busca de emprego: Tenho dificuldade em encontrar vagas compatíveis.', '2024-07-15'),
(8, 8, 'Apoio na candidatura: Preciso de ajuda para me candidatar às vagas.', '2024-08-20'),
(9, 9, 'Informações sobre os programas: Gostaria de mais detalhes sobre os programas de suporte.', '2024-09-10'),
(10, 10, 'Orientação profissional: Estou em dúvida sobre a melhor área para atuar.', '2024-10-01');

-- --------------------------------------------------------

--
-- Estrutura para tabela `usuarios`
--

CREATE TABLE `usuarios` (
  `id_usuarios` int(255) NOT NULL,
  `nome` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `senha` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `usuarios`
--

INSERT INTO `usuarios` (`id_usuarios`, `nome`, `email`, `senha`) VALUES
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
(20, 'Bianca Xavier', 'bianca.xavier@example.com', '2ebf36607408752caf99b35d24e1785c');

--
-- Acionadores `usuarios`
--
DELIMITER $$
CREATE TRIGGER `before_insert_usuario` BEFORE INSERT ON `usuarios` FOR EACH ROW SET NEW.senha = MD5(NEW.senha)
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Estrutura para tabela `vagas`
--

CREATE TABLE `vagas` (
  `id_vagas` int(255) NOT NULL,
  `id_empregadores` int(255) NOT NULL,
  `titulo_vaga` varchar(100) NOT NULL,
  `descricao_vaga` varchar(100) NOT NULL,
  `salario` varchar(7) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `vagas`
--

INSERT INTO `vagas` (`id_vagas`, `id_empregadores`, `titulo_vaga`, `descricao_vaga`, `salario`) VALUES
(1, 1, 'Desenvolvedor Front-End', 'Desenvolvimento de interfaces web.', '4500.00'),
(2, 2, 'Desenvolvedor Back-End', 'Desenvolvimento de APIs e serviços.', '5000.00'),
(3, 3, 'Administrador de Banco de Dados', 'Gestão e manutenção de bancos de dados.', '5500.00'),
(4, 4, 'Desenvolvedor Mobile', 'Desenvolvimento de apps Android/iOS.', '4000.00'),
(5, 5, 'Engenheiro DevOps', 'Automatização de processos e CI/CD.', '6000.00'),
(6, 6, 'Engenheiro de Qualidade', 'Testes e validação de sistemas.', '4500.00'),
(7, 7, 'Gerente de Projetos', 'Gerenciamento de times e projetos.', '7000.00'),
(8, 8, 'Designer UI/UX', 'Criação de interfaces intuitivas.', '3500.00'),
(9, 9, 'Cientista de Dados', 'Análise de dados e machine learning.', '8000.00'),
(10, 10, 'Analista de Suporte', 'Suporte técnico a clientes.', '3000.00'),
(11, 1, 'Desenvolvedor PHP', 'Desenvolvimento web com PHP.', '4000.00'),
(12, 2, 'Scrum Master', 'Liderança em metodologias ágeis.', '6500.00'),
(13, 3, 'Gerente Financeiro', 'Gestão de finanças e controle de custos.', '7000.00'),
(14, 4, 'Analista de Marketing', 'Desenvolvimento de campanhas de marketing.', '4000.00'),
(15, 5, 'Consultor de Vendas', 'Consultoria em vendas e atendimento.', '3500.00'),
(16, 6, 'Desenvolvedor Full Stack', 'Desenvolvimento front-end e back-end.', '5500.00'),
(17, 7, 'Administrador de Redes', 'Gestão e manutenção de redes.', '5000.00'),
(18, 8, 'Especialista em SEO', 'Otimização de mecanismos de busca.', '4500.00'),
(19, 9, 'Engenheiro de Software', 'Desenvolvimento de sistemas complexos.', '7000.00'),
(20, 10, 'Coordenador de RH', 'Coordenação do departamento de RH.', '6000.00');

--
-- Índices para tabelas despejadas
--

--
-- Índices de tabela `candidaturas`
--
ALTER TABLE `candidaturas`
  ADD PRIMARY KEY (`id_candidaturas`),
  ADD KEY `vagas` (`id_vagas`),
  ADD KEY `perfis_candidatos` (`id_perfis_candidatos`);

--
-- Índices de tabela `empregadores`
--
ALTER TABLE `empregadores`
  ADD PRIMARY KEY (`id_empregadores`);

--
-- Índices de tabela `eventos`
--
ALTER TABLE `eventos`
  ADD PRIMARY KEY (`id_eventos`);

--
-- Índices de tabela `feedback`
--
ALTER TABLE `feedback`
  ADD PRIMARY KEY (`id_feedback`),
  ADD KEY `id_vagas` (`id_vagas`),
  ADD KEY `perfis` (`id_perfis_candidatos`);

--
-- Índices de tabela `historico_atividades`
--
ALTER TABLE `historico_atividades`
  ADD PRIMARY KEY (`id_historico_atividades`),
  ADD KEY `id_usuarios` (`id_usuarios`);

--
-- Índices de tabela `participacao_eventos`
--
ALTER TABLE `participacao_eventos`
  ADD PRIMARY KEY (`id_participacao_eventos`),
  ADD KEY `candidatos` (`id_perfis_candidatos`),
  ADD KEY `eventos` (`id_eventos`);

--
-- Índices de tabela `participacoes`
--
ALTER TABLE `participacoes`
  ADD PRIMARY KEY (`id_participacoes`),
  ADD KEY `id_perfis_candidatos` (`id_perfis_candidatos`),
  ADD KEY `id_programas` (`id_programas`);

--
-- Índices de tabela `perfis_candidatos`
--
ALTER TABLE `perfis_candidatos`
  ADD PRIMARY KEY (`id_perfis_candidatos`),
  ADD KEY `usuarios` (`id_usuarios`);

--
-- Índices de tabela `programas`
--
ALTER TABLE `programas`
  ADD PRIMARY KEY (`id_programas`);

--
-- Índices de tabela `recursos_educacionais`
--
ALTER TABLE `recursos_educacionais`
  ADD PRIMARY KEY (`id_recursos_educacionais`);

--
-- Índices de tabela `solicitar_assistencia`
--
ALTER TABLE `solicitar_assistencia`
  ADD PRIMARY KEY (`id_soliciatar_assistencia`),
  ADD KEY `usuarios` (`id_usuarios`);

--
-- Índices de tabela `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`id_usuarios`);

--
-- Índices de tabela `vagas`
--
ALTER TABLE `vagas`
  ADD PRIMARY KEY (`id_vagas`),
  ADD KEY `empregadores` (`id_empregadores`);

--
-- AUTO_INCREMENT para tabelas despejadas
--

--
-- AUTO_INCREMENT de tabela `candidaturas`
--
ALTER TABLE `candidaturas`
  MODIFY `id_candidaturas` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=31;

--
-- AUTO_INCREMENT de tabela `empregadores`
--
ALTER TABLE `empregadores`
  MODIFY `id_empregadores` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT de tabela `eventos`
--
ALTER TABLE `eventos`
  MODIFY `id_eventos` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT de tabela `feedback`
--
ALTER TABLE `feedback`
  MODIFY `id_feedback` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT de tabela `historico_atividades`
--
ALTER TABLE `historico_atividades`
  MODIFY `id_historico_atividades` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT de tabela `participacao_eventos`
--
ALTER TABLE `participacao_eventos`
  MODIFY `id_participacao_eventos` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT de tabela `participacoes`
--
ALTER TABLE `participacoes`
  MODIFY `id_participacoes` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT de tabela `perfis_candidatos`
--
ALTER TABLE `perfis_candidatos`
  MODIFY `id_perfis_candidatos` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT de tabela `programas`
--
ALTER TABLE `programas`
  MODIFY `id_programas` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT de tabela `recursos_educacionais`
--
ALTER TABLE `recursos_educacionais`
  MODIFY `id_recursos_educacionais` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT de tabela `solicitar_assistencia`
--
ALTER TABLE `solicitar_assistencia`
  MODIFY `id_soliciatar_assistencia` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT de tabela `usuarios`
--
ALTER TABLE `usuarios`
  MODIFY `id_usuarios` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;

--
-- AUTO_INCREMENT de tabela `vagas`
--
ALTER TABLE `vagas`
  MODIFY `id_vagas` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- Restrições para tabelas despejadas
--

--
-- Restrições para tabelas `candidaturas`
--
ALTER TABLE `candidaturas`
  ADD CONSTRAINT `perfis_candidatos` FOREIGN KEY (`id_perfis_candidatos`) REFERENCES `perfis_candidatos` (`id_perfis_candidatos`) ON DELETE CASCADE,
  ADD CONSTRAINT `vagas` FOREIGN KEY (`id_vagas`) REFERENCES `vagas` (`id_vagas`) ON DELETE CASCADE;

--
-- Restrições para tabelas `feedback`
--
ALTER TABLE `feedback`
  ADD CONSTRAINT `id_vagas` FOREIGN KEY (`id_vagas`) REFERENCES `vagas` (`id_vagas`) ON DELETE CASCADE,
  ADD CONSTRAINT `perfis` FOREIGN KEY (`id_perfis_candidatos`) REFERENCES `perfis_candidatos` (`id_perfis_candidatos`) ON DELETE CASCADE;

--
-- Restrições para tabelas `historico_atividades`
--
ALTER TABLE `historico_atividades`
  ADD CONSTRAINT `id_usuarios` FOREIGN KEY (`id_usuarios`) REFERENCES `usuarios` (`id_usuarios`) ON DELETE CASCADE;

--
-- Restrições para tabelas `participacao_eventos`
--
ALTER TABLE `participacao_eventos`
  ADD CONSTRAINT `candidatos` FOREIGN KEY (`id_perfis_candidatos`) REFERENCES `perfis_candidatos` (`id_perfis_candidatos`) ON DELETE CASCADE,
  ADD CONSTRAINT `eventos` FOREIGN KEY (`id_eventos`) REFERENCES `eventos` (`id_eventos`) ON DELETE CASCADE;

--
-- Restrições para tabelas `participacoes`
--
ALTER TABLE `participacoes`
  ADD CONSTRAINT `id_perfis_candidatos` FOREIGN KEY (`id_perfis_candidatos`) REFERENCES `perfis_candidatos` (`id_perfis_candidatos`) ON DELETE CASCADE,
  ADD CONSTRAINT `id_programas` FOREIGN KEY (`id_programas`) REFERENCES `programas` (`id_programas`) ON DELETE CASCADE;

--
-- Restrições para tabelas `solicitar_assistencia`
--
ALTER TABLE `solicitar_assistencia`
  ADD CONSTRAINT `usuarios` FOREIGN KEY (`id_usuarios`) REFERENCES `usuarios` (`id_usuarios`) ON DELETE CASCADE;

--
-- Restrições para tabelas `vagas`
--
ALTER TABLE `vagas`
  ADD CONSTRAINT `empregadores` FOREIGN KEY (`id_empregadores`) REFERENCES `empregadores` (`id_empregadores`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
