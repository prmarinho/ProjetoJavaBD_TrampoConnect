package javaapplication1;

/**
 *
 * @author p7
 */
public class MainApp {
    public static void main(String[] args) {
        // Instanciar o DAO
        UsuarioDAO usuarioDAO = new UsuarioDAO();

        // Criar um novo usuário
        Usuario usuario = new Usuario("Teste", "teste@gmail.com", "senha123");

        // Adicionar o usuário ao banco de dados
        usuarioDAO.adicionarUsuario(usuario);
    }
}