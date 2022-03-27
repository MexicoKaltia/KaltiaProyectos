package mx.uniprotec.inicio.service;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.uniprotec.entidad.modelo.ClienteModelo;
import mx.uniprotec.entidad.modelo.MonitorEntidades;
import mx.uniprotec.entidad.modelo.Region;
import mx.uniprotec.entidad.modelo.ResultVO;
import mx.uniprotec.inicio.util.BaseClientRest;
import mx.uniprotec.inicio.util.ComponenteComun;


@Service
public class ProspectoService implements IProspectoService {
	
	private static Logger log = LoggerFactory.getLogger(ProspectoService.class);
	
	ClienteModelo cliente  = new ClienteModelo();
	ResultVO resultVO = new ResultVO();
	@Autowired
	BaseClientRest baseClientRest ;
	MonitorEntidades me = new MonitorEntidades();



	@SuppressWarnings("unchecked")
	@Override
	public ResultVO consultaProspectos(String token) {
		
		ResultVO rs = (ResultVO) baseClientRest.objetoGetAll(token, BaseClientRest.URL_CRUD_PROSPECTOS);
		
		if(rs.getCodigo() == 202) {
			JSONObject jsonGeneral = rs.getJsonResponse();
			JSONObject jsonProspectos= new JSONObject();
			jsonProspectos.put("prospectos", jsonGeneral.get("prospectos"));
			
			rs.setJsonResponseObject(jsonProspectos);
		}	
		
		return rs;
	}



}
