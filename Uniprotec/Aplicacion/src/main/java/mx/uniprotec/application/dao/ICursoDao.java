package mx.uniprotec.application.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import mx.uniprotec.application.entity.Curso;

public interface ICursoDao extends JpaRepository<Curso,Long>{

}
