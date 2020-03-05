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
@Table(name="submodulos")
public class SubModulo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	
	@Column
	private String name;

//	 @OneToMany(mappedBy = "submodulo", cascade = CascadeType.ALL)
//	    private Set<Modulos_SubModulos> modulos_submodulos = new HashSet<>();

	    public SubModulo(String name) {
	        this.name = name;
	    }
	    
	    public SubModulo() {
			// TODO Auto-generated constructor stub
		}

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

//		public Set<Modulos_SubModulos> getModulos_submodulos() {
//			return modulos_submodulos;
//		}
//
//		public void setModulos_submodulos(Set<Modulos_SubModulos> modulos_submodulos) {
//			this.modulos_submodulos = modulos_submodulos;
//		}
//	
//	
		

	
}
