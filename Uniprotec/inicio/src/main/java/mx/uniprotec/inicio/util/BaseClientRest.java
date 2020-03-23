package mx.uniprotec.inicio.util;

import java.util.Base64;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import mx.uniprotec.inicio.entity.ResultVO;
import mx.uniprotec.inicio.entity.User;

@Service
public class BaseClientRest extends WebMvcConfigurerAdapter implements IBaseClientRest {
	
	@Autowired
	ResultVO resultVO;
	
	private static Logger log = LoggerFactory.getLogger(BaseClientRest.class);
	
	
	public BaseClientRest() {
		// TODO Auto-generated constructor stub
	}
	
	////////////   URL LOCAL /////////////////
//	static final String URL_POST_LOGIN 			  =	"http://31.220.63.183:8016/oauth/token";
	
	
	static final String URL_POST_LOGIN 			  =	"http://31.220.63.183:8014/oauth/token";
	
	static final String POST = "HttpMethod.POST";
	static final String GET  = "HttpMethod.GET";
	static final String PUT  = "HttpMethod.PUT";
	static final String GRANT_TYPE ="password";
	

	@Override
	public ResultVO login(User user) {
		
		 resultVO = getTemplateLogin(URL_POST_LOGIN, POST, user);
		 return resultVO;  
	}
	

	private ResultVO getTemplateLogin(String url, String metodo, User user) {
		try {
			 String plainCreds = "angularapp:12345";
			 String base64Creds = Base64.getEncoder().encodeToString(plainCreds.getBytes());

			 HttpHeaders headers = new HttpHeaders();
			 headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);//.APPLICATION_JSON);		 
	   	     headers.add("Authorization", "Basic " + base64Creds);
			 
	   	    MultiValueMap<String, String> mapbody= new LinkedMultiValueMap<String, String>();
	   	    mapbody.add("username", user.getUsername());
	  		mapbody.add("password", user.getPassword());
	    	mapbody.add("grant_type", "password");
	
	    	HttpEntity<?> entity = new HttpEntity<>(mapbody, headers);
		    RestTemplate restTemplate = new RestTemplate();
		    ResponseEntity<JSONObject> response  = restTemplate.exchange(url, HttpMethod.POST, entity, JSONObject.class);
		    
		    resultVO = asignaResponse(response);
	    
	    }catch(Exception e) {
	    	resultVO.setMensaje("Fallo conexion RestTemplate");
	    	e.printStackTrace();
	    }
	    return resultVO;
	}


	@SuppressWarnings("unchecked")
	private ResultVO asignaResponse(ResponseEntity<JSONObject> response) {

	    JSONObject jsonResponse = response.getBody();
	    JSONObject fields = response.getBody();
	    resultVO.setAccesToken(jsonResponse.get("access_token").toString());
	    resultVO.setCodigo(Integer.valueOf(jsonResponse.get("code").toString()));
	    resultVO.setMensaje(jsonResponse.get("message").toString());
	    fields.put("fields", jsonResponse.get("fields"));
	    resultVO.setJsonResponse(fields);
	    
	    log.info(jsonResponse.get("access_token").toString());
	    log.info(jsonResponse.get("code").toString());
	    log.info(jsonResponse.get("message").toString());
	    log.info(jsonResponse.get("fields").toString());
	    
	    
		return resultVO;
	}
	
	 
	
}
