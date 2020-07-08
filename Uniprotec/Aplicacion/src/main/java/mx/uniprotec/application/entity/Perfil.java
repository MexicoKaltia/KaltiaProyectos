package mx.uniprotec.application.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="perfiles")
public class Perfil implements Serializable{

		/**
	 * 
	 */
	private static final long serialVersionUID = 1497323468116803359L;

		@Id
		@GeneratedValue(strategy=GenerationType.IDENTITY)
		private Long idPerfil;
		
		@Column(unique=true, length=120)
		private String nombrePerfil;
		
		@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
		@JoinTable(name="perfiles_modulos", joinColumns= @JoinColumn(name="perfil_id"),
		inverseJoinColumns=@JoinColumn(name="modulo_id"),
		uniqueConstraints= {@UniqueConstraint(columnNames= {"perfil_id", "modulo_id"})})
		private List<Modulo> modulos;
		
		
	public Perfil() {
		// TODO Auto-generated constructor stub
	}


	public Long getIdPerfil() {
		return idPerfil;
	}


	public void setIdPerfil(Long idPerfil) {
		this.idPerfil = idPerfil;
	}


	public String getNombrePerfil() {
		return nombrePerfil;
	}


	public void setNombrePerfil(String nombrePerfil) {
		this.nombrePerfil = nombrePerfil;
	}


	public List<Modulo> getModulos() {
		return modulos;
	}


	public void setModulos(List<Modulo> modulos) {
		this.modulos = modulos;
	}


	public Perfil orElse(Object object) {
		// TODO Auto-generated method stub
		return null;
	}

}
