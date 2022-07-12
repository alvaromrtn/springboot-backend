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

import springboot.model.Profesor;

@Service
public class ProfesoresExcel_Service {

	public List<Profesor> getProfesores() {

		List<Profesor> profesores = new ArrayList<>();

		try {
			String rutaArchivoExcel = "./././bd/nuevo-excel.xlsx";
			File fichero = new File(rutaArchivoExcel);
			FileInputStream inputStream = new FileInputStream(fichero);

			try (Workbook workbook = new XSSFWorkbook(inputStream)) {
				Sheet firstSheet = workbook.getSheetAt(0);
				Iterator<Row> iterator = firstSheet.iterator();

				DataFormatter formatter = new DataFormatter();

				Row nextRow = iterator.next();
				Iterator<Cell> cellIterator = nextRow.cellIterator();

				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();

					if (cell.getColumnIndex() >= 20) {

						if (cell.getColumnIndex() % 2 == 0) {
							String contenidoCelda = formatter.formatCellValue(cell);

							Profesor profesor = new Profesor();

							profesor.setId(cell.getColumnIndex());
							profesor.setNombre(contenidoCelda);
							profesor.setApellidos("");
							profesor.setCorreo("");

							profesores.add(profesor);
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return profesores;
	}

}
