package mx.uniprotec.application.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import mx.uniprotec.application.entity.Instructor;
import mx.uniprotec.application.entity.Region;
import mx.uniprotec.application.entity.Vendedor;

public interface IInstructorService {

	public List<Instructor> findAll();
	
	public Page<Instructor> findAll(Pageable pageable);
	
	public Instructor findById(Long id);
	
	public Instructor save(Instructor Instructor);
	
	public void delete(Long id);

	public Instructor findByUsuarioInstructorIdUsuario(Long idUsuario);

	
	//public List<Region> findAllRegiones();

}
