package mx.uniprotec.inicio.entity;

import mx.uniprotec.entidad.modelo.AsignacionModelo;

public class MailVO {

	public MailVO() {
		// TODO Auto-generated constructor stub
	}
	
	private String asuntoMail;
	private String bodyMail;
	private String mensajeMail;
	private String destinatarioMail;
	private AsignacionModelo asignacionMail;
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
	
	
	
	

}
