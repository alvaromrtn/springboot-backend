package springboot.utils;

import java.util.StringTokenizer;

public class ObtenerNombreApellidos {

	public static String getNombre(String cadena) {

		String[] partes = cadena.split(", ");

		String nombre = partes[1];

		return nombre;
	}

	public static String getApellidos(String cadena) {

		StringTokenizer st = new StringTokenizer(cadena, ",");

		String apellidos = st.nextToken();

		return apellidos;
	}

	public static String getNombreCompleto(String nombre, String apellidos) {

		String nombre_completo = nombre + " " + apellidos;

		return nombre_completo;
	}

}
