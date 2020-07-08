package mx.uniprotec.application.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import mx.uniprotec.application.entity.Perfil;

public interface IPerfilDao extends CrudRepository<Perfil,Long>{

//	@Query("select u from Perfil u where u.nombrePerfil=?1")
	public Perfil findByNombrePerfil(String nombrePerfil);
}
