//@Paulo Ribeiro Marinho
package javaapplication1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CandidaturasDAO {
    public void adicionarCandidatura(Candidaturas candidatura) {
        String sql = "INSERT INTO candidaturas (id_vagas, id_perfis_candidatos, data, status) VALUES (?, ?, ?, ?)";

        try (Connection conexao = ConexaoBD.getConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, candidatura.getIdVagas());
            stmt.setInt(2, candidatura.getIdPerfisCandidatos());
            stmt.setString(3, candidatura.getData());
            stmt.setString(4, candidatura.getStatus());

            stmt.executeUpdate();
            System.out.println("Candidatura adicionada com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao adicionar candidatura: " + e.getMessage());
        }
    }

    public List<Candidaturas> listarCandidaturas() {
        List<Candidaturas> candidaturas = new ArrayList<>();
        String sql = "SELECT * FROM candidaturas";

        try (Connection conexao = ConexaoBD.getConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Candidaturas candidatura = new Candidaturas();
                candidatura.setIdCandidaturas(rs.getInt("id_candidaturas"));
                candidatura.setIdVagas(rs.getInt("id_vagas"));
                candidatura.setIdPerfisCandidatos(rs.getInt("id_perfis_candidatos"));
                candidatura.setData(rs.getString("data"));
                candidatura.setStatus(rs.getString("status"));
                candidaturas.add(candidatura);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar candidaturas: " + e.getMessage());
        }
        return candidaturas;
    }

    public void atualizarCandidatura(Candidaturas candidatura) throws SQLException {
        String sql = "UPDATE candidaturas SET id_vagas = ?, id_perfis_candidatos = ?, data = ?, status = ? WHERE id_candidaturas = ?";

        try (Connection conexao = ConexaoBD.getConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, candidatura.getIdVagas());
            stmt.setInt(2, candidatura.getIdPerfisCandidatos());
            stmt.setString(3, candidatura.getData());
            stmt.setString(4, candidatura.getStatus());
            stmt.setInt(5, candidatura.getIdCandidaturas());

            stmt.executeUpdate();  // Executa a atualização no banco de dados

        } catch (SQLException e) {
            throw new SQLException("Erro ao atualizar a candidatura: " + e.getMessage());
        }
    }

    public Candidaturas buscarCandidaturaPorId(int idCandidatura) {
        Candidaturas candidatura = null;
        String sql = "SELECT * FROM candidaturas WHERE id_candidaturas = ?";

        try (Connection conexao = ConexaoBD.getConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, idCandidatura);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    candidatura = new Candidaturas();
                    candidatura.setIdCandidaturas(rs.getInt("id_candidaturas"));
                    candidatura.setIdVagas(rs.getInt("id_vagas"));
                    candidatura.setIdPerfisCandidatos(rs.getInt("id_perfis_candidatos"));
                    candidatura.setData(rs.getString("data"));
                    candidatura.setStatus(rs.getString("status"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return candidatura;  // Retorna a candidatura ou null se não for encontrada.
    }

    public void excluirCandidatura(int idCandidatura) {
        String sql = "DELETE FROM candidaturas WHERE id_candidaturas = ?";

        try (Connection conexao = ConexaoBD.getConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, idCandidatura);  // Define o ID da candidatura a ser excluída.
            stmt.executeUpdate();  // Executa a instrução DELETE no banco de dados.

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
