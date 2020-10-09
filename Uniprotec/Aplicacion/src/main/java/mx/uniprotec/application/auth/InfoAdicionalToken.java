package mx.uniprotec.application.auth;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import mx.uniprotec.application.entity.Instructor;
import mx.uniprotec.application.entity.Usuario;
import mx.uniprotec.application.entity.Vendedor;
import mx.uniprotec.application.service.IAplicacionService;
import mx.uniprotec.application.service.IUsuarioService;

@Component
public class InfoAdicionalToken implements TokenEnhancer{
	
	private static final Logger log = LoggerFactory.getLogger(InfoAdicionalToken.class);
	
	@Autowired
	private IUsuarioService usuarioService;
	@Autowired
	private IAplicacionService aplicacionService;

	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
		
		Usuario usuario = usuarioService.findByUsername(authentication.getName());
//		log.info(usuario.toString());
		Map<String, Object> info = new HashMap<>();
		Map<String, Object> subInfo = new HashMap<>();
		
		info.put("status", 0);
		info.put("message", "Login Correcto");
		info.put("code", 200);
		info.put("mensaje", aplicacionService.getMensaje());
		
		subInfo.put("id", usuario.getIdUsuario());
		subInfo.put("nombre", usuario.getNombreUsuario());
		subInfo.put("email", usuario.getEmailUsuario());
		subInfo.put("perfil", usuario.getPerfilUsuario());
//		subInfo.put("notificaciones", aplicacionService.getNotificaciones(usuario.getIdUsuario(), usuario.getPerfilUsuario()));
		
		if(usuario.getPerfilUsuario().equals("Vendedor")) {
			Vendedor vendedor = aplicacionService.getOperacionUsuarioV(usuario.getIdUsuario());
			subInfo.put("operacionId", vendedor.getIdVendedor());
		}else if(usuario.getPerfilUsuario().equals("Instructor")) {
			Instructor instructor= aplicacionService.getOperacionUsuarioI(usuario.getIdUsuario());
			subInfo.put("operacionId", instructor.getIdInstructor());
		}else {
			subInfo.put("operacionId", 0);
		}
		
		info.put("user", subInfo);
		
		((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(info);
		
		return accessToken;
	}

}
