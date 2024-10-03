//@Paulo Ribeiro Marinho
package javaapplication1;

/**
 *
 * @author p7
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RecursosEducacionaisDAO {
    public void adicionarRecursoEducacional(RecursosEducacionais recursoEducacional) {
        String sql = "INSERT INTO recursos_educacionais (recurso, link) VALUES (?, ?)";

        try (Connection conexao = ConexaoBD.getConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, recursoEducacional.getRecurso());
            stmt.setString(2, recursoEducacional.getLink());

            stmt.executeUpdate();
            System.out.println("Recurso educacional adicionado com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao adicionar recurso educacional: " + e.getMessage());
        }
    }

    public List<RecursosEducacionais> listarRecursosEducacionais() {
        List<RecursosEducacionais> recursosEducacionais = new ArrayList<>();
        String sql = "SELECT * FROM recursos_educacionais";

        try (Connection conexao = ConexaoBD.getConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                RecursosEducacionais recursoEducacional = new RecursosEducacionais();
                recursoEducacional.setIdRecursosEducacionais(rs.getInt("id_recursos_educacionais"));
                recursoEducacional.setRecurso(rs.getString("recurso"));
                recursoEducacional.setLink(rs.getString("link"));
                recursosEducacionais.add(recursoEducacional);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar recursos educacionais: " + e.getMessage());
        }
        return recursosEducacionais;
    }

    public void atualizarRecursoEducacional(RecursosEducacionais recursoEducacional) throws SQLException {
        String sql = "UPDATE recursos_educacionais SET recurso = ?, link = ? WHERE id_recursos_educacionais = ?";

        try (Connection conexao = ConexaoBD.getConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, recursoEducacional.getRecurso());
            stmt.setString(2, recursoEducacional.getLink());
            stmt.setInt(3, recursoEducacional.getIdRecursosEducacionais());

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new SQLException("Erro ao atualizar o recurso educacional: " + e.getMessage());
        }
    }

    public RecursosEducacionais buscarRecursoEducacionalPorId(int idRecursoEducacional) {
        RecursosEducacionais recursoEducacional = null;
        String sql = "SELECT * FROM recursos_educacionais WHERE id_recursos_educacionais = ?";

        try (Connection conexao = ConexaoBD.getConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, idRecursoEducacional);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    recursoEducacional = new RecursosEducacionais();
                    recursoEducacional.setIdRecursosEducacionais(rs.getInt("id_recursos_educacionais"));
                    recursoEducacional.setRecurso(rs.getString("recurso"));
                    recursoEducacional.setLink(rs.getString("link"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return recursoEducacional;
    }

    public void excluirRecursoEducacional(int idRecursoEducacional) {
        String sql = "DELETE FROM recursos_educacionais WHERE id_recursos_educacionais = ?";

        try (Connection conexao = ConexaoBD.getConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, idRecursoEducacional);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
