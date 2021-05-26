package mx.uniprotec.gamerFront.service.impl;

import java.time.LocalDateTime;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.uniprotec.entidad.modelo.ModuloDidactico;
import mx.uniprotec.entidad.modelo.ResultVO;
import mx.uniprotec.entidad.modelo.ValoresJsonVO;
import mx.uniprotec.gamerFront.service.IModuloService;
import mx.uniprotec.gamerFront.util.BaseClientRest;

@Service
public class ModuloService implements IModuloService{
	
	@Autowired
	BaseClientRest baseClientRest;
	
	protected final Log log = LogFactory.getLog(getClass());



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

	@Override
	public ResultVO edicionServiceUpdate(String token, ValoresJsonVO valoresJsonVO) {
		ResultVO rs = new ResultVO();
		log.info(valoresJsonVO.toString());
		rs= (ResultVO) baseClientRest.objetoPut(token, BaseClientRest.URL_UPDATE_MODULO,  valoresJsonVO, Long.valueOf(valoresJsonVO.getModulo().toString()));
		return rs;
	}

	

}
