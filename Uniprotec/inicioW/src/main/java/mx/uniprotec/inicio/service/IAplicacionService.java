package mx.uniprotec.inicio.service;

import java.util.List;

import mx.uniprotec.entidad.modelo.AsignacionModelo;
import mx.uniprotec.entidad.modelo.MensajeModelo;
import mx.uniprotec.entidad.modelo.ResultVO;
import mx.uniprotec.entidad.modelo.UserCorreo;
import mx.uniprotec.inicio.entity.StatusVO;



public interface IAplicacionService {
	
	public ResultVO consultaRegiones(String token);
	public ResultVO consultaData(ResultVO resultVO);
	public StatusVO enviaMail(AsignacionModelo asignacion);
	public List<UserCorreo> usersCorreo(Long idInstructorAsignacion, Long userCreateAsignacion, String token);
	public void enviaMail(AsignacionModelo asignacion, String token);
	public ResultVO altaMensaje(MensajeModelo mensaje, String accesToken);
	public void citaInstructor(AsignacionModelo asignacion);
	public ResultVO getNotificacion(Long idNotificacion);

}
