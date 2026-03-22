# 🔗 TrampoConnect

> Plataforma de conexão entre candidatos em situação de vulnerabilidade e empregadores, desenvolvida em Java com integração a banco de dados MySQL.

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-4479A1?style=for-the-badge&logo=mysql&logoColor=white)
![NetBeans](https://img.shields.io/badge/NetBeans-1B6AC6?style=for-the-badge&logo=apache-netbeans-ide&logoColor=white)
![XAMPP](https://img.shields.io/badge/XAMPP-FB7A24?style=for-the-badge&logo=xampp&logoColor=white)
![Status](https://img.shields.io/badge/Status-Concluído-brightgreen?style=for-the-badge)


## 💡 Sobre o Projeto

O **TrampoConnect** é uma plataforma desktop desenvolvida como parte do *Challenge do Terceiro Bimestre* do **Senac**, com o objetivo de conectar pessoas em situação de vulnerabilidade social ao mercado de trabalho.

A aplicação permite que candidatos criem perfis com suas habilidades e experiências, se candidatem a vagas disponibilizadas por empregadores cadastrados, participem de programas de capacitação e acessem recursos educacionais — tudo integrado a um banco de dados MySQL robusto.

---

## ✨ Funcionalidades

- [x] Cadastro e autenticação de usuários (com senha criptografada via MD5)
- [x] Gerenciamento de perfis de candidatos (habilidades e experiência)
- [x] Cadastro de empregadores e publicação de vagas
- [x] Sistema de candidaturas com status (Pendente / Aceito / Rejeitado)
- [x] Feedback de candidatos sobre o processo seletivo
- [x] Programas de capacitação e inclusão digital
- [x] Cadastro e participação em eventos
- [x] Recursos educacionais (artigos, videoaulas, PDFs, cursos online)
- [x] Solicitação de assistência e suporte
- [x] Histórico de atividades dos usuários
- [x] Triggers automáticos no banco de dados (hash de senha, log de atividades)

---

## 🗄️ Banco de Dados

O projeto utiliza um banco de dados MySQL chamado `plataforma_vulnerabilidade`, composto pelas seguintes tabelas:

| Tabela                  | Descrição                                          |
|-------------------------|----------------------------------------------------|
| `usuarios`              | Dados de autenticação dos usuários                 |
| `perfis_candidatos`     | Habilidades e experiência profissional             |
| `empregadores`          | Empresas cadastradas na plataforma                 |
| `vagas`                 | Oportunidades de emprego publicadas                |
| `candidaturas`          | Inscrições dos candidatos em vagas                 |
| `feedback`              | Avaliações do processo seletivo                    |
| `programas`             | Programas de capacitação disponíveis               |
| `participacoes`         | Vínculo entre candidatos e programas               |
| `eventos`               | Eventos e workshops da plataforma                  |
| `participacao_eventos`  | Inscrições em eventos                              |
| `recursos_educacionais` | Links e materiais de aprendizado                   |
| `solicitar_assistencia` | Pedidos de suporte enviados pelos usuários         |
| `historico_atividades`  | Log de ações realizadas na plataforma              |

> O projeto inclui dois scripts SQL:
> - **`1BD_TrampoConnect.sql`** — versão inicial (apenas tabela de usuários)
> - **`2BD_TrampoConnect.sql`** — versão completa com todas as tabelas, dados de exemplo, triggers e chaves estrangeiras

---

## ✅ Pré-requisitos

Antes de começar, você precisa ter instalado em sua máquina:

- [Java JDK 8+](https://www.oracle.com/java/technologies/downloads/)
- [NetBeans IDE](https://netbeans.apache.org/front/main/index.html)
- [XAMPP](https://www.apachefriends.org/) (para rodar o MySQL/MariaDB localmente)
- [MySQL Connector/J](https://dev.mysql.com/downloads/connector/j/) (driver JDBC para Java)

---

## 🚀 Como Executar

### 1. Clone o repositório

```bash
git clone https://github.com/prmarinho/ProjetoJavaBD_TrampoConnect.git
```

### 2. Configure o banco de dados

1. Inicie o **XAMPP** e ative o serviço **MySQL**
2. Acesse o **phpMyAdmin** em `http://localhost/phpmyadmin`
3. Crie um novo banco de dados chamado `plataforma_vulnerabilidade`
4. Importe o arquivo `2BD_TrampoConnect.sql` (versão completa)

### 3. Configure a conexão no projeto

No código Java, localize o arquivo de configuração de conexão e ajuste as credenciais:

```java
String url = "jdbc:mysql://localhost:3306/plataforma_vulnerabilidade";
String user = "root";
String password = ""; // sua senha do MySQL
```

### 4. Abra o projeto no NetBeans

1. Abra o **NetBeans IDE**
2. Vá em `File > Open Project`
3. Selecione a pasta `Projeto TrampoConnect`
4. Certifique-se de que o **MySQL Connector/J** está adicionado às bibliotecas do projeto
5. Execute o projeto com `F6` ou clicando em **Run Project**

---

## 🎬 Demonstração

O repositório inclui o arquivo `Demonstracao.mp4` com uma gravação do funcionamento da aplicação.

---

## 🛠️ Tecnologias

As seguintes ferramentas foram utilizadas na construção do projeto:

- **[Java](https://www.java.com/)** — Linguagem principal da aplicação
- **[NetBeans IDE](https://netbeans.apache.org/)** — Ambiente de desenvolvimento
- **[MySQL](https://www.mysql.com/)** — Sistema gerenciador de banco de dados relacional
- **[XAMPP](https://www.apachefriends.org/)** — Servidor local com Apache e MySQL/MariaDB
- **[phpMyAdmin](https://www.phpmyadmin.net/)** — Interface gráfica para administração do MySQL
- **[JDBC](https://docs.oracle.com/javase/tutorial/jdbc/)** — API de conexão Java com banco de dados

