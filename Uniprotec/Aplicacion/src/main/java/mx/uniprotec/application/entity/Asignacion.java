package mx.uniprotec.application.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name="asignaciones")
@JsonIdentityInfo(
		  generator = ObjectIdGenerators.PropertyGenerator.class, 
		  property = "idAsignacion")
public class Asignacion implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 9031446066131991355L;

	public Asignacion() {
		// TODO Auto-generated constructor stub
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idAsignacion;
	@Column(nullable=false)
	private String fechaAsignacion;
	@Column(nullable=false)
	private Long idClienteAsignacion;
	@NotEmpty(message ="clienteAsignacion no puede estar vacio")
	@Size(min=1, max=300, message="el tamaño tiene que estar entre 4 y 300")
	@Column(nullable=false)
	private String clienteAsignacion;
	@Column(nullable=false)
	private Long idCursoAsignacion;
	@Column(nullable=false)
	private String cursoAsignacion;
	@Column(nullable=false)
	private Long idInstructorAsignacion;
	@Column(nullable=false)
	private String instructorAsignacion;
	@Column(nullable=false)
	private String horarioAsignacion;
	@Column(nullable=false)
	private String participantesAsignacion;
	@Column(nullable=false)
	private String nivelAsignacion;
	@Column
	private String archivosAsignacion;
	@Column
	private String observacionesAsignacion;
	@Column(nullable=false)
	private long idRegionAsignacion;
	@Column(nullable=false)
	private String nombreRegionAsignacion;
	@Column(nullable=false)
	private LocalDateTime createAtAsignacion;
	@Column(nullable=false)
	private Long userCreateAsignacion;
	@Column(nullable=false)
	private String statusAsignacion;

	@Override
	public String toString() {
		return "Asignacion [idAsignacion=" + idAsignacion + ", fechaAsignacion=" + fechaAsignacion
				+ ", idClienteAsignacion=" + idClienteAsignacion + ", clienteAsignacion=" + clienteAsignacion
				+ ", idCursoAsignacion=" + idCursoAsignacion + ", cursoAsignacion=" + cursoAsignacion
				+ ", idInstructorAsignacion=" + idInstructorAsignacion + ", instructorAsignacion="
				+ instructorAsignacion + ", horarioAsignacion=" + horarioAsignacion + ", participantesAsignacion="
				+ participantesAsignacion + ", nivelAsignacion=" + nivelAsignacion + ", archivosAsignacion="
				+ archivosAsignacion + ", observacionesAsignacion=" + observacionesAsignacion + ", createAtAsignacion="
				+ createAtAsignacion + ", userCreateAsignacion=" + userCreateAsignacion + ", statusAsignacion="
				+ statusAsignacion + "]";
	}

	public Asignacion(Long idAsignacion, String fechaAsignacion, Long idClienteAsignacion,
			@NotEmpty(message = "clienteAsignacion no puede estar vacio") @Size(min = 1, max = 300, message = "el tamaño tiene que estar entre 4 y 300") String clienteAsignacion,
			Long idCursoAsignacion, String cursoAsignacion, Long idInstructorAsignacion, String instructorAsignacion,
			String horarioAsignacion, String participantesAsignacion, String nivelAsignacion, String archivosAsignacion,
			String observacionesAsignacion, LocalDateTime createAtAsignacion, Long userCreateAsignacion,
			String statusAsignacion) {
		super();
		this.idAsignacion = idAsignacion;
		this.fechaAsignacion = fechaAsignacion;
		this.idClienteAsignacion = idClienteAsignacion;
		this.clienteAsignacion = clienteAsignacion;
		this.idCursoAsignacion = idCursoAsignacion;
		this.cursoAsignacion = cursoAsignacion;
		this.idInstructorAsignacion = idInstructorAsignacion;
		this.instructorAsignacion = instructorAsignacion;
		this.horarioAsignacion = horarioAsignacion;
		this.participantesAsignacion = participantesAsignacion;
		this.nivelAsignacion = nivelAsignacion;
		this.archivosAsignacion = archivosAsignacion;
		this.observacionesAsignacion = observacionesAsignacion;
		this.createAtAsignacion = createAtAsignacion;
		this.userCreateAsignacion = userCreateAsignacion;
		this.statusAsignacion = statusAsignacion;
	}

	public Long getIdAsignacion() {
		return idAsignacion;
	}

	public void setIdAsignacion(Long idAsignacion) {
		this.idAsignacion = idAsignacion;
	}

	public String getFechaAsignacion() {
		return fechaAsignacion;
	}

	public void setFechaAsignacion(String fechaAsignacion) {
		this.fechaAsignacion = fechaAsignacion;
	}

	public Long getIdClienteAsignacion() {
		return idClienteAsignacion;
	}

	public void setIdClienteAsignacion(Long idClienteAsignacion) {
		this.idClienteAsignacion = idClienteAsignacion;
	}

	public String getClienteAsignacion() {
		return clienteAsignacion;
	}

	public void setClienteAsignacion(String clienteAsignacion) {
		this.clienteAsignacion = clienteAsignacion;
	}

	public Long getIdCursoAsignacion() {
		return idCursoAsignacion;
	}

	public void setIdCursoAsignacion(Long idCursoAsignacion) {
		this.idCursoAsignacion = idCursoAsignacion;
	}

	public String getCursoAsignacion() {
		return cursoAsignacion;
	}

	public void setCursoAsignacion(String cursoAsignacion) {
		this.cursoAsignacion = cursoAsignacion;
	}

	public Long getIdInstructorAsignacion() {
		return idInstructorAsignacion;
	}

	public void setIdInstructorAsignacion(Long idInstructorAsignacion) {
		this.idInstructorAsignacion = idInstructorAsignacion;
	}

	public String getInstructorAsignacion() {
		return instructorAsignacion;
	}

	public void setInstructorAsignacion(String instructorAsignacion) {
		this.instructorAsignacion = instructorAsignacion;
	}

	public String getHorarioAsignacion() {
		return horarioAsignacion;
	}

	public void setHorarioAsignacion(String horarioAsignacion) {
		this.horarioAsignacion = horarioAsignacion;
	}

	public String getParticipantesAsignacion() {
		return participantesAsignacion;
	}

	public void setParticipantesAsignacion(String participantesAsignacion) {
		this.participantesAsignacion = participantesAsignacion;
	}

	public String getNivelAsignacion() {
		return nivelAsignacion;
	}

	public void setNivelAsignacion(String nivelAsignacion) {
		this.nivelAsignacion = nivelAsignacion;
	}

	public String getArchivosAsignacion() {
		return archivosAsignacion;
	}

	public void setArchivosAsignacion(String archivosAsignacion) {
		this.archivosAsignacion = archivosAsignacion;
	}

	public String getObservacionesAsignacion() {
		return observacionesAsignacion;
	}

	public void setObservacionesAsignacion(String observacionesAsignacion) {
		this.observacionesAsignacion = observacionesAsignacion;
	}

	public LocalDateTime getCreateAtAsignacion() {
		return createAtAsignacion;
	}

	public void setCreateAtAsignacion(LocalDateTime createAtAsignacion) {
		this.createAtAsignacion = createAtAsignacion;
	}

	public Long getUserCreateAsignacion() {
		return userCreateAsignacion;
	}

	public void setUserCreateAsignacion(Long userCreateAsignacion) {
		this.userCreateAsignacion = userCreateAsignacion;
	}

	public String getStatusAsignacion() {
		return statusAsignacion;
	}

	public void setStatusAsignacion(String statusAsignacion) {
		this.statusAsignacion = statusAsignacion;
	}

	public long getIdRegionAsignacion() {
		return idRegionAsignacion;
	}

	public void setIdRegionAsignacion(long idRegionAsignacion) {
		this.idRegionAsignacion = idRegionAsignacion;
	}

	public String getNombreRegionAsignacion() {
		return nombreRegionAsignacion;
	}

	public void setNombreRegionAsignacion(String nombreRegionAsignacion) {
		this.nombreRegionAsignacion = nombreRegionAsignacion;
	}
	
	
	
		
	
}
