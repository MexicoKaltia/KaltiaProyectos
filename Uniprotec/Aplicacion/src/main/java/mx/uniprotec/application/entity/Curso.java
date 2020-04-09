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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name="cursos")
@JsonIdentityInfo(
		  generator = ObjectIdGenerators.PropertyGenerator.class, 
		  property = "idCurso")
public class Curso implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4150838735898302842L;

	

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idCurso;
	
	@NotEmpty(message ="nombreCurso no puede estar vacio")
	@Size(min=4, max=220, message="el tamaño tiene que estar entre 4 y 220")
	@Column(nullable=false)
	private String nombreCurso;

//	@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
//	@JoinTable(name="cursos_instructores", joinColumns= @JoinColumn(name="fk_curso" ),
//	inverseJoinColumns=@JoinColumn(name="fk_instructor" ))
////	uniqueConstraints= {@UniqueConstraint(columnNames= {"fk_curso", "fk_instructor"})})
//	private List<Instructor> ainstructores = new ArrayList<>();
	
//	 @OneToMany(cascade = CascadeType.ALL, mappedBy = "cursos")
//	 private List<Instructor> ainstructores = new ArrayList<>();
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name="cursos_instructores", joinColumns= @JoinColumn(name="curso_id"),
	inverseJoinColumns=@JoinColumn(name="instructor_id"))
//	uniqueConstraints= {@UniqueConstraint(columnNames= {"curso_id", "instructor_id"})})
	private List<Instructor> instructores = new ArrayList<>();
	 
	public void addInstructor(Instructor instructor){
	        if(this.instructores == null){
	            this.instructores = new ArrayList<>();
	        }
	        
	        this.instructores.add(instructor);
	    }

	@Column
	private String notaCurso;
	
	@Column(nullable=false)
	private Long userCreateCurso;
	
	@Column(nullable=false)
	private LocalDateTime createAtCurso;
	
	@Column(nullable=false)
	private String statusCurso;
	
	public Curso() {
		
	}

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

	@Override
	public String toString() {
		return "Curso [idCurso=" + idCurso + ", nombreCurso=" + nombreCurso + ", instructores=" + instructores
				+ ", notaCurso=" + notaCurso + ", userCreateCurso=" + userCreateCurso + ", createAtCurso="
				+ createAtCurso + ", statusCurso=" + statusCurso + "]";
	}

	public Curso(Long idCurso,
			@NotEmpty(message = "nombreCurso no puede estar vacio") @Size(min = 4, max = 220, message = "el tamaño tiene que estar entre 4 y 220") String nombreCurso,
			List<Instructor> instructores, String notaCurso, Long userCreateCurso, LocalDateTime createAtCurso,
			String statusCurso) {
		super();
		this.idCurso = idCurso;
		this.nombreCurso = nombreCurso;
		this.instructores = instructores;
		this.notaCurso = notaCurso;
		this.userCreateCurso = userCreateCurso;
		this.createAtCurso = createAtCurso;
		this.statusCurso = statusCurso;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((createAtCurso == null) ? 0 : createAtCurso.hashCode());
		result = prime * result + ((idCurso == null) ? 0 : idCurso.hashCode());
		result = prime * result + ((instructores == null) ? 0 : instructores.hashCode());
		result = prime * result + ((nombreCurso == null) ? 0 : nombreCurso.hashCode());
		result = prime * result + ((notaCurso == null) ? 0 : notaCurso.hashCode());
		result = prime * result + ((statusCurso == null) ? 0 : statusCurso.hashCode());
		result = prime * result + ((userCreateCurso == null) ? 0 : userCreateCurso.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Curso other = (Curso) obj;
		if (createAtCurso == null) {
			if (other.createAtCurso != null)
				return false;
		} else if (!createAtCurso.equals(other.createAtCurso))
			return false;
		if (idCurso == null) {
			if (other.idCurso != null)
				return false;
		} else if (!idCurso.equals(other.idCurso))
			return false;
		if (instructores == null) {
			if (other.instructores != null)
				return false;
		} else if (!instructores.equals(other.instructores))
			return false;
		if (nombreCurso == null) {
			if (other.nombreCurso != null)
				return false;
		} else if (!nombreCurso.equals(other.nombreCurso))
			return false;
		if (notaCurso == null) {
			if (other.notaCurso != null)
				return false;
		} else if (!notaCurso.equals(other.notaCurso))
			return false;
		if (statusCurso == null) {
			if (other.statusCurso != null)
				return false;
		} else if (!statusCurso.equals(other.statusCurso))
			return false;
		if (userCreateCurso == null) {
			if (other.userCreateCurso != null)
				return false;
		} else if (!userCreateCurso.equals(other.userCreateCurso))
			return false;
		return true;
	}
	
	
	
	
}
