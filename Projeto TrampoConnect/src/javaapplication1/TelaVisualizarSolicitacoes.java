//@Paulo Ribeiro Marinho
package javaapplication1;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.sql.SQLException;

public class TelaVisualizarSolicitacoes extends JFrame {

    private JTable tblSolicitacoes;
    private JButton btnCarregar, btnAdicionar, btnEditar, btnSalvar, btnExcluir;
    private JTextField txtIdUsuarios, txtSolicitacao, txtData;

    public TelaVisualizarSolicitacoes() {
        setTitle("Gerenciar Solicitações de Assistência");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        tblSolicitacoes = new JTable();
        tblSolicitacoes.setModel(new DefaultTableModel(
            new Object[][]{},
            new String[]{"ID Solicitação", "ID Usuários", "Solicitação", "Data"}
        ));
        JScrollPane scrollPane = new JScrollPane(tblSolicitacoes);
        add(scrollPane, BorderLayout.CENTER);

        JPanel panel = new JPanel(new GridLayout(2, 1));
        
        JPanel formPanel = new JPanel(new GridLayout(4, 2));
        formPanel.add(new JLabel("ID Usuários:"));
        txtIdUsuarios = new JTextField();
        formPanel.add(txtIdUsuarios);

        formPanel.add(new JLabel("Solicitação:"));
        txtSolicitacao = new JTextField();
        formPanel.add(txtSolicitacao);

        formPanel.add(new JLabel("Data:"));
        txtData = new JTextField();
        formPanel.add(txtData);

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
                adicionarSolicitacao();
            }
        });

        btnEditar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editarSolicitacao();
            }
        });

        btnExcluir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                excluirSolicitacao();
            }
        });
    }

    private void carregarDados() {
        SolicitarAssistenciaDAO solicitarAssistenciaDAO = new SolicitarAssistenciaDAO();
        List<SolicitarAssistencia> solicitacoes = solicitarAssistenciaDAO.listarSolicitacoes();
        DefaultTableModel model = (DefaultTableModel) tblSolicitacoes.getModel();
        model.setRowCount(0);
        for (SolicitarAssistencia solicitacao : solicitacoes) {
            model.addRow(new Object[]{
                solicitacao.getIdSolicitarAssistencia(),
                solicitacao.getIdUsuarios(),
                solicitacao.getSolicitacao(),
                solicitacao.getData()
            });
        }
    }

    private void adicionarSolicitacao() {
        SolicitarAssistenciaDAO solicitarAssistenciaDAO = new SolicitarAssistenciaDAO();
        SolicitarAssistencia novaSolicitacao = new SolicitarAssistencia();
        novaSolicitacao.setIdUsuarios(Integer.parseInt(txtIdUsuarios.getText()));
        novaSolicitacao.setSolicitacao(txtSolicitacao.getText());
        novaSolicitacao.setData(txtData.getText());

        solicitarAssistenciaDAO.adicionarSolicitacao(novaSolicitacao);
        carregarDados();
    }

    private void excluirSolicitacao() {
        int selectedRow = tblSolicitacoes.getSelectedRow();
        if (selectedRow >= 0) {
            int idSolicitacao = (int) tblSolicitacoes.getValueAt(selectedRow, 0);
            SolicitarAssistenciaDAO solicitarAssistenciaDAO = new SolicitarAssistenciaDAO();
            solicitarAssistenciaDAO.excluirSolicitacao(idSolicitacao);
            carregarDados();
        }
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> {
            new TelaVisualizarSolicitacoes().setVisible(true);
        });
    }
    
    private void editarSolicitacao() {
        int selectedRow = tblSolicitacoes.getSelectedRow();

        if (selectedRow >= 0) {
            int idSolicitacao = (int) tblSolicitacoes.getValueAt(selectedRow, 0);

            SolicitarAssistenciaDAO solicitarAssistenciaDAO = new SolicitarAssistenciaDAO();
            SolicitarAssistencia solicitacao = solicitarAssistenciaDAO.buscarSolicitacaoPorId(idSolicitacao);

            txtIdUsuarios.setText(String.valueOf(solicitacao.getIdUsuarios()));
            txtSolicitacao.setText(solicitacao.getSolicitacao());
            txtData.setText(solicitacao.getData());

            for (ActionListener al : btnSalvar.getActionListeners()) {
                btnSalvar.removeActionListener(al);
            }

            btnSalvar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    salvarAlteracoes(idSolicitacao);
                }
            });

        } else {
            JOptionPane.showMessageDialog(this, "Por favor, selecione uma solicitação para editar.");
        }
    }
    
    private void salvarAlteracoes(int idSolicitacao) {
        int idUsuarios = Integer.parseInt(txtIdUsuarios.getText());
        String solicitacao = txtSolicitacao.getText();
        String data = txtData.getText();

        if (solicitacao.isEmpty() || data.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos os campos devem ser preenchidos.");
            return;
        }

        SolicitarAssistencia novaSolicitacao = new SolicitarAssistencia();
        novaSolicitacao.setIdSolicitarAssistencia(idSolicitacao);
        novaSolicitacao.setIdUsuarios(idUsuarios);
        novaSolicitacao.setSolicitacao(solicitacao);
        novaSolicitacao.setData(data);

        SolicitarAssistenciaDAO solicitarAssistenciaDAO = new SolicitarAssistenciaDAO();
        try {
            solicitarAssistenciaDAO.atualizarSolicitacao(novaSolicitacao);
            JOptionPane.showMessageDialog(this, "Solicitação atualizada com sucesso!");
            carregarDados(); 
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erro ao atualizar a solicitação: " + e.getMessage());
        }
    }
}