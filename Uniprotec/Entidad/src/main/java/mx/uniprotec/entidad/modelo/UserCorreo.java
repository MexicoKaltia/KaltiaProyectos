package mx.uniprotec.entidad.modelo;

import java.io.Serializable;

public class UserCorreo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8734871480834437671L;

	private Long idUser;
	private String perfil;
	private String emailUniprotec;
	private String emailGmail;

	public UserCorreo(Long idUser, String perfil) {
		super();
		this.idUser = idUser;
		this.perfil = perfil;
	}



	public UserCorreo(Long idUser, String perfil, String emailUniprotec, String emailGmail) {
		super();
		this.idUser = idUser;
		this.perfil = perfil;
		this.emailUniprotec = emailUniprotec;
		this.emailGmail = emailGmail;
	}

	public UserCorreo() {
		// TODO Auto-generated constructor stub
	}
	
	
	@Override
	public String toString() {
		return "UserCorreo [idUser=" + idUser + ", perfil=" + perfil + ", emailUniprotec=" + emailUniprotec
				+ ", emailGmail=" + emailGmail + "]";
	}
	
	
	
	public Long getIdUser() {
		return idUser;
	}
	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}
	public String getPerfil() {
		return perfil;
	}
	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}
	public String getEmailUniprotec() {
		return emailUniprotec;
	}
	public void setEmailUniprotec(String emailUniprotec) {
		this.emailUniprotec = emailUniprotec;
	}
	public String getEmailGmail() {
		return emailGmail;
	}
	public void setEmailGmail(String emailGmail) {
		this.emailGmail = emailGmail;
	}



	

}
