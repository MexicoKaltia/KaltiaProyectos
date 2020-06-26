package mx.uniprotec.application.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import mx.uniprotec.application.entity.Region;
import mx.uniprotec.application.entity.Usuario;
import mx.uniprotec.application.entity.Usuario2;

public interface IUsuarioService {

	public Usuario findByUsername(String username);
	
	public List<Usuario2> findAll();
	public Page<Usuario2> findAll(Pageable pageable);
	public Usuario2 findById(Long id);
	public Usuario2 save(Usuario2 usuario);
	public void delete(Long id);
	
	//public List<Region> findAllRegiones();

}
