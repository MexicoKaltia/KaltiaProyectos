package mx.uniprotec.application.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import mx.uniprotec.application.entity.Asignacion;

public interface IAsignacionDao extends CrudRepository<Asignacion, Long>{
	
	@Query("select u from Asignacion u where u.dateAsignacion between ?1 and ?2")
	public List<Asignacion> findTrimestre(String pasado, String futuro);
	
	

}

