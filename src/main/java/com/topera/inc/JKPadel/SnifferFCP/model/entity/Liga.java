package com.topera.inc.JKPadel.SnifferFCP.model.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "LIGAS")   
public class Liga implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID_LIGA")
	private Integer id_liga;
	
	@Column(name = "TEMPORADA")
	private Integer temporada;
	
	@Column(name = "LIGA")
	private String liga;
	
	@Column(name = "URL")
	private String url;
	
	@Column(name = "C_CATEGORIA")
	private String c_categoria;

	@OneToMany(mappedBy="liga", fetch=FetchType.LAZY)
	List<Grupo> grupos;
	
	public Integer getId_liga() {
		return id_liga;
	}

	public void setId_liga(Integer id_liga) {
		this.id_liga = id_liga;
	}

	public Integer getTemporada() {
		return temporada;
	}

	public void setTemporada(Integer temporada) {
		this.temporada = temporada;
	}

	public String getLiga() {
		return liga;
	}

	public void setLiga(String liga) {
		this.liga = liga;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getC_categoria() {
		return c_categoria;
	}

	public void setC_categoria(String c_categoria) {
		this.c_categoria = c_categoria;
	}
	
	

}
