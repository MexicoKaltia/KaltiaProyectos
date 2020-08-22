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

import mx.uniprotec.application.controller.InstructorRestController;
import mx.uniprotec.application.dao.IInstructorDao;
import mx.uniprotec.application.entity.Instructor;
import mx.uniprotec.application.entity.Region;

@Service
public class InstructorServiceImpl implements IInstructorService {
	
	private final Logger log = LoggerFactory.getLogger(InstructorServiceImpl.class);

	@Autowired
	private IInstructorDao instructorDao;

	@Override
	@Transactional(readOnly = true)
	public List<Instructor> findAll() {
//		List<Instructor> listaInstructores = (List<Instructor>) instructorDao.findAll();
//		List<Instructor> listaInstructoresA = new ArrayList<Instructor>(); 
//		for(Instructor i : listaInstructores) {
//			if(!i.getStatusInstructor().equals("Baja")) {
//				listaInstructoresA.add(i);
//			}
//		}
//		return  listaInstructoresA;
		return (List<Instructor>) instructorDao.findAllActivo();
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
