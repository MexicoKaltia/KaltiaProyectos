package mx.uniprotec.application.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import mx.uniprotec.application.entity.EntregableEntity;

public interface IEntregableDao extends JpaRepository<EntregableEntity, Long>{
	
//	@Query("select u from entregables u where u.idAsignacion =?1 ")
	public List<EntregableEntity> findByIdAsignacion(Long id);

}

