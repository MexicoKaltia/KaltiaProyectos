package mx.uniprotec.inicio.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import mx.uniprotec.entidad.modelo.ClienteModelo;
import mx.uniprotec.entidad.modelo.ResultVO;
import mx.uniprotec.inicio.service.IClienteService;


@RestController
@CrossOrigin(origins = "*")
//@Scope("prototype")
public class ControllerUtil {
	public ControllerUtil() {}
	
	protected final Log log = LogFactory.getLog(getClass());

	@Autowired
	private IClienteService clienteService;	
	
	
	private ResultVO resultVO = new ResultVO();
	
	@GetMapping("/version")
	public  String version() {
		return  "01072020";
	}


	/*
	 * Imagenes y Archivos 
	 */
	@PostMapping("/clientes/upload")
	public ResponseEntity<?> upload(@RequestParam("archivo") MultipartFile archivo, @RequestParam("id") Long id){
		Map<String, Object> response = new HashMap<>();
		
		ClienteModelo cliente = new ClienteModelo();//clienteService.findById(id);
		
		if(!archivo.isEmpty()) {
			String nombreArchivo = UUID.randomUUID().toString() + "_" +  archivo.getOriginalFilename().replace(" ", "");
			
			Path rutaArchivo = Paths.get("uploads").resolve(nombreArchivo).toAbsolutePath();
			log.info(rutaArchivo.toString());
			
			try {
				Files.copy(archivo.getInputStream(), rutaArchivo);
			} catch (IOException e) {
				response.put("mensaje", "Error al subir la imagen del cliente " + nombreArchivo);
				response.put("error", e.getMessage().concat(": ").concat(e.getCause().getMessage()));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			
			String nombreFotoAnterior = cliente.getImagenLogoCliente();
			
			if(nombreFotoAnterior !=null && nombreFotoAnterior.length() >0) {
				Path rutaFotoAnterior = Paths.get("uploads").resolve(nombreFotoAnterior).toAbsolutePath();
				File archivoFotoAnterior = rutaFotoAnterior.toFile();
				if(archivoFotoAnterior.exists() && archivoFotoAnterior.canRead()) {
					archivoFotoAnterior.delete();
				}
			}
			
			cliente.setImagenLogoCliente(nombreArchivo);
			
//			clienteService.save(cliente);
			
			response.put("cliente", cliente);
			response.put("mensaje", "Has subido correctamente la imagen: " + nombreArchivo);
			
		}
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@GetMapping("/uploads/img/{idEmpresa}/{nombreFoto:.+}")
	public ResponseEntity<Resource> verFoto(@PathVariable String idEmpresa, @PathVariable String nombreFoto){
		
		Path rutaArchivo = Paths.get("/uniprotec/"+idEmpresa+"/image/").resolve(nombreFoto).toAbsolutePath();
		log.info(rutaArchivo.toString());
		
		Resource recurso = null;
		
		try {
			recurso = new UrlResource(rutaArchivo.toUri());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
		if(!recurso.exists() && !recurso.isReadable()) {
			throw new RuntimeException("Error no se pudo cargar la imagen: " + nombreFoto);
		}
		HttpHeaders cabecera = new HttpHeaders();
		cabecera.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + recurso.getFilename() + "\"");
		
		return new ResponseEntity<Resource>(recurso, cabecera, HttpStatus.OK);
	}
	
	@GetMapping("/uploads/file/{idEmpresa}/{nombreFile:.+}")
	public ResponseEntity<Resource> verFile(@PathVariable String idEmpresa, @PathVariable String nombreFile){
		
		Path rutaArchivo = Paths.get("/uniprotec/"+idEmpresa+"/file/").resolve(nombreFile).toAbsolutePath();
		log.info(rutaArchivo.toString());
		
		Resource recurso = null;
		
		try {
			recurso = new UrlResource(rutaArchivo.toUri());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
		if(!recurso.exists() && !recurso.isReadable()) {
			throw new RuntimeException("Error no se pudo cargar la Archivo: " + nombreFile);
		}
		HttpHeaders cabecera = new HttpHeaders();
		cabecera.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + recurso.getFilename() + "\"");
		
		return new ResponseEntity<Resource>(recurso, cabecera, HttpStatus.OK);
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
	public ResultVO fileUpload(@PathVariable String idEmpresa, @RequestParam("archivosCliente") MultipartFile archivosCliente){
	   try {
		   log.info(idEmpresa);
		    // Get the filename and build the local file path (be sure that the 
		    // application have write permissions on such directory)
		    String filename = archivosCliente.getOriginalFilename();

		    String directory = "/uniprotec/"+idEmpresa+"/file/";
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

	@RequestMapping(method = RequestMethod.POST, path = "/fileAsignacion/{idEmpresa}",  consumes = "multipart/form-data", produces = "application/json")
		public ResultVO fileAsignacion(@PathVariable String idEmpresa, @RequestParam("archivosAsignacion") MultipartFile asignaArchivos){
	   try {
		   log.info(idEmpresa);
		    // Get the filename and build the local file path (be sure that the 
		    // application have write permissions on such directory)
		    String filename = asignaArchivos.getOriginalFilename();

		    String directory = "/uniprotec/asignacion/"+idEmpresa+"/";
	        File directorio = new File(directory);
	        if (!directorio.exists()) {
	            if (directorio.mkdirs()) {
	                log.info("Directorio creado");
	    		    String filepath = Paths.get(directory, filename).toString();
	    		    
	    		    // Save the file locally
	    		    BufferedOutputStream stream =
	    		        new BufferedOutputStream(new FileOutputStream(new File(filepath)));
	    		    stream.write(asignaArchivos.getBytes());
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
    		    stream.write(asignaArchivos.getBytes());
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

	@GetMapping("/uploads/fileAsignacion/{idEmpresa}/{nombreFile:.+}")
	public ResponseEntity<Resource> verFileAsignacion(@PathVariable String idEmpresa, @PathVariable String nombreFile){
		
		Path rutaArchivo = Paths.get("/uniprotec/asignacion/"+idEmpresa+"/").resolve(nombreFile).toAbsolutePath();
		log.info(rutaArchivo.toString());
		
		Resource recurso = null;
		
		try {
			recurso = new UrlResource(rutaArchivo.toUri());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
		if(!recurso.exists() && !recurso.isReadable()) {
			throw new RuntimeException("Error no se pudo cargar la Archivo: " + nombreFile);
		}
		HttpHeaders cabecera = new HttpHeaders();
		cabecera.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + recurso.getFilename() + "\"");
		
		return new ResponseEntity<Resource>(recurso, cabecera, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST, path = "/fileAsignacionV/{idEmpresa}",  consumes = "multipart/form-data", produces = "application/json")
	public ResultVO fileAsignacionV(@PathVariable String idEmpresa, @RequestParam("archivoParticipantes") MultipartFile asignaArchivos){
   try {
	   log.info(idEmpresa);
	    // Get the filename and build the local file path (be sure that the 
	    // application have write permissions on such directory)
	    String filename = asignaArchivos.getOriginalFilename();

	    String directory = "/uniprotec/asignacion/"+idEmpresa+"/";
        File directorio = new File(directory);
        if (!directorio.exists()) {
            if (directorio.mkdirs()) {
                log.info("Directorio creado");
    		    String filepath = Paths.get(directory, filename).toString();
    		    
    		    // Save the file locally
    		    BufferedOutputStream stream =
    		        new BufferedOutputStream(new FileOutputStream(new File(filepath)));
    		    stream.write(asignaArchivos.getBytes());
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
		    stream.write(asignaArchivos.getBytes());
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


}
