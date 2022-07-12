package springboot.controller;

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

import springboot.model.Asignatura;
import springboot.model.AsignaturaExcel;
import springboot.request.Asignatura_Request;
import springboot.request.Profesor_Request;
import springboot.service.asignaturas.AsignaturasExcel_Service;
import springboot.service.asignaturas.Asignaturas_Service;

@RestController
@RequestMapping("/api")
//@CrossOrigin("http://localhost:8081/")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class Asignaturas_Controller {

	@Autowired
	private Asignaturas_Service asignaturas_Service;

	@Autowired
	private AsignaturasExcel_Service asignaturasExcel_Service;

	@PostMapping("/asignaturas")
	public List<Asignatura> getAsignaturasTitulacion(@RequestBody Asignatura_Request data) throws IOException,
			ParserConfigurationException, SAXException, KeyManagementException, NoSuchAlgorithmException {

		int codigo_titulacion = data.getCodigo();

		List<Asignatura> asignaturas = asignaturas_Service.getAsignaturasTitulacion(codigo_titulacion);

		return asignaturas;
	}

	@GetMapping("/asignaturas_excel")
	public List<AsignaturaExcel> getAsignaturas() throws IOException, ParserConfigurationException, SAXException {

		List<AsignaturaExcel> asignaturas = asignaturasExcel_Service.getAsignaturas();

		return asignaturas;
	}
	
	@PostMapping("/asignaturasProfesor_excel")
	public List<AsignaturaExcel> getAsignaturasProfesor(@RequestBody Profesor_Request data) throws IOException,
			ParserConfigurationException, SAXException, KeyManagementException, NoSuchAlgorithmException {

		int columna_profesor = data.getColumna();

		List<AsignaturaExcel> asignaturas = asignaturasExcel_Service.getAsignaturasProfesor(columna_profesor);

		return asignaturas;
	}

}
