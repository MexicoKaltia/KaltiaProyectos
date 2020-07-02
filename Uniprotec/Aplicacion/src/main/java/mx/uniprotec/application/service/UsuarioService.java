package mx.uniprotec.application.service;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.uniprotec.application.dao.IUsuario2Dao;
import mx.uniprotec.application.dao.IUsuarioDao;
import mx.uniprotec.application.entity.Usuario;
import mx.uniprotec.application.entity.Usuario1;

@Service
public class UsuarioService implements IUsuarioService, UserDetailsService{
	
	private Logger logger = LoggerFactory.getLogger(UsuarioService.class);

	@Autowired
	private IUsuarioDao usuarioDao;
	@Autowired
	private IUsuario2Dao usuario2Dao;
	
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
				.map(role -> new SimpleGrantedAuthority(role.getName()))
				.peek(authority -> logger.info("Role2: " + authority.getAuthority()))
				.collect(Collectors.toList());
		
		return new User(usuario.getUsername(), usuario.getPassword(), usuario.getEnabled(), true, true, true, authorities);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Usuario findByUsername(String username) {
		try {
			return usuarioDao.findByUsername(username);
		} catch (Exception e) {
			Usuario usuario = new Usuario();
			usuario.setUsername("false");
			return usuario;
		}
		
	}

	@Override
	@Transactional(readOnly = true)
	public List<Usuario1> findAll() {
		return (List<Usuario1>) usuario2Dao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Usuario1> findAll(Pageable pageable) {
		return null;//usuarioDao.findAll(pageable);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Usuario1 findById(Long id) {
		return usuario2Dao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Usuario1 save(Usuario1 usuario) {
		return usuario2Dao.save(usuario);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		usuario2Dao.deleteById(id);
	}

	
	
}
