package mx.uniprotec.inicio.entity;

import java.util.List;

import mx.uniprotec.entidad.modelo.AsignacionModelo;
import mx.uniprotec.entidad.modelo.ReporteSemanalModelo;

public class MailVO {

	public MailVO() {
		// TODO Auto-generated constructor stub
	}
	
	private String asuntoMail;
	private String bodyMail;
	private String mensajeMail;
	private String destinatarioMail;
	private List<String> destinatarioMailList;
	private AsignacionModelo asignacionMail;
	private ReporteSemanalModelo reporteSemanalMail;
	
	
	public String getAsuntoMail() {
		return asuntoMail;
	}
	public void setAsuntoMail(String asuntoMail) {
		this.asuntoMail = asuntoMail;
	}
	public String getBodyMail() {
		return bodyMail;
	}
	public void setBodyMail(String bodyMail) {
		this.bodyMail = bodyMail;
	}
	public String getMensajeMail() {
		return mensajeMail;
	}
	public void setMensajeMail(String mensajeMail) {
		this.mensajeMail = mensajeMail;
	}
	public String getDestinatarioMail() {
		return destinatarioMail;
	}
	public void setDestinatarioMail(String destinatarioMail) {
		this.destinatarioMail = destinatarioMail;
	}
	public AsignacionModelo getAsignacionMail() {
		return asignacionMail;
	}
	public void setAsignacionMail(AsignacionModelo asignacionMail) {
		this.asignacionMail = asignacionMail;
	}
	public List<String> getDestinatarioMailList() {
		return destinatarioMailList;
	}
	public void setDestinatarioMailList(List<String> destinatarioMailList) {
		this.destinatarioMailList = destinatarioMailList;
	}
	public ReporteSemanalModelo getReporteSemanalMail() {
		return reporteSemanalMail;
	}
	public void setReporteSemanalMail(ReporteSemanalModelo reporteSemanalMail) {
		this.reporteSemanalMail = reporteSemanalMail;
	}
	
	
	
	

}
