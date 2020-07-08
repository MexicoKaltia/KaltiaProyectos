package mx.uniprotec.application.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import mx.uniprotec.application.entity.Vendedor;

public interface IVendedorDao extends CrudRepository<Vendedor, Long>{

	public Vendedor findByUsuarioVendedorIdUsuario(Long idUsuario);

}
