package mx.uniprotec.application.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.uniprotec.application.dao.IVendedorDao;
import mx.uniprotec.application.entity.Vendedor;
import mx.uniprotec.application.entity.Instructor;
import mx.uniprotec.application.entity.Region;

@Service
public class VendedorServiceImpl implements IVendedorService {

	@Autowired
	private IVendedorDao vendedorDao;

	@Override
	@Transactional(readOnly = true)
	public List<Vendedor> findAll() {
		return (List<Vendedor>) vendedorDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Vendedor> findAll(Pageable pageable) {
		return null;//vendedorDao.findAll(pageable);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Vendedor findById(Long id) {
		return vendedorDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Vendedor save(Vendedor instructor) {
		return vendedorDao.save(instructor);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		vendedorDao.deleteById(id);
	}

	@Override
	public Vendedor findByUsuarioVendedorIdUsuario(Long idUsuario) {
		
		return vendedorDao.findByUsuarioVendedorIdUsuario(idUsuario);
	}

//	@Override
//	@Transactional(readOnly = true)
//	public List<Region> findAllRegiones() {
//		return vendedorDao.findAllRegiones();
//	}

}
