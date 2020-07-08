package mx.uniprotec.application.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.uniprotec.application.dao.IInstructorDao;
import mx.uniprotec.application.entity.Instructor;
import mx.uniprotec.application.entity.Region;

@Service
public class InstructorServiceImpl implements IInstructorService {

	@Autowired
	private IInstructorDao instructorDao;

	@Override
	@Transactional(readOnly = true)
	public List<Instructor> findAll() {
		return (List<Instructor>) instructorDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Instructor> findAll(Pageable pageable) {
		return null;//instructorDao.findAll(pageable);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Instructor findById(Long id) {
		return instructorDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Instructor save(Instructor instructor) {
		return instructorDao.save(instructor);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		instructorDao.deleteById(id);
	}

//	@Override
//	@Transactional(readOnly = true)
//	public List<Region> findAllRegiones() {
//		return instructorDao.findAllRegiones();
//	}

}
