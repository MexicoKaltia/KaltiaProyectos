package mx.uniprotec.gamerBack.service;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import mx.uniprotec.entidad.modelo.ModuloDidactico;
import mx.uniprotec.entidad.modelo.UsuarioAudiencia;
import mx.uniprotec.gamerBack.dao.IModuloDao;
import mx.uniprotec.gamerBack.dao.IUsuarioDao;
import mx.uniprotec.gamerBack.entity.ModuloDidacticoEntity;

@Service
public class ModuloService implements IModuloService {

	@Autowired
	private IModuloDao moduloDao;
	
	private Logger logger = LoggerFactory.getLogger(ModuloService.class);
	public ModuloService() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public ModuloDidacticoEntity saveModulo(@Valid ModuloDidactico modulo) {
		
		ModuloDidacticoEntity moduloEntity = new ModuloDidacticoEntity();
		ModuloDidacticoEntity moduloNew = new ModuloDidacticoEntity();
		moduloEntity.setModuloDidacticoNombre(modulo.getModuloDidacticoNombre());
		moduloEntity.setModuloDidacticoDescripcion(modulo.getModuloDidacticoDescripcion());
		moduloEntity.setModuloDidacticoInstrucciones(modulo.getModuloDidacticoInstrucciones());
		moduloEntity.setModuloDidacticoImagen(modulo.getModuloDidacticoImagen());
		moduloEntity.setModuloDidacticoIdImagen(modulo.getModuloDidacticoIdImagen());
		moduloEntity.setCreateAt(modulo.getCreateAt());
		moduloEntity.setCreateUser(modulo.getCreateUser());
		moduloEntity.setStatus(modulo.getStatus());
		
		try {
			moduloNew = moduloDao.save(moduloEntity);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return moduloNew;
	}

	@Override
	public ModuloDidacticoEntity actualizaModulo(@Valid ModuloDidactico modulo) {
		
		ModuloDidacticoEntity moduloActual = findById(Long.valueOf(modulo.getIdModuloDidactico()));
//		ModuloDidacticoEntity moduloUpdated = null;
		if (moduloActual == null) {
			return null;
		}
		if(modulo.getModuloDidacticoNombre()==null) {
			String arrayToString = arrayToString(modulo.getModuloDidacticoCursos());
			moduloActual.setModuloDidacticoCursos(arrayToString);
		}else {
			moduloActual.setModuloDidacticoNombre(modulo.getModuloDidacticoNombre());
		}
		
		moduloActual = moduloDao.save(moduloActual);
		
		return moduloActual;
	}

	

	@Override
	public ModuloDidacticoEntity findByModuloNombre(String moduloNombre) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ModuloDidacticoEntity> findAll() {
		List<ModuloDidacticoEntity> modulos = (List<ModuloDidacticoEntity>) moduloDao.findAll(); 
		return modulos;
	}

	@Override
	public ModuloDidacticoEntity findById(Long id) {
		return moduloDao.findById(id).orElse(null);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub

	}
	
	/*
	 * Privates
	 */

	private String arrayToString(List<Integer> moduloDidacticoCursos) {
		String string="";
		for(Integer i : moduloDidacticoCursos) {
			string = string + i + ",";
		}
		string = string.substring(0, (string.length()-1));
		logger.info(string);
		return string ;
	}
}
