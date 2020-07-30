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
			
		
	
		if(usuario.getUsernameUsuario() == null) {
			usuario.setUsernameUsuario(letraInicial(usuario.getPerfilUsuario())+username(usuario.getNombreUsuario()));
			usuario.setEmailUsuario(usuario.getEmailUsuario().concat("@uniprotec.net"));
		}else {
			usuario.setUsernameUsuario(letraInicial(usuario.getPerfilUsuario())+usernameMail(usuario.getUsernameUsuario()));
			
		}

		usuario.setPasswordUsuario(setPassword());
		
		me = ComponenteComun.monitorCampos();
		usuario.setCreateAtUsuario(me.getNowEntidad());
		usuario.setUserCreateUsuario(me.getIdUsuarioEntidad());
		usuario.setStatusUsuario(me.getStatusEntidad());
		
		
//		log.info(usuario.toString());

		resultVO = (ResultVO) baseClientRest.objetoPost(
				token,
				BaseClientRest.URL_CRUD_USUARIO,
				usuario);
		
		return resultVO;
	}

	
	


	@Override
	public ResultVO edicionUsuario(UsuarioModelo usuario, String token) {
		me = ComponenteComun.monitorCampos();
		
//		usuario.setUsernameUsuario(letraInicial(usuario.getPerfilUsuario())+usuario.getUsernameUsuario().substring(1,usuario.getUsernameUsuario().length()));
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
		return "12345";
	}

	private String usernameMail(String usernameUsuario) {
		String [] userArray = usernameUsuario.split("@");
		return userArray[0];
	}

	private String username(String usuario) {
		String[] nombres = usuario.split(" ");
		String nombre = nombres[0].toLowerCase();
		String apellido = nombres[1].toLowerCase();
//		String a = apellido.substring(0, 1);
//		apellido = a.toLowerCase() + apellido.substring(1, apellido.length());
		return nombre +"."+ apellido;
	}
	
	private String letraInicial(String letraInicial) {
			
			switch (letraInicial) {
			case "Vendedor": letraInicial ="v.";
			break;
			case "Instructor": letraInicial ="i.";
			break;
			case "Operacion": letraInicial ="o.";
			break;
			case "Administracion": letraInicial ="a.";
			break;
			case "Direccion": letraInicial ="d.";
			break;
			}
			return letraInicial;
		}





	@Override
	public ResultVO consultaUsuario(String token, String l) {
		ResultVO rs = (ResultVO) baseClientRest.objetoGetId(token, BaseClientRest.URL_CRUD_USUARIO, null, l.toString());
		
		if(rs.getCodigo() == 202) {
			JSONObject jsonGeneral = rs.getJsonResponse();
			JSONObject jsonClientes = new JSONObject();
			jsonClientes.put("usuario", jsonGeneral.get("usuario"));
			
			rs.setJsonResponseObject(jsonClientes);
			return rs;
		}else {
			return rs;
		}
	}

}
