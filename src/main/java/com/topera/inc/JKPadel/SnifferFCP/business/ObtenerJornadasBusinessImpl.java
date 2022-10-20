package com.topera.inc.JKPadel.SnifferFCP.business;

import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.topera.inc.JKPadel.SnifferFCP.model.DAO.FaseDAO;
import com.topera.inc.JKPadel.SnifferFCP.model.DAO.GrupoDAO;
import com.topera.inc.JKPadel.SnifferFCP.model.DAO.LigaDAO;
import com.topera.inc.JKPadel.SnifferFCP.model.entity.Fase;
import com.topera.inc.JKPadel.SnifferFCP.model.entity.Grupo;
import com.topera.inc.JKPadel.SnifferFCP.model.entity.Liga;
import com.topera.inc.JKPadel.SnifferFCP.service.Helper;
import com.topera.inc.JKPadel.SnifferFCP.service.SSLHelper;

@Component
@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = RuntimeException.class, timeout = 18000)
public class ObtenerJornadasBusinessImpl implements ObtenerJornadasBusiness {

	private static final Logger logger = LogManager.getLogger(ObtenerJornadasBusinessImpl.class);
	
	@Autowired
	private LigaDAO ligaDAO;
	@Autowired
	private GrupoDAO grupoDAO;
	@Autowired
	private FaseDAO faseDAO;
	
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
				
				List<Grupo> grupos = grupoDAO.findByLiga(liga.getId_liga());
				
				for (Grupo grupo : grupos) {
				
					retorno += ObtenerJornadasLiga(liga.getId_liga(), grupo.getId_grupo(), grupo.getGenero());
				}
				
			}
			
			return retorno;
		
		}
		catch (Exception e) {
			logger.error("Error al obtener los datos de las Jornadas (ObtenerJornadas): " + e.getMessage());
			throw e;
		}
		
	}
	
	Integer ObtenerJornadasLiga (Integer idLiga, Integer idGrupo, String genero) throws Exception {
		
		Integer retorno = 0;
		
		try {
			
			logger.info("ObtenerJornadasBusinessImpl :: ObtenerJornadasLiga ") ;

			List<Fase> fases = faseDAO.findAll();
			
			for (Fase fase : fases) {
			
				//id=20453&genero=M&fase=1&grupo=19165&jornada=--
				String url = urlBase + calendario + "?id=" + idLiga + "&genero=" + genero + "&fase=" + fase.getId_fase() + "&grupo=" + idGrupo + "&jornada=--";
						
				Document doc = SSLHelper.getConnection(url).userAgent(userAgent).get();
				
				//Buscamos los datos de cada jornada. Comenzamos por los partidos cerrados.
				Elements tdJornadas = doc.select("td.cerrados > a");
				
				String link = urlBase + tdJornadas.attr("href");
				
				logger.error("Link a consultar: " + link);
				
				obtenerResultadoLiga(link);
				
			}
		
			return retorno;
			
		}
		catch (Exception e) {			
			logger.error("Error ObtenerJugadoresEquipo(" + idLiga + "," + idGrupo +  "," + genero + ") " + e.getMessage());
			throw e;
		}
		
	}
	
	void obtenerResultadoLiga(String url) throws Exception{
		
		try {
			
			logger.info("Procesando liga:  " + url);
			
			Document doc = SSLHelper.getConnection(url).userAgent(userAgent).get();
			
			//Buscamos los datos de la cabecera del encuentro.
			Elements elementos = doc.select("section.container > div.row.mBtm-20 > div > input");
			
			logger.error("Link a consultar: " + elementos.toString());
			
			int i = 0;
			for (Element input : elementos) {
				// Como vienen en orden siempre van a coincidir la posicion con lo que se recoge.
				switch(i) {
					case 0:
						Integer nJornada = Integer.parseInt(input.attr("value"));
						break;
					case 1:
						// MySql reconoce el formato YYYY-MM-DD hh:mm:ss como String. Será necesario reconvertirlo.					
						Helper helper = new Helper();
						Map<String, String> fecEliminatoria = helper.convierteFecha(input.attr("value"));						
						break;
					case 2:
						String lugar = input.attr("value");
						break;
					case 3:
						String orden = input.attr("value");
						break;
					case 4:
						String eqLocal = input.attr("value");
						break;
					case 5:
						String eqVisitante = input.attr("value");
						break;
					case 6:
						String jgLoc = input.attr("value");
						break;
					case 7:
						String setLoc = input.attr("value");
						break;
					case 8:
						String partidoLoc = input.attr("value");
						break;
					case 9:
						String jgVis = input.attr("value");
						break;
					case 10:
						String setVis = input.attr("value");
						break;
					case 11:
						String partidoVis = input.attr("value");
						break;					
				}

				
				
				i++;
			}
			
			
			
		}
		catch (Exception e) {			
			logger.error("Error obtenerResultadoLiga(" + url + ")" + e.getMessage());
			throw e;
		}
		
	}
}
