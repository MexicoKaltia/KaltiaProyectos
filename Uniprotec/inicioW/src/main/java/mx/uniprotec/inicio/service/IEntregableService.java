package mx.uniprotec.inicio.service;

import mx.uniprotec.entidad.modelo.EntregableModelo;
import mx.uniprotec.entidad.modelo.ResultVO;

public interface IEntregableService {
	
	public ResultVO consultaEntregable(String token, Long idAsignacion);
//	public ResultVO createEntrgable(String token, String idAsignacion);
	public ResultVO actualizaEntregable(String token, String idAsignacion);
	public ResultVO deleteEntregable(String idAsignacion, String token);
	public ResultVO createEntregable(EntregableModelo entregable, String accesToken, Long idUsuario);	

}
