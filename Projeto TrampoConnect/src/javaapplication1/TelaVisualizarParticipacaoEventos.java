//@Paulo Ribeiro Marinho
package javaapplication1;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.sql.SQLException;

public class TelaVisualizarParticipacaoEventos extends JFrame {

    private JTable tblParticipacoes;
    private JButton btnCarregar, btnAdicionar, btnEditar, btnSalvar, btnExcluir;
    private JTextField txtData;
    private JComboBox<Integer> cbPerfisCandidatos, cbEventos;

    public TelaVisualizarParticipacaoEventos() {
        setTitle("Gerenciar Participações em Eventos");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        tblParticipacoes = new JTable();
        tblParticipacoes.setModel(new DefaultTableModel(
            new Object[][]{},
            new String[]{"ID_Participacao", "ID_Perfil Candidato", "ID_Evento", "Data"}
        ));
        JScrollPane scrollPane = new JScrollPane(tblParticipacoes);
        add(scrollPane, BorderLayout.CENTER);

        JPanel panel = new JPanel(new GridLayout(2, 1));
        
        JPanel formPanel = new JPanel(new GridLayout(6, 2));
        formPanel.add(new JLabel("ID Perfil Candidato:"));
        cbPerfisCandidatos = new JComboBox<>();
        formPanel.add(cbPerfisCandidatos);

        formPanel.add(new JLabel("ID Evento:"));
        cbEventos = new JComboBox<>();
        formPanel.add(cbEventos);
        
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
                adicionarParticipacao();
            }
        });

        btnEditar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editarParticipacao();
            }
        });

        btnExcluir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                excluirParticipacao();
            }
        });
    }

    private void carregarDados() {
        ParticipacaoEventosDAO participacaoEventosDAO = new ParticipacaoEventosDAO();
        List<ParticipacaoEventos> participacoes = participacaoEventosDAO.listarParticipacoes();
        DefaultTableModel model = (DefaultTableModel) tblParticipacoes.getModel();
        model.setRowCount(0);
        for (ParticipacaoEventos participacao : participacoes) {
            model.addRow(new Object[]{
                participacao.getIdParticipacaoEventos(),
                participacao.getIdPerfisCandidatos(),
                participacao.getIdEventos(),
                participacao.getData()
            });
        }
    }

    private void adicionarParticipacao() {
        ParticipacaoEventosDAO participacaoEventosDAO = new ParticipacaoEventosDAO();
        ParticipacaoEventos novaParticipacao = new ParticipacaoEventos();
        novaParticipacao.setIdPerfisCandidatos((Integer) cbPerfisCandidatos.getSelectedItem());
        novaParticipacao.setIdEventos((Integer) cbEventos.getSelectedItem());
        novaParticipacao.setData(txtData.getText());

        participacaoEventosDAO.adicionarParticipacao(novaParticipacao);
        carregarDados();
    }

    private void excluirParticipacao() {
        int selectedRow = tblParticipacoes.getSelectedRow();
        if (selectedRow >= 0) {
            int idParticipacao = (int) tblParticipacoes.getValueAt(selectedRow, 0);
            ParticipacaoEventosDAO participacaoEventosDAO = new ParticipacaoEventosDAO();
            participacaoEventosDAO.excluirParticipacao(idParticipacao);
            carregarDados();
        }
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> {
            new TelaVisualizarParticipacaoEventos().setVisible(true);
        });
    }
    
    private void editarParticipacao() {
        int selectedRow = tblParticipacoes.getSelectedRow();

        if (selectedRow >= 0) {
            int idParticipacao = (int) tblParticipacoes.getValueAt(selectedRow, 0);

            ParticipacaoEventosDAO participacaoEventosDAO = new ParticipacaoEventosDAO();
            ParticipacaoEventos participacao = participacaoEventosDAO.buscarParticipacaoPorId(idParticipacao);

            cbPerfisCandidatos.setSelectedItem(participacao.getIdPerfisCandidatos());
            cbEventos.setSelectedItem(participacao.getIdEventos());
            txtData.setText(participacao.getData());

            for (ActionListener al : btnSalvar.getActionListeners()) {
                btnSalvar.removeActionListener(al);
            }

            btnSalvar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    salvarAlteracoes(idParticipacao);
                }
            });

        } else {
            JOptionPane.showMessageDialog(this, "Por favor, selecione uma participação para editar.");
        }
    }
    
    private void salvarAlteracoes(int idParticipacao) {
        int idPerfisCandidatos = (Integer) cbPerfisCandidatos.getSelectedItem();
        int idEventos = (Integer) cbEventos.getSelectedItem();
        String data = txtData.getText();

        if (data.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos os campos devem ser preenchidos.");
            return;
        }

        ParticipacaoEventos participacao = new ParticipacaoEventos();
        participacao.setIdParticipacaoEventos(idParticipacao);
        participacao.setIdPerfisCandidatos(idPerfisCandidatos);
        participacao.setIdEventos(idEventos);
        participacao.setData(data);

        ParticipacaoEventosDAO participacaoEventosDAO = new ParticipacaoEventosDAO();
        try {
            participacaoEventosDAO.atualizarParticipacao(participacao);
            JOptionPane.showMessageDialog(this, "Participação atualizada com sucesso!");
            carregarDados();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erro ao atualizar a participação: " + e.getMessage());
        }
    }
}
