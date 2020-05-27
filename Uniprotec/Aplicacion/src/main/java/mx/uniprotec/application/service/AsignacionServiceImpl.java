package mx.uniprotec.application.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.uniprotec.application.dao.IAsignacionDao;
import mx.uniprotec.application.entity.Asignacion;

@Service
public class AsignacionServiceImpl implements IAsignacionService {

	public AsignacionServiceImpl() {
		// TODO Auto-generated constructor stub
	}
	
	@Autowired
	IAsignacionDao asignacionDao;

	@Override
	@Transactional(readOnly = true)
	public List<Asignacion> findAll() {
		return (List<Asignacion>) asignacionDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Asignacion findById(Long id) {
		return asignacionDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Asignacion save(Asignacion asignacion) {
		return asignacionDao.save(asignacion);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		asignacionDao.deleteById(id);

	}

}
