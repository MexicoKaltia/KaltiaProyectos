package mx.uniprotec.entidad.modelo;

import java.time.LocalDateTime;

public class UsuarioInstructor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long idUsuarioInstructor;
	private String usuarioInstructorNombre;
	private String usuarioInstructorIdAsignacion;
	private String usuarioInstructorUserName;
	private String usuarioInstructorPassword;
	private LocalDateTime createAt;
	private String userCreate;
	private String status;
	
	public UsuarioInstructor() {	}

	@Override
	public String toString() {
		return "UsuarioInstructorEntity [idUsuarioInstructor=" + idUsuarioInstructor + ", usuarioInstructorNombre="
				+ usuarioInstructorNombre + ", usuarioInstructorIdAsignacion=" + usuarioInstructorIdAsignacion
				+ ", createAtAsignacion=" + createAt + ", userCreateAsignacion=" + userCreate
				+ ", statusAsignacion=" + status + "]";
	}

	public UsuarioInstructor(Long idUsuarioInstructor, String usuarioInstructorNombre,
			String usuarioInstructorIdAsignacion, LocalDateTime createAt, String userCreate,
			String status) {
		super();
		this.idUsuarioInstructor = idUsuarioInstructor;
		this.usuarioInstructorNombre = usuarioInstructorNombre;
		this.usuarioInstructorIdAsignacion = usuarioInstructorIdAsignacion;
		this.createAt = createAt;
		this.userCreate = userCreate;
		this.status = status;
	}

	public Long getIdUsuarioInstructor() {
		return idUsuarioInstructor;
	}

	public void setIdUsuarioInstructor(Long idUsuarioInstructor) {
		this.idUsuarioInstructor = idUsuarioInstructor;
	}

	public String getUsuarioInstructorNombre() {
		return usuarioInstructorNombre;
	}

	public void setUsuarioInstructorNombre(String usuarioInstructorNombre) {
		this.usuarioInstructorNombre = usuarioInstructorNombre;
	}

	public String getUsuarioInstructorIdAsignacion() {
		return usuarioInstructorIdAsignacion;
	}

	public void setUsuarioInstructorIdAsignacion(String usuarioInstructorIdAsignacion) {
		this.usuarioInstructorIdAsignacion = usuarioInstructorIdAsignacion;
	}

	public LocalDateTime getCreateAt() {
		return createAt;
	}

	public void setCreateAt(LocalDateTime createAt) {
		this.createAt = createAt;
	}

	public String getUserCreate() {
		return userCreate;
	}

	public void setUserCreate(String userCreate) {
		this.userCreate = userCreate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUsuarioInstructorUserName() {
		return usuarioInstructorUserName;
	}

	public void setUsuarioInstructorUserName(String usuarioInstructorUserName) {
		this.usuarioInstructorUserName = usuarioInstructorUserName;
	}
	public String getUsuarioInstructorPassword() {
		return usuarioInstructorPassword;
	}

	public void setUsuarioInstructorPassword(String usuarioInstructorPassword) {
		this.usuarioInstructorPassword = usuarioInstructorPassword;
	}

	
	
		
	

}
