package mx.uniprotec.application.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mx.uniprotec.application.entity.Asignacion;
import mx.uniprotec.application.service.IAsignacionService;
import mx.uniprotec.application.util.UtilController;
import mx.uniprotec.entidad.modelo.AsignacionModelo;

@CrossOrigin(origins = { "*" })
@RestController
@RequestMapping("/crud")
public class AsignacionRestController {
	
	@Autowired
	private IAsignacionService asignacionService;
	
	
	
	 private final Logger log = LoggerFactory.getLogger(AsignacionRestController.class);
	public AsignacionRestController() {
		// TODO Auto-generated constructor stub
	}
	
	@GetMapping("/asignaciones")
	public ResponseEntity<?> index() {
//		return asignacionService.findAll();
		return UtilController.responseGeneric(asignacionService.findAll(), "asignaciones", HttpStatus.ACCEPTED);
	}

	 /*
	  * 
	  */
	@GetMapping("/asignacion/{id}")
	public ResponseEntity<?> show(@PathVariable Long id) {
		
		HttpStatus status ;
		Asignacion asignacion = null;
		Map<String, Object> response = new HashMap<>();
		
		try {
			asignacion = asignacionService.findById(id);
			status = HttpStatus.OK;
		} catch(DataAccessException e) {
			status = HttpStatus.INTERNAL_SERVER_ERROR;
//			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if(asignacion == null) {
			status = HttpStatus.NOT_FOUND;
//			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
//		status =  HttpStatus.OK;
//		return UtilController.responseGeneric(response, asignacion, "asignacion");
		return UtilController.responseGeneric(asignacion, "asignacion", status);
	}
	
	
	
	 /*
	  * 
	  */
	@SuppressWarnings("null")
	@PostMapping("/asignacion")
	public ResponseEntity<?> create(@Valid @RequestBody AsignacionModelo asignacion, BindingResult result) {
		
		HttpStatus status ;
		Asignacion asignacionNew = new Asignacion();
		Map<String, Object> response = new HashMap<>();
		
		if(result.hasErrors()) {

			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() +"' "+ err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			status = HttpStatus.BAD_REQUEST;
//			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		try {
			
			asignacionNew.setFechaAsignacion(asignacion.getFechaAsignacion());
			asignacionNew.setIdClienteAsignacion(asignacion.getIdClienteAsignacion());
			asignacionNew.setClienteAsignacion(asignacion.getClienteAsignacion());
			asignacionNew.setIdCursoAsignacion(asignacion.getIdCursoAsignacion());
			asignacionNew.setCursoAsignacion(asignacion.getCursoAsignacion());
			asignacionNew.setIdInstructorAsignacion(asignacion.getIdInstructorAsignacion());
			asignacionNew.setInstructorAsignacion(asignacion.getInstructorAsignacion());
			asignacionNew.setHorarioAsignacion(asignacion.getHorarioAsignacion());
			asignacionNew.setObservacionesAsignacion(asignacion.getObservacionesAsignacion());
			asignacionNew.setCreateAtAsignacion(asignacion.getCreateAtAsignacion());
			asignacionNew.setUserCreateAsignacion(asignacion.getUserCreateAsignacion());
			asignacionNew.setStatusAsignacion(asignacion.getStatusAsignacion());
			
			asignacionNew = asignacionService.save(asignacionNew);
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			status = HttpStatus.INTERNAL_SERVER_ERROR;
//			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "El asignacion ha sido creado con éxito!");
		response.put("asignacion", asignacionNew);
//		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
		status = HttpStatus.CREATED;
		return UtilController.responseGeneric(asignacion, "asignacion", status);
	}
	
	
	/*
	 * 
	 */
	@PutMapping("/asignacion/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody AsignacionModelo asignacion, BindingResult result, @PathVariable Long id) {

		HttpStatus status ;
		Asignacion asignacionActual = asignacionService.findById(id);

		Asignacion asignacionUpdated = null;

		Map<String, Object> response = new HashMap<>();

		if(result.hasErrors()) {

			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() +"' "+ err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
//			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
			status = HttpStatus.BAD_REQUEST;
		}
		
		if (asignacionActual == null) {
			response.put("mensaje", "Error: no se pudo editar, el asignacion ID: "
					.concat(id.toString().concat(" no existe en la base de datos!")));
//			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			status = HttpStatus.NOT_FOUND;
		}

		try {
		
			asignacionActual.setFechaAsignacion(asignacion.getFechaAsignacion());
			asignacionActual.setIdClienteAsignacion(asignacion.getIdClienteAsignacion());
			asignacionActual.setClienteAsignacion(asignacion.getClienteAsignacion());
			asignacionActual.setIdCursoAsignacion(asignacion.getIdCursoAsignacion());
			asignacionActual.setCursoAsignacion(asignacion.getCursoAsignacion());
			asignacionActual.setIdInstructorAsignacion(asignacion.getIdInstructorAsignacion());
			asignacionActual.setInstructorAsignacion(asignacion.getInstructorAsignacion());
			asignacionActual.setHorarioAsignacion(asignacion.getHorarioAsignacion());
			asignacionActual.setObservacionesAsignacion(asignacion.getObservacionesAsignacion());
			asignacionActual.setCreateAtAsignacion(asignacion.getCreateAtAsignacion());
			asignacionActual.setUserCreateAsignacion(asignacion.getUserCreateAsignacion());
			asignacionActual.setStatusAsignacion(asignacion.getStatusAsignacion());
			
			asignacionUpdated = asignacionService.save(asignacionActual);

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al actualizar el asignacion en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
//			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "El asignacion ha sido actualizado con éxito!");
		response.put("asignacion", asignacionUpdated);

//		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
		status = HttpStatus.CREATED;
		return UtilController.responseGeneric(asignacionUpdated, "asignacion", status);
	}
	
	
	/*
	 * 
	 */
	@DeleteMapping("/asignacion/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		
		HttpStatus status ;
		
		Map<String, Object> response = new HashMap<>();
		Asignacion asignacion = null;
		try {
			 asignacion = asignacionService.findById(id);
			String nombreFotoAnterior = "";//asignacion.getFoto();
			
			
			
		    asignacionService.delete(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al eliminar el asignacion de la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
//			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			status =  HttpStatus.INTERNAL_SERVER_ERROR;
		}
		
		response.put("mensaje", "El asignacion eliminado con éxito!");
		
//		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		status =  HttpStatus.OK;
		return UtilController.responseGeneric(asignacion, "asignacion", status);
	}
	
	
	
}
