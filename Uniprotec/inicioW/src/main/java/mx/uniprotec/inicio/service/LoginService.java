package mx.uniprotec.inicio.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.uniprotec.entidad.modelo.LoginSingle;
import mx.uniprotec.entidad.modelo.Modulo;
import mx.uniprotec.entidad.modelo.PerfilModelo;
import mx.uniprotec.entidad.modelo.ResultVO;
import mx.uniprotec.entidad.modelo.SubModulo;
import mx.uniprotec.entidad.modelo.User;
import mx.uniprotec.entidad.modelo.UserMap;
import mx.uniprotec.entidad.modelo.UsuarioModelo;
import mx.uniprotec.inicio.util.BaseClientRest;

@Service
public class LoginService implements ILoginService{
	
	private static Logger log = LoggerFactory.getLogger(LoginService.class);
	
	@Autowired
	BaseClientRest baseClientRest;
	UserMap um = UserMap.getSingletonInstance();
	HashMap<String, Object> valoresResponse = new HashMap<String, Object>();
//	ResultVO resultVO;// = new ResultVO();
	LoginSingle loginSingle;

	
	public ResultVO login(User user) {
		
//		if(verificaSesion(user.getUserName())) {
//			resultVO = loginSingle.getResultVO();
//		}else {
			log.info("Nuevo Usuario");
			ResultVO resultVO = new ResultVO();
			try {
				resultVO = baseClientRest.login(user);	
//				log.info(resultVO.getCodigo().toString());
				if(resultVO.getCodigo() == 200) {
					JSONObject jsonObject = (JSONObject) resultVO.getJsonResponse();
					JSONObject jsonUsuario = new JSONObject((Map) jsonObject.get("user"));
					
					resultVO.setResponse(jsonUsuario.get("perfil").toString());	
					resultVO.setPerfil(jsonUsuario.get("perfil").toString());	
					LoginSingle ls = new LoginSingle(resultVO.getAccesToken(), System.currentTimeMillis(),  resultVO);
					actualizaSesion(user.getUserName(), ls);
								
					ResultVO resultUsuario = baseClientRest.objetoGetId(
							resultVO.getAccesToken(),
							BaseClientRest.URL_CRUD_PERFIL,
							new PerfilModelo(),
							jsonUsuario.get("perfil").toString());
					
					JSONObject jsonObjectPerfil = (JSONObject) resultUsuario.getJsonResponse();
//					log.info(jsonObjectPerfil.toJSONString());
					JSONObject jsonPerfil= new JSONObject((Map) jsonObjectPerfil.get("perfil"));
					JSONObject jsonFields =new JSONObject();
					jsonUsuario.put("modules", jsonPerfil);
					jsonFields.put("fields", jsonUsuario);
					jsonObject.putAll(jsonUsuario);
					resultVO.setJsonResponse(jsonObject);
					
					if(jsonUsuario.get("perfil").toString().equals("Instructor")) {
						ResultVO resultNotificaciones = baseClientRest.objetoGetNotificaciones(
								resultVO.getAccesToken(),
								BaseClientRest.URL_CRUD_NOTIFICACIONES,
								Integer.valueOf(jsonUsuario.get("id").toString()));
						JSONObject jsonResultNotificaciones = (JSONObject) resultNotificaciones.getJsonResponse();
//						log.info(jsonResultNotificaciones.toJSONString());
//						JSONArray jsonNotificaciones = new JSONArray();
						JSONObject jsonNotificaciones = new JSONObject();
						jsonNotificaciones.put( "notificaciones", jsonResultNotificaciones.get("notificaciones"));
						jsonUsuario.put("notificaciones", jsonResultNotificaciones.get("notificaciones"));
						jsonObject.putAll(jsonUsuario);
						resultVO.setJsonResponse(jsonObject);
					}
					
				}else {
					resultVO.setResponse("index");
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
//		}
		return resultVO;
	}


	private void actualizaSesion(String username, LoginSingle ls) {
		if(um.getMapLogin().containsKey(username)) {
			um.getMapLogin().remove(username, ls);
		}else {
			um.getMapLogin().put(username, ls);
		}
	}


	private boolean verificaSesion(String user) {
			
		log.info("----------------------------------------------");
		log.info(um.getMapLogin().values().toString());
		log.info("----------------------------------------------");
		
		if(um.getMapLogin().containsKey(user)) {
			loginSingle = um.getMapLogin().get(user);
			log.info("usuario ya existente:"+user);
			if((System.currentTimeMillis()-loginSingle.getTiempoInicio())<BaseClientRest.MAX_TIME_TOKEN) {
				log.info("usuario y valido:"+ (System.currentTimeMillis()-loginSingle.getTiempoInicio()));
				return false;
			}else {
				log.info("Token expirado inválido");
					return false;
				}
		}else {
			return false;
		}
	}

	
}
