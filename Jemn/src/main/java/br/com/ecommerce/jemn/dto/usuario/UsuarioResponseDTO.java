package br.com.ecommerce.jemn.dto.usuario;

import java.sql.Date;

public class UsuarioResponseDTO extends UsuarioBaseDTO {
    
    private Long id;
    private Date dtCadastro;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

	public Date getDtCadastro() {
		return dtCadastro;
	}

	public void setDtCadastro(Date dtCadastro) {
		this.dtCadastro = dtCadastro;
	}
}
