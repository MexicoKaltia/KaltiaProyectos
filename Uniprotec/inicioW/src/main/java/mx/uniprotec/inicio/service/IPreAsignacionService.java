package mx.uniprotec.inicio.service;

import mx.uniprotec.entidad.modelo.AsignacionModelo;
import mx.uniprotec.entidad.modelo.PreAsignacionAE;
import mx.uniprotec.entidad.modelo.ResultVO;

public interface IPreAsignacionService {
	
//	public ResultVO altaAsignacion(AsignacionModelo asignacion, String token, Long idUsuario);
//	public ResultVO edicionAsignacion(AsignacionModelo asignacion, String token, String status);
//	public ResultVO edicionAsignacionC(AsignacionModelo asignacion);
//	public ResultVO consultaAsignacion(String token);
//	public ResultVO consultaAsignacionCorreo(String idAsignacion);
//	public ResultVO edicionAsignacionV(AsignacionModelo asignacion, String token);
//	public ResultVO edicionAsignacionVConfirma(AsignacionModelo asignacion, String token);
//	public ResultVO consultaAsignacionHistorico(String accesToken);
//	ResultVO consultaAsignacionCliente(String token);
	ResultVO altaPreAsignacion(AsignacionModelo asignacion, String token, Long idUser);

	ResultVO altaPreAsignacionAE(PreAsignacionAE preAsignacionAE, String accesToken, Long idUsuario);

	ResultVO consultaPreAsignacion(ResultVO resultVO);

	ResultVO deletePreAsignacion(String idAsignacion, String accesToken);

	ResultVO actualizaPreAsignacion(AsignacionModelo asignacion, String accesToken);
	
	
	

}
