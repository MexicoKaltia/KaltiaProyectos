package mx.uniprotec.application.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import mx.uniprotec.application.entity.Notificacion;

public interface INotificacionDao extends CrudRepository<Notificacion, Long>{

	@Query("select u from Notificacion u where u.idInstructorNotificacion =?1 and statusNotificacion = 'nueva'")
	public List<Notificacion> findByIdInstructorNotificacion(Long idInstructor);
	
	@Query("select u from Notificacion u where u.idAsignacionNotificacion =?1 and statusNotificacion = 'nueva'")
	public Notificacion findByIdAsignacionNotificacion(Long idAsignacion);
}
