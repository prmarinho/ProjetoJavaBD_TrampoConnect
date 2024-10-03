package javaapplication1;

public class SolicitarAssistencia {
    private int id_solicitar_assistencia;
    private int id_usuarios;
    private String solicitacao;
    private String data;

    public SolicitarAssistencia() {
    }

    public SolicitarAssistencia(int id_usuarios, String solicitacao, String data) {
        this.id_usuarios = id_usuarios;
        this.solicitacao = solicitacao;
        this.data = data;
    }

    // Getters e Setters
    public int getIdSolicitarAssistencia() {
        return id_solicitar_assistencia;
    }

    public void setIdSolicitarAssistencia(int id_solicitar_assistencia) {
        this.id_solicitar_assistencia = id_solicitar_assistencia;
    }

    public int getIdUsuarios() {
        return id_usuarios;
    }

    public void setIdUsuarios(int id_usuarios) {
        this.id_usuarios = id_usuarios;
    }

    public String getSolicitacao() {
        return solicitacao;
    }

    public void setSolicitacao(String solicitacao) {
        this.solicitacao = solicitacao;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
