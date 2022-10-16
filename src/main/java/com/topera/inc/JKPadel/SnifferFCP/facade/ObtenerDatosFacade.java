package com.topera.inc.JKPadel.SnifferFCP.facade;

public interface ObtenerDatosFacade {

	// Obtiene los datos de la temporada indicada. 
	// Si la temporada es null se obtienen todas las temporadas
	public void obtenerDatosFCP() throws RuntimeException;
	
	// Metodo de ejemplo para realizar alguna llamada diferente a la anterior.
	public void comrpobarDatos();
	
}
