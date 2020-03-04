package mx.uniprotec.application.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.mapping.Array;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="modulos")
public class Modulo implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

//	@OneToMany( cascade = CascadeType.ALL)
//	@JoinTable(name="modulos_submodulos", joinColumns= @JoinColumn(name="modulo_id"))
//	inverseJoinColumns=@JoinColumn(name="submodulo_id"),
//	uniqueConstraints= {@UniqueConstraint(columnNames= {"modulo_id", "submodulo_id"})})
//	private List<SubModulo> submodulo;
//	
//	
//	public List<SubModulo> getSubModulo() {
//		return submodulo;
//	}
//
//	public void setSubModulo(List<SubModulo> submodulo) {
//		this.submodulo = submodulo;
//	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
	
	
}
