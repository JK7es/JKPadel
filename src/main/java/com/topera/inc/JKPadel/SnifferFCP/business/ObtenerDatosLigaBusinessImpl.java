package com.topera.inc.JKPadel.SnifferFCP.business;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.topera.inc.JKPadel.util.BaseResponse;

@Component
public class ObtenerDatosLigaBusinessImpl implements ObtenerDatosLigaBusiness{

	private static final Logger logger = LogManager.getLogger(ObtenerDatosLigaBusinessImpl.class);
	
	@Autowired
	private ObtenerLigaBusinessImpl obtenerLiga;
	@Autowired
	private ObtenerLicenciasBusinessImpl obtenerJugador;	
	@Autowired
	private ObtenerGruposBusinessImpl obtenerGrupo;
	@Autowired
	private ObtenerEquiposBusinessImpl obtenerEquipo;
	@Autowired
	private ObtenerJugadoresEquiposBusinessImpl obtenerJugadoresEquipos;
	@Autowired
	private ObtenerJornadasBusinessImpl obtenerJornadas;
	
	
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
			nLigas = obtenerLiga.ObtenerLigas();
			logger.info(".:: Ligas insertadas ::. " + nLigas);
			logger.info("********************************");
			
			nLicencias = obtenerJugador.ObtenerJugadores();
			logger.info(".:: Jugadores insertadas ::. " + nLicencias);
			logger.info("********************************");
			
			nGrupos = obtenerGrupo.ObtenerGrupos();
			logger.info(".:: Grupos insertados ::. " + nGrupos);
			logger.info("********************************");
			
			nEquipos = obtenerEquipo.ObtenerEquipos();
			logger.info(".:: Grupos insertados ::. " + nGrupos);
			logger.info("********************************");
			
		}
		catch (Exception e) {
			logger.error("Error al obtener los datos de la web: " + e.getMessage());			
		}
	}
	
	@Transactional
	@Override
	public BaseResponse ObtenerdatosFCP(Integer anno, boolean obtLicencias, boolean obtGrupos, boolean obtEquipos, boolean obtJugadoresEquipos,
										boolean obtJornadas) {
		
		logger.info("ObtenerDatosLigaBusinessImpl :: ObtenerDatosFCP :: ");
		Integer nLigas 	   = 0;
		Integer nLicencias = 0;
		Integer nGrupos    = 0;
		Integer nEquipos   = 0;
		Integer nJugadores = 0;
		Integer nJornadas  = 0;
		
		BaseResponse baseResponse = new BaseResponse();
		
		try {
			
			logger.info("********************************");
			nLigas = obtenerLiga.ObtenerLigas(anno);
			logger.info(".:: Ligas insertadas ::. " + nLigas);
			logger.info("********************************");
			
			if (obtLicencias) {
				nLicencias = obtenerJugador.ObtenerJugadores();
				logger.info(".:: Licencias insertadas ::. " + nLicencias);
				logger.info("********************************");
			}
			
			if (obtGrupos) {
				nGrupos = obtenerGrupo.ObtenerGrupos(anno);
				logger.info(".:: Grupos insertados ::. " + nGrupos);
				logger.info("********************************");
			}
			
			if (obtEquipos) {
				nEquipos = obtenerEquipo.ObtenerEquipos(anno);
				logger.info(".:: Equipos insertados ::. " + nEquipos);
				logger.info("********************************");
			}
			
			if (obtJugadoresEquipos) {
				nJugadores = obtenerJugadoresEquipos.ObtenerJugadoresEquipos(anno);
				logger.info(".:: Jugadores insertados ::. " + nJugadores);
				logger.info("********************************");
			}
			
			if (obtJornadas) {
				nJornadas = obtenerJornadas.ObtenerJornadas(anno);
				logger.info(".:: Jornadas insertados ::. " + nJornadas);
				logger.info("********************************");
			}
			
			String respuesta = "Nuevas Ligas=" + nLigas +
							   " || Grupos=" + nGrupos + 
							   " || Equipos=" + nEquipos +
							   " || Jugadores=" + nJugadores;
			
			baseResponse.setOk(true);
			baseResponse.setResponseMessage(respuesta);
			
			return baseResponse;
		}
		catch (Exception e) {
			e.printStackTrace();
			logger.error("Error al obtener los datos de la web: " + e.getMessage());	
			
			baseResponse.setOk(true);
			baseResponse.setResponseMessage("Error al obtener los datos de la web: " + e.getMessage());
			
			return baseResponse;
		}
	}
}
