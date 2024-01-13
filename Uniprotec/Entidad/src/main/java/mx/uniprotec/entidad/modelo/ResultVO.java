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
	
	private String  perfil;
	
	private List<Region> regiones;
	private List<ClienteModelo> clientes;
	private List<InstructorModelo> instructores;
	private List<CursoModelo> cursos;
	private List<VendedorModelo> vendedores;
	private List<UsuarioModelo> usuarios;
	private List<AsignacionModelo> asignaciones;
	private List<ParticipanteDescarga> participantes;
	
	@Override
	public String toString() {
		return "ResultVO [accesToken=" + accesToken + ", codigo=" + codigo + ", mensaje=" + mensaje + ", mensajeArray="
				+ mensajeArray + ", response=" + response + ", jsonResponse=" + jsonResponse + ", jsonResponseArray="
				+ jsonResponseArray + ", jsonResponseObject=" + jsonResponseObject + ", object=" + object + ", perfil="
				+ perfil + ", regiones=" + regiones + ", clientes=" + clientes + ", instructores=" + instructores
				+ ", cursos=" + cursos + ", vendedores=" + vendedores + ", usuarios=" + usuarios + ", asignaciones="
				+ asignaciones + "]";
	}
	
	
	public ResultVO(Long codigo, String mensaje) {
		super();
		this.codigo = codigo;
		this.mensaje = mensaje;
	}


	public ResultVO(String accesToken, Long codigo, String mensaje, ArrayList<String> mensajeArray, String response,
			JSONObject jsonResponse, JSONArray jsonResponseArray, JSONObject jsonResponseObject, List<Region> regiones,
			List<ClienteModelo> clientes, List<InstructorModelo> instructores, List<CursoModelo> cursos,
			List<VendedorModelo> vendedores, List<UsuarioModelo> usuarios, List<AsignacionModelo> asignaciones) {
		super();
		this.accesToken = accesToken;
		this.codigo = codigo;
		this.mensaje = mensaje;
		this.mensajeArray = mensajeArray;
		this.response = response;
		this.jsonResponse = jsonResponse;
		this.jsonResponseArray = jsonResponseArray;
		this.jsonResponseObject = jsonResponseObject;
		this.regiones = regiones;
		this.clientes = clientes;
		this.instructores = instructores;
		this.cursos = cursos;
		this.vendedores = vendedores;
		this.usuarios = usuarios;
		this.asignaciones = asignaciones;
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
	public List<Region> getRegiones() {
		return regiones;
	}
	public void setRegiones(List<Region> regiones) {
		this.regiones = regiones;
	}
	public List<ClienteModelo> getClientes() {
		return clientes;
	}
	public void setClientes(List<ClienteModelo> clientes) {
		this.clientes = clientes;
	}
	public List<InstructorModelo> getInstructores() {
		return instructores;
	}
	public void setInstructores(List<InstructorModelo> instructores) {
		this.instructores = instructores;
	}
	public List<CursoModelo> getCursos() {
		return cursos;
	}
	public void setCursos(List<CursoModelo> cursos) {
		this.cursos = cursos;
	}
	public List<UsuarioModelo> getUsuarios() {
		return usuarios;
	}
	public void setUsuarios(List<UsuarioModelo> usuarios) {
		this.usuarios = usuarios;
	}
	public List<VendedorModelo> getVendedores() {
		return vendedores;
	}
	public void setVendedores(List<VendedorModelo> vendedores) {
		this.vendedores = vendedores;
	}
	public List<AsignacionModelo> getAsignaciones() {
		return asignaciones;
	}
	public void setAsignaciones(List<AsignacionModelo> asignaciones) {
		this.asignaciones = asignaciones;
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


	public List<ParticipanteDescarga> getParticipantes() {
		return participantes;
	}


	public void setParticipantes(List<ParticipanteDescarga> participantes) {
		this.participantes = participantes;
	}
	
	
	
	
}
