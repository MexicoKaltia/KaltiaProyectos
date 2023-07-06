package mx.uniprotec.entidad.modelo;

import java.time.LocalDateTime;
import java.util.List;

public class DatosEconomicosModelo {

	public DatosEconomicosModelo() {
		// TODO Auto-generated constructor stub
	}
	
	private Long idDatosEconomicos;
	private Double ventaReal;
	private Double porcentajeVenta;
	private Double comisionReal;
	private Double porcentajeComision;
	private Double viaticosTotales;
	private String fechaPromesaPago;
	private String fechaPromesaPagoFormat;
	private String fechaConfirmacion;
	private String fechaConfirmacionFormat;
	private String observacion;
	private Long idAsignacion;
	private String status;
	private LocalDateTime createAtAsignacion;
	private Long userCreateAsignacion;
	private List<String> listFechaPromesaPago;
	private List<String> listFechaConfirmacion;
	private List<VendedorDEModelo> vendedores;
	private String vendedoresStr;
	private String numFactura;
	private int prospectoCliente;
	private String prospectoClienteTexto;
	private List<Integer> listAsignaciones;
	private String estatusDatoEconomico;
	private String fechaCambioEstatus;
	private String fechaPago;
	private String fechaPagoFormat;
	private String fechaEmision;
	private String fechaEmisionFormat;
	
	
	
	public Long getIdDatosEconomicos() {
		return idDatosEconomicos;
	}
	public void setIdDatosEconomicos(Long idDatosEconomicos) {
		this.idDatosEconomicos = idDatosEconomicos;
	}
	public Double getVentaReal() {
		return ventaReal;
	}
	public void setVentaReal(Double ventaReal) {
		this.ventaReal = ventaReal;
	}
	public Double getPorcentajeVenta() {
		return porcentajeVenta;
	}
	public void setPorcentajeVenta(Double porcentajeVenta) {
		this.porcentajeVenta = porcentajeVenta;
	}
	public Double getComisionReal() {
		return comisionReal;
	}
	public void setComisionReal(Double comisionReal) {
		this.comisionReal = comisionReal;
	}
	public Double getPorcentajeComision() {
		return porcentajeComision;
	}
	public void setPorcentajeComision(Double porcentajeComision) {
		this.porcentajeComision = porcentajeComision;
	}
	public Double getViaticosTotales() {
		return viaticosTotales;
	}
	public void setViaticosTotales(Double viaticosTotales) {
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
	
	public List<String> getListFechaPromesaPago() {
		return listFechaPromesaPago;
	}
	public void setListFechaPromesaPago(List<String> listFechaPromesaPago) {
		this.listFechaPromesaPago = listFechaPromesaPago;
	}
	public List<String> getListFechaConfirmacion() {
		return listFechaConfirmacion;
	}
	public void setListFechaConfirmacion(List<String> listFechaConfirmacion) {
		this.listFechaConfirmacion = listFechaConfirmacion;
	}
	public List<VendedorDEModelo> getVendedores() {
		return vendedores;
	}
	public void setVendedores(List<VendedorDEModelo> vendedores) {
		this.vendedores = vendedores;
	}
	
	public String getVendedoresStr() {
		return vendedoresStr;
	}
	public void setVendedoresStr(String vendedoresStr) {
		this.vendedoresStr = vendedoresStr;
	}
	
	
	public String getNumFactura() {
		return numFactura;
	}
	public void setNumFactura(String numFactura) {
		this.numFactura = numFactura;
	}
	
	public String getProspectoClienteTexto() {
		return prospectoClienteTexto;
	}
	public void setProspectoClienteTexto(String prospectoClienteTexto) {
		this.prospectoClienteTexto = prospectoClienteTexto;
	}
	
	@Override
	public String toString() {
		return "DatosEconomicosModelo [idDatosEconomicos=" + idDatosEconomicos + ", ventaReal=" + ventaReal
				+ ", porcentajeVenta=" + porcentajeVenta + ", comisionReal=" + comisionReal + ", porcentajeComision="
				+ porcentajeComision + ", viaticosTotales=" + viaticosTotales + ", fechaPromesaPago=" + fechaPromesaPago
				+ ", fechaPromesaPagoFormat=" + fechaPromesaPagoFormat + ", fechaConfirmacion=" + fechaConfirmacion
				+ ", fechaConfirmacionFormat=" + fechaConfirmacionFormat + ", observacion=" + observacion
				+ ", idAsignacion=" + idAsignacion + ", status=" + status + ", createAtAsignacion=" + createAtAsignacion
				+ ", userCreateAsignacion=" + userCreateAsignacion + ", listFechaPromesaPago=" + listFechaPromesaPago
				+ ", listFechaConfirmacion=" + listFechaConfirmacion + ", vendedores=" + vendedores + ", vendedoresStr="
				+ vendedoresStr + ", numFactura=" + numFactura + ", prospectoCliente=" + prospectoCliente
				+ ", prospectoClienteTexto=" + prospectoClienteTexto + ", listAsignaciones=" + listAsignaciones
				+ ", estatusDatoEconomico=" + estatusDatoEconomico + ", fechaCambioEstatus=" + fechaCambioEstatus
				+ ", fechaPago=" + fechaPago + "]";
	}
	
	public int getProspectoCliente() {
		return prospectoCliente;
	}
	public void setProspectoCliente(int prospectoCliente) {
		this.prospectoCliente = prospectoCliente;
	}
	public List<Integer> getListAsignaciones() {
		return listAsignaciones;
	}
	public void setListAsignaciones(List<Integer> listAsignaciones) {
		this.listAsignaciones = listAsignaciones;
	}
	public String getEstatusDatoEconomico() {
		return estatusDatoEconomico;
	}
	public void setEstatusDatoEconomico(String estatusDatoEconomico) {
		this.estatusDatoEconomico = estatusDatoEconomico;
	}
	public String getFechaCambioEstatus() {
		return fechaCambioEstatus;
	}
	public void setFechaCambioEstatus(String fechaCambioEstatus) {
		this.fechaCambioEstatus = fechaCambioEstatus;
	}
	public String getFechaPago() {
		return fechaPago;
	}
	public void setFechaPago(String fechaPago) {
		this.fechaPago = fechaPago;
	}
	public String getFechaPagoFormat() {
		return fechaPagoFormat;
	}
	public void setFechaPagoFormat(String fechaPagoFormat) {
		this.fechaPagoFormat = fechaPagoFormat;
	}
	public String getFechaEmision() {
		return fechaEmision;
	}
	public void setFechaEmision(String fechaEmision) {
		this.fechaEmision = fechaEmision;
	}
	public String getFechaEmisionFormat() {
		return fechaEmisionFormat;
	}
	public void setFechaEmisionFormat(String fechaEmisionFormat) {
		this.fechaEmisionFormat = fechaEmisionFormat;
	}

	
	
}
