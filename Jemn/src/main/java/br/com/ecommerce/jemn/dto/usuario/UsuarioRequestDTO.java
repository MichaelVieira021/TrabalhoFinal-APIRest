package br.com.ecommerce.jemn.dto.usuario;


public class UsuarioRequestDTO extends UsuarioBaseDTO {
    
    private String senha;

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

}
