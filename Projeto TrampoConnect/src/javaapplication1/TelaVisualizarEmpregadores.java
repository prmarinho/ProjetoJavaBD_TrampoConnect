//@Paulo Ribeiro Marinho
package javaapplication1;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.sql.SQLException;

public class TelaVisualizarEmpregadores extends JFrame {

    private JTable tblEmpregadores;
    private JButton btnCarregar, btnAdicionar, btnEditar, btnSalvar, btnExcluir;
    private JTextField txtEmpresa, txtCnpj, txtEmail;

    public TelaVisualizarEmpregadores() {
        setTitle("Gerenciar Empregadores");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);


        tblEmpregadores = new JTable();
        tblEmpregadores.setModel(new DefaultTableModel(
            new Object[][]{},
            new String[]{"ID_Empregadores", "Empresa", "CNPJ", "Email"}
        ));
        JScrollPane scrollPane = new JScrollPane(tblEmpregadores);
        add(scrollPane, BorderLayout.CENTER);

        JPanel panel = new JPanel(new GridLayout(2, 1));
        
        JPanel formPanel = new JPanel(new GridLayout(3, 2));
        formPanel.add(new JLabel("Empresa:"));
        txtEmpresa = new JTextField();
        formPanel.add(txtEmpresa);

        formPanel.add(new JLabel("CNPJ:"));
        txtCnpj = new JTextField();
        formPanel.add(txtCnpj);

        formPanel.add(new JLabel("Email:"));
        txtEmail = new JTextField();
        formPanel.add(txtEmail);
        
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
                adicionarEmpregador();
            }
        });

        btnEditar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editarEmpregador();
            }
        });

        btnExcluir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                excluirEmpregador();
            }
        });

    }

    private void carregarDados() {
        EmpregadoresDAO empregadoresDAO = new EmpregadoresDAO();
        List<Empregadores> empregadores = empregadoresDAO.listarEmpregadores();
        DefaultTableModel model = (DefaultTableModel) tblEmpregadores.getModel();
        model.setRowCount(0);
        for (Empregadores empregador : empregadores) {
            model.addRow(new Object[]{
                empregador.getIdEmpregadores(),
                empregador.getEmpresa(),
                empregador.getCnpj(),
                empregador.getEmail(),
            });
        }
    }

    private void adicionarEmpregador() {
        EmpregadoresDAO empregadoresDAO = new EmpregadoresDAO();
        Empregadores novoEmpregador = new Empregadores();
        novoEmpregador.setEmpresa(txtEmpresa.getText());
        novoEmpregador.setCnpj(txtCnpj.getText());
        novoEmpregador.setEmail(txtEmail.getText());

        empregadoresDAO.adicionarEmpregador(novoEmpregador);
        carregarDados();
    }

    private void excluirEmpregador() {
        int selectedRow = tblEmpregadores.getSelectedRow();
        if (selectedRow >= 0) {
            int idEmpregador = (int) tblEmpregadores.getValueAt(selectedRow, 0);
            EmpregadoresDAO empregadoresDAO = new EmpregadoresDAO();
            empregadoresDAO.excluirEmpregador(idEmpregador);
            carregarDados();
        }
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> {
            new TelaVisualizarEmpregadores().setVisible(true);
        });
    }
    
    private void editarEmpregador() {
        int selectedRow = tblEmpregadores.getSelectedRow();

        if (selectedRow >= 0) {
            int id_empregadores = (int) tblEmpregadores.getValueAt(selectedRow, 0);

            EmpregadoresDAO empregadoresDAO = new EmpregadoresDAO();
            Empregadores empregador = empregadoresDAO.buscarEmpregadorPorId(id_empregadores);

            if (empregador != null) {
                txtEmpresa.setText(empregador.getEmpresa());
                txtCnpj.setText(empregador.getCnpj());
                txtEmail.setText(empregador.getEmail());

                for (ActionListener al : btnSalvar.getActionListeners()) {
                    btnSalvar.removeActionListener(al);
                }

                btnSalvar.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        salvarAlteracoes(id_empregadores);
                    }
                });

            } else {
                JOptionPane.showMessageDialog(this, "Erro: Empregador não encontrado.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, selecione um empregador para editar.");
        }
    }
    
    private void salvarAlteracoes(int id_empregadores) {
        String empresa = txtEmpresa.getText();
        String cnpj = txtCnpj.getText();
        String email = txtEmail.getText();

        if (empresa.isEmpty() || cnpj.isEmpty() || email.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos os campos devem ser preenchidos.");
            return;
        }

        Empregadores empregador = new Empregadores();
        empregador.setIdEmpregadores(id_empregadores);
        empregador.setEmpresa(empresa);
        empregador.setCnpj(cnpj);
        empregador.setEmail(email);

        EmpregadoresDAO empregadoresDAO = new EmpregadoresDAO();
        try {
            empregadoresDAO.atualizarEmpregador(empregador);
            JOptionPane.showMessageDialog(this, "Empregador atualizado com sucesso!");
            carregarDados();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erro ao atualizar o empregador: " + e.getMessage());
        }
    }

}
