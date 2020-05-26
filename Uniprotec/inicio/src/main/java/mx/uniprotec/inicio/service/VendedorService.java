package mx.uniprotec.inicio.service;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.uniprotec.entidad.modelo.ClienteModelo;
import mx.uniprotec.entidad.modelo.VendedorModelo;
import mx.uniprotec.entidad.modelo.MonitorEntidades;
import mx.uniprotec.entidad.modelo.ResultVO;
import mx.uniprotec.inicio.util.BaseClientRest;
import mx.uniprotec.inicio.util.ComponenteComun;

@Service
public class VendedorService implements IVendedorService {
	
	private static Logger log = LoggerFactory.getLogger(VendedorService.class);
	
//	@Autowired
	VendedorModelo vendedor = new VendedorModelo();
//	@Autowired
	ResultVO resultVO = new  ResultVO();
	@Autowired
	BaseClientRest baseClientRest;
//	@Autowired
	MonitorEntidades me = new  MonitorEntidades();

	@Override
	public ResultVO altaVendedor(VendedorModelo vendedor, String token) {
		log.info(vendedor.toString());
		
//		List<ClienteModelo> listCliente =  new ArrayList<ClienteModelo>();
//		for(Long idCliente : vendedor.getListClienteVendedor()) {
//			listCliente.add(new ClienteModelo(idCliente));
//		}
//		vendedor.setListClienteVendedor(listClienteVendedor);
		
		me = ComponenteComun.monitorCampos();
		vendedor.setCreateAtVendedor(me.getNowEntidad());
		vendedor.setUserCreateVendedor(me.getIdUsuarioEntidad());
		vendedor.setStatusVendedor(me.getStatusEntidad());
		vendedor.setEmailVendedor(vendedor.getEmailGmailVendedor().concat("@gmail.com"));
		
		log.info(vendedor.toString());
		
		resultVO = (ResultVO) baseClientRest.objetoPost(
				token,
				BaseClientRest.URL_CRUD_VENDEDOR,
				vendedor);
		
		return resultVO;
	}

	@Override
	public ResultVO edicionVendedor(VendedorModelo vendedor, String token) {
		me = ComponenteComun.monitorCampos();
//		String email = vendedor.getEmailVendedor().concat("--").concat(vendedor.getEmailVendedorGmail()) ;
//		vendedor.setEmailVendedor(email);
		
		vendedor.setCreateAtVendedor(me.getNowEntidad());
		vendedor.setUserCreateVendedor(me.getIdUsuarioEntidad());
		vendedor.setStatusVendedor(me.getStatusEntidad());
		
		log.info(vendedor.toString());
		
		resultVO = (ResultVO) baseClientRest.objetoPut(
				token,
				BaseClientRest.URL_CRUD_VENDEDOR,
				vendedor,
				vendedor.getIdVendedor());
				
		return resultVO;
	}

	@Override
	public ResultVO consultaVendedores( String token) {
		ResultVO rs = (ResultVO) baseClientRest.objetoGetAll(token, BaseClientRest.URL_CRUD_VENDEDORES);
		
		if(rs.getCodigo() == 202) {
			JSONObject jsonGeneral = rs.getJsonResponse();
			JSONObject jsonClientes = new JSONObject();
			jsonClientes.put("vendedores", jsonGeneral.get("vendedores"));
			
			rs.setJsonResponseObject(jsonClientes);
			return rs;
		}else {
			return rs;
		}
	}

}
