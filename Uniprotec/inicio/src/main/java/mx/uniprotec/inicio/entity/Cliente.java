package mx.uniprotec.inicio.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import org.springframework.stereotype.Component;

@Component
public class Cliente implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long idCliente;
	private String nombreCortoCliente;
	private String nombreCompletoCliente;
	private String rfcCliente;
	private String regionIdCliente;
	private Region regionCliente;
	private String emailCliente;
	private String telefonoCliente;
	private String domicilioCliente;
	private String notaCliente;
	private Long userCreateCliente;
	private LocalDateTime createAtCliente;
	private String statusCliente;
	

	public Long getIdCliente() {
		return idCliente;
	}


	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}


	public String getNombreCortoCliente() {
		return nombreCortoCliente;
	}


	public void setNombreCortoCliente(String nombreCortoCliente) {
		this.nombreCortoCliente = nombreCortoCliente;
	}


	public String getNombreCompletoCliente() {
		return nombreCompletoCliente;
	}


	public void setNombreCompletoCliente(String nombreCompletoCliente) {
		this.nombreCompletoCliente = nombreCompletoCliente;
	}


	public String getRfcCliente() {
		return rfcCliente;
	}


	public void setRfcCliente(String rfcCliente) {
		this.rfcCliente = rfcCliente;
	}


	public String getRegionIdCliente() {
		return regionIdCliente;
	}


	public void setRegionIdCliente(String regionIdCliente) {
		this.regionIdCliente = regionIdCliente;
	}


	public Region getRegionCliente() {
		return regionCliente;
	}


	public void setRegionCliente(Region regionCliente) {
		this.regionCliente = regionCliente;
	}


	public String getEmailCliente() {
		return emailCliente;
	}


	public void setEmailCliente(String emailCliente) {
		this.emailCliente = emailCliente;
	}


	public String getTelefonoCliente() {
		return telefonoCliente;
	}


	public void setTelefonoCliente(String telefonoCliente) {
		this.telefonoCliente = telefonoCliente;
	}


	public String getDomicilioCliente() {
		return domicilioCliente;
	}


	public void setDomicilioCliente(String domicilioCliente) {
		this.domicilioCliente = domicilioCliente;
	}


	public String getNotaCliente() {
		return notaCliente;
	}


	public void setNotaCliente(String notaCliente) {
		this.notaCliente = notaCliente;
	}


	public Long getUserCreateCliente() {
		return userCreateCliente;
	}


	public void setUserCreateCliente(Long userCreateCliente) {
		this.userCreateCliente = userCreateCliente;
	}


	public LocalDateTime getCreateAtCliente() {
		return createAtCliente;
	}


	public void setCreateAtCliente(LocalDateTime createAtCliente) {
		this.createAtCliente = createAtCliente;
	}


	public String getStatusCliente() {
		return statusCliente;
	}


	public void setStatusCliente(String statusCliente) {
		this.statusCliente = statusCliente;
	}


	public Cliente(Long idCliente, String nombreCortoCliente, String nombreCompletoCliente, String rfcCliente,
			String regionIdCliente, Region regionCliente, String emailCliente, String telefonoCliente,
			String domicilioCliente, String notaCliente, Long userCreateCliente, LocalDateTime createAtCliente,
			String statusCliente) {
		super();
		this.idCliente = idCliente;
		this.nombreCortoCliente = nombreCortoCliente;
		this.nombreCompletoCliente = nombreCompletoCliente;
		this.rfcCliente = rfcCliente;
		this.regionIdCliente = regionIdCliente;
		this.regionCliente = regionCliente;
		this.emailCliente = emailCliente;
		this.telefonoCliente = telefonoCliente;
		this.domicilioCliente = domicilioCliente;
		this.notaCliente = notaCliente;
		this.userCreateCliente = userCreateCliente;
		this.createAtCliente = createAtCliente;
		this.statusCliente = statusCliente;
	}


	@Override
	public String toString() {
		return "Cliente [idCliente=" + idCliente + ", nombreCortoCliente=" + nombreCortoCliente
				+ ", nombreCompletoCliente=" + nombreCompletoCliente + ", rfcCliente=" + rfcCliente
				+ ", regionIdCliente=" + regionIdCliente + ", regionCliente=" + regionCliente + ", emailCliente="
				+ emailCliente + ", telefonoCliente=" + telefonoCliente + ", domicilioCliente=" + domicilioCliente
				+ ", notaCliente=" + notaCliente + ", userCreateCliente=" + userCreateCliente + ", createAtCliente="
				+ createAtCliente + ", statusCliente=" + statusCliente + "]";
	}


	public Cliente() {}
	
}
