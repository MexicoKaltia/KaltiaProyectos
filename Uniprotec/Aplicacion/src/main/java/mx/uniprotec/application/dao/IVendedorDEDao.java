package mx.uniprotec.application.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import mx.uniprotec.application.entity.Vendedor;
import mx.uniprotec.application.entity.VendedorDatosEconomicos;

public interface IVendedorDEDao extends JpaRepository<VendedorDatosEconomicos, Long>{

	void deleteByIdDatosEconomicos(Long idDatosEconomicos);

	List<VendedorDatosEconomicos> findByIdDatosEconomicos(Long idDatosEconomicos);

//	@Query("delete u from VendedorDatosEconomicos u where u.idDatosEconomicos=?1")
//	void deleteAllByIdDatosEconomicos(Long idDatosEconomicos);

//	public Vendedor findByUsuarioVendedorIdUsuario(Long idUsuario);

}
