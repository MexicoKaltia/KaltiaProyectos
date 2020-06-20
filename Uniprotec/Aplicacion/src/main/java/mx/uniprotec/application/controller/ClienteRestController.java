package mx.uniprotec.application.controller;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.NestedRuntimeException;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.dao.DataAccessException;
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

import mx.uniprotec.application.entity.Cliente;
import mx.uniprotec.application.entity.Region;
import mx.uniprotec.application.entity.ResponseGeneral;
import mx.uniprotec.application.entity.Vendedor;
import mx.uniprotec.application.service.IAplicacionService;
import mx.uniprotec.application.service.IClienteService;
import mx.uniprotec.application.service.IUploadFileService;
import mx.uniprotec.application.util.UtilController;
import mx.uniprotec.entidad.modelo.ClienteModelo;



@CrossOrigin(origins = { "http://localhost:8080" })
@RestController
@RequestMapping("/crud")
public class ClienteRestController {
	
	 private final Logger log = LoggerFactory.getLogger(ClienteRestController.class);

	
	@Autowired
	private ResponseGeneral rg;

	@Autowired
	private IClienteService clienteService;
	
	@Autowired
	private IUploadFileService uploadService;

	@Autowired
	private IAplicacionService aplicacionService;


	 /*
	  * 
	  */
	@GetMapping("/clientes")
	public ResponseEntity<?> index() {
		List<Cliente> clientes = null;
		Map<String, Object> response = new HashMap<>();
		try {
			 clientes = clienteService.findAll();
			 response.put("clientes", clientes);
			 response.put("mensaje", "Exito en la busqueda de clientes");
			 response.put("status", HttpStatus.ACCEPTED);
			 response.put("code", HttpStatus.ACCEPTED.value());
			 return new ResponseEntity<Map<String, Object>>(response, HttpStatus.ACCEPTED);
		} catch (Exception e) {
			response.put("mensaje", e.getMessage().concat(": ").concat(((NestedRuntimeException) e).getMostSpecificCause().getMessage()));
			response.put("status", HttpStatus.INTERNAL_SERVER_ERROR);
			 response.put("code", HttpStatus.INTERNAL_SERVER_ERROR.value());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
//		return UtilController.responseGeneric(clienteService.findAll(), "clientes", HttpStatus.ACCEPTED);
		
	}
	
	
	 /*
	  * 
	  */
	@GetMapping("/clientes/page/{page}")
	public ResponseEntity<?> index(@PathVariable Integer page) {
		Pageable pageable = PageRequest.of(page, 4);
		return UtilController.responseGeneric(clienteService.findAll(pageable), "clientes",HttpStatus.ACCEPTED);
	}
	
	
	 /*
	  * 
	  */
	@GetMapping("/cliente/{id}")
	public ResponseEntity<?> show(@PathVariable Long id) {
		
		Map<String, Object> response = new HashMap<>();
		Cliente cliente = null;
		try {
			cliente = clienteService.findById(id);
			
			if(cliente == null) {
				response.put("mensaje", "Error: no se pudo encontrar, el cliente ID: "
						.concat(id.toString().concat(" no existe en la base de datos!")));
				 response.put("status", HttpStatus.NOT_FOUND);
				 response.put("code", HttpStatus.NOT_FOUND.value());
				 return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			}
   			response.put("clientes", cliente);
			 response.put("mensaje", "Exito en la busqueda de cliente");
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
	@SuppressWarnings("null")
	@PostMapping("/cliente")
	public ResponseEntity<?> create(@Valid @RequestBody ClienteModelo cliente, BindingResult result) {
		
		log.info("PostCliente:"+cliente.getNombreCortoCliente());
		
		HttpStatus status ;
		
		Cliente clienteNew = new Cliente();
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
			
			Region region = 	aplicacionService.findRegion(	cliente.getIdRegionCliente());
			Vendedor vendedor = aplicacionService.findVendedor(	cliente.getIdVendedorCliente());
			
			clienteNew.setNombreCortoCliente(cliente.getNombreCortoCliente());
			clienteNew.setNombreCompletoCliente(cliente.getNombreCompletoCliente());
			clienteNew.setRfcCliente(cliente.getRfcCliente());
			clienteNew.setRegionCliente(region);
			clienteNew.setVendedorCliente(vendedor);
			clienteNew.setCreateAtCliente(cliente.getCreateAtCliente());
			clienteNew.setEmailCliente(cliente.getEmailCliente());
			clienteNew.setTelefonoCliente(cliente.getTelefonoCliente());
			clienteNew.setDomicilioCliente(cliente.getDomicilioCliente());
			clienteNew.setStatusCliente(cliente.getStatusCliente());
			clienteNew.setUserCreateCliente(cliente.getUserCreateCliente());
			
			clienteNew.setNombreContactoRecibeCliente(cliente.getNombreContactoRecibeCliente());
			clienteNew.setGoogleMapsCliente(cliente.getGoogleMapsCliente());
			clienteNew.setPautaOperativaCliente(cliente.getPautaOperativaCliente());
			clienteNew.setReglasAccesoCliente(cliente.getReglasAccesoCliente());
			clienteNew.setDocumentosAccesoCliente(cliente.getDocumentosAccesoCliente());
			clienteNew.setMaterialDidacticoCliente(cliente.getMaterialDidacticoCliente());
			clienteNew.setPautaGeneralCliente(cliente.getPautaGeneralCliente());
			clienteNew.setPautaEntregableCliente(cliente.getPautaEntregableCliente());
			clienteNew.setRepresentanteEmpresaCliente(cliente.getRepresentanteEmpresaCliente());
			clienteNew.setRepresentanteTrabajadorCliente(cliente.getRepresentanteTrabajadorCliente());
			clienteNew.setInformacionPaqueteriaCliente(cliente.getInformacionPaqueteriaCliente());
			clienteNew.setImagenLogoCliente(cliente.getImagenLogoCliente());
			clienteNew.setArchivosCliente(cliente.getArchivosCliente());
			
			clienteNew = clienteService.save(clienteNew);

			response.put("cliente", clienteNew);
			response.put("mensaje", "El Cliente ha sido creado con éxito!");
			 response.put("status", HttpStatus.CREATED);
			 response.put("code", HttpStatus.CREATED.value());
			 return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
			
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			log.info(response.get("error").toString());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
//		log.info("PostClienteStatus:"+status);
//		return UtilController.responseGeneric(cliente, "cliente", status);
	}
	
	
	/*
	 * 
	 */
	@PutMapping("/cliente/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody ClienteModelo cliente, BindingResult result, @PathVariable Long id) {

		HttpStatus status ;
		Cliente clienteActual = clienteService.findById(id);
		Cliente clienteUpdated = new Cliente();

		Map<String, Object> response = new HashMap<>();

		if(result.hasErrors()) {

			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() +"' "+ err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			response.put("mensaje", errors);
			 response.put("status", HttpStatus.BAD_REQUEST);
			 response.put("code", HttpStatus.BAD_REQUEST.value());
			 return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
			
		}
		
		if (clienteActual == null) {
			response.put("mensaje", "Error: no se pudo editar, el cliente ID: "
					.concat(id.toString().concat(" no existe en la base de datos!")));
			 response.put("status", HttpStatus.NOT_FOUND);
			 response.put("code", HttpStatus.NOT_FOUND.value());
			 return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		try {

			Region region = aplicacionService.findRegion(cliente.getIdRegionCliente());
			Vendedor vendedor = aplicacionService.findVendedor(	cliente.getIdVendedorCliente());
			
			clienteActual.setNombreCortoCliente(cliente.getNombreCortoCliente());
			clienteActual.setNombreCompletoCliente(cliente.getNombreCompletoCliente());
			clienteActual.setRfcCliente(cliente.getRfcCliente());
			clienteActual.setRegionCliente(region);
			clienteActual.setVendedorCliente(vendedor);
			clienteActual.setCreateAtCliente(cliente.getCreateAtCliente());
			clienteActual.setEmailCliente(cliente.getEmailCliente());
			clienteActual.setTelefonoCliente(cliente.getTelefonoCliente());
			clienteActual.setDomicilioCliente(cliente.getDomicilioCliente());
			clienteActual.setCreateAtCliente(cliente.getCreateAtCliente());
			clienteActual.setStatusCliente(cliente.getStatusCliente());
			clienteActual.setUserCreateCliente(cliente.getUserCreateCliente());
			

			clienteActual.setNombreContactoRecibeCliente(cliente.getNombreContactoRecibeCliente());
			clienteActual.setGoogleMapsCliente(cliente.getGoogleMapsCliente());
			clienteActual.setPautaOperativaCliente(cliente.getPautaOperativaCliente());
			clienteActual.setReglasAccesoCliente(cliente.getReglasAccesoCliente());
			clienteActual.setDocumentosAccesoCliente(cliente.getDocumentosAccesoCliente());
			clienteActual.setMaterialDidacticoCliente(cliente.getMaterialDidacticoCliente());
			clienteActual.setPautaGeneralCliente(cliente.getPautaGeneralCliente());
			clienteActual.setPautaEntregableCliente(cliente.getPautaEntregableCliente());
			clienteActual.setRepresentanteEmpresaCliente(cliente.getRepresentanteEmpresaCliente());
			clienteActual.setRepresentanteTrabajadorCliente(cliente.getRepresentanteTrabajadorCliente());
			clienteActual.setInformacionPaqueteriaCliente(cliente.getInformacionPaqueteriaCliente());
			clienteActual.setImagenLogoCliente(cliente.getImagenLogoCliente());
			clienteActual.setArchivosCliente(cliente.getArchivosCliente());
			
			log.info(clienteActual.getArchivosCliente());

			clienteUpdated = clienteService.save(clienteActual);
			
			response.put("mensaje", "El cliente ha sido actualizado con éxito!");
			response.put("cliente", clienteUpdated);
			response.put("mensaje", "El cliente ha sido creado con éxito!");
			 response.put("status", HttpStatus.CREATED);
			 response.put("code", HttpStatus.CREATED.value());
			 return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
			
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	/*
	 * 
	 */
	@DeleteMapping("/cliente/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		
		HttpStatus status ;
		
		Map<String, Object> response = new HashMap<>();
		Cliente cliente = null;
		try {
			 cliente = clienteService.findById(id);
			String nombreFotoAnterior = "";//cliente.getFoto();
			
			uploadService.eliminar(nombreFotoAnterior);
			
		    clienteService.delete(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al eliminar el cliente de la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
//			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			status =  HttpStatus.INTERNAL_SERVER_ERROR;
		}
		
		response.put("mensaje", "El cliente eliminado con éxito!");
		
//		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		status =  HttpStatus.OK;
		return UtilController.responseGeneric(cliente, "cliente", status);
	}
		
}