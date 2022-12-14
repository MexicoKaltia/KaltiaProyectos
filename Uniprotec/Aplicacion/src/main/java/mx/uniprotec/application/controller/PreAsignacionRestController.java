package mx.uniprotec.application.controller;

import java.util.Calendar;
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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mx.uniprotec.application.dao.INotificacionDao;
import mx.uniprotec.application.dao.IPreAsignacionAEDao;
import mx.uniprotec.application.dao.IPreAsignacionDao;
import mx.uniprotec.application.entity.ClienteProspectoEntity;
import mx.uniprotec.application.entity.PreAsignacion;
import mx.uniprotec.application.entity.PreAsignacionAEEntity;
import mx.uniprotec.application.service.IClienteProspecto;
import mx.uniprotec.application.service.IPreAsignacionAEService;
import mx.uniprotec.application.service.IPreAsignacionService;
import mx.uniprotec.entidad.modelo.AsignacionModelo;
import mx.uniprotec.entidad.modelo.DatosEconomicosModelo;
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
	private IPreAsignacionDao preAsignacionDao;
	@Autowired
	private IPreAsignacionAEDao preAsignacionAEDao;
	@Autowired
	private IClienteProspecto clienteProspectoService;
	
	
	
	
	 private final Logger log = LoggerFactory.getLogger(PreAsignacionRestController.class);
	public PreAsignacionRestController() {
		// TODO Auto-generated constructor stub
	}
	

	@PostMapping("/datosEconomicos")
	public ResponseEntity<?> createDatosEconomicos(@Valid @RequestBody DatosEconomicosModelo datosEconomicos, BindingResult result) {
		log.info("datosEconomicos create");
		
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
			
			preAsignacionAENew.setFormAEidPreAsignacion(datosEconomicos.getIdAsignacion());
			preAsignacionAENew.setFormAERegla3PorcentajeNuevaComisionReal(datosEconomicos.getPorcentajeVenta());
			preAsignacionAENew.setFormAEPrecioVentaReal(datosEconomicos.getVentaReal());
			
			preAsignacionAENew.setFormAEComisionVendedor(datosEconomicos.getComisionReal());
			preAsignacionAENew.setFormAEComisionVendedorPorcentaje(datosEconomicos.getPorcentajeComision());
			preAsignacionAENew.setFormAEViaticosTotal(datosEconomicos.getViaticosTotales());
			preAsignacionAENew.setFormAEFechaPromesaPago(datosEconomicos.getFechaPromesaPago());
			preAsignacionAENew.setFormAEFechaPromesaPagoFormat(datosEconomicos.getFechaPromesaPagoFormat());
			preAsignacionAENew.setFormAEFechaConfirmacion(datosEconomicos.getFechaConfirmacion());
			preAsignacionAENew.setFormAEFechaConfirmacionFormat(datosEconomicos.getFechaConfirmacionFormat());
			preAsignacionAENew.setFormAEObservaciones(datosEconomicos.getObservacion());
			
			preAsignacionAENew.setStatus(datosEconomicos.getStatus());
			preAsignacionAENew.setUserCreate(datosEconomicos.getUserCreateAsignacion());
			preAsignacionAENew.setCreateAt(datosEconomicos.getCreateAtAsignacion());
			
			
			preAsignacionAENew = preAsignacionAEService.savePreAsignacionAE(preAsignacionAENew);
			
			
			 response.put("datosEconomicos", preAsignacionAENew );
			 response.put("mensaje", "datosEconomicos creada con Exito");
			 response.put("status", HttpStatus.CREATED);
			 response.put("code", HttpStatus.CREATED.value());
			 log.info("datosEconomicos  create fin");
			 return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
		} catch(DataAccessException e) {
			response.put("mensaje", e.getMessage().concat(": ").concat(((NestedRuntimeException) e).getMostSpecificCause().getMessage()));
			response.put("status", HttpStatus.INTERNAL_SERVER_ERROR);
			response.put("code", HttpStatus.INTERNAL_SERVER_ERROR.value());
			log.info("catch datosEconomicos   create fin");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
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
			pre_asignacionNew.setIdStatusAsignacion(1);
			pre_asignacionNew.setClienteStatus(asignacion.getClienteStatus());
			
			pre_asignacionNew.setSeguimiento(seguimientoUpdate("",asignacion.getNombreUsuarioSeguimiento(),asignacion.getPerfilUsuarioSeguimiento(),asignacion.getMensajeSeguimiento()));
			
			pre_asignacionNew.setFechaPago("");
			pre_asignacionNew.setGuiaEntregable("");
			pre_asignacionNew.setNumeroFactura("");
			pre_asignacionNew.setArchivoParticipantes("");
			pre_asignacionNew.setCostoHotel("");
			pre_asignacionNew.setErrorProceso("");
			
			pre_asignacionNew = preAsignacionService.savePreAsignacion(pre_asignacionNew);
			
			try {
				if(asignacion.getIdClienteAsignacion() == 9999) {
					
					ClienteProspectoEntity clienteProspecto = new ClienteProspectoEntity();
					clienteProspecto.setNombreCortoClienteProspecto(asignacion.getNombreCortoClienteProspecto());
					clienteProspecto.setNombreCompletoClienteProspecto(asignacion.getNombreCompletoClienteProspecto());
					clienteProspecto.setIdRegionClienteProspecto(asignacion.getIdRegionClienteProspecto());
					clienteProspecto.setNombreRegionClienteProspecto(asignacion.getNombreRegionClienteProspecto());
					clienteProspecto.setRfcClienteProspecto(asignacion.getRfcClienteProspecto());
					clienteProspecto.setIdPreAsignacion(pre_asignacionNew.getIdPreAsignacion());
					clienteProspecto.setUserCreate(asignacion.getUserCreateAsignacion());
					clienteProspecto.setStatus(asignacion.getStatusAsignacion());
					clienteProspecto.setCreateAt(asignacion.getCreateAtAsignacion());
					clienteProspecto.setDireccionClienteProspecto(asignacion.getDireccionClienteProspecto());
					
					clienteProspectoService.saveClienteProspecto(clienteProspecto);
				}
			} catch (Exception e) {
					response.put("mensaje", e.getMessage().concat(": ").concat(((NestedRuntimeException) e).getMostSpecificCause().getMessage()));
					response.put("status", HttpStatus.INTERNAL_SERVER_ERROR);
					response.put("code", HttpStatus.INTERNAL_SERVER_ERROR.value());
					log.info("catch cliente Prosptecto create fin");
					return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
				}
			
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
	

	@PostMapping("/preAsignacionAE")
	public ResponseEntity<?> createPreAsignacionAE(@Valid @RequestBody PreAsignacionAE preAsignacionAE, BindingResult result) {
		log.info("preasignacion create");
		
		PreAsignacionAEEntity preAsignacionAENew = new PreAsignacionAEEntity();
		if((Long.valueOf(preAsignacionAE.getIdPreAsignacionAE()) != 0)) {
			preAsignacionAENew = preAsignacionAEDao.findById(preAsignacionAE.getIdPreAsignacionAE()).orElse(null);
		}
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
//			preAsignacionAENew.setFormAEidPreAsignacion(preAsignacionAE.getFormAEidPreAsignacion());

			preAsignacionAENew.setCreateAt(preAsignacionAE.getCreateAt());
			preAsignacionAENew.setUserCreate(preAsignacionAE.getUserCreate());
			preAsignacionAENew.setUserCreateTexto(preAsignacionAE.getUserCreateTexto());
			preAsignacionAENew.setStatus(preAsignacionAE.getStatus());
			
			preAsignacionAENew = preAsignacionAEService.savePreAsignacionAE(preAsignacionAENew);
			
			PreAsignacion preAsignacion = preAsignacionService.findId(Long.valueOf(preAsignacionAE.getFormAEidPreAsignacion()));
			preAsignacion.setIdPreAsignacionAE(preAsignacionAENew.getIdPreAsignacionAE());
			preAsignacion.setPreAsignacionAEStatus(preAsignacionAENew.getStatus());
			preAsignacion.setStatusAsignacion(preAsignacionAENew.getStatus());
			preAsignacion.setIdStatusAsignacion(2);
//			preAsignacion.setSeguimiento(seguimientoUpdate(preAsignacion.getSeguimiento(),"nombreUsuario","Vendedor","ALTA ANÁLISIS ECONÓMICO"));
			preAsignacion.setSeguimiento(seguimientoUpdate(preAsignacion.getSeguimiento(),preAsignacionAE.getNombreUsuarioSeguimiento(),preAsignacionAE.getPerfilUsuarioSeguimiento(),preAsignacionAE.getMensajeSeguimiento()));
			
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
	
	@DeleteMapping("/preAsignacion/{idPreAsignacion}")
	public ResponseEntity<?> deletePreAsignaciones(@PathVariable Long idPreAsignacion) {
		log.info("Delete preAsignaciones");
		
		Map<String, Object> response = new HashMap<>();
		try {
			int code  = preAsignacionAEService.deleteIdpreAsignacion(idPreAsignacion);
			if(code == 0){
				code  = preAsignacionService.deleteId(idPreAsignacion);
			}
			 response.put("code", code );
			 response.put("mensaje", "Registro eliminado correctamente");
			 response.put("status", HttpStatus.ACCEPTED);
			 response.put("code", HttpStatus.ACCEPTED.value());
			 log.info("Delete preAsignaciones  fin");
			 return new ResponseEntity<Map<String, Object>>(response, HttpStatus.ACCEPTED);
		} catch (Exception e) {
			response.put("mensaje", e.getMessage().concat(": ").concat(((NestedRuntimeException) e).getMostSpecificCause().getMessage()));
			response.put("status", HttpStatus.INTERNAL_SERVER_ERROR);
			response.put("code", HttpStatus.INTERNAL_SERVER_ERROR.value());
			e.printStackTrace();
			log.info("catch Delete preAsignaciones fin");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@PutMapping("/preAsignacion/{id}")
	public ResponseEntity<?> updatePreAsignacion(@Valid @RequestBody AsignacionModelo asignacion, BindingResult result, @PathVariable Long id) {
		
		log.info("asignacion update:"+ id);
		PreAsignacion preAsignacionActual = preAsignacionDao.findById(id).orElse(null);
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
		
		if (preAsignacionActual == null) {
			response.put("mensaje", "Error: no se pudo editar, asignacion ID: "
					.concat(id.toString().concat(" no existe en la base de datos!")));
			 response.put("status", HttpStatus.NOT_FOUND);
			 response.put("code", HttpStatus.NOT_FOUND.value());
			 log.info("asignacion update fin:"+ id);
			 return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		log.info("update Asignacion:"+asignacion.toString());

		if(asignacion.getIdPreAsignacionAE() == null || asignacion.getIdStatusAsignacion() > 4 ) {
			try {
				String mensaje = asignacion.getMensajeSeguimiento();
//				preAsignacionActual.setSeguimiento(seguimientoUpdate(preAsignacionActual.getSeguimiento(), asignacion.getNombreUsuarioSeguimiento(), asignacion.getPerfilUsuarioSeguimiento(), asignacion.getMensajeSeguimiento()));
				if(asignacion.getIdStatusAsignacion() == 4) {
					preAsignacionActual.setIdStatusAsignacion(4);
					preAsignacionActual.setStatusAsignacion("APROBACION PREASIGNACION");
				}else if(asignacion.getIdStatusAsignacion() == 3){
					preAsignacionActual.setIdStatusAsignacion(3);
					preAsignacionActual.setStatusAsignacion("REVISION");
				}else if(asignacion.getIdStatusAsignacion() == 5){
					preAsignacionActual.setIdStatusAsignacion(5);
					preAsignacionActual.setStatusAsignacion("ASIGNACION");
				}else if(asignacion.getIdStatusAsignacion() == 6){
					preAsignacionActual.setIdStatusAsignacion(6);
					preAsignacionActual.setStatusAsignacion("FACTURA INTEGRADA");
					if(asignacion.getAgregarFactura() != null) {
						mensaje = asignacion.getMensajeSeguimiento() + " " + "<a href='/uploads/fileAsignacionFactura/"+preAsignacionActual.getIdAsignacionLogica()+"/"+asignacion.getAgregarFactura()+"' id='linkFile'>"+asignacion.getAgregarFactura()+"</a>";
						preAsignacionActual.setNombreFactura(asignacion.getAgregarFactura());
					}
				}
				if(asignacion.getFechaInicioFactura() != null ) {
					preAsignacionActual.setFechaInicioFactura(asignacion.getFechaInicioFactura());
				}
				 if(asignacion.getFechaFinFactura() != null) {
					 preAsignacionActual.setFechaFinFactura(asignacion.getFechaFinFactura());
				 }
				 if(asignacion.getFechaHoy() != null) {
					 preAsignacionActual.setFechaHoy(asignacion.getFechaHoy());
				 }
				
				preAsignacionActual.setSeguimiento(seguimientoUpdate(preAsignacionActual.getSeguimiento(), asignacion.getNombreUsuarioSeguimiento(), asignacion.getPerfilUsuarioSeguimiento(), mensaje));			
				preAsignacionActual= preAsignacionService.savePreAsignacion(preAsignacionActual);
				
    			 response.put("asignacion", preAsignacionActual  );
				 response.put("mensaje", "PRE Asignacion actualizada con Exito");
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
		}else {
			response.put("mensaje", "PRE Asignacion NO actualizada con Exito es otro tipo de actualizacion.");
			response.put("status", HttpStatus.INTERNAL_SERVER_ERROR);
			response.put("code", HttpStatus.INTERNAL_SERVER_ERROR.value());
			log.info("asignacion update fin:"+ id);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
	}

	
	
	/*
	 * PRIVATES
	 */	
	public String seguimientoUpdate(String seguimiento, String nombreUsuario, String perfilUsuario, String mensaje) {
		
		String nuevoSeguimiento1 = "<div class='alert alert-ligth alert-dismissible fade show' role='alert'><ul id='listPrimerNivel11'><li style='list-style-type: disc;'><ul id='itemPrimerNivel11'><b>";
		String nuevoSeguimiento2 = "</b><li style='list-style-type: circle;'><ul id='itemSegundoNivel11'>"; 
		String nuevoSeguimiento3 = "</ul></li></ul></li></ul></div>";
		Calendar c1 = Calendar.getInstance();
		String dia = Integer.toString(c1.get(Calendar.DATE));
		String mes = Integer.toString(c1.get(Calendar.MONTH)+1);
		String anio = Integer.toString(c1.get(Calendar.YEAR));
		String hora = Integer.toString(c1.get(Calendar.HOUR_OF_DAY)-7);
		String min = Integer.toString(c1.get(Calendar.MINUTE));
		String seg = Integer.toString(c1.get(Calendar.SECOND));
		String diaSemana = diaSemana(c1.get(Calendar.DAY_OF_WEEK));
		String fechaHoy = diaSemana+" "+dia + "/" + mes + "/" + anio +"  " +hora+":"+min+":"+seg;
		
		return seguimiento+nuevoSeguimiento1+fechaHoy+"  "+perfilUsuario+" "+nombreUsuario+nuevoSeguimiento2+mensaje+nuevoSeguimiento3;
	}

	private String diaSemana(int i) {
		
		String diaSemana = null;
		switch (i) {
		case 1:
			diaSemana = "DOMINGO";
			break;
		case 2:
			diaSemana = "LUNES";
			break;
		case 3:
			diaSemana = "MARTES";
			break;
		case 4:
			diaSemana = "MIERCOLES";
			break;
		case 5:
			diaSemana = "JUEVES";
			break;
		case 6:
			diaSemana = "VIERNES";
			break;
		case 7:
			diaSemana = "SABADO";
			break;
		}
		
		
		return diaSemana;
	}
	
	
	
}
