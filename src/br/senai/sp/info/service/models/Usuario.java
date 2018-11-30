package br.senai.sp.info.service.models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.util.DigestUtils;

@Entity
@Table(name = "usuario")
public class Usuario implements Authentication {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "usuarioId")
	private Long id;

	@NotNull(message = "{NotNull}")
	private TiposUsuario tipo;

	public TiposUsuario getTipo() {
		return tipo;
	}

	public void setTipo(TiposUsuario tipo) {
		this.tipo = tipo;
	}

	@Column(length = 32, nullable = false, unique = false)
	@Size(min = 1, max = 32, message = "{Size}")
	@NotNull(message = "{NotNull}")
	private String nome;

	@Column(length = 32, nullable = false, unique = false)
	@Size(min = 1, max = 32, message = "{Size}")
	@NotNull(message = "{NotNull}")
	private String sobrenome;
	
	@Column(nullable = false, unique = false)
	@NotNull(message = "{NotNull}")
	private String projetoVinculado;



	@Column(length = 120, nullable = false, unique = true)
	@Email(message = "{Email}")
	@NotNull(message = "{NotNull}")
	private String email;

	@Column(length = 64, nullable = false, unique = false)
	@Size(min = 5, max = 32, message = "{Size}")
	@NotNull(message = "{NotNull}")
	private String senha;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getProjetoVinculado() {
		return projetoVinculado;
	}

	public void setProjetoVinculado(String projetoVinculado) {
		this.projetoVinculado = projetoVinculado;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> autorizacoes = new ArrayList<>(2);

		if (this.getTipo() == TiposUsuario.ADMINISTRADOR) {
			autorizacoes.add(new SimpleGrantedAuthority("ROLE_ADM"));

		}

		autorizacoes.add(new SimpleGrantedAuthority("ROLE_COMUM"));

		return autorizacoes;
	}

	@Override
	public Object getCredentials() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getDetails() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getPrincipal() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isAuthenticated() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void setAuthenticated(boolean arg0) throws IllegalArgumentException {
	}

	public void hashearSenha() {
		this.senha = DigestUtils.md5DigestAsHex(this.senha.getBytes());
	}

	public void setCaminhoFoto(String caminhoRelativo) {
		
	}
}
