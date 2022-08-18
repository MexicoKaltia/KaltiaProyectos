package mx.uniprotec.application.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.uniprotec.application.dao.IEntregableDao;
import mx.uniprotec.application.dao.IParticipanteDao;
import mx.uniprotec.application.entity.EntregableEntity;
import mx.uniprotec.application.entity.ParticipanteEntity;
import mx.uniprotec.entidad.modelo.ParticipantesModelo;

@Service
public class EntregableService implements IEntregableService {

	@Autowired
	IEntregableDao entregableDao;
	@Autowired
	IParticipanteDao participanteDao;
	
	public EntregableService() {
		// TODO Auto-generated constructor stub
	}

	@Override
	@Transactional(readOnly = true)
	public List<EntregableEntity> consultaEntregable(Long idAsignacion) {
		try {
			List<EntregableEntity> entregables = entregableDao.findByIdAsignacion(idAsignacion);
			return entregables;
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<EntregableEntity>();
		}
		
	}

	@Override
	public EntregableEntity createEntregable(EntregableEntity entregableEntity) {
		try {
			EntregableEntity ee= new EntregableEntity();
			ee = entregableDao.save(entregableEntity);
			return ee;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}

	@Override
	public EntregableEntity actualizaEntregable(EntregableEntity entregableEntity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int deleteEntrgable(String idAsignacion) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	/*
	 * Participantes
	 */
	@Override
	public List<ParticipanteEntity> consultaParticipantes(Long idEntregable) {
		try {
			List<ParticipanteEntity> participantes = participanteDao.findByIdEntregable(idEntregable);
			return participantes;
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<ParticipanteEntity>();
		}

	}

	@Override
	public ParticipanteEntity createParticipantes(Long idEntregable, Long idAsignacion,
		List<ParticipanteEntity> formBParticipantes) {
		ParticipanteEntity participanteEntity = null;
		for(ParticipanteEntity pe : formBParticipantes) {
			try {
				participanteEntity = participanteDao.save(pe);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return participanteEntity ;
	}

	@Override
	@Transactional
	public ParticipanteEntity updateParticipantes(List<ParticipanteEntity> participantes, Long idEntregable) {
		
		ParticipanteEntity participanteEntity = null;
		for(ParticipanteEntity pe : participantes) {
			try {
				participanteDao.deleteByIdParticipanteAndIdEntregable(pe.getIdParticipante(), idEntregable);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		for(ParticipanteEntity pe : participantes) {
			try {
				participanteEntity = participanteDao.save(pe);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
			return participanteEntity ;
	}

	@Override
	public int deleteIdEntregable(Long idEntregable) {
		try {
			entregableDao.deleteById(idEntregable);
			return  0;
			} catch (Exception e) {
			return  99;
			}
	}

	

}
