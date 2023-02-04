package mx.uniprotec.application.service;

import java.util.List;

import mx.uniprotec.application.entity.Asignacion;
import mx.uniprotec.application.entity.DatosEconomicosEntity;
import mx.uniprotec.application.entity.PreAsignacion;
import mx.uniprotec.application.entity.PreAsignacionAEEntity;
import mx.uniprotec.application.entity.VendedorDatosEconomicos;

public interface IPreAsignacionAEService {
	

	public DatosEconomicosEntity savePreAsignacionAE(DatosEconomicosEntity datosEconomicosEntity);

	public List<DatosEconomicosEntity> findAll();

	public int deleteIdpreAsignacion(Long id);

	public DatosEconomicosEntity findByIdDatoEconomico(Long idDatosEconomicos);

	public void saveVendedores(List<VendedorDatosEconomicos> vendedores);

	public void updateVendedores(List<VendedorDatosEconomicos> vendedores, Long idPreAsignacionAE);

	public List<VendedorDatosEconomicos> VendedoresFindAll();
	
}
