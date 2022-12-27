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
@Table(name="preAsignaciones")
@JsonIdentityInfo(
		  generator = ObjectIdGenerators.PropertyGenerator.class, 
		  property = "idPreAsignacion")
public class PreAsignacion implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 9031446066131991355L;

	public PreAsignacion() {
		// TODO Auto-generated constructor stub
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idPreAsignacion;
	@Column(nullable=false)
	private String idAsignacionLogica;
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
	private String tipoCursoAsignacion;
	@Column(nullable=false)
	private LocalDateTime createAtAsignacion;
	@Column(nullable=false)
	private Long userCreateAsignacion;
	@Column(nullable=false)
	private String userCreateAsignacionTexto;
	@Column(nullable=false)
	private String statusAsignacion;
	@Column
	private int idStatusAsignacion;
	@Column
	private Boolean verificarEntregable;
	@Column
	private String guiaEntregable;
	@Column
	private String fechaPago;
	@Column
	private String numeroFactura;
	@Column
	private String archivoParticipantes;
	@Column
	private String costoHotel;
	@Column
	private String errorProceso;
	@Column
	private Long idPreAsignacionAE;
	@Column
	private String preAsignacionAEStatus;
	@Column
	private String clienteStatus;
	@Column
	private String seguimiento;
	@Column
	private String fechaInicioFactura;
	@Column
	private String fechaFinFactura;
	@Column
	private String fechaHoy;
	@Column
	private String nombreFactura;

	
	public String getNombreFactura() {
		return nombreFactura;
	}

	public void setNombreFactura(String nombreFactura) {
		this.nombreFactura = nombreFactura;
	}

	@Override
	public String toString() {
		return "Asignacion [idAsignacion=" + idPreAsignacion + ", idAsignacionLogica=" + idAsignacionLogica
				+ ", fechaAsignacion=" + fechaAsignacion + ", idClienteAsignacion=" + idClienteAsignacion
				+ ", clienteAsignacion=" + clienteAsignacion + ", idCursoAsignacion=" + idCursoAsignacion
				+ ", cursoAsignacion=" + cursoAsignacion + ", idInstructorAsignacion=" + idInstructorAsignacion
				+ ", instructorAsignacion=" + instructorAsignacion + ", horarioAsignacion=" + horarioAsignacion
				+ ", participantesAsignacion=" + participantesAsignacion + ", nivelAsignacion=" + nivelAsignacion
				+ ", archivosAsignacion=" + archivosAsignacion + ", observacionesAsignacion=" + observacionesAsignacion
				+ ", idRegionAsignacion=" + idRegionAsignacion + ", nombreRegionAsignacion=" + nombreRegionAsignacion
				+ ", tipoCursoAsignacion=" + tipoCursoAsignacion + ", createAtAsignacion=" + createAtAsignacion
				+ ", userCreateAsignacion=" + userCreateAsignacion + ", userCreateAsignacionTexto="
				+ userCreateAsignacionTexto + ", statusAsignacion=" + statusAsignacion + ", verificarEntregable="
				+ verificarEntregable + ", guiaEntregable=" + guiaEntregable + ", fechaPago=" + fechaPago
				+ ", numeroFactura=" + numeroFactura + ", archivoParticipantes=" + archivoParticipantes
				+ ", costoHotel=" + costoHotel + ", errorProceso=" + errorProceso + "]";
	}

	public PreAsignacion(Long idAsignacion, String fechaAsignacion, Long idClienteAsignacion,
			@NotEmpty(message = "clienteAsignacion no puede estar vacio") @Size(min = 1, max = 300, message = "el tamaño tiene que estar entre 4 y 300") String clienteAsignacion,
			Long idCursoAsignacion, String cursoAsignacion, Long idInstructorAsignacion, String instructorAsignacion,
			String horarioAsignacion, String participantesAsignacion, String nivelAsignacion, String archivosAsignacion,
			String observacionesAsignacion, LocalDateTime createAtAsignacion, Long userCreateAsignacion,
			String statusAsignacion) {
		super();
		this.idPreAsignacion = idAsignacion;
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

	public Long getIdPreAsignacion() {
		return idPreAsignacion;
	}

	public void setIdPreAsignacion(Long idPreAsignacion) {
		this.idPreAsignacion = idPreAsignacion;
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

	public String getTipoCursoAsignacion() {
		return tipoCursoAsignacion;
	}

	public void setTipoCursoAsignacion(String tipoCursoAsignacion) {
		this.tipoCursoAsignacion = tipoCursoAsignacion;
	}

	public String getIdAsignacionLogica() {
		return idAsignacionLogica;
	}

	public void setIdAsignacionLogica(String idAsignacionLogica) {
		this.idAsignacionLogica = idAsignacionLogica;
	}

	public Boolean getVerificarEntregable() {
		return verificarEntregable;
	}

	public void setVerificarEntregable(Boolean verificarEntregable) {
		this.verificarEntregable = verificarEntregable;
	}

	public String getGuiaEntregable() {
		return guiaEntregable;
	}

	public void setGuiaEntregable(String guiaEntregable) {
		this.guiaEntregable = guiaEntregable;
	}

	public String getFechaPago() {
		return fechaPago;
	}

	public void setFechaPago(String fechaPago) {
		this.fechaPago = fechaPago;
	}

	public String getNumeroFactura() {
		return numeroFactura;
	}

	public void setNumeroFactura(String numeroFactura) {
		this.numeroFactura = numeroFactura;
	}

	public String getUserCreateAsignacionTexto() {
		return userCreateAsignacionTexto;
	}

	public void setUserCreateAsignacionTexto(String userCreateAsignacionTexto) {
		this.userCreateAsignacionTexto = userCreateAsignacionTexto;
	}

	public String getArchivoParticipantes() {
		return archivoParticipantes;
	}

	public void setArchivoParticipantes(String archivoParticipantes) {
		this.archivoParticipantes = archivoParticipantes;
	}

	public String getCostoHotel() {
		return costoHotel;
	}

	public void setCostoHotel(String costoHotel) {
		this.costoHotel = costoHotel;
	}

	public String getErrorProceso() {
		return errorProceso;
	}

	public void setErrorProceso(String errorProceso) {
		this.errorProceso = errorProceso;
	}

	public Long getIdPreAsignacionAE() {
		return idPreAsignacionAE;
	}

	public void setIdPreAsignacionAE(Long idPreAsignacionAE) {
		this.idPreAsignacionAE = idPreAsignacionAE;
	}

	public String getPreAsignacionAEStatus() {
		return preAsignacionAEStatus;
	}

	public void setPreAsignacionAEStatus(String preAsignacionAEStatus) {
		this.preAsignacionAEStatus = preAsignacionAEStatus;
	}

	public String getClienteStatus() {
		return clienteStatus;
	}

	public void setClienteStatus(String clienteStatus) {
		this.clienteStatus = clienteStatus;
	}

	public String getSeguimiento() {
		return seguimiento;
	}

	public void setSeguimiento(String seguimiento) {
		this.seguimiento = seguimiento;
	}

	public int getIdStatusAsignacion() {
		return idStatusAsignacion;
	}

	public void setIdStatusAsignacion(int idStatusAsignacion) {
		this.idStatusAsignacion = idStatusAsignacion;
	}

	public String getFechaInicioFactura() {
		return fechaInicioFactura;
	}

	public void setFechaInicioFactura(String fechaInicioFactura) {
		this.fechaInicioFactura = fechaInicioFactura;
	}

	public String getFechaFinFactura() {
		return fechaFinFactura;
	}

	public void setFechaFinFactura(String fechaFinFactura) {
		this.fechaFinFactura = fechaFinFactura;
	}
	public String getFechaHoy() {
		return fechaHoy;
	}

	public void setFechaHoy(String fechaHoy) {
		this.fechaHoy = fechaHoy;
	}
	
	
	
		
	
}