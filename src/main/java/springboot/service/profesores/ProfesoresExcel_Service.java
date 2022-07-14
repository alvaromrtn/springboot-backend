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
		List<Float> horas = new ArrayList<>();

		try {
			String rutaArchivoExcel = "./././bd/nuevo-excel.xlsx";
			File fichero = new File(rutaArchivoExcel);
			FileInputStream inputStream = new FileInputStream(fichero);

			try (Workbook workbook = new XSSFWorkbook(inputStream)) {
				Sheet firstSheet = workbook.getSheetAt(0);
				Iterator<Row> iterator = firstSheet.iterator();

				DataFormatter formatter = new DataFormatter();

				while (iterator.hasNext()) {

					Row nextRow = iterator.next();
					Iterator<Cell> cellIterator = nextRow.cellIterator();

					while (cellIterator.hasNext()) {
						Cell cell = cellIterator.next();

						if (cell.getColumnIndex() >= 20 && cell.getRowIndex() == 0) {

							if (cell.getColumnIndex() % 2 == 0) {
								String contenidoCelda = formatter.formatCellValue(cell);

								Profesor profesor = new Profesor();

								profesor.setId(cell.getColumnIndex());
								profesor.setNombre(contenidoCelda);
								profesor.setApellidos("");

								profesores.add(profesor);
							}
						}

						if (cell.getColumnIndex() >= 20 && cell.getRowIndex() == 2) {

							if (cell.getColumnIndex() % 2 == 0) {

								String contenidoCelda = formatter.formatCellValue(cell);
								String nuevoContenido = contenidoCelda.replace(",", ".");

								horas.add(Float.parseFloat(nuevoContenido));
							}
						}

						if (cell.getRowIndex() == 3)
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
