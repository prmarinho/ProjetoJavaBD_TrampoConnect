//@Paulo Ribeiro Marinho
package javaapplication1;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.sql.SQLException;

public class TelaVisualizarCandidaturas extends JFrame {

    private JTable tblCandidaturas;
    private JButton btnCarregar, btnAdicionar, btnEditar, btnSalvar, btnExcluir;
    private JTextField txtData, txtStatus;
    private JComboBox<Integer> cmbVagas, cmbPerfisCandidatos;

    public TelaVisualizarCandidaturas() {
        setTitle("Gerenciar Candidaturas");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        tblCandidaturas = new JTable();
        tblCandidaturas.setModel(new DefaultTableModel(
            new Object[][]{},
            new String[]{"ID_Candidaturas", "ID_Vagas", "ID_Perfis_Candidatos", "Data", "Status"}
        ));
        JScrollPane scrollPane = new JScrollPane(tblCandidaturas);
        add(scrollPane, BorderLayout.CENTER);

        JPanel panel = new JPanel(new GridLayout(2, 1));

        JPanel formPanel = new JPanel(new GridLayout(6, 2));
        formPanel.add(new JLabel("ID Vagas:"));
        cmbVagas = new JComboBox<>(); 
        formPanel.add(cmbVagas);

        formPanel.add(new JLabel("ID Perfis Candidatos:"));
        cmbPerfisCandidatos = new JComboBox<>(); 
        formPanel.add(cmbPerfisCandidatos);

        formPanel.add(new JLabel("Data:"));
        txtData = new JTextField();
        formPanel.add(txtData);

        formPanel.add(new JLabel("Status:"));
        txtStatus = new JTextField();
        formPanel.add(txtStatus);

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

        // Ações dos botões
        btnCarregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                carregarDados();
            }
        });

        btnAdicionar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adicionarCandidatura();
            }
        });

        btnEditar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editarCandidatura();
            }
        });

        btnExcluir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                excluirCandidatura();
            }
        });
    }

    private void carregarDados() {
        CandidaturasDAO candidaturasDAO = new CandidaturasDAO();
        List<Candidaturas> candidaturas = candidaturasDAO.listarCandidaturas();
        DefaultTableModel model = (DefaultTableModel) tblCandidaturas.getModel();
        model.setRowCount(0);
        for (Candidaturas candidatura : candidaturas) {
            model.addRow(new Object[]{
                candidatura.getIdCandidaturas(),
                candidatura.getIdVagas(),
                candidatura.getIdPerfisCandidatos(),
                candidatura.getData(),
                candidatura.getStatus()
            });
        }
    }

    private void adicionarCandidatura() {
        CandidaturasDAO candidaturasDAO = new CandidaturasDAO();
        Candidaturas novaCandidatura = new Candidaturas();
        novaCandidatura.setIdVagas((Integer) cmbVagas.getSelectedItem());
        novaCandidatura.setIdPerfisCandidatos((Integer) cmbPerfisCandidatos.getSelectedItem());
        novaCandidatura.setData(txtData.getText());
        novaCandidatura.setStatus(txtStatus.getText());

        candidaturasDAO.adicionarCandidatura(novaCandidatura);
        carregarDados(); 
    }

    private void excluirCandidatura() {
        int selectedRow = tblCandidaturas.getSelectedRow();
        if (selectedRow >= 0) {
            int idCandidatura = (int) tblCandidaturas.getValueAt(selectedRow, 0); 
            CandidaturasDAO candidaturasDAO = new CandidaturasDAO();
            candidaturasDAO.excluirCandidatura(idCandidatura);
            carregarDados();
        }
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> {
            new TelaVisualizarCandidaturas().setVisible(true);
        });
    }

    private void editarCandidatura() {
        int selectedRow = tblCandidaturas.getSelectedRow();

        if (selectedRow >= 0) {
            int idCandidatura = (int) tblCandidaturas.getValueAt(selectedRow, 0);

            CandidaturasDAO candidaturasDAO = new CandidaturasDAO();
            Candidaturas candidatura = candidaturasDAO.buscarCandidaturaPorId(idCandidatura);

            cmbVagas.setSelectedItem(candidatura.getIdVagas());
            cmbPerfisCandidatos.setSelectedItem(candidatura.getIdPerfisCandidatos());
            txtData.setText(candidatura.getData());
            txtStatus.setText(candidatura.getStatus());

            for (ActionListener al : btnSalvar.getActionListeners()) {
                btnSalvar.removeActionListener(al);
            }


            btnSalvar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    salvarAlteracoes(idCandidatura);
                }
            });

        } else {
            JOptionPane.showMessageDialog(this, "Por favor, selecione uma candidatura para editar.");
        }
    }

    private void salvarAlteracoes(int idCandidatura) {
        Integer idVagas = (Integer) cmbVagas.getSelectedItem();
        Integer idPerfisCandidatos = (Integer) cmbPerfisCandidatos.getSelectedItem();
        String data = txtData.getText();
        String status = txtStatus.getText();

        if (data.isEmpty() || status.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos os campos devem ser preenchidos.");
            return;
        }

        Candidaturas candidatura = new Candidaturas();
        candidatura.setIdCandidaturas(idCandidatura);
        candidatura.setIdVagas(idVagas);
        candidatura.setIdPerfisCandidatos(idPerfisCandidatos);
        candidatura.setData(data);
        candidatura.setStatus(status);

        CandidaturasDAO candidaturasDAO = new CandidaturasDAO();
        try {
            candidaturasDAO.atualizarCandidatura(candidatura);
            JOptionPane.showMessageDialog(this, "Candidatura atualizada com sucesso!");
            carregarDados(); 
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erro ao atualizar a candidatura: " + e.getMessage());
        }
    }
}
