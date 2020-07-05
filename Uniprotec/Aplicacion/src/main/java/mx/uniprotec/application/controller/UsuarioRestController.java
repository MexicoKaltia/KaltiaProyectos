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
import org.springframework.core.NestedRuntimeException;
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
import mx.uniprotec.application.entity.Usuario;
import mx.uniprotec.application.entity.Instructor;
import mx.uniprotec.application.entity.Region;
import mx.uniprotec.application.entity.ResponseGeneral;
import mx.uniprotec.application.service.IUsuarioService;
import mx.uniprotec.application.service.IUploadFileService;
import mx.uniprotec.application.util.UtilController;
import mx.uniprotec.entidad.modelo.UsuarioModelo;

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
		List<Usuario> usuarios = null;
		Map<String, Object> response = new HashMap<>();
		try {
			usuarios = usuarioService.findAll();
			 response.put("usuarios", usuarios);
			 response.put("mensaje", "Exito en la busqueda de usuarios");
			 response.put("status", HttpStatus.ACCEPTED);
			 response.put("code", HttpStatus.ACCEPTED.value());
			 return new ResponseEntity<Map<String, Object>>(response, HttpStatus.ACCEPTED);
		} catch (Exception e) {
			response.put("mensaje", e.getMessage().concat(": ").concat(((NestedRuntimeException) e).getMostSpecificCause().getMessage()));
			response.put("status", HttpStatus.INTERNAL_SERVER_ERROR);
			 response.put("code", HttpStatus.INTERNAL_SERVER_ERROR.value());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
//		return UtilController.responseGeneric(usuarioService.findAll(), "usuarios", HttpStatus.ACCEPTED);
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
			if(usuario == null) {
				response.put("mensaje", "Error: no se pudo editar, el usuario ID: "
						.concat(id.toString().concat(" no existe en la base de datos!")));
				response.put("status", HttpStatus.NOT_FOUND);
				 response.put("code", HttpStatus.NOT_FOUND.value());
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			}
			response.put("usuario", usuario);
			 response.put("mensaje", "Exito en la busqueda de usuario");
			 response.put("status", HttpStatus.ACCEPTED);
			 response.put("code", HttpStatus.ACCEPTED.value());
			 return new ResponseEntity<Map<String, Object>>(response, HttpStatus.ACCEPTED);
		} catch(DataAccessException e) {
			response.put("mensaje", e.getMessage().concat(": ").concat(((NestedRuntimeException) e).getMostSpecificCause().getMessage()));
			response.put("status", HttpStatus.INTERNAL_SERVER_ERROR);
			 response.put("code", HttpStatus.INTERNAL_SERVER_ERROR.value());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	
	
	 /*
	  * 
	  */
	@PostMapping("/usuario")
	public ResponseEntity<?> create(@Valid @RequestBody UsuarioModelo usuario, BindingResult result) {
		
		HttpStatus status ;
		Usuario usuarioNew = new Usuario();
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
			usuarioNew.setUsernameUsuario(usuario.getUsernameUsuario());
			usuarioNew.setPasswordUsuario(usuario.getPasswordUsuario());
			usuarioNew.setPerfilUsuario(usuario.getPerfilUsuario());
			usuarioNew.setNombreUsuario(usuario.getNombreUsuario());
			usuarioNew.setEmailUsuario(usuario.getEmailUsuario());
			usuarioNew.setNotaUsuario(usuario.getNotaUsuario());
			usuarioNew.setCreateAtUsuario(usuario.getCreateAtUsuario());
			usuarioNew.setUserCreateUsuario(usuario.getUserCreateUsuario());
			usuarioNew.setStatusUsuario(usuario.getStatusUsuario());
			
			usuarioNew = usuarioService.save(usuarioNew);
			response.put("usuario", usuarioNew);
			 response.put("mensaje", "Usuario creado con exito");
			 response.put("status", HttpStatus.CREATED);
			 response.put("code", HttpStatus.CREATED.value());
			 return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
		} catch(DataAccessException e) {
			response.put("mensaje", e.getMessage().concat(": ").concat(((NestedRuntimeException) e).getMostSpecificCause().getMessage()));
			response.put("status", HttpStatus.INTERNAL_SERVER_ERROR);
			 response.put("code", HttpStatus.INTERNAL_SERVER_ERROR.value());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	/*
	 * 
	 */
	@PutMapping("/usuario/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody UsuarioModelo usuario, BindingResult result, @PathVariable Long id) {

		HttpStatus status ;
		Usuario usuarioActual = usuarioService.findById(id);

		Usuario usuarioUpdated = null;

		Map<String, Object> response = new HashMap<>();

		if(result.hasErrors()) {

			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() +"' "+ err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("mensaje", errors);
			 response.put("status", HttpStatus.BAD_REQUEST);
			 response.put("code", HttpStatus.BAD_REQUEST.value());
			 return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		if (usuarioActual == null) {
			response.put("mensaje", "Error: no se pudo editar, el usuario ID: "
					.concat(id.toString().concat(" no existe en la base de datos!")));
//						 response.put("status", HttpStatus.CREATED);
			 response.put("code", HttpStatus.NOT_FOUND.value());
			 return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		try {

			usuarioActual.setUsernameUsuario(usuario.getUsernameUsuario());
			usuarioActual.setPasswordUsuario(usuario.getPasswordUsuario());
			usuarioActual.setPerfilUsuario(usuario.getPerfilUsuario());
			usuarioActual.setNombreUsuario(usuario.getNombreUsuario());
			usuarioActual.setEmailUsuario(usuario.getEmailUsuario());
			usuarioActual.setNotaUsuario(usuario.getNotaUsuario());
			usuarioActual.setCreateAtUsuario(usuario.getCreateAtUsuario());
			usuarioActual.setCreateAtUsuario(usuario.getCreateAtUsuario());
			usuarioActual.setStatusUsuario(usuario.getStatusUsuario());

			usuarioUpdated = usuarioService.save(usuarioActual);
			response.put("usuario", usuarioUpdated);
			 response.put("mensaje", "Usuario actualizado con exito");
			 response.put("status", HttpStatus.CREATED);
			 response.put("code", HttpStatus.CREATED.value());
			 return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);


		} catch (DataAccessException e) {
			response.put("mensaje", e.getMessage().concat(": ").concat(((NestedRuntimeException) e).getMostSpecificCause().getMessage()));
			response.put("status", HttpStatus.INTERNAL_SERVER_ERROR);
			 response.put("code", HttpStatus.INTERNAL_SERVER_ERROR.value());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

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
	
	
}
