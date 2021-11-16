package mx.uniprotec.application.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name="participantes")
@JsonIdentityInfo(
		  generator = ObjectIdGenerators.PropertyGenerator.class, 
		  property = "idParticipante")
public class ParticipanteEntity {

	public ParticipanteEntity() {
		// TODO Auto-generated constructor stub
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idParticipanteBD;
	@Column
	private String idParticipante;
	@Column
	private Long idEntregable;
	@Column
	private String participanteNombre;
	@Column
	private String participanteCURP;
	@Column
	private String participantePuesto;
	@Column
	private String participanteOcupacion;
	@Column
	private String participanteFoto;
	@Column
	private double participanteExamenTeoricoInicial;
	@Column
	private double participanteExamenTeoricoFinal;
	@Column
	private double participanteExamenPractico;
	@Column
	private double participantePromedio;
	@Column
	private String participanteAprovechamiento;
	@Column
	private String participanteObservaciones;
	@Column
	private String statusParticipante;
	@Column
	private Long userCreateParticipante;
	@Column
	private LocalDateTime createAtParticipante;
	
	
	@Override
	public String toString() {
		return "ParticipanteEntity [idParticipanteBD=" + idParticipanteBD + ", idParticipante=" + idParticipante
				+ ", idEntregable=" + idEntregable + ", participanteNombre=" + participanteNombre
				+ ", participanteCURP=" + participanteCURP + ", participantePuesto=" + participantePuesto
				+ ", participanteOcupacion=" + participanteOcupacion + ", participanteFoto=" + participanteFoto
				+ ", participanteExamenTeoricoInicial=" + participanteExamenTeoricoInicial
				+ ", participanteExamenTeoricoFinal=" + participanteExamenTeoricoFinal + ", participanteExamenPractico="
				+ participanteExamenPractico + ", participantePromedio=" + participantePromedio
				+ ", participanteAprovechamiento=" + participanteAprovechamiento + ", participanteObservaciones="
				+ participanteObservaciones + ", statusParticipante=" + statusParticipante + ", userCreateParticipante="
				+ userCreateParticipante + ", createAtParticipante=" + createAtParticipante + "]";
	}

	
	public String getIdParticipante() {
		return idParticipante;
	}
	public void setIdParticipante(String string) {
		this.idParticipante = string;
	}
	public Long getIdEntregable() {
		return idEntregable;
	}
	public void setIdEntregable(Long idEntregable) {
		this.idEntregable = idEntregable;
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
	public double getParticipanteExamenTeoricoInicial() {
		return participanteExamenTeoricoInicial;
	}
	public void setParticipanteExamenTeoricoInicial(double participanteExamenTeoricoInicial) {
		this.participanteExamenTeoricoInicial = participanteExamenTeoricoInicial;
	}
	public double getParticipanteExamenTeoricoFinal() {
		return participanteExamenTeoricoFinal;
	}
	public void setParticipanteExamenTeoricoFinal(double participanteExamenTeoricoFinal) {
		this.participanteExamenTeoricoFinal = participanteExamenTeoricoFinal;
	}
	public double getParticipanteExamenPractico() {
		return participanteExamenPractico;
	}
	public void setParticipanteExamenPractico(double participanteExamenPractico) {
		this.participanteExamenPractico = participanteExamenPractico;
	}
	public double getParticipantePromedio() {
		return participantePromedio;
	}
	public void setParticipantePromedio(double participantePromedio) {
		this.participantePromedio = participantePromedio;
	}
	public String getParticipanteObservaciones() {
		return participanteObservaciones;
	}
	public void setParticipanteObservaciones(String participanteObservaciones) {
		this.participanteObservaciones = participanteObservaciones;
	}
	public String getStatusParticipante() {
		return statusParticipante;
	}
	public void setStatusParticipante(String statusParticipante) {
		this.statusParticipante = statusParticipante;
	}
	public Long getUserCreateParticipante() {
		return userCreateParticipante;
	}
	public void setUserCreateParticipante(Long userCreateParticipante) {
		this.userCreateParticipante = userCreateParticipante;
	}
	public LocalDateTime getCreateAtParticipante() {
		return createAtParticipante;
	}
	public void setCreateAtParticipante(LocalDateTime createAtParticipante) {
		this.createAtParticipante = createAtParticipante;
	}


	public Long getIdParticipanteBD() {
		return idParticipanteBD;
	}


	public void setIdParticipanteBD(Long idParticipanteBD) {
		this.idParticipanteBD = idParticipanteBD;
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
	
	

	
	
}
