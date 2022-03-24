package springboot.service.grupos;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import springboot.model.Grupo;

@Service
public class GruposTeoria_Service {

	public List<Grupo> getGruposTeoria(int codigo)
			throws IOException, ParserConfigurationException, SAXException {
		
		List<Grupo> gruposTeoria = new ArrayList<>();

		// 1. Crear dirección de servicio
		URL url = new URL("http://diaweb.usal.es/diaweb/services/Grupos.GruposHttpSoap11Endpoint/");

		// 2. Abrir conexión a la dirección del servicio
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();

		// 3. Establecer los parámetros
		// 3.1 Configuración del modo de envío: GET
		connection.setRequestMethod("GET");

		// 3.2 Establecer el formato de datos: tipo de contenido
		connection.setRequestProperty("content-type", "text/xml;charset=utf-8");

		// 3.3 Establecer entrada y salida, la conexión predeterminada recién creada no
		// tiene permisos de lectura y escritura
		connection.setDoInput(true);
		connection.setDoOutput(true);

		// 4. Organizar datos SOAP y enviar solicitud
		String soapXML = getXML_GruposTeoria(String.valueOf(codigo));

		/// Enviar la información en una secuencia
		OutputStream os = connection.getOutputStream();
		os.write(soapXML.getBytes());

		// El quinto paso: recibir la respuesta del servidor e imprimir
		int responseCode = connection.getResponseCode();
		if (200 == responseCode) { // indica que el servidor respondió con éxito
			/// Obtener el flujo de datos devuelto por la solicitud de conexión actual
			InputStream is = connection.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);

			StringBuilder sb = new StringBuilder();

			String temp = null;
			while (null != (temp = br.readLine())) {
				sb.append(temp);
			}

			is.close();
			isr.close();
			br.close();

			os.close();

			// RESULTADO:
			System.out.println("RESULTADO: " + sb.toString());
			
			InputSource inputSource = new InputSource(new StringReader(sb.toString()));
			
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document document = db.parse(inputSource);

			document.getDocumentElement().normalize();
			System.out.println("Root Element :" + document.getDocumentElement().getNodeName());

			NodeList nList = document.getElementsByTagName("soapenv:Body");

			Node nNode = nList.item(0);
			
			System.out.println("\nCurrent Element :" + nNode.getNodeName());
			
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				
				Element eElement = (Element) nNode;
				
				int numero_grupos = eElement.getElementsByTagName("ns:return").getLength();
				
				for (int n=0; n<numero_grupos; n++) {
					
					Grupo grupo = new Grupo();
					
					grupo.setId(n);
					grupo.setCodigoAula(Integer.parseInt(eElement.getElementsByTagName("ax21:codigo_aula").item(n).getTextContent()));
					grupo.setCodigoGrupo(Integer.parseInt(eElement.getElementsByTagName("ax21:codigo_grupo").item(n).getTextContent()));
					grupo.setCodigoProfesor(Integer.parseInt(eElement.getElementsByTagName("ax21:codigo_profesor").item(n).getTextContent()));
					grupo.setHorarioDiaSemana(Integer.parseInt(eElement.getElementsByTagName("ax21:horario_dia_semana").item(n).getTextContent()));
					grupo.setHorarioHoraFin(eElement.getElementsByTagName("ax21:horario_hora_fin").item(n).getTextContent());
					grupo.setHorarioHoraInicio(eElement.getElementsByTagName("ax21:horario_hora_inicio").item(n).getTextContent());
					grupo.setHorarioQuincenal(eElement.getElementsByTagName("ax21:horario_quincenal").item(n).getTextContent());
					grupo.setNombreAsignatura(eElement.getElementsByTagName("ax21:nombre_asignatura").item(n).getTextContent());
					grupo.setNombreAula(eElement.getElementsByTagName("ax21:nombre_aula").item(n).getTextContent());
					grupo.setNombreEdificio(eElement.getElementsByTagName("ax21:nombre_edificio").item(n).getTextContent());
					grupo.setNombreGrupo(eElement.getElementsByTagName("ax21:nombre_grupo").item(n).getTextContent());
					grupo.setNombreProfesor(eElement.getElementsByTagName("ax21:nombre_profesor").item(n).getTextContent());
					grupo.setNumeroAlumnos(Integer.parseInt(eElement.getElementsByTagName("ax21:numero_alumnos").item(n).getTextContent()));
					grupo.setPeriodo(eElement.getElementsByTagName("ax21:periodo").item(n).getTextContent());
					
					gruposTeoria.add(grupo);
				}

			}
			
		}
		
		return gruposTeoria;
	}

	String getXML_GruposTeoria(String codigo_asignatura) {

		String soapXML = "<soapenv:Envelope xmlns:soapenv='http://schemas.xmlsoap.org/soap/envelope/' xmlns:ser='http://serviciosweb'>"
				+ "<soapenv:Header/>"
				+ "<soapenv:Body>"
				+ "<ser:gruposTeoriaAsignatura>"
				+ "<ser:codigo_asignatura>" + codigo_asignatura + "</ser:codigo_asignatura>"
				+ "</ser:gruposTeoriaAsignatura>"
				+ "</soapenv:Body>"
				+ "</soapenv:Envelope>";

		return soapXML;
	}

}
