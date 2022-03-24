package springboot.service.titulaciones;

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

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import springboot.model.Titulacion;

@Service
public class Titulaciones_Service {

	public List<Titulacion> getListadoTitulaciones()
			throws IOException, ParserConfigurationException, SAXException {
		
		List<Titulacion> titulaciones = new ArrayList<>();

		// 1. Crear dirección de servicio
		URL url = new URL("http://diaweb.usal.es/diaweb/services/Titulaciones.TitulacionesHttpSoap11Endpoint/");

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
		// String soapXML = getXML("211"); // 211 INGENIERIA INFORMATICA
		String soapXML = getXML_Titulaciones();

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
				
				int numero_titulaciones = eElement.getElementsByTagName("ns:return").getLength();
				
				for (int n=0; n<numero_titulaciones; n++) {
					
					Titulacion titulacion = new Titulacion();
					
					titulacion.setId(n);
					titulacion.setCentro(eElement.getElementsByTagName("ax219:centro").item(n).getTextContent());
					titulacion.setCodigo(Integer.parseInt(eElement.getElementsByTagName("ax219:codigo").item(n).getTextContent()));
					titulacion.setNombre(eElement.getElementsByTagName("ax219:nombre").item(n).getTextContent());
					titulacion.setNumeroCursos(Integer.parseInt(eElement.getElementsByTagName("ax219:numero_cursos").item(n).getTextContent()));
					

					titulaciones.add(titulacion);
				}

			}
			
		}

		return titulaciones;
	}

	String getXML_Titulaciones() {

		String soapXML = "<soapenv:Envelope xmlns:soapenv='http://schemas.xmlsoap.org/soap/envelope/' xmlns:ser='http://serviciosweb'>"
				+ "<soapenv:Header/>"
				+ "<soapenv:Body>"
				+ "<ser:titulaciones/>"
				+ "</soapenv:Body>"
				+ "</soapenv:Envelope>";

		return soapXML;
	}

}
