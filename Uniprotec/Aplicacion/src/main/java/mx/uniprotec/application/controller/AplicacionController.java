package mx.uniprotec.application.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.NestedRuntimeException;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mx.uniprotec.application.entity.Cliente;
import mx.uniprotec.application.entity.Perfil;
import mx.uniprotec.application.service.IAplicacionService;
import mx.uniprotec.application.util.UtilController;
import mx.uniprotec.application.entity.Region;

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
		List<Region> regiones = null;
		Map<String, Object> response = new HashMap<>();
		try {
			regiones = aplicacionService.findAllRegiones();
			 response.put("regiones", regiones);
			 response.put("mensaje", "Exito en la busqueda de regiones");
			 response.put("status", HttpStatus.ACCEPTED);
			 response.put("code", HttpStatus.ACCEPTED.value());
			 return new ResponseEntity<Map<String, Object>>(response, HttpStatus.ACCEPTED);
		} catch (Exception e) {
			response.put("mensaje", e.getMessage().concat(": ").concat(((NestedRuntimeException) e).getMostSpecificCause().getMessage()));
			response.put("status", HttpStatus.INTERNAL_SERVER_ERROR);
			response.put("code", HttpStatus.INTERNAL_SERVER_ERROR.value());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	
	}
	
	@GetMapping("/perfiles")
	public ResponseEntity<?> perfiles() {
		List<Perfil> perfiles= null;
		Map<String, Object> response = new HashMap<>();
		try {
			perfiles = aplicacionService.findAllPerfiles();
			 response.put("perfiles", perfiles);
			 response.put("mensaje", "Exito en la busqueda de perfiles");
			 response.put("status", HttpStatus.ACCEPTED);
			 response.put("code", HttpStatus.ACCEPTED.value());
			 return new ResponseEntity<Map<String, Object>>(response, HttpStatus.ACCEPTED);
		} catch (Exception e) {
			response.put("mensaje", e.getMessage().concat(": ").concat(((NestedRuntimeException) e).getMostSpecificCause().getMessage()));
			response.put("status", HttpStatus.INTERNAL_SERVER_ERROR);
			response.put("code", HttpStatus.INTERNAL_SERVER_ERROR.value());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	
	}
	
	 /*
	  * 
	  */
	@GetMapping("/perfil/{nombrePerfil}")
	public ResponseEntity<?> show(@PathVariable String nombrePerfil) {
		
		Map<String, Object> response = new HashMap<>();
		Perfil perfil = new Perfil();
		try {
			log.info(nombrePerfil);
			perfil = aplicacionService.findByNombrePerfil(nombrePerfil);
			
			if(perfil == null) {
				response.put("mensaje", "Error: no se pudo encontrar, el perfil ID: "
						.concat(nombrePerfil.toString().concat(" no existe en la base de datos!")));
				 response.put("status", HttpStatus.NOT_FOUND);
				 response.put("code", HttpStatus.NOT_FOUND.value());
				 return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			}
  			response.put("perfil", perfil);
			 response.put("mensaje", "Exito en la busqueda de perfil");
			 response.put("status", HttpStatus.ACCEPTED);
			 response.put("code", HttpStatus.ACCEPTED.value());
			 return new ResponseEntity<Map<String, Object>>(response, HttpStatus.ACCEPTED);
		} catch(DataAccessException e) {
			response.put("mensaje", e.getMessage().concat(": ").concat(((NestedRuntimeException) e).getMostSpecificCause().getMessage()));
			response.put("status", HttpStatus.INTERNAL_SERVER_ERROR);
			 response.put("code", HttpStatus.INTERNAL_SERVER_ERROR.value());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	
}
