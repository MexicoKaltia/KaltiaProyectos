package mx.uniprotec.inicio.service;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.uniprotec.entidad.modelo.CursoModelo;
import mx.uniprotec.entidad.modelo.InstructorModelo;
import mx.uniprotec.entidad.modelo.MonitorEntidades;
import mx.uniprotec.entidad.modelo.ResultVO;
import mx.uniprotec.inicio.util.BaseClientRest;
import mx.uniprotec.inicio.util.ComponenteComun;

@Service
public class InstructorService implements IInstructorService {
	
	private static Logger log = LoggerFactory.getLogger(InstructorService.class);
	
//	@Autowired
	InstructorModelo instructor = new InstructorModelo();
//	@Autowired
	ResultVO resultVO = new  ResultVO();
	@Autowired
	BaseClientRest baseClientRest;
//	@Autowired
	MonitorEntidades me = new  MonitorEntidades();

	@Override
	public ResultVO altaInstructor(InstructorModelo instructor, String token) {
		log.info(instructor.toString());
		
		List<CursoModelo> listCurso =  new ArrayList<CursoModelo>();
		for(Long idCurso : instructor.getListCursoInstructor()) {
			listCurso.add(new CursoModelo(idCurso));
		}
//		instructor.setListCursoInstructor(listCursoInstructor);
		
		me = ComponenteComun.monitorCampos();
		instructor.setCreateAtInstructor(me.getNowEntidad());
		instructor.setUserCreateInstructor(me.getIdUsuarioEntidad());
		instructor.setStatusInstructor(me.getStatusEntidad());
		instructor.setEmailGmailInstructor(instructor.getEmailGmailInstructor().concat("@gmail.com"));
		
		log.info(instructor.toString());
		
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
		instructor.setStatusInstructor(me.getStatusEntidad());
		
		log.info(instructor.toString());
		
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
			JSONObject jsonClientes = new JSONObject();
			jsonClientes.put("instructores", jsonGeneral.get("instructores"));
			
			rs.setJsonResponseObject(jsonClientes);
			return rs;
		}else {
			return rs;
		}
	}

}
