package mx.uniprotec.inicio.service;

import mx.uniprotec.inicio.entity.Cliente;
import mx.uniprotec.inicio.entity.ResultVO;

public interface IClienteService {
	
	public ResultVO altaCliente(Cliente cliente);
	public ResultVO edicionCliente(Cliente cliente);
	public ResultVO consultaClientes();
	
	

}
