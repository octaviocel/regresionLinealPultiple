/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regresionlinealmultiple;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author DELL
 */
public class ConexionExcel {
    
    private Double distr;
    
    public ConexionExcel(Double i, int f) throws IOException{
        //crear(i,f);
        this.distr = numeroRegresa();
    }

    public Double getDistr() {
        return distr;
    }
        
    
    public Double numeroRegresa() throws FileNotFoundException, IOException{
        FileInputStream file = new FileInputStream("Excel.xlsx");

        XSSFWorkbook open = new XSSFWorkbook(file);
        XSSFSheet sheet = open.getSheetAt(0);

        Row fi = sheet.getRow(1);
        Cell celdita = fi.getCell(1);
        return celdita.getNumericCellValue();
    }
    public void crear(Double i, int y) {
        String fileName = "Excel.xlsx";
        String filePath = fileName;
        //Seteando el nombre de la hoja donde agregaremos los items
        String hoja = "Hoja1";

        //Creando objeto libro de Excel
        XSSFWorkbook book = new XSSFWorkbook();
        XSSFSheet hoja1 = book.createSheet(hoja);

        Row row = hoja1.createRow(1);
        Cell celda = row.createCell(1);
        celda.setCellFormula(String.format("DISTR.T.INV(%f,%d)",i,y));

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
