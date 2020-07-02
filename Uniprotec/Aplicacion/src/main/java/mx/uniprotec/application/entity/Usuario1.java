package mx.uniprotec.application.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name="usuariosA")
public class Usuario1 {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idUsuario;
	@NotEmpty(message ="user no puede estar vacio")
	@Size(min=4, max=300, message="el tamaño tiene que estar entre 4 y 300")
	@Column(nullable=false, unique = true)
	private String usernameUsuario;
	@NotEmpty(message ="pass no puede estar vacio")
	@Size(min=4, max=300, message="el tamaño tiene que estar entre 4 y 300")
	@Column(nullable=false)
	private String passwordUsuario;
	
	@Column(nullable=false)
	private String perfilUsuario;
	@Column(nullable=false)
	private String nombreUsuario;
	
	@Column(nullable=false, unique = true)
	private String emailUsuario;
	@Column
	private String notaUsuario;
	@Column(nullable=false)
	private Long userCreateUsuario;
	@Column(nullable=false)
	private LocalDateTime createAtUsuario;
	@Column(nullable=false)
	private String statusUsuario;
	
	public Usuario1() {}

	@Override
	public String toString() {
		return "Usuario2 [idUsuario=" + idUsuario + ", usernameUsuario=" + usernameUsuario + ", passwordUsuario="
				+ passwordUsuario + ", perfilUsuario=" + perfilUsuario + ", nombreUsuario=" + nombreUsuario
				+ ", emailUsuario=" + emailUsuario + ", notaUsuario=" + notaUsuario + ", userCreateUsuario="
				+ userCreateUsuario + ", createAtUsuario=" + createAtUsuario + ", statusUsuario=" + statusUsuario + "]";
	}

	public Usuario1(Long idUsuario,
			@NotEmpty(message = "user no puede estar vacio") @Size(min = 4, max = 300, message = "el tamaño tiene que estar entre 4 y 300") String usernameUsuario,
			@NotEmpty(message = "pass no puede estar vacio") @Size(min = 4, max = 300, message = "el tamaño tiene que estar entre 4 y 300") String passwordUsuario,
			String perfilUsuario, String nombreUsuario, String emailUsuario, String notaUsuario, Long userCreateUsuario,
			LocalDateTime createAtUsuario, String statusUsuario) {

		this.idUsuario = idUsuario;
		this.usernameUsuario = usernameUsuario;
		this.passwordUsuario = passwordUsuario;
		this.perfilUsuario = perfilUsuario;
		this.nombreUsuario = nombreUsuario;
		this.emailUsuario = emailUsuario;
		this.notaUsuario = notaUsuario;
		this.userCreateUsuario = userCreateUsuario;
		this.createAtUsuario = createAtUsuario;
		this.statusUsuario = statusUsuario;
	}

	public Usuario1(Long idUsuario) {
		 this.idUsuario = idUsuario;
	}

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getUsernameUsuario() {
		return usernameUsuario;
	}

	public void setUsernameUsuario(String usernameUsuario) {
		this.usernameUsuario = usernameUsuario;
	}

	public String getPasswordUsuario() {
		return passwordUsuario;
	}

	public void setPasswordUsuario(String passwordUsuario) {
		this.passwordUsuario = passwordUsuario;
	}

	public String getPerfilUsuario() {
		return perfilUsuario;
	}

	public void setPerfilUsuario(String perfilUsuario) {
		this.perfilUsuario = perfilUsuario;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getEmailUsuario() {
		return emailUsuario;
	}

	public void setEmailUsuario(String emailUsuario) {
		this.emailUsuario = emailUsuario;
	}

	public String getNotaUsuario() {
		return notaUsuario;
	}

	public void setNotaUsuario(String notaUsuario) {
		this.notaUsuario = notaUsuario;
	}

	public Long getUserCreateUsuario() {
		return userCreateUsuario;
	}

	public void setUserCreateUsuario(Long userCreateUsuario) {
		this.userCreateUsuario = userCreateUsuario;
	}

	public LocalDateTime getCreateAtUsuario() {
		return createAtUsuario;
	}

	public void setCreateAtUsuario(LocalDateTime createAtUsuario) {
		this.createAtUsuario = createAtUsuario;
	}

	public String getStatusUsuario() {
		return statusUsuario;
	}

	public void setStatusUsuario(String statusUsuario) {
		this.statusUsuario = statusUsuario;
	}
	
		
	

}
