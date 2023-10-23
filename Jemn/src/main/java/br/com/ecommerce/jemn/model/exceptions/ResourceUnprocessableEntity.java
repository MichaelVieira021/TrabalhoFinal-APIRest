package br.com.ecommerce.jemn.model.exceptions;

public class ResourceUnprocessableEntity extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public ResourceUnprocessableEntity(String mensagem){
        super(mensagem);
    }
    
}
