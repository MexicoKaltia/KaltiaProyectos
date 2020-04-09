package mx.uniprotec.inicio.util;

import java.time.LocalDateTime;

import mx.uniprotec.entidad.modelo.LoginSingle;
import mx.uniprotec.entidad.modelo.MonitorEntidades;



public class ComponenteComun {
	
	
	public static MonitorEntidades  monitorCampos() {
		
		MonitorEntidades me = new MonitorEntidades(LoginSingle.getToken(),
				LocalDateTime.now(),
				1l,
				"Creado");
				
		return me;
		
	}
	
	

}
