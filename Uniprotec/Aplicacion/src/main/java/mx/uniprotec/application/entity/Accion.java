package mx.uniprotec.application.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="acciones")
public class Accion implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Accion() {
	}	
	

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idAccion;
	@Column
	private String nombreAccion;
	@Column
	private String referenciaAccion;
	@Column
	private String iconoAccion;

	public Long getIdAccion() {
		return idAccion;
	}
	public void setIdAccion(Long idAccion) {
		this.idAccion = idAccion;
	}
	public String getNombreAccion() {
		return nombreAccion;
	}
	public void setNombreAccion(String nombreAccion) {
		this.nombreAccion = nombreAccion;
	}
	
	public String getReferenciaAccion() {
		return referenciaAccion;
	}
	public void setReferenciaAccion(String referenciaAccion) {
		this.referenciaAccion = referenciaAccion;
	}
	public String getIconoAccion() {
		return iconoAccion;
	}
	public void setIconoAccion(String iconoAccion) {
		this.iconoAccion = iconoAccion;
	}
	
	



}
