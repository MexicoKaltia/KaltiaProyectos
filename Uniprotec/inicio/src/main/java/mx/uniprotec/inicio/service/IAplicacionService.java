package mx.uniprotec.inicio.service;

import mx.uniprotec.entidad.modelo.AsignacionModelo;
import mx.uniprotec.entidad.modelo.ResultVO;
import mx.uniprotec.inicio.entity.StatusVO;



public interface IAplicacionService {
	
	public ResultVO consultaRegiones(String token);
	public ResultVO consultaData(ResultVO resultVO);
	public StatusVO enviaMail(AsignacionModelo asignacion);

}
