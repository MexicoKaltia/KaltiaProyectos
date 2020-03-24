package mx.uniprotec.inicio.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import mx.uniprotec.inicio.entity.Cliente;
import mx.uniprotec.inicio.entity.ResultVO;
import mx.uniprotec.inicio.entity.Usuario;
import mx.uniprotec.inicio.service.ILoginService;

@Controller
public class ControllerCrud extends HttpServlet {
	
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

private static Logger log = LoggerFactory.getLogger(ControllerCrud.class);
	
	@Autowired
	ILoginService loginService;
	@Autowired
	ResultVO resultVO;
	@Autowired
	Usuario usuario;
	@Autowired
	Cliente cliente;
	
	

	
	/*
	 * CRUD CLIENTE
	 * 
	 */
	@SuppressWarnings({ "unused", "unchecked" })
	@GetMapping("/ACliente")
	public ModelAndView acliente(ModelMap model) {
		
		if(model.equals(null)) {
			log.info("ACTIVO");
			return new  ModelAndView("login");
		}else {
			log.info("ES NULL");
			HashMap<String, Object> hashUsuario = (HashMap<String, Object>) model.get("loginResponse");
//			HashMap<String, Object> hashUsuario = (HashMap<String, Object>) request.getSession().getAttribute("requestLoginVO");
//			mav.addObject("loginResponse", hashUsuario);
//			mav.setViewName("ACliente");
			return new  ModelAndView("ACliente", "model", model);
			
		}
				
		
	}
//	
	@PostMapping("/altaCliente")
	public ModelAndView altaCliente(@ModelAttribute("altaCliente") Cliente cliente) {
		ModelAndView mav = new ModelAndView();
//		log.info(user.toString());
//		
//		HashMap<String, Object> hashUsuario = loginService.login(user);	 
//		mav.addObject("loginResponse", hashUsuario);
//		mav.setViewName(hashUsuario.get("vista").toString());
		
		return mav;
		
		
	}

}
