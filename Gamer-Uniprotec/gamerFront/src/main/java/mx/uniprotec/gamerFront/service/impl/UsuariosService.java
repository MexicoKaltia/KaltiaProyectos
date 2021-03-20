package mx.uniprotec.gamerFront.service.impl;

import java.util.List;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.uniprotec.entidad.modelo.ResultVO;
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
		
		jsonResponse.put("asignaciones", asignaciones);
		jsonResponse.put("cursos", cursos);
		jsonResponse.put("instructores", instructores);
		
		rs.setJsonResponse(jsonResponse);
		
		return jsonResponse;
	}

	

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

private JSONObject getAsignaciones(String tokenCU) {
		
		ResultVO rs = new ResultVO();
		JSONObject jsonAsignaciones = new JSONObject();
		
		try {
			rs= (ResultVO) baseClientRestCU.objetoGetAll(tokenCU, BaseClientRestCU.URL_CRUD_ASIGNACIONES);
			if(rs.getCodigo() != 500) {
				JSONObject jsonGeneral = rs.getJsonResponse();
//				log.info(rs.getJsonResponse().toJSONString());
//				JSONObject jsonAsignaciones = new JSONObject();
				jsonAsignaciones.put("asignaciones", jsonGeneral.get("asignaciones"));
				
//				rs.setJsonResponseObject(jsonAsignaciones);
//				log.info(jsonGeneral.toString());
//				log.info(rs.toString());
			
			}else {
//				return rs;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return jsonAsignaciones;
	}
	
}