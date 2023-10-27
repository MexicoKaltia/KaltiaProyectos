package mx.uniprotec.inicio.service;

import java.util.List;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.uniprotec.entidad.modelo.ClienteModelo;
import mx.uniprotec.entidad.modelo.MonitorEntidades;
import mx.uniprotec.entidad.modelo.Region;
import mx.uniprotec.entidad.modelo.ResultVO;
import mx.uniprotec.inicio.util.BaseClientRest;
import mx.uniprotec.inicio.util.ComponenteComun;


@Service
public class ClienteService implements IClienteService {
	
	private static Logger log = LoggerFactory.getLogger(ClienteService.class);
	
//	@Autowired
	ClienteModelo cliente  = new ClienteModelo();
//	@Autowired
	ResultVO resultVO = new ResultVO();
	@Autowired
	BaseClientRest baseClientRest ;
//	@Autowired
	MonitorEntidades me = new MonitorEntidades();


	@Override
	public ResultVO altaCliente(ClienteModelo cliente, String token) {

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
	public ResultVO edicionCliente(ClienteModelo cliente , String token) {
		Region region = new Region(cliente.getIdRegionCliente());
		cliente.setRegionCliente(region);
		log.info(cliente.getImagenLogoCliente());
		log.info("logoH:"+cliente.getImagenLogoClienteH());
		log.info(cliente.getArchivosCliente());
		log.info("archivosH:"+cliente.getArchivosClienteH());
		log.info("----------------------");
		
		
		if(cliente.getImagenLogoCliente().equals("")) {
			log.info("no se activo el cambio de imagen");
			cliente.setImagenLogoCliente(cliente.getImagenLogoClienteH());
		}else {
			log.info("SI  activo el cambio de imagen");
			cliente.setImagenLogoClienteH(cliente.getImagenLogoCliente());
		}
		
		if(cliente.getArchivosCliente().equals("")) {
			cliente.setArchivosCliente(cliente.getArchivosClienteH());
		}else {
			cliente.setArchivosClienteH(cliente.getArchivosCliente());
		}
		
		log.info(cliente.getImagenLogoCliente());
		log.info("logoH:"+cliente.getImagenLogoClienteH());
		log.info(cliente.getArchivosCliente());
		log.info("archivosH:"+cliente.getArchivosClienteH());
		


		me = ComponenteComun.monitorCampos();
		cliente.setCreateAtCliente(me.getNowEntidad());
		cliente.setUserCreateCliente(me.getIdUsuarioEntidad());
		cliente.setStatusCliente("Actualizado");
//		log.info(cliente.toString());
		
		resultVO = (ResultVO) baseClientRest.objetoPut(
				token,
				BaseClientRest.URL_CRUD_CLIENTE,
				cliente,
				cliente.getIdCliente());
		
		return resultVO;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ResultVO consultaClientes(String token) {
		
		ResultVO rs = (ResultVO) baseClientRest.objetoGetAll(token, BaseClientRest.URL_CRUD_CLIENTES);
		
		if(rs.getCodigo() == 202) {
			JSONObject jsonGeneral = rs.getJsonResponse();
			JSONObject jsonClientes = new JSONObject();
			jsonClientes.put("clientes", jsonGeneral.get("clientes"));
			rs.setClientes((List<ClienteModelo>) jsonGeneral.get("clientes"));
			rs.setJsonResponseObject(jsonClientes);
			return rs;
		}else {
			return rs;
		}
	}


	@Override
	public ResultVO consultaCliente(String token,Long idClienteAsignacion) {
		
		ResultVO rs = (ResultVO) baseClientRest.objetoGetId(token, BaseClientRest.URL_CRUD_CLIENTE, null, idClienteAsignacion.toString());
		
		if(rs.getCodigo() == 202) {
			JSONObject jsonGeneral = rs.getJsonResponse();
			JSONObject jsonClientes = new JSONObject();
			jsonClientes.put("clientes", jsonGeneral.get("clientes"));
			
			rs.setJsonResponseObject(jsonClientes);
			return rs;
		}else {
			return rs;
		}
	}
	
	@Override
	public ResultVO consultaClienteCorreo(String idClienteAsignacion) {
		
		ResultVO rs = (ResultVO) baseClientRest.objetoGetIdCorreo(BaseClientRest.URL_CRUD_CLIENTE, idClienteAsignacion.toString());
		
		if(rs.getCodigo() == 202) {
			JSONObject jsonGeneral = rs.getJsonResponse();
			JSONObject jsonClientes = new JSONObject();
			jsonClientes.put("clientes", jsonGeneral.get("clientes"));
			
//			rs.setJsonResponseObject(jsonClientes);
			return rs;
		}else {
			return rs;
		}
	}

}
