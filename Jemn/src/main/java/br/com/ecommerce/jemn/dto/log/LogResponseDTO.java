package br.com.ecommerce.jemn.dto.log;

import java.util.Date;

public class LogResponseDTO extends LogRequestDTO {
    
    private Long Id;
    private Date data;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}
}
