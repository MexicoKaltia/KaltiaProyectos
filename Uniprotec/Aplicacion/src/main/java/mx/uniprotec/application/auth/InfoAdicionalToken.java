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

import mx.uniprotec.application.entity.Usuario;
import mx.uniprotec.application.service.IUsuarioService;

@Component
public class InfoAdicionalToken implements TokenEnhancer{
	
	private static final Logger log = LoggerFactory.getLogger(InfoAdicionalToken.class);
	
	@Autowired
	private IUsuarioService usuarioService;

	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
		
		Usuario usuario = usuarioService.findByUsername(authentication.getName());
		Map<String, Object> info = new HashMap<>();
		Map<String, Object> subInfo = new HashMap<>();
		Map<String, Object> subsubInfo = new HashMap<>();
		
		info.put("status", 0);
		info.put("message", "Login Correcto");
		info.put("code", 200);
		
		subInfo.put("id", usuario.getIdUsuario());
		subInfo.put("nombre", usuario.getNombreUsuario());
		subInfo.put("email", usuario.getEmailUsuario());
//		subsubInfo.put("modules", usuario.getRoles());
//		subsubInfo.put("modules", usuario.getModulos());
		subInfo.put("perfil", usuario.getPerfilUsuario());
		info.put("user", subInfo);

//		info.put("fields", subInfo);
			
		((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(info);
		
		return accessToken;
	}

}
