package mx.uniprotec.inicio.service;

import java.util.HashMap;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.uniprotec.inicio.entity.Modulo;
import mx.uniprotec.inicio.entity.ResultVO;
import mx.uniprotec.inicio.entity.SubModulo;
import mx.uniprotec.inicio.entity.User;
import mx.uniprotec.inicio.entity.Usuario;
import mx.uniprotec.inicio.util.BaseClientRest;

@Service
public class LoginService implements ILoginService{
	
	@Autowired
	ResultVO resultVO;
	@Autowired
	BaseClientRest baseClientRest;
	@Autowired
	Usuario usuario;
	@Autowired
	Modulo modulo;
	@Autowired
	SubModulo submodulo;
	
	
	private static Logger log = LoggerFactory.getLogger(LoginService.class);
	
	
	@SuppressWarnings("unchecked")
	public JSONObject login(User user) {
		
		JSONObject jsonRequest  = new JSONObject();
		HashMap<String, Object> valoresResponse = new HashMap<String, Object>();

		try {
			resultVO = baseClientRest.login(user);
			valoresResponse.put("resultVO", resultVO);
			if(resultVO.getCodigo() == 200) {
				jsonRequest = resultVO.getJsonResponse();
				usuario = (Usuario) jsonRequest.get("user");
			}

			
		}catch (Exception e) {
			// TODO: handle exception
		}
		
		return jsonRequest;
				
	}

}
