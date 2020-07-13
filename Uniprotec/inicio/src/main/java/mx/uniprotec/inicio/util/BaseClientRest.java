package mx.uniprotec.inicio.util;

import java.util.Base64;
import java.util.Map;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.NestedRuntimeException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import mx.uniprotec.entidad.modelo.PerfilModelo;
import mx.uniprotec.entidad.modelo.ResultVO;
import mx.uniprotec.entidad.modelo.User;


@Service
public class BaseClientRest extends WebMvcConfigurerAdapter implements IBaseClientRest {
	
	private static Logger log = LoggerFactory.getLogger(BaseClientRest.class);
	
	
	public BaseClientRest() {
		// TODO Auto-generated constructor stub
	}
	
	////////////   URL LOCAL /////////////////
//	public static final String URL_POST_LOGIN 	      =	"http://localhost:8016/oauth/token";
//	public static final String URL_CRUD				  = "http://localhost:8016/crud/";
	
	public static final String URL_POST_LOGIN 		  =	"http://45.80.153.253:8016/oauth/token";
	public static final String URL_CRUD				  = "http://45.80.153.253:8016/crud/";
	

	public static final String URL_CRUD_CLIENTE		  =	"cliente";
	public static final String URL_CRUD_INSTRUCTOR	  =	"instructor";
	public static final String URL_CRUD_VENDEDOR	  =	"vendedor";
	public static final String URL_CRUD_CURSO		  =	"curso";
	public static final String URL_CRUD_USUARIO		  =	"usuario";
	public static final String URL_CRUD_CLIENTES	  =	"clientes";
	public static final String URL_CRUD_INSTRUCTORES  =	"instructores";
	public static final String URL_CRUD_VENDEDORES    =	"vendedores";
	public static final String URL_CRUD_CURSOS		  =	"cursos";
	public static final String URL_CRUD_USUARIOS	  =	"usuarios";
	public static final String URL_CRUD_REGIONES	  =	"regiones";
	public static final String URL_CRUD_ASIGNACIONES  =	"asignaciones";
	public static final String URL_CRUD_ASIGNACION	  =	"asignacion";
	public static final String URL_CRUD_PERFIL		  =	"perfil";
	public static final String URL_CRUD_PERFILES	  =	"perfiles";
	
	
	
	public static final String POST = "HttpMethod.POST";
	public static final String GET  = "HttpMethod.GET";
	public static final String PUT  = "HttpMethod.PUT";
	public static final String GRANT_TYPE ="password";
	public static final String APP_ID = "UNIPROTEC:KALTIA2020";
	
	public static final Long MAX_TIME_TOKEN = (long) 3600000; 
//	String APP_ID = "angularapp:12345";
	
	ResultVO resultVO = new ResultVO();
	
	@Override
	public ResultVO login(User user) {
		
		 resultVO = getTemplateLogin( user);
		 return resultVO;  
	}
	
	

	@Override
	public Object objetoPost(String token, String urlCrud, Object objeto) {
		
		resultVO = getTemplateObjetoPost(token, urlCrud, objeto);
		return resultVO;
	}
	
	@Override
	public Object objetoPut(String token, String urlCrud, Object objeto, Long idObjeto) {
		
		resultVO = getTemplateObjetoPut(token, urlCrud, objeto, idObjeto);
		return resultVO;
	}
	

	public ResultVO objetoGetAll(String token, String urlCrud) {
		return getTemplateObjetoGetAll(token, urlCrud);//resultVO;
	}

	@Override
	public ResultVO objetoGetId(String token, String urlCrud, Object object, String idObject) {
		resultVO = getTemplateObjetoGetId(token, urlCrud, object, idObject);
		return resultVO;
	}




	
	



	


	/*
	 * 
	 *   Templates 
	 * 
	 */
	private ResultVO getTemplateLogin(User user) {
		
		try {
			 
			 String base64Creds = Base64.getEncoder().encodeToString(APP_ID.getBytes());

			 HttpHeaders headers = new HttpHeaders();
			 headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);//.APPLICATION_JSON);		 
	   	     headers.add("Authorization", "Basic " + base64Creds);
			 
	   	    MultiValueMap<String, String> mapbody= new LinkedMultiValueMap<String, String>();
	   	    mapbody.add("username", user.getUserName());
	  		mapbody.add("password", user.getPassword());
	    	mapbody.add("grant_type", GRANT_TYPE);
	
	    	HttpEntity<?> entity = new HttpEntity<>(mapbody, headers);
		    RestTemplate restTemplate = new RestTemplate();
		    ResponseEntity<JSONObject> response = new ResponseEntity<JSONObject>(HttpStatus.CONTINUE); 
		    try {
		    	response  = restTemplate.exchange(URL_POST_LOGIN, HttpMethod.POST, entity, JSONObject.class);
		    	resultVO= asignaResponse(response);
			} catch (Exception e) {
				JSONObject jsonResponse = (JSONObject) response.getBody();
			    ResultVO rs = new ResultVO();
			    rs.setJsonResponse(jsonResponse);
			    rs.setMensaje(e.getMessage().concat(": ").concat(((NestedRuntimeException) e).getMostSpecificCause().getMessage()));
			    rs.setCodigo(Long.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()));
			    e.printStackTrace();
				return rs;
			}
		    
	    
	    }catch(Exception e) {
	    	resultVO.setMensaje("Fallo conexion RestTemplate");
	    	e.printStackTrace();
	    }
	    return resultVO;
	}
	
	

	private ResultVO getTemplateObjetoPost(String token,  String urlCrud, Object object) {
		
		log.info(URL_CRUD+urlCrud);
//		log.info(token);
		
		 HttpHeaders headers = new HttpHeaders();
		 headers.setContentType(MediaType.APPLICATION_JSON);//.APPLICATION_JSON);		 
   	     headers.add("Authorization", "Bearer " + token);
   	     
   	    HttpEntity<?> entity = new HttpEntity<>(object, headers);
	    RestTemplate restTemplate = new RestTemplate();
	    try {
	    	ResponseEntity<JSONObject> response  = restTemplate.exchange(URL_CRUD+urlCrud, HttpMethod.POST, entity, JSONObject.class);	    
//		    resultVO = asignaResponseObject(response);
			return asignaResponseObject(response);
		} catch (Exception e) {
			JSONObject jsonResponse = new JSONObject();
		    ResultVO rs = new ResultVO();
		    rs.setJsonResponse(jsonResponse);
		    rs.setMensaje("Error:"+e.getMessage().concat(": ").concat(((NestedRuntimeException) e).getMostSpecificCause().getMessage()));
		    rs.setCodigo(Long.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()));
		        
		   
		    
			return rs;
		}
	    
	}
	
	private ResultVO getTemplateObjetoPut(String token, String urlCrud, Object objeto, Long idObjeto) {

		String urlPut = URL_CRUD+urlCrud + "/" +Long.valueOf(idObjeto).toString();
		log.info(urlPut);
		
		 HttpHeaders headers = new HttpHeaders();
		 headers.setContentType(MediaType.APPLICATION_JSON);//.APPLICATION_JSON);		 
   	     headers.add("Authorization", "Bearer " + token);
   	     
   	    HttpEntity<?> entity = new HttpEntity<>(objeto, headers);
	    RestTemplate restTemplate = new RestTemplate();
	    try {
	    	ResponseEntity<JSONObject> response  = restTemplate.exchange(urlPut, HttpMethod.PUT, entity, JSONObject.class);
	    	return asignaResponseObject(response);
		} catch (Exception e) {
			JSONObject jsonResponse = new JSONObject();
		    ResultVO rs = new ResultVO();
		    rs.setJsonResponse(jsonResponse);
		    rs.setMensaje("Error:"+e.getMessage().concat(": ").concat(((NestedRuntimeException) e).getMostSpecificCause().getMessage()));
		    rs.setCodigo(Long.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()));
		        
		   return rs;
		}
	    
	    
//	    resultVO = asignaResponseObject(response);
//		return resultVO;
	}



	private ResultVO getTemplateObjetoGetAll(String token, String urlCrud) {
		
		HttpHeaders headers = new HttpHeaders();
		 headers.setContentType(MediaType.APPLICATION_JSON);//.APPLICATION_JSON);		 
  	     headers.add("Authorization", "Bearer " + token);
  	     
  	    HttpEntity<?> entity = new HttpEntity<>(headers);
	    RestTemplate restTemplate = new RestTemplate();
	    try {
	    	ResponseEntity<JSONObject> response  = restTemplate.exchange(URL_CRUD+urlCrud, HttpMethod.GET, entity, JSONObject.class);
//		    log.info(resultVO.toString());
//		    resultVO = asignaResponseObject(response);
	  	     
			return asignaResponseObject(response);
			
		} catch (Exception e) {
			
				JSONObject jsonResponse = new JSONObject();
			    ResultVO rs = new ResultVO();
			    rs.setJsonResponse(jsonResponse);
			    e.printStackTrace();
			    rs.setMensaje("Error:"+e.getMessage().concat(": ").concat("-----"));
			    rs.setCodigo(Long.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()));
			        
				return rs;
		}
	    
	}
	
	private ResultVO getTemplateObjetoGetId(String token, String urlCrud, Object object, String idObject) {
		
		String urlGetId = URL_CRUD+urlCrud + "/" +idObject.toString();
		log.info(urlGetId);
		HttpHeaders headers = new HttpHeaders();
		 headers.setContentType(MediaType.APPLICATION_JSON);//.APPLICATION_JSON);		 
 	     headers.add("Authorization", "Bearer " + token);
 	     
 	    HttpEntity<?> entity = new HttpEntity<>(headers);
	    RestTemplate restTemplate = new RestTemplate();
	    try {
	    	ResponseEntity<JSONObject> response  = restTemplate.exchange(urlGetId, HttpMethod.GET, entity, JSONObject.class);
//		    log.info(resultVO.toString());
//		    resultVO = asignaResponseObject(response);
	  	     
			return asignaResponseObject(response);
			
		} catch (Exception e) {
			
				JSONObject jsonResponse = new JSONObject();
			    ResultVO rs = new ResultVO();
			    rs.setJsonResponse(jsonResponse);
			    e.printStackTrace();
			    rs.setMensaje("Error:"+e.getMessage().concat(": ").concat("-----"));
			    rs.setCodigo(Long.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()));
			        
				return rs;
		}
	    
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*
	 *  Auxiliares
	 */

	@SuppressWarnings("unchecked")
	private ResultVO asignaResponse(ResponseEntity<JSONObject> response) {

	    JSONObject jsonResponse = (JSONObject) response.getBody();
	   
	    resultVO.setAccesToken(jsonResponse.get("access_token").toString());
	    resultVO.setCodigo(Long.valueOf(jsonResponse.get("code").toString()));
	    resultVO.setMensaje(jsonResponse.get("message").toString());   
	    resultVO.setJsonResponse(jsonResponse);
	    log.info(resultVO.toString());
	    
		return resultVO;
	}
	

	private ResultVO asignaResponseObject(ResponseEntity<JSONObject> response) {

	    if(response.getStatusCodeValue() == 401) {
	    	log.info("Estoy en un 401");
	    }
		JSONObject jsonResponse = (JSONObject) response.getBody();
	    ResultVO rs = new ResultVO();
	    log.info(jsonResponse.toJSONString());
	    rs.setJsonResponse(jsonResponse);
	    rs.setMensaje(jsonResponse.get("mensaje").toString());
	    rs.setCodigo(Long.valueOf(jsonResponse.get("code").toString()));
	    log.info(rs.toString());    
//	    log.info(jsonResponse.toJSONString());
//	    log.info(jsonResponse.get("mensaje").toString());
	    
		return rs;
	}



	public ResultVO objetoGet(String token, String urlCrudCliente, Long idClienteAsignacion) {
		// TODO Auto-generated method stub
		return null;
	}



	
	
	

	 
	
}
