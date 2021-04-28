package mx.uniprotec.gamerBack.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "usuarios_administrador")
public class UsuarioAdministradorEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idUsuarioAdministrador;
	@Column
	private String usuarioAdministradorNombre;
	@Column
	private String usuarioAdministradorIdAsignacion;
	@Column
	private LocalDateTime createAt;
	@Column
	private String userCreate;
	@Column
	private String status;
	
	public UsuarioAdministradorEntity() {}

	

	public UsuarioAdministradorEntity(String usuarioAdministradorNombre, String usuarioAdministradorIdAsignacion,
			LocalDateTime createAt, String userCreate, String status) {
		super();
		this.usuarioAdministradorNombre = usuarioAdministradorNombre;
		this.usuarioAdministradorIdAsignacion = usuarioAdministradorIdAsignacion;
		this.createAt = createAt;
		this.userCreate = userCreate;
		this.status = status;
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


			

}
