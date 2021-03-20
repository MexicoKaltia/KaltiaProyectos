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
import mx.uniprotec.entidad.modelo.UsuarioAudiencia;
import mx.uniprotec.gamerFront.service.ILoginService;
import mx.uniprotec.gamerFront.service.impl.UsuariosService;
import mx.uniprotec.gamerFront.vo.UserForm;

@Controller
@SessionAttributes ("model")
public class ControllerOperacion {
	

	private static Logger log = LoggerFactory.getLogger(ControllerOperacion.class);
	@Autowired
	ILoginService loginService;
	@Autowired
	UsuariosService usuariosService;
	
	@GetMapping("/usuarios")
	public ModelAndView usuarios(@RequestParam(name="ejecucion", required=false) boolean ejecucion, 
								 @RequestParam(name="error", required=false) boolean error, ModelMap model) {
		
		model.addAttribute("usuarioAudiencia", new UsuarioAudiencia());
		
		if(model.equals(null)) {
			log.info("NULL");
			return new  ModelAndView("login");
		}else {
			log.info("Usuarios model Activo");
			
			ResultVO resultVO = (ResultVO)model.get("model");
			ModelAndView mav = new  ModelAndView("usuarios", model );
						
//			JSONObject jsonResponse = usuariosService.dataUsuarios(resultVO.getObject().toString());
//			resultVO.setJsonResponse(jsonResponse);
//			
//			model.addAttribute("model", resultVO);
//			mav.addObject("data" , jsonResponse);
//			mav.addObject("error", error);
//			mav.addObject("ejecucion", ejecucion);
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
		}else {
			mav.addObject("error", true);
			log.info("NOK AltaCliente");
		}
		return mav;
	}
	
	
	
	@GetMapping("/cursos")
	public ModelAndView cursos(@RequestParam(name="login", required=false) Boolean loginIn) {
		ModelAndView mav = new ModelAndView("cursos");
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
