package mx.uniprotec.application.service;

import java.util.List;

import mx.uniprotec.application.entity.Asignacion;
import mx.uniprotec.application.entity.AsignacionHistorico;
import mx.uniprotec.application.entity.PreAsignacion;

public interface IPreAsignacionService {
	
//	public List<Asignacion> findAll();
//	
//	public Asignacion findById(Long id);
//	
//	public Asignacion save(Asignacion asignacion);
//	
//	public void delete(Long id);
//
//	public List<AsignacionHistorico> findAllHistorico();

	public PreAsignacion savePreAsignacion(PreAsignacion pre_asignacionNew);
	
}
