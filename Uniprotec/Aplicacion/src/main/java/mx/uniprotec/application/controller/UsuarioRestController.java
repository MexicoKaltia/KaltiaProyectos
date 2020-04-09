package mx.uniprotec.application.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import mx.uniprotec.application.entity.Usuario;
import mx.uniprotec.application.entity.Region;
import mx.uniprotec.application.entity.ResponseGeneral;
import mx.uniprotec.application.service.IUsuarioService;
import mx.uniprotec.application.service.IUploadFileService;
import mx.uniprotec.application.util.UtilController;

@CrossOrigin(origins = { "http://localhost:8080" })
@RestController
@RequestMapping("/crud")
public class UsuarioRestController {
	
	@Autowired
	private ResponseGeneral rg;

	@Autowired
	private IUsuarioService usuarioService;
	
	@Autowired
	private IUploadFileService uploadService;
	
	 private final Logger log = LoggerFactory.getLogger(UsuarioRestController.class);

	 /*
	  * 
	  */
	@GetMapping("/usuarios")
	public ResponseEntity<?> index() {
//		return usuarioService.findAll();
		return UtilController.responseGeneric(usuarioService.findAll(), "usuarios", HttpStatus.ACCEPTED);
	}
	
	
	 /*
	  * 
	  */
	@GetMapping("/usuarios/page/{page}")
	public ResponseEntity<?> index(@PathVariable Integer page) {
		Pageable pageable = PageRequest.of(page, 4);
//		return usuarioService.findAll(pageable);
		return UtilController.responseGeneric(usuarioService.findAll(pageable), "usuarios",HttpStatus.ACCEPTED);
	}
	
	
	 /*
	  * 
	  */
	@GetMapping("/usuario/{id}")
	public ResponseEntity<?> show(@PathVariable Long id) {
		
		HttpStatus status ;
		Usuario usuario = null;
		Map<String, Object> response = new HashMap<>();
		
		try {
			usuario = usuarioService.findById(id);
			status = HttpStatus.OK;
		} catch(DataAccessException e) {
			status = HttpStatus.INTERNAL_SERVER_ERROR;
//			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if(usuario == null) {
			status = HttpStatus.NOT_FOUND;
//			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
//		status =  HttpStatus.OK;
//		return UtilController.responseGeneric(response, usuario, "usuario");
		return UtilController.responseGeneric(usuario, "usuario", status);
	}
	
	
	
	 /*
	  * 
	  */
	@PostMapping("/usuario")
	public ResponseEntity<?> create(@Valid @RequestBody Usuario usuario, BindingResult result) {
		
		HttpStatus status ;
		Usuario usuarioNew = null;
		Map<String, Object> response = new HashMap<>();
		
		if(result.hasErrors()) {

			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() +"' "+ err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			status = HttpStatus.BAD_REQUEST;
//			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		try {
			usuarioNew = usuarioService.save(usuario);
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			status = HttpStatus.INTERNAL_SERVER_ERROR;
//			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "El usuario ha sido creado con éxito!");
		response.put("usuario", usuarioNew);
//		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
		status = HttpStatus.CREATED;
		return UtilController.responseGeneric(usuario, "usuario", status);
	}
	
	
	/*
	 * 
	 */
	@PutMapping("/usuario/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody Usuario usuario, BindingResult result, @PathVariable Long id) {

		HttpStatus status ;
		Usuario usuarioActual = usuarioService.findById(id);

		Usuario usuarioUpdated = null;

		Map<String, Object> response = new HashMap<>();

		if(result.hasErrors()) {

			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() +"' "+ err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
//			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
			status = HttpStatus.BAD_REQUEST;
		}
		
		if (usuarioActual == null) {
			response.put("mensaje", "Error: no se pudo editar, el usuario ID: "
					.concat(id.toString().concat(" no existe en la base de datos!")));
//			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			status = HttpStatus.NOT_FOUND;
		}

		try {

//			usuarioActual.setApellido(usuario.getApellido());
			usuarioActual.setUsername(usuario.getUsername());
//			usuarioActual.setEmail(usuario.getEmail());
//			usuarioActual.setCreateAt(usuario.getCreateAt());
//			usuarioActual.setRegion(usuario.getRegion());

			usuarioUpdated = usuarioService.save(usuarioActual);

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al actualizar el usuario en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
//			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "El usuario ha sido actualizado con éxito!");
		response.put("usuario", usuarioUpdated);

//		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
		status = HttpStatus.CREATED;
		return UtilController.responseGeneric(usuarioUpdated, "usuario", status);
	}
	
	
	/*
	 * 
	 */
	@DeleteMapping("/usuario/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		
		HttpStatus status ;
		
		Map<String, Object> response = new HashMap<>();
		Usuario usuario = null;
		try {
			 usuario = usuarioService.findById(id);
			String nombreFotoAnterior = "";//usuario.getFoto();
			
			uploadService.eliminar(nombreFotoAnterior);
			
		    usuarioService.delete(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al eliminar el usuario de la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
//			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			status =  HttpStatus.INTERNAL_SERVER_ERROR;
		}
		
		response.put("mensaje", "El usuario eliminado con éxito!");
		
//		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		status =  HttpStatus.OK;
		return UtilController.responseGeneric(usuario, "usuario", status);
	}
	
	
	/*
	 * 
	 */
	@PostMapping("/usuario/upload")
	public ResponseEntity<?> upload(@RequestParam("archivo") MultipartFile archivo, @RequestParam("id") Long id){
		Map<String, Object> response = new HashMap<>();
		
		Usuario usuario = usuarioService.findById(id);
		
		if(!archivo.isEmpty()) {

			String nombreArchivo = null;
			try {
				nombreArchivo = uploadService.copiar(archivo);
			} catch (IOException e) {
				response.put("mensaje", "Error al subir la imagen del usuario");
				response.put("error", e.getMessage().concat(": ").concat(e.getCause().getMessage()));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			
			String nombreFotoAnterior = "";//usuario.getFoto();
			
			uploadService.eliminar(nombreFotoAnterior);
						
//			usuario.setFoto(nombreArchivo);
			
			usuarioService.save(usuario);
			
			response.put("usuario", usuario);
			response.put("mensaje", "Has subido correctamente la imagen: " + nombreArchivo);
			
		}
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
}
//package mx.uniprotec.application.controller;
//
//import java.io.IOException;
//import java.net.MalformedURLException;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.stream.Collectors;
//
//import javax.validation.Valid;
//
////import org.slf4j.Logger;
////import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.io.Resource;
//import org.springframework.dao.DataAccessException;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.multipart.MultipartFile;
//
//import mx.uniprotec.application.entity.Usuario;
//import mx.uniprotec.application.entity.Region;
//import mx.uniprotec.application.service.IUsuarioService;
//import mx.uniprotec.application.service.IUploadFileService;
//
//@CrossOrigin(origins = { "http://localhost:8080" })
//@RestController
//@RequestMapping("/crud")
//public class UsuarioRestController {
//
//	@Autowired
//	private IUsuarioService usuarioService;
//	
//	@Autowired
//	private IUploadFileService uploadService;
//	
//	// private final Logger log = LoggerFactory.getLogger(usuarioRestController.class);
//
//	@GetMapping("/usuarios")
//	public List<Usuario> index() {
//		return usuarioService.findAll();
//	}
//	
//	@GetMapping("/usuarios/page/{page}")
//	public Page<Usuario> index(@PathVariable Long page) {
//		Pageable pageable = PageRequest.of(page, 4);
//		return usuarioService.findAll(pageable);
//	}
//	
//	@GetMapping("/usuario/{id}")
//	public ResponseEntity<?> show(@PathVariable Long id) {
//		
//		Usuario usuario = null;
//		Map<String, Object> response = new HashMap<>();
//		
//		try {
//			usuario = usuarioService.findById(id);
//		} catch(DataAccessException e) {
//			response.put("mensaje", "Error al realizar la consulta en la base de datos");
//			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
//			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//		
//		if(usuario == null) {
//			response.put("mensaje", "El usuario ID: ".concat(id.toString().concat(" no existe en la base de datos!")));
//			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
//		}
//		
//		return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
//	}
//	
//	@PostMapping("/usuario")
//	public ResponseEntity<?> create(@Valid @RequestBody Usuario usuario, BindingResult result) {
//		
//		Usuario usuarioNew = null;
//		Map<String, Object> response = new HashMap<>();
//		
//		if(result.hasErrors()) {
//
//			List<String> errors = result.getFieldErrors()
//					.stream()
//					.map(err -> "El campo '" + err.getField() +"' "+ err.getDefaultMessage())
//					.collect(Collectors.toList());
//			
//			response.put("errors", errors);
//			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
//		}
//		
//		try {
//			usuarioNew = usuarioService.save(usuario);
//		} catch(DataAccessException e) {
//			response.put("mensaje", "Error al realizar el insert en la base de datos");
//			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
//			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//		
//		response.put("mensaje", "El usuario ha sido creado con éxito!");
//		response.put("usuario", usuarioNew);
//		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
//	}
//	
//	@PutMapping("/usuario/{id}")
//	public ResponseEntity<?> update(@Valid @RequestBody Usuario usuario, BindingResult result, @PathVariable Long id) {
//
//		Usuario usuarioActual = usuarioService.findById(id);
//
//		Usuario usuarioUpdated = null;
//
//		Map<String, Object> response = new HashMap<>();
//
//		if(result.hasErrors()) {
//
//			List<String> errors = result.getFieldErrors()
//					.stream()
//					.map(err -> "El campo '" + err.getField() +"' "+ err.getDefaultMessage())
//					.collect(Collectors.toList());
//			
//			response.put("errors", errors);
//			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
//		}
//		
//		if (usuarioActual == null) {
//			response.put("mensaje", "Error: no se pudo editar, el usuario ID: "
//					.concat(id.toString().concat(" no existe en la base de datos!")));
//			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
//		}
//
//		try {
//
//			usuarioActual.setApellido(usuario.getApellido());
//			usuarioActual.setNombre(usuario.getNombre());
//			usuarioActual.setEmail(usuario.getEmail());
////			usuarioActual.setCreateAt(usuario.getCreateAt());
////			usuarioActual.setRegion(usuario.getRegion());
//
//			usuarioUpdated = usuarioService.save(usuarioActual);
//
//		} catch (DataAccessException e) {
//			response.put("mensaje", "Error al actualizar el usuario en la base de datos");
//			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
//			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//
//		response.put("mensaje", "El usuario ha sido actualizado con éxito!");
//		response.put("usuario", usuarioUpdated);
//
//		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
//	}
//	
//	@DeleteMapping("/usuario/{id}")
//	public ResponseEntity<?> delete(@PathVariable Long id) {
//		
//		Map<String, Object> response = new HashMap<>();
//		
//		try {
//			Usuario usuario = usuarioService.findById(id);
//			String nombreFotoAnterior = "";//usuario.getFoto();
//			
//			uploadService.eliminar(nombreFotoAnterior);
//			
//		    usuarioService.delete(id);
//		} catch (DataAccessException e) {
//			response.put("mensaje", "Error al eliminar el usuario de la base de datos");
//			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
//			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//		
//		response.put("mensaje", "El usuario eliminado con éxito!");
//		
//		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
//	}
//	
//	@PostMapping("/usuario/upload")
//	public ResponseEntity<?> upload(@RequestParam("archivo") MultipartFile archivo, @RequestParam("id") Long id){
//		Map<String, Object> response = new HashMap<>();
//		
//		Usuario usuario = usuarioService.findById(id);
//		
//		if(!archivo.isEmpty()) {
//
//			String nombreArchivo = null;
//			try {
//				nombreArchivo = uploadService.copiar(archivo);
//			} catch (IOException e) {
//				response.put("mensaje", "Error al subir la imagen del usuario");
//				response.put("error", e.getMessage().concat(": ").concat(e.getCause().getMessage()));
//				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
//			}
//			
//			String nombreFotoAnterior = "";//usuario.getFoto();
//			
//			uploadService.eliminar(nombreFotoAnterior);
//						
////			usuario.setFoto(nombreArchivo);
//			
//			usuarioService.save(usuario);
//			
//			response.put("usuario", usuario);
//			response.put("mensaje", "Has subido correctamente la imagen: " + nombreArchivo);
//			
//		}
//		
//		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
//	}
//	
////	@GetMapping("/uploads/img/{nombreFoto:.+}")
////	public ResponseEntity<Resource> verFoto(@PathVariable String nombreFoto){
////
////		Resource reusuario = null;
////		
////		try {
////			reusuario = uploadService.cargar(nombreFoto);
////		} catch (MalformedURLException e) {
////			e.printStackTrace();
////		}
////		
////		HttpHeaders cabecera = new HttpHeaders();
////		cabecera.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + reusuario.getFilename() + "\"");
////		
////		return new ResponseEntity<Resource>(reusuario, cabecera, HttpStatus.OK);
////	}
//	
////	@GetMapping("/usuarios/regiones")
////	public List<Region> listarRegiones(){
////		return usuarioService.findAllRegiones();
////	}
//}
