package mx.uniprotec.application.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import mx.uniprotec.application.entity.Asignacion;
import mx.uniprotec.application.entity.ZonaBase;

public interface IZonaBaseDao extends JpaRepository<ZonaBase, Long>{

	Optional<ZonaBase> findByIdZonabase(long l);

}

