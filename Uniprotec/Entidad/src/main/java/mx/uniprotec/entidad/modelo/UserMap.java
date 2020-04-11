package mx.uniprotec.entidad.modelo;

import java.util.HashMap;

public class UserMap {

	public UserMap() {
		// TODO Auto-generated constructor stub
	}

	
	public HashMap<String, LoginSingle> mapLogin;
    private static UserMap userMap;

    // El constructor es privado, no permite que se genere un constructor por defecto.
    private UserMap(HashMap<String, LoginSingle> mapLogin) {
        this.mapLogin = mapLogin;
        System.out.println("Mi mapLogin es: " + this.mapLogin);
    }

    public static UserMap getSingletonInstance() {
        if (userMap == null){
        	HashMap<String, LoginSingle> mapLogin = new HashMap<String, LoginSingle>();
            userMap = new UserMap(mapLogin);
        }
        else{
            System.out.println("No se puede crear el objeto  mapLogin  porque ya existe un objeto de la clase UserMap");
        }
        
        return userMap;
    }

	public HashMap<String , LoginSingle> getMapLogin() {
		return mapLogin;
	}

	public void setMapLogin(HashMap<String, LoginSingle> mapLogin) {
		this.mapLogin = mapLogin;
	}

	public static UserMap getUserMap() {
		return userMap;
	}

	public static void setUserMap(UserMap userMap) {
		UserMap.userMap = userMap;
	}
    
    
}
