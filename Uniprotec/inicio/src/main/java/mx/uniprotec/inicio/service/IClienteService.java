package mx.uniprotec.inicio.service;

import mx.uniprotec.entidad.modelo.Cliente;
import mx.uniprotec.entidad.modelo.ResultVO;

public interface IClienteService {
	
	public ResultVO altaCliente(Cliente cliente, String token);
	public ResultVO edicionCliente(Cliente cliente , String token);
	public ResultVO consultaClientes( String token);
	
	

}
