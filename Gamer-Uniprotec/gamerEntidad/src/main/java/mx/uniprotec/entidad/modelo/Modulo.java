package mx.uniprotec.entidad.modelo;

import java.io.Serializable;
import java.util.List;


public class Modulo implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long idModulo;
	private String name;
	private List<Long> submodules;
	

	public Modulo() {}
   

	public Long getIdModulo() {
		return idModulo;
	}

	public void setIdModulo(Long id) {
		this.idModulo = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public List<Long> getSubmodules() {
		return submodules;
	}

	public void setSubmodules(List<Long> submodules) {
		this.submodules = submodules;
	}
	

	@Override
	public String toString() {
		return "Modulo [id=" + idModulo + ", name=" + name + ", submodules=" + submodules + "]";
	}

	public Modulo(Long id, String name, List<SubModulo> submodules) {
		super();
		this.idModulo = id;
		this.name = name;
	}

}
