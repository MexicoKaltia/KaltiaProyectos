package mx.uniprotec.application.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.uniprotec.application.dao.IClienteDao;
import mx.uniprotec.application.entity.Cliente;
import mx.uniprotec.application.entity.Region;

@Service
public class ClienteServiceImpl implements IClienteService {
	
	 private final Logger log = LoggerFactory.getLogger(ClienteServiceImpl.class);


	@Autowired
	private IClienteDao clienteDao;

	@Override
	@Transactional(readOnly = true)
	public List<Cliente> findAll() {
		List<Cliente> clientes = null;
		try {
			clientes = clienteDao.findAll();
		} catch (Exception e) {
			clientes.set(0, new Cliente());
			e.printStackTrace();
		}
		return clientes;
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Cliente> findAll(Pageable pageable) {
		Page<Cliente> clientes = null;
		try {
			clientes = clienteDao.findAll(pageable);
		} catch (Exception e) {
			clientes.toString();
			e.printStackTrace();
		}
		return clientes;
	}
	
	@Override
	@Transactional(readOnly = true)
	public Cliente findById(Long id) {
		Cliente cliente = null;
		try {
			cliente = clienteDao.findById(id).orElse(null); 
		} catch (Exception e) {
			cliente.setIdCliente(0l);
			e.printStackTrace();
		}
		return cliente;
	}

	@Override
	@Transactional
	public Cliente save(Cliente cliente) {
		log.info("save cliente:"+cliente.toString());
//		Cliente cliente = null;
		try {
			cliente = clienteDao.save(cliente); 
		} catch (Exception e) {
			cliente.setIdCliente(0l);
			e.printStackTrace();
		}
		return cliente;
	}

	@Override
	@Transactional
	public void delete(Long id) {
		clienteDao.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Region> findAllRegiones() {
		return clienteDao.findAllRegiones();
	}

}
