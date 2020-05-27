package mx.uniprotec.application.service;

import java.util.List;

import mx.uniprotec.application.entity.Asignacion;

public interface IAsignacionService {
	
	public List<Asignacion> findAll();
	
	public Asignacion findById(Long id);
	
	public Asignacion save(Asignacion asignacion);
	
	public void delete(Long id);
	
}
