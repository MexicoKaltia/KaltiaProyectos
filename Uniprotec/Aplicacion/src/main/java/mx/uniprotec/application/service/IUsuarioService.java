package mx.uniprotec.application.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import mx.uniprotec.application.entity.Usuario;
import mx.uniprotec.application.entity.Usuario1;

public interface IUsuarioService {

	public Usuario findByUsername(String username);
	
	public List<Usuario> findAll();
	public Page<Usuario> findAll(Pageable pageable);
	public Usuario findById(Long id);
	public Usuario save(Usuario usuario);
	public void delete(Long id);
	
	//public List<Region> findAllRegiones();

}
