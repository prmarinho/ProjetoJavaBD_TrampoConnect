//@Paulo Ribeiro Marinho
package javaapplication1;

public class Candidaturas {
    private int id_candidaturas;
    private int id_vagas;
    private int id_perfis_candidatos;
    private String data;
    private String status;

    public Candidaturas() {
    }

    public Candidaturas(int id_vagas, int id_perfis_candidatos, String data, String status) {
        this.id_vagas = id_vagas;
        this.id_perfis_candidatos = id_perfis_candidatos;
        this.data = data;
        this.status = status;
    }

    // Getters e Setters
    public int getIdCandidaturas() {
        return id_candidaturas;
    }

    public void setIdCandidaturas(int id_candidaturas) {
        this.id_candidaturas = id_candidaturas;
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

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}