//@Paulo Ribeiro Marinho
package javaapplication1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VagasDeEmpregoDAO {
    public void adicionarVaga(VagasDeEmprego vaga) {
        String sql = "INSERT INTO vagas (id_empregadores, titulo_vaga, descricao_vaga, salario) VALUES (?, ?, ?, ?)";

        try (Connection conexao = ConexaoBD.getConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, vaga.getIdEmpregadores());
            stmt.setString(2, vaga.getTituloVaga());
            stmt.setString(3, vaga.getDescricaoVaga());
            stmt.setInt(4, vaga.getSalario());

            stmt.executeUpdate();
            System.out.println("Vaga adicionada com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao adicionar vaga: " + e.getMessage());
        }
    }

    public List<VagasDeEmprego> listarVagas() {
        List<VagasDeEmprego> vagas = new ArrayList<>();
        String sql = "SELECT * FROM vagas";

        try (Connection conexao = ConexaoBD.getConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                VagasDeEmprego vaga = new VagasDeEmprego();
                vaga.setIdVagas(rs.getInt("id_vagas"));
                vaga.setIdEmpregadores(rs.getInt("id_empregadores"));
                vaga.setTituloVaga(rs.getString("titulo_vaga"));
                vaga.setDescricaoVaga(rs.getString("descricao_vaga"));
                vaga.setSalario(rs.getInt("salario"));
                vagas.add(vaga);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar vagas: " + e.getMessage());
        }
        return vagas;
    }

    public void atualizarVaga(VagasDeEmprego vaga) throws SQLException {
        String sql = "UPDATE vagas SET id_empregadores = ?, titulo_vaga = ?, descricao_vaga = ?, salario = ? WHERE id_vagas = ?";

        try (Connection conexao = ConexaoBD.getConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, vaga.getIdEmpregadores());
            stmt.setString(2, vaga.getTituloVaga());
            stmt.setString(3, vaga.getDescricaoVaga());
            stmt.setInt(4, vaga.getSalario());
            stmt.setInt(5, vaga.getIdVagas());

            stmt.executeUpdate();
            System.out.println("Vaga atualizada com sucesso!");
        } catch (SQLException e) {
            throw new SQLException("Erro ao atualizar a vaga: " + e.getMessage());
        }
    }

    // Método para buscar uma vaga pelo ID
    public VagasDeEmprego buscarVagaPorId(int id_vagas) {
        VagasDeEmprego vaga = null;
        String sql = "SELECT * FROM vagas WHERE id_vagas = ?"; 

        try (Connection conexao = ConexaoBD.getConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, id_vagas);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    vaga = new VagasDeEmprego();
                    vaga.setIdVagas(rs.getInt("id_vagas"));
                    vaga.setIdEmpregadores(rs.getInt("id_empregadores"));
                    vaga.setTituloVaga(rs.getString("titulo_vaga"));
                    vaga.setDescricaoVaga(rs.getString("descricao_vaga"));
                    vaga.setSalario(rs.getInt("salario"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return vaga; 
    }

    // Método para excluir uma vaga
    public void excluirVaga(int id_vagas) {
        String sql = "DELETE FROM vagas WHERE id_vagas = ?"; 

        try (Connection conexao = ConexaoBD.getConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, id_vagas); 
            stmt.executeUpdate(); 
            System.out.println("Vaga excluída com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
