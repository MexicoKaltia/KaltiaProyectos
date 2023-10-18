package mx.uniprotec.entidad.modelo;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

public class AsignacionModeloDescarga implements Serializable{

		
	/**
	 * 
	 */
	private static final long serialVersionUID = -2228678205987218014L;

	public AsignacionModeloDescarga() {
		// TODO Auto-generated constructor stub
	}

	private List<AsignacionModelo> listAsignacionesDescargas;
	private String strAsignacionesDescargas;
	
	public List<AsignacionModelo> getListAsignacionesDescargas() {
		return listAsignacionesDescargas;
	}
	public void setListAsignacionesDescargas(List<AsignacionModelo> listAsignacionesDescargas) {
		this.listAsignacionesDescargas = listAsignacionesDescargas;
	}
	public String getStrAsignacionesDescargas() {
		return strAsignacionesDescargas;
	}
	public void setStrAsignacionesDescargas(String strAsignacionesDescargas) {
		this.strAsignacionesDescargas = strAsignacionesDescargas;
	}


}
