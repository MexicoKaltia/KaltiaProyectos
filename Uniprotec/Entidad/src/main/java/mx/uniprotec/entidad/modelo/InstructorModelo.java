package mx.uniprotec.entidad.modelo;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;


public class InstructorModelo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long idInstructor;
	private String nombreInstructor;
	private String emailInstructor;
	private String emailGmailInstructor;
	private Long regionInstructor;
	private List<Long> listCursoInstructor;
	private String notaInstructor;
	private Long userCreateInstructor;
	private LocalDateTime createAtInstructor;
	private String statusInstructor;
	

	public InstructorModelo() {}

	
	
	public InstructorModelo(Long id) {
		this.idInstructor = id;
	}



	@Override
	public String toString() {
		return "InstructorModelo [idInstructor=" + idInstructor + ", nombreInstructor=" + nombreInstructor
				+ ", emailInstructor=" + emailInstructor + ", emailGmailInstructor=" + emailGmailInstructor
				+ ", regionInstructor=" + regionInstructor + ", listCursoInstructor=" + listCursoInstructor
				+ ", notaInstructor=" + notaInstructor + ", userCreateInstructor=" + userCreateInstructor
				+ ", createAtInstructor=" + createAtInstructor + ", statusInstructor=" + statusInstructor + "]";
	}



	public InstructorModelo(Long idInstructor, String nombreInstructor, String emailInstructor,
			String emailGmailInstructor, Long regionInstructor, List<Long> listCursoInstructor, String notaInstructor,
			Long userCreateInstructor, LocalDateTime createAtInstructor, String statusInstructor) {
		super();
		this.idInstructor = idInstructor;
		this.nombreInstructor = nombreInstructor;
		this.emailInstructor = emailInstructor;
		this.emailGmailInstructor = emailGmailInstructor;
		this.regionInstructor = regionInstructor;
		this.listCursoInstructor = listCursoInstructor;
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



	public String getEmailInstructor() {
		return emailInstructor;
	}



	public void setEmailInstructor(String emailInstructor) {
		this.emailInstructor = emailInstructor;
	}



	public String getEmailGmailInstructor() {
		return emailGmailInstructor;
	}



	public void setEmailGmailInstructor(String emailGmailInstructor) {
		this.emailGmailInstructor = emailGmailInstructor;
	}



	public Long getRegionInstructor() {
		return regionInstructor;
	}



	public void setRegionInstructor(Long regionInstructor) {
		this.regionInstructor = regionInstructor;
	}



	public List<Long> getListCursoInstructor() {
		return listCursoInstructor;
	}



	public void setListCursoInstructor(List<Long> listCursoInstructor) {
		this.listCursoInstructor = listCursoInstructor;
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
