package mx.uniprotec.application.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.uniprotec.application.dao.IPreAsignacionAEDao;
import mx.uniprotec.application.dao.IPreAsignacionDao;
import mx.uniprotec.application.dao.IVendedorDEDao;
import mx.uniprotec.application.entity.ParticipanteEntity;
import mx.uniprotec.application.entity.PreAsignacion;
import mx.uniprotec.application.entity.PreAsignacionAEEntity;
import mx.uniprotec.application.entity.VendedorDatosEconomicos;

@Service
public class PreAsignacionAEServiceImpl implements IPreAsignacionAEService {

	public PreAsignacionAEServiceImpl() {
		// TODO Auto-generated constructor stub
	}
	
	
	@Autowired
	IPreAsignacionAEDao preAsignacionAEDao;
	@Autowired
	IPreAsignacionDao preAsignacionDao;
	@Autowired
	IVendedorDEDao vendedorDatosEconomicosDao;


		
	/*
	 * PRE ASIGNACION
	 */
	
	@Override
	public PreAsignacionAEEntity savePreAsignacionAE(PreAsignacionAEEntity preAsignacionAENew) {
		return preAsignacionAEDao.save(preAsignacionAENew);
	}



	

	@Override
	public List<PreAsignacionAEEntity> findAll() {
		return (List<PreAsignacionAEEntity>) preAsignacionAEDao.findAll();
	}

	@Override
	public int deleteIdpreAsignacion(Long id) {
		try {
			PreAsignacion preAsignacion = preAsignacionDao.findById(id).orElse(null);
			if(preAsignacion != null) {
				preAsignacionAEDao.deleteById(preAsignacion.getIdPreAsignacionAE());
				return 0;
			}else {
				return 98;
			}
		} catch (Exception e) {
			return 99;
		}
		
	}


	@Override
	public PreAsignacionAEEntity findByIdDatoEconomico(Long idDatosEconomicos) {
		PreAsignacionAEEntity preAsignacionAE = preAsignacionAEDao.findById(idDatosEconomicos).orElse(null);
		return preAsignacionAE;
	}



//Vendedores Datos Economicos

	@Override
	public void saveVendedores(List<VendedorDatosEconomicos> vendedores) {
		for(VendedorDatosEconomicos vDE : vendedores) {
			try {
				vendedorDatosEconomicosDao.save(vDE);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}		
	}





	@Override
	@Transactional
	public void updateVendedores(List<VendedorDatosEconomicos> vendedores, Long idPreAsignacionAE) {
		
		try {
			vendedorDatosEconomicosDao.deleteByIdDatosEconomicos(idPreAsignacionAE);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		for(VendedorDatosEconomicos vDE : vendedores) {
			try {
				vendedorDatosEconomicosDao.save(vDE);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}





	@Override
	public List<VendedorDatosEconomicos> VendedoresFindAll() {
		return (List<VendedorDatosEconomicos>) vendedorDatosEconomicosDao.findAll();
		
	}


}
