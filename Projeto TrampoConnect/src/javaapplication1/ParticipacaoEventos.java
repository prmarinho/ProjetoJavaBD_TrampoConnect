//@Paulo Ribeiro Marinho
package javaapplication1;

public class ParticipacaoEventos {
    private int id_participacao_eventos;
    private int id_perfis_candidatos;
    private int id_eventos;
    private String data;

    public int getIdParticipacaoEventos() {
        return id_participacao_eventos;
    }

    public void setIdParticipacaoEventos(int id_participacao_eventos) {
        this.id_participacao_eventos = id_participacao_eventos;
    }

    public int getIdPerfisCandidatos() {
        return id_perfis_candidatos;
    }

    public void setIdPerfisCandidatos(int id_perfis_candidatos) {
        this.id_perfis_candidatos = id_perfis_candidatos;
    }

    public int getIdEventos() {
        return id_eventos;
    }

    public void setIdEventos(int id_eventos) {
        this.id_eventos = id_eventos;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}