package mx.uniprotec.gamerFront.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import mx.uniprotec.entidad.modelo.ResultVO;
import mx.uniprotec.gamerFront.service.ILoginService;
import mx.uniprotec.gamerFront.vo.UserForm;

@Controller
public class ControllerInicio {
	

	private static Logger log = LoggerFactory.getLogger(ControllerInicio.class);
	@Autowired
	ILoginService loginService;
	

	
	@GetMapping("/")
	public ModelAndView uno() {
		ModelAndView mav = new ModelAndView("intro");
		return mav;
	}
	
	@GetMapping("/login")
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
//		String nombreUsuario = user.getUserName();
//		log.info(user.toString());
//		if(user.getUserPass().equals("12345")) {
//			switch (nombreUsuario) {
//				case "administrador":
//					mav.setViewName("inicio");
//					break;
//				case "instructor":
//					mav.setViewName("inicioInstructor");
//					break;
//				case "audiencia":
//					mav.setViewName("inicioAudiencia");
//					break;
//			}
//		}else {
//			mav = new ModelAndView("redirect:/login", model);
//			mav.addObject("login", true);
//		}
		
		resultVO = loginService.login(user);
		
		if(resultVO.getCodigo() != 500) {
			log.info("Bienvenido");
			mav.setViewName(resultVO.getResponse());
			mav.addObject("model", resultVO);
		}else {
			log.info("Credenciales inválidas");
			mav = new ModelAndView("redirect:/login", model);
			mav.addObject("login", true);
		}
		
		return mav;
	}
	
	@GetMapping("/usuarios")
	public ModelAndView usuarios(@RequestParam(name="ejecucion", required=false) boolean ejecucion, 
								 @RequestParam(name="error", required=false) boolean error,
								 ModelMap model) {
		if(model.equals(null)) {
			log.info("NULL");
			return new  ModelAndView("login");
		}else {
			log.info("Usuarios model Activo");
			
			ResultVO resultVO = (ResultVO)model.get("model");
			
			JSONObject jsonObject = (JSONObject) resultVO.getJsonResponse();
			JSONObject jsonUsuario = new JSONObject((Map) jsonObject.get("user"));
			
			ResultVO rs = usuarioService.consultaUsuario(resultVO.getAccesToken(), jsonUsuario.get("id").toString());
			JSONObject jsonObject2 = (JSONObject) rs.getJsonResponseObject();
//			log.info(jsonObject2.toJSONString());
			JSONObject jsonUsuario2 = new JSONObject((Map) jsonObject2.get("usuario"));
			resultVO.setJsonResponseObject(rs.getJsonResponseObject());
			
			UsuarioModelo usuario = new UsuarioModelo(Long.valueOf(jsonUsuario2.get("idUsuario").toString()),
					jsonUsuario2.get("usernameUsuario").toString(),
					jsonUsuario2.get("nombreUsuario").toString(),
					jsonUsuario2.get("emailUsuario").toString(),
					jsonUsuario2.get("notaUsuario").toString(),
					jsonUsuario2.get("perfilUsuario").toString());
//			usuario.setPasswordUsuarioOld(jsonUsuario2.get("passwordUsuario").toString());
//			log.info(usuario.toString());
			model.addAttribute("usuarioForm", usuario);
			
			
			ModelAndView mav = new  ModelAndView("CUsuario", model );
			model.addAttribute("model", resultVO);
			mav.addObject("error", error);
			mav.addObject("ejecucion", ejecucion);
			return mav;
		}
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
