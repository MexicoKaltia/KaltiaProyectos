package mx.uniprotec.application.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.uniprotec.application.dao.ICursoDao;
import mx.uniprotec.application.entity.Curso;
import mx.uniprotec.application.entity.Instructor;
import mx.uniprotec.application.entity.Region;
import mx.uniprotec.entidad.modelo.CursoModelo;



@Service
public class CursoServiceImpl implements ICursoService {
	private static Logger log = LoggerFactory.getLogger(CursoServiceImpl.class);
	
	@Autowired
	IInstructorService instructorService;

	@Autowired
	private ICursoDao CursoDao;

	@Override
	@Transactional(readOnly = true)
	public List<Curso> findAll() {
		return (List<Curso>) CursoDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Curso> findAll(Pageable pageable) {
		return CursoDao.findAll(pageable);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Curso findById(Long id) {
		return CursoDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Curso save(Curso curso) {

//		Curso cursoEntity = new Curso();
		
		try {
//			cursoEntity.setNombreCurso(curso.getNombreCurso());
//			cursoEntity.setNotaCurso(curso.getNotaCurso());
//			cursoEntity.setCreateAtCurso(curso.getCreateAtCurso());
//			cursoEntity.setStatusCurso(curso.getStatusCurso());
//			cursoEntity.setUserCreateCurso(curso.getUserCreateCurso());
//			
//			List<Instructor> allInstructores = instructorService.findAll();
//			for(Long idInstrucotor : curso.getListInstructores()) {
//				for(Instructor instructor : allInstructores) {
//					if(instructor.getIdInstructor().longValue() == idInstrucotor ) {
//						cursoEntity.addInstructor(instructor);
//					}
//				}
//			}
			log.info(curso.toString());
			return CursoDao.save(curso);
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}

	@Override
	@Transactional
	public void delete(Long id) {
		CursoDao.deleteById(id);
	}

	@Override
	public List<Region> findAllRegiones() {
		// TODO Auto-generated method stub
		return null;
	}

}
