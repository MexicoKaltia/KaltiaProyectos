package mx.uniprotec.entidad.modelo;

public class EntregableDiploma{

	public EntregableDiploma() {
		// TODO Auto-generated constructor stub
	}

	private String diplomaNombreParticipante;
	private String diplomaNombreCurso;
	private String diplomaFecha;
	private String diplomaDuracion;
	private String diplomaNombreInstructor;
	private String diplomaNombreDirector;
	
	 
	@Override
	public String toString() {
		return "DiplomaReporte [diplomaNombreParticipante=" + diplomaNombreParticipante + ", diplomaNombreCurso="
				+ diplomaNombreCurso + ", diplomaFecha=" + diplomaFecha + ", diplomaDuracion=" + diplomaDuracion
				+ ", diplomaNombreInstructor=" + diplomaNombreInstructor + ", diplomaNombreDirector="
				+ diplomaNombreDirector + "]";
	}
	
	
	public EntregableDiploma(String diplomaNombreParticipante, String diplomaNombreCurso, String diplomaFecha,
			String diplomaDuracion, String diplomaNombreInstructor, String diplomaNombreDirector) {
		super();
		this.diplomaNombreParticipante = diplomaNombreParticipante;
		this.diplomaNombreCurso = diplomaNombreCurso;
		this.diplomaFecha = diplomaFecha;
		this.diplomaDuracion = diplomaDuracion;
		this.diplomaNombreInstructor = diplomaNombreInstructor;
		this.diplomaNombreDirector = diplomaNombreDirector;
	}
	
	
	public String getDiplomaNombreParticipante() {
		return diplomaNombreParticipante;
	}
	public void setDiplomaNombreParticipante(String diplomaNombreParticipante) {
		this.diplomaNombreParticipante = diplomaNombreParticipante;
	}
	public String getDiplomaNombreCurso() {
		return diplomaNombreCurso;
	}
	public void setDiplomaNombreCurso(String diplomaNombreCurso) {
		this.diplomaNombreCurso = diplomaNombreCurso;
	}
	public String getDiplomaFecha() {
		return diplomaFecha;
	}
	public void setDiplomaFecha(String diplomaFecha) {
		this.diplomaFecha = diplomaFecha;
	}
	public String getDiplomaDuracion() {
		return diplomaDuracion;
	}
	public void setDiplomaDuracion(String diplomaDuracion) {
		this.diplomaDuracion = diplomaDuracion;
	}
	public String getDiplomaNombreInstructor() {
		return diplomaNombreInstructor;
	}
	public void setDiplomaNombreInstructor(String diplomaNombreInstructor) {
		this.diplomaNombreInstructor = diplomaNombreInstructor;
	}
	public String getDiplomaNombreDirector() {
		return diplomaNombreDirector;
	}
	public void setDiplomaNombreDirector(String diplomaNombreDirector) {
		this.diplomaNombreDirector = diplomaNombreDirector;
	}
	
	
	
}
