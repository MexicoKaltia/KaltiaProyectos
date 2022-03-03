package mx.uniprotec.inicio.util;


import java.util.List;

import mx.uniprotec.entidad.modelo.ResultVO;
import mx.uniprotec.entidad.modelo.User;
import mx.uniprotec.entidad.modelo.UserCorreo;


public interface IBaseClientRest {
	
	public Object login(User user);
	public Object objetoPost(String token, String urlCrud, Object cliente);
	public Object objetoPut(String token, String urlCrud, Object objeto, Long idObjeto);
	public ResultVO objetoGetAll(String token, String urlCrudCliente);
	public ResultVO objetoGetId(String token, String urlCrud, Object object, String idObject);
	public List<UserCorreo> objetoGetObject(String token, String urlCrudRegiones, List<UserCorreo> usersCorreo);
	public ResultVO objetoDeleteId(String token, String urlCrud, String idObject);

}
