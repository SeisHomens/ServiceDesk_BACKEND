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
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "chamadoId")
	private Long id;
	
	@Column(length = 32, nullable = false, unique = false)
	@Size(min = 1, max = 32, message = "{Size}")
	@NotNull(message = "{NotNull}")
	private String resumo;
	
	@Column(length = 32, nullable = false, unique = false)
	@Size(min = 1, max = 32, message = "{Size}")
	@NotNull(message = "{NotNull}")
	private String descricao;
	
	@Column(length = 32, nullable = false, unique = false)
	@Size(min = 1, max = 32, message = "{Size}")
	private String rotulos;
	
	@Column(length = 32, nullable = false, unique = false)
	@Size(min = 1, max = 32, message = "{Size}")
	private String fabricante;
	
	@Column(length = 32, nullable = false, unique = false)
	@Size(min = 1, max = 32, message = "{Size}")
	private String modelo;
	
	@Column(length = 32, nullable = false, unique = false)
	@Size(min = 1, max = 32, message = "{Size}")
	private String garantia;

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

	public String getRotulos() {
		return rotulos;
	}

	public void setRotulos(String rotulos) {
		this.rotulos = rotulos;
	}

	public String getFabricante() {
		return fabricante;
	}

	public void setFabricante(String fabricante) {
		this.fabricante = fabricante;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getGarantia() {
		return garantia;
	}

	public void setGarantia(String garantia) {
		this.garantia = garantia;
	}
	
}
