package com.topera.inc.JKPadel.SnifferFCP.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.topera.inc.JKPadel.SnifferFCP.facade.ObtenerDatosFacade;

@Component
public class SnifarFCPServiceImpl implements SnifarFCPService{
	
	private static final Logger logger = LogManager.getLogger(SnifarFCPServiceImpl.class);
	
	private ObtenerDatosFacade obtenerDatosFacade;
	
	@Autowired
	public SnifarFCPServiceImpl(ObtenerDatosFacade obtenerDatosFacade) {
		this.obtenerDatosFacade = obtenerDatosFacade;
	}
	
	@Override	
	public void datosFcp() {

		logger.info("SnifarFCPServiceImpl :: datosFcp :: ");
		
		try {
			obtenerDatosFacade.obtenerDatosFCP();
		}
		catch (Exception e) {
			logger.error("Error al recuperar información: " + e.getMessage());
		}
	}
}
