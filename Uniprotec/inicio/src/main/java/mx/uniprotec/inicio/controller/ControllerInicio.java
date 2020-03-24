package mx.uniprotec.inicio.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServlet;

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
import org.springframework.web.servlet.ModelAndView;

import mx.uniprotec.inicio.entity.ResultVO;
import mx.uniprotec.inicio.entity.User;
import mx.uniprotec.inicio.entity.Usuario;
import mx.uniprotec.inicio.service.ILoginService;

@CrossOrigin(origins = { "*" })
@Controller
//@RequestMapping("/")
public class ControllerInicio {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static Logger log = LoggerFactory.getLogger(ControllerInicio.class);
	
	@Autowired
	ILoginService loginService;
	@Autowired
	ResultVO resultVO;
	@Autowired
	Usuario usuario;
	
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
			
			HashMap<String, Object> hashUsuario = loginService.login(user);	 
			model.addAttribute("loginResponse", hashUsuario);
//			mav.addObject("loginResponse", hashUsuario);
			mav.setViewName(hashUsuario.get("vista").toString());
//			request.getSession().setAttribute("loginResponse", hashUsuario);
			
			
			return new ModelAndView(hashUsuario.get("vista").toString(), "model", model);
		}
//		
		/*
		 * 
		 */
		@GetMapping("/formACurso")
		public String formAcurso(Model model) {
			model.addAttribute("userLogin", new User());
			return "form-ACurso";
		}


}


