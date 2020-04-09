package mx.uniprotec.inicio.service;

import mx.uniprotec.entidad.modelo.CursoModelo;
import mx.uniprotec.entidad.modelo.ResultVO;

public interface ICursoService {

	public ResultVO altaCurso(CursoModelo curso);
	public ResultVO edicionCurso(CursoModelo curso);
	public ResultVO consultaCursos();

}
