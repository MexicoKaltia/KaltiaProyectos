package mx.uniprotec.inicio.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.uniprotec.inicio.entity.Curso;
import mx.uniprotec.inicio.entity.Instructor;
import mx.uniprotec.inicio.entity.LoginSingle;
import mx.uniprotec.inicio.entity.MonitorEntidades;
import mx.uniprotec.inicio.entity.ResultVO;
import mx.uniprotec.inicio.util.BaseClientRest;
import mx.uniprotec.inicio.util.ComponenteComun;

@Service
public class CursoService implements ICursoService {
	
private static Logger log = LoggerFactory.getLogger(CursoService.class);
	
	@Autowired
	Curso curso;
//	@Autowired
//	Region region;
	@Autowired
	ResultVO resultVO;
	@Autowired
	BaseClientRest baseClientRest;
	@Autowired
	MonitorEntidades me;


	@SuppressWarnings("null")
	@Override
	public ResultVO altaCurso(Curso curso) {

		log.info(curso.toString());
		
		List<Instructor> listInstructor =  new ArrayList<Instructor>();
		for(Long idInstructor : curso.getListInstructorCurso()) {
			listInstructor.add(new Instructor(idInstructor));
		}
		curso.setListInstructoresCurso(listInstructor);
		
		me = ComponenteComun.monitorCampos();
		curso.setCreateAtCurso(me.getNowEntidad());
		curso.setUserCreateCurso(me.getIdUsuarioEntidad());
		curso.setStatusCurso(me.getStatusEntidad());
		
		log.info(curso.toString());
		
		resultVO = (ResultVO) baseClientRest.objetoPost(
				me.getToken(),
				BaseClientRest.URL_CRUD_CURSO,
				curso);
		
		return resultVO;
	}

	@Override
	public ResultVO edicionCurso(Curso curso) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultVO consultaCursos() {
		// TODO Auto-generated method stub
		return null;
	}

}
