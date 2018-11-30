package br.senai.sp.info.service.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "chamado")
public class Chamado {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "chamadoId")
	private Long id;
	
	@Column(length = 120, nullable = false, unique = false)
	@Size(min = 1, max = 120, message = "{Size}")
	@NotNull(message = "{NotNull}")
	private String usuario;
	
	@Column(length = 64, nullable = false, unique = false)
	@Size(min = 1, max = 64, message = "{Size}")
	@NotNull(message = "{Size}")
	private String tipoPendencia;
	
	
	@Column(length = 32, nullable = false, unique = false)
	@Size(min = 1, max = 32, message = "{Size}")
	@NotNull(message = "{NotNull}")
	private String resumo;
	
	@Column(length = 32, nullable = false, unique = false)
	@Size(min = 1, max = 32, message = "{Size}")
	@NotNull(message = "{NotNull}")
	private String descricao;
	
	@Column(length = 64, nullable = false, unique = false)
	@Size(min = 1, max = 64, message = "{Size}")
	@NotNull(message = "{NotNull}")
	private String contato;
	
	@Column(length = 10, nullable = false, unique = false)
	@Size(min = 1, max = 10, message = "{Size}")
	@NotNull(message = "{NotNull}")
	private String dataCadastro;
	
	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getTipoPendencia() {
		return tipoPendencia;
	}

	public void setTipoPendencia(String tipoPendencia) {
		this.tipoPendencia = tipoPendencia;
	}

	public String getContato() {
		return contato;
	}

	public void setContato(String contato) {
		this.contato = contato;
	}

	public String getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(String dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getResumo() {
		return resumo;
	}

	public void setResumo(String resumo) {
		this.resumo = resumo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

		
}
