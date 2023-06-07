package mx.uniprotec.entidad.modelo;

import java.time.LocalDateTime;

public class PreAsignacionAE {

	public PreAsignacionAE() {
		// TODO Auto-generated constructor stub
	}

	private long idPreAsignacionAE;
	private String formAECurso;
	private String formAEEmpresa;
	private int formAEHorasEfectivas;
	private int formAESesiones;
	private int formAEParticipantes;
	private String formAEFechaCotizacion;
	private String formAESede;
	private String formAENivelCurso;

	private int formAENumInstructor;
	private int formAETotalHoras;
	private int formAECostoHoraInstructor;
	private int formAETotalImparticion;
	private int formAEViaticosTotal;

	private int formAESumaImparticionViaticos;
	private int formAECostoCursoRecomendado;
	private int formAECostoHoraRecomendada;

	private int formAEImparticion;
	private int formAEImparticionPorcentaje;
	private int formAEComisionVendedor;
	private int formAEComisionVendedorPorcentaje;
	private int formAEViaticos;
	private int formAEViaticosPorcentaje;
	private int formAEGastosFijos;
	private int formAEGastosFijosPorcentaje;
	private int formAEGananciaCurso;
	private int formAEGananciaCursoPorcentaje;
	private int formAETotales;
	private int formAETotalesPorcentaje;
	private int formAEPrecioVentaReal;
	private int formAEComisionVendedorReal;
	private int formAEGastosFijosReal;
	private int formAEUtilidadReal;
	private int formAENuevaComisionReal;

	private int formAERegla3PorcentajeNuevaComisionReal;
	private int formAERegla3PorcentajeNuevaComision;

	private String formAEObservaciones;
	
	private String formAEidPreAsignacionLogica;
	private String formAEidPreAsignacion;
	
	private String nombreUsuarioSeguimiento;
	private String perfilUsuarioSeguimiento;
	private String mensajeSeguimiento;

	
	private LocalDateTime createAt;
	private Long userCreate;
	private String userCreateTexto;
	private String status;
	
	private String estatusDatoEconomico;
	private String fechaCambioEstatus;

	public String getFormAECurso() {
		return formAECurso;
	}

	public void setFormAECurso(String formAECurso) {
		this.formAECurso = formAECurso;
	}

	public String getFormAEEmpresa() {
		return formAEEmpresa;
	}

	public void setFormAEEmpresa(String formAEEmpresa) {
		this.formAEEmpresa = formAEEmpresa;
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

	public int getFormAEViaticosTotal() {
		return formAEViaticosTotal;
	}

	public void setFormAEViaticosTotal(int formAEViaticosTotal) {
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

	public int getFormAEComisionVendedor() {
		return formAEComisionVendedor;
	}

	public void setFormAEComisionVendedor(int formAEComisionVendedor) {
		this.formAEComisionVendedor = formAEComisionVendedor;
	}

	public int getFormAEViaticos() {
		return formAEViaticos;
	}

	public void setFormAEViaticos(int formAEViaticos) {
		this.formAEViaticos = formAEViaticos;
	}

	public int getFormAEGastosFijos() {
		return formAEGastosFijos;
	}

	public void setFormAEGastosFijos(int formAEGastosFijos) {
		this.formAEGastosFijos = formAEGastosFijos;
	}

	public int getFormAEGananciaCurso() {
		return formAEGananciaCurso;
	}

	public void setFormAEGananciaCurso(int formAEGananciaCurso) {
		this.formAEGananciaCurso = formAEGananciaCurso;
	}

	public int getFormAETotales() {
		return formAETotales;
	}

	public void setFormAETotales(int formAETotales) {
		this.formAETotales = formAETotales;
	}

	public int getFormAEPrecioVentaReal() {
		return formAEPrecioVentaReal;
	}

	public void setFormAEPrecioVentaReal(int formAEPrecioVentaReal) {
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

	public int getFormAERegla3PorcentajeNuevaComisionReal() {
		return formAERegla3PorcentajeNuevaComisionReal;
	}

	public void setFormAERegla3PorcentajeNuevaComisionReal(int formAERegla3PorcentajeNuevaComisionReal) {
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

	public String getFormAEidPreAsignacion() {
		return formAEidPreAsignacion;
	}

	public void setFormAEidPreAsignacion(String formAEidPreAsignacion) {
		this.formAEidPreAsignacion = formAEidPreAsignacion;
	}

	public int getFormAEImparticionPorcentaje() {
		return formAEImparticionPorcentaje;
	}

	public void setFormAEImparticionPorcentaje(int formAEImparticionPorcentaje) {
		this.formAEImparticionPorcentaje = formAEImparticionPorcentaje;
	}

	public int getFormAEComisionVendedorPorcentaje() {
		return formAEComisionVendedorPorcentaje;
	}

	public void setFormAEComisionVendedorPorcentaje(int formAEComisionVendedorPorcentaje) {
		this.formAEComisionVendedorPorcentaje = formAEComisionVendedorPorcentaje;
	}

	public int getFormAEViaticosPorcentaje() {
		return formAEViaticosPorcentaje;
	}

	public void setFormAEViaticosPorcentaje(int formAEViaticosPorcentaje) {
		this.formAEViaticosPorcentaje = formAEViaticosPorcentaje;
	}

	public int getFormAEGastosFijosPorcentaje() {
		return formAEGastosFijosPorcentaje;
	}

	public void setFormAEGastosFijosPorcentaje(int formAEGastosFijosPorcentaje) {
		this.formAEGastosFijosPorcentaje = formAEGastosFijosPorcentaje;
	}

	public int getFormAEGananciaCursoPorcentaje() {
		return formAEGananciaCursoPorcentaje;
	}

	public void setFormAEGananciaCursoPorcentaje(int formAEGananciaCursoPorcentaje) {
		this.formAEGananciaCursoPorcentaje = formAEGananciaCursoPorcentaje;
	}

	public int getFormAETotalesPorcentaje() {
		return formAETotalesPorcentaje;
	}

	public void setFormAETotalesPorcentaje(int formAETotalesPorcentaje) {
		this.formAETotalesPorcentaje = formAETotalesPorcentaje;
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
		this.status= status;
	}

	public long getIdPreAsignacionAE() {
		return idPreAsignacionAE;
	}

	public void setIdPreAsignacionAE(long idPreAsignacionAE) {
		this.idPreAsignacionAE = idPreAsignacionAE;
	}

	public String getNombreUsuarioSeguimiento() {
		return nombreUsuarioSeguimiento;
	}

	public void setNombreUsuarioSeguimiento(String nombreUsuarioSeguimiento) {
		this.nombreUsuarioSeguimiento = nombreUsuarioSeguimiento;
	}

	public String getPerfilUsuarioSeguimiento() {
		return perfilUsuarioSeguimiento;
	}

	public void setPerfilUsuarioSeguimiento(String perfilUsuarioSeguimiento) {
		this.perfilUsuarioSeguimiento = perfilUsuarioSeguimiento;
	}

	public String getMensajeSeguimiento() {
		return mensajeSeguimiento;
	}

	public void setMensajeSeguimiento(String mensajeSeguimiento) {
		this.mensajeSeguimiento = mensajeSeguimiento;
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

	
	

}
