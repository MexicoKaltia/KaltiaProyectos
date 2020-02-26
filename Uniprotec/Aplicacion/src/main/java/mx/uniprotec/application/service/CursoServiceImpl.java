package mx.uniprotec.application.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.uniprotec.application.dao.ICursoDao;
import mx.uniprotec.application.entity.Curso;
import mx.uniprotec.application.entity.Curso;
import mx.uniprotec.application.entity.Region;

@Service
public class CursoServiceImpl implements ICursoService {

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
	public Curso save(Curso Curso) {
		return CursoDao.save(Curso);
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
