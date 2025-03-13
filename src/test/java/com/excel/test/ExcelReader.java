package com.excel.test;

import java.io.InputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {

    private static final String EXCEL_FILE = "testdata.xlsx"; // File should be in src/test/resources/
    private static final String SHEET_NAME = "APITestData";
    private static List<Map<String, String>> testData;

    public static void main(String[] args) {
        testData = ExcelReader.getTestData(EXCEL_FILE, SHEET_NAME);
        System.out.println("data is->>>>"+testData.get(0).get("Scenario"));
        System.out.println("data is->>>>"+testData.get(0).get("data"));
    }



    public static List<Map<String, String>> getTestData(String fileName, String sheetName) {
        List<Map<String, String>> testData = new ArrayList<>();

        try (InputStream is = ExcelReader.class.getClassLoader().getResourceAsStream(fileName);
             Workbook workbook = new XSSFWorkbook(is)) {

            Sheet sheet = workbook.getSheet(sheetName);
            Row headerRow = sheet.getRow(0);

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                Map<String, String> dataMap = new HashMap<>();

                for (int j = 0; j < headerRow.getPhysicalNumberOfCells(); j++) {
                    String key = headerRow.getCell(j).getStringCellValue();
                    String value = getCellValueAsString(row.getCell(j));
                    dataMap.put(key, value);
                }
                testData.add(dataMap);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return testData;
    }

    private static String getCellValueAsString(Cell cell) {
        if (cell == null) {
            return "";
        }
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                return String.valueOf((int) cell.getNumericCellValue());
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            default:
                return "";
        }
    }
}
