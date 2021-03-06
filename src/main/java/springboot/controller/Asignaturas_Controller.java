package springboot.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.xml.sax.SAXException;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;

import springboot.model.AsignaturaTitulacion;
import springboot.model.Asignatura;
import springboot.model.AsignaturaProfesor;
import springboot.request.Asignatura_Request;
import springboot.request.Profesor_Request;
import springboot.service.asignaturas.AsignaturaNombre_Service;
import springboot.service.asignaturas.AsignaturasProfesor_Service;
import springboot.service.asignaturas.AsignaturasTitulacion_Service;
import springboot.service.asignaturas.Asignaturas_Service;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class Asignaturas_Controller {

	@Autowired
	private Asignaturas_Service asignaturas_Service;

	@Autowired
	private AsignaturasTitulacion_Service asignaturasTitulacion_Service;

	@Autowired
	private AsignaturasProfesor_Service asignaturasProfesor_Service;

	@Autowired
	private AsignaturaNombre_Service asignaturaNombre_Service;

	@GetMapping("/asignaturas")
	public List<Asignatura> getAsignaturas() throws IOException, ParserConfigurationException, SAXException {

		List<Asignatura> asignaturas = asignaturas_Service.getAsignaturas();

		return asignaturas;
	}

	@PostMapping("/asignaturasTitulacion")
	public List<AsignaturaTitulacion> getAsignaturasTitulacion(@RequestBody Asignatura_Request data) throws IOException,
			ParserConfigurationException, SAXException, KeyManagementException, NoSuchAlgorithmException {

		int codigo_titulacion = data.getCodigo();

		List<AsignaturaTitulacion> asignaturas = asignaturasTitulacion_Service
				.getAsignaturasTitulacion(codigo_titulacion);

		return asignaturas;
	}

	@PostMapping("/asignaturasProfesor")
	public List<AsignaturaProfesor> getAsignaturasProfesor(@RequestBody Profesor_Request data) throws IOException,
			ParserConfigurationException, SAXException, KeyManagementException, NoSuchAlgorithmException {

		int columna_profesor = data.getColumna();

		List<AsignaturaProfesor> asignaturas = asignaturasProfesor_Service.getAsignaturasProfesor(columna_profesor);

		return asignaturas;
	}

	@PostMapping("/asignaturaNombre")
	public String getAsignaturaNombre(@RequestBody Asignatura_Request data)
			throws FileNotFoundException, IOException, InvalidFormatException {

		int id = data.getCodigo();

		String nombre = asignaturaNombre_Service.getAsignaturaNombre(id);

		return nombre;
	}

}
