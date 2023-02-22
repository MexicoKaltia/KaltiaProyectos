package mx.uniprotec.application.service;

import java.util.List;

import javax.validation.Valid;

import mx.uniprotec.application.entity.DatosEconomicosEntity;
import mx.uniprotec.application.entity.VendedorDatosEconomicos;
import mx.uniprotec.entidad.modelo.DatosEconomicosModelo;

public interface IPreAsignacionAEService {
	

	public DatosEconomicosEntity savePreAsignacionAE(DatosEconomicosEntity datosEconomicosEntity);

	public List<DatosEconomicosEntity> findAll();

	public int deleteIdpreAsignacion(Long id);

	public DatosEconomicosEntity findByIdDatoEconomico(Long idDatosEconomicos);
	
	//VENDEDORES DE

	public void saveVendedores(List<VendedorDatosEconomicos> vendedores);

	public void updateVendedores(List<VendedorDatosEconomicos> vendedores, Long idPreAsignacionAE);

	public List<VendedorDatosEconomicos> VendedoresFindAll();

	public int deleteVendedoresIdDatosEconomicos(Long idDatosEconomicos);

	public int deleteDatosEconomicos(Long idDatosEconomicos);

	public List<VendedorDatosEconomicos> findVendedoresIdDatosEconomicos(Long idDatosEconomicos);

	public void updateAsignacionFactura(@Valid DatosEconomicosModelo datosEconomicos);


}
