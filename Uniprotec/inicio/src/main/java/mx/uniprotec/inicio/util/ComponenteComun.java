package mx.uniprotec.inicio.util;

import java.time.LocalDateTime;

import mx.uniprotec.inicio.entity.LoginSingle;
import mx.uniprotec.inicio.entity.MonitorEntidades;

public class ComponenteComun {
	
	
	public static MonitorEntidades  monitorCampos() {
		
		MonitorEntidades me = new MonitorEntidades(LoginSingle.getToken(),
				LocalDateTime.now(),
				1l,
				"Creado");
				
		return me;
		
	}
	
	

}
