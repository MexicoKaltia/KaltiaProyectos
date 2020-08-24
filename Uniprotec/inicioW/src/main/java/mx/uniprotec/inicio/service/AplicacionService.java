package mx.uniprotec.inicio.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import mx.uniprotec.entidad.modelo.MensajeModelo;
import mx.uniprotec.entidad.modelo.MonitorEntidades;
import mx.uniprotec.entidad.modelo.Region;
import mx.uniprotec.entidad.modelo.ResultVO;
import mx.uniprotec.entidad.modelo.UserCorreo;
import mx.uniprotec.entidad.modelo.VendedorModelo;
import mx.uniprotec.inicio.entity.StatusVO;
import mx.uniprotec.inicio.util.BaseClientRest;
import mx.uniprotec.inicio.util.IBaseClientRest;


@Service
public class AplicacionService implements IAplicacionService {

	public AplicacionService() {}
	
	private static Logger log = LoggerFactory.getLogger(AplicacionService.class);
	
	ResultVO resultVO = new ResultVO();
	
	@Autowired
	IBaseClientRest baseClientRest ;
	
	@Autowired
	IMailService mailService;
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
	public ResultVO altaMensaje(MensajeModelo mensaje, String accesToken) {
		
		ResultVO rs = (ResultVO) baseClientRest.objetoPost(accesToken, BaseClientRest.URL_CRUD_MENSAJE, mensaje);
		return rs;
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
	
	@Override
	public void enviaMail(AsignacionModelo asignacion, String token) {
		String inicio = hora();
		StatusVO statusVO = mailService.mailServicePreCorreo(asignacion, token);
		
		log.info("inicio:"+inicio+"\t final:"+hora());
		
	//	return statusVO;
		
	}
	
	
	
	@Override
	public List<UserCorreo> usersCorreo(Long idInstructorAsignacion, Long userCreateAsignacion, String token) {
		
		UserCorreo userVendedor = new UserCorreo(userCreateAsignacion, "Ventas");
		UserCorreo userInstructor = new UserCorreo(idInstructorAsignacion, "Instructor");
		List<UserCorreo> usersCorreo = new ArrayList<UserCorreo>();
		usersCorreo.add(userVendedor);
		usersCorreo.add(userInstructor);
		
		usersCorreo =  (List<UserCorreo>) baseClientRest.objetoGetObject(token, BaseClientRest.URL_CRUD_CORREOS, usersCorreo);
		
//			JSONObject jsonGeneral = rs.getJsonResponse();
//			JSONObject jsonuserCorreo = new JSONObject();
//			jsonuserCorreo.put("usersCorreo", jsonGeneral.get("usersCorreo"));
//			log.info(jsonuserCorreo.toJSONString());
//			
//			JSONArray ja = new JSONArray();
//			ja.add(jsonuserCorreo);
//			
//			
//			Iterator<JSONObject> iter = ja.iterator();//jsonUsuariosCorreo.iterator(); 
//			while(iter.hasNext()){
//				log.info(iter.toString());
//				JSONObject node = iter.next();
//				log.info(node.get("idUser").toString());
//				log.info(node.get("perfil").toString());
//				log.info(node.get("emailUniprotec").toString());
//				log.info(node.get("emailGmail").toString());
//				
//				UserCorreo userCorreo = new UserCorreo(Long.valueOf(node.get("idUser").toString()),node.get("perfil").toString(),node.get("emailUniprotec").toString(),node.get("emailGmail").toString());
//				usersCorreo.add(userCorreo);
//			}
			
			return usersCorreo;
		
	}
	
	
	
	
	
	private String hora() {
		
		java.util.Date date = new java.util.Date();
		DateFormat hourFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
											//      dd/MM/yyyy
											//		HH:mm:ss dd/MM/yyyy
		
		return hourFormat.format(date);
	}



	@Override
	public StatusVO enviaMail(AsignacionModelo asignacion) {
		// TODO Auto-generated method stub
		return null;
	}






	



	
	
	}
