package mx.uniprotec.application.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.NestedRuntimeException;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mx.uniprotec.application.dao.INotificacionDao;
import mx.uniprotec.application.entity.Mensaje;
import mx.uniprotec.application.entity.Notificacion;
import mx.uniprotec.application.entity.Perfil;
import mx.uniprotec.application.entity.Region;
import mx.uniprotec.application.service.IAplicacionService;
import mx.uniprotec.entidad.modelo.AsignacionModelo;
import mx.uniprotec.entidad.modelo.MensajeModelo;
import mx.uniprotec.entidad.modelo.UserCorreo;

@CrossOrigin(origins = { "*" })
@RestController
@RequestMapping("/crud")
public class AplicacionController {
	

	@Autowired
	private IAplicacionService aplicacionService;
	
	@Autowired
	private INotificacionDao notificacionDao;


	
	
	
	 private final Logger log = LoggerFactory.getLogger(AplicacionController.class);

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
//			log.info(nombrePerfil);
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
	
	@PostMapping("/correos")
	public ResponseEntity<?> correos(@Valid @RequestBody List<UserCorreo> usersCorreo, BindingResult result) {
		
		HttpStatus status ;
		Map<String, Object> response = new HashMap<>();
//		log.info("Correos : " + usersCorreo.toString());
		if(result.hasErrors()) {

			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() +"' "+ err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			status = HttpStatus.BAD_REQUEST;
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		try {			
			usersCorreo = aplicacionService.usersCorreo(usersCorreo);
//			log.info(usersCorreo.toString());
			response.put("usersCorreo", usersCorreo);
			 response.put("mensaje", "Consulta Correos Exitosa");
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
	
	@PostMapping("/mensaje")
	public ResponseEntity<?> mensaje(@Valid @RequestBody MensajeModelo mensaje, BindingResult result) {
		
		HttpStatus status ;
		Map<String, Object> response = new HashMap<>();
//		log.info("Correos : " + usersCorreo.toString());
		if(result.hasErrors()) {
			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() +"' "+ err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			status = HttpStatus.BAD_REQUEST;
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		try {			
			Mensaje mensajeDao = aplicacionService.altaMensaje(mensaje);
//			log.info(mensaje.toString());
			 response.put("mensaje", mensajeDao);
			 response.put("mensaje", "Alta Mensaje Exitosa");
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
	
	@GetMapping("/notificaciones/{idUsuario}")
	public ResponseEntity<?> getNotificaciones(@PathVariable Long idUsuario) {
		
		Map<String, Object> response = new HashMap<>();
		List<Notificacion> notificaciones = new ArrayList<Notificacion>();
		try {
//			log.info(nombrePerfil);
			notificaciones = aplicacionService.getNotificaciones(idUsuario, "Instructor");
			if(notificaciones == null) {
				log.info("notificaciones null");
				response.put("mensaje", "Usuario no tiene notificaciones");
				 response.put("status", HttpStatus.NOT_FOUND);
				 response.put("code", HttpStatus.NOT_FOUND.value());
				 return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			}else {
				response.put("notificaciones", notificaciones);
				 response.put("mensaje", "Exito en la busqueda de notificaciones");
				 response.put("status", HttpStatus.ACCEPTED);
				 response.put("code", HttpStatus.ACCEPTED.value());
				 return new ResponseEntity<Map<String, Object>>(response, HttpStatus.ACCEPTED);
			}
		} catch(DataAccessException e) {
			response.put("mensaje", e.getMessage().concat(": ").concat(((NestedRuntimeException) e).getMostSpecificCause().getMessage()));
			response.put("status", HttpStatus.INTERNAL_SERVER_ERROR);
			 response.put("code", HttpStatus.INTERNAL_SERVER_ERROR.value());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@PostMapping("/notificacion")
	public ResponseEntity<?> notificacionCreate(@Valid @RequestBody AsignacionModelo asignacion, BindingResult result) {
		
		HttpStatus status ;
		Map<String, Object> response = new HashMap<>();
//		log.info("Correos : " + usersCorreo.toString());
		if(result.hasErrors()) {
			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() +"' "+ err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			status = HttpStatus.BAD_REQUEST;
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		try {	
			String mensaje = "Hola, usted tiene una nueva notificación respecto a la Asignación : "
					+ asignacion.getIdAsignacionLogica() 
					+". \n Se ha adjuntado el Archivo de Participantes,"
					+"\n dar click para verificar Archivo de Asignacion y confirmar presente Notificación. ";
			
			Notificacion notificacion = new Notificacion();
			
			notificacion.setIdAsignacionNotificacion(asignacion.getIdAsignacion());
			notificacion.setIdAsignacionLogicaNotificacion(asignacion.getIdAsignacionLogica());
			notificacion.setIdVendedorNotificacion(asignacion.getUserCreateAsignacion());
			notificacion.setIdInstructorNotificacion(asignacion.getIdInstructorAsignacion());
			notificacion.setStatusNotificacion("nueva");
			notificacion.setReferenciaNotificacion("notificacion/".concat(asignacion.getIdAsignacion().toString()));
			notificacion.setCreateAtNotificacion(asignacion.getCreateAtAsignacion());
			notificacion.setUserCreateNotificacion(asignacion.getUserCreateAsignacion());
			notificacion.setMensaje(mensaje);
			
			notificacionDao.save(notificacion);
			
//			log.info(mensaje.toString());
//			 response.put("notificacion", mensajeDao);
			 response.put("mensaje", "Alta Notificacion Exitosa");
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
	
	@PutMapping("/notificacion/{id}")
	public ResponseEntity<?> notificacionUpdate(@PathVariable Long id) {
		
		HttpStatus status ;
		Map<String, Object> response = new HashMap<>();
		
		try {
//			Notificacion notificacionUpdate = notificacionDao.findById(id).orElse(null);
			List<Notificacion> notificacionUpdate = notificacionDao.findByIdAsignacionNotificacion(id);
			for(Notificacion notificacion : notificacionUpdate) {
				notificacion.setStatusNotificacion("atendida");
				notificacionDao.save(notificacion);
			}
			
			
//			log.info(mensaje.toString());
//			 response.put("notificacion", mensajeDao);
			 response.put("mensaje", "Actualizacion Notificacion Exitosa");
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
