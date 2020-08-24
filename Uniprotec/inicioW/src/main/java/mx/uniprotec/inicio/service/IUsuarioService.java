package mx.uniprotec.inicio.service;

import mx.uniprotec.entidad.modelo.ResultVO;
import mx.uniprotec.entidad.modelo.UsuarioModelo;

public interface IUsuarioService {

	public ResultVO altaUsuario(UsuarioModelo usuario, String token);
	public ResultVO edicionUsuario(UsuarioModelo usuario, String token);
	public ResultVO consultaUsuarios(String token);
	public ResultVO consultaUsuario(String string, String l);

}
