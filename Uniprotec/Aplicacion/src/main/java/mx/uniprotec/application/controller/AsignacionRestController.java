package mx.uniprotec.application.controller;

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
import mx.uniprotec.application.entity.Asignacion;
import mx.uniprotec.application.entity.AsignacionHistorico;
import mx.uniprotec.application.entity.EntregableEntity;
import mx.uniprotec.application.entity.Notificacion;
import mx.uniprotec.application.service.IAsignacionService;
import mx.uniprotec.application.service.IEntregableService;
import mx.uniprotec.entidad.modelo.AsignacionModelo;

@CrossOrigin(origins = { "*" })
@RestController
@RequestMapping("/crud")
public class AsignacionRestController {
	
	@Autowired
	private IAsignacionService asignacionService;
	@Autowired
	private INotificacionDao notificacionDao;
	@Autowired
	private IEntregableService entregableService;

	
	
	
	 private final Logger log = LoggerFactory.getLogger(AsignacionRestController.class);
	public AsignacionRestController() {
		// TODO Auto-generated constructor stub
	}
	
	@GetMapping("/asignaciones")
	public ResponseEntity<?> index() {
		log.info("asignaciones");
		List<Asignacion> asignaciones = null;
		Map<String, Object> response = new HashMap<>();
		try {
			asignaciones  = asignacionService.findAll();
			
			 response.put("asignaciones", asignaciones );
			 response.put("mensaje", "Exito en la busqueda de asignaciones ");
			 response.put("status", HttpStatus.ACCEPTED);
			 response.put("code", HttpStatus.ACCEPTED.value());
			 log.info("asignaciones fin");
			 return new ResponseEntity<Map<String, Object>>(response, HttpStatus.ACCEPTED);
		} catch (Exception e) {
			response.put("mensaje", e.getMessage().concat(": ").concat(((NestedRuntimeException) e).getMostSpecificCause().getMessage()));
			response.put("status", HttpStatus.INTERNAL_SERVER_ERROR);
			response.put("code", HttpStatus.INTERNAL_SERVER_ERROR.value());
			e.printStackTrace();
			log.info("asignaciones fin");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}

	 /*
	  * 
	  */
	@GetMapping("/asignacion/{id}")
	public ResponseEntity<?> show(@PathVariable Long id) {
		log.info("asignacion:"+id);
		HttpStatus status ;
		Asignacion asignacion = null;
		Map<String, Object> response = new HashMap<>();
		
		try {
			asignacion = asignacionService.findById(id);
			if(asignacion == null || asignacion.getStatusAsignacion().equals("Evento Cancelado")) {
				response.put("mensaje", "Error: no se pudo encontrar, asignacion ID: "
						.concat(id.toString().concat(" no existe en la base de datos!")));
				 response.put("status", HttpStatus.NOT_FOUND);
				 response.put("code", HttpStatus.NOT_FOUND.value());
				 log.info("asignacion fin :"+id);
				 return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			}
			
			 response.put("asignacion", asignacion );
			 response.put("mensaje", "Exito en la busqueda de asignacion ");
			 response.put("status", HttpStatus.ACCEPTED);
			 response.put("code", HttpStatus.ACCEPTED.value());
			 log.info("asignacion fin :"+id);
			 return new ResponseEntity<Map<String, Object>>(response, HttpStatus.ACCEPTED);
		} catch(DataAccessException e) {
			response.put("mensaje", e.getMessage().concat(": ").concat(((NestedRuntimeException) e).getMostSpecificCause().getMessage()));
			response.put("status", HttpStatus.INTERNAL_SERVER_ERROR);
			response.put("code", HttpStatus.INTERNAL_SERVER_ERROR.value());
			log.info("asignacion fin :"+id);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	
	
	 /*
	  * 
	  */
	@PostMapping("/asignacion")
	public ResponseEntity<?> create(@Valid @RequestBody AsignacionModelo asignacion, BindingResult result) {
		log.info("asignacion create");
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
			
			asignacionNew.setIdAsignacionLogica(asignacion.getIdAsignacionLogica());
			asignacionNew.setFechaAsignacion(asignacion.getFechaAsignacion());
			asignacionNew.setIdClienteAsignacion(asignacion.getIdClienteAsignacion());
			asignacionNew.setClienteAsignacion(asignacion.getClienteAsignacion());
			asignacionNew.setIdCursoAsignacion(asignacion.getIdCursoAsignacion());
			asignacionNew.setCursoAsignacion(asignacion.getCursoAsignacion());
			asignacionNew.setIdInstructorAsignacion(asignacion.getIdInstructorAsignacion());
			asignacionNew.setInstructorAsignacion(asignacion.getInstructorAsignacion());
			asignacionNew.setHorarioAsignacion(asignacion.getHorarioAsignacion());
			asignacionNew.setParticipantesAsignacion(asignacion.getParticipantesAsignacion());
			asignacionNew.setNivelAsignacion(asignacion.getNivelAsignacion());
			asignacionNew.setObservacionesAsignacion(asignacion.getObservacionesAsignacion());
			asignacionNew.setArchivosAsignacion(asignacion.getArchivosAsignacionTexto());
			asignacionNew.setIdRegionAsignacion(asignacion.getIdRegionAsignacion());
			asignacionNew.setNombreRegionAsignacion(asignacion.getNombreRegionAsignacion());
			if(asignacion.getTipoCursoAsignacion().equals("")) {
				asignacionNew.setTipoCursoAsignacion("PRESENCIAL");
			}else {
				asignacionNew.setTipoCursoAsignacion(asignacion.getTipoCursoAsignacion());
			}
			asignacionNew.setCreateAtAsignacion(asignacion.getCreateAtAsignacion());
			asignacionNew.setUserCreateAsignacion(asignacion.getUserCreateAsignacion());
			asignacionNew.setUserCreateAsignacionTexto(asignacion.getUserCreateAsignacionTexto());
			asignacionNew.setStatusAsignacion(asignacion.getStatusAsignacion());
			
			asignacionNew.setFechaPago("");
			asignacionNew.setGuiaEntregable("");
//			asignacionNew.setVerificarEntregable("");
			asignacionNew.setNumeroFactura("");
			asignacionNew.setArchivoParticipantes("");
			asignacionNew.setCostoHotel("");
			asignacionNew.setErrorProceso("");
			
			asignacionNew = asignacionService.save(asignacionNew);
			 response.put("asignacion", asignacionNew );
			 response.put("mensaje", "Asignacion creada con Exito");
			 response.put("status", HttpStatus.CREATED);
			 response.put("code", HttpStatus.CREATED.value());
			 log.info("asignacion create fin");
			 return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
		} catch(DataAccessException e) {
			response.put("mensaje", e.getMessage().concat(": ").concat(((NestedRuntimeException) e).getMostSpecificCause().getMessage()));
			response.put("status", HttpStatus.INTERNAL_SERVER_ERROR);
			response.put("code", HttpStatus.INTERNAL_SERVER_ERROR.value());
			log.info("asignacion create fin");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	/*
	 * 
	 */
	@PutMapping("/asignacion/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody AsignacionModelo asignacion, BindingResult result, @PathVariable Long id) {
		log.info("asignacion update:"+ id);
		
		Asignacion asignacionActual = asignacionService.findById(id);
		
		Asignacion asignacionUpdated = null;

		Map<String, Object> response = new HashMap<>();

		if(result.hasErrors()) {
			log.info("result.hasErrors");
			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() +"' "+ err.getDefaultMessage())
					.collect(Collectors.toList());
			
			 response.put("errors", errors);
			 response.put("mensaje", errors);
			 response.put("status", HttpStatus.BAD_REQUEST);
			 response.put("code", HttpStatus.BAD_REQUEST.value());
			 log.info("asignacion update fin:"+ id);
			 return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		if (asignacionActual == null) {
			response.put("mensaje", "Error: no se pudo editar, asignacion ID: "
					.concat(id.toString().concat(" no existe en la base de datos!")));
			 response.put("status", HttpStatus.NOT_FOUND);
			 response.put("code", HttpStatus.NOT_FOUND.value());
			 log.info("asignacion update fin:"+ id);
			 return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		log.info("update Asignacion:"+asignacion.toString());
		if(asignacion.getStatusAsignacion().equals("Evento Cancelado")) {
			try {
				List<Notificacion> notificacionUpdate = notificacionDao.findByIdAsignacionNotificacion(id);
				for(Notificacion notificacion : notificacionUpdate) {
					notificacion.setStatusNotificacion("cancelada");
					notificacionDao.save(notificacion);
				}
			} catch (Exception e) {
				response.put("mensaje", e.getMessage().concat(": ").concat(((NestedRuntimeException) e).getMostSpecificCause().getMessage()));
				response.put("status", HttpStatus.INTERNAL_SERVER_ERROR);
				response.put("code", HttpStatus.INTERNAL_SERVER_ERROR.value());
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}			
		}
		try {				
				asignacionActual.setIdAsignacionLogica(asignacion.getIdAsignacionLogica());
				asignacionActual.setFechaAsignacion(asignacion.getFechaAsignacion());
				asignacionActual.setIdClienteAsignacion(asignacion.getIdClienteAsignacion());
				asignacionActual.setClienteAsignacion(asignacion.getClienteAsignacion());
				asignacionActual.setIdCursoAsignacion(asignacion.getIdCursoAsignacion());
				asignacionActual.setCursoAsignacion(asignacion.getCursoAsignacion());
				asignacionActual.setTipoCursoAsignacion(asignacion.getTipoCursoAsignacion());
				asignacionActual.setIdInstructorAsignacion(asignacion.getIdInstructorAsignacion());
				asignacionActual.setInstructorAsignacion(asignacion.getInstructorAsignacion());
				asignacionActual.setHorarioAsignacion(asignacion.getHorarioAsignacion());
				asignacionActual.setParticipantesAsignacion(asignacion.getParticipantesAsignacion());
				asignacionActual.setNivelAsignacion(asignacion.getNivelAsignacion());
				asignacionActual.setObservacionesAsignacion(asignacion.getObservacionesAsignacion());
				asignacionActual.setArchivosAsignacion(asignacion.getArchivosAsignacionTexto());
				asignacionActual.setIdRegionAsignacion(asignacion.getIdRegionAsignacion());
				asignacionActual.setNombreRegionAsignacion(asignacion.getNombreRegionAsignacion());
				asignacionActual.setCreateAtAsignacion(asignacion.getCreateAtAsignacion());
				asignacionActual.setUserCreateAsignacion(asignacion.getUserCreateAsignacion());
				asignacionActual.setUserCreateAsignacionTexto(asignacion.getUserCreateAsignacionTexto());
				asignacionActual.setStatusAsignacion(asignacion.getStatusAsignacion());
				asignacionActual.setFechaPago(asignacion.getFechaPago());
				asignacionActual.setGuiaEntregable(asignacion.getGuiaEntregable());
				asignacionActual.setVerificarEntregable(asignacion.getVerificarEntregable());
				asignacionActual.setNumeroFactura(asignacion.getNumeroFactura());
				asignacionActual.setArchivoParticipantes(asignacion.getArchivoParticipantesTexto());
				asignacionActual.setCostoHotel(asignacion.getCostoHotel());
				if(asignacion.getErrorProceso() == null || asignacion.getErrorProceso().length() == 0) {
					asignacionActual.setErrorProceso("");
				}else {
					asignacionActual.setErrorProceso(asignacion.getErrorProceso());
				}
				
				asignacionUpdated = asignacionService.save(asignacionActual);
//				EntregableEntity ee = (EntregableEntity) entregableService.consultaEntregable(asignacion.getIdAsignacion());
				
				response.put("asignacion", asignacionUpdated  );
				 response.put("mensaje", "Asignacion actualizada con Exito");
				 response.put("status", HttpStatus.CREATED);
				 response.put("code", HttpStatus.CREATED.value());
				 log.info("asignacion update fin:"+ id);
				 return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);

			} catch (DataAccessException e) {
				response.put("mensaje", e.getMessage().concat(": ").concat(((NestedRuntimeException) e).getMostSpecificCause().getMessage()));
				response.put("status", HttpStatus.INTERNAL_SERVER_ERROR);
				response.put("code", HttpStatus.INTERNAL_SERVER_ERROR.value());
				log.info("asignacion update fin:"+ id);
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
//		}

	}
	
	
	@GetMapping("/asignacionesHistorico")
	public ResponseEntity<?> asignacionesHistorico() {
		log.info("asignacion historico");
		List<AsignacionHistorico> asignaciones = null;
		Map<String, Object> response = new HashMap<>();
		try {
			asignaciones  = asignacionService.findAllHistorico();
			
			 response.put("asignacionesHistorico", asignaciones );
			 response.put("mensaje", "Exito en la busqueda de asignaciones Historico");
			 response.put("status", HttpStatus.ACCEPTED);
			 response.put("code", HttpStatus.ACCEPTED.value());
			 log.info("asignacionesHistorico:"+asignaciones.size());
			 log.info("asignacion historico fin");
			 return new ResponseEntity<Map<String, Object>>(response, HttpStatus.ACCEPTED);
		} catch (Exception e) {
			response.put("mensaje", e.getMessage().concat(": ").concat(((NestedRuntimeException) e).getMostSpecificCause().getMessage()));
			response.put("status", HttpStatus.INTERNAL_SERVER_ERROR);
			response.put("code", HttpStatus.INTERNAL_SERVER_ERROR.value());
			log.info("asignacion historico fin");
			e.printStackTrace();
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}	
	
	
	
}
