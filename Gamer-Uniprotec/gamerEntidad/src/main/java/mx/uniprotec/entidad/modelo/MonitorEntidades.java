package mx.uniprotec.entidad.modelo;

import java.time.LocalDateTime;


public class MonitorEntidades {
	
	
	private LocalDateTime nowEntidad ;
	private Long idUsuarioEntidad;
	private String statusEntidad;
	
	
	
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
		return "MonitorEntidades [nowEntidad=" + nowEntidad + ", idUsuarioEntidad="
				+ idUsuarioEntidad + ", statusEntidad=" + statusEntidad + "]";
	}
	public MonitorEntidades(LocalDateTime nowEntidad, Long idUsuarioEntidad, String statusEntidad) {
		
		this.nowEntidad = nowEntidad;
		this.idUsuarioEntidad = idUsuarioEntidad;
		this.statusEntidad = statusEntidad;
	}
	
	public MonitorEntidades() {
	}

}
