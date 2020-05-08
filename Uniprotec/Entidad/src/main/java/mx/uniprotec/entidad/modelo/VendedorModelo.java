package mx.uniprotec.entidad.modelo;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;


public class VendedorModelo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long idVendedor;
	private String nombreVendedor;
	private String emailVendedor;
	private String emailGmailVendedor;
//	private Long clienteVendedor;
	private List<Long> listClienteVendedor;
	private String notaVendedor;
	private Long userCreateVendedor;
	private LocalDateTime createAtVendedor;
	private String statusVendedor;
	

	public VendedorModelo() {}

	
	
	public VendedorModelo(Long id) {
		this.idVendedor = id;
	}



	@Override
	public String toString() {
		return "VendedorModelo [idVendedor=" + idVendedor + ", nombreVendedor=" + nombreVendedor + ", emailVendedor="
				+ emailVendedor + ", emailGmailVendedor=" + emailGmailVendedor + ", listClienteVendedor="
				+ listClienteVendedor + ", notaVendedor=" + notaVendedor + ", userCreateVendedor=" + userCreateVendedor
				+ ", createAtVendedor=" + createAtVendedor + ", statusVendedor=" + statusVendedor + "]";
	}



	public VendedorModelo(Long idVendedor, String nombreVendedor, String emailVendedor, String emailGmailVendedor,
			List<Long> listClienteVendedor, String notaVendedor, Long userCreateVendedor,
			LocalDateTime createAtVendedor, String statusVendedor) {
		super();
		this.idVendedor = idVendedor;
		this.nombreVendedor = nombreVendedor;
		this.emailVendedor = emailVendedor;
		this.emailGmailVendedor = emailGmailVendedor;
		this.listClienteVendedor = listClienteVendedor;
		this.notaVendedor = notaVendedor;
		this.userCreateVendedor = userCreateVendedor;
		this.createAtVendedor = createAtVendedor;
		this.statusVendedor = statusVendedor;
	}



	public Long getIdVendedor() {
		return idVendedor;
	}



	public void setIdVendedor(Long idVendedor) {
		this.idVendedor = idVendedor;
	}



	public String getNombreVendedor() {
		return nombreVendedor;
	}



	public void setNombreVendedor(String nombreVendedor) {
		this.nombreVendedor = nombreVendedor;
	}



	public String getEmailVendedor() {
		return emailVendedor;
	}



	public void setEmailVendedor(String emailVendedor) {
		this.emailVendedor = emailVendedor;
	}



	public String getEmailGmailVendedor() {
		return emailGmailVendedor;
	}



	public void setEmailGmailVendedor(String emailGmailVendedor) {
		this.emailGmailVendedor = emailGmailVendedor;
	}



	public List<Long> getListClienteVendedor() {
		return listClienteVendedor;
	}



	public void setListClienteVendedor(List<Long> listClienteVendedor) {
		this.listClienteVendedor = listClienteVendedor;
	}



	public String getNotaVendedor() {
		return notaVendedor;
	}



	public void setNotaVendedor(String notaVendedor) {
		this.notaVendedor = notaVendedor;
	}



	public Long getUserCreateVendedor() {
		return userCreateVendedor;
	}



	public void setUserCreateVendedor(Long userCreateVendedor) {
		this.userCreateVendedor = userCreateVendedor;
	}



	public LocalDateTime getCreateAtVendedor() {
		return createAtVendedor;
	}



	public void setCreateAtVendedor(LocalDateTime createAtVendedor) {
		this.createAtVendedor = createAtVendedor;
	}



	public String getStatusVendedor() {
		return statusVendedor;
	}



	public void setStatusVendedor(String statusVendedor) {
		this.statusVendedor = statusVendedor;
	}


	
	

		
}
