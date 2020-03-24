package mx.uniprotec.inicio.service;

import java.util.HashMap;

import mx.uniprotec.inicio.entity.User;
import mx.uniprotec.inicio.entity.Usuario;

public interface ILoginService  {
	
	
	public HashMap<String, Object> login(User user);

}
