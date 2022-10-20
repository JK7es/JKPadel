package com.topera.inc.JKPadel.SnifferFCP.facade;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.topera.inc.JKPadel.SnifferFCP.business.ObtenerDatosLigaBusiness;
import com.topera.inc.JKPadel.api.LanzarSnifarFCPDTO;
import com.topera.inc.JKPadel.util.BaseResponse;

@Component("ObtenerDatosFacade")
public class ObtenerDatosFacadeImpl implements ObtenerDatosFacade{

	private static final Logger logger = LogManager.getLogger(ObtenerDatosFacadeImpl.class);
			
	@Autowired
	private ObtenerDatosLigaBusiness obtenerDatosLigaBusiness;
	
	@Autowired
	public ObtenerDatosFacadeImpl( ObtenerDatosLigaBusiness obtenerDatosLigaBusiness) {
		this.obtenerDatosLigaBusiness = obtenerDatosLigaBusiness;
	}

	@Override
	public void obtenerDatosFCP() throws RuntimeException{
		logger.info("ObtenerDatosFacadeImpl :: obtenerDatosFCP :: ");
		
		try {
			logger.info(">>>INICIO Recuperaciondatos de las ligas de la web FCP.");
			
			obtenerDatosLigaBusiness.ObtenerdatosFCP();
			
			logger.info("<<<FIN Recuperaciondatos de las ligas de la web FCP.");
		}
		catch (Exception e) {
			logger.error("Error al obtener los datos de la FCP: " + e.getMessage());			
			throw new RuntimeException();
		}
		
	}
	
	@Override
	public void comrpobarDatos() {
		logger.info("ObtenerDatosFacadeImpl :: comrpobarDatos :: ");
		
	}

	@Override
	public BaseResponse obtenerDatosFCP(LanzarSnifarFCPDTO lanzarSnifarFCPDTO) throws Exception{
		
		logger.info("ObtenerDatosFacadeImpl :: obtenerDatosFCP :: ");
		
		logger.info(">>>INICIO Recuperaciondatos de las ligas de la web FCP.");
		
		BaseResponse baseResponse = obtenerDatosLigaBusiness.ObtenerdatosFCP(lanzarSnifarFCPDTO.getAnno(), lanzarSnifarFCPDTO.isObtenerLicencias(), 
																 lanzarSnifarFCPDTO.isObtenerGrupos(), lanzarSnifarFCPDTO.isObtenerEquipos(), 
																 lanzarSnifarFCPDTO.isObtenerJugadores(), lanzarSnifarFCPDTO.isObtenerJornadas());
		
		logger.info("<<<FIN Recuperaciondatos de las ligas de la web FCP.");
		
		return baseResponse;
		
		
	}

}
