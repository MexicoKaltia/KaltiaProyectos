package mx.uniprotec.inicio.entity;

import java.io.Serializable;
import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class Cliente implements Serializable {

	private Long id;
	private String name;
	private String apellido;
	private String email;
	private Date createAt;
	private String foto;
	private Region region;
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public Region getRegion() {
		return region;
	}

	public void setRegion(Region region) {
		this.region = region;
	}

	private static final long serialVersionUID = 1L;


	@Override
	public String toString() {
		return "Cliente [id=" + id + ", name=" + name + ", apellido=" + apellido + ", email=" + email + ", createAt="
				+ createAt + ", foto=" + foto + ", region=" + region + "]";
	}

	public Cliente(Long id, String name, String apellido, String email, Date createAt, String foto, Region region) {
		super();
		this.id = id;
		this.name = name;
		this.apellido = apellido;
		this.email = email;
		this.createAt = createAt;
		this.foto = foto;
		this.region = region;
	}
	
	public Cliente() {}
	
}
