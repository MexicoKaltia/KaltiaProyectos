package mx.uniprotec.application.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.json.simple.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.uniprotec.application.dao.IInstructorDao;
import mx.uniprotec.application.dao.IPerfilDao;
import mx.uniprotec.application.dao.IRegionDao;
import mx.uniprotec.application.dao.IUsuarioDao;
import mx.uniprotec.application.dao.IVendedorDao;
import mx.uniprotec.application.entity.Instructor;
import mx.uniprotec.application.entity.Perfil;
import mx.uniprotec.application.entity.Region;
import mx.uniprotec.application.entity.Usuario;
import mx.uniprotec.application.entity.Vendedor;
import mx.uniprotec.entidad.modelo.UserCorreo;


@Service
public class AplicacionServiceImpl implements IAplicacionService {
	
	private final Logger log = LoggerFactory.getLogger(AplicacionServiceImpl.class);
	
	public AplicacionServiceImpl() {
		// TODO Auto-generated constructor stub
	}
	
	@Autowired
	IClienteService clienteService;
	
	@Autowired
	IVendedorDao vendedorDao;
	@Autowired
	IInstructorDao instructorDao;
	@Autowired
	IUsuarioDao usuarioDao;
	
	@Autowired
	IRegionDao regionDao;
	@Autowired
	IPerfilDao perfilDao;
	
	@Override
	@Transactional(readOnly = true)
	public Region findRegion(Long idRegion) {
		Region region = null;
		try {
			region = regionDao.findById(idRegion).orElse(null);
		} catch (Exception e) {
			log.info("error: Region FindbyId");
			region.setIdRegion(0l);
			region.setNombreRegion("");
			e.printStackTrace();
		}
		return region;
	}

	@Override
	public List<Region> findAllRegiones() {
		List<Region> regiones = null;
		try {
			 regiones = clienteService.findAllRegiones();
		} catch (Exception e) {
			log.info("error:  findAllRegiones");
			regiones.set(0, new Region());
			e.printStackTrace();
		}
			return regiones;
	}

	@Override
	public Vendedor findVendedor(Long idVendedorCliente) {
		Vendedor vendedor = null;
		try {
			vendedor = vendedorDao.findById(idVendedorCliente).orElse(null);
		} catch (Exception e) {
			log.info("error:  findVendedor");
			vendedor.setIdVendedor(0l);
			e.printStackTrace();
		}
		return vendedor;
	}

	@Override
	public List<Perfil> findAllPerfiles() {
		List<Perfil> perfiles = null;
		try {
			 perfiles = (List<Perfil>) perfilDao.findAll();
		} catch (Exception e) {
			log.info("error:  findAllPerfiles");
			perfiles.set(0, new Perfil());
			e.printStackTrace();
		}
			return perfiles;
	}

	@Override
	public Perfil findByNombrePerfil(String nombrePerfil) {
		Perfil perfil= null;
		try {
			perfil = perfilDao.findByNombrePerfil(nombrePerfil);
		} catch (Exception e) {
			log.info("error:  findPerfil");
//			perfil.setNombrePerfil("0l");
			e.printStackTrace();
		}
		return perfil;
	}

	@Override
	public Vendedor getOperacionUsuarioV(Long idUsuario) {		 
		Vendedor vendedor = vendedorDao.findByUsuarioVendedorIdUsuario(idUsuario);
		return vendedor ;
	}
	@Override
	public Instructor getOperacionUsuarioI(Long idUsuario) {		 
		Instructor instructor = instructorDao.findByUsuarioInstructorIdUsuario(idUsuario);
		return instructor ;
	}

	@Override
	public @Valid List<UserCorreo> usersCorreo(@Valid List<UserCorreo> usersCorreo) {
		
		List<UserCorreo> ussC = new ArrayList<UserCorreo>();
		JSONArray jsonArray = new JSONArray();
		UserCorreo userCorreo;// = new UserCorreo();
		for(UserCorreo uc : usersCorreo) {

			if(uc.getPerfil().equals("Ventas")) {
				Vendedor vendedor = vendedorDao.findByUsuarioVendedorIdUsuario(uc.getIdUser());
				userCorreo = new UserCorreo(vendedor.getIdVendedor(), "Ventas", vendedor.getEmailVendedor(), vendedor.getEmailGmailVendedor());
				ussC.add(userCorreo);
//				jsonArray.add(userCorreo);
			}else if(uc.getPerfil().equals("Instructor")) {
				Instructor instructor = instructorDao.findById(uc.getIdUser()).orElse(new Instructor());;
				userCorreo = new UserCorreo(instructor.getIdInstructor(), "Instructor", instructor.getEmailInstructor(), instructor.getEmailGmailInstructor());
				ussC.add(userCorreo);
//				jsonArray.add(userCorreo);
			}			
		}
		
		List<Usuario> usuarios = usuarioDao.getUsuariosCorreo();
		for(Usuario u : usuarios) {
			if(u.getPerfilUsuario().equals("Operacion")) {
				userCorreo = new UserCorreo(u.getIdUsuario(), "Operacion", u.getEmailUsuario(), null);
				ussC.add(userCorreo);
//				jsonArray.add(userCorreo);
			}else if(u.getPerfilUsuario().equals("Administracion")) {
				userCorreo = new UserCorreo(u.getIdUsuario(), "Administracion", u.getEmailUsuario(), null);
				ussC.add(userCorreo);
//				jsonArray.add(userCorreo);
			}else if(u.getPerfilUsuario().equals("Direccion")) {
				userCorreo = new UserCorreo(u.getIdUsuario(), "Direccion", u.getEmailUsuario(), null);
				ussC.add(userCorreo);
//				jsonArray.add(userCorreo);
			}
		}
		return ussC;
	}

}
