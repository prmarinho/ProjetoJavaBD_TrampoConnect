//@Paulo Ribeiro Marinho
package javaapplication1;

public class Empregadores {
    private int id_empregadores;
    private String empresa;
    private String cnpj;
    private String email;

    public Empregadores() {
    }

    public Empregadores(String empresa, String cnpj, String email) {
        this.empresa = empresa;
        this.cnpj = cnpj;
        this.email = email;
    }

    // Getters e Setters
    public int getIdEmpregadores() {
        return id_empregadores;
    }

    public void setIdEmpregadores(int id_empregadores) {
        this.id_empregadores = id_empregadores;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

