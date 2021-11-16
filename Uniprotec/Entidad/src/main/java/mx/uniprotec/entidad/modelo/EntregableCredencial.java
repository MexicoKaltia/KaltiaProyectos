package mx.uniprotec.entidad.modelo;

public class EntregableCredencial {

	public EntregableCredencial() {
		// TODO Auto-generated constructor stub
	}
	
	private String credencialParticipante;
	private String credencialCurso;
	private String credencialFechaInicio;
	private String credencialFechaFinal;
	private String credencialInstructor;
	private String credencialLogoEmpresa;
	private String credencialFotoParticipante;
	@Override
	public String toString() {
		return "EntregableCredencial [credencialParticipante=" + credencialParticipante + ", credencialCurso="
				+ credencialCurso + ", credencialFechaInicio=" + credencialFechaInicio + ", credencialFechaFinal="
				+ credencialFechaFinal + ", credencialInstructor=" + credencialInstructor + ", credencialLogoEmpresa="
				+ credencialLogoEmpresa + ", credencialFotoParticipante=" + credencialFotoParticipante + "]";
	}
	public EntregableCredencial(String credencialParticipante, String credencialCurso, String credencialFechaInicio,
			String credencialFechaFinal, String credencialInstructor, String credencialLogoEmpresa,
			String credencialFotoParticipante) {
		super();
		this.credencialParticipante = credencialParticipante;
		this.credencialCurso = credencialCurso;
		this.credencialFechaInicio = credencialFechaInicio;
		this.credencialFechaFinal = credencialFechaFinal;
		this.credencialInstructor = credencialInstructor;
		this.credencialLogoEmpresa = credencialLogoEmpresa;
		this.credencialFotoParticipante = credencialFotoParticipante;
	}
	public String getCredencialParticipante() {
		return credencialParticipante;
	}
	public void setCredencialParticipante(String credencialParticipante) {
		this.credencialParticipante = credencialParticipante;
	}
	public String getCredencialCurso() {
		return credencialCurso;
	}
	public void setCredencialCurso(String credencialCurso) {
		this.credencialCurso = credencialCurso;
	}
	public String getCredencialFechaInicio() {
		return credencialFechaInicio;
	}
	public void setCredencialFechaInicio(String credencialFechaInicio) {
		this.credencialFechaInicio = credencialFechaInicio;
	}
	public String getCredencialFechaFinal() {
		return credencialFechaFinal;
	}
	public void setCredencialFechaFinal(String credencialFechaFinal) {
		this.credencialFechaFinal = credencialFechaFinal;
	}
	public String getCredencialInstructor() {
		return credencialInstructor;
	}
	public void setCredencialInstructor(String credencialInstructor) {
		this.credencialInstructor = credencialInstructor;
	}
	public String getCredencialLogoEmpresa() {
		return credencialLogoEmpresa;
	}
	public void setCredencialLogoEmpresa(String credencialLogoEmpresa) {
		this.credencialLogoEmpresa = credencialLogoEmpresa;
	}
	public String getCredencialFotoParticipante() {
		return credencialFotoParticipante;
	}
	public void setCredencialFotoParticipante(String credencialFotoParticipante) {
		this.credencialFotoParticipante = credencialFotoParticipante;
	}
	
	
	
}
