package com.example.apachePOI;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;

public class DbToExcel {


    public static void main(String[] args) {
        String jdbcUrl = "jdbc:postgresql://localhost:5432/CsvToDb";
        String username = "postgres";
        String password = "Raja@nagar08";
        String tableName = "sales"; // Change to the desired table name

        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password)) {
            DatabaseMetaData metaData = connection.getMetaData();

            // Retrieve column names and metadata from the specified table
            ResultSet columnsResultSet = metaData.getColumns(null, "public", tableName, null);

            // Create workbook and sheet
            Workbook workbook = new XSSFWorkbook();

            Sheet sheet = workbook.createSheet(tableName);

            // Create header row
            Row headerRow = sheet.createRow(0);

            int columnIndex = 0;

            while (columnsResultSet.next()) {
                String columnName = columnsResultSet.getString("COLUMN_NAME");
                headerRow.createCell(columnIndex++).setCellValue(columnName);
            }

            // Retrieve data from the table
            Statement statement = connection.createStatement();

            ResultSet dataResultSet = statement.executeQuery("SELECT * FROM " + tableName);

            // Populate Excel sheet with data
            int rowNum = 1;
            while (dataResultSet.next()) {
                Row dataRow = sheet.createRow(rowNum++);
                for (int i = 1; i <= columnIndex; i++) {
                    dataRow.createCell(i - 1).setCellValue(dataResultSet.getString(i));
                }
            }

            // Specify the directory to save the Excel file
            String directory = "C:\\Users\\RajdeepNagar\\Downloads";

            Path filePath = Paths.get(directory, "TableData.xlsx");

            // Write workbook content to file
            try (FileOutputStream fileOut = new FileOutputStream(filePath.toFile())) {
                workbook.write(fileOut);
                System.out.println("Excel file created successfully at: " + filePath);
            }

        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }





}
