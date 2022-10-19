package com.topera.inc.JKPadel.SnifferFCP.business;

import com.topera.inc.JKPadel.util.BaseResponse;

public interface ObtenerDatosLigaBusiness {

	public BaseResponse ObtenerdatosFCP(Integer anno, boolean obtLicencias, boolean obtGrupos, boolean obtEquipos, boolean obtJugadoresEquipos,
										boolean obtJornadas);
	
	
	public void ObtenerdatosFCP();
	
}
