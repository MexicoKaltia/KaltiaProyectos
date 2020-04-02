package mx.uniprotec.inicio.util;

import org.json.simple.JSONObject;

import mx.uniprotec.inicio.entity.Cliente;
import mx.uniprotec.inicio.entity.User;

public interface IBaseClientRest {
	
	public Object login(User user);
	public Object objetoPost(String token, String urlCrudCliente, Object cliente);

}
