package mx.uniprotec.gamerBack.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import mx.uniprotec.gamerBack.entity.Usuario;
import mx.uniprotec.gamerBack.entity.UsuarioInstructorEntity;

public interface IUsuarioInstructorDao extends CrudRepository<UsuarioInstructorEntity, Long>{

	public UsuarioInstructorEntity findByUsuarioInstructorUserName(String username);
	
}
