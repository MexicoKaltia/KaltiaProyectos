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
@Table(name="notificaciones")
public class Notificacion implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idNotificacion;
	@NotEmpty(message ="Mensaje no puede estar vacio")
	@Size(min=1, max=300, message="el tamaño tiene que estar entre 4 y 300")
	@Column(nullable=false)
	private String mensaje;
	@Column
	private Long idAsignacionNotificacion;
	@Column
	private String idAsignacionLogicaNotificacion;
	@Column
	private Long idVendedorNotificacion;
	@Column
	private Long idInstructorNotificacion;
	@Column
	private String referenciaNotificacion;
	@Column
	private String statusNotificacion;
	@Column
	private Long userCreateNotificacion;
	@Column
	private LocalDateTime createAtNotificacion;

	public Notificacion() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Notificacion [idNotificacion=" + idNotificacion + ", mensaje=" + mensaje + ", idAsignacionNotificacion="
				+ idAsignacionNotificacion + ", idAsignacionLogicaNotificacion=" + idAsignacionLogicaNotificacion
				+ ", idVendedorNotificacion=" + idVendedorNotificacion + ", idInstructorNotificacion="
				+ idInstructorNotificacion + ", statusNotificacion=" + statusNotificacion + ", userCreateNotificacion="
				+ userCreateNotificacion + ", createAtNotificacion=" + createAtNotificacion + "]";
	}

	public Notificacion(Long idNotificacion,
			@NotEmpty(message = "Mensaje no puede estar vacio") @Size(min = 1, max = 300, message = "el tamaño tiene que estar entre 4 y 300") String mensaje,
			Long idAsignacionNotificacion, String idAsignacionLogicaNotificacion, Long idVendedorNotificacion,
			Long idInstructorNotificacion, String statusNotificacion, Long userCreateNotificacion,
			LocalDateTime createAtNotificacion) {
		super();
		this.idNotificacion = idNotificacion;
		this.mensaje = mensaje;
		this.idAsignacionNotificacion = idAsignacionNotificacion;
		this.idAsignacionLogicaNotificacion = idAsignacionLogicaNotificacion;
		this.idVendedorNotificacion = idVendedorNotificacion;
		this.idInstructorNotificacion = idInstructorNotificacion;
		this.statusNotificacion = statusNotificacion;
		this.userCreateNotificacion = userCreateNotificacion;
		this.createAtNotificacion = createAtNotificacion;
	}

	public Long getIdNotificacion() {
		return idNotificacion;
	}

	public void setIdNotificacion(Long idNotificacion) {
		this.idNotificacion = idNotificacion;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public Long getIdAsignacionNotificacion() {
		return idAsignacionNotificacion;
	}

	public void setIdAsignacionNotificacion(Long idAsignacionNotificacion) {
		this.idAsignacionNotificacion = idAsignacionNotificacion;
	}

	public String getIdAsignacionLogicaNotificacion() {
		return idAsignacionLogicaNotificacion;
	}

	public void setIdAsignacionLogicaNotificacion(String idAsignacionLogicaNotificacion) {
		this.idAsignacionLogicaNotificacion = idAsignacionLogicaNotificacion;
	}

	public Long getIdVendedorNotificacion() {
		return idVendedorNotificacion;
	}

	public void setIdVendedorNotificacion(Long idVendedorNotificacion) {
		this.idVendedorNotificacion = idVendedorNotificacion;
	}

	public Long getIdInstructorNotificacion() {
		return idInstructorNotificacion;
	}

	public void setIdInstructorNotificacion(Long idInstructorNotificacion) {
		this.idInstructorNotificacion = idInstructorNotificacion;
	}

	public String getStatusNotificacion() {
		return statusNotificacion;
	}

	public void setStatusNotificacion(String statusNotificacion) {
		this.statusNotificacion = statusNotificacion;
	}

	public Long getUserCreateNotificacion() {
		return userCreateNotificacion;
	}

	public void setUserCreateNotificacion(Long userCreateNotificacion) {
		this.userCreateNotificacion = userCreateNotificacion;
	}

	public LocalDateTime getCreateAtNotificacion() {
		return createAtNotificacion;
	}

	public void setCreateAtNotificacion(LocalDateTime createAtNotificacion) {
		this.createAtNotificacion = createAtNotificacion;
	}

	public String getReferenciaNotificacion() {
		return referenciaNotificacion;
	}

	public void setReferenciaNotificacion(String referenciaNotificacion) {
		this.referenciaNotificacion = referenciaNotificacion;
	}

	
}
