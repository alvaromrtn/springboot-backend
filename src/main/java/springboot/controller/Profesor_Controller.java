package springboot.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import springboot.model.Profesor;

@RestController
@RequestMapping("/api")
@CrossOrigin("http://localhost:8081/")
public class Profesor_Controller {

	@GetMapping("/profesores")
	public List<Profesor> buscarEmpleados() {

		List<Profesor> profesores = new ArrayList<Profesor>();

		Profesor profesor1 = new Profesor();

		profesor1.setNombre("Nombre1");
		profesor1.setApellidos("Apellido1");
		profesor1.setCorreo("Correo1");

		profesores.add(profesor1);

		Profesor profesor2 = new Profesor();

		profesor2.setNombre("Nombre2");
		profesor2.setApellidos("Apellido2");
		profesor2.setCorreo("Correo2");

		profesores.add(profesor2);

		Profesor profesor3 = new Profesor();

		profesor3.setNombre("Nombre3");
		profesor3.setApellidos("Apellido3");
		profesor3.setCorreo("Correo3");

		profesores.add(profesor3);

		return profesores;
	}

}
