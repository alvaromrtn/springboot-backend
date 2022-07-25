package springboot.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;

import springboot.model.Profesor;
import springboot.request.Asignatura_Request;
import springboot.request.Profesor_Request;
import springboot.service.profesores.ProfesorNombre_Service;
import springboot.service.profesores.ProfesoresAsignatura_Service;
import springboot.service.profesores.Profesores_Service;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class Profesores_Controller {

	@Autowired
	private Profesores_Service profesores_Service;

	@Autowired
	private ProfesoresAsignatura_Service profesoresAsignatura_Service;

	@Autowired
	private ProfesorNombre_Service profesorNombre_Service;

	@GetMapping("/profesores")
	public List<Profesor> getProfesores() throws FileNotFoundException, IOException, InvalidFormatException {

		List<Profesor> profesores = profesores_Service.getProfesores();

		return profesores;
	}

	@PostMapping("/profesoresAsignatura")
	public List<Profesor> getProfesorNombre(@RequestBody Asignatura_Request data)
			throws FileNotFoundException, IOException, InvalidFormatException {

		int id = data.getCodigo();

		List<Profesor> profesores = profesoresAsignatura_Service.getProfesoresAsignatura(id);

		return profesores;
	}

	@PostMapping("/profesorNombre")
	public String getProfesorNombre(@RequestBody Profesor_Request data)
			throws FileNotFoundException, IOException, InvalidFormatException {

		int columna_profesor = data.getColumna();

		Profesor profesor = profesorNombre_Service.getProfesorNombre(columna_profesor);

		String nombre = profesor.getNombreCompleto();

		return nombre;
	}

}
