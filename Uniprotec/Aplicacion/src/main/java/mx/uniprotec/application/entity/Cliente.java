package mx.uniprotec.application.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name="clientes")
@JsonIdentityInfo(
		  generator = ObjectIdGenerators.PropertyGenerator.class, 
		  property = "idCliente")

public class Cliente implements Serializable {
	
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -7513049079357375181L;

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
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) 
	private Region regionCliente;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) 
	private Vendedor vendedorCliente;
	
	
	@NotEmpty(message ="emailCliente no puede estar vacio")
	@Email(message="no es una dirección de correo bien formada")
	@Column(nullable=false)
	private String emailCliente;
	
	@NotEmpty(message ="telefonoCliente no puede estar vacio")
	@Size(min=1, max=50, message="el tamaño tiene que estar entre 8 y 50")
	@Column(nullable=false)
	private String telefonoCliente;
	
	@NotEmpty(message ="domicilioCliente no puede estar vacio")
	@Size(min=1, max=500, message="el tamaño tiene que estar entre 1 y 500")
	@Column(nullable=false)
	private String domicilioCliente;
	
	@Column
	private String notaCliente;
	
	@Column(nullable=false)
	private Long userCreateCliente;
	
	@Column(nullable=false)
	private LocalDateTime createAtCliente;
	
	@Column(nullable=false)
	private String statusCliente;
	
	@Column(nullable=false)
	private String nombreContactoRecibeCliente;
	@Column(nullable=false)
	private String googleMapsCliente;
	@Column(nullable=false)
	private String pautaOperativaCliente;
	@Column(nullable=false)
	private String reglasAccesoCliente;
	@Column(nullable=false)
	private String documentosAccesoCliente;
	@Column(nullable=false)
	private String materialDidacticoCliente;
	@Column(nullable=false)
	private String pautaGeneralCliente;
	@Column(nullable=false)
	private String pautaEntregableCliente;
	@Column(nullable=false)
	private String representanteEmpresaCliente;
	@Column(nullable=false)
	private String representanteTrabajadorCliente;
	@Column(nullable=false)
	private String informacionPaqueteriaCliente;
	@Column(nullable=false)
	private String imagenLogoCliente;
	@Column(nullable=false)
	private String archivosCliente;

	public Cliente(Long idCliente) {
		super();
		this.idCliente = idCliente;
	}

	public Cliente() {
	}

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

	public Region getRegionCliente() {
		return regionCliente;
	}

	public void setRegionCliente(Region regionCliente) {
		this.regionCliente = regionCliente;
	}

	public Vendedor getVendedorCliente() {
		return vendedorCliente;
	}

	public void setVendedorCliente(Vendedor vendedorCliente) {
		this.vendedorCliente = vendedorCliente;
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

	public String getNombreContactoRecibeCliente() {
		return nombreContactoRecibeCliente;
	}

	public void setNombreContactoRecibeCliente(String nombreContactoRecibeCliente) {
		this.nombreContactoRecibeCliente = nombreContactoRecibeCliente;
	}

	public String getGoogleMapsCliente() {
		return googleMapsCliente;
	}

	public void setGoogleMapsCliente(String googleMapsCliente) {
		this.googleMapsCliente = googleMapsCliente;
	}

	public String getPautaOperativaCliente() {
		return pautaOperativaCliente;
	}

	public void setPautaOperativaCliente(String pautaOperativaCliente) {
		this.pautaOperativaCliente = pautaOperativaCliente;
	}

	public String getReglasAccesoCliente() {
		return reglasAccesoCliente;
	}

	public void setReglasAccesoCliente(String reglasAccesoCliente) {
		this.reglasAccesoCliente = reglasAccesoCliente;
	}

	public String getDocumentosAccesoCliente() {
		return documentosAccesoCliente;
	}

	public void setDocumentosAccesoCliente(String documentosAccesoCliente) {
		this.documentosAccesoCliente = documentosAccesoCliente;
	}

	public String getMaterialDidacticoCliente() {
		return materialDidacticoCliente;
	}

	public void setMaterialDidacticoCliente(String materialDidacticoCliente) {
		this.materialDidacticoCliente = materialDidacticoCliente;
	}

	public String getPautaGeneralCliente() {
		return pautaGeneralCliente;
	}

	public void setPautaGeneralCliente(String pautaGeneralCliente) {
		this.pautaGeneralCliente = pautaGeneralCliente;
	}

	public String getPautaEntregableCliente() {
		return pautaEntregableCliente;
	}

	public void setPautaEntregableCliente(String pautaEntregableCliente) {
		this.pautaEntregableCliente = pautaEntregableCliente;
	}

	public String getRepresentanteEmpresaCliente() {
		return representanteEmpresaCliente;
	}

	public void setRepresentanteEmpresaCliente(String representanteEmpresaCliente) {
		this.representanteEmpresaCliente = representanteEmpresaCliente;
	}

	public String getRepresentanteTrabajadorCliente() {
		return representanteTrabajadorCliente;
	}

	public void setRepresentanteTrabajadorCliente(String representanteTrabajadorCliente) {
		this.representanteTrabajadorCliente = representanteTrabajadorCliente;
	}

	public String getInformacionPaqueteriaCliente() {
		return informacionPaqueteriaCliente;
	}

	public void setInformacionPaqueteriaCliente(String informacionPaqueteriaCliente) {
		this.informacionPaqueteriaCliente = informacionPaqueteriaCliente;
	}

	public String getImagenLogoCliente() {
		return imagenLogoCliente;
	}

	public void setImagenLogoCliente(String imagenLogoCliente) {
		this.imagenLogoCliente = imagenLogoCliente;
	}

	public String getArchivosCliente() {
		return archivosCliente;
	}

	public void setArchivosCliente(String archivosCliente) {
		this.archivosCliente = archivosCliente;
	}


	






}
