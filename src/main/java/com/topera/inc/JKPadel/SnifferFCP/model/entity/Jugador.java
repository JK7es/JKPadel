package com.topera.inc.JKPadel.SnifferFCP.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table (name="jugadores")
@NamedQuery(name = "Jugador.findAll", query = "SELECT a FROM Jugador a")
public class Jugador implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="id_jugador")
	private Integer id_jugador;
	
	@Column(name="nombre")
	private String nombre;
	
	@Column(name="apellido1")
	private String apellido1;
	
	@Column(name="apellido2")
	private String apellido2;
	
	@Column(name="genero")
	private String genero;
	
	public Jugador() {
		
	}
	
	public Jugador(Integer id_jugador, String nombre, String apellido1, String apellido2, String genero) {
		super();
		this.id_jugador = id_jugador;
		this.nombre = nombre;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
		this.genero = genero;
	}

	public Integer getId_jugador() {
		return id_jugador;
	}

	public void setId_jugador(Integer id_jugador) {
		this.id_jugador = id_jugador;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido1() {
		return apellido1;
	}

	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}

	public String getApellido2() {
		return apellido2;
	}

	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}
	
	public String getGenero() {
		return genero;
	}
	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getNombreCompleto() {
		return nombre + " " + apellido1 + " " + apellido2;
	}
}
