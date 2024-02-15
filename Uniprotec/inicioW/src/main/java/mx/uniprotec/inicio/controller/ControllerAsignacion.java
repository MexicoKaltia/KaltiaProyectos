package mx.uniprotec.inicio.controller;


import java.util.Map;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import mx.uniprotec.entidad.modelo.AsignacionModelo;
import mx.uniprotec.entidad.modelo.DatosEconomicosModelo;
import mx.uniprotec.entidad.modelo.ResultVO;
import mx.uniprotec.entidad.modelo.UsuarioModelo;
import mx.uniprotec.inicio.service.IAplicacionService;
import mx.uniprotec.inicio.service.IAsignacionService;
import mx.uniprotec.inicio.service.IClienteService;
import mx.uniprotec.inicio.service.IDatosEconomicosService;
import mx.uniprotec.inicio.service.IEntregableService;
import mx.uniprotec.inicio.service.ILoginService;
import mx.uniprotec.inicio.service.IUsuarioService;

@CrossOrigin(origins = { "*" })
@Controller
@SessionAttributes ("model")
//@Scope("prototype")
public class ControllerAsignacion {

	private static Logger log = LoggerFactory.getLogger(ControllerAsignacion.class);
	
	@Autowired
	ILoginService loginService;
	@Autowired
	IAplicacionService aplicacionService;
	@Autowired
	IClienteService clienteService;
	@Autowired
	IAsignacionService asignacionService;
	@Autowired
	IUsuarioService usuarioService;
	@Autowired
	IEntregableService entregableService;
	@Autowired
	IDatosEconomicosService datosEconomicosService;
	
	
//	ResultVO resultVO = new ResultVO();
	UsuarioModelo usuario = new UsuarioModelo();
	
	public ControllerAsignacion() {
		// TODO Auto-generated constructor stub
	}
	
	/*
	 *   ASIGNACION
	 */

	
	
	@GetMapping("/AAsignacion")
	public ModelAndView asignacion(ModelMap model) {
		model.addAttribute("asignacionForm", new AsignacionModelo());
		model.addAttribute("asignaForm", new AsignacionModelo());

		if(model.equals(null)) {
			log.info("NULL");
			return new  ModelAndView("login");
		}else {
			log.info("Asignacion model Activo");
			ResultVO resultVO = (ResultVO)model.get("model");			
			ResultVO rs = aplicacionService.consultaData(resultVO);

			resultVO.setJsonResponseObject(rs.getJsonResponseObject());
			
			model.addAttribute("model", resultVO);
			ModelAndView mav = new  ModelAndView("Asignacion",  model);
			if(rs.getCodigo() != 500) {
				return mav;
			}else {
				mav.addObject("consulta", true);
				log.info("NOK Asignacion");
				return mav;	
			}
		}		
	}
	

	@PostMapping("/altaAsignacion")
	public ModelAndView altaAsignacion(@ModelAttribute("asignacionForm") AsignacionModelo asignacion, ModelMap model) {
		log.info("Metodo de alta Asignacion");
		log.info(asignacion.toString());
		
		ResultVO resultVO = (ResultVO)model.get("model");
//		log.info(resultVO.getJsonResponse().toJSONString());
		JSONObject jsonObject = (JSONObject) resultVO.getJsonResponse();
		JSONObject jsonUsuario = new JSONObject((Map) jsonObject.get("user"));
		Long idUsuario = Long.valueOf( jsonUsuario.get("id").toString());
		
		resultVO  = asignacionService.altaAsignacion(asignacion, resultVO.getAccesToken(), idUsuario );
		ModelAndView mav = new ModelAndView("redirect:/CAsignacion" , model);
		if(resultVO.getCodigo() != 500) {
//			log.info(resultVO.toString());
			mav.addObject("ejecucion", true);
			return mav;
		}else {
			mav.addObject("error", true);
			log.info("NOK AltaCliente");
			return mav;
		}
	}
	
	@PostMapping("/BAsignacion")
	public ModelAndView BAsignacion(@ModelAttribute("asignacionItem") AsignacionModelo asignacion, ModelMap model) {
		model.addAttribute("asignacionItem", asignacion);
		log.info(asignacion.toString());
		if(model.equals(null)) {
			log.info("NULL");
			return new  ModelAndView("login");
		}else {
			log.info("Edicion Asignacion model Activo");
			ResultVO resultVO = (ResultVO)model.get("model");			
			ResultVO rs = aplicacionService.consultaData(resultVO);
			model.addAttribute("model", rs);
			ModelAndView mav = new  ModelAndView("BAsignacion",  model);
			if(rs.getCodigo() != 500) {					
				return mav;
			}else {
				mav.addObject("consulta", true);
				log.info("NOK AltaCliente");
				return mav;	
			}
				
		}		
			
	}
	
	
	
	@GetMapping("/CAsignacion")
	public ModelAndView consultaAsignacion(@RequestParam(name="ejecucion", required=false) boolean ejecucion,
			@RequestParam(name="ejecucion2", required=false) boolean ejecucion2,
			@RequestParam(name="cancela", required=false) boolean cancela,
			@RequestParam(name="error", required=false) boolean error,
			ModelMap model) {
			log.info("Calendario model Activo");
			model.addAttribute("asignacionItem", new AsignacionModelo());
			model.addAttribute("datosEconomicosItem", new DatosEconomicosModelo());
			
			ResultVO resultVO = (ResultVO)model.get("model");
			model.addAttribute("model", resultVO);
			
			ResultVO rs = asignacionService.consultaAsignacion(resultVO.getAccesToken());
			resultVO.setJsonResponseObject(rs.getJsonResponseObject());
			ModelAndView mav = new ModelAndView("CAsignacion", model);
			if(rs.getCodigo() != 500) {
				mav.addObject("ejecucion", ejecucion);
				mav.addObject("ejecucion2", ejecucion2);
				mav.addObject("cancela", cancela);
				mav.addObject("error", error);
				return mav;
			}else {
				mav.addObject("consulta", true);
				log.info("NOK ConsultaAsignacion");
				return mav;
			}
		}
	
	@PostMapping("/actualizaAsignacion")
	public ModelAndView actualizaAsignacion(@ModelAttribute("asignacionItem") AsignacionModelo asignacion, ModelMap model) {
		log.info("Actualiza Asignacion model Activo");
		log.info(asignacion.toString());
		ResultVO resultVO = (ResultVO)model.get("model");
		model.addAttribute("model", resultVO);

		ResultVO rs = asignacionService.edicionAsignacion(asignacion, resultVO.getAccesToken(), asignacion.getStatusAsignacion());
		ModelAndView mav = new ModelAndView("redirect:/CAsignacion", model);
		if(rs.getCodigo() != 500) {
			resultVO.setJsonResponseObject(rs.getJsonResponseObject());
			mav.addObject("ejecucion2", true);
		}else {
			mav.addObject("error", true);
			log.info("NOK AltaCliente");
		}
		return mav;			
	}
	
	@PostMapping("/actualizaAsignacionV")
	public ModelAndView actualizaAsignacionV(@ModelAttribute("asignacionItem") AsignacionModelo asignacion, ModelMap model) {
		log.info("Actualiza Asignacion model Activo");
		log.info(asignacion.toString());
		ResultVO resultVO = (ResultVO)model.get("model");
		model.addAttribute("model", resultVO);

		ResultVO rs = asignacionService.edicionAsignacionV(asignacion, resultVO.getAccesToken());
		ModelAndView mav = new ModelAndView("redirect:/CAsignacion", model);
		if(rs.getCodigo() != 500) {
			resultVO.setJsonResponseObject(rs.getJsonResponseObject());
			mav.addObject("ejecucion2", true);
		}else {
			mav.addObject("error", true);
			log.info("NOK AltaCliente");
		}
		return mav;			
	}

	@PutMapping("/actualizaAsignacionVConfirma")
	public ModelAndView actualizaAsignacionVConfirma(@ModelAttribute("asignacionItem") AsignacionModelo asignacion, ModelMap model) {
		log.info("Confirma Notificacion Asignacion model Activo");
//		log.info(asignacion.toString());
		ResultVO resultVO = (ResultVO)model.get("model");
		model.addAttribute("model", resultVO);

		ResultVO rs = asignacionService.edicionAsignacionVConfirma(asignacion, resultVO.getAccesToken());
		ModelAndView mav = new ModelAndView("redirect:/BAsignacionI/", model);
		if(rs.getCodigo() != 500) {
			resultVO.setJsonResponseObject(rs.getJsonResponseObject());
			mav.addObject("ejecucion2", true);
		}else {
			mav.addObject("error", true);
			log.info("NOK AltaCliente");
		}
		return mav;			
	}
	@GetMapping("/CAsignacionI")
	public ModelAndView consultaAsignacionInstructor(@RequestParam(name="ejecucion", required=false) boolean ejecucion,
			@RequestParam(name="ejecucion2", required=false) boolean ejecucion2,
			@RequestParam(name="error", required=false) boolean error,
			ModelMap model) {
			log.info("Calendario Instructor model Activo");
			model.addAttribute("asignacionItem", new AsignacionModelo());
			
			ResultVO resultVO = (ResultVO)model.get("model");
			model.addAttribute("model", resultVO);
			
			ResultVO rs = asignacionService.consultaAsignacion(resultVO.getAccesToken());
			resultVO.setJsonResponseObject(rs.getJsonResponseObject());
			ModelAndView mav = new ModelAndView("CAsignacionI", model);
			if(rs.getCodigo() != 500) {
				mav.addObject("ejecucion", ejecucion);
				mav.addObject("ejecucion2", ejecucion2);
				mav.addObject("error", error);
				return mav;
			}else {
				mav.addObject("consulta", true);
				log.info("NOK ConsultaAsignacionInstructor");
				return mav;
			}
		}
	

	
	@PostMapping("/BAsignacionI")
	public ModelAndView BAsignacionInstructor(@ModelAttribute("asignacionItem") AsignacionModelo asignacion, ModelMap model) {
		model.addAttribute("asignacionItem", asignacion);
		if(model.equals(null)) {
			log.info("NULL");
			return new  ModelAndView("login");
		}else {
			log.info("Edicion Asignacion model Activo");
//			JSONObject jsonResponse = new JSONObject();
			ResultVO resultVO = (ResultVO)model.get("model");			
			ResultVO rs = clienteService.consultaCliente(resultVO.getAccesToken(), asignacion.getIdClienteAsignacion());
			resultVO.setJsonResponseObject(rs.getJsonResponse());
			model.addAttribute("model", resultVO);
			
			rs = entregableService.consultaEntregable(resultVO.getAccesToken(), asignacion.getIdAsignacion());
//			resultVO.setJsonResponse(rs.getJsonResponse());
//			jsonResponse.put("entregables", rs.getJsonResponse());
			model.addAttribute("entregables", rs.getJsonResponseObject());
			
			ModelAndView mav = new  ModelAndView("BAsignacionI",  model);
			if(rs.getCodigo() != 500) {					
				return mav;
			}else {
				mav.addObject("consulta", true);
				return mav;	
			}
		}		
	}
	
	@PostMapping("/DAsignacion")
	public ModelAndView DAsignacion(@ModelAttribute("asignacionItem") AsignacionModelo asignacion, ModelMap model) {
		if(model.equals(null)) {
			log.info("NULL");
			return new  ModelAndView("login");
		}else {
			log.info("Delete Asignacion model Activo");
			asignacion.setStatusAsignacion("Evento Cancelado");
			ResultVO resultVO = (ResultVO)model.get("model");
			model.addAttribute("model", resultVO);
			ResultVO rs = asignacionService.edicionAsignacion(asignacion, resultVO.getAccesToken(),  asignacion.getStatusAsignacion());
			log.info(rs.getCodigo().toString());
			ModelAndView mav = new  ModelAndView("redirect:/CAsignacion",  model);
			if(rs.getCodigo() != 500) {
				mav.addObject("cancela", true);
				return mav;
			}else {
				return mav;	
			}
		}		
	}
	
	
	
	@PostMapping("/BAsignacionV")
	public ModelAndView BAsignacionVendedor(@ModelAttribute("asignacionItem") AsignacionModelo asignacion, ModelMap model) {
		model.addAttribute("asignacionItem", asignacion);
//		log.info(asignacion.toString());
		if(model.equals(null)) {
			log.info("NULL");
			return new  ModelAndView("login");
		}else {
			log.info("Edicion Asignacion Archivo Participantes model Activo");
			ResultVO resultVO = (ResultVO)model.get("model");			
//			ResultVO rs = aplicacionService.consultaData(resultVO);
//			model.addAttribute("model", rs);
			ModelAndView mav = new  ModelAndView("BAsignacionV",  model);
			return mav;
		}		
	}
	
	@PostMapping("/actualizaAsignacionI")
	public ModelAndView actualizaAsignacionI(@ModelAttribute("asignacionItem") AsignacionModelo asignacion, ModelMap model) {
		log.info("Actualiza Asignacion Instructor model Activo");
		ResultVO resultVO = (ResultVO)model.get("model");
		model.addAttribute("model", resultVO);
		log.info(asignacion.toString());
		ModelAndView mav=null;
		ResultVO rs = asignacionService.edicionAsignacion(asignacion, resultVO.getAccesToken(), asignacion.getStatusAsignacion());
		if(asignacion.getStatusAsignacion().equals("Confirmado Instructor") || asignacion.getStatusAsignacion().equals("Curso Editado") || asignacion.getStatusAsignacion().equals("Curso Completado") || asignacion.getStatusAsignacion().equals("Evento Cancelado")) {
			mav = new ModelAndView("redirect:/CAsignacionI", model);
		}else {
			mav = new ModelAndView("redirect:/CEntregableI", model);
		}
		
		if(resultVO.getPerfil().equals("Direccion") || resultVO.getPerfil().equals("Operacion") || resultVO.getPerfil().equals("Administracion")) {
			if(asignacion.getStatusAsignacion().equals("Omitir Entregable") || asignacion.getStatusAsignacion().equals("Entregable Enviado") ) {
				mav = new ModelAndView("redirect:/CEntregable", model);
			}
		}else {
			mav = new ModelAndView("redirect:/CEntregableI", model);
		}
		
		if(rs.getCodigo() != 500) {
			resultVO.setJsonResponseObject(rs.getJsonResponseObject());
			mav.addObject("ejecucion2", true);
		}else {
			mav.addObject("error", true);
			log.info("NOK AltaCliente");
		}
		return mav;			
	}
	
	@GetMapping("/CAsignacionIC/{idAsignacion}/{idInstructor}")
	public ModelAndView consultaAsignacionInstructorCorreo(
			@RequestParam(name="ejecucion2", required=false) boolean ejecucion2,
			@RequestParam(name="error", required=false) boolean error,
			@PathVariable String idAsignacion, @PathVariable String idInstructor) {
			log.info("Consulta Instructor Correo model Activo");
			ModelMap model =new ModelMap();
			
			ResultVO rs = asignacionService.consultaAsignacionCorreo(idAsignacion);
			JSONObject jsonObject = (JSONObject) rs.getJsonResponse();
			JSONObject jsonAsignacion = new JSONObject((Map) jsonObject.get("asignacion"));
			model.addAttribute("asignacion", jsonAsignacion);
			model.addAttribute("asignacionItem", new AsignacionModelo());
			
			ResultVO rs1 = clienteService.consultaClienteCorreo(jsonAsignacion.get("idClienteAsignacion").toString());
			jsonObject = (JSONObject) rs1.getJsonResponse();
			JSONObject jsonCliente = new JSONObject((Map) jsonObject.get("clientes"));
			model.addAttribute("cliente", jsonCliente);

			if(!idInstructor.equals("0")) {
				if(!jsonAsignacion.get("idInstructorAsignacion").toString().equals(idInstructor)) {
					ModelAndView mav = new ModelAndView("CAsignacionIC0", model);
					mav.addObject("error", true);
					return mav;
				}
			}
			
			ModelAndView mav = new ModelAndView("CAsignacionIC", model);
			if(idInstructor.equals("0")) {
				mav.addObject("staff", true);
			}
			if(rs.getCodigo() != 500) {
				mav.addObject("ejecucion2", ejecucion2);
				mav.addObject("error", error);
				return mav;
			}else {
				mav.addObject("consulta", true);
				log.info("NOK ConsultaAsignacionInstructor");
				return mav;
			}
		}
	
	
	@PostMapping("/actualizaAsignacionIC")
	public ModelAndView actualizaAsignacionIC(@ModelAttribute("asignacionItem") AsignacionModelo asignacion, ModelMap model) {
		log.info("Actualiza Asignacion Correo model Activo");
		ResultVO resultVO = (ResultVO)model.get("model");
		model.addAttribute("model", resultVO);
		ModelAndView mav=null;
		ResultVO rs = asignacionService.edicionAsignacionC(asignacion);
		
		if(asignacion.getStatusAsignacion().equals("Confirmado Instructor") || asignacion.getStatusAsignacion().equals("Curso Editado") || asignacion.getStatusAsignacion().equals("Curso Completado") || asignacion.getStatusAsignacion().equals("Evento Cancelado")) {
			mav = new ModelAndView("CAsignacionIC0", model);
		}else {
			mav = new ModelAndView("CAsignacionIC0", model);
		}
		if(asignacion.getStatusAsignacion().equals("Entregables Validado") || asignacion.getStatusAsignacion().equals("Entregable Enviado") ) {
			mav = new ModelAndView("CAsignacionIC0", model);
		}
		
		
		if(rs.getCodigo() != 500) {
			mav.addObject("ejecucion2", true);
		}else {
			mav.addObject("error", true);
			log.info("NOK CAsignacionIC0");
		}
		return mav;			
	}
	
	@GetMapping("/CHistorico")
	public ModelAndView consultaHistorico(@RequestParam(name="ejecucion", required=false) boolean ejecucion,
			@RequestParam(name="ejecucion2", required=false) boolean ejecucion2,
			@RequestParam(name="error", required=false) boolean error,
			ModelMap model) {
			log.info("Calendario Asignacion Historico model Activo");
			model.addAttribute("asignacionItem", new AsignacionModelo());
			
			ResultVO resultVO = (ResultVO)model.get("model");
			model.addAttribute("model", resultVO);
			
			ResultVO rs = asignacionService.consultaAsignacionHistorico(resultVO.getAccesToken());
			resultVO.setJsonResponseObject(rs.getJsonResponseObject());
			ModelAndView mav = new ModelAndView("CHistorico", model);
			if(rs.getCodigo() != 500) {
				mav.addObject("ejecucion", ejecucion);
				mav.addObject("ejecucion2", ejecucion2);
				mav.addObject("error", error);
				return mav;
			}else {
				mav.addObject("consulta", true);
				log.info("NOK ConsultaAsignacionHistorico");
				return mav;
			}
		}
	
	

	

}
