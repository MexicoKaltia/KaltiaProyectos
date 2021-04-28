package mx.uniprotec.gamerBack.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "usuarios")
public class Usuario implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(unique = true, length = 40)
	private String username;
	@Column(length = 60)
	private String password;
	@Column
	private Boolean enabled;
	@Column
	private String nombre;
	@Column
	private LocalDateTime createAt;
	@Column
	private String userCreate;
	@Column
	private String status;

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinTable(name="usuarios_roles", joinColumns= @JoinColumn(name="usuario_id"),
	inverseJoinColumns=@JoinColumn(name="role_id"),
	uniqueConstraints= {@UniqueConstraint(columnNames= {"usuario_id", "role_id"})})
	private List<Role> roles;
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name="idUsuarioAudiencia")
	private UsuarioAudienciaEntity usuarioAudiencia;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name="idUsuarioInstructor")
	private UsuarioInstructorEntity usuarioInstructor;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name="idUsuarioAdministrador")
	private UsuarioAdministradorEntity usuarioAdministrador;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
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
	
	public UsuarioInstructorEntity getUsuarioInstructor() {
		return usuarioInstructor;
	}

	public void setUsuarioInstructor(UsuarioInstructorEntity usuarioInstructor) {
		this.usuarioInstructor = usuarioInstructor;
	}

	public UsuarioAdministradorEntity getUsuarioAdministrador() {
		return usuarioAdministrador;
	}

	public void setUsuarioAdministrador(UsuarioAdministradorEntity usuarioAdministrador) {
		this.usuarioAdministrador = usuarioAdministrador;
	}

	public UsuarioAudienciaEntity getUsuarioAudiencia() {
		return usuarioAudiencia;
	}

	public void setUsuarioAudiencia(UsuarioAudienciaEntity usuarioAudiencia) {
		this.usuarioAudiencia = usuarioAudiencia;
	}




	@Override
	public String toString() {
		return "Usuario [id=" + id + ", username=" + username + ", password=" + password + ", enabled=" + enabled
				+ ", nombre=" + nombre + ", createAt=" + createAt + ", userCreate=" + userCreate + ", status=" + status
				+ ", roles=" + roles + ", usuarioAudiencia=" + usuarioAudiencia + ", usuarioInstructor="
				+ usuarioInstructor + ", usuarioAdministrador=" + usuarioAdministrador + "]";
	}




	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
}
