package mx.uniprotec.application.entity;

import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class ResponseGeneral {

	public ResponseGeneral() {
		// TODO Auto-generated constructor stub
	}

	private Long status;
	private String message;
	private Long code;
	
//	private Usuario usuario;
//	private Instructor instructor;
//	private Curso curso;
//	private Cliente cliente;
//	private Map<String, Object> response;
	
	
	public Long getStatus() {
		return status;
	}
	public void setStatus(Long status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Long getCode() {
		return code;
	}
	public void setCode(Long code) {
		this.code = code;
	}

	
//	public Usuario getUsuario() {
//		return usuario;
//	}
//	public void setUsuario(Usuario usuario) {
//		this.usuario = usuario;
//	}
//	public Instructor getInstructor() {
//		return instructor;
//	}
//	public void setInstructor(Instructor instructor) {
//		this.instructor = instructor;
//	}
//	public Curso getCurso() {
//		return curso;
//	}
//	public void setCurso(Curso curso) {
//		this.curso = curso;
//	}
//	public Cliente getCliente() {
//		return cliente;
//	}
//	public void setCliente(Cliente cliente) {
//		this.cliente = cliente;
//	}
//	public Map<String, Object> getResponse() {
//		return response;
//	}
//	public void setResponse(Map<String, Object> response) {
//		this.response = response;
//	}
	
	
}
