package mx.uniprotec.inicio.entity;

import java.io.Serializable;

import org.springframework.stereotype.Component;


@Component
public class Region implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Long idRegion;
	private String name;

	public Long getIdRegion() {
		return idRegion;
	}

	public void setIdRegion(Long id) {
		this.idRegion = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	

	@Override
	public String toString() {
		return "Region [id=" + idRegion + ", name=" + name + "]";
	}

	public Region(Long id, String name) {
		this.idRegion = id;
		this.name = name;
	}
	
	public Region(Long id) {
		this.idRegion = id;
	}
	
	public Region() {}

}
