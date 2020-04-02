package mx.uniprotec.application.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="cursos")
public class Curso implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idCurso;
	
	@NotEmpty(message ="nombreCurso no puede estar vacio")
	@Size(min=4, max=220, message="el tamaño tiene que estar entre 4 y 220")
	@Column(nullable=false)
	private String nombreCurso;

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name="curso_instructor", joinColumns= @JoinColumn(name="curso_id"),
	inverseJoinColumns=@JoinColumn(name="instructor_id"),
	uniqueConstraints= {@UniqueConstraint(columnNames= {"curso_id", "instructor_id"})})
	private List<Instructor> instructores;
	
	
	@Column
	private String notaCurso;
	
	@Column(nullable=false)
	private Long userCreateCurso;
	
	@Column(nullable=false)
	private LocalDateTime createAtCurso;
	
	@Column(nullable=false)
	private String statusCurso;

	public Long getIdCurso() {
		return idCurso;
	}

	public void setIdCurso(Long id) {
		this.idCurso = id;
	}

	public String getNombreCurso() {
		return nombreCurso;
	}

	public void setNombreCurso(String nombreCurso) {
		this.nombreCurso = nombreCurso;
	}

	public List<Instructor> getInstructores() {
		return instructores;
	}

	public void setInstructores(List<Instructor> instructores) {
		this.instructores = instructores;
	}

	public String getNotaCurso() {
		return notaCurso;
	}

	public void setNotaCurso(String notaCurso) {
		this.notaCurso = notaCurso;
	}

	public Long getUserCreateCurso() {
		return userCreateCurso;
	}

	public void setUserCreateCurso(Long userCreateCurso) {
		this.userCreateCurso = userCreateCurso;
	}

	public LocalDateTime getCreateAtCurso() {
		return createAtCurso;
	}

	public void setCreateAtCurso(LocalDateTime createAtCurso) {
		this.createAtCurso = createAtCurso;
	}

	public String getStatusCurso() {
		return statusCurso;
	}

	public void setStatusCurso(String statusCurso) {
		this.statusCurso = statusCurso;
	}
	
	
}
