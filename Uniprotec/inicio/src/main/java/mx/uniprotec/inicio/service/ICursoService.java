package mx.uniprotec.inicio.service;

import mx.uniprotec.inicio.entity.Curso;
import mx.uniprotec.inicio.entity.ResultVO;

public interface ICursoService {

	public ResultVO altaCurso(Curso curso);
	public ResultVO edicionCurso(Curso curso);
	public ResultVO consultaCursos();

}
