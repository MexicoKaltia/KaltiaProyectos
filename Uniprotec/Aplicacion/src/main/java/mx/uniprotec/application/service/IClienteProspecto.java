package mx.uniprotec.application.service;

import java.util.List;

import mx.uniprotec.application.entity.ClienteProspectoEntity;

public interface IClienteProspecto {
	
	public ClienteProspectoEntity saveClienteProspecto(ClienteProspectoEntity clienteProspecto);

	public List<ClienteProspectoEntity> findAll();

	public ClienteProspectoEntity findId(Long valueOf);

	public int deleteId(Long id);

}
