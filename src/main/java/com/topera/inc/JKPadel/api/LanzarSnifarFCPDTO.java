package com.topera.inc.JKPadel.api;

import com.topera.inc.JKPadel.util.ServiceDTO;

public class LanzarSnifarFCPDTO extends ServiceDTO {

	private static final long serialVersionUID = 1L;
	
	private Integer anno;
	
	private boolean obtenerJugadores;
	
	private boolean obtenerGrupos;
	
	private boolean obtenerEquipos;
	
	private boolean obtenerLicencias;
	
	private boolean obtenerJornadas;

	public Integer getAnno() {
		return anno;
	}

	public void setAnno(Integer anno) {
		this.anno = anno;
	}

	public boolean isObtenerLicencias() {
		return obtenerLicencias;
	}

	public void setObtenerLicencias(boolean obtenerLicencias) {
		this.obtenerLicencias = obtenerLicencias;
	}
	
		public boolean isObtenerGrupos() {
		return obtenerGrupos;
	}

	public void setObtenerGrupos(boolean obtenerGrupos) {
		this.obtenerGrupos = obtenerGrupos;
	}

	public boolean isObtenerEquipos() {
		return obtenerEquipos;
	}

	public void setObtenerEquipos(boolean obtenerEquipos) {
		this.obtenerEquipos = obtenerEquipos;
	}

	public boolean isObtenerJugadores() {
		return obtenerJugadores;
	}

	public void setObtenerJugadores(boolean obtenerJugadores) {
		this.obtenerJugadores = obtenerJugadores;
	}

	public boolean isObtenerJornadas() {
		return obtenerJornadas;
	}

	public void setObtenerJornadas(boolean obtenerJornadas) {
		this.obtenerJornadas = obtenerJornadas;
	}
	
	
}
