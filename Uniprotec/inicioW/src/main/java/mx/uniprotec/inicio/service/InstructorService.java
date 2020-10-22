package mx.uniprotec.inicio.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.NestedRuntimeException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import mx.uniprotec.entidad.modelo.CursoModelo;
import mx.uniprotec.entidad.modelo.InstructorModelo;
import mx.uniprotec.entidad.modelo.MonitorEntidades;
import mx.uniprotec.entidad.modelo.ResultVO;
import mx.uniprotec.entidad.modelo.UsuarioModelo;
import mx.uniprotec.inicio.util.BaseClientRest;
import mx.uniprotec.inicio.util.ComponenteComun;

@Service
public class InstructorService implements IInstructorService {
	
	
	private static Logger log = LoggerFactory.getLogger(InstructorService.class);
	@Autowired
	UsuarioService usuarioService;
	InstructorModelo instructor = new InstructorModelo();
	ResultVO resultVO = new  ResultVO();
	@Autowired
	BaseClientRest baseClientRest;
	MonitorEntidades me = new  MonitorEntidades();

	@Override
	public ResultVO altaInstructor(InstructorModelo instructor, String token) {
		
		UsuarioModelo usuario = new UsuarioModelo();
		usuario.setNombreUsuario(instructor.getNombreInstructor());
		usuario.setEmailUsuario(instructor.getEmailInstructor().concat("@uniprotec.net"));
		usuario.setPerfilUsuario("Instructor");
		usuario.setUsernameUsuario(instructor.getEmailInstructor());
//		usuario.setPasswordUsuario("12345678");
		usuario.setNotaUsuario(instructor.getNotaInstructor());
		ResultVO resultUsuario = usuarioService.altaUsuario(usuario, token);

		try {
			JSONObject jsonObject = (JSONObject) resultUsuario.getJsonResponse();
			JSONObject jsonUsuario = new JSONObject((Map) jsonObject.get("usuario"));
			Long idUsuario = Long.valueOf( jsonUsuario.get("idUsuario").toString());
			instructor.setUsuarioInstructor(idUsuario);
		} catch (Exception e) {
			JSONObject jsonResponse = new JSONObject();
		    ResultVO rs = new ResultVO();
		    rs.setJsonResponse(jsonResponse);
		    rs.setMensaje("Error: Usuario ya Existe");
		    rs.setCodigo(Long.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()));
		    
			return rs;
		}
		
		
		List<CursoModelo> listCurso =  new ArrayList<CursoModelo>();
		for(Long idCurso : instructor.getListCursoInstructor()) {
			listCurso.add(new CursoModelo(idCurso));
		}
		
		instructor.setEmailInstructor(instructor.getEmailInstructor().concat("@uniprotec.net"));
		instructor.setEmailGmailInstructor(instructor.getEmailGmailInstructor().concat("@gmail.com"));
		
		me = ComponenteComun.monitorCampos();
		instructor.setCreateAtInstructor(me.getNowEntidad());
		instructor.setUserCreateInstructor(me.getIdUsuarioEntidad());
		instructor.setStatusInstructor(me.getStatusEntidad());
		
		
//		log.info(instructor.toString());
		
		resultVO = (ResultVO) baseClientRest.objetoPost(
				token,
				BaseClientRest.URL_CRUD_INSTRUCTOR,
				instructor);
		
		return resultVO;
	}

	@Override
	public ResultVO edicionInstructor(InstructorModelo instructor, String token) {
		me = ComponenteComun.monitorCampos();
		
//		instructor.setEmailInstructor(email);
//		instructor.getEmailInstructorGmail()
		
		instructor.setCreateAtInstructor(me.getNowEntidad());
		instructor.setUserCreateInstructor(me.getIdUsuarioEntidad());
//		instructor.setStatusInstructor("Actualizado");
		
//		log.info(instructor.toString());
		
		resultVO = (ResultVO) baseClientRest.objetoPut(
				token,
				BaseClientRest.URL_CRUD_INSTRUCTOR,
				instructor,
				instructor.getIdInstructor());
				
		return resultVO;
	}

	@Override
	public ResultVO consultaInstructores( String token) {
		
		ResultVO rs = (ResultVO) baseClientRest.objetoGetAll(token, BaseClientRest.URL_CRUD_INSTRUCTORES);
		
		if(rs.getCodigo() == 202) {
			JSONObject jsonGeneral = rs.getJsonResponse();
			JSONObject jsonInstructores = new JSONObject();
			jsonInstructores.put("instructores", jsonGeneral.get("instructores"));
			
			rs.setJsonResponseObject(jsonInstructores);
			return rs;
		}else {
			return rs;
		}
	}

}
