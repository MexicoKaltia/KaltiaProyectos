package mx.uniprotec.inicio.controller;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Paths;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import mx.uniprotec.entidad.modelo.ResultVO;


@RestController
@CrossOrigin(origins = "*")
public class ControllerUtil {
	public ControllerUtil() {}
	
	protected final Log log = LogFactory.getLog(getClass());

	
	
	
	private ResultVO resultVO = new ResultVO();
	
	@GetMapping("/version")
	public  String version() {
		return  "23012020";
	}


	@RequestMapping(method = RequestMethod.POST, path = "/imageUpload/{idEmpresa}",  consumes = "multipart/form-data", produces = "application/json")
//	@PostMapping("/fileUpload/{idEmpresa}")
		public ResultVO imageUpload(@PathVariable String idEmpresa, @RequestParam("imagenLogoCliente") MultipartFile imagenLogoCliente){
	   try {
		   log.info(idEmpresa);
		    // Get the filename and build the local file path (be sure that the 
		    // application have write permissions on such directory)
		    String filename = imagenLogoCliente.getOriginalFilename();

		    String directory = "/uniprotec/"+idEmpresa+"/image/";
//		    String directory = "C:\\Uniprotec\\empresa\\nuevo\\logo\\";
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
		    
		  }
		  catch (Exception e) {
		    log.info("exception : "+e.getMessage());
		    resultVO.setCodigo((long) 99);
		    resultVO.setMensaje(e.getMessage());
		    return resultVO;//new ResultVO(99, "fallo");
		  }
	      return resultVO;//new ResultVO(1, "ExitoFileUpload");
		}
	
	
	@RequestMapping(method = RequestMethod.POST, path = "/fileUpload/{idEmpresa}",  consumes = "multipart/form-data", produces = "application/json")
//	@PostMapping("/fileUpload/{idEmpresa}")
		public ResultVO fileUpload(@PathVariable String idEmpresa, @RequestParam("archivosCliente") MultipartFile archivosCliente){
	   try {
		   log.info(idEmpresa);
		    // Get the filename and build the local file path (be sure that the 
		    // application have write permissions on such directory)
		    String filename = archivosCliente.getOriginalFilename();

		    String directory = "/uniprotec/"+idEmpresa+"/file/";
//		    String directory = "C:\\Uniprotec\\empresa\\nuevo\\logo\\";
	        File directorio = new File(directory);
	        if (!directorio.exists()) {
	            if (directorio.mkdirs()) {
	                log.info("Directorio creado");
	    		    String filepath = Paths.get(directory, filename).toString();
	    		    
	    		    // Save the file locally
	    		    BufferedOutputStream stream =
	    		        new BufferedOutputStream(new FileOutputStream(new File(filepath)));
	    		    stream.write(archivosCliente.getBytes());
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
    		    stream.write(archivosCliente.getBytes());
    		    stream.close();
	        }
		    
		    
		    
		    resultVO.setCodigo((long) 0);
		    resultVO.setMensaje("Exito File Upload");
		    
		  }
		  catch (Exception e) {
		    log.info("exception : "+e.getMessage());
		    resultVO.setCodigo((long) 99);
		    resultVO.setMensaje(e.getMessage());
		    return resultVO;//new ResultVO(99, "fallo");
		  }
	      return resultVO;//new ResultVO(1, "ExitoFileUpload");
		}
	
//	@RequestMapping(method = RequestMethod.POST, path = "/fileUpload/{idEmpresa}",  consumes = "multipart/form-data", produces = "application/json")
////	@PostMapping("/fileAsignacion/{idEmpresa}")
//		public ResultVO fileAsignacion(@PathVariable String idEmpresa, @RequestParam("asignaArchivos") MultipartFile archivosCliente){
//	   try {
//		   log.info(idEmpresa);
//		    // Get the filename and build the local file path (be sure that the 
//		    // application have write permissions on such directory)
//		    String filename = archivosCliente.getOriginalFilename();
//
//		    String directory = "/uniprotec/"+idEmpresa+"/file/";
////		    String directory = "C:\\Uniprotec\\empresa\\nuevo\\logo\\";
//	        File directorio = new File(directory);
//	        if (!directorio.exists()) {
//	            if (directorio.mkdirs()) {
//	                log.info("Directorio creado");
//	    		    String filepath = Paths.get(directory, filename).toString();
//	    		    
//	    		    // Save the file locally
//	    		    BufferedOutputStream stream =
//	    		        new BufferedOutputStream(new FileOutputStream(new File(filepath)));
//	    		    stream.write(archivosCliente.getBytes());
//	    		    stream.close();
//	            } else {
//	            	log.info("Error al crear directorio");
//	            }
//	        }else {
//	        	log.info("Directorio Ya existe");
//	        	String filepath = Paths.get(directory, filename).toString();
//    		    
//    		    // Save the file locally
//    		    BufferedOutputStream stream =
//    		        new BufferedOutputStream(new FileOutputStream(new File(filepath)));
//    		    stream.write(archivosCliente.getBytes());
//    		    stream.close();
//	        }
//		    
//		    
//		    
//		    resultVO.setCodigo((long) 0);
//		    resultVO.setMensaje("Exito File Upload");
//		    
//		  }
//		  catch (Exception e) {
//		    log.info("exception : "+e.getMessage());
//		    resultVO.setCodigo((long) 99);
//		    resultVO.setMensaje(e.getMessage());
//		    return resultVO;//new ResultVO(99, "fallo");
//		  }
//	      return resultVO;//new ResultVO(1, "ExitoFileUpload");
//		}
}
