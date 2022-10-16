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

import com.topera.inc.JKPadel.SnifferFCP.model.DAO.FaseDAO;
import com.topera.inc.JKPadel.SnifferFCP.model.DAO.GrupoDAO;
import com.topera.inc.JKPadel.SnifferFCP.model.DAO.LigaDAO;
import com.topera.inc.JKPadel.SnifferFCP.model.entity.Fase;
import com.topera.inc.JKPadel.SnifferFCP.model.entity.Grupo;
import com.topera.inc.JKPadel.SnifferFCP.model.entity.Liga;
import com.topera.inc.JKPadel.SnifferFCP.service.SSLHelper;

@Component
@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = RuntimeException.class, timeout = 18000)
public class ObtenerGruposBusinessImpl implements ObtenerGruposBusiness{

	private static final Logger logger = LogManager.getLogger(ObtenerGruposBusinessImpl.class);
	
	@Autowired
	private LigaDAO ligaDAO;
	@Autowired
	private GrupoDAO grupoDAO;
	@Autowired
	private FaseDAO faseDAO;
	
	// Lectura de properties	
	@Value("${jsoup.url.userAgent}")
	private String userAgent;	
	@Value("${jsoup.url.urlBase}")
	private String urlBase;	
	@Value("${jsoup.url.ligas}")
	private String ligas;
	@Value("${jsoup.url.grupos}")
	private String grupos;
	@Value("${jsoup.url.licencias}")
	private String licencias;
	
	public ObtenerGruposBusinessImpl() {
		
	}

	@Override
	public Integer ObtenerGrupos() {
		logger.info("ObtenerGruposBusinessImpl :: ObtenerGrupos :: ");
		
		try {
			
			logger.info("================================");
			logger.info("Obtenemos los datos de los GRUPOS");
			
			List<Liga> ligas = ligaDAO.findAll();
//			List<Grupo> gruposT = new ArrayList<>();
			Integer gruposRecuperados = 0;
			
			for (Liga liga : ligas) {
				
				List<Grupo> grupos = ObtenerGruposByLiga(liga);
//				gruposT.addAll(grupos);
				grupoDAO.save(grupos);	
				gruposRecuperados += grupos.size();
				
				logger.info(".:: Liga {} - Nº GRUPOS {} ::. ", liga.getTemporada(), grupos.size());
			}
			
			logger.info(".:: GRUPOS TOTALES recuperados {}::. ", gruposRecuperados);
//			grupoDAO.save(gruposT);			
			
			logger.info("=================================");
			return gruposRecuperados;
			
		}
		catch (Exception e) {
			logger.error("Error al obtener los datos de los grupos (ObtenerGrupos): " + e.getMessage());
			return null;
		}
	}
	
	public List<Grupo> ObtenerGruposByLiga(Liga liga) {
		
		logger.info("ObtenerGruposBusinessImpl :: ObtenerGruposByLiga :: {}-{}", liga.getTemporada(), liga.getC_categoria());
		try {
			
			if (liga.getTemporada() == 2021) {
				String a="";
				a=a;
			}
			
			// Buscamos todas las fases posibles que puede tener la liga
			List<Fase> fases = faseDAO.findAll();
			List<Grupo> gruposT = new ArrayList<Grupo>();
			
			for (Fase fase : fases) {
			
				// Tratamos los grupos Masculinos
				String url = urlBase + grupos + "?id=" + liga.getId_liga() + "&genero=M&fase=" + fase.getId_fase() + "&grupo=-&jornada=--";
				
				logger.info("Procesando Liga " + url );
				logger.info("****LIGA " + liga.getTemporada() + " - FASE " + fase.getId_fase() + "****");
	
				Document doc = SSLHelper.getConnection(url).userAgent(userAgent).get();
	//System.out.println( doc.html());
				Element EselectGrupos = doc.getElementById("grupo");
						
				Elements EselOptions = 	EselectGrupos.select("option");
				
				List<Grupo> gruposM = trataGrupo(liga.getTemporada(), liga.getId_liga(), "M", EselOptions, fase.getId_fase());
				logger.info("********* Nº GRUPOS {} *********", gruposM.size());
				gruposT.addAll(gruposM);
				
				
				// Tratamos los grupos femeninos
				url = urlBase + grupos + "?id=" + liga.getId_liga() + "&genero=F&fase=" + fase.getId_fase() + "&grupo=-&jornada=--";
				
				logger.info("Procesando Liga " + url );
	
				doc = SSLHelper.getConnection(url).userAgent(userAgent).get();
	//System.out.println( doc.html());
				EselectGrupos = doc.getElementById("grupo");
						
				EselOptions = 	EselectGrupos.select("option");
				
				List<Grupo> gruposF = trataGrupo(liga.getTemporada(), liga.getId_liga(), "F", EselOptions, fase.getId_fase());
				logger.info("********* Nº GRUPOS {} *********", gruposF.size());
				gruposT.addAll(gruposF);
			}
			
			return gruposT;
		}
		catch (Exception e) {
			logger.error("Error al obtener los datos de los grupo por liga (ObtenerGrupos)s: " + e.getMessage());
			e.printStackTrace();
			return null;
		}
	}
	
	private List<Grupo> trataGrupo(Integer anno, Integer idLiga, String genero, Elements EselOptions, Integer id_fase) {
		
		try {
			
			List<Grupo> lGrupos = new ArrayList<>();
			
			for (Element option : EselOptions ) {
				if (!"-".equals(option.select("option").get(0).val())) {
					
					String idGrupo = option.select("option").get(0).val();
					String tGrupo = option.select("option").get(0).text();
					
					Optional<Grupo> grupoAux = grupoDAO.findById(Integer.parseInt(idGrupo));
					
					if (!grupoAux.isPresent()) {
						
						Grupo grupo = new Grupo();						
						grupo.setId_grupo(Integer.parseInt(idGrupo));
						grupo.setId_liga(idLiga);
						grupo.setId_fase(id_fase);
						grupo.setGrupo(tGrupo);
						grupo.setGenero(genero);
						String param = "?id=" + grupo.getId_liga() + "&genero=" + grupo.getGenero() + "&grupo=" + grupo.getId_grupo() + "&fase=" + id_fase + "&jornada=--";
						grupo.setUrl(urlBase + grupos + param);
											
						logger.info("{} - Encontrado GRUPO {} ({}) - URL: {}", anno, grupo.getGrupo(), grupo.getId_grupo(), grupo.getUrl());
						
						lGrupos.add(grupo);
						
						//logger.info("{} - GRUPO GUARDADO - {} ({}) - URL: {}", anno, grupo.getGrupo(), grupo.getId_grupo(), grupo.getUrl());
						
					}
				}	
			}			
			return lGrupos;
		}
		catch(Exception e) {
			logger.error("Error al obtener los datos de los grupo por liga (ObtenerGrupos)s: " + e.getMessage());
			e.printStackTrace();
			return null;
		}		
	}

}
