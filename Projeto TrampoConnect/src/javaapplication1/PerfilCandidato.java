//@Paulo Ribeiro Marinho
package javaapplication1;

public class PerfilCandidato {
    private int id_perfis_candidatos ;
    private int id_usuarios ;
    private String habilidade;
    private String experiencia;

    public PerfilCandidato() {
    }

    public PerfilCandidato(String habilidade, String experiencia) {
        this.habilidade = habilidade;
        this.experiencia = experiencia;

    }

    // Getters e Setters
    public int getId_perfis_candidatos() {
        return id_perfis_candidatos;
    }

    public void setId_perfis_candidatos(int id_perfis_candidatos) {
        this.id_perfis_candidatos = id_perfis_candidatos;
    }

    public int getId_usuarios() {
        return id_usuarios;
    }

    public void setId_usuarios(int id_usuarios) {
        this.id_usuarios = id_usuarios;
    }

    public String getHabilidade() {
        return habilidade;
    }

    public void setHabilidade(String habilidade) {
        this.habilidade = habilidade;
    }

    public String getExperiencia() {
        return experiencia;
    }

    public void setExperiencia(String experiencia) {
        this.experiencia = experiencia;
    }
}