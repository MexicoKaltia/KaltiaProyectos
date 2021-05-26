package mx.uniprotec.gamerFront.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;

import mx.uniprotec.entidad.modelo.ResultVO;
import mx.uniprotec.entidad.modelo.ValoresJsonVO;
import mx.uniprotec.gamerFront.service.impl.ModuloService;

@RestController
@CrossOrigin(origins = "*")
//@Scope("prototype")
public class ControllerUtil {
	
	protected final Log log = LogFactory.getLog(getClass());
	
	@Autowired
	ModuloService moduloService;


	public ControllerUtil() {}
	
	@RequestMapping(method = RequestMethod.POST, path = "/imageUpload/{idEmpresa}",  consumes = "multipart/form-data", produces = "application/json")
//	@PostMapping("/fileUpload/{idEmpresa}")
	public ResultVO imageUpload(@PathVariable String idEmpresa, @RequestParam("moduloDidacticoImagen") MultipartFile imagenLogoCliente){
		
		ResultVO resultVO = new ResultVO();
	   try {
		   log.info(idEmpresa);
		    // Get the filename and build the local file path (be sure that the 
		    // application have write permissions on such directory)
		   
		    String filename = imagenLogoCliente.getOriginalFilename();

		    String directory = "/uniprotec/"+idEmpresa+"/image/";
	        File directorio = new File(directory);
	        if (!directorio.exists()) {
	            if (directorio.mkdirs()) {
	                log.info("Nuevo Directorio creado");
	    		    String filepath = Paths.get(directory, filename).toString();
	    		    
	    		    // Save the file locally
	    		    BufferedOutputStream stream =
	    		        new BufferedOutputStream(new FileOutputStream(new File(filepath)));
	    		    stream.write(imagenLogoCliente.getBytes());
	    		    stream.close();
	            } else {
	            	log.info("Error al crear directorio");
	            }
	        }else {
	        	log.info("Directorio Ya existe");
	        	String filepath = Paths.get(directory, filename).toString();
    		    
    		    // Save the file locally
    		    BufferedOutputStream stream =
    		        new BufferedOutputStream(new FileOutputStream(new File(filepath)));
    		    stream.write(imagenLogoCliente.getBytes());
    		    stream.close();
	        }
		    
		    
		    resultVO.setCodigo((long) 0);
		    resultVO.setMensaje("Exito Imagen Upload");
		    return resultVO;
		  }
		  catch (Exception e) {
		    log.info("exception : "+e.getMessage());
		    resultVO.setCodigo((long) 99);
		    resultVO.setMensaje(e.getMessage());
		    return resultVO; 
		  }
	      //new ResultVO(1, "ExitoFileUpload");
		}
	
	
	@RequestMapping(method = RequestMethod.POST, path = "/imageUploadElemento/{idModulo}",  consumes = "multipart/form-data", produces = "application/json")
//	@PostMapping("/fileUpload/{idEmpresa}")
	public ResultVO imageUploadElemento(@PathVariable String idModulo, @RequestParam("imagenObjetoQRD") MultipartFile imagenObjetoQRD){
		
		ResultVO resultVO = new ResultVO();
	   try {
		   log.info(idModulo);
		    // Get the filename and build the local file path (be sure that the 
		    // application have write permissions on such directory)
		   
		    String filename = imagenObjetoQRD.getOriginalFilename();

		    String directory = "/uniprotec/"+idModulo+"/image/";
	        File directorio = new File(directory);
	        if (!directorio.exists()) {
	            if (directorio.mkdirs()) {
	                log.info("Nuevo Directorio creado");
	    		    String filepath = Paths.get(directory, filename).toString();
	    		    
	    		    // Save the file locally
	    		    BufferedOutputStream stream =
	    		        new BufferedOutputStream(new FileOutputStream(new File(filepath)));
	    		    stream.write(imagenObjetoQRD.getBytes());
	    		    stream.close();
	            } else {
	            	log.info("Error al crear directorio");
	            }
	        }else {
	        	log.info("Directorio Ya existe");
	        	String filepath = Paths.get(directory, filename).toString();
    		    
    		    // Save the file locally
    		    BufferedOutputStream stream =
    		        new BufferedOutputStream(new FileOutputStream(new File(filepath)));
    		    stream.write(imagenObjetoQRD.getBytes());
    		    stream.close();
	        }
		    
		    
		    resultVO.setCodigo((long) 0);
		    resultVO.setMensaje("Exito Imagen Upload");
		    return resultVO;
		  }
		  catch (Exception e) {
		    log.info("exception : "+e.getMessage());
		    resultVO.setCodigo((long) 99);
		    resultVO.setMensaje(e.getMessage());
		    return resultVO; 
		  }
	      //new ResultVO(1, "ExitoFileUpload");
		}

	@PostMapping("/edicionSeccion")
	public  ResultVO updateSeccion(@RequestBody ValoresJsonVO valoresJsonVO) {

		/* ValoresJsonVO
		 *
		 * finalJson = {
					elementos : $elementosFinal,
					modulo : $moduloSel,
					idCurso : $idCurso,
					val : $val
			}
		 */
			ResultVO resultVO = moduloService.edicionServiceUpdate(valoresJsonVO.getVal(), valoresJsonVO);
//			ModelAndView mav = new ModelAndView("redirect:/modulos" , model);
//			if(resultVO.getCodigo() == 200) {
//				mav.addObject("ejecucionI", true);
//			}else {
//				mav.addObject("error", true);
//				log.info("NOK Usuarios");
//			}
			return resultVO;
	}
	
	@GetMapping("/uploads/img/{idEmpresa}/{nombreFoto:.+}")
	public ResponseEntity<Resource> verFoto(@PathVariable String idEmpresa, @PathVariable String nombreFoto){
		
		Path rutaArchivo = Paths.get("/uniprotec/"+idEmpresa+"/image/").resolve(nombreFoto).toAbsolutePath();
		log.info(rutaArchivo.toString());
		
		Resource recurso = null;
		HttpHeaders cabecera = new HttpHeaders();
		try {
			recurso = new UrlResource(rutaArchivo.toUri());
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return new ResponseEntity<Resource>(recurso, cabecera, HttpStatus.FORBIDDEN);
		}
		
		cabecera.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + recurso.getFilename() + "\"");
		
		if(!recurso.exists() && !recurso.isReadable()) {
			log.info("hellow no se pudo cargar la imagen");
			throw new RuntimeException("Error no se pudo cargar la imagen: " + nombreFoto);
			
		}
		
		
		return new ResponseEntity<Resource>(recurso, cabecera, HttpStatus.OK);
	}

}
