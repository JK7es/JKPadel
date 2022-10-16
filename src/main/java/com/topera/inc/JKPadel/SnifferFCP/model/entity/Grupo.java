package com.topera.inc.JKPadel.SnifferFCP.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;



@Entity
@Table (name="grupos")
@NamedQuery(name = "Grupo.findAll", query = "SELECT a FROM Grupo a")
public class Grupo implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="id_grupo")
	private int id_grupo;
	
	@Column(name="id_liga")
	private int id_liga;
	
	@Column(name="id_fase")
	private int id_fase;
	
	@Column(name="url")
	private String url;
	
	@Column(name="grupo")
	private String grupo;
	
	@Column(name="genero")
	private String genero;
	
	@ManyToOne (fetch=FetchType.LAZY)	// NO se trae el objeto linkado a no ser que se haga una consulta expreso para ello (fetch)
	@JoinColumn (name="id_liga", referencedColumnName="id_liga", insertable=false, updatable=false)
	private Liga liga;
	
	public Grupo() {}

	public Grupo(int id_grupo, int id_liga, int id_fase, String url, String grupo, String genero) {
		super();
		this.id_grupo = id_grupo;
		this.id_liga  = id_liga;
		this.id_fase  = id_fase;
		this.url 	  = url;
		this.grupo 	  = grupo;
		this.genero   = genero;
	}

	public int getId_grupo() {
		return id_grupo;
	}
	public void setId_grupo(int id_grupo) {
		this.id_grupo = id_grupo;
	}

	public int getId_liga() {
		return id_liga;
	}
	public void setId_liga(int id_liga) {
		this.id_liga = id_liga;
	}

	public int getId_fase() {
		return id_fase;
	}
	public void setId_fase(int id_fase) {
		this.id_fase = id_fase;
	}

	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	public String getGrupo() {
		return grupo;
	}
	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}

	public String getGenero() {
		return genero;
	}
	public void setGenero(String genero) {
		this.genero = genero;
	}
	
	
}
