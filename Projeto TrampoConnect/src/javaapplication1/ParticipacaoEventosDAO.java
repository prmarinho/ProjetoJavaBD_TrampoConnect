//@Paulo Ribeiro Marinho
package javaapplication1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ParticipacaoEventosDAO {
    public void adicionarParticipacao(ParticipacaoEventos participacao) {
        String sql = "INSERT INTO participacao_eventos (id_perfis_candidatos, id_eventos, data) VALUES (?, ?, ?)";

        try (Connection conexao = ConexaoBD.getConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, participacao.getIdPerfisCandidatos());
            stmt.setInt(2, participacao.getIdEventos());
            stmt.setString(3, participacao.getData());

            stmt.executeUpdate();
            System.out.println("Participação adicionada com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao adicionar participação: " + e.getMessage());
        }
    }

    public List<ParticipacaoEventos> listarParticipacoes() {
        List<ParticipacaoEventos> participacoes = new ArrayList<>();
        String sql = "SELECT * FROM participacao_eventos";

        try (Connection conexao = ConexaoBD.getConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                ParticipacaoEventos participacao = new ParticipacaoEventos();
                participacao.setIdParticipacaoEventos(rs.getInt("id_participacao_eventos"));
                participacao.setIdPerfisCandidatos(rs.getInt("id_perfis_candidatos"));
                participacao.setIdEventos(rs.getInt("id_eventos"));
                participacao.setData(rs.getString("data"));
                participacoes.add(participacao);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar participações: " + e.getMessage());
        }
        return participacoes;
    }

    public ParticipacaoEventos buscarParticipacaoPorId(int idParticipacao) {
        ParticipacaoEventos participacao = null;
        String sql = "SELECT * FROM participacao_eventos WHERE id_participacao_eventos = ?";

        try (Connection conexao = ConexaoBD.getConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, idParticipacao);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    participacao = new ParticipacaoEventos();
                    participacao.setIdParticipacaoEventos(rs.getInt("id_participacao_eventos"));
                    participacao.setIdPerfisCandidatos(rs.getInt("id_perfis_candidatos"));
                    participacao.setIdEventos(rs.getInt("id_eventos"));
                    participacao.setData(rs.getString("data"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return participacao;
    }

    public void atualizarParticipacao(ParticipacaoEventos participacao) throws SQLException {
        String sql = "UPDATE participacao_eventos SET id_perfis_candidatos = ?, id_eventos = ?, data = ? WHERE id_participacao_eventos = ?";

        try (Connection conexao = ConexaoBD.getConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, participacao.getIdPerfisCandidatos());
            stmt.setInt(2, participacao.getIdEventos());
            stmt.setString(3, participacao.getData());
            stmt.setInt(4, participacao.getIdParticipacaoEventos());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Erro ao atualizar a participação: " + e.getMessage());
        }
    }

    public void excluirParticipacao(int idParticipacao) {
        String sql = "DELETE FROM participacao_eventos WHERE id_participacao_eventos = ?";

        try (Connection conexao = ConexaoBD.getConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, idParticipacao);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}