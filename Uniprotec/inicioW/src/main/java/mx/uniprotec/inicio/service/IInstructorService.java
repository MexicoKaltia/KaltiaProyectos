package mx.uniprotec.inicio.service;

import mx.uniprotec.entidad.modelo.InstructorModelo;
import mx.uniprotec.entidad.modelo.ResultVO;

public interface IInstructorService {

	public ResultVO altaInstructor(InstructorModelo cliente, String token);
	public ResultVO edicionInstructor(InstructorModelo cliente, String token);
	public ResultVO consultaInstructores(String token);

}


