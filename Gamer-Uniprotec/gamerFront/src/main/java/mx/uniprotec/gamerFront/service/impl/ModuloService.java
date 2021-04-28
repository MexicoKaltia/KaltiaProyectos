package mx.uniprotec.gamerFront.service.impl;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.uniprotec.entidad.modelo.ModuloDidactico;
import mx.uniprotec.entidad.modelo.ResultVO;
import mx.uniprotec.gamerFront.service.IModuloService;
import mx.uniprotec.gamerFront.util.BaseClientRest;

@Service
public class ModuloService implements IModuloService{
	
	@Autowired
	BaseClientRest baseClientRest;


	public ModuloService() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public ResultVO getModulos(String token) {
		ResultVO rs = new ResultVO();
		rs= (ResultVO) baseClientRest.objetoGetAll(token, BaseClientRest.URL_GET_MODULOS);
		return rs;
	}

	@Override
	public ResultVO getModulo(String token, Long idModulo) {
		ResultVO rs = new ResultVO();
		rs= (ResultVO) baseClientRest.objetoGetId(token, BaseClientRest.URL_GET_MODULO, null, idModulo.toString());
		return rs;
	}
	
	@Override
	public ResultVO altaModulo(String token, ModuloDidactico moduloDidactico) {
		ResultVO rs = new ResultVO();
		
		moduloDidactico.setCreateAt(LocalDateTime.now().toString());
		moduloDidactico.setCreateUser("nombreUsuario");
		moduloDidactico.setStatus("create");
		rs= (ResultVO) baseClientRest.objetoPost(token, BaseClientRest.URL_ALTA_MODULO,  moduloDidactico);
		return rs;
	}

	@Override
	public ResultVO actualizaModulo(String token, ModuloDidactico moduloDidactico) {
		ResultVO rs = new ResultVO();
		rs= (ResultVO) baseClientRest.objetoPut(token, BaseClientRest.URL_ACTUALIZA_MODULO,  moduloDidactico, Long.valueOf(moduloDidactico.getIdModuloDidactico()));
		return rs;
	}

	@Override
	public ResultVO eliminarModulo(String token, ModuloDidactico moduloDidactico) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
