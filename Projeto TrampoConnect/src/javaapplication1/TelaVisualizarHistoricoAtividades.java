//@Paulo Ribeiro Marinho
package javaapplication1;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.sql.SQLException;

public class TelaVisualizarHistoricoAtividades extends JFrame {

    private JTable tblAtividades;
    private JButton btnCarregar, btnAdicionar, btnEditar, btnSalvar, btnExcluir;
    private JTextField txtDescricao, txtData, txtIdUsuario;

    public TelaVisualizarHistoricoAtividades() {
        setTitle("Gerenciar Histórico de Atividades");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        tblAtividades = new JTable();
        tblAtividades.setModel(new DefaultTableModel(
            new Object[][]{},
            new String[]{"ID_Historico_Atividades", "ID_Usuarios", "Descrição", "Data"}
        ));
        JScrollPane scrollPane = new JScrollPane(tblAtividades);
        add(scrollPane, BorderLayout.CENTER);

        JPanel panel = new JPanel(new GridLayout(2, 1));
        
        JPanel formPanel = new JPanel(new GridLayout(6, 2));
        formPanel.add(new JLabel("ID Usuário:"));
        txtIdUsuario = new JTextField();
        formPanel.add(txtIdUsuario);

        formPanel.add(new JLabel("Descrição:"));
        txtDescricao = new JTextField();
        formPanel.add(txtDescricao);
        
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
                adicionarAtividade();
            }
        });

        btnEditar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editarAtividade();
            }
        });

        btnExcluir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                excluirAtividade();
            }
        });

    }

    private void carregarDados() {
        HistoricoAtividadesDAO atividadesDAO = new HistoricoAtividadesDAO();
        List<HistoricoAtividades> atividades = atividadesDAO.listarAtividades();
        DefaultTableModel model = (DefaultTableModel) tblAtividades.getModel();
        model.setRowCount(0);
        for (HistoricoAtividades atividade : atividades) {
            model.addRow(new Object[]{
                atividade.getIdHistoricoAtividades(),
                atividade.getIdUsuarios(),
                atividade.getDescricao(),
                atividade.getData()
            });
        }
    }

    private void adicionarAtividade() {
        HistoricoAtividadesDAO atividadesDAO = new HistoricoAtividadesDAO();
        HistoricoAtividades novaAtividade = new HistoricoAtividades();
        novaAtividade.setIdUsuarios(Integer.parseInt(txtIdUsuario.getText()));
        novaAtividade.setDescricao(txtDescricao.getText());
        novaAtividade.setData(txtData.getText());

        atividadesDAO.adicionarAtividade(novaAtividade);
        carregarDados(); 
    }

    private void excluirAtividade() {
        int selectedRow = tblAtividades.getSelectedRow();
        if (selectedRow >= 0) {
            int idAtividade = (int) tblAtividades.getValueAt(selectedRow, 0); // Pega o ID da linha selecionada
            HistoricoAtividadesDAO atividadesDAO = new HistoricoAtividadesDAO();
            atividadesDAO.excluirAtividade(idAtividade);
            carregarDados();
        }
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> {
            new TelaVisualizarHistoricoAtividades().setVisible(true);
        });
    }
    
    private void editarAtividade() {
        int selectedRow = tblAtividades.getSelectedRow();

        if (selectedRow >= 0) {
            int idAtividade = (int) tblAtividades.getValueAt(selectedRow, 0);

            HistoricoAtividadesDAO atividadesDAO = new HistoricoAtividadesDAO();
            HistoricoAtividades atividade = atividadesDAO.buscarAtividadePorId(idAtividade);

            txtIdUsuario.setText(String.valueOf(atividade.getIdUsuarios()));
            txtDescricao.setText(atividade.getDescricao());
            txtData.setText(atividade.getData());

            // Remove listeners anteriores
            for (ActionListener al : btnSalvar.getActionListeners()) {
                btnSalvar.removeActionListener(al);
            }

            // Adiciona novo listener para salvar as alterações
            btnSalvar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    salvarAlteracoes(idAtividade);
                }
            });

        } else {
            JOptionPane.showMessageDialog(this, "Por favor, selecione uma atividade para editar.");
        }
    }
    
    private void salvarAlteracoes(int idAtividade) {
        int idUsuario = Integer.parseInt(txtIdUsuario.getText());
        String descricao = txtDescricao.getText();
        String data = txtData.getText();

        if (descricao.isEmpty() || data.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos os campos devem ser preenchidos.");
            return;
        }

        HistoricoAtividades atividade = new HistoricoAtividades();
        atividade.setIdHistoricoAtividades(idAtividade);
        atividade.setIdUsuarios(idUsuario);
        atividade.setDescricao(descricao);
        atividade.setData(data);

        HistoricoAtividadesDAO atividadesDAO = new HistoricoAtividadesDAO();
        try {
            atividadesDAO.atualizarAtividade(atividade);
            JOptionPane.showMessageDialog(this, "Atividade atualizada com sucesso!");
            carregarDados();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erro ao atualizar a atividade: " + e.getMessage());
        }
    }
}
