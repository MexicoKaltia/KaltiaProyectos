package mx.uniprotec.entidad.modelo;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
	private JSONObject jsonResponseObject;			//
	private Object object;
	private String perfil;
	
	
	public ResultVO(String accesToken, Long codigo, String mensaje, ArrayList<String> mensajeArray, String response,
			JSONObject jsonResponse, JSONArray jsonResponseArray, JSONObject jsonResponseObject, Object object) {
		super();
		this.accesToken = accesToken;
		this.codigo = codigo;
		this.mensaje = mensaje;
		this.mensajeArray = mensajeArray;
		this.response = response;
		this.jsonResponse = jsonResponse;
		this.jsonResponseArray = jsonResponseArray;
		this.jsonResponseObject = jsonResponseObject;
		this.object = object;
	}
	@Override
	public String toString() {
		return "ResultVO [accesToken=" + accesToken + ", codigo=" + codigo + ", mensaje=" + mensaje + ", mensajeArray="
				+ mensajeArray + ", response=" + response + ", jsonResponse=" + jsonResponse + ", jsonResponseArray="
				+ jsonResponseArray + ", jsonResponseObject=" + jsonResponseObject + ", object=" + object + "]";
	}
	public String getAccesToken() {
		return accesToken;
	}
	public void setAccesToken(String accesToken) {
		this.accesToken = accesToken;
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
	public ArrayList<String> getMensajeArray() {
		return mensajeArray;
	}
	public void setMensajeArray(ArrayList<String> mensajeArray) {
		this.mensajeArray = mensajeArray;
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
	public JSONArray getJsonResponseArray() {
		return jsonResponseArray;
	}
	public void setJsonResponseArray(JSONArray jsonResponseArray) {
		this.jsonResponseArray = jsonResponseArray;
	}
	public JSONObject getJsonResponseObject() {
		return jsonResponseObject;
	}
	public void setJsonResponseObject(JSONObject jsonResponseObject) {
		this.jsonResponseObject = jsonResponseObject;
	}
	public Object getObject() {
		return object;
	}
	public void setObject(Object object) {
		this.object = object;
	}
	public String getPerfil() {
		return perfil;
	}
	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}		
	
	
}
