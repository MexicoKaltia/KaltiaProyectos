package mx.uniprotec.inicio.entity;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

@Component
public class MonitorEntidades {
	
	private String token;
	private LocalDateTime nowEntidad ;
	private Long idUsuarioEntidad;
	private String statusEntidad;
	
	
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public LocalDateTime getNowEntidad() {
		return nowEntidad;
	}
	public void setNowEntidad(LocalDateTime nowEntidad) {
		this.nowEntidad = nowEntidad;
	}
	public Long getIdUsuarioEntidad() {
		return idUsuarioEntidad;
	}
	public void setIdUsuarioEntidad(Long idUsuarioEntidad) {
		this.idUsuarioEntidad = idUsuarioEntidad;
	}
	public String getStatusEntidad() {
		return statusEntidad;
	}
	public void setStatusEntidad(String statusEntidad) {
		this.statusEntidad = statusEntidad;
	}
	@Override
	public String toString() {
		return "MonitorEntidades [token=" + token + ", nowEntidad=" + nowEntidad + ", idUsuarioEntidad="
				+ idUsuarioEntidad + ", statusEntidad=" + statusEntidad + "]";
	}
	public MonitorEntidades(String token, LocalDateTime nowEntidad, Long idUsuarioEntidad, String statusEntidad) {
		this.token = token;
		this.nowEntidad = nowEntidad;
		this.idUsuarioEntidad = idUsuarioEntidad;
		this.statusEntidad = statusEntidad;
	}
	
	public MonitorEntidades() {
	}

}
