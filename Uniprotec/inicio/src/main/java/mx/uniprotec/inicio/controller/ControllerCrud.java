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

import mx.uniprotec.entidad.modelo.Cliente;
import mx.uniprotec.entidad.modelo.CursoModelo;
import mx.uniprotec.entidad.modelo.Instructor;
import mx.uniprotec.entidad.modelo.ResultVO;
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
	
//	@Autowired
	ResultVO resultVO = new  ResultVO ();
	

	@GetMapping("/Asignacion")
	public ModelAndView Asignacion(ModelMap model) {

		if(model.equals(null)) {
			log.info("NULL");
			return new  ModelAndView("login");
		}else {
			log.info("ACliente model Activo");
//			log.info(model.values().toString());
			return new  ModelAndView("Asignacion",  model);	
		}		

		}

	

	
	/*
	 * CRUD CLIENTE
	 * 
	 */
	@GetMapping("/ACliente")
	public ModelAndView acliente(ModelMap model) {
		
		model.addAttribute("clienteForm", new Cliente());
		
		if(model.equals(null)) {
			log.info("NULL");
			return new  ModelAndView("login");
		}else {
			log.info("ACliente model Activo");
//			log.info(model.values().toString());
			return new  ModelAndView("ACliente",  model);	
		}		
		
	}
//	
	@PostMapping("/altaCliente")
	public ModelAndView altaCliente(@ModelAttribute("clienteForm") Cliente cliente, ModelMap model) {
		log.info("Metodo de alta Cliente");
//		log.info(model.values().toString());
		
		ResultVO resultVO = (ResultVO)model.get("model");
		
		resultVO  = clienteService.altaCliente(cliente, resultVO.getAccesToken());
		log.info(resultVO.toString());
		
		ModelAndView mav = new ModelAndView("ACliente" );
		return mav;
	}
	
	
	@GetMapping("/BCliente")
	public ModelAndView bcliente(ModelMap model) {
		
		model.addAttribute("clienteForm", new Cliente());
//		log.info(model.values().toString());
		
		ResultVO resultVO = (ResultVO)model.get("model");
//		log.info(resultVO.toString());
		model.addAttribute("model", resultVO);
		
//		ResultVO rs = new ResultVO();
		ResultVO rs = clienteService.consultaClientes(resultVO.getAccesToken());
		resultVO.setJsonResponseObject(rs.getJsonResponseObject());
//		model.addAttribute("result", rs.getJsonResponse());
		log.info(model.values().toString());
		ModelAndView mav = new ModelAndView("BCliente", model);
		
		return mav;
		}
				
		

	/*
	 * CRUD CURSO
	 * 
	 */
	@GetMapping("/ACurso")
	public ModelAndView acurso(ModelMap model) {
		
		model.addAttribute("cursoForm", new CursoModelo());
		
		if(model.equals(null)) {
			log.info("NULL");
			return new  ModelAndView("login");
		}else {
			log.info("ACurso model Activo");
		
			return new  ModelAndView("ACurso", model);	
		}		
		
	}
//	
	@PostMapping("/altaCurso")
	public ModelAndView altaCurso(@ModelAttribute("cursoForm") CursoModelo curso , ModelMap model) {
		log.info("metodo de alta Curso");

		ResultVO resultVO = (ResultVO)model.get("model");
		
		resultVO  = cursoService.altaCurso(curso, resultVO.getAccesToken());
		ModelAndView mav = new ModelAndView("ACurso" );
		return mav;
	}
	
	@GetMapping("/BCurso")
	public ModelAndView bcurso(ModelMap model,
			HttpServletRequest request,
			HttpServletResponse response) {
		
		
		ModelAndView mav = new ModelAndView("index");
		
		JSONObject jsonLogin = new JSONObject((Map) model.get("model"));
		log.info(jsonLogin.toJSONString());

		return mav;
		}

	
	/*
	 * CRUD INSTRUCTOR
	 * 
	 */
	@GetMapping("/AInstructor")
	public ModelAndView AInstructor(ModelMap model) {
		
		model.addAttribute("instructorForm", new Instructor());
		
		if(model.equals(null)) {
			log.info("NULL");
			return new  ModelAndView("login");
		}else {
			log.info("AInstructor model Activo");
			return new  ModelAndView("AInstructor", model);	
		}		
		
	}
//	
	@PostMapping("/altaInstructor")
	public ModelAndView altaInstructor(@ModelAttribute("instructorForm") Instructor instructor, ModelMap model) {

		log.info("metodo de alta Instructor");
		
		ResultVO resultVO = (ResultVO)model.get("model");
		
		resultVO  = instructorService.altaInstructor(instructor, resultVO.getAccesToken());
		ModelAndView mav = new ModelAndView("AInstructor" );
		return mav;
	}
	
	@GetMapping("/BInstructor")
	public ModelAndView BInstructor(ModelMap model,
			HttpServletRequest request,
			HttpServletResponse response) {
		
		ModelAndView mav = new ModelAndView("index");
		
		JSONObject jsonLogin = new JSONObject((Map) model.get("model"));
		log.info(jsonLogin.toJSONString());

		return mav;
		}

	

}
