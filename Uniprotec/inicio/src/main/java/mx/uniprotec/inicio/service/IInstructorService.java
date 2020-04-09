package mx.uniprotec.inicio.service;

import mx.uniprotec.entidad.modelo.Instructor;
import mx.uniprotec.entidad.modelo.ResultVO;

public interface IInstructorService {

	public ResultVO altaInstructor(Instructor cliente);
	public ResultVO edicionInstructor(Instructor cliente);
	public ResultVO consultaInstructores();

}


