package mx.uniprotec.application.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="clienteProspecto")
public class ClienteProspectoEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public ClienteProspectoEntity() {
		// TODO Auto-generated constructor stub
	}
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idClienteProspecto;
	@Column
	private String nombreCortoClienteProspecto;
	@Column
	private String nombreCompletoClienteProspecto;
	@Column
	private int idRegionClienteProspecto;
	@Column
	private String nombreRegionClienteProspecto;
	@Column
	private String rfcClienteProspecto;
	@Column
	private String direccionClienteProspecto;
	@Column
	private Long idPreAsignacion;
	@Column
	private Long userCreate;
	@Column
	private String status;
	@Column
	private LocalDateTime createAt;
	@Override
	public String toString() {
		return "ClienteProspectoEntity [idClienteProspecto=" + idClienteProspecto + ", nombreCortoClienteProspecto="
				+ nombreCortoClienteProspecto + ", nombreCompletoClienteProspecto=" + nombreCompletoClienteProspecto
				+ ", idRegionClienteProspecto=" + idRegionClienteProspecto + ", nombreRegionClienteProspecto="
				+ nombreRegionClienteProspecto + ", rfcClienteProspecto=" + rfcClienteProspecto
				+ ", direccionClienteProspecto=" + direccionClienteProspecto + ", idPreAsignacion=" + idPreAsignacion
				+ ", userCreate=" + userCreate + ", status=" + status + ", createAt=" + createAt + "]";
	}
	public ClienteProspectoEntity(Long idClienteProspecto, String nombreCortoClienteProspecto,
			String nombreCompletoClienteProspecto, int idRegionClienteProspecto, String nombreRegionClienteProspecto,
			String rfcClienteProspecto, String direccionClienteProspecto, Long idPreAsignacion, Long userCreate,
			String status, LocalDateTime createAt) {
		super();
		this.idClienteProspecto = idClienteProspecto;
		this.nombreCortoClienteProspecto = nombreCortoClienteProspecto;
		this.nombreCompletoClienteProspecto = nombreCompletoClienteProspecto;
		this.idRegionClienteProspecto = idRegionClienteProspecto;
		this.nombreRegionClienteProspecto = nombreRegionClienteProspecto;
		this.rfcClienteProspecto = rfcClienteProspecto;
		this.direccionClienteProspecto = direccionClienteProspecto;
		this.idPreAsignacion = idPreAsignacion;
		this.userCreate = userCreate;
		this.status = status;
		this.createAt = createAt;
	}
	public Long getIdClienteProspecto() {
		return idClienteProspecto;
	}
	public void setIdClienteProspecto(Long idClienteProspecto) {
		this.idClienteProspecto = idClienteProspecto;
	}
	public String getNombreCortoClienteProspecto() {
		return nombreCortoClienteProspecto;
	}
	public void setNombreCortoClienteProspecto(String nombreCortoClienteProspecto) {
		this.nombreCortoClienteProspecto = nombreCortoClienteProspecto;
	}
	public String getNombreCompletoClienteProspecto() {
		return nombreCompletoClienteProspecto;
	}
	public void setNombreCompletoClienteProspecto(String nombreCompletoClienteProspecto) {
		this.nombreCompletoClienteProspecto = nombreCompletoClienteProspecto;
	}
	public int getIdRegionClienteProspecto() {
		return idRegionClienteProspecto;
	}
	public void setIdRegionClienteProspecto(int idRegionClienteProspecto) {
		this.idRegionClienteProspecto = idRegionClienteProspecto;
	}
	public String getNombreRegionClienteProspecto() {
		return nombreRegionClienteProspecto;
	}
	public void setNombreRegionClienteProspecto(String nombreRegionClienteProspecto) {
		this.nombreRegionClienteProspecto = nombreRegionClienteProspecto;
	}
	public String getRfcClienteProspecto() {
		return rfcClienteProspecto;
	}
	public void setRfcClienteProspecto(String rfcClienteProspecto) {
		this.rfcClienteProspecto = rfcClienteProspecto;
	}
	public String getDireccionClienteProspecto() {
		return direccionClienteProspecto;
	}
	public void setDireccionClienteProspecto(String direccionClienteProspecto) {
		this.direccionClienteProspecto = direccionClienteProspecto;
	}
	public Long getIdPreAsignacion() {
		return idPreAsignacion;
	}
	public void setIdPreAsignacion(Long idPreAsignacion) {
		this.idPreAsignacion = idPreAsignacion;
	}
	public Long getUserCreate() {
		return userCreate;
	}
	public void setUserCreate(Long userCreate) {
		this.userCreate = userCreate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public LocalDateTime getCreateAt() {
		return createAt;
	}
	public void setCreateAt(LocalDateTime createAt) {
		this.createAt = createAt;
	}
	
	
	

}
