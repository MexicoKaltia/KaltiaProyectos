package mx.uniprotec.gamerBack.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


@Entity
@Table(name = "modulo_curso")
public class ModuloCurso {

	public ModuloCurso() {
		// TODO Auto-generated constructor stub
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idModuloCurso;
	@Column
	private Long idModuloDidactico;
	@Column
	private Long idCurso;
	@Column
	private String elementos;
	@Column
	private String cursoNombre;
	@Column
	private String moduloNombre;
	
	
	
	
	public Long getIdModuloCurso() {
		return idModuloCurso;
	}
	public void setIdModuloCurso(Long idModuloCurso) {
		this.idModuloCurso = idModuloCurso;
	}
	public Long getIdModuloDidactico() {
		return idModuloDidactico;
	}
	public void setIdModuloDidactico(Long idModuloDidactico) {
		this.idModuloDidactico = idModuloDidactico;
	}
	public Long getIdCurso() {
		return idCurso;
	}
	public void setIdCurso(Long idCurso) {
		this.idCurso = idCurso;
	}
	public String getElementos() {
		return elementos;
	}
	public void setElementos(String elementos) {
		this.elementos = elementos;
	}
	public String getCursoNombre() {
		return cursoNombre;
	}
	public void setCursoNombre(String cursoNombre) {
		this.cursoNombre = cursoNombre;
	}
	public String getModuloNombre() {
		return moduloNombre;
	}
	public void setModuloNombre(String moduloNombre) {
		this.moduloNombre = moduloNombre;
	}
	@Override
	public String toString() {
		return "ModuloCurso [idModuloCurso=" + idModuloCurso + ", idModuloDidactico=" + idModuloDidactico + ", idCurso="
				+ idCurso + ", elementos=" + elementos + ", cursoNombre=" + cursoNombre + ", moduloNombre="
				+ moduloNombre + "]";
	}
	public ModuloCurso(Long idModuloCurso, Long idModuloDidactico, Long idCurso, String elementos, String cursoNombre,
			String moduloNombre) {
		super();
		this.idModuloCurso = idModuloCurso;
		this.idModuloDidactico = idModuloDidactico;
		this.idCurso = idCurso;
		this.elementos = elementos;
		this.cursoNombre = cursoNombre;
		this.moduloNombre = moduloNombre;
	}

	
	
		


}
