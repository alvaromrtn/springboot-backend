package springboot.utils;

public class ObtenerCuatrimestre {

	public static String getNombreCuatrimestre(String cuatrimestre) {

		String nombre = "";

		if (cuatrimestre.equals("P"))
			nombre = "Primer Cuatrimestre";
		else if (cuatrimestre.equals("S"))
			nombre = "Segundo Cuatrimestre";
		else
			nombre = cuatrimestre;

		return nombre;
	}

}
