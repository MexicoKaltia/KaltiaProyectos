package mx.uniprotec.application.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "regiones")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Region implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7314022896395690732L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idRegion;

	@Column
	private String nombreRegion;

	public String getNombreRegion() {
		return nombreRegion;
	}

	public void setNombreRegion(String nombreRegion) {
		this.nombreRegion = nombreRegion;
	}

	public Long getIdRegion() {
		return idRegion;
	}

	public void setIdRegion(Long id) {
		this.idRegion = id;
	}

	

}
