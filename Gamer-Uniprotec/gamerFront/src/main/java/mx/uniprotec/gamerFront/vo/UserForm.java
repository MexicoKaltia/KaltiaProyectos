package mx.uniprotec.gamerFront.vo;

import java.io.Serializable;

import org.json.simple.JSONObject;

public class UserForm implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String idUser;
	private String userName;
	private String userPass;
	private String userPerfil;
	private String userToken;
	private String userNombre;
	private String userCreate;
	private String userNota;
	private JSONObject userJSON;
	public UserForm(String idUser, String userName, String userPass, String userPerfil, String userToken,
			String userNombre, String userCreate, String userNota, JSONObject userJSON) {
		super();
		this.idUser = idUser;
		this.userName = userName;
		this.userPass = userPass;
		this.userPerfil = userPerfil;
		this.userToken = userToken;
		this.userNombre = userNombre;
		this.userCreate = userCreate;
		this.userNota = userNota;
		this.userJSON = userJSON;
	}
	public UserForm() {
		
	}
	@Override
	public String toString() {
		return "UserForm [idUser=" + idUser + ", userName=" + userName + ", userPass=" + userPass + ", userPerfil="
				+ userPerfil + ", userToken=" + userToken + ", userNombre=" + userNombre + ", userCreate=" + userCreate
				+ ", userNota=" + userNota + ", userJSON=" + userJSON + "]";
	}
	public String getIdUser() {
		return idUser;
	}
	public void setIdUser(String idUser) {
		this.idUser = idUser;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPass() {
		return userPass;
	}
	public void setUserPass(String userPass) {
		this.userPass = userPass;
	}
	public String getUserPerfil() {
		return userPerfil;
	}
	public void setUserPerfil(String userPerfil) {
		this.userPerfil = userPerfil;
	}
	public String getUserToken() {
		return userToken;
	}
	public void setUserToken(String userToken) {
		this.userToken = userToken;
	}
	public String getUserNombre() {
		return userNombre;
	}
	public void setUserNombre(String userNombre) {
		this.userNombre = userNombre;
	}
	public String getUserCreate() {
		return userCreate;
	}
	public void setUserCreate(String userCreate) {
		this.userCreate = userCreate;
	}
	public String getUserNota() {
		return userNota;
	}
	public void setUserNota(String userNota) {
		this.userNota = userNota;
	}
	public JSONObject getUserJSON() {
		return userJSON;
	}
	public void setUserJSON(JSONObject userJSON) {
		this.userJSON = userJSON;
	}
	
	
	
}
