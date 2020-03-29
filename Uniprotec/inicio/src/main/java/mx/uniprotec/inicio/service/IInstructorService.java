package mx.uniprotec.inicio.service;

import mx.uniprotec.inicio.entity.Instructor;
import mx.uniprotec.inicio.entity.ResultVO;

public interface IInstructorService {

	public ResultVO altaInstructor(Instructor cliente);
	public ResultVO edicionInstructor(Instructor cliente);
	public ResultVO consultaInstructores();

}


