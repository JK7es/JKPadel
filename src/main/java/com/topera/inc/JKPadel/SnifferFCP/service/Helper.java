package com.topera.inc.JKPadel.SnifferFCP.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Helper {
	
	

	public String concatNombre (String nombre, String apellido1, String apellido2) {
		String nombreCompleto = nombre + " " + apellido1 + " " + apellido2;
		return nombreCompleto.trim();
	}
	
	public String convierteFecha (String pFecha, String salida) {
		
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
		
//		System.out.println("fechaSalida: " + fechaSalida);
		
		
		if ("F".equals(salida)) {
			return fechaSalida;
		}
		else {
			return horaSalida;
		}
	}
}
