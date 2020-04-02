package mx.uniprotec.inicio.entity;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Component;


@Component
public class Usuario implements Serializable {

	private Long idUsuario;
	private String username;
	private String password;
	private Boolean enabled;
	private String name;
	private String apellido;
	private String email;

	private List<Role> roles;

	private List<Modulo> modulos;
	
	public List<Modulo> getModulos() {
		return modulos;
	}

	public void setModulos(List<Modulo> modulos) {
		this.modulos = modulos;
	}

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long id) {
		this.idUsuario = id;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	private static final long serialVersionUID = 1L;


	@Override
	public String toString() {
		return "Usuario [id=" + idUsuario + ", username=" + username + ", password=" + password + ", enabled=" + enabled
				+ ", name=" + name + ", apellido=" + apellido + ", email=" + email + ", roles=" + roles + ", modulos="
				+ modulos + "]";
	}

	public Usuario(Long id, String username, String password, Boolean enabled, String name, String apellido,
			String email, List<Role> roles, List<Modulo> modulos) {
		
		this.idUsuario = id;
		this.username = username;
		this.password = password;
		this.enabled = enabled;
		this.name = name;
		this.apellido = apellido;
		this.email = email;
		this.roles = roles;
		this.modulos = modulos;
	}

	public Usuario() {	}
	
	
	
}
