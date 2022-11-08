package com.topera.inc.JKPadel.SnifferFCP.model.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table (name="jornadas")
@NamedQuery(name = "Jornada.findAll", query = "SELECT a FROM Jornada a")
public class Jornada {

	
	@Id
	@Column(name="id_jornada")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	Integer id_jornada;
	
	
	@Column(name="id_liga")
	Integer id_liga;		
//	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//	@JoinColumn(name="id_liga", referencedColumnName="id_liga", nullable = false, updatable = false, insertable = false)
//	@org.hibernate.annotations.Cascade(value = org.hibernate.annotations.CascadeType.ALL)
//	private Liga liga;
	
	
	@Column(name="id_grupo")
	Integer id_grupo;
//	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//	@JoinColumn(name="id_grupo", referencedColumnName="id_grupo", nullable = false, updatable = false, insertable = false)
//	@org.hibernate.annotations.Cascade(value = org.hibernate.annotations.CascadeType.ALL)
//	private Grupo grupo;

	@Column(name="id_sede")
	Integer id_sede;
//	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//	@JoinColumn(name="id_sede", referencedColumnName="id_sede", nullable = false, updatable = false, insertable = false)
//	@org.hibernate.annotations.Cascade(value = org.hibernate.annotations.CascadeType.ALL)
//	private Sede sede;
	
	
	@Column(name="id_equipo_local")
	Integer id_equipo_local;
//	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//	@JoinColumn(name="id_equipo_local", referencedColumnName="id_equipo", nullable = false, updatable = false, insertable = false)
//	@org.hibernate.annotations.Cascade(value = org.hibernate.annotations.CascadeType.ALL)
	
	
	
	@Column(name="id_equipo_visitante")
	Integer id_equipo_visitante;
//	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//	@JoinColumn(name="id_equipo_visitante", referencedColumnName="id_equipo", nullable = false, updatable = false, insertable = false)
//	@org.hibernate.annotations.Cascade(value = org.hibernate.annotations.CascadeType.ALL)	
		
	
	@Column(name="partido")
	Integer partido;
	
	@Column(name="n_jornada")
	Integer n_jornada;
	
	@Column(name = "fecha")
    @Temporal(TemporalType.TIMESTAMP)
    Date fecha;
	
	@Column(name="tandas")
	Integer tandas;
	
	@Column(name="juegos_win_local")
	Integer juegos_win_local;
	
	@Column(name="sets_win_local")
	Integer sets_win_local;
	
	@Column(name="partidos_win_local")
	Integer partidos_win_local;
	
	@Column(name="juegos_win_visit")
	Integer juegos_win_visit;
	
	@Column(name="sets_win_visit")
	Integer sets_win_visit;
	
	@Column(name="partidos_win_visit")
	Integer partidos_win_visit;

	
	public Integer getId_jornada() {
		return id_jornada;
	}
	public void setId_jornada(Integer id_jornada) {
		this.id_jornada = id_jornada;
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

	public Integer getId_equipo_local() {
		return id_equipo_local;
	}
	public void setId_equipo_local(Integer id_equipo_local) {
		this.id_equipo_local = id_equipo_local;
	}

	public Integer getId_equipo_visitante() {
		return id_equipo_visitante;
	}
	public void setId_equipo_visitante(Integer id_equipo_visitante) {
		this.id_equipo_visitante = id_equipo_visitante;
	}

	public Integer getPartido() {
		return partido;
	}
	public void setPartido(Integer partido) {
		this.partido = partido;
	}

	public Integer getN_jornada() {
		return n_jornada;
	}
	public void setN_jornada(Integer n_jornada) {
		this.n_jornada = n_jornada;
	}

	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Integer getTandas() {
		return tandas;
	}
	public void setTandas(Integer tandas) {
		this.tandas = tandas;
	}

	public Integer getJuegos_win_local() {
		return juegos_win_local;
	}
	public void setJuegos_win_local(Integer juegos_win_local) {
		this.juegos_win_local = juegos_win_local;
	}

	public Integer getSets_win_local() {
		return sets_win_local;
	}
	public void setSets_win_local(Integer sets_win_local) {
		this.sets_win_local = sets_win_local;
	}

	public Integer getPartidos_win_local() {
		return partidos_win_local;
	}
	public void setPartidos_win_local(Integer partidos_win_local) {
		this.partidos_win_local = partidos_win_local;
	}

	public Integer getJuegos_win_visit() {
		return juegos_win_visit;
	}
	public void setJuegos_win_visit(Integer juegos_win_visit) {
		this.juegos_win_visit = juegos_win_visit;
	}

	public Integer getSets_win_visit() {
		return sets_win_visit;
	}
	public void setSets_win_visit(Integer sets_win_visit) {
		this.sets_win_visit = sets_win_visit;
	}

	public Integer getPartidos_win_visit() {
		return partidos_win_visit;
	}
	public void setPartidos_win_visit(Integer partidos_win_visit) {
		this.partidos_win_visit = partidos_win_visit;
	}
	
}

