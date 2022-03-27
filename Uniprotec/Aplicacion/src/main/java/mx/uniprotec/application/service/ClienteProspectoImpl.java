package mx.uniprotec.application.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.uniprotec.application.dao.IClienteProspectoDao;
import mx.uniprotec.application.entity.ClienteProspectoEntity;

@Service
public class ClienteProspectoImpl implements IClienteProspecto {

	@Autowired
	IClienteProspectoDao clienteProspectoDao;
	
	public ClienteProspectoImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public ClienteProspectoEntity saveClienteProspecto(ClienteProspectoEntity clienteProspecto) {
		
		return clienteProspectoDao.save(clienteProspecto);
	}

	@Override
	public List<ClienteProspectoEntity> findAll() {
		return clienteProspectoDao.findAll();
	}

	@Override
	public ClienteProspectoEntity findId(Long valueOf) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public int deleteId(Long id) {
		try {
			clienteProspectoDao.deleteById(id);
			return 0;
		} catch (Exception e) {
			return 99;
		}
		
	}

}
