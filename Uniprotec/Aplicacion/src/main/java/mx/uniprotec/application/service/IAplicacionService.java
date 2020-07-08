package mx.uniprotec.application.service;

import java.util.List;

import mx.uniprotec.application.entity.Instructor;
import mx.uniprotec.application.entity.Perfil;
import mx.uniprotec.application.entity.Region;
import mx.uniprotec.application.entity.Vendedor;


public interface IAplicacionService {
	
	public Region findRegion(Long idRegion);
	public List<Region> findAllRegiones();
	public Vendedor findVendedor(Long idVendedorCliente);
	public List<Perfil> findAllPerfiles();
	public Perfil findByNombrePerfil(String nombrePerfil);
	public Instructor getOperacionUsuarioI(Long idUsuario);
	public Vendedor getOperacionUsuarioV(Long idUsuario);

}
