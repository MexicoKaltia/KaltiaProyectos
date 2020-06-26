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
@Table(name="usuarios2")
public class Usuario2 {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idUsuario;
	@NotEmpty(message ="user no puede estar vacio")
	@Size(min=4, max=300, message="el tamaño tiene que estar entre 4 y 300")
	@Column(nullable=false)
	private String usernameUsuario;
	@NotEmpty(message ="pass no puede estar vacio")
	@Size(min=4, max=300, message="el tamaño tiene que estar entre 4 y 300")
	@Column(nullable=false)
	private String passwordUsuario;
	
	@Column(nullable=false)
	private String perfilUsuario;
	@Column(nullable=false)
	private String nameUsuario;
	@Column(nullable=false)
	private String apellidoUsuario;
	@Column(nullable=false)
	private String emailUsuario;
	@Column
	private String notaUsuario;
	@Column(nullable=false)
	private Long userCreateUsuario;
	@Column(nullable=false)
	private LocalDateTime createAtUsuario;
	@Column(nullable=false)
	private String statusUsuario;
	
	public Usuario2() {}
	
	@Override
	public String toString() {
		return "UsuarioModelo [idUsuario=" + idUsuario + ", usernameUsuario=" + usernameUsuario + ", passwordUsuario="
				+ passwordUsuario + ", perfilUsuario=" + perfilUsuario + ", nameUsuario=" + nameUsuario
				+ ", apellidoUsuario=" + apellidoUsuario + ", emailUsuario=" + emailUsuario + ", userCreateUsuario="
				+ userCreateUsuario + ", createAtUsuario=" + createAtUsuario + ", statusUsuario=" + statusUsuario + "]";
	}
	
	public Usuario2(Long idUsuario, String usernameUsuario, String passwordUsuario, String perfilUsuario,
			String nameUsuario, String apellidoUsuario, String emailUsuario, Long userCreateUsuario,
			LocalDateTime createAtUsuario, String statusUsuario) {
		super();
		this.idUsuario = idUsuario;
		this.usernameUsuario = usernameUsuario;
		this.passwordUsuario = passwordUsuario;
		this.perfilUsuario = perfilUsuario;
		this.nameUsuario = nameUsuario;
		this.apellidoUsuario = apellidoUsuario;
		this.emailUsuario = emailUsuario;
		this.userCreateUsuario = userCreateUsuario;
		this.createAtUsuario = createAtUsuario;
		this.statusUsuario = statusUsuario;
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

	public String getNameUsuario() {
		return nameUsuario;
	}

	public void setNameUsuario(String nameUsuario) {
		this.nameUsuario = nameUsuario;
	}

	public String getApellidoUsuario() {
		return apellidoUsuario;
	}

	public void setApellidoUsuario(String apellidoUsuario) {
		this.apellidoUsuario = apellidoUsuario;
	}

	public String getEmailUsuario() {
		return emailUsuario;
	}

	public void setEmailUsuario(String emailUsuario) {
		this.emailUsuario = emailUsuario;
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

	public String getNotaUsuario() {
		return notaUsuario;
	}

	public void setNotaUsuario(String notaUsuario) {
		this.notaUsuario = notaUsuario;
	}
	
	
	

}
