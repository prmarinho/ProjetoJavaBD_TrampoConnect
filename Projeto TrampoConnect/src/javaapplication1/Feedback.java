//@Paulo Ribeiro Marinho
package javaapplication1;

public class Feedback {
    private int id_feedback;
    private int id_vagas;
    private int id_perfis_candidatos;
    private String feedback;
    private String data;

    public Feedback() {
    }

    public Feedback(int id_vagas, int id_perfis_candidatos, String feedback, String data) {
        this.id_vagas = id_vagas;
        this.id_perfis_candidatos = id_perfis_candidatos;
        this.feedback = feedback;
        this.data = data;
    }

    // Getters e Setters
    public int getIdFeedback() {
        return id_feedback;
    }

    public void setIdFeedback(int id_feedback) {
        this.id_feedback = id_feedback;
    }

    public int getIdVagas() {
        return id_vagas;
    }

    public void setIdVagas(int id_vagas) {
        this.id_vagas = id_vagas;
    }

    public int getIdPerfisCandidatos() {
        return id_perfis_candidatos;
    }

    public void setIdPerfisCandidatos(int id_perfis_candidatos) {
        this.id_perfis_candidatos = id_perfis_candidatos;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}