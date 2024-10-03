//@Paulo Ribeiro Marinho
package javaapplication1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FeedbackDAO {
    public void adicionarFeedback(Feedback feedback) {
        String sql = "INSERT INTO feedback (id_vagas, id_perfis_candidatos, feedback, data) VALUES (?, ?, ?, ?)";

        try (Connection conexao = ConexaoBD.getConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, feedback.getIdVagas());
            stmt.setInt(2, feedback.getIdPerfisCandidatos());
            stmt.setString(3, feedback.getFeedback());
            stmt.setString(4, feedback.getData());

            stmt.executeUpdate();
            System.out.println("Feedback adicionado com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao adicionar feedback: " + e.getMessage());
        }
    }

    public List<Feedback> listarFeedbacks() {
        List<Feedback> feedbacks = new ArrayList<>();
        String sql = "SELECT * FROM feedback";

        try (Connection conexao = ConexaoBD.getConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Feedback feedback = new Feedback();
                feedback.setIdFeedback(rs.getInt("id_feedback"));
                feedback.setIdVagas(rs.getInt("id_vagas"));
                feedback.setIdPerfisCandidatos(rs.getInt("id_perfis_candidatos"));
                feedback.setFeedback(rs.getString("feedback"));
                feedback.setData(rs.getString("data"));
                feedbacks.add(feedback);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar feedbacks: " + e.getMessage());
        }
        return feedbacks;
    }

    public void atualizarFeedback(Feedback feedback) throws SQLException {
        String sql = "UPDATE feedback SET id_vagas = ?, id_perfis_candidatos = ?, feedback = ?, data = ? WHERE id_feedback = ?";

        try (Connection conexao = ConexaoBD.getConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, feedback.getIdVagas());
            stmt.setInt(2, feedback.getIdPerfisCandidatos());
            stmt.setString(3, feedback.getFeedback());
            stmt.setString(4, feedback.getData());
            stmt.setInt(5, feedback.getIdFeedback());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Erro ao atualizar o feedback: " + e.getMessage());
        }
    }

    public Feedback buscarFeedbackPorId(int idFeedback) {
        Feedback feedback = null;
        String sql = "SELECT * FROM feedback WHERE id_feedback = ?";  

        try (Connection conexao = ConexaoBD.getConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, idFeedback); 
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    feedback = new Feedback();
                    feedback.setIdFeedback(rs.getInt("id_feedback"));
                    feedback.setIdVagas(rs.getInt("id_vagas"));
                    feedback.setIdPerfisCandidatos(rs.getInt("id_perfis_candidatos"));
                    feedback.setFeedback(rs.getString("feedback"));
                    feedback.setData(rs.getString("data"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return feedback;  
    }

    public void excluirFeedback(int idFeedback) {
        String sql = "DELETE FROM feedback WHERE id_feedback = ?";  // Substitua 'feedback' pelo nome da sua tabela.

        try (Connection conexao = ConexaoBD.getConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, idFeedback);  
            stmt.executeUpdate();  
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
