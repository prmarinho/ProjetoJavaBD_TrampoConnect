//@Paulo Ribeiro Marinho
package javaapplication1;

public class VagasDeEmprego {
    private int id_vagas;
    private int id_empregadores;
    private String titulo_vaga;
    private String descricao_vaga;
    private int salario;

    public VagasDeEmprego() {
    }

    public VagasDeEmprego(int id_empregadores, String titulo_vaga, String descricao_vaga, int salario) {
        this.id_empregadores = id_empregadores;
        this.titulo_vaga = titulo_vaga;
        this.descricao_vaga = descricao_vaga;
        this.salario = salario;
    }

    // Getters e Setters
    public int getIdVagas() {
        return id_vagas;
    }

    public void setIdVagas(int id_vagas) {
        this.id_vagas = id_vagas;
    }

    public int getIdEmpregadores() {
        return id_empregadores;
    }

    public void setIdEmpregadores(int id_empregadores) {
        this.id_empregadores = id_empregadores;
    }

    public String getTituloVaga() {
        return titulo_vaga;
    }

    public void setTituloVaga(String titulo_vaga) {
        this.titulo_vaga = titulo_vaga;
    }

    public String getDescricaoVaga() {
        return descricao_vaga;
    }

    public void setDescricaoVaga(String descricao_vaga) {
        this.descricao_vaga = descricao_vaga;
    }

    public int getSalario() {
        return salario;
    }

    public void setSalario(int salario) {
        this.salario = salario;
    }
}