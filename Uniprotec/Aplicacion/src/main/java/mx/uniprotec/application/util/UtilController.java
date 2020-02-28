package mx.uniprotec.application.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import mx.uniprotec.application.entity.Instructor;

public class UtilController {

	public UtilController() {
		// TODO Auto-generated constructor stub
	}
	
	/*
	 * 
	 */
	public static ResponseEntity<Map<String, Object>> responseGeneric(Map<String,Object> response, Object object, String tipoObject) {
		
//		Map<String, Object> response = new HashMap<>();
		response.put(tipoObject, object);
		
		
		return new ResponseEntity<Map<String,Object>>(response, (HttpStatus) response.get("code"));
	}


	
	
	
	
	
	/*
	 * 
	 */
	public static ResponseEntity<Map<String, Object>> responseGeneric(Object object, String tipoObject, HttpStatus status) {

		
		Map<String, Object> response = new HashMap<>();
		response.put(tipoObject, object);
		if(status.value() == 200) {
			response.put("status", status.value());
			response.put("message", "Datos recuperados con exito");
			response.put("code", HttpStatus.OK);
			
		}else {
			response.put("status", 99);
			response.put("message", "Error al realizar la consulta en la base de datos");
//			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			response.put("code", HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		
		
		return new ResponseEntity<Map<String,Object>>(response, (HttpStatus) response.get("code"));

	}

}
