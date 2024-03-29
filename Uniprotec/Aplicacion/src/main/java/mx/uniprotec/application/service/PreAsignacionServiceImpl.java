package mx.uniprotec.application.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.uniprotec.application.dao.IAsignacionDao;
import mx.uniprotec.application.dao.IAsignacionHistoricoDao;
import mx.uniprotec.application.dao.IPreAsignacionAEDao;
import mx.uniprotec.application.dao.IPreAsignacionDao;
import mx.uniprotec.application.entity.PreAsignacion;

@Service
public class PreAsignacionServiceImpl implements IPreAsignacionService {

	public PreAsignacionServiceImpl() {
		// TODO Auto-generated constructor stub
	}
	
	@Autowired
	IAsignacionDao asignacionDao;
	@Autowired
	IAsignacionHistoricoDao asignacionHistoricoDao;
	@Autowired
	IPreAsignacionDao preAsignacionDao;
	@Autowired
	IPreAsignacionAEDao preAsignacionAEDao;


		
	/*
	 * PRE ASIGNACION
	 */
	@Override
	@Transactional
	public PreAsignacion savePreAsignacion(PreAsignacion asignacion) {
		return preAsignacionDao.save(asignacion);
	}



	@Override
	public List<PreAsignacion> findAll() {
		return (List<PreAsignacion>) preAsignacionDao.findAll();
	}



	@Override
	public PreAsignacion findId(Long id) {
		return preAsignacionDao.findById(id).orElse(null);
	}



	@Override
	public int deleteId(Long id) {
		try {
			preAsignacionDao.deleteById(id);
			return  0;
		} catch (Exception e) {
			return  99;
		}
		
	}



	
	
//	@Override
//	@Transactional(readOnly = true)
//	public List<Asignacion> findAll() {
//		return (List<Asignacion>) asignacionDao.findAll();
//	}
//
//	@Override
//	@Transactional(readOnly = true)
//	public Asignacion findById(Long id) {
//		return asignacionDao.findById(id).orElse(null);
//	}
//
//	@Override
//	@Transactional
//	public Asignacion save(Asignacion asignacion) {
//		return asignacionDao.save(asignacion);
//	}
//
//	@Override
//	@Transactional
//	public void delete(Long id) {
//		asignacionDao.deleteById(id);
//
//	}
//	
//	@Override
//	@Transactional(readOnly = true)
//	public List<AsignacionHistorico> findAllHistorico() {
//		return (List<AsignacionHistorico>) asignacionHistoricoDao.findAll();
//	}


}
