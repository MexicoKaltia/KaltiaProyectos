package mx.uniprotec.entidad.modelo;


import java.io.Serializable;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


public class ResultVO implements Serializable{
	
	private static final long serialVersionUID = -8432269758174775337L;
	public ResultVO() {}
	
	private String accesToken;   				//
	private Long codigo;						//
	private String	mensaje;					//
	private ArrayList<String> mensajeArray;
	private String  response;
	private JSONObject jsonResponse;			//
	private JSONArray jsonResponseArray;			//
	
	public ResultVO(String accesToken, Long codigo, String mensaje, ArrayList<String> mensajeArray, String response,
			JSONObject jsonResponse, JSONArray jsonResponseArray) {
		super();
		this.accesToken = accesToken;
		this.codigo = codigo;
		this.mensaje = mensaje;
		this.mensajeArray = mensajeArray;
		this.response = response;
		this.jsonResponse = jsonResponse;
		this.jsonResponseArray = jsonResponseArray;
	}
	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	
	public String getResponse() {
		return response;
	}
	public void setResponse(String response) {
		this.response = response;
	}
	public JSONObject getJsonResponse() {
		return jsonResponse;
	}
	public void setJsonResponse(JSONObject jsonResponse) {
		this.jsonResponse = jsonResponse;
	}
	
	public ArrayList<String> getMensajeArray() {
		return mensajeArray;
	}
	public void setMensajeArray(ArrayList<String> mensajeArray) {
		this.mensajeArray = mensajeArray;
	}
	
	public String getAccesToken() {
		return accesToken;
	}
	public void setAccesToken(String accesToken) {
		this.accesToken = accesToken;
	}
	public JSONArray getJsonResponseArray() {
		return jsonResponseArray;
	}
	public void setJsonResponseArray(JSONArray jsonResponseArray) {
		this.jsonResponseArray = jsonResponseArray;
	}

	@Override
	public String toString() {
		return "ResultVO [accesToken=" + accesToken + ", codigo=" + codigo + ", mensaje=" + mensaje + ", mensajeArray="
				+ mensajeArray + ", response=" + response + ", jsonResponse=" + jsonResponse + ", jsonResponseArray="
				+ jsonResponseArray + "]";
	}
	
	


	
	

}
