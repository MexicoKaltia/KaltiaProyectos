package mx.uniprotec.application.service;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.uniprotec.application.controller.AsignacionRestController;
import mx.uniprotec.application.dao.IAsignacionDao;
import mx.uniprotec.application.dao.IAsignacionHistoricoDao;
import mx.uniprotec.application.entity.Asignacion;
import mx.uniprotec.application.entity.AsignacionHistorico;

@Service
public class AsignacionServiceImpl implements IAsignacionService {
	 private final Logger log = LoggerFactory.getLogger(AsignacionServiceImpl.class);

	public AsignacionServiceImpl() {
		// TODO Auto-generated constructor stub
	}
	
	@Autowired
	IAsignacionDao asignacionDao;
	@Autowired
	IAsignacionHistoricoDao asignacionHistoricoDao;


	@Override
	@Transactional(readOnly = true)
	public List<Asignacion> findAll() {
		return (List<Asignacion>) asignacionDao.findAll();
	}
	@Override
	@Transactional(readOnly = true)
	public List<Asignacion> findTrimestre() {
		
		 Calendar hoy = Calendar.getInstance();
		 Calendar hoyFuturo = Calendar.getInstance();
		 hoy.add(Calendar.MONTH, -3);
		 
		 String pasado= String.valueOf(hoy.get(Calendar.YEAR)).concat("-").concat(String.valueOf(hoy.get(Calendar.MONTH)+1)).concat("-01");
		 String futuro= String.valueOf(hoyFuturo.get(Calendar.YEAR) + 1).concat("-12-31");
		 log.info("periodo  : " + pasado + "-" + futuro);
		return (List<Asignacion>) asignacionDao.findTrimestre(pasado, futuro);
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
	
	@Override
	@Transactional(readOnly = true)
	public List<AsignacionHistorico> findAllHistorico() {
		return (List<AsignacionHistorico>) asignacionHistoricoDao.findAll();
	}

}
