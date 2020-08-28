package mx.uniprotec.inicio.controller;

import java.time.LocalDateTime;
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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import mx.uniprotec.entidad.modelo.AsignacionModelo;
import mx.uniprotec.entidad.modelo.MensajeModelo;
import mx.uniprotec.entidad.modelo.ResultVO;
import mx.uniprotec.entidad.modelo.User;
import mx.uniprotec.entidad.modelo.UsuarioModelo;
import mx.uniprotec.inicio.service.IAplicacionService;
import mx.uniprotec.inicio.service.IAsignacionService;
import mx.uniprotec.inicio.service.IClienteService;
import mx.uniprotec.inicio.service.ILoginService;
import mx.uniprotec.inicio.service.IUsuarioService;

@CrossOrigin(origins = { "*" })
@Controller
@SessionAttributes ("model")
//@Scope("prototype")
public class ControllerInicio extends HttpServlet{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static Logger log = LoggerFactory.getLogger(ControllerInicio.class);
	
	@Autowired
	ILoginService loginService;
	@Autowired
	IAplicacionService aplicacionService;
	@Autowired
	IClienteService clienteService;
	@Autowired
	IAsignacionService asignacionService;
	@Autowired
	IUsuarioService usuarioService;
	
	
//	ResultVO resultVO = new ResultVO();
	UsuarioModelo usuario = new UsuarioModelo();
	
	
		
//		
		/*
		 *     USUARIO 
		 */
	
	@GetMapping("/")
//	@Scope("prototype")
	public ModelAndView inicio(@RequestParam(name="login", required=false) Boolean loginIn) {
		ModelAndView mav = new ModelAndView("login");
		mav.addObject("userLogin", new User());
		mav.addObject("loginForm", loginIn);
		if(loginIn != null)
			log.info(loginIn.toString());
		return mav;
	}
	
	
		@PostMapping("/loginInit")
//		@Scope("prototype")
		public ModelAndView loginInit(@ModelAttribute("userLogin") User user, HttpServletRequest request, HttpServletResponse response) {
			
			ModelAndView mav = new ModelAndView();
			ModelMap model = new ModelMap();
			ResultVO resultVO = new ResultVO();
			log.info(user.toString());
			
//			HttpSession misession = request.getSession(true);
			resultVO = loginService.login(user);
//			misession.setAttribute("model",resultVO);
			

			log.info(resultVO.toString());
			if(resultVO.getCodigo() != 500) {
				log.info("Bienvenido");
				mav.setViewName(resultVO.getResponse());
				mav.addObject("model", resultVO);
				mav.addObject("mensajeItem", new MensajeModelo());
				return mav;
//				return new ModelAndView(resultVO.getResponse(), "model", resultVO);
			}else {
				log.info("Credenciales inválidas");
				mav = new ModelAndView("redirect:/", model);
				mav.addObject("login", true);
				return mav;
			}

		}
		
		
		
		
		@GetMapping("/inicio")
//		@Scope("prototype")
		public ModelAndView inicio(ModelMap model) {

			if(model.equals(null)) {
				log.info("NULL");
				return new  ModelAndView("login");
			}else {
				log.info("Inicio model Activo");
				ResultVO resultVO = (ResultVO)model.get("model");
				ModelAndView mav = new  ModelAndView(resultVO.getResponse(),  model);
				mav.addObject("mensajeItem", new MensajeModelo());
				log.info(resultVO.getResponse());
						return 	mav;
			}		

		}
				
		@GetMapping("/OffSession")
//		@Scope("prototype")
		public ModelAndView offSession() {
				return new ModelAndView("redirect:/");	
		}
		
		@GetMapping("/CUsuario")
		public ModelAndView CUsuario(@RequestParam(name="ejecucion", required=false) boolean ejecucion, 
				@RequestParam(name="error", required=false) boolean error,
				ModelMap model) {
			
			
			if(model.equals(null)) {
				log.info("NULL");
				return new  ModelAndView("login");
			}else {
				log.info("CUsuario model Activo");
				
				ResultVO resultVO = (ResultVO)model.get("model");
				
				JSONObject jsonObject = (JSONObject) resultVO.getJsonResponse();
				JSONObject jsonUsuario = new JSONObject((Map) jsonObject.get("user"));
				
				ResultVO rs = usuarioService.consultaUsuario(resultVO.getAccesToken(), jsonUsuario.get("id").toString());
				JSONObject jsonObject2 = (JSONObject) rs.getJsonResponseObject();
//				log.info(jsonObject2.toJSONString());
				JSONObject jsonUsuario2 = new JSONObject((Map) jsonObject2.get("usuario"));
				resultVO.setJsonResponseObject(rs.getJsonResponseObject());
				
				UsuarioModelo usuario = new UsuarioModelo(Long.valueOf(jsonUsuario2.get("idUsuario").toString()),
						jsonUsuario2.get("usernameUsuario").toString(),
						jsonUsuario2.get("nombreUsuario").toString(),
						jsonUsuario2.get("emailUsuario").toString(),
						jsonUsuario2.get("notaUsuario").toString(),
						jsonUsuario2.get("perfilUsuario").toString());
//				usuario.setPasswordUsuarioOld(jsonUsuario2.get("passwordUsuario").toString());
				log.info(usuario.toString());
				model.addAttribute("usuarioForm", usuario);
				
				
				ModelAndView mav = new  ModelAndView("CUsuario", model );
				model.addAttribute("model", resultVO);
				mav.addObject("error", error);
				mav.addObject("ejecucion", ejecucion);
				return mav;
			}	
		}
		
		@PostMapping("/actualizaUsuarioPass")
		public ModelAndView actualizaUsuarioPass(@ModelAttribute("usuarioForm") UsuarioModelo usuario, ModelMap model) {
			log.info("ActualizaUsuarioPass model Activo");
			log.info(usuario.toString());
				model.addAttribute("usuarioForm", new UsuarioModelo());
					
				ResultVO resultVO = (ResultVO)model.get("model");
				model.addAttribute("model", resultVO);
				
				ResultVO rs = usuarioService.edicionUsuario(usuario, resultVO.getAccesToken());
				ModelAndView mav = new ModelAndView("redirect:/CUsuario", model);
				if(rs.getCodigo() != 500) {
					resultVO.setJsonResponseObject(rs.getJsonResponseObject());
					mav.addObject("ejecucion", true);
				}else {
					mav.addObject("error", true);		
				}
				return mav;
			}
		
		
		/*
		 *   ASIGNACION
		 */

		
		
		@GetMapping("/AAsignacion")
		public ModelAndView asignacion(ModelMap model) {
			log.info(model.toString());
			model.addAttribute("asignacionForm", new AsignacionModelo());
			model.addAttribute("asignaForm", new AsignacionModelo());

			if(model.equals(null)) {
				log.info("NULL");
				return new  ModelAndView("login");
			}else {
				log.info("Asignacion model Activo");
				ResultVO resultVO = (ResultVO)model.get("model");			
				ResultVO rs = aplicacionService.consultaData(resultVO);
				resultVO.setJsonResponseObject(rs.getJsonResponseObject());
				
				model.addAttribute("model", resultVO);
				log.info(rs.toString());
				ModelAndView mav = new  ModelAndView("Asignacion",  model);
				log.info(mav.toString());
				if(rs.getCodigo() != 500) {
					return mav;
				}else {
					mav.addObject("consulta", true);
					log.info("NOK Asignacion");
					return mav;	
				}
			}		
		}
		
		@PostMapping("/altaAsignacion")
		public ModelAndView altaAsignacion(@ModelAttribute("asignacionForm") AsignacionModelo asignacion, ModelMap model) {
			log.info("Metodo de alta Asignacion");
			log.info(asignacion.toString());
			
			ResultVO resultVO = (ResultVO)model.get("model");
			log.info(resultVO.getJsonResponse().toJSONString());
			JSONObject jsonObject = (JSONObject) resultVO.getJsonResponse();
			JSONObject jsonUsuario = new JSONObject((Map) jsonObject.get("user"));
			Long idUsuario = Long.valueOf( jsonUsuario.get("id").toString());
			
			resultVO  = asignacionService.altaAsignacion(asignacion, resultVO.getAccesToken(), idUsuario );
			ModelAndView mav = new ModelAndView("redirect:/CAsignacion" , model);
			if(resultVO.getCodigo() != 500) {
//				log.info(resultVO.toString());
				mav.addObject("ejecucion", true);
				return mav;
			}else {
				mav.addObject("error", true);
				log.info("NOK AltaCliente");
				return mav;
			}
		}
		
		@PostMapping("/BAsignacion")
		public ModelAndView BAsignacion(@ModelAttribute("asignacionItem") AsignacionModelo asignacion, ModelMap model) {
			model.addAttribute("asignacionItem", asignacion);
			log.info(asignacion.toString());
			if(model.equals(null)) {
				log.info("NULL");
				return new  ModelAndView("login");
			}else {
				log.info("Edicion Asignacion model Activo");
				ResultVO resultVO = (ResultVO)model.get("model");			
				ResultVO rs = aplicacionService.consultaData(resultVO);
				model.addAttribute("model", rs);
				ModelAndView mav = new  ModelAndView("BAsignacion",  model);
				if(rs.getCodigo() != 500) {					
					return mav;
				}else {
					mav.addObject("consulta", true);
					log.info("NOK AltaCliente");
					return mav;	
				}
					
			}		
				
		}
		
		
		
		@GetMapping("/CAsignacion")
		public ModelAndView consultaAsignacion(@RequestParam(name="ejecucion", required=false) boolean ejecucion,
				@RequestParam(name="ejecucion2", required=false) boolean ejecucion2,
				@RequestParam(name="error", required=false) boolean error,
				ModelMap model) {
				log.info("Calendario model Activo");
				model.addAttribute("asignacionItem", new AsignacionModelo());
				
				ResultVO resultVO = (ResultVO)model.get("model");
				model.addAttribute("model", resultVO);
				
				ResultVO rs = asignacionService.consultaAsignacion(resultVO.getAccesToken());
				resultVO.setJsonResponseObject(rs.getJsonResponseObject());
				ModelAndView mav = new ModelAndView("CAsignacion", model);
				if(rs.getCodigo() != 500) {
					mav.addObject("ejecucion", ejecucion);
					mav.addObject("ejecucion2", ejecucion2);
					mav.addObject("error", error);
					return mav;
				}else {
					mav.addObject("consulta", true);
					log.info("NOK ConsultaAsignacion");
					return mav;
				}
			}
		
		@PostMapping("/actualizaAsignacion")
		public ModelAndView actualizaAsignacion(@ModelAttribute("asignacionItem") AsignacionModelo asignacion, ModelMap model) {
			log.info("Actualiza Asignacion model Activo");
			log.info(asignacion.toString());
			ResultVO resultVO = (ResultVO)model.get("model");
			model.addAttribute("model", resultVO);

			ResultVO rs = asignacionService.edicionAsignacion(asignacion, resultVO.getAccesToken(), asignacion.getStatusAsignacion());
			ModelAndView mav = new ModelAndView("redirect:/CAsignacion", model);
			if(rs.getCodigo() != 500) {
				resultVO.setJsonResponseObject(rs.getJsonResponseObject());
				mav.addObject("ejecucion2", true);
			}else {
				mav.addObject("error", true);
				log.info("NOK AltaCliente");
			}
			return mav;			
		}


		
		@GetMapping("/CEntregable")
		public ModelAndView consultaEntregable(@RequestParam(name="ejecucion", required=false) boolean ejecucion,
				@RequestParam(name="ejecucion2", required=false) boolean ejecucion2,
				@RequestParam(name="error", required=false) boolean error,
				ModelMap model) {
				log.info("Entregable model Activo");
				model.addAttribute("entregableItem", new AsignacionModelo());
				
				ResultVO resultVO = (ResultVO)model.get("model");
				model.addAttribute("model", resultVO);
				
				ResultVO rs = asignacionService.consultaAsignacion(resultVO.getAccesToken());
				resultVO.setJsonResponseObject(rs.getJsonResponseObject());
				ModelAndView mav = new ModelAndView("CEntregable", model);
				if(rs.getCodigo() != 500) {
					mav.addObject("ejecucion", ejecucion);
					mav.addObject("ejecucion2", ejecucion2);
					mav.addObject("error", error);
					return mav;
				}else {
					mav.addObject("consulta", true);
					log.info("NOK ConsultaAsignacion");
					return mav;
				}
			}
		
		
		@GetMapping("/CAsignacionI")
		public ModelAndView consultaAsignacionInstructor(@RequestParam(name="ejecucion", required=false) boolean ejecucion,
				@RequestParam(name="ejecucion2", required=false) boolean ejecucion2,
				@RequestParam(name="error", required=false) boolean error,
				ModelMap model) {
				log.info("Calendario Instructor model Activo");
				model.addAttribute("asignacionItem", new AsignacionModelo());
				
				ResultVO resultVO = (ResultVO)model.get("model");
				model.addAttribute("model", resultVO);
				
				ResultVO rs = asignacionService.consultaAsignacion(resultVO.getAccesToken());
				resultVO.setJsonResponseObject(rs.getJsonResponseObject());
				ModelAndView mav = new ModelAndView("CAsignacionI", model);
				if(rs.getCodigo() != 500) {
					mav.addObject("ejecucion", ejecucion);
					mav.addObject("ejecucion2", ejecucion2);
					mav.addObject("error", error);
					return mav;
				}else {
					mav.addObject("consulta", true);
					log.info("NOK ConsultaAsignacionInstructor");
					return mav;
				}
			}
		

		
		@PostMapping("/BAsignacionI")
		public ModelAndView BAsignacionInstructor(@ModelAttribute("asignacionItem") AsignacionModelo asignacion, ModelMap model) {
			model.addAttribute("asignacionItem", asignacion);
			if(model.equals(null)) {
				log.info("NULL");
				return new  ModelAndView("login");
			}else {
				log.info("Edicion Asignacion model Activo");
				ResultVO resultVO = (ResultVO)model.get("model");			
				ResultVO rs = clienteService.consultaCliente(resultVO.getAccesToken(), asignacion.getIdClienteAsignacion());
				resultVO.setJsonResponseObject(rs.getJsonResponse());
				model.addAttribute("model", resultVO);
				log.info(resultVO.toString());
				ModelAndView mav = new  ModelAndView("BAsignacionI",  model);
				if(rs.getCodigo() != 500) {					
					return mav;
				}else {
					mav.addObject("consulta", true);
					log.info("NOK AltaCliente");
					return mav;	
				}
			}		
		}
		
		@PostMapping("/actualizaAsignacionI")
		public ModelAndView actualizaAsignacionI(@ModelAttribute("asignacionItem") AsignacionModelo asignacion, ModelMap model) {
			log.info("Actualiza Asignacion Instructor model Activo");
			ResultVO resultVO = (ResultVO)model.get("model");
			model.addAttribute("model", resultVO);
			log.info(asignacion.toString());
			ModelAndView mav=null;
			ResultVO rs = asignacionService.edicionAsignacion(asignacion, resultVO.getAccesToken(), asignacion.getStatusAsignacion());
			if(asignacion.getStatusAsignacion().equals("Confirmado Instructor") || asignacion.getStatusAsignacion().equals("Curso Editado") || asignacion.getStatusAsignacion().equals("Curso Completado") || asignacion.getStatusAsignacion().equals("Curso Cancelado")) {
				mav = new ModelAndView("redirect:/CAsignacionI", model);
			}else {
				mav = new ModelAndView("redirect:/CEntregableI", model);
			}
			
			if(asignacion.getStatusAsignacion().equals("Entregables Validado") || asignacion.getStatusAsignacion().equals("Entregable Enviado") ) {
				mav = new ModelAndView("redirect:/CEntregable", model);
			}
			
			
			if(rs.getCodigo() != 500) {
				resultVO.setJsonResponseObject(rs.getJsonResponseObject());
				mav.addObject("ejecucion2", true);
			}else {
				mav.addObject("error", true);
				log.info("NOK AltaCliente");
			}
			return mav;			
		}
		

		@GetMapping("/CEntregableI")
		public ModelAndView consultaEntregableI(@RequestParam(name="ejecucion", required=false) boolean ejecucion,
				@RequestParam(name="ejecucion2", required=false) boolean ejecucion2,
				@RequestParam(name="error", required=false) boolean error,
				ModelMap model) {
				log.info("Entregable model Activo");
				model.addAttribute("entregableItem", new AsignacionModelo());
				
				ResultVO resultVO = (ResultVO)model.get("model");
				model.addAttribute("model", resultVO);
				
				ResultVO rs = asignacionService.consultaAsignacion(resultVO.getAccesToken());
				resultVO.setJsonResponseObject(rs.getJsonResponseObject());
				ModelAndView mav = new ModelAndView("CEntregableI", model);
				if(rs.getCodigo() != 500) {
					mav.addObject("ejecucion", ejecucion);
					mav.addObject("ejecucion2", ejecucion2);
					mav.addObject("error", error);
					return mav;
				}else {
					mav.addObject("consulta", true);
					log.info("NOK ConsultaAsignacion");
					return mav;
				}
			}
		
		
		@GetMapping("/CAsignacionIC/{idAsignacion}/{idInstructor}")
		public ModelAndView consultaAsignacionInstructorCorreo(
				@RequestParam(name="ejecucion2", required=false) boolean ejecucion2,
				@RequestParam(name="error", required=false) boolean error,
				@PathVariable String idAsignacion, @PathVariable String idInstructor) {
				log.info("Consulta Instructor Correo model Activo");
				ModelMap model =new ModelMap();
				
				ResultVO rs = asignacionService.consultaAsignacionCorreo(idAsignacion);
				JSONObject jsonObject = (JSONObject) rs.getJsonResponse();
				JSONObject jsonUsuario = new JSONObject((Map) jsonObject.get("asignacion"));
				model.addAttribute("asignacion", jsonUsuario);
				model.addAttribute("asignacionItem", new AsignacionModelo());
				
				ResultVO rs1 = clienteService.consultaClienteCorreo(jsonUsuario.get("idClienteAsignacion").toString());
				jsonObject = (JSONObject) rs1.getJsonResponse();
				JSONObject jsonCliente = new JSONObject((Map) jsonObject.get("clientes"));
				model.addAttribute("cliente", jsonCliente);

				if(!idInstructor.equals("0")) {
					if(!jsonUsuario.get("idInstructorAsignacion").toString().equals(idInstructor)) {
						ModelAndView mav = new ModelAndView("CAsignacionIC0", model);
						mav.addObject("error", true);
						return mav;
					}
				}
				
				ModelAndView mav = new ModelAndView("CAsignacionIC", model);
				if(idInstructor.equals("0")) {
					mav.addObject("staff", true);
				}
				if(rs.getCodigo() != 500) {
					mav.addObject("ejecucion2", ejecucion2);
					mav.addObject("error", error);
					return mav;
				}else {
					mav.addObject("consulta", true);
					log.info("NOK ConsultaAsignacionInstructor");
					return mav;
				}
			}
		
		@PostMapping("/actualizaAsignacionIC")
		public ModelAndView actualizaAsignacionIC(@ModelAttribute("asignacionItem") AsignacionModelo asignacion, ModelMap model) {
			log.info("Actualiza Asignacion Correo model Activo");
			ResultVO resultVO = (ResultVO)model.get("model");
			model.addAttribute("model", resultVO);
//			log.info(asignacion.toString());
//			log.info(asignacion.getStatusAsignacion());
			ModelAndView mav=null;
			ResultVO rs = asignacionService.edicionAsignacionC(asignacion);
			if(asignacion.getStatusAsignacion().equals("Confirmado Instructor") || asignacion.getStatusAsignacion().equals("Curso Editado") || asignacion.getStatusAsignacion().equals("Curso Completado") || asignacion.getStatusAsignacion().equals("Curso Cancelado")) {
				mav = new ModelAndView("CAsignacionIC0", model);
			}else {
				mav = new ModelAndView("CAsignacionIC0", model);
			}
			if(asignacion.getStatusAsignacion().equals("Entregables Validado") || asignacion.getStatusAsignacion().equals("Entregable Enviado") ) {
				mav = new ModelAndView("CAsignacionIC0", model);
			}
			
			
			if(rs.getCodigo() != 500) {
//				resultVO.setJsonResponseObject(rs.getJsonResponseObject());
				mav.addObject("ejecucion2", true);
			}else {
				mav.addObject("error", true);
				log.info("NOK CAsignacionIC0");
			}
			return mav;			
		}

		@PostMapping("/mensaje")
		public ModelAndView mensaje(@ModelAttribute("mensajeItem") MensajeModelo mensaje, ModelMap model) {
			log.info("Actualiza Mensaje de Aplicacion");
			ResultVO resultVO = (ResultVO)model.get("model");
			model.addAttribute("model", resultVO);
			ModelAndView mav=new ModelAndView("Direccion");
			JSONObject object = resultVO.getJsonResponse();
			JSONObject jsonUser= new JSONObject((Map) object.get("user"));
			mensaje.setUserCreateInstructor(Long.valueOf(jsonUser.get("id").toString()));
			mensaje.setCreateAtInstructor(LocalDateTime.now());
			ResultVO rs = aplicacionService.altaMensaje(mensaje, resultVO.getAccesToken());
			if(rs.getCodigo() != 500) {
				mav.addObject("ejecucion2", true);
			}else {
				mav.addObject("error", true);
				log.info("NOK CAsignacionIC0");
			}
			return mav;			
		}

}


