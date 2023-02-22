package mx.uniprotec.application.service;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.jfree.util.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.uniprotec.application.dao.IPreAsignacionAEDao;
import mx.uniprotec.application.dao.IPreAsignacionDao;
import mx.uniprotec.application.dao.IVendedorDEDao;
import mx.uniprotec.application.entity.Asignacion;
import mx.uniprotec.application.entity.DatosEconomicosEntity;
import mx.uniprotec.application.entity.ParticipanteEntity;
import mx.uniprotec.application.entity.PreAsignacion;
import mx.uniprotec.application.entity.PreAsignacionAEEntity;
import mx.uniprotec.application.entity.VendedorDatosEconomicos;
import mx.uniprotec.entidad.modelo.DatosEconomicosModelo;

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
	@Autowired
	IAsignacionService asignacionService;

		
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
	
	



	@Override
	public int deleteDatosEconomicos(Long idDatosEconomicos) {
		try {
			preAsignacionAEDao.deleteById(idDatosEconomicos);
				return 0;
		} catch (Exception e) {
			return 99;
		}
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
		boolean flagExist = false;
		String strAsignaciones = null;
		try {
			List<VendedorDatosEconomicos> vDENew = (List<VendedorDatosEconomicos>) vendedorDatosEconomicosDao.findByIdDatosEconomicos(idPreAsignacionAE);
			if(vDENew.get(0).getIdAsignacion() == null) {
				flagExist = true;
				strAsignaciones = vDENew.get(0).getListAsignaciones();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			vendedorDatosEconomicosDao.deleteByIdDatosEconomicos(idPreAsignacionAE);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		for(VendedorDatosEconomicos vDE : vendedores) {
			try {
				if(flagExist) {
					Log.info("asignaciones");
					vDE.setListAsignaciones(strAsignaciones);
					vDE.setIdAsignacion(null);
				}
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





	@Override
	@Transactional
	public int deleteVendedoresIdDatosEconomicos(Long idDatosEconomicos) {
		try {
			vendedorDatosEconomicosDao.deleteByIdDatosEconomicos(idDatosEconomicos);
				return 0;
		} catch (Exception e) {
			return 99;
		}
		
		
	}





	@Override
	public List<VendedorDatosEconomicos> findVendedoresIdDatosEconomicos(Long idDatosEconomicos) {
		return (List<VendedorDatosEconomicos>) vendedorDatosEconomicosDao.findByIdDatosEconomicos(idDatosEconomicos);
		
	}





	@Override
	public void updateAsignacionFactura(@Valid DatosEconomicosModelo datosEconomicos) {
		try {
			if(datosEconomicos.getIdAsignacion() == null && datosEconomicos.getListAsignaciones() != null) {
				List<Integer> arrayAsignaciones = datosEconomicos.getListAsignaciones();
				for(Integer a : arrayAsignaciones) {
					Asignacion asignacion = asignacionService.findById(Long.valueOf(a));
					asignacion.setNumeroFactura(datosEconomicos.getNumFactura());
					asignacionService.save(asignacion);		
				}
			}
		} catch (Exception e) {
			Log.info(e.getMessage());
		}	
	}

	



}
