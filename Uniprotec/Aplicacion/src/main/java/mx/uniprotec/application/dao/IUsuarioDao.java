package mx.uniprotec.application.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import mx.uniprotec.application.entity.Usuario;

public interface IUsuarioDao extends CrudRepository<Usuario, Long>{
	
	public Usuario findByUsernameUsuario(String username);
	
	@Query("select u from Usuario u where u.usernameUsuario=?1")
	public Usuario findByUsername2(String username);

	@Query("select u from Usuario u where u.perfilUsuario in('Operacion', 'Administracion','Direccion')")
	public List<Usuario> getUsuariosCorreo();

	public List<Usuario> findByPerfilUsuario(String string);

}
