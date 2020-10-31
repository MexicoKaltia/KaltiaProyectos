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
	@Autowired
	IInstructorService instructorService;
	
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
//		log.info(asignacion.toString());
		
		resultVO = (ResultVO) baseClientRest.objetoPost(
				token,
				BaseClientRest.URL_CRUD_ASIGNACION,
				asignacion);
		
		//Envio de correo
		if(resultVO.getCodigo() != 500) {
			JSONObject jsonObject = (JSONObject) resultVO.getJsonResponse();
			JSONObject jsonAsignacion = new JSONObject((Map) jsonObject.get("asignacion"));
			asignacion.setIdAsignacion(Long.valueOf(jsonAsignacion.get("idAsignacion").toString()));
			aplicacionService.enviaMail(asignacion, token);	
			
			//Envio correo Test
//			MailServiceTest.mailServicePreCorreo(asignacion, token);
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
		asignacion.setStatusAsignacion(status);
		
		//Obtener idInstructor anterior
		Long idInstructorAnterior = null;
		JSONObject jsonAsignacionAnterior = null;
		resultVO = (ResultVO) baseClientRest.objetoGetId(
				token,
				BaseClientRest.URL_CRUD_ASIGNACION, null,
				asignacion.getIdAsignacion().toString());
		if(resultVO.getCodigo() != 500) {
			JSONObject jsonObject = (JSONObject) resultVO.getJsonResponse();
			jsonAsignacionAnterior = new JSONObject((Map) jsonObject.get("asignacion"));
			idInstructorAnterior = Long.valueOf(jsonAsignacionAnterior.get("idInstructorAsignacion").toString());
			log.info("IdInstructor Anterior:"+idInstructorAnterior);
		}
		//

		// Actualiza Asignacion
		resultVO = (ResultVO) baseClientRest.objetoPut(
				token,
				BaseClientRest.URL_CRUD_ASIGNACION,
				asignacion,
				asignacion.getIdAsignacion());
		//
		
		//Envio de correo
		if(asignacion.getStatusAsignacion().equals("Curso Editado")) {
			if(resultVO.getCodigo() != 500) {
				JSONObject jsonObject = (JSONObject) resultVO.getJsonResponse();
				JSONObject jsonAsignacion = new JSONObject((Map) jsonObject.get("asignacion"));
//				asignacion.setIdAsignacion(Long.valueOf(jsonAsignacion.get("idAsignacion").toString()));
				log.info("Listo proceso envia correo");
				aplicacionService.enviaMail(asignacion, token);
			}
		}
		//
		
		// Envia Notifiacion SUSTITUCION
		log.info("idInstructorAnterior : "+idInstructorAnterior.toString());
		log.info("asignacion : "+asignacion.getIdInstructorAsignacion().toString());
		if(idInstructorAnterior != Long.valueOf(asignacion.getIdInstructorAsignacion().toString())  || asignacion.getStatusAsignacion().equals("Evento Cancelado")) {
			log.info("--------------EVENTO CAMBIO DE INSTRUCTOR O CANCELACION.....");
			if(asignacion.getStatusAsignacion().equals("Evento Cancelado")) {
				aplicacionService.enviaMailSustitucion(asignacion, token, asignacion.getIdInstructorAsignacion());
			}else {
				aplicacionService.enviaMailSustitucion(asignacion, token, idInstructorAnterior);
			}
			
		}

		return resultVO;
	}
	
	@Override
	public ResultVO edicionAsignacionV(AsignacionModelo asignacion, String token) {
		
		me = ComponenteComun.monitorCampos();
		asignacion.setCreateAtAsignacion(me.getNowEntidad());
		resultVO = (ResultVO) baseClientRest.objetoPut(
				token,
				BaseClientRest.URL_CRUD_ASIGNACION,
				asignacion,
				asignacion.getIdAsignacion());
		if(resultVO.getCodigo() != 500) {
			baseClientRest.objetoPost(
					token,
					BaseClientRest.URL_CRUD_NOTIFICACION,
					asignacion);
		}
			
		return resultVO;
		
	}
	@Override
	public ResultVO edicionAsignacionVConfirma(AsignacionModelo asignacion, String token) {
		
		me = ComponenteComun.monitorCampos();
		asignacion.setCreateAtAsignacion(me.getNowEntidad());
		resultVO = (ResultVO) baseClientRest.objetoPut(
				token,
				BaseClientRest.URL_CRUD_NOTIFICACION,
				asignacion, asignacion.getIdAsignacion());
			
		return resultVO;
		
	}
	
	@Override
	public ResultVO edicionAsignacionC(AsignacionModelo asignacion) {
		
		me = ComponenteComun.monitorCampos();
		
		asignacion.setCreateAtAsignacion(me.getNowEntidad());
//		asignacion.setUserCreateAsignacion(me.getIdUsuarioEntidad());
		asignacion.setStatusAsignacion(asignacion.getStatusAsignacion());
		
//		log.info(asignacion.toString());
		
		resultVO = (ResultVO) baseClientRest.objetoPutC(
				BaseClientRest.URL_CRUD_ASIGNACION,
				asignacion,
				asignacion.getIdAsignacion());
				
		return resultVO;
	}


	@Override
	public ResultVO consultaAsignacion(String token) {
		
		ResultVO rs= (ResultVO) baseClientRest.objetoGetAll(token, BaseClientRest.URL_CRUD_ASIGNACIONES);
		if(rs.getCodigo() != 500) {
			JSONObject jsonGeneral = rs.getJsonResponse();
//			log.info(rs.getJsonResponse().toJSONString());
			JSONObject jsonAsignaciones = new JSONObject();
			jsonAsignaciones.put("asignaciones", jsonGeneral.get("asignaciones"));
			
			ResultVO rsInstructores = instructorService.consultaInstructores(token);
			if(rsInstructores.getCodigo() != 500) {
				JSONObject jsonInstructores = rsInstructores.getJsonResponseObject();
				jsonAsignaciones.put("instructores", jsonInstructores.get("instructores"));
				
			}else {
				return rsInstructores;
			}
			
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
//			log.info(rs.getJsonResponse().toJSONString());
			JSONObject jsonAsignaciones = new JSONObject((Map) jsonObject.get("asignacion"));
			
			rs.setJsonResponseObject(jsonAsignaciones);
//			log.info(jsonGeneral.toString());
//			log.info(rs.toString());
			return rs;
		}else {
			return rs;
		}
	}


}
