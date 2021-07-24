package mx.uniprotec.application.controller;

import java.io.IOException;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import mx.uniprotec.application.entity.Cliente;
import mx.uniprotec.application.entity.Curso;
import mx.uniprotec.application.entity.Instructor;
import mx.uniprotec.application.entity.ResponseGeneral;
import mx.uniprotec.application.service.ICursoService;
import mx.uniprotec.application.service.IInstructorService;
import mx.uniprotec.application.service.IUploadFileService;
import mx.uniprotec.application.util.UtilController;
import mx.uniprotec.entidad.modelo.CursoModelo;

@CrossOrigin(origins = { "*" })
@RestController
@RequestMapping("/crud")
public class CursoRestController {
	
	@Autowired
	private ResponseGeneral rg;

	@Autowired
	private ICursoService cursoService;
	
	@Autowired
	private IUploadFileService uploadService;
	
	@Autowired
	IInstructorService instructorService;

	
	 private final Logger log = LoggerFactory.getLogger(CursoRestController.class);

	 /*
	  * 
	  */
	@GetMapping("/cursos")
	public ResponseEntity<?> index() {
		log.info("cursos:");
		List<Curso> cursos = null;
		Map<String, Object> response = new HashMap<>();
		try {
			cursos = cursoService.findAll();
			 response.put("cursos", cursos);
			 response.put("mensaje", "Exito en la busqueda de cursos");
			 response.put("status", HttpStatus.ACCEPTED);
			 response.put("code", HttpStatus.ACCEPTED.value());
			 log.info("cursos fin:");
			 return new ResponseEntity<Map<String, Object>>(response, HttpStatus.ACCEPTED);
		} catch (Exception e) {
			response.put("mensaje", e.getMessage().concat(": ").concat(((NestedRuntimeException) e).getMostSpecificCause().getMessage()));
			response.put("status", HttpStatus.INTERNAL_SERVER_ERROR);
			 response.put("code", HttpStatus.INTERNAL_SERVER_ERROR.value());
			 log.info("cursos fin:");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	 /*
	  * 
	  */
	@GetMapping("/cursos/page/{page}")
	public ResponseEntity<?> index(@PathVariable Integer page) {
		Pageable pageable = PageRequest.of(page, 4);
//		return cursoService.findAll(pageable);
		return UtilController.responseGeneric(cursoService.findAll(pageable), "cursos",HttpStatus.ACCEPTED);
	}
	
	
	 /*
	  * 
	  */
	@GetMapping("/curso/{id}")
	public ResponseEntity<?> show(@PathVariable Long id) {
		log.info("curso :"+id);		
		HttpStatus status ;
		Curso curso = null;
		Map<String, Object> response = new HashMap<>();
		
		try {
			curso = cursoService.findById(id);
			if(curso == null) {
				response.put("mensaje", "Error: no se pudo editar, el curso ID: "
						.concat(id.toString().concat(" no existe en la base de datos!")));
				response.put("status", HttpStatus.NOT_FOUND);
				response.put("code", HttpStatus.NOT_FOUND.value());
				log.info("curso fin:"+id);
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			}
			response.put("curso", curso);
			 response.put("mensaje", "Exito en la busqueda de curso");
			 response.put("status", HttpStatus.ACCEPTED);
			 response.put("code", HttpStatus.ACCEPTED.value());
			 log.info("curso fin:"+id);
			 return new ResponseEntity<Map<String, Object>>(response, HttpStatus.ACCEPTED);
		} catch(DataAccessException e) {
			response.put("mensaje", e.getMessage().concat(": ").concat(((NestedRuntimeException) e).getMostSpecificCause().getMessage()));
			response.put("status", HttpStatus.INTERNAL_SERVER_ERROR);
			 response.put("code", HttpStatus.INTERNAL_SERVER_ERROR.value());
			 log.info("curso fin:"+id);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
//		
	}
	
	
	
	 /*
	  * 
	  */
	@PostMapping("/curso")
	public ResponseEntity<?> create(@Valid @RequestBody CursoModelo cursoModelo, BindingResult result) {
		log.info("curso create"+cursoModelo.toString());
		Curso cursoNew = new Curso();
		Map<String, Object> response = new HashMap<>();
		
		if(result.hasErrors()) {

			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() +"' "+ err.getDefaultMessage())
					.collect(Collectors.toList());		
			response.put("errors", errors);
			log.info("curso create fin:"+cursoModelo.toString());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		try {
			cursoNew = cursoService.save(cursoModelo);
			
			response.put("curso", cursoNew);
			 response.put("mensaje", "El curso se ha creado con Exito");
			 response.put("status", HttpStatus.CREATED);
			 response.put("code", HttpStatus.CREATED.value());
			 log.info("curso create fin:"+cursoModelo.toString());
			 return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);

		} catch(DataAccessException e) {
			response.put("mensaje", e.getMessage().concat(": ").concat(((NestedRuntimeException) e).getMostSpecificCause().getMessage()));
			response.put("status", HttpStatus.INTERNAL_SERVER_ERROR);
			 response.put("code", HttpStatus.INTERNAL_SERVER_ERROR.value());
			 e.printStackTrace();
			 log.info("curso create fin:"+cursoModelo.toString());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	
	/*
	 * 
	 */
	@PutMapping("/curso/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody CursoModelo cursoModelo, BindingResult result, @PathVariable Long id) {
		log.info("curso update :"+cursoModelo.toString());
		
		Curso cursoActual = cursoService.findById(id);
		Curso cursoUpdated = null;
		Map<String, Object> response = new HashMap<>();

		if(result.hasErrors()) {

			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() +"' "+ err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("mensaje", errors);
			 response.put("status", HttpStatus.BAD_REQUEST);
			 response.put("code", HttpStatus.BAD_REQUEST.value());
			 log.info("curso update fin:"+cursoModelo.toString());
			 return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		if (cursoActual == null) {
			response.put("mensaje", "Error: no se pudo editar, el curso ID: "
					.concat(id.toString().concat(" no existe en la base de datos!")));
			
			response.put("status", HttpStatus.NOT_FOUND);
			response.put("code", HttpStatus.NOT_FOUND.value());
			log.info("curso update fin:"+cursoModelo.toString());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		try {
			List<Instructor> allInstructores = instructorService.findAll();
			List<Instructor> instructores = new ArrayList<Instructor>();
			for(Long idInstructor : cursoModelo.getListInstructores()) {
				for(Instructor instructor : allInstructores) {
					if(instructor.getIdInstructor().longValue() == idInstructor ) {
						instructores.add(instructor);
					}
				}
			}
			
			
			cursoActual.setNombreCurso(cursoModelo.getNombreCurso());
			cursoActual.setInstructores(instructores);
			cursoActual.setNotaCurso(cursoModelo.getNotaCurso());
			cursoActual.setUserCreateCurso(cursoModelo.getUserCreateCurso());
			cursoActual.setCreateAtCurso(cursoModelo.getCreateAtCurso());
			cursoActual.setStatusCurso(cursoModelo.getStatusCurso());

			cursoUpdated = cursoService.update(cursoActual);
			response.put("curso", cursoUpdated);
			 response.put("mensaje", "El curso se ha creado con Exito");
			 response.put("status", HttpStatus.CREATED);
			 response.put("code", HttpStatus.CREATED.value());
			 log.info("curso update fin:"+cursoModelo.toString());
			 return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);


		} catch (DataAccessException e) {
			response.put("mensaje", e.getMessage().concat(": ").concat(((NestedRuntimeException) e).getMostSpecificCause().getMessage()));
			response.put("status", HttpStatus.INTERNAL_SERVER_ERROR);
			 response.put("code", HttpStatus.INTERNAL_SERVER_ERROR.value());
			 log.info("curso update fin:"+cursoModelo.toString());
			 e.printStackTrace();
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

//		return UtilController.responseGeneric(cursoUpdated, "curso", status);
	}
	
	
	/*
	 * 
	 */
//	@DeleteMapping("/curso/{id}")
//	public ResponseEntity<?> delete(@PathVariable Long id) {
//		
//		HttpStatus status ;
//		
//		Map<String, Object> response = new HashMap<>();
//		Curso curso = null;
//		try {
//			 curso = cursoService.findById(id);
//			String nombreFotoAnterior = "";//curso.getFoto();
//			
//			uploadService.eliminar(nombreFotoAnterior);
//			
//		    cursoService.delete(id);
//		} catch (DataAccessException e) {
//			response.put("mensaje", "Error al eliminar el curso de la base de datos");
//			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
////			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
//			status =  HttpStatus.INTERNAL_SERVER_ERROR;
//		}
//		
//		response.put("mensaje", "El curso eliminado con éxito!");
//		
////		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
//		status =  HttpStatus.OK;
//		return UtilController.responseGeneric(curso, "curso", status);
//	}
//	
//	
//	/*
//	 * 
//	 */
//	@PostMapping("/curso/upload")
//	public ResponseEntity<?> upload(@RequestParam("archivo") MultipartFile archivo, @RequestParam("id") Long id){
//		Map<String, Object> response = new HashMap<>();
//		
//		Curso curso = cursoService.findById(id);
//		
//		if(!archivo.isEmpty()) {
//
//			String nombreArchivo = null;
//			try {
//				nombreArchivo = uploadService.copiar(archivo);
//			} catch (IOException e) {
//				response.put("mensaje", "Error al subir la imagen del curso");
//				response.put("error", e.getMessage().concat(": ").concat(e.getCause().getMessage()));
//				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
//			}
//			
//			String nombreFotoAnterior = "";//curso.getFoto();
//			
//			uploadService.eliminar(nombreFotoAnterior);
//						
////			curso.setFoto(nombreArchivo);
//			
////			cursoService.save(curso);
//			
//			response.put("curso", curso);
//			response.put("mensaje", "Has subido correctamente la imagen: " + nombreArchivo);
//			
//		}
//		
//		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
//	}
	
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
	
//	@GetMapping("/curso/regiones")
//	public List<Region> listarRegiones(){
//		return cursoService.findAllRegiones();
//	}
}

