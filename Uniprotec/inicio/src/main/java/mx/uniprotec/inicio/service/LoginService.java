package mx.uniprotec.inicio.service;

import java.util.HashMap;
import java.util.List;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.uniprotec.entidad.modelo.LoginSingle;
import mx.uniprotec.entidad.modelo.Modulo;
import mx.uniprotec.entidad.modelo.ResultVO;
import mx.uniprotec.entidad.modelo.SubModulo;
import mx.uniprotec.entidad.modelo.User;
import mx.uniprotec.entidad.modelo.Usuario;
import mx.uniprotec.inicio.util.BaseClientRest;

@Service
public class LoginService implements ILoginService{
	
	
	@Autowired
	BaseClientRest baseClientRest;
//	@Autowired
	Usuario usuario = new Usuario();
//	@Autowired
	Modulo modulo = new Modulo();
//	@Autowired
	SubModulo submodulo = new SubModulo();
	
	
	private static Logger log = LoggerFactory.getLogger(LoginService.class);
	
	
	
	public HashMap<String, Object> login(User user) {
		
		HashMap<String, Object> valoresResponse = new HashMap<String, Object>();
		ResultVO resultVO = new ResultVO();

		try {
			
			resultVO = baseClientRest.login(user);
			log.info(resultVO.getCodigo().toString());
			valoresResponse.put("resultVO", resultVO);
			
			if(resultVO.getCodigo() == 200) {
				valoresResponse.put("jsonResponse",  resultVO.getJsonResponse());
				valoresResponse.put("vista", "index");	
//				log.info(valoresResponse.get("usuario").toString());
				
				LoginSingle.getLoginSingle(resultVO.getAccesToken(), resultVO);
				
			}else {
				valoresResponse.put("vista", "login");
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return valoresResponse;
	}



	
}
