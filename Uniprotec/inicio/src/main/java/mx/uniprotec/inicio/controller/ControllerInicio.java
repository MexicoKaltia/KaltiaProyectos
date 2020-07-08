package mx.uniprotec.inicio.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import mx.uniprotec.entidad.modelo.AsignacionModelo;
import mx.uniprotec.entidad.modelo.ClienteModelo;
import mx.uniprotec.entidad.modelo.ResultVO;
import mx.uniprotec.entidad.modelo.User;
import mx.uniprotec.entidad.modelo.UsuarioModelo;
import mx.uniprotec.entidad.modelo.VendedorModelo;
import mx.uniprotec.inicio.service.IAplicacionService;
import mx.uniprotec.inicio.service.IAsignacionService;
import mx.uniprotec.inicio.service.ILoginService;

@CrossOrigin(origins = { "*" })
@Controller
@SessionAttributes ("model")
public class ControllerInicio extends HttpServlet{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static Logger log = LoggerFactory.getLogger(ControllerInicio.class);
	
	@Autowired
	ILoginService loginService;
	@Autowired
	IAplicacionService aplicacionService;
	@Autowired
	IAsignacionService asignacionService;
	
//	ResultVO resultVO = new ResultVO();
	UsuarioModelo usuario = new UsuarioModelo();
	
	
		@GetMapping("/")
		public ModelAndView inicio(@RequestParam(name="login", required=false) Boolean loginIn) {
			ModelAndView mav = new ModelAndView("login");
			mav.addObject("userLogin", new User());
			mav.addObject("loginForm", loginIn);
			if(loginIn != null)
				log.info(loginIn.toString());
			return mav;
		}
		
		
//		
		/*
		 * 
		 */
		@PostMapping("/loginInit")
		public ModelAndView loginInit(@ModelAttribute("userLogin") User user) {
			
			ModelAndView mav = new ModelAndView();
			ModelMap model = new ModelMap();
			log.info(user.toString());
			
			ResultVO resultVO = new ResultVO();
			resultVO = loginService.login(user);
			log.info(resultVO.toString());
			if(resultVO.getCodigo() != 500) {
				
				return new ModelAndView(resultVO.getResponse(), "model", resultVO);
			}else {
				log.info("Credenciales inválidas");
				mav = new ModelAndView("redirect:/", model);
				mav.addObject("login", true);
				return mav;
			}

		}
		
		
		@GetMapping("/inicio")
		public ModelAndView inicio(ModelMap model) {

			if(model.equals(null)) {
				log.info("NULL");
				return new  ModelAndView("login");
			}else {
				log.info("Inicio model Activo");
				ResultVO resultVO = (ResultVO)model.get("model");
				log.info(resultVO.getResponse());
						return new  ModelAndView(resultVO.getResponse(),  model);	
			}		

			}
		
		
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
				
				model.addAttribute("model", rs);
				
				ModelAndView mav = new  ModelAndView("Asignacion",  model);
				log.info(mav.toString());
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
			resultVO  = asignacionService.altaAsignacion(asignacion, resultVO.getAccesToken());
			ModelAndView mav = new ModelAndView("redirect:/CAsignacion" , model);
			if(resultVO.getCodigo() != 500) {
//				log.info(resultVO.toString());
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
			model.addAttribute("asignacionItemUpdate", new AsignacionModelo());
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
				@RequestParam(name="error", required=false) boolean error,
				ModelMap model) {
				log.info("Calendario model Activo");
				model.addAttribute("asignacionItem", new AsignacionModelo());
				
				ResultVO resultVO = (ResultVO)model.get("model");
				model.addAttribute("model", resultVO);
				
				ResultVO rs = asignacionService.consultaAsignacion(resultVO.getAccesToken());
				resultVO.setJsonResponseObject(rs.getJsonResponseObject());
				ModelAndView mav = new ModelAndView("CAsignacion", model);
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
		
		@PostMapping("/actualizaAsignacion")
		public ModelAndView actualizaAsignacion(@ModelAttribute("asignacionItemUpdate") AsignacionModelo asignacion, ModelMap model) {
			log.info("Actualiza Asignacion model Activo");
			ResultVO resultVO = (ResultVO)model.get("model");
			model.addAttribute("model", resultVO);

			ResultVO rs = asignacionService.edicionAsignacion(asignacion, resultVO.getAccesToken());
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


		
		@GetMapping("/CEntregable")
		public ModelAndView consultaEntregable(@RequestParam(name="ejecucion", required=false) boolean ejecucion,
				@RequestParam(name="ejecucion2", required=false) boolean ejecucion2,
				@RequestParam(name="error", required=false) boolean error,
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
					mav.addObject("error", error);
					return mav;
				}else {
					mav.addObject("consulta", true);
					log.info("NOK ConsultaAsignacion");
					return mav;
				}
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
			model.addAttribute("asignacionItemUpdate", new AsignacionModelo());
			if(model.equals(null)) {
				log.info("NULL");
				return new  ModelAndView("login");
			}else {
				log.info("Edicion Asignacion model Activo");
				ResultVO resultVO = (ResultVO)model.get("model");			
				ResultVO rs = aplicacionService.consultaData(resultVO);
				model.addAttribute("model", rs);
				ModelAndView mav = new  ModelAndView("BAsignacionI",  model);
				if(rs.getCodigo() != 500) {					
					return mav;
				}else {
					mav.addObject("consulta", true);
					log.info("NOK AltaCliente");
					return mav;	
				}
					
			}		
				
		}
		
		

}


