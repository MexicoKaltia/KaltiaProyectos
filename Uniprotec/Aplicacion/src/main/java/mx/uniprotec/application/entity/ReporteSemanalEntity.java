package mx.uniprotec.application.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="reportesSemanales")
public class ReporteSemanalEntity {

	public ReporteSemanalEntity() {
		// TODO Auto-generated constructor stub
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idReporteSemanal;
	@Column
	private String totalCobranza;
	@Column
	private String totalProgramadoSemana;
	@Column
	private String totalVencido;
	@Column
	private String totalSinFecha;
	@Column
	private String totalProgramadoMes1;
	@Column
	private String totalProgramadoMes2;
	@Column
	private String totalProgramadoMes3;
	@Column
	private Integer semanaReporte;
	@Column
	private String dayInit;
	@Column
	private String dayFinish;
	@Column
	private String status;
	@Column
	private Long userCreate;
	@Column
	private LocalDateTime createAt;
	@Override
	public String toString() {
		return "ReporteSemanalEntity [idReporteSemanal=" + idReporteSemanal + ", totalCobranza=" + totalCobranza
				+ ", totalProgramadoSemana=" + totalProgramadoSemana + ", totalVencido=" + totalVencido
				+ ", totalSinFecha=" + totalSinFecha + ", totalProgramadoMes1=" + totalProgramadoMes1
				+ ", totalProgramadoMes2=" + totalProgramadoMes2 + ", totalProgramadoMes3=" + totalProgramadoMes3
				+ ", semanaReporte=" + semanaReporte + ", dayInit=" + dayInit + ", dayFinish=" + dayFinish + ", status="
				+ status + ", userCreate=" + userCreate + ", createAt=" + createAt + "]";
	}
	public Long getIdReporteSemanal() {
		return idReporteSemanal;
	}
	public void setIdReporteSemanal(Long idReporteSemanal) {
		this.idReporteSemanal = idReporteSemanal;
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
