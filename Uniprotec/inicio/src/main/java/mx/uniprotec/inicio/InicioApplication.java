package mx.uniprotec.inicio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import mx.uniprotec.inicio.controller.ControllerCrud;
import mx.uniprotec.inicio.controller.ControllerInicio;
import mx.uniprotec.inicio.controller.ControllerUtil;

@SpringBootApplication
public class InicioApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext applicationContext = SpringApplication.run(InicioApplication.class, args);
		ControllerInicio ci = applicationContext.getBean(ControllerInicio.class);
		applicationContext.getBean(ControllerCrud.class);
		applicationContext.getBean(ControllerUtil.class);
		
		System.out.println(ci);

	}

}
