package mx.uniprotec.application.service;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.json.JSONObject;

import mx.uniprotec.application.entity.Instructor;
import mx.uniprotec.application.entity.Mensaje;
import mx.uniprotec.application.entity.Notificacion;
import mx.uniprotec.application.entity.Perfil;
import mx.uniprotec.application.entity.Region;
import mx.uniprotec.application.entity.Vendedor;
import mx.uniprotec.application.entity.ZonaBase;
import mx.uniprotec.entidad.modelo.MensajeModelo;
import mx.uniprotec.entidad.modelo.UserCorreo;


public interface IAplicacionService {
	
	public Region findRegion(Long idRegion);
	public List<Region> findAllRegiones();
	public Vendedor findVendedor(Long idVendedorCliente);
	public List<Perfil> findAllPerfiles();
	public Perfil findByNombrePerfil(String nombrePerfil);
	public Instructor getOperacionUsuarioI(Long idUsuario);
	public Vendedor getOperacionUsuarioV(Long idUsuario);
	public @Valid List<UserCorreo> usersCorreo(@Valid List<UserCorreo> usersCorreo);
	public @Valid Mensaje altaMensaje(@Valid MensajeModelo mensaje);
	public String getMensaje();
	public List<Notificacion> getNotificaciones(Long idUsuario, String perfilUsuario);
	public ZonaBase getZonaBase();

}
