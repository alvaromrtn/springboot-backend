package springboot.service.grupos;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringReader;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
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
import springboot.utils.ObtenerCuatrimestre;
import springboot.utils.ObtenerDia;
import springboot.utils.Utils;
import springboot.utils.Utils.TrustAllCertificates;

@Service
public class GruposTeoria_Service {

	public List<Grupo> getGruposTeoria(int codigo_asignatura) throws NoSuchAlgorithmException, KeyManagementException,
			IOException, ParserConfigurationException, SAXException {

		List<Grupo> gruposTeoria = new ArrayList<>();

		// 1. Crear dirección del servicio
		URL url = new URL("https://diaweb.usal.es/diaweb/services/Grupos.GruposHttpSoap11Endpoint/");

		// 2. Crear contexto SSL y confiar en todos los certificados
		SSLContext sslContext = SSLContext.getInstance("SSL");
		TrustManager[] trustAll = new TrustManager[] { new TrustAllCertificates() };
		sslContext.init(null, trustAll, new java.security.SecureRandom());

		// - 2.1 Establecer el contexto de confianza en todos los certificados en HttpsURLConnection
		HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.getSocketFactory());

		// 3. Abrir conexión a la dirección del servicio
		HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();

		// 4. Establecer los parámetros:
		// - 4.1 Configuración del modo de envío: GET
		connection.setRequestMethod("GET");

		// - 4.2 Establecer el formato de datos: Tipo de contenido
		connection.setRequestProperty("content-type", "text/xml;charset=utf-8");

		// - 4.3 Establecer entrada y salida, la conexión predeterminada recién creada
		// no tiene permisos de lectura y escritura
		connection.setDoInput(true);
		connection.setDoOutput(true);

		// - 4.4 Confiar en todos los anfitriones
		connection.setHostnameVerifier(new Utils.TrustAllHosts());

		// 5. Crear solicitud SOAP
		String soapXML = getXML_GruposTeoria(String.valueOf(codigo_asignatura));

		// 6. Enviar solicitud SOAP en una secuencia
		OutputStream os = connection.getOutputStream();
		os.write(soapXML.getBytes());

		// 7. Recibir respuesta del servidor y guardar datos
		int responseCode = connection.getResponseCode();
		if (200 == responseCode) { // El servidor respondió con éxito
			// Obtener flujo de datos devuelto por la solicitud de conexión actual
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

			InputSource inputSource = new InputSource(new StringReader(sb.toString()));

			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document document = db.parse(inputSource);

			document.getDocumentElement().normalize();

			NodeList nList = document.getElementsByTagName("soapenv:Body");

			Node nNode = nList.item(0);

			if (nNode.getNodeType() == Node.ELEMENT_NODE) {

				Element eElement = (Element) nNode;

				int numero_grupos = eElement.getElementsByTagName("ns:return").getLength();

				if (numero_grupos > 1) {
					for (int n = 0; n < numero_grupos; n++) {

						Grupo grupo = new Grupo();

						grupo.setId(n);
						grupo.setCodigoAula(Integer.parseInt(eElement.getElementsByTagName("ax21:codigo_aula").item(n).getTextContent()));
						grupo.setCodigoGrupo(Integer.parseInt(eElement.getElementsByTagName("ax21:codigo_grupo").item(n).getTextContent()));
						grupo.setCodigoProfesor(Integer.parseInt(eElement.getElementsByTagName("ax21:codigo_profesor").item(n).getTextContent()));
						String dia = ObtenerDia.getNombreDia(Integer.parseInt(eElement.getElementsByTagName("ax21:horario_dia_semana").item(n).getTextContent()));
						grupo.setHorarioDiaSemana(dia);
						grupo.setHorarioHoraFin(eElement.getElementsByTagName("ax21:horario_hora_fin").item(n).getTextContent());
						grupo.setHorarioHoraInicio(eElement.getElementsByTagName("ax21:horario_hora_inicio").item(n).getTextContent());
						grupo.setHorarioQuincenal(eElement.getElementsByTagName("ax21:horario_quincenal").item(n).getTextContent());
						grupo.setNombreAsignatura(eElement.getElementsByTagName("ax21:nombre_asignatura").item(n).getTextContent());
						grupo.setNombreAula(eElement.getElementsByTagName("ax21:nombre_aula").item(n).getTextContent());
						grupo.setNombreEdificio(eElement.getElementsByTagName("ax21:nombre_edificio").item(n).getTextContent());
						grupo.setNombreGrupo(eElement.getElementsByTagName("ax21:nombre_grupo").item(n).getTextContent());
						grupo.setNombreProfesor(eElement.getElementsByTagName("ax21:nombre_profesor").item(n).getTextContent());
						grupo.setNumeroAlumnos(Integer.parseInt(eElement.getElementsByTagName("ax21:numero_alumnos").item(n).getTextContent()));
						String cuatrimestre = ObtenerCuatrimestre.getNombreCuatrimestre(eElement.getElementsByTagName("ax21:periodo").item(n).getTextContent());
						grupo.setPeriodo(cuatrimestre);

						gruposTeoria.add(grupo);
					}
				}
			}

		}

		// 8. Cerrar la conexión a la dirección del servicio
		connection.disconnect();

		return gruposTeoria;
	}

	String getXML_GruposTeoria(String codigo_asignatura) {

		String soapXML =	"<soapenv:Envelope xmlns:soapenv='http://schemas.xmlsoap.org/soap/envelope/' xmlns:ser='http://serviciosweb'>" +
							"<soapenv:Header/>" +
							"<soapenv:Body>" +
							"<ser:gruposTeoriaAsignatura>" +
							"<ser:codigo_asignatura>" + codigo_asignatura + "</ser:codigo_asignatura>" +
							"</ser:gruposTeoriaAsignatura>" +
							"</soapenv:Body>" +
							"</soapenv:Envelope>";

		return soapXML;
	}

}
