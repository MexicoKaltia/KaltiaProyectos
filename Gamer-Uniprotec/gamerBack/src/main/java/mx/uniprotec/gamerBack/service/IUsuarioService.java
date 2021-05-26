package mx.uniprotec.gamerBack.service;

import java.util.List;

import javax.validation.Valid;

import mx.uniprotec.entidad.modelo.UsuarioAdministrador;
import mx.uniprotec.entidad.modelo.UsuarioAudiencia;
import mx.uniprotec.entidad.modelo.UsuarioInstructor;
import mx.uniprotec.gamerBack.entity.Usuario;
import mx.uniprotec.gamerBack.entity.UsuarioAdministradorEntity;
import mx.uniprotec.gamerBack.entity.UsuarioAudienciaEntity;
import mx.uniprotec.gamerBack.entity.UsuarioInstructorEntity;


public interface IUsuarioService {

	public Usuario findByUsername(String username);

	public List<Usuario> findAll();

	public Usuario findById(Long id);

	public void delete(Long id);

	public List<UsuarioAudienciaEntity> findAllAudiencia();

	public List<UsuarioInstructorEntity> findAllInstructor();

	public List<UsuarioAdministradorEntity> findAllAdministrador();
	
	public Usuario saveAudiencia(@Valid UsuarioAudiencia usuario);

	public Usuario saveInstructor(@Valid UsuarioInstructor usuario);

	public Usuario saveAdministrador(@Valid UsuarioAdministrador usuario);
	
	public Usuario actualizaUsuarioInstructor(@Valid UsuarioInstructor usuario);
	public Usuario actualizaUsuarioAudiencia(@Valid UsuarioAudiencia usuario);

	public UsuarioAudienciaEntity findByAudienciaUsername(String userName);

}
