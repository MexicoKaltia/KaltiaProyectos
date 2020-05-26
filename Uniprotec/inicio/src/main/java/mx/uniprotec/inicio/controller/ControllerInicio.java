package mx.uniprotec.inicio.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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

import mx.uniprotec.entidad.modelo.ClienteModelo;
import mx.uniprotec.entidad.modelo.ResultVO;
import mx.uniprotec.entidad.modelo.User;
import mx.uniprotec.entidad.modelo.UsuarioModelo;
import mx.uniprotec.entidad.modelo.VendedorModelo;
import mx.uniprotec.inicio.service.IAplicacionService;
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
	IAplicacionService aplicacionService;
	
//	ResultVO resultVO = new ResultVO();
	UsuarioModelo usuario = new UsuarioModelo();
	
	
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
		public ModelAndView loginInit(@ModelAttribute("userLogin") User user) {
			
			ModelAndView mav = new ModelAndView();
			ModelMap model = new ModelMap();
			log.info(user.toString());
			
			ResultVO resultVO = new ResultVO();
			resultVO = loginService.login(user);

//			model.addAttribute("loginResponse", resultVO);

//			log.info(model.values().toString());			
			return new ModelAndView(resultVO.getResponse(), "model", resultVO);

		}
		
		
		@GetMapping("/inicio")
		public ModelAndView inicio(ModelMap model) {

			if(model.equals(null)) {
				log.info("NULL");
				return new  ModelAndView("login");
			}else {
				log.info("Inicio model Activo");
						return new  ModelAndView("index",  model);	
			}		

			}
		
		
		@GetMapping("/Asignacion")
		public ModelAndView asignacion(ModelMap model) {

			if(model.equals(null)) {
				log.info("NULL");
				return new  ModelAndView("login");
			}else {
				log.info("Asignacion model Activo");
				
				ResultVO resultVO = (ResultVO)model.get("model");			
				ResultVO rs = aplicacionService.consultaData(resultVO);
				model.addAttribute("model", rs);
				
//				ArrayList<ClienteModelo> clientes = (ArrayList<ClienteModelo>) rs.getClientes();
//				for(int i= 0; i<rs.getClientes().size(); i++) {
//					ClienteModelo cliente = (ClienteModelo)clientes.get(i);
//					log.info(cliente.getIdCliente() + ":" + cliente.getNombreCortoCliente());
//				}
				
//				for(VendedorModelo vendedor : rs.getVendedores()) {
//					log.info(vendedor.getIdVendedor() + ":" + vendedor.getNombreVendedor());
//				}

				return new  ModelAndView("Asignacion",  model);	
			}		

			}

		
		@GetMapping("/Calendario")
		public ModelAndView calendario(ModelMap model) {

			if(model.equals(null)) {
				log.info("NULL");
				return new  ModelAndView("login");
			}else {
				log.info("Calendario model Activo");
//				log.info(model.values().toString());
				return new  ModelAndView("calendario",  model);	
			}		

			}

		

}


