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
@Table(name="vendedoresDE")
public class VendedorDatosEconomicos implements Serializable{

	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */

	public VendedorDatosEconomicos() {
		
	}
	

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idVendedorDE;
	@Column
	private Long idVendedorAsignacion;
	@Column
	private Long idAsignacion;
	@Column
	private Long idDatosEconomicos;
	@Column
	private String nombreVendedor;
	@Column
	private Double comisionRealVendedor;
	@Column
	private Double porcentajeComisionVendedor;
	@Column
	private Double montoFacturaDivida;
	
	@Column
	private Long userCreateVendedor;
	@Column
	private LocalDateTime createAtVendedor;
	@Column
	private String statusVendedor;
	public Long getIdVendedorDE() {
		return idVendedorDE;
	}
	public void setIdVendedorDE(Long idVendedorDE) {
		this.idVendedorDE = idVendedorDE;
	}
	public Long getIdVendedorAsignacion() {
		return idVendedorAsignacion;
	}
	public void setIdVendedorAsignacion(Long idVendedorAsignacion) {
		this.idVendedorAsignacion = idVendedorAsignacion;
	}
	public Long getIdAsignacion() {
		return idAsignacion;
	}
	public void setIdAsignacion(Long idAsignacion) {
		this.idAsignacion = idAsignacion;
	}
	public Long getIdDatosEconomicos() {
		return idDatosEconomicos;
	}
	public void setIdDatosEconomicos(Long idDatosEconomicos) {
		this.idDatosEconomicos = idDatosEconomicos;
	}
	public String getNombreVendedor() {
		return nombreVendedor;
	}
	public void setNombreVendedor(String nombreVendedor) {
		this.nombreVendedor = nombreVendedor;
	}
	public Double getComisionRealVendedor() {
		return comisionRealVendedor;
	}
	public void setComisionRealVendedor(Double comisionRealVendedor) {
		this.comisionRealVendedor = comisionRealVendedor;
	}
	public Double getPorcentajeComisionVendedor() {
		return porcentajeComisionVendedor;
	}
	public void setPorcentajeComisionVendedor(Double porcentajeComisionVendedor) {
		this.porcentajeComisionVendedor = porcentajeComisionVendedor;
	}
	public Double getMontoFacturaDivida() {
		return montoFacturaDivida;
	}
	public void setMontoFacturaDivida(Double montoFacturaDivida) {
		this.montoFacturaDivida = montoFacturaDivida;
	}
	public Long getUserCreateVendedor() {
		return userCreateVendedor;
	}
	public void setUserCreateVendedor(Long userCreateVendedor) {
		this.userCreateVendedor = userCreateVendedor;
	}
	public LocalDateTime getCreateAtVendedor() {
		return createAtVendedor;
	}
	public void setCreateAtVendedor(LocalDateTime createAtVendedor) {
		this.createAtVendedor = createAtVendedor;
	}
	public String getStatusVendedor() {
		return statusVendedor;
	}
	public void setStatusVendedor(String statusVendedor) {
		this.statusVendedor = statusVendedor;
	}
	@Override
	public String toString() {
		return "VendedorDatosEconomicos [idVendedorDE=" + idVendedorDE + ", idVendedorAsignacion="
				+ idVendedorAsignacion + ", idAsignacion=" + idAsignacion + ", idDatosEconomicos=" + idDatosEconomicos
				+ ", nombreVendedor=" + nombreVendedor + ", comisionRealVendedor=" + comisionRealVendedor
				+ ", porcentajeComisionVendedor=" + porcentajeComisionVendedor + ", montoFacturaDivida="
				+ montoFacturaDivida + ", userCreateVendedor=" + userCreateVendedor + ", createAtVendedor="
				+ createAtVendedor + ", statusVendedor=" + statusVendedor + "]";
	}
	public VendedorDatosEconomicos(Long idVendedorDE, Long idVendedorAsignacion, Long idAsignacion,
			Long idDatosEconomicos, String nombreVendedor, Double comisionRealVendedor,
			Double porcentajeComisionVendedor, Double montoFacturaDivida, Long userCreateVendedor,
			LocalDateTime createAtVendedor, String statusVendedor) {
		super();
		this.idVendedorDE = idVendedorDE;
		this.idVendedorAsignacion = idVendedorAsignacion;
		this.idAsignacion = idAsignacion;
		this.idDatosEconomicos = idDatosEconomicos;
		this.nombreVendedor = nombreVendedor;
		this.comisionRealVendedor = comisionRealVendedor;
		this.porcentajeComisionVendedor = porcentajeComisionVendedor;
		this.montoFacturaDivida = montoFacturaDivida;
		this.userCreateVendedor = userCreateVendedor;
		this.createAtVendedor = createAtVendedor;
		this.statusVendedor = statusVendedor;
	}

		
	
}
