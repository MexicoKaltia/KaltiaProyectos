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
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
	
	
	/*
	 * Documentos Imagenes Entregables.
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
		    
		    //

		    String directory = "/uniprotec/entregables/"+idEmpresa+"/"+idEntregable +"/imagenesParticipantes/";
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


	@RequestMapping(method = RequestMethod.POST, path = "/imageUploadEvidencia/{idEmpresa}/{idEntregable}",  consumes = "multipart/form-data", produces = "application/json")
	public ResultVO imageUploadEvidencia(@PathVariable String idEmpresa, @PathVariable String idEntregable, @RequestParam("entregableEdicion[]") MultipartFile[] formCEvidenciasFotograficas){
		   log.info(idEmpresa);
		   String message = "";
		    try {
//		      List<String> fileNames = new ArrayList<>();
		      String directory = "/uniprotec/entregables/"+idEmpresa+"/"+idEntregable +"/imagenesEvidencias/";

		      Arrays.asList(formCEvidenciasFotograficas).stream().forEach(file -> {
		    	  File directorio = new File(directory);
			        if (!directorio.exists()) {
			            if (directorio.mkdirs()) {
			                log.info("Nuevo Directorio creado");
			                String filename = file.getOriginalFilename();
			    		    String filepath = Paths.get(directory, filename).toString();
			    		    
			    		    // Save the file locally
			    		    BufferedOutputStream stream = null;
							try {
								stream = new BufferedOutputStream(new FileOutputStream(new File(filepath)));
							} catch (FileNotFoundException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
			    		    try {
								stream.write(file.getBytes());
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
			    		    try {
								stream.close();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
			            } else {
			            	log.info("Error al crear directorio");
			            }
			        }else {
			        	log.info("Directorio Ya existe");
			        	String filename = file.getOriginalFilename();
			        	String filepath = Paths.get(directory, filename).toString();
		    		    
		    		    // Save the file locally
		    		    BufferedOutputStream stream = null;
						try {
							stream = new BufferedOutputStream(new FileOutputStream(new File(filepath)));
						} catch (FileNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
		    		    try {
							stream.write(file.getBytes());
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
		    		    try {
							stream.close();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
			        }
		      });
		    
	        
		    
		    
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

	
	@RequestMapping(method = RequestMethod.POST, path = "/fileUploadEntregables/{idEmpresa}/{idEntregable}",  consumes = "multipart/form-data", produces = "application/json")
	public ResultVO fileUploadEntregables(@PathVariable String idEmpresa, @PathVariable String idEntregable, @RequestParam("formCEvidenciaDocto") MultipartFile formCEvidenciaDocto){
	   try {
		   log.info(idEmpresa);
		    // Get the filename and build the local file path (be sure that the 
		    // application have write permissions on such directory)
		    String filename = formCEvidenciaDocto.getOriginalFilename();

		    String directory = "/uniprotec/entregables/"+idEmpresa+"/"+idEntregable +"/file/";
	        File directorio = new File(directory);
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
		
		String[] tmp = idEntregable.split("-");
		String idEmpresa =tmp[0];
		idEntregable =tmp[1];
		
		compress(idEmpresa, idEntregable);
		
		Path rutaArchivo = Paths.get("/uniprotec/entregables/"+idEmpresa+"/"+idEntregable +"/zip/").resolve(idEntregable+".zip").toAbsolutePath();
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
    
    
    
    private void compress(String idEmpresa, String idEntregable) throws Exception {
    	byte[] buffer = new byte[1024];
        
        try{
//          ZipOutputStream zos = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(new File("target/file.zip"))));
          
          FileOutputStream fos = new FileOutputStream("/uniprotec/entregables/"+idEmpresa+"/"+idEntregable+"/zip/"+idEntregable+".zip");
          
          ZipOutputStream zos = new ZipOutputStream(fos);
          try {    
              
              // create new file
        	  String rutaCarpeta = "/uniprotec/entregables/"+idEmpresa+"/"+idEntregable+"/documentacion/";
            File  f = new File(rutaCarpeta);
                                      
              // array of files and directory
            String[] paths = f.list();
                 
              // for each name in the path array
              for(String path:paths) {
              
                 // prints filename and directory name
            	  ZipEntry ze= new ZipEntry(path);
                  zos.putNextEntry(ze);
                  FileInputStream in = new FileInputStream(rutaCarpeta+path);
                  
                  int len;
                  while ((len = in.read(buffer)) > 0) {
                    zos.write(buffer, 0, len);
                  }
                  
                  
                  in.close();
              }
              
           } catch(Exception e) {
              // if any error occurs
              e.printStackTrace();
           }
          
          zos.closeEntry();
             
          zos.close();
            
          System.out.println("Hecho");
        }catch(IOException ex){
           ex.printStackTrace();
        }
      }
       
 


}
