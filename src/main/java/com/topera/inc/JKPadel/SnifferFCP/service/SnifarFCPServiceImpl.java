package com.topera.inc.JKPadel.SnifferFCP.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.topera.inc.JKPadel.SnifferFCP.facade.ObtenerDatosFacade;
import com.topera.inc.JKPadel.api.request.LanzarSnifarFCPRequestMessage;
import com.topera.inc.JKPadel.util.BaseResponse;

@Component
public class SnifarFCPServiceImpl implements SnifarFCPService{
	
	private static final Logger logger = LogManager.getLogger(SnifarFCPServiceImpl.class);
	
	private ObtenerDatosFacade obtenerDatosFacade;
	
	@Autowired
	public SnifarFCPServiceImpl(ObtenerDatosFacade obtenerDatosFacade) {
		this.obtenerDatosFacade = obtenerDatosFacade;
	}
	
	
	@Override	
	public BaseResponse obtenerDatosFcp(LanzarSnifarFCPRequestMessage request) {

		logger.info("SnifarFCPServiceImpl :: datosFcp :: ");
		
		BaseResponse baseResponse = new BaseResponse();
		
		try {
			baseResponse = obtenerDatosFacade.obtenerDatosFCP(request.getLanzarSnifarFCPDTO());
			
			return baseResponse;
		}
		catch (Exception e) {
			logger.error("Error al recuperar información: " + e.getMessage());
			
			baseResponse.setOk(false);
			baseResponse.setResponseMessage("Error al recuperar información: " + e.getMessage());			
			return baseResponse;
		}
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
