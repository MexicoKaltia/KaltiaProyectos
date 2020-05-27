package mx.uniprotec.entidad.modelo;


public class AsignacionModelo {

	public AsignacionModelo() {
		// TODO Auto-generated constructor stub
	}

	
	private Long idAsignacion;
	private String fechaAsignacion;
	private Long idClienteAsignacion;
	private String clienteAsignacion;
	private Long idCursoAsignacion;
	private String cursoAsignacion;
	private Long idInstructorAsignacion;
	private String instructorAsignacion;
	private String horarioAsignacion;
	private String archivosAsignacion;
	private String observacionesAsignacion;
	private String createAtAsignacion;
	private String userCreateAsignacion;
	private String statusAsignacion;
	
	@Override
	public String toString() {
		return "Asignacion [idAsignacion=" + idAsignacion + ", fechaAsignacion=" + fechaAsignacion
				+ ", idClienteAsignacion=" + idClienteAsignacion + ", clienteAsignacion=" + clienteAsignacion
				+ ", idCursoAsignacion=" + idCursoAsignacion + ", cursoAsignacion=" + cursoAsignacion
				+ ", idInstructorAsignacion=" + idInstructorAsignacion + ", instructorAsignacion="
				+ instructorAsignacion + ", horarioAsignacion=" + horarioAsignacion + ", archivosAsignacion="
				+ archivosAsignacion + ", observacionesAsignacion=" + observacionesAsignacion + ", createAtAsignacion="
				+ createAtAsignacion + ", userCreateAsignacion=" + userCreateAsignacion + ", statusAsignacion="
				+ statusAsignacion + "]";
	}

	public AsignacionModelo(Long idAsignacion, String fechaAsignacion, Long idClienteAsignacion,
			String clienteAsignacion,
			Long idCursoAsignacion, String cursoAsignacion, Long idInstructorAsignacion, String instructorAsignacion,
			String horarioAsignacion, String archivosAsignacion, String observacionesAsignacion,
			String createAtAsignacion, String userCreateAsignacion, String statusAsignacion) {
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

	public String getCreateAtAsignacion() {
		return createAtAsignacion;
	}

	public void setCreateAtAsignacion(String createAtAsignacion) {
		this.createAtAsignacion = createAtAsignacion;
	}

	public String getUserCreateAsignacion() {
		return userCreateAsignacion;
	}

	public void setUserCreateAsignacion(String userCreateAsignacion) {
		this.userCreateAsignacion = userCreateAsignacion;
	}

	public String getStatusAsignacion() {
		return statusAsignacion;
	}

	public void setStatusAsignacion(String statusAsignacion) {
		this.statusAsignacion = statusAsignacion;
	}

	
	
	

}
