package mx.uniprotec.application.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

//@Entity
//@Table(name="preAsignacionesAE")
//@JsonIdentityInfo(
//		  generator = ObjectIdGenerators.PropertyGenerator.class, 
//		  property = "idPreAsignacionAE")
public class PreAsignacionAEEntity {

	public PreAsignacionAEEntity() {
		// TODO Auto-generated constructor stub
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
		private Long idPreAsignacionAE;
	@Column(nullable=false)
		private String formAECurso;
	@Column(nullable=false)
		private int formAECliente;
	@Column(nullable=false)
	private String formAEClienteTexto;
	@Column(nullable=false)
		private int formAEHorasEfectivas;
	@Column(nullable=false)
		private int formAESesiones;
	@Column(nullable=false)
		private int formAEParticipantes;
	@Column(nullable=false)
		private String formAEFechaCotizacion;
	@Column(nullable=false)
		private String formAESede;
	@Column(nullable=false)
		private String formAENivelCurso;
	
	@Column(nullable=false)
		private int formAENumInstructor;
	@Column(nullable=false)
		private int formAETotalHoras;
	@Column(nullable=false)
		private int formAECostoHoraInstructor;
	@Column(nullable=false)
		private int formAETotalImparticion;
	@Column(nullable=false)
		private Double formAEViaticosTotal;

	@Column(nullable=false)
		private int formAESumaImparticionViaticos;
	@Column(nullable=false)
		private int formAECostoCursoRecomendado;
	@Column(nullable=false)
		private int formAECostoHoraRecomendada;
	
	@Column(nullable=false)
		private int formAEImparticion;
	@Column(nullable=false)
		private int formAEImparticionPorcentaje;
	@Column(nullable=false)
		private Double formAEComisionVendedor;
	@Column(nullable=false)
		private Double formAEComisionVendedorPorcentaje;
	@Column(nullable=false)
		private int formAEViaticos;
	@Column(nullable=false)
		private int formAEViaticosPorcentaje;
	@Column(nullable=false)
		private int formAEGastosFijos;
	@Column(nullable=false)
		private int formAEGastosFijosPorcentaje;
	@Column(nullable=false)
		private int formAEGananciaCurso;
	@Column(nullable=false)
		private int formAEGananciaCursoPorcentaje;
	@Column(nullable=false)
		private int formAETotales;
	@Column(nullable=false)
		private int formAETotalesPorcentaje;
	@Column(nullable=false)
		private Double formAEPrecioVentaReal;
	@Column(nullable=false)
		private int formAEComisionVendedorReal;
	@Column(nullable=false)
		private int formAEGastosFijosReal;
	@Column(nullable=false)
		private int formAEUtilidadReal;
	@Column(nullable=false)
		private int formAENuevaComisionReal;
	
	@Column(nullable=false)
		private Double formAERegla3PorcentajeNuevaComisionReal;
	@Column(nullable=false)
		private int formAERegla3PorcentajeNuevaComision;

	@Column(nullable=false)
		private String formAEObservaciones;
	
	@Column(nullable=false)	
		private String formAEidPreAsignacionLogica;
	@Column(nullable=false)
		private Long formAEidPreAsignacion;
	
	@Column(nullable=false)	
		private LocalDateTime createAt;
	@Column(nullable=false)
		private Long userCreate;
	@Column(nullable=false)
		private String userCreateTexto;
	@Column(nullable=false)
		private String status;
	@Column(nullable=false)
		private String formAEFechaPromesaPago;
	@Column(nullable=false)
		private String formAEFechaPromesaPagoFormat;
	@Column(nullable=false)
		private String formAEFechaConfirmacion;
	@Column(nullable=false)
		private String formAEFechaConfirmacionFormat;
	@Column(nullable=false)
		private String formAEFechaPago;
	@Column(nullable=false)
		private String formAEFechaPagoFormat;
	@Column(nullable=false)
		private String formAEFechaEmision;
	@Column(nullable=false)
		private String formAEFechaEmisionFormat;
	@Column(nullable=false)
		private String formAEListFechaPromesaPago;
	@Column(nullable=false)
		private String formAEListFechaConfirmacion;
	@Column(nullable=false)
		private String formAENumFactura;
	
	
	
	public Long getIdPreAsignacionAE() {
		return idPreAsignacionAE;
	}
	public void setIdPreAsignacionAE(Long idPreAsignacionAE) {
		this.idPreAsignacionAE = idPreAsignacionAE;
	}
	public String getFormAECurso() {
		return formAECurso;
	}
	public void setFormAECurso(String formAECurso) {
		this.formAECurso = formAECurso;
	}
	
	public int getFormAEHorasEfectivas() {
		return formAEHorasEfectivas;
	}
	public void setFormAEHorasEfectivas(int formAEHorasEfectivas) {
		this.formAEHorasEfectivas = formAEHorasEfectivas;
	}
	public int getFormAESesiones() {
		return formAESesiones;
	}
	public void setFormAESesiones(int formAESesiones) {
		this.formAESesiones = formAESesiones;
	}
	public int getFormAEParticipantes() {
		return formAEParticipantes;
	}
	public void setFormAEParticipantes(int formAEParticipantes) {
		this.formAEParticipantes = formAEParticipantes;
	}
	public String getFormAEFechaCotizacion() {
		return formAEFechaCotizacion;
	}
	public void setFormAEFechaCotizacion(String formAEFechaCotizacion) {
		this.formAEFechaCotizacion = formAEFechaCotizacion;
	}
	public String getFormAESede() {
		return formAESede;
	}
	public void setFormAESede(String formAESede) {
		this.formAESede = formAESede;
	}
	public String getFormAENivelCurso() {
		return formAENivelCurso;
	}
	public void setFormAENivelCurso(String formAENivelCurso) {
		this.formAENivelCurso = formAENivelCurso;
	}
	public int getFormAENumInstructor() {
		return formAENumInstructor;
	}
	public void setFormAENumInstructor(int formAENumInstructor) {
		this.formAENumInstructor = formAENumInstructor;
	}
	public int getFormAETotalHoras() {
		return formAETotalHoras;
	}
	public void setFormAETotalHoras(int formAETotalHoras) {
		this.formAETotalHoras = formAETotalHoras;
	}
	public int getFormAECostoHoraInstructor() {
		return formAECostoHoraInstructor;
	}
	public void setFormAECostoHoraInstructor(int formAECostoHoraInstructor) {
		this.formAECostoHoraInstructor = formAECostoHoraInstructor;
	}
	public int getFormAETotalImparticion() {
		return formAETotalImparticion;
	}
	public void setFormAETotalImparticion(int formAETotalImparticion) {
		this.formAETotalImparticion = formAETotalImparticion;
	}
	public Double getFormAEViaticosTotal() {
		return formAEViaticosTotal;
	}
	public void setFormAEViaticosTotal(Double formAEViaticosTotal) {
		this.formAEViaticosTotal = formAEViaticosTotal;
	}
	public int getFormAESumaImparticionViaticos() {
		return formAESumaImparticionViaticos;
	}
	public void setFormAESumaImparticionViaticos(int formAESumaImparticionViaticos) {
		this.formAESumaImparticionViaticos = formAESumaImparticionViaticos;
	}
	public int getFormAECostoCursoRecomendado() {
		return formAECostoCursoRecomendado;
	}
	public void setFormAECostoCursoRecomendado(int formAECostoCursoRecomendado) {
		this.formAECostoCursoRecomendado = formAECostoCursoRecomendado;
	}
	public int getFormAECostoHoraRecomendada() {
		return formAECostoHoraRecomendada;
	}
	public void setFormAECostoHoraRecomendada(int formAECostoHoraRecomendada) {
		this.formAECostoHoraRecomendada = formAECostoHoraRecomendada;
	}
	public int getFormAEImparticion() {
		return formAEImparticion;
	}
	public void setFormAEImparticion(int formAEImparticion) {
		this.formAEImparticion = formAEImparticion;
	}
	public int getFormAEImparticionPorcentaje() {
		return formAEImparticionPorcentaje;
	}
	public void setFormAEImparticionPorcentaje(int formAEImparticionPorcentaje) {
		this.formAEImparticionPorcentaje = formAEImparticionPorcentaje;
	}
	public Double getFormAEComisionVendedor() {
		return formAEComisionVendedor;
	}
	public void setFormAEComisionVendedor(Double formAEComisionVendedor) {
		this.formAEComisionVendedor = formAEComisionVendedor;
	}
	public Double getFormAEComisionVendedorPorcentaje() {
		return formAEComisionVendedorPorcentaje;
	}
	public void setFormAEComisionVendedorPorcentaje(Double formAEComisionVendedorPorcentaje) {
		this.formAEComisionVendedorPorcentaje = formAEComisionVendedorPorcentaje;
	}
	public int getFormAEViaticos() {
		return formAEViaticos;
	}
	public void setFormAEViaticos(int formAEViaticos) {
		this.formAEViaticos = formAEViaticos;
	}
	public int getFormAEViaticosPorcentaje() {
		return formAEViaticosPorcentaje;
	}
	public void setFormAEViaticosPorcentaje(int formAEViaticosPorcentaje) {
		this.formAEViaticosPorcentaje = formAEViaticosPorcentaje;
	}
	public int getFormAEGastosFijos() {
		return formAEGastosFijos;
	}
	public void setFormAEGastosFijos(int formAEGastosFijos) {
		this.formAEGastosFijos = formAEGastosFijos;
	}
	public int getFormAEGastosFijosPorcentaje() {
		return formAEGastosFijosPorcentaje;
	}
	public void setFormAEGastosFijosPorcentaje(int formAEGastosFijosPorcentaje) {
		this.formAEGastosFijosPorcentaje = formAEGastosFijosPorcentaje;
	}
	public int getFormAEGananciaCurso() {
		return formAEGananciaCurso;
	}
	public void setFormAEGananciaCurso(int formAEGananciaCurso) {
		this.formAEGananciaCurso = formAEGananciaCurso;
	}
	public int getFormAEGananciaCursoPorcentaje() {
		return formAEGananciaCursoPorcentaje;
	}
	public void setFormAEGananciaCursoPorcentaje(int formAEGananciaCursoPorcentaje) {
		this.formAEGananciaCursoPorcentaje = formAEGananciaCursoPorcentaje;
	}
	public int getFormAETotales() {
		return formAETotales;
	}
	public void setFormAETotales(int formAETotales) {
		this.formAETotales = formAETotales;
	}
	public int getFormAETotalesPorcentaje() {
		return formAETotalesPorcentaje;
	}
	public void setFormAETotalesPorcentaje(int formAETotalesPorcentaje) {
		this.formAETotalesPorcentaje = formAETotalesPorcentaje;
	}
	public Double getFormAEPrecioVentaReal() {
		return formAEPrecioVentaReal;
	}
	public void setFormAEPrecioVentaReal(Double formAEPrecioVentaReal) {
		this.formAEPrecioVentaReal = formAEPrecioVentaReal;
	}
	public int getFormAEComisionVendedorReal() {
		return formAEComisionVendedorReal;
	}
	public void setFormAEComisionVendedorReal(int formAEComisionVendedorReal) {
		this.formAEComisionVendedorReal = formAEComisionVendedorReal;
	}
	public int getFormAEGastosFijosReal() {
		return formAEGastosFijosReal;
	}
	public void setFormAEGastosFijosReal(int formAEGastosFijosReal) {
		this.formAEGastosFijosReal = formAEGastosFijosReal;
	}
	public int getFormAEUtilidadReal() {
		return formAEUtilidadReal;
	}
	public void setFormAEUtilidadReal(int formAEUtilidadReal) {
		this.formAEUtilidadReal = formAEUtilidadReal;
	}
	public int getFormAENuevaComisionReal() {
		return formAENuevaComisionReal;
	}
	public void setFormAENuevaComisionReal(int formAENuevaComisionReal) {
		this.formAENuevaComisionReal = formAENuevaComisionReal;
	}
	public Double getFormAERegla3PorcentajeNuevaComisionReal() {
		return formAERegla3PorcentajeNuevaComisionReal;
	}
	public void setFormAERegla3PorcentajeNuevaComisionReal(Double formAERegla3PorcentajeNuevaComisionReal) {
		this.formAERegla3PorcentajeNuevaComisionReal = formAERegla3PorcentajeNuevaComisionReal;
	}
	public int getFormAERegla3PorcentajeNuevaComision() {
		return formAERegla3PorcentajeNuevaComision;
	}
	public void setFormAERegla3PorcentajeNuevaComision(int formAERegla3PorcentajeNuevaComision) {
		this.formAERegla3PorcentajeNuevaComision = formAERegla3PorcentajeNuevaComision;
	}
	public String getFormAEObservaciones() {
		return formAEObservaciones;
	}
	public void setFormAEObservaciones(String formAEObservaciones) {
		this.formAEObservaciones = formAEObservaciones;
	}
	public String getFormAEidPreAsignacionLogica() {
		return formAEidPreAsignacionLogica;
	}
	public void setFormAEidPreAsignacionLogica(String formAEidPreAsignacionLogica) {
		this.formAEidPreAsignacionLogica = formAEidPreAsignacionLogica;
	}
	public Long getFormAEidPreAsignacion() {
		return formAEidPreAsignacion;
	}
	public void setFormAEidPreAsignacion(Long formAEidPreAsignacion) {
		this.formAEidPreAsignacion = formAEidPreAsignacion;
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
	public String getFormAEListFechaPromesaPago() {
		return formAEListFechaPromesaPago;
	}
	public void setFormAEListFechaPromesaPago(String formAEListFechaPromesaPago) {
		this.formAEListFechaPromesaPago = formAEListFechaPromesaPago;
	}
	public String getFormAEListFechaConfirmacion() {
		return formAEListFechaConfirmacion;
	}
	public void setFormAEListFechaConfirmacion(String formAEListFechaConfirmacion) {
		this.formAEListFechaConfirmacion = formAEListFechaConfirmacion;
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
	public String getFormAEFechaPago() {
		return formAEFechaPago;
	}
	public void setFormAEFechaPago(String formAEFechaPago) {
		this.formAEFechaPago = formAEFechaPago;
	}
	public String getFormAEFechaPagoFormat() {
		return formAEFechaPagoFormat;
	}
	public void setFormAEFechaPagoFormat(String formAEFechaPagoFormat) {
		this.formAEFechaPagoFormat = formAEFechaPagoFormat;
	}
	public String getFormAEFechaEmision() {
		return formAEFechaEmision;
	}
	public void setFormAEFechaEmision(String formAEFechaEmision) {
		this.formAEFechaEmision = formAEFechaEmision;
	}
	public String getFormAEFechaEmisionFormat() {
		return formAEFechaEmisionFormat;
	}
	public void setFormAEFechaEmisionFormat(String formAEFechaEmisionFormat) {
		this.formAEFechaEmisionFormat = formAEFechaEmisionFormat;
	}
	
	
	}

