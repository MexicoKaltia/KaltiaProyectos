package mx.uniprotec.application.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import mx.uniprotec.application.entity.Asignacion;
import mx.uniprotec.application.entity.PreAsignacion;

public interface IPreAsignacionDao extends JpaRepository<PreAsignacion, Long>{

}

