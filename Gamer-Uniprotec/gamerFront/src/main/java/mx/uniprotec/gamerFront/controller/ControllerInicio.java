package mx.uniprotec.gamerFront.controller;

import java.util.Map;

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
import mx.uniprotec.gamerFront.service.IModuloService;
import mx.uniprotec.gamerFront.service.IUsuariosService;
import mx.uniprotec.gamerFront.service.impl.UsuariosService;
import mx.uniprotec.gamerFront.util.BaseClientRest;
import mx.uniprotec.gamerFront.vo.UserForm;

@Controller
@SessionAttributes ("model")
public class ControllerInicio {
	

	private static Logger log = LoggerFactory.getLogger(ControllerInicio.class);
	@Autowired
	ILoginService loginService;
	@Autowired
	IUsuariosService usuariosService;
	@Autowired
	BaseClientRest baseClientRest;
	@Autowired
	IModuloService moduloService;
	

	
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
			String accesToken = resultVO.getAccesToken();
			String tokenCU = resultVO.getObject().toString();
			
			if(resultVO.getPerfil().equals("ROLE_ADMIN")) {
				JSONObject jsonResponse = usuariosService.dataUsuarios(resultVO.getObject().toString());
				resultVO.setJsonResponse(jsonResponse);
				resultVO.setResponse("inicio");
				mav.addObject("data" , jsonResponse);
			}else if(resultVO.getPerfil().equals("ROLE_INSTR")){
				/*
				 * obtener el idUsuario, para obtener el idUsuarioInstructor, y obtener el usuario control uniprotec.
				 */
				
				ResultVO rs = (ResultVO) baseClientRest.objetoGetId( accesToken, BaseClientRest.URL_GET_USUARIOINSTRUCTOR, null, user.getUserName());
				JSONObject jsonInstructor = rs.getJsonResponse();
				JSONObject jsonUsuario = new JSONObject((Map) jsonInstructor.get("usuario"));//(JSONObject) jsonInstructor.get("usuario");
				JSONObject jsonUsuarioInstructor = new JSONObject((Map) jsonUsuario.get("usuarioInstructor"));//(JSONObject) jsonUsuario .get("usuarioInstructor");
				String idUsuarioInstructorControl = jsonUsuarioInstructor.get("usuarioInstructorIdAsignacion").toString();
				
				JSONObject jsonResponse = usuariosService.dataInstructor(idUsuarioInstructorControl, tokenCU);
				resultVO.setJsonResponse(jsonResponse);
				mav.addObject("data" , jsonResponse);
				
				JSONObject jsonInstructor2 = new JSONObject();
				resultVO = (ResultVO) moduloService.getModulos(accesToken);
				mav.addObject("modulosDidacticos" , resultVO.getJsonResponse());
				jsonInstructor2.put("modulosDidacticos", resultVO.getJsonResponse());
				model.addAttribute("modulosDidacticos", resultVO.getJsonResponse());
				
//				ResultVO rs2 = (ResultVO) baseClientRest.objetoGetId( accesToken, BaseClientRest.URL_GET_USUARIOAUDIENCIA, null, user.getUserName());
//				mav.addObject("usuarioAudiencia" , rs2.getJsonResponse());
//				jsonInstructor2.put("usuarioAudiencia", rs2.getJsonResponse());
//				model.addAttribute("usuarioAudiencia", rs2.getJsonResponse());
				
				resultVO.setJsonResponse(jsonInstructor2);
				
				JSONObject cursosControl =  usuariosService.getCursosControl(tokenCU);
				mav.addObject("cursosControl" , cursosControl);
				
				resultVO.setResponse("inicioInstructor");
				
			}else if(resultVO.getPerfil().equals("ROLE_USER")){
				/*
				 * Conseguir los modulos didacticos de usuario
				 */
				ResultVO rs = (ResultVO) baseClientRest.objetoGetId( accesToken, BaseClientRest.URL_GET_USUARIOAUDIENCIA, null, user.getUserName());
				mav.addObject("usuarioAudiencia" , rs.getJsonResponse());
				JSONObject jsonAudiencia = new JSONObject();
				jsonAudiencia.put("usuarioAudiencia", rs.getJsonResponse());
				model.addAttribute("usuarioAudiencia", rs.getJsonResponse());
				
				rs = (ResultVO) moduloService.getModulos(accesToken);
				mav.addObject("modulosDidacticos" , rs.getJsonResponse());
				jsonAudiencia.put("modulosDidacticos", rs.getJsonResponse());
				model.addAttribute("modulosDidacticos", rs.getJsonResponse());
				
				resultVO.setJsonResponse(jsonAudiencia);
				
				resultVO.setResponse("inicioAudiencia");
				
			}
			
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
	public ModelAndView inicio2(ModelMap model) {

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
