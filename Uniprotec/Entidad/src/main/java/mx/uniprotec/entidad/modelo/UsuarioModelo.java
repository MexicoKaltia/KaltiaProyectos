package mx.uniprotec.entidad.modelo;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;



public class UsuarioModelo implements Serializable {

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8299641142506231071L;
	
	
	private Long idUsuario;
	private String usernameUsuario;
	private String passwordUsuario;
	private String perfilUsuario;
	private String nombreUsuario;
	
	private String emailUsuario;
	private String notaUsuario;
	private Long userCreateUsuario;
	private LocalDateTime createAtUsuario;
	private String statusUsuario;
	
	public UsuarioModelo() {}

	@Override
	public String toString() {
		return "UsuarioModelo [idUsuario=" + idUsuario + ", usernameUsuario=" + usernameUsuario + ", passwordUsuario="
				+ passwordUsuario + ", perfilUsuario=" + perfilUsuario + ", nombreUsuario=" + nombreUsuario
				+ ", emailUsuario=" + emailUsuario + ", notaUsuario=" + notaUsuario + ", userCreateUsuario="
				+ userCreateUsuario + ", createAtUsuario=" + createAtUsuario + ", statusUsuario=" + statusUsuario + "]";
	}
	
	

	public UsuarioModelo(Long idUsuario, String passwordUsuario, String nombreUsuario, String emailUsuario,
			String notaUsuario, String perfilUsuario) {
		super();
		this.idUsuario = idUsuario;
		this.passwordUsuario = passwordUsuario;
		this.nombreUsuario = nombreUsuario;
		this.emailUsuario = emailUsuario;
		this.notaUsuario = notaUsuario;
		this.perfilUsuario = perfilUsuario;
	}

	public UsuarioModelo(Long idUsuario, String usernameUsuario, String passwordUsuario, String perfilUsuario,
			String nombreUsuario, String emailUsuario, String notaUsuario, Long userCreateUsuario,
			LocalDateTime createAtUsuario, String statusUsuario) {
		super();
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
