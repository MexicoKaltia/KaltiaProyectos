package mx.uniprotec.gamerBack.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.uniprotec.entidad.modelo.UsuarioAdministrador;
import mx.uniprotec.entidad.modelo.UsuarioAudiencia;
import mx.uniprotec.entidad.modelo.UsuarioInstructor;
import mx.uniprotec.gamerBack.dao.IUsuarioAdministradorDao;
import mx.uniprotec.gamerBack.dao.IUsuarioAudienciaDao;
import mx.uniprotec.gamerBack.dao.IUsuarioDao;
import mx.uniprotec.gamerBack.dao.IUsuarioInstructorDao;
import mx.uniprotec.gamerBack.entity.Role;
import mx.uniprotec.gamerBack.entity.Usuario;
import mx.uniprotec.gamerBack.entity.UsuarioAdministradorEntity;
import mx.uniprotec.gamerBack.entity.UsuarioAudienciaEntity;
import mx.uniprotec.gamerBack.entity.UsuarioInstructorEntity;


@Service
public class UsuarioService implements IUsuarioService, UserDetailsService{
	
	private Logger logger = LoggerFactory.getLogger(UsuarioService.class);

	@Autowired
	private IUsuarioDao usuarioDao;
	@Autowired
	private IUsuarioAdministradorDao usuarioAdministradorDao;
	@Autowired
	private IUsuarioAudienciaDao usuarioAudienciaDao;
	@Autowired
	private IUsuarioInstructorDao usuarioInstructorDao;
	
	
	
	@Override
	@Transactional(readOnly=true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Usuario usuario = usuarioDao.findByUsername(username);
		
		if(usuario == null) {
			logger.error("Error en el login: no existe el usuario '"+username+"' en el sistema!");
			throw new UsernameNotFoundException("Error en el login: no existe el usuario '"+username+"' en el sistema!");
		}
		
		List<GrantedAuthority> authorities = usuario.getRoles()
				.stream()
				.map(role -> new SimpleGrantedAuthority(role.getNombre()))
				.peek(authority -> logger.info("Role: " + authority.getAuthority()))
				.collect(Collectors.toList());
//		GrantedAuthority authorities = usuario.getRoles().stream().equals(map(role -> new SimpleGrantedAuthority(role.getNombre()).collect(Collectors.toList())));
		
		return new User(usuario.getUsername(), usuario.getPassword(), usuario.getEnabled(), true, true, true, authorities);
	}

	@Override
	@Transactional(readOnly=true)
	public Usuario findByUsername(String username) {
		return usuarioDao.findByUsername(username);
	}

	@Override
	public List<Usuario> findAll() {
		return (List<Usuario>) usuarioDao.findAll();
	}

	@Override
	public List<UsuarioAudienciaEntity> findAllAudiencia() {
		List<UsuarioAudienciaEntity> usuariosAudiencia = (List<UsuarioAudienciaEntity>) usuarioAudienciaDao.findAll();
		return usuariosAudiencia;
	}

	@Override
	public List<UsuarioInstructorEntity> findAllInstructor() {
		List<UsuarioInstructorEntity> usuariosInstructor = (List<UsuarioInstructorEntity>) usuarioInstructorDao.findAll();
		return usuariosInstructor;
	}

	@Override
	public List<UsuarioAdministradorEntity> findAllAdministrador() {
		List<UsuarioAdministradorEntity> usuariosAdministrador= (List<UsuarioAdministradorEntity>) usuarioAdministradorDao.findAll();
		return usuariosAdministrador;
	}
	
	
	@Override
	public Usuario findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public Usuario saveAudiencia(@Valid UsuarioAudiencia usuarioAudiencia) {
		
		logger.info(usuarioAudiencia.toString());
		Usuario usuarionew  = new Usuario();
		for(int i=0; i<Integer.valueOf(usuarioAudiencia.getUsuarioAudienciaParticipantes()); i++){
			Usuario usuario = new Usuario();
			usuario.setNombre(usuarioAudiencia.getUsuarioAudienciaResponsable());
			usuario.setUsername(usuarioAudiencia.getUsuarioAudienciaIdAsignacion()+"-"+(i+1));
			usuario.setPassword(getPassword(usuarioAudiencia.getUsuarioAudienciaIdAsignacion()));
			usuario.setEnabled(false);
			usuario.setUserCreate(usuarioAudiencia.getUserCreate());
			usuario.setStatus(usuarioAudiencia.getStatus());
			usuario.setCreateAt(usuarioAudiencia.getCreateAt());
			List<Role> rol = new ArrayList<Role>();
			rol.add(new Role(3l, "ROLE_USER"));
			usuario.setRoles(rol);
			
			String modulos = "";
			for(String a : usuarioAudiencia.getUsuarioAudienciaModulos()) {
				modulos = a + "," + modulos;
			}
			
			UsuarioAudienciaEntity usuarioAudienciaEntity = new UsuarioAudienciaEntity(
					usuarioAudiencia.getUsuarioAudienciaResponsable(),
					usuarioAudiencia.getUsuarioAudienciaFecha(),
					usuarioAudiencia.getUsuarioAudienciaEvento(),
					modulos,
					usuarioAudiencia.getUsuarioAudienciaParticipantes().toString(),
					(usuarioAudiencia.getUsuarioAudienciaIdAsignacion()+"-"+(i+1)),
					usuarioAudiencia.getUsuarioAudienciaNombreEvento(),
					usuarioAudiencia.getCreateAt(),
					usuarioAudiencia.getUserCreate(),
					usuarioAudiencia.getStatus(),
					usuarioAudiencia.getUsuarioAudienciaidCurso()
					);
			
			usuario.setUsuarioAudiencia(usuarioAudienciaEntity);
			try {
				usuarionew = usuarioDao.save(usuario);
				logger.info("exito en la generacion de usuarios");
			} catch (Exception e) {
				logger.info("NO generacion de usuarios");
				e.printStackTrace();
			}
			
		}
		return usuarionew;
	}
	

	@Override
	@Transactional
	public Usuario saveInstructor(@Valid UsuarioInstructor usuarioInstructor) {
		logger.info(usuarioInstructor.toString());
		Usuario usuarionew  = new Usuario();
		
			Usuario usuario = new Usuario();
			usuario.setNombre(usuarioInstructor.getUsuarioInstructorNombre());
			usuario.setUsername(usuarioInstructor.getUsuarioInstructorUserName());
			usuario.setPassword(usuarioInstructor.getUsuarioInstructorPassword());
			usuario.setEnabled(true);
			usuario.setUserCreate(usuarioInstructor.getUserCreate());
			usuario.setStatus(usuarioInstructor.getStatus());
			usuario.setCreateAt(usuarioInstructor.getCreateAt());
			List<Role> rol = new ArrayList<Role>();
			rol.add(new Role(2l, "ROLE_INST"));
			usuario.setRoles(rol);
			
			UsuarioInstructorEntity usuarioInstructorEntity = new UsuarioInstructorEntity(
					usuarioInstructor.getUsuarioInstructorNombre(),
					usuarioInstructor.getUsuarioInstructorIdAsignacion(),
					usuarioInstructor.getCreateAt(),
					usuarioInstructor.getUserCreate(),
					usuarioInstructor.getStatus());
			
			usuario.setUsuarioInstructor(usuarioInstructorEntity);
			try {
				usuarionew = usuarioDao.save(usuario);
				logger.info("exito en la generacion de usuarios");
			} catch (Exception e) {
				logger.info("NO generacion de usuarios");
				e.printStackTrace();
			}
			
		
		return usuarionew;
	}

	@Override
	public Usuario saveAdministrador(@Valid UsuarioAdministrador usuarioAdministrador) {
		logger.info(usuarioAdministrador.toString());
		Usuario usuarionew  = new Usuario();
		
			Usuario usuario = new Usuario();
			usuario.setNombre(usuarioAdministrador.getUsuarioAdministradorNombre());
			usuario.setUsername(usuarioAdministrador.getUsuarioAdministradorUserName());
			usuario.setPassword(usuarioAdministrador.getUsuarioAdministradorPassword());
			usuario.setEnabled(true);
			usuario.setUserCreate(usuarioAdministrador.getUserCreate());
			usuario.setStatus(usuarioAdministrador.getStatus());
			usuario.setCreateAt(usuarioAdministrador.getCreateAt());
			List<Role> rol = new ArrayList<Role>();
			rol.add(new Role(1l, "ROLE_ADMIN"));
			usuario.setRoles(rol);
			
			UsuarioAdministradorEntity usuarioAdministradorEntity = new UsuarioAdministradorEntity(
					usuarioAdministrador.getUsuarioAdministradorNombre(),
					usuarioAdministrador.getUsuarioAdministradorIdAsignacion(),
					usuarioAdministrador.getCreateAt(),
					usuarioAdministrador.getUserCreate(),
					usuarioAdministrador.getStatus());
			
			usuario.setUsuarioAdministrador(usuarioAdministradorEntity);
			try {
				usuarionew = usuarioDao.save(usuario);
				logger.info("exito en la generacion de usuarios");
			} catch (Exception e) {
				logger.info("NO generacion de usuarios");
				e.printStackTrace();
			}
			
			return usuarionew;
	}
	
	@Override
	public Usuario actualizaUsuarioInstructor(@Valid UsuarioInstructor usuario) {
		logger.info(usuario.toString());
//		Usuario usuarioActual = usuarioDao.findByUsername(usuario.getUsuarioInstructorUserName());
		Usuario usuarioActual = usuarioDao.findById(usuario.getIdUsuarioInstructor()).orElse(null);//(usuario.getUsuarioInstructorUserName());
		boolean enabled = false;
		if(usuario.getStatus().equals("ACTIVO")) {
			enabled = true;
		}
		usuarioActual.setEnabled(enabled);
		usuarioActual.setStatus(usuario.getStatus());
		Usuario  usuarioNew = usuarioDao.save(usuarioActual);
		logger.info(usuarioNew.toString());
		UsuarioInstructorEntity uIE = usuarioNew.getUsuarioInstructor();//usuarioInstructorDao.findByUsuarioInstructorUserName(usuarioNew.getUsername());
		uIE.setStatusAsignacion(usuario.getStatus());
		usuarioInstructorDao.save(uIE);
		
		return usuarioNew;
		
	}

	@Override
	public Usuario actualizaUsuarioAudiencia(@Valid UsuarioAudiencia usuario) {
		Usuario usuarioActual = usuarioDao.findById(usuario.getIdUsuarioAudiencia()).orElse(null);//usuarioDao.findByUsername(usuario.getUsuarioAudienciaIdAsignacion());
		boolean enabled = false;
		if(usuario.getStatus().equals("ACTIVO")) {
			enabled = true;
		}
		usuarioActual.setEnabled(enabled);
		usuarioActual.setStatus(usuario.getStatus());
		Usuario  usuarioNew = usuarioDao.save(usuarioActual);
		logger.info(usuarioNew.toString());
		UsuarioAudienciaEntity uIE = usuarioNew.getUsuarioAudiencia();//usuarioInstructorDao.findByUsuarioInstructorUserName(usuarioNew.getUsername());
		uIE.setStatusAsignacion(usuario.getStatus());
		usuarioAudienciaDao.save(uIE);
		
		return usuarioNew;
	}


	@Override
	public UsuarioAudienciaEntity findByAudienciaUsername(String userName) {
		UsuarioAudienciaEntity usuarioAudiencia = new UsuarioAudienciaEntity();
		try {
			usuarioAudiencia = usuarioAudienciaDao.findByUsuarioAudienciaIdAsignacion(userName); 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return usuarioAudiencia;
	}



	

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		
	}
	
	

	
	
	/*
	 *  PRIVATES
	 */
	
	private String getPassword(String usuarioAudienciaIdAsignacion) {
		 BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		 return passwordEncoder.encode(usuarioAudienciaIdAsignacion);
		 
	}


	
	
}
