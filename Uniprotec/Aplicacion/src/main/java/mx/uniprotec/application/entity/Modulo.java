package mx.uniprotec.application.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
	
	private String name;

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name="modulos_submodulos", joinColumns= @JoinColumn(name="modulo_id"),
	inverseJoinColumns=@JoinColumn(name="submodule_id"),
	uniqueConstraints= {@UniqueConstraint(columnNames= {"modulo_id", "submodule_id"})})
	private List<SubModulo> submodules;
	
//	 @OneToMany(mappedBy = "modulo", cascade = CascadeType.ALL)
//	    private Set<Modulos_SubModulos> modulos_submodulos;
//
//	    public Modulo(String name, Modulos_SubModulos... modulos_submodulos) {
//	        this.name = name;
//	        for(Modulos_SubModulos modulos_submodulo : modulos_submodulos) modulos_submodulo.setModulo(this);
//	        this.modulos_submodulos = Stream.of(modulos_submodulos).collect(Collectors.toSet());
//	    }
	    
	public Modulo() {}

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
	
	public List<SubModulo> getSubmodules() {
		return submodules;
	}

	public void setSubmodules(List<SubModulo> submodules) {
		this.submodules = submodules;
	}

//	public Set<Modulos_SubModulos> getModulos_submodulos() {
//		return modulos_submodulos;
//	}
//
//	public void setModulos_submodulos(Set<Modulos_SubModulos> modulos_submodulos) {
//		this.modulos_submodulos = modulos_submodulos;
//	}
		
	
	
}
