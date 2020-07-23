package mx.uniprotec.inicio.entity;

public class StatusVO {

	public StatusVO() {}
	
	private int codigo;
	private String mensaje;
	
	
	public StatusVO(int codigo, String mensaje) {
		super();
		this.codigo = codigo;
		this.mensaje = mensaje;
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
	
	
	@Override
	public String toString() {
		return "StatusVO [codigo=" + codigo + ", mensaje=" + mensaje + "]";
	}
	
	

}
