//@Paulo Ribeiro Marinho
package javaapplication1;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.sql.SQLException;

public class TelaVisualizarFeedback extends JFrame {

    private JTable tblFeedback;
    private JButton btnCarregar, btnAdicionar, btnEditar, btnSalvar, btnExcluir;
    private JTextField txtIdVagas, txtIdPerfisCandidatos, txtFeedback, txtData;

    public TelaVisualizarFeedback() {
        setTitle("Gerenciar Feedbacks");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        tblFeedback = new JTable();
        tblFeedback.setModel(new DefaultTableModel(
            new Object[][]{},
            new String[]{"ID_Feedback", "ID_Vagas", "ID_Perfis_Candidatos", "Feedback", "Data"}
        ));
        JScrollPane scrollPane = new JScrollPane(tblFeedback);
        add(scrollPane, BorderLayout.CENTER);

        // Painel inferior com campos e botões
        JPanel panel = new JPanel(new GridLayout(2, 1));
        
        JPanel formPanel = new JPanel(new GridLayout(6, 2));
        formPanel.add(new JLabel("ID Vagas:"));
        txtIdVagas = new JTextField();
        formPanel.add(txtIdVagas);

        formPanel.add(new JLabel("ID Perfis Candidatos:"));
        txtIdPerfisCandidatos = new JTextField();
        formPanel.add(txtIdPerfisCandidatos);
        
        formPanel.add(new JLabel("Feedback:"));
        txtFeedback = new JTextField();
        formPanel.add(txtFeedback);
        
        formPanel.add(new JLabel("Data:"));
        txtData = new JTextField();
        formPanel.add(txtData);
        
        panel.add(formPanel);

        // Painel de botões
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
                adicionarFeedback();
            }
        });

        btnEditar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editarFeedback();
            }
        });

        btnExcluir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                excluirFeedback();
            }
        });

    }

    private void carregarDados() {
        FeedbackDAO feedbackDAO = new FeedbackDAO();
        List<Feedback> feedbacks = feedbackDAO.listarFeedbacks();
        DefaultTableModel model = (DefaultTableModel) tblFeedback.getModel();
        model.setRowCount(0);
        for (Feedback feedback : feedbacks) {
            model.addRow(new Object[]{
                feedback.getIdFeedback(),
                feedback.getIdVagas(),
                feedback.getIdPerfisCandidatos(),
                feedback.getFeedback(),
                feedback.getData()
            });
        }
    }

    private void adicionarFeedback() {
        FeedbackDAO feedbackDAO = new FeedbackDAO();
        Feedback novoFeedback = new Feedback();
        novoFeedback.setIdVagas(Integer.parseInt(txtIdVagas.getText()));
        novoFeedback.setIdPerfisCandidatos(Integer.parseInt(txtIdPerfisCandidatos.getText()));
        novoFeedback.setFeedback(txtFeedback.getText());
        novoFeedback.setData(txtData.getText());

        feedbackDAO.adicionarFeedback(novoFeedback);
        carregarDados();
    }

    private void excluirFeedback() {
        int selectedRow = tblFeedback.getSelectedRow();
        if (selectedRow >= 0) {
            int idFeedback = (int) tblFeedback.getValueAt(selectedRow, 0);
            FeedbackDAO feedbackDAO = new FeedbackDAO();
            feedbackDAO.excluirFeedback(idFeedback);
            carregarDados();
        }
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> {
            new TelaVisualizarFeedback().setVisible(true);
        });
    }
    
    private void editarFeedback() {
        int selectedRow = tblFeedback.getSelectedRow();

        if (selectedRow >= 0) {
            int idFeedback = (int) tblFeedback.getValueAt(selectedRow, 0);

            FeedbackDAO feedbackDAO = new FeedbackDAO();
            Feedback feedback = feedbackDAO.buscarFeedbackPorId(idFeedback);

            txtIdVagas.setText(String.valueOf(feedback.getIdVagas()));
            txtIdPerfisCandidatos.setText(String.valueOf(feedback.getIdPerfisCandidatos()));
            txtFeedback.setText(feedback.getFeedback());
            txtData.setText(feedback.getData());

            for (ActionListener al : btnSalvar.getActionListeners()) {
                btnSalvar.removeActionListener(al);
            }

            btnSalvar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    salvarAlteracoes(idFeedback);
                }
            });

        } else {
            JOptionPane.showMessageDialog(this, "Por favor, selecione um feedback para editar.");
        }
    }
    
    private void salvarAlteracoes(int idFeedback) {
        int idVagas = Integer.parseInt(txtIdVagas.getText());
        int idPerfisCandidatos = Integer.parseInt(txtIdPerfisCandidatos.getText());
        String feedbackTexto = txtFeedback.getText();
        String data = txtData.getText();

        if (feedbackTexto.isEmpty() || data.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos os campos devem ser preenchidos.");
            return;
        }

        Feedback feedback = new Feedback();
        feedback.setIdFeedback(idFeedback);
        feedback.setIdVagas(idVagas);
        feedback.setIdPerfisCandidatos(idPerfisCandidatos);
        feedback.setFeedback(feedbackTexto);
        feedback.setData(data);

        FeedbackDAO feedbackDAO = new FeedbackDAO();
        try {
            feedbackDAO.atualizarFeedback(feedback);
            JOptionPane.showMessageDialog(this, "Feedback atualizado com sucesso!");
            carregarDados(); 
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erro ao atualizar o feedback: " + e.getMessage());
        }
    }
}
