package mx.uniprotec.application.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import mx.uniprotec.application.entity.Vendedor;

public interface IVendedorService {

	public List<Vendedor> findAll();
	
	public Page<Vendedor> findAll(Pageable pageable);
	
	public Vendedor findById(Long id);
	
	public Vendedor save(Vendedor Vendedor);
	
	public void delete(Long id);
	
	//public List<Region> findAllRegiones();

}
