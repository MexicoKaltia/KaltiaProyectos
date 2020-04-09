package mx.uniprotec.entidad.modelo;

public class User {

	public User() {
		// TODO Auto-generated constructor stub
	}

	private String userName;
	private String password;
	
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "User [User=" + userName + ", Password=" + password + "]";
	}
	
	
	
	
}
