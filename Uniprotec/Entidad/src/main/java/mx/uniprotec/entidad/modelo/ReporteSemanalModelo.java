package mx.uniprotec.entidad.modelo;

import java.io.Serializable;
import java.time.LocalDateTime;

public class ReporteSemanalModelo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 459431060620807331L;
	public ReporteSemanalModelo() {
		// TODO Auto-generated constructor stub
	}

	private String totalCobranza;
	private String totalProgramadoSemana;
	private String totalVencido;
	private String totalSinFecha;
	private String totalProgramadoMes1;
	private String totalProgramadoMes2;
	private String totalProgramadoMes3;
	private Integer semanaReporte;
	private String dayInit;
	private String dayFinish;
	private String status;
	private Long userCreate;
	private LocalDateTime createAt;
	
	
	@Override
	public String toString() {
		return "ReporteSemanalModelo [totalCobranza=" + totalCobranza + ", totalProgramadoSemana="
				+ totalProgramadoSemana + ", totalVencido=" + totalVencido + ", totalSinFecha=" + totalSinFecha
				+ ", totalProgramadoMes1=" + totalProgramadoMes1 + ", totalProgramadoMes2=" + totalProgramadoMes2
				+ ", totalProgramadoMes3=" + totalProgramadoMes3 + ", semanaReporte=" + semanaReporte + ", dayInit="
				+ dayInit + ", dayFinish=" + dayFinish + ", status=" + status + ", userCreate=" + userCreate
				+ ", createAt=" + createAt + "]";
	}
	public ReporteSemanalModelo(String totalCobranza, String totalProgramadoSemana, String totalVencido,
			String totalSinFecha, String totalProgramadoMes1, String totalProgramadoMes2, String totalProgramadoMes3,
			Integer semanaReporte, String dayInit, String dayFinish, String status, Long userCreate,
			LocalDateTime createAt) {
		super();
		this.totalCobranza = totalCobranza;
		this.totalProgramadoSemana = totalProgramadoSemana;
		this.totalVencido = totalVencido;
		this.totalSinFecha = totalSinFecha;
		this.totalProgramadoMes1 = totalProgramadoMes1;
		this.totalProgramadoMes2 = totalProgramadoMes2;
		this.totalProgramadoMes3 = totalProgramadoMes3;
		this.semanaReporte = semanaReporte;
		this.dayInit = dayInit;
		this.dayFinish = dayFinish;
		this.status = status;
		this.userCreate = userCreate;
		this.createAt = createAt;
	}
	public String getTotalCobranza() {
		return totalCobranza;
	}
	public void setTotalCobranza(String totalCobranza) {
		this.totalCobranza = totalCobranza;
	}
	public String getTotalProgramadoSemana() {
		return totalProgramadoSemana;
	}
	public void setTotalProgramadoSemana(String totalProgramadoSemana) {
		this.totalProgramadoSemana = totalProgramadoSemana;
	}
	public String getTotalVencido() {
		return totalVencido;
	}
	public void setTotalVencido(String totalVencido) {
		this.totalVencido = totalVencido;
	}
	public String getTotalSinFecha() {
		return totalSinFecha;
	}
	public void setTotalSinFecha(String totalSinFecha) {
		this.totalSinFecha = totalSinFecha;
	}
	public String getTotalProgramadoMes1() {
		return totalProgramadoMes1;
	}
	public void setTotalProgramadoMes1(String totalProgramadoMes1) {
		this.totalProgramadoMes1 = totalProgramadoMes1;
	}
	public String getTotalProgramadoMes2() {
		return totalProgramadoMes2;
	}
	public void setTotalProgramadoMes2(String totalProgramadoMes2) {
		this.totalProgramadoMes2 = totalProgramadoMes2;
	}
	public String getTotalProgramadoMes3() {
		return totalProgramadoMes3;
	}
	public void setTotalProgramadoMes3(String totalProgramadoMes3) {
		this.totalProgramadoMes3 = totalProgramadoMes3;
	}
	public Integer getSemanaReporte() {
		return semanaReporte;
	}
	public void setSemanaReporte(Integer semanaReporte) {
		this.semanaReporte = semanaReporte;
	}
	public String getDayInit() {
		return dayInit;
	}
	public void setDayInit(String dayInit) {
		this.dayInit = dayInit;
	}
	public String getDayFinish() {
		return dayFinish;
	}
	public void setDayFinish(String dayFinish) {
		this.dayFinish = dayFinish;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Long getUserCreate() {
		return userCreate;
	}
	public void setUserCreate(Long userCreate) {
		this.userCreate = userCreate;
	}
	public LocalDateTime getCreateAt() {
		return createAt;
	}
	public void setCreateAt(LocalDateTime createAt) {
		this.createAt = createAt;
	}
	
	

}
