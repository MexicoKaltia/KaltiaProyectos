package mx.uniprotec.inicio.service;

import mx.uniprotec.entidad.modelo.AsignacionModelo;
import mx.uniprotec.entidad.modelo.DatosEconomicosModelo;
import mx.uniprotec.entidad.modelo.ResultVO;
import mx.uniprotec.inicio.util.BaseClientRest;
import mx.uniprotec.inicio.util.ComponenteComun;

public interface IAsignacionService {
	
	public ResultVO altaAsignacion(AsignacionModelo asignacion, String token, Long idUsuario);
	public ResultVO edicionAsignacion(AsignacionModelo asignacion, String token, String status);
	public ResultVO edicionAsignacionC(AsignacionModelo asignacion);
	public ResultVO consultaAsignacion(String token);
	public ResultVO consultaAsignacionCorreo(String idAsignacion);
	public ResultVO edicionAsignacionV(AsignacionModelo asignacion, String token);
	public ResultVO edicionAsignacionVConfirma(AsignacionModelo asignacion, String token);
	public ResultVO consultaAsignacionHistorico(String accesToken);
	ResultVO consultaAsignacionCliente(String token);
	public ResultVO deleteAsignacion(String accesToken, Long idAsignacion);

	
	
	

}
