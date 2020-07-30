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
		http.authorizeRequests().antMatchers(HttpMethod.GET, "/crud/asignacion/{id}", "/crud/cliente/{id}",
//				"/crud/instructores",
//				"/crud/cursos",
//				"/crud/vendedores",
//				"/crud/usuarios",
//				"/crud/clientes/page/**", "/crud/uploads/img/**",
				"/", "/css/**" , "/scripts/**" , "/images/**" ).permitAll()
		.antMatchers(HttpMethod.POST, "/crud/cliente",
				"/crud/instructor",
				"/crud/curso",
				"/crud/usuario",
				"/crud/vendedor").permitAll()
		.antMatchers(HttpMethod.PUT, "/crud/cliente/{id}","/crud/asignacion/{id}",
				"/crud/instructor/{id}",
				"/crud/curso/{id}",
				"/crud/vendedor/{id}",
				"/crud/usuario/{id}",
				"/crud/usuario").permitAll()
//		.antMatchers(HttpMethod.GET, "/crud/cliente/{id}").hasAnyRole("USER", "ADMIN")
//		.antMatchers(HttpMethod.POST, "/crud/cliente/upload").hasAnyRole("USER", "ADMIN")
//		.antMatchers(HttpMethod.POST, "/crud/cliente").hasRole("ADMIN")
//		.antMatchers("/crud/clientes/**").hasRole("ADMIN")
//		
//		.antMatchers(HttpMethod.GET, "/crud/instructor/{id}").hasAnyRole("USER", "ADMIN")
//		.antMatchers(HttpMethod.POST, "/crud/instructor/upload").hasAnyRole("USER", "ADMIN")
//		.antMatchers(HttpMethod.POST, "/crud/instructor").hasRole("ADMIN")
//		.antMatchers("/crud/instructores/**").hasRole("ADMIN")
//		
//		.antMatchers(HttpMethod.GET, "/crud/curso/{id}").hasAnyRole("USER", "ADMIN")
//		.antMatchers(HttpMethod.POST, "/crud/curso/upload").hasAnyRole("USER", "ADMIN")
//		.antMatchers(HttpMethod.POST, "/crud/curso").hasRole("ADMIN")
//		.antMatchers("/crud/cursos/**").hasRole("ADMIN")
		
//		.antMatchers(HttpMethod.GET, "/crud/usuario/{id}").hasAnyRole("USER", "ADMIN")
//		.antMatchers(HttpMethod.POST, "/crud/usuario/upload").hasAnyRole("USER", "ADMIN")
//		.antMatchers(HttpMethod.POST, "/crud/usuario").hasRole("ADMIN")
//		.antMatchers("/crud/usuarios/**").hasRole("ADMIN")
		
		.anyRequest().authenticated()
		.and();
	}

	
}

//.antMatchers(HttpMethod.GET, "/crud/clientes/**").hasAnyRole("USER", "ADMIN","Vendedor", "Instructor", "Operacion", "Administracion", "Direccion")
//.antMatchers(HttpMethod.GET, "/crud/cliente/{id}").hasAnyRole("USER", "ADMIN","Vendedor", "Instructor", "Operacion", "Administracion", "Direccion")
//.antMatchers(HttpMethod.PUT, "/crud/cliente/{id}").hasAnyRole("USER", "ADMIN", "Operacion", "Administracion", "Direccion")
//.antMatchers(HttpMethod.POST, "/crud/cliente").hasAnyRole("ADMIN", "Operacion", "Administracion", "Direccion")
////.antMatchers("/crud/clientes/**").hasRole("ADMIN")
//
//.antMatchers(HttpMethod.GET, "/crud/instructores/**").hasAnyRole("USER", "ADMIN","Vendedor", "Instructor", "Operacion", "Administracion", "Direccion")
//.antMatchers(HttpMethod.GET, "/crud/instructor/{id}").hasAnyRole("USER", "ADMIN","Vendedor", "Instructor", "Operacion", "Administracion", "Direccion")
//.antMatchers(HttpMethod.PUT, "/crud/instructor/{id}").hasAnyRole("USER", "ADMIN", "Operacion", "Administracion", "Direccion")
//.antMatchers(HttpMethod.POST, "/crud/instructor").hasAnyRole("ADMIN", "Operacion", "Administracion", "Direccion")
////.antMatchers("/crud/instructores/**").hasRole("ADMIN")
//
//.antMatchers(HttpMethod.GET, "/crud/cursos/**").hasAnyRole("USER", "ADMIN","Vendedor", "Instructor", "Operacion", "Administracion", "Direccion")
//.antMatchers(HttpMethod.GET, "/crud/curso/{id}").hasAnyRole("USER", "ADMIN","Vendedor", "Instructor", "Operacion", "Administracion", "Direccion")
//.antMatchers(HttpMethod.PUT, "/crud/curso/{id}").hasAnyRole("USER", "ADMIN", "Operacion", "Administracion", "Direccion")
//.antMatchers(HttpMethod.POST, "/crud/curso").hasAnyRole("ADMIN", "Operacion", "Administracion", "Direccion")
////.antMatchers("/crud/cursos/**").hasRole("ADMIN")
//
//.antMatchers(HttpMethod.GET, "/crud/usuarios/**").hasAnyRole("USER", "ADMIN", "Operacion", "Administracion", "Direccion")
//.antMatchers(HttpMethod.GET, "/crud/usuario/{id}").hasAnyRole("USER", "ADMIN", "Operacion", "Administracion", "Direccion")
//.antMatchers(HttpMethod.PUT, "/crud/usuario/{id}").hasAnyRole("USER", "ADMIN", "Operacion", "Administracion", "Direccion")
//.antMatchers(HttpMethod.POST, "/crud/usuario").hasAnyRole("ADMIN", "Operacion", "Administracion", "Direccion")
////.antMatchers("/crud/usuarios/**").hasRole("ADMIN")
//
//.antMatchers(HttpMethod.GET, "/crud/vendedores/**").hasAnyRole("USER", "ADMIN", "Operacion", "Administracion", "Direccion")
//.antMatchers(HttpMethod.GET, "/crud/vendedor/{id}").hasAnyRole("USER", "ADMIN", "Operacion", "Administracion", "Direccion")
//.antMatchers(HttpMethod.PUT, "/crud/vendedor/{id}").hasAnyRole("USER", "ADMIN", "Operacion", "Administracion", "Direccion")
//.antMatchers(HttpMethod.POST, "/crud/vendedor").hasAnyRole("ADMIN", "Operacion", "Administracion", "Direccion")
////.antMatchers("/crud/vendedor/**").hasRole("ADMIN")
//
//.antMatchers(HttpMethod.GET, "/crud/asignaciones/**").hasAnyRole("USER", "ADMIN","Vendedor", "Instructor", "Operacion", "Administracion", "Direccion")
//.antMatchers(HttpMethod.GET, "/crud/asignacion/{id}").hasAnyRole("USER", "ADMIN","Vendedor", "Instructor", "Operacion", "Administracion", "Direccion")
//.antMatchers(HttpMethod.PUT, "/crud/asignacion/{id}").hasAnyRole("USER", "ADMIN","Vendedor", "Instructor", "Operacion", "Administracion", "Direccion")
//.antMatchers(HttpMethod.POST, "/crud/asignacion").hasAnyRole("ADMIN","Vendedor", "Instructor", "Operacion", "Administracion", "Direccion")
////.antMatchers("/crud/asignacion/**").hasRole("ADMIN")
//
////.antMatchers(HttpMethod.GET, "/crud/clientes/**, /crud/cursos/**, /crud/instructores/**, /crud/asignaciones/**, }").hasAnyRole("USER", "ADMIN","Vendedor", "Instructor", "Operacion", "Administracion", "Direccion")
////.antMatchers(HttpMethod.POST, "/crud/curso","/crud/instructor","/crud/cliente","/crud/vendedor","/crud/usuario").hasAnyRole("USER", "ADMIN", "Direccion")
////.antMatchers(HttpMethod.PUT, "/crud/curso/{id}","/crud/instructor/{id}","/crud/cliente/{id}","/crud/vendedor/{id}","/crud/usuario/{id}").hasAnyRole("USER", "ADMIN", "Direccion")
////.antMatchers(HttpMethod.POST, "/crud/instructor","/crud/cliente").hasAnyRole("USER", "ADMIN", "Operacion")
////.antMatchers(HttpMethod.POST, "/crud/usuario").hasRole("ADMIN")
////.antMatchers("/crud/usuarios/**").hasRole("ADMIN")
////