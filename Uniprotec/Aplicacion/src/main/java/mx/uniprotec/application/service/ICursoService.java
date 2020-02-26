package mx.uniprotec.application.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import mx.uniprotec.application.entity.Curso;
import mx.uniprotec.application.entity.Region;

public interface ICursoService {

	public List<Curso> findAll();
	
	public Page<Curso> findAll(Pageable pageable);
	
	public Curso findById(Long id);
	
	public Curso save(Curso Curso);
	
	public void delete(Long id);
	
	public List<Region> findAllRegiones();

}