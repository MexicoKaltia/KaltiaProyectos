package mx.uniprotec.application.service;

import java.util.List;

import mx.uniprotec.application.entity.Asignacion;
import mx.uniprotec.application.entity.PreAsignacion;
import mx.uniprotec.application.entity.PreAsignacionAEEntity;
import mx.uniprotec.application.entity.VendedorDatosEconomicos;

public interface IPreAsignacionAEService {
	

	public PreAsignacionAEEntity savePreAsignacionAE(PreAsignacionAEEntity preAsignacionAENew);

	public List<PreAsignacionAEEntity> findAll();

	public int deleteIdpreAsignacion(Long id);

	public PreAsignacionAEEntity findByIdDatoEconomico(Long idDatosEconomicos);

	public void saveVendedores(List<VendedorDatosEconomicos> vendedores);

	public void updateVendedores(List<VendedorDatosEconomicos> vendedores, Long idPreAsignacionAE);

	public List<VendedorDatosEconomicos> VendedoresFindAll();
	
}
