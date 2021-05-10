package mx.uniprotec.entidad.modelo;

import java.io.Serializable;
import java.util.List;

public class ModuloDidactico implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String idModuloDidactico;
	private String moduloDidacticoNombre;
	private String moduloDidacticoDescripcion;
	private String moduloDidacticoInstrucciones;
	private String moduloDidacticoImagen;
	private String moduloDidacticoIdImagen;
	private List<Integer> moduloDidacticoCursos;
	private String moduloDidacticoElemento;
//	private String moduloDidacticoElementos;
	private String createAt;
	private String status;
	private String createUser;
	
	

	
	

	@Override
	public String toString() {
		return "ModuloDidactico [idModuloDidactico=" + idModuloDidactico + ", moduloDidacticoNombre="
				+ moduloDidacticoNombre + ", moduloDidacticoDescripcion=" + moduloDidacticoDescripcion
				+ ", moduloDidacticoInstrucciones=" + moduloDidacticoInstrucciones + ", moduloDidacticoImagen="
				+ moduloDidacticoImagen + ", moduloDidacticoCursos=" + moduloDidacticoCursos + ", createAt=" + createAt
				+ ", status=" + status + ", createUser=" + createUser + "]";
	}




	public String getModuloDidacticoElemento() {
		return moduloDidacticoElemento;
	}




	public void setModuloDidacticoElemento(String moduloDidacticoElemento) {
		this.moduloDidacticoElemento = moduloDidacticoElemento;
	}




	public String getIdModuloDidactico() {
		return idModuloDidactico;
	}




	public void setIdModuloDidactico(String idModuloDidactico) {
		this.idModuloDidactico = idModuloDidactico;
	}




	public String getModuloDidacticoNombre() {
		return moduloDidacticoNombre;
	}




	public void setModuloDidacticoNombre(String moduloDidacticoNombre) {
		this.moduloDidacticoNombre = moduloDidacticoNombre;
	}




	public String getModuloDidacticoDescripcion() {
		return moduloDidacticoDescripcion;
	}




	public void setModuloDidacticoDescripcion(String moduloDidacticoDescripcion) {
		this.moduloDidacticoDescripcion = moduloDidacticoDescripcion;
	}




	public String getModuloDidacticoInstrucciones() {
		return moduloDidacticoInstrucciones;
	}




	public void setModuloDidacticoInstrucciones(String moduloDidacticoInstrucciones) {
		this.moduloDidacticoInstrucciones = moduloDidacticoInstrucciones;
	}




	public String getModuloDidacticoImagen() {
		return moduloDidacticoImagen;
	}




	public void setModuloDidacticoImagen(String moduloDidacticoImagen) {
		this.moduloDidacticoImagen = moduloDidacticoImagen;
	}




	public List<Integer> getModuloDidacticoCursos() {
		return moduloDidacticoCursos;
	}




	public void setModuloDidacticoCursos(List<Integer> moduloDidacticoCursos) {
		this.moduloDidacticoCursos = moduloDidacticoCursos;
	}

	public String getCreateAt() {
		return createAt;
	}




	public void setCreateAt(String createAt) {
		this.createAt = createAt;
	}




	public String getStatus() {
		return status;
	}




	public void setStatus(String status) {
		this.status = status;
	}




	public String getCreateUser() {
		return createUser;
	}




	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public String getModuloDidacticoIdImagen() {
		return moduloDidacticoIdImagen;
	}




	public void setModuloDidacticoIdImagen(String moduloDidacticoIdImagen) {
		this.moduloDidacticoIdImagen = moduloDidacticoIdImagen;
	}















	public ModuloDidactico() {
		// TODO Auto-generated constructor stub
	}

}
