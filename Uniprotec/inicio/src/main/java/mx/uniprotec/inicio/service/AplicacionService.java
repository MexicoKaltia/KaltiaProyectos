package mx.uniprotec.inicio.service;

import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.uniprotec.entidad.modelo.MonitorEntidades;
import mx.uniprotec.entidad.modelo.Region;
import mx.uniprotec.entidad.modelo.ResultVO;
import mx.uniprotec.inicio.util.BaseClientRest;

@Service
public class AplicacionService implements IAplicacionService {

	public AplicacionService() {}
	
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
}
