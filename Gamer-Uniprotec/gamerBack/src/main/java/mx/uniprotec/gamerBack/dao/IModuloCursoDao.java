package mx.uniprotec.gamerBack.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import mx.uniprotec.gamerBack.entity.ModuloCurso;

public interface IModuloCursoDao extends CrudRepository<ModuloCurso, Long>{
	
	public ModuloCurso findByIdModuloDidacticoAndIdCurso(Long idModulo, Long idCurso);
	
//	@Query("select u from Usuario u where u.username=?1")
//	public Usuario findByUsername2(String username);
	
//	@Query("select u from Usuario u where u.id_usuario_instructor=?1")
//	public Usuario findByIdUsuarioInstructor(Long idUsuario);
//	@Query("select u from Usuario u where u.id_usuario_audiencia=?1")
//	public Usuario findByIdUsuarioAudiencia(Long idUsuario);

}
