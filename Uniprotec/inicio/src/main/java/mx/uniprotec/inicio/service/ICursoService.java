package mx.uniprotec.inicio.service;

import mx.uniprotec.entidad.modelo.CursoModelo;
import mx.uniprotec.entidad.modelo.InstructorModelo;
import mx.uniprotec.entidad.modelo.ResultVO;

public interface ICursoService {

	public ResultVO altaCurso(CursoModelo curso, String token);
	public ResultVO edicionCurso(CursoModelo curso, String token);
	public ResultVO consultaCursos( String token);
	

}
