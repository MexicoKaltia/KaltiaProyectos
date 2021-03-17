package mx.uniprotec.gamerFront.service;

import mx.uniprotec.entidad.modelo.ResultVO;
import mx.uniprotec.gamerFront.vo.UserForm;

public interface ILoginService {

	ResultVO login(UserForm user);

}
