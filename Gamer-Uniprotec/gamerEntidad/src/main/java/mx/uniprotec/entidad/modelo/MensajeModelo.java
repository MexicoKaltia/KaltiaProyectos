package mx.uniprotec.entidad.modelo;

import java.io.Serializable;
import java.time.LocalDateTime;

public class MensajeModelo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String mensaje;
	private Long userCreateInstructor;
	private LocalDateTime createAtInstructor;

	public MensajeModelo() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "MensajeModelo [mensaje=" + mensaje + ", userCreateInstructor=" + userCreateInstructor
				+ ", createAtInstructor=" + createAtInstructor + "]";
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
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
	
	

}
