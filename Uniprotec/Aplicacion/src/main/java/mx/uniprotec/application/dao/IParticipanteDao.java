package mx.uniprotec.application.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import mx.uniprotec.application.entity.ParticipanteEntity;

@Repository
public interface IParticipanteDao extends JpaRepository<ParticipanteEntity, Long>{

//	List<ParticipanteEntity> findByIdEntregable(Long idEntregable);
	
//	@Query("select u from participantes u where u.idEntregable=?1 ")
	public List<ParticipanteEntity> findByIdEntregable(Long idEntregable);

	public void deleteByIdParticipante(String idParticipante);

}

