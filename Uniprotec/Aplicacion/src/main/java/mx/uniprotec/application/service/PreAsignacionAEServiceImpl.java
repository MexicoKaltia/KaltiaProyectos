package mx.uniprotec.application.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.uniprotec.application.dao.IPreAsignacionAEDao;
import mx.uniprotec.application.dao.IPreAsignacionDao;
import mx.uniprotec.application.entity.PreAsignacion;
import mx.uniprotec.application.entity.PreAsignacionAEEntity;

@Service
public class PreAsignacionAEServiceImpl implements IPreAsignacionAEService {

	public PreAsignacionAEServiceImpl() {
		// TODO Auto-generated constructor stub
	}
	
	
	@Autowired
	IPreAsignacionAEDao preAsignacionAEDao;
	@Autowired
	IPreAsignacionDao preAsignacionDao;


		
	/*
	 * PRE ASIGNACION
	 */
	
	@Override
	public PreAsignacionAEEntity savePreAsignacionAE(PreAsignacionAEEntity preAsignacionAENew) {
		return preAsignacionAEDao.save(preAsignacionAENew);
	}



	

	@Override
	public List<PreAsignacionAEEntity> findAll() {
		return (List<PreAsignacionAEEntity>) preAsignacionAEDao.findAll();
	}





	@Override
	public int deleteIdpreAsignacion(Long id) {
		try {
			PreAsignacion preAsignacion = preAsignacionDao.findById(id).orElse(null);
			if(preAsignacion != null) {
				preAsignacionAEDao.deleteById(preAsignacion.getIdPreAsignacionAE());
				return 0;
			}else {
				return 98;
			}
		} catch (Exception e) {
			return 99;
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
