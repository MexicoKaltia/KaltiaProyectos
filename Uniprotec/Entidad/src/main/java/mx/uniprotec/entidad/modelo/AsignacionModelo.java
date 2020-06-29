package mx.uniprotec.entidad.modelo;

import java.io.Serializable;
import java.time.LocalDateTime;

public class AsignacionModelo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1958831875740136587L;
	public AsignacionModelo() {
		// TODO Auto-generated constructor stub
	}

	
	private Long idAsignacion;
	private String idAsignacionLogica;
	private String fechaAsignacion;
	private Long idClienteAsignacion;
	private String clienteAsignacion;
	private Long idCursoAsignacion;
	private String cursoAsignacion;
	private Long idInstructorAsignacion;
	private String instructorAsignacion;
	private String horarioAsignacion;
	private String archivosAsignacion;
	private String archivosAsignacionTexto;
	private String participantesAsignacion;
	private String nivelAsignacion;
	private String observacionesAsignacion;
	private Long idRegionAsignacion;
	private String nombreRegionAsignacion;
	private String tipoCursoAsignacion;
	private LocalDateTime createAtAsignacion;
	private Long userCreateAsignacion;
	private String statusAsignacion;
	
	@Override
	public String toString() {
		return "AsignacionModelo [idAsignacion=" + idAsignacion + ", idAsignacionLogica=" + idAsignacionLogica
				+ ", fechaAsignacion=" + fechaAsignacion + ", idClienteAsignacion=" + idClienteAsignacion
				+ ", clienteAsignacion=" + clienteAsignacion + ", idCursoAsignacion=" + idCursoAsignacion
				+ ", cursoAsignacion=" + cursoAsignacion + ", idInstructorAsignacion=" + idInstructorAsignacion
				+ ", instructorAsignacion=" + instructorAsignacion + ", horarioAsignacion=" + horarioAsignacion
				+ ", archivosAsignacion=" + archivosAsignacion + ", archivosAsignacionTexto=" + archivosAsignacionTexto
				+ ", participantesAsignacion=" + participantesAsignacion + ", nivelAsignacion=" + nivelAsignacion
				+ ", observacionesAsignacion=" + observacionesAsignacion + ", idRegionAsignacion=" + idRegionAsignacion
				+ ", nombreRegionAsignacion=" + nombreRegionAsignacion + ", tipoCursoAsignacion=" + tipoCursoAsignacion
				+ ", createAtAsignacion=" + createAtAsignacion + ", userCreateAsignacion=" + userCreateAsignacion
				+ ", statusAsignacion=" + statusAsignacion + "]";
	}
	
	public AsignacionModelo(Long idAsignacion, String idAsignacionLogica, String fechaAsignacion,
			Long idClienteAsignacion, String clienteAsignacion, Long idCursoAsignacion, String cursoAsignacion,
			Long idInstructorAsignacion, String instructorAsignacion, String horarioAsignacion,
			String archivosAsignacion, String archivosAsignacionTexto, String participantesAsignacion,
			String nivelAsignacion, String observacionesAsignacion, Long idRegionAsignacion,
			String nombreRegionAsignacion, String tipoCursoAsignacion, LocalDateTime createAtAsignacion,
			Long userCreateAsignacion, String statusAsignacion) {
		super();
		this.idAsignacion = idAsignacion;
		this.idAsignacionLogica = idAsignacionLogica;
		this.fechaAsignacion = fechaAsignacion;
		this.idClienteAsignacion = idClienteAsignacion;
		this.clienteAsignacion = clienteAsignacion;
		this.idCursoAsignacion = idCursoAsignacion;
		this.cursoAsignacion = cursoAsignacion;
		this.idInstructorAsignacion = idInstructorAsignacion;
		this.instructorAsignacion = instructorAsignacion;
		this.horarioAsignacion = horarioAsignacion;
		this.archivosAsignacion = archivosAsignacion;
		this.archivosAsignacionTexto = archivosAsignacionTexto;
		this.participantesAsignacion = participantesAsignacion;
		this.nivelAsignacion = nivelAsignacion;
		this.observacionesAsignacion = observacionesAsignacion;
		this.idRegionAsignacion = idRegionAsignacion;
		this.nombreRegionAsignacion = nombreRegionAsignacion;
		this.tipoCursoAsignacion = tipoCursoAsignacion;
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
	public String getArchivosAsignacion() {
		return archivosAsignacion;
	}
	public void setArchivosAsignacion(String archivosAsignacion) {
		this.archivosAsignacion = archivosAsignacion;
	}
	public String getArchivosAsignacionTexto() {
		return archivosAsignacionTexto;
	}
	public void setArchivosAsignacionTexto(String archivosAsignacionTexto) {
		this.archivosAsignacionTexto = archivosAsignacionTexto;
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
	public String getObservacionesAsignacion() {
		return observacionesAsignacion;
	}
	public void setObservacionesAsignacion(String observacionesAsignacion) {
		this.observacionesAsignacion = observacionesAsignacion;
	}
	public Long getIdRegionAsignacion() {
		return idRegionAsignacion;
	}
	public void setIdRegionAsignacion(Long idRegionAsignacion) {
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
	public String getIdAsignacionLogica() {
		return idAsignacionLogica;
	}
	public void setIdAsignacionLogica(String idAsignacionLogica) {
		this.idAsignacionLogica = idAsignacionLogica;
	}
	
		
	
	
		
	

}
