//@Paulo Ribeiro Marinho
package javaapplication1;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.sql.SQLException;

public class TelaVisualizarPerfilCandidato extends JFrame {

    private JTable tblPerfis;
    private JButton btnCarregar, btnAdicionar, btnEditar, btnSalvar, btnExcluir;
    private JTextField txtHabilidade, txtExperiencia;

    public TelaVisualizarPerfilCandidato() {
        setTitle("Gerenciar Perfis de Candidatos");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);


        tblPerfis = new JTable();
        tblPerfis.setModel(new DefaultTableModel(
            new Object[][]{},
            new String[]{"ID_Perfil_Candidato", "Habilidade", "Experiência"}
        ));
        JScrollPane scrollPane = new JScrollPane(tblPerfis);
        add(scrollPane, BorderLayout.CENTER);

        JPanel panel = new JPanel(new GridLayout(2, 1));
        
        JPanel formPanel = new JPanel(new GridLayout(2, 2));
        formPanel.add(new JLabel("Habilidade:"));
        txtHabilidade = new JTextField();
        formPanel.add(txtHabilidade);

        formPanel.add(new JLabel("Experiência:"));
        txtExperiencia = new JTextField();
        formPanel.add(txtExperiencia);
        
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
                adicionarPerfilCandidato();
            }
        });

        btnEditar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editarPerfilCandidato();
            }
        });

        btnExcluir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                excluirPerfilCandidato();
            }
        });

    }

    private void carregarDados() {
        PerfilCandidatoDAO perfilCandidatoDAO = new PerfilCandidatoDAO();
        List<PerfilCandidato> perfisCandidatos = perfilCandidatoDAO.listarPerfisCandidatos();
        DefaultTableModel model = (DefaultTableModel) tblPerfis.getModel();
        model.setRowCount(0);
        for (PerfilCandidato perfil : perfisCandidatos) {
            model.addRow(new Object[]{
                perfil.getId_perfis_candidatos(),
                perfil.getHabilidade(),
                perfil.getExperiencia(),
            });
        }
    }

    private void adicionarPerfilCandidato() {
        PerfilCandidatoDAO PerfilCandidatoDAO = new PerfilCandidatoDAO ();
        PerfilCandidato novoPerfilCandidato = new PerfilCandidato();
        novoPerfilCandidato.setHabilidade(txtHabilidade.getText());
        novoPerfilCandidato.setExperiencia(txtExperiencia.getText());

        PerfilCandidatoDAO.adicionarPerfilCandidato(novoPerfilCandidato);
        carregarDados(); 
    }


    private void excluirPerfilCandidato() {
        int selectedRow = tblPerfis.getSelectedRow();
        if (selectedRow >= 0) {
            int idPerfilCandidato = (int) tblPerfis.getValueAt(selectedRow, 0); 
            PerfilCandidatoDAO perfilCandidatoDAO = new PerfilCandidatoDAO();
            perfilCandidatoDAO.excluirPerfilCandidato(idPerfilCandidato);
            carregarDados(); 
        }
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> {
            new TelaVisualizarPerfilCandidato().setVisible(true);
        });
    }
    
private void editarPerfilCandidato() {
    int selectedRow = tblPerfis.getSelectedRow();

    if (selectedRow >= 0) {
        int id_perfis_candidatos = (int) tblPerfis.getValueAt(selectedRow, 0);

        PerfilCandidatoDAO perfilCandidatoDAO = new PerfilCandidatoDAO();
        PerfilCandidato perfil = perfilCandidatoDAO.buscarPerfilCandidatoPorId(id_perfis_candidatos);

        if (perfil != null) {
            txtHabilidade.setText(perfil.getHabilidade());
            txtExperiencia.setText(perfil.getExperiencia());

            // Remove listeners anteriores
            for (ActionListener al : btnSalvar.getActionListeners()) {
                btnSalvar.removeActionListener(al);
            }

            btnSalvar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    salvarAlteracoes(id_perfis_candidatos);
                }
            });

        } else {
            JOptionPane.showMessageDialog(this, "Erro: Perfil de candidato não encontrado.");
        }
    } else {
        JOptionPane.showMessageDialog(this, "Por favor, selecione um perfil para editar.");
    }
}
    
    private void salvarAlteracoes(int Id_perfis_candidatos) {
        String habilidade = txtHabilidade.getText();
        String experiencia = txtExperiencia.getText();

        if (habilidade.isEmpty() || experiencia.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos os campos devem ser preenchidos.");
            return;
        }

        PerfilCandidato perfil = new PerfilCandidato();
        perfil.setId_perfis_candidatos(Id_perfis_candidatos);
        perfil.setHabilidade(habilidade);
        perfil.setExperiencia(experiencia);

        PerfilCandidatoDAO perfilCandidatoDAO = new PerfilCandidatoDAO();
        try {
            perfilCandidatoDAO.atualizarPerfilCandidato(perfil);
            JOptionPane.showMessageDialog(this, "Perfil atualizado com sucesso!");
            carregarDados();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erro ao atualizar o perfil: " + e.getMessage());
        }
    }

}