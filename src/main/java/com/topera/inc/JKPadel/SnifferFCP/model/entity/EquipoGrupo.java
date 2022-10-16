package com.topera.inc.JKPadel.SnifferFCP.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table (name="eq_grupo")
@NamedQuery(name = "EquipoGrupo.findAll", query = "SELECT a FROM EquipoGrupo a")
public class EquipoGrupo implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_eq_gru")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	Integer id_eq_gru; 	

	@Column(name="id_equipo")
	Integer id_equipo; 	

	@Column(name="id_grupo")
	Integer id_grupo; 	

	@Column(name="id_sede")
	Integer id_sede;
	
//	@LazyToOne(LazyToOneOption.NO_PROXY)
//	@ManyToOne (fetch=FetchType.LAZY)	// NO se trae el objeto linkado a no ser que se haga una consulta expreso para ello (fetch)
//	@JoinColumn (name="id_equipo", referencedColumnName="id_equipo", insertable=false, updatable=false)
//	private Equipo equipo;
//	
//	@LazyToOne(LazyToOneOption.NO_PROXY)
//	@ManyToOne (fetch=FetchType.LAZY)	// NO se trae el objeto linkado a no ser que se haga una consulta expreso para ello (fetch)
//	@JoinColumn (name="id_grupo", referencedColumnName="id_grupo", insertable=false, updatable=false)
//	private Grupo grupo;
//	
//	@LazyToOne(LazyToOneOption.NO_PROXY)
//	@ManyToOne (fetch=FetchType.LAZY)	// NO se trae el objeto linkado a no ser que se haga una consulta expreso para ello (fetch)
//	@JoinColumn (name="id_sede", referencedColumnName="id_sede", insertable=false, updatable=false)
//	private Sede sede;
	
	
			
	public Integer getId_eq_gru() {
		return id_eq_gru;
	}

	public void setId_eq_gru(Integer id_eq_gru) {
		this.id_eq_gru = id_eq_gru;
	}

	public Integer getId_equipo() {
		return id_equipo;
	}

	public void setId_equipo(Integer id_equipo) {
		this.id_equipo = id_equipo;
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


	

}
