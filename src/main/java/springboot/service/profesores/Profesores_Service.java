package springboot.service.profesores;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import springboot.config.Config;
import springboot.model.Profesor;
import springboot.utils.ObtenerNombreApellidos;

@Service
public class Profesores_Service {

	public List<Profesor> getProfesores() {

		List<Profesor> profesores = new ArrayList<>();
		List<Float> horas = new ArrayList<>();

		try {
			String rutaArchivoExcel = Config.DATABASE;
			File fichero = new File(rutaArchivoExcel);
			FileInputStream inputStream = new FileInputStream(fichero);

			try (Workbook workbook = new XSSFWorkbook(inputStream)) {
				Sheet primeraHoja = workbook.getSheetAt(0);
				Iterator<Row> iterador = primeraHoja.iterator();

				DataFormatter formateador = new DataFormatter();

				while (iterador.hasNext()) {

					Row siguienteFila = iterador.next();
					Iterator<Cell> iteradorCelda = siguienteFila.cellIterator();

					while (iteradorCelda.hasNext()) {
						Cell celda = iteradorCelda.next();

						if (celda.getColumnIndex() >= 20 && celda.getRowIndex() == 0) {

							if (celda.getColumnIndex() % 2 == 0) {
								String contenidoCelda = formateador.formatCellValue(celda);

								Profesor profesor = new Profesor();

								profesor.setId(celda.getColumnIndex());
								profesor.setNombre(ObtenerNombreApellidos.getNombre(contenidoCelda));
								profesor.setApellidos(ObtenerNombreApellidos.getApellidos(contenidoCelda));
								profesor.setNombreCompleto(ObtenerNombreApellidos
										.getNombreCompleto(profesor.getNombre(), profesor.getApellidos()));

								profesores.add(profesor);
							}
						}

						if (celda.getColumnIndex() >= 20 && celda.getRowIndex() == 2) {

							if (celda.getColumnIndex() % 2 == 0) {

								String contenidoCelda = formateador.formatCellValue(celda);
								String nuevoContenido = contenidoCelda.replace(",", ".");

								horas.add(Float.parseFloat(nuevoContenido));
							}
						}

						if (celda.getRowIndex() == 3)
							break;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		for (int i = 0; i < profesores.size(); i++)
			profesores.get(i).setHoras(horas.get(i));

		return profesores;
	}

}
