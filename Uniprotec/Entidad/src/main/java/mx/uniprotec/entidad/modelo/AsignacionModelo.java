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
	private Boolean verificarEntregable;
	private String guiaEntregable;
	private String fechaPago;
	private String numeroFactura;
	private LocalDateTime createAtAsignacion;
	private Long userCreateAsignacion;
	private String userCreateAsignacionTexto;
	private String statusAsignacion;
	private int idStatusAsignacion;  // 1 CURSO PREASIGNADO -- 2 ALTA ANALISIS ECONOMICO -- 3 REVISON -- 4 APROBACION PREASIGNACION -- 5 ASIGNACION -- 99 CANCELACION 
	private String archivoParticipantes;
	private String archivoParticipantesTexto;
	private String costoHotel;
	private String errorProceso;
	private String nombreFirmaInstructor;
	private Long idPreAsignacionAE;
	private String preAsignacionAEStatus;	
	private String clienteStatus;
	private String auxiliar0;
	
	private String seguimiento;
	private String nombreUsuarioSeguimiento;
	private String perfilUsuarioSeguimiento;
	private String mensajeSeguimiento;
	
	private String nombreCortoClienteProspecto;
	private String nombreCompletoClienteProspecto;
	private String rfcClienteProspecto;
	private int idRegionClienteProspecto;
	private String nombreRegionClienteProspecto;
	private String direccionClienteProspecto;
	private boolean flagClienteProspecto;
	
	private String agregarFactura;
	private String agregarFacturaTexto;
	private String fechaInicioFactura;
	private String fechaFinFactura;
	private String fechaHoy;
	
	

	private Long idPreAsignacion; // Este valor solo se carga una vez aprobado el evento. 
	
	
	
	
	
	public AsignacionModelo(Long idAsignacion, String idAsignacionLogica, String fechaAsignacion,
			Long idClienteAsignacion, String clienteAsignacion, Long idCursoAsignacion, String cursoAsignacion,
			Long idInstructorAsignacion, String instructorAsignacion, String horarioAsignacion,
			String archivosAsignacion, String archivosAsignacionTexto, String participantesAsignacion,
			String nivelAsignacion, String observacionesAsignacion, Long idRegionAsignacion,
			String nombreRegionAsignacion, String tipoCursoAsignacion, Boolean verificarEntregable,
			String guiaEntregable, String fechaPago, String numeroFactura, LocalDateTime createAtAsignacion,
			Long userCreateAsignacion, String userCreateAsignacionTexto, String statusAsignacion,
			int idStatusAsignacion, String archivoParticipantes, String archivoParticipantesTexto, String costoHotel,
			String errorProceso, String nombreFirmaInstructor, Long idPreAsignacionAE, String preAsignacionAEStatus,
			String clienteStatus, String auxiliar0, String seguimiento, String nombreUsuarioSeguimiento,
			String perfilUsuarioSeguimiento, String mensajeSeguimiento, String nombreCortoClienteProspecto,
			String nombreCompletoClienteProspecto, String rfcClienteProspecto, int idRegionClienteProspecto,
			String nombreRegionClienteProspecto, String direccionClienteProspecto, boolean flagClienteProspecto) {
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
		this.verificarEntregable = verificarEntregable;
		this.guiaEntregable = guiaEntregable;
		this.fechaPago = fechaPago;
		this.numeroFactura = numeroFactura;
		this.createAtAsignacion = createAtAsignacion;
		this.userCreateAsignacion = userCreateAsignacion;
		this.userCreateAsignacionTexto = userCreateAsignacionTexto;
		this.statusAsignacion = statusAsignacion;
		this.idStatusAsignacion = idStatusAsignacion;
		this.archivoParticipantes = archivoParticipantes;
		this.archivoParticipantesTexto = archivoParticipantesTexto;
		this.costoHotel = costoHotel;
		this.errorProceso = errorProceso;
		this.nombreFirmaInstructor = nombreFirmaInstructor;
		this.idPreAsignacionAE = idPreAsignacionAE;
		this.preAsignacionAEStatus = preAsignacionAEStatus;
		this.clienteStatus = clienteStatus;
		this.auxiliar0 = auxiliar0;
		this.seguimiento = seguimiento;
		this.nombreUsuarioSeguimiento = nombreUsuarioSeguimiento;
		this.perfilUsuarioSeguimiento = perfilUsuarioSeguimiento;
		this.mensajeSeguimiento = mensajeSeguimiento;
		this.nombreCortoClienteProspecto = nombreCortoClienteProspecto;
		this.nombreCompletoClienteProspecto = nombreCompletoClienteProspecto;
		this.rfcClienteProspecto = rfcClienteProspecto;
		this.idRegionClienteProspecto = idRegionClienteProspecto;
		this.nombreRegionClienteProspecto = nombreRegionClienteProspecto;
		this.direccionClienteProspecto = direccionClienteProspecto;
		this.flagClienteProspecto = flagClienteProspecto;
	}
	public Long getIdAsignacion() {
		return idAsignacion;
	}
	public void setIdAsignacion(Long idAsignacion) {
		this.idAsignacion = idAsignacion;
	}
	public String getIdAsignacionLogica() {
		return idAsignacionLogica;
	}
	public void setIdAsignacionLogica(String idAsignacionLogica) {
		this.idAsignacionLogica = idAsignacionLogica;
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
	public String getUserCreateAsignacionTexto() {
		return userCreateAsignacionTexto;
	}
	public void setUserCreateAsignacionTexto(String userCreateAsignacionTexto) {
		this.userCreateAsignacionTexto = userCreateAsignacionTexto;
	}
	public String getStatusAsignacion() {
		return statusAsignacion;
	}
	public void setStatusAsignacion(String statusAsignacion) {
		this.statusAsignacion = statusAsignacion;
	}
	public String getArchivoParticipantes() {
		return archivoParticipantes;
	}
	public void setArchivoParticipantes(String archivoParticipantes) {
		this.archivoParticipantes = archivoParticipantes;
	}
	public String getArchivoParticipantesTexto() {
		return archivoParticipantesTexto;
	}
	public void setArchivoParticipantesTexto(String archivoParticipantesTexto) {
		this.archivoParticipantesTexto = archivoParticipantesTexto;
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
	public String getNombreFirmaInstructor() {
		return nombreFirmaInstructor;
	}
	public void setNombreFirmaInstructor(String nombreFirmaInstructor) {
		this.nombreFirmaInstructor = nombreFirmaInstructor;
	}
	public Long getIdPreAsignacionAE() {
		return idPreAsignacionAE;
	}
	public void setIdPreAsignacionAE(Long idPreAsignacionAE) {
		this.idPreAsignacionAE = idPreAsignacionAE;
	}
	public String getClienteStatus() {
		return clienteStatus;
	}
	public void setClienteStatus(String clienteStatus) {
		this.clienteStatus = clienteStatus;
	}
	public String getPreAsignacionAEStatus() {
		return preAsignacionAEStatus;
	}
	public void setPreAsignacionAEStatus(String preAsignacionAEStatus) {
		this.preAsignacionAEStatus = preAsignacionAEStatus;
	}
	public String getSeguimiento() {
		return seguimiento;
	}
	public void setSeguimiento(String seguimiento) {
		this.seguimiento = seguimiento;
	}
	public String getPerfilUsuarioSeguimiento() {
		return perfilUsuarioSeguimiento;
	}
	public void setPerfilUsuarioSeguimiento(String perfilUsuarioSeguimiento) {
		this.perfilUsuarioSeguimiento = perfilUsuarioSeguimiento;
	}
	public String getMensajeSeguimiento() {
		return mensajeSeguimiento;
	}
	public void setMensajeSeguimiento(String mensajeSeguimiento) {
		this.mensajeSeguimiento = mensajeSeguimiento;
	}
	public String getNombreUsuarioSeguimiento() {
		return nombreUsuarioSeguimiento;
	}
	public void setNombreUsuarioSeguimiento(String nombreUsuarioSeguimiento) {
		this.nombreUsuarioSeguimiento = nombreUsuarioSeguimiento;
	}
	public String getAuxiliar0() {
		return auxiliar0;
	}
	public void setAuxiliar0(String auxiliar0) {
		this.auxiliar0 = auxiliar0;
	}
	public int getIdStatusAsignacion() {
		return idStatusAsignacion;
	}
	public void setIdStatusAsignacion(int idStatusAsignacion) {
		this.idStatusAsignacion = idStatusAsignacion;
	}
	public String getNombreCortoClienteProspecto() {
		return nombreCortoClienteProspecto;
	}
	public void setNombreCortoClienteProspecto(String nombreCortoClienteProspecto) {
		this.nombreCortoClienteProspecto = nombreCortoClienteProspecto;
	}
	public String getNombreCompletoClienteProspecto() {
		return nombreCompletoClienteProspecto;
	}
	public void setNombreCompletoClienteProspecto(String nombreCompletoClienteProspecto) {
		this.nombreCompletoClienteProspecto = nombreCompletoClienteProspecto;
	}
	public String getRfcClienteProspecto() {
		return rfcClienteProspecto;
	}
	public void setRfcClienteProspecto(String rfcClienteProspecto) {
		this.rfcClienteProspecto = rfcClienteProspecto;
	}
	public int getIdRegionClienteProspecto() {
		return idRegionClienteProspecto;
	}
	public void setIdRegionClienteProspecto(int idRegionClienteProspecto) {
		this.idRegionClienteProspecto = idRegionClienteProspecto;
	}
	public String getNombreRegionClienteProspecto() {
		return nombreRegionClienteProspecto;
	}
	public void setNombreRegionClienteProspecto(String nombreRegionClienteProspecto) {
		this.nombreRegionClienteProspecto = nombreRegionClienteProspecto;
	}
	public String getDireccionClienteProspecto() {
		return direccionClienteProspecto;
	}
	public void setDireccionClienteProspecto(String direccionClienteProspecto) {
		this.direccionClienteProspecto = direccionClienteProspecto;
	}
	public boolean isFlagClienteProspecto() {
		return flagClienteProspecto;
	}
	public void setFlagClienteProspecto(boolean flagClienteProspecto) {
		this.flagClienteProspecto = flagClienteProspecto;
	}
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
				+ ", verificarEntregable=" + verificarEntregable + ", guiaEntregable=" + guiaEntregable + ", fechaPago="
				+ fechaPago + ", numeroFactura=" + numeroFactura + ", createAtAsignacion=" + createAtAsignacion
				+ ", userCreateAsignacion=" + userCreateAsignacion + ", userCreateAsignacionTexto="
				+ userCreateAsignacionTexto + ", statusAsignacion=" + statusAsignacion + ", idStatusAsignacion="
				+ idStatusAsignacion + ", archivoParticipantes=" + archivoParticipantes + ", archivoParticipantesTexto="
				+ archivoParticipantesTexto + ", costoHotel=" + costoHotel + ", errorProceso=" + errorProceso
				+ ", nombreFirmaInstructor=" + nombreFirmaInstructor + ", idPreAsignacionAE=" + idPreAsignacionAE
				+ ", preAsignacionAEStatus=" + preAsignacionAEStatus + ", clienteStatus=" + clienteStatus
				+ ", auxiliar0=" + auxiliar0 + ", seguimiento=" + seguimiento + ", nombreUsuarioSeguimiento="
				+ nombreUsuarioSeguimiento + ", perfilUsuarioSeguimiento=" + perfilUsuarioSeguimiento
				+ ", mensajeSeguimiento=" + mensajeSeguimiento + ", nombreCortoClienteProspecto="
				+ nombreCortoClienteProspecto + ", nombreCompletoClienteProspecto=" + nombreCompletoClienteProspecto
				+ ", rfcClienteProspecto=" + rfcClienteProspecto + ", idRegionClienteProspecto="
				+ idRegionClienteProspecto + ", nombreRegionClienteProspecto=" + nombreRegionClienteProspecto
				+ ", direccionClienteProspecto=" + direccionClienteProspecto + ", flagClienteProspecto="
				+ flagClienteProspecto + "]";
	}
	public Long getIdPreAsignacion() {
		return idPreAsignacion;
	}
	public void setIdPreAsignacion(Long idPreAsignacion) {
		this.idPreAsignacion = idPreAsignacion;
	}
	public String getAgregarFactura() {
		return agregarFactura;
	}
	public void setAgregarFactura(String fileFactura) {
		this.agregarFactura = fileFactura;
	}
	public String getAgregarFacturaTexto() {
		return agregarFacturaTexto;
	}
	public void setAgregarFacturaTexto(String fileFacturaTexto) {
		this.agregarFacturaTexto = fileFacturaTexto;
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
