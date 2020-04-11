package mx.uniprotec.inicio.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.uniprotec.entidad.modelo.CursoModelo;
import mx.uniprotec.entidad.modelo.Instructor;
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultVO consultaCursos(String token) {
		// TODO Auto-generated method stub
		return null;
	}

}
