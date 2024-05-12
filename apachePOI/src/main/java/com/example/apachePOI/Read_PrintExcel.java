package com.example.apachePOI;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.print.DocFlavor;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import static java.sql.Types.BOOLEAN;
import static java.sql.Types.NUMERIC;
import static org.apache.tomcat.util.bcel.classfile.ElementValue.STRING;

@SpringBootApplication
public class Read_PrintExcel {

    public static void main(String[] args) throws IOException {

        SpringApplication.run(Read_PrintExcel.class, args);

        String excelFilePath= "C:\\Users\\RajdeepNagar\\Downloads\\Valid Test Data 1 2.xlsx";

        // opening the file using file input stream class
        FileInputStream inputStream = new FileInputStream(excelFilePath);

        // getting workbook
        XSSFWorkbook workbook=new XSSFWorkbook(inputStream);

        // getting sheet
//		XSSFSheet sheet= workbook.getSheet("Valid Test Data 12");
        XSSFSheet sheet=workbook.getSheetAt(0);

        // reading rows and columns

        int rows=sheet.getLastRowNum();

        int cols=sheet.getRow(1).getLastCellNum();

        System.out.println("NO of rows: "+rows);

        System.out.println("NO of cols: "+cols);

        // printing excel sheet
        for(int r=0;r<rows;r++){
            XSSFRow row=sheet.getRow(r);
            for(int c=0;c<cols;c++){
                XSSFCell cell=row.getCell(c);
//				System.out.println(cell.getCellType());  // 0 numeric , 1 string, 2 Formula, 3 Blank, 4 Boolean, 5 Error
                switch (cell.getCellType()){
                    case 0:
                        System.out.print(cell.getNumericCellValue()); break;
                    case 1:
                        System.out.print(cell.getStringCellValue()); break;
                    default:
                        throw new RuntimeException("There is no support for this type of cell");
                }
                System.out.print(" ");
            }
            System.out.println();
        }

    }

}
