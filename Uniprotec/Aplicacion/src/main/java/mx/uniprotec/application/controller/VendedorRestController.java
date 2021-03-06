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

import mx.uniprotec.application.entity.Cliente;
import mx.uniprotec.application.entity.ResponseGeneral;
import mx.uniprotec.application.entity.Usuario;
import mx.uniprotec.application.entity.Vendedor;
import mx.uniprotec.application.service.IUploadFileService;
import mx.uniprotec.application.service.IUsuarioService;
import mx.uniprotec.application.service.IVendedorService;
import mx.uniprotec.application.util.UtilController;
import mx.uniprotec.entidad.modelo.VendedorModelo;

@CrossOrigin(origins = { "*" })
@RestController
@RequestMapping("/crud")
public class VendedorRestController {
	
	@Autowired
	private ResponseGeneral rg;

	@Autowired
	private IVendedorService vendedorService;
	@Autowired
	private IUsuarioService usuarioService;
	
	@Autowired
	private IUploadFileService uploadService;
	
//	@Autowired
//	private IAplicacionService aplicacionService;

	
	 private final Logger log = LoggerFactory.getLogger(VendedorRestController.class);

	 /*
	  * 
	  */
	@GetMapping("/vendedores")
	public ResponseEntity<?> index() {
		List<Vendedor> vendedores = null;
		Map<String, Object> response = new HashMap<>();
		try {
			vendedores = vendedorService.findAll();
			 response.put("vendedores", vendedores);
			 response.put("mensaje", "Exito en la busqueda de vendedores");
			 response.put("status", HttpStatus.ACCEPTED);
			 response.put("code", HttpStatus.ACCEPTED.value());
			 return new ResponseEntity<Map<String, Object>>(response, HttpStatus.ACCEPTED);
		} catch (Exception e) {
			response.put("mensaje", e.getMessage().concat(": ").concat(((NestedRuntimeException) e).getMostSpecificCause().getMessage()));
			response.put("status", HttpStatus.INTERNAL_SERVER_ERROR);
			response.put("code", HttpStatus.INTERNAL_SERVER_ERROR.value());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
//		return UtilController.responseGeneric(vendedorService.findAll(), "vendedores", HttpStatus.ACCEPTED);
	}
	
	
	 /*
	  * 
	  */
	@GetMapping("/vendedores/page/{page}")
	public ResponseEntity<?> index(@PathVariable Integer page) {
		Pageable pageable = PageRequest.of(page, 4);
//		return vendedorService.findAll(pageable);
		return UtilController.responseGeneric(vendedorService.findAll(pageable), "vendedores",HttpStatus.ACCEPTED);
	}
	
	
	 /*
	  * 
	  */
	@GetMapping("/vendedor/{id}")
	public ResponseEntity<?> show(@PathVariable Long id) {
		
		HttpStatus status ;
		Vendedor vendedor = null;
		Map<String, Object> response = new HashMap<>();
		
		try {
			vendedor = vendedorService.findById(id);
			if(vendedor == null) {
				response.put("mensaje", "Error: no se pudo editar, el vendedor ID: "
						.concat(id.toString().concat(" no existe en la base de datos!")));
				response.put("status", HttpStatus.NOT_FOUND);
				response.put("code", HttpStatus.NOT_FOUND.value());
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			}
			response.put("vendedor", vendedor);
			 response.put("mensaje", "Exito en la busqueda de vendedor");
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
	@PostMapping("/vendedor")
	public ResponseEntity<?> create(@Valid @RequestBody VendedorModelo vendedor, BindingResult result) {
		
		HttpStatus status ;
		Vendedor vendedorNew = new Vendedor();
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
//			
			vendedorNew.setNombreVendedor(vendedor.getNombreVendedor());
			vendedorNew.setEmailVendedor(vendedor.getEmailVendedor());
			vendedorNew.setEmailGmailVendedor(vendedor.getEmailGmailVendedor());
			vendedorNew.setNotaVendedor(vendedor.getNotaVendedor());
			vendedorNew.setUsuarioVendedor(usuarioService.findById(vendedor.getUsuarioVendedor()));
			vendedorNew.setCreateAtVendedor(vendedor.getCreateAtVendedor());
			vendedorNew.setStatusVendedor(vendedor.getStatusVendedor());
			vendedorNew.setUserCreateVendedor(vendedor.getUserCreateVendedor());
			
			vendedorNew = vendedorService.save(vendedorNew);
			response.put("vendedor", vendedorNew);
			 response.put("mensaje", "Vendedor creado con Exito");
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
	@PutMapping("/vendedor/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody VendedorModelo vendedor, BindingResult result, @PathVariable Long id) {

		HttpStatus status ;
		Vendedor vendedorActual = vendedorService.findById(id);

		Vendedor vendedorUpdated = null;

		Map<String, Object> response = new HashMap<>();
		log.info("Actualizacion Vendedor");
		if(result.hasErrors()) {

			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() +"' "+ err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("mensaje", errors);
			 response.put("status", HttpStatus.BAD_REQUEST);
			 response.put("code", HttpStatus.BAD_REQUEST.value());
			 log.info("Error");
			 return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		if (vendedorActual == null) {
			response.put("mensaje", "Error: no se pudo editar, el vendedor ID: "
					.concat(id.toString().concat(" no existe en la base de datos!")));
			response.put("status", HttpStatus.NOT_FOUND);
			response.put("code", HttpStatus.NOT_FOUND.value());
			log.info("Error: no se pudo editar, el vendedor ID:".concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		if(vendedor.getStatusVendedor().equals("Baja")) {
//			Usuario usuario = usuarioService.findById(vendedorActual.getUsuarioVendedor().getIdUsuario());
//			usuario.setStatusUsuario(vendedor.getStatusVendedor());
//			usuario.setPasswordUsuario(vendedor.getStatusVendedor());
//			usuario.setUsernameUsuario("");
//			usuario.setPerfilUsuario("x".concat(usuario.getPerfilUsuario()));
//			usuario = usuarioService.save(usuario);
//			
			try {
				vendedorService.delete(id);
				
				response.put("vendedor", vendedor  );
				 response.put("mensaje", "Vendedor Eliminado con Exito");
				 response.put("status", HttpStatus.ACCEPTED);
				 response.put("code", HttpStatus.ACCEPTED.value());
				 log.info("Vendedor eliminado con Exito");
				 return new ResponseEntity<Map<String, Object>>(response, HttpStatus.ACCEPTED);
				
			} catch (Exception e) {
				
				response.put("vendedor", vendedor );
				 response.put("mensaje", "Vendedor Eliminado con Exito");
				 response.put("status", HttpStatus.ACCEPTED);
				 response.put("code", HttpStatus.ACCEPTED.value());
				 log.info("Vendedor Eliminado con Exito");
				 e.printStackTrace();
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.ACCEPTED);
			}
			
		}else {
			try {
				
				vendedorActual.setNombreVendedor(vendedor.getNombreVendedor());
				vendedorActual.setEmailVendedor(vendedor.getEmailVendedor());
				vendedorActual.setNotaVendedor(vendedor.getNotaVendedor());
				vendedorActual.setCreateAtVendedor(vendedor.getCreateAtVendedor());
				vendedorActual.setStatusVendedor(vendedor.getStatusVendedor());
				vendedorActual.setUserCreateVendedor(vendedor.getUserCreateVendedor());
				
				vendedorUpdated = vendedorService.save(vendedorActual);
				response.put("vendedor", vendedorUpdated);
				 response.put("mensaje", "Vendedor actualizado con Exito");
				 response.put("status", HttpStatus.ACCEPTED);
				 response.put("code", HttpStatus.ACCEPTED.value());
				 log.info("Vendedor Actualizado con Exito");
				 return new ResponseEntity<Map<String, Object>>(response, HttpStatus.ACCEPTED);
	
			} catch (DataAccessException e) {
				response.put("mensaje", e.getMessage().concat(": ").concat(((NestedRuntimeException) e).getMostSpecificCause().getMessage()));
				response.put("status", HttpStatus.INTERNAL_SERVER_ERROR);
				response.put("code", HttpStatus.INTERNAL_SERVER_ERROR.value());
				e.printStackTrace();
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
	}
	
	
	/*
	 * 
	 */
	@DeleteMapping("/vendedor/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		
		HttpStatus status ;
		
		Map<String, Object> response = new HashMap<>();
		Vendedor vendedor = null;
		try {
			 vendedor = vendedorService.findById(id);
			String nombreFotoAnterior = "";//vendedor.getFoto();
			
			uploadService.eliminar(nombreFotoAnterior);
			
		    vendedorService.delete(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al eliminar el vendedor de la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
//			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			status =  HttpStatus.INTERNAL_SERVER_ERROR;
		}
		
		response.put("mensaje", "El vendedor eliminado con éxito!");
		
//		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		status =  HttpStatus.OK;
		return UtilController.responseGeneric(vendedor, "vendedor", status);
	}
	
	
}
