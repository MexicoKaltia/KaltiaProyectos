package mx.uniprotec.gamerBack.service;

import mx.uniprotec.gamerBack.entity.Usuario;

public interface IUsuarioService {

	public Usuario findByUsername(String username);
}
