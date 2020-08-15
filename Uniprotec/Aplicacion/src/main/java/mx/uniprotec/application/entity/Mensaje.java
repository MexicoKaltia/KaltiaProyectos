package mx.uniprotec.application.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name="mensajes")
public class Mensaje implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idMensaje;
	@NotEmpty(message ="Mensaje no puede estar vacio")
	@Size(min=1, max=300, message="el tamaño tiene que estar entre 4 y 300")
	@Column(nullable=false)
	private String mensaje;
	@Column
	private Long userCreateInstructor;
	@Column
	private LocalDateTime createAtInstructor;

	public Mensaje() {
		// TODO Auto-generated constructor stub
	}

	
	
	

	public Mensaje(
			@NotEmpty(message = "Mensaje no puede estar vacio") @Size(min = 1, max = 300, message = "el tamaño tiene que estar entre 4 y 300") String mensaje,
			Long userCreateInstructor, LocalDateTime createAtInstructor) {
		super();
		this.mensaje = mensaje;
		this.userCreateInstructor = userCreateInstructor;
		this.createAtInstructor = createAtInstructor;
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
