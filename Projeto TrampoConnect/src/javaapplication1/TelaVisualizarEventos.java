//@Paulo Ribeiro Marinho
package javaapplication1;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.sql.SQLException;

public class TelaVisualizarEventos extends JFrame {

    private JTable tblEventos;
    private JButton btnCarregar, btnAdicionar, btnEditar, btnSalvar, btnExcluir;
    private JTextField txtNome, txtDescricao, txtData;

    public TelaVisualizarEventos() {
        setTitle("Gerenciar Eventos");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        // Tabela de eventos
        tblEventos = new JTable();
        tblEventos.setModel(new DefaultTableModel(
            new Object[][]{},
            new String[]{"ID_Eventos", "Nome", "Descrição", "Data"}
        ));
        JScrollPane scrollPane = new JScrollPane(tblEventos);
        add(scrollPane, BorderLayout.CENTER);

        // Painel inferior com campos e botões
        JPanel panel = new JPanel(new GridLayout(2, 1));
        
        JPanel formPanel = new JPanel(new GridLayout(6, 2));
        formPanel.add(new JLabel("Nome:"));
        txtNome = new JTextField();
        formPanel.add(txtNome);

        formPanel.add(new JLabel("Descrição:"));
        txtDescricao = new JTextField();
        formPanel.add(txtDescricao);
        
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
                adicionarEvento();
            }
        });

        btnEditar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editarEvento();
            }
        });

        btnExcluir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                excluirEvento();
            }
        });
    }

    private void carregarDados() {
        EventosDAO eventosDAO = new EventosDAO();
        List<Eventos> eventos = eventosDAO.listarEventos();
        DefaultTableModel model = (DefaultTableModel) tblEventos.getModel();
        model.setRowCount(0);
        for (Eventos evento : eventos) {
            model.addRow(new Object[]{
                evento.getIdEventos(),
                evento.getNome(),
                evento.getDescricao(),
                evento.getData()
            });
        }
    }

    private void adicionarEvento() {
        EventosDAO eventosDAO = new EventosDAO();
        Eventos novoEvento = new Eventos();
        novoEvento.setNome(txtNome.getText());
        novoEvento.setDescricao(txtDescricao.getText());
        novoEvento.setData(txtData.getText());

        eventosDAO.adicionarEvento(novoEvento);
        carregarDados(); // Atualiza a tabela após a inserção
    }

    private void excluirEvento() {
        int selectedRow = tblEventos.getSelectedRow();
        if (selectedRow >= 0) {
            int idEvento = (int) tblEventos.getValueAt(selectedRow, 0); // Pega o ID da linha selecionada
            EventosDAO eventosDAO = new EventosDAO();
            eventosDAO.excluirEvento(idEvento);
            carregarDados(); // Atualiza a tabela após a exclusão
        }
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> {
            new TelaVisualizarEventos().setVisible(true);
        });
    }

    private void editarEvento() {
        int selectedRow = tblEventos.getSelectedRow();

        if (selectedRow >= 0) {
            int idEvento = (int) tblEventos.getValueAt(selectedRow, 0);

            EventosDAO eventosDAO = new EventosDAO();
            Eventos evento = eventosDAO.buscarEventoPorId(idEvento);

            txtNome.setText(evento.getNome());
            txtDescricao.setText(evento.getDescricao());
            txtData.setText(evento.getData());

            // Remove listeners anteriores
            for (ActionListener al : btnSalvar.getActionListeners()) {
                btnSalvar.removeActionListener(al);
            }

            // Adiciona novo listener para salvar as alterações
            btnSalvar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    salvarAlteracoes(idEvento);
                }
            });

        } else {
            JOptionPane.showMessageDialog(this, "Por favor, selecione um evento para editar.");
        }
    }

    private void salvarAlteracoes(int idEvento) {
        String nome = txtNome.getText();
        String descricao = txtDescricao.getText();
        String data = txtData.getText();

        if (nome.isEmpty() || descricao.isEmpty() || data.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos os campos devem ser preenchidos.");
            return;
        }

        Eventos evento = new Eventos();
        evento.setIdEventos(idEvento);
        evento.setNome(nome);
        evento.setDescricao(descricao);
        evento.setData(data);

        EventosDAO eventosDAO = new EventosDAO();
        try {
            eventosDAO.atualizarEvento(evento);
            JOptionPane.showMessageDialog(this, "Evento atualizado com sucesso!");
            carregarDados();  // Atualiza a tabela
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erro ao atualizar o evento: " + e.getMessage());
        }
    }
}
