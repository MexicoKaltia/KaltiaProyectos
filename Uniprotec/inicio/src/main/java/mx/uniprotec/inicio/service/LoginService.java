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
import mx.uniprotec.entidad.modelo.UserMap;
import mx.uniprotec.entidad.modelo.Usuario;
import mx.uniprotec.inicio.util.BaseClientRest;

@Service
public class LoginService implements ILoginService{
	
	private static Logger log = LoggerFactory.getLogger(LoginService.class);
	
	@Autowired
	BaseClientRest baseClientRest;
////	@Autowired
//	Usuario usuario = new Usuario();
////	@Autowired
//	Modulo modulo = new Modulo();
////	@Autowired
//	SubModulo submodulo = new SubModulo();
	UserMap um = UserMap.getSingletonInstance();
	HashMap<String, Object> valoresResponse = new HashMap<String, Object>();
	ResultVO resultVO = new ResultVO();
	LoginSingle loginSingle;

	
	public ResultVO login(User user) {
		
		if(verificaSesion(user.getUserName())) {
			resultVO = loginSingle.getResultVO();
		}else {
			log.info("Nuevo Usuario");
			try {
				resultVO = baseClientRest.login(user);	
				log.info(resultVO.getCodigo().toString());
				
				if(resultVO.getCodigo() == 200) {
					resultVO.setResponse("index");	
					LoginSingle ls = new LoginSingle(resultVO.getAccesToken(), System.currentTimeMillis(),  resultVO);
					actualizaSesion(user.getUserName(), ls);
				
				}else {
					resultVO.setResponse("index");
				}
		
				
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		return resultVO;
	}


	private void actualizaSesion(String username, LoginSingle ls) {
		if(um.getMapLogin().containsKey(username)) {
			um.getMapLogin().replace(username, ls);
		}else {
			um.getMapLogin().put(username, ls);
		}
	}


	private boolean verificaSesion(String user) {
		
		if(um.getMapLogin().containsKey(user)) {
			loginSingle = um.getMapLogin().get(user);
			log.info("usuario ya existente:"+user);
			if((System.currentTimeMillis()-loginSingle.getTiempoInicio())<BaseClientRest.MAX_TIME_TOKEN) {
				log.info("usuario y valido:"+ (System.currentTimeMillis()-loginSingle.getTiempoInicio()));
				return true;
			}else {
				log.info("Token expirado valido");
					return false;
				}
		}else {
			return false;
		}

	}

	
}
