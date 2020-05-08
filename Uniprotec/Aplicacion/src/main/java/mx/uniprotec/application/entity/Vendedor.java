package mx.uniprotec.application.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;


@Entity
@Table(name="vendedores")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idVendedor")
public class Vendedor implements Serializable{

	/**
	 * 
	 * 
	 */
	private static final long serialVersionUID = -16881992196551177L;


	public Vendedor() {
		
	}
	

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idVendedor;
	
	@NotEmpty(message ="nombreVendedor no puede estar vacio")
	@Size(min=4, max=300, message="el tamaño tiene que estar entre 4 y 300")
	@Column(nullable=false)
	private String nombreVendedor;
	
	@Column(nullable=false)
	private String emailVendedor;

	@Column(nullable=false)
	private String emailGmailVendedor;
	
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "vendedorCliente")
	private List<Cliente> cliente= new ArrayList<>();
	
	@Column
	private String notaVendedor;
	@Column(nullable=false)
	private Long userCreateVendedor;
	@Column(nullable=false)
	private LocalDateTime createAtVendedor;
	@Column(nullable=false)
	private String statusVendedor;

	/*
	 * 
	 * 
	 */
	@Override
	public String toString() {
		return "Vendedor [idVendedor=" + idVendedor + ", nombreVendedor=" + nombreVendedor + ", emailVendedor="
				+ emailVendedor + ", emailGmailVendedor=" + emailGmailVendedor + ", cliente=" + cliente
				+ ", notaVendedor=" + notaVendedor + ", userCreateVendedor=" + userCreateVendedor
				+ ", createAtVendedor=" + createAtVendedor + ", statusVendedor=" + statusVendedor + "]";
	}
	
	
	public Vendedor(Long idVendedor,
			@NotEmpty(message = "nombreVendedor no puede estar vacio") @Size(min = 4, max = 300, message = "el tamaño tiene que estar entre 4 y 300") String nombreVendedor,
			String emailVendedor, String emailGmailVendedor, List<Cliente> cliente, String notaVendedor,
			Long userCreateVendedor, LocalDateTime createAtVendedor, String statusVendedor) {
		super();
		this.idVendedor = idVendedor;
		this.nombreVendedor = nombreVendedor;
		this.emailVendedor = emailVendedor;
		this.emailGmailVendedor = emailGmailVendedor;
		this.cliente = cliente;
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
	public List<Cliente> getCliente() {
		return cliente;
	}
	public void setCliente(List<Cliente> cliente) {
		this.cliente = cliente;
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
	public String getEmailGmailVendedor() {
		return emailGmailVendedor;
	}
	public void setEmailGmailVendedor(String emailGmailVendedor) {
		this.emailGmailVendedor = emailGmailVendedor;
	}
	
	
	
	
}
