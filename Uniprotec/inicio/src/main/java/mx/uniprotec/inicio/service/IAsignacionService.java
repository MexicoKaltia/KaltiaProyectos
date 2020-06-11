package mx.uniprotec.inicio.service;

import mx.uniprotec.entidad.modelo.AsignacionModelo;
import mx.uniprotec.entidad.modelo.ResultVO;

public interface IAsignacionService {
	
	public ResultVO altaAsignacion(AsignacionModelo asignacion, String token);
	public ResultVO edicionAsignacion(AsignacionModelo asignacion, String token);
	public ResultVO consultaAsignacion( String token);
	
	

}
