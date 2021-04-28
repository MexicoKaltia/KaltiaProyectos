package mx.uniprotec.entidad.modelo;

import java.io.Serializable;
import java.time.LocalDateTime;


public class UsuarioAdministrador implements Serializable {

	/**
	 * 
	 */
private static final long serialVersionUID = 1L;
	
	private Long idUsuarioAdministrador;
	private String usuarioAdministradorNombre;
	private String usuarioAdministradorUserName;
	private String usuarioAdministradorPassword;
	private String usuarioAdministradorIdAsignacion;
	private LocalDateTime createAt;
	private String userCreate;
	private String status;
	
	public UsuarioAdministrador() {}
	
	
	

	
	public UsuarioAdministrador(String usuarioAdministradorNombre, String usuarioAdministradorIdAsignacion,
			LocalDateTime createAt, String userCreate, String status) {
		super();
		this.usuarioAdministradorNombre = usuarioAdministradorNombre;
		this.usuarioAdministradorIdAsignacion = usuarioAdministradorIdAsignacion;
		this.createAt = createAt;
		this.userCreate = userCreate;
		this.status = status;
	}





	@Override
	public String toString() {
		return "UsuarioAdministrador [idUsuarioAdministrador=" + idUsuarioAdministrador
				+ ", usuarioAdministradorNombre=" + usuarioAdministradorNombre + ", usuarioAdministradorUserName="
				+ usuarioAdministradorUserName + ", usuarioAdministradorPassword=" + usuarioAdministradorPassword
				+ ", usuarioAdministradorIdAsignacion=" + usuarioAdministradorIdAsignacion + ", createAt=" + createAt
				+ ", userCreate=" + userCreate + ", status=" + status + "]";
	}





	public Long getIdUsuarioAdministrador() {
		return idUsuarioAdministrador;
	}

	public void setIdUsuarioAdministrador(Long idUsuarioAdministrador) {
		this.idUsuarioAdministrador = idUsuarioAdministrador;
	}

	public String getUsuarioAdministradorNombre() {
		return usuarioAdministradorNombre;
	}

	public void setUsuarioAdministradorNombre(String usuarioAdministradorNombre) {
		this.usuarioAdministradorNombre = usuarioAdministradorNombre;
	}

	public String getUsuarioAdministradorIdAsignacion() {
		return usuarioAdministradorIdAsignacion;
	}

	public void setUsuarioAdministradorIdAsignacion(String usuarioAdministradorIdAsignacion) {
		this.usuarioAdministradorIdAsignacion = usuarioAdministradorIdAsignacion;
	}

	public LocalDateTime getCreateAt() {
		return createAt;
	}

	public void setCreateAt(LocalDateTime createAtAsignacion) {
		this.createAt = createAtAsignacion;
	}

	public String getUserCreate() {
		return userCreate;
	}

	public void setUserCreate(String userCreateAsignacion) {
		this.userCreate = userCreateAsignacion;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String statusAsignacion) {
		this.status = statusAsignacion;
	}

	public String getUsuarioAdministradorUserName() {
		return usuarioAdministradorUserName;
	}

	public void setUsuarioAdministradorUserName(String usuarioAdministradorUserName) {
		this.usuarioAdministradorUserName = usuarioAdministradorUserName;
	}

	public String getUsuarioAdministradorPassword() {
		return usuarioAdministradorPassword;
	}

	public void setUsuarioAdministradorPassword(String usuarioAdministradorPassword) {
		this.usuarioAdministradorPassword = usuarioAdministradorPassword;
	}

		
	
}
