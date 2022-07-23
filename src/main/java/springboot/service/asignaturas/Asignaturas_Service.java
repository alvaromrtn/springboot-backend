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

import springboot.config.Config;
import springboot.model.Asignatura;
import springboot.utils.ObtenerCuatrimestre;

@Service
public class Asignaturas_Service {

	public List<Asignatura> getAsignaturas() {

		List<Asignatura> asignaturas = new ArrayList<>();

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

					Asignatura asignatura = new Asignatura();

					while (iteradorCelda.hasNext()) {
						Cell celda = iteradorCelda.next();

						if (celda.getRowIndex() >= 2) {

							String contenidoCelda = formateador.formatCellValue(celda);

							if (celda.getColumnIndex() == 1)
								asignatura.setCodigoAsignatura(Integer.parseInt(contenidoCelda));
							if (celda.getColumnIndex() == 2)
								asignatura.setNombreAsignatura(contenidoCelda);
							if (celda.getColumnIndex() == 7)
								asignatura.setNombreTitulacion(contenidoCelda);
							if (celda.getColumnIndex() == 8)
								asignatura.setCursoAsignatura(contenidoCelda);
							if (celda.getColumnIndex() == 9)
								asignatura.setPeriodoAsignatura(ObtenerCuatrimestre.getNombreCuatrimestre(contenidoCelda));
							if (celda.getColumnIndex() == 10)
								asignatura.setCaracterAsignatura(contenidoCelda);
							if (celda.getColumnIndex() == 11)
								asignatura.setCreditosTeoria(contenidoCelda);
							if (celda.getColumnIndex() == 12) {
								asignatura.setCreditosPractica(contenidoCelda);

								asignatura.setId(celda.getRowIndex());
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
