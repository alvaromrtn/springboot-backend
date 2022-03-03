package springboot.service.titulaciones;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import org.springframework.stereotype.Service;
import springboot.response.Titulaciones_Response;

@Service
public class Titulaciones_Service {

	public List<Titulaciones_Response> getListadoTitulaciones() throws IOException {

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
		String soapXML = getXML("211"); // 211 INGENIERIA INFORMATICA

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
		}
		
		return null;
	}

	String getXML(String cod) {
		
		String soapXML =
				"<soapenv:Envelope xmlns:soapenv='http://schemas.xmlsoap.org/soap/envelope/' xmlns:ser='http://serviciosweb'>" +
				"<soapenv:Header/>" +
				"<soapenv:Body>" +
				"<ser:datosTitulacion>" +
				"<ser:codigo_titulacion>" + cod + "</ser:codigo_titulacion>" +
				"</ser:datosTitulacion>" +
				"</soapenv:Body>" +
				"</soapenv:Envelope>";
		
		return soapXML;
	}

}
