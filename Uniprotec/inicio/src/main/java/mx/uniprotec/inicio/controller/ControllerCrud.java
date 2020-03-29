package mx.uniprotec.inicio.controller;

import java.util.Map;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import mx.uniprotec.inicio.entity.Cliente;
import mx.uniprotec.inicio.entity.ResultVO;
import mx.uniprotec.inicio.entity.User;
import mx.uniprotec.inicio.entity.Usuario;
import mx.uniprotec.inicio.service.IClienteService;
import mx.uniprotec.inicio.service.ICursoService;
import mx.uniprotec.inicio.service.IInstructorService;
import mx.uniprotec.inicio.service.ILoginService;
import mx.uniprotec.inicio.service.IUsuarioService;

@Controller
@SessionAttributes ("model")
public class ControllerCrud extends HttpServlet {
	
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

private static Logger log = LoggerFactory.getLogger(ControllerCrud.class);
	
	@Autowired
	ILoginService loginService;
	@Autowired
	IClienteService clienteService;
	@Autowired
	IInstructorService instructorService;
	@Autowired
	ICursoService cursoService;
	@Autowired
	IUsuarioService usuarioService;
	
	@Autowired
	ResultVO resultVO;
	
	
	

	
	/*
	 * CRUD CLIENTE
	 * 
	 */
	@GetMapping("/ACliente")
	public ModelAndView acliente(ModelMap model,
			HttpServletRequest request,
			HttpServletResponse response) {
		model.addAttribute("clienteForm", new Cliente());
		if(model.equals(null)) {
			log.info("NULL");
			return new  ModelAndView("login");
		}else {
			log.info("ACliente model Activo");
			ResultVO resultVO= (ResultVO) request.getSession().getAttribute("resultVO");
			return new  ModelAndView("ACliente", "model", resultVO);	
		}		
		
	}
//	
	@PostMapping("/altaCliente")
	public ModelAndView altaCliente(@ModelAttribute("clienteForm") Cliente cliente) {
		log.info("metodo de alta Cliente");
		resultVO  = clienteService.altaCliente(cliente);
		log.info(resultVO.toString());
		
		ModelAndView mav = new ModelAndView("ACliente" );
		return mav;
	}
	
	
	@GetMapping("/BCliente")
	public ModelAndView bcliente(ModelMap model,
			HttpServletRequest request,
			HttpServletResponse response) {
		
		ModelAndView mav = new ModelAndView("index");
		
		JSONObject jsonLogin = new JSONObject((Map) model.get("model"));
		log.info(jsonLogin.toJSONString());

		return mav;
		}
				
		
	

}
