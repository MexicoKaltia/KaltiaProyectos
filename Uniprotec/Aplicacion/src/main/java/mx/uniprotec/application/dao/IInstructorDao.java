package mx.uniprotec.application.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import mx.uniprotec.application.entity.Instructor;

public interface IInstructorDao extends CrudRepository<Instructor, Long>{

	public	Instructor findByUsuarioInstructorIdUsuario(Long idUsuario);

	@Query("select u from Instructor u where u.statusInstructor not in('Baja')")
	public List<Instructor> findAllActivo();

	public List<Instructor> findByIdOperacion(Long idOperacion);

//	@Query("select u from Instructor u where u.idInstructor =?1")
//	public List<Instructor> findAllActivo(Long idInstructor);
}
