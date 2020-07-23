package mx.uniprotec.inicio.service;

import mx.uniprotec.entidad.modelo.AsignacionModelo;
import mx.uniprotec.inicio.entity.StatusVO;

public interface IMailService {
	
	public StatusVO mailServicePreCorreo(AsignacionModelo asignacion);
	

}
