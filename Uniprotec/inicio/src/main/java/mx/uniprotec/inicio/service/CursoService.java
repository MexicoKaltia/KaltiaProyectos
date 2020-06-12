package mx.uniprotec.inicio.service;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.uniprotec.entidad.modelo.CursoModelo;
import mx.uniprotec.entidad.modelo.MonitorEntidades;
import mx.uniprotec.entidad.modelo.ResultVO;
import mx.uniprotec.inicio.util.BaseClientRest;
import mx.uniprotec.inicio.util.ComponenteComun;

@Service
public class CursoService implements ICursoService {
	
private static Logger log = LoggerFactory.getLogger(CursoService.class);
	
//	@Autowired
	CursoModelo curso = new CursoModelo();
//	@Autowired
	ResultVO resultVO = new	ResultVO ();
	@Autowired
	BaseClientRest baseClientRest;
//	@Autowired
	MonitorEntidades me = new MonitorEntidades();


	@Override
	public ResultVO altaCurso(CursoModelo curso , String token) {

		log.info(curso.toString());
		
//		List<Instructor> listInstructor =  new ArrayList<Instructor>();
//		for(Long idInstructor : curso.getListInstructores()) {
//			listInstructor.add(new Instructor(idInstructor));
//		}
//		curso.setListInstructores(listInstructor);
		
		me = ComponenteComun.monitorCampos();
		curso.setCreateAtCurso(me.getNowEntidad());
		curso.setUserCreateCurso(me.getIdUsuarioEntidad());
		curso.setStatusCurso(me.getStatusEntidad());
		
		log.info(curso.toString());
		
		resultVO = (ResultVO) baseClientRest.objetoPost(
				token,
				BaseClientRest.URL_CRUD_CURSO,
				curso);
		
		return resultVO;
	}

	@Override
	public ResultVO edicionCurso(CursoModelo curso, String token) {
		log.info(curso.toString());
		
//		List<Instructor> listInstructor =  new ArrayList<Instructor>();
//		for(Long idInstructor : curso.getListInstructores()) {
//			listInstructor.add(new Instructor(idInstructor));
//		}
//		curso.setListInstructores(listInstructor);
		
		me = ComponenteComun.monitorCampos();
		curso.setCreateAtCurso(me.getNowEntidad());
		curso.setUserCreateCurso(me.getIdUsuarioEntidad());
		curso.setStatusCurso(me.getStatusEntidad());
		
		log.info(curso.toString());
		
		resultVO = (ResultVO) baseClientRest.objetoPut(
				token,
				BaseClientRest.URL_CRUD_CURSO,
				curso,
				curso.getIdCurso());
				
		return resultVO;
	}

	@Override
	public ResultVO consultaCursos(String token) {
	
		ResultVO rs = (ResultVO) baseClientRest.objetoGetAll(token, BaseClientRest.URL_CRUD_CURSOS);
		
		if(rs.getCodigo() == 202) {
			JSONObject jsonGeneral = rs.getJsonResponse();
			JSONObject jsonClientes = new JSONObject();
			jsonClientes.put("cursos", jsonGeneral.get("cursos"));
			
			rs.setJsonResponseObject(jsonClientes);
			return rs;
		}else {
			return rs;
		}
		
	}

}
