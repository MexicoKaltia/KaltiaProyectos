package mx.uniprotec.gamerBack.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import mx.uniprotec.gamerBack.entity.Usuario;
import mx.uniprotec.gamerBack.entity.UsuarioAudienciaEntity;

public interface IUsuarioAudienciaDao extends CrudRepository<UsuarioAudienciaEntity, Long>{

	UsuarioAudienciaEntity findByUsuarioAudienciaIdAsignacion(String userName);
	
	
}
