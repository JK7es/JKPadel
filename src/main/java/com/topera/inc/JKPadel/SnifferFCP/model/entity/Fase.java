package com.topera.inc.JKPadel.SnifferFCP.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table (name="fases")
@NamedQuery(name = "Fase.findAll", query = "SELECT a FROM Fase a")
public class Fase implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="id_fase")
	private int id_fase;
	@Column(name="fase")
	private String fase;
	
	
	public Fase() {
		super();
	}

	public Fase(int id_fase, String fase) {
		super();
		this.id_fase = id_fase;
		this.fase = fase;
	}

	public int getId_fase() {
		return id_fase;
	}
	public void setId_fase(int id_fase) {
		this.id_fase = id_fase;
	}

	public String getFase() {
		return fase;
	}
	public void setFase(String fase) {
		this.fase = fase;
	}
	
	

}
