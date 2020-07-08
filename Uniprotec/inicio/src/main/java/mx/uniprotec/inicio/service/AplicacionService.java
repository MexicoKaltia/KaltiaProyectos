package mx.uniprotec.inicio.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//import org.json.JSONException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.uniprotec.entidad.modelo.AsignacionModelo;
import mx.uniprotec.entidad.modelo.ClienteModelo;
import mx.uniprotec.entidad.modelo.CursoModelo;
import mx.uniprotec.entidad.modelo.InstructorModelo;
import mx.uniprotec.entidad.modelo.MonitorEntidades;
import mx.uniprotec.entidad.modelo.Region;
import mx.uniprotec.entidad.modelo.ResultVO;
import mx.uniprotec.entidad.modelo.VendedorModelo;
import mx.uniprotec.inicio.util.BaseClientRest;

@Service
public class AplicacionService implements IAplicacionService {

	public AplicacionService() {}
	
	private static Logger log = LoggerFactory.getLogger(AplicacionService.class);
	
	ResultVO resultVO = new ResultVO();
	
	@Autowired
	BaseClientRest baseClientRest ;
//	@Autowired
	MonitorEntidades me = new MonitorEntidades();


	@Override
	public ResultVO consultaRegiones(String token) {
		
		ResultVO rs = (ResultVO) baseClientRest.objetoGetAll(token, BaseClientRest.URL_CRUD_REGIONES);
		
		if(rs.getCodigo() == 202) {
			JSONObject jsonGeneral = rs.getJsonResponse();
			JSONObject jsonClientes = new JSONObject();
			jsonClientes.put("regiones", jsonGeneral.get("regiones"));
			
			rs.setJsonResponseObject(jsonClientes);
			return rs;
		}else {
			return rs;
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public ResultVO consultaData(ResultVO resultVO) {
		
		String token = resultVO.getAccesToken();
		ResultVO rs = new ResultVO();
		ResultVO rsInstructores = new ResultVO();
		ResultVO rsRegiones = new ResultVO();
		ResultVO rsVendedores = new ResultVO();
		ResultVO rsAsignaciones = new ResultVO();
		
		ResultVO rsClientes = (ResultVO) baseClientRest.objetoGetAll(token, BaseClientRest.URL_CRUD_CLIENTES);
		
		if(rsClientes .getCodigo() != 500) {
			rs.setClientes((List<ClienteModelo>) rsClientes.getJsonResponse().get("clientes"));
			rsInstructores = (ResultVO) baseClientRest.objetoGetAll(token, BaseClientRest.URL_CRUD_INSTRUCTORES);
			if(rsInstructores.getCodigo() != 500) {
				rs.setInstructores((List<InstructorModelo>) rsInstructores.getJsonResponse().get("instructores"));
				rsRegiones = (ResultVO) baseClientRest.objetoGetAll(token, BaseClientRest.URL_CRUD_REGIONES);
				if(rsRegiones.getCodigo() != 500) {
					rs.setRegiones((List<Region>) rsRegiones.getJsonResponse().get("regiones"));
					rsVendedores = (ResultVO) baseClientRest.objetoGetAll(token, BaseClientRest.URL_CRUD_VENDEDORES);
					if(rsVendedores.getCodigo() != 500) {
						rs.setVendedores((List<VendedorModelo>) rsVendedores.getJsonResponse().get("vendedores"));
						rsAsignaciones = (ResultVO) baseClientRest.objetoGetAll(token, BaseClientRest.URL_CRUD_ASIGNACIONES);
						if(rsAsignaciones.getCodigo() != 500) {
							rs.setAsignaciones((List<AsignacionModelo>) rsAsignaciones.getJsonResponse().get("asignaciones"));
						}else {
							log.info("error Asignaciones");
							return rsAsignaciones;
						}
					}else {
						log.info("error Vendedores");
						return rsVendedores;
					}
						
				}else {
					log.info("error Regiones");
					return rsRegiones;
				}
				
			}else {
				log.info("error Instructores");
				return rsInstructores;
			}
			
		}else {
			log.info("error Clientes");
			return rsClientes;
		}
		
			
		ResultVO rsCursos = (ResultVO) baseClientRest.objetoGetAll(token, BaseClientRest.URL_CRUD_CURSOS);
		if(rsCursos.getCodigo() != 500) {
			rs.setCursos((List<CursoModelo>) rsCursos.getJsonResponse().get("cursos"));		
		}else {
			return rsCursos;
		}
		
		rs.setCodigo(rsCursos.getCodigo());
		rs.setMensaje("Exito en las consultas Asignaciones");
		rs.setAccesToken(token);
		rs.setJsonResponse(resultVO.getJsonResponse());
		
		JSONObject jsonData = new JSONObject();
		jsonData.put("clientes", rs.getClientes());
		jsonData.put("instructores", rs.getInstructores());
		jsonData.put("cursos", rs.getCursos());
		jsonData.put("regiones", rs.getRegiones());
		jsonData.put("vendedores", rs.getVendedores());
		jsonData.put("asignaciones", rs.getAsignaciones());
		
		rs.setJsonResponseObject(jsonData);

//		log.info(rs.toString());
		
		return rs;
	}
	
	private static Map<String, Object> toMap(JSONObject jsonObject)  {
	    Map<String, Object> map = new HashMap<>();

	    for (Object key : jsonObject.keySet()) {
	        Object value = jsonObject.get(key);

	        if (value instanceof JSONObject) {
		        map.put(String.valueOf(key), value);
	        }
	        if (value instanceof JSONArray) {
		        map.put(String.valueOf(key), value);
	        }


	    }

	    return map;
	}
	
	private void test() {
		
		ResultVO rs = (ResultVO) baseClientRest.objetoGetAll("token", BaseClientRest.URL_CRUD_CLIENTE);
		if(rs.getCodigo() == 202) {
			
			JSONObject jsonResponse = rs.getJsonResponse();//.get("data");
			log.info(rs.getJsonResponse().toJSONString());
//			log.info(rs.getJsonResponse().get("data.regiones").toString());
			
			JSONObject jsonData = new JSONObject();
			jsonData.put("data",  jsonResponse.get("data"));
			Map<String, Object> map = toMap(jsonData);
			
			
			
    		JSONObject jsonRegiones = new  JSONObject();
			jsonRegiones.put("regiones", (JSONArray)jsonData.get("regiones"));
			JSONObject jsonClientes = new JSONObject();
			jsonClientes.put("clientes", (JSONArray)jsonData.get("clientes"));		
			JSONObject jsonInstructores = new JSONObject();
			jsonInstructores.put("instructores", (JSONArray)jsonData.get("instructores"));
			JSONObject jsonVendedores = new JSONObject();
			jsonVendedores.put("vendedores", (JSONArray)jsonData.get("vendedores"));
			
//			JSONArray jsonRegiones = (JSONArray)jsonData.get("regiones");
//			JSONArray jsonClientes = (JSONArray)jsonData.get("clientes");
//			JSONArray jsonInstructores = (JSONArray)jsonData.get("instructores");
//			JSONArray jsonVendedores = (JSONArray)jsonData.get("vendedores");
			
			log.info(jsonData.toJSONString());
//			log.info(jsonData.get("regiones").toString());
			log.info(jsonRegiones.toJSONString());
			log.info(jsonClientes.toJSONString());
			log.info(jsonInstructores.toJSONString());
			log.info(jsonVendedores.toJSONString());
			
//			log.info(jsonData.get("regiones").toString());
//			log.info(jsonData.get("clientes").toString());
//			log.info(jsonData.get("instructores").toString());
//			log.info(jsonData.get("vendedores").toString());
			
			rs.setRegiones((List<Region>) jsonRegiones);
			rs.setClientes((List<ClienteModelo>) jsonClientes);
			rs.setInstructores((List<InstructorModelo>) jsonInstructores);
			rs.setVendedores((List<VendedorModelo>) jsonVendedores);
			
			log.info(rs.getClientes().toString());
			log.info(rs.getRegiones().toString());
			log.info(rs.getInstructores().toString());
			log.info(rs.getVendedores().toString());
		}
	}
}
