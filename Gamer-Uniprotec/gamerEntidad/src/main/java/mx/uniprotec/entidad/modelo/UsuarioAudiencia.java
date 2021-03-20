package mx.uniprotec.entidad.modelo;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

public class UsuarioAudiencia implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long idUsuarioAudiencia;
	private String usuarioAudienciaResponsable;
	private String usuarioAudienciaFecha;
	private String usuarioAudienciaEvento;
	private List<String> usuarioAudienciaModulos;
	private Integer usuarioAudienciaParticipantes;
	private String usuarioAudienciaIdAsignacion;
	private LocalDateTime createAt;
	private String userCreate;
	private String status;
	
	public UsuarioAudiencia() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UsuarioAudiencia(Long idUsuarioAudiencia, String usuarioAudienciaResponsable, String usuarioAudienciaFecha,
			String usuarioAudienciaEvento, List<String> usuarioAudienciaModulos,
			Integer usuarioAudienciaParticipantes) {
		super();
		this.idUsuarioAudiencia = idUsuarioAudiencia;
		this.usuarioAudienciaResponsable = usuarioAudienciaResponsable;
		this.usuarioAudienciaFecha = usuarioAudienciaFecha;
		this.usuarioAudienciaEvento = usuarioAudienciaEvento;
		this.usuarioAudienciaModulos = usuarioAudienciaModulos;
		this.usuarioAudienciaParticipantes = usuarioAudienciaParticipantes;
	}

	@Override
	public String toString() {
		return "UsuarioAudiencia [idUsuarioAudiencia=" + idUsuarioAudiencia + ", usuarioAudienciaResponsable="
				+ usuarioAudienciaResponsable + ", usuarioAudienciaFecha=" + usuarioAudienciaFecha
				+ ", usuarioAudienciaEvento=" + usuarioAudienciaEvento + ", usuarioAudienciaModulos="
				+ usuarioAudienciaModulos + ", usuarioAudienciaParticipantes=" + usuarioAudienciaParticipantes
				+ ", usuarioAudienciaIdAsignacion=" + usuarioAudienciaIdAsignacion + ", createAt=" + createAt
				+ ", userCreate=" + userCreate + ", status=" + status + "]";
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

	public List<String> getUsuarioAudienciaModulos() {
		return usuarioAudienciaModulos;
	}

	public void setUsuarioAudienciaModulos(List<String> usuarioAudienciaModulos) {
		this.usuarioAudienciaModulos = usuarioAudienciaModulos;
	}

	public Integer getUsuarioAudienciaParticipantes() {
		return usuarioAudienciaParticipantes;
	}

	public void setUsuarioAudienciaParticipantes(Integer usuarioAudienciaParticipantes) {
		this.usuarioAudienciaParticipantes = usuarioAudienciaParticipantes;
	}

	public LocalDateTime getCreateAt() {
		return createAt;
	}

	public void setCreateAt(LocalDateTime createAt) {
		this.createAt = createAt;
	}

	public String getUserCreate() {
		return userCreate;
	}

	public void setUserCreate(String userCreate) {
		this.userCreate = userCreate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUsuarioAudienciaIdAsignacion() {
		return usuarioAudienciaIdAsignacion;
	}

	public void setUsuarioAudienciaIdAsignacion(String usuarioAudienciaIdAsignacion) {
		this.usuarioAudienciaIdAsignacion = usuarioAudienciaIdAsignacion;
	}

		

}