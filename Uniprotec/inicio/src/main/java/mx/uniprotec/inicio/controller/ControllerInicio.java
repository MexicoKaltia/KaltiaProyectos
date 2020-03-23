package mx.uniprotec.inicio.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import mx.uniprotec.inicio.entity.User;
import mx.uniprotec.inicio.service.ILoginService;

@CrossOrigin(origins = { "*" })
@Controller
//@RequestMapping("/")
public class ControllerInicio {

	
	private static Logger log = LoggerFactory.getLogger(ControllerInicio.class);
	
	@Autowired
	ILoginService loginService;
	
		@GetMapping("/")
		public String inicio(Model model) {
			model.addAttribute("userLogin", new User());
			return "login";
		}
		
		@PostMapping("/loginInit")
		public ModelAndView loginInit(@ModelAttribute("userLogin") User user) {
			ModelAndView mav ;
			log.info(user.toString());
			if(user.getUsername().equals("admin")) {
				loginService.login(user);
				 mav = new ModelAndView("index");
			}else {
				mav = new ModelAndView("login");
			}
			
			return mav;
		}
		
		@GetMapping("/formACurso")
		public String formAcurso(Model model) {
			model.addAttribute("userLogin", new User());
			return "form-ACurso";
		}


}


