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
@Table(name = "usuarios_instructor")
public class UsuarioInstructorEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idUsuarioInstructor;
	@Column
	private String usuarioInstructorNombre;
	@Column
	private String usuarioInstructorIdAsignacion;
	@Column
	private String usuarioInstructorUserName;
	@Column
	private String usuarioInstructorPassword;
	@Column
	private LocalDateTime createAtAsignacion;
	@Column
	private String userCreateAsignacion;
	@Column
	private String statusAsignacion;
	
	public UsuarioInstructorEntity() {	}

	@Override
	public String toString() {
		return "UsuarioInstructorEntity [idUsuarioInstructor=" + idUsuarioInstructor + ", usuarioInstructorNombre="
				+ usuarioInstructorNombre + ", usuarioInstructorIdAsignacion=" + usuarioInstructorIdAsignacion
				+ ", createAtAsignacion=" + createAtAsignacion + ", userCreateAsignacion=" + userCreateAsignacion
				+ ", statusAsignacion=" + statusAsignacion + "]";
	}

	public UsuarioInstructorEntity(Long idUsuarioInstructor, String usuarioInstructorNombre,
			String usuarioInstructorIdAsignacion, LocalDateTime createAtAsignacion, String userCreateAsignacion,
			String statusAsignacion) {
		
		this.idUsuarioInstructor = idUsuarioInstructor;
		this.usuarioInstructorNombre = usuarioInstructorNombre;
		this.usuarioInstructorIdAsignacion = usuarioInstructorIdAsignacion;
		this.createAtAsignacion = createAtAsignacion;
		this.userCreateAsignacion = userCreateAsignacion;
		this.statusAsignacion = statusAsignacion;
	}

	public UsuarioInstructorEntity( String usuarioInstructorNombre,
			String usuarioInstructorIdAsignacion, LocalDateTime createAtAsignacion, String userCreateAsignacion,
			String statusAsignacion) {
		
		this.usuarioInstructorNombre = usuarioInstructorNombre;
		this.usuarioInstructorIdAsignacion = usuarioInstructorIdAsignacion;
		this.createAtAsignacion = createAtAsignacion;
		this.userCreateAsignacion = userCreateAsignacion;
		this.statusAsignacion = statusAsignacion;
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

	public LocalDateTime getCreateAtAsignacion() {
		return createAtAsignacion;
	}

	public void setCreateAtAsignacion(LocalDateTime createAtAsignacion) {
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
