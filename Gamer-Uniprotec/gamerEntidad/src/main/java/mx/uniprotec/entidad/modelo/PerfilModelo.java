package mx.uniprotec.entidad.modelo;

import java.io.Serializable;
import java.util.List;

public class PerfilModelo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4601521169489104090L;

	public PerfilModelo() {
		// TODO Auto-generated constructor stub
	}

	private Long idPerfil;
	private String nombrePerfil;
	private List<Modulo> modulos;

	public Long getIdPerfil() {
		return idPerfil;
	}
	public void setIdPerfil(Long idPerfil) {
		this.idPerfil = idPerfil;
	}
	public String getNombrePerfil() {
		return nombrePerfil;
	}
	public void setNombrePerfil(String nombrePerfil) {
		this.nombrePerfil = nombrePerfil;
	}
	public List<Modulo> getModulos() {
		return modulos;
	}
	public void setModulos(List<Modulo> modulos) {
		this.modulos = modulos;
	}
	
	
	
}
