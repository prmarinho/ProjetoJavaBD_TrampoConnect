//@Paulo Ribeiro Marinho
package javaapplication1;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.sql.SQLException;


public class TelaVisualizarUsuarios extends JFrame {

    private JTable tblUsuarios;
    private JButton btnCarregar, btnAdicionar, btnEditar, btnSalvar, btnExcluir;
    private JTextField txtNome, txtSenha, txtEmail;

    public TelaVisualizarUsuarios() {
        setTitle("Gerenciar Usuários");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);


        tblUsuarios = new JTable();
        tblUsuarios.setModel(new DefaultTableModel(
            new Object[][]{},
            new String[]{"ID_Usuarios", "Nome Completo", "Email"}
        ));
        JScrollPane scrollPane = new JScrollPane(tblUsuarios);
        add(scrollPane, BorderLayout.CENTER);

        JPanel panel = new JPanel(new GridLayout(2, 1));
        
        JPanel formPanel = new JPanel(new GridLayout(6, 2));
        formPanel.add(new JLabel("Nome Completo:"));
        txtNome = new JTextField();
        formPanel.add(txtNome);

        formPanel.add(new JLabel("Email:"));
        txtEmail = new JTextField();
        formPanel.add(txtEmail);
        
        formPanel.add(new JLabel("Senha:"));
        txtSenha = new JTextField();
        formPanel.add(txtSenha);
        
        panel.add(formPanel);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        btnCarregar = new JButton("Carregar Dados");
        btnAdicionar = new JButton("Adicionar");
        btnEditar = new JButton("Editar");
        btnExcluir = new JButton("Excluir");
        btnSalvar = new JButton("Salvar");

        buttonPanel.add(btnCarregar);
        buttonPanel.add(btnAdicionar);
        buttonPanel.add(btnEditar);
        buttonPanel.add(btnSalvar);
        buttonPanel.add(btnExcluir);
        
        panel.add(buttonPanel);
        add(panel, BorderLayout.SOUTH);

        btnCarregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                carregarDados();
            }
        });

        btnAdicionar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adicionarUsuario();
            }
        });

        btnEditar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editarUsuario();
            }
        });

        btnExcluir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                excluirUsuario();
            }
        });

    }

    private void carregarDados() {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        List<Usuario> usuarios = usuarioDAO.listarUsuarios();
        DefaultTableModel model = (DefaultTableModel) tblUsuarios.getModel();
        model.setRowCount(0);
        for (Usuario usuario : usuarios) {
            model.addRow(new Object[]{
                usuario.getIdUsuarios(),
                usuario.getNomeCompleto(),
                usuario.getEmail()
            });
        }
    }

    private void adicionarUsuario() {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Usuario novoUsuario = new Usuario();
        novoUsuario.setNomeCompleto(txtNome.getText());
        novoUsuario.setEmail(txtEmail.getText());
        novoUsuario.setSenha(txtSenha.getText());


        usuarioDAO.adicionarUsuario(novoUsuario);
        carregarDados();
    }


    private void excluirUsuario() {
        int selectedRow = tblUsuarios.getSelectedRow();
        if (selectedRow >= 0) {
            int idUsuario = (int) tblUsuarios.getValueAt(selectedRow, 0);
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            usuarioDAO.excluirUsuario(idUsuario);
            carregarDados(); 
        }
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> {
            new TelaVisualizarUsuarios().setVisible(true);
        });
    }
    
    private void editarUsuario() {
    int selectedRow = tblUsuarios.getSelectedRow();

    if (selectedRow >= 0) {
        int idUsuario = (int) tblUsuarios.getValueAt(selectedRow, 0);

        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Usuario usuario = usuarioDAO.buscarUsuarioPorId(idUsuario);

        txtNome.setText(usuario.getNomeCompleto());
        txtEmail.setText(usuario.getEmail());


        for (ActionListener al : btnSalvar.getActionListeners()) {
            btnSalvar.removeActionListener(al);
        }

        btnSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                salvarAlteracoes(idUsuario);
            }
        });

    } else {
        JOptionPane.showMessageDialog(this, "Por favor, selecione um usuário para editar.");
    }
}
    
    private void salvarAlteracoes(int idUsuario) {
    String nomeCompleto = txtNome.getText();
    String email = txtEmail.getText();


    if (nomeCompleto.isEmpty() || email.isEmpty() || nomeCompleto.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Todos os campos devem ser preenchidos.");
        return;
    }

    Usuario usuario = new Usuario();
    usuario.setIdUsuarios(idUsuario);
    usuario.setNomeCompleto(nomeCompleto);
    usuario.setEmail(email);


    UsuarioDAO usuarioDAO = new UsuarioDAO();
    try {
        usuarioDAO.atualizarUsuario(usuario);
        JOptionPane.showMessageDialog(this, "Usuário atualizado com sucesso!");
        carregarDados();
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Erro ao atualizar o usuário: " + e.getMessage());
    }
}


}
