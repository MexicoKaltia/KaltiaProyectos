package mx.uniprotec.inicio.service;

import mx.uniprotec.entidad.modelo.DatosEconomicosModelo;
import mx.uniprotec.entidad.modelo.ResultVO;

public interface IDatosEconomicosService {
	
	public ResultVO altaDatosEconomicos(DatosEconomicosModelo datosEconomicosItem, String accesToken);

	public ResultVO deleteDatosEconomicos(String idAsignacion, String accesToken);

	public ResultVO actualizaDatosEconomicos(DatosEconomicosModelo datosEconomicos, String accesToken);

	public ResultVO consultaDatosEconomicos(String token);

	public ResultVO consultaVendoresDatosEconomicos(String token);

	ResultVO consultaVendedoresAnalisis(String token);
	

		
	

}
