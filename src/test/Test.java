/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.commons.math3.distribution.TDistribution;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author DELL
 */
public class Test {

    public static void main(String[] args) throws FileNotFoundException, IOException {

        //crear();
//        FileInputStream file = new FileInputStream("Excel.xlsx");
//
//        XSSFWorkbook open = new XSSFWorkbook(file);
//        XSSFSheet sheet = open.getSheetAt(0);
//
//        Row fi = sheet.getRow(1);
//        Cell celdita = fi.getCell(1);
//        System.out.println(celdita.getNumericCellValue());
        TDistribution t = new TDistribution(15 - (2 + 1));
        //Double confianza = doub.doubleValue() / 100;
        String student = String.format("%,.5f", t.inverseCumulativeProbability(1 - ((1 - .95) / 2)));
        System.out.println(student);
    }

    public static void crear() {
        String fileName = "Excel.xlsx";
        String filePath = fileName;
        //Seteando el nombre de la hoja donde agregaremos los items
        String hoja = "Hoja1";

        //Creando objeto libro de Excel
        XSSFWorkbook book = new XSSFWorkbook();
        XSSFSheet hoja1 = book.createSheet(hoja);

        Row row = hoja1.createRow(1);
        Cell celda = row.createCell(1);
        celda.setCellFormula(String.format("DISTR.T.INV(%f,%d)", .05, 12));

        File excelFile;
        excelFile = new File(filePath); // Referenciando a la ruta y el archivo Excel a crear
        try ( FileOutputStream fileOuS = new FileOutputStream(excelFile)) {
            if (excelFile.exists()) { // Si el archivo existe lo eliminaremos
                excelFile.delete();
                System.out.println("Archivo eliminado.!");
            }
            book.write(fileOuS);
            fileOuS.flush();
            fileOuS.close();
            System.out.println("Archivo Creado.!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
