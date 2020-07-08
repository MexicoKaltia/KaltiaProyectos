package mx.uniprotec.application.dao;

import org.springframework.data.repository.CrudRepository;

import mx.uniprotec.application.entity.Instructor;

public interface IInstructorDao extends CrudRepository<Instructor, Long>{

	public	Instructor findByUsuarioInstructorIdUsuario(Long idUsuario);

}
