package mx.uniprotec.inicio.service;

import mx.uniprotec.entidad.modelo.ClienteModelo;
import mx.uniprotec.entidad.modelo.ResultVO;

public interface IClienteService {
	
	public ResultVO altaCliente(ClienteModelo cliente, String token);
	public ResultVO edicionCliente(ClienteModelo cliente , String token);
	public ResultVO consultaClientes( String token);
	public ResultVO consultaCliente(String token, Long idClienteAsignacion);
	
	

}
