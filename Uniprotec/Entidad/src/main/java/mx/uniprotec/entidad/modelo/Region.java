package mx.uniprotec.entidad.modelo;

import java.io.Serializable;



public class Region implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Long idRegion;
	private String nombreRegion;

	public Long getIdRegion() {
		return idRegion;
	}

	public void setIdRegion(Long id) {
		this.idRegion = id;
	}
	
	public String getNombreRegion() {
		return nombreRegion;
	}

	public void setNombreRegion(String nombreRegion) {
		this.nombreRegion = nombreRegion;
	}


	

	

	@Override
	public String toString() {
		return "Region [id=" + idRegion + ", name=" + nombreRegion + "]";
	}

	public Region(Long id, String name) {
		this.idRegion = id;
		this.nombreRegion = name;
	}
	
	public Region(Long id) {
		this.idRegion = id;
	}
	
	public Region() {}

	
}
