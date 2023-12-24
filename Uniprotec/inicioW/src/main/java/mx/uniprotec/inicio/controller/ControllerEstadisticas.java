package mx.uniprotec.inicio.controller;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import mx.uniprotec.entidad.modelo.AsignacionModelo;
import mx.uniprotec.entidad.modelo.AsignacionModeloDescarga;
import mx.uniprotec.entidad.modelo.ClienteModelo;
import mx.uniprotec.entidad.modelo.DatosEconomicosModelo;
import mx.uniprotec.entidad.modelo.ReporteSemanalModelo;
import mx.uniprotec.entidad.modelo.ResultVO;
import mx.uniprotec.entidad.modelo.UsuarioModelo;
import mx.uniprotec.entidad.modelo.VendedorModelo;
import mx.uniprotec.inicio.service.IAplicacionService;
import mx.uniprotec.inicio.service.IAsignacionService;
import mx.uniprotec.inicio.service.IClienteService;
import mx.uniprotec.inicio.service.ICursoService;
import mx.uniprotec.inicio.service.IDatosEconomicosService;
import mx.uniprotec.inicio.service.ILoginService;
import mx.uniprotec.inicio.service.IUsuarioService;
import mx.uniprotec.inicio.service.IVendedorService;

@CrossOrigin(origins = { "*" })
@Controller
@SessionAttributes ("model")
//@Scope("prototype")
public class ControllerEstadisticas {

	private static Logger log = LoggerFactory.getLogger(ControllerEstadisticas.class);
	
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
	ICursoService cursoService;
	@Autowired
	IDatosEconomicosService datosEconomicosService;
	
	
//	ResultVO resultVO = new ResultVO();
	UsuarioModelo usuario = new UsuarioModelo();
	
	public ControllerEstadisticas() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	@GetMapping("/CEstadisticaGeneral")
	public ModelAndView estadisticaInstructor(@RequestParam(name="ejecucion", required=false) boolean ejecucion, 
			@RequestParam(name="error", required=false) boolean error,
			ModelMap model) {
			log.info("CEstadisticaInstructor model Activo");
			model.addAttribute("asignacionItem", new AsignacionModelo());
			model.addAttribute("asignacionesDescargaForm",new AsignacionModeloDescarga());
			
			
			ResultVO resultVO = (ResultVO)model.get("model");
			model.addAttribute("model", resultVO);
			
			ResultVO rs = asignacionService.consultaAsignacionHistorico(resultVO.getAccesToken());
			resultVO.setJsonResponseObject(rs.getJsonResponseObject());

			rs = aplicacionService.consultaRegiones(resultVO.getAccesToken());
			resultVO.setRegiones(rs.getRegiones());

			rs = cursoService.consultaCursos(resultVO.getAccesToken());
			resultVO.setCursos(rs.getCursos());
			
			rs = clienteService.consultaClientes(resultVO.getAccesToken());
			resultVO.setClientes(rs.getClientes());

			
			ModelAndView mav = new ModelAndView("CEstadisticaGeneral", model);
			if(resultVO.getCodigo() != 500) {	
//				log.info(model.values().toString());
				
				mav.addObject("error", error);
				mav.addObject("ejecucion", ejecucion);
			}else {
				mav.addObject("consulta", true);
			}
			return mav;
		}		
	

}
