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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
	@Column(nullable=false)
	private String emailInstructor;
	@Column(nullable=false)
	private String emailGmailInstructor;
	
	@Column(nullable=false)
	private String cursosInstructor;
	
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "instructores")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) 
	private List<Curso> curso = new ArrayList<>();

	@OneToOne(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
	private Region regionInstructor;
	@OneToOne(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
	private Usuario usuarioInstructor;
	@Column
	private String notaInstructor;
	@Column(nullable=false)
	private Long userCreateInstructor;
	@Column(nullable=false)
	private LocalDateTime createAtInstructor;
	@Column(nullable=false)
	private String statusInstructor;
	@Size(max=3000)
	@Column
	private String listFechas;
	@Column
	private String firmaInstructor;
	

	public Instructor() {		}

	
	public Instructor(Long idInstructor) {
		this.idInstructor = idInstructor;
	}


	
	
	public Instructor(Long idInstructor,
			@NotEmpty(message = "nombreInstructor no puede estar vacio") @Size(min = 4, max = 300, message = "el tamaño tiene que estar entre 4 y 300") String nombreInstructor,
			String emailInstructor, String emailGmailInstructor, String cursosInstructor, List<Curso> curso,
			Region regionInstructor, String notaInstructor, Long userCreateInstructor, LocalDateTime createAtInstructor,
			String statusInstructor) {
		super();
		this.idInstructor = idInstructor;
		this.nombreInstructor = nombreInstructor;
		this.emailInstructor = emailInstructor;
		this.emailGmailInstructor = emailGmailInstructor;
		this.cursosInstructor = cursosInstructor;
		this.curso = curso;
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


	public Region getRegionInstructor() {
		return regionInstructor;
	}


	public void setRegionInstructor(Region regionInstructor) {
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


	public String getEmailInstructor() {
		return emailInstructor;
	}


	public void setEmailInstructor(String emailInstructor) {
		this.emailInstructor = emailInstructor;
	}


//	public List<Curso> getCurso() {
//		return curso;
//	}
//
//
//	public void setCurso(List<Curso> curso) {
//		this.curso = curso;
//	}


	public String getCursosInstructor() {
		return cursosInstructor;
	}


	public void setCursosInstructor(String cursosInstructor) {
		this.cursosInstructor = cursosInstructor;
	}
	

	public String getEmailGmailInstructor() {
		return emailGmailInstructor;
	}


	public void setEmailGmailInstructor(String emailGmailInstructor) {
		this.emailGmailInstructor = emailGmailInstructor;
	}


	public String getListFechas() {
		return listFechas;
	}


	public void setListFechas(String listFechas) {
		this.listFechas = listFechas;
	}
	
	public Usuario getUsuarioInstructor() {
		return usuarioInstructor;
	}


	public void setUsuarioInstructor(Usuario usuarioInstructor) {
		this.usuarioInstructor = usuarioInstructor;
	}







	@Override
	public String toString() {
		return "Instructor [idInstructor=" + idInstructor + ", nombreInstructor=" + nombreInstructor
				+ ", emailInstructor=" + emailInstructor + ", emailGmailInstructor=" + emailGmailInstructor
				+ ", cursosInstructor=" + cursosInstructor + ", curso=" + curso + ", regionInstructor="
				+ regionInstructor + ", notaInstructor=" + notaInstructor + ", userCreateInstructor="
				+ userCreateInstructor + ", createAtInstructor=" + createAtInstructor + ", statusInstructor="
				+ statusInstructor + "]";
	}


	public String getFirmaInstructor() {
		return firmaInstructor;
	}


	public void setFirmaInstructor(String firmaInstructor) {
		this.firmaInstructor = firmaInstructor;
	}




	
	
	
	
}
