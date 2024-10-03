//@Paulo Ribeiro Marinho
package javaapplication1;

public class RecursosEducacionais {
    private int id_recursos_educacionais;
    private String recurso;
    private String link;

    public RecursosEducacionais() {
    }

    public RecursosEducacionais(String recurso, String link) {
        this.recurso = recurso;
        this.link = link;
    }

    // Getters e Setters
    public int getIdRecursosEducacionais() {
        return id_recursos_educacionais;
    }

    public void setIdRecursosEducacionais(int id_recursos_educacionais) {
        this.id_recursos_educacionais = id_recursos_educacionais;
    }

    public String getRecurso() {
        return recurso;
    }

    public void setRecurso(String recurso) {
        this.recurso = recurso;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
