package com.topera.inc.JKPadel.SnifferFCP.facade;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.topera.inc.JKPadel.SnifferFCP.business.ObtenerDatosLigaBusiness;

@Component("ObtenerDatosFacade")
public class ObtenerDatosFacadeImpl implements ObtenerDatosFacade{

	private static final Logger logger = LogManager.getLogger(ObtenerDatosFacadeImpl.class);
			
	@Autowired
	private ObtenerDatosLigaBusiness obtenerDatosLigaBusiness;
	
	@Autowired
	public ObtenerDatosFacadeImpl( ObtenerDatosLigaBusiness obtenerDatosLigaBusiness) {
		this.obtenerDatosLigaBusiness = obtenerDatosLigaBusiness;
	}

	public void obtenerDatosFCP() throws RuntimeException{
		logger.info("ObtenerDatosFacadeImpl :: obtenerDatosFCP :: ");
		
		try {
			logger.info(">>>INICIO Recuperaciondatos de las ligas de la web FCP.");
			
			obtenerDatosLigaBusiness.ObtenerdatosFCP();
			
			logger.info("<<<FIN Recuperaciondatos de las ligas de la web FCP.");
		}
		catch (Exception e) {
			logger.error("Error al obtener los datos de la liga: " + e.getMessage());
		}
		
	}
	
	@Override
	public void comrpobarDatos() {
		logger.info("ObtenerDatosFacadeImpl :: comrpobarDatos :: ");
		
	}

}
