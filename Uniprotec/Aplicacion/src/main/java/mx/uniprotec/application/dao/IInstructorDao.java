package mx.uniprotec.application.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import mx.uniprotec.application.entity.Instructor;

public interface IInstructorDao extends JpaRepository<Instructor, Long>{

}
