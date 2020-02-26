package mx.uniprotec.application.util;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class UtilController {

	public UtilController() {
		// TODO Auto-generated constructor stub
	}
	
	
	public static ResponseEntity responseGeneric(Map<String,Object> response, Object object, String tipoObject) {
		
//		Map<String, Object> response = new HashMap<>();
		response.put(tipoObject, object);
		
		
		return new ResponseEntity<Map<String,Object>>(response, (HttpStatus) response.get("code"));
	}

}
