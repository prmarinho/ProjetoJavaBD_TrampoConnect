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

public class ProgramasDeSuporteDAO {
    public void adicionarPrograma(ProgramasDeSuporte programa) {
        String sql = "INSERT INTO programas (programa, descricao) VALUES (?, ?)";

        try (Connection conexao = ConexaoBD.getConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, programa.getPrograma());
            stmt.setString(2, programa.getDescricao());

            stmt.executeUpdate();
            System.out.println("Programa adicionado com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao adicionar programa: " + e.getMessage());
        }
    }

    public List<ProgramasDeSuporte> listarProgramas() {
        List<ProgramasDeSuporte> programas = new ArrayList<>();
        String sql = "SELECT * FROM programas";

        try (Connection conexao = ConexaoBD.getConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                ProgramasDeSuporte programa = new ProgramasDeSuporte();
                programa.setIdProgramas(rs.getInt("id_programas"));
                programa.setPrograma(rs.getString("programa"));
                programa.setDescricao(rs.getString("descricao"));
                programas.add(programa);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar programas: " + e.getMessage());
        }
        return programas;
    }

    public void atualizarPrograma(ProgramasDeSuporte programa) throws SQLException {
        String sql = "UPDATE programas SET programa = ?, descricao = ? WHERE id_programas = ?";

        try (Connection conexao = ConexaoBD.getConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, programa.getPrograma());
            stmt.setString(2, programa.getDescricao());
            stmt.setInt(3, programa.getIdProgramas());

            stmt.executeUpdate(); 
        } catch (SQLException e) {
            throw new SQLException("Erro ao atualizar o programa: " + e.getMessage());
        }
    }

    public ProgramasDeSuporte buscarProgramaPorId(int idPrograma) {
        ProgramasDeSuporte programa = null;
        String sql = "SELECT * FROM programas WHERE id_programas = ?";

        try (Connection conexao = ConexaoBD.getConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, idPrograma);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    programa = new ProgramasDeSuporte();
                    programa.setIdProgramas(rs.getInt("id_programas"));
                    programa.setPrograma(rs.getString("programa"));
                    programa.setDescricao(rs.getString("descricao"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return programa;
    }

    public void excluirPrograma(int idPrograma) {
        String sql = "DELETE FROM programas WHERE id_programas = ?";

        try (Connection conexao = ConexaoBD.getConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, idPrograma);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
