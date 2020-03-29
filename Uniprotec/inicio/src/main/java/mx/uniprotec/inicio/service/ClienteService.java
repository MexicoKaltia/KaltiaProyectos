package mx.uniprotec.inicio.service;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.uniprotec.inicio.controller.ControllerCrud;
import mx.uniprotec.inicio.entity.Cliente;
import mx.uniprotec.inicio.entity.LoginSingle;
import mx.uniprotec.inicio.entity.ResultVO;
import mx.uniprotec.inicio.util.BaseClientRest;


@Service
public class ClienteService implements IClienteService {
	
	private static Logger log = LoggerFactory.getLogger(ClienteService.class);
	
	@Autowired
	Cliente cliente;
	@Autowired
	ResultVO resultVO;
	@Autowired
	BaseClientRest baseClientRest;


	@Override
	public ResultVO altaCliente(Cliente clienteC) {
//		log.info(cliente.toString());
		log.info(LoginSingle.getToken());
		JSONObject cliente = new JSONObject();
		JSONObject region = new JSONObject();
		
		region.put("id", 1);
		region.put("name", "prueba1region");
		
		cliente.put("id", null);
		cliente.put("name", "prueba1");
		cliente.put("apellido", "prueba1");
		cliente.put("email", "prueba1@prueba.com");
		cliente.put("createAt", "2020-03-28");
		cliente.put("region", region);
		log.info(cliente.toJSONString());
		
		resultVO = (ResultVO) baseClientRest.altaObjeto(LoginSingle.getToken(),
				BaseClientRest.POST,
				BaseClientRest.URL_CRUD_CLIENTE,
				cliente);
		
		return resultVO;
	}


	@Override
	public ResultVO edicionCliente(Cliente cliente) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultVO consultaClientes() {
		// TODO Auto-generated method stub
		return null;
	}

}
