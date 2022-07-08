package springboot.service.asignaturas;

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

import springboot.model.AsignaturaExcel;

@Service
public class AsignaturasExcel_Service {

	public List<AsignaturaExcel> getAsignaturas() {

		List<AsignaturaExcel> asignaturas = new ArrayList<>();

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

					AsignaturaExcel asignatura = new AsignaturaExcel();

					while (cellIterator.hasNext()) {
						Cell cell = cellIterator.next();

						if (cell.getRowIndex() >= 2) {

							String contenidoCelda = formatter.formatCellValue(cell);
							// System.out.println("[" + cell.getRowIndex() + "," + cell.getColumnIndex() +
							// "] " + contenidoCelda);

							if (cell.getColumnIndex() == 1)
								asignatura.setCodigoAsignatura(Integer.parseInt(contenidoCelda));
							if (cell.getColumnIndex() == 2)
								asignatura.setNombreAsignatura(contenidoCelda);
							if (cell.getColumnIndex() == 7)
								asignatura.setNombreTitulacion(contenidoCelda);
							if (cell.getColumnIndex() == 8)
								asignatura.setCursoAsignatura(contenidoCelda);
							if (cell.getColumnIndex() == 9)
								asignatura.setPeriodoAsignatura(contenidoCelda);
							if (cell.getColumnIndex() == 10)
								asignatura.setCaracterAsignatura(contenidoCelda);
							if (cell.getColumnIndex() == 11)
								asignatura.setCreditosTeoria(contenidoCelda);
							if (cell.getColumnIndex() == 12) {
								asignatura.setCreditosPractica(contenidoCelda);

								asignatura.setId(cell.getRowIndex());
								asignaturas.add(asignatura);
							}
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return asignaturas;
	}

}
