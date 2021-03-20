package mx.uniprotec.gamerBack.service;

import java.util.List;

import javax.validation.Valid;

import mx.uniprotec.entidad.modelo.UsuarioAudiencia;
import mx.uniprotec.gamerBack.entity.Usuario;


public interface IUsuarioService {

	public Usuario findByUsername(String username);

	public List<Usuario> findAll();

	public Usuario findById(Long id);

	public Usuario save(@Valid UsuarioAudiencia usuario);

	public void delete(Long id);
}
