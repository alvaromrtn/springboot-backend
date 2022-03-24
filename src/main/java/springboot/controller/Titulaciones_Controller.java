package springboot.controller;

import java.io.IOException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.xml.sax.SAXException;

import springboot.model.Titulacion;
import springboot.service.titulaciones.Titulaciones_Service;

@RestController
@RequestMapping("/api")
@CrossOrigin("http://localhost:8081/")
public class Titulaciones_Controller {

	@Autowired
	private Titulaciones_Service titulaciones_Service;

	@GetMapping("/titulaciones")
	public List<Titulacion> getTitulaciones() throws IOException, ParserConfigurationException, SAXException {

		List<Titulacion> titulaciones = titulaciones_Service.getListadoTitulaciones();

		return titulaciones;
	}

}
