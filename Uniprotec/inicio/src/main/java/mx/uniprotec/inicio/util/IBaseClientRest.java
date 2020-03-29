package mx.uniprotec.inicio.util;

import org.json.simple.JSONObject;

import mx.uniprotec.inicio.entity.Cliente;
import mx.uniprotec.inicio.entity.User;

public interface IBaseClientRest {
	
	public Object login(User user);
	public Object altaObjeto(String token, String post2, String urlCrudCliente, JSONObject cliente);

}
