package mx.uniprotec.application.controller;

import java.util.ArrayList;
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
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
import org.springframework.web.bind.annotation.RestController;

import mx.uniprotec.application.entity.Curso;
import mx.uniprotec.application.entity.Instructor;
import mx.uniprotec.application.entity.Region;
import mx.uniprotec.application.service.IAplicacionService;
import mx.uniprotec.application.service.IInstructorService;
import mx.uniprotec.application.service.IUploadFileService;
import mx.uniprotec.application.service.IUsuarioService;
import mx.uniprotec.application.util.UtilController;
import mx.uniprotec.entidad.modelo.InstructorModelo;

@CrossOrigin(origins = { "*" })
@RestController
@RequestMapping("/crud")
public class InstructorRestController {
	
	@Autowired
	private IInstructorService instructorService;
	@Autowired
	private IUsuarioService usuarioService;
	
	@Autowired
	private IUploadFileService uploadService;
	
	@Autowired
	private IAplicacionService aplicacionService;

	
	 private final Logger log = LoggerFactory.getLogger(InstructorRestController.class);

	 /*
	  * 
	  */
	@GetMapping("/instructores")
	public ResponseEntity<?> index() {
		List<Instructor> instructores = null;
		Map<String, Object> response = new HashMap<>();
		try {
			instructores = instructorService.findAll();
			 response.put("instructores", instructores);
			 response.put("mensaje", "Exito en la busqueda de instructores");
			 response.put("status", HttpStatus.ACCEPTED);
			 response.put("code", HttpStatus.ACCEPTED.value());
			 return new ResponseEntity<Map<String, Object>>(response, HttpStatus.ACCEPTED);
		} catch (Exception e) {
			response.put("mensaje", e.getMessage().concat(": ").concat(((NestedRuntimeException) e).getMostSpecificCause().getMessage()));
			response.put("status", HttpStatus.INTERNAL_SERVER_ERROR);
			 response.put("code", HttpStatus.INTERNAL_SERVER_ERROR.value());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
//		return UtilController.responseGeneric(instructorService.findAll(), "instructores", HttpStatus.ACCEPTED);
	}
	
	
	 /*
	  * 
	  */
	@GetMapping("/instructores/page/{page}")
	public ResponseEntity<?> index(@PathVariable Integer page) {
		Pageable pageable = PageRequest.of(page, 4);
//		return instructorService.findAll(pageable);
		return UtilController.responseGeneric(instructorService.findAll(pageable), "instructores",HttpStatus.ACCEPTED);
	}
	
	
	 /*
	  * 
	  */
	@GetMapping("/instructor/{id}")
	public ResponseEntity<?> show(@PathVariable Long id) {
		
		Instructor instructor = null;
		Map<String, Object> response = new HashMap<>();
		
		try {
			instructor = instructorService.findById(id);
			if(instructor == null) {
				response.put("mensaje", "Error: no se pudo editar, el usuario ID: "
						.concat(id.toString().concat(" no existe en la base de datos!")));
				response.put("status", HttpStatus.NOT_FOUND);
				 response.put("code", HttpStatus.NOT_FOUND.value());
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			}
			 response.put("instructor", instructor);
			 response.put("mensaje", "Exito en la busqueda de instructor");
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
	@PostMapping("/instructor")
	public ResponseEntity<?> create(@Valid @RequestBody InstructorModelo instructor, BindingResult result) {
		
		HttpStatus status ;
		Instructor instructorNew = new Instructor();
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
	Region region = aplicacionService.findRegion(instructor.getRegionInstructor());
	List<Curso> cursos = new ArrayList<Curso>();
	
	for(Long idCurso : instructor.getListCursoInstructor()) {
		Curso curso = new Curso(idCurso);
		cursos.add(curso);
	}
			
			instructorNew.setNombreInstructor(instructor.getNombreInstructor());
			instructorNew.setEmailInstructor(instructor.getEmailInstructor());
			instructorNew.setEmailGmailInstructor(instructor.getEmailGmailInstructor());
			instructorNew.setRegionInstructor(region);
			instructorNew.setCursosInstructor(instructor.getListCursoInstructor().toString());
			instructorNew.setUsuarioInstructor(usuarioService.findById(instructor.getUsuarioInstructor()));
			instructorNew.setNotaInstructor(instructor.getNotaInstructor());
			instructorNew.setCreateAtInstructor(instructor.getCreateAtInstructor());
			instructorNew.setStatusInstructor(instructor.getStatusInstructor());
			instructorNew.setUserCreateInstructor(instructor.getUserCreateInstructor());
			
			instructorNew = instructorService.save(instructorNew);
			
			 response.put("instructor", instructorNew);
			 response.put("mensaje", "Se ha generado con Exito el Instructor");
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
	@PutMapping("/instructor/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody InstructorModelo instructor, BindingResult result, @PathVariable Long id) {

		HttpStatus status ;
		Instructor instructorActual = instructorService.findById(id);
		Instructor instructorUpdated = null;
		Map<String, Object> response = new HashMap<>();
		log.info("Actualizar Instructor");

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
		
		if (instructorActual == null) {
			log.info("Error en busqueda de Instructor");
			response.put("mensaje", "Error: no se pudo editar, el instructor ID: "
					.concat(id.toString().concat(" no existe en la base de datos!")));
			response.put("status", HttpStatus.NOT_FOUND);
			 response.put("code", HttpStatus.NOT_FOUND.value());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		try {
			Region region = aplicacionService.findRegion(instructor.getRegionInstructor());
			
			instructorActual.setNombreInstructor(instructor.getNombreInstructor());
			instructorActual.setEmailInstructor(instructor.getEmailInstructor());
			instructorActual.setEmailGmailInstructor(instructor.getEmailGmailInstructor());
			instructorActual.setRegionInstructor(region);
//			instructorActual.setUsuarioInstructor(usuarioService.findById(instructor.getUsuarioInstructor()));
			instructorActual.setCursosInstructor(instructor.getListCursoInstructor().toString());
			instructorActual.setListFechas(UtilController.listToString(instructor.getListFechas()));
			log.info(instructorActual.getListFechas());
			instructorActual.setNotaInstructor(instructor.getNotaInstructor());
			instructorActual.setCreateAtInstructor(instructor.getCreateAtInstructor());
			instructorActual.setStatusInstructor(instructor.getStatusInstructor());
			instructorActual.setUserCreateInstructor(instructor.getUserCreateInstructor());
			
			instructorUpdated = instructorService.save(instructorActual);
			
			 response.put("instructor", instructorUpdated);
			 response.put("mensaje", "Se ha actualizado con Exito el Instructor");
			 response.put("status", HttpStatus.CREATED);
			 response.put("code", HttpStatus.CREATED.value());
			 return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);

		} catch (DataAccessException e) {
			
			response.put("mensaje", e.getMessage().concat(": ").concat(((NestedRuntimeException) e).getMostSpecificCause().getMessage()));
			response.put("status", HttpStatus.INTERNAL_SERVER_ERROR);
			 response.put("code", HttpStatus.INTERNAL_SERVER_ERROR.value());
			 e.printStackTrace();
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	
	/*
	 * 
	 */
	@DeleteMapping("/instructor/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		
		HttpStatus status ;
		
		Map<String, Object> response = new HashMap<>();
		Instructor instructor = null;
		try {
			 instructor = instructorService.findById(id);
			String nombreFotoAnterior = "";//instructor.getFoto();
			
			uploadService.eliminar(nombreFotoAnterior);
			
		    instructorService.delete(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al eliminar el instructor de la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
//			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			status =  HttpStatus.INTERNAL_SERVER_ERROR;
		}
		
		response.put("mensaje", "El instructor eliminado con éxito!");
		
//		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		status =  HttpStatus.OK;
		return UtilController.responseGeneric(instructor, "instructor", status);
	}
	
	

}
