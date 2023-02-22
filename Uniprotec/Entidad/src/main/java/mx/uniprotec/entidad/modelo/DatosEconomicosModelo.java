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
				+ ", prospectoClienteTexto=" + prospectoClienteTexto + ", listAsignaciones=" + listAsignaciones + "]";
	}
	public DatosEconomicosModelo(Long idDatosEconomicos, Double ventaReal, Double porcentajeVenta, Double comisionReal,
			Double porcentajeComision, Double viaticosTotales, String fechaPromesaPago, String fechaPromesaPagoFormat,
			String fechaConfirmacion, String fechaConfirmacionFormat, String observacion, Long idAsignacion,
			String status, LocalDateTime createAtAsignacion, Long userCreateAsignacion,
			List<String> listFechaPromesaPago, List<String> listFechaConfirmacion, List<VendedorDEModelo> vendedores,
			String vendedoresStr, String numFactura, int prospectoCliente, String prospectoClienteTexto) {
		super();
		this.idDatosEconomicos = idDatosEconomicos;
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
		this.listFechaPromesaPago = listFechaPromesaPago;
		this.listFechaConfirmacion = listFechaConfirmacion;
		this.vendedores = vendedores;
		this.vendedoresStr = vendedoresStr;
		this.numFactura = numFactura;
		this.prospectoCliente = prospectoCliente;
		this.prospectoClienteTexto = prospectoClienteTexto;
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

	
	
}
