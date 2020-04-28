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

import mx.uniprotec.entidad.modelo.ClienteModelo;
import mx.uniprotec.entidad.modelo.CursoModelo;
import mx.uniprotec.entidad.modelo.InstructorModelo;
import mx.uniprotec.entidad.modelo.ResultVO;
import mx.uniprotec.inicio.entity.Curso;
import mx.uniprotec.inicio.service.IAplicacionService;
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
	IAplicacionService aplicacionService;
	
//	@Autowired
	ResultVO resultVO = new  ResultVO ();
	

	

	
	/*
	 * CRUD CLIENTE
	 * 
	 */
	@GetMapping("/ACliente")
	public ModelAndView acliente(ModelMap model) {
		
		model.addAttribute("clienteForm", new ClienteModelo());
		
		if(model.equals(null)) {
			log.info("NULL");
			return new  ModelAndView("login");
		}else {
			log.info("ACliente model Activo");

			ResultVO resultVO = (ResultVO)model.get("model");
			model.addAttribute("model", resultVO);
			
			ResultVO rs = aplicacionService.consultaRegiones(resultVO.getAccesToken());
			resultVO.setJsonResponseObject(rs.getJsonResponseObject());
			log.info(model.values().toString());
			
			return new  ModelAndView("ACliente",  model);
		}		
		
	}
//	
	@PostMapping("/altaCliente")
	public ModelAndView altaCliente(@ModelAttribute("clienteForm") ClienteModelo cliente, ModelMap model) {
		log.info("Metodo de alta Cliente");
//		log.info(model.values().toString());
		
		ResultVO resultVO = (ResultVO)model.get("model");
		
		resultVO  = clienteService.altaCliente(cliente, resultVO.getAccesToken());
		log.info(resultVO.toString());
		
		ModelAndView mav = new ModelAndView("redirect:/inicio" );
		return mav;
	}
	
	
	@SuppressWarnings("unchecked")
	@GetMapping("/BCliente")
	public ModelAndView bcliente(ModelMap model) {
		
		model.addAttribute("clienteForm", new ClienteModelo());
		
		ResultVO resultVO = (ResultVO)model.get("model");
		model.addAttribute("model", resultVO);
		
		ResultVO rs = clienteService.consultaClientes(resultVO.getAccesToken());
		resultVO.setJsonResponseObject(rs.getJsonResponseObject());

		ResultVO rs2 = aplicacionService.consultaRegiones(resultVO.getAccesToken());
		JSONObject jsonResponse = resultVO.getJsonResponseObject();
		jsonResponse.put("regiones", rs2.getJsonResponseObject());
		
		resultVO.setJsonResponseObject(jsonResponse);
		
		log.info(model.values().toString());
		ModelAndView mav = new ModelAndView("BCliente", model);
		
		return mav;
		}
	
	@PostMapping("/actualizaCliente")
	public ModelAndView actualizaCliente(@ModelAttribute("clienteForm") ClienteModelo cliente, ModelMap model) {
		
		model.addAttribute("clienteForm", new ClienteModelo());
			
		ResultVO resultVO = (ResultVO)model.get("model");
		model.addAttribute("model", resultVO);
		
		ResultVO rs = clienteService.edicionCliente(cliente, resultVO.getAccesToken());
		resultVO.setJsonResponseObject(rs.getJsonResponseObject());
		log.info(model.values().toString());
		ModelAndView mav = new ModelAndView("redirect:/inicio", model);
		
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
			
			ResultVO resultVO = (ResultVO)model.get("model");
			model.addAttribute("model", resultVO);
			
			ResultVO rs = instructorService.consultaInstructores(resultVO.getAccesToken());
			resultVO.setJsonResponseObject(rs.getJsonResponseObject());
			log.info(model.values().toString());
		
			return new  ModelAndView("ACurso", model);	
		}		
		
	}
//	
	@PostMapping("/altaCurso")
	public ModelAndView altaCurso(@ModelAttribute("cursoForm") CursoModelo curso , ModelMap model) {
		log.info("metodo de alta Curso");

		ResultVO resultVO = (ResultVO)model.get("model");
		
		resultVO  = cursoService.altaCurso(curso, resultVO.getAccesToken());
		ModelAndView mav = new ModelAndView("redirect:/inicio" );
		return mav;
	}
	
	@SuppressWarnings("unchecked")
	@GetMapping("/BCurso")
	public ModelAndView bCurso(ModelMap model) {
		
		model.addAttribute("cursoForm", new Curso());
		
		ResultVO resultVO = (ResultVO)model.get("model");
		model.addAttribute("model", resultVO);
		
		ResultVO rs = cursoService.consultaCursos(resultVO.getAccesToken());
		resultVO.setJsonResponseObject(rs.getJsonResponseObject());
		
		ResultVO rs2 = instructorService.consultaInstructores(resultVO.getAccesToken());
		JSONObject jsonResponse = resultVO.getJsonResponseObject();
		jsonResponse.put("instructor", rs2.getJsonResponseObject());
		resultVO.setJsonResponseObject(jsonResponse);
		
		log.info(model.values().toString());
		ModelAndView mav = new ModelAndView("BCurso", model);
		
		return mav;
		}
	
	@PostMapping("/actualizaCurso")
	public ModelAndView actualizaCurso(@ModelAttribute("cursoForm") CursoModelo curso, ModelMap model) {
		
		model.addAttribute("cursoForm", new CursoModelo());
			
		ResultVO resultVO = (ResultVO)model.get("model");
		model.addAttribute("model", resultVO);
		
		ResultVO rs = cursoService.edicionCurso(curso, resultVO.getAccesToken());
		resultVO.setJsonResponseObject(rs.getJsonResponseObject());
		log.info(model.values().toString());
		ModelAndView mav = new ModelAndView("redirect:/inicio", model);
		
		return mav;
		}


	
	/*
	 * CRUD INSTRUCTOR
	 * 
	 */
	@GetMapping("/AInstructor")
	public ModelAndView AInstructor(ModelMap model) {
		
		model.addAttribute("instructorForm", new InstructorModelo());
		
		if(model.equals(null)) {
			log.info("NULL");
			return new  ModelAndView("login");
		}else {
			log.info("AInstructor model Activo");
			
			ResultVO resultVO = (ResultVO)model.get("model");
			model.addAttribute("model", resultVO);
			
			ResultVO rs = aplicacionService.consultaRegiones(resultVO.getAccesToken());
			resultVO.setJsonResponseObject(rs.getJsonResponseObject());
			
			ResultVO rs2 = cursoService.consultaCursos(resultVO.getAccesToken());
			JSONObject jsonResponse = resultVO.getJsonResponseObject();
			jsonResponse.put("cursos", rs2.getJsonResponseObject());
			resultVO.setJsonResponseObject(jsonResponse);
			
			log.info(model.values().toString());
			
			return new  ModelAndView("AInstructor", model);	
		}		
		
	}
//	
	@PostMapping("/altaInstructor")
	public ModelAndView altaInstructor(@ModelAttribute("instructorForm") InstructorModelo instructor, ModelMap model) {

		log.info("metodo de alta Instructor");
		
		ResultVO resultVO = (ResultVO)model.get("model");
		
		resultVO  = instructorService.altaInstructor(instructor, resultVO.getAccesToken());
		ModelAndView mav = new ModelAndView("redirect:/inicio" );
		return mav;
	}
	
	@GetMapping("/BInstructor")
	public ModelAndView BInstructor(ModelMap model) {
			
			model.addAttribute("instructorForm", new InstructorModelo());
			
			ResultVO resultVO = (ResultVO)model.get("model");
			model.addAttribute("model", resultVO);
			
			ResultVO rs = instructorService.consultaInstructores(resultVO.getAccesToken());
			resultVO.setJsonResponseObject(rs.getJsonResponseObject());
			
			ResultVO rs2 = aplicacionService.consultaRegiones(resultVO.getAccesToken());
			JSONObject jsonResponse = resultVO.getJsonResponseObject();
			jsonResponse.put("regiones", rs2.getJsonResponseObject());
			
			ResultVO rs3 = cursoService.consultaCursos(resultVO.getAccesToken());
//			resultVO.setJsonResponseObject(rs3.getJsonResponseObject());
			jsonResponse.put("cursos", rs3.getJsonResponseObject());
			
			resultVO.setJsonResponseObject(jsonResponse);
			
			log.info(model.values().toString());
			ModelAndView mav = new ModelAndView("BInstructor", model);
			
			return mav;
		}

	@PostMapping("/actualizaInstructor")
	public ModelAndView actualizaInstructor(@ModelAttribute("instructorForm") InstructorModelo instructor, ModelMap model) {
			
			model.addAttribute("instructorForm", new InstructorModelo());
				
			ResultVO resultVO = (ResultVO)model.get("model");
			model.addAttribute("model", resultVO);
			
			ResultVO rs = instructorService.edicionInstructor(instructor, resultVO.getAccesToken());
			resultVO.setJsonResponseObject(rs.getJsonResponseObject());
			log.info(model.values().toString());
			ModelAndView mav = new ModelAndView("redirect:/inicio", model);
			
			return mav;
			}
	

}
