package mx.uniprotec.application.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import mx.uniprotec.application.entity.Asignacion;
import mx.uniprotec.application.entity.PreAsignacion;
import mx.uniprotec.application.entity.PreAsignacionAEEntity;

public interface IPreAsignacionAEDao extends JpaRepository<PreAsignacionAEEntity, Long>{

}

