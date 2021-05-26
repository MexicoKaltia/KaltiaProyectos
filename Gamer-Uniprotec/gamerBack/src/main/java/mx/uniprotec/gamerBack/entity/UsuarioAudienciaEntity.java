package mx.uniprotec.gamerBack.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "usuarios_audiencia")
public class UsuarioAudienciaEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idUsuarioAudiencia;
	@Column
	private String usuarioAudienciaResponsable;
	@Column
	private String usuarioAudienciaFecha;
	@Column
	private String usuarioAudienciaEvento;
	@Column
	private String usuarioAudienciaModulos;
	@Column
	private String usuarioAudienciaParticipantes;
	@Column
	private String usuarioAudienciaIdAsignacion;
	@Column
	private String usuarioAudienciaNombreEvento;
	@Column
	private LocalDateTime createAtAsignacion;
	@Column
	private String userCreateAsignacion;
	@Column
	private String statusAsignacion;
	@Column
	private String usuarioAudienciaidCurso;
	
	


	public UsuarioAudienciaEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
	
	
	


	public UsuarioAudienciaEntity( String usuarioAudienciaResponsable,
			String usuarioAudienciaFecha, String usuarioAudienciaEvento, String usuarioAudienciaModulos,
			String usuarioAudienciaParticipantes, String usuarioAudienciaIdAsignacion,
			String usuarioAudienciaNombreEvento, LocalDateTime createAtAsignacion, String userCreateAsignacion,
			String statusAsignacion, String usuarioAudienciaidCurso) {
		super();
		
		this.usuarioAudienciaResponsable = usuarioAudienciaResponsable;
		this.usuarioAudienciaFecha = usuarioAudienciaFecha;
		this.usuarioAudienciaEvento = usuarioAudienciaEvento;
		this.usuarioAudienciaModulos = usuarioAudienciaModulos;
		this.usuarioAudienciaParticipantes = usuarioAudienciaParticipantes;
		this.usuarioAudienciaIdAsignacion = usuarioAudienciaIdAsignacion;
		this.usuarioAudienciaNombreEvento = usuarioAudienciaNombreEvento;
		this.createAtAsignacion = createAtAsignacion;
		this.userCreateAsignacion = userCreateAsignacion;
		this.statusAsignacion = statusAsignacion;
		this.usuarioAudienciaidCurso = usuarioAudienciaidCurso;
	}















	@Override
	public String toString() {
		return "UsuarioAudienciaEntity [idUsuarioAudiencia=" + idUsuarioAudiencia + ", usuarioAudienciaResponsable="
				+ usuarioAudienciaResponsable + ", usuarioAudienciaFecha=" + usuarioAudienciaFecha
				+ ", usuarioAudienciaEvento=" + usuarioAudienciaEvento + ", usuarioAudienciaModulos="
				+ usuarioAudienciaModulos + ", usuarioAudienciaParticipantes=" + usuarioAudienciaParticipantes
				+ ", usuarioAudienciaIdAsignacion=" + usuarioAudienciaIdAsignacion + ", usuarioAudienciaNombreEvento="
				+ usuarioAudienciaNombreEvento + ", createAtAsignacion=" + createAtAsignacion
				+ ", userCreateAsignacion=" + userCreateAsignacion + ", statusAsignacion=" + statusAsignacion + "]";
	}



	public Long getIdUsuarioAudiencia() {
		return idUsuarioAudiencia;
	}

	public void setIdUsuarioAudiencia(Long idUsuarioAudiencia) {
		this.idUsuarioAudiencia = idUsuarioAudiencia;
	}

	public String getUsuarioAudienciaResponsable() {
		return usuarioAudienciaResponsable;
	}

	public void setUsuarioAudienciaResponsable(String usuarioAudienciaResponsable) {
		this.usuarioAudienciaResponsable = usuarioAudienciaResponsable;
	}

	public String getUsuarioAudienciaFecha() {
		return usuarioAudienciaFecha;
	}

	public void setUsuarioAudienciaFecha(String usuarioAudienciaFecha) {
		this.usuarioAudienciaFecha = usuarioAudienciaFecha;
	}

	public String getUsuarioAudienciaEvento() {
		return usuarioAudienciaEvento;
	}

	public void setUsuarioAudienciaEvento(String usuarioAudienciaEvento) {
		this.usuarioAudienciaEvento = usuarioAudienciaEvento;
	}

	public String getUsuarioAudienciaModulos() {
		return usuarioAudienciaModulos;
	}

	public void setUsuarioAudienciaModulos(String usuarioAudienciaModulos) {
		this.usuarioAudienciaModulos = usuarioAudienciaModulos;
	}

	public String getUsuarioAudienciaParticipantes() {
		return usuarioAudienciaParticipantes;
	}

	public void setUsuarioAudienciaParticipantes(String usuarioAudienciaParticipantes) {
		this.usuarioAudienciaParticipantes = usuarioAudienciaParticipantes;
	}

	public LocalDateTime getCreateAtAsignacion() {
		return createAtAsignacion;
	}

	public void setCreateAtAsignacion(LocalDateTime createAtAsignacion) {
		this.createAtAsignacion = createAtAsignacion;
	}

	public String getUserCreateAsignacion() {
		return userCreateAsignacion;
	}

	public void setUserCreateAsignacion(String userCreateAsignacion) {
		this.userCreateAsignacion = userCreateAsignacion;
	}

	public String getStatusAsignacion() {
		return statusAsignacion;
	}

	public void setStatusAsignacion(String statusAsignacion) {
		this.statusAsignacion = statusAsignacion;
	}

	public String getUsuarioAudienciaIdAsignacion() {
		return usuarioAudienciaIdAsignacion;
	}

	public void setUsuarioAudienciaIdAsignacion(String usuarioAudienciaIdAsignacion) {
		this.usuarioAudienciaIdAsignacion = usuarioAudienciaIdAsignacion;
	}
	
	public String getUsuarioAudienciaNombreEvento() {
		return usuarioAudienciaNombreEvento;
	}

	public void setUsuarioAudienciaNombreEvento(String usuarioAudienciaNombreEvento) {
		this.usuarioAudienciaNombreEvento = usuarioAudienciaNombreEvento;
	}
	public String getUsuarioAudienciaidCurso() {
		return usuarioAudienciaidCurso;
	}
	public void setUsuarioAudienciaidCurso(String usuarioAudienciaidCurso) {
		this.usuarioAudienciaidCurso = usuarioAudienciaidCurso;
	}


	

	
	

}
