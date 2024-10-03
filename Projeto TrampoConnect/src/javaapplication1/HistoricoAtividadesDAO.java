//@Paulo Ribeiro Marinho
package javaapplication1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HistoricoAtividadesDAO {
    public void adicionarAtividade(HistoricoAtividades atividade) {
        String sql = "INSERT INTO historico_atividades (id_usuarios, descricao, data) VALUES (?, ?, ?)";

        try (Connection conexao = ConexaoBD.getConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, atividade.getIdUsuarios());
            stmt.setString(2, atividade.getDescricao());
            stmt.setString(3, atividade.getData());

            stmt.executeUpdate();
            System.out.println("Atividade adicionada com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao adicionar atividade: " + e.getMessage());
        }
    }

    public List<HistoricoAtividades> listarAtividades() {
        List<HistoricoAtividades> atividades = new ArrayList<>();
        String sql = "SELECT * FROM historico_atividades";

        try (Connection conexao = ConexaoBD.getConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                HistoricoAtividades atividade = new HistoricoAtividades();
                atividade.setIdHistoricoAtividades(rs.getInt("id_historico_atividades"));
                atividade.setIdUsuarios(rs.getInt("id_usuarios"));
                atividade.setDescricao(rs.getString("descricao"));
                atividade.setData(rs.getString("data"));
                atividades.add(atividade);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar atividades: " + e.getMessage());
        }
        return atividades;
    }

    public void atualizarAtividade(HistoricoAtividades atividade) throws SQLException {
        String sql = "UPDATE historico_atividades SET id_usuarios = ?, descricao = ?, data = ? WHERE id_historico_atividades = ?";

        try (Connection conexao = ConexaoBD.getConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, atividade.getIdUsuarios());
            stmt.setString(2, atividade.getDescricao());
            stmt.setString(3, atividade.getData());
            stmt.setInt(4, atividade.getIdHistoricoAtividades());

            stmt.executeUpdate();
            System.out.println("Atividade atualizada com sucesso!");
        } catch (SQLException e) {
            throw new SQLException("Erro ao atualizar a atividade: " + e.getMessage());
        }
    }


    public HistoricoAtividades buscarAtividadePorId(int id_historico_atividades) {
        HistoricoAtividades atividade = null;
        String sql = "SELECT * FROM historico_atividades WHERE id_historico_atividades = ?";

        try (Connection conexao = ConexaoBD.getConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, id_historico_atividades);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    atividade = new HistoricoAtividades();
                    atividade.setIdHistoricoAtividades(rs.getInt("id_historico_atividades"));
                    atividade.setIdUsuarios(rs.getInt("id_usuarios"));
                    atividade.setDescricao(rs.getString("descricao"));
                    atividade.setData(rs.getString("data"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return atividade;
    }

    public void excluirAtividade(int id_historico_atividades) {
        String sql = "DELETE FROM historico_atividades WHERE id_historico_atividades = ?";

        try (Connection conexao = ConexaoBD.getConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, id_historico_atividades);
            stmt.executeUpdate();
            System.out.println("Atividade excluída com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
