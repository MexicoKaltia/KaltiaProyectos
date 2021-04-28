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
				switch (resultVO.getPerfil()) {
				case "ROLE_ADMIN":
					//Obtener token de aplicacion-controluniprotec
					UserForm userCU = new UserForm("d.hrivas", "12345");
					ResultVO resultVOCU = baseClientRestCU.login(userCU);
					resultVO.setObject(resultVOCU.getAccesToken());
					resultVO.setResponse("inicio");
					break;
				case "ROLE_INSTR":
					resultVO.setResponse("inicioInstructor");
					break;
				case "ROLE_USER":
					resultVO.setResponse("inicioAudiencia");
					break;
			}
				
//				ResultVO resultUsuario = baseClientRest.objetoGetId(
//						resultVO.getAccesToken(),
//						BaseClientRest.URL_CRUD_USUARIO,
//						new UserForm(),null);
//
//					JSONObject jsonObject = (JSONObject) resultVO.getJsonResponse();
//					JSONObject jsonUsuario = new JSONObject();
//					
//					
//					JSONObject jsonObjectPerfil = (JSONObject) resultUsuario.getJsonResponse();
////					log.info(jsonObjectPerfil.toJSONString());
//					JSONObject jsonPerfil= new JSONObject();
//					JSONObject jsonFields =new JSONObject();
//					jsonUsuario.put("modules", jsonPerfil);
//					jsonFields.put("fields", jsonUsuario);
//					jsonObject.putAll(jsonUsuario);
//					resultVO.setJsonResponse(jsonObject);
			}catch (Exception e) {
				e.printStackTrace();
				resultVO.setCodigo((long) 500);
			}
//		}
		return resultVO;
	}
	
}