package mx.uniprotec.inicio.service;

import java.util.List;

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
import mx.uniprotec.inicio.util.ComponenteComun;

@Service
public class AsignacionService implements IAsignacionService{
	private static Logger log = LoggerFactory.getLogger(AsignacionService.class);

	MonitorEntidades me = new MonitorEntidades();
	ResultVO resultVO = new ResultVO();
	@Autowired
	BaseClientRest baseClientRest ;
	
	public AsignacionService() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public ResultVO altaAsignacion(AsignacionModelo asignacion, String token) {
		me = ComponenteComun.monitorCampos();
		asignacion.setCreateAtAsignacion(me.getNowEntidad());
		asignacion.setUserCreateAsignacion(me.getIdUsuarioEntidad());
		asignacion.setStatusAsignacion(me.getStatusEntidad());
		log.info(asignacion.toString());
		
		resultVO = (ResultVO) baseClientRest.objetoPost(
				token,
				BaseClientRest.URL_CRUD_ASIGNACION,
				asignacion);
		
		return resultVO;
	}

	@Override
	public ResultVO edicionAsignacion(AsignacionModelo asignacion, String token) {
		
		me = ComponenteComun.monitorCampos();
		
		asignacion.setCreateAtAsignacion(me.getNowEntidad());
		asignacion.setUserCreateAsignacion(me.getIdUsuarioEntidad());
		asignacion.setStatusAsignacion(me.getStatusEntidad());
		
		log.info(asignacion.toString());
		
		resultVO = (ResultVO) baseClientRest.objetoPut(
				token,
				BaseClientRest.URL_CRUD_ASIGNACION,
				asignacion,
				asignacion.getIdAsignacion());
				
		return resultVO;
	}

	@Override
	public ResultVO consultaAsignacion(String token) {
		
		ResultVO rs= (ResultVO) baseClientRest.objetoGetAll(token, BaseClientRest.URL_CRUD_ASIGNACIONES);
		if(rs.getCodigo() == 202) {
			JSONObject jsonGeneral = rs.getJsonResponse();
			JSONObject jsonAsignaciones = new JSONObject();
			jsonAsignaciones.put("asignaciones", jsonGeneral.get("asignaciones"));
			
			rs.setJsonResponseObject(jsonAsignaciones);
//			log.info(jsonGeneral.toString());
//			log.info(rs.toString());
			return rs;
		}else {
			return rs;
		}
	}


}
