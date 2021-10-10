package mx.uniprotec.inicio.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.uniprotec.entidad.modelo.EntregableModelo;
import mx.uniprotec.entidad.modelo.MonitorEntidades;
import mx.uniprotec.entidad.modelo.ParticipantesModelo;
import mx.uniprotec.entidad.modelo.ResultVO;
import mx.uniprotec.inicio.util.BaseClientRest;
import mx.uniprotec.inicio.util.ComponenteComun;

@Service
public class EntregableService implements IEntregableService {
	
	@Autowired
	BaseClientRest baseClientRest ;
	
	protected final Log log = LogFactory.getLog(getClass());

	public EntregableService() {
		// TODO Auto-generated constructor stub
	}
	
	MonitorEntidades me = new MonitorEntidades();

	@SuppressWarnings("unchecked")
	@Override
	public ResultVO consultaEntregable(String token, Long idAsignacion) {
		ResultVO rs= (ResultVO) baseClientRest.objetoGetId(token, BaseClientRest.URL_CRUD_ENTREGABLE, null, idAsignacion.toString());
		if(rs.getCodigo() != 500) {
			JSONObject jsonGeneral = rs.getJsonResponse();
			JSONObject jsonEntregables = new JSONObject();
			jsonEntregables.put("entregables", jsonGeneral.get("entregables"));
			jsonEntregables.put("participantes", jsonGeneral.get("participantes"));
			rs.setJsonResponseObject(jsonEntregables);
		}
			return rs;
		
	}
	
	@Override
	public ResultVO createEntrgable(EntregableModelo entregable, String accesToken, Long idUsuario) {
		log.info(entregable.toString());
		List<ParticipantesModelo> participantes = getParticipantes(entregable, idUsuario);
		
		log.info("evidencias");
		if(entregable.getFormCEvidenciasFotograficasB() != null || !(entregable.getFormCEvidenciasFotograficasB().size() == 0)) {
			if(entregable.getFormCEvidenciasFotograficas() == null || entregable.getFormCEvidenciasFotograficas().size() == 0) {
				entregable.setFormCEvidenciasFotograficas(entregable.getFormCEvidenciasFotograficasB());
				log.info(entregable.getFormCEvidenciasFotograficas());
			}
		}
		entregable.setFormBParticipantes(participantes);
		entregable.setUserCreate(idUsuario);
		entregable.setCreateAt(me.getNowEntidad());
		entregable.setStatus("create");
		
		ResultVO rs= (ResultVO) baseClientRest.objetoPost(accesToken, BaseClientRest.URL_CRUD_ENTREGABLE, entregable);
		if(rs.getCodigo() != 500) {
			rs.setJsonResponseObject(null);
		}
			return rs;
	}

	

	
	@Override
	public ResultVO actualizaEntrgable(String token, String idAsignacion) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultVO deleteEntrgable(String token, String idAsignacion) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	/*
	 * Privates
	 */
	
	private List<ParticipantesModelo> getParticipantes(EntregableModelo entregable, Long idUsuario) {
//		log.info(entregable.getFormBParticipantesStr());
		String[] tmp = null;
		List<ParticipantesModelo> participantes = new ArrayList<ParticipantesModelo>();
		me = ComponenteComun.monitorCampos();
		
		if(entregable.getFormBParticipantesStr().contains("idParticipante")) {
			 tmp = entregable.getFormBParticipantesStr().split("},");
			 if(tmp.length > 0) {
					for(String a : tmp) {
						if(!a.contains("}")) {
							a = a.concat("}");
						}
						JSONParser parser = new JSONParser();
						try {
							
							ParticipantesModelo participante = new ParticipantesModelo(); 
							JSONObject json = (JSONObject) parser.parse(a);
							
							participante.setIdParticipante(json.get("idParticipante").toString());
							participante.setParticipanteNombre((String) json.get("participanteNombre"));
							participante.setParticipanteCURP((String) json.get("participanteCURP"));
							participante.setParticipantePuesto((String) json.get("participantePuesto"));
							participante.setParticipanteFoto((String) json.get("participanteFoto"));
							participante.setParticipanteExamenTeoricoInicial(Double.valueOf(json.get("participanteExamenTeoricoInicial").toString()));
							participante.setParticipanteExamenTeoricoFinal(Double.valueOf(json.get("participanteExamenTeoricoFinal").toString()));
							participante.setParticipanteExamenPractico(Double.valueOf(json.get("participanteExamenPractico").toString()));
							participante.setParticipantePromedio(Double.valueOf(json.get("participantePromedio").toString()));
							participante.setParticipanteObservaciones("");
							participante.setUserCreate(idUsuario);
							participante.setCreateAt(me.getNowEntidad());
							participante.setStatus("create");
							
							participantes.add(participante);
//							log.info(participante);
						} catch (ParseException e) {
							e.printStackTrace();
						}
					}
				}
		}
		
		
		
		return participantes;
	}

	

}
