package mx.uniprotec.gamerBack.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "modulo_didactico")
public class ModuloDidacticoEntity {

	public ModuloDidacticoEntity() {
		// TODO Auto-generated constructor stub
	}

private static final long serialVersionUID = 1L;
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idModuloDidactico;
@Column
	private String moduloDidacticoNombre;
@Column
	private String moduloDidacticoDescripcion;
@Column
	private String moduloDidacticoInstrucciones;
@Column
	private String moduloDidacticoImagen;
@Column
private String moduloDidacticoIdImagen;
@Column
	private String moduloDidacticoCursos;
@Column
	private String moduloDidacticoElemento;
@Column
	private String createAt;
@Column
	private String status;
@Column
	private String createUser;
@Override
public String toString() {
	return "ModuloDidacticoEntity [idModuloDidactico=" + idModuloDidactico + ", moduloDidacticoNombre="
			+ moduloDidacticoNombre + ", moduloDidacticoDescripcion=" + moduloDidacticoDescripcion
			+ ", moduloDidacticoInstrucciones=" + moduloDidacticoInstrucciones + ", moduloDidacticoImagen="
			+ moduloDidacticoImagen + ", moduloDidacticoCursos=" + moduloDidacticoCursos + ", createAt=" + createAt
			+ ", status=" + status + ", createUser=" + createUser + "]";
}
public Long getIdModuloDidactico() {
	return idModuloDidactico;
}
public void setIdModuloDidactico(Long idModuloDidactico) {
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
public String getModuloDidacticoCursos() {
	return moduloDidacticoCursos;
}
public void setModuloDidacticoCursos(String moduloDidacticoCursos) {
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
public String getModuloDidacticoElemento() {
	return moduloDidacticoElemento;
}
public void setModuloDidacticoElemento(String moduloDidacticoElemento) {
	this.moduloDidacticoElemento = moduloDidacticoElemento;
}


	
}
