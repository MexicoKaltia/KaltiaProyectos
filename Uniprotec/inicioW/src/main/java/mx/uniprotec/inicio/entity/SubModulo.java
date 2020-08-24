package mx.uniprotec.inicio.entity;

import java.io.Serializable;

import org.springframework.stereotype.Component;

@Component
public class SubModulo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long idSubmodulo;
	private String name;

	    public SubModulo(String name) {
	        this.name = name;
	    }
	    
	    public SubModulo() {
			// TODO Auto-generated constructor stub
		}

		public Long getIdSubmodulo() {
			return idSubmodulo;
		}

		public void setIdSubmodulo(Long id) {
			this.idSubmodulo = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		@Override
		public String toString() {
			return "SubModulo [id=" + idSubmodulo + ", name=" + name + "]";
		}

		public SubModulo(Long id, String name) {
			super();
			this.idSubmodulo = id;
			this.name = name;
		}

	
}
