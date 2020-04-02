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

import mx.uniprotec.application.entity.Curso;
import mx.uniprotec.application.entity.Region;
import mx.uniprotec.application.entity.ResponseGeneral;
import mx.uniprotec.application.service.ICursoService;
import mx.uniprotec.application.service.IUploadFileService;
import mx.uniprotec.application.util.UtilController;

@CrossOrigin(origins = { "http://localhost:8080" })
@RestController
@RequestMapping("/crud")
public class CursoRestController {
	
	@Autowired
	private ResponseGeneral rg;

	@Autowired
	private ICursoService cursoService;
	
	@Autowired
	private IUploadFileService uploadService;
	
	 private final Logger log = LoggerFactory.getLogger(CursoRestController.class);

	 /*
	  * 
	  */
	@GetMapping("/cursos")
	public ResponseEntity<?> index() {
//		return cursoService.findAll();
		return UtilController.responseGeneric(cursoService.findAll(), "cursos", HttpStatus.ACCEPTED);
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
		
		HttpStatus status ;
		Curso curso = null;
		Map<String, Object> response = new HashMap<>();
		
		try {
			curso = cursoService.findById(id);
			status = HttpStatus.OK;
		} catch(DataAccessException e) {
			status = HttpStatus.INTERNAL_SERVER_ERROR;
//			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if(curso == null) {
			status = HttpStatus.NOT_FOUND;
//			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
//		status =  HttpStatus.OK;
//		return UtilController.responseGeneric(response, curso, "curso");
		return UtilController.responseGeneric(curso, "curso", status);
	}
	
	
	
	 /*
	  * 
	  */
	@PostMapping("/curso")
	public ResponseEntity<?> create(@Valid @RequestBody Curso curso, BindingResult result) {
		
		HttpStatus status ;
		Curso cursoNew = null;
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
			cursoNew = cursoService.save(curso);
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			status = HttpStatus.INTERNAL_SERVER_ERROR;
//			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "El curso ha sido creado con éxito!");
		response.put("curso", cursoNew);
//		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
		status = HttpStatus.CREATED;
		return UtilController.responseGeneric(curso, "curso", status);
	}
	
	
	/*
	 * 
	 */
	@PutMapping("/curso/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody Curso curso, BindingResult result, @PathVariable Long id) {

		HttpStatus status ;
		Curso cursoActual = cursoService.findById(id);

		Curso cursoUpdated = null;

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
		
		if (cursoActual == null) {
			response.put("mensaje", "Error: no se pudo editar, el curso ID: "
					.concat(id.toString().concat(" no existe en la base de datos!")));
//			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			status = HttpStatus.NOT_FOUND;
		}

		try {


			cursoActual.setNombreCurso(curso.getNombreCurso());
			cursoActual.setInstructores(curso.getInstructores());
			cursoActual.setNotaCurso(curso.getNotaCurso());
			cursoActual.setUserCreateCurso(curso.getUserCreateCurso());
			cursoActual.setCreateAtCurso(curso.getCreateAtCurso());
			cursoActual.setStatusCurso(curso.getStatusCurso());

			cursoUpdated = cursoService.save(cursoActual);

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al actualizar el curso en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
//			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "El curso ha sido actualizado con éxito!");
		response.put("curso", cursoUpdated);

//		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
		status = HttpStatus.CREATED;
		return UtilController.responseGeneric(cursoUpdated, "curso", status);
	}
	
	
	/*
	 * 
	 */
	@DeleteMapping("/curso/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		
		HttpStatus status ;
		
		Map<String, Object> response = new HashMap<>();
		Curso curso = null;
		try {
			 curso = cursoService.findById(id);
			String nombreFotoAnterior = "";//curso.getFoto();
			
			uploadService.eliminar(nombreFotoAnterior);
			
		    cursoService.delete(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al eliminar el curso de la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
//			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			status =  HttpStatus.INTERNAL_SERVER_ERROR;
		}
		
		response.put("mensaje", "El curso eliminado con éxito!");
		
//		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		status =  HttpStatus.OK;
		return UtilController.responseGeneric(curso, "curso", status);
	}
	
	
	/*
	 * 
	 */
	@PostMapping("/curso/upload")
	public ResponseEntity<?> upload(@RequestParam("archivo") MultipartFile archivo, @RequestParam("id") Long id){
		Map<String, Object> response = new HashMap<>();
		
		Curso curso = cursoService.findById(id);
		
		if(!archivo.isEmpty()) {

			String nombreArchivo = null;
			try {
				nombreArchivo = uploadService.copiar(archivo);
			} catch (IOException e) {
				response.put("mensaje", "Error al subir la imagen del curso");
				response.put("error", e.getMessage().concat(": ").concat(e.getCause().getMessage()));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			
			String nombreFotoAnterior = "";//curso.getFoto();
			
			uploadService.eliminar(nombreFotoAnterior);
						
//			curso.setFoto(nombreArchivo);
			
			cursoService.save(curso);
			
			response.put("curso", curso);
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
	
//	@GetMapping("/curso/regiones")
//	public List<Region> listarRegiones(){
//		return cursoService.findAllRegiones();
//	}
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
//import mx.uniprotec.application.entity.Curso;
//import mx.uniprotec.application.entity.Region;
//import mx.uniprotec.application.service.ICursoService;
//import mx.uniprotec.application.service.IUploadFileService;
//
//@CrossOrigin(origins = { "http://localhost:8080" })
//@RestController
//@RequestMapping("/crud")
//public class CursoRestController {
//
//	@Autowired
//	private ICursoService cursoService;
//	
//	@Autowired
//	private IUploadFileService uploadService;
//	
//	// private final Logger log = LoggerFactory.getLogger(cursoRestController.class);
//
//	@GetMapping("/cursos")
//	public List<Curso> index() {
//		return cursoService.findAll();
//	}
//	
//	@GetMapping("/cursos/page/{page}")
//	public Page<Curso> index(@PathVariable Integer page) {
//		Pageable pageable = PageRequest.of(page, 4);
//		return cursoService.findAll(pageable);
//	}
//	
//	@GetMapping("/curso/{id}")
//	public ResponseEntity<?> show(@PathVariable Long id) {
//		
//		Curso curso = null;
//		Map<String, Object> response = new HashMap<>();
//		
//		try {
//			curso = cursoService.findById(id);
//		} catch(DataAccessException e) {
//			response.put("mensaje", "Error al realizar la consulta en la base de datos");
//			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
//			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//		
//		if(curso == null) {
//			response.put("mensaje", "El curso ID: ".concat(id.toString().concat(" no existe en la base de datos!")));
//			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
//		}
//		
//		return new ResponseEntity<Curso>(curso, HttpStatus.OK);
//	}
//	
//	@PostMapping("/curso")
//	public ResponseEntity<?> create(@Valid @RequestBody Curso curso, BindingResult result) {
//		
//		Curso cursoNew = null;
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
//			cursoNew = cursoService.save(curso);
//		} catch(DataAccessException e) {
//			response.put("mensaje", "Error al realizar el insert en la base de datos");
//			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
//			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//		
//		response.put("mensaje", "El curso ha sido creado con éxito!");
//		response.put("curso", cursoNew);
//		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
//	}
//	
//	@PutMapping("/curso/{id}")
//	public ResponseEntity<?> update(@Valid @RequestBody Curso curso, BindingResult result, @PathVariable Long id) {
//
//		Curso cursoActual = cursoService.findById(id);
//
//		Curso cursoUpdated = null;
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
//		if (cursoActual == null) {
//			response.put("mensaje", "Error: no se pudo editar, el curso ID: "
//					.concat(id.toString().concat(" no existe en la base de datos!")));
//			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
//		}
//
//		try {
//
////			cursoActual..setApellido(curso.getApellido());
////			cursoActual.setNombre(curso.getNombre());
////			cursoActual.setEmail(curso.getEmail());
////			cursoActual.setCreateAt(curso.getCreateAt());
////			cursoActual.setRegion(curso.getRegion());
//
//			cursoUpdated = cursoService.save(cursoActual);
//
//		} catch (DataAccessException e) {
//			response.put("mensaje", "Error al actualizar el curso en la base de datos");
//			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
//			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//
//		response.put("mensaje", "El curso ha sido actualizado con éxito!");
//		response.put("curso", cursoUpdated);
//
//		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
//	}
//	
//	@DeleteMapping("/curso/{id}")
//	public ResponseEntity<?> delete(@PathVariable Long id) {
//		
//		Map<String, Object> response = new HashMap<>();
//		
//		try {
//			Curso curso = cursoService.findById(id);
//			String nombreFotoAnterior = "";//curso.getFoto();
//			
//			uploadService.eliminar(nombreFotoAnterior);
//			
//		    cursoService.delete(id);
//		} catch (DataAccessException e) {
//			response.put("mensaje", "Error al eliminar el curso de la base de datos");
//			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
//			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//		
//		response.put("mensaje", "El curso eliminado con éxito!");
//		
//		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
//	}
//	
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
//			//curso.setFoto(nombreArchivo);
//			
//			cursoService.save(curso);
//			
//			response.put("curso", curso);
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
////		Resource recurso = null;
////		
////		try {
////			recurso = uploadService.cargar(nombreFoto);
////		} catch (MalformedURLException e) {
////			e.printStackTrace();
////		}
////		
////		HttpHeaders cabecera = new HttpHeaders();
////		cabecera.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + recurso.getFilename() + "\"");
////		
////		return new ResponseEntity<Resource>(recurso, cabecera, HttpStatus.OK);
////	}
//	
////	@GetMapping("/cursos/regiones")
////	public List<Region> listarRegiones(){
////		return cursoService.findAllRegiones();
////	}
//}
