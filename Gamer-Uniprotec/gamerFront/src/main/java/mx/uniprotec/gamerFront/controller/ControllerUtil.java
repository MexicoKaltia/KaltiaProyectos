package mx.uniprotec.gamerFront.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Paths;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import mx.uniprotec.entidad.modelo.ResultVO;

@RestController
@CrossOrigin(origins = "*")
//@Scope("prototype")
public class ControllerUtil {
	
	protected final Log log = LogFactory.getLog(getClass());

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

}
