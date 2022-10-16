package com.topera.inc.JKPadel.SnifferFCP.business;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ObtenerDatosLigaBusinessImpl implements ObtenerDatosLigaBusiness{

	private static final Logger logger = LogManager.getLogger(ObtenerDatosLigaBusinessImpl.class);
	
	@Autowired
	private ObtenerLigaBusinessImpl obtenerLiga;
	@Autowired
	private ObtenerJugadoresBusinessImpl obtenerJugador;	
	@Autowired
	private ObtenerGruposBusinessImpl obtenerGrupo;
	@Autowired
	private ObtenerEquiposBusinessImpl obtenerEquipo;
	
	public ObtenerDatosLigaBusinessImpl () {}
	
	@Override
	public void ObtenerdatosFCP() {
		
		logger.info("ObtenerDatosLigaBusinessImpl :: ObtenerDatosFCP :: ");
		Integer nLigas 	   = 0;
		Integer nLicencias = 0;
		Integer nGrupos    = 0;
		Integer nEquipos   = 0;
		try {
			
			logger.info("********************************");
//			nLigas = obtenerLiga.ObtenerLigas();
//			logger.info(".:: Ligas insertadas ::. " + nLigas);
			logger.info("********************************");
			
//			nLicencias = obtenerJugador.ObtenerJugadores();
//			logger.info(".:: Jugadores insertadas ::. " + nLicencias);
			logger.info("********************************");
			
//			nGrupos = obtenerGrupo.ObtenerGrupos();
//			logger.info(".:: Grupos insertados ::. " + nGrupos);
			logger.info("********************************");
			
			nEquipos = obtenerEquipo.ObtenerEquipos();
			logger.info(".:: Grupos insertados ::. " + nGrupos);
			logger.info("********************************");
			
		}
		catch (Exception e) {
			logger.error("Error al obtener los datos de la web: " + e.getMessage());			
		}
	}
}
