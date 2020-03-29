package mx.uniprotec.inicio.service;

import mx.uniprotec.inicio.entity.Usuario;
import mx.uniprotec.inicio.entity.ResultVO;

public interface IUsuarioService {

	public ResultVO altaUsuario(Usuario cliente);
	public ResultVO edicionUsuario(Usuario cliente);
	public ResultVO consultaUsuarios();

}
