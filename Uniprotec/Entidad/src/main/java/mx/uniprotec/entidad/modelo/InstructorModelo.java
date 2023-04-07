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
	private String firmaInstructorH;
	private Long usuarioInstructor;
	private Long userCreateInstructor;
	private LocalDateTime createAtInstructor;
	private String statusInstructor;
	private List<String> listFechas;
	private Long idOperacion;

	public InstructorModelo() {}

	
	
	public InstructorModelo(Long id) {
		this.idInstructor = id;
	}



	@Override
	public String toString() {
		return "InstructorModelo [idInstructor=" + idInstructor + ", nombreInstructor=" + nombreInstructor
				+ ", emailInstructor=" + emailInstructor + ", emailGmailInstructor=" + emailGmailInstructor
				+ ", regionInstructor=" + regionInstructor + ", listCursoInstructor=" + listCursoInstructor
				+ ", notaInstructor=" + notaInstructor + ", firmaInstructorH=" + firmaInstructorH
				+ ", usuarioInstructor=" + usuarioInstructor + ", userCreateInstructor=" + userCreateInstructor
				+ ", createAtInstructor=" + createAtInstructor + ", statusInstructor=" + statusInstructor
				+ ", listFechas=" + listFechas + ", idOperacion=" + idOperacion + "]";
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



	public Long getUsuarioInstructor() {
		return usuarioInstructor;
	}



	public void setUsuarioInstructor(Long usuarioInstructor) {
		this.usuarioInstructor = usuarioInstructor;
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



	public List<String> getListFechas() {
		return listFechas;
	}



	public void setListFechas(List<String> listFechas) {
		this.listFechas = listFechas;
	}





	public String getFirmaInstructorH() {
		return firmaInstructorH;
	}



	public void setFirmaInstructorH(String firmaInstructorH) {
		this.firmaInstructorH = firmaInstructorH;
	}



	public Long getIdOperacion() {
		return idOperacion;
	}



	public void setIdOperacion(Long idOperacion) {
		this.idOperacion = idOperacion;
	}


	
	
	
}
