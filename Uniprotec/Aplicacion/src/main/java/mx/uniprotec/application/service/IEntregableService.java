package mx.uniprotec.application.service;

import java.util.List;

import mx.uniprotec.application.entity.EntregableEntity;
import mx.uniprotec.application.entity.ParticipanteEntity;
import mx.uniprotec.entidad.modelo.ParticipantesModelo;

public interface IEntregableService {
	
	public List<EntregableEntity> consultaEntregable(Long idAsignacion);
	public EntregableEntity createEntregable(EntregableEntity entregableEntity);
	public EntregableEntity actualizaEntregable(EntregableEntity entregableEntity);
	public int deleteEntrgable(String idAsignacion);
	
	public ParticipanteEntity createParticipantes(List<ParticipanteEntity> list);
	public ParticipanteEntity updateParticipantes(List<ParticipanteEntity> participantes, Long idEntregable);
	public List<ParticipanteEntity> consultaParticipantes(Long idEntregable);
	public int deleteIdEntregable(Long idEntregable);
	public List<ParticipanteEntity> consultaParticipantesImportar(Long idClienteAsignacion);

}
