package mx.uniprotec.gamerFront.util;


import java.util.List;

import mx.uniprotec.entidad.modelo.ResultVO;
import mx.uniprotec.entidad.modelo.User;
import mx.uniprotec.gamerFront.vo.UserForm;


public interface IBaseClientRest {
	
	
	public Object objetoPost(String token, String urlCrud, Object cliente);
	public Object objetoPut(String token, String urlCrud, Object objeto, Long idObjeto);
	public ResultVO objetoGetAll(String token, String urlCrudCliente);
	public ResultVO objetoGetId(String token, String urlCrud, Object object, String idObject);
	public ResultVO login(UserForm user);
	
}
