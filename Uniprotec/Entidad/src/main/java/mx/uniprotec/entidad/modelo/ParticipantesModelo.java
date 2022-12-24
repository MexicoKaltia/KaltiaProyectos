package mx.uniprotec.entidad.modelo;

import java.io.Serializable;
import java.time.LocalDateTime;

public class ParticipantesModelo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7446430477158256914L;

	public ParticipantesModelo() {
		// TODO Auto-generated constructor stub
	}

	private String idParticipante;
	private String participanteNombre;
	private String participanteCURP;
	private String participantePuesto;
	private String participanteOcupacion;
	private String participanteFoto;
	private String participanteExamenTeoricoInicial;
	private String participanteExamenTeoricoFinal;
	private String participanteExamenPractico;
	private String participantePromedio;
	private String participanteObservaciones;
	private String participanteAprovechamiento;
	private boolean participanteAprobado;
	private String status;
	private Long userCreate;
	private LocalDateTime createAt;
	private Long idCliente;
	

	

	public ParticipantesModelo(String idParticipante, String participanteNombre, String participanteCURP,
			String participantePuesto, String participanteFoto, String participanteExamenTeoricoInicial,
			String participanteExamenTeoricoFinal, String participanteExamenPractico, String participantePromedio,
			String participanteObservaciones) {
		super();
		this.idParticipante = idParticipante;
		this.participanteNombre = participanteNombre;
		this.participanteCURP = participanteCURP;
		this.participantePuesto = participantePuesto;
		this.participanteFoto = participanteFoto;
		this.participanteExamenTeoricoInicial = participanteExamenTeoricoInicial;
		this.participanteExamenTeoricoFinal = participanteExamenTeoricoFinal;
		this.participanteExamenPractico = participanteExamenPractico;
		this.participantePromedio = participantePromedio;
		this.participanteObservaciones = participanteObservaciones;
	}

	public String getIdParticipante() {
		return idParticipante;
	}

	public void setIdParticipante(String string) {
		this.idParticipante = string;
	}

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

	public String getParticipanteFoto() {
		return participanteFoto;
	}

	public void setParticipanteFoto(String participanteFoto) {
		this.participanteFoto = participanteFoto;
	}

	public String getParticipanteExamenTeoricoInicial() {
		return participanteExamenTeoricoInicial;
	}

	public void setParticipanteExamenTeoricoInicial(String participanteExamenTeoricoInicial) {
		this.participanteExamenTeoricoInicial = participanteExamenTeoricoInicial;
	}

	public String getParticipanteExamenTeoricoFinal() {
		return participanteExamenTeoricoFinal;
	}

	public void setParticipanteExamenTeoricoFinal(String participanteExamenTeoricoFinal) {
		this.participanteExamenTeoricoFinal = participanteExamenTeoricoFinal;
	}

	public String getParticipanteExamenPractico() {
		return participanteExamenPractico;
	}

	public void setParticipanteExamenPractico(String participanteExamenPractico) {
		this.participanteExamenPractico = participanteExamenPractico;
	}

	public String getParticipantePromedio() {
		return participantePromedio;
	}

	public void setParticipantePromedio(String participantePromedio) {
		this.participantePromedio = participantePromedio;
	}

	public String getParticipanteObservaciones() {
		return participanteObservaciones;
	}

	public void setParticipanteObservaciones(String participanteObservaciones) {
		this.participanteObservaciones = participanteObservaciones;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getUserCreate() {
		return userCreate;
	}

	public void setUserCreate(Long userCreate) {
		this.userCreate = userCreate;
	}

	public LocalDateTime getCreateAt() {
		return createAt;
	}

	public void setCreateAt(LocalDateTime createAt) {
		this.createAt = createAt;
	}

	public String getParticipanteOcupacion() {
		return participanteOcupacion;
	}

	public void setParticipanteOcupacion(String participanteOcupacion) {
		this.participanteOcupacion = participanteOcupacion;
	}

	public String getParticipanteAprovechamiento() {
		return participanteAprovechamiento;
	}

	public void setParticipanteAprovechamiento(String participanteAprovechamiento) {
		this.participanteAprovechamiento = participanteAprovechamiento;
	}

	public boolean isParticipanteAprobado() {
		return participanteAprobado;
	}

	public void setParticipanteAprobado(boolean participanteAprobado) {
		this.participanteAprobado = participanteAprobado;
	}

	public Long getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}

	@Override
	public String toString() {
		return "ParticipantesModelo [idParticipante=" + idParticipante + ", participanteNombre=" + participanteNombre
				+ ", participanteCURP=" + participanteCURP + ", participantePuesto=" + participantePuesto
				+ ", participanteOcupacion=" + participanteOcupacion + ", participanteFoto=" + participanteFoto
				+ ", participanteExamenTeoricoInicial=" + participanteExamenTeoricoInicial
				+ ", participanteExamenTeoricoFinal=" + participanteExamenTeoricoFinal + ", participanteExamenPractico="
				+ participanteExamenPractico + ", participantePromedio=" + participantePromedio
				+ ", participanteObservaciones=" + participanteObservaciones + ", participanteAprovechamiento="
				+ participanteAprovechamiento + ", participanteAprobado=" + participanteAprobado + ", status=" + status
				+ ", userCreate=" + userCreate + ", createAt=" + createAt + ", idCliente=" + idCliente + "]";
	}

	
	
	
}
