package mx.uniprotec.inicio.service;

import java.util.List;
import java.util.Map;

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
	@Autowired
	IAplicacionService aplicacionService;
	
	public AsignacionService() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public ResultVO altaAsignacion(AsignacionModelo asignacion, String token, Long idUser) {
		me = ComponenteComun.monitorCampos();
		asignacion.setCreateAtAsignacion(me.getNowEntidad());
		asignacion.setUserCreateAsignacion(idUser);
		asignacion.setStatusAsignacion("Curso Asignado");
		asignacion.setIdAsignacionLogica(fecha(asignacion.getFechaAsignacion())+"-"+asignacion.getIdClienteAsignacion()+"-"+asignacion.getIdInstructorAsignacion()+"-"+asignacion.getIdCursoAsignacion());
		log.info(asignacion.toString());
		
		resultVO = (ResultVO) baseClientRest.objetoPost(
				token,
				BaseClientRest.URL_CRUD_ASIGNACION,
				asignacion);
		
		//Envio de correo
		if(resultVO.getCodigo() != 500) {
			aplicacionService.enviaMail(asignacion, token);
		}
		return resultVO;
	}

	private String fecha(String fechaAsignacion) {
		String[] fechas = fechaAsignacion.split("/");
		return fechas[1]+fechas[0]+fechas[2];
	}

	@Override
	public ResultVO edicionAsignacion(AsignacionModelo asignacion, String token, String status) {
		
		me = ComponenteComun.monitorCampos();
		
		asignacion.setCreateAtAsignacion(me.getNowEntidad());
//		asignacion.setUserCreateAsignacion(me.getIdUsuarioEntidad());
		asignacion.setStatusAsignacion(status);
		
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
			log.info(rs.getJsonResponse().toJSONString());
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
	
	@Override
	public ResultVO consultaAsignacionCorreo(String idAsignacion) {
		
		ResultVO rs= (ResultVO) baseClientRest.objetoGetIdCorreo(BaseClientRest.URL_CRUD_ASIGNACION,  idAsignacion);
		if(rs.getCodigo() == 202) {
			JSONObject jsonObject = (JSONObject) rs.getJsonResponse();
			log.info(rs.getJsonResponse().toJSONString());
			JSONObject jsonUsuario = new JSONObject((Map) jsonObject.get("asignacion"));
			
//			rs.setJsonResponseObject(jsonAsignaciones);
//			log.info(jsonGeneral.toString());
//			log.info(rs.toString());
			return rs;
		}else {
			return rs;
		}
	}


}
