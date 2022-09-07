package mx.uniprotec.application.entity;

import java.io.Serializable;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.json.JSONObject;

@Entity
@Table(name = "zonabase")
public class ZonaBase implements Serializable {



	/**
	 * 
	 */
	private static final long serialVersionUID = 8124177947445084275L;

	@Id
	private Long idZonabase;

	@Column
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
