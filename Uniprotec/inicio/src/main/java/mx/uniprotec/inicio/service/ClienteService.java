package mx.uniprotec.inicio.service;

import java.time.LocalDateTime;

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
	public ResultVO altaCliente(Cliente cliente) {
		log.info(cliente.toString());
//		log.info(LoginSingle.getToken());

		LocalDateTime now = LocalDateTime.now();
		Region region = new Region(cliente.getIdRegionCliente());
		cliente.setRegionCliente(region);
		me = ComponenteComun.monitorCampos();
		cliente.setCreateAtCliente(me.getNowEntidad());
		cliente.setUserCreateCliente(me.getIdUsuarioEntidad());
		cliente.setStatusCliente(me.getStatusEntidad());
		cliente.setCreateAtCliente(now);

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
