package springboot.service.asignaturas;

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

@Service
public class AsignaturaNombre_Service {
	
	public String getAsignaturaNombre(int fila_asignatura) {

		String nombre = "";

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

						if ((celda.getRowIndex() == fila_asignatura) && (celda.getColumnIndex() == 2)) {

							nombre = formateador.formatCellValue(celda);
							
							break;
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return nombre;
	}

}
