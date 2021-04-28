package mx.uniprotec.gamerBack.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mx.uniprotec.entidad.modelo.UsuarioAdministrador;
import mx.uniprotec.entidad.modelo.UsuarioAudiencia;
import mx.uniprotec.entidad.modelo.UsuarioInstructor;
import mx.uniprotec.gamerBack.dao.IUsuarioDao;
import mx.uniprotec.gamerBack.entity.Usuario;
import mx.uniprotec.gamerBack.entity.UsuarioAdministradorEntity;
import mx.uniprotec.gamerBack.entity.UsuarioAudienciaEntity;
import mx.uniprotec.gamerBack.entity.UsuarioInstructorEntity;
import mx.uniprotec.gamerBack.service.IUsuarioService;

@CrossOrigin(origins = { "http://localhost:8020" })
@RestController
@RequestMapping("/crud")
public class UsuariosRestController {

	@Autowired
	private IUsuarioService usuarioService;
	
	@Autowired
	private IUsuarioDao usuarioDao;

	
	@Secured({"ROLE_ADMIN"})
	@GetMapping("/getUsuarios")
	public ResponseEntity<?> index() {
		
		Map<String, Object> response = new HashMap<>();
		List<Usuario> usuarios = new ArrayList<Usuario>();
		List<UsuarioAudienciaEntity> usuariosAudiencia = new ArrayList<UsuarioAudienciaEntity>();
		List<UsuarioInstructorEntity> usuariosInstructor = new ArrayList<UsuarioInstructorEntity>();
		List<UsuarioAdministradorEntity> usuariosAdministrador = new ArrayList<UsuarioAdministradorEntity>();
		try {
			usuarios = usuarioService.findAll();
			usuariosAudiencia = usuarioService.findAllAudiencia();
			usuariosInstructor = usuarioService.findAllInstructor();
			usuariosAdministrador = usuarioService.findAllAdministrador();
			
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			e.printStackTrace();
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		
		response.put("mensaje", "Los usuarios han sido consultados con éxito!");
		response.put("usuarios", usuarios);
		response.put("usuariosAudiencia", usuariosAudiencia);
		response.put("usuariosInstructor", usuariosInstructor);
		response.put("usuariosAdministrador", usuariosAdministrador);
		response.put("code", 200);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.ACCEPTED);
	}
	
	
	
//	@Secured({"ROLE_ADMIN", "ROLE_USER"})
//	@GetMapping("/usuarioss/{id}")
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
//			response.put("mensaje", "El usuarios ID: ".concat(id.toString().concat(" no existe en la base de datos!")));
//			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
//		}
//		
//		return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
//	}
	
	@Secured("ROLE_ADMIN")
	@PostMapping("/altaUsuariosAudiencia")
	public ResponseEntity<?> altaUsuariosAudiencia(@Valid @RequestBody UsuarioAudiencia usuario, BindingResult result) {
		
		Usuario usuarioNew = null;
		Map<String, Object> response = new HashMap<>();
		
		if(result.hasErrors()) {
			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() +"' "+ err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		try {
			usuarioNew = usuarioService.saveAudiencia(usuario);
			
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			e.printStackTrace();
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		
		response.put("mensaje", "El usuario Audiencia ha sido creado con éxito!");
		response.put("usuarios", usuarioNew);
		response.put("code", 200);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@Secured("ROLE_ADMIN")
	@PostMapping("/altaUsuariosInstructor")
	public ResponseEntity<?> altaUsuariosInstructor(@Valid @RequestBody UsuarioInstructor usuario, BindingResult result) {
		
		Usuario usuarioNew = null;
		Map<String, Object> response = new HashMap<>();
		
		if(result.hasErrors()) {
			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() +"' "+ err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		try {
			usuarioNew = usuarioService.saveInstructor(usuario);
			
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			e.printStackTrace();
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		
		response.put("mensaje", "El usuario Instructor se ha activado con éxito!");
		response.put("usuarios", usuarioNew);
		response.put("code", 200);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@Secured("ROLE_ADMIN")
	@PostMapping("/altaUsuariosAdministrador")
	public ResponseEntity<?> altaUsuariosAdministrador(@Valid @RequestBody UsuarioAdministrador usuario, BindingResult result) {
		
		Usuario usuarioNew = null;
		Map<String, Object> response = new HashMap<>();
		
		if(result.hasErrors()) {
			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() +"' "+ err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		try {
			usuarioNew = usuarioService.saveAdministrador(usuario);
			
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			e.printStackTrace();
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		
		response.put("mensaje", "El usuario Administrador se ha activado con éxito!");
		response.put("usuarios", usuarioNew);
		response.put("code", 200);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@Secured("ROLE_ADMIN")
	@PostMapping("/actualizaUsuariosInstructor")
	public ResponseEntity<?> actualizaUsuariosInstructor(@Valid @RequestBody UsuarioInstructor usuario, BindingResult result) {
		
		Usuario usuarioActual = null;
		Usuario usuarioNew = null;
		Map<String, Object> response = new HashMap<>();
		
		if(result.hasErrors()) {
			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() +"' "+ err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		try {
			usuarioNew = usuarioService.actualizaUsuarioInstructor(usuario);
			
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			e.printStackTrace();
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		
		response.put("mensaje", "El usuario Administrador se ha actualizado con éxito!");
		response.put("usuarios", usuarioNew);
		response.put("code", 200);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@Secured("ROLE_ADMIN")
	@PostMapping("/actualizaUsuariosAudiencia")
	public ResponseEntity<?> actualizaUsuariosAudiencia(@Valid @RequestBody UsuarioAudiencia usuario, BindingResult result) {
		
		Usuario usuarioActual = null;
		Usuario usuarioNew = null;
		Map<String, Object> response = new HashMap<>();
		
		if(result.hasErrors()) {
			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() +"' "+ err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		try {
			usuarioNew = usuarioService.actualizaUsuarioAudiencia(usuario);
			
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			e.printStackTrace();
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		
		response.put("mensaje", "El usuario Administrador se ha actualizado con éxito!");
		response.put("usuarios", usuarioNew);
		response.put("code", 200);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	
//	@Secured("ROLE_ADMIN")
//	@PutMapping("/usuarios/{id}")
//	public ResponseEntity<?> update(@Valid @RequestBody Usuario usuario, BindingResult result, @PathVariable Long id) {
//
//		Usuario usuariosActual = usuarioService.findById(id);
//		Usuario usuariosUpdated = null;
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
//		if (usuariosActual == null) {
//			response.put("mensaje", "Error: no se pudo editar, el usuarios ID: "
//					.concat(id.toString().concat(" no existe en la base de datos!")));
//			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
//		}
//
//		try {
//			usuariosActual.setNombre(usuario.getNombre());
//			usuariosUpdated = usuarioService.save(usuariosActual);
//
//		} catch (DataAccessException e) {
//			response.put("mensaje", "Error al actualizar el usuarios en la base de datos");
//			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
//			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//
//		response.put("mensaje", "El usuarios ha sido actualizado con éxito!");
//		response.put("usuarios", usuariosUpdated);
//
//		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
//	}
	
//	@Secured("ROLE_ADMIN")
//	@DeleteMapping("/usuarioss/{id}")
//	public ResponseEntity<?> delete(@PathVariable Long id) {
//		
//		Map<String, Object> response = new HashMap<>();
//		
//		try {
//			Usuario usuario = usuarioService.findById(id);
//			String nombreFotoAnterior ="";
//			
//			usuarioService.delete(id);
//		} catch (DataAccessException e) {
//			response.put("mensaje", "Error al eliminar el usuarios de la base de datos");
//			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
//			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//		
//		response.put("mensaje", "El usuarios eliminado con éxito!");
//		
//		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
//	}
//	
//		
	
}
