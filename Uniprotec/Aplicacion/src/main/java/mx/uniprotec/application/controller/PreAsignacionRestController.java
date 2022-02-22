package mx.uniprotec.application.controller;

import java.util.Calendar;
import java.util.Date;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mx.uniprotec.application.dao.INotificacionDao;
import mx.uniprotec.application.entity.Asignacion;
import mx.uniprotec.application.entity.PreAsignacion;
import mx.uniprotec.application.entity.PreAsignacionAEEntity;
import mx.uniprotec.application.service.IEntregableService;
import mx.uniprotec.application.service.IPreAsignacionAEService;
import mx.uniprotec.application.service.IPreAsignacionService;
import mx.uniprotec.entidad.modelo.AsignacionModelo;
import mx.uniprotec.entidad.modelo.PreAsignacionAE;

@CrossOrigin(origins = { "*" })
@RestController
@RequestMapping("/crud")
public class PreAsignacionRestController {
	
	@Autowired
	private IPreAsignacionService preAsignacionService;
	@Autowired
	private IPreAsignacionAEService preAsignacionAEService;
	@Autowired
	private INotificacionDao notificacionDao;
	@Autowired
	private IEntregableService entregableService;

	
	
	
	 private final Logger log = LoggerFactory.getLogger(PreAsignacionRestController.class);
	public PreAsignacionRestController() {
		// TODO Auto-generated constructor stub
	}
	
	@PostMapping("/preAsignacion")
	public ResponseEntity<?> createPreAsignacion(@Valid @RequestBody AsignacionModelo asignacion, BindingResult result) {
		log.info("preasignacion create");
		
		PreAsignacion pre_asignacionNew = new PreAsignacion();
		Map<String, Object> response = new HashMap<>();
		
		if(result.hasErrors()) {

			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() +"' "+ err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		try {
			
			pre_asignacionNew.setIdAsignacionLogica(asignacion.getIdAsignacionLogica());
			pre_asignacionNew.setFechaAsignacion(asignacion.getFechaAsignacion());
			pre_asignacionNew.setIdClienteAsignacion(asignacion.getIdClienteAsignacion());
			pre_asignacionNew.setClienteAsignacion(asignacion.getClienteAsignacion());
			pre_asignacionNew.setIdCursoAsignacion(asignacion.getIdCursoAsignacion());
			pre_asignacionNew.setCursoAsignacion(asignacion.getCursoAsignacion());
			pre_asignacionNew.setIdInstructorAsignacion(asignacion.getIdInstructorAsignacion());
			pre_asignacionNew.setInstructorAsignacion(asignacion.getInstructorAsignacion());
			pre_asignacionNew.setHorarioAsignacion(asignacion.getHorarioAsignacion());
			pre_asignacionNew.setParticipantesAsignacion(asignacion.getParticipantesAsignacion());
			pre_asignacionNew.setNivelAsignacion(asignacion.getNivelAsignacion());
			pre_asignacionNew.setObservacionesAsignacion(asignacion.getObservacionesAsignacion());
			pre_asignacionNew.setArchivosAsignacion(asignacion.getArchivosAsignacionTexto());
			pre_asignacionNew.setIdRegionAsignacion(asignacion.getIdRegionAsignacion());
			pre_asignacionNew.setNombreRegionAsignacion(asignacion.getNombreRegionAsignacion());
			if(asignacion.getTipoCursoAsignacion().equals("")) {
				pre_asignacionNew.setTipoCursoAsignacion("PRESENCIAL");
			}else {
				pre_asignacionNew.setTipoCursoAsignacion(asignacion.getTipoCursoAsignacion());
			}
			
			pre_asignacionNew.setCreateAtAsignacion(asignacion.getCreateAtAsignacion());
			pre_asignacionNew.setUserCreateAsignacion(asignacion.getUserCreateAsignacion());
			pre_asignacionNew.setUserCreateAsignacionTexto(asignacion.getUserCreateAsignacionTexto());
			pre_asignacionNew.setStatusAsignacion(asignacion.getStatusAsignacion());
			pre_asignacionNew.setClienteStatus(asignacion.getClienteStatus());
			pre_asignacionNew.setSeguimiento(seguimientoUpdate("","nombreUsuario","Vendedor","ALTA PREASIGNACION"));
			
			
			pre_asignacionNew.setFechaPago("");
			pre_asignacionNew.setGuiaEntregable("");
			pre_asignacionNew.setNumeroFactura("");
			pre_asignacionNew.setArchivoParticipantes("");
			pre_asignacionNew.setCostoHotel("");
			pre_asignacionNew.setErrorProceso("");
			
			pre_asignacionNew = preAsignacionService.savePreAsignacion(pre_asignacionNew);
			 response.put("preAsignacion", pre_asignacionNew );
			 response.put("mensaje", "Asignacion creada con Exito");
			 response.put("status", HttpStatus.CREATED);
			 response.put("code", HttpStatus.CREATED.value());
			 log.info("preAsignacion create fin");
			 return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
		} catch(DataAccessException e) {
			response.put("mensaje", e.getMessage().concat(": ").concat(((NestedRuntimeException) e).getMostSpecificCause().getMessage()));
			response.put("status", HttpStatus.INTERNAL_SERVER_ERROR);
			response.put("code", HttpStatus.INTERNAL_SERVER_ERROR.value());
			log.info("catch preAsignacion  create fin");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	private String seguimientoUpdate(String seguimiento, String nombreUsuario, String perfilUsuario, String mensaje) {
		
		String nuevoSeguimiento1 = "<div class='alert alert-ligth alert-dismissible fade show' role='alert'><ul id='listPrimerNivel11'><li style='list-style-type: disc;'><ul id='itemPrimerNivel11'><b>";
		String nuevoSeguimiento2 = "</b><li style='list-style-type: circle;'><ul id='itemSegundoNivel11'>"; 
		String nuevoSeguimiento3 = "</ul></li></ul></li></ul></div>";
		Calendar c1 = Calendar.getInstance();
		String dia = Integer.toString(c1.get(Calendar.DATE));
		String mes = Integer.toString(c1.get(Calendar.MONTH));
		String anio = Integer.toString(c1.get(Calendar.YEAR));
		String fechaHoy = dia + "-" + mes + "-" + anio;
		Date fecha = new Date();
		
		return seguimiento+nuevoSeguimiento1+fecha+" "+perfilUsuario+" "+nombreUsuario+nuevoSeguimiento2+mensaje+nuevoSeguimiento3;
	}

	@PostMapping("/preAsignacionAE")
	public ResponseEntity<?> createPreAsignacionAE(@Valid @RequestBody PreAsignacionAE preAsignacionAE, BindingResult result) {
		log.info("preasignacion create");
		
		PreAsignacionAEEntity preAsignacionAENew = new PreAsignacionAEEntity();
		Map<String, Object> response = new HashMap<>();
		
		if(result.hasErrors()) {

			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() +"' "+ err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		try {
			
			preAsignacionAENew.setFormAECurso(preAsignacionAE.getFormAECurso());
			preAsignacionAENew.setFormAEEmpresa(preAsignacionAE.getFormAEEmpresa());
			preAsignacionAENew.setFormAEHorasEfectivas(preAsignacionAE.getFormAEHorasEfectivas());
			preAsignacionAENew.setFormAESesiones(preAsignacionAE.getFormAESesiones());
			preAsignacionAENew.setFormAEParticipantes(preAsignacionAE.getFormAEParticipantes());
			preAsignacionAENew.setFormAEFechaCotizacion(preAsignacionAE.getFormAEFechaCotizacion());
			preAsignacionAENew.setFormAESede(preAsignacionAE.getFormAESede());
			preAsignacionAENew.setFormAENivelCurso(preAsignacionAE.getFormAENivelCurso());

			preAsignacionAENew.setFormAENumInstructor(preAsignacionAE.getFormAENumInstructor());
			preAsignacionAENew.setFormAETotalHoras(preAsignacionAE.getFormAETotalHoras());
			preAsignacionAENew.setFormAECostoHoraInstructor(preAsignacionAE.getFormAECostoHoraInstructor());
			preAsignacionAENew.setFormAETotalImparticion(preAsignacionAE.getFormAETotalImparticion());
			preAsignacionAENew.setFormAEViaticosTotal(preAsignacionAE.getFormAEViaticosTotal());

			preAsignacionAENew.setFormAESumaImparticionViaticos(preAsignacionAE.getFormAESumaImparticionViaticos());
			preAsignacionAENew.setFormAECostoCursoRecomendado(preAsignacionAE.getFormAECostoCursoRecomendado());
			preAsignacionAENew.setFormAECostoHoraRecomendada(preAsignacionAE.getFormAECostoHoraRecomendada());

			preAsignacionAENew.setFormAEImparticion(preAsignacionAE.getFormAEImparticion());
			preAsignacionAENew.setFormAEImparticionPorcentaje(preAsignacionAE.getFormAEImparticionPorcentaje());
			preAsignacionAENew.setFormAEComisionVendedor(preAsignacionAE.getFormAEComisionVendedor());
			preAsignacionAENew.setFormAEComisionVendedorPorcentaje(preAsignacionAE.getFormAEComisionVendedorPorcentaje());
			preAsignacionAENew.setFormAEViaticos(preAsignacionAE.getFormAEViaticos());
			preAsignacionAENew.setFormAEViaticosPorcentaje(preAsignacionAE.getFormAEViaticosPorcentaje());
			preAsignacionAENew.setFormAEGastosFijos(preAsignacionAE.getFormAEGastosFijos());
			preAsignacionAENew.setFormAEGastosFijosPorcentaje(preAsignacionAE.getFormAEGastosFijosPorcentaje());
			preAsignacionAENew.setFormAEGananciaCurso(preAsignacionAE.getFormAEGananciaCurso());
			preAsignacionAENew.setFormAEGananciaCursoPorcentaje(preAsignacionAE.getFormAEGananciaCursoPorcentaje());
			preAsignacionAENew.setFormAETotales(preAsignacionAE.getFormAETotales());
			preAsignacionAENew.setFormAETotalesPorcentaje(preAsignacionAE.getFormAETotalesPorcentaje());
			preAsignacionAENew.setFormAEPrecioVentaReal(preAsignacionAE.getFormAEPrecioVentaReal());
			preAsignacionAENew.setFormAEComisionVendedorReal(preAsignacionAE.getFormAEComisionVendedorReal());
			preAsignacionAENew.setFormAEGastosFijosReal(preAsignacionAE.getFormAEGastosFijosReal());
			preAsignacionAENew.setFormAEUtilidadReal(preAsignacionAE.getFormAEUtilidadReal());
			preAsignacionAENew.setFormAENuevaComisionReal(preAsignacionAE.getFormAENuevaComisionReal());

			preAsignacionAENew.setFormAERegla3PorcentajeNuevaComisionReal(preAsignacionAE.getFormAERegla3PorcentajeNuevaComisionReal());
			preAsignacionAENew.setFormAERegla3PorcentajeNuevaComision(preAsignacionAE.getFormAERegla3PorcentajeNuevaComision());

			preAsignacionAENew.setFormAEObservaciones(preAsignacionAE.getFormAEObservaciones());

			preAsignacionAENew.setFormAEidPreAsignacionLogica(preAsignacionAE.getFormAEidPreAsignacionLogica());
			preAsignacionAENew.setFormAEidPreAsignacion(preAsignacionAE.getFormAEidPreAsignacion());

			preAsignacionAENew.setCreateAt(preAsignacionAE.getCreateAt());
			preAsignacionAENew.setUserCreate(preAsignacionAE.getUserCreate());
			preAsignacionAENew.setUserCreateTexto(preAsignacionAE.getUserCreateTexto());
			preAsignacionAENew.setStatus(preAsignacionAE.getStatus());
			
			preAsignacionAENew = preAsignacionAEService.savePreAsignacionAE(preAsignacionAENew);
			PreAsignacion preAsignacion = preAsignacionService.findId(Long.valueOf(preAsignacionAE.getFormAEidPreAsignacion()));
			preAsignacion.setIdPreAsignacionAE(preAsignacionAENew.getIdPreAsignacionAE());
			preAsignacion.setPreAsignacionAEStatus(preAsignacionAENew.getStatus());
			preAsignacion.setStatusAsignacion(preAsignacionAENew.getStatus());
			preAsignacion.setSeguimiento(seguimientoUpdate(preAsignacion.getSeguimiento(),"nombreUsuario","Vendedor","ALTA ANÁLISIS ECONÓMICO"));
			preAsignacionService.savePreAsignacion(preAsignacion);
			
			
			
			 response.put("preAsignacionAE", preAsignacionAENew );
			 response.put("mensaje", "AE creada con Exito");
			 response.put("status", HttpStatus.CREATED);
			 response.put("code", HttpStatus.CREATED.value());
			 log.info("preAsignacion create fin");
			 return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
		} catch(DataAccessException e) {
			response.put("mensaje", e.getMessage().concat(": ").concat(((NestedRuntimeException) e).getMostSpecificCause().getMessage()));
			response.put("status", HttpStatus.INTERNAL_SERVER_ERROR);
			response.put("code", HttpStatus.INTERNAL_SERVER_ERROR.value());
			log.info("catch preAsignacion  create fin");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	
	@GetMapping("/preAsignaciones")
	public ResponseEntity<?> PreAsignaciones() {
		log.info("preAsignaciones");
		List<PreAsignacion> preAsignaciones = null;
		List<PreAsignacionAEEntity> preAsignacionesAE = null;
		Map<String, Object> response = new HashMap<>();
		try {
			preAsignaciones  = preAsignacionService.findAll();
			preAsignacionesAE = preAsignacionAEService.findAll();
			 response.put("preAsignaciones", preAsignaciones );
			 response.put("preAsignacionesAE", preAsignacionesAE );
			 response.put("mensaje", "Exito en la busqueda de asignaciones ");
			 response.put("status", HttpStatus.ACCEPTED);
			 response.put("code", HttpStatus.ACCEPTED.value());
			 log.info("preAsignaciones fin");
			 return new ResponseEntity<Map<String, Object>>(response, HttpStatus.ACCEPTED);
		} catch (Exception e) {
			response.put("mensaje", e.getMessage().concat(": ").concat(((NestedRuntimeException) e).getMostSpecificCause().getMessage()));
			response.put("status", HttpStatus.INTERNAL_SERVER_ERROR);
			response.put("code", HttpStatus.INTERNAL_SERVER_ERROR.value());
			e.printStackTrace();
			log.info("catch preAsignaciones fin");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
//
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
//	}
//	
//	
//	
//	
//		
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
//				asignacionUpdated = asignacionService.save(asignacionActual);
////				EntregableEntity ee = (EntregableEntity) entregableService.consultaEntregable(asignacion.getIdAsignacion());
//				
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
	
	
	
}
