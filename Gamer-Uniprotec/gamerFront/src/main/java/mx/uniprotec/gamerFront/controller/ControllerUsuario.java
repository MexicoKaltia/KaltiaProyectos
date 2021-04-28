package mx.uniprotec.gamerFront.controller;

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
public class ControllerUsuario {
	

	private static Logger log = LoggerFactory.getLogger(ControllerUsuario.class);
	@Autowired
	ILoginService loginService;
	@Autowired
	UsuariosService usuariosService;
	@Autowired
	ModuloService moduloService;
	
	@GetMapping("/usuarios")
	public ModelAndView usuarios(@RequestParam(name="ejecucion", required=false) boolean ejecucion,
			@RequestParam(name="ejecucionI", required=false) boolean ejecucionI,
			@RequestParam(name="ejecucionA", required=false) boolean ejecucionA,
								 @RequestParam(name="error", required=false) boolean error, ModelMap model) {
		
		model.addAttribute("usuarioAudiencia", new UsuarioAudiencia());
		model.addAttribute("usuarioInstructor", new UsuarioInstructor());
		model.addAttribute("usuarioAdministrador", new UsuarioAdministrador());
//		model.addAttribute("usuarioAdministrador", new UsuarioAdministrador());
		
		if(model.equals(null)) {
			log.info("NULL");
			return new  ModelAndView("login");
		}else {
			log.info("Usuarios model Activo");
			
			ResultVO resultVO = (ResultVO)model.get("model");
			ModelAndView mav = new  ModelAndView("usuarios", model );
			String token = resultVO.getAccesToken();
			resultVO = (ResultVO) moduloService.getModulos(token);
			if(resultVO.getCodigo() > 210) {
				mav.setViewName(resultVO.getCodigo().toString());
				return mav;
			}else {
				mav.addObject("modulosDidacticos" , resultVO.getJsonResponse());
			}
			resultVO = usuariosService.getDataUsuarios(token);
//			JSONObject jsonResponse  = usuariosService.getDataUsuarios(resultVO.getAccesToken());
//			resultVO.setJsonResponse(jsonResponse);
			mav.addObject("dataUsuarios" , resultVO.getJsonResponse());

			
//			model.addAttribute("model", resultVO);
//			mav.addObject("data" , jsonResponse);
			mav.addObject("error", error);
			mav.addObject("ejecucion", ejecucion);
			mav.addObject("ejecucionI", ejecucionI);
			mav.addObject("ejecucionA", ejecucionA);
			if(resultVO.getCodigo() > 210) {
				mav.setViewName(resultVO.getCodigo().toString());
			}
			return mav;
		}
	}
	
	@PostMapping("/altaAudiencia")
	public ModelAndView altaAudiencia(@ModelAttribute("usuarioAudiencia") UsuarioAudiencia userA, ModelMap model) {
		
		ResultVO resultVO = (ResultVO)model.get("model");
		log.info(userA.toString());
		resultVO  = usuariosService.altaUsuarioAudiencia(userA, resultVO.getAccesToken());
		ModelAndView mav = new ModelAndView("redirect:/usuarios" , model);
		if(resultVO.getCodigo() != 500) {
			mav.addObject("ejecucion", true);
//			mav.addObject("ejecucionI", true);
//			mav.addObject("ejecucionA", true);
		}else {
			mav.addObject("error", true);
			log.info("NOK Usuarios");
		}
		return mav;
	}
	@PostMapping("/altaInstructor")
	public ModelAndView altaInstructor(@ModelAttribute("usuarioInstructor") UsuarioInstructor userI, ModelMap model) {
		
		ResultVO resultVO = (ResultVO)model.get("model");
		log.info(userI.toString());
		resultVO  = usuariosService.altaUsuarioInstructor(userI, resultVO.getAccesToken());
		ModelAndView mav = new ModelAndView("redirect:/usuarios" , model);
		if(resultVO.getCodigo() != 500) {
			
			mav.addObject("ejecucionI", true);
		}else {
			mav.addObject("error", true);
			log.info("NOK Usuarios");
		}
		return mav;
	}
	@PostMapping("/altaAdministrador")
	public ModelAndView altaAdministrador(@ModelAttribute("usuarioAdministrador") UsuarioAdministrador userA, ModelMap model) {
		
		ResultVO resultVO = (ResultVO)model.get("model");
		log.info(userA.toString());
		resultVO  = usuariosService.altaUsuarioAdministrador(userA, resultVO.getAccesToken());
		ModelAndView mav = new ModelAndView("redirect:/usuarios" , model);
		if(resultVO.getCodigo() != 500) {
			
			mav.addObject("ejecucionA", true);
		}else {
			mav.addObject("error", true);
			log.info("NOK Usuarios");
		}
		return mav;
	}

	@PostMapping("/actualizaInstructor")
	public ModelAndView actualizaInstructor(@ModelAttribute("usuarioInstructor") UsuarioInstructor userA, ModelMap model) {
		
		ResultVO resultVO = (ResultVO)model.get("model");
		log.info(userA.toString());
		resultVO  = usuariosService.actualizaInstructor(userA, resultVO.getAccesToken());
		ModelAndView mav = new ModelAndView("redirect:/usuarios" , model);
		if(resultVO.getCodigo() != 500) {
			
			mav.addObject("ejecucionI", true);
		}else {
			mav.addObject("error", true);
			log.info("NOK Usuarios");
		}
		return mav;
	}
	
	@PostMapping("/actualizaAudiencia")
	public ModelAndView actualizaAudiencia(@ModelAttribute("usuarioAudiencia") UsuarioAudiencia userA, ModelMap model) {
		
		ResultVO resultVO = (ResultVO)model.get("model");
		log.info(userA.toString());
		resultVO  = usuariosService.actualizaAudiencia(userA, resultVO.getAccesToken());
		ModelAndView mav = new ModelAndView("redirect:/usuarios" , model);
		if(resultVO.getCodigo() != 500) {
			
			mav.addObject("ejecucion", true);
		}else {
			mav.addObject("error", true);
			log.info("NOK Usuarios");
		}
		return mav;
	}

	
	
	
	@GetMapping("/estadisticas")
	public ModelAndView estadistica(@RequestParam(name="login", required=false) Boolean loginIn) {
		ModelAndView mav = new ModelAndView("estadisticas");
		return mav;
	}
	@GetMapping("/instructor")
	public ModelAndView instructor(@RequestParam(name="login", required=false) Boolean loginIn) {
		ModelAndView mav = new ModelAndView("instructor");
		return mav;
	}
	@GetMapping("/accesoModulo")
	public ModelAndView modulo(@RequestParam(name="login", required=false) Boolean loginIn) {
		ModelAndView mav = new ModelAndView("moduloDidactico");
		return mav;
	}
}
