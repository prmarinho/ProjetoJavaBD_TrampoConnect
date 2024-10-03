//@Paulo Ribeiro Marinho
package javaapplication1;

public class Usuario {
    private int id_usuarios;
    private String nomeCompleto;
    private String email;
    private String senha;

    public Usuario() {
    }

    public Usuario(String nomeCompleto, String email, String senha) {
        this.nomeCompleto = nomeCompleto;
        this.email = email;
        this.senha = senha;

    }

    // Getters e Setters
    public int getIdUsuarios() {
        return id_usuarios;
    }

    public void setIdUsuarios(int id_usuarios) {
        this.id_usuarios = id_usuarios;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

}
