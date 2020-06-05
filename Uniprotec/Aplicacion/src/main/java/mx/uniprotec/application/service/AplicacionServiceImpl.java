package mx.uniprotec.application.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.uniprotec.application.dao.IRegionDao;
import mx.uniprotec.application.dao.IVendedorDao;
import mx.uniprotec.application.entity.Region;
import mx.uniprotec.application.entity.Vendedor;


@Service
public class AplicacionServiceImpl implements IAplicacionService {
	
	private final Logger log = LoggerFactory.getLogger(AplicacionServiceImpl.class);
	
	public AplicacionServiceImpl() {
		// TODO Auto-generated constructor stub
	}
	
	@Autowired
	IClienteService clienteService;
	
	@Autowired
	IVendedorDao vendedorDao;
	
	@Autowired
	IRegionDao regionDao;
	
	@Override
	@Transactional(readOnly = true)
	public Region findRegion(Long idRegion) {
		Region region = null;
		try {
			region = regionDao.findById(idRegion).orElse(null);
		} catch (Exception e) {
			log.info("error: Region FindbyId");
			region.setIdRegion(0l);
			region.setNombreRegion("");
			e.printStackTrace();
		}
		return region;
	}

	@Override
	public List<Region> findAllRegiones() {
		List<Region> regiones = null;
		try {
			 regiones = clienteService.findAllRegiones();
		} catch (Exception e) {
			log.info("error:  findAllRegiones");
			regiones.set(0, new Region());
			e.printStackTrace();
		}
			return regiones;
	}

	@Override
	public Vendedor findVendedor(Long idVendedorCliente) {
		Vendedor vendedor = null;
		try {
			vendedor = vendedorDao.findById(idVendedorCliente).orElse(null);
		} catch (Exception e) {
			log.info("error:  findVendedor");
			vendedor.setIdVendedor(0l);
			e.printStackTrace();
		}
		return vendedor;
	}

}
