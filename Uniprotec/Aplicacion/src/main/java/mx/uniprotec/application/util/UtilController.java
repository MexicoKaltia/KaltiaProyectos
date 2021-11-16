package mx.uniprotec.application.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class UtilController {

	public UtilController() {
		// TODO Auto-generated constructor stub
	}
	
	/*
	 * 
	 */
	public static ResponseEntity<Map<String, Object>> responseGeneric2(Map<String,Object> response, Object object, String tipoObject) {
		
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
		
			response.put("status", status.value());
			response.put("message", status.toString());
			response.put("code", status.name());
			
//		}else {
//			response.put("status", 99);
//			response.put("message", "Error al realizar la consulta en la base de datos");
////			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
//			response.put("code", HttpStatus.INTERNAL_SERVER_ERROR);
//			
//		}
//		
		
		return new ResponseEntity<Map<String,Object>>(response,  status);

	}

	public static String listToString(List<String> list) {
//		List<String> list = Arrays.asList("A", "B", "C");
		String delim = ";";

		StringBuilder sb = new StringBuilder();

		int i = 0;
		while (i < list.size() - 1) {
			sb.append(list.get(i));
			sb.append(delim);
			i++;
		}
		sb.append(list.get(i));

		return sb.toString();
		
	}

}
