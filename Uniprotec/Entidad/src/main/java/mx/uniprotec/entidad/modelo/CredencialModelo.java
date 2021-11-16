package mx.uniprotec.entidad.modelo;

import java.io.InputStream;

public class CredencialModelo {

	public CredencialModelo() {
		// TODO Auto-generated constructor stub
	}
	
	private String nombreParticipante;
	private String nombreCurso;
	private String fechaInicio;
	private String fechaFinal;
	private String instructor;
	private InputStream  logoEmpresa;
	private InputStream  fotoParticipante;
	private InputStream  firmaInstructor;
	private InputStream  firmaDirector;
	
	

	public String getNombreParticipante() {
		return nombreParticipante;
	}

	public void setNombreParticipante(String nombreParticipante) {
		this.nombreParticipante = nombreParticipante;
	}

	public String getNombreCurso() {
		return nombreCurso;
	}

	public void setNombreCurso(String nombreCurso) {
		this.nombreCurso = nombreCurso;
	}

	public String getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public String getFechaFinal() {
		return fechaFinal;
	}

	public void setFechaFinal(String fechaFinal) {
		this.fechaFinal = fechaFinal;
	}

	public String getInstructor() {
		return instructor;
	}

	public void setInstructor(String instructor) {
		this.instructor = instructor;
	}

	public InputStream getLogoEmpresa() {
		return logoEmpresa;
	}

	public void setLogoEmpresa(InputStream logoEmpresa) {
		this.logoEmpresa = logoEmpresa;
	}

	public InputStream getFotoParticipante() {
		return fotoParticipante;
	}

	public void setFotoParticipante(InputStream fotoParticipante) {
		this.fotoParticipante = fotoParticipante;
	}

	@Override
	public String toString() {
		return "CredencialesModelo [nombreParticipante=" + nombreParticipante + ", nombreCurso=" + nombreCurso
				+ ", fechaInicio=" + fechaInicio + ", fechaFinal=" + fechaFinal + ", instructor=" + instructor
				+ ", logoEmpresa=" + logoEmpresa + ", fotoParticipante=" + fotoParticipante + "]";
	}

	public CredencialModelo(String nombreParticipante, String nombreCurso, String fechaInicio, String fechaFinal,
			String instructor, InputStream logoEmpresa, InputStream fotoParticipante) {
		super();
		this.nombreParticipante = nombreParticipante;
		this.nombreCurso = nombreCurso;
		this.fechaInicio = fechaInicio;
		this.fechaFinal = fechaFinal;
		this.instructor = instructor;
		this.logoEmpresa = logoEmpresa;
		this.fotoParticipante = fotoParticipante;
	}

	public InputStream getFirmaInstructor() {
		return firmaInstructor;
	}

	public void setFirmaInstructor(InputStream firmaInstructor) {
		this.firmaInstructor = firmaInstructor;
	}

	public InputStream getFirmaDirector() {
		return firmaDirector;
	}

	public void setFirmaDirector(InputStream firmaDirector) {
		this.firmaDirector = firmaDirector;
	}



}
