package mx.uniprotec.application.controller;

import java.io.IOException;
import java.net.MalformedURLException;
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

import mx.uniprotec.application.entity.Curso;
import mx.uniprotec.application.entity.Instructor;
import mx.uniprotec.application.entity.Region;
import mx.uniprotec.application.entity.ResponseGeneral;
import mx.uniprotec.application.service.IAplicacionService;
import mx.uniprotec.application.service.IInstructorService;
import mx.uniprotec.application.service.IUploadFileService;
import mx.uniprotec.application.util.UtilController;
import mx.uniprotec.entidad.modelo.InstructorModelo;

@CrossOrigin(origins = { "http://localhost:8080" })
@RestController
@RequestMapping("/crud")
public class InstructorRestController {
	
	@Autowired
	private ResponseGeneral rg;

	@Autowired
	private IInstructorService instructorService;
	
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
//		return instructorService.findAll();
		return UtilController.responseGeneric(instructorService.findAll(), "instructores", HttpStatus.ACCEPTED);
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
		
		HttpStatus status ;
		Instructor instructor = null;
		Map<String, Object> response = new HashMap<>();
		
		try {
			instructor = instructorService.findById(id);
			status = HttpStatus.OK;
		} catch(DataAccessException e) {
			status = HttpStatus.INTERNAL_SERVER_ERROR;
//			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if(instructor == null) {
			status = HttpStatus.NOT_FOUND;
//			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
//		status =  HttpStatus.OK;
//		return UtilController.responseGeneric(response, instructor, "instructor");
		return UtilController.responseGeneric(instructor, "instructor", status);
	}
	
	
	
	 /*
	  * 
	  */
	@SuppressWarnings("null")
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
			instructorNew.setRegionInstructor(region);
			instructorNew.setCursosInstructor(instructor.getListCursoInstructor().toString());
			instructorNew.setNotaInstructor(instructor.getNotaInstructor());
			instructorNew.setCreateAtInstructor(instructor.getCreateAtInstructor());
			instructorNew.setStatusInstructor(instructor.getStatusInstructor());
			instructorNew.setUserCreateInstructor(instructor.getUserCreateInstructor());
			
			instructorNew = instructorService.save(instructorNew);
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			status = HttpStatus.INTERNAL_SERVER_ERROR;
//			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "El instructor ha sido creado con éxito!");
		response.put("instructor", instructorNew);
//		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
		status = HttpStatus.CREATED;
		return UtilController.responseGeneric(instructor, "instructor", status);
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

		if(result.hasErrors()) {

			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() +"' "+ err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
//			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
			status = HttpStatus.BAD_REQUEST;
		}
		
		if (instructorActual == null) {
			response.put("mensaje", "Error: no se pudo editar, el instructor ID: "
					.concat(id.toString().concat(" no existe en la base de datos!")));
//			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			status = HttpStatus.NOT_FOUND;
		}

		try {
			Region region = aplicacionService.findRegion(instructor.getRegionInstructor());
			
			instructorActual.setNombreInstructor(instructor.getNombreInstructor());
			instructorActual.setEmailInstructor(instructor.getEmailInstructor());
			instructorActual.setRegionInstructor(region);
			instructorActual.setCursosInstructor(instructor.getListCursoInstructor().toString());
			instructorActual.setNotaInstructor(instructor.getNotaInstructor());
			instructorActual.setCreateAtInstructor(instructor.getCreateAtInstructor());
			instructorActual.setStatusInstructor(instructor.getStatusInstructor());
			instructorActual.setUserCreateInstructor(instructor.getUserCreateInstructor());
			
			instructorUpdated = instructorService.save(instructorActual);

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al actualizar el instructor en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
//			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "El instructor ha sido actualizado con éxito!");
		response.put("instructor", instructorUpdated);

//		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
		status = HttpStatus.CREATED;
		return UtilController.responseGeneric(instructorUpdated, "instructor", status);
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
	
	
	/*
	 * 
	 */
	@PostMapping("/instructor/upload")
	public ResponseEntity<?> upload(@RequestParam("archivo") MultipartFile archivo, @RequestParam("id") Long id){
		Map<String, Object> response = new HashMap<>();
		
		Instructor instructor = instructorService.findById(id);
		
		if(!archivo.isEmpty()) {

			String nombreArchivo = null;
			try {
				nombreArchivo = uploadService.copiar(archivo);
			} catch (IOException e) {
				response.put("mensaje", "Error al subir la imagen del instructor");
				response.put("error", e.getMessage().concat(": ").concat(e.getCause().getMessage()));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			
			String nombreFotoAnterior = "";//instructor.getFoto();
			
			uploadService.eliminar(nombreFotoAnterior);
						
//			instructor.setFoto(nombreArchivo);
			
			instructorService.save(instructor);
			
			response.put("instructor", instructor);
			response.put("mensaje", "Has subido correctamente la imagen: " + nombreArchivo);
			
		}
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
//	@GetMapping("/uploads/img/{nombreFoto:.+}")
//	public ResponseEntity<Resource> verFoto(@PathVariable String nombreFoto){
//
//		Resource recurso = null;
//		
//		try {
//			recurso = uploadService.cargar(nombreFoto);
//		} catch (MalformedURLException e) {
//			e.printStackTrace();
//		}
//		
//		HttpHeaders cabecera = new HttpHeaders();
//		cabecera.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + recurso.getFilename() + "\"");
//		
//		return new ResponseEntity<Resource>(recurso, cabecera, HttpStatus.OK);
//	}
	
//	@GetMapping("/instructor/regiones")
//	public List<Region> listarRegiones(){
//		return instructorService.findAllRegiones();
//	}
}
