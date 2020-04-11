package mx.uniprotec.inicio.service;

import mx.uniprotec.entidad.modelo.ResultVO;
import mx.uniprotec.entidad.modelo.User;



public interface ILoginService  {
	
	
	public ResultVO login(User user);

}
