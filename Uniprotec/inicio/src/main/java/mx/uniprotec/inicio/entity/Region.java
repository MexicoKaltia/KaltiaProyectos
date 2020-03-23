package mx.uniprotec.inicio.entity;

import java.io.Serializable;

import org.springframework.stereotype.Component;


@Component
public class Region implements Serializable {

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

	private static final long serialVersionUID = 1L;

	@Override
	public String toString() {
		return "Region [id=" + id + ", name=" + name + "]";
	}

	public Region(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	public Region() {}

}
