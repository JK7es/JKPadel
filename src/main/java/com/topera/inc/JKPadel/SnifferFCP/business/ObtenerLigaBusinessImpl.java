package com.topera.inc.JKPadel.SnifferFCP.business;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

import com.topera.inc.JKPadel.SnifferFCP.model.DAO.LigaDAO;
import com.topera.inc.JKPadel.SnifferFCP.model.entity.Liga;
import com.topera.inc.JKPadel.SnifferFCP.service.SSLHelper;

@Component
@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = RuntimeException.class, timeout = 18000)
public class ObtenerLigaBusinessImpl implements ObtenerLigaBusiness{

	private static final Logger logger = LogManager.getLogger(ObtenerLigaBusinessImpl.class);
	
	@Autowired
	private LigaDAO ligaDAO;
	
	@Value("${jsoup.url.userAgent}")
	private String userAgent;	
	@Value("${jsoup.url.urlBase}")
	private String urlBase;	
	@Value("${jsoup.url.ligas}")
	private String ligas;
	
	public ObtenerLigaBusinessImpl() {}

	@Override
	public Integer ObtenerLigas(Integer anno) throws Exception {
		logger.info("ObtenerLigaBusinessImpl :: ObtenerLigas :: ");
		
		Integer retorno = 0;
		try {
			
			logger.info("================================");
			logger.info("Obtenemos los datos de las LIGAS");
						
			List<Liga> ligas = ObtenerDatosLiga(anno);
			
			
			logger.info(".:: Ligas recuperadas ::. " + ligas.size());
			
			ligaDAO.save(ligas);
			
			logger.info("================================");
			retorno = ligas.size();
			return retorno;
		}
		catch (Exception e) {
			logger.error("Error al obtener los datos de las ligas (ObtenerLigas): " + e.getMessage());
			throw e;
		}
	}
	
	@Override
	public Integer ObtenerLigas() {
		logger.info("ObtenerLigaBusinessImpl :: ObtenerLigas :: ");
		
		Integer retorno = 0;
		try {
			
			logger.info("================================");
			logger.info("Obtenemos los datos de las LIGAS");
						
			List<Liga> ligas = ObtenerDatosLiga(null);
			
			
			logger.info(".:: Ligas recuperadas ::. " + ligas.size());
			
			ligaDAO.save(ligas);
			
			logger.info("================================");
			retorno = ligas.size();
			return retorno;
		}
		catch (Exception e) {
			logger.error("Error al obtener los datos de las ligas (ObtenerLigas): " + e.getMessage());
			return retorno;
		}
	}
	
	public List<Liga> ObtenerDatosLiga(Integer anno) {
	
		logger.info("ObtenerDatosLigaBusinessImpl :: ObtenerDatosLiga :: ");
		
		try {
			
			List<Liga> lLigas = new ArrayList<Liga>();
			
			String url = urlBase + ligas;
			
			logger.info("Procesando liga:  " + url);
			
			Document doc = SSLHelper.getConnection(url).userAgent(userAgent).get();
			
			//int esLigaEscolar = doc.select("h3").first().val().indexOf("ESCOLAR");			
			
			Elements ELigas = doc.select("form > select > option");
			
			for (Element htmlLiga : ELigas) {
				
				String ano = htmlLiga.select("option").get(0).val();
				
				String url2 = url + "?ano=" + ano;
				
				Document doc2 = SSLHelper.getConnection(url2).userAgent(userAgent).get();
				
				Element link = doc2.selectFirst("div.post-media > a");
				String linkFin = urlBase + link.attr("href");
				
				//https://federacioncantabradepadel.com/Liga_Calendario?Id=20453
				Integer id = Integer.parseInt(link.attr("href").substring(link.attr("href").indexOf("=") + 1));
				
				logger.info("Informacion de la liga " + ano + ": " + linkFin);				
				
								
				Optional<Liga> ligaAux = ligaDAO.findById(id);
				
				if (!ligaAux.isPresent()) {
					
					Liga liga = new Liga();
					liga.setId_liga(id);
					liga.setLiga(ano);
					liga.setTemporada(Integer.parseInt(ano));
					liga.setUrl(linkFin);
					liga.setC_categoria("ABS");

					logger.info("Añadiendo liga " + ano + " al array de ligas");
					lLigas.add(liga);
										
				}				
			}
			
			return lLigas;
		}
		catch (Exception e) {
			logger.error("Error al obtener los datos de la liga: " + e.getMessage());
			e.printStackTrace();
			return null;
		}
		
	}

}
