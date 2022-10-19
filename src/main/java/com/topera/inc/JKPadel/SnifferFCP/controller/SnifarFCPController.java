package com.topera.inc.JKPadel.SnifferFCP.controller;

import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.topera.inc.JKPadel.SnifferFCP.service.SnifarFCPService;
import com.topera.inc.JKPadel.api.request.LanzarSnifarFCPRequestMessage;
import com.topera.inc.JKPadel.util.BaseResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


@Api(value = "snifar_fcp", tags = { "" }, consumes = MediaType.APPLICATION_JSON_VALUE, 
						   produces = MediaType.APPLICATION_JSON_VALUE, 
						   protocols = "http, https")
@RestController
@RequestMapping (value = "/snifar_fcp")
public class SnifarFCPController {

	private static final Logger logger = LogManager.getLogger(SnifarFCPController.class);
	
	private final SnifarFCPService service;
	
	@Autowired
    public SnifarFCPController(final SnifarFCPService service) {
        this.service = service;
    }
	
	@GetMapping(value = "/datosfcp")
    public void datosFcp(){
		
		logger.info("SnifarFCPController :: obtenerDatosFcp :: ");
		
        service.datosFcp();
        
     // Retorno de la respuesta.
    	logger.info("SnifarFCPController :: obtenerDatosFcp ::");
    }
	
	
	/**
     * Llama a /snifar_fcp/obtenerdatosfcp
     * @param request Peticion.
     * @return
     */
    @ApiOperation(value = "obtenerdatosfcp", response = BaseResponse.class, code = 200, notes = "Servicio REST/JSON que obtiene la informacion de la web.")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Listado de parametros obtenidos con éxito"),
            @ApiResponse(code = 401, message = "Error en la autorización para consultar el recurso."),
            @ApiResponse(code = 403, message = "Acceso no permitido para consultar el recurso"),
            @ApiResponse(code = 404, message = "Recurso no encontrado") })
    @RequestMapping(value = "/obtenerdatosfcp", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, 
    				produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public BaseResponse obtenerDatosFcp(@RequestBody final LanzarSnifarFCPRequestMessage request, HttpServletResponse res) {
    	
    	logger.info("SnifarFCPController :: obtenerDatosFcp :: Request({})", request);

        // Llamada al servicio.
    	BaseResponse baseResponse = this.service.obtenerDatosFcp(request);

        // Retorno de la respuesta.
    	logger.info("SnifarFCPController :: obtenerDatosFcp :: Response({})");
    	
    	return baseResponse;
        
    }
	
	
}
