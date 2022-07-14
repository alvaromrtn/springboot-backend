package springboot.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;

import springboot.model.Profesor;
import springboot.service.profesores.Profesores_Service;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class Profesores_Controller {

	@Autowired
	private Profesores_Service profesores_Service;

	@GetMapping("/profesores")
	public List<Profesor> getProfesores() throws FileNotFoundException, IOException, InvalidFormatException {

		List<Profesor> profesores = profesores_Service.getProfesores();

		return profesores;
	}

}
