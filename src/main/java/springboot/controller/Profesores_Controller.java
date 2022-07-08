package springboot.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import java.util.Iterator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;

import springboot.model.Profesor;

@RestController
@RequestMapping("/api")
//@CrossOrigin("http://localhost:8081/")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class Profesores_Controller {

	@GetMapping("/profesores")
	public List<Profesor> getProfesores() throws FileNotFoundException, IOException, InvalidFormatException {

///////////////////////////////

		///// RECORRO9 TODO EL ARCHIVO

		/*
		 * try { String rutaArchivoExcel = "./././bd/nuevo-excel.xlsx"; File fichero =
		 * new File(rutaArchivoExcel); FileInputStream inputStream = new
		 * FileInputStream(fichero); Workbook workbook = new XSSFWorkbook(inputStream);
		 * Sheet firstSheet = workbook.getSheetAt(0); Iterator<Row> iterator =
		 * firstSheet.iterator();
		 * 
		 * DataFormatter formatter = new DataFormatter(); while (iterator.hasNext()) {
		 * Row nextRow = iterator.next(); Iterator<Cell> cellIterator =
		 * nextRow.cellIterator(); while(cellIterator.hasNext()) { Cell cell =
		 * cellIterator.next(); String contenidoCelda = formatter.formatCellValue(cell);
		 * System.out.println("celda: " + contenidoCelda); } } } catch (Exception e) {
		 * e.printStackTrace(); }
		 * 
		 * System.out.println("FIN");
		 */

		/////////////////// RECORRO SOLO LA PRIMERA FILA

		List<Profesor> profesores = new ArrayList<Profesor>();

		try {
			String rutaArchivoExcel = "./././bd/nuevo-excel.xlsx";
			File fichero = new File(rutaArchivoExcel);
			FileInputStream inputStream = new FileInputStream(fichero);
			Workbook workbook = new XSSFWorkbook(inputStream);
			Sheet firstSheet = workbook.getSheetAt(0);
			Iterator<Row> iterator = firstSheet.iterator();

			DataFormatter formatter = new DataFormatter();

			Row nextRow = iterator.next();
			Iterator<Cell> cellIterator = nextRow.cellIterator();
			while (cellIterator.hasNext()) {
				Cell cell = cellIterator.next();
				if (cell.getColumnIndex() >= 20) {// obtiene la celda especifica

					if (cell.getColumnIndex() % 2 == 0) {
						String contenidoCelda = formatter.formatCellValue(cell);
						System.out.println("celda: " + contenidoCelda + " - fila: " + cell.getRowIndex()
								+ " - columna: " + cell.getColumnIndex());

						Profesor profesor = new Profesor();

						profesor.setNombre(contenidoCelda);
						profesor.setApellidos("");
						profesor.setCorreo("");

						profesores.add(profesor);

					}

				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("FIN");

		// ASIGNATURAS
		try {
			String rutaArchivoExcel = "./././bd/nuevo-excel.xlsx";
			File fichero = new File(rutaArchivoExcel);
			FileInputStream inputStream = new FileInputStream(fichero);
			Workbook workbook = new XSSFWorkbook(inputStream);
			Sheet firstSheet = workbook.getSheetAt(0);
			Iterator<Row> iterator = firstSheet.iterator();

			DataFormatter formatter = new DataFormatter();
			while (iterator.hasNext()) {
				Row nextRow = iterator.next();
				Iterator<Cell> cellIterator = nextRow.cellIterator();
				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();

					if (cell.getColumnIndex() == 2) {
						String contenidoCelda = formatter.formatCellValue(cell);
						System.out.println("celda: " + contenidoCelda + " - fila: " + cell.getRowIndex()
								+ " - columna: " + cell.getColumnIndex());

					}

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("FIN");

		// ASIGNATURAS DE UN PROFESOR
		// celda: Zato Domínguez, Carolina - fila: 0 - columna: 159

		List<Integer> fila = new ArrayList<Integer>();
		try {
			String rutaArchivoExcel = "./././bd/nuevo-excel.xlsx";
			File fichero = new File(rutaArchivoExcel);
			FileInputStream inputStream = new FileInputStream(fichero);
			Workbook workbook = new XSSFWorkbook(inputStream);
			Sheet firstSheet = workbook.getSheetAt(0);
			Iterator<Row> iterator = firstSheet.iterator();

			DataFormatter formatter = new DataFormatter();
			while (iterator.hasNext()) {
				Row nextRow = iterator.next();
				Iterator<Cell> cellIterator = nextRow.cellIterator();
				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();

					if (cell.getColumnIndex() == 159 && cell.getRowIndex() > 1) {// obtiene la celda especifica

						String contenidoCelda = formatter.formatCellValue(cell);
						if (contenidoCelda != null) {
							System.out.println("celda: -" + contenidoCelda + "- - fila: " + cell.getRowIndex());
							fila.add(cell.getRowIndex());
						}

					}

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			String rutaArchivoExcel = "./././bd/nuevo-excel.xlsx";
			File fichero = new File(rutaArchivoExcel);
			FileInputStream inputStream = new FileInputStream(fichero);
			Workbook workbook = new XSSFWorkbook(inputStream);
			Sheet firstSheet = workbook.getSheetAt(0);
			Iterator<Row> iterator = firstSheet.iterator();

			DataFormatter formatter = new DataFormatter();
			while (iterator.hasNext()) {
				Row nextRow = iterator.next();
				Iterator<Cell> cellIterator = nextRow.cellIterator();
				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();

					if (fila.size() !=0 && cell.getRowIndex() == fila.get(0) && cell.getColumnIndex() == 2) {// obtiene la celda especifica

						String contenidoCelda = formatter.formatCellValue(cell);
							System.out.println("celda: -" + contenidoCelda + "- - fila: " + cell.getRowIndex());
							
							fila.remove(0);
						}

					

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("FIN");

///////////////////////////////

		return profesores;
	}

}
