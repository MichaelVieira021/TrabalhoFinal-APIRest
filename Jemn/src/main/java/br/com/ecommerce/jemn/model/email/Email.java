package br.com.ecommerce.jemn.model.email;

public class Email {
    
    private String assunto;
    private String mensagem;
    private String remetente;
    private String destinatario;

    public Email(String assunto, String mensagem, String remetente, String destinatario) {
        this.assunto = assunto;
        this.mensagem = mensagem;
        this.remetente = remetente;
        this.destinatario = destinatario;
    }

    public Email() {
    }

    public String getAssunto() {
        return assunto;
    }

    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getRemetente() {
        return remetente;
    }

    public void setRemetente(String remetente) {
        this.remetente = remetente;
    }

    public String getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(String destinatario) {
        this.destinatario = destinatario;
    }
}
