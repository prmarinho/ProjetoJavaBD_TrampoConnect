//@Paulo Ribeiro Marinho
package javaapplication1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmpregadoresDAO {
    public void adicionarEmpregador(Empregadores empregador) {
        String sql = "INSERT INTO empregadores (empresa, cnpj, email) VALUES (?, ?, ?)";

        try (Connection conexao = ConexaoBD.getConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, empregador.getEmpresa());
            stmt.setString(2, empregador.getCnpj());
            stmt.setString(3, empregador.getEmail());

            stmt.executeUpdate();
            System.out.println("Empregador adicionado com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao adicionar empregador: " + e.getMessage());
        }
    }

    public List<Empregadores> listarEmpregadores() {
        List<Empregadores> empregadores = new ArrayList<>();
        String sql = "SELECT * FROM empregadores";

        try (Connection conexao = ConexaoBD.getConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Empregadores empregador = new Empregadores();
                empregador.setIdEmpregadores(rs.getInt("id_empregadores"));
                empregador.setEmpresa(rs.getString("empresa"));
                empregador.setCnpj(rs.getString("cnpj"));
                empregador.setEmail(rs.getString("email"));
                empregadores.add(empregador);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar empregadores: " + e.getMessage());
        }
        return empregadores;
    }

    public void atualizarEmpregador(Empregadores empregador) throws SQLException {
        String sql = "UPDATE empregadores SET empresa = ?, cnpj = ?, email = ? WHERE id_empregadores = ?";

        try (Connection conexao = ConexaoBD.getConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, empregador.getEmpresa());
            stmt.setString(2, empregador.getCnpj());
            stmt.setString(3, empregador.getEmail());
            stmt.setInt(4, empregador.getIdEmpregadores());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Erro ao atualizar o empregador: " + e.getMessage());
        }
    }

    public Empregadores buscarEmpregadorPorId(int idEmpregador) {
        Empregadores empregador = null;
        String sql = "SELECT * FROM empregadores WHERE id_empregadores = ?";

        try (Connection conexao = ConexaoBD.getConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, idEmpregador);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    empregador = new Empregadores();
                    empregador.setIdEmpregadores(rs.getInt("id_empregadores"));
                    empregador.setEmpresa(rs.getString("empresa"));
                    empregador.setCnpj(rs.getString("cnpj"));
                    empregador.setEmail(rs.getString("email"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return empregador;
    }

    public void excluirEmpregador(int idEmpregador) {
        String sql = "DELETE FROM empregadores WHERE id_empregadores = ?";

        try (Connection conexao = ConexaoBD.getConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, idEmpregador);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}