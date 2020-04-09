package mx.uniprotec.inicio.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class Curso implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long idCurso;
	private String nombreCurso;
	private List<Instructor> listInstructoresCurso;
	private List<Long> listInstructorCurso;
	private String notaCurso;
	private Long userCreateCurso;
	private LocalDateTime createAtCurso;
	private String statusCurso;
	
	
	public Curso(Long id) {
		this.idCurso = id;
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





	public List<Instructor> getListInstructoresCurso() {
		return listInstructoresCurso;
	}





	public void setListInstructoresCurso(List<Instructor> listInstructoresCurso) {
		this.listInstructoresCurso = listInstructoresCurso;
	}





	public List<Long> getListInstructorCurso() {
		return listInstructorCurso;
	}





	public void setListInstructorCurso(List<Long> listInstructorCurso) {
		this.listInstructorCurso = listInstructorCurso;
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





	public Curso(Long id, String nombreCurso, List<Instructor> listInstructoresCurso, List<Long> listInstructorCurso,
			String notaCurso, Long userCreateCurso, LocalDateTime createAtCurso, String statusCurso) {
		
		this.idCurso = id;
		this.nombreCurso = nombreCurso;
		this.listInstructoresCurso = listInstructoresCurso;
		this.listInstructorCurso = listInstructorCurso;
		this.notaCurso = notaCurso;
		this.userCreateCurso = userCreateCurso;
		this.createAtCurso = createAtCurso;
		this.statusCurso = statusCurso;
	}





	@Override
	public String toString() {
		return "Curso [id=" + idCurso + ", nombreCurso=" + nombreCurso + ", listInstructoresCurso=" + listInstructoresCurso
				+ ", listInstructorCurso=" + listInstructorCurso + ", notaCurso=" + notaCurso + ", userCreateCurso="
				+ userCreateCurso + ", createAtCurso=" + createAtCurso + ", statusCurso=" + statusCurso + "]";
	}





	public Curso() {}
	

}
