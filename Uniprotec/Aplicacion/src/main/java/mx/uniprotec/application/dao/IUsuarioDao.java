package mx.uniprotec.application.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import mx.uniprotec.application.entity.Usuario;

public interface IUsuarioDao extends CrudRepository<Usuario, Long>{
	
	public Usuario findByUsernameUsuario(String username);
	
	@Query("select u from Usuario u where u.usernameUsuario=?1")
	public Usuario findByUsername2(String username);

}
