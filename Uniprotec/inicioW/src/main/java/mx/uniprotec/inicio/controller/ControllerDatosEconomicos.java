package mx.uniprotec.inicio.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import mx.uniprotec.entidad.modelo.DatosEconomicosModelo;
import mx.uniprotec.entidad.modelo.ResultVO;
import mx.uniprotec.entidad.modelo.UsuarioModelo;
import mx.uniprotec.entidad.modelo.VendedorModelo;
import mx.uniprotec.inicio.service.IAplicacionService;
import mx.uniprotec.inicio.service.IAsignacionService;
import mx.uniprotec.inicio.service.IClienteService;
import mx.uniprotec.inicio.service.IDatosEconomicosService;
import mx.uniprotec.inicio.service.ILoginService;
import mx.uniprotec.inicio.service.IUsuarioService;
import mx.uniprotec.inicio.service.IVendedorService;

@CrossOrigin(origins = { "*" })
@Controller
@SessionAttributes ("model")
//@Scope("prototype")
public class ControllerDatosEconomicos {

	private static Logger log = LoggerFactory.getLogger(ControllerDatosEconomicos.class);
	
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
	@Autowired
	IVendedorService vendedorService;
	@Autowired
	IDatosEconomicosService datosEconomicosService;
	
	
//	ResultVO resultVO = new ResultVO();
	UsuarioModelo usuario = new UsuarioModelo();
	
	public ControllerDatosEconomicos() {
		// TODO Auto-generated constructor stub
	}
	
	/*
	 * Analisis Vendedor
	 */
	@GetMapping("/BVendedorAnalisis")
	public ModelAndView BVendedorAnalisis(@RequestParam(name="ejecucion", required=false) boolean ejecucion, 
			@RequestParam(name="error", required=false) boolean error,
			ModelMap model) {
			log.info("BVendedorAnalisis model Activo");
			model.addAttribute("vendedorForm", new VendedorModelo());
			
			ResultVO resultVO = (ResultVO)model.get("model");
			model.addAttribute("model", resultVO);
			
			ResultVO rs = datosEconomicosService.consultaVendedoresAnalisis(resultVO.getAccesToken());
			resultVO.setJsonResponseObject(rs.getJsonResponseObject());
			ModelAndView mav = new ModelAndView("BVendedorAnalisis", model);
			if(resultVO.getCodigo() != 500) {	
//				log.info(model.values().toString());
				
				mav.addObject("error", error);
				mav.addObject("ejecucion", ejecucion);
			}else {
				mav.addObject("consulta", true);
			}
			return mav;
		}	
		
	@PostMapping("/altaDatosEconomicos")
	public ModelAndView altaDatosEconomicos(@ModelAttribute("datosEconomicosItem") DatosEconomicosModelo datosEconomicosItem, ModelMap model) {
		log.info("altaDatosEconomicos model Activo");
		log.info(datosEconomicosItem.toString());
		ResultVO resultVO = (ResultVO)model.get("model");
		model.addAttribute("model", resultVO);

		ResultVO rs = datosEconomicosService.altaDatosEconomicos(datosEconomicosItem, resultVO.getAccesToken());
		ModelAndView mav = null;
		if(datosEconomicosItem.getIdAsignacion()==null) {
			mav = new ModelAndView("redirect:/ADatosEconomicos", model);
		}else {
			mav = new ModelAndView("redirect:/BVendedorAnalisis", model);
		}
		
		if(rs.getCodigo() != 500) {
			resultVO.setJsonResponseObject(rs.getJsonResponseObject());
			mav.addObject("ejecucion", true);
		}else {
			mav.addObject("error", true);
			log.info("NOK altaDatosEconomicos");
		}
		return mav;			
	}

	@GetMapping("/ADatosEconomicos")
	public ModelAndView adatosEconomicos(@RequestParam(name="ejecucion", required=false) boolean ejecucion,
			@RequestParam(name="ejecucion2", required=false) boolean ejecucion2,
			@RequestParam(name="error", required=false) boolean error,
			ModelMap model) {
			log.info("ADatosEconomicos model Activo");
			model.addAttribute("datosEconomicosItem", new DatosEconomicosModelo());
			
			ResultVO resultVO = (ResultVO)model.get("model");
			model.addAttribute("model", resultVO);
			
			ResultVO rs = asignacionService.consultaAsignacion(resultVO.getAccesToken());
			resultVO.setJsonResponseObject(rs.getJsonResponseObject());
			
			ModelAndView mav = new ModelAndView("ADatosEconomicos", model);
			if(rs.getCodigo() != 500) {
				mav.addObject("ejecucion", ejecucion);
				mav.addObject("ejecucion2", ejecucion2);
				mav.addObject("error", error);
				return mav;
			}else {
				mav.addObject("consulta DaTOS Economicos", true);
				log.info("NOK Consulta DaTOS Economicos");
				return mav;
			}
	}
	
//	@PostMapping("/altaDatosEconomicosAsignacion")
//	public ModelAndView altaDatosEconomicosAsignacion(@ModelAttribute("datosEconomicosItem") DatosEconomicosModelo datosEconomicosItem, ModelMap model) {
//		log.info("altaDatosEconomicosAsignacion model Activo");
//		log.info(datosEconomicosItem.toString());
//		ResultVO resultVO = (ResultVO)model.get("model");
//		model.addAttribute("model", resultVO);
//
//		ResultVO rs = datosEconomicosService.altaDatosEconomicosAsignacion(datosEconomicosItem, resultVO.getAccesToken());
//		ModelAndView mav = new ModelAndView("redirect:/BVendedorAnalisis", model);
//		if(rs.getCodigo() != 500) {
//			resultVO.setJsonResponseObject(rs.getJsonResponseObject());
//			mav.addObject("ejecucion", true);
//		}else {
//			mav.addObject("error", true);
//			log.info("NOK altaDatosEconomicos");
//		}
//		return mav;			
//	}



//	@GetMapping("CVendedorAnalisis")
//	public ModelAndView CVendedorAnalisis(@RequestParam(name="ejecucion", required=false) boolean ejecucion, 
//			@RequestParam(name="error", required=false) boolean error,
//			ModelMap model) {
//			log.info("CVendedorAnalisis model Activo");
//			
//			ResultVO resultVO = (ResultVO)model.get("model");
//			model.addAttribute("model", resultVO);
//			
//			ResultVO rs = vendedorService.consultaVendedoresAnalisis(resultVO.getAccesToken());
//			resultVO.setJsonResponseObject(rs.getJsonResponseObject());
//			
//			ModelAndView mav = new ModelAndView("CVendedorAnalisis", model);
//			if(resultVO.getCodigo() != 500) {	
//				mav.addObject("error", error);
//				mav.addObject("ejecucion", ejecucion);
//			}
//			return mav;
//		}	
	

	
	
	

}
