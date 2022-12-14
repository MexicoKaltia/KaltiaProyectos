package mx.uniprotec.inicio.service;

import mx.uniprotec.entidad.modelo.VendedorModelo;
import mx.uniprotec.entidad.modelo.ResultVO;

public interface IVendedorService {

	public ResultVO altaVendedor(VendedorModelo vendedor, String token);
	public ResultVO edicionVendedor(VendedorModelo vendedor, String token);
	public ResultVO consultaVendedores(String token);
	ResultVO consultaVendedoresAnalisis(String token);

}


