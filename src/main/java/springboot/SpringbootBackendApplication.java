package springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import springboot.entity.Profesor;
import springboot.repository.Profesor_Repository;

@SpringBootApplication
public class SpringbootBackendApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(SpringbootBackendApplication.class, args);
	}

	@Autowired
	private Profesor_Repository profesorRepositorio;
	
	@Override
	public void run(String... args) throws Exception {
		
		Profesor profesor1 = new Profesor();
		
		profesor1.setNombre("Nombre1");
		profesor1.setApellidos("Apellido1");
		profesor1.setCorreo("Correo1");
		
		profesorRepositorio.save(profesor1);
		
		Profesor profesor2 = new Profesor();
		
		profesor2.setNombre("Nombre2");
		profesor2.setApellidos("Apellido2");
		profesor2.setCorreo("Correo2");
		
		profesorRepositorio.save(profesor2);
		
		Profesor profesor3 = new Profesor();
		
		profesor3.setNombre("Nombre3");
		profesor3.setApellidos("Apellido3");
		profesor3.setCorreo("Correo3");
		
		profesorRepositorio.save(profesor3);
	}

}
