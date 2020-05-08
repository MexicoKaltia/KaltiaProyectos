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

import mx.uniprotec.application.entity.Cliente;
import mx.uniprotec.application.entity.Vendedor;
import mx.uniprotec.application.entity.Cliente;
import mx.uniprotec.application.entity.ResponseGeneral;
import mx.uniprotec.application.service.IAplicacionService;
import mx.uniprotec.application.service.IVendedorService;
import mx.uniprotec.application.service.IUploadFileService;
import mx.uniprotec.application.util.UtilController;
import mx.uniprotec.entidad.modelo.VendedorModelo;

@CrossOrigin(origins = { "http://localhost:8080" })
@RestController
@RequestMapping("/crud")
public class VendedorRestController {
	
	@Autowired
	private ResponseGeneral rg;

	@Autowired
	private IVendedorService vendedorService;
	
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
//		return vendedorService.findAll();
		return UtilController.responseGeneric(vendedorService.findAll(), "vendedores", HttpStatus.ACCEPTED);
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
			status = HttpStatus.OK;
		} catch(DataAccessException e) {
			status = HttpStatus.INTERNAL_SERVER_ERROR;
//			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if(vendedor == null) {
			status = HttpStatus.NOT_FOUND;
//			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
//		status =  HttpStatus.OK;
//		return UtilController.responseGeneric(response, vendedor, "vendedor");
		return UtilController.responseGeneric(vendedor, "vendedor", status);
	}
	
	
	
	 /*
	  * 
	  */
	@SuppressWarnings("null")
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
//	Cliente cliente = aplicacionService.findCliente(vendedor.getClienteVendedor());
	List<Cliente> clientes = new ArrayList<Cliente>();
	
	for(Long idCliente : vendedor.getListClienteVendedor()) {
		Cliente cliente = new Cliente(idCliente);
		clientes.add(cliente);
	}
			
			vendedorNew.setNombreVendedor(vendedor.getNombreVendedor());
			vendedorNew.setEmailVendedor(vendedor.getEmailVendedor());
			vendedorNew.setNotaVendedor(vendedor.getNotaVendedor());
			vendedorNew.setCreateAtVendedor(vendedor.getCreateAtVendedor());
			vendedorNew.setStatusVendedor(vendedor.getStatusVendedor());
			vendedorNew.setUserCreateVendedor(vendedor.getUserCreateVendedor());
			
			vendedorNew = vendedorService.save(vendedorNew);
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			status = HttpStatus.INTERNAL_SERVER_ERROR;
//			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "El vendedor ha sido creado con éxito!");
		response.put("vendedor", vendedorNew);
//		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
		status = HttpStatus.CREATED;
		return UtilController.responseGeneric(vendedor, "vendedor", status);
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

		if(result.hasErrors()) {

			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() +"' "+ err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
//			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
			status = HttpStatus.BAD_REQUEST;
		}
		
		if (vendedorActual == null) {
			response.put("mensaje", "Error: no se pudo editar, el vendedor ID: "
					.concat(id.toString().concat(" no existe en la base de datos!")));
//			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			status = HttpStatus.NOT_FOUND;
		}

		try {
			List<Cliente> clientes = new ArrayList<Cliente>();
			
			for(Long idCliente : vendedor.getListClienteVendedor()) {
				Cliente cliente = new Cliente(idCliente);
				clientes.add(cliente);
			}
			
			vendedorActual.setCliente(clientes);
			vendedorActual.setNombreVendedor(vendedor.getNombreVendedor());
			vendedorActual.setEmailVendedor(vendedor.getEmailVendedor());
			vendedorActual.setNotaVendedor(vendedor.getNotaVendedor());
			vendedorActual.setCreateAtVendedor(vendedor.getCreateAtVendedor());
			vendedorActual.setStatusVendedor(vendedor.getStatusVendedor());
			vendedorActual.setUserCreateVendedor(vendedor.getUserCreateVendedor());
			
			vendedorUpdated = vendedorService.save(vendedorActual);

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al actualizar el vendedor en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
//			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "El vendedor ha sido actualizado con éxito!");
		response.put("vendedor", vendedorUpdated);

//		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
		status = HttpStatus.CREATED;
		return UtilController.responseGeneric(vendedorUpdated, "vendedor", status);
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
	
	
	/*
	 * 
	 */
	@PostMapping("/vendedor/upload")
	public ResponseEntity<?> upload(@RequestParam("archivo") MultipartFile archivo, @RequestParam("id") Long id){
		Map<String, Object> response = new HashMap<>();
		
		Vendedor vendedor = vendedorService.findById(id);
		
		if(!archivo.isEmpty()) {

			String nombreArchivo = null;
			try {
				nombreArchivo = uploadService.copiar(archivo);
			} catch (IOException e) {
				response.put("mensaje", "Error al subir la imagen del vendedor");
				response.put("error", e.getMessage().concat(": ").concat(e.getCause().getMessage()));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			
			String nombreFotoAnterior = "";//vendedor.getFoto();
			
			uploadService.eliminar(nombreFotoAnterior);
						
//			vendedor.setFoto(nombreArchivo);
			
			vendedorService.save(vendedor);
			
			response.put("vendedor", vendedor);
			response.put("mensaje", "Has subido correctamente la imagen: " + nombreArchivo);
			
		}
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
//	@GetMapping("/uploads/img/{nombreFoto:.+}")
//	public ResponseEntity<Resource> verFoto(@PathVariable String nombreFoto){
//
//		Resource recliente = null;
//		
//		try {
//			recliente = uploadService.cargar(nombreFoto);
//		} catch (MalformedURLException e) {
//			e.printStackTrace();
//		}
//		
//		HttpHeaders cabecera = new HttpHeaders();
//		cabecera.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + recliente.getFilename() + "\"");
//		
//		return new ResponseEntity<Resource>(recliente, cabecera, HttpStatus.OK);
//	}
	
//	@GetMapping("/vendedor/clientees")
//	public List<Cliente> listarClientees(){
//		return vendedorService.findAllClientees();
//	}
}
