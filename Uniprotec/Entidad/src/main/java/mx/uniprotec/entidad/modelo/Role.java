package mx.uniprotec.entidad.modelo;

import java.io.Serializable;




public class Role implements Serializable{

	private Long idRole;
	private String name;
	
	public Long getIdRole() {
		return idRole;
	}

	public void setIdRole(Long id) {
		this.idRole = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	private static final long serialVersionUID = 1L;

	@Override
	public String toString() {
		return "Role [id=" + idRole + ", name=" + name + "]";
	}

	public Role(Long id, String name) {
		super();
		this.idRole = id;
		this.name = name;
	}

	public Role() {
	
	}
	
	
}
