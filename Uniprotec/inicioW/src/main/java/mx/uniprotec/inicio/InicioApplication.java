package mx.uniprotec.inicio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ConfigurableApplicationContext;

import mx.uniprotec.inicio.controller.ControllerCrud;
import mx.uniprotec.inicio.controller.ControllerInicio;
import mx.uniprotec.inicio.controller.ControllerUtil;

@SpringBootApplication
public class InicioApplication extends SpringBootServletInitializer{
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(InicioApplication.class);
	}

	public static void main(String[] args) {
		
		SpringApplication.run(InicioApplication.class, args);
//		ConfigurableApplicationContext applicationContext = SpringApplication.run(InicioApplication.class, args);
//		ControllerInicio ci = applicationContext.getBean(ControllerInicio.class);
//		applicationContext.getBean(ControllerCrud.class);
//		applicationContext.getBean(ControllerUtil.class);
		
//		System.out.println(ci);

	}

}
