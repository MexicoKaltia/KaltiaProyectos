package mx.uniprotec.application.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="roles")
public class Role implements Serializable{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique=true, length=120)
	private String name;
	
//	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//	@JoinTable(name="roles_modulos", joinColumns= @JoinColumn(name="rol_id"),
//	inverseJoinColumns=@JoinColumn(name="modulo_id"),
//	uniqueConstraints= {@UniqueConstraint(columnNames= {"rol_id", "modulo_id"})})
//	private List<Modulo> modulos;
	
	
	
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

	
	public Role(Long id, String name) {
		this.id = id;
		this.name = name;
	}

	public Role() {}

	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String toString() {
		return "Role [id=" + id + ", name=" + name + "]";
	}
}
