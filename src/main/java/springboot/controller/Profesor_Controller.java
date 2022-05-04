package springboot.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import springboot.model.Profesor;

@RestController
@RequestMapping("/api")
//@CrossOrigin("http://localhost:8081/")
@CrossOrigin(origins = "*", allowedHeaders="*")
public class Profesor_Controller {

	@GetMapping("/profesores")
	public List<Profesor> getProfesores() throws FileNotFoundException, IOException, InvalidFormatException {

		List<Profesor> profesores = new ArrayList<Profesor>();

		Profesor profesor1 = new Profesor();

		profesor1.setNombre("Nombre1");
		profesor1.setApellidos("Apellido1");
		profesor1.setCorreo("Correo1");

		profesores.add(profesor1);

		Profesor profesor2 = new Profesor();

		profesor2.setNombre("Nombre2");
		profesor2.setApellidos("Apellido2");
		profesor2.setCorreo("Correo2");

		profesores.add(profesor2);

		Profesor profesor3 = new Profesor();

		profesor3.setNombre("Nombre3");
		profesor3.setApellidos("Apellido3");
		profesor3.setCorreo("Correo3");

		profesores.add(profesor3);

///////////////////////////////
/*
		OPCPackage pkg = OPCPackage.open(new File("C:\\Users\\alvar\\Desktop\\TFG\\BD.xlsx"));	// CAMBIAR RUTA
		try (XSSFWorkbook wb = new XSSFWorkbook(pkg)) {
			    XSSFSheet sheet = wb.getSheetAt(1);
			    int rows = sheet.getLastRowNum();
			    for (int i = 1; i < rows; ++i) {
			        XSSFRow row = sheet.getRow(i);
			        //HSSFCell productCell = row.getCell(0);
			        // HSSFCell priceCell = row.getCell(1);
			        // HSSFCell linkCell = row.getCell(2);
			        // String product = productCell.getStringCellValue();
			        // BigDecimal price = new BigDecimal(priceCell.getNumericCellValue()).setScale(2, BigDecimal.ROUND_HALF_DOWN);
			        // String link = linkCell.getStringCellValue();
			        //System.out.printf("%s, %s, %s%n", product, price.toString(), link);
			        if(i==1) {
			        	System.out.printf("%s", row);
			        }
			    }
		}
*/	
///////////////////////////////
		return profesores;
	}

}
