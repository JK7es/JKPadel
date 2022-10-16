package com.topera.inc.JKPadel.SnifferFCP.business;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
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

import com.topera.inc.JKPadel.SnifferFCP.model.DAO.EquipoDAO;
import com.topera.inc.JKPadel.SnifferFCP.model.DAO.EquipoGrupoDAO;
import com.topera.inc.JKPadel.SnifferFCP.model.DAO.EquipoJugadorDAO;
import com.topera.inc.JKPadel.SnifferFCP.model.DAO.GrupoDAO;
import com.topera.inc.JKPadel.SnifferFCP.model.DAO.JugadorDAO;
import com.topera.inc.JKPadel.SnifferFCP.model.DAO.LigaDAO;
import com.topera.inc.JKPadel.SnifferFCP.model.DAO.SedeDAO;
import com.topera.inc.JKPadel.SnifferFCP.model.entity.Equipo;
import com.topera.inc.JKPadel.SnifferFCP.model.entity.EquipoGrupo;
import com.topera.inc.JKPadel.SnifferFCP.model.entity.EquipoJugador;
import com.topera.inc.JKPadel.SnifferFCP.model.entity.Grupo;
import com.topera.inc.JKPadel.SnifferFCP.model.entity.Jugador;
import com.topera.inc.JKPadel.SnifferFCP.model.entity.Liga;
import com.topera.inc.JKPadel.SnifferFCP.model.entity.Sede;
import com.topera.inc.JKPadel.SnifferFCP.service.SSLHelper;

@Component
public class ObtenerEquiposBusinessImpl implements ObtenerEquiposBusiness{
	
	private static final Logger logger = LogManager.getLogger(ObtenerEquiposBusinessImpl.class);
	
	@Autowired
	private LigaDAO ligaDAO;
	@Autowired
	private GrupoDAO grupoDAO;
	@Autowired 
	private EquipoDAO equipoDAO;
	@Autowired 
	private SedeDAO sedeDAO;
	@Autowired 
	private EquipoGrupoDAO equipoGrupoDAO;
	@Autowired 
	private EquipoJugadorDAO equipoJugadorDAO;
	@Autowired 
	private JugadorDAO jugadorDAO;
	
	
	// Lectura de properties	
	@Value("${jsoup.url.userAgent}")
	private String userAgent;	
	@Value("${jsoup.url.urlBase}")
	private String urlBase;	
	@Value("${jsoup.url.equipos}")
	private String listadoEquipos;	
	
	@Override
	public Integer ObtenerEquipos() {
		
		logger.info("ObtenerEquiposBusinessImpl :: ObtenerEquipos :: ");
		Integer retorno = 0;
		
		try {
			logger.info("====================================");
			List<Liga> ligas = ligaDAO.findAll();
			Integer equiposRecuperados = 0;
			
			for (Liga liga : ligas) {
				Integer nEquipos = ObtenerEquiposByLiga(liga);
				
				logger.info(".:: Liga {} - Nº Equipos {} ::. ", liga.getTemporada(), nEquipos);
				equiposRecuperados += nEquipos;
			}
			
			return equiposRecuperados;		
		}
		catch (Exception e) {
			e.printStackTrace();
			logger.error("Error al obtener los datos de los equipos (ObtenerEquipos): " + e.getMessage());
			return -1;
		}
	}

	public Integer ObtenerEquiposByLiga(Liga liga) {
		logger.info("ObtenerGruposBusinessImpl :: ObtenerEquiposByLiga :: {}-{}", liga.getTemporada(), liga.getC_categoria());
		Integer equiposRecuperados = 0;
		
		try {
			
			logger.info("====================================");
			List<Grupo> grupos = grupoDAO.findByLiga(liga.getId_liga());
			
			for (Grupo grupo : grupos) {
				Integer nEquipos = ObtenerEquiposByGrupo(grupo, liga);
				
				equiposRecuperados += nEquipos;				
			}
			
			return equiposRecuperados;
		}
		catch (Exception e) {
			e.printStackTrace();
			logger.error("Error al obtener los datos de los Equipos - ObtenerEquiposByLiga({})", liga.getTemporada());
			return -1;
		}
	}
	
	public Integer ObtenerEquiposByGrupo(Grupo grupo, Liga liga) {
		
		logger.info("ObtenerGruposBusinessImpl :: ObtenerEquiposByLiga :: {}-{}({})", grupo.getGenero(), grupo.getGrupo(), grupo.getId_grupo());
		Integer equiposRecuperados = 0;
		
		List<Equipo> lEquipos = new ArrayList<>();
		
		try {
			
			String parametros = "?id=" + grupo.getId_liga() + "&genero=" + grupo.getGenero() + "&grupo=" + grupo.getId_grupo();
			
			String url = urlBase + listadoEquipos + parametros; 
					
			Document doc = SSLHelper.getConnection(url).userAgent(userAgent).get();
			
			Elements ElinksEquipos = doc.select("tr.lineas > td:eq(1) > a");
			
			for (Element htmlEquipo : ElinksEquipos) {
				
				String dEquipo = htmlEquipo.text();
				String link = urlBase + htmlEquipo.attr("href");
				int idEquipo = Integer.parseInt(link.substring(link.indexOf("=") + 1, link.indexOf("&")));
				logger.info(idEquipo + " - " + dEquipo + "(" +  grupo.getGenero() + ") "  + url );
								
				Optional<Equipo> equipoAux = equipoDAO.findById(idEquipo);
				
				if (!equipoAux.isPresent()) {
					Equipo equipo = new Equipo();
					equipo.setId_equipo(idEquipo);
					equipo.setEquipo(dEquipo);
					equipo.setGenero(grupo.getGenero());
					equipo.setUrl(link);
					
					lEquipos.add(equipo);
					
					Equipo EquipoInserted = equipoDAO.save(equipo);					
				}
				
				Integer idSede = ObtenerSedeByEquipo(link);
				
				Optional<EquipoGrupo> GrupoEquipoAux = equipoGrupoDAO.findByIdEquipoGrupo(idEquipo, grupo.getId_grupo());
				
				if (!GrupoEquipoAux.isPresent()) {
					EquipoGrupo equipoGrupo = new EquipoGrupo();
					equipoGrupo.setId_equipo(idEquipo);
					equipoGrupo.setId_grupo(grupo.getId_grupo());
					equipoGrupo.setId_sede(idSede);
					
					EquipoGrupo EquipoGrupoInserted = equipoGrupoDAO.save(equipoGrupo);
				}
				
				Integer nJugadores = ObtenerJugadoresEquipo(link, grupo.getGenero(), liga, idEquipo);
				
			}
			
			return equiposRecuperados;
		}
		catch (Exception e) {
			e.printStackTrace();
			logger.error("Error al obtener los datos de los Equipos - ObtenerEquiposByGrupo({})", grupo.getId_grupo());
			return -1;
		}
	}
	
	public Integer ObtenerSedeByEquipo(String url) {
		
		Integer retorno = -1;
		try {
			Document doc = SSLHelper.getConnection(url).userAgent(userAgent).get();
			
			String nombreSede = doc.select("div.row.centrado > h5:eq(2)").text();
			
			if (nombreSede.length() > 6) {
				
				nombreSede = nombreSede.substring(6);
			}
			else {
				nombreSede = "SIN SEDE";
			}
			
			Optional<Sede> sedeAux = sedeDAO.findBySede(nombreSede);
			
			if (!sedeAux.isPresent()) {
				Sede sede = new Sede();
				sede.setSede(nombreSede);
				
				Sede sedeInserted =sedeDAO.save(sede);
				retorno = sedeInserted.getId_sede();
			}
			else {
				retorno = sedeAux.get().getId_sede();
			}
			
			return retorno;
		}
		catch (Exception e) {
			e.printStackTrace();
			logger.error("Error al obtener los datos de los Equipos - ObtenerSedeByEquipo({})", url);			
			return -1;
		}
	}
	
	public Integer ObtenerJugadoresEquipo(String url, String genero, Liga liga, Integer idEquipo) {
		
		Integer retorno = 0;
		
		BufferedWriter bw = null;
	    FileWriter fw = null;
		
		try {
			
			logger.info("ObtenerGruposBusinessImpl :: ObtenerJugadoresEquipo ") ;
			
			Document doc = SSLHelper.getConnection(url).userAgent(userAgent).get();
			
			// Comenzamos la busqueda de los jugadores del equipo.
			Elements trJugadores = doc.select("tr.lineas");
			
//			FileWriter fileRevisionJg = new FileWriter("./logs/RevisionJg.txt");
			
			
			for (Element trJugador : trJugadores) {
				
				String ape1 	= trJugador.select("td:eq(1)").text().trim();
				String ape2 	= trJugador.select("td:eq(2)").text().trim();
				String nombre 	= trJugador.select("td:eq(3)").text().trim();
				String cat 		= trJugador.select("td:eq(4)").text().trim();
				String puntos 	= trJugador.select("td:eq(5)").text().trim();
				
				Jugador jugador = new Jugador();
				jugador.setNombre(nombre);
				jugador.setApellido1(ape1);
				jugador.setApellido2(ape2);
				jugador.setGenero(genero);
				
				
				Optional<Jugador> jugadorAux = jugadorDAO.findByName(jugador.getNombre(), jugador.getApellido1(), jugador.getApellido2());
				
				
				boolean insertar = false;
				EquipoJugador equipoJugador = new EquipoJugador();
				
				if (!jugadorAux.isPresent()) {
					
					String data = jugador.getNombre() + ";" + jugador.getApellido1() + ";" + jugador.getApellido2() + ";\n";
			        File file = new File("./logs/RevisionJg.txt");
			        
			        // Si el archivo no existe, se crea!
			        if (!file.exists()) {
			            file.createNewFile();
			        }
			        // flag true, indica adjuntar información al archivo.
			        fw = new FileWriter(file.getAbsoluteFile(), true);
			        bw = new BufferedWriter(fw);
			        bw.write(data);
					
					logger.info(".:::::::::::::::::::::::::::::::::::::::::::::: REVISAR {} {} {}  ::.", jugador.getNombre(), jugador.getApellido1(), jugador.getApellido2());
					
				}
				else {
 					logger.info(".:: Jugador {} {} {} ENCONTRADO ::.", jugador.getNombre(), jugador.getApellido1(), jugador.getApellido2());
					jugador.setId_jugador(jugadorAux.get().getId_jugador());
					
					equipoJugador.setIdEquipo(idEquipo);
					equipoJugador.setIdJugador(jugador.getId_jugador());
					equipoJugador.setIdLiga(liga.getId_liga());
					equipoJugador.setTemporada(liga.getTemporada());
					
					insertar = true;
				}
				
				if (insertar) {					
					List<EquipoJugador> eqJg = equipoJugadorDAO.findByAllIds(jugadorAux.get().getId_jugador(), idEquipo, liga.getId_liga());
					if (eqJg == null) {
						equipoJugadorDAO.save(equipoJugador);
						retorno++;
					}	
				}
			}
			
			if (bw != null)
                bw.close();
            if (fw != null)
                fw.close();
			return retorno;			
			
		}
		catch (Exception e) {
			e.printStackTrace();
			logger.error("Error ObtenerJugadoresEquipo()" );
			return -1;
		}
	}
	
}
