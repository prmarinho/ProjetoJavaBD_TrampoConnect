package javaapplication1;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaUsuarios extends JFrame {
    private JTextField txtNomeCompleto;
    private JTextField txtEmail;
    private JPasswordField txtSenha;
    private JButton btnAdicionar;

    public TelaUsuarios() {
        // Configurar o layout e os componentes aqui

        // Exemplo de evento para o botão Adicionar
        btnAdicionar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Captura os dados do formulário
                String nomeCompleto = txtNomeCompleto.getText();
                String email = txtEmail.getText();
                String senha = new String(txtSenha.getPassword());

                // Cria o objeto Usuario
                Usuario usuario = new Usuario(nomeCompleto, email, senha);

                // Usa a classe DAO para adicionar o usuário ao banco de dados
                UsuarioDAO usuarioDAO = new UsuarioDAO();
                usuarioDAO.adicionarUsuario(usuario);

                // Exibir uma mensagem de sucesso
                JOptionPane.showMessageDialog(null, "Usuário adicionado com sucesso!");
                
                // Limpa os campos
                txtNomeCompleto.setText("");
                txtEmail.setText("");
                txtSenha.setText("");
            }
        });
    }

    // Método principal para rodar a interface gráfica
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> {
            new TelaUsuarios().setVisible(true);
        });
    }
}