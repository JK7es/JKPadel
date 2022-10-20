package com.topera.inc.JKPadel.SnifferFCP.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Helper {
		
	private static final Logger logger = LogManager.getLogger(Helper.class);
		
	public String concatNombre (String nombre, String apellido1, String apellido2) {
		String nombreCompleto = nombre + " " + apellido1 + " " + apellido2;
		return nombreCompleto.trim();
	}
	
	/**
	 * Dada una fecha en formato "domingo, 28 de febrero de 2021 - 17:00" se devolverá un array de Strings 
	 * con los formatos -> DD/MM/YYYY
	 * 					-> hh:mm
	 * 					-> YYYY-MM-DD hh:mm:ss (Formato que admite MySql como DateTime Format)
	 * 
	 * @param pFecha
	 * @param salida
	 * @return Map<String, String>
	 */
	public Map<String, String> convierteFecha (String pFecha) {
		
		logger.info("Se trocea la fecha ->" + pFecha + "<-");
		
		Map<String, String> map = new HashMap<String, String>();
	    		
		String fechaSalida = "";
		String horaSalida  = "";
//		System.out.println("Parametro fecha: " + pFecha);
		
		horaSalida = pFecha.substring(pFecha.indexOf(" - ") + 3).trim();
//		System.out.println("Hora: " + horaSalida);
		
		String fecha = pFecha.substring(pFecha.indexOf(",") + 1, pFecha.indexOf(" - ")).trim();
//		System.out.println("fecha: " + fecha);
					
		DateTimeFormatter esDateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate hoy = LocalDate.parse(fecha, DateTimeFormatter.ofPattern("dd 'de' MMMM 'de' yyyy").withLocale(new Locale("es", "ES")));
		fechaSalida = hoy.format(esDateFormat);
		
		esDateFormat = DateTimeFormatter.ofPattern("YYYY-MM-DD");
		hoy = LocalDate.parse(fecha, DateTimeFormatter.ofPattern("dd 'de' MMMM 'de' yyyy").withLocale(new Locale("es", "ES")));

		String fechaMySql = hoy.format(esDateFormat) + " " + horaSalida;
		
		logger.info("La fecha se ha troceado en: " + fechaSalida);
		logger.info("                            " + horaSalida);
		logger.info("                            " + fechaMySql);
		
		map.put("fecha", fechaSalida);
	    map.put("hora", horaSalida);
	    map.put("fechaMySql", fechaMySql);
		
		return map;

	}
}
