package com.topera.inc.JKPadel.SnifferFCP.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table (name="equipos")
@NamedQuery(name = "Equipo.findAll", query = "SELECT a FROM Equipo a")
public class Equipo {

	@Id
	@Column(name="id_equipo")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	Integer id_equipo; 	

	@Column(name="equipo")
	String equipo; 	

	@Column(name="genero")
    String genero; 	

	@Column(name="url")
    String url;
	
	
	public Equipo() {}


	public Equipo(Integer id_equipo, String equipo, String genero, String url) {
		super();
		this.id_equipo = id_equipo;
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
