package com.topera.inc.JKPadel.SnifferFCP.service;

import com.topera.inc.JKPadel.api.request.LanzarSnifarFCPRequestMessage;
import com.topera.inc.JKPadel.util.BaseResponse;

public interface SnifarFCPService {
	
	/**
	* <b>Descripcion</b>: Lanza el inicio del snifeo de la web FCP.
	**/
	
	BaseResponse obtenerDatosFcp(LanzarSnifarFCPRequestMessage request);

	void datosFcp();

}
