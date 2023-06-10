package mx.uniprotec.inicio.service;

import mx.uniprotec.entidad.modelo.AsignacionModelo;
import mx.uniprotec.entidad.modelo.ReporteSemanalModelo;
import mx.uniprotec.inicio.entity.StatusVO;

public interface IMailService {
	
	public StatusVO mailServicePreCorreo(AsignacionModelo asignacion, String token);

	public void mailServicePreCorreoSustitucion(AsignacionModelo asignacion, String token, Long idInstructor);

	public Boolean enviaMailReporteSemanal(ReporteSemanalModelo reporteSemanalItem, String accesToken);

	

}
