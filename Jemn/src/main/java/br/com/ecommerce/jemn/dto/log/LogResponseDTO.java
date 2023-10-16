package br.com.ecommerce.jemn.dto.log;

import java.util.Date;

public class LogResponseDTO extends LogRequestDTO {
    
    private Long id;
    private Date data;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}
}
