package mx.uniprotec.entidad.modelo;

import java.io.Serializable;

public class ZonaBaseModelo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public ZonaBaseModelo() {
		// TODO Auto-generated constructor stub
	}
	
	private Long idZonabase;

	private String dataZonabase;

	public Long getIdZonabase() {
		return idZonabase;
	}

	public void setIdZonabase(Long idZonabase) {
		this.idZonabase = idZonabase;
	}

	public String getDataZonabase() {
		return dataZonabase;
	}

	public void setDataZonabase(String dataZonabase) {
		this.dataZonabase = dataZonabase;
	}


}




	