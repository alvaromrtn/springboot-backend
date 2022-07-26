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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import springboot.config.Config;
import springboot.model.Profesor;

@Service
public class ProfesoresAsignatura_Service {

	@Autowired
	private ProfesorNombre_Service profesorNombre_Service;

	public List<Profesor> getProfesoresAsignatura(int fila_asignatura) {

		List<Float> horas_profesor = new ArrayList<Float>();
		List<Integer> columna_profesor = new ArrayList<Integer>();

		if (fila_asignatura > 2) {
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

							if ((celda.getRowIndex() == fila_asignatura) && (celda.getColumnIndex() >= 20)) {

								if (celda.getColumnIndex() % 2 == 0) {
									String contenidoCelda = formateador.formatCellValue(celda);
									String nuevoContenido = contenidoCelda.replace(",", ".");

									horas_profesor.add(Float.parseFloat(nuevoContenido));
									columna_profesor.add(celda.getColumnIndex());
								}

							}

							if (celda.getRowIndex() == fila_asignatura + 1)
								break;
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		List<Profesor> profesores = new ArrayList<Profesor>();

		for (int i = 0; i < columna_profesor.size(); i++) {
			Profesor profesor = new Profesor();

			profesor = profesorNombre_Service.getProfesorNombre(columna_profesor.get(i));
			profesor.setId(columna_profesor.get(i));
			profesor.setHoras(horas_profesor.get(i));

			profesores.add(profesor);
		}

		return profesores;
	}

}
