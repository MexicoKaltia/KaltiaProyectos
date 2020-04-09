package mx.uniprotec.inicio.service;

import mx.uniprotec.entidad.modelo.ResultVO;
import mx.uniprotec.entidad.modelo.Usuario;

public interface IUsuarioService {

	public ResultVO altaUsuario(Usuario cliente);
	public ResultVO edicionUsuario(Usuario cliente);
	public ResultVO consultaUsuarios();

}
