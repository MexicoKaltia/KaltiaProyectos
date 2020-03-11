package mx.uniprotec.application.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

@CrossOrigin(origins = { "*" })
@Controller
//@RequestMapping("/")
public class AplicacionController {
	
	@GetMapping("/")
	public String index() {
		return "login";
	}
	
	
	

}
