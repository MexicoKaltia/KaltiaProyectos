package mx.uniprotec.application.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mx.uniprotec.application.service.IAplicacionService;
import mx.uniprotec.application.util.UtilController;

@CrossOrigin(origins = { "http://localhost:8080" })
@RestController
@RequestMapping("/crud")
public class AplicacionController {
	

	@Autowired
	private IAplicacionService aplicacionService;
	
	 private final Logger log = LoggerFactory.getLogger(UsuarioRestController.class);

	 /*
	  * 
	  */
	@GetMapping("/regiones")
	public ResponseEntity<?> regiones() {
//		return usuarioService.findAll();
		return UtilController.responseGeneric(aplicacionService.findAllRegiones(), "regiones", HttpStatus.ACCEPTED);
	}
	
	
	
	
}
