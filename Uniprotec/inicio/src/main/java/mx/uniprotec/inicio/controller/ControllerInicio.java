package mx.uniprotec.inicio.controller;

import java.util.HashMap;

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
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import mx.uniprotec.entidad.modelo.ResultVO;
import mx.uniprotec.entidad.modelo.User;
import mx.uniprotec.entidad.modelo.UsuarioModelo;
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
	ResultVO resultVO = new ResultVO();
	UsuarioModelo usuario = new UsuarioModelo();
	
	
		@GetMapping("/")
		public String inicio(Model model) {
			model.addAttribute("userLogin", new User());
			return "login";
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
			resultVO = loginService.login(user);

//			model.addAttribute("loginResponse", resultVO);

			log.info(model.values().toString());			
			return new ModelAndView(resultVO.getResponse(), "model", resultVO);

		}
		
		
		@GetMapping("/inicio")
		public ModelAndView inicio(ModelMap model) {

			if(model.equals(null)) {
				log.info("NULL");
				return new  ModelAndView("login");
			}else {
				log.info("Inicio model Activo");
//				log.info(model.values().toString());
				return new  ModelAndView("index",  model);	
			}		

			}

}


