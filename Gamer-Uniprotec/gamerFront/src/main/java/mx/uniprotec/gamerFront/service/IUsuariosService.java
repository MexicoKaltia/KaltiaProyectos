package mx.uniprotec.gamerFront.service;

import org.json.simple.JSONObject;

import mx.uniprotec.entidad.modelo.ResultVO;
import mx.uniprotec.entidad.modelo.UsuarioAdministrador;
import mx.uniprotec.entidad.modelo.UsuarioAudiencia;
import mx.uniprotec.entidad.modelo.UsuarioInstructor;

public interface IUsuariosService {

	public JSONObject dataUsuarios(String tokenCU);
	public ResultVO getDataUsuarios(String token);
	public ResultVO altaUsuarioAudiencia(UsuarioAudiencia userA, String accesToken);
	public ResultVO altaUsuarioInstructor(UsuarioInstructor userI, String accesToken);
	public ResultVO actualizaAudiencia(UsuarioAudiencia userA, String accesToken);
	public ResultVO actualizaInstructor(UsuarioInstructor userI, String accesToken);
	public ResultVO altaUsuarioAdministrador(UsuarioAdministrador userA, String accesToken);
	public JSONObject dataInstructor(String token, String tokenCU);
	public JSONObject getCursosControl(String tokenCU);

}
