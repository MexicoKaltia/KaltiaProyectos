package mx.uniprotec.gamerFront.service.impl;

import java.util.Map;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.uniprotec.entidad.modelo.ResultVO;
import mx.uniprotec.gamerFront.service.ILoginService;
import mx.uniprotec.gamerFront.util.BaseClientRest;
import mx.uniprotec.gamerFront.util.BaseClientRestCU;
import mx.uniprotec.gamerFront.vo.UserForm;


@Service
public class LoginService implements ILoginService{
	
	private static Logger log = LoggerFactory.getLogger(LoginService.class);
	
	@Autowired
	BaseClientRest baseClientRest;
	@Autowired
	BaseClientRestCU baseClientRestCU;
//	UserMap um = UserMap.getSingletonInstance();
//	HashMap<String, Object> valoresResponse = new HashMap<String, Object>();
////	ResultVO resultVO;// = new ResultVO();
//	LoginSingle loginSingle;

	
	public ResultVO login(UserForm user) {
			log.info("Nuevo Usuario");
			ResultVO resultVO = new ResultVO();
			try {
				resultVO = baseClientRest.login(user);
				JSONObject jsonObject = new JSONObject(resultVO.getJsonResponse());
				JSONObject roleUsuario = new JSONObject((Map) jsonObject.get("perfil"));
				resultVO.setPerfil(roleUsuario.get("nombre").toString());
				String accesToken = resultVO.getAccesToken();

				resultVO.setObject(getTokenControlUniprotec());

			}catch (Exception e) {
				e.printStackTrace();
				resultVO.setCodigo((long) 500);
			}
//		}
		return resultVO;
	}
private String getTokenControlUniprotec() {
	//Obtener token de aplicacion-controluniprotec
	UserForm userCU = new UserForm("d.hrivas", "12345");
	ResultVO resultVOCU = baseClientRestCU.login(userCU);
//	resultVO.setObject(resultVOCU.getAccesToken());
	return resultVOCU.getAccesToken();
}
	
}