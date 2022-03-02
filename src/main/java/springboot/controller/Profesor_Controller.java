package springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import springboot.entity.Profesor;
import springboot.repository.Profesor_Repository;

@RestController
@RequestMapping("/api")
@CrossOrigin("http://localhost:8081/")
public class Profesor_Controller {

	@Autowired
	private Profesor_Repository profesorRepositorio;

	@GetMapping("/profesores")
	public List<Profesor> buscarEmpleados(){
		
		return profesorRepositorio.findAll();
	}
	
}
