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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mx.uniprotec.application.entity.EntregableEntity;
import mx.uniprotec.application.entity.ParticipanteEntity;
import mx.uniprotec.application.service.IEntregableService;
import mx.uniprotec.entidad.modelo.EntregableModelo;
import mx.uniprotec.entidad.modelo.ParticipantesModelo;

@CrossOrigin(origins = { "*" })
@RestController
@RequestMapping("/crud")
public class EntregableRestController {
	
	@Autowired
	private IEntregableService entregableService;
//	@Autowired
//	private IParticipanteService participanteService;
	
	
	private final Logger log = LoggerFactory.getLogger(EntregableRestController.class);
	public EntregableRestController() {
		// TODO Auto-generated constructor stub
	}
	
	@GetMapping("/entregable/{idAsignacion}")
	public ResponseEntity<?> consultaEntregables(@PathVariable String idAsignacion) {
		log.info("consulta entregable");
		List<EntregableEntity> entregables = null;
		Map<String, Object> response = new HashMap<>();
		try {
			entregables = (List<EntregableEntity>) entregableService.consultaEntregable(Long.valueOf(idAsignacion));
			List<List<ParticipanteEntity>> arrayParticipantes = new ArrayList<List<ParticipanteEntity>>(); 
			Long idEntregable = null;
			for(EntregableEntity ee : entregables) {
				idEntregable = ee.getIdEntregable();
				List<ParticipanteEntity> participantes = entregableService.consultaParticipantes(idEntregable);
				arrayParticipantes.add(participantes);
			}
			
			 response.put("entregables", entregables);
			 response.put("participantes", arrayParticipantes);
			 response.put("mensaje", "Exito en la busqueda de entregables ");
			 response.put("status", HttpStatus.ACCEPTED);
			 response.put("code", HttpStatus.ACCEPTED.value());
			 log.info("consulta entregables fin");
			 return new ResponseEntity<Map<String, Object>>(response, HttpStatus.ACCEPTED);
		} catch (Exception e) {
			response.put("mensaje", e.getMessage().concat(": ").concat(((NestedRuntimeException) e).getMostSpecificCause().getMessage()));
			response.put("status", HttpStatus.INTERNAL_SERVER_ERROR);
			response.put("code", HttpStatus.INTERNAL_SERVER_ERROR.value());
			e.printStackTrace();
			log.info("consulta entregable error fin");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@SuppressWarnings("unused")
	@PostMapping("/entregable")
	public ResponseEntity<?> create(@Valid @RequestBody EntregableModelo entregable, BindingResult result) {
		log.info("entregbale create");
		HttpStatus status ;
		EntregableEntity entregableEntity = new EntregableEntity ();
		Map<String, Object> response = new HashMap<>();
		
		if(result.hasErrors()) {
			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() +"' "+ err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			status = HttpStatus.BAD_REQUEST;
		}
		try {		
			entregableEntity.setIdAsignacion(entregable.getIdAsignacion());
			entregableEntity.setFormARazonSocial(entregable.getFormARazonSocial());
			entregableEntity.setFormARFC(entregable.getFormARFC());
			entregableEntity.setFormACurso(entregable.getFormACurso());
			entregableEntity.setFormADuracion(entregable.getFormADuracion());
			entregableEntity.setFormAFechaInicioDC3(entregable.getFormAFechaInicioDC3());
			entregableEntity.setFormAFechaFinDC3(entregable.getFormAFechaFinDC3());
			entregableEntity.setFormAFechaDiploma(entregable.getFormAFechaDiploma());
			entregableEntity.setFormAEquipoCredencial(entregable.getFormAEquipoCredencial());
			entregableEntity.setFormAFechaInicioCredenciales(entregable.getFormAFechaInicioCredenciales());
			entregableEntity.setFormAFechaFinalCredenciales(entregable.getFormAFechaFinalCredenciales());
			entregableEntity.setFormAInstructor(entregable.getFormAInstructor());
			entregableEntity.setFormARepresentanteEmpresa(entregable.getFormARepresentanteEmpresa());
			entregableEntity.setFormARepresentanteTrabajador(entregable.getFormARepresentanteTrabajador());
			entregableEntity.setFormALogo(entregable.getFormALogo());
			
//			entregableEntity.setFormBParticipantes(getPArticipantes(entregable.getFormBParticipantes()));
//			
			entregableEntity.setFormCRazonSocial(entregable.getFormCRazonSocial());
			entregableEntity.setFormCCurso(entregable.getFormCCurso());
			entregableEntity.setFormCDuracion(entregable.getFormCDuracion());
			entregableEntity.setFormCInstructor(entregable.getFormCInstructor());
			entregableEntity.setFormCFechaInicio(entregable.getFormCFechaInicio());
			entregableEntity.setFormCFechaFinal(entregable.getFormCFechaFinal());
			entregableEntity.setFormCSede(entregable.getFormCSede());
			entregableEntity.setFormCComentariosGrupo(entregable.getFormCComentariosGrupo());
			entregableEntity.setFormCProcesoAprendizaje(entregable.getFormCProcesoAprendizaje());
			entregableEntity.setFormCTeoria(entregable.getFormCTeoria());
			entregableEntity.setFormCPractica(entregable.getFormCPractica());
			entregableEntity.setFormCEvidenciasFotograficas(getEvidenciasFoto(entregable.getFormCEvidenciasFotograficas()));
			entregableEntity.setFormCRecomendaciones(entregable.getFormCRecomendaciones());
			entregableEntity.setFormCNivelCumplimiento(entregable.getFormCNivelCumplimiento());
			entregableEntity.setFormCContingencias(entregable.getFormCContingencias());
			entregableEntity.setFormCAvancesLogrados(entregable.getFormCAvancesLogrados());
			entregableEntity.setFormCObservaciones(entregable.getFormCObservaciones());
			entregableEntity.setFormCEvidenciaDocto(entregable.getFormCEvidenciaDocto());
			entregableEntity.setStatusEntregable(entregable.getStatus());
			entregableEntity.setCreateAtEntregable(entregable.getCreateAt());
			entregableEntity.setUserCreateEntregable(entregable.getUserCreate());
			
			EntregableEntity entregableNew = null;
			ParticipanteEntity pe = null;
			if(entregable.getIdEntregable() == null) {
				log.info("nuevo Entregable");
				entregableNew = entregableService.createEntregable(entregableEntity);
				pe = entregableService.createParticipantes(entregableNew.getIdEntregable(), entregableNew.getIdAsignacion(), getParticipantes(entregable.getFormBParticipantes(), entregableNew.getIdEntregable()));
			}else {
				// Update
				log.info("update Entregable");
				entregableEntity.setIdEntregable(entregable.getIdEntregable());
				entregableNew = entregableService.createEntregable(entregableEntity);
				pe = entregableService.updateParticipantes(getParticipantes(entregable.getFormBParticipantes(), entregableNew.getIdEntregable()), entregableNew.getIdEntregable());
			}
			
			
			
			if(entregableNew != null ) {
				 response.put("entregable", entregableEntity );
				 response.put("mensaje", "Asignacion creada con Exito");
				 response.put("status", HttpStatus.CREATED);
				 response.put("code", HttpStatus.CREATED.value());
				 log.info("entregable create fin");
				 return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
			}else {
				response.put("entregable", null );
				 response.put("mensaje", "Error crear Entregable");
				 response.put("status", HttpStatus.INTERNAL_SERVER_ERROR);
				 response.put("code", HttpStatus.INTERNAL_SERVER_ERROR.value());
				 log.info("entregable error create fin");
				 return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
//			 
		} catch(DataAccessException e) {
			response.put("mensaje", e.getMessage().concat(": ").concat(((NestedRuntimeException) e).getMostSpecificCause().getMessage()));
			response.put("status", HttpStatus.INTERNAL_SERVER_ERROR);
			response.put("code", HttpStatus.INTERNAL_SERVER_ERROR.value());
			log.info("entregable catch create fin");
			e.printStackTrace();
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	

	/*
	 * privates
	 */

	private List<ParticipanteEntity> getParticipantes(List<ParticipantesModelo> formBParticipantes, Long IdEntregable) {
		List<ParticipanteEntity> participantesEntity = new ArrayList<ParticipanteEntity>();
		
		for(ParticipantesModelo pm : formBParticipantes) {
			ParticipanteEntity pe = new ParticipanteEntity();
			pe.setIdParticipante(pm.getIdParticipante());
			pe.setIdEntregable(IdEntregable);
			pe.setParticipanteNombre(pm.getParticipanteNombre());
			pe.setParticipantePuesto(pm.getParticipantePuesto());
			pe.setParticipanteCURP(pm.getParticipanteCURP());
			pe.setParticipanteFoto(pm.getParticipanteFoto());
			pe.setParticipanteExamenTeoricoInicial(pm.getParticipanteExamenTeoricoInicial());
			pe.setParticipanteExamenTeoricoFinal(pm.getParticipanteExamenTeoricoInicial());
			pe.setParticipanteExamenPractico(pm.getParticipanteExamenPractico());
			pe.setParticipantePromedio(pm.getParticipantePromedio());
			pe.setParticipanteObservaciones(pm.getParticipanteObservaciones());
			pe.setStatusParticipante(pm.getStatus());
			pe.setUserCreateParticipante(pm.getUserCreate());
			pe.setCreateAtParticipante(pm.getCreateAt());
			
			participantesEntity.add(pe);
			
		}
		return participantesEntity;
	}
	
	private String getEvidenciasFoto(List<String> formCEvidenciasFotograficas) {
		String str ="";
		for(String a : formCEvidenciasFotograficas) {
			str = str.concat(a).concat(",");
		}
		return str;
	}


//	 /*
//	  * 
//	  */
//	@GetMapping("/asignacion/{id}")
//	public ResponseEntity<?> show(@PathVariable Long id) {
//		log.info("asignacion:"+id);
//		HttpStatus status ;
//		Asignacion asignacion = null;
//		Map<String, Object> response = new HashMap<>();
//		
//		try {
//			asignacion = asignacionService.findById(id);
//			if(asignacion == null || asignacion.getStatusAsignacion().equals("Evento Cancelado")) {
//				response.put("mensaje", "Error: no se pudo encontrar, asignacion ID: "
//						.concat(id.toString().concat(" no existe en la base de datos!")));
//				 response.put("status", HttpStatus.NOT_FOUND);
//				 response.put("code", HttpStatus.NOT_FOUND.value());
//				 log.info("asignacion fin :"+id);
//				 return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
//			}
//			
//			 response.put("asignacion", asignacion );
//			 response.put("mensaje", "Exito en la busqueda de asignacion ");
//			 response.put("status", HttpStatus.ACCEPTED);
//			 response.put("code", HttpStatus.ACCEPTED.value());
//			 log.info("asignacion fin :"+id);
//			 return new ResponseEntity<Map<String, Object>>(response, HttpStatus.ACCEPTED);
//		} catch(DataAccessException e) {
//			response.put("mensaje", e.getMessage().concat(": ").concat(((NestedRuntimeException) e).getMostSpecificCause().getMessage()));
//			response.put("status", HttpStatus.INTERNAL_SERVER_ERROR);
//			response.put("code", HttpStatus.INTERNAL_SERVER_ERROR.value());
//			log.info("asignacion fin :"+id);
//			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//		
//	}
//	
//	
//	
//	 /*
//	  * 
//	  */
//	@SuppressWarnings("null")
//	@PostMapping("/asignacion")
//	public ResponseEntity<?> create(@Valid @RequestBody AsignacionModelo asignacion, BindingResult result) {
//		log.info("asignacion create");
//		HttpStatus status ;
//		Asignacion asignacionNew = new Asignacion();
//		Map<String, Object> response = new HashMap<>();
//		
//		if(result.hasErrors()) {
//
//			List<String> errors = result.getFieldErrors()
//					.stream()
//					.map(err -> "El campo '" + err.getField() +"' "+ err.getDefaultMessage())
//					.collect(Collectors.toList());
//			
//			response.put("errors", errors);
//			status = HttpStatus.BAD_REQUEST;
////			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
//		}
//		
//		try {
//			
//			asignacionNew.setIdAsignacionLogica(asignacion.getIdAsignacionLogica());
//			asignacionNew.setFechaAsignacion(asignacion.getFechaAsignacion());
//			asignacionNew.setIdClienteAsignacion(asignacion.getIdClienteAsignacion());
//			asignacionNew.setClienteAsignacion(asignacion.getClienteAsignacion());
//			asignacionNew.setIdCursoAsignacion(asignacion.getIdCursoAsignacion());
//			asignacionNew.setCursoAsignacion(asignacion.getCursoAsignacion());
//			asignacionNew.setIdInstructorAsignacion(asignacion.getIdInstructorAsignacion());
//			asignacionNew.setInstructorAsignacion(asignacion.getInstructorAsignacion());
//			asignacionNew.setHorarioAsignacion(asignacion.getHorarioAsignacion());
//			asignacionNew.setParticipantesAsignacion(asignacion.getParticipantesAsignacion());
//			asignacionNew.setNivelAsignacion(asignacion.getNivelAsignacion());
//			asignacionNew.setObservacionesAsignacion(asignacion.getObservacionesAsignacion());
//			asignacionNew.setArchivosAsignacion(asignacion.getArchivosAsignacionTexto());
//			asignacionNew.setIdRegionAsignacion(asignacion.getIdRegionAsignacion());
//			asignacionNew.setNombreRegionAsignacion(asignacion.getNombreRegionAsignacion());
//			asignacionNew.setTipoCursoAsignacion(asignacion.getTipoCursoAsignacion());
//			asignacionNew.setCreateAtAsignacion(asignacion.getCreateAtAsignacion());
//			asignacionNew.setUserCreateAsignacion(asignacion.getUserCreateAsignacion());
//			asignacionNew.setUserCreateAsignacionTexto(asignacion.getUserCreateAsignacionTexto());
//			asignacionNew.setStatusAsignacion(asignacion.getStatusAsignacion());
//			
//			asignacionNew.setFechaPago("");
//			asignacionNew.setGuiaEntregable("");
////			asignacionNew.setVerificarEntregable("");
//			asignacionNew.setNumeroFactura("");
//			asignacionNew.setArchivoParticipantes("");
//			asignacionNew.setCostoHotel("");
//			asignacionNew.setErrorProceso("");
//			
//			asignacionNew = asignacionService.save(asignacionNew);
//			 response.put("asignacion", asignacionNew );
//			 response.put("mensaje", "Asignacion creada con Exito");
//			 response.put("status", HttpStatus.CREATED);
//			 response.put("code", HttpStatus.CREATED.value());
//			 log.info("asignacion create fin");
//			 return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
//		} catch(DataAccessException e) {
//			response.put("mensaje", e.getMessage().concat(": ").concat(((NestedRuntimeException) e).getMostSpecificCause().getMessage()));
//			response.put("status", HttpStatus.INTERNAL_SERVER_ERROR);
//			response.put("code", HttpStatus.INTERNAL_SERVER_ERROR.value());
//			log.info("asignacion create fin");
//			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//	}
//	
//	
//	/*
//	 * 
//	 */
//	@PutMapping("/asignacion/{id}")
//	public ResponseEntity<?> update(@Valid @RequestBody AsignacionModelo asignacion, BindingResult result, @PathVariable Long id) {
//		log.info("asignacion update:"+ id);
//		
//		Asignacion asignacionActual = asignacionService.findById(id);
//		
//		Asignacion asignacionUpdated = null;
//
//		Map<String, Object> response = new HashMap<>();
//
//		if(result.hasErrors()) {
//			log.info("result.hasErrors");
//			List<String> errors = result.getFieldErrors()
//					.stream()
//					.map(err -> "El campo '" + err.getField() +"' "+ err.getDefaultMessage())
//					.collect(Collectors.toList());
//			
//			 response.put("errors", errors);
//			 response.put("mensaje", errors);
//			 response.put("status", HttpStatus.BAD_REQUEST);
//			 response.put("code", HttpStatus.BAD_REQUEST.value());
//			 log.info("asignacion update fin:"+ id);
//			 return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
//		}
//		
//		if (asignacionActual == null) {
//			response.put("mensaje", "Error: no se pudo editar, asignacion ID: "
//					.concat(id.toString().concat(" no existe en la base de datos!")));
//			 response.put("status", HttpStatus.NOT_FOUND);
//			 response.put("code", HttpStatus.NOT_FOUND.value());
//			 log.info("asignacion update fin:"+ id);
//			 return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
//		}
//		log.info("update Asignacion:"+asignacion.toString());
//		if(asignacion.getStatusAsignacion().equals("Evento Cancelado")) {
//			try {
//				List<Notificacion> notificacionUpdate = notificacionDao.findByIdAsignacionNotificacion(id);
//				for(Notificacion notificacion : notificacionUpdate) {
//					notificacion.setStatusNotificacion("cancelada");
//					notificacionDao.save(notificacion);
//				}
//			} catch (Exception e) {
//				response.put("mensaje", e.getMessage().concat(": ").concat(((NestedRuntimeException) e).getMostSpecificCause().getMessage()));
//				response.put("status", HttpStatus.INTERNAL_SERVER_ERROR);
//				response.put("code", HttpStatus.INTERNAL_SERVER_ERROR.value());
//				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
//			}			
//		}
//		try {				
//				asignacionActual.setIdAsignacionLogica(asignacion.getIdAsignacionLogica());
//				asignacionActual.setFechaAsignacion(asignacion.getFechaAsignacion());
//				asignacionActual.setIdClienteAsignacion(asignacion.getIdClienteAsignacion());
//				asignacionActual.setClienteAsignacion(asignacion.getClienteAsignacion());
//				asignacionActual.setIdCursoAsignacion(asignacion.getIdCursoAsignacion());
//				asignacionActual.setCursoAsignacion(asignacion.getCursoAsignacion());
//				asignacionActual.setTipoCursoAsignacion(asignacion.getTipoCursoAsignacion());
//				asignacionActual.setIdInstructorAsignacion(asignacion.getIdInstructorAsignacion());
//				asignacionActual.setInstructorAsignacion(asignacion.getInstructorAsignacion());
//				asignacionActual.setHorarioAsignacion(asignacion.getHorarioAsignacion());
//				asignacionActual.setParticipantesAsignacion(asignacion.getParticipantesAsignacion());
//				asignacionActual.setNivelAsignacion(asignacion.getNivelAsignacion());
//				asignacionActual.setObservacionesAsignacion(asignacion.getObservacionesAsignacion());
//				asignacionActual.setArchivosAsignacion(asignacion.getArchivosAsignacionTexto());
//				asignacionActual.setIdRegionAsignacion(asignacion.getIdRegionAsignacion());
//				asignacionActual.setNombreRegionAsignacion(asignacion.getNombreRegionAsignacion());
//				asignacionActual.setCreateAtAsignacion(asignacion.getCreateAtAsignacion());
//				asignacionActual.setUserCreateAsignacion(asignacion.getUserCreateAsignacion());
//				asignacionActual.setUserCreateAsignacionTexto(asignacion.getUserCreateAsignacionTexto());
//				asignacionActual.setStatusAsignacion(asignacion.getStatusAsignacion());
//				asignacionActual.setFechaPago(asignacion.getFechaPago());
//				asignacionActual.setGuiaEntregable(asignacion.getGuiaEntregable());
//				asignacionActual.setVerificarEntregable(asignacion.getVerificarEntregable());
//				asignacionActual.setNumeroFactura(asignacion.getNumeroFactura());
//				asignacionActual.setArchivoParticipantes(asignacion.getArchivoParticipantesTexto());
//				asignacionActual.setCostoHotel(asignacion.getCostoHotel());
//				if(asignacion.getErrorProceso() == null || asignacion.getErrorProceso().length() == 0) {
//					asignacionActual.setErrorProceso("");
//				}else {
//					asignacionActual.setErrorProceso(asignacion.getErrorProceso());
//				}
//				
//				
//				asignacionUpdated = asignacionService.save(asignacionActual);
//				response.put("asignacion", asignacionUpdated  );
//				 response.put("mensaje", "Asignacion actualizada con Exito");
//				 response.put("status", HttpStatus.CREATED);
//				 response.put("code", HttpStatus.CREATED.value());
//				 log.info("asignacion update fin:"+ id);
//				 return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
//
//			} catch (DataAccessException e) {
//				response.put("mensaje", e.getMessage().concat(": ").concat(((NestedRuntimeException) e).getMostSpecificCause().getMessage()));
//				response.put("status", HttpStatus.INTERNAL_SERVER_ERROR);
//				response.put("code", HttpStatus.INTERNAL_SERVER_ERROR.value());
//				log.info("asignacion update fin:"+ id);
//				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
//			}
////		}
//
//	}
//	
//	
//	@GetMapping("/asignacionesHistorico")
//	public ResponseEntity<?> asignacionesHistorico() {
//		log.info("asignacion historico");
//		List<AsignacionHistorico> asignaciones = null;
//		Map<String, Object> response = new HashMap<>();
//		try {
//			asignaciones  = asignacionService.findAllHistorico();
//			
//			 response.put("asignacionesHistorico", asignaciones );
//			 response.put("mensaje", "Exito en la busqueda de asignaciones Historico");
//			 response.put("status", HttpStatus.ACCEPTED);
//			 response.put("code", HttpStatus.ACCEPTED.value());
//			 log.info("asignacionesHistorico:"+asignaciones.size());
//			 log.info("asignacion historico fin");
//			 return new ResponseEntity<Map<String, Object>>(response, HttpStatus.ACCEPTED);
//		} catch (Exception e) {
//			response.put("mensaje", e.getMessage().concat(": ").concat(((NestedRuntimeException) e).getMostSpecificCause().getMessage()));
//			response.put("status", HttpStatus.INTERNAL_SERVER_ERROR);
//			response.put("code", HttpStatus.INTERNAL_SERVER_ERROR.value());
//			log.info("asignacion historico fin");
//			e.printStackTrace();
//			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//		
//	}	
//	
	
	
}
