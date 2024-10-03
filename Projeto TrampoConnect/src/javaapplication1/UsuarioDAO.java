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

public class UsuarioDAO {
    public void adicionarUsuario(Usuario usuario) {
        String sql = "INSERT INTO usuarios (nomeCompleto, email, senha) VALUES (?, ?, ?)";

        try (Connection conexao = ConexaoBD.getConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, usuario.getNomeCompleto());
            stmt.setString(2, usuario.getEmail());
            stmt.setString(3, usuario.getSenha());

            stmt.executeUpdate();
            System.out.println("Usuário adicionado com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao adicionar usuário: " + e.getMessage());
        }
    }

    public List<Usuario> listarUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM usuarios";

        try (Connection conexao = ConexaoBD.getConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setIdUsuarios(rs.getInt("id_usuarios"));
                usuario.setNomeCompleto(rs.getString("nomeCompleto"));
                usuario.setEmail(rs.getString("email"));
                usuario.setSenha(rs.getString("senha"));
                usuarios.add(usuario);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar usuários: " + e.getMessage());
        }
        return usuarios;
    }
    public void atualizarUsuario(Usuario usuario) throws SQLException {
    String sql = "UPDATE usuarios SET NomeCompleto = ?, Email = ? WHERE ID_Usuarios = ?";

    try (Connection conexao = ConexaoBD.getConexao();
         PreparedStatement stmt = conexao.prepareStatement(sql)) {

        stmt.setString(1, usuario.getNomeCompleto());
        stmt.setString(2, usuario.getEmail());
        stmt.setInt(3, usuario.getIdUsuarios());

        stmt.executeUpdate();  // Executa a atualização no banco de dados

    } catch (SQLException e) {
        throw new SQLException("Erro ao atualizar o usuário: " + e.getMessage());
    }
}
    public Usuario buscarUsuarioPorId(int idUsuario) {
    Usuario usuario = null;
    String sql = "SELECT * FROM usuarios WHERE ID_Usuarios = ?";  // Substitua 'usuarios' pelo nome correto da tabela.

    try (Connection conexao = ConexaoBD.getConexao();
         PreparedStatement stmt = conexao.prepareStatement(sql)) {

        stmt.setInt(1, idUsuario);  // Define o valor do parâmetro ID_Usuarios na consulta.
        try (ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                usuario = new Usuario();
                usuario.setIdUsuarios(rs.getInt("ID_Usuarios"));
                usuario.setNomeCompleto(rs.getString("NomeCompleto"));
                usuario.setEmail(rs.getString("Email"));
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return usuario;  // Retorna o usuário ou null se não for encontrado.
}
    public void excluirUsuario(int idUsuario) {
    String sql = "DELETE FROM usuarios WHERE ID_Usuarios = ?";  // Substitua 'usuarios' pelo nome da sua tabela.

    try (Connection conexao = ConexaoBD.getConexao();
         PreparedStatement stmt = conexao.prepareStatement(sql)) {

        stmt.setInt(1, idUsuario);  // Define o ID do usuário a ser excluído.
        stmt.executeUpdate();  // Executa a instrução DELETE no banco de dados.

    } catch (SQLException e) {
        e.printStackTrace();
    }
}
    
}

