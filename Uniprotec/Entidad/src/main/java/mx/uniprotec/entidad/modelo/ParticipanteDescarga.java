package mx.uniprotec.entidad.modelo;

import java.io.Serializable;
import java.time.LocalDateTime;

public class ParticipanteDescarga implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7446430477158256914L;

	public ParticipanteDescarga() {}

	private String participanteNombre;
	private String participanteCURP;
	private String participantePuesto;
//	private String participanteFoto;
	

	public String getParticipanteNombre() {
		return participanteNombre;
	}
	public void setParticipanteNombre(String participanteNombre) {
		this.participanteNombre = participanteNombre;
	}
	public String getParticipanteCURP() {
		return participanteCURP;
	}
	public void setParticipanteCURP(String participanteCURP) {
		this.participanteCURP = participanteCURP;
	}
	public String getParticipantePuesto() {
		return participantePuesto;
	}
	public void setParticipantePuesto(String participantePuesto) {
		this.participantePuesto = participantePuesto;
	}
//	public String getParticipanteFoto() {
//		return participanteFoto;
//	}
//	public void setParticipanteFoto(String participanteFoto) {
//		this.participanteFoto = participanteFoto;
//	}
	
	@Override
	public String toString() {
		return "ParticipanteDescarga [participanteNombre=" + participanteNombre + ", participanteCURP="
				+ participanteCURP + ", participantePuesto=" + participantePuesto + ", participanteFoto="
				+ "]";
	}
	public ParticipanteDescarga(String participanteNombre, String participanteCURP, String participantePuesto,
			String participanteFoto, Long idCliente) {
		super();
		this.participanteNombre = participanteNombre;
		this.participanteCURP = participanteCURP;
		this.participantePuesto = participantePuesto;
		
		
	}

	
	
}
