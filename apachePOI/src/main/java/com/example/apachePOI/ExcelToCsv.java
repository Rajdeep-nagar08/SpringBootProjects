package com.example.apachePOI;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import static org.apache.poi.ss.usermodel.DataValidationConstraint.ValidationType.FORMULA;

public class ExcelToCsv {

    public static void main(String[] args) {

        String excelFilePath = "C:\\Users\\RajdeepNagar\\Downloads\\Valid Test Data 1 2.xlsx";

        String csvDirectoryPath = "C:\\Users\\RajdeepNagar\\Downloads\\"; // Specify the directory where you want to save the CSV file


        try{

//       Use WorkbookFactory.create to create a Workbook object from the Excel file.
        Workbook workbook = WorkbookFactory.create(new FileInputStream(excelFilePath));

        for (int sheetIndex = 0; sheetIndex < workbook.getNumberOfSheets(); sheetIndex++) {

//            Get the sheets one by one from the workbook using workbook.getSheetAt(0).
            Sheet sheet = workbook.getSheetAt(sheetIndex);

            String sheeName=sheet.getSheetName();

            String csvFilePath=csvDirectoryPath+sheeName+".csv";

            FileWriter csvWriter = new FileWriter(csvFilePath);

            CSVWriter writer = new CSVWriter(csvWriter);

            String[] rowDataArray;
// writer.writeNext() accept array of string only, that's why we are storing data in array of string instead of list of string
            for (Row row : sheet) {
                rowDataArray = new String[row.getPhysicalNumberOfCells()];
                int cellIndex = 0;
                for (Cell cell : row) {
                    rowDataArray[cellIndex++] = getCellValue(cell);
                }
                writer.writeNext(rowDataArray);
            }

            writer.close();
            System.out.println("Excel file converted to CSV successfully.");
        }

    }
        catch (IOException | EncryptedDocumentException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            throw new RuntimeException(e);
        }
    }


    private static String getCellValue(Cell cell) {
        switch (cell.getCellType()) {
            case 1:   // string
                return cell.getStringCellValue();
            case 0:  // numeric
                return String.valueOf(cell.getNumericCellValue());
            case 4:  // boolean
                return String.valueOf(cell.getBooleanCellValue());
            case 2:   // formula
                return cell.getCellFormula();
            default:
                return "";
        }
    }
}
