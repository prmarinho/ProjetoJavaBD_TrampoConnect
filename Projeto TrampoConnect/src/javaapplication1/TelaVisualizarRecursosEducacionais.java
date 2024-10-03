//@Paulo Ribeiro Marinho
package javaapplication1;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.sql.SQLException;

public class TelaVisualizarRecursosEducacionais extends JFrame {

    private JTable tblRecursosEducacionais;
    private JButton btnCarregar, btnAdicionar, btnEditar, btnSalvar, btnExcluir;
    private JTextField txtRecurso, txtLink;

    public TelaVisualizarRecursosEducacionais() {
        setTitle("Gerenciar Recursos Educacionais");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        tblRecursosEducacionais = new JTable();
        tblRecursosEducacionais.setModel(new DefaultTableModel(
            new Object[][]{},
            new String[]{"ID_Recursos_Educacionais", "Recurso", "Link"}
        ));
        JScrollPane scrollPane = new JScrollPane(tblRecursosEducacionais);
        add(scrollPane, BorderLayout.CENTER);

        JPanel panel = new JPanel(new GridLayout(2, 1));

        JPanel formPanel = new JPanel(new GridLayout(4, 2));
        formPanel.add(new JLabel("Recurso:"));
        txtRecurso = new JTextField();
        formPanel.add(txtRecurso);

        formPanel.add(new JLabel("Link:"));
        txtLink = new JTextField();
        formPanel.add(txtLink);

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
                adicionarRecursoEducacional();
            }
        });

        btnEditar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editarRecursoEducacional();
            }
        });

        btnExcluir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                excluirRecursoEducacional();
            }
        });
    }

    private void carregarDados() {
        RecursosEducacionaisDAO recursosEducacionaisDAO = new RecursosEducacionaisDAO();
        List<RecursosEducacionais> recursosEducacionais = recursosEducacionaisDAO.listarRecursosEducacionais();
        DefaultTableModel model = (DefaultTableModel) tblRecursosEducacionais.getModel();
        model.setRowCount(0);
        for (RecursosEducacionais recursoEducacional : recursosEducacionais) {
            model.addRow(new Object[]{
                recursoEducacional.getIdRecursosEducacionais(),
                recursoEducacional.getRecurso(),
                recursoEducacional.getLink()
            });
        }
    }

    private void adicionarRecursoEducacional() {
        RecursosEducacionaisDAO recursosEducacionaisDAO = new RecursosEducacionaisDAO();
        RecursosEducacionais novoRecurso = new RecursosEducacionais();
        novoRecurso.setRecurso(txtRecurso.getText());
        novoRecurso.setLink(txtLink.getText());

        recursosEducacionaisDAO.adicionarRecursoEducacional(novoRecurso);
        carregarDados();     
    }

    private void excluirRecursoEducacional() {
        int selectedRow = tblRecursosEducacionais.getSelectedRow();
        if (selectedRow >= 0) {
            int idRecurso = (int) tblRecursosEducacionais.getValueAt(selectedRow, 0); 
            RecursosEducacionaisDAO recursosEducacionaisDAO = new RecursosEducacionaisDAO();
            recursosEducacionaisDAO.excluirRecursoEducacional(idRecurso);
            carregarDados();
        }
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> {
            new TelaVisualizarRecursosEducacionais().setVisible(true);
        });
    }

    private void editarRecursoEducacional() {
        int selectedRow = tblRecursosEducacionais.getSelectedRow();

        if (selectedRow >= 0) {
            int idRecurso = (int) tblRecursosEducacionais.getValueAt(selectedRow, 0);

            RecursosEducacionaisDAO recursosEducacionaisDAO = new RecursosEducacionaisDAO();
            RecursosEducacionais recursoEducacional = recursosEducacionaisDAO.buscarRecursoEducacionalPorId(idRecurso);

            txtRecurso.setText(recursoEducacional.getRecurso());
            txtLink.setText(recursoEducacional.getLink());

            for (ActionListener al : btnSalvar.getActionListeners()) {
                btnSalvar.removeActionListener(al);
            }

            btnSalvar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    salvarAlteracoes(idRecurso);
                }
            });

        } else {
            JOptionPane.showMessageDialog(this, "Por favor, selecione um recurso educacional para editar.");
        }
    }

    private void salvarAlteracoes(int idRecurso) {
        String recurso = txtRecurso.getText();
        String link = txtLink.getText();

        if (recurso.isEmpty() || link.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos os campos devem ser preenchidos.");
            return;
        }

        RecursosEducacionais recursoEducacional = new RecursosEducacionais();
        recursoEducacional.setIdRecursosEducacionais(idRecurso);
        recursoEducacional.setRecurso(recurso);
        recursoEducacional.setLink(link);

        RecursosEducacionaisDAO recursosEducacionaisDAO = new RecursosEducacionaisDAO();
        try {
            recursosEducacionaisDAO.atualizarRecursoEducacional(recursoEducacional);
            JOptionPane.showMessageDialog(this, "Recurso educacional atualizado com sucesso!");
            carregarDados();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erro ao atualizar o recurso educacional: " + e.getMessage());
        }
    }
}
