package mx.uniprotec.inicio.entity;

import java.io.Serializable;

import org.springframework.stereotype.Component;

@Component
public class Curso implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;
	private String name;


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

	@Override
	public String toString() {
		return "Curso [id=" + id + ", name=" + name + "]";
	}

	public Curso(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Curso() {}
	

}
