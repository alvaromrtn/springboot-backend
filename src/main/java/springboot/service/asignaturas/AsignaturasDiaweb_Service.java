package springboot.service.asignaturas;

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

import springboot.model.AsignaturaDiaweb;
import springboot.utils.Utils;
import springboot.utils.Utils.TrustAllCertificates;

@Service
public class AsignaturasDiaweb_Service {

	public List<AsignaturaDiaweb> getAsignaturasTitulacion(int codigo_titulacion) throws NoSuchAlgorithmException,
			KeyManagementException, IOException, ParserConfigurationException, SAXException {

		List<AsignaturaDiaweb> asignaturas = new ArrayList<>();

		// 1. Crear dirección del servicio
		URL url = new URL("https://diaweb.usal.es/diaweb/services/Asignaturas.AsignaturasHttpSoap11Endpoint/");

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
		String soapXML = getXML_AsignaturasTitulacion(String.valueOf(codigo_titulacion));

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

				int numero_asignaturas = eElement.getElementsByTagName("ns:return").getLength();

				for (int n = 0; n < numero_asignaturas; n++) {

					AsignaturaDiaweb asignatura = new AsignaturaDiaweb();

					asignatura.setId(n);
					asignatura.setCaracterAsignatura(eElement.getElementsByTagName("ax211:caracter_asignatura").item(n).getTextContent());
					asignatura.setCodigoAsignatura(Integer.parseInt(eElement.getElementsByTagName("ax211:codigo_asignatura").item(n).getTextContent()));
					asignatura.setCodigoOcurrencia(Integer.parseInt(eElement.getElementsByTagName("ax211:codigo_ocurrencia").item(n).getTextContent()));
					asignatura.setCreditosPractica(Float.parseFloat(eElement.getElementsByTagName("ax211:creditos_practica").item(n).getTextContent()));
					asignatura.setCreditosTeoria(Float.parseFloat(eElement.getElementsByTagName("ax211:creditos_teoria").item(n).getTextContent()));
					asignatura.setCursoAsignatura(Integer.parseInt(eElement.getElementsByTagName("ax211:curso_asignatura").item(n).getTextContent()));
					asignatura.setNombreAsignatura(eElement.getElementsByTagName("ax211:nombre_asignatura").item(n).getTextContent());
					asignatura.setPeriodoAsignatura(eElement.getElementsByTagName("ax211:periodo_asignatura").item(n).getTextContent());
					asignatura.setResponsableAsignatura(eElement.getElementsByTagName("ax211:responsable_asignatura").item(n).getTextContent());
					asignatura.setTitulacionAsignatura(eElement.getElementsByTagName("ax211:titulacion_asignatura").item(n).getTextContent());

					asignaturas.add(asignatura);
				}

			}

		}

		// 8. Cerrar la conexión a la dirección del servicio
		connection.disconnect();

		return asignaturas;
	}

	String getXML_AsignaturasTitulacion(String codigo_titulacion) {

		String soapXML =	"<soapenv:Envelope xmlns:soapenv='http://schemas.xmlsoap.org/soap/envelope/' xmlns:ser='http://serviciosweb'>" +
							"<soapenv:Header/>" +
							"<soapenv:Body>" +
							"<ser:asignaturasTitulacion>" +
							"<ser:codigo_titulacion>" + codigo_titulacion + "</ser:codigo_titulacion>" +
							"</ser:asignaturasTitulacion>" +
							"</soapenv:Body>" +
							"</soapenv:Envelope>";

		return soapXML;
	}

}
