package mx.uniprotec.application.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name="preAsignacionesAE")
@JsonIdentityInfo(
		  generator = ObjectIdGenerators.PropertyGenerator.class, 
		  property = "idPreAsignacionAE")
public class DatosEconomicosEntity {

	public DatosEconomicosEntity() {
		// TODO Auto-generated constructor stub
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
		private Long idPreAsignacionAE;
	@Column(nullable=false)
		private Long formAEidPreAsignacion;
	@Column(nullable=false)
		private String formAENumFactura;
	@Column(nullable=false)
		private String formAEListAsignaciones;
	@Column(nullable=false)
		private int formAECliente;
	@Column(nullable=false)
		private String formAEClienteTexto;
	@Column(nullable=false)
		private Double formAEPrecioVentaReal;
	@Column(nullable=false)
		private Double formAEPorcentajeVentaReal;
	@Column(nullable=false)
		private String formAEFechaConfirmacion;
	@Column(nullable=false)
		private String formAEFechaConfirmacionFormat;
	@Column(nullable=false)
		private String formAEFechaPromesaPago;
	@Column(nullable=false)
		private String formAEFechaPromesaPagoFormat;
	@Column(nullable=false)
		private String formAEListFechaConfirmacion;
	@Column(nullable=false)
		private String formAEListFechaPromesaPago;
	@Column(nullable=false)
		private Double formAEViaticosTotal;
	@Column(nullable=false)
	private String formAEObservaciones;

	@Column(nullable=false)	
		private LocalDateTime createAt;
	@Column(nullable=false)
		private Long userCreate;
	@Column(nullable=false)
		private String userCreateTexto;
	@Column(nullable=false)
		private String status;

	@Column
		private String estatusDatoEconomico;
	@Column
		private String fechaCambioEstatus;
	@Column(nullable=false)
		private String fechaPago;
	@Column(nullable=false)
		private String fechaPagoFormat;
	@Column(nullable=false)
		private String fechaEmision;
	@Column(nullable=false)
		private String fechaEmisionFormat;
	
	//no se ocupan
	@Column(nullable=false)
		private String formAEFechaCotizacion;
	@Column(nullable=false)
		private int formAENuevaComisionReal;
	
	
	
	public Long getIdPreAsignacionAE() {
		return idPreAsignacionAE;
	}
	public void setIdPreAsignacionAE(Long idPreAsignacionAE) {
		this.idPreAsignacionAE = idPreAsignacionAE;
	}
	public Long getFormAEidPreAsignacion() {
		return formAEidPreAsignacion;
	}
	public void setFormAEidPreAsignacion(Long formAEidPreAsignacion) {
		this.formAEidPreAsignacion = formAEidPreAsignacion;
	}
	public String getFormAENumFactura() {
		return formAENumFactura;
	}
	public void setFormAENumFactura(String formAENumFactura) {
		this.formAENumFactura = formAENumFactura;
	}
	public int getFormAECliente() {
		return formAECliente;
	}
	public void setFormAECliente(int formAECliente) {
		this.formAECliente = formAECliente;
	}
	public String getFormAEClienteTexto() {
		return formAEClienteTexto;
	}
	public void setFormAEClienteTexto(String formAEClienteTexto) {
		this.formAEClienteTexto = formAEClienteTexto;
	}
	public Double getFormAEPrecioVentaReal() {
		return formAEPrecioVentaReal;
	}
	public void setFormAEPrecioVentaReal(Double formAEPrecioVentaReal) {
		this.formAEPrecioVentaReal = formAEPrecioVentaReal;
	}
	public Double getFormAEPorcentajeVentaReal() {
		return formAEPorcentajeVentaReal;
	}
	public void setFormAEPorcentajeVentaReal(Double formAEPorcentajeVentaReal) {
		this.formAEPorcentajeVentaReal = formAEPorcentajeVentaReal;
	}
	public String getFormAEFechaConfirmacion() {
		return formAEFechaConfirmacion;
	}
	public void setFormAEFechaConfirmacion(String formAEFechaConfirmacion) {
		this.formAEFechaConfirmacion = formAEFechaConfirmacion;
	}
	public String getFormAEFechaConfirmacionFormat() {
		return formAEFechaConfirmacionFormat;
	}
	public void setFormAEFechaConfirmacionFormat(String formAEFechaConfirmacionFormat) {
		this.formAEFechaConfirmacionFormat = formAEFechaConfirmacionFormat;
	}
	public String getFormAEFechaPromesaPago() {
		return formAEFechaPromesaPago;
	}
	public void setFormAEFechaPromesaPago(String formAEFechaPromesaPago) {
		this.formAEFechaPromesaPago = formAEFechaPromesaPago;
	}
	public String getFormAEFechaPromesaPagoFormat() {
		return formAEFechaPromesaPagoFormat;
	}
	public void setFormAEFechaPromesaPagoFormat(String formAEFechaPromesaPagoFormat) {
		this.formAEFechaPromesaPagoFormat = formAEFechaPromesaPagoFormat;
	}
	public String getFormAEListFechaConfirmacion() {
		return formAEListFechaConfirmacion;
	}
	public void setFormAEListFechaConfirmacion(String formAEListFechaConfirmacion) {
		this.formAEListFechaConfirmacion = formAEListFechaConfirmacion;
	}
	public String getFormAEListFechaPromesaPago() {
		return formAEListFechaPromesaPago;
	}
	public void setFormAEListFechaPromesaPago(String formAEListFechaPromesaPago) {
		this.formAEListFechaPromesaPago = formAEListFechaPromesaPago;
	}
	public Double getFormAEViaticosTotal() {
		return formAEViaticosTotal;
	}
	public void setFormAEViaticosTotal(Double formAEViaticosTotal) {
		this.formAEViaticosTotal = formAEViaticosTotal;
	}
	public String getFormAEObservaciones() {
		return formAEObservaciones;
	}
	public void setFormAEObservaciones(String formAEObservaciones) {
		this.formAEObservaciones = formAEObservaciones;
	}
	public LocalDateTime getCreateAt() {
		return createAt;
	}
	public void setCreateAt(LocalDateTime createAt) {
		this.createAt = createAt;
	}
	public Long getUserCreate() {
		return userCreate;
	}
	public void setUserCreate(Long userCreate) {
		this.userCreate = userCreate;
	}
	public String getUserCreateTexto() {
		return userCreateTexto;
	}
	public void setUserCreateTexto(String userCreateTexto) {
		this.userCreateTexto = userCreateTexto;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getFormAEFechaCotizacion() {
		return formAEFechaCotizacion;
	}
	public void setFormAEFechaCotizacion(String formAEFechaCotizacion) {
		this.formAEFechaCotizacion = formAEFechaCotizacion;
	}
	public int getFormAENuevaComisionReal() {
		return formAENuevaComisionReal;
	}
	public void setFormAENuevaComisionReal(int formAENuevaComisionReal) {
		this.formAENuevaComisionReal = formAENuevaComisionReal;
	}
	public String getFormAEListAsignaciones() {
		return formAEListAsignaciones;
	}
	public void setFormAEListAsignaciones(String formAEListAsignaciones) {
		this.formAEListAsignaciones = formAEListAsignaciones;
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

