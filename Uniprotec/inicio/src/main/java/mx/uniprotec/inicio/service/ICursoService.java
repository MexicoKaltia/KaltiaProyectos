package mx.uniprotec.inicio.service;

import mx.uniprotec.inicio.entity.Curso;
import mx.uniprotec.inicio.entity.ResultVO;

public interface ICursoService {

	public ResultVO altaCurso(Curso cliente);
	public ResultVO edicionCurso(Curso cliente);
	public ResultVO consultaCursos();

}
