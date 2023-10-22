package br.com.ecommerce.jemn.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
public class Usuario implements UserDetails{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idUsuario")
    private Long id;

    @Column(nullable = false, length = 15)
    private String nomeUsuario;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, length = 15)
    private String senha;

    @Column(nullable = false, unique = true, length = 14)
    private String telefone;

    @Column(nullable = false)
    private Date dtCadastro;

    @OneToMany(mappedBy = "usuario")
    private List<Pedido> pedidos;

    @OneToMany(mappedBy = "usuario")
    private List<Log> logs;

    @Column(nullable = false)
    private ETipoPerfil perfil;
    
    public Usuario(Date dtCadastro) {
        this.dtCadastro = new Date();
    }
    //#region Getters e setters do usuario
    public Usuario() {
    	this.dtCadastro = new Date();
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Date getDtCadastro() {
        return dtCadastro;
    }

    public void setDtCadastro(Date dtCadastro) {
        this.dtCadastro = dtCadastro;
    }

    public ETipoPerfil getPerfil() {
        return perfil;
    }
    public void setPerfil(ETipoPerfil perfil) {
        this.perfil = perfil;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
       List<String> perfis = new ArrayList<>();
       perfis.add(perfil.toString());

      // Converter a lista de perfis em uma lista de Authorities
      return perfis.stream()
        .map(perfil -> new SimpleGrantedAuthority(perfil))
        // .map(SimpleGrantedAuthority::new)
        .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
       return email;
    }

    @Override
    public boolean isAccountNonExpired() {
       return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
       return true;
    }
}
