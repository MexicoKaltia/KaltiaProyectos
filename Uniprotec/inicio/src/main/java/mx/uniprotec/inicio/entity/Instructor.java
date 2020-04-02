package mx.uniprotec.inicio.entity;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class Instructor implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long idInstructor;
	private String name;
	private List<Curso> curso;


	public List<Curso> getCurso() {
		return curso;
	}

	public void setCurso(List<Curso> curso) {
		this.curso = curso;
	}

	public Long getIdInstructor() {
		return idInstructor;
	}

	public void setIdInstructor(Long id) {
		this.idInstructor = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Instructor [id=" + idInstructor + ", name=" + name + ", curso=" + curso + "]";
	}

	public Instructor(Long id, String name, List<Curso> curso) {
		this.idInstructor = id;
		this.name = name;
		this.curso = curso;
	}
	
	public Instructor(Long id) {
		this.idInstructor = id;
	}


	public Instructor() {}
	
}
