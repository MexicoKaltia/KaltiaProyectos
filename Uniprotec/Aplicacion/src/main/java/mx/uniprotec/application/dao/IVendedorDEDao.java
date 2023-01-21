package mx.uniprotec.application.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import mx.uniprotec.application.entity.Vendedor;
import mx.uniprotec.application.entity.VendedorDatosEconomicos;

public interface IVendedorDEDao extends JpaRepository<VendedorDatosEconomicos, Long>{

	void deleteByIdDatosEconomicos(Long idDatosEconomicos);

	//public Vendedor findByUsuarioVendedorIdUsuario(Long idUsuario);

}
