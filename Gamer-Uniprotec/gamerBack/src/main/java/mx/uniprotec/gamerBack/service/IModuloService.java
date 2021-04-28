package mx.uniprotec.gamerBack.service;

import java.util.List;

import javax.validation.Valid;

import mx.uniprotec.entidad.modelo.ModuloDidactico;
import mx.uniprotec.entidad.modelo.UsuarioAudiencia;
import mx.uniprotec.gamerBack.entity.ModuloDidacticoEntity;

public interface IModuloService {
	

	public ModuloDidacticoEntity saveModulo(@Valid ModuloDidactico modulo);

	public ModuloDidacticoEntity actualizaModulo(@Valid ModuloDidactico modulo);

	public ModuloDidacticoEntity findByModuloNombre(String moduloNombre);

	public List<ModuloDidacticoEntity> findAll();

	public ModuloDidacticoEntity findById(Long id);

	public void delete(Long id);
	

}
