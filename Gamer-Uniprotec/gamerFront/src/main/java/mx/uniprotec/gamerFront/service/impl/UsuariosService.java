package mx.uniprotec.gamerFront.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.uniprotec.entidad.modelo.ResultVO;
import mx.uniprotec.entidad.modelo.UsuarioAdministrador;
import mx.uniprotec.entidad.modelo.UsuarioAudiencia;
import mx.uniprotec.entidad.modelo.UsuarioInstructor;
import mx.uniprotec.entidad.modelo.CursoModelo;
import mx.uniprotec.entidad.modelo.InstructorModelo;
import mx.uniprotec.entidad.modelo.AsignacionModelo;
import mx.uniprotec.gamerFront.service.IUsuariosService;
import mx.uniprotec.gamerFront.util.BaseClientRest;
import mx.uniprotec.gamerFront.util.BaseClientRestCU;


@Service
public class UsuariosService implements IUsuariosService{
	
	private static Logger log = LoggerFactory.getLogger(UsuariosService.class);
	
	@Autowired
	BaseClientRest baseClientRest;
	@Autowired
	BaseClientRestCU baseClientRestCU;

	@Override
	public JSONObject dataUsuarios(String tokenCU) {
		
		ResultVO rs = new ResultVO();
		JSONObject jsonResponse = new JSONObject();
		//datos instructores
//		List<InstructorModelo> instructores = getInstructores(tokenCU);
		JSONObject instructores = getInstructores(tokenCU);
		
		//datos cursos
//		List<CursoModelo> cursos = getCursos(tokenCU);
		JSONObject cursos = getCursos(tokenCU);
		
		//datos asignaciones
//		List<AsignacionModelo> asignaciones = getAsignaciones(tokenCU);
		JSONObject asignaciones = getAsignaciones(tokenCU);
		
		//datos usuariosControl
		JSONObject usuariosControl = getUsuariosControl(tokenCU);
		
		jsonResponse.put("asignaciones", asignaciones);
		jsonResponse.put("cursos", cursos);
		jsonResponse.put("usuariosControl", usuariosControl);
		jsonResponse.put("instructores", instructores);
		
		rs.setJsonResponse(jsonResponse);
		
		return jsonResponse;
	}
	
	@Override
	public JSONObject dataInstructor(String idInstructorControl, String tokenCU) {
		ResultVO rs = new ResultVO();
		JSONObject jsonResponse = new JSONObject();
		//datos cursos
//		List<CursoModelo> cursos = getCursos(tokenCU);
		JSONObject instructor = getInstructor(tokenCU, "8");
		
		jsonResponse.put("instructor", instructor);
		rs.setJsonResponse(jsonResponse);
		
		return jsonResponse;
	}
	

	@Override
	public ResultVO getDataUsuarios(String token) {
		ResultVO rs = new ResultVO();
		JSONObject jsonResponse = new JSONObject();
		//datos usuariosAudiencia
		 rs = getUsuarios(token);

		jsonResponse.put("usuarios", rs.getJsonResponse());
		
		rs.setJsonResponse(jsonResponse);
		
		return rs;

	}
	



	@Override
	public ResultVO altaUsuarioAudiencia(UsuarioAudiencia userA, String accesToken) {
		
		userA.setCreateAt(LocalDateTime.now());
		userA.setUserCreate("nombreUsuario");
		userA.setStatus("create");
	
		ResultVO resultVO = (ResultVO) baseClientRest.objetoPost(
				accesToken,
				BaseClientRest.URL_ALTA_USUARIOAUDIENCIA,
				userA);
		
		return resultVO;
	}
	
	@Override
	public ResultVO altaUsuarioInstructor(UsuarioInstructor userI, String accesToken) {
		userI.setCreateAt(LocalDateTime.now());
		userI.setUserCreate("nombreUsuario");
		userI.setStatus("activo");
	
		ResultVO resultVO = (ResultVO) baseClientRest.objetoPost(
				accesToken,
				BaseClientRest.URL_ALTA_USUARIOINSTRUCTOR,
				userI);
//		if(resultVO.getCodigo()!=500) {
//			//Actualizar Usuario a Habilitado en la tabla de usuarios 
//			
//		}
		
		return resultVO;
	}
	
	@Override
	public ResultVO altaUsuarioAdministrador(UsuarioAdministrador userA, String accesToken) {
		userA.setCreateAt(LocalDateTime.now());
		userA.setUserCreate("nombreUsuario");
		userA.setStatus("activo");
	
		log.info(userA.toString());
		ResultVO resultVO = (ResultVO) baseClientRest.objetoPost(
				accesToken,
				BaseClientRest.URL_ALTA_USUARIOADMINISTRADOR,
				userA);
//		if(resultVO.getCodigo()!=500) {
//			//Actualizar Usuario a Habilitado en la tabla de usuarios 
//			
//		}
		
		return resultVO;
	}
	
	@Override
	public ResultVO actualizaAudiencia(UsuarioAudiencia userA, String accesToken) {
		
		ResultVO resultVO = (ResultVO) baseClientRest.objetoPost(
				accesToken,
				BaseClientRest.URL_ACTUALIZA_USUARIOAUDIENCIA,
				userA);
//		if(resultVO.getCodigo()!=500) {
//			//Actualizar Usuario a Habilitado en la tabla de usuarios 
//			
//		}
		
		return resultVO;
	}


	@Override
	public ResultVO actualizaInstructor(UsuarioInstructor userI, String accesToken) {
		ResultVO resultVO = (ResultVO) baseClientRest.objetoPost(
				accesToken,
				BaseClientRest.URL_ACTUALIZA_USUARIOINSTRUCTOR,
				userI);
//		if(resultVO.getCodigo()!=500) {
//			//Actualizar Usuario a Habilitado en la tabla de usuarios 
//			
//		}
		
		return resultVO;
	}

	@Override
	public JSONObject getCursosControl(String tokenCU) {
		return getCursos(tokenCU);
	}
	


	
	
	
	/*
	 *  privates
	 */

	private JSONObject getInstructores(String tokenCU) {
		
		ResultVO rs = new ResultVO();
		JSONObject jsonInstructores = new JSONObject();
		try {
			rs = (ResultVO) baseClientRestCU.objetoGetAll(tokenCU, BaseClientRestCU.URL_CRUD_INSTRUCTORES);
			if(rs.getCodigo() == 202) {
				JSONObject jsonGeneral = rs.getJsonResponse();
				jsonInstructores.put("instructores", jsonGeneral.get("instructores"));
				rs.setJsonResponseObject(jsonInstructores);
				
			}else {
				
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return jsonInstructores;
	}
	
private JSONObject getInstructor(String tokenCU, String idInstructor) {
		
		ResultVO rs = new ResultVO();
		JSONObject jsonInstructor = new JSONObject();
		try {
			rs = (ResultVO) baseClientRestCU.objetoGetId(tokenCU, BaseClientRestCU.URL_CRUD_INSTRUCTOR, null, idInstructor);
			if(rs.getCodigo() == 202) {
				JSONObject jsonGeneral = rs.getJsonResponse();
				jsonInstructor.put("instructor", jsonGeneral.get("instructor"));
				rs.setJsonResponseObject(jsonInstructor);
				
			}else {
				
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return jsonInstructor;
	}

	private JSONObject getCursos(String tokenCU) {
		
		ResultVO rs = new ResultVO();
		JSONObject jsonCursos = new JSONObject();
		try {
			rs = (ResultVO) baseClientRestCU.objetoGetAll(tokenCU, BaseClientRestCU.URL_CRUD_CURSOS);
			
			if(rs.getCodigo() == 202) {
				JSONObject jsonGeneral = rs.getJsonResponse();
				jsonCursos.put("cursos", jsonGeneral.get("cursos"));
				rs.setJsonResponseObject(jsonCursos);
				
			}else {
				
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return jsonCursos;
	}
	
	private JSONObject getUsuariosControl(String tokenCU) {
		
		ResultVO rs = new ResultVO();
		JSONObject jsonUsuariosControl = new JSONObject();
		try {
			rs = (ResultVO) baseClientRestCU.objetoGetAll(tokenCU, BaseClientRestCU.URL_CRUD_USUARIOS);
			
			if(rs.getCodigo() == 202) {
				JSONObject jsonGeneral = rs.getJsonResponse();
				jsonUsuariosControl.put("usuariosControl", jsonGeneral.get("usuarios"));
				rs.setJsonResponseObject(jsonUsuariosControl);
				
			}else {
				
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return jsonUsuariosControl;
	}

	private JSONObject getAsignaciones(String tokenCU) {
		
		ResultVO rs = new ResultVO();
		JSONObject jsonAsignaciones = new JSONObject();
		
		try {
			rs= (ResultVO) baseClientRestCU.objetoGetAll(tokenCU, BaseClientRestCU.URL_CRUD_ASIGNACIONES);
			if(rs.getCodigo() != 500) {
				JSONObject jsonGeneral = rs.getJsonResponse();
				jsonAsignaciones.put("asignaciones", jsonGeneral.get("asignaciones"));
			
			}else {
//				return rs;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return jsonAsignaciones;
	}
	
	private ResultVO getUsuarios(String token) {
		JSONObject jsonObject = new JSONObject();
		ResultVO rs = new ResultVO();
		rs= (ResultVO) baseClientRest.objetoGetAll(token, BaseClientRest.URL_GET_USUARIOS);
		if(rs.getCodigo() == 200) {
			JSONObject jsonGeneral = rs.getJsonResponse();
			jsonObject.put("usuarios", jsonGeneral.get("usuarios"));
			jsonObject.put("usuariosAudiencia", jsonGeneral.get("usuariosAudiencia"));
			jsonObject.put("usuariosInstructor", jsonGeneral.get("usuariosInstructor"));
			jsonObject.put("usuariosAdministrador", jsonGeneral.get("usuariosAdministrador"));
		}
		rs.setJsonResponse(jsonObject);
		return rs;
	}

	private JSONObject getUsuariosAdministrador(String token) {
		JSONObject jsonObject = new JSONObject();
		ResultVO rs = new ResultVO();
		rs= (ResultVO) baseClientRest.objetoGetAll(token, BaseClientRest.URL_GET_USUARIOSADMINISTRADOR);
		if(rs.getCodigo() != 500) {
			JSONObject jsonGeneral = rs.getJsonResponse();
			jsonObject.put("usuariosAdmninistrador", jsonGeneral.get("usuariosAdmninistrador"));			
		}
		return jsonObject;
	}


	private JSONObject getUsuariosInstructor(String token) {
		JSONObject jsonObject = new JSONObject();
		ResultVO rs = new ResultVO();
		rs= (ResultVO) baseClientRest.objetoGetAll(token, BaseClientRest.URL_GET_USUARIOSINSTRUCTORES);
		if(rs.getCodigo() != 500) {
			JSONObject jsonGeneral = rs.getJsonResponse();
			jsonObject.put("usuariosInstructor", jsonGeneral.get("usuariosInstructor"));			
		}
		return jsonObject;
	}


	private JSONObject getUsuariosAudiencia(String token) {
		JSONObject jsonObject = new JSONObject();
		ResultVO rs = new ResultVO();
		rs= (ResultVO) baseClientRest.objetoGetAll(token, BaseClientRest.URL_GET_USUARIOSAUDIENCIA);
		if(rs.getCodigo() != 500) {
			JSONObject jsonGeneral = rs.getJsonResponse();
			jsonObject.put("usuariosAudiencia", jsonGeneral.get("usuariosAudiencia"));			
		}
		return jsonObject;
	}



	





	





	
}