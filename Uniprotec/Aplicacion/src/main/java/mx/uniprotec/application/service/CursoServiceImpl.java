package mx.uniprotec.application.service;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.uniprotec.application.dao.ICursoDao;
import mx.uniprotec.application.entity.Curso;
import mx.uniprotec.application.entity.Instructor;
import mx.uniprotec.application.entity.Region;
import mx.uniprotec.application.util.UtilController;
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
	public Curso save(CursoModelo cursoModelo) {

		Curso cursoEntity = new Curso();
		
		try {
			List<Instructor> allInstructores = instructorService.findAll();
//			for(Long idInstrucotor : cursoModelo.getListInstructores()) {
//				for(Instructor instructor : allInstructores) {
//					if(instructor.getIdInstructor().longValue() == idInstrucotor ) {
//						cursoEntity.addInstructor(instructor);
//					}
//				}
//			}
			List<Instructor> instructores = new ArrayList<Instructor>();
			for(Long idInstructor : cursoModelo.getListInstructores()) {
				for(Instructor instructor : allInstructores) {
					if(instructor.getIdInstructor().longValue() == idInstructor ) {
						instructores.add(instructor);
					}
				}
			}
			
			
			cursoEntity.setNombreCurso(cursoModelo.getNombreCurso());
			cursoEntity.setInstructores(instructores);
			cursoEntity.setNotaCurso(cursoModelo.getNotaCurso());
			cursoEntity.setUserCreateCurso(cursoModelo.getUserCreateCurso());
			cursoEntity.setCreateAtCurso(cursoModelo.getCreateAtCurso());
			cursoEntity.setStatusCurso(cursoModelo.getStatusCurso());
			
			log.info(cursoEntity.toString());
			return CursoDao.save(cursoEntity);
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	@Override
	public Curso update(@Valid Curso curso) {
		
		log.info(curso.toString());
		return CursoDao.save(curso);
		

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
