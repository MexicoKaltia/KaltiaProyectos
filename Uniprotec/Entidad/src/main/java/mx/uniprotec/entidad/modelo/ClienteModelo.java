package mx.uniprotec.entidad.modelo;

import java.io.Serializable;
import java.time.LocalDateTime;

public class ClienteModelo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long idCliente;
	private String nombreCortoCliente;
	private String nombreCompletoCliente;
	private String rfcCliente;
	private String regionIdCliente;
	private Long idRegionCliente;
	private Region regionCliente;
	private String emailCliente;
	private String telefonoCliente;
	private String domicilioCliente;
	private String notaCliente;
	private Long userCreateCliente;
	private LocalDateTime createAtCliente;
	private String statusCliente;
	
	private String nombreContactoRecibeCliente;
	private String googleMapsCliente;
	private String pautaOperativaCliente;
	private String reglasAccesoCliente;
	private String documentosAccesoCliente;
	private String materialDidacticoCliente;
	private String pautaGeneralCliente;
	private String pautaEntregableCliente;
	private String representanteEmpresaCliente;
	private String representanteTrabajadorCliente;
	private String informacionPaqueteriaCliente;
	private String imagenLogoCliente;
	private String archivosCliente;
	
	private Long idVendedorCliente;
	private VendedorModelo vendedorCliente;

	
	
	public ClienteModelo() {}


	public ClienteModelo(Long idCliente) {	this.idCliente = idCliente;	}


	@Override
	public String toString() {
		return "ClienteModelo [idCliente=" + idCliente + ", nombreCortoCliente=" + nombreCortoCliente
				+ ", nombreCompletoCliente=" + nombreCompletoCliente + ", rfcCliente=" + rfcCliente
				+ ", regionIdCliente=" + regionIdCliente + ", idRegionCliente=" + idRegionCliente + ", regionCliente="
				+ regionCliente + ", emailCliente=" + emailCliente + ", telefonoCliente=" + telefonoCliente
				+ ", domicilioCliente=" + domicilioCliente + ", notaCliente=" + notaCliente + ", userCreateCliente="
				+ userCreateCliente + ", createAtCliente=" + createAtCliente + ", statusCliente=" + statusCliente
				+ ", nombreContactoRecibeCliente=" + nombreContactoRecibeCliente + ", googleMapsCliente="
				+ googleMapsCliente + ", pautaOperativaCliente=" + pautaOperativaCliente + ", reglasAccesoCliente="
				+ reglasAccesoCliente + ", documentosAccesoCliente=" + documentosAccesoCliente
				+ ", materialDidacticoCliente=" + materialDidacticoCliente + ", pautaGeneralCliente="
				+ pautaGeneralCliente + ", pautaEntregableCliente=" + pautaEntregableCliente
				+ ", representanteEmpresaCliente=" + representanteEmpresaCliente + ", representanteTrabajadorCliente="
				+ representanteTrabajadorCliente + ", informacionPaqueteriaCliente=" + informacionPaqueteriaCliente
				+ ", imagenLogoCliente=" + imagenLogoCliente + ", archivosCliente=" + archivosCliente
				+ ", idVendedorCliente=" + idVendedorCliente + "]";
	}


	public ClienteModelo(Long idCliente, String nombreCortoCliente, String nombreCompletoCliente, String rfcCliente,
			String regionIdCliente, Long idRegionCliente, Region regionCliente, String emailCliente,
			String telefonoCliente, String domicilioCliente, String notaCliente, Long userCreateCliente,
			LocalDateTime createAtCliente, String statusCliente, String nombreContactoRecibeCliente,
			String googleMapsCliente, String pautaOperativaCliente, String reglasAccesoCliente,
			String documentosAccesoCliente, String materialDidacticoCliente, String pautaGeneralCliente,
			String pautaEntregableCliente, String representanteEmpresaCliente, String representanteTrabajadorCliente,
			String informacionPaqueteriaCliente, String imagenLogoCliente, String archivosCliente,
			Long idVendedorCliente) {
		super();
		this.idCliente = idCliente;
		this.nombreCortoCliente = nombreCortoCliente;
		this.nombreCompletoCliente = nombreCompletoCliente;
		this.rfcCliente = rfcCliente;
		this.regionIdCliente = regionIdCliente;
		this.idRegionCliente = idRegionCliente;
		this.regionCliente = regionCliente;
		this.emailCliente = emailCliente;
		this.telefonoCliente = telefonoCliente;
		this.domicilioCliente = domicilioCliente;
		this.notaCliente = notaCliente;
		this.userCreateCliente = userCreateCliente;
		this.createAtCliente = createAtCliente;
		this.statusCliente = statusCliente;
		this.nombreContactoRecibeCliente = nombreContactoRecibeCliente;
		this.googleMapsCliente = googleMapsCliente;
		this.pautaOperativaCliente = pautaOperativaCliente;
		this.reglasAccesoCliente = reglasAccesoCliente;
		this.documentosAccesoCliente = documentosAccesoCliente;
		this.materialDidacticoCliente = materialDidacticoCliente;
		this.pautaGeneralCliente = pautaGeneralCliente;
		this.pautaEntregableCliente = pautaEntregableCliente;
		this.representanteEmpresaCliente = representanteEmpresaCliente;
		this.representanteTrabajadorCliente = representanteTrabajadorCliente;
		this.informacionPaqueteriaCliente = informacionPaqueteriaCliente;
		this.imagenLogoCliente = imagenLogoCliente;
		this.archivosCliente = archivosCliente;
		this.idVendedorCliente = idVendedorCliente;
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


	public String getRegionIdCliente() {
		return regionIdCliente;
	}


	public void setRegionIdCliente(String regionIdCliente) {
		this.regionIdCliente = regionIdCliente;
	}


	public Long getIdRegionCliente() {
		return idRegionCliente;
	}


	public void setIdRegionCliente(Long idRegionCliente) {
		this.idRegionCliente = idRegionCliente;
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


	public Long getIdVendedorCliente() {
		return idVendedorCliente;
	}


	public void setIdVendedorCliente(Long idVendedorCliente) {
		this.idVendedorCliente = idVendedorCliente;
	}


	public VendedorModelo getVendedorCliente() {
		return vendedorCliente;
	}


	public void setVendedorCliente(VendedorModelo vendedorCliente) {
		this.vendedorCliente = vendedorCliente;
	}
	
	

		
	
	
	
}
