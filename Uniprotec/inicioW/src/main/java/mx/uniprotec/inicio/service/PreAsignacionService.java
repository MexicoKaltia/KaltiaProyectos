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
import mx.uniprotec.entidad.modelo.PreAsignacionAE;
import mx.uniprotec.entidad.modelo.Region;
import mx.uniprotec.entidad.modelo.ResultVO;
import mx.uniprotec.entidad.modelo.VendedorModelo;
import mx.uniprotec.inicio.util.BaseClientRest;
import mx.uniprotec.inicio.util.ComponenteComun;

@Service
public class PreAsignacionService implements IPreAsignacionService{
	private static Logger log = LoggerFactory.getLogger(PreAsignacionService.class);

	MonitorEntidades me = new MonitorEntidades();
	ResultVO resultVO = new ResultVO();
	@Autowired
	BaseClientRest baseClientRest ;
	@Autowired
	IAplicacionService aplicacionService;
	@Autowired
	IInstructorService instructorService;
	
	public PreAsignacionService() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public ResultVO altaPreAsignacion(AsignacionModelo asignacion, String token, Long idUser) {
		me = ComponenteComun.monitorCampos();
		asignacion.setCreateAtAsignacion(me.getNowEntidad());
		asignacion.setUserCreateAsignacion(idUser);
		asignacion.setStatusAsignacion("Curso PreAsignado");
		
		asignacion.setIdAsignacionLogica(fecha(asignacion.getFechaAsignacion())+"-"+asignacion.getIdClienteAsignacion()+"-"+asignacion.getIdInstructorAsignacion()+"-"+asignacion.getIdCursoAsignacion());
		asignacion.setErrorProceso("");
		
		
		resultVO = (ResultVO) baseClientRest.objetoPost(
				token,
				BaseClientRest.URL_CRUD_PREASIGNACION,
				asignacion);
		
		
		return resultVO;
	}

	private String seguimiento(String seguimiento) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultVO altaPreAsignacionAE(PreAsignacionAE preAsignacionAE, String accesToken, Long idUsuario) {
		me = ComponenteComun.monitorCampos();
		preAsignacionAE.setCreateAt(me.getNowEntidad());
		preAsignacionAE.setUserCreate(idUsuario);
		preAsignacionAE.setStatus("Alta Análisis Económico");
		
		resultVO = (ResultVO) baseClientRest.objetoPost(
				accesToken,
				BaseClientRest.URL_CRUD_PREASIGNACIONAE,
				preAsignacionAE);
		
		return resultVO;
	}
	
	@Override
	public ResultVO consultaPreAsignacion(ResultVO resultVO) {
		
		ResultVO rs= (ResultVO) baseClientRest.objetoGetAll(resultVO.getAccesToken(), BaseClientRest.URL_CRUD_PREASIGNACIONES);
		
		if(rs.getCodigo() != 500) {
			JSONObject jsonGeneral = rs.getJsonResponse();
//			log.info(rs.getJsonResponse().toJSONString());
			JSONObject jsonPreAsignaciones = new JSONObject();
			jsonPreAsignaciones.put("preAsignaciones", jsonGeneral.get("preAsignaciones"));
			jsonPreAsignaciones.put("preAsignacionesAE", jsonGeneral.get("preAsignacionesAE"));
									
			ResultVO resultData = aplicacionService.consultaData(resultVO);
			rs.setAsignaciones(resultData.getAsignaciones());
			rs.setClientes(resultData.getClientes());
			rs.setInstructores(resultData.getInstructores());
			rs.setCursos(resultData.getCursos());
			rs.setRegiones(resultData.getRegiones());
			
			jsonPreAsignaciones.put("consultaData", resultData.getJsonResponseObject());
			
			rs.setJsonResponseObject(jsonPreAsignaciones);
			
		}
			return rs;
	}
	
	@Override
	public ResultVO deletePreAsignacion(String idAsignacion, String accesToken) {
	ResultVO rs= (ResultVO) baseClientRest.objetoDeleteId(idAsignacion, accesToken , BaseClientRest.URL_CRUD_PREASIGNACION_D);
		
		if(rs.getCodigo() != 500) {
			JSONObject jsonGeneral = rs.getJsonResponse();
			JSONObject jsonResponseObject = new JSONObject();
			jsonResponseObject.put("mensaje", jsonGeneral.get("mensaje"));
			rs.setJsonResponseObject(jsonResponseObject);
		}
		return rs;
	}
	
	@Override
	public ResultVO actualizaPreAsignacion(AsignacionModelo asignacion, String accesToken) {
		me = ComponenteComun.monitorCampos();
		asignacion.setCreateAtAsignacion(me.getNowEntidad());
		resultVO = (ResultVO) baseClientRest.objetoPut(
				accesToken,
				BaseClientRest.URL_CRUD_PREASIGNACION,
				asignacion,
				asignacion.getIdPreAsignacion());
//		if(resultVO.getCodigo() != 500) {
//			baseClientRest.objetoPost(
//					accesToken,
//					BaseClientRest.URL_CRUD_NOTIFICACION,
//					asignacion);
//		}
			
		return resultVO;
	}

	
	
	
	
	/*
	 * PRIVATE
	 */

	private String fecha(String fechaAsignacion) {
		String[] fechas = fechaAsignacion.split("/");
		return fechas[1]+fechas[0]+fechas[2];
	}


	

	

	

	
	
//	@Override
//	public ResultVO edicionAsignacion(AsignacionModelo asignacion, String token, String status) {
//		
//		me = ComponenteComun.monitorCampos();
//		
//		asignacion.setCreateAtAsignacion(me.getNowEntidad());
//		asignacion.setStatusAsignacion(status);
//		if(asignacion.getErrorProceso() == null || asignacion.getErrorProceso().length() == 0) {
//			asignacion.setErrorProceso("");
//		}
//		
//		//Obtener idInstructor anterior
//		Long idInstructorAnterior = null;
//		JSONObject jsonAsignacionAnterior = null;
//		resultVO = (ResultVO) baseClientRest.objetoGetId(
//				token,
//				BaseClientRest.URL_CRUD_ASIGNACION, null,
//				asignacion.getIdAsignacion().toString());
//		if(resultVO.getCodigo() != 500) {
//			JSONObject jsonObject = (JSONObject) resultVO.getJsonResponse();
//			jsonAsignacionAnterior = new JSONObject((Map) jsonObject.get("asignacion"));
//			idInstructorAnterior = Long.valueOf(jsonAsignacionAnterior.get("idInstructorAsignacion").toString());
//			log.info("IdInstructor Anterior:"+idInstructorAnterior);
//		}
//		// Actualiza Asignacion
//			resultVO = (ResultVO) baseClientRest.objetoPut(
//					token,
//					BaseClientRest.URL_CRUD_ASIGNACION,
//					asignacion,
//					asignacion.getIdAsignacion());
//			//
//		
//		//Envio de correo
//		if(resultVO.getCodigo() != 500) {
//			if(asignacion.getStatusAsignacion().equals("Curso Editado") ) {
//				JSONObject jsonObject = (JSONObject) resultVO.getJsonResponse();
//				log.info("Listo proceso envia correo");
//				aplicacionService.enviaMail(asignacion, token);
//			}
//			if(asignacion.getStatusAsignacion().equals("Curso Editado") || asignacion.getStatusAsignacion().equals("Evento Cancelado")) {
//			// Envia Notifiacion SUSTITUCION 0 CANCELACION
//				log.info("idInstructorAnterior : "+idInstructorAnterior.toString());
//				log.info("asignacion : "+asignacion.getIdInstructorAsignacion().toString());
//				if(idInstructorAnterior != Long.valueOf(asignacion.getIdInstructorAsignacion().toString()) || asignacion.getStatusAsignacion().equals("Evento Cancelado")) {
//					log.info("--------------EVENTO CAMBIO DE INSTRUCTOR O CANCELACION.....");
//					aplicacionService.enviaMailSustitucion(asignacion, token, idInstructorAnterior);
//				}
//			//Envio correo Test
////			MailServiceTest.mailServicePreCorreo(asignacion, token);
//			}
//		}
//		
//		return resultVO;
//	}
//	
//	@Override
//	public ResultVO edicionAsignacionV(AsignacionModelo asignacion, String token) {
//		
//		me = ComponenteComun.monitorCampos();
//		asignacion.setCreateAtAsignacion(me.getNowEntidad());
//		resultVO = (ResultVO) baseClientRest.objetoPut(
//				token,
//				BaseClientRest.URL_CRUD_ASIGNACION,
//				asignacion,
//				asignacion.getIdAsignacion());
//		if(resultVO.getCodigo() != 500) {
//			baseClientRest.objetoPost(
//					token,
//					BaseClientRest.URL_CRUD_NOTIFICACION,
//					asignacion);
//		}
//			
//		return resultVO;
//		
//	}
//	@Override
//	public ResultVO edicionAsignacionVConfirma(AsignacionModelo asignacion, String token) {
//		
//		me = ComponenteComun.monitorCampos();
//		asignacion.setCreateAtAsignacion(me.getNowEntidad());
//		resultVO = (ResultVO) baseClientRest.objetoPut(
//				token,
//				BaseClientRest.URL_CRUD_NOTIFICACION,
//				asignacion, asignacion.getIdAsignacion());
//			
//		return resultVO;
//		
//	}
//	
//	@Override
//	public ResultVO edicionAsignacionC(AsignacionModelo asignacion) {
//		
//		me = ComponenteComun.monitorCampos();
//		
//		asignacion.setCreateAtAsignacion(me.getNowEntidad());
////		asignacion.setUserCreateAsignacion(me.getIdUsuarioEntidad());
//		asignacion.setStatusAsignacion(asignacion.getStatusAsignacion());
//		
////		log.info(asignacion.toString());
//		
//		resultVO = (ResultVO) baseClientRest.objetoPutC(
//				BaseClientRest.URL_CRUD_ASIGNACION,
//				asignacion,
//				asignacion.getIdAsignacion());
//				
//		return resultVO;
//	}
//
//
//	@Override
//	public ResultVO consultaAsignacion(String token) {
//		
//		ResultVO rs= (ResultVO) baseClientRest.objetoGetAll(token, BaseClientRest.URL_CRUD_ASIGNACIONES);
//		if(rs.getCodigo() != 500) {
//			JSONObject jsonGeneral = rs.getJsonResponse();
////			log.info(rs.getJsonResponse().toJSONString());
//			JSONObject jsonAsignaciones = new JSONObject();
//			jsonAsignaciones.put("asignaciones", jsonGeneral.get("asignaciones"));
//			
//			ResultVO rsInstructores = instructorService.consultaInstructores(token);
//			if(rsInstructores.getCodigo() != 500) {
//				JSONObject jsonInstructores = rsInstructores.getJsonResponseObject();
//				jsonAsignaciones.put("instructores", jsonInstructores.get("instructores"));
//				
//			}else {
//				return rsInstructores;
//			}
//			
//			rs.setJsonResponseObject(jsonAsignaciones);
//		}
//			return rs;
//		
//	}
//	
//	@Override
//	public ResultVO consultaAsignacionCliente(String token) {
//		
//		ResultVO rs= (ResultVO) baseClientRest.objetoGetAll(token, BaseClientRest.URL_CRUD_ASIGNACIONES);
//		if(rs.getCodigo() != 500) {
//			JSONObject jsonGeneral = rs.getJsonResponse();
////			log.info(rs.getJsonResponse().toJSONString());
//			JSONObject jsonAsignaciones = new JSONObject();
//			jsonAsignaciones.put("asignaciones", jsonGeneral.get("asignaciones"));
//			
//			rs.setJsonResponseObject(jsonAsignaciones);
////			log.info(jsonGeneral.toString());
////			log.info(rs.toString());
//			return rs;
//		}else {
//			return rs;
//		}
//	}
//	
//	@Override
//	public ResultVO consultaAsignacionCorreo(String idAsignacion) {
//		
//		ResultVO rs= (ResultVO) baseClientRest.objetoGetIdCorreo(BaseClientRest.URL_CRUD_ASIGNACION,  idAsignacion);
//		if(rs.getCodigo() == 202) {
//			JSONObject jsonObject = (JSONObject) rs.getJsonResponse();
////			log.info(rs.getJsonResponse().toJSONString());
//			JSONObject jsonAsignaciones = new JSONObject((Map) jsonObject.get("asignacion"));
//			
//			rs.setJsonResponseObject(jsonAsignaciones);
////			log.info(jsonGeneral.toString());
////			log.info(rs.toString());
//			return rs;
//		}else {
//			return rs;
//		}
//	}
//
//	@Override
//	public ResultVO consultaAsignacionHistorico(String token) {
//		ResultVO rs= (ResultVO) baseClientRest.objetoGetAll(token, BaseClientRest.URL_CRUD_ASIGNACIONES_HISTORICO);
//		if(rs.getCodigo() != 500) {
//			JSONObject jsonGeneral = rs.getJsonResponse();
////			log.info(rs.getJsonResponse().toJSONString());
//			JSONObject jsonAsignaciones = new JSONObject();
//			jsonAsignaciones.put("asignacionesHistorico", jsonGeneral.get("asignacionesHistorico"));
//			
//			ResultVO rsInstructores = instructorService.consultaInstructores(token);
//			if(rsInstructores.getCodigo() != 500) {
//				JSONObject jsonInstructores = rsInstructores.getJsonResponseObject();
//				jsonAsignaciones.put("instructores", jsonInstructores.get("instructores"));
//				
//			}else {
//				return rsInstructores;
//			}
//			
//			rs.setJsonResponseObject(jsonAsignaciones);
//			return rs;
//		}else {
//			return rs;
//		}
//
//	}


}
