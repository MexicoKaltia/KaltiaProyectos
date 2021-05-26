package mx.uniprotec.gamerBack.controller;

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

import mx.uniprotec.entidad.modelo.ModuloDidactico;
import mx.uniprotec.entidad.modelo.ResultVO;
import mx.uniprotec.entidad.modelo.ValoresJsonVO;
import mx.uniprotec.gamerBack.dao.IModuloDao;
import mx.uniprotec.gamerBack.entity.ModuloCurso;
import mx.uniprotec.gamerBack.entity.ModuloDidacticoEntity;
import mx.uniprotec.gamerBack.service.IModuloService;
import mx.uniprotec.gamerBack.service.ModuloService;


@CrossOrigin(origins = { "http://localhost:8020" })
@RestController
@RequestMapping("/crud")
public class ModulosRestController {

	@Autowired
	private IModuloService moduloService;
	
	@Autowired
	private IModuloDao moduloDao;
	
	private Logger logger = LoggerFactory.getLogger(ModulosRestController.class);

	
	@Secured({"ROLE_ADMIN" ,"ROLE_INSTR" ,"ROLE_USER"})
	@GetMapping("/getModulos")
	public ResponseEntity<?> index() {
		
		Map<String, Object> response = new HashMap<>();
		List<ModuloDidacticoEntity> modulos = new ArrayList<ModuloDidacticoEntity>();
		List<ModuloCurso> moduloCurso = new ArrayList<ModuloCurso>();
		
		try {
			modulos = moduloService.findAll();
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al consultar los modulos didacticos en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			e.printStackTrace();
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		try {
			moduloCurso = moduloService.findAllModuloCurso();
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al consultar los modulos-cursos en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			e.printStackTrace();
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		
		response.put("mensaje", "Los Modulos han sido consultado con éxito!");
		response.put("modulos", modulos);
		response.put("moduloCurso", moduloCurso);
		response.put("code", 200);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.ACCEPTED);
	}
	
	
	
	@Secured("ROLE_ADMIN")
	@PostMapping("/altaModulo")
	public ResponseEntity<?> altaModulosAudiencia(@Valid @RequestBody ModuloDidactico modulo, BindingResult result) {
		
		ModuloDidacticoEntity moduloNew = null;
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
			moduloNew = moduloService.saveModulo(modulo);
			
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			e.printStackTrace();
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		
		response.put("mensaje", "El Modulo ha sido creado con éxito!");
		response.put("Modulos", moduloNew);
		response.put("code", 200);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@Secured("ROLE_ADMIN")
	@PutMapping("/actualizaModulo/{id}")
	public ResponseEntity<?> actualizaModulo(@Valid @RequestBody ModuloDidactico modulo, BindingResult result, @PathVariable Long id) {

		
		ModuloDidacticoEntity moduloUpdated = new ModuloDidacticoEntity (); 
		Map<String, Object> response = new HashMap<>();
		logger.info(modulo.toString());
		
		if(result.hasErrors()) {
			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() +"' "+ err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		try {
			moduloUpdated = moduloService.actualizaModulo(modulo);
			if (moduloUpdated  == null) {
				response.put("mensaje", "Error: no se pudo editar, el Modulos ID: "
						.concat(id.toString().concat(" no existe en la base de datos!")));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			}
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al actualizar el Modulos en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "El Modulo ha sido actualizado con éxito!");
		response.put("modulo", moduloUpdated);
		response.put("code", 200);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@Secured("ROLE_ADMIN")
	@PutMapping("/updateModulo/{id}")
	public ResponseEntity<?> updateModulo(@Valid @RequestBody ValoresJsonVO valoresJson, BindingResult result, @PathVariable Long id) {

		/* ValoresJsonVO
		 *
		 * finalJson = {
					elementos : $elementosFinal,
					modulo : $moduloSel,
					idCurso : $idCurso,
					val : $val,
					arrayCursos : arrayCursos
			}
		 */
		
		ModuloDidacticoEntity moduloUpdated = new ModuloDidacticoEntity (); 
		Map<String, Object> response = new HashMap<>();
		logger.info(valoresJson.toString());
		ResultVO rs = new ResultVO();
		if(result.hasErrors()) {
			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() +"' "+ err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		try {
			rs = moduloService.updateModulo(valoresJson);
			if (rs.getCodigo() == 99l) {
				response.put("mensaje", rs.getMensaje() +" Error: no se pudo editar, el Modulos ID: "
						.concat(id.toString().concat(" no existe en la base de datos!")));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			}
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al actualizar el Modulos en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "El Modulo ha sido actualizado con éxito!");
		response.put("modulo", rs);
		response.put("code", 200);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
//	
//	@Secured("ROLE_ADMIN")
//	@PostMapping("/altaModulosInstructor")
//	public ResponseEntity<?> altaModulosInstructor(@Valid @RequestBody ModuloInstructor Modulo, BindingResult result) {
//		
//		Modulo ModuloNew = null;
//		Map<String, Object> response = new HashMap<>();
//		
//		if(result.hasErrors()) {
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
//			ModuloNew = ModuloService.saveInstructor(Modulo);
//			
//		} catch(DataAccessException e) {
//			response.put("mensaje", "Error al realizar el insert en la base de datos");
//			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
//			e.printStackTrace();
//			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
//			
//		}
//		
//		response.put("mensaje", "El Modulo Instructor se ha activado con éxito!");
//		response.put("Modulos", ModuloNew);
//		response.put("code", 200);
//		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
//	}
//	
//	@Secured("ROLE_ADMIN")
//	@PostMapping("/altaModulosAdministrador")
//	public ResponseEntity<?> altaModulosAdministrador(@Valid @RequestBody ModuloAdministrador Modulo, BindingResult result) {
//		
//		Modulo ModuloNew = null;
//		Map<String, Object> response = new HashMap<>();
//		
//		if(result.hasErrors()) {
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
//			ModuloNew = ModuloService.saveAdministrador(Modulo);
//			
//		} catch(DataAccessException e) {
//			response.put("mensaje", "Error al realizar el insert en la base de datos");
//			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
//			e.printStackTrace();
//			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
//			
//		}
//		
//		response.put("mensaje", "El Modulo Administrador se ha activado con éxito!");
//		response.put("Modulos", ModuloNew);
//		response.put("code", 200);
//		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
//	}
//	
//	@Secured("ROLE_ADMIN")
//	@PostMapping("/actualizaModulosInstructor")
//	public ResponseEntity<?> actualizaModulosInstructor(@Valid @RequestBody ModuloInstructor Modulo, BindingResult result) {
//		
//		Modulo ModuloActual = null;
//		Modulo ModuloNew = null;
//		Map<String, Object> response = new HashMap<>();
//		
//		if(result.hasErrors()) {
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
//			ModuloNew = ModuloService.actualizaModuloInstructor(Modulo);
//			
//		} catch(DataAccessException e) {
//			response.put("mensaje", "Error al realizar el insert en la base de datos");
//			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
//			e.printStackTrace();
//			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
//			
//		}
//		
//		response.put("mensaje", "El Modulo Administrador se ha activado con éxito!");
//		response.put("Modulos", ModuloNew);
//		response.put("code", 200);
//		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
//	}
//	
//	@Secured("ROLE_ADMIN")
//	@PostMapping("/actualizaModulosAudiencia")
//	public ResponseEntity<?> actualizaModulosAudiencia(@Valid @RequestBody ModuloAudiencia Modulo, BindingResult result) {
//		
//		Modulo ModuloActual = null;
//		Modulo ModuloNew = null;
//		Map<String, Object> response = new HashMap<>();
//		
//		if(result.hasErrors()) {
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
//			ModuloNew = ModuloService.actualizaModuloAudiencia(Modulo);
//			
//		} catch(DataAccessException e) {
//			response.put("mensaje", "Error al realizar el insert en la base de datos");
//			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
//			e.printStackTrace();
//			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
//			
//		}
//		
//		response.put("mensaje", "El Modulo Administrador se ha activado con éxito!");
//		response.put("Modulos", ModuloNew);
//		response.put("code", 200);
//		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
//	}
//	
//	
	
//	
////	@Secured("ROLE_ADMIN")
////	@DeleteMapping("/Moduloss/{id}")
////	public ResponseEntity<?> delete(@PathVariable Long id) {
////		
////		Map<String, Object> response = new HashMap<>();
////		
////		try {
////			Modulo Modulo = ModuloService.findById(id);
////			String nombreFotoAnterior ="";
////			
////			ModuloService.delete(id);
////		} catch (DataAccessException e) {
////			response.put("mensaje", "Error al eliminar el Modulos de la base de datos");
////			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
////			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
////		}
////		
////		response.put("mensaje", "El Modulos eliminado con éxito!");
////		
////		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
////	}
////	
////		
	
}
