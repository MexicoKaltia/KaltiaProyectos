package mx.uniprotec.inicio.controller;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.imageio.ImageIO;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import mx.uniprotec.entidad.modelo.AsignacionModelo;
import mx.uniprotec.entidad.modelo.AsignacionModeloDescarga;
import mx.uniprotec.entidad.modelo.ClienteModelo;
import mx.uniprotec.entidad.modelo.EntregableModelo;
import mx.uniprotec.entidad.modelo.ParticipantesModelo;
import mx.uniprotec.entidad.modelo.ResultVO;
import mx.uniprotec.inicio.service.IAplicacionService;
import mx.uniprotec.inicio.service.IClienteService;


@RestController
@CrossOrigin(origins = "*")
//@Scope("prototype")
public class ControllerUtil {
	public ControllerUtil() {}
	
	protected final Log log = LogFactory.getLog(getClass());

	@Autowired
	private IAplicacionService aplicacionService;	
	
	
	private ResultVO resultVO = new ResultVO();
	
	@GetMapping("/version")
	public  String version() {
		return  "17102023";
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
	
	@GetMapping("/fotoParticipante/{idEmpresa}/{nombreFoto:.+}")
	public ResponseEntity<Resource> verFotoParticipante(@PathVariable String idEmpresa, @PathVariable String nombreFoto){
		
		Path rutaArchivo = Paths.get("/uniprotec/entregables/"+idEmpresa+"/imagenesParticipantes/").resolve(nombreFoto).toAbsolutePath();
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
	
	
	/*
	 * Documentos  Entregables.
	 */
	@RequestMapping(method = RequestMethod.POST, path = "/imageUploadEntregable/{idEmpresa}/{idEntregable}",  consumes = "multipart/form-data", produces = "application/json")
	public ResultVO imageUploadEntregable(@PathVariable String idEmpresa, @PathVariable String idEntregable, @RequestParam("formALogo") MultipartFile formALogo){
	   try {
		   log.info(idEmpresa);
		    // Get the filename and build the local file path (be sure that the 
		    // application have write permissions on such directory)
		    String filename = formALogo.getOriginalFilename();

		    String directory = "/uniprotec/entregables/"+idEmpresa+"/"+idEntregable +"/imageLogo/";
	        File directorio = new File(directory);
	        if (!directorio.exists()) {
	            if (directorio.mkdirs()) {
	                log.info("Nuevo Directorio creado");
	    		    String filepath = Paths.get(directory, filename).toString();
	    		    
	    		    // Save the file locally
	    		    BufferedOutputStream stream =
	    		        new BufferedOutputStream(new FileOutputStream(new File(filepath)));
	    		    stream.write(formALogo.getBytes());
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
    		    stream.write(formALogo.getBytes());
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

	@RequestMapping(method = RequestMethod.POST, path = "/imageUploadParticipante/{idEmpresa}/{idEntregable}",  consumes = "multipart/form-data", produces = "application/json")
	public ResultVO imageUploadParticipante(@PathVariable String idEmpresa, @PathVariable String idEntregable, @RequestParam("formBFotoParticipante") MultipartFile formBFotoParticipante){
	   try {
		   log.info(idEmpresa);
		    // Get the filename and build the local file path (be sure that the 
		    // application have write permissions on such directory)
		    String filename = formBFotoParticipante.getOriginalFilename();
		    
		    String directory = "/uniprotec/entregables/"+idEmpresa+"/imagenesParticipantes/";
	        File directorio = new File(directory);
	        String filepath = Paths.get(directory, filename).toString();
	        if (!directorio.exists()) {
	            if (directorio.mkdirs()) {
	                log.info("Nuevo Directorio creado");
	    		
	    		    
	    		    // Save the file locally
	    		    BufferedOutputStream stream =
	    		        new BufferedOutputStream(new FileOutputStream(new File(filepath)));
	    		    stream.write(formBFotoParticipante.getBytes());
	    		    stream.close();
	            } else {
	            	log.info("Error al crear directorio");
	            }
	        }else {
	        	log.info("Directorio Ya existe");
//	        	String filepath = Paths.get(directory, filename).toString();
	        	
	        	// Save the file locally
    		    BufferedOutputStream stream =
    		        new BufferedOutputStream(new FileOutputStream(new File(filepath)));
    		    stream.write(formBFotoParticipante.getBytes());
    		    stream.close();
	        }
	        
	        crearDirectoriosDocumentacion(idEmpresa, idEntregable);
	        
	        try {
    			resize(filepath);
    		} catch (IOException e1) {
    			// TODO Auto-generated catch block
    			e1.printStackTrace();
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
	
	 

	
	@RequestMapping(method = RequestMethod.POST, path = "/imageUploadEvidencia/{idEmpresa}/{idEntregable}",  consumes = "multipart/form-data", produces = "application/json")
	public ResultVO imageUploadEvidencia(@PathVariable String idEmpresa, @PathVariable String idEntregable, @RequestParam("entregableEdicion[]") MultipartFile formCEvidenciasFotograficas){
		   log.info(idEmpresa);
		   String message = "";
		    try {
		    	 String filename = formCEvidenciasFotograficas.getOriginalFilename();
		      String directory = "/uniprotec/entregables/"+idEmpresa+"/"+idEntregable +"/externo/";
		      
		      File directorio = new File(directory);
			    FileUtils.deleteDirectory(directorio);
			    
		        if (!directorio.exists()) {
		            if (directorio.mkdirs()) {
		                log.info("Directorio creado");
		    		    String filepath = Paths.get(directory, filename).toString();
		    		    // Save the file locally
		    		    BufferedOutputStream stream =
		    		        new BufferedOutputStream(new FileOutputStream(new File(filepath)));
		    		    stream.write(formCEvidenciasFotograficas.getBytes());
		    		    stream.close();
		            } else {
		            	log.info("Error al crear directorio");
		            }
		        }else {
		        	log.info("Directorio Ya existe");
//		        	Files.delete(FileSystems.getDefault().getPath(directory));
		        	
		        	String filepath = Paths.get(directory, filename).toString();
	    		    
	    		    // Save the file locally
	    		    BufferedOutputStream stream =
	    		        new BufferedOutputStream(new FileOutputStream(new File(filepath)));
	    		    stream.write(formCEvidenciasFotograficas.getBytes());
	    		    stream.close();
		        }
			    resultVO.setCodigo((long) 0);
			    resultVO.setMensaje("Exito File Upload");
			    
			    //copia archivo a documentacion para descarga
//			    try {
//					copyArchivos("/uniprotec/entregables/"+idEmpresa+"/"+idEntregable ,idEmpresa, idEntregable, filename);
//				} catch (Exception e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
			    
			  }
			  catch (Exception e) {
			    log.info("exception : "+e.getMessage());
			    resultVO.setCodigo((long) 99);
			    resultVO.setMensaje(e.getMessage());
			    return resultVO;//new ResultVO(99, "fallo");
			  }
		      return resultVO;//new ResultVO(1, "ExitoFileUpload");
	}

	
	@RequestMapping(method = RequestMethod.POST, path = "/fileUploadEntregables/{idEmpresa}/{idEntregable}",  consumes = "multipart/form-data", produces = "application/json")
	public ResultVO fileUploadEntregables(@PathVariable String idEmpresa, @PathVariable String idEntregable, @RequestParam("formCEvidenciaDocto") MultipartFile formCEvidenciaDocto){
	   try {
		   log.info(idEmpresa);
		    // Get the filename and build the local file path (be sure that the 
		    // application have write permissions on such directory)
		    String filename = formCEvidenciaDocto.getOriginalFilename();
		    
		    String directory = "/uniprotec/entregables/"+idEmpresa+"/"+idEntregable +"/file/";
		    File directorio = new File(directory);
		    FileUtils.deleteDirectory(directorio);
		    
	        if (!directorio.exists()) {
	            if (directorio.mkdirs()) {
	                log.info("Directorio creado");
	    		    String filepath = Paths.get(directory, filename).toString();
	    		    // Save the file locally
	    		    BufferedOutputStream stream =
	    		        new BufferedOutputStream(new FileOutputStream(new File(filepath)));
	    		    stream.write(formCEvidenciaDocto.getBytes());
	    		    stream.close();
	            } else {
	            	log.info("Error al crear directorio");
	            }
	        }else {
	        	log.info("Directorio Ya existe");
//	        	Files.delete(FileSystems.getDefault().getPath(directory));
	        	
	        	String filepath = Paths.get(directory, filename).toString();
    		    
    		    // Save the file locally
    		    BufferedOutputStream stream =
    		        new BufferedOutputStream(new FileOutputStream(new File(filepath)));
    		    stream.write(formCEvidenciaDocto.getBytes());
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
	

		

	@GetMapping("/uploadsEntregables/{idEmpresa}/{idEntregable}/{tipoCarpeta}/{nombreFile:.+}")
	public ResponseEntity<Resource> verImagenEntregables(@PathVariable String idEmpresa, @PathVariable String idEntregable, @PathVariable String tipoCarpeta, @PathVariable String nombreFile){
		
		Path rutaArchivo = Paths.get("/uniprotec/entregables/"+idEmpresa+"/"+idEntregable +"/"+tipoCarpeta+"/").resolve(nombreFile).toAbsolutePath();
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
	
	@GetMapping("/verEntregable/{idEntregable}")
	public ResponseEntity<Resource> verFileEntregable( @PathVariable String idEntregable) throws Exception{
		
		log.info("verFileEntregable");
		String[] tmp = idEntregable.split("-");
		String idEmpresa =tmp[0];
		idEntregable =tmp[1];
		String pathLogico = "/uniprotec/entregables/"+idEmpresa+"/"+idEntregable;
		
//		/*
//         * comprimir archivos files a documentacion/evidenciaDocto.zip 
//         */
//		 	String directory = pathLogico+"/file/";
//	        File directorio = new File(directory);
//	        if (directorio.exists()) {
//	        	compressFile(idEmpresa, idEntregable);
//	        }
//		
//        /*
//         * comprimir archivos zip 
//         */
//		compress(idEmpresa, idEntregable);
		
		Path rutaArchivo = Paths.get(pathLogico +"/zip/").resolve(idEntregable+".zip").toAbsolutePath();
		log.info(rutaArchivo.toString());
		
		Resource recurso = null;
		
		try {
			recurso = new UrlResource(rutaArchivo.toUri());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
		if(!recurso.exists() && !recurso.isReadable()) {
			throw new RuntimeException("Error no se pudo cargar la Archivo: " + idEntregable);
		}
		HttpHeaders cabecera = new HttpHeaders();
		cabecera.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + recurso.getFilename() + "\"");
		
		
		
		return new ResponseEntity<Resource>(recurso, cabecera, HttpStatus.OK);
	}
	
	

	
	
	/*
	 * FIRMA INSTRUCTOR
	 */
	
	@RequestMapping(method = RequestMethod.POST, path = "/enviaFirmaInstructor/{idInstructor}",  consumes = "multipart/form-data", produces = "application/json")
	public ResultVO enviaFirmaInstructor(@PathVariable String idInstructor, @RequestParam("firmaInstructor") MultipartFile firmaInstructor){
	   try {
		   log.info(idInstructor);
		    // Get the filename and build the local file path (be sure that the 
		    // application have write permissions on such directory)
		    String filename = firmaInstructor.getOriginalFilename();

		    String directory = "/uniprotec/firmaInstructor/"+idInstructor+"/image/";
	        File directorio = new File(directory);
	        if (!directorio.exists()) {
	            if (directorio.mkdirs()) {
	                log.info("Nuevo Directorio creado");
	    		    String filepath = Paths.get(directory, filename).toString();
	    		    
	    		    // Save the file locally
	    		    BufferedOutputStream stream =
	    		        new BufferedOutputStream(new FileOutputStream(new File(filepath)));
	    		    stream.write(firmaInstructor.getBytes());
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
    		    stream.write(firmaInstructor.getBytes());
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
	

	
	@GetMapping("/firmainstructor/{idInstructor}/{nombreFoto:.+}")
	public ResponseEntity<Resource> verFirmainstructor(@PathVariable String idInstructor, @PathVariable String nombreFoto){
		
		Path rutaArchivo = Paths.get("/uniprotec/firmaInstructor/"+idInstructor+"/image/").resolve(nombreFoto).toAbsolutePath();
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

	@RequestMapping(method = RequestMethod.POST, path = "/fileParticipantesExcel/{idEmpresa}/{idEntregable}",  consumes = "multipart/form-data", produces = "application/json")
	public ResultVO fileUploadParticipantesExcel(@PathVariable String idEmpresa, @PathVariable String idEntregable, @RequestParam("importarParticipantesExcel") MultipartFile importarParticipantesExcel){
	   try {
		   log.info(idEmpresa);
		    // Get the filename and build the local file path (be sure that the 
		    // application have write permissions on such directory)
		    String filename = importarParticipantesExcel.getOriginalFilename();
		    
		    String directory = "/uniprotec/entregables/"+idEmpresa+"/"+idEntregable +"/importarParticipantes/";
		    File directorio = new File(directory);
		    FileUtils.deleteDirectory(directorio);
		    String filepath = "";
	        if (!directorio.exists()) {
	            if (directorio.mkdirs()) {
	                log.info("Directorio creado");
	    		    filepath = Paths.get(directory, filename).toString();
	    		    // Save the file locally
	    		    BufferedOutputStream stream =
	    		        new BufferedOutputStream(new FileOutputStream(new File(filepath)));
	    		    stream.write(importarParticipantesExcel.getBytes());
	    		    stream.close();
	            } else {
	            	log.info("Error al crear directorio");
	            }
	        }else {
	        	log.info("Directorio Ya existe");       	
	        	filepath = Paths.get(directory, filename).toString();
    		    
    		    // Save the file locally
    		    BufferedOutputStream stream =
    		        new BufferedOutputStream(new FileOutputStream(new File(filepath)));
    		    stream.write(importarParticipantesExcel.getBytes());
    		    stream.close();
	        }
	        
	        List<ParticipantesModelo> ltPE = lecturaExcel(filepath);
	        
	        JSONObject jsonResponse = new JSONObject();
	        jsonResponse.put("participantesImportados", ltPE);
	        resultVO.setJsonResponse(jsonResponse);
		    
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
	
	@GetMapping("/verParticipantesExcelMuestra")
	public ResponseEntity<Resource> verParticipantesExcelMuestra(){
		
		Path rutaArchivo = Paths.get("/uniprotec/recursos/verParticipantesExcelMuestra.xlsx").toAbsolutePath();
		log.info(rutaArchivo.toString());
		
		Resource recurso = null;
		
		try {
			recurso = new UrlResource(rutaArchivo.toUri());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
		if(!recurso.exists() && !recurso.isReadable()) {
			throw new RuntimeException("Error no se pudo cargar la imagen: " );
		}
		HttpHeaders cabecera = new HttpHeaders();
		cabecera.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + recurso.getFilename() + "\"");
		
		return new ResponseEntity<Resource>(recurso, cabecera, HttpStatus.OK);
	}

	
	@PostMapping("/descargaAsignaciones")
	public ModelAndView descargaAsignaciones(@ModelAttribute("asignacionesDescargaForm") AsignacionModeloDescarga asignacionesDescarga, ModelMap model) { 
		log.info("descargaAsignaciones");
		ResultVO resultVO = (ResultVO)model.get("model");
		model.addAttribute("model", resultVO);
		ResultVO rs = aplicacionService.descargaAsignaciones(asignacionesDescarga);
		ModelAndView mav = new ModelAndView("redirect:/CCliente", model);
		if(rs.getCodigo() != 500) {
//			resultVO.setJsonResponseObject(rs.getJsonResponseObject());
			mav.addObject("ejecucion", true);
		}else {
			mav.addObject("error", true);
		}
		return mav;
	}

	@GetMapping("/descargaAsignaciones")
	public ResponseEntity<Resource> detDescargaAsignaciones() { 
		Path rutaArchivo = Paths.get("/uniprotec/descargaAsignaciones/descargaAsignaciones.csv").toAbsolutePath();
		log.info(rutaArchivo.toString());
		
		Resource recurso = null;
		
		try {
			recurso = new UrlResource(rutaArchivo.toUri());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
		if(!recurso.exists() && !recurso.isReadable()) {
			throw new RuntimeException("Error no se pudo cargar la imagen: " );
		}
		HttpHeaders cabecera = new HttpHeaders();
		cabecera.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + recurso.getFilename() + "\"");
		
		return new ResponseEntity<Resource>(recurso, cabecera, HttpStatus.OK);
	}

	
	/*
	 * Private
	 */
	/**
     * Resizes an image to a absolute width and height (the image may not be
     * proportional)
     * @param inputImagePath Path of the original image
     * @param outputImagePath Path to save the resized image
     * @param scaledWidth absolute width in pixels
     * @param scaledHeight absolute height in pixels
     * @throws IOException
     */
    private static void resize(String inputImagePath)
            throws IOException {
    	 String outputImagePath = inputImagePath;
    	 int scaledWidth = 530;
    	 int scaledHeight = 680;
    	 
    	 System.out.println("inicio resize");
        // reads input image
        File inputFile = new File(inputImagePath);
        BufferedImage inputImage = ImageIO.read(inputFile);
 
        // creates output image
        BufferedImage outputImage = new BufferedImage(scaledWidth,
                scaledHeight, inputImage.getType());
 
        // scales the input image to the output image
        Graphics2D g2d = outputImage.createGraphics();
        g2d.drawImage(inputImage, 0, 0, scaledWidth, scaledHeight, null);
        g2d.dispose();
 
        // extracts extension of output file
        String formatName = outputImagePath.substring(outputImagePath
                .lastIndexOf(".") + 1);
 
        // writes to output file
        ImageIO.write(outputImage, formatName, new File(outputImagePath));
        System.out.println("fin resize");
    }
    
    
//    private void copyArchivos(String pathLogico, String idEmpresa, String idEntregable, String fileName) throws Exception {
//    	
//		Path origenPath = FileSystems.getDefault().getPath( pathLogico +"/externo/"+fileName);
//	    Path destinoPath = FileSystems.getDefault().getPath( pathLogico +"/documentacion/"+fileName);
//
//	    try {
//	        Files.copy(origenPath, destinoPath, StandardCopyOption.REPLACE_EXISTING);
//	    } catch (IOException e) {
//	      System.err.println(e);
//	    }	    
//	}
    
    
    
    
       
    private void crearDirectoriosDocumentacion(String idEmpresa, String idEntregable) {
		String directory = "/uniprotec/entregables/"+idEmpresa+"/"+idEntregable +"/documentacion/";
        File directorio = new File(directory);
        if (!directorio.exists()) {
            if (directorio.mkdirs()) {
                log.info("Nuevo Directorio creado documentacion");
    		
            } else {
            	log.info("Error al crear directorio documentacion");
            }
        }
        directory = "/uniprotec/entregables/"+idEmpresa+"/"+idEntregable +"/zip/";
        directorio = new File(directory);
        if (!directorio.exists()) {
            if (directorio.mkdirs()) {
                log.info("Nuevo Directorio creado zip");
    		
            } else {
            	log.info("Error al crear directorio zip");
            }
        }
	}
    
	private List<ParticipantesModelo> lecturaExcel(String pathExcel) {
		
		 
//		 File f = new File("C:/Test/testExcel/ejemplo expediente participantes.xlsx");
		 File f = new File(pathExcel);
	       InputStream inp = null;
		try {
			inp = new FileInputStream(f);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	       Workbook wb = null;
		try {
			wb = WorkbookFactory.create(inp);
		} catch (EncryptedDocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	       Sheet sheet = wb.getSheetAt(0);
	       
	       int iRow = 1;
	       Row row = sheet.getRow(iRow); //En qué fila empezar ya dependerá también de si tenemos, por ejemplo, el título de cada columna en la primera fila
	       List<ParticipantesModelo> ltPE = new ArrayList<ParticipantesModelo>();
	       while(row!=null) 
	       {
	    	   ParticipantesModelo pe = new ParticipantesModelo();;
	    	   for(int iCell=0;iCell<7;iCell++) {
	    		   Cell cell = row.getCell(iCell);
	    		   CellType ct = cell.getCellType();
	    		   String valueOficial = "";
	    		   Double dlvalueOficial = 0.0;
	    		   if(ct.name().equals("STRING")) {
	    			   String value = cell.getStringCellValue();
	    			   valueOficial = value;
	    		   }else{
	    			   Double value = (Double)cell.getNumericCellValue();
	    			   dlvalueOficial = value;
	    		   }
	    		   if(iCell == 0) {
	    			 pe.setParticipanteNombre(valueOficial);  
	    		   }else if(iCell == 1) {
	    			 pe.setParticipanteCURP(valueOficial);  
	    		   }else if(iCell == 2) {
	    			 pe.setParticipantePuesto(valueOficial);  
	    		   }else if(iCell == 3) {
	    			 pe.setParticipanteExamenTeoricoInicial(String.valueOf(dlvalueOficial));  
	    		   }else if(iCell == 4) {
		    			 pe.setParticipanteExamenTeoricoFinal(String.valueOf(dlvalueOficial));
	    		   }else if(iCell == 5) {
		    			 pe.setParticipanteExamenPractico(String.valueOf(dlvalueOficial));
	    		   }else if(iCell == 6) {
		    			 pe.setParticipantePromedio(String.valueOf(dlvalueOficial));
	    		   } 
	    	   }
	    	   ltPE.add(pe);
	    	   iRow++;  
	           row = sheet.getRow(iRow);
	       }
	       return ltPE;
	}



}
