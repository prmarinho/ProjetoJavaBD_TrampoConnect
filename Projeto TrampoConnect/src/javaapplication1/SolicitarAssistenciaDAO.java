//@Paulo Ribeiro Marinho
package javaapplication1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SolicitarAssistenciaDAO {
    public void adicionarSolicitacao(SolicitarAssistencia solicitacao) {
        String sql = "INSERT INTO solicitar_assistencia (id_usuarios, solicitacao, data) VALUES (?, ?, ?)";

        try (Connection conexao = ConexaoBD.getConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, solicitacao.getIdUsuarios());
            stmt.setString(2, solicitacao.getSolicitacao());
            stmt.setString(3, solicitacao.getData());

            stmt.executeUpdate();
            System.out.println("Solicitação adicionada com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao adicionar solicitação: " + e.getMessage());
        }
    }

    public List<SolicitarAssistencia> listarSolicitacoes() {
        List<SolicitarAssistencia> solicitacoes = new ArrayList<>();
        String sql = "SELECT * FROM solicitar_assistencia";

        try (Connection conexao = ConexaoBD.getConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                SolicitarAssistencia solicitacao = new SolicitarAssistencia();
                solicitacao.setIdSolicitarAssistencia(rs.getInt("id_solicitar_assistencia"));
                solicitacao.setIdUsuarios(rs.getInt("id_usuarios"));
                solicitacao.setSolicitacao(rs.getString("solicitacao"));
                solicitacao.setData(rs.getString("data"));
                solicitacoes.add(solicitacao);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar solicitações: " + e.getMessage());
        }
        return solicitacoes;
    }

    public void atualizarSolicitacao(SolicitarAssistencia solicitacao) throws SQLException {
        String sql = "UPDATE solicitar_assistencia SET id_usuarios = ?, solicitacao = ?, data = ? WHERE id_solicitar_assistencia = ?";

        try (Connection conexao = ConexaoBD.getConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, solicitacao.getIdUsuarios());
            stmt.setString(2, solicitacao.getSolicitacao());
            stmt.setString(3, solicitacao.getData());
            stmt.setInt(4, solicitacao.getIdSolicitarAssistencia());

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new SQLException("Erro ao atualizar a solicitação: " + e.getMessage());
        }
    }

    public SolicitarAssistencia buscarSolicitacaoPorId(int idSolicitacao) {
        SolicitarAssistencia solicitacao = null;
        String sql = "SELECT * FROM solicitar_assistencia WHERE id_solicitar_assistencia = ?"; // Nome da tabela atualizada

        try (Connection conexao = ConexaoBD.getConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, idSolicitacao);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    solicitacao = new SolicitarAssistencia();
                    solicitacao.setIdSolicitarAssistencia(rs.getInt("id_solicitar_assistencia")); 
                    solicitacao.setIdUsuarios(rs.getInt("id_usuarios"));
                    solicitacao.setSolicitacao(rs.getString("solicitacao"));
                    solicitacao.setData(rs.getString("data"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return solicitacao; 
    }

    public void excluirSolicitacao(int idSolicitacao) {
        String sql = "DELETE FROM solicitar_assistencia WHERE id_solicitar_assistencia = ?"; 

        try (Connection conexao = ConexaoBD.getConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, idSolicitacao); 
            stmt.executeUpdate(); 

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}