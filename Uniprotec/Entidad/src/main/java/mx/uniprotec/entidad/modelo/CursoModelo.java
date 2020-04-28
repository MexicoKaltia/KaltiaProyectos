package mx.uniprotec.entidad.modelo;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;


public class CursoModelo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long idCurso;
	private String nombreCurso;
	private List<Long> listInstructores;
	private String notaCurso;
	private Long userCreateCurso;
	private LocalDateTime createAtCurso;
	private String statusCurso;
	
	
	
	public CursoModelo() {}



	@Override
	public String toString() {
		return "Curso [idCurso=" + idCurso + ", nombreCurso=" + nombreCurso + ", listInstructores=" + listInstructores
				+ ", notaCurso=" + notaCurso + ", userCreateCurso=" + userCreateCurso + ", createAtCurso="
				+ createAtCurso + ", statusCurso=" + statusCurso + "]";
	}



	public CursoModelo(Long idCurso, String nombreCurso, List<Long> listInstructores, String notaCurso, Long userCreateCurso,
			LocalDateTime createAtCurso, String statusCurso) {
	
		this.idCurso = idCurso;
		this.nombreCurso = nombreCurso;
		this.listInstructores = listInstructores;
		this.notaCurso = notaCurso;
		this.userCreateCurso = userCreateCurso;
		this.createAtCurso = createAtCurso;
		this.statusCurso = statusCurso;
	}
	
	public CursoModelo(Long idCurso) {
	
		this.idCurso = idCurso;
		
	}



	public Long getIdCurso() {
		return idCurso;
	}



	public void setIdCurso(Long idCurso) {
		this.idCurso = idCurso;
	}



	public String getNombreCurso() {
		return nombreCurso;
	}



	public void setNombreCurso(String nombreCurso) {
		this.nombreCurso = nombreCurso;
	}



	public List<Long> getListInstructores() {
		return listInstructores;
	}



	public void setListInstructores(List<Long> listInstructores) {
		this.listInstructores = listInstructores;
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
