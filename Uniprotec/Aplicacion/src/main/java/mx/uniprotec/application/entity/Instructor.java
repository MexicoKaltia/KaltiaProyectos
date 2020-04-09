package mx.uniprotec.application.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;


@Entity
@Table(name="instructores")
@JsonIdentityInfo(
		  generator = ObjectIdGenerators.PropertyGenerator.class, 
		  property = "idInstructor")
public class Instructor implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5754999833823607562L;

	

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idInstructor;
	
	@NotEmpty(message ="nombreInstructor no puede estar vacio")
	@Size(min=4, max=300, message="el tamaño tiene que estar entre 4 y 300")
	@Column(nullable=false)
	private String nombreInstructor;
	
//	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "ainstructores")
//	private List<Curso> curso = new ArrayList<>();
	
//	 @ManyToOne
//	 @JoinColumn(name = "id_curso", nullable = false, updatable = false)
//	 private Curso curso;
	
//	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//	@JoinTable(name="instructores_cursos", joinColumns= @JoinColumn(name="instructores_id"),
//	inverseJoinColumns=@JoinColumn(name="cursos_id"))
////	uniqueConstraints= {@UniqueConstraint(columnNames= {"instructores_id", "cursos_id"})})
//	private List<Curso> cursos = new ArrayList<>();
	
	
	@Column(nullable=false)
	private Long regionInstructor;
	@Column
	private String notaInstructor;
	@Column(nullable=false)
	private Long userCreateInstructor;
	@Column(nullable=false)
	private LocalDateTime createAtInstructor;
	@Column(nullable=false)
	private String statusInstructor;
	

	public Instructor() {
		
	}
	
	
	
	public Instructor(Long idInstructor,
			@NotEmpty(message = "nombreInstructor no puede estar vacio") @Size(min = 4, max = 300, message = "el tamaño tiene que estar entre 4 y 300") String nombreInstructor,
			Long regionInstructor, String notaInstructor, Long userCreateInstructor, LocalDateTime createAtInstructor,
			String statusInstructor) {
		super();
		this.idInstructor = idInstructor;
		this.nombreInstructor = nombreInstructor;
		this.regionInstructor = regionInstructor;
		this.notaInstructor = notaInstructor;
		this.userCreateInstructor = userCreateInstructor;
		this.createAtInstructor = createAtInstructor;
		this.statusInstructor = statusInstructor;
	}



	public Long getIdInstructor() {
		return idInstructor;
	}
	public void setIdInstructor(Long idInstructor) {
		this.idInstructor = idInstructor;
	}
	public String getNombreInstructor() {
		return nombreInstructor;
	}
	public void setNombreInstructor(String nombreInstructor) {
		this.nombreInstructor = nombreInstructor;
	}
	public Long getRegionInstructor() {
		return regionInstructor;
	}
	public void setRegionInstructor(Long regionInstructor) {
		this.regionInstructor = regionInstructor;
	}
	public String getNotaInstructor() {
		return notaInstructor;
	}
	public void setNotaInstructor(String notaInstructor) {
		this.notaInstructor = notaInstructor;
	}
	public Long getUserCreateInstructor() {
		return userCreateInstructor;
	}
	public void setUserCreateInstructor(Long userCreateInstructor) {
		this.userCreateInstructor = userCreateInstructor;
	}
	public LocalDateTime getCreateAtInstructor() {
		return createAtInstructor;
	}
	public void setCreateAtInstructor(LocalDateTime createAtInstructor) {
		this.createAtInstructor = createAtInstructor;
	}
	public String getStatusInstructor() {
		return statusInstructor;
	}
	public void setStatusInstructor(String statusInstructor) {
		this.statusInstructor = statusInstructor;
	}
	

		
	
}
