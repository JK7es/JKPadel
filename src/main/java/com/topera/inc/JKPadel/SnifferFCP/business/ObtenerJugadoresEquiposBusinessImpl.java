package com.topera.inc.JKPadel.SnifferFCP.business;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
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

import com.topera.inc.JKPadel.SnifferFCP.model.DAO.EquipoDAO;
import com.topera.inc.JKPadel.SnifferFCP.model.DAO.EquipoJugadorDAO;
import com.topera.inc.JKPadel.SnifferFCP.model.DAO.JugadorDAO;
import com.topera.inc.JKPadel.SnifferFCP.model.DAO.LigaDAO;
import com.topera.inc.JKPadel.SnifferFCP.model.entity.Equipo;
import com.topera.inc.JKPadel.SnifferFCP.model.entity.EquipoJugador;
import com.topera.inc.JKPadel.SnifferFCP.model.entity.Jugador;
import com.topera.inc.JKPadel.SnifferFCP.model.entity.Liga;
import com.topera.inc.JKPadel.SnifferFCP.service.SSLHelper;

@Component
//@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = RuntimeException.class, timeout = 18000)
public class ObtenerJugadoresEquiposBusinessImpl implements ObtenerJugadoresEquiposBusiness{
	
	private static final Logger logger = LogManager.getLogger(ObtenerEquiposBusinessImpl.class);
	
	// Lectura de properties	
	@Value("${jsoup.url.userAgent}")
	private String userAgent;
	
	@Autowired 
	private EquipoDAO equipoDAO;
	@Autowired 
	private JugadorDAO jugadorDAO;
	@Autowired 
	private LigaDAO ligaDAO;
	@Autowired 
	private EquipoJugadorDAO equipoJugadorDAO;
	
	@Override
	public Integer ObtenerJugadoresEquipos(Integer anno) throws Exception {
		
		logger.info("ObtenerJugadoresEquiposBusinessImpl :: ObtenerJugadoresEquipos :: ");
		Integer retorno = 0;
		
		try {			
		
			// Obtenemos los grupos de la liga del año solicitado
			List<Equipo> equipos = equipoDAO.findByAnno(anno);
			
			for (Equipo equipo : equipos) {
				
				retorno += ObtenerJugadoresEquipo (equipo.getUrl(), equipo.getGenero(), equipo.getId_liga(), equipo.getId_equipo());
			}
		
			return retorno;
		}
		catch (Exception e) {			
			logger.error("Error al obtener los datos de los jugadores por equipo (ObtenerJugadoresEquipos): " + e.getMessage());
			throw e;
		}
	}

	
	public Integer ObtenerJugadoresEquipo(String url, String genero, Integer idLiga, Integer idEquipo) throws Exception {
		
		Integer retorno = 0;
		
		BufferedWriter bw = null;
	    FileWriter fw = null;
	    File file = new File("./logs/RevisionJg.txt");
		
		try {
			
			logger.info("ObtenerJugadoresEquiposBusinessImpl :: ObtenerJugadoresEquipo ") ;
			
			Document doc = SSLHelper.getConnection(url).userAgent(userAgent).get();
			
			// Comenzamos la busqueda de los jugadores del equipo.
			Elements trJugadores = doc.select("tr.lineas");
			
			// Si el archivo no existe, se crea!
	        if (!file.exists()) {
	            file.createNewFile();
	        }
			
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
				
				List<Jugador> jugadorAux = jugadorDAO.findByName(jugador.getNombre(), jugador.getApellido1(), jugador.getApellido2());
				
				boolean insertar = false;
				EquipoJugador equipoJugador = new EquipoJugador();
				
				if (jugadorAux.isEmpty() || jugadorAux.size() > 1) {
					
					String data = jugador.getNombre() + ";" + jugador.getApellido1() + ";" + jugador.getApellido2() + ";\n";			        
			        
			        // flag true, indica adjuntar información al archivo.
			        fw = new FileWriter(file.getAbsoluteFile(), true);
			        bw = new BufferedWriter(fw);
			        bw.write(data);
					
					logger.info(".:::::::::::::::::::::::::::::::::::::::::::::: REVISAR {} {} {}  ::.", jugador.getNombre(), jugador.getApellido1(), jugador.getApellido2());
					
				}
				else {
 					logger.info(".:: Jugador {} {} {} ENCONTRADO ::.", jugador.getNombre(), jugador.getApellido1(), jugador.getApellido2());
					jugador.setId_jugador(jugadorAux.get(0).getId_jugador());
					
					Optional<Liga> liga = ligaDAO.findById(idLiga);					
					
					equipoJugador.setIdEquipo(idEquipo);
					equipoJugador.setIdJugador(jugador.getId_jugador());
					equipoJugador.setIdLiga(idLiga);
					equipoJugador.setTemporada(liga.get().getTemporada());
					equipoJugador.setIdLiga(liga.get().getId_liga());
					
					insertar = true;
				}
				
				if (insertar) {					
					List<EquipoJugador> eqJg = equipoJugadorDAO.findByAllIds(equipoJugador.getIdJugador(), equipoJugador.getIdEquipo(), equipoJugador.getTemporada());
					if (eqJg.isEmpty()) {
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
			logger.error("Error ObtenerJugadoresEquipo()" );
			throw e;
		}
	}
	
}