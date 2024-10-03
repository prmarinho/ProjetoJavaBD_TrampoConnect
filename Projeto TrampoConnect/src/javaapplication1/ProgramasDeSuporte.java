//@Paulo Ribeiro Marinho
package javaapplication1;

public class ProgramasDeSuporte {
    private int id_programas;
    private String programa;
    private String descricao;

    public ProgramasDeSuporte() {
    }

    public ProgramasDeSuporte(String programa, String descricao) {
        this.programa = programa;
        this.descricao = descricao;
    }

    // Getters e Setters
    public int getIdProgramas() {
        return id_programas;
    }

    public void setIdProgramas(int id_programas) {
        this.id_programas = id_programas;
    }

    public String getPrograma() {
        return programa;
    }

    public void setPrograma(String programa) {
        this.programa = programa;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}