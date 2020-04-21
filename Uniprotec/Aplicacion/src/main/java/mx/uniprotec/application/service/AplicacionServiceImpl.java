package mx.uniprotec.application.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.uniprotec.application.dao.IRegionDao;
import mx.uniprotec.application.entity.Region;


@Service
public class AplicacionServiceImpl implements IAplicacionService {
	
	public AplicacionServiceImpl() {
		// TODO Auto-generated constructor stub
	}
	
	@Autowired
	IClienteService clienteService;
	
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

}
