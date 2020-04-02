package mx.uniprotec.application.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="clientes")
public class Cliente implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idCliente;
	
	@NotEmpty(message ="nombreCortoCliente no puede estar vacio")
	@Size(min=1, max=220, message="el tamaño tiene que estar entre 4 y 220")
	@Column(nullable=false)
	private String nombreCortoCliente;
	
	@NotEmpty(message ="nombreCompletoCliente no puede estar vacio")
	@Size(min=1, max=220, message="el tamaño tiene que estar entre 4 y 220")
	@Column(nullable=false)
	private String nombreCompletoCliente;
	
	@NotEmpty(message ="rfcCliente no puede estar vacio")
	@Size(min=12, max=14, message="el tamaño tiene que estar entre 12 y 14")
	@Column(nullable=false)
	private String rfcCliente;
	
	@OneToOne(fetch = FetchType.LAZY)
	private Region regionCliente;
	
	
	@NotEmpty(message ="emailCliente no puede estar vacio")
	@Email(message="no es una dirección de correo bien formada")
	@Column(nullable=false, unique=true)
	private String emailCliente;
	
	@NotEmpty(message ="telefonoCliente no puede estar vacio")
	@Size(min=1, max=50, message="el tamaño tiene que estar entre 8 y 50")
	@Column(nullable=false, unique=true)
	private String telefonoCliente;
	
	@NotEmpty(message ="domicilioCliente no puede estar vacio")
	@Size(min=1, max=500, message="el tamaño tiene que estar entre 1 y 500")
	@Column(nullable=false, unique=true)
	private String domicilioCliente;
	
	@Column
	private String notaCliente;
	
	@Column(nullable=false)
	private Long userCreateCliente;
	
	@Column(nullable=false)
	private LocalDateTime createAtCliente;
	
	@Column(nullable=false)
	private String statusCliente;

	
	
	




	public Long getIdCliente() {
		return idCliente;
	}








	public void setIdCliente(Long id) {
		this.idCliente = id;
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








	private static final long serialVersionUID = 1L;
}
