package springboot.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import springboot.model.Profesor;
import springboot.service.titulaciones.Titulaciones_Service;

@RestController
@RequestMapping("/api")
@CrossOrigin("http://localhost:8081/")
public class Titulaciones_Controller {

	@Autowired
	private Titulaciones_Service titulaciones_Service;

	@GetMapping("/titulaciones")
	public List<Profesor> buscarEmpleados() throws IOException {

		titulaciones_Service.getListadoTitulaciones();

		List<Profesor> profesores = new ArrayList<Profesor>();

		Profesor profesor1 = new Profesor();

		profesor1.setNombre("111_titul");
		profesor1.setApellidos("111_titul");
		profesor1.setCorreo("111_titul");

		profesores.add(profesor1);

		Profesor profesor2 = new Profesor();

		profesor2.setNombre("222_titul");
		profesor2.setApellidos("222_titul");
		profesor2.setCorreo("222_titul");

		profesores.add(profesor2);

		Profesor profesor3 = new Profesor();

		profesor3.setNombre("333_titul");
		profesor3.setApellidos("333_titul");
		profesor3.setCorreo("333_titul");

		profesores.add(profesor3);

		return profesores;

	}
	
}
