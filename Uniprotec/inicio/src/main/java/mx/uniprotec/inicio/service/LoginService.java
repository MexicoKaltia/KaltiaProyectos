package mx.uniprotec.inicio.service;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.uniprotec.inicio.entity.ResultVO;
import mx.uniprotec.inicio.entity.User;
import mx.uniprotec.inicio.util.BaseClientRest;

@Service
public class LoginService implements ILoginService{
	
	@Autowired
	ResultVO resultVO;
	@Autowired
	BaseClientRest baseClientRest;
	
	private static Logger log = LoggerFactory.getLogger(LoginService.class);
	
	
	@SuppressWarnings("unchecked")
	public JSONObject login(User user) {
		
		JSONObject jsonRequest  = new JSONObject();
		resultVO = baseClientRest.login(user);

		if(resultVO.getCodigo() == 200) {
			jsonRequest = resultVO.getJsonResponse();
		}
		jsonRequest.put("code", resultVO.getCodigo());
		
		return jsonRequest;
				
	}

}
