package mx.uniprotec.inicio.service;

import java.util.ArrayList;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.uniprotec.entidad.modelo.MonitorEntidades;
import mx.uniprotec.entidad.modelo.ResultVO;
import mx.uniprotec.entidad.modelo.UsuarioModelo;
import mx.uniprotec.inicio.util.BaseClientRest;
import mx.uniprotec.inicio.util.ComponenteComun;

@Service
public class UsuarioService implements IUsuarioService {
	
	private static Logger log = LoggerFactory.getLogger(UsuarioService.class);
	

	UsuarioModelo usuario = new UsuarioModelo();
	ResultVO resultVO = new  ResultVO();
	@Autowired
	BaseClientRest baseClientRest;
	MonitorEntidades me = new  MonitorEntidades();

	@Override
	public ResultVO altaUsuario(UsuarioModelo usuario, String token) {
			
		me = ComponenteComun.monitorCampos();
		usuario.setCreateAtUsuario(me.getNowEntidad());
		usuario.setUserCreateUsuario(me.getIdUsuarioEntidad());
		usuario.setStatusUsuario(me.getStatusEntidad());
		if(usuario.getUsernameUsuario().equals(null)) {
			usuario.setUsernameUsuario(username(usuario.getNombreUsuario()));
		}else {
			usuario.setUsernameUsuario(usernameMail(usuario.getUsernameUsuario()));
		}
		usuario.setPasswordUsuario(setPassword());
		log.info(usuario.toString());

		resultVO = (ResultVO) baseClientRest.objetoPost(
				token,
				BaseClientRest.URL_CRUD_USUARIO,
				usuario);
		
		return resultVO;
	}

	
	@Override
	public ResultVO edicionUsuario(UsuarioModelo usuario, String token) {
		me = ComponenteComun.monitorCampos();
		
		usuario.setCreateAtUsuario(me.getNowEntidad());
		usuario.setUserCreateUsuario(me.getIdUsuarioEntidad());
		usuario.setStatusUsuario("Actualizado");
		
		log.info(usuario.toString());
		
		resultVO = (ResultVO) baseClientRest.objetoPut(
				token,
				BaseClientRest.URL_CRUD_USUARIO,
				usuario,
				usuario.getIdUsuario());
				
		return resultVO;
	}

	@Override
	public ResultVO consultaUsuarios( String token) {
		ResultVO rs = (ResultVO) baseClientRest.objetoGetAll(token, BaseClientRest.URL_CRUD_USUARIOS);
		
		if(rs.getCodigo() == 202) {
			JSONObject jsonGeneral = rs.getJsonResponse();
			JSONObject jsonClientes = new JSONObject();
			jsonClientes.put("usuarios", jsonGeneral.get("usuarios"));
			
			rs.setJsonResponseObject(jsonClientes);
			return rs;
		}else {
			return rs;
		}
	}

	private String setPassword() {
		return "12345678";
	}

	private String usernameMail(String usernameUsuario) {
		String [] userArray = usernameUsuario.split("@");
		return userArray[0];
	}

	private String username(String usuario) {
		String[] nombres = usuario.split(" ");
		String nombre = nombres[0].toLowerCase();
		String apellido = nombres[1].toLowerCase();
		String a = apellido.substring(0, 1);
		apellido = a.toUpperCase() + apellido.substring(1, apellido.length());
		return nombre + apellido;
	}

}
