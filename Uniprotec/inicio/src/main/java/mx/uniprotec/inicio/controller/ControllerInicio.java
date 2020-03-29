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

import mx.uniprotec.inicio.entity.ResultVO;
import mx.uniprotec.inicio.entity.User;
import mx.uniprotec.inicio.entity.Usuario;
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
	ResultVO resultVO;
	@Autowired
	Usuario usuario;
	
//	 @ModelAttribute
//     public void modeloComun(Model model) {
//    	 model.addAttribute("requestLoginVO", requestLoginVO);
//    	 
//     }
	
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
		public ModelAndView loginInit(@ModelAttribute("userLogin") User user,
				HttpServletRequest request,
				HttpServletResponse response) {
			
			ModelAndView mav = new ModelAndView();
			ModelMap model = new ModelMap();
			log.info(user.toString());
			
			HashMap<String, Object> hashUsuario = loginService.login(user);	 
			
			
			model.addAttribute("loginResponse", hashUsuario);
			 request.getSession().setAttribute("resultVO", hashUsuario.get("resultVO"));
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


