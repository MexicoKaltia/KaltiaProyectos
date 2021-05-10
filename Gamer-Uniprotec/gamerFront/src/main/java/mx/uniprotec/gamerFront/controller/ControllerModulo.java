package mx.uniprotec.gamerFront.controller;

import java.util.List;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import mx.uniprotec.entidad.modelo.ModuloDidactico;
import mx.uniprotec.entidad.modelo.ResultVO;
import mx.uniprotec.entidad.modelo.UsuarioAdministrador;
import mx.uniprotec.entidad.modelo.UsuarioAudiencia;
import mx.uniprotec.entidad.modelo.UsuarioInstructor;
import mx.uniprotec.gamerFront.service.ILoginService;
import mx.uniprotec.gamerFront.service.impl.ModuloService;
import mx.uniprotec.gamerFront.service.impl.UsuariosService;
import mx.uniprotec.gamerFront.vo.UserForm;

@Controller
@SessionAttributes ("model")
public class ControllerModulo {
	

	private static Logger log = LoggerFactory.getLogger(ControllerModulo.class);
	@Autowired
	ILoginService loginService;
	@Autowired
	ModuloService moduloService;
	
	@GetMapping("/modulos")
	public ModelAndView modulos(@RequestParam(name="ejecucion", required=false) boolean ejecucion,
			@RequestParam(name="ejecucionI", required=false) boolean ejecucionI,
			@RequestParam(name="ejecucionA", required=false) boolean ejecucionA,
								 @RequestParam(name="error", required=false) boolean error, ModelMap model) {
		
		model.addAttribute("moduloDidactico", new ModuloDidactico());
		
		if(model.equals(null)) {
			log.info("NULL");
			return new  ModelAndView("");
		}else {
			log.info("modulos model Activo");
			
			ResultVO resultVO = (ResultVO)model.get("model");
			ModelAndView mav = new  ModelAndView("modulos", model );
//			List<ModuloDidactico> modulosDidacticos = moduloService.getModulos(resultVO.getAccesToken());
			resultVO = (ResultVO) moduloService.getModulos(resultVO.getAccesToken());
			mav.addObject("modulosDidacticos" , resultVO.getJsonResponse());

			//			model.addAttribute("model", resultVO);
//			mav.addObject("data" , jsonResponse);
			mav.addObject("error", error);
			mav.addObject("ejecucion", ejecucion);
			mav.addObject("ejecucionI", ejecucionI);
			mav.addObject("ejecucionA", ejecucionA);
			if(resultVO.getCodigo() != 200) {
				mav.setViewName(resultVO.getCodigo().toString());
			}
			return mav;
		}
	}
	
	@PostMapping("/altaModulo")
	public ModelAndView altaModulo(@ModelAttribute("moduloDidactico") ModuloDidactico moduloDidactico , ModelMap model) {
		
		ResultVO resultVO = (ResultVO)model.get("model");
		log.info(moduloDidactico.toString());
		resultVO  = moduloService.altaModulo(resultVO.getAccesToken(), moduloDidactico);
		ModelAndView mav = new ModelAndView("redirect:/modulos" , model);
		if(resultVO.getCodigo() == 200) {
			mav.addObject("ejecucion", true);
//			mav.addObject("ejecucionI", true);
//			mav.addObject("ejecucionA", true);
		}else {
			mav.addObject("error", true);
			log.info("NOK Modulos");
		}
		return mav;
	}
	
	@PostMapping("/actualizaModulo")
	public ModelAndView actualizaModulo(@ModelAttribute("moduloDidactico") ModuloDidactico moduloDidactico, ModelMap model) {
		
		ResultVO resultVO = (ResultVO)model.get("model");
		log.info(moduloDidactico.toString());
		resultVO  = moduloService.actualizaModulo(resultVO.getAccesToken(), moduloDidactico);
		ModelAndView mav = new ModelAndView("redirect:/modulos" , model);
		if(resultVO.getCodigo() == 200) {
			mav.addObject("ejecucionI", true);
		}else {
			mav.addObject("error", true);
			log.info("NOK Usuarios");
		}
		return mav;
	}
	
	}
