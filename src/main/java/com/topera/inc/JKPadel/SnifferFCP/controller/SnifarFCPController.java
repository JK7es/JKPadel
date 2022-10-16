package com.topera.inc.JKPadel.SnifferFCP.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.topera.inc.JKPadel.SnifferFCP.service.SnifarFCPService;

@RestController
@RequestMapping (value = "/snifar_fcp")
public class SnifarFCPController {

	private static final Logger logger = LogManager.getLogger(SnifarFCPController.class);
	
	@Autowired
	private final SnifarFCPService service = null;
	
	@GetMapping(value = "/fcp")
    public void datosFcp(){
		
		logger.info("SnifarFCPController :: datosFcp :: ");
		
        service.datosFcp();
    }
}
