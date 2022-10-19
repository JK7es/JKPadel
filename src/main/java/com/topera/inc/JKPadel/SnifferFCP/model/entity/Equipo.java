package com.topera.inc.JKPadel.SnifferFCP.model.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table (name="equipos")
@NamedQuery(name = "Equipo.findAll", query = "SELECT a FROM Equipo a")
public class Equipo {

	@Id
	@Column(name="id_equipo")
//	@GeneratedValue(strategy=GenerationType.IDENTITY)
	Integer id_equipo; 	

	@Column(name="id_liga")
	Integer id_liga;		
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name="id_liga", referencedColumnName="id_liga", nullable = false, updatable = false, insertable = false)
	@org.hibernate.annotations.Cascade(value = org.hibernate.annotations.CascadeType.ALL)
	private Liga liga;
		
	@Column(name="id_grupo")
	Integer id_grupo;
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name="id_grupo", referencedColumnName="id_grupo", nullable = false, updatable = false, insertable = false)
	@org.hibernate.annotations.Cascade(value = org.hibernate.annotations.CascadeType.ALL)
	private Grupo grupo;

	@Column(name="id_sede")
	Integer id_sede;
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name="id_sede", referencedColumnName="id_sede", nullable = false, updatable = false, insertable = false)
	@org.hibernate.annotations.Cascade(value = org.hibernate.annotations.CascadeType.ALL)
	private Sede sede;
	
	@Column(name="equipo")
	String equipo; 	

	@Column(name="genero")
    String genero; 	

	@Column(name="url")
    String url;
	
	
	public Equipo() {}
	
	public Equipo(Integer id_equipo, Integer id_liga, Integer id_grupo, String equipo, String genero, String url) {
		super();
		this.id_equipo = id_equipo;
		this.id_liga = id_liga;
		this.id_grupo = id_grupo;
		this.equipo = equipo;
		this.genero = genero;
		this.url = url;
	}

	public Integer getId_equipo() {
		return id_equipo;
	}

	public void setId_equipo(Integer id_equipo) {
		this.id_equipo = id_equipo;
	}

	public Integer getId_liga() {
		return id_liga;
	}

	public void setId_liga(Integer id_liga) {
		this.id_liga = id_liga;
	}

	public Integer getId_grupo() {
		return id_grupo;
	}

	public void setId_grupo(Integer id_grupo) {
		this.id_grupo = id_grupo;
	}	
	
	public Integer getId_sede() {
		return id_sede;
	}

	public void setId_sede(Integer id_sede) {
		this.id_sede = id_sede;
	}

	public String getEquipo() {
		return equipo;
	}

	public void setEquipo(String equipo) {
		this.equipo = equipo;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}	
}
