package com.topera.inc.JKPadel.SnifferFCP.business;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.topera.inc.JKPadel.SnifferFCP.model.DAO.LigaDAO;
import com.topera.inc.JKPadel.SnifferFCP.model.entity.Liga;
import com.topera.inc.JKPadel.SnifferFCP.service.SSLHelper;

@Component
@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = RuntimeException.class, timeout = 18000)
public class ObtenerJornadasBusinessImpl implements ObtenerJornadasBusiness {

	private static final Logger logger = LogManager.getLogger(ObtenerJornadasBusinessImpl.class);
	
	@Autowired
	private LigaDAO ligaDAO;
	
	@Value("${jsoup.url.userAgent}")
	private String userAgent;
	@Value("${jsoup.url.urlBase}")
	private String urlBase;
	@Value("${jsoup.url.calendario}")
	private String calendario;
	
	public ObtenerJornadasBusinessImpl() {}

	@Override
	public Integer ObtenerJornadas(Integer anno) throws Exception{
		
		logger.info("ObtenerJornadasBusinessImpl :: ObtenerJornadas :: ");
		Integer retorno = 0;
		
		try {
			logger.info("====================================");
			logger.info("Obtenemos los datos de las JORNADAS");
			
			List<Liga> ligas = ligaDAO.findByTemporada(anno);
					
			for (Liga liga : ligas) {
				
				retorno += ObtenerJornadasLiga(liga.getId_liga());				
			}
			
			return retorno;
		
		}
		catch (Exception e) {
			logger.error("Error al obtener los datos de las Jornadas (ObtenerJornadas): " + e.getMessage());
			throw e;
		}
		
	}
	
	Integer ObtenerJornadasLiga (Integer idLiga) throws Exception {
		
		Integer retorno = 0;
		
		try {
			
			logger.info("ObtenerJornadasBusinessImpl :: ObtenerJornadasLiga ") ;
			
			String url = urlBase + calendario + "?id=" + idLiga;
			
			//id=20453&genero=M&fase=1&grupo=19165&jornada=--
			
			Document doc = SSLHelper.getConnection(url).userAgent(userAgent).get();
		
			return retorno;
			
		}
		catch (Exception e) {			
			logger.error("Error ObtenerJugadoresEquipo()" );
			throw e;
		}
		
	}
}
