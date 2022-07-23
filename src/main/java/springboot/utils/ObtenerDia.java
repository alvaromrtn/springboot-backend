package springboot.utils;

public class ObtenerDia {

	public static String getNombreDia(int dia) {

		String nombre = "";

		if (dia == 1)
			nombre = "Lunes";
		else if (dia == 2)
			nombre = "Martes";
		else if (dia == 3)
			nombre = "Miércoles";
		else if (dia == 4)
			nombre = "Jueves";
		else if (dia == 5)
			nombre = "Viernes";

		return nombre;
	}

}
