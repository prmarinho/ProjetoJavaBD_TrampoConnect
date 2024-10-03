//@Paulo Ribeiro Marinho
package javaapplication1;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.sql.SQLException;

public class TelaVisualizarProgramasDeSuporte extends JFrame {

    private JTable tblProgramas;
    private JButton btnCarregar, btnAdicionar, btnEditar, btnSalvar, btnExcluir;
    private JTextField txtPrograma, txtDescricao;

    public TelaVisualizarProgramasDeSuporte() {
        setTitle("Gerenciar Programas de Suporte");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        tblProgramas = new JTable();
        tblProgramas.setModel(new DefaultTableModel(
            new Object[][]{},
            new String[]{"ID_Programas", "Programa", "Descrição"}
        ));
        JScrollPane scrollPane = new JScrollPane(tblProgramas);
        add(scrollPane, BorderLayout.CENTER);

        JPanel panel = new JPanel(new GridLayout(2, 1));
        
        JPanel formPanel = new JPanel(new GridLayout(4, 2));
        formPanel.add(new JLabel("Programa:"));
        txtPrograma = new JTextField();
        formPanel.add(txtPrograma);

        formPanel.add(new JLabel("Descrição:"));
        txtDescricao = new JTextField();
        formPanel.add(txtDescricao);
        
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
                adicionarPrograma();
            }
        });

        btnEditar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editarPrograma();
            }
        });

        btnExcluir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                excluirPrograma();
            }
        });
    }

    private void carregarDados() {
        ProgramasDeSuporteDAO programasDeSuporteDAO = new ProgramasDeSuporteDAO();
        List<ProgramasDeSuporte> programas = programasDeSuporteDAO.listarProgramas();
        DefaultTableModel model = (DefaultTableModel) tblProgramas.getModel();
        model.setRowCount(0);
        for (ProgramasDeSuporte programa : programas) {
            model.addRow(new Object[]{
                programa.getIdProgramas(),
                programa.getPrograma(),
                programa.getDescricao()
            });
        }
    }

    private void adicionarPrograma() {
        ProgramasDeSuporteDAO programasDeSuporteDAO = new ProgramasDeSuporteDAO();
        ProgramasDeSuporte novoPrograma = new ProgramasDeSuporte();
        novoPrograma.setPrograma(txtPrograma.getText());
        novoPrograma.setDescricao(txtDescricao.getText());

        programasDeSuporteDAO.adicionarPrograma(novoPrograma);
        carregarDados();
    }

    private void excluirPrograma() {
        int selectedRow = tblProgramas.getSelectedRow();
        if (selectedRow >= 0) {
            int idPrograma = (int) tblProgramas.getValueAt(selectedRow, 0);
            ProgramasDeSuporteDAO programasDeSuporteDAO = new ProgramasDeSuporteDAO();
            programasDeSuporteDAO.excluirPrograma(idPrograma);
            carregarDados(); 
        }
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> {
            new TelaVisualizarProgramasDeSuporte().setVisible(true);
        });
    }

    private void editarPrograma() {
        int selectedRow = tblProgramas.getSelectedRow();

        if (selectedRow >= 0) {
            int idPrograma = (int) tblProgramas.getValueAt(selectedRow, 0);

            ProgramasDeSuporteDAO programasDeSuporteDAO = new ProgramasDeSuporteDAO();
            ProgramasDeSuporte programa = programasDeSuporteDAO.buscarProgramaPorId(idPrograma);

            txtPrograma.setText(programa.getPrograma());
            txtDescricao.setText(programa.getDescricao());

            for (ActionListener al : btnSalvar.getActionListeners()) {
                btnSalvar.removeActionListener(al);
            }

            btnSalvar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    salvarAlteracoes(idPrograma);
                }
            });

        } else {
            JOptionPane.showMessageDialog(this, "Por favor, selecione um programa para editar.");
        }
    }

    private void salvarAlteracoes(int idPrograma) {
        String programa = txtPrograma.getText();
        String descricao = txtDescricao.getText();

        if (programa.isEmpty() || descricao.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos os campos devem ser preenchidos.");
            return;
        }

        ProgramasDeSuporte programaAtualizado = new ProgramasDeSuporte();
        programaAtualizado.setIdProgramas(idPrograma);
        programaAtualizado.setPrograma(programa);
        programaAtualizado.setDescricao(descricao);

        ProgramasDeSuporteDAO programasDeSuporteDAO = new ProgramasDeSuporteDAO();
        try {
            programasDeSuporteDAO.atualizarPrograma(programaAtualizado);
            JOptionPane.showMessageDialog(this, "Programa atualizado com sucesso!");
            carregarDados(); 
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erro ao atualizar o programa: " + e.getMessage());
        }
    }
}
