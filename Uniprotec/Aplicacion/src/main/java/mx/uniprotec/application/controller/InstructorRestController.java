package mx.uniprotec.application.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

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

import mx.uniprotec.application.entity.Instructor;
import mx.uniprotec.application.entity.Region;
import mx.uniprotec.application.service.IInstructorService;
import mx.uniprotec.application.service.IUploadFileService;

@CrossOrigin(origins = { "http://localhost:8080" })
@RestController
@RequestMapping("/crud")
public class InstructorRestController {

	@Autowired
	private IInstructorService instructorervice;
	
	@Autowired
	private IUploadFileService uploadService;
	
	// private final Logger log = LoggerFactory.getLogger(instructorRestController.class);

	@GetMapping("/instructores")
	public List<Instructor> index() {
		return instructorervice.findAll();
	}
	
	@GetMapping("/instructores/page/{page}")
	public Page<Instructor> index(@PathVariable Integer page) {
		Pageable pageable = PageRequest.of(page, 4);
		return instructorervice.findAll(pageable);
	}
	
	@GetMapping("/instructor/{id}")
	public ResponseEntity<?> show(@PathVariable Long id) {
		
		Instructor instructor = null;
		Map<String, Object> response = new HashMap<>();
		
		try {
			instructor = instructorervice.findById(id);
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if(instructor == null) {
			response.put("mensaje", "El instructor ID: ".concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Instructor>(instructor, HttpStatus.OK);
	}
	
	@PostMapping("/instructor")
	public ResponseEntity<?> create(@Valid @RequestBody Instructor instructor, BindingResult result) {
		
		Instructor instructorNew = null;
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
			instructorNew = instructorervice.save(instructor);
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "El instructor ha sido creado con éxito!");
		response.put("instructor", instructorNew);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@PutMapping("/instructor/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody Instructor instructor, BindingResult result, @PathVariable Long id) {

		Instructor instructorActual = instructorervice.findById(id);

		Instructor instructorUpdated = null;

		Map<String, Object> response = new HashMap<>();

		if(result.hasErrors()) {

			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() +"' "+ err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		if (instructorActual == null) {
			response.put("mensaje", "Error: no se pudo editar, el instructor ID: "
					.concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		try {

//			instructorActual.setApellido(instructor.getApellido());
//			instructorActual.setNombre(instructor.getNombre());
//			instructorActual.setEmail(instructor.getEmail());
//			instructorActual.setCreateAt(instructor.getCreateAt());
//			instructorActual.setRegion(instructor.getRegion());

			instructorUpdated = instructorervice.save(instructorActual);

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al actualizar el instructor en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "El instructor ha sido actualizado con éxito!");
		response.put("instructor", instructorUpdated);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/instructor/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		
		Map<String, Object> response = new HashMap<>();
		
		try {
			Instructor instructor = instructorervice.findById(id);
			String nombreFotoAnterior = "";//instructor.getFoto();
			
			uploadService.eliminar(nombreFotoAnterior);
			
		    instructorervice.delete(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al eliminar el instructor de la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "El instructor eliminado con éxito!");
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	
	@PostMapping("/instructor/upload")
	public ResponseEntity<?> upload(@RequestParam("archivo") MultipartFile archivo, @RequestParam("id") Long id){
		Map<String, Object> response = new HashMap<>();
		
		Instructor instructor = instructorervice.findById(id);
		
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
			
			instructorervice.save(instructor);
			
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
//		return instructorervice.findAllRegiones();
//	}
}