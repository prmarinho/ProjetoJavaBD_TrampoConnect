//@Paulo Ribeiro Marinho
package javaapplication1;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.sql.SQLException;

public class TelaVisualizarVagasDeEmprego extends JFrame {

    private JTable tblVagas;
    private JButton btnCarregar, btnAdicionar, btnEditar, btnSalvar, btnExcluir;
    private JTextField txtTitulo, txtDescricao, txtSalario, txtIdEmpregadores;

    public TelaVisualizarVagasDeEmprego() {
        setTitle("Gerenciar Vagas de Emprego");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        tblVagas = new JTable();
        tblVagas.setModel(new DefaultTableModel(
            new Object[][]{},
            new String[]{"ID_Vagas", "ID Empregadores", "Título da Vaga", "Descrição da Vaga", "Salário"}
        ));
        JScrollPane scrollPane = new JScrollPane(tblVagas);
        add(scrollPane, BorderLayout.CENTER);

        JPanel panel = new JPanel(new GridLayout(2, 1));
        
        JPanel formPanel = new JPanel(new GridLayout(5, 2));
        formPanel.add(new JLabel("ID Empregadores:"));
        txtIdEmpregadores = new JTextField();
        formPanel.add(txtIdEmpregadores);

        formPanel.add(new JLabel("Título da Vaga:"));
        txtTitulo = new JTextField();
        formPanel.add(txtTitulo);

        formPanel.add(new JLabel("Descrição da Vaga:"));
        txtDescricao = new JTextField();
        formPanel.add(txtDescricao);

        formPanel.add(new JLabel("Salário:"));
        txtSalario = new JTextField();
        formPanel.add(txtSalario);

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
                adicionarVaga();
            }
        });

        btnEditar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editarVaga();
            }
        });

        btnExcluir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                excluirVaga();
            }
        });
    }

    private void carregarDados() {
        VagasDeEmpregoDAO vagasDeEmpregoDAO = new VagasDeEmpregoDAO();
        List<VagasDeEmprego> vagas = vagasDeEmpregoDAO.listarVagas();
        DefaultTableModel model = (DefaultTableModel) tblVagas.getModel();
        model.setRowCount(0);
        for (VagasDeEmprego vaga : vagas) {
            model.addRow(new Object[]{
                vaga.getIdVagas(),
                vaga.getIdEmpregadores(),
                vaga.getTituloVaga(),
                vaga.getDescricaoVaga(),
                vaga.getSalario()
            });
        }
    }

    private void adicionarVaga() {
        VagasDeEmpregoDAO vagasDeEmpregoDAO = new VagasDeEmpregoDAO();
        VagasDeEmprego novaVaga = new VagasDeEmprego();
        novaVaga.setIdEmpregadores(Integer.parseInt(txtIdEmpregadores.getText()));
        novaVaga.setTituloVaga(txtTitulo.getText());
        novaVaga.setDescricaoVaga(txtDescricao.getText());
        novaVaga.setSalario(Integer.parseInt(txtSalario.getText()));

        vagasDeEmpregoDAO.adicionarVaga(novaVaga);
        carregarDados(); 
    }

    private void excluirVaga() {
        int selectedRow = tblVagas.getSelectedRow();
        if (selectedRow >= 0) {
            int idVaga = (int) tblVagas.getValueAt(selectedRow, 0); 
            VagasDeEmpregoDAO vagasDeEmpregoDAO = new VagasDeEmpregoDAO();
            vagasDeEmpregoDAO.excluirVaga(idVaga);
            carregarDados(); 
        }
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> {
            new TelaVisualizarVagasDeEmprego().setVisible(true);
        });
    }
    
    private void editarVaga() {
        int selectedRow = tblVagas.getSelectedRow();

        if (selectedRow >= 0) {
            int idVaga = (int) tblVagas.getValueAt(selectedRow, 0);

            VagasDeEmpregoDAO vagasDeEmpregoDAO = new VagasDeEmpregoDAO();
            VagasDeEmprego vaga = vagasDeEmpregoDAO.buscarVagaPorId(idVaga);

            txtIdEmpregadores.setText(String.valueOf(vaga.getIdEmpregadores()));
            txtTitulo.setText(vaga.getTituloVaga());
            txtDescricao.setText(vaga.getDescricaoVaga());
            txtSalario.setText(String.valueOf(vaga.getSalario()));

            for (ActionListener al : btnSalvar.getActionListeners()) {
                btnSalvar.removeActionListener(al);
            }

            btnSalvar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    salvarAlteracoes(idVaga);
                }
            });

        } else {
            JOptionPane.showMessageDialog(this, "Por favor, selecione uma vaga para editar.");
        }
    }
    
    private void salvarAlteracoes(int idVaga) {
        int idEmpregadores = Integer.parseInt(txtIdEmpregadores.getText());
        String titulo = txtTitulo.getText();
        String descricao = txtDescricao.getText();
        int salario = Integer.parseInt(txtSalario.getText());

        if (titulo.isEmpty() || descricao.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos os campos devem ser preenchidos.");
            return;
        }

        VagasDeEmprego vaga = new VagasDeEmprego();
        vaga.setIdVagas(idVaga);
        vaga.setIdEmpregadores(idEmpregadores);
        vaga.setTituloVaga(titulo);
        vaga.setDescricaoVaga(descricao);
        vaga.setSalario(salario);

        VagasDeEmpregoDAO vagasDeEmpregoDAO = new VagasDeEmpregoDAO();
        try {
            vagasDeEmpregoDAO.atualizarVaga(vaga);
            JOptionPane.showMessageDialog(this, "Vaga atualizada com sucesso!");
            carregarDados();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erro ao atualizar a vaga: " + e.getMessage());
        }
    }
}
