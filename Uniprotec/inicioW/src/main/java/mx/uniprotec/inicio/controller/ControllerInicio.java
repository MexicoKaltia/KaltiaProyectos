package mx.uniprotec.inicio.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import mx.uniprotec.entidad.modelo.AsignacionModelo;
import mx.uniprotec.entidad.modelo.MensajeModelo;
import mx.uniprotec.entidad.modelo.Region;
import mx.uniprotec.entidad.modelo.ResultVO;
import mx.uniprotec.entidad.modelo.User;
import mx.uniprotec.entidad.modelo.UsuarioModelo;
import mx.uniprotec.entidad.modelo.ZonaBaseModelo;
import mx.uniprotec.inicio.service.IAplicacionService;
import mx.uniprotec.inicio.service.IAsignacionService;
import mx.uniprotec.inicio.service.IClienteService;
import mx.uniprotec.inicio.service.ILoginService;
import mx.uniprotec.inicio.service.IUsuarioService;
import mx.uniprotec.inicio.util.BaseClientRest;

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
//				log.info(resultVO.getResponse());
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
//				log.info(usuario.toString());
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
		
		@GetMapping("/notificacion/{idAsignacion}")
		public ModelAndView getNotificaciona(@PathVariable String idAsignacion, 
				ModelMap model) {
			
			if(model.equals(null)) {
				log.info("NULL");
				return new  ModelAndView("login");
			}else {
				log.info("Notificacion Asignacion model Activo");
				ResultVO resultVO = (ResultVO)model.get("model");	
				model.addAttribute("model", resultVO);
				
				ResultVO rs0 = asignacionService.consultaAsignacionCorreo(idAsignacion);
				JSONObject jsonObject = (JSONObject) rs0.getJsonResponse();
				JSONObject asignacionJson = new JSONObject((Map) jsonObject.get("asignacion"));

				AsignacionModelo asignacion = new AsignacionModelo();
				asignacion.setIdAsignacion(Long.valueOf(asignacionJson.get("idAsignacion").toString()));
				asignacion.setIdAsignacionLogica((String) asignacionJson.get("idAsignacionLogica"));
				asignacion.setFechaAsignacion((String) asignacionJson.get("fechaAsignacion"));
				asignacion.setIdClienteAsignacion(Long.valueOf(asignacionJson.get("idClienteAsignacion").toString()));
				asignacion.setClienteAsignacion((String) asignacionJson.get("clienteAsignacion"));
				asignacion.setIdCursoAsignacion(Long.valueOf(asignacionJson.get("idCursoAsignacion").toString()));
				asignacion.setCursoAsignacion((String) asignacionJson.get("cursoAsignacion"));
				asignacion.setIdInstructorAsignacion(Long.valueOf(asignacionJson.get("idInstructorAsignacion").toString()));
				asignacion.setInstructorAsignacion((String) asignacionJson.get("instructorAsignacion"));
				asignacion.setHorarioAsignacion((String) asignacionJson.get("horarioAsignacion"));
				asignacion.setParticipantesAsignacion((String) asignacionJson.get("participantesAsignacion"));
				asignacion.setNivelAsignacion((String) asignacionJson.get("nivelAsignacion"));
				asignacion.setObservacionesAsignacion((String) asignacionJson.get("observacionesAsignacion"));
				asignacion.setArchivosAsignacion((String) asignacionJson.get("archivosAsignacion"));
				asignacion.setIdRegionAsignacion(Long.valueOf(asignacionJson.get("idRegionAsignacion").toString()));
				asignacion.setNombreRegionAsignacion((String) asignacionJson.get("nombreRegionAsignacion"));
				asignacion.setTipoCursoAsignacion((String) asignacionJson.get("tipoCursoAsignacion"));
				asignacion.setCreateAtAsignacion(LocalDateTime.parse(asignacionJson.get("createAtAsignacion").toString()));
				asignacion.setUserCreateAsignacion(Long.valueOf(asignacionJson.get("userCreateAsignacion").toString()));
				asignacion.setUserCreateAsignacionTexto((String) asignacionJson.get("userCreateAsignacionTexto"));
				asignacion.setStatusAsignacion((String) asignacionJson.get("statusAsignacion"));
				asignacion.setFechaPago((String) asignacionJson.get("fechaPago"));
				asignacion.setGuiaEntregable((String) asignacionJson.get("guiaEntregable"));
				asignacion.setNumeroFactura((String) asignacionJson.get("numeroFactura"));
				asignacion.setCostoHotel((String) asignacionJson.get("costoHotel"));
				asignacion.setArchivoParticipantes((String) asignacionJson.get("archivoParticipantes"));
				model.addAttribute("asignacionItem", asignacion);
				
				
				ResultVO rs = clienteService.consultaCliente(resultVO.getAccesToken(), Long.valueOf(asignacionJson.get("idClienteAsignacion").toString()));
//				log.info(rs.getJsonResponse().toJSONString());
				resultVO.setJsonResponseObject(rs.getJsonResponse());
				
				aplicacionService.actualizaNotificacion(resultVO.getAccesToken(), idAsignacion);
				
				
				ModelAndView mav = new  ModelAndView("CNotificacion",  model);
//				ModelAndView mav = new  ModelAndView("redirect:/CAsignacionIC/"+asignacion.getIdAsignacion()+"/"+asignacion.getInstructorAsignacion(),  model);
				
//				log.info(model.toString());
				if(rs0.getCodigo() != 500) {					
					return mav;
				}else {
					mav.addObject("consulta", true);
					return mav;	
				}
			}		
		}
		
		@PostMapping("/BMovilidad")
		public ModelAndView BMovilidad(@ModelAttribute("zonaBase") ZonaBaseModelo zonaBase, ModelMap model) {
			
			
			if(model.equals(null)) {
				log.info("NULL");
				return new  ModelAndView("login");
			}else {
				log.info("CMovilidad model Activo");
				ResultVO resultVO = (ResultVO)model.get("model");
				
				zonaBase.setIdZonabase(1l);
				ResultVO resultZonaBaseUpdate = aplicacionService.updateMovilidad(resultVO.getAccesToken(), zonaBase); 
						
				ModelAndView mav = new  ModelAndView("redirect:/CMovilidad", model );
				if(resultZonaBaseUpdate.getCodigo() != 500) {
					mav.addObject("ejecucion", true);
				}else {
					mav.addObject("error", true);
					log.info("NOK BMOVILIDAD");
				}		
				model.addAttribute("model", resultVO);
				return mav;
									
//				JSONParser jsonZonaBase = new JSONParser();
//				try {
//					jsonZonaBase.parse(zonaBase.getDataZonabase());
//				} catch (ParseException e) {
//					e.printStackTrace();
//				}
//				JSONObject jsonObject = (JSONObject) jsonParser(new FileReader("E:/players_data.json"));
//				JSONParser jsonParser = new JSONParser();
//				JSONObject jsonDataZonaBase = (JSONObject) jsonParser.parse(jsonZonaBase.get("dataZonabase").toString());
//				log.info(jsonDataZonaBase.get("11").toString());
//				//-------------------------------------------- ACTUALIZAR JSON ----------------------------------//
//				jsonDataZonaBase.put("11", false);
//				jsonDataZonaBase.put("AA", true);
				
			}
		}

		
		@GetMapping("/CMovilidad")
		public ModelAndView CMovilidad(@RequestParam(name="ejecucion", required=false) boolean ejecucion, 
				@RequestParam(name="error", required=false) boolean error,
				ModelMap model) {
			
			
			if(model.equals(null)) {
				log.info("NULL");
				return new  ModelAndView("login");
			}else {
				log.info("CMovilidad model Activo");
				
				ResultVO resultVO = (ResultVO)model.get("model");
				ResultVO resultZonaBase =  aplicacionService.consultaMovilidad(resultVO.getAccesToken());
				JSONObject jsonResultObject = (JSONObject) resultZonaBase.getJsonResponse();
				
				resultVO.setJsonResponseObject(jsonResultObject );
				resultVO.setRegiones(resultZonaBase.getRegiones());
				
				model.addAttribute("zonaBase", new ZonaBaseModelo());
				
				ModelAndView mav = new  ModelAndView("CMovilidad", model );
				model.addAttribute("model", resultVO);
				mav.addObject("error", error);
				mav.addObject("ejecucion", ejecucion);
				return mav;
			}	
		}
			

}


