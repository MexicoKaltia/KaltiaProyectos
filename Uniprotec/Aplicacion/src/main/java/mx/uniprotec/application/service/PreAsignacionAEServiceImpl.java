package mx.uniprotec.application.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.uniprotec.application.dao.IPreAsignacionAEDao;
import mx.uniprotec.application.dao.IPreAsignacionDao;
import mx.uniprotec.application.dao.IVendedorDEDao;
import mx.uniprotec.application.entity.DatosEconomicosEntity;
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
	public DatosEconomicosEntity savePreAsignacionAE(DatosEconomicosEntity preAsignacionAENew) {
		return preAsignacionAEDao.save(preAsignacionAENew);
	}



	

	@Override
	public List<DatosEconomicosEntity> findAll() {
		return (List<DatosEconomicosEntity>) preAsignacionAEDao.findAll();
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
	public DatosEconomicosEntity findByIdDatoEconomico(Long idDatosEconomicos) {
		DatosEconomicosEntity datosEconomicosEntity = preAsignacionAEDao.findById(idDatosEconomicos).orElse(null);
		return datosEconomicosEntity;
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
