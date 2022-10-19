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

import com.topera.inc.JKPadel.SnifferFCP.model.DAO.JugadorDAO;
import com.topera.inc.JKPadel.SnifferFCP.model.entity.Jugador;
import com.topera.inc.JKPadel.SnifferFCP.service.SSLHelper;

@Component
@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = RuntimeException.class, timeout = 18000)
public class ObtenerLicenciasBusinessImpl implements ObtenerLicenciasBusiness{

	private static final Logger logger = LogManager.getLogger(ObtenerLicenciasBusinessImpl.class);

	@Value("${jsoup.url.userAgent}")
	private String userAgent;	
	@Value("${jsoup.url.urlBase}")
	private String urlBase;	
	@Value("${jsoup.url.licencias}")
	private String licencias;
	
	@Autowired
	private JugadorDAO jugadorDAO;
	
	public ObtenerLicenciasBusinessImpl() {
		
	}

	@Override
	public Integer ObtenerJugadores() throws Exception {
		
		logger.info("ObtenerLicenciasBusinessImpl :: ObtenerJugadores :: ");
		Integer retorno = 0;
		
		try {
			logger.info("====================================");
			logger.info("Obtenemos los datos de las LICENCIAS");
						
			List<Jugador> jugadoresM = ObtenerLicenciasFEP("M");
			List<Jugador> jugadoresF = ObtenerLicenciasFEP("F");
			List<Jugador> jugadoresT = new ArrayList<Jugador>();
			
			jugadoresT.addAll(jugadoresM);
			jugadoresT.addAll(jugadoresF);
			
			logger.info(".:: Jugadores recuperados ::. " + jugadoresT.size());
			
			jugadorDAO.save(jugadoresT);
			
			logger.info("====================================");
			retorno = jugadoresT.size();
			
			return retorno;
		}
		catch (Exception e) {
			logger.error("Error al obtener los datos de los Jugadores (ObtenerJugadores): " + e.getMessage());
			throw e;
		}
	}
	
	public List<Jugador> ObtenerLicenciasFEP(String genero) throws Exception {
		
		logger.info("ObtenerJugadoresBusinessImpl :: ObtenerLicenciasFEP :: ");
		
		try {
			
			List<Jugador> jugadores = new ArrayList<Jugador>();			
			
			String url = urlBase + licencias + genero;
			
			logger.info(".: Procesando Licencias {} :. -> {}", genero, url);
			
			Document doc = SSLHelper.getConnection(url).userAgent(userAgent).get();
			
			Elements Ejugadores = doc.select("table.standard-table > tbody > tr");
			
			int 	i = 0;
			Jugador jugador = null;
			String 	licencia = "";
			String 	t_nombreComp = "";
			Integer t_licencia;
			
			for (Element htmlJugador : Ejugadores) {
				
				// Para quitar la cabecera
				if (i != 0) {
				
					licencia 	 = "";
					t_nombreComp = "";
				
					licencia   	 = htmlJugador.select("td strong").get(0).text().replace(".", "");
					t_nombreComp = htmlJugador.select("td").get(1).text();
					t_licencia 	 = Integer.parseInt(licencia);
					
					Optional<Jugador> jgAux = jugadorDAO.findById(t_licencia);
					
					if(!jgAux.isPresent()) {
						jugador = separarNombreCompleto(t_licencia, t_nombreComp, genero);
						
						if (jugador != null) {
							jugadores.add(jugador);
							
							logger.info("Jugador a INSERTAR --> {} ({})", t_nombreComp, t_licencia);
						}
					}
					else {
						Jugador jg = jgAux.get();
						Jugador jgUpdate = new Jugador();
						jgUpdate = actualizarNombre(jg, t_licencia, t_nombreComp, genero);
						if (jgUpdate != null) {
							jugadorDAO.update(jgUpdate);
						}
					}						
				}
				i++;
			}
			
			return jugadores;
		}
		catch (Exception e) {
			logger.error("Error al obtener las licencias: " + e.getMessage());
			throw e;
		}
	}
	
	
	private Jugador separarNombreCompleto(int n_licencia, String nombreCompleto, String genero) throws Exception {
		
		int posicion 	= 0;
		String tAux		= "";
		String t_nombre = "";
		String t_apellido1 = "";
		String t_apellido2 = "";		
		
		try {

			if (n_licencia == 174895) {
				posicion = 0;
			}
			
			posicion = nombreCompleto.indexOf(", ");
			if (posicion < 0 ) {
				t_nombre = nombreCompleto.substring(0);
			}
			else {
				t_nombre = nombreCompleto.substring(posicion + 2);
				tAux = nombreCompleto.substring(0, posicion);
				
				posicion = tAux.indexOf(" ");
				t_apellido1 = tAux.substring(0, posicion);
				tAux = tAux.substring(posicion + 1);
				
				t_apellido2 = tAux;
			}
			Jugador jugador = new Jugador();
			jugador.setId_jugador(n_licencia);
			jugador.setNombre(t_nombre);
			jugador.setApellido1(t_apellido1);
			jugador.setApellido2(t_apellido2);
			jugador.setGenero(genero);
						
			return jugador;
			
		}catch(Exception e) {
			logger.error("---->Error separarNombreCompleto ({}, {}, {})", n_licencia, nombreCompleto, genero , e);		    
			throw e;
		}
	}
	
	private Jugador actualizarNombre(Jugador jg, int t_licencia, String t_nombreComp, String genero) throws Exception {
		
		try {
			
			Jugador newJg = separarNombreCompleto(t_licencia, t_nombreComp, genero);
			
			if (!jg.getNombre().equals(newJg.getNombre()) ||
				!jg.getApellido1().equals(newJg.getApellido1()) || 
				!jg.getApellido2().equals(newJg.getApellido2())) {
				
				jg.setNombre(newJg.getNombre());
				jg.setApellido1(newJg.getApellido1());
				jg.setApellido2(newJg.getApellido2());
				
				logger.info("Jugador a ACTUALIZAR --> {} ({})", t_nombreComp, t_licencia);
				
				return jg;
			}
			else {
				return null;
			}
			
			
		}catch(Exception e) {
			logger.error("Error updateando {} {} {} Licencia {}", jg.getNombre(), jg.getApellido1(), jg.getApellido2(), jg.getId_jugador(), e);
			throw e;
		}
	}
}
