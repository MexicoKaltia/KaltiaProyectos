package mx.uniprotec.application.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import mx.uniprotec.application.entity.Asignacion;
import mx.uniprotec.application.entity.AsignacionHistorico;

public interface IAsignacionHistoricoDao extends JpaRepository<AsignacionHistorico, Long>{

}

