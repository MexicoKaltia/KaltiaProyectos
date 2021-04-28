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
import mx.uniprotec.gamerFront.service.ILoginService;
import mx.uniprotec.gamerFront.service.impl.UsuariosService;
import mx.uniprotec.gamerFront.vo.UserForm;

@Controller
@SessionAttributes ("model")
public class ControllerInicio {
	

	private static Logger log = LoggerFactory.getLogger(ControllerInicio.class);
	@Autowired
	ILoginService loginService;
	@Autowired
	UsuariosService usuariosService;
	

	
	@GetMapping("/intro")
	public ModelAndView uno() {
		ModelAndView mav = new ModelAndView("intro");
		return mav;
	}
	
	@GetMapping("/")
	public ModelAndView login(@RequestParam(name="login", required=false) Boolean loginIn) {
		ModelAndView mav = new ModelAndView("login");
		mav.addObject("userLogin", new UserForm());
		mav.addObject("loginForm", loginIn);
		
		return mav;
	}
	
	@PostMapping("/inicio")
	public ModelAndView inicio(@ModelAttribute("userLogin") UserForm user) {
		ModelAndView mav = new ModelAndView();
		ModelMap model = new ModelMap();
		ResultVO resultVO = new ResultVO();
		log.info(user.toString());
		resultVO = loginService.login(user);
		
		if(resultVO.getCodigo() != 500) {
			JSONObject jsonUser = resultVO.getJsonResponse();
			resultVO.setJsonResponseObject(jsonUser);
			model.addAttribute("userNombre", jsonUser.get("nombre"));
			model.addAttribute("userStatus", jsonUser.get("status"));
			
			if(resultVO.getPerfil().equals("ROLE_ADMIN")) {
				JSONObject jsonResponse = usuariosService.dataUsuarios(resultVO.getObject().toString());
				resultVO.setJsonResponse(jsonResponse);
				mav.addObject("data" , jsonResponse);
			}
			
//			mav.addObject("error", error);
//			mav.addObject("ejecucion", ejecucion);

			log.info("Bienvenido");

			mav.setViewName(resultVO.getResponse());
			mav.addObject("user", model);
			mav.addObject("model", resultVO);
		}else {
			log.info("Credenciales inválidas");
			mav = new ModelAndView("redirect:/", model);
			mav.addObject("login", true);
		}
		
		return mav;
	}
	
	@GetMapping("/inicio")
//	@Scope("prototype")
	public ModelAndView inicio(ModelMap model) {

		if(model.equals(null)) {
			log.info("NULL");
			return new  ModelAndView("login");
		}else {
			log.info("Inicio model Activo");
			ResultVO resultVO = (ResultVO)model.get("model");
			JSONObject jsonUser = resultVO.getJsonResponseObject();
			model.addAttribute("userNombre", jsonUser.get("nombre"));
			model.addAttribute("userStatus", jsonUser.get("status"));
			ModelAndView mav = new  ModelAndView(resultVO.getResponse(),  model);
			mav.addObject("user", model);
			mav.addObject("model", resultVO);
					return 	mav;
		}		

	}
	
		
	
}
