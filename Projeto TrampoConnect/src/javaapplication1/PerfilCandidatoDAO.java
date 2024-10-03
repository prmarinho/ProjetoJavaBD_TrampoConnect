//@Paulo Ribeiro Marinho
package javaapplication1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PerfilCandidatoDAO {

    public void adicionarPerfilCandidato(PerfilCandidato perfil) {
        String sql = "INSERT INTO perfis_candidatos (habilidade, experiencia) VALUES (?, ?)";

        try (Connection conexao = ConexaoBD.getConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, perfil.getHabilidade());
            stmt.setString(2, perfil.getExperiencia());

            stmt.executeUpdate();
            System.out.println("Perfil de candidato adicionado com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao adicionar perfil de candidato: " + e.getMessage());
        }
    }


    public List<PerfilCandidato> listarPerfisCandidatos() {
        List<PerfilCandidato> perfisCandidatos = new ArrayList<>();
        String sql = "SELECT * FROM perfis_candidatos";

        try (Connection conexao = ConexaoBD.getConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                PerfilCandidato perfil = new PerfilCandidato();
                perfil.setHabilidade(rs.getString("habilidade"));
                perfil.setExperiencia(rs.getString("experiencia"));

                perfisCandidatos.add(perfil);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar perfis de candidatos: " + e.getMessage());
        }

        return perfisCandidatos;
    }

    public void atualizarPerfilCandidato(PerfilCandidato perfil) throws SQLException {
        String sql = "UPDATE perfis_candidatos SET habilidade = ?, experiencia = ? WHERE id_perfil_candidato = ?";

        try (Connection conexao = ConexaoBD.getConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, perfil.getHabilidade());
            stmt.setString(2, perfil.getExperiencia());
            stmt.setInt(3, perfil.getId_perfis_candidatos());

            stmt.executeUpdate();
            System.out.println("Perfil de candidato atualizado com sucesso!");

        } catch (SQLException e) {
            throw new SQLException("Erro ao atualizar perfil de candidato: " + e.getMessage());
        }
    }

    public PerfilCandidato buscarPerfilCandidatoPorId(int idPerfilCandidato) {
        PerfilCandidato perfil = null;
        String sql = "SELECT * FROM perfis_candidatos WHERE id_perfis_candidatos = ?";

        try (Connection conexao = ConexaoBD.getConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, idPerfilCandidato);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    perfil = new PerfilCandidato();
                    perfil.setId_perfis_candidatos(rs.getInt("id_perfis_candidatos"));
                    perfil.setHabilidade(rs.getString("habilidade"));
                    perfil.setExperiencia(rs.getString("experiencia"));
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar perfil de candidato por ID: " + e.getMessage());
        }

        return perfil;
    }

    public void excluirPerfilCandidato(int idPerfilCandidato) {
        String sql = "DELETE FROM perfis_candidatos WHERE id_perfis_candidatos = ?";

        try (Connection conexao = ConexaoBD.getConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, idPerfilCandidato);
            stmt.executeUpdate();
            System.out.println("Perfil de candidato excluído com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao excluir perfil de candidato: " + e.getMessage());
        }
    }
}
