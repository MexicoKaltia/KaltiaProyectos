package mx.uniprotec.gamerFront.service;

import java.util.List;

import mx.uniprotec.entidad.modelo.ModuloDidactico;
import mx.uniprotec.entidad.modelo.ResultVO;

public interface IModuloService {

	public ResultVO getModulos(String token);
	public ResultVO getModulo(String token, Long idModulo);
	public ResultVO altaModulo(String token, ModuloDidactico moduloDidactico);
	public ResultVO actualizaModulo(String token, ModuloDidactico moduloDidactico);
	public ResultVO eliminarModulo(String token, ModuloDidactico moduloDidactico);
	
}
