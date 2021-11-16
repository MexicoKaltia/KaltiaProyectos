package mx.uniprotec.entidad.modelo;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


public class ResultLocal implements Serializable{
	
	private static final long serialVersionUID = -8432269758174775337L;
	public ResultLocal() {}
	
	
	private int codigo;						//
	private String	mensaje;					//
	private ArrayList<String> mensajeArray;
	private String  response;
	private JSONObject jsonResponse;			//
	private JSONArray jsonResponseArray;			//
	private Object object;
	
	@Override
	public String toString() {
		return "ResultLocal [codigo=" + codigo + ", mensaje=" + mensaje + ", mensajeArray=" + mensajeArray
				+ ", response=" + response + ", jsonResponse=" + jsonResponse + ", jsonResponseArray="
				+ jsonResponseArray + ", object=" + object + "]";
	}

	public ResultLocal(int codigo, String mensaje, ArrayList<String> mensajeArray, String response,
			JSONObject jsonResponse, JSONArray jsonResponseArray, Object object) {
		super();
		this.codigo = codigo;
		this.mensaje = mensaje;
		this.mensajeArray = mensajeArray;
		this.response = response;
		this.jsonResponse = jsonResponse;
		this.jsonResponseArray = jsonResponseArray;
		this.object = object;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
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

	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}
	
	
	
}
