package com.topera.inc.JKPadel.SnifferFCP.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table (name="sedes")
@NamedQuery(name = "Sede.findAll", query = "SELECT a FROM Sede a")
public class Sede {

	@Id
	@Column(name="id_sede")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	Integer id_sede; 	


	@Column(name="sede")
	String sede; 	


	@Column(name="url")
	String url; 	


	@Column(name="latitud")
	double latitud; 	


	@Column(name="longitud")
	double longitud; 	


	@Column(name="n_pistas")
	Integer n_pistas; 	


	@Column(name="telefono")
	Integer telefono; 	
	
	
	public Sede() {}


	public Integer getId_sede() {
		return id_sede;
	}


	public void setId_sede(Integer id_sede) {
		this.id_sede = id_sede;
	}


	public String getSede() {
		return sede;
	}


	public void setSede(String sede) {
		this.sede = sede;
	}


	public String getUrl() {
		return url;
	}


	public void setUrl(String url) {
		this.url = url;
	}


	public double getLatitud() {
		return latitud;
	}


	public void setLatitud(double latitud) {
		this.latitud = latitud;
	}


	public double getLongitud() {
		return longitud;
	}


	public void setLongitud(double longitud) {
		this.longitud = longitud;
	}


	public Integer getN_pistas() {
		return n_pistas;
	}


	public void setN_pistas(Integer n_pistas) {
		this.n_pistas = n_pistas;
	}


	public Integer getTelefono() {
		return telefono;
	}


	public void setTelefono(Integer telefono) {
		this.telefono = telefono;
	}

	
}
