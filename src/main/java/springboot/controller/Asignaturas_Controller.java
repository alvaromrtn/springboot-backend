package springboot.controller;

import java.io.IOException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.xml.sax.SAXException;

import springboot.model.Asignatura;
import springboot.request.Asignatura_Request;
import springboot.service.asignaturas.Asignaturas_Service;

@RestController
@RequestMapping("/api")
@CrossOrigin("http://localhost:8081/")
public class Asignaturas_Controller {

	@Autowired
	private Asignaturas_Service asignaturas_Service;

	@PostMapping("/asignaturas")
	public List<Asignatura> getAsignaturasTitulacion(@RequestBody Asignatura_Request data)
			throws IOException, ParserConfigurationException, SAXException {

		int codigo_titulacion = data.getCodigo();

		List<Asignatura> asignaturas = asignaturas_Service.getAsignaturasTitulacion(codigo_titulacion);

		return asignaturas;
	}

}
