package mx.uniprotec.application.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.uniprotec.application.dao.IRegionDao;
import mx.uniprotec.application.dao.IVendedorDao;
import mx.uniprotec.application.entity.Region;
import mx.uniprotec.application.entity.Vendedor;


@Service
public class AplicacionServiceImpl implements IAplicacionService {
	
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
			return regionDao.findById(idRegion).orElse(null);
	}

	@Override
	public List<Region> findAllRegiones() {
			return clienteService.findAllRegiones();
	}

	@Override
	public Vendedor findVendedor(Long idVendedorCliente) {
		return vendedorDao.findById(idVendedorCliente).orElse(null);
	}

}
