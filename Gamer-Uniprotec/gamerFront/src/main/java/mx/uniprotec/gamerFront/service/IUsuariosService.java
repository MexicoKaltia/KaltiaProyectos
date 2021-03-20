package mx.uniprotec.gamerFront.service;

import org.json.simple.JSONObject;

import mx.uniprotec.entidad.modelo.ResultVO;
import mx.uniprotec.entidad.modelo.UsuarioAudiencia;

public interface IUsuariosService {

	public JSONObject dataUsuarios(String tokenCU);
	public ResultVO altaUsuarioAudiencia(UsuarioAudiencia userA, String accesToken);

}
