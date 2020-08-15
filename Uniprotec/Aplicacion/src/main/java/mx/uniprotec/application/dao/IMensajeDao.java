package mx.uniprotec.application.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import mx.uniprotec.application.entity.Mensaje;

public interface IMensajeDao extends CrudRepository<Mensaje, Long>{

	@Query("select max(u.idMensaje) from Mensaje u ")
	public Long findMaxId();

}
