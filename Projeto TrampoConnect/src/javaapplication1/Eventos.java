//@Paulo Ribeiro Marinho
package javaapplication1;

public class Eventos {
    private int id_eventos;
    private String nome;
    private String descricao;
    private String data;

    public Eventos() {
    }

    public Eventos(String nome, String descricao, String data) {
        this.nome = nome;
        this.descricao = descricao;
        this.data = data;
    }

    // Getters e Setters
    public int getIdEventos() {
        return id_eventos;
    }

    public void setIdEventos(int id_eventos) {
        this.id_eventos = id_eventos;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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
