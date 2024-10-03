//@Paulo Ribeiro Marinho
package javaapplication1;

public class HistoricoAtividades {
    private int id_historico_atividades;
    private int id_usuarios;
    private String descricao;
    private String data;

    public HistoricoAtividades() {
    }

    public HistoricoAtividades(int id_usuarios, String descricao, String data) {
        this.id_usuarios = id_usuarios;
        this.descricao = descricao;
        this.data = data;
    }

    // Getters e Setters
    public int getIdHistoricoAtividades() {
        return id_historico_atividades;
    }

    public void setIdHistoricoAtividades(int id_historico_atividades) {
        this.id_historico_atividades = id_historico_atividades;
    }

    public int getIdUsuarios() {
        return id_usuarios;
    }

    public void setIdUsuarios(int id_usuarios) {
        this.id_usuarios = id_usuarios;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
