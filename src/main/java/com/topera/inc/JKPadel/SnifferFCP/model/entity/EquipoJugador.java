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
@Table (name="eq_jug")
@NamedQuery(name = "EquipoJugador.findAll", query = "SELECT a FROM EquipoJugador a")
public class EquipoJugador implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_eq_jug")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	Integer id_eqJug; 	

	@Column(name="id_equipo")
	Integer idEquipo; 	

	@Column(name="id_jugador")
	Integer idJugador; 	

	@Column(name="id_liga")
	Integer idLiga;
	
	@Column(name="temporada")
	Integer temporada;
	
//	@LazyToOne(LazyToOneOption.NO_PROXY)
//	@ManyToOne (fetch=FetchType.LAZY)	// NO se trae el objeto linkado a no ser que se haga una consulta expreso para ello (fetch)
//	@JoinColumn (name="id_equipo", referencedColumnName="id_equipo", insertable=false, updatable=false)
//	private Equipo equipo;
//	
//	@LazyToOne(LazyToOneOption.NO_PROXY)
//	@ManyToOne (fetch=FetchType.LAZY)	// NO se trae el objeto linkado a no ser que se haga una consulta expreso para ello (fetch)
//	@JoinColumn (name="id_jugador", referencedColumnName="id_jugador", insertable=false, updatable=false)
//	private Jugador jugador;
//	
//	@LazyToOne(LazyToOneOption.NO_PROXY)
//	@ManyToOne (fetch=FetchType.LAZY)	// NO se trae el objeto linkado a no ser que se haga una consulta expreso para ello (fetch)
//	@JoinColumn (name="id_liga", referencedColumnName="id_liga", insertable=false, updatable=false)
//	private Liga liga;
	

	public Integer getId_eqJug() {
		return id_eqJug;
	}

	public void setId_eqJug(Integer id_eqJug) {
		this.id_eqJug = id_eqJug;
	}

	public Integer getIdEquipo() {
		return idEquipo;
	}

	public void setIdEquipo(Integer idEquipo) {
		this.idEquipo = idEquipo;
	}

	public Integer getIdJugador() {
		return idJugador;
	}

	public void setIdJugador(Integer idJugador) {
		this.idJugador = idJugador;
	}

	public Integer getIdLiga() {
		return idLiga;
	}

	public void setIdLiga(Integer idLiga) {
		this.idLiga = idLiga;
	}

	public Integer getTemporada() {
		return temporada;
	}

	public void setTemporada(Integer temporada) {
		this.temporada = temporada;
	}

	
			
}
