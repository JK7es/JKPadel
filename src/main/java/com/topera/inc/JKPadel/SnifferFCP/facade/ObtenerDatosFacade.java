package com.topera.inc.JKPadel.SnifferFCP.facade;

import com.topera.inc.JKPadel.api.LanzarSnifarFCPDTO;
import com.topera.inc.JKPadel.util.BaseResponse;

public interface ObtenerDatosFacade {

	// Obtiene los datos de la temporada indicada. 
	// Si la temporada es null se obtienen todas las temporadas
	public void obtenerDatosFCP() throws RuntimeException;
	
	public BaseResponse obtenerDatosFCP(LanzarSnifarFCPDTO lanzarSnifarFCPDTO) throws RuntimeException, Exception;
	
	// Metodo de ejemplo para realizar alguna llamada diferente a la anterior.
	public void comrpobarDatos();
	
}
