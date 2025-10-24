package utilities;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

public class ExcelUtility {


    public static Object[][] getTestData(String fileName, String sheetName) {

        InputStream is = null;
        XSSFWorkbook workbook = null;
        Object[][] data = new Object[][] {};

        try {
            is = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);

            if (is == null) {
                System.err.println("Error: Excel file '" + fileName + "' not found in the classpath (e.g., src/test/resources).");
                throw new RuntimeException("Excel file not found: " + fileName);
            }

            workbook = new XSSFWorkbook(is);

            XSSFSheet sheet = workbook.getSheet(sheetName);

            if (sheet == null) {
                System.err.println("Error: Sheet '" + sheetName + "' not found in the Excel file.");
                throw new IllegalArgumentException("Sheet not found: " + sheetName);
            }

            int rowCount = sheet.getLastRowNum();

            if (rowCount < 1) {
                return data;
            }

            int colCount = Objects.requireNonNull(sheet.getRow(0), "Excel sheet has no header row at index 0.").getLastCellNum();

            data = new Object[rowCount][colCount];

            DataFormatter formatter = new DataFormatter();

            for (int i = 0; i < rowCount; i++) {
                XSSFRow row = sheet.getRow(i + 1);

                if (row == null) {
                    for (int j = 0; j < colCount; j++) {
                        data[i][j] = "";
                    }
                    continue;
                }

                for (int j = 0; j < colCount; j++) {
                    XSSFCell cell = row.getCell(j);
                    String cellData = "";

                    if (cell != null) {
                        cellData = formatter.formatCellValue(cell);
                    }
                    data[i][j] = cellData;
                }
            }

        } catch (IOException e) {
            System.err.println("Failed to read Excel file: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Error reading Excel data from file: " + fileName, e);
        } finally {
            try {
                if (workbook != null) workbook.close();
                if (is != null) is.close();
            } catch (IOException e) {
                System.err.println("Error closing resources: " + e.getMessage());
            }
        }
        return data;
    }
}
