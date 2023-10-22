package br.com.ecommerce.jemn.dto.usuario;

import br.com.ecommerce.jemn.model.ETipoPerfil;

public abstract class UsuarioBaseDTO {
    
    private String nomeUsuario;
    private String email;
    private String telefone;
    private ETipoPerfil perfil; // TOKEN

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

     public ETipoPerfil getPerfil() {
        return perfil;
    }

    public void setPerfil(ETipoPerfil perfil) {
        this.perfil = perfil;
    }
}
