//@Paulo Ribeiro Marinho
package javaapplication1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EventosDAO {
    public void adicionarEvento(Eventos evento) {
        String sql = "INSERT INTO eventos (nome, descricao, data) VALUES (?, ?, ?)";

        try (Connection conexao = ConexaoBD.getConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, evento.getNome());
            stmt.setString(2, evento.getDescricao());
            stmt.setString(3, evento.getData());

            stmt.executeUpdate();
            System.out.println("Evento adicionado com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao adicionar evento: " + e.getMessage());
        }
    }

    public List<Eventos> listarEventos() {
        List<Eventos> eventos = new ArrayList<>();
        String sql = "SELECT * FROM eventos";

        try (Connection conexao = ConexaoBD.getConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Eventos evento = new Eventos();
                evento.setIdEventos(rs.getInt("id_eventos"));
                evento.setNome(rs.getString("nome"));
                evento.setDescricao(rs.getString("descricao"));
                evento.setData(rs.getString("data"));
                eventos.add(evento);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar eventos: " + e.getMessage());
        }
        return eventos;
    }

    public void atualizarEvento(Eventos evento) throws SQLException {
        String sql = "UPDATE eventos SET nome = ?, descricao = ?, data = ? WHERE id_eventos = ?";

        try (Connection conexao = ConexaoBD.getConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, evento.getNome());
            stmt.setString(2, evento.getDescricao());
            stmt.setString(3, evento.getData());
            stmt.setInt(4, evento.getIdEventos());

            stmt.executeUpdate();  // Executa a atualização no banco de dados

        } catch (SQLException e) {
            throw new SQLException("Erro ao atualizar o evento: " + e.getMessage());
        }
    }

    public Eventos buscarEventoPorId(int idEvento) {
        Eventos evento = null;
        String sql = "SELECT * FROM eventos WHERE id_eventos = ?"; 

        try (Connection conexao = ConexaoBD.getConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, idEvento);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    evento = new Eventos();
                    evento.setIdEventos(rs.getInt("id_eventos"));
                    evento.setNome(rs.getString("nome"));
                    evento.setDescricao(rs.getString("descricao"));
                    evento.setData(rs.getString("data"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return evento;
    }

    public void excluirEvento(int idEvento) {
        String sql = "DELETE FROM eventos WHERE id_eventos = ?";

        try (Connection conexao = ConexaoBD.getConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, idEvento);
            stmt.executeUpdate();  

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
