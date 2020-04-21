package mx.uniprotec.inicio.service;

import mx.uniprotec.entidad.modelo.ResultVO;
import mx.uniprotec.entidad.modelo.UsuarioModelo;

public interface IUsuarioService {

	public ResultVO altaUsuario(UsuarioModelo cliente);
	public ResultVO edicionUsuario(UsuarioModelo cliente);
	public ResultVO consultaUsuarios();

}
