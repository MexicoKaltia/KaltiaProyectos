package mx.uniprotec.inicio.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.uniprotec.entidad.modelo.Instructor;
import mx.uniprotec.entidad.modelo.MonitorEntidades;
import mx.uniprotec.entidad.modelo.ResultVO;
import mx.uniprotec.inicio.util.BaseClientRest;
import mx.uniprotec.inicio.util.ComponenteComun;

@Service
public class InstructorService implements IInstructorService {
	
	private static Logger log = LoggerFactory.getLogger(ClienteService.class);
	
//	@Autowired
	Instructor instructor = new Instructor();
//	@Autowired
	ResultVO resultVO = new  ResultVO();
	@Autowired
	BaseClientRest baseClientRest;
//	@Autowired
	MonitorEntidades me = new  MonitorEntidades();

	@Override
	public ResultVO altaInstructor(Instructor instructor, String token) {
		log.info(instructor.toString());
		
//		List<CursoModelo> listCurso =  new ArrayList<CursoModelo>();
//		for(Long idCurso : instructor.getListCursoInstructor()) {
//			listCurso.add(new Curso(idCurso));
//		}
//		instructor.setCurso(listCurso);
		
		me = ComponenteComun.monitorCampos();
		instructor.setCreateAtInstructor(me.getNowEntidad());
		instructor.setUserCreateInstructor(me.getIdUsuarioEntidad());
		instructor.setStatusInstructor(me.getStatusEntidad());
		
		log.info(instructor.toString());
		
		resultVO = (ResultVO) baseClientRest.objetoPost(
				token,
				BaseClientRest.URL_CRUD_INSTRUCTOR,
				instructor);
		
		return resultVO;
	}

	@Override
	public ResultVO edicionInstructor(Instructor cliente, String token) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultVO consultaInstructores( String token) {
		// TODO Auto-generated method stub
		return null;
	}

}
