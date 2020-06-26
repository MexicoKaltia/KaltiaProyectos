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
		usuario.setUsernameUsuario(username(usuario));
		usuario.setPasswordUsuario("12345678");
		log.info(usuario.toString());

		resultVO = (ResultVO) baseClientRest.objetoPost(
				token,
				BaseClientRest.URL_CRUD_USUARIO,
				usuario);
		
		return resultVO;
	}

	private String username(UsuarioModelo usuario) {
		String[] nombres = usuario.getNameUsuario().split(" ");
		String[] apellidos = usuario.getApellidoUsuario().split(" ");
		String nombre = nombres[0].toLowerCase();
		String apellido = apellidos[0].toLowerCase();
		String a = apellido.substring(0, 1);
		apellido = a.toUpperCase() + apellido.substring(1, apellido.length());
		return nombre + apellido;
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

}
