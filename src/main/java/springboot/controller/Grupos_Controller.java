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

import springboot.model.Grupo;
import springboot.request.Grupo_Request;
import springboot.service.grupos.GruposPractica_Service;
import springboot.service.grupos.GruposTeoria_Service;

@RestController
@RequestMapping("/api")
//@CrossOrigin("http://localhost:8081/")
//@CrossOrigin("*")
@CrossOrigin(origins = "*", allowedHeaders="*")
public class Grupos_Controller {

	@Autowired
	private GruposTeoria_Service gruposTeoria_Service;

	@Autowired
	private GruposPractica_Service gruposPractica_Service;

	@PostMapping("/gruposTeoria")
	public List<Grupo> getGruposTeoria(@RequestBody Grupo_Request data)
			throws IOException, ParserConfigurationException, SAXException {

		int codigo_asignatura = data.getCodigo();

		List<Grupo> gruposTeoria = gruposTeoria_Service.getGruposTeoria(codigo_asignatura);

		return gruposTeoria;
	}

	@PostMapping("/gruposPractica")
	public List<Grupo> getGruposPractica(@RequestBody Grupo_Request data)
			throws IOException, ParserConfigurationException, SAXException {

		int codigo_asignatura = data.getCodigo();

		List<Grupo> gruposPractica = gruposPractica_Service.getGruposPractica(codigo_asignatura);

		return gruposPractica;
	}

}
