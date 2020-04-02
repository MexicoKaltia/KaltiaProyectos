package mx.uniprotec.application.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name="submodules")
public class SubModulo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idSubmodulo;

	
	@Column
	private String nombreSubmodulo;
	

	@Column
	private String referenciaSubmodulo;
	
	@Column
	private String idModulo;

	public Long getIdSubmodulo() {
		return idSubmodulo;
	}

	public void setIdSubmodulo(Long idSubmodulo) {
		this.idSubmodulo = idSubmodulo;
	}

	public String getNombreSubmodulo() {
		return nombreSubmodulo;
	}

	public void setNombreSubmodulo(String nombreSubmodulo) {
		this.nombreSubmodulo = nombreSubmodulo;
	}

	public String getReferenciaSubmodulo() {
		return referenciaSubmodulo;
	}

	public void setReferenciaSubmodulo(String referenciaSubmodulo) {
		this.referenciaSubmodulo = referenciaSubmodulo;
	}

	public String getIdModulo() {
		return idModulo;
	}

	public void setIdModulo(String idModulo) {
		this.idModulo = idModulo;
	}
	

//	 @OneToMany(mappedBy = "submodulo", cascade = CascadeType.ALL)
//	    private Set<Modulos_SubModulos> modulos_submodulos = new HashSet<>();

	    
	
}
