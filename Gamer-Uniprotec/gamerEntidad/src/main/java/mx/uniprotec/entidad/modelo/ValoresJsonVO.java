package mx.uniprotec.entidad.modelo;

import java.io.Serializable;
import java.util.Arrays;

public class ValoresJsonVO implements Serializable{
	
/**
	 * 
	 */
	private static final long serialVersionUID = 2989884388929127461L;

//	private static final long serialVersionUID = 1L;

	public ValoresJsonVO() {
		// TODO Auto-generated constructor stub
	}
	
	private String elementos;
	private String modulo;
	private String idCurso;
	private String val;
	private String arrayCursos;
	private String cursoNombre;
	private String moduloNombre;
	

	public String getElementos() {
		return elementos;
	}
	public void setElementos(String elementos) {
		this.elementos = elementos;
	}
	public String getModulo() {
		return modulo;
	}
	public void setModulo(String modulo) {
		this.modulo = modulo;
	}
	public String getIdCurso() {
		return idCurso;
	}
	public void setIdCurso(String idCurso) {
		this.idCurso = idCurso;
	}
	public String getVal() {
		return val;
	}
	public void setVal(String val) {
		this.val = val;
	}
	public String getArrayCursos() {
		return arrayCursos;
	}
	public void setArrayCursos(String arrayCursos) {
		this.arrayCursos = arrayCursos;
	}
	
	public String getModuloNombre() {
		return moduloNombre;
	}
	public void setModuloNombre(String moduloNombre) {
		this.moduloNombre = moduloNombre;
	}
	public String getCursoNombre() {
		return cursoNombre;
	}
	public void setCursoNombre(String cursoNombre) {
		this.cursoNombre = cursoNombre;
	}
	@Override
	public String toString() {
		return "ValoresJsonVO [elementos=" + elementos + ", modulo=" + modulo + ", idCurso=" + idCurso + ", val=" + val
				+ ", arrayCursos=" + arrayCursos + ", cursoNombre=" + cursoNombre + ", moduloNombre=" + moduloNombre
				+ "]";
	}
	public ValoresJsonVO(String elementos, String modulo, String idCurso, String val, String arrayCursos,
			String cursoNombre, String moduloNombre) {
		super();
		this.elementos = elementos;
		this.modulo = modulo;
		this.idCurso = idCurso;
		this.val = val;
		this.arrayCursos = arrayCursos;
		this.cursoNombre = cursoNombre;
		this.moduloNombre = moduloNombre;
	}
	
	
	
	
}
