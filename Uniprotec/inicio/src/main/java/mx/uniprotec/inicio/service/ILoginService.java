package mx.uniprotec.inicio.service;

import java.util.HashMap;

import mx.uniprotec.entidad.modelo.User;



public interface ILoginService  {
	
	
	public HashMap<String, Object> login(User user);

}
