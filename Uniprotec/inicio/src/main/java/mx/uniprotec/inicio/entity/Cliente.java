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
	private Long id;
	private String nombreCortoCliente;
	private String nombreCompletoCliente;
	private String rfcCliente;
	private String regionCliente;
	private String emailCliente;
	private String telefonoCliente;
	private String domicilioCliente;
	private LocalDateTime createAtCliente;
	
	
		
	public Cliente(Long id, String nombreCortoCliente, String nombreCompletoCliente, String rfcCliente,
			String regionCliente, String emailCliente, String telefonoCliente, String domicilioCliente,
			LocalDateTime createAtCliente) {
		super();
		this.id = id;
		this.nombreCortoCliente = nombreCortoCliente;
		this.nombreCompletoCliente = nombreCompletoCliente;
		this.rfcCliente = rfcCliente;
		this.regionCliente = regionCliente;
		this.emailCliente = emailCliente;
		this.telefonoCliente = telefonoCliente;
		this.domicilioCliente = domicilioCliente;
		this.createAtCliente = createAtCliente;
	}



	@Override
	public String toString() {
		return "Cliente [id=" + id + ", nombreCortoCliente=" + nombreCortoCliente + ", nombreCompletoCliente="
				+ nombreCompletoCliente + ", rfcCliente=" + rfcCliente + ", regionCliente=" + regionCliente
				+ ", emailCliente=" + emailCliente + ", telefonoCliente=" + telefonoCliente + ", domicilioCliente="
				+ domicilioCliente + ", createAtCliente=" + createAtCliente + "]";
	}



	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
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



	public String getRegionCliente() {
		return regionCliente;
	}



	public void setRegionCliente(String regionCliente) {
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



	public LocalDateTime getCreateAtCliente() {
		return createAtCliente;
	}



	public void setCreateAtCliente() {
		LocalDateTime date = LocalDateTime.now();
		this.createAtCliente = date;
	}



	public Cliente() {}
	
}
