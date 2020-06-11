package mx.uniprotec.inicio.controller;

import javax.servlet.http.HttpServlet;

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

import mx.uniprotec.entidad.modelo.ClienteModelo;
import mx.uniprotec.entidad.modelo.CursoModelo;
import mx.uniprotec.entidad.modelo.InstructorModelo;
import mx.uniprotec.entidad.modelo.ResultVO;
import mx.uniprotec.entidad.modelo.VendedorModelo;
import mx.uniprotec.inicio.entity.Curso;
import mx.uniprotec.inicio.service.IAplicacionService;
import mx.uniprotec.inicio.service.IClienteService;
import mx.uniprotec.inicio.service.ICursoService;
import mx.uniprotec.inicio.service.IInstructorService;
import mx.uniprotec.inicio.service.ILoginService;
import mx.uniprotec.inicio.service.IUsuarioService;
import mx.uniprotec.inicio.service.IVendedorService;

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
	IVendedorService vendedorService;
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
	public ModelAndView acliente(@RequestParam(name="ejecucion", required=false) boolean ejecucion, 
			@RequestParam(name="error", required=false) boolean error,
			ModelMap model) {
		
		model.addAttribute("clienteForm", new ClienteModelo());
		
		if(model.equals(null)) {
			log.info("NULL");
			return new  ModelAndView("login");
		}else {
			log.info("ACliente model Activo");
			
			ResultVO resultVO = (ResultVO)model.get("model");
			model.addAttribute("model", resultVO);
			log.info(model.values().toString());
			ResultVO rs = aplicacionService.consultaRegiones(resultVO.getAccesToken());
			if(rs.getCodigo() != 500) {
				log.info("Consulta REGIONES OK");
				resultVO.setJsonResponseObject(rs.getJsonResponseObject());
//				log.info(model.values().toString());	
				ResultVO rs2 = vendedorService.consultaVendedores(resultVO.getAccesToken());
				if(rs2.getCodigo() != 500) {
					log.info("Consulta VENDEDORES OK");
					JSONObject jsonResponse = resultVO.getJsonResponseObject();
					jsonResponse.put("vendedores", rs2.getJsonResponseObject());
				
					resultVO.setJsonResponseObject(jsonResponse);
					
					ModelAndView mav = new ModelAndView("ACliente", model);
					mav.addObject("create", true);
					log.info(mav.toString());
					mav.addObject("ejecucion", ejecucion);
					mav.addObject("error", error);
					return mav;
						
				}else {
					ModelAndView mav = new ModelAndView("redirect:/ACliente", model);
					log.info("NOK Vendedores");
					mav.addObject("error", error);
					return mav;
				}
					
			}else {
				ModelAndView mav = new ModelAndView("redirect:/ACliente", model);
				log.info("NOK REGIONES");
				mav.addObject("error", error);
				return mav;

			}
		}	
	}
	
//	
	@PostMapping("/altaCliente")
	public ModelAndView altaCliente(@ModelAttribute("clienteForm") ClienteModelo cliente, ModelMap model) {
		log.info("Metodo de alta Cliente");
//		log.info(model.values().toString());
		
		ResultVO resultVO = (ResultVO)model.get("model");
		resultVO  = clienteService.altaCliente(cliente, resultVO.getAccesToken());
		if(resultVO.getCodigo() != 500) {
			log.info(resultVO.toString());
			
			ModelAndView mav = new ModelAndView("redirect:/ACliente" , model);
			mav.addObject("ejecucion", true);
			return mav;
		}else {
			ModelAndView mav = new ModelAndView("redirect:/ACliente", model);
//			mav.addObject("dataEjecutable", resultVO);
			mav.addObject("error", true);
			log.info("NOK AltaCliente");
			return mav;
		}
		
	}
	
	
	@SuppressWarnings("unchecked")
	@GetMapping("/BCliente")
	public ModelAndView bcliente(ModelMap model) {
		log.info("BCliente model Activo");
		
		JSONObject jsonResponse = new JSONObject();
		ResultVO rs2 = new ResultVO();
		ResultVO rs3 = new ResultVO();
		model.addAttribute("clienteForm", new ClienteModelo());
		
		ResultVO resultVO = (ResultVO)model.get("model");
		model.addAttribute("model", resultVO);
		
		ResultVO rs = clienteService.consultaClientes(resultVO.getAccesToken());
		if(rs.getCodigo() != 500) {
			resultVO.setJsonResponseObject(rs.getJsonResponseObject());
			jsonResponse = resultVO.getJsonResponseObject();

			 rs2 = aplicacionService.consultaRegiones(resultVO.getAccesToken());
			if(rs2.getCodigo() != 500) {
				 rs3 = vendedorService.consultaVendedores(resultVO.getAccesToken());
				if(rs3.getCodigo() != 500) {
					log.info(jsonResponse.toJSONString());
					jsonResponse.put("regiones", rs2.getJsonResponseObject());
					jsonResponse.put("vendedores", rs3.getJsonResponseObject());
					
					resultVO.setJsonResponseObject(jsonResponse);
					
					log.info(model.values().toString());
					ModelAndView mav = new ModelAndView("BCliente", model);
					
					return mav;
				}else {
					model.addAttribute("model", rs3);
					ModelAndView mav = new ModelAndView("redirect:/AClienteExito", model);
					return mav;
				}
				
			}else {
				model.addAttribute("model", rs2);
				ModelAndView mav = new ModelAndView("redirect:/AClienteExito", model);
				return mav;
			}
			
		}else {
			model.addAttribute("model", rs);
			ModelAndView mav = new ModelAndView("redirect:/AClienteExito", model);
			return mav;
		}
	}
	
	@PostMapping("/actualizaCliente")
	public ModelAndView actualizaCliente(@ModelAttribute("clienteForm") ClienteModelo cliente, ModelMap model) {
		log.info("ActualizaCliente model Activo");
		model.addAttribute("clienteForm", new ClienteModelo());
			
		ResultVO resultVO = (ResultVO)model.get("model");
		model.addAttribute("model", resultVO);
		if(resultVO.getCodigo() != 500) {
			ResultVO rs = clienteService.edicionCliente(cliente, resultVO.getAccesToken());
			resultVO.setJsonResponseObject(rs.getJsonResponseObject());
			log.info(model.values().toString());
			ModelAndView mav = new ModelAndView("redirect:/inicio", model);
			
			return mav;
		}else {
			ModelAndView mav = new ModelAndView("redirect:/AClienteExito", model);
			return mav;
		}
		
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
			if(resultVO.getCodigo() != 500) {
				ResultVO rs = instructorService.consultaInstructores(resultVO.getAccesToken());
				resultVO.setJsonResponseObject(rs.getJsonResponseObject());
				log.info(model.values().toString());
			
				return new  ModelAndView("ACurso", model);
			}else {
				ModelAndView mav = new ModelAndView("redirect:/AClienteExito", model);
				return mav;
			}
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
		log.info("BCurso model Activo");
		model.addAttribute("cursoForm", new Curso());
		
		ResultVO resultVO = (ResultVO)model.get("model");
		model.addAttribute("model", resultVO);
		if(resultVO.getCodigo() != 500) {
			ResultVO rs = cursoService.consultaCursos(resultVO.getAccesToken());
			resultVO.setJsonResponseObject(rs.getJsonResponseObject());
			
			ResultVO rs2 = instructorService.consultaInstructores(resultVO.getAccesToken());
			if(resultVO.getCodigo() != 500) {
				JSONObject jsonResponse = resultVO.getJsonResponseObject();
				jsonResponse.put("instructor", rs2.getJsonResponseObject());
				resultVO.setJsonResponseObject(jsonResponse);
				
				log.info(model.values().toString());
				ModelAndView mav = new ModelAndView("BCurso", model);
				
				return mav;
			}else {
				model.addAttribute("model", rs2);
				ModelAndView mav = new ModelAndView("redirect:/AClienteExito", model);
				return mav;
			}
			
		}else {
			ModelAndView mav = new ModelAndView("redirect:/AClienteExito", model);
			return mav;
		}
		
		
		}
	
	@PostMapping("/actualizaCurso")
	public ModelAndView actualizaCurso(@ModelAttribute("cursoForm") CursoModelo curso, ModelMap model) {
		log.info("ActualizaCurso model Activo");
		model.addAttribute("cursoForm", new CursoModelo());
			
		ResultVO resultVO = (ResultVO)model.get("model");
		model.addAttribute("model", resultVO);
		if(resultVO.getCodigo() != 500) {
			ResultVO rs = cursoService.edicionCurso(curso, resultVO.getAccesToken());
			if(rs.getCodigo() != 500) {
				resultVO.setJsonResponseObject(rs.getJsonResponseObject());
				log.info(model.values().toString());
				ModelAndView mav = new ModelAndView("redirect:/inicio", model);
				
				return mav;
			}else {
				model.addAttribute("model", rs);
				ModelAndView mav = new ModelAndView("redirect:/AClienteExito", model);
				return mav;
			}
			
		}else {
			ModelAndView mav = new ModelAndView("redirect:/AClienteExito", model);
			return mav;
		}
		
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
			if(resultVO.getCodigo() != 500) {
				ResultVO rs = aplicacionService.consultaRegiones(resultVO.getAccesToken());
				resultVO.setJsonResponseObject(rs.getJsonResponseObject());
				
				ResultVO rs2 = cursoService.consultaCursos(resultVO.getAccesToken());
				if(rs2.getCodigo() != 500) {
					JSONObject jsonResponse = resultVO.getJsonResponseObject();
					jsonResponse.put("cursos", rs2.getJsonResponseObject());
					resultVO.setJsonResponseObject(jsonResponse);
					
					log.info(model.values().toString());
					
					return new  ModelAndView("AInstructor", model);
				}else {
					model.addAttribute("model", rs);
					ModelAndView mav = new ModelAndView("redirect:/AClienteExito", model);
					return mav;
				}

			}else {
				ModelAndView mav = new ModelAndView("redirect:/AClienteExito", model);
				return mav;
			}
		}		
	}
//	
	@PostMapping("/altaInstructor")
	public ModelAndView altaInstructor(@ModelAttribute("instructorForm") InstructorModelo instructor, ModelMap model) {

		log.info("metodo de alta Instructor");
		
		ResultVO resultVO = (ResultVO)model.get("model");
		if(resultVO.getCodigo() != 500) {
			resultVO  = instructorService.altaInstructor(instructor, resultVO.getAccesToken());
			ModelAndView mav = new ModelAndView("redirect:/inicio" );
			return mav;
		}else {
			ModelAndView mav = new ModelAndView("redirect:/AClienteExito", model);
			return mav;
		}
	}
	
	@GetMapping("/BInstructor")
	public ModelAndView BInstructor(ModelMap model) {
		log.info("BInstructor model Activo");
			model.addAttribute("instructorForm", new InstructorModelo());
			
			ResultVO resultVO = (ResultVO)model.get("model");
			model.addAttribute("model", resultVO);
			if(resultVO.getCodigo() != 500) {
				ResultVO rs = instructorService.consultaInstructores(resultVO.getAccesToken());
				if(resultVO.getCodigo() != 500) {
					resultVO.setJsonResponseObject(rs.getJsonResponseObject());
					
					ResultVO rs2 = aplicacionService.consultaRegiones(resultVO.getAccesToken());
					if(rs2.getCodigo() != 500) {
						JSONObject jsonResponse = resultVO.getJsonResponseObject();
						jsonResponse.put("regiones", rs2.getJsonResponseObject());
						
						ResultVO rs3 = cursoService.consultaCursos(resultVO.getAccesToken());
						if(rs2.getCodigo() != 500) {
							resultVO.setJsonResponseObject(rs3.getJsonResponseObject());
							jsonResponse.put("cursos", rs3.getJsonResponseObject());
							
							resultVO.setJsonResponseObject(jsonResponse);
							
							log.info(model.values().toString());
							ModelAndView mav = new ModelAndView("BInstructor", model);
							
							return mav;
						}else {
							model.addAttribute("model", rs3);
							ModelAndView mav = new ModelAndView("redirect:/AClienteExito", model);
							return mav;
						}
//						
					}else {
						model.addAttribute("model", rs2);
						ModelAndView mav = new ModelAndView("redirect:/AClienteExito", model);
						return mav;
					}
					
				}else {
					model.addAttribute("model", rs);
					ModelAndView mav = new ModelAndView("redirect:/AClienteExito", model);
					return mav;
				}
				
			}else {
				ModelAndView mav = new ModelAndView("redirect:/AClienteExito", model);
				return mav;
			}
			
		}

	@PostMapping("/actualizaInstructor")
	public ModelAndView actualizaInstructor(@ModelAttribute("instructorForm") InstructorModelo instructor, ModelMap model) {
		log.info("ActualizaInstructor model Activo");
			model.addAttribute("instructorForm", new InstructorModelo());
				
			ResultVO resultVO = (ResultVO)model.get("model");
			model.addAttribute("model", resultVO);
			if(resultVO.getCodigo() != 500) {
				ResultVO rs = instructorService.edicionInstructor(instructor, resultVO.getAccesToken());
				resultVO.setJsonResponseObject(rs.getJsonResponseObject());
				log.info(model.values().toString());
				ModelAndView mav = new ModelAndView("redirect:/inicio", model);
				
				return mav;
			}else {
				ModelAndView mav = new ModelAndView("redirect:/AClienteExito", model);
				return mav;
			}
		}
	

	
	/*
	 * CRUD VENDEDOR
	 * 
	 */
	@GetMapping("/AVendedor")
	public ModelAndView AVendedor(ModelMap model) {
		
		model.addAttribute("vendedorForm", new VendedorModelo());
		
		if(model.equals(null)) {
			log.info("NULL");
			return new  ModelAndView("login");
		}else {
			log.info("AVendedor model Activo");
			
			ResultVO resultVO = (ResultVO)model.get("model");
			model.addAttribute("model", resultVO);
			if(resultVO.getCodigo() != 500) {
				log.info(model.values().toString());
				
				return new  ModelAndView("AVendedor", model);	
			}else {
				ModelAndView mav = new ModelAndView("redirect:/AClienteExito", model);
				return mav;
			}

		}		
		
	}
//	
	@PostMapping("/altaVendedor")
	public ModelAndView altaVendedor(@ModelAttribute("vendedorForm") VendedorModelo vendedor, ModelMap model) {

		log.info("metodo de alta Vendedor");
		
		ResultVO resultVO = (ResultVO)model.get("model");
		resultVO  = vendedorService.altaVendedor(vendedor, resultVO.getAccesToken());
		
		if(resultVO.getCodigo() != 500) {
			ModelAndView mav = new ModelAndView("redirect:/inicio" );
			return mav;
		}else {
			ModelAndView mav = new ModelAndView("redirect:/AClienteExito", model);
			return mav;
		}
	}
	
	@GetMapping("/BVendedor")
	public ModelAndView BVendedor(ModelMap model) {
		log.info("BVendedor model Activo");
			model.addAttribute("vendedorForm", new VendedorModelo());
			
			ResultVO resultVO = (ResultVO)model.get("model");
			model.addAttribute("model", resultVO);
			if(resultVO.getCodigo() != 500) {
				ResultVO rs = vendedorService.consultaVendedores(resultVO.getAccesToken());
				resultVO.setJsonResponseObject(rs.getJsonResponseObject());
				
				log.info(model.values().toString());
				ModelAndView mav = new ModelAndView("BVendedor", model);
				
				return mav;
			}else {
				ModelAndView mav = new ModelAndView("redirect:/AClienteExito", model);
				return mav;
			}
		}

	@PostMapping("/actualizaVendedor")
	public ModelAndView actualizaVendedor(@ModelAttribute("vendedorForm") VendedorModelo vendedor, ModelMap model) {
		log.info("ActualizaVendedor model Activo");
			model.addAttribute("vendedorForm", new VendedorModelo());
				
			ResultVO resultVO = (ResultVO)model.get("model");
			model.addAttribute("model", resultVO);
			if(resultVO.getCodigo() != 500) {
				ResultVO rs = vendedorService.edicionVendedor(vendedor, resultVO.getAccesToken());
				resultVO.setJsonResponseObject(rs.getJsonResponseObject());
				log.info(model.values().toString());
				ModelAndView mav = new ModelAndView("redirect:/inicio", model);
				
				return mav;
			}else {
				ModelAndView mav = new ModelAndView("redirect:/AClienteExito", model);
				return mav;
			}
		}

	//Fin de clase
}
