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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import mx.uniprotec.entidad.modelo.AsignacionModelo;
import mx.uniprotec.entidad.modelo.PreAsignacionAE;
import mx.uniprotec.entidad.modelo.ResultVO;
import mx.uniprotec.entidad.modelo.UsuarioModelo;
import mx.uniprotec.inicio.service.IAplicacionService;
import mx.uniprotec.inicio.service.IClienteService;
import mx.uniprotec.inicio.service.IEntregableService;
import mx.uniprotec.inicio.service.ILoginService;
import mx.uniprotec.inicio.service.IPreAsignacionService;
import mx.uniprotec.inicio.service.IUsuarioService;

@CrossOrigin(origins = { "*" })
@Controller
@SessionAttributes ("model")
//@Scope("prototype")
public class ControllerPreAsignacion {

	private static Logger log = LoggerFactory.getLogger(ControllerPreAsignacion.class);
	
	@Autowired
	ILoginService loginService;
	@Autowired
	IAplicacionService aplicacionService;
	@Autowired
	IClienteService clienteService;
	@Autowired
	IPreAsignacionService preAsignacionService;
	@Autowired
	IUsuarioService usuarioService;
	@Autowired
	IEntregableService entregableService;
	
	
//	ResultVO resultVO = new ResultVO();
	UsuarioModelo usuario = new UsuarioModelo();
	
	public ControllerPreAsignacion() {
		// TODO Auto-generated constructor stub
	}
	
	/*
	 *  PRE ASIGNACION
	 */
	@GetMapping("/APreAsignacion")
	public ModelAndView preAsignacion(ModelMap model) {
//		log.info(model.toString());
		model.addAttribute("asignacionForm", new AsignacionModelo());
		model.addAttribute("asignaForm", new AsignacionModelo());

		if(model.equals(null)) {
			log.info("NULL");
			return new  ModelAndView("login");
		}else {
			log.info("PRE Asignacion model Activo");
			ResultVO resultVO = (ResultVO)model.get("model");			
			ResultVO rs = aplicacionService.consultaData(resultVO);
			resultVO.setJsonResponseObject(rs.getJsonResponseObject());
			
			model.addAttribute("model", resultVO);
//			log.info(rs.toString());
			ModelAndView mav = new  ModelAndView("APreAsignacion",  model);
//			log.info(mav.toString());
			if(rs.getCodigo() != 500) {
				return mav;
			}else {
				mav.addObject("consulta", true);
				log.info("NOK Asignacion");
				return mav;	
			}
		}		
	}
	
	@PostMapping("/altaPreAsignacion")
	public ModelAndView altaPreAsignacion(@ModelAttribute("asignacionForm") AsignacionModelo asignacion, ModelMap model) {
		log.info("Metodo de altaPreAsignacion");
		log.info(asignacion.toString());
		
		ResultVO resultVO = (ResultVO)model.get("model");
//		log.info(resultVO.getJsonResponse().toJSONString());
		JSONObject jsonObject = (JSONObject) resultVO.getJsonResponse();
		JSONObject jsonUsuario = new JSONObject((Map) jsonObject.get("user"));
		Long idUsuario = Long.valueOf( jsonUsuario.get("id").toString());
		
		resultVO  = preAsignacionService.altaPreAsignacion(asignacion, resultVO.getAccesToken(), idUsuario );
		ModelAndView mav = new ModelAndView("redirect:/BSeguimiento" , model);
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
	
	@PostMapping("/APreAsignacionAE")
	public ModelAndView APreAsignacionAE(@ModelAttribute("asignacionForm") AsignacionModelo asignacion, ModelMap model) {
		log.info("Metodo de APreAsignacionAE");
		model.addAttribute("preAsignacionAE", new PreAsignacionAE());
		
		ResultVO resultVO = (ResultVO)model.get("model");
//		log.info(resultVO.getJsonResponse().toJSONString());
		JSONObject jsonObject = (JSONObject) resultVO.getJsonResponse();
		JSONObject jsonUsuario = new JSONObject((Map) jsonObject.get("user"));
		Long idUsuario = Long.valueOf( jsonUsuario.get("id").toString());
		
		if(asignacion.getIdAsignacion() == null) {
			resultVO  = preAsignacionService.altaPreAsignacion(asignacion, resultVO.getAccesToken(), idUsuario );
			JSONObject jsonPreAsignacion= new JSONObject((Map) resultVO.getJsonResponse().get("preAsignacion"));
			asignacion.setIdAsignacion(Long.valueOf( jsonPreAsignacion.get("idPreAsignacion").toString()));
		}
		  
		
		ModelAndView mav = new ModelAndView("APreAsignacionAE" , model);
		model.addAttribute("preAsignacion", asignacion);
//		model.addAttribute("idUsuario", idUsuario);
		
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
	
	@PostMapping("/altaPreAsignacionAE")
	public ModelAndView altaPreAsignacionAE(@ModelAttribute("preAsignacionAE") PreAsignacionAE preAsignacionAE, ModelMap model) {
		log.info("Metodo de Alata PreAsignacionAE");
				
		ResultVO resultVO = (ResultVO)model.get("model");
		JSONObject jsonObject = (JSONObject) resultVO.getJsonResponse();
		JSONObject jsonUsuario = new JSONObject((Map) jsonObject.get("user"));
		Long idUsuario = Long.valueOf( jsonUsuario.get("id").toString());
		preAsignacionAE.setUserCreateTexto(jsonUsuario.get("nombre").toString());
		
		resultVO  = preAsignacionService.altaPreAsignacionAE(preAsignacionAE, resultVO.getAccesToken(), idUsuario );
		ModelAndView mav = new ModelAndView("redirect:/BSeguimiento" , model);
		model.addAttribute("preAsignacion", preAsignacionAE);
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
	
	@GetMapping("/BSeguimiento")
	public ModelAndView consultaSeguimiento(@RequestParam(name="ejecucion", required=false) boolean ejecucion,
			@RequestParam(name="ejecucion2", required=false) boolean ejecucion2,
			@RequestParam(name="error", required=false) boolean error,
			ModelMap model) {
		
			log.info("Seguimiento model Activo");
			
			ResultVO resultVO = (ResultVO)model.get("model");
			model.addAttribute("model", resultVO);
			
			ResultVO rs = preAsignacionService.consultaPreAsignacion(resultVO);
			
			resultVO.setJsonResponseObject(rs.getJsonResponseObject());
			ModelAndView mav = new ModelAndView("BSeguimiento", model);
			model.addAttribute("asignacionForm", new AsignacionModelo());
//			model.addAttribute("asignaciones", rs.getAsignaciones());
//			model.addAttribute("cursos", rs.getCursos());
//			model.addAttribute("clientes", rs.getClientes());
//			model.addAttribute("instructores", rs.getInstructores());
//			model.addAttribute("regiones", rs.getRegiones());
			
			mav.addObject("asignaciones", rs.getAsignaciones());
			mav.addObject("cursos", rs.getCursos());
			mav.addObject("clientes", rs.getClientes());
			mav.addObject("instructores", rs.getInstructores());
			mav.addObject("regiones", rs.getRegiones());
			
			
			
			if(rs.getCodigo() != 500) {
				mav.addObject("ejecucion", ejecucion);
				mav.addObject("ejecucion2", ejecucion2);
				mav.addObject("error", error);
				return mav;
			}else {
				mav.addObject("consulta", true);
				log.info("NOK ConsultaAsignacion");
				return mav;
			}
		}
	
	
	@PostMapping("/DPreAsignacion/{idAsignacion}")
	public ModelAndView DPreAsignacion(@PathVariable String idAsignacion, ModelMap model) {
//		log.info(idAsignacion);
		if(model.equals(null)) {
			log.info("NULL");
			return new  ModelAndView("login");
		}else {
			log.info("Delete PRE Asignacion model Activo");
			ResultVO resultVO = (ResultVO)model.get("model");			
			ResultVO rs = preAsignacionService.deletePreAsignacion(idAsignacion, resultVO.getAccesToken());
			model.addAttribute("model", resultVO);
			ModelAndView mav = new  ModelAndView("redirect:/BSeguimiento",  model);
			if(rs.getCodigo() != 500) {
				mav.addObject("ejecucion", true);
				return mav;
			}else {
				mav.addObject("error", true);
				log.info("NOK AltaCliente");
				return mav;	
			}
		}		
	}
	
	@PostMapping("/BPreAsignacion")
	public ModelAndView actualizaPreAsignacion(@ModelAttribute("asignacionForm") AsignacionModelo asignacion, ModelMap model) {
		log.info("Actualizacion PreAsignacion");
		ResultVO resultVO = (ResultVO)model.get("model");
		model.addAttribute("model", resultVO);

		ResultVO rs = preAsignacionService.actualizaPreAsignacion(asignacion, resultVO.getAccesToken());
		ModelAndView mav = new ModelAndView("redirect:/BSeguimiento", model);
		if(rs.getCodigo() != 500) {
			resultVO.setJsonResponseObject(rs.getJsonResponseObject());
			mav.addObject("ejecucion", true);
		}else {
			mav.addObject("error", true);
			log.info("NOK AltaCliente");
		}
		return mav;			
	}
//	
//	
//	
//	@GetMapping("/CAsignacion")
//	public ModelAndView consultaAsignacion(@RequestParam(name="ejecucion", required=false) boolean ejecucion,
//			@RequestParam(name="ejecucion2", required=false) boolean ejecucion2,
//			@RequestParam(name="error", required=false) boolean error,
//			ModelMap model) {
//			log.info("Calendario model Activo");
//			model.addAttribute("asignacionItem", new AsignacionModelo());
//			
//			ResultVO resultVO = (ResultVO)model.get("model");
//			model.addAttribute("model", resultVO);
//			
//			ResultVO rs = asignacionService.consultaAsignacion(resultVO.getAccesToken());
//			resultVO.setJsonResponseObject(rs.getJsonResponseObject());
//			ModelAndView mav = new ModelAndView("CAsignacion", model);
//			if(rs.getCodigo() != 500) {
//				mav.addObject("ejecucion", ejecucion);
//				mav.addObject("ejecucion2", ejecucion2);
//				mav.addObject("error", error);
//				return mav;
//			}else {
//				mav.addObject("consulta", true);
//				log.info("NOK ConsultaAsignacion");
//				return mav;
//			}
//		}
//	
//	@PostMapping("/actualizaAsignacion")
//	public ModelAndView actualizaAsignacion(@ModelAttribute("asignacionItem") AsignacionModelo asignacion, ModelMap model) {
//		log.info("Actualiza Asignacion model Activo");
//		log.info(asignacion.toString());
//		ResultVO resultVO = (ResultVO)model.get("model");
//		model.addAttribute("model", resultVO);
//
//		ResultVO rs = asignacionService.edicionAsignacion(asignacion, resultVO.getAccesToken(), asignacion.getStatusAsignacion());
//		ModelAndView mav = new ModelAndView("redirect:/CAsignacion", model);
//		if(rs.getCodigo() != 500) {
//			resultVO.setJsonResponseObject(rs.getJsonResponseObject());
//			mav.addObject("ejecucion2", true);
//		}else {
//			mav.addObject("error", true);
//			log.info("NOK AltaCliente");
//		}
//		return mav;			
//	}
//	
//	@PostMapping("/actualizaAsignacionV")
//	public ModelAndView actualizaAsignacionV(@ModelAttribute("asignacionItem") AsignacionModelo asignacion, ModelMap model) {
//		log.info("Actualiza Asignacion model Activo");
//		log.info(asignacion.toString());
//		ResultVO resultVO = (ResultVO)model.get("model");
//		model.addAttribute("model", resultVO);
//
//		ResultVO rs = asignacionService.edicionAsignacionV(asignacion, resultVO.getAccesToken());
//		ModelAndView mav = new ModelAndView("redirect:/CAsignacion", model);
//		if(rs.getCodigo() != 500) {
//			resultVO.setJsonResponseObject(rs.getJsonResponseObject());
//			mav.addObject("ejecucion2", true);
//		}else {
//			mav.addObject("error", true);
//			log.info("NOK AltaCliente");
//		}
//		return mav;			
//	}
//
//	@PutMapping("/actualizaAsignacionVConfirma")
//	public ModelAndView actualizaAsignacionVConfirma(@ModelAttribute("asignacionItem") AsignacionModelo asignacion, ModelMap model) {
//		log.info("Confirma Notificacion Asignacion model Activo");
////		log.info(asignacion.toString());
//		ResultVO resultVO = (ResultVO)model.get("model");
//		model.addAttribute("model", resultVO);
//
//		ResultVO rs = asignacionService.edicionAsignacionVConfirma(asignacion, resultVO.getAccesToken());
//		ModelAndView mav = new ModelAndView("redirect:/BAsignacionI/", model);
//		if(rs.getCodigo() != 500) {
//			resultVO.setJsonResponseObject(rs.getJsonResponseObject());
//			mav.addObject("ejecucion2", true);
//		}else {
//			mav.addObject("error", true);
//			log.info("NOK AltaCliente");
//		}
//		return mav;			
//	}
//	@GetMapping("/CAsignacionI")
//	public ModelAndView consultaAsignacionInstructor(@RequestParam(name="ejecucion", required=false) boolean ejecucion,
//			@RequestParam(name="ejecucion2", required=false) boolean ejecucion2,
//			@RequestParam(name="error", required=false) boolean error,
//			ModelMap model) {
//			log.info("Calendario Instructor model Activo");
//			model.addAttribute("asignacionItem", new AsignacionModelo());
//			
//			ResultVO resultVO = (ResultVO)model.get("model");
//			model.addAttribute("model", resultVO);
//			
//			ResultVO rs = asignacionService.consultaAsignacion(resultVO.getAccesToken());
//			resultVO.setJsonResponseObject(rs.getJsonResponseObject());
//			ModelAndView mav = new ModelAndView("CAsignacionI", model);
//			if(rs.getCodigo() != 500) {
//				mav.addObject("ejecucion", ejecucion);
//				mav.addObject("ejecucion2", ejecucion2);
//				mav.addObject("error", error);
//				return mav;
//			}else {
//				mav.addObject("consulta", true);
//				log.info("NOK ConsultaAsignacionInstructor");
//				return mav;
//			}
//		}
//	
//
//	
//	@PostMapping("/BAsignacionI")
//	public ModelAndView BAsignacionInstructor(@ModelAttribute("asignacionItem") AsignacionModelo asignacion, ModelMap model) {
//		model.addAttribute("asignacionItem", asignacion);
//		if(model.equals(null)) {
//			log.info("NULL");
//			return new  ModelAndView("login");
//		}else {
//			log.info("Edicion Asignacion model Activo");
//			ResultVO resultVO = (ResultVO)model.get("model");			
//			ResultVO rs = clienteService.consultaCliente(resultVO.getAccesToken(), asignacion.getIdClienteAsignacion());
//			resultVO.setJsonResponseObject(rs.getJsonResponse());
//			model.addAttribute("model", resultVO);
////			log.info(resultVO.toString());
//			ModelAndView mav = new  ModelAndView("BAsignacionI",  model);
////			log.info(model.toString());
//			if(rs.getCodigo() != 500) {					
//				return mav;
//			}else {
//				mav.addObject("consulta", true);
//				return mav;	
//			}
//		}		
//	}
//	
//	
//	
//	@PostMapping("/BAsignacionV")
//	public ModelAndView BAsignacionVendedor(@ModelAttribute("asignacionItem") AsignacionModelo asignacion, ModelMap model) {
//		model.addAttribute("asignacionItem", asignacion);
////		log.info(asignacion.toString());
//		if(model.equals(null)) {
//			log.info("NULL");
//			return new  ModelAndView("login");
//		}else {
//			log.info("Edicion Asignacion Archivo Participantes model Activo");
//			ResultVO resultVO = (ResultVO)model.get("model");			
////			ResultVO rs = aplicacionService.consultaData(resultVO);
////			model.addAttribute("model", rs);
//			ModelAndView mav = new  ModelAndView("BAsignacionV",  model);
//			return mav;
//		}		
//	}
//	
//	@PostMapping("/actualizaAsignacionI")
//	public ModelAndView actualizaAsignacionI(@ModelAttribute("asignacionItem") AsignacionModelo asignacion, ModelMap model) {
//		log.info("Actualiza Asignacion Instructor model Activo");
//		ResultVO resultVO = (ResultVO)model.get("model");
//		model.addAttribute("model", resultVO);
//		log.info(asignacion.toString());
//		ModelAndView mav=null;
//		ResultVO rs = asignacionService.edicionAsignacion(asignacion, resultVO.getAccesToken(), asignacion.getStatusAsignacion());
//		if(asignacion.getStatusAsignacion().equals("Confirmado Instructor") || asignacion.getStatusAsignacion().equals("Curso Editado") || asignacion.getStatusAsignacion().equals("Curso Completado") || asignacion.getStatusAsignacion().equals("Evento Cancelado")) {
//			mav = new ModelAndView("redirect:/CAsignacionI", model);
//		}else {
//			mav = new ModelAndView("redirect:/CEntregableI", model);
//		}
//		
//		if(resultVO.getPerfil().equals("Direccion") || resultVO.getPerfil().equals("Operacion") || resultVO.getPerfil().equals("Administracion")) {
//			if(asignacion.getStatusAsignacion().equals("Omitir Entregable") || asignacion.getStatusAsignacion().equals("Entregable Enviado") ) {
//				mav = new ModelAndView("redirect:/CEntregable", model);
//			}
//		}else {
//			mav = new ModelAndView("redirect:/CEntregableI", model);
//		}
//			
//		
//		
//		
//		if(rs.getCodigo() != 500) {
//			resultVO.setJsonResponseObject(rs.getJsonResponseObject());
//			mav.addObject("ejecucion2", true);
//		}else {
//			mav.addObject("error", true);
//			log.info("NOK AltaCliente");
//		}
//		return mav;			
//	}
//	
//	@GetMapping("/CAsignacionIC/{idAsignacion}/{idInstructor}")
//	public ModelAndView consultaAsignacionInstructorCorreo(
//			@RequestParam(name="ejecucion2", required=false) boolean ejecucion2,
//			@RequestParam(name="error", required=false) boolean error,
//			@PathVariable String idAsignacion, @PathVariable String idInstructor) {
//			log.info("Consulta Instructor Correo model Activo");
//			ModelMap model =new ModelMap();
//			
//			ResultVO rs = asignacionService.consultaAsignacionCorreo(idAsignacion);
//			JSONObject jsonObject = (JSONObject) rs.getJsonResponse();
//			JSONObject jsonUsuario = new JSONObject((Map) jsonObject.get("asignacion"));
//			model.addAttribute("asignacion", jsonUsuario);
//			model.addAttribute("asignacionItem", new AsignacionModelo());
//			
//			ResultVO rs1 = clienteService.consultaClienteCorreo(jsonUsuario.get("idClienteAsignacion").toString());
//			jsonObject = (JSONObject) rs1.getJsonResponse();
//			JSONObject jsonCliente = new JSONObject((Map) jsonObject.get("clientes"));
//			model.addAttribute("cliente", jsonCliente);
//
//			if(!idInstructor.equals("0")) {
//				if(!jsonUsuario.get("idInstructorAsignacion").toString().equals(idInstructor)) {
//					ModelAndView mav = new ModelAndView("CAsignacionIC0", model);
//					mav.addObject("error", true);
//					return mav;
//				}
//			}
//			
//			ModelAndView mav = new ModelAndView("CAsignacionIC", model);
//			if(idInstructor.equals("0")) {
//				mav.addObject("staff", true);
//			}
//			if(rs.getCodigo() != 500) {
//				mav.addObject("ejecucion2", ejecucion2);
//				mav.addObject("error", error);
//				return mav;
//			}else {
//				mav.addObject("consulta", true);
//				log.info("NOK ConsultaAsignacionInstructor");
//				return mav;
//			}
//		}
//	
//	
//	@PostMapping("/actualizaAsignacionIC")
//	public ModelAndView actualizaAsignacionIC(@ModelAttribute("asignacionItem") AsignacionModelo asignacion, ModelMap model) {
//		log.info("Actualiza Asignacion Correo model Activo");
//		ResultVO resultVO = (ResultVO)model.get("model");
//		model.addAttribute("model", resultVO);
////		log.info(asignacion.toString());
////		log.info(asignacion.getStatusAsignacion());
//		ModelAndView mav=null;
//		ResultVO rs = asignacionService.edicionAsignacionC(asignacion);
////		if(asignacion.getStatusAsignacion().equals("Confirmado Instructor") && rs.getCodigo() != 500) {
////			aplicacionService.citaInstructor(asignacion);
////		}
//
//			
//		
//		if(asignacion.getStatusAsignacion().equals("Confirmado Instructor") || asignacion.getStatusAsignacion().equals("Curso Editado") || asignacion.getStatusAsignacion().equals("Curso Completado") || asignacion.getStatusAsignacion().equals("Evento Cancelado")) {
//			mav = new ModelAndView("CAsignacionIC0", model);
//		}else {
//			mav = new ModelAndView("CAsignacionIC0", model);
//		}
//		if(asignacion.getStatusAsignacion().equals("Entregables Validado") || asignacion.getStatusAsignacion().equals("Entregable Enviado") ) {
//			mav = new ModelAndView("CAsignacionIC0", model);
//		}
//		
//		
//		if(rs.getCodigo() != 500) {
////			resultVO.setJsonResponseObject(rs.getJsonResponseObject());
//			mav.addObject("ejecucion2", true);
//		}else {
//			mav.addObject("error", true);
//			log.info("NOK CAsignacionIC0");
//		}
//		return mav;			
//	}
//	
//	@GetMapping("/CHistorico")
//	public ModelAndView consultaHistorico(@RequestParam(name="ejecucion", required=false) boolean ejecucion,
//			@RequestParam(name="ejecucion2", required=false) boolean ejecucion2,
//			@RequestParam(name="error", required=false) boolean error,
//			ModelMap model) {
//			log.info("Calendario Asignacion Historico model Activo");
//			model.addAttribute("asignacionItem", new AsignacionModelo());
//			
//			ResultVO resultVO = (ResultVO)model.get("model");
//			model.addAttribute("model", resultVO);
//			
//			ResultVO rs = asignacionService.consultaAsignacionHistorico(resultVO.getAccesToken());
//			resultVO.setJsonResponseObject(rs.getJsonResponseObject());
//			ModelAndView mav = new ModelAndView("CHistorico", model);
//			if(rs.getCodigo() != 500) {
//				mav.addObject("ejecucion", ejecucion);
//				mav.addObject("ejecucion2", ejecucion2);
//				mav.addObject("error", error);
//				return mav;
//			}else {
//				mav.addObject("consulta", true);
//				log.info("NOK ConsultaAsignacionHistorico");
//				return mav;
//			}
//		}


}
