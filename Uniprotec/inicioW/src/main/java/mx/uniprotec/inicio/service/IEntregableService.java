package mx.uniprotec.inicio.service;

import mx.uniprotec.entidad.modelo.EntregableModelo;
import mx.uniprotec.entidad.modelo.ResultVO;

public interface IEntregableService {
	
	public ResultVO consultaEntregable(String token, Long idAsignacion);
//	public ResultVO createEntrgable(String token, String idAsignacion);
	public ResultVO actualizaEntrgable(String token, String idAsignacion);
	public ResultVO deleteEntrgable(String token, String idAsignacion);
	public ResultVO createEntrgable(EntregableModelo entregable, String accesToken, Long idUsuario);	

}
