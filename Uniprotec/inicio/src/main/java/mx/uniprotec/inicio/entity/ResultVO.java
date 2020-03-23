package mx.uniprotec.inicio.entity;


import java.io.Serializable;
import java.util.ArrayList;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Component;

@Component
public class ResultVO implements Serializable{
	
	private static final long serialVersionUID = -8432269758174775337L;
	public ResultVO() {}
	
	private String accesToken;
	private Integer codigo;
	private String	mensaje;
	private ArrayList<String> mensajeArray;
	private String  response;
	private JSONObject jsonResponse;
	
	public Integer getCodigo() {
		return codigo;
	}
	public void setCodigo(Integer codigo) {
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
	@Override
	public String toString() {
		return "ResultVO [accesToken=" + accesToken + ", codigo=" + codigo + ", mensaje=" + mensaje + ", mensajeArray="
				+ mensajeArray + ", response=" + response + ", jsonResponse=" + jsonResponse + "]";
	}
	public ResultVO(String accesToken, Integer codigo, String mensaje, ArrayList<String> mensajeArray, String response,
			JSONObject jsonResponse) {
		super();
		this.accesToken = accesToken;
		this.codigo = codigo;
		this.mensaje = mensaje;
		this.mensajeArray = mensajeArray;
		this.response = response;
		this.jsonResponse = jsonResponse;
	}



	
	

}
