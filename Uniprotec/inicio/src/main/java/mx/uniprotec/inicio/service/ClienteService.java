package mx.uniprotec.inicio.service;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.uniprotec.inicio.entity.Cliente;
import mx.uniprotec.inicio.entity.LoginSingle;
import mx.uniprotec.inicio.entity.MonitorEntidades;
import mx.uniprotec.inicio.entity.Region;
import mx.uniprotec.inicio.entity.ResultVO;
import mx.uniprotec.inicio.util.BaseClientRest;
import mx.uniprotec.inicio.util.ComponenteComun;


@Service
public class ClienteService implements IClienteService {
	
	private static Logger log = LoggerFactory.getLogger(ClienteService.class);
	
	@Autowired
	Cliente cliente;
//	@Autowired
//	Region region;
	@Autowired
	ResultVO resultVO;
	@Autowired
	BaseClientRest baseClientRest;
	@Autowired
	MonitorEntidades me;


	@Override
	public ResultVO altaCliente(Cliente cliente) {
		log.info(cliente.toString());
		log.info(LoginSingle.getToken());

		LocalDateTime now = LocalDateTime.now();
		Region region = new Region(Long.parseLong(cliente.getRegionIdCliente().toString()));
		

		
		me = ComponenteComun.monitorCampos();
		cliente.setCreateAtCliente(me.getNowEntidad());
		cliente.setUserCreateCliente(me.getIdUsuarioEntidad());
		cliente.setStatusCliente(me.getStatusEntidad());
		cliente.setCreateAtCliente(now);
		cliente.setRegionCliente(region);
		log.info(cliente.toString());
		
		resultVO = (ResultVO) baseClientRest.objetoPost(
				LoginSingle.getToken(),
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
