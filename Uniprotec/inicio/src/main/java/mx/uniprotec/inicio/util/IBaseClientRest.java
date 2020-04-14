package mx.uniprotec.inicio.util;


import mx.uniprotec.entidad.modelo.ResultVO;
import mx.uniprotec.entidad.modelo.User;


public interface IBaseClientRest {
	
	public Object login(User user);
	public Object objetoPost(String token, String urlCrudCliente, Object cliente);
	public ResultVO objetoGetAll(String token, String urlCrudCliente);

}
