package mx.uniprotec.entidad.modelo;

import java.time.LocalDateTime;

public class DatosEconomicosModelo {

	public DatosEconomicosModelo() {
		// TODO Auto-generated constructor stub
	}
	
	private Integer ventaReal;
	private Integer porcentajeVenta;
	private Integer comisionReal;
	private Integer porcentajeComision;
	private Integer viaticosTotales;
	private String fechaPromesaPago;
	private String fechaPromesaPagoFormat;
	private String fechaConfirmacion;
	private String fechaConfirmacionFormat;
	private String observacion;
	private Long idAsignacion;
	private String status;
	private LocalDateTime createAtAsignacion;
	private Long userCreateAsignacion;

	
	public Integer getVentaReal() {
		return ventaReal;
	}
	public void setVentaReal(Integer ventaReal) {
		this.ventaReal = ventaReal;
	}
	public Integer getPorcentajeVenta() {
		return porcentajeVenta;
	}
	public void setPorcentajeVenta(Integer porcentajeVenta) {
		this.porcentajeVenta = porcentajeVenta;
	}
	public Integer getComisionReal() {
		return comisionReal;
	}
	public void setComisionReal(Integer comisionReal) {
		this.comisionReal = comisionReal;
	}
	public Integer getPorcentajeComision() {
		return porcentajeComision;
	}
	public void setPorcentajeComision(Integer porcentajeComision) {
		this.porcentajeComision = porcentajeComision;
	}
	public Integer getViaticosTotales() {
		return viaticosTotales;
	}
	public void setViaticosTotales(Integer viaticosTotales) {
		this.viaticosTotales = viaticosTotales;
	}
	public String getFechaPromesaPago() {
		return fechaPromesaPago;
	}
	public void setFechaPromesaPago(String fechaPromesaPago) {
		this.fechaPromesaPago = fechaPromesaPago;
	}
	
	public String getFechaPromesaPagoFormat() {
		return fechaPromesaPagoFormat;
	}
	public void setFechaPromesaPagoFormat(String fechaPromesaPagoFormat) {
		this.fechaPromesaPagoFormat = fechaPromesaPagoFormat;
	}
	
	public Long getIdAsignacion() {
		return idAsignacion;
	}
	public void setIdAsignacion(Long idAsignacion) {
		this.idAsignacion = idAsignacion;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public LocalDateTime getCreateAtAsignacion() {
		return createAtAsignacion;
	}
	public void setCreateAtAsignacion(LocalDateTime createAtAsignacion) {
		this.createAtAsignacion = createAtAsignacion;
	}
	public Long getUserCreateAsignacion() {
		return userCreateAsignacion;
	}
	public void setUserCreateAsignacion(Long userCreateAsignacion) {
		this.userCreateAsignacion = userCreateAsignacion;
	}
	public String getFechaConfirmacion() {
		return fechaConfirmacion;
	}
	public void setFechaConfirmacion(String fechaConfirmacion) {
		this.fechaConfirmacion = fechaConfirmacion;
	}
	public String getFechaConfirmacionFormat() {
		return fechaConfirmacionFormat;
	}
	public void setFechaConfirmacionFormat(String fechaConfirmacionFormat) {
		this.fechaConfirmacionFormat = fechaConfirmacionFormat;
	}
	public String getObservacion() {
		return observacion;
	}
	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}
	@Override
	public String toString() {
		return "DatosEconomicosModelo [ventaReal=" + ventaReal + ", porcentajeVenta=" + porcentajeVenta
				+ ", comisionReal=" + comisionReal + ", porcentajeComision=" + porcentajeComision + ", viaticosTotales="
				+ viaticosTotales + ", fechaPromesaPago=" + fechaPromesaPago + ", fechaPromesaPagoFormat="
				+ fechaPromesaPagoFormat + ", fechaConfirmacion=" + fechaConfirmacion + ", fechaConfirmacionFormat="
				+ fechaConfirmacionFormat + ", observacion=" + observacion + ", idAsignacion=" + idAsignacion
				+ ", status=" + status + ", createAtAsignacion=" + createAtAsignacion + ", userCreateAsignacion="
				+ userCreateAsignacion + "]";
	}
	public DatosEconomicosModelo(Integer ventaReal, Integer porcentajeVenta, Integer comisionReal,
			Integer porcentajeComision, Integer viaticosTotales, String fechaPromesaPago, String fechaPromesaPagoFormat,
			String fechaConfirmacion, String fechaConfirmacionFormat, String observacion, Long idAsignacion,
			String status, LocalDateTime createAtAsignacion, Long userCreateAsignacion) {
		super();
		this.ventaReal = ventaReal;
		this.porcentajeVenta = porcentajeVenta;
		this.comisionReal = comisionReal;
		this.porcentajeComision = porcentajeComision;
		this.viaticosTotales = viaticosTotales;
		this.fechaPromesaPago = fechaPromesaPago;
		this.fechaPromesaPagoFormat = fechaPromesaPagoFormat;
		this.fechaConfirmacion = fechaConfirmacion;
		this.fechaConfirmacionFormat = fechaConfirmacionFormat;
		this.observacion = observacion;
		this.idAsignacion = idAsignacion;
		this.status = status;
		this.createAtAsignacion = createAtAsignacion;
		this.userCreateAsignacion = userCreateAsignacion;
	}
	

		

}
