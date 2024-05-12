package com.example.project1;

import com.github.pjfanning.xlsx.StreamingReader;
import com.google.gson.Gson;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.json.JSONArray;
import org.springframework.boot.SpringApplication;
import java.io.*;
import java.sql.*;
import java.util.*;
import org.json.JSONObject;


public class ExcelToJsonToDB {

    public static void main(String[] args) throws IOException {
        SpringApplication.run(Project1Application.class, args);
        // to store headers
        List<String> headers = new ArrayList<>();
        List<Map<String,List<JSONObject>>> dataList = new ArrayList<>();

        try (
                InputStream is = new FileInputStream(new File("C:\\Users\\RajdeepNagar\\Downloads\\project1Excel.xlsx"));
                Workbook workbook = StreamingReader.builder()
                        .rowCacheSize(100)
                        .bufferSize(4096)
                        .open(is)
        ) {
            for (Sheet sheet : workbook) {
                List<JSONObject> dataSheet = new ArrayList<>();
                int row = 0;
                for (Row r : sheet) {
                    JSONObject rowJsonObject = new JSONObject();
                    int col = 0;
                    for (Cell c : r) {
                        if (row == 0) {
                            headers.add(c.getStringCellValue());
                        } else {
                            rowJsonObject.put(headers.get(col), c.getStringCellValue());
                        }
                        col++;
                    }
                    if (row > 0) {
                        dataSheet.add(rowJsonObject);
                    }
                    row++;
                }

                Map<String,List<JSONObject>>mp=new HashMap<>();
                mp.put(sheet.getSheetName(),dataSheet);
                dataList.add(mp);
            }
            System.out.println(dataList);
            writeData2JsonFile(dataList);
        }

        String tableName = "ExcelToJson";
        String jdbcUrl = "jdbc:postgresql://localhost:5432/excelJson";
        String username = "postgres";
        String password = "Raja@nagar08";

        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password)) {

            if (!tableExists(connection, tableName)) {
                createTable(connection, tableName);
                System.out.println("Table " + tableName + " created successfully.");
            }

            for (Map<String,List<JSONObject>> dataSheet : dataList) {
                String sheet_name = dataSheet.keySet().iterator().next();
                List<JSONObject> jsonData = dataSheet.get(sheet_name);
                JSONArray jsonArray = new JSONArray(jsonData);

                if(IsDoesExistInTable(connection, tableName, sheet_name)) {
                    try (PreparedStatement preparedStatement = connection.prepareStatement(
                            "INSERT INTO " + tableName + " (sheet_name, json_data) VALUES (?, ?)")) {
                        preparedStatement.setString(1, sheet_name);
                        preparedStatement.setObject(2, jsonArray.toString(), Types.OTHER); // Specify the data type as OTHER to indicate JSON
                        preparedStatement.executeUpdate();
                    } catch (SQLException e) {
                        System.out.println("Database connection error: " + e.getMessage());
                    }
                }
                else {
                    System.out.println(sheet_name+ "data already exist in the tabele");
                }
            }
            System.out.println("Data inserted into the table successfully.");
        } catch (SQLException e) {
            System.err.println("Database connection error: " + e.getMessage());
        }
    }

    private static boolean IsDoesExistInTable(Connection connection, String tableName, String sheetName) {

        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT COUNT(*) FROM " + tableName + " WHERE sheet_name = ?")) {
            preparedStatement.setString(1, sheetName);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            int count = resultSet.getInt(1);
            return count == 0 ? false : true; // Return true if no rows exist with the specified sheet_name
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static boolean tableExists(Connection connection, String tableName) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT EXISTS (SELECT 1 FROM information_schema.tables WHERE table_name = ?)")) {
            preparedStatement.setString(1, tableName);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return resultSet.getBoolean(1);
        }
    }

    public static boolean createTable(Connection connection, String tableName) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            String createTableSql = "CREATE TABLE public." + tableName + " ("
                    + "sheet_name VARCHAR(255) PRIMARY KEY,"
                    + "json_data JSON"
                    + ")";
            statement.executeUpdate(createTableSql);
        }

        return true;

    }

    private static void writeData2JsonFile (List <Map<String, List < JSONObject >>> dataList) throws IOException {
        Gson gson = new Gson();
        FileWriter fileWriter = new FileWriter("C:\\Users\\RajdeepNagar\\Desktop\\Java Notes\\data.json");
        fileWriter.write(gson.toJson(dataList));
        fileWriter.close();
    }
}

