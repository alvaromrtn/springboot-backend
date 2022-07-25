package springboot.service.profesores;

import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;

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
public class ProfesorNombre_Service {
	
	public Profesor getProfesorNombre(int columna_profesor) {

		Profesor profesor = new Profesor();

		try {
			String rutaArchivoExcel = Config.DATABASE;
			File fichero = new File(rutaArchivoExcel);
			FileInputStream inputStream = new FileInputStream(fichero);

			try (Workbook workbook = new XSSFWorkbook(inputStream)) {
				Sheet primeraHoja = workbook.getSheetAt(0);
				Iterator<Row> iterador = primeraHoja.iterator();

				DataFormatter formateador = new DataFormatter();

					Row siguienteFila = iterador.next();
					Iterator<Cell> iteradorCelda = siguienteFila.cellIterator();

					while (iteradorCelda.hasNext()) {
						Cell celda = iteradorCelda.next();

						if (celda.getColumnIndex() == columna_profesor) {

							if (celda.getColumnIndex() % 2 == 0) {
								String contenidoCelda = formateador.formatCellValue(celda);

								String nombre_profesor = ObtenerNombreApellidos.getNombre(contenidoCelda);
								String apellidos_profesor = ObtenerNombreApellidos.getApellidos(contenidoCelda);
								
								profesor.setNombre(nombre_profesor);
								profesor.setApellidos(apellidos_profesor);
								profesor.setNombreCompleto(ObtenerNombreApellidos
										.getNombreCompleto(nombre_profesor, apellidos_profesor));
								
								break;
							}
						}
					}
				}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return profesor;
	}

}
