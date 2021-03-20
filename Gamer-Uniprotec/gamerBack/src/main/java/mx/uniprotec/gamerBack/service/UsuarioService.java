package mx.uniprotec.gamerBack.service;

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

import mx.uniprotec.entidad.modelo.UsuarioAudiencia;
import mx.uniprotec.gamerBack.dao.IUsuarioDao;
import mx.uniprotec.gamerBack.entity.Usuario;


@Service
public class UsuarioService implements IUsuarioService, UserDetailsService{
	
	private Logger logger = LoggerFactory.getLogger(UsuarioService.class);

	@Autowired
	private IUsuarioDao usuarioDao;
//	@Autowired
	
	
	
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
		
		return new User(usuario.getUsername(), usuario.getPassword(), usuario.getEnabled(), true, true, true, authorities);
	}

	@Override
	@Transactional(readOnly=true)
	public Usuario findByUsername(String username) {
		return usuarioDao.findByUsername(username);
	}

	@Override
	public List<Usuario> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Usuario findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public Usuario save(@Valid UsuarioAudiencia usuarioAudiencia) {
		
		logger.info(usuarioAudiencia.toString());
		
		for(int i=0; i<Integer.valueOf(usuarioAudiencia.getUsuarioAudienciaParticipantes()); i++){
			Usuario usuario = new Usuario();
			usuario.setNombre(usuarioAudiencia.getUsuarioAudienciaResponsable());
			usuario.setUsername(usuarioAudiencia.getUsuarioAudienciaIdAsignacion()+"-"+(i+1));
			usuario.setPassword(getPassword(usuarioAudiencia.getUsuarioAudienciaIdAsignacion()));
			usuario.setEnabled(false);
			usuario.setUserCreate(usuarioAudiencia.getUserCreate());
			usuario.setStatus(usuarioAudiencia.getStatus());
			usuario.setCreateAt(usuarioAudiencia.getCreateAt());
			
			usuarioDao.save(usuario);
		}
		
		
		return new Usuario();
	}

	private String getPassword(String usuarioAudienciaIdAsignacion) {
		 BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		 return passwordEncoder.encode(usuarioAudienciaIdAsignacion);
		 
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		
	}

}
