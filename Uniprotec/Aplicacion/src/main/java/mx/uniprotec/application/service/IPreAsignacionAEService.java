package mx.uniprotec.application.service;

import java.util.List;

import mx.uniprotec.application.entity.Asignacion;
import mx.uniprotec.application.entity.PreAsignacion;
import mx.uniprotec.application.entity.PreAsignacionAEEntity;

public interface IPreAsignacionAEService {
	
//	public List<Asignacion> findAll();
//	
//	public Asignacion findById(Long id);
//	
//	public Asignacion save(Asignacion asignacion);
//	
//	public void delete(Long id);
//
//	public List<AsignacionHistorico> findAllHistorico();

	

	public PreAsignacionAEEntity savePreAsignacionAE(PreAsignacionAEEntity preAsignacionAENew);

	public List<PreAsignacionAEEntity> findAll();

	public int deleteIdpreAsignacion(Long id);
	
}
