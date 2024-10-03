//@Paulo Ribeiro Marinho
package javaapplication1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoBD {
    private static final String URL = "jdbc:mysql://localhost:3306/plataforma_vulnerabilidade";
    private static final String USUARIO = "root";  // Seu usuário do MySQL
    private static final String SENHA = ""; // Sua senha do MySQL

    public static Connection getConexao() throws SQLException {
        try {
            return DriverManager.getConnection(URL, USUARIO, SENHA);
        } catch (SQLException e) {
            System.out.println("Erro ao conectar com o banco de dados: " + e.getMessage());
            throw e;
        }
    }
}
