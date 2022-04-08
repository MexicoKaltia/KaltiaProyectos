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
import mx.uniprotec.entidad.modelo.EntregableModelo;
import mx.uniprotec.entidad.modelo.ResultVO;
import mx.uniprotec.entidad.modelo.UsuarioModelo;
import mx.uniprotec.inicio.service.IAplicacionService;
import mx.uniprotec.inicio.service.IAsignacionService;
import mx.uniprotec.inicio.service.IClienteService;
import mx.uniprotec.inicio.service.IEntregableService;
import mx.uniprotec.inicio.service.ILoginService;
import mx.uniprotec.inicio.service.IUsuarioService;

@CrossOrigin(origins = { "*" })
@Controller
@SessionAttributes ("model")
//@Scope("prototype")
public class ControllerEntregable {

	private static Logger log = LoggerFactory.getLogger(ControllerEntregable.class);
	
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
	
	
//	ResultVO resultVO = new ResultVO();
	UsuarioModelo usuario = new UsuarioModelo();
	
	public ControllerEntregable() {
		// TODO Auto-generated constructor stub
	}
	
	
	/*
	 * Entregables
	 */
	@PostMapping("/AEntregable")
	public ModelAndView AEntregable(@ModelAttribute("asignacionItem") AsignacionModelo asignacion, ModelMap model) {
		model.addAttribute("asignacionItem", asignacion);
		model.addAttribute("entregableModelo", new EntregableModelo());
		if(model.equals(null)) {
			log.info("NULL");
			return new  ModelAndView("login");
		}else {
			log.info("Captura Entregable model Activo");
			JSONObject jsonResponse = new JSONObject();
			ResultVO resultVO = (ResultVO)model.get("model");			
			ResultVO rs = clienteService.consultaCliente(resultVO.getAccesToken(), asignacion.getIdClienteAsignacion());
			
			jsonResponse.put("clientes", rs.getJsonResponse());
			
			rs = entregableService.consultaEntregable(resultVO.getAccesToken(), asignacion.getIdAsignacion());
			jsonResponse.put("entregables", rs.getJsonResponse());
			
			resultVO.setJsonResponseObject(jsonResponse);
			
			model.addAttribute("model", resultVO);
//			log.info(resultVO.toString());
			ModelAndView mav = new  ModelAndView("AEntregable",  model);
//			log.info(model.toString());
			if(rs.getCodigo() != 500) {					
				return mav;
			}else {
				mav.addObject("consulta", true);
				return mav;	
			}
		}		
	}
	
	@PostMapping("/AEntregableV")
	public ModelAndView AEntregableV(@ModelAttribute("asignacionItem") AsignacionModelo asignacion, ModelMap model) {
//		model.addAttribute("asignacionItem", asignacion);
//		model.addAttribute("entregableModelo", new EntregableModelo());
		if(model.equals(null)) {
			log.info("NULL");
			return new  ModelAndView("login");
		}else {
			log.info("Get Entregable Descarga Vendedor model Activo");
			JSONObject jsonResponse = new JSONObject();
			ResultVO resultVO = (ResultVO)model.get("model");			
			
			ResultVO rs = entregableService.consultaEntregable(resultVO.getAccesToken(), asignacion.getIdAsignacion());
			jsonResponse.put("entregables", rs.getJsonResponse());
			
			resultVO.setJsonResponseObject(jsonResponse);	
			model.addAttribute("model", resultVO);

			ModelAndView mav = new  ModelAndView("AEntregableV",  model);
			if(rs.getCodigo() != 500) {					
				return mav;
			}else {
				mav.addObject("consulta", true);
				return mav;	
			}
		}		
	}

	
	@PostMapping("/BEntregable")
	public ModelAndView BEntregable(@ModelAttribute("entregableModelo") EntregableModelo entregable, ModelMap model) {
		if(model.equals(null)) {
			log.info("NULL");
			return new  ModelAndView("login");
		}else {
			log.info("Edicion Entregable model Activo");

			JSONObject jsonResponse = new JSONObject();
			ResultVO resultVO = (ResultVO)model.get("model");			
			JSONObject jsonObject = (JSONObject) resultVO.getJsonResponse();
			JSONObject jsonUsuario = new JSONObject((Map) jsonObject.get("user"));
			Long idUsuario = Long.valueOf( jsonUsuario.get("id").toString());
			String perfil = jsonUsuario.get("perfil").toString();
			ResultVO rs  = entregableService.createEntregable(entregable, resultVO.getAccesToken(), idUsuario );
			
			ModelAndView mav = new ModelAndView("redirect:/CEntregable" , model);
			if(perfil.equals("Instructor")) {
				mav = new ModelAndView("redirect:/CEntregableI" , model);
			}
			
			model.addAttribute("model", resultVO);
			log.info(rs.getCodigo().toString());
			
			if(rs.getCodigo() != 500) {
				if(entregable.isAltaDocto()) {
					mav.addObject("documentacionEntregable", true);
					mav.addObject("idEntregable", entregable.getRfcOriginalAsignacion()+"-"+entregable.getIdEntregableLogico());
					
				}else {
					mav.addObject("ejecucionEntregable", true);
				}
				
				return mav;
			}else {
				mav.addObject("ejecucionEntregableError", true);
				mav.addObject("causa", rs.getMensaje());
				log.info("NOK Entregable");
				return mav;
			}
		}		
	}
	
	@GetMapping("/CEntregable")
	public ModelAndView consultaEntregable(@RequestParam(name="ejecucion", required=false) boolean ejecucion,
			@RequestParam(name="ejecucion2", required=false) boolean ejecucion2,
			@RequestParam(name="error", required=false) boolean error,
			@RequestParam(name="ejecucionEntregable", required=false) boolean ejecucionEntregable,
			@RequestParam(name="documentacionEntregable", required=false) boolean documentacionEntregable,
			@RequestParam(name="idEntregable", required=false) String idEntregable,
			@RequestParam(name="ejecucionEntregableActualizado", required=false) boolean ejecucionEntregableActualizado,
			@RequestParam(name="ejecucionEntregableError", required=false) boolean ejecucionEntregableError,
			@RequestParam(name="ejecucionEntregableActualizadoError", required=false) boolean ejecucionEntregableActualizadoError,
			@RequestParam(name="causa", required=false) String causa,
			ModelMap model) {
			log.info("Entregable model Activo");
			model.addAttribute("entregableItem", new AsignacionModelo());
			
			ResultVO resultVO = (ResultVO)model.get("model");
			model.addAttribute("model", resultVO);
			
			ResultVO rs = asignacionService.consultaAsignacion(resultVO.getAccesToken());
			resultVO.setJsonResponseObject(rs.getJsonResponseObject());
			ModelAndView mav = new ModelAndView("CEntregable", model);
			if(rs.getCodigo() != 500) {
				mav.addObject("ejecucion", ejecucion);
				mav.addObject("ejecucion2", ejecucion2);
				mav.addObject("ejecucionEntregable", ejecucionEntregable);
				mav.addObject("documentacionEntregable", documentacionEntregable);
				mav.addObject("idEntregable", "/verEntregable/"+idEntregable);
				mav.addObject("ejecucionEntregableActualizado", ejecucionEntregableActualizado);
				mav.addObject("ejecucionEntregableError", ejecucionEntregableError);
				mav.addObject("ejecucionEntregableActualizadoError", ejecucionEntregableActualizadoError);
				mav.addObject("error", error);
				mav.addObject("causa", causa);
				return mav;
			}else {
				mav.addObject("consulta", true);
				log.info("NOK ConsultaAsignacion");
				return mav;
			}
		}
	
	/*
	 * 
	 */
	
		

	@GetMapping("/CEntregableI")
	public ModelAndView consultaEntregableI(@RequestParam(name="ejecucion", required=false) boolean ejecucion,
			@RequestParam(name="ejecucion2", required=false) boolean ejecucion2,
			@RequestParam(name="error", required=false) boolean error,
			@RequestParam(name="ejecucionEntregable", required=false) boolean ejecucionEntregable,
			@RequestParam(name="documentacionEntregable", required=false) boolean documentacionEntregable,
			@RequestParam(name="idEntregable", required=false) String idEntregable,
			@RequestParam(name="ejecucionEntregableActualizado", required=false) boolean ejecucionEntregableActualizado,
			@RequestParam(name="ejecucionEntregableError", required=false) boolean ejecucionEntregableError,
			@RequestParam(name="ejecucionEntregableActualizadoError", required=false) boolean ejecucionEntregableActualizadoError,
			@RequestParam(name="causa", required=false) String causa,
			ModelMap model) {
			log.info("Entregable model Activo");
			model.addAttribute("entregableItem", new AsignacionModelo());
			
			ResultVO resultVO = (ResultVO)model.get("model");
			model.addAttribute("model", resultVO);
			
			ResultVO rs = asignacionService.consultaAsignacion(resultVO.getAccesToken());
			resultVO.setJsonResponseObject(rs.getJsonResponseObject());
			ModelAndView mav = new ModelAndView("CEntregableI", model);
			if(rs.getCodigo() != 500) {
				mav.addObject("ejecucion", ejecucion);
				mav.addObject("ejecucion2", ejecucion2);
				mav.addObject("ejecucionEntregable", ejecucionEntregable);
				mav.addObject("documentacionEntregable", documentacionEntregable);
				mav.addObject("idEntregable", "/verEntregable/"+idEntregable);
				mav.addObject("ejecucionEntregableActualizado", ejecucionEntregableActualizado);
				mav.addObject("ejecucionEntregableError", ejecucionEntregableError);
				mav.addObject("ejecucionEntregableActualizadoError", ejecucionEntregableActualizadoError);
				mav.addObject("error", error);
				mav.addObject("causa", causa);
				return mav;
			}else {
				mav.addObject("consulta", true);
				log.info("NOK ConsultaAsignacion");
				return mav;
			}
		}

	
	@GetMapping("/DEntregable/{idEntregable}")
	public ModelAndView DPreAsignacion(@PathVariable String idEntregable, ModelMap model) {
		if (model.equals(null)) {
			log.info("NULL");
			return new ModelAndView("login");
		} else {
			log.info("Delete Entregable model Activo");
			ResultVO resultVO = (ResultVO) model.get("model");
			ResultVO rs = entregableService.deleteEntregable(idEntregable, resultVO.getAccesToken());
			model.addAttribute("model", resultVO);
			ModelAndView mav = new ModelAndView("redirect:/CEntregable", model);
			if (rs.getCodigo() != 500) {
				mav.addObject("ejecucion", true);
				return mav;
			} else {
				mav.addObject("error", true);
				log.info("NOK AltaCliente");
				return mav;
			}
		}
	}
	
	

}
