package mx.uniprotec.inicio.service;

import java.time.LocalDateTime;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.uniprotec.entidad.modelo.Cliente;
import mx.uniprotec.entidad.modelo.LoginSingle;
import mx.uniprotec.entidad.modelo.MonitorEntidades;
import mx.uniprotec.entidad.modelo.Region;
import mx.uniprotec.entidad.modelo.ResultVO;
import mx.uniprotec.inicio.util.BaseClientRest;
import mx.uniprotec.inicio.util.ComponenteComun;


@Service
public class ClienteService implements IClienteService {
	
	private static Logger log = LoggerFactory.getLogger(ClienteService.class);
	
//	@Autowired
	Cliente cliente  = new Cliente();
//	@Autowired
	ResultVO resultVO = new ResultVO();
	@Autowired
	BaseClientRest baseClientRest ;
//	@Autowired
	MonitorEntidades me = new MonitorEntidades();


	@Override
	public ResultVO altaCliente(Cliente cliente, String token) {

//		log.info(cliente.toString());
		
		Region region = new Region(cliente.getIdRegionCliente());
		cliente.setRegionCliente(region);
		
		me = ComponenteComun.monitorCampos();
		cliente.setCreateAtCliente(me.getNowEntidad());
		cliente.setUserCreateCliente(me.getIdUsuarioEntidad());
		cliente.setStatusCliente(me.getStatusEntidad());
//		log.info(cliente.toString());
		
		resultVO = (ResultVO) baseClientRest.objetoPost(
				token,
				BaseClientRest.URL_CRUD_CLIENTE,
				cliente);
		
		return resultVO;
	}


	@Override
	public ResultVO edicionCliente(Cliente cliente , String token) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultVO consultaClientes(String token) {
		
		me = ComponenteComun.monitorCampos();
		cliente.setCreateAtCliente(me.getNowEntidad());
		cliente.setUserCreateCliente(me.getIdUsuarioEntidad());
		cliente.setStatusCliente(me.getStatusEntidad());
		
		resultVO = (ResultVO) baseClientRest.objetoGetAll(token, BaseClientRest.URL_CRUD_CLIENTES);
		
		if(resultVO.getCodigo() == 202) {
			JSONObject jsonGeneral = resultVO.getJsonResponse();
//			JSONArray jsonClientes = (JSONArray ) jsonGeneral.get("clientes");
			
//			resultVO.setJsonResponseArray(jsonClientes);
			return resultVO;
		}else {
			return resultVO;
		}
	}
	
	

}
