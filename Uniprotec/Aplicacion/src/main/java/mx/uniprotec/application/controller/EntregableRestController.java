package mx.uniprotec.application.controller;

import java.time.LocalDateTime;
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

import mx.uniprotec.application.entity.Asignacion;
import mx.uniprotec.application.entity.EntregableEntity;
import mx.uniprotec.application.entity.ParticipanteEntity;
import mx.uniprotec.application.service.IAsignacionService;
import mx.uniprotec.application.service.IEntregableService;
import mx.uniprotec.entidad.modelo.EntregableModelo;
import mx.uniprotec.entidad.modelo.ParticipantesModelo;

@CrossOrigin(origins = { "*" })
@RestController
@RequestMapping("/crud")
public class EntregableRestController {
	
	@Autowired
	private IEntregableService entregableService;
	@Autowired
	private IAsignacionService asignacionService;
	
	
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
			entregableEntity.setFormALogo(entregable.getFormALogoEmpresa());
			
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
			log.info("size:"+entregable.getFormCEvidenciasFotograficas().size());
			if(entregable.getFormCEvidenciasFotograficas().size() > 0) {
				entregableEntity.setFormCEvidenciasFotograficas(getEvidenciasFoto(entregable.getFormCEvidenciasFotograficas()));
			}		
			entregableEntity.setFormCRecomendaciones(entregable.getFormCRecomendaciones());
			entregableEntity.setFormCNivelCumplimiento(entregable.getFormCNivelCumplimiento());
			entregableEntity.setFormCContingencias(entregable.getFormCContingencias());
			entregableEntity.setFormCAvancesLogrados(entregable.getFormCAvancesLogrados());
			entregableEntity.setFormCObservaciones(entregable.getFormCObservaciones());
			if(entregable.getFormCEvidenciasFotograficas().size() > 0) {
				entregableEntity.setFormCEvidenciaDocto(getEvidenciasFoto(entregable.getFormCEvidenciaDocto()));
			}
			entregableEntity.setRFCOriginalAsignacion(entregable.getRfcOriginalAsignacion());
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
//				entregableEntity.setStatusEntregable("update");
				entregableNew = entregableService.createEntregable(entregableEntity);
				pe = entregableService.updateParticipantes(getParticipantes(entregable.getFormBParticipantes(), entregableNew.getIdEntregable()), entregableNew.getIdEntregable());
			}
			
			if(entregableNew != null ) {
				try {
					Asignacion asignacion = asignacionService.findById(entregable.getIdAsignacion());
					asignacion.setStatusAsignacion("Elaborar Entregable");
					if(entregable.getStatus().equals("Entregable Generado")) {
						asignacion.setStatusAsignacion("Entregable Generado");
					}
					
					asignacion.setCreateAtAsignacion(LocalDateTime.now()); 
					try {
						asignacionService.save(asignacion);
					} catch (Exception e) {
						response.put("mensaje", e.getMessage().concat(": ").concat(((NestedRuntimeException) e).getMostSpecificCause().getMessage()));
						response.put("status", HttpStatus.INTERNAL_SERVER_ERROR);
						response.put("code", HttpStatus.INTERNAL_SERVER_ERROR.value());
						log.info("entregable catch update status Asignacion fin");
						e.printStackTrace();
						return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
					}
				} catch (Exception e) {
					response.put("mensaje", e.getMessage().concat(": ").concat(((NestedRuntimeException) e).getMostSpecificCause().getMessage()));
					response.put("status", HttpStatus.INTERNAL_SERVER_ERROR);
					response.put("code", HttpStatus.INTERNAL_SERVER_ERROR.value());
					log.info("entregable catch consulta Asignacion fin");
					e.printStackTrace();
					return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
				}
			}
			
			
			if(entregableNew != null ) {
				 response.put("entregable", entregableEntity );
				 response.put("mensaje", "Entregable creado con Exito");
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
			pe.setParticipantePuesto(pm.getParticipantePuesto() != null ? pm.getParticipantePuesto() : " ");  
			pe.setParticipanteOcupacion(pm.getParticipanteOcupacion() != null ? pm.getParticipanteOcupacion() : " "); 
 			pe.setParticipanteCURP(pm.getParticipanteCURP() != null ? pm.getParticipanteCURP() : " ");
			pe.setParticipanteFoto(pm.getParticipanteFoto());
			pe.setParticipanteExamenTeoricoInicial(Double.valueOf(pm.getParticipanteExamenTeoricoInicial()));
			pe.setParticipanteExamenTeoricoFinal(Double.valueOf(pm.getParticipanteExamenTeoricoFinal()));
			pe.setParticipanteExamenPractico(Double.valueOf(pm.getParticipanteExamenPractico()));
			pe.setParticipantePromedio(Double.valueOf(pm.getParticipantePromedio()));
			pe.setParticipanteAprovechamiento(pm.getParticipanteAprovechamiento() != null ? pm.getParticipanteAprovechamiento() : " ");
			pe.setParticipanteAprobado(pm.isParticipanteAprobado());
			pe.setParticipanteObservaciones(pm.getParticipanteObservaciones() != null ? pm.getParticipanteObservaciones() : " ");
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
		str = str.substring(0,str.length()-1);
		return str;
	}

	
	
}
