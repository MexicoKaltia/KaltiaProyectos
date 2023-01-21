package mx.uniprotec.entidad.modelo;

import java.io.Serializable;


public class VendedorDEModelo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long idVendedorDE;
	private Long idVendedorAsignacion;
	private Long idAsignacion;
	private Long idDatosEconomicos;
	private String nombreVendedor;
	private Double comisionRealVendedor;
	private Double porcentajeComisionVendedor;
	private Double montoFacturaDivida;
	
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
	@Override
	public String toString() {
		return "VendedorDEModelo [idVendedorDE=" + idVendedorDE + ", idVendedorAsignacion=" + idVendedorAsignacion
				+ ", idAsignacion=" + idAsignacion + ", idDatosEconomicos=" + idDatosEconomicos + ", nombreVendedor="
				+ nombreVendedor + ", comisionRealVendedor=" + comisionRealVendedor + ", porcentajeComisionVendedor="
				+ porcentajeComisionVendedor + ", montoFacturaDivida=" + montoFacturaDivida + "]";
	}
	public VendedorDEModelo(Long idVendedorDE, Long idVendedorAsignacion, Long idAsignacion, Long idDatosEconomicos,
			String nombreVendedor, Double comisionRealVendedor, Double porcentajeComisionVendedor,
			Double montoFacturaDivida) {
		super();
		this.idVendedorDE = idVendedorDE;
		this.idVendedorAsignacion = idVendedorAsignacion;
		this.idAsignacion = idAsignacion;
		this.idDatosEconomicos = idDatosEconomicos;
		this.nombreVendedor = nombreVendedor;
		this.comisionRealVendedor = comisionRealVendedor;
		this.porcentajeComisionVendedor = porcentajeComisionVendedor;
		this.montoFacturaDivida = montoFacturaDivida;
	}
	public VendedorDEModelo() {
		// TODO Auto-generated constructor stub
	}
	
	
	
}
