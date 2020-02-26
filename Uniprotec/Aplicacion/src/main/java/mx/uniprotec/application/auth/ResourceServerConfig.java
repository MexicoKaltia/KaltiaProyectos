package mx.uniprotec.application.auth;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers(HttpMethod.GET, "/crud/clientes", "/crud/clientes/page/**", "/crud/uploads/img/**").permitAll()
		.antMatchers(HttpMethod.GET, "/crud/cliente/{id}").hasAnyRole("USER", "ADMIN")
		.antMatchers(HttpMethod.POST, "/crud/cliente/upload").hasAnyRole("USER", "ADMIN")
		.antMatchers(HttpMethod.POST, "/crud/cliente").hasRole("ADMIN")
		.antMatchers("/crud/clientes/**").hasRole("ADMIN")
		
		.antMatchers(HttpMethod.GET, "/crud/instructor/{id}").hasAnyRole("USER", "ADMIN")
		.antMatchers(HttpMethod.POST, "/crud/instructor/upload").hasAnyRole("USER", "ADMIN")
		.antMatchers(HttpMethod.POST, "/crud/instructor").hasRole("ADMIN")
		.antMatchers("/crud/instructores/**").hasRole("ADMIN")
		
		.antMatchers(HttpMethod.GET, "/crud/curso/{id}").hasAnyRole("USER", "ADMIN")
		.antMatchers(HttpMethod.POST, "/crud/curso/upload").hasAnyRole("USER", "ADMIN")
		.antMatchers(HttpMethod.POST, "/crud/curso").hasRole("ADMIN")
		.antMatchers("/crud/cursos/**").hasRole("ADMIN")
		
		.antMatchers(HttpMethod.GET, "/crud/usuario/{id}").hasAnyRole("USER", "ADMIN")
		.antMatchers(HttpMethod.POST, "/crud/usuario/upload").hasAnyRole("USER", "ADMIN")
		.antMatchers(HttpMethod.POST, "/crud/usuario").hasRole("ADMIN")
		.antMatchers("/crud/usuarios/**").hasRole("ADMIN")
		
		.anyRequest().authenticated()
		.and();
	}

	
}
